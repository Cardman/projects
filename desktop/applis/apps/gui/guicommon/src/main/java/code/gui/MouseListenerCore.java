package code.gui;

import code.gui.events.AbsMouseListener;

public final class MouseListenerCore implements AbsMouseListener {

    private final AbsMouseListener adapter;

    private final LabelButton button;

    public MouseListenerCore(AbsMouseListener _adapter, LabelButton _button) {
        adapter = _adapter;
        button = _button;
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseClicked(_location, _keyState, _buttons);
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mousePressed(_location, _keyState, _buttons);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseReleased(_location, _keyState, _buttons);
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseEntered(_location, _keyState, _buttons);
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseExited(_location, _keyState, _buttons);
    }

}
