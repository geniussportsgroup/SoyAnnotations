package com.geniussports.soy.helpers;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface FactoryHelper {

    @Nonnull
    Boolean isFactoryClass(@Nonnull Class<?> classObject);

    @Nonnull
    Boolean isDynamicFactoryClass(@Nonnull Class<?> classObject);

    @Nonnull
    Boolean isFactoryMethod(@Nonnull Method method);

    @Nonnull
    Boolean isDynamicFactoryMethod(@Nonnull Method method);

    @Nonnull
    Boolean isFactoryField(@Nonnull Field field);

    @Nonnull
    Boolean isDynamicFactoryField(@Nonnull Field field);

    @Nonnull
    Boolean hasTemplate(@Nonnull Class<?> classObject);

    @Nonnull
    String getTemplateName(@Nonnull Class<?> classObject);

    @Nonnull
    String getFieldKey(@Nonnull Field field);

    @Nonnull
    String getMethodKey(@Nonnull Method field);

    @Nonnull
    Boolean useOriginalToString(@Nonnull Class<?> classObject);
}

