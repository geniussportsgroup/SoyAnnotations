package com.geniussports.soy.context;

import com.geniussports.soy.exceptions.MissingFactory;
import com.geniussports.soy.factories.RendererFactory;
import com.geniussports.soy.factories.rendering.Renderer;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 *
 */
@SuppressWarnings("WeakerAccess")
public class RendererFactoryContext implements Renderer<Object> {

    private final SoyValueFactoryContext soyValueFactoryContext;

    private final List<RendererFactory> rendererFactories;

    @Inject
    public RendererFactoryContext(SoyValueFactoryContext soyValueFactoryContext,
                                  Set<RendererFactory> rendererFactories) {
        this.soyValueFactoryContext = soyValueFactoryContext;
        this.rendererFactories = Lists.newArrayList(rendererFactories);
    }

    @Nonnull
    public RendererFactory getFactory(@Nonnull Class<?> classObject) {
        for (RendererFactory factory : Lists.reverse(rendererFactories)) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory(classObject);
    }

    @Nonnull
    public Renderer<Object> getRenderer(@Nonnull Class<?> classObject) {
        return getFactory(classObject).create(classObject, this);
    }

    @Nonnull
    public Renderer<Object> getRendererFromInstance(@Nonnull Object instanceObject) {
        return getRenderer(instanceObject.getClass());
    }

    @Override
    public String render(@Nullable Object instanceObject) {
        if (instanceObject != null) {
            return getRendererFromInstance(instanceObject).render(instanceObject);
        } else {
            return null;
        }
    }
}
