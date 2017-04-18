package com.geniussports.soy.converters.soydata;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;
import java.util.function.Function;

public class SoyMapDataConverter implements Function<Object, SoyMapData> {

    private final Function<Object, SoyData> typeConverter;

    public SoyMapDataConverter(Function<Object, SoyData> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public SoyMapData apply(Object from) {
        if (from instanceof Map<?, ?>) {
            @SuppressWarnings("unchecked")
            Map<String, ?> castMap = (Map<String, ?>) from;
            SoyMapData soyMapData = new SoyMapData();
            for (Map.Entry<String, ?> entry : castMap.entrySet()) {
                soyMapData.put(entry.getKey(), typeConverter.apply(entry.getValue()));
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
