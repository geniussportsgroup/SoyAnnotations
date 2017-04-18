package com.geniussports.soy.converters.soydata;

import com.geniussports.soy.model.DefaultLazyResult;
import com.geniussports.soy.model.DefaultLazyResultWithOriginalToString;
import com.geniussports.soy.model.LazySoyList;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class LazySoyListDataConverter implements Function<Object, SoyData> {

    private final Function<Object, SoyListData> listDataConverter;

    private final Boolean useOriginalToString;

    public LazySoyListDataConverter(Function<Object, SoyListData> listDataConverter,
                                    Boolean useOriginalToString) {
        this.listDataConverter = listDataConverter;
        this.useOriginalToString = useOriginalToString;
    }

    public Supplier<SoyListData> getLazyResult(Object from) {
        if (useOriginalToString) {
            return new DefaultLazyResultWithOriginalToString<>(from, listDataConverter);
        } else {
            return new DefaultLazyResult<>(from, listDataConverter);
        }
    }

    @Override
    public SoyData apply(Object from) {
        if (from instanceof Collection) {
            return new LazySoyList(getLazyResult(from));
        } else {
            return null;
        }
    }
}
