package code.expressionlanguage.guicompos;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public final class KeyEventStruct implements Struct {
    private String className;
    private boolean ctrl;
    private boolean alt;
    private boolean shift;
    private int keyCode;
    private char keyChar;
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
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    public BooleanStruct isAlt() {
        return new BooleanStruct(alt);
    }

    public BooleanStruct isCtrl() {
        return new BooleanStruct(ctrl);
    }

    public BooleanStruct isShift() {
        return new BooleanStruct(shift);
    }

    public CharStruct getKeyChar() {
        return new CharStruct(keyChar);
    }

    public IntStruct getKeyCode() {
        return new IntStruct(keyCode);
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
