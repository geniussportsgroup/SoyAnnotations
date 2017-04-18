package com.geniussports.soy.converters.json;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonMapConverter implements Function<Object, Map> {

    private final Function<Object, ?> typeConverter;

    public JsonMapConverter(@Nonnull Function<Object, ?> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public Map apply(Object from) {
        if (from instanceof Map<?, ?>) {
            return ((Map<?, ?>) from)
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> typeConverter.apply(entry.getValue())));
        } else {
            return null;
        }
    }
}
