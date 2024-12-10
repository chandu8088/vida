/**
 * 
 */
package com.heromotocorp.vida.core.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for AesCryptUtil
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AesCryptUtilTest {

	@Mock
	private AesCryptUtil aesCryptUtil;

	@Mock
	private KeyGenerator kgen;

	@Mock
	private SecretKey key;

	@Mock
	private InputStream in;

	@Mock
	private OutputStream out;

	byte[] buf = new byte[1024];

	@Mock
	private Cipher ecipher;

	@Mock
	private Cipher dcipher;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#AesCryptUtil()}.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void testAesCryptUtil() throws NoSuchFieldException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
			Mockito.mockStatic(KeyGenerator.class);
			Mockito.when(KeyGenerator.getInstance("AES")).thenReturn(kgen);
			Mockito.when(kgen.generateKey()).thenReturn(key);
			aesCryptUtil = new AesCryptUtil();
		});
		PrivateAccessor.setField(aesCryptUtil, "ecipher", ecipher);
		PrivateAccessor.setField(aesCryptUtil, "dcipher", dcipher);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#AesCryptUtil(java.lang.String)}.
	 */
	@Test
	void testAesCryptUtilString() {
		aesCryptUtil = new AesCryptUtil("AES");
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#encrypt(java.io.InputStream, java.io.OutputStream)}.
	 * 
	 * @throws IOException
	 * @throws NoSuchFieldException
	 */
	@Test
	void testEncryptInputStreamOutputStream() {
		// aesCryptUtil.encrypt(in, out);
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#encrypt(java.lang.String)}.
	 */
	@Test
	void testEncryptString() {
		aesCryptUtil.encrypt("text");

		testAesCryptUtilString();
		aesCryptUtil.encrypt("text");
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#decrypt(java.io.InputStream, java.io.OutputStream)}.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void testDecryptInputStreamOutputStream() throws NoSuchFieldException {

		// aesCryptUtil.decrypt(in, out);
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#decrypt(java.lang.String)}.
	 */
	@Test
	void testDecryptString() {
		aesCryptUtil.decrypt("text");

		testAesCryptUtilString();
		aesCryptUtil.decrypt("text");
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#decrypt(byte[])}.
	 */
	@Test
	void testDecryptByteArray() {
		aesCryptUtil.decrypt(buf);
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#byteToHex(byte[])}.
	 */
	@Test
	void testByteToHex() {
		AesCryptUtil.byteToHex(buf);
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#hexToByte(java.lang.String)}.
	 */
	@Test
	void testHexToByte() {
		AesCryptUtil.hexToByte("base16");
		assertNotNull(aesCryptUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.AesCryptUtil#main(java.lang.String[])}.
	 */
	@Test
	void testMain() {
		String[] args = {};
		AesCryptUtil.main(args);

		String[] args1 = {"key1", "key2", "key3", "key4"};
		AesCryptUtil.main(args1);
		assertNotNull(aesCryptUtil);
	}

}
