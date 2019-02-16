package code.expressionlanguage.methods.util;
import code.util.StringList;
import code.util.ints.GraphElement;

public final class ClassEdge implements GraphElement<ClassEdge> {

    private final String id;

    public ClassEdge(String _id) {
        id = _id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean eq(ClassEdge _g) {
        return StringList.quickEq(id, _g.getId());
    }

}
