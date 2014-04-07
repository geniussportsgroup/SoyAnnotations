package slieb.soy.converters;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import javax.annotation.Nullable;
import java.util.Map;

public class SoyDataMapConverter implements Converter<Object, SoyMapData> {

    private final Converter<Object, ? extends SoyData> typeConverter;

    public SoyDataMapConverter(Converter<Object, ? extends SoyData> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    @Nullable
    public SoyMapData convert(@Nullable Object object) {
        if (object instanceof Map) {
            SoyMapData soyMapData = new SoyMapData();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) object).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (key instanceof String) {
                    soyMapData.putSingle((String) key, typeConverter.convert(value));
                }
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
