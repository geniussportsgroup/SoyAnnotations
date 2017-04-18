package example.cases;

import com.geniussports.soy.Loader;
import com.geniussports.soy.annotations.CustomConverter;
import com.geniussports.soy.annotations.Soy;
import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.meta.MetaConverter;
import com.google.inject.Injector;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CustomConverterTestCase {

    private final Injector injector = Loader.getFullInjector();

    @Test
    public void testCustomConverter() {
        SoyValueFactoryContext context = injector.getInstance(SoyValueFactoryContext.class);
        Example example = new Example("John", "john@domain.com");
        Object object = context.apply(example);
    }

    public static class ExampleToMapConverter implements MetaConverter {

        @Override
        public Map<String, Integer> apply(Object from) {
            if (from instanceof Example) {
                Map<String, Integer> amountMap = new HashMap<>();
                amountMap.put("Name", ((Example) from).userName.length());
                amountMap.put("Email", ((Example) from).userEmail.length());
                return amountMap;
            } else {
                return null;
            }
        }

    }

    @Soy
    @CustomConverter(ExampleToMapConverter.class)
    public static class Example {
        @Soy.Field("Name")
        public final String userName;

        @Soy.Field("Email")
        public final String userEmail;

        public Example(String userName, String userEmail) {
            this.userName = userName;
            this.userEmail = userEmail;
        }

    }
}
