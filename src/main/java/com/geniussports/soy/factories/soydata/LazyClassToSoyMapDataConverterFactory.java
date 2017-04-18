package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.LazyMapDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Inject;

import javax.annotation.Nonnull;

public class LazyClassToSoyMapDataConverterFactory implements SoyConverterFactory {

    private final MetaInformationSoyDataConverterFactory metaClassToSoyMapDataConverterFactory;

    @Inject
    public LazyClassToSoyMapDataConverterFactory(MetaInformationSoyDataConverterFactory classToSoyMapDataConverterFactory) {
        this.metaClassToSoyMapDataConverterFactory = classToSoyMapDataConverterFactory;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return metaClassToSoyMapDataConverterFactory.canCreate(classObject);
    }

    @Nonnull
    @Override
    public LazyMapDataConverter create(@Nonnull Class<?> classObject,
                                       @Nonnull SoyValueFactoryContext context) {
        return new LazyMapDataConverter(metaClassToSoyMapDataConverterFactory.create(classObject, context));
    }
}
