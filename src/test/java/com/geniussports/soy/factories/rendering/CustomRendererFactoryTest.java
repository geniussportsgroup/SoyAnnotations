package com.geniussports.soy.factories.rendering;

import com.geniussports.soy.annotations.CustomRenderer;
import com.geniussports.soy.annotations.Soy;
import com.geniussports.soy.context.RendererFactoryContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.Nullable;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CustomRendererFactoryTest {

    @Mock private RendererFactoryContext mockRendererFactoryContext;
    private CustomRendererFactory customRendererFactory;

    @Before
    public void setUp() {
        customRendererFactory = new CustomRendererFactory();
    }

    @Test
    public void testRenderParrot() {
        assertTrue(customRendererFactory.canCreate(Parrot.class));
        assertFalse(customRendererFactory.canCreate(Dog.class));
        assertEquals("parrot say whoo", customRendererFactory.create(Parrot.class, mockRendererFactoryContext).render(new Parrot()));
    }

    public static class ParrotRenderer implements Renderer<Object> {

        private final String FORMAT = "parrot say %s";

        public ParrotRenderer() {
        }

        @Nullable
        @Override
        public String render(@Nullable Object instanceObject) {
            return format(FORMAT, instanceObject);
        }
    }

    @Soy
    @CustomRenderer(ParrotRenderer.class)
    public class Parrot {
        @Override
        public String toString() {
            return "whoo";
        }
    }

    public class Dog {
        @Override
        public String toString() {
            return "woof";
        }
    }

}
