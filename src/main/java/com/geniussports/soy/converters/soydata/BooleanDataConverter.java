package com.geniussports.soy.converters.soydata;

import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.BooleanData;

import java.util.function.Function;

@Singleton
public class BooleanDataConverter implements Function<Object, BooleanData> {

    @Override
    public BooleanData apply(Object from) {
        if (from instanceof Boolean) {
            return BooleanData.forValue((Boolean) from);
        } else {
            return null;
        }
    }
}
