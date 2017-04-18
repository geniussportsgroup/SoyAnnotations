package example.models;

import com.geniussports.soy.annotations.Soy;

import java.util.List;

@Soy
public class TreeNodeExample {

    @Soy.Field("Name")
    public String name;

    @Soy.Dynamic
    @Soy.Field("Children")
    public List<TreeNodeExample> Children;

    @Soy.Method("ChildrenCount")
    public Integer getChildrenCount() {
        return Children.size();
    }
}
