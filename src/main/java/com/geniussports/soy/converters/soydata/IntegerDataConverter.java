package com.geniussports.soy.converters.soydata;

import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.IntegerData;

import javax.annotation.Nullable;
import java.util.function.Function;

@Singleton
public class IntegerDataConverter implements Function<Object, IntegerData> {

    @Override
    @Nullable
    public IntegerData apply(@Nullable Object from) {
        if (from instanceof Integer) {
            return IntegerData.forValue((Integer) from);
        } else {
            return null;
        }
    }
}
