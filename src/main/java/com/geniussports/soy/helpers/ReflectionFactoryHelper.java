package com.geniussports.soy.helpers;


import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import static com.google.common.primitives.Primitives.isWrapperType;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

@Singleton
public class ReflectionFactoryHelper implements FactoryHelper {

    @Nonnull
    @Override
    public Boolean isFactoryClass(@Nonnull Class<?> classObject) {
        return !isContainerType(classObject) && !isPrimitiveType(classObject);
    }

    @Nonnull
    @Override
    public Boolean isDynamicFactoryClass(@Nonnull Class<?> classObject) {
        return isContainerType(classObject) || !isPrimitiveType(classObject);
    }

    @Nonnull
    @Override
    public Boolean isFactoryMethod(@Nonnull Method method) {
        return !isStatic(method.getModifiers()) &&
                isPublic(method.getModifiers()) &&
                !method.getReturnType().equals(Void.TYPE) &&
                method.getParameterTypes().length == 0;
    }

    @Nonnull
    @Override
    public Boolean isDynamicFactoryMethod(@Nonnull Method method) {
        return isDynamicFactoryClass(method.getReturnType());
    }

    @Nonnull
    @Override
    public Boolean isFactoryField(@Nonnull Field field) {
        return !isStatic(field.getModifiers()) &&
                isPublic(field.getModifiers());
    }

    @Nonnull
    @Override
    public Boolean isDynamicFactoryField(@Nonnull Field field) {
        return isDynamicFactoryClass(field.getType());
    }

    @Nonnull
    @Override
    public Boolean hasTemplate(@Nonnull Class<?> classObject) {
        return Boolean.FALSE;
    }

    @Nonnull
    @Override
    public String getTemplateName(@Nonnull Class<?> classObject) {
        throw new IllegalStateException("ReflectionFactoryHelper does not support templates.");
    }

    @Nonnull
    @Override
    public String getFieldKey(@Nonnull Field field) {
        return field.getName();
    }

    @Nonnull
    @Override
    public String getMethodKey(@Nonnull Method field) {
        String name = field.getName();
        if (name.startsWith("get")) {
            name = name.substring(3);
        }
        return name;
    }

    @Nonnull
    @Override
    public Boolean useOriginalToString(@Nonnull Class<?> classObject) {
        return true;
    }

    private Boolean isContainerType(Class<?> classObject) {
        return Collection.class.isAssignableFrom(classObject) || Map.class.isAssignableFrom(classObject);
    }

    private Boolean isPrimitiveType(Class<?> classObject) {
        return isWrapperType(classObject) || String.class.equals(classObject) || classObject.isPrimitive();
    }
}
