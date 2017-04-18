package com.geniussports.soy.factories.rendering;

import com.geniussports.soy.context.RendererFactoryContext;
import com.geniussports.soy.factories.RendererFactory;
import com.geniussports.soy.renderers.NativeRenderer;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;

@Singleton
public class NativeRendererFactory implements RendererFactory {

    private final NativeRenderer nativeRenderer;

    @Inject
    public NativeRendererFactory(NativeRenderer nativeRenderer) {
        this.nativeRenderer = nativeRenderer;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Boolean.TRUE;
    }

    @Override
    public Renderer<Object> create(Class<?> classObject, RendererFactoryContext context) {
        return nativeRenderer;
    }
}
