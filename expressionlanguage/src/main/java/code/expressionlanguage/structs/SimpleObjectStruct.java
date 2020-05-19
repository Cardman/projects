package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class SimpleObjectStruct implements Struct {

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getAliasObject();
    }

}
