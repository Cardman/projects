package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.event.MouseWheelEvent;

public final class MouseWheelEventStruct extends MouseEventStruct {

    private int rotated;

    public MouseWheelEventStruct(MouseWheelEvent _action, String _className) {
        super(_action,_className);
        rotated = _action.getWheelRotation();
    }
    public MouseWheelEventStruct(String _className) {
        super(_className);
    }

    public void setRotated(Struct _rotated) {
        rotated = ((NumberStruct)_rotated).intStruct();
    }

    public IntStruct getRotated() {
        return new IntStruct(rotated);
    }
}
