package com.geniussports.soy.model;

import com.google.template.soy.data.SoyMap;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.SoyValueProvider;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class LazySoyMapData extends LazySoyValue<SoyMapData> implements SoyMap {

    public LazySoyMapData(Supplier<SoyMapData> supplier) {
        super(supplier);
    }

    @Override
    public int getItemCnt() {
        return container.get().getItemCnt();
    }

    @Nonnull
    @Override
    public Iterable<? extends SoyValue> getItemKeys() {
        return container.get().getItemKeys();
    }

    @Override
    public boolean hasItem(final SoyValue key) {
        return container.get().hasItem(key);
    }

    @Override
    public SoyValue getItem(final SoyValue key) {
        return container.get().getItem(key);
    }

    @Override
    public SoyValueProvider getItemProvider(final SoyValue key) {
        return container.get().getItemProvider(key);
    }
}
