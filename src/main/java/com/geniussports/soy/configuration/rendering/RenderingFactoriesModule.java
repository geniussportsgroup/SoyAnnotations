package com.geniussports.soy.configuration.rendering;

import com.geniussports.soy.factories.RendererFactory;
import com.geniussports.soy.factories.rendering.CustomRendererFactory;
import com.geniussports.soy.factories.rendering.NativeRendererFactory;
import com.geniussports.soy.factories.rendering.TemplateRendererFactory;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class RenderingFactoriesModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<RendererFactory> binder = newSetBinder(binder(), RendererFactory.class);
        binder.addBinding().to(NativeRendererFactory.class);
        binder.addBinding().to(TemplateRendererFactory.class);
        binder.addBinding().to(CustomRendererFactory.class);
    }
}
