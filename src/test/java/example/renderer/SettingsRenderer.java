package example.renderer;

import com.geniussports.soy.context.RendererFactoryContext;
import com.geniussports.soy.factories.rendering.Renderer;
import com.geniussports.soy.factories.rendering.RendererFactoryContextAware;
import example.models.Setting;

import javax.annotation.Nullable;

public class SettingsRenderer implements Renderer<Object>, RendererFactoryContextAware {

    private RendererFactoryContext factoryContext;

    @Override
    public void setRendererFactoryContext(RendererFactoryContext context) {
        this.factoryContext = context;
    }

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        if (instanceObject instanceof Setting) {
            return ((Setting) instanceObject).toString(factoryContext);
        } else {
            return null;
        }
    }
}
