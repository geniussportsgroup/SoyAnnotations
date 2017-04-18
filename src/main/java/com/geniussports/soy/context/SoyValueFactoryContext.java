package com.geniussports.soy.context;

import com.geniussports.soy.exceptions.MissingFactory;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Inject;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.restricted.NullData;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static java.util.Collections.unmodifiableList;

@SuppressWarnings("WeakerAccess")
public class SoyValueFactoryContext implements Function<Object, SoyData> {

    private final List<SoyConverterFactory> factories;

    @Inject
    public SoyValueFactoryContext(Set<SoyConverterFactory> factories) {
        this.factories = unmodifiableList(newArrayList(factories));
    }

    @Nonnull
    public List<SoyConverterFactory> getFactories() {
        return factories;
    }

    @Nonnull
    public SoyConverterFactory getFactory(@Nonnull Class<?> classObject) {
        for (SoyConverterFactory factory : reverse(factories)) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory(classObject);
    }

    @Nonnull
    public Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject) {
        return getFactory(classObject).create(classObject, this);
    }

    @Nonnull
    public Function<Object, ? extends SoyData> createFromInstance(@Nonnull Object instanceObject) {
        return create(instanceObject.getClass());
    }

    @Override
    @Nonnull
    public SoyData apply(Object from) {
        if (from != null) {
            return createFromInstance(from).apply(from);
        } else {
            return NullData.INSTANCE;
        }
    }

    public SoyValue getSoyData(Object instanceObject) {
        return apply(instanceObject);
    }

    @SuppressWarnings("unused")
    public <T> T getSoyValueAs(final Object obj,
                               final Class<T> classObject) {
        return classObject.cast(apply(obj));
    }
}
