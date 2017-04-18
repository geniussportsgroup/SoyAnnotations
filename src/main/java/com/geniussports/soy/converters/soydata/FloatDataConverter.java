package com.geniussports.soy.converters.soydata;


import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.FloatData;

import java.util.function.Function;

@Singleton
public class FloatDataConverter implements Function<Object, FloatData> {

    @Override
    public FloatData apply(Object from) {
        if (from instanceof Float) {
            return FloatData.forValue((Float) from);
        }
        if (from instanceof Double) {
            return FloatData.forValue((Double) from);
        }
        return null;
    }
}
