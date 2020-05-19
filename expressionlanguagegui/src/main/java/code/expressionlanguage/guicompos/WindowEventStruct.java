package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class WindowEventStruct implements Struct {
    private String className;
    public WindowEventStruct(String _className) {
        className = _className;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
