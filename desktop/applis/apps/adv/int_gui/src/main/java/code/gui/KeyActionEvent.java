package code.gui;

public final class KeyActionEvent implements AbsCtrlKeyState {
    private final boolean control;
    private final boolean alt;
    private final boolean shift;
    public KeyActionEvent(int _modifiers) {
        shift = _modifiers % 2 == 1;
        int rem_ = _modifiers - _modifiers % 2;
        control = rem_ % 4 != 0;
        alt = rem_ >= 8;
    }
    public KeyActionEvent(boolean _control, boolean _alt, boolean _shift) {
        control = _control;
        alt = _alt;
        shift = _shift;
    }
    @Override
    public boolean isControlDown() {
        return control;
    }

    @Override
    public boolean isAltDown() {
        return alt;
    }

    @Override
    public boolean isShiftDown() {
        return shift;
    }
}
