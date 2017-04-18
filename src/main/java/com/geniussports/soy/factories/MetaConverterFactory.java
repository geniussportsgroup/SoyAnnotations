package com.geniussports.soy.factories;

import com.geniussports.soy.meta.MetaClassInformation;

import javax.annotation.Nonnull;
import java.util.function.Function;

public interface MetaConverterFactory {

    @Nonnull
    Boolean canCreate(Class<?> classObject);

    @Nonnull
    Function<Class<?>, MetaClassInformation> create(@Nonnull Class<?> classObject);
}
