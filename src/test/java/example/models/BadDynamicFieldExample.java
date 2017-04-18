package example.models;

import com.geniussports.soy.annotations.Soy;

@Soy
public class BadDynamicFieldExample {

    @Soy.Field("Child")
    public BadDynamicFieldExample Child;
}
