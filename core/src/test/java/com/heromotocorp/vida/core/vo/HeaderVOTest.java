package com.heromotocorp.vida.core.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class HeaderVOTest {

    @InjectMocks
    HeaderVO headerVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        headerVO.setName("linkText");
        headerVO.setNavLink("/content/test");
    }

    @Test
    void getLinkTest() {
        assertEquals("linkText",headerVO.getName());
        assertEquals("/content/test",headerVO.getNavLink());
    }

}