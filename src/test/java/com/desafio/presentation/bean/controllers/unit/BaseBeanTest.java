package com.desafio.presentation.bean.controllers.unit;

import com.sun.faces.config.InitFacesContext;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseBeanTest {
    @Mock
    protected FacesContext context;

    public void setUp(){
        ServletContext sc = mock(ServletContext.class);
        new FakeContext(sc);
        assertEquals(context, FacesContext.getCurrentInstance());
    }

    private class FakeContext extends InitFacesContext {

        public FakeContext(ServletContext sc) {
            super(sc);
            setCurrentInstance(context);
        }
    }
}
