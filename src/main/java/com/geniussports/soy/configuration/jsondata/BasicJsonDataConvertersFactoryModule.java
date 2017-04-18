package com.geniussports.soy.configuration.jsondata;

import com.geniussports.soy.factories.JsonConverterFactory;
import com.geniussports.soy.factories.jsondata.ListJsonConverterFactory;
import com.geniussports.soy.factories.jsondata.MapJsonConverterFactory;
import com.geniussports.soy.factories.jsondata.NativeJsonConverterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class BasicJsonDataConvertersFactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<JsonConverterFactory> binder = newSetBinder(binder(), JsonConverterFactory.class);
        binder.addBinding().to(NativeJsonConverterFactory.class);
        binder.addBinding().to(MapJsonConverterFactory.class);
        binder.addBinding().to(ListJsonConverterFactory.class);
    }
}
