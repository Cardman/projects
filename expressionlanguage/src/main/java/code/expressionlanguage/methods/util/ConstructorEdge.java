package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.ints.GraphElement;

public final class ConstructorEdge implements GraphElement<ConstructorEdge> {

    private final FctConstraints id;

    public ConstructorEdge(FctConstraints _id) {
        id = _id;
    }

    public FctConstraints getId() {
        return id;
    }

    @Override
    public boolean eq(ConstructorEdge _g) {
        return id.eq(_g.id);
    }

}
