package com.geniussports.soy.renderers;

import com.geniussports.soy.factories.rendering.Renderer;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.tofu.SoyTofu;

import javax.annotation.Nullable;
import java.util.Set;

public class DataRenderer implements Renderer<SoyValue> {

    private final SoyTofu tofu;

    private final Set<String> activePackages;

    private final String templateName;
    private SoyTofu.Renderer internalRenderer;

    public DataRenderer(SoyTofu tofu,
                        String templateName,
                        Set<String> activePackages) {
        this.tofu = tofu;
        this.templateName = templateName;
        this.activePackages = activePackages;
    }

    @Nullable
    @Override
    public String render(@Nullable SoyValue data) {
        return getRenderer().setData((SoyMapData) data).render();
    }

    private SoyTofu.Renderer getRenderer() {
        if (internalRenderer == null) {
            internalRenderer = tofu
                    .newRenderer(templateName)
                    .setActiveDelegatePackageNames(activePackages);
        }
        return internalRenderer;
    }
}
