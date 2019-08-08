package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.event.WindowEvent;

public final class WindowEventStruct implements Struct {
    private WindowEvent actionEvent;
    private String className;
    public WindowEventStruct(WindowEvent _action, String _className) {
        actionEvent = _action;
        className = _className;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof WindowEventStruct)) {
            return false;
        }
        return actionEvent == ((WindowEventStruct)_other).actionEvent;
    }
}
