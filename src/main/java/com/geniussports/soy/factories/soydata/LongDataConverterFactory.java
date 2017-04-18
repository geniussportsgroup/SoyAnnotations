package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.LongDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;

@Singleton
public class LongDataConverterFactory implements SoyConverterFactory {

    private final LongDataConverter longDataConverter;

    @Inject
    public LongDataConverterFactory(LongDataConverter longDataConverter) {
        this.longDataConverter = longDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Long.class.equals(classObject) || classObject.equals(long.class);
    }

    @Nonnull
    @Override
    public LongDataConverter create(@Nonnull Class<?> classObject, @Nonnull SoyValueFactoryContext context) {
        return this.longDataConverter;
    }


}
