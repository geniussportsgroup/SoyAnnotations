package example.models;

import com.geniussports.soy.annotations.Soy;

import java.util.Collection;

@Soy
public class GoodDynamicMethodExample {

    private Collection<GoodDynamicMethodExample> children;

    public void setChildren(Collection<GoodDynamicMethodExample> children) {
        this.children = children;
    }

    @Soy.Dynamic
    @Soy.Method("FirstChild")
    public GoodDynamicMethodExample getFirstChild() {
        if (children != null) {
            return children.iterator().next();
        } else {
            return null;
        }
    }
}
