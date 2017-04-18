package com.geniussports.soy.meta;

import java.lang.reflect.Method;
import java.util.function.Function;

public class MemberMethodValueConverter implements Function<Object, Object> {

    private final Method method;

    public MemberMethodValueConverter(Method method) {
        this.method = method;
    }

    @Override
    public Object apply(Object from) {
        try {
            return method.invoke(from);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
