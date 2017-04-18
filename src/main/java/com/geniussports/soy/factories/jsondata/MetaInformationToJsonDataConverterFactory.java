package com.geniussports.soy.factories.jsondata;

import com.geniussports.soy.context.JsonDataFactoryContext;
import com.geniussports.soy.factories.JsonConverterFactory;
import com.geniussports.soy.meta.MetaContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import java.util.function.Function;

@Singleton
public class MetaInformationToJsonDataConverterFactory implements JsonConverterFactory {

    private MetaContext metaContext;

    @Inject
    public MetaInformationToJsonDataConverterFactory(MetaContext metaContext) {
        this.metaContext = metaContext;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return metaContext.hasFactory(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ?> create(@Nonnull Class<?> classObject,
                                      @Nonnull JsonDataFactoryContext context) {
        return new MetaInformationToJsonDataConverterConverter(context).apply(metaContext.apply(classObject));
    }
}
