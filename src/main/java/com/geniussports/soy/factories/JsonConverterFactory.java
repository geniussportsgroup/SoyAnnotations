package com.geniussports.soy.factories;

import com.geniussports.soy.context.JsonDataFactoryContext;

import javax.annotation.Nonnull;
import java.util.function.Function;

public interface JsonConverterFactory {

    @Nonnull
    Boolean canCreate(@Nonnull Class<?> classObject);

    @Nonnull
    Function<Object, ?> create(@Nonnull Class<?> classObject,
                               @Nonnull JsonDataFactoryContext context);
}
