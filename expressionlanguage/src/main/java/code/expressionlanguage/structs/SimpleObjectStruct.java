package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

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
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasObject();
    }

}
