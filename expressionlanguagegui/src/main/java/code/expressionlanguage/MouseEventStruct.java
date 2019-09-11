package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import javax.swing.*;
import java.awt.event.MouseEvent;

public final class MouseEventStruct implements Struct {

    private int first;
    private int second;
    private boolean ctrl;
    private boolean alt;
    private boolean shift;
    private boolean left;
    private boolean middle;
    private boolean right;
    private int clicks;
    private String className;
    public MouseEventStruct(MouseEvent _action, String _className) {
        first = _action.getX();
        second = _action.getY();
        ctrl = _action.isControlDown();
        alt = _action.isAltDown();
        shift = _action.isShiftDown();
        left = SwingUtilities.isLeftMouseButton(_action);
        middle = SwingUtilities.isMiddleMouseButton(_action);
        right = SwingUtilities.isRightMouseButton(_action);
        clicks = _action.getClickCount();
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

    public BooleanStruct isAlt() {
        return new BooleanStruct(alt);
    }

    public BooleanStruct isCtrl() {
        return new BooleanStruct(ctrl);
    }

    public BooleanStruct isLeft() {
        return new BooleanStruct(left);
    }

    public BooleanStruct isMiddle() {
        return new BooleanStruct(middle);
    }

    public BooleanStruct isRight() {
        return new BooleanStruct(right);
    }

    public BooleanStruct isShift() {
        return new BooleanStruct(shift);
    }

    public IntStruct getClicks() {
        return new IntStruct(clicks);
    }

    public IntStruct getFirst() {
        return new IntStruct(first);
    }

    public IntStruct getSecond() {
        return new IntStruct(second);
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
