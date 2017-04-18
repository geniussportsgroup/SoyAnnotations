package com.geniussports.soy.meta;


import java.util.Map;
import java.util.function.Function;

public interface MetaConverter extends Function<Object, Map<String, ?>> {

    @Override
    public Map<String, ?> apply(Object from);
}
