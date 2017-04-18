package example.models;

import com.geniussports.soy.annotations.CustomRenderer;
import com.geniussports.soy.annotations.Soy;
import com.geniussports.soy.context.RendererFactoryContext;
import example.renderer.SettingsRenderer;

import static java.lang.String.format;

@Soy
@CustomRenderer(SettingsRenderer.class)
public class Setting<A> {

    @Soy.Field
    public String Name;

    @Soy.Field
    public A Value;

    public String toString(RendererFactoryContext factoryContext) {
        return format("%s: %s", Name, factoryContext.render(Value));
    }
}
