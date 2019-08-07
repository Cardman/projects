package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public final class MouseEventStruct implements Struct {
    private MouseEvent actionEvent;
    private String className;
    public MouseEventStruct(MouseEvent _action, String _className) {
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

    public int getFirst() {
        return actionEvent.getX();
    }

    public int getSecond() {
        return actionEvent.getY();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof MouseEventStruct)) {
            return false;
        }
        return actionEvent == ((MouseEventStruct)_other).actionEvent;
    }
}
