package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.*;

import java.awt.event.KeyEvent;

public final class KeyEventStruct extends WithoutParentIdStruct implements Struct {
    private String className;
    private boolean ctrl;
    private boolean alt;
    private boolean shift;
    private int keyCode;
    private char keyChar;
    public KeyEventStruct(String _className) {
        className = _className;
    }
    public KeyEventStruct(KeyEvent _action, String _className, char _keyChar) {
        ctrl = _action.isControlDown();
        alt = _action.isAltDown();
        shift = _action.isShiftDown();
        className = _className;
        keyChar = _keyChar;
    }
    public KeyEventStruct(KeyEvent _action, String _className, char _keyChar, int _keyCode) {
        ctrl = _action.isControlDown();
        alt = _action.isAltDown();
        shift = _action.isShiftDown();
        className = _className;
        keyChar = _keyChar;
        keyCode = _keyCode;
    }

    public void setAlt(Struct _alt) {
        alt = BooleanStruct.isTrue(_alt);
    }

    public void setCtrl(Struct _ctrl) {
        ctrl = BooleanStruct.isTrue(_ctrl);
    }

    public void setShift(Struct _shift) {
        shift = BooleanStruct.isTrue(_shift);
    }

    public void setKeyChar(Struct _clicks) {
        keyChar = (char)((NumberStruct)_clicks).intStruct();
    }

    public void setKeyCode(Struct _clicks) {
        keyCode = ((NumberStruct)_clicks).intStruct();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public BooleanStruct isAlt() {
        return BooleanStruct.of(alt);
    }

    public BooleanStruct isCtrl() {
        return BooleanStruct.of(ctrl);
    }

    public BooleanStruct isShift() {
        return BooleanStruct.of(shift);
    }

    public CharStruct getKeyChar() {
        return new CharStruct(keyChar);
    }

    public IntStruct getKeyCode() {
        return new IntStruct(keyCode);
    }

}
