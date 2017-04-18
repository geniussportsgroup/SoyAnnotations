package com.geniussports.soy.configuration.soydata;

import com.geniussports.soy.factories.SoyConverterFactory;
import com.geniussports.soy.factories.soydata.LazyClassToSoyMapDataConverterFactory;
import com.geniussports.soy.factories.soydata.LazySoyListDataConverterFactory;
import com.geniussports.soy.factories.soydata.LazySoyMapDataConverterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import static com.google.inject.multibindings.Multibinder.newSetBinder;


public class LazyClassSoyFactoriesModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<SoyConverterFactory> binder = newSetBinder(binder(), SoyConverterFactory.class);
        binder.addBinding().to(LazySoyListDataConverterFactory.class);
        binder.addBinding().to(LazySoyMapDataConverterFactory.class);
        binder.addBinding().to(LazyClassToSoyMapDataConverterFactory.class);
    }
}
