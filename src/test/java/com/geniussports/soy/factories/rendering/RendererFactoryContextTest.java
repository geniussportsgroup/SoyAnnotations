package com.geniussports.soy.factories.rendering;

import com.geniussports.soy.Loader;
import com.geniussports.soy.annotations.Soy;
import com.geniussports.soy.context.RendererFactoryContext;
import com.google.inject.Injector;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import example.models.Setting;
import example.models.Settings;
import example.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RendererFactoryContextTest {

    private final SoyTofu soyTofu =
            SoyFileSet.builder()
                      .add(getClass().getResource("/templates.soy"))
                      .build().compileToTofu();

    private final Injector injector = Loader.getFullInjector(soyTofu, null);

    private RendererFactoryContext rendererFactoryContext;

    @Before
    public void setUp() throws Exception {
        rendererFactoryContext = injector.getInstance(RendererFactoryContext.class);
    }

    @Test
    public void testBasicSoyTemplateWithFactory() throws Exception {

        @Soy
        @Soy.Template("example.BasicString")
        class BasicString {
        }

        Renderer<Object> renderer = rendererFactoryContext.getRenderer(BasicString.class);
        BasicString exampleUser = new BasicString();
        String expected = "basic";
        String result = renderer.render(exampleUser);
        assertEquals(expected, result);
    }

    @Test
    public void testComplexSoyTemplateWithFactory() throws Exception {
        Renderer<Object> renderer = rendererFactoryContext.getRenderer(User.class);
        User exampleUser = new User("1");
        exampleUser.setName("John");
        exampleUser.setEmail("john@gmail.com");

        String expected = "User 1, Name John, Email: john@gmail.com";
        String result = renderer.render(exampleUser);
        assertEquals(expected, result);
    }

    @Test
    public void testCustomRendererAnnotation() {
        Settings settings = new Settings();
        settings.Name = "GlobalSettings";
        settings.Value = true;
        settings.GeneralSettings = new ArrayList<>();

        Setting<String> userNameSetting = new Setting<>();
        userNameSetting.Name = "UserName";
        userNameSetting.Value = "user";
        settings.GeneralSettings.add(userNameSetting);

        String result = rendererFactoryContext.render(settings);
        String expected = "{ GlobalSettings : true, GeneralSettings: [UserName: user], SpecialSettings: null}";
        assertEquals(expected, result);
    }
}
