package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.util.ClassName;
import code.util.CustList;
import code.util.ints.GraphElement;

public final class ClassEdge implements GraphElement<ClassEdge> {

    private final ClassName id;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    public ClassEdge(ClassName _id) {
        id = _id;
    }

    public ClassName getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        order = _order;
    }

    @Override
    public boolean eq(ClassEdge _g) {
        return id.eq(_g.id);
    }

}
