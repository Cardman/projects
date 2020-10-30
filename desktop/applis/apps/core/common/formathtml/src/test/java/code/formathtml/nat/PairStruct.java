package code.formathtml.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class PairStruct extends WithoutParentIdStruct {
    private final String className;
    private final Struct first;
    private final Struct second;

    public PairStruct(String _className, Struct _first, Struct _second) {
        this.className = _className;
        this.first = _first;
        this.second = _second;
    }

    public Struct getFirst() {
        return first;
    }

    public Struct getSecond() {
        return second;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
}
