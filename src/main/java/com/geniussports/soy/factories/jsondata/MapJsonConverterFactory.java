package com.geniussports.soy.factories.jsondata;

import com.geniussports.soy.context.JsonDataFactoryContext;
import com.geniussports.soy.converters.json.DynamicConverter;
import com.geniussports.soy.converters.json.JsonMapConverter;
import com.geniussports.soy.factories.JsonConverterFactory;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;

@Singleton
public class MapJsonConverterFactory implements JsonConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ?> create(@Nonnull Class<?> classObject,
                                      @Nonnull JsonDataFactoryContext context) {
        return new JsonMapConverter(new DynamicConverter(context));
    }
}
