package com.geniussports.soy.configuration.jsondata;

import com.geniussports.soy.factories.JsonConverterFactory;
import com.geniussports.soy.factories.jsondata.MetaInformationToJsonDataConverterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class MetaJsonDataConvertersFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<JsonConverterFactory> binder = newSetBinder(binder(), JsonConverterFactory.class);
        binder.addBinding().to(MetaInformationToJsonDataConverterFactory.class);
    }
}
