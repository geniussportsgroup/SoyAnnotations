package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.geniussports.soy.meta.MetaContext;
import com.geniussports.soy.meta.MetaInformationToSoyDataConverter;
import com.google.inject.Inject;
import com.google.template.soy.data.SoyMapData;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class MetaInformationSoyDataConverterFactory implements SoyConverterFactory {

    private final MetaContext metaContext;

    @Inject
    public MetaInformationSoyDataConverterFactory(MetaContext metaContext) {
        this.metaContext = metaContext;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return metaContext.hasFactory(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, SoyMapData> create(@Nonnull Class<?> classObject,
                                               @Nonnull SoyValueFactoryContext context) {
        final MetaInformationToSoyDataConverter metaInformationToSoyDataConverter = new MetaInformationToSoyDataConverter(context);
        return metaInformationToSoyDataConverter.apply(metaContext.apply(classObject));
    }
}
