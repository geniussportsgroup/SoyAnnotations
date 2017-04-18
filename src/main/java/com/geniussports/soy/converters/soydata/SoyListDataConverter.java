package com.geniussports.soy.converters.soydata;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Function;

public class SoyListDataConverter implements Function<Object, SoyListData> {

    private final Function<Object, ? extends SoyData> itemConverter;

    public SoyListDataConverter(@Nonnull Function<Object, ? extends SoyData> itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    @Nullable
    public SoyListData apply(@Nullable Object collection) {
        if (collection instanceof Collection) {
            SoyListData soyListData = new SoyListData();
            for (Object item : (Collection) collection) {
                soyListData.add(itemConverter.apply(item));
            }
            return soyListData;
        } else {
            return null;
        }
    }
}
