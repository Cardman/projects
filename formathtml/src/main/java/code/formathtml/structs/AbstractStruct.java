package code.formathtml.structs;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractStruct implements Struct {

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
