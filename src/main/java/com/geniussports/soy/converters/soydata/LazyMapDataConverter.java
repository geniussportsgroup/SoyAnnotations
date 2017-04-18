package com.geniussports.soy.converters.soydata;

import com.geniussports.soy.model.DefaultLazyResult;
import com.geniussports.soy.model.LazySoyMapData;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.restricted.NullData;

import java.util.function.Function;
import java.util.function.Supplier;

public class LazyMapDataConverter implements Function<Object, SoyData> {

    private final Function<Object, SoyMapData> soyMapDataConverter;

    public LazyMapDataConverter(Function<Object, SoyMapData> soyMapDataConverter) {
        this.soyMapDataConverter = soyMapDataConverter;
    }

    public Supplier<SoyMapData> getLazyResult(Object from) {
        return new DefaultLazyResult<>(from, soyMapDataConverter);
    }

    @Override
    public SoyData apply(Object from) {
        if (from != null) {
            return new LazySoyMapData(getLazyResult(from));
        } else {
            return NullData.INSTANCE;
        }
    }
}
