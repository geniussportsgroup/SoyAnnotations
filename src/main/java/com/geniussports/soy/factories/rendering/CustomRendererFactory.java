package com.geniussports.soy.factories.rendering;

import com.geniussports.soy.annotations.CustomRenderer;
import com.geniussports.soy.context.RendererFactoryContext;
import com.geniussports.soy.factories.RendererFactory;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;

@Singleton
public class CustomRendererFactory implements RendererFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(CustomRenderer.class);
    }

    @Override
    public Renderer<Object> create(Class<?> classObject,
                                   RendererFactoryContext context) {
        return getRendererInstance(getRendererClass(classObject), context);
    }

    private Class<? extends Renderer<Object>> getRendererClass(Class<?> classObject) {
        return classObject.getAnnotation(CustomRenderer.class).value();
    }

    private Renderer<Object> getRendererInstance(Class<? extends Renderer<Object>> rendererClass,
                                                 RendererFactoryContext context) {
        try {
            Renderer<Object> renderer = rendererClass.newInstance();
            if (renderer instanceof RendererFactoryContextAware) {
                ((RendererFactoryContextAware) renderer).setRendererFactoryContext(context);
            }
            return renderer;
        } catch (ReflectiveOperationException r) {
            throw new RuntimeException(r);
        }
    }
}
