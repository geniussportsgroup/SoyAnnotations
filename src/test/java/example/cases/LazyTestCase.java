package example.cases;

import com.geniussports.soy.Loader;
import com.geniussports.soy.annotations.Soy;
import com.geniussports.soy.context.SoyValueFactoryContext;
import com.google.inject.Injector;
import com.google.template.soy.data.SoyValue;
import org.junit.Test;

public class LazyTestCase {

    public Node createChain(Integer count,
                            Node lastNode) {
        if (count > 0) {
            Node node = new Node();
            node.name = "Node " + count;
            node.next = createChain(count - 1, lastNode);
            return node;
        } else {
            return lastNode;
        }
    }

    public Node createSelfReferenceChain(Integer count) {
        Node root = new Node();
        root.name = "Root Node";
        root.next = createChain(count - 1, root);
        return root;
    }

    @Test
    public void testExceptionIsNotThrownNormalChain() {
        Injector injector = Loader.getFullInjector();
        SoyValueFactoryContext context = injector.getInstance(SoyValueFactoryContext.class);
        Node node = createChain(3, null);
        SoyValue soyData = context.apply(node);
    }

    @Test(expected = StackOverflowError.class)
    public void testExceptionIsThrownOnSelfReferencingChain() {
        Injector injector = Loader.getFullInjector();
        SoyValueFactoryContext context = injector.getInstance(SoyValueFactoryContext.class);
        Node node = createSelfReferenceChain(10);
        SoyValue soyData = context.apply(node);
    }

    @Test
    public void testExceptionIsNotThrownOnSelfReferencingChainWithLazyContext() {
        Injector injector = Loader.getLazyInjector();
        SoyValueFactoryContext context = injector.getInstance(SoyValueFactoryContext.class);
        Node node = createSelfReferenceChain(10);
        SoyValue soyData = context.apply(node);
    }

    @Soy
    @Soy.Dynamic
    public static class Node {

        @Soy.Field("Name") public String name;

        @Soy.Field("Next") @Soy.Dynamic public Node next;

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            b.append(name).append(":");
            if (next != null) {
                b.append("[").append(next.toString()).append("]");
            } else {
                b.append("null");
            }
            return b.toString();
        }
    }
}
