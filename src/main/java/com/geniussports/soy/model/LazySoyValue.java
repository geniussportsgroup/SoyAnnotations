package com.geniussports.soy.model;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.jbcsrc.api.AdvisingAppendable;
import com.google.template.soy.jbcsrc.api.RenderResult;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
@ParametersAreNonnullByDefault
public class LazySoyValue<T extends SoyData> extends SoyData {

    protected final LazyContainer<T> container;

    protected LazySoyValue(final Supplier<T> container) {
        this.container = LazyContainer.of(container);
    }

    @Override
    public boolean coerceToBoolean() {
        return container.get().coerceToBoolean();
    }

    /**
     * This was a required method in the old SoyData interface. For new data classes, please use
     * interface SoyValue, which has the method coerceToBoolean().
     * <p>
     * Converts this data object into a boolean (e.g. when used in a boolean context). In other words,
     * this method tells whether this object is truthy.
     *
     * @return The value of this data object if coerced into a boolean. I.e. true if this object is
     * truthy, false if this object is falsy.
     */
    @Override
    public boolean toBoolean() {
        return container.get().toBoolean();
    }

    @Override
    public String coerceToString() {
        return container.get().coerceToString();
    }

    @Override
    public String toString() {
        return container.get().toString();
    }

    @Override
    public void render(final Appendable appendable) throws IOException {
        container.get().render(appendable);
    }

    @Nonnull
    @Override
    public SoyValue resolve() {
        return container.get().resolve();
    }

    @Nonnull
    @Override
    public RenderResult status() {
        return container.get().status();
    }

    @Override
    public RenderResult renderAndResolve(final AdvisingAppendable appendable,
                                         final boolean isLast) throws IOException {
        return container.get().renderAndResolve(appendable, isLast);
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object other) {
        return container.get().equals(other);
    }

    @Override
    public boolean booleanValue() {
        return container.get().booleanValue();
    }

    @Override
    public int integerValue() {
        return container.get().integerValue();
    }

    @Override
    public long longValue() {
        return container.get().longValue();
    }

    @Override
    public double floatValue() {
        return container.get().floatValue();
    }

    @Override
    public double numberValue() {
        return container.get().numberValue();
    }

    @Override
    public String stringValue() {
        return container.get().stringValue();
    }
}
