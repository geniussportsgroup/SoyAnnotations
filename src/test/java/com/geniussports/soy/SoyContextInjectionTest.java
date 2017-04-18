package com.geniussports.soy;

import com.geniussports.soy.context.JsonDataFactoryContext;
import com.geniussports.soy.context.RendererFactoryContext;
import com.geniussports.soy.context.SoyValueFactoryContext;
import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class SoyContextInjectionTest {

    private SoyTofu soyTofu = SoyFileSet.builder()
                                        .add(getClass().getResource("/templates.soy"))
                                        .build().compileToTofu();

    @Test
    public void testBasicInjection() {
        Injector injector = Loader.getBasicInjector();
        // todo, maybe test with == instead
        Class<SoyValueFactoryContext> soyContextClass = SoyValueFactoryContext.class;
        assertSame(injector.getInstance(soyContextClass), injector.getInstance(soyContextClass));

        Class<JsonDataFactoryContext> jsonContextClass = JsonDataFactoryContext.class;
        assertSame(injector.getInstance(jsonContextClass), injector.getInstance(jsonContextClass));
    }

    @Test(expected = ConfigurationException.class)
    public void testBasicInjectionCannotGiveRenderer() {
        Loader.getBasicInjector().getInstance(RendererFactoryContext.class);
    }

    @Test
    public void testFullInjection() {
        Injector injector = Loader.getFullInjector();
        Class<SoyValueFactoryContext> soyContextClass = SoyValueFactoryContext.class;
        assertSame(injector.getInstance(soyContextClass), injector.getInstance(soyContextClass));

        Class<JsonDataFactoryContext> jsonContextClass = JsonDataFactoryContext.class;
        assertSame(injector.getInstance(jsonContextClass), injector.getInstance(jsonContextClass));
    }

    @Test(expected = ConfigurationException.class)
    public void testFullInjectionCannotGiveRenderer() {
        Loader.getFullInjector().getInstance(RendererFactoryContext.class);
    }

    @Test
    public void testFullInjectionCanSupplyRenderer() {
        Loader.getFullInjector(soyTofu, null)
              .getInstance(RendererFactoryContext.class);
    }

    @Test
    public void testLazyInjection() {
        Injector injector = Loader.getLazyInjector();
        Class<SoyValueFactoryContext> soyContextClass = SoyValueFactoryContext.class;
        assertSame(injector.getInstance(soyContextClass), injector.getInstance(soyContextClass));

        Class<JsonDataFactoryContext> jsonContextClass = JsonDataFactoryContext.class;
        assertSame(injector.getInstance(jsonContextClass), injector.getInstance(jsonContextClass));
    }

    @Test(expected = ConfigurationException.class)
    public void testLazyInjectionCannotGiveRenderer() {
        Loader.getLazyInjector().getInstance(RendererFactoryContext.class);
    }

    @Test
    public void testLazyInjectionCanSupplyRenderer() {
        Loader.getLazyInjector(soyTofu, null)
              .getInstance(RendererFactoryContext.class);
    }
}
