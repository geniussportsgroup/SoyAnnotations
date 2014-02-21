package slieb.soy.internal;


import ch.lambdaj.function.convert.Converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodConverter<A>
        implements Converter<Object, A> {

    private final Method method;

    private final Converter<Object, A> typeConverter;

    public MethodConverter(Method method, Converter<Object, A> typeConverter) {
        this.method = method;
        this.typeConverter = typeConverter;
    }

    @Override
    public A convert(Object a) {
        try {
            return typeConverter.convert(method.invoke(a));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }
}
