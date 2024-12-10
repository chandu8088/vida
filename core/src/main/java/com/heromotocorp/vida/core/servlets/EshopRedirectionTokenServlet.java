package com.heromotocorp.vida.core.servlets;

import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.utils.RSADecryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = HttpConstants.METHOD_GET, selectors = "getToken", extensions = "json")
@SuppressWarnings({"squid:S2070","squid:S5542"})
@ServiceDescription("Eshop Redirection Token Servlet")
public class EshopRedirectionTokenServlet extends SlingSafeMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(EshopRedirectionTokenServlet.class);

    @Reference
    private transient GlobalConfig globalConfig;

    private final String ENCRYPTION_VALUE = "encypVal";

    private final String REDIRECTION = "redirectUrl";

    @Reference
    private transient ResourceResolverFactory resolverFactory;

    /**
     * Called by the
     * {@link #mayService(SlingHttpServletRequest, SlingHttpServletResponse)} method to
     * handle an HTTP <em>GET</em> request.
     * <p>
     * This default implementation reports back to the client that the method is
     * not supported.
     * <p>
     * Implementations of this class should overwrite this method with their
     * implementation for the HTTP <em>GET</em> method support.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @throws ServletException Not thrown by this implementation.
     * @throws IOException      If the error status cannot be reported back to the
     *                          client.
     */
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try{
            ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.READERSERVICEUSER);
            RSADecryptionUtil util = RSADecryptionUtil.encriptParams(resolver, StringUtils.EMPTY);
            String encryptedValue = request.getParameter(ENCRYPTION_VALUE);
            String[] values = encryptedValue.split("\\|");
            log.info("recieved values",values[0],values[1],values.length);
            String decryptedEmail = util.decrypt(values[0].contains(" ") ? values[0].replaceAll(" ", "+") : values[0]);
            String decryptedMob = util.decrypt(values[1].contains(" ") ? values[1].replaceAll(" ", "+") : values[1]);
            String redirectionUrl = request.getParameter("redirectUrl");
            URI uri = new URI(redirectionUrl);
            String redirectionQueryString = (uri.getQuery() != null) ? ("?" + uri.getQuery()) : StringUtils.EMPTY;
            String redirectionPath = uri.getPath() + redirectionQueryString;
            String token = generateToken(globalConfig.eshopTokenKey(), decryptedEmail,decryptedMob,redirectionPath);
            response.setContentType("text/plain");
            response.getWriter().write(globalConfig.eshopRedirectionURL().concat(token));
        }
        catch (IllegalBlockSizeException | BadPaddingException | URISyntaxException e){
            log.error("error while decrypting mobile number and email",e);
        }
    }

    private String generateToken(String key, String email, String mobNumber, String redirectionUrl){
        try {
            // Step 1: Generate MD5 hash and extract keys
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(key.getBytes(StandardCharsets.UTF_8));
            String hashHex = bytesToHex(hash);

            // Split hash into encryption and signing keys
            String encryptionKeyHex = hashHex.substring(0, 16);
            String signingKeyHex = hashHex.substring(16, 32);

            byte[] encryptionKey = encryptionKeyHex.getBytes(StandardCharsets.UTF_8);
            byte[] signingKey = signingKeyHex.getBytes(StandardCharsets.UTF_8);

            String userInfo = "{\"email\":\"%s\",\"phone\":\"%s\",\"return_to\":\"%s\"}";
            String userData = String.format(userInfo, email, mobNumber, redirectionUrl);
            byte[] iv = new byte[16]; // generate 16-byte IV
            new java.security.SecureRandom().nextBytes(iv);

            byte[] verUser = encryptToken(encryptionKey, userData.getBytes(StandardCharsets.UTF_8), iv);

            // Step 3: Calculate HMAC
            byte[] verHmac = calculateHmac(signingKey, verUser);

            // Step 4: Concatenate verUser and verHmac, then encode as base64
            byte[] tokenBytes = new byte[verUser.length + verHmac.length];
            System.arraycopy(verUser, 0, tokenBytes, 0, verUser.length);
            System.arraycopy(verHmac, 0, tokenBytes, verUser.length, verHmac.length);

            String token = Base64.getEncoder().encodeToString(tokenBytes);
            token = token.replace("+", "-");
            return token;

        } catch (Exception e) {
            log.error("error in eshop redirection servlet",e);
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Equivalent to Node.js calculateHmac function
    private static byte[] calculateHmac(byte[] key, byte[] data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data);
    }

    // Equivalent to Node.js encryptToken function
    private static byte[] encryptToken(byte[] key, byte[] data, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedData = cipher.doFinal(data);
        byte[] result = new byte[iv.length + encryptedData.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encryptedData, 0, result, iv.length, encryptedData.length);
        return result;
    }
}
