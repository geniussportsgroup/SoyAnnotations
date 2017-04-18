package com.geniussports.soy.converters.soydata;

import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.StringData;

import java.util.function.Function;

@Singleton
public class StringDataConverter implements Function<Object, StringData> {

    @Override
    public StringData apply(Object from) {
        if (from != null) {
            return StringData.forValue(String.valueOf(from));
        } else {
            return null;
        }
    }
}
