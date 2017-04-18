package example.models;

import com.geniussports.soy.annotations.Soy;

import java.util.Collection;

@Soy
public class GoodDynamicFieldExample {

    @Soy.Dynamic
    @Soy.Field("Children")
    public Collection Children;
}
