package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.vo.HeaderVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class GenericDataModelTest {

    @InjectMocks
    GenericDataModel genericDataModel;

    @Mock
    private HeaderVO headerVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genericDataModel.setLabel("Label");
        genericDataModel.setTitle("title");
        genericDataModel.setImage("Image");
        genericDataModel.setNavLink("/test");
        genericDataModel.setName("name");
        genericDataModel.setSecondarynavtext("secNavText");
        genericDataModel.setSecondarynavtextlink("/text");
        genericDataModel.setMobImg("mobImg");
        genericDataModel.setDesktopImg("desktopImg");
        genericDataModel.setNewTab(true);
        genericDataModel.setIsButtton(true);
        genericDataModel.setImagealttext("altText");
        genericDataModel.setImageTitle("imageTitle");
    }

    @Test
    void getGenericData() {
        assertEquals("Label",genericDataModel.getLabel());
        assertEquals("title",genericDataModel.getTitle());
        assertEquals("Image",genericDataModel.getImage());
        assertEquals("/test",genericDataModel.getNavLink());
        assertEquals("name",genericDataModel.getName());
        assertEquals("secNavText",genericDataModel.getSecondarynavtext());
        assertEquals("/text",genericDataModel.getSecondarynavtextlink());
        assertEquals("mobImg",genericDataModel.getMobImg());
        assertEquals("desktopImg",genericDataModel.getDesktopImg());
        assertEquals(true,genericDataModel.getNewTab());
        assertEquals(true,genericDataModel.getIsButton());
        assertEquals("altText", genericDataModel.getImagealttext());
        assertEquals("imageTitle", genericDataModel.getImageTitle());
    }

}