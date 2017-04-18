package com.geniussports.soy.model;

import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class LazyContainer<T> implements Supplier<T> {

    private final Supplier<T> supplier;

    private T reference;

    public LazyContainer(final Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> LazyContainer<T> of(Supplier<T> supplier) {
        if (supplier instanceof LazyContainer) {
            return (LazyContainer<T>) supplier;
        } else {
            return new LazyContainer<>(supplier);
        }
    }

    @Override
    public T get() {
        if (reference == null) {
            reference = supplier.get();
        }
        return reference;
    }
}
