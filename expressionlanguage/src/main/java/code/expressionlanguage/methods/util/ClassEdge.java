package code.expressionlanguage.methods.util;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.GraphElement;

public final class ClassEdge implements GraphElement<ClassEdge> {

    private final String id;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    public ClassEdge(String _id) {
        id = _id;
    }

    public String getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public boolean eq(ClassEdge _g) {
        return StringList.quickEq(id, _g.id);
    }

}
