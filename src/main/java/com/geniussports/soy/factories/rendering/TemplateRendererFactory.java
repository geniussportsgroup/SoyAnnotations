package com.geniussports.soy.factories.rendering;

import com.geniussports.soy.context.RendererFactoryContext;
import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.factories.RendererFactory;
import com.geniussports.soy.helpers.FactoryHelper;
import com.geniussports.soy.renderers.ClassRenderer;
import com.geniussports.soy.renderers.DataRenderer;
import com.google.inject.Inject;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.tofu.SoyTofu;

import javax.inject.Named;
import java.util.Set;
import java.util.function.Function;

public class TemplateRendererFactory implements RendererFactory {

    private final FactoryHelper factoryHelper;

    private final SoyValueFactoryContext soyValueFactoryContext;

    private final SoyTofu soyTofu;

    private final Set<String> delegatePackages;

    @Inject
    public TemplateRendererFactory(FactoryHelper factoryHelper,
                                   SoyValueFactoryContext soyValueFactoryContext,
                                   SoyTofu soyTofu,
                                   @Named("DelegatePackages") Set<String> delegatePackages) {
        this.factoryHelper = factoryHelper;
        this.soyValueFactoryContext = soyValueFactoryContext;
        this.soyTofu = soyTofu;
        this.delegatePackages = delegatePackages;
    }

    @Override
    public Boolean canCreate(Class<?> classObject) {
        return factoryHelper.hasTemplate(classObject);
    }

    @Override
    public Renderer<Object> create(Class<?> classObject,
                                   RendererFactoryContext context) {
        String templateName = factoryHelper.getTemplateName(classObject);
        Function<Object, ? extends SoyValue> dataConverter = soyValueFactoryContext.create(classObject);
        Renderer<SoyValue> dataRenderer = new DataRenderer(soyTofu, templateName, delegatePackages);
        return new ClassRenderer(dataConverter, dataRenderer);
    }
}
