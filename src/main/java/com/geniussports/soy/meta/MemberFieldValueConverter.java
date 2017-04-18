package com.geniussports.soy.meta;

import org.slieb.throwables.FunctionWithThrowable;

import java.lang.reflect.Field;

public class MemberFieldValueConverter implements FunctionWithThrowable<Object, Object, ReflectiveOperationException> {

    private final Field field;

    public MemberFieldValueConverter(Field field) {
        this.field = field;
    }

    @Override
    public Object applyWithThrowable(final Object from) throws ReflectiveOperationException {
        return field.get(from);
    }
}
