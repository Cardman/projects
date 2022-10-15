package code.bean.nat;

import code.expressionlanguage.structs.AbNullStruct;
import code.expressionlanguage.structs.Struct;

public final class PairStruct extends AbNullStruct {
    private final Struct first;
    private final Struct second;

    public PairStruct(Struct _first, Struct _second) {
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
