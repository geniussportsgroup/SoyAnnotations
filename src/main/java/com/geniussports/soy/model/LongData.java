package com.geniussports.soy.model;

import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.restricted.NullData;
import com.google.template.soy.data.restricted.NumberData;

import static com.google.common.base.Preconditions.checkNotNull;

public class LongData extends NumberData {

    private final Long longValue;

    public LongData(Long longValue) {
        checkNotNull(longValue);
        this.longValue = longValue;
    }

    @Override
    public boolean toBoolean() {
        return !longValue.equals(0L);
    }

    @Override
    public String toString() {
        return longValue.toString();
    }

    @Override
    public double toFloat() {
        return longValue.floatValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        LongData that = (LongData) o;

        if (!longValue.equals(that.longValue)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return longValue.hashCode();
    }

    @Override
    public double floatValue() {
        return longValue.floatValue();
    }

    @Override
    public String stringValue() {
        return longValue.toString();
    }

    public static SoyValue forValue(Object value) {
        if (value != null && value instanceof Long) {
            return new LongData((Long) value);
        } else {
            return NullData.INSTANCE;
        }
    }
}
