package aiki.gui.components.checks;
import code.gui.*;
import code.gui.events.*;

public final class CheckEvent implements AbsMouseListenerIntRel {

    private final CheckBox checkBox;

    public CheckEvent(CheckBox _checkBox) {
        checkBox = _checkBox;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        checkBox.toggle();
        checkBox.processKey(checkBox.getKey());
    }

}
