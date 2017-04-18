package com.geniussports.soy.configuration;

import com.geniussports.soy.helpers.DefaultFactoryHelper;
import com.geniussports.soy.helpers.FactoryHelper;
import com.google.inject.AbstractModule;

public class DefaultFactoryHelperModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(FactoryHelper.class)
                .to(DefaultFactoryHelper.class)
                .asEagerSingleton();

    }
}
