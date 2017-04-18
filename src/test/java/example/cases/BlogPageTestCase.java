package example.cases;

import com.geniussports.soy.Loader;
import com.geniussports.soy.context.JsonDataFactoryContext;
import com.geniussports.soy.context.SoyValueFactoryContext;
import com.google.inject.Injector;
import example.models.blog.BlogPostsPage;
import org.junit.Before;
import org.junit.Test;

import static example.assertions.BlogPageAssertions.assertBlogPostPageJsonEquals;
import static example.assertions.BlogPageAssertions.assertBlogPostPageSoyDataEquals;
import static example.builders.BlogPostPageBuilder.getPage;

public class BlogPageTestCase {

    private Injector injector = Loader.getFullInjector();

    private BlogPostsPage page;

    @Before
    public void setup() {
        page = getPage(10, 5);

    }

    @Test
    public void testPageSoy() {
        SoyValueFactoryContext context = injector.getInstance(SoyValueFactoryContext.class);
        assertBlogPostPageSoyDataEquals(page, context.getSoyData(page));
    }

    @Test
    public void testPageJson() {
        JsonDataFactoryContext context = injector.getInstance(JsonDataFactoryContext.class);
        // todo, add getJson and getJsonMap to context as methods.
        assertBlogPostPageJsonEquals(page, context.apply(page));
    }

}
