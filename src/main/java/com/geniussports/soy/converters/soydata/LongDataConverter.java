package com.geniussports.soy.converters.soydata;

import com.geniussports.soy.model.LongData;
import com.google.inject.Singleton;

import java.util.function.Function;

@Singleton
public class LongDataConverter implements Function<Object, LongData> {

    @Override
    public LongData apply(Object from) {
        if (from instanceof Long) {
            return new LongData((Long) from);
        } else {
            return null;
        }
    }
}
