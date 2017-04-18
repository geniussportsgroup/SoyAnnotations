package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.Loader;
import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.exceptions.NeedsDynamicConverterException;
import com.google.inject.Injector;
import example.models.BadDynamicClassExample;
import example.models.BadDynamicFieldExample;
import example.models.BadDynamicMethodExample;
import example.models.GoodDynamicClassExample;
import example.models.GoodDynamicFieldExample;
import example.models.GoodDynamicMethodExample;
import org.junit.Before;
import org.junit.Test;

public class SoyDataConverterFactoryDynamicExceptionsTest {

    private final Injector injector = Loader.getFullInjector();

    private SoyValueFactoryContext factoryContext;

    @Before
    public void setUp() throws Exception {
        factoryContext = injector.getInstance(SoyValueFactoryContext.class);
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicFieldException() {
        factoryContext.apply(new BadDynamicFieldExample()).toString();
    }

    @Test
    public void testGoodDynamicFieldException() {
        factoryContext.apply(new GoodDynamicFieldExample()).toString();
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicMethodException() {
        factoryContext.apply(new BadDynamicMethodExample()).toString();
    }

    @Test
    public void testGoodDynamicMethodException() {
        factoryContext.apply(new GoodDynamicMethodExample()).toString();
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicClassException() {
        factoryContext.apply(new BadDynamicClassExample()).toString();
    }

    @Test
    public void testGoodDynamicClassException() {
        factoryContext.apply(new GoodDynamicClassExample()).toString();
    }
}
