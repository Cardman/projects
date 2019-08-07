package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.event.ActionEvent;

public final class ActionEventStruct implements Struct {
    private ActionEvent actionEvent;
    private String className;
    public ActionEventStruct(ActionEvent _action, String _className) {
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
        if (!(_other instanceof ActionEventStruct)) {
            return false;
        }
        return actionEvent == ((ActionEventStruct)_other).actionEvent;
    }
}
