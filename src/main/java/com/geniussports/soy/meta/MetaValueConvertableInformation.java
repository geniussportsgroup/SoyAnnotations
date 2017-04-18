package com.geniussports.soy.meta;

import javax.annotation.concurrent.Immutable;
import java.util.function.Function;

@Immutable
public class MetaValueConvertableInformation extends MetaInformation {

    private final Function<Object, ?> valueConverter;

    public MetaValueConvertableInformation(Boolean dynamic,
                                           Class<?> type,
                                           Function<Object, ?> valueConverter) {
        super(dynamic, type);
        this.valueConverter = valueConverter;
    }

    public final Function<Object, ?> getValueConverter() {
        return valueConverter;
    }
}
