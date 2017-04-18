package com.geniussports.soy.model;

import com.google.template.soy.data.SoyList;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.SoyValueProvider;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class LazySoyList extends LazySoyValue<SoyListData> implements SoyList {

    public LazySoyList(Supplier<SoyListData> supplier) {
        super(supplier);
    }

    @Override
    public int length() {
        return container.get().length();
    }

    @Nonnull
    @Override
    public List<? extends SoyValueProvider> asJavaList() {
        return container.get().asJavaList();
    }

    @Nonnull
    @Override
    public List<? extends SoyValue> asResolvedJavaList() {
        return container.get().asResolvedJavaList();
    }

    @Override
    public SoyValue get(final int index) {
        return container.get().get(index);
    }

    @Override
    public SoyValueProvider getProvider(final int index) {
        return container.get().getProvider(index);
    }

    @Override
    public int getItemCnt() {
        return 0;
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
