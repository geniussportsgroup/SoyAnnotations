package com.geniussports.soy.converters.json;


import com.google.inject.Singleton;

import java.util.function.Function;

@Singleton
public class NativeJsonConverter implements Function<Object, Object> {
    @Override
    public Object apply(Object from) {
        return from;
    }
}
