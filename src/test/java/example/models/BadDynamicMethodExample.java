package example.models;

import com.geniussports.soy.annotations.Soy;

import java.util.Collection;

@Soy
public class BadDynamicMethodExample {

    private Collection<BadDynamicMethodExample> children;

    public void setChildren(Collection<BadDynamicMethodExample> children) {
        this.children = children;
    }

    @Soy.Method("FirstChild")
    public BadDynamicMethodExample getFirstChild() {
        return children.iterator().next();
    }
}
