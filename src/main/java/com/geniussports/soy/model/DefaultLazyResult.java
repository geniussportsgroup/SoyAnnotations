package com.geniussports.soy.model;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class DefaultLazyResult<A, B> implements Supplier<B> {

    protected final A object;

    protected final Function<A, B> converter;

    private B result;

    public DefaultLazyResult(@Nonnull A object,
                             @Nonnull Function<A, B> converter) {
        this.object = object;
        this.converter = converter;
    }

    @Nonnull
    public B get() {
        if (result == null) {
            result = converter.apply(object);
        }
        return result;
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
