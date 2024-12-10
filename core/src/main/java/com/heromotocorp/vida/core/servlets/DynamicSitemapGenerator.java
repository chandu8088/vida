package com.heromotocorp.vida.core.servlets;

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.commons.inherit.InheritanceValueMap;
import com.day.cq.replication.ReplicationStatus;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.SitemapBean;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = {
        HttpConstants.METHOD_GET }, selectors = { "dynamic-sitemap" }, extensions = { "xml" })
public class DynamicSitemapGenerator extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicSitemapGenerator.class);

    private transient List<String> cityNamesList;

    @Reference
    transient GlobalConfig globalConfig;

    @Reference
    private transient Externalizer externalizer;

    private static final String NS = "http://www.sitemaps.org/schemas/sitemap/0.9";
    private static final String URLSET = "urlset";
    private static final String NO_INDEX = "noindex";
    private static final String URL = "url";
    private static final String LOC = "loc";
    private static final String LAST_MOD = "lastmod";
    private static final String CHANGE_FREQ = "changefreq";
    private static final String PRIORITY = "priority";
    private static final String PAGE_PRIORITY = "pagePriority";

    private static final String ROBOTS_TAG = "cq:robotsTags";

    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");

    private transient XMLStreamWriter stream;

    private transient List<SitemapBean> sitemapList;



    private static final String cityNameResource = "/content/vida/in/en/dealers-locator/jcr:content/root/container/container";


    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {
        sitemapList = new ArrayList<>();
        cityNamesList = new ArrayList<>();
        ResourceResolver resolver = req.getResourceResolver();
        getCitiesList(resolver);
        PageManager pageManager = resolver.adaptTo(PageManager.class);
        Page page = pageManager.getContainingPage(req.getResource());
        addPageToSiteMapList(page);
        for (Iterator<Page> children = page.listChildren(new PageFilter(false, true),
                true); children.hasNext();) {
            addPageToSiteMapList(children.next());
        }
        XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
        try {
        stream = outputFactory.createXMLStreamWriter(resp.getWriter());
        stream.writeStartDocument("UTF-8", "1.0");
        stream.writeStartElement("", URLSET, NS);
        stream.writeNamespace("", NS);
        for(SitemapBean sb : sitemapList){
            String loc = sb.getLoc();
            if (!sb.getRobotsTags().equalsIgnoreCase(NO_INDEX) && !sb.isDisableSitemap()) {
                if(sb.getLoc().equals("/content/vida/in/en")){
                    loc = globalConfig.getDomainForSitemap();
                }
                else if(sb.getLoc().equals("/content/vida/in/en/dealers")|| sb.getLoc().equals("/dealers")){
                    continue;
                }
                else {
                    loc = loc.startsWith("/content/vida/") ?
                            externalizer.relativeLink(req, sb.getLoc()) : sb.getLoc();
                    loc = String.format("%s%s.html", globalConfig.getDomainForSitemap(), loc);
                }
                stream.writeStartElement(NS, URL);
                writeElement(stream, LOC, loc);
                writeElement(stream, LAST_MOD, sb.getLastmod());
                stream.writeEndElement();
            }
        }
            stream.writeEndElement();
            stream.writeEndDocument();
        }
        catch (XMLStreamException e) {
            LOGGER.error("Error in sitemap generating {} ",e.getMessage(),e);
        }finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (XMLStreamException e) {
                    LOGGER.warn("Can not close xml stream writer", e);
                }
            }
        }

    }

    private void getCitiesList(ResourceResolver resolver){
        Resource pageResource = resolver.getResource(cityNameResource);
        Iterator<Resource> childNodes = pageResource.listChildren();
        while (childNodes.hasNext()){
            Resource childNode = childNodes.next();
            if(childNode.getResourceType().equals("vida/components/vida-2.0/content/dealershipFinder")){
                Resource itemsNode = childNode.getChild("items");
                Iterator<Resource> cityNodes = itemsNode.listChildren();
                while (cityNodes.hasNext()){
                    Resource cityName = cityNodes.next();
                    cityNamesList.add(cityName.getValueMap().get("cityName",String.class));
                }
            }

        }
    }
    private void writeElement(final XMLStreamWriter stream, final String elementName, final String text)
            throws XMLStreamException {
        stream.writeStartElement(NS, elementName);
        stream.writeCharacters(text);
        stream.writeEndElement();
    }
    private void addPageToSiteMapList(Page page) {
        String loc = page.getPath();
        String lastMod = "";
        Double priority;
        String robotsTag = "";
        boolean isActivated = false;
        Boolean disableSitemap = null;


        try {
            ReplicationStatus activationStatus = page.adaptTo(ReplicationStatus.class);
            InheritanceValueMap ivm = new HierarchyNodeInheritanceValueMap(page.getContentResource());

            priority = ivm.getInherited(PAGE_PRIORITY, Double.class);
            if (priority == null) {
                priority = 0.0d;
            }

            robotsTag = ivm.getInherited(ROBOTS_TAG, String.class);
            if (robotsTag == null)
                robotsTag = "";

            if (activationStatus != null && activationStatus.isActivated())
                isActivated = activationStatus.isActivated();

            Calendar calender = page.getLastModified();
            if (calender != null) {
                lastMod = DATE_FORMAT.format(calender);
            }

            ValueMap pageProps = page.getProperties();
            if (pageProps != null)
                disableSitemap = pageProps.get("disableSitemap", Boolean.class);
            if (disableSitemap == null)
                disableSitemap = false;

            if (loc != null && lastMod != null) {
                if(loc.contains("/dealers") && !loc.contains("/dealers-locator") && ! loc.contains("/dealers-lead")){
                    for (String city : cityNamesList){
                        String dynamicLocation = loc.concat("/").concat(city);
                        SitemapBean bean = new SitemapBean(dynamicLocation,lastMod,priority,isActivated,robotsTag,disableSitemap);
                        sitemapList.add(bean);
                    }
                }
                SitemapBean bean = new SitemapBean(loc, lastMod, priority, isActivated, robotsTag, disableSitemap);
                sitemapList.add(bean);
            } else {
                LOGGER.info("Cannot add {} to sitemap\n lastmod:{}, priority:{}", loc, lastMod, priority);
            }
        } catch (ClassCastException e) {
            LOGGER.error("Error Occurred for {}", page.getPath(), e);
        }
    }
}
