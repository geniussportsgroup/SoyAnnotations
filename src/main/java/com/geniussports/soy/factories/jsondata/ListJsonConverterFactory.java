package com.geniussports.soy.factories.jsondata;

import com.geniussports.soy.context.JsonDataFactoryContext;
import com.geniussports.soy.converters.json.DynamicConverter;
import com.geniussports.soy.converters.json.JsonListConverter;
import com.geniussports.soy.factories.JsonConverterFactory;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import java.util.function.Function;

@Singleton
public class ListJsonConverterFactory implements JsonConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ?> create(@Nonnull Class<?> classObject,
                                      @Nonnull JsonDataFactoryContext context) {
        return new JsonListConverter(new DynamicConverter(context));
    }
}
