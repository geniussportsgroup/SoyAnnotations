package com.geniussports.soy.factories;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;
import java.util.function.Function;

public interface SoyConverterFactory {

    @Nonnull
    Boolean canCreate(@Nonnull Class<?> classObject);

    @Nonnull
    Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject,
                                               @Nonnull SoyValueFactoryContext context);
}
