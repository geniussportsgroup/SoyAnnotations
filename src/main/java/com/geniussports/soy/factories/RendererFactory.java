package com.geniussports.soy.factories;

import com.geniussports.soy.context.RendererFactoryContext;
import com.geniussports.soy.factories.rendering.Renderer;

public interface RendererFactory {

    Boolean canCreate(Class<?> classObject);

    Renderer<Object> create(Class<?> classObject,
                            RendererFactoryContext context);
}
