package com.geniussports.soy.configuration;

import com.google.inject.AbstractModule;

import javax.annotation.Nonnull;

public class SingletonModule extends AbstractModule {

    private final Class<?> classObject;

    public SingletonModule(@Nonnull Class<?> classObject) {
        this.classObject = classObject;
    }

    @Override
    protected void configure() {
        bind(classObject).asEagerSingleton();
    }
}
