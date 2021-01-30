package code.bean.nat;

import code.expressionlanguage.structs.Struct;

public final class PairStruct extends CommNatStruct {
    private final Struct first;
    private final Struct second;

    public PairStruct(String _className, Struct _first, Struct _second) {
        super(_className);
        this.first = _first;
        this.second = _second;
    }

    public Struct getFirst() {
        return first;
    }

    public Struct getSecond() {
        return second;
    }
}
