package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.*;

public final class MouseWheelEventStruct extends MouseEventStruct {

    private int rotated;

    public MouseWheelEventStruct(String _className, AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons, AbsMouseWheel _wheel) {
        super(_className, _location, _keyState, _buttons);
        rotated = _wheel.getWheelRotation();
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
