package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.BooleanDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class BooleanDataConverterFactory implements SoyConverterFactory {

    private final BooleanDataConverter booleanDataConverter;

    @Inject
    public BooleanDataConverterFactory(BooleanDataConverter booleanDataConverter) {
        this.booleanDataConverter = booleanDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return classObject.equals(Boolean.class) || classObject.equals(boolean.class);
    }

    @Nonnull
    @Override
    public BooleanDataConverter create(@Nonnull Class<?> classObject,
                                       @Nonnull SoyValueFactoryContext context) {
        checkArgument(canCreate(classObject));
        return booleanDataConverter;
    }
}
