package com.geniussports.soy.converters.soydata;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.restricted.NullData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class NullSafeConverter implements Function<Object, SoyData> {

    private final Function<Object, ? extends SoyData> converter;

    public NullSafeConverter(@Nonnull Function<Object, ? extends SoyData> converter) {
        this.converter = converter;
    }

    public Function<Object, ? extends SoyValue> getNestedConverter() {
        return converter;
    }

    @Override
    @Nonnull
    public SoyData apply(@Nullable Object from) {
        if (from != null) {
            return convertResult(converter.apply(from));
        } else {
            return NullData.INSTANCE;
        }
    }

    @Nonnull
    public static NullSafeConverter wrapConverterWithNullSafe(@Nonnull Function<Object, ? extends SoyData> converter) {
        return new NullSafeConverter(converter);
    }

    @Nonnull
    private SoyData convertResult(@Nullable SoyData from) {
        return from != null ? from : NullData.INSTANCE;
    }
}
