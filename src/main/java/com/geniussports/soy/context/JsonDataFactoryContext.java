package com.geniussports.soy.context;

import com.geniussports.soy.exceptions.MissingFactory;
import com.geniussports.soy.factories.JsonConverterFactory;
import com.google.inject.Inject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;

@SuppressWarnings("WeakerAccess")
public class JsonDataFactoryContext implements Function<Object, Object> {

    private final List<JsonConverterFactory> factories;

    @Inject
    public JsonDataFactoryContext(Set<JsonConverterFactory> factories) {
        this.factories = newArrayList(factories);
    }

    @Nonnull
    public List<JsonConverterFactory> getFactories() {
        return factories;
    }

    @Nonnull
    public JsonConverterFactory getFactory(@Nonnull Class<?> classObject) {
        for (JsonConverterFactory factory : reverse(factories)) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory(classObject);
    }

    @Nonnull
    public Function<Object, ?> create(@Nonnull Class<?> classObject) {
        return getFactory(classObject).create(classObject, this);
    }

    @Nonnull
    public Function<Object, ?> createFromInstance(@Nonnull Object instanceObject) {
        return create(instanceObject.getClass());
    }

    @Nullable
    public Object apply(Object from) {
        if (from != null) {
            return createFromInstance(from).apply(from);
        } else {
            return null;
        }
    }
}
