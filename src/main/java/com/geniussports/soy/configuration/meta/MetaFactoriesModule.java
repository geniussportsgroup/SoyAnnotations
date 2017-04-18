package com.geniussports.soy.configuration.meta;

import com.geniussports.soy.factories.MetaConverterFactory;
import com.geniussports.soy.meta.MetaConverterClassConverter;
import com.geniussports.soy.meta.MetaCustomClassConverterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class MetaFactoriesModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<MetaConverterFactory> binder = newSetBinder(binder(), MetaConverterFactory.class);
        binder.addBinding().to(MetaConverterClassConverter.class);
        binder.addBinding().to(MetaCustomClassConverterFactory.class);
    }
}
