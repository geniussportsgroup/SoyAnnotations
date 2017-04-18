package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.StringDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import javax.inject.Inject;

@Singleton
public class StringDataConverterFactory implements SoyConverterFactory {

    private final StringDataConverter stringDataConverter;

    @Inject
    public StringDataConverterFactory(StringDataConverter stringDataConverter) {
        this.stringDataConverter = stringDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        // todo, make separate factory class for enums?
        return String.class.equals(classObject) || classObject.isEnum();
    }

    @Nonnull
    @Override
    public StringDataConverter create(@Nonnull Class<?> classObject,
                                      @Nonnull SoyValueFactoryContext context) {
        return stringDataConverter;
    }


}
