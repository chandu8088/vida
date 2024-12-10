package com.heromotocorp.vida.core.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ImageVariantBeanModelTest {

    @InjectMocks
    ImageVariantBeanModel imageVariantBeanModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        imageVariantBeanModel.setAltText("alt");;
        imageVariantBeanModel.setOrder("1");
        imageVariantBeanModel.setSectionid("1");
        imageVariantBeanModel.setVarinatDesktopImage("/content/desk.png");
        imageVariantBeanModel.setVarinatMobileImage("/content/mob.png");
    }

    @Test
    void getImageVariantData() {
        assertEquals("alt",imageVariantBeanModel.getAltText());
        assertEquals("1",imageVariantBeanModel.getOrder());
        assertEquals("1", imageVariantBeanModel.getSectionid());
        assertEquals("/content/desk.png",imageVariantBeanModel.getVarinatDesktopImage());
        assertEquals("/content/mob.png",imageVariantBeanModel.getVarinatMobileImage());
    }
}