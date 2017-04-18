package com.geniussports.soy.converters.json;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class JsonListConverter implements Function<Object, List> {

    private final Function<Object, ?> typeConverter;

    public JsonListConverter(@Nonnull Function<Object, ?> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public List<?> apply(Object from) {
        if (from instanceof Collection) {
            return ((Collection<?>) from).stream()
                                         .map(typeConverter)
                                         .collect(toList());
        } else {
            return null;
        }
    }
}
