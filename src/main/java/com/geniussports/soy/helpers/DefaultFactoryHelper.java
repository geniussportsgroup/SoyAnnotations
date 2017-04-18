package com.geniussports.soy.helpers;

import com.geniussports.soy.annotations.Soy;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkState;

@Singleton
public class DefaultFactoryHelper implements FactoryHelper {

    public DefaultFactoryHelper() {}

    @Override
    @Nonnull
    public Boolean isFactoryClass(@Nonnull Class<?> classObject) {
        return getSoyAnnotatedClass(classObject) != null;
    }

    @Override
    @Nonnull
    public Boolean isDynamicFactoryClass(@Nonnull Class<?> classObject) {
        Class<?> annotatedClass = getSoyAnnotatedClass(classObject);
        return annotatedClass != null && annotatedClass.isAnnotationPresent(Soy.Dynamic.class);
    }

    @Override
    @Nonnull
    public Boolean isFactoryMethod(@Nonnull Method method) {
        return method.isAnnotationPresent(Soy.Method.class);
    }

    @Override
    @Nonnull
    public Boolean isDynamicFactoryMethod(@Nonnull Method method) {
        return method.isAnnotationPresent(Soy.Dynamic.class);
    }

    @Override
    @Nonnull
    public Boolean isFactoryField(@Nonnull Field field) {
        return field.isAnnotationPresent(Soy.Field.class);
    }

    @Override
    @Nonnull
    public Boolean isDynamicFactoryField(@Nonnull Field field) {
        return field.isAnnotationPresent(Soy.Dynamic.class);
    }

    @Override
    @Nonnull
    public Boolean hasTemplate(@Nonnull Class<?> classObject) {
        Class<?> annotatedClass = getSoyAnnotatedClass(classObject);
        return annotatedClass != null &&
                annotatedClass.isAnnotationPresent(Soy.Template.class);
    }

    @Override
    @Nonnull
    public String getTemplateName(@Nonnull Class<?> classObject) {
        Class<?> annotatedClass = getSoyAnnotatedClass(classObject);
        if (annotatedClass != null) {
            return annotatedClass.getAnnotation(Soy.Template.class).value();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    @Nonnull
    public String getFieldKey(@Nonnull Field field) {
        checkState(isFactoryField(field));
        String fieldValue = field.getAnnotation(Soy.Field.class).value();
        return fieldValue.isEmpty() ? field.getName() : fieldValue;
    }

    @Override
    @Nonnull
    public String getMethodKey(@Nonnull Method field) {
        return field.getAnnotation(Soy.Method.class).value();
    }

    @Nonnull
    @Override
    public Boolean useOriginalToString(@Nonnull Class<?> classObject) {
        Class<?> annotatedClass = getSoyAnnotatedClass(classObject);
        if (annotatedClass != null) {
            return annotatedClass.getAnnotation(Soy.class).useOriginalToString();
        } else {
            return false;
        }
    }

    @Nullable
    private Class<?> getSoyAnnotatedClass(@Nullable Class<?> classObject) {
        if (classObject == null || classObject.equals(Object.class)) {
            return null;
        } else if (classObject.isAnnotationPresent(Soy.class)) {
            return classObject;
        } else {
            return getSoyAnnotatedClass(classObject.getSuperclass());
        }
    }
}
