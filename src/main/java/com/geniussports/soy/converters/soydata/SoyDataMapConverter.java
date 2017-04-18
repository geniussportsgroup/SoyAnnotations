package com.geniussports.soy.converters.soydata;


import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public class SoyDataMapConverter implements Function<Object, SoyMapData> {

    private final Function<Object, ? extends SoyData> typeConverter;

    public SoyDataMapConverter(Function<Object, ? extends SoyData> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    @Nullable
    public SoyMapData apply(@Nullable Object object) {
        if (object instanceof Map) {
            SoyMapData soyMapData = new SoyMapData();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) object).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (key instanceof String) {
                    soyMapData.put((String) key, typeConverter.apply(value));
                }
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
