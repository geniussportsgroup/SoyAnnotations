package com.geniussports.soy.meta;

import com.geniussports.soy.converters.meta.MetaClassConverter;
import com.geniussports.soy.factories.MetaConverterFactory;
import com.geniussports.soy.helpers.FactoryHelper;
import com.google.inject.Inject;

import javax.annotation.Nonnull;

public class MetaConverterClassConverter implements MetaConverterFactory {

    private final FactoryHelper factoryHelper;

    @Inject
    public MetaConverterClassConverter(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return factoryHelper.isFactoryClass(classObject);
    }

    @Nonnull
    @Override
    public MetaClassConverter create(@Nonnull Class<?> classObject) {
        return new MetaClassConverter(factoryHelper);
    }
}
