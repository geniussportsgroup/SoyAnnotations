package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.IntegerDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;

@Singleton
public class IntegerDataConverterFactory implements SoyConverterFactory {

    private final IntegerDataConverter integerDataConverter;

    @Inject
    public IntegerDataConverterFactory(IntegerDataConverter integerDataConverter) {
        this.integerDataConverter = integerDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Integer.class.equals(classObject) || classObject.equals(int.class);
    }

    @Nonnull
    @Override
    public IntegerDataConverter create(@Nonnull Class<?> classObject,
                                       @Nonnull SoyValueFactoryContext context) {
        return integerDataConverter;
    }
}
