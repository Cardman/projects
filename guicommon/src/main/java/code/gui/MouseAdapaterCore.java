package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public final class MouseAdapaterCore extends MouseAdapter {

    private final MouseAdapter adapter;

    private final LabelButton button;

    public MouseAdapaterCore(MouseAdapter _adapter, LabelButton _button) {
        adapter = _adapter;
        button = _button;
    }

    @Override
    public void mouseClicked(MouseEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseClicked(_e);
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mousePressed(_e);
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseReleased(_e);
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseEntered(_e);
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseExited(_e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseWheelMoved(_e);
    }

    @Override
    public void mouseDragged(MouseEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseDragged(_e);
    }

    @Override
    public void mouseMoved(MouseEvent _e) {
        if (!button.isEnabledLabel()) {
            return;
        }
        adapter.mouseMoved(_e);
    }

}
