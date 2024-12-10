package com.heromotocorp.vida.core.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class BlogsRedirectionBeansModelTest {

    @InjectMocks
    BlogsRedirectionBeansModel blogsRedirectionBeansModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        blogsRedirectionBeansModel.setBlogCardDesktopImg("imgDeskop");
        blogsRedirectionBeansModel.setBlogCardMobileImg("imgMobile");
        blogsRedirectionBeansModel.setIsVideo(true);
        blogsRedirectionBeansModel.setNewTab(true);
        blogsRedirectionBeansModel.setBlogCardDescription("description");
        blogsRedirectionBeansModel.setCardNavLink("/text");
        blogsRedirectionBeansModel.setImageAlt("altImage");
        blogsRedirectionBeansModel.setImageTitle("imageTitle");
    }

    @Test
    void getGenericData() {
        assertEquals("imgDeskop",blogsRedirectionBeansModel.getBlogCardDesktopImg());
        assertEquals("imgMobile",blogsRedirectionBeansModel.getBlogCardMobileImg());
        assertEquals(true,blogsRedirectionBeansModel.getIsVideo());
        assertEquals(true,blogsRedirectionBeansModel.getNewTab());
        assertEquals("description",blogsRedirectionBeansModel.getBlogCardDescription());
        assertEquals("/text",blogsRedirectionBeansModel.getCardNavLink());
        assertEquals("altImage",blogsRedirectionBeansModel.getImageAlt());
        assertEquals("imageTitle",blogsRedirectionBeansModel.getImageTitle());
    }

}