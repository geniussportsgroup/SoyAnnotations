package com.geniussports.soy.converters.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class DynamicConverter implements Function<Object, SoyData> {

    private final SoyValueFactoryContext context;

    public DynamicConverter(@Nonnull SoyValueFactoryContext context) {
        this.context = context;
    }

    @Override
    @Nullable
    public SoyData apply(@Nullable Object from) {
        return context.apply(from);
    }
}
