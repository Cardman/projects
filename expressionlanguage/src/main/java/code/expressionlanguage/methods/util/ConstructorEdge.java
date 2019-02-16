package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.ints.GraphElement;

public final class ConstructorEdge implements GraphElement<ConstructorEdge> {

    private final ConstructorId id;

    public ConstructorEdge(ConstructorId _id) {
        id = _id;
    }

    public ConstructorId getId() {
        return id;
    }

    @Override
    public boolean eq(ConstructorEdge _g) {
        return id.eq(_g.getId());
    }

}
