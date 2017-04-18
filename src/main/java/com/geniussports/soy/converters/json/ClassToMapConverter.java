package com.geniussports.soy.converters.json;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class ClassToMapConverter implements Function<Object, Map<String, Object>> {

    public final Map<String, Function<Object, ?>> membersConverters;

    public ClassToMapConverter(Map<String, Function<Object, ?>> membersConverters) {
        this.membersConverters = membersConverters;
    }

    @Override
    public Map<String, Object> apply(Object from) {
        if (from != null) {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, Function<Object, ?>> entry : membersConverters.entrySet()) {
                map.put(entry.getKey(), entry.getValue().apply(from));
            }
            return map;
        } else {
            return null;
        }
    }
}
