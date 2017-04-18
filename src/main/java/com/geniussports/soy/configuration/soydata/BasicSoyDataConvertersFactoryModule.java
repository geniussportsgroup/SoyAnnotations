package com.geniussports.soy.configuration.soydata;

import com.geniussports.soy.factories.SoyConverterFactory;
import com.geniussports.soy.factories.soydata.BooleanDataConverterFactory;
import com.geniussports.soy.factories.soydata.FloatDataConverterFactory;
import com.geniussports.soy.factories.soydata.IntegerDataConverterFactory;
import com.geniussports.soy.factories.soydata.LongDataConverterFactory;
import com.geniussports.soy.factories.soydata.SoyDataConverterFactory;
import com.geniussports.soy.factories.soydata.SoyListDataConverterFactory;
import com.geniussports.soy.factories.soydata.SoyMapDataConverterFactory;
import com.geniussports.soy.factories.soydata.StringDataConverterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class BasicSoyDataConvertersFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<SoyConverterFactory> binder = newSetBinder(binder(), SoyConverterFactory.class);
        binder.addBinding().to(SoyMapDataConverterFactory.class);
        binder.addBinding().to(SoyListDataConverterFactory.class);
        binder.addBinding().to(LongDataConverterFactory.class);
        binder.addBinding().to(BooleanDataConverterFactory.class);
        binder.addBinding().to(FloatDataConverterFactory.class);
        binder.addBinding().to(IntegerDataConverterFactory.class);
        binder.addBinding().to(StringDataConverterFactory.class);
        binder.addBinding().to(SoyDataConverterFactory.class);
    }
}
