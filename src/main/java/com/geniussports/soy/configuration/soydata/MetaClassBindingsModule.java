package com.geniussports.soy.configuration.soydata;

import com.geniussports.soy.factories.SoyConverterFactory;
import com.geniussports.soy.factories.soydata.MetaInformationSoyDataConverterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import static com.google.inject.multibindings.Multibinder.newSetBinder;


public class MetaClassBindingsModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<SoyConverterFactory> binder = newSetBinder(binder(), SoyConverterFactory.class);
        binder.addBinding().to(MetaInformationSoyDataConverterFactory.class);
    }
}
