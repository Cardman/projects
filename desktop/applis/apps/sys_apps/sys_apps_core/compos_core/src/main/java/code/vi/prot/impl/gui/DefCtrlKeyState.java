package code.vi.prot.impl.gui;

import code.gui.KeyActionEvent;

import java.awt.event.InputEvent;

public final class DefCtrlKeyState extends KeyActionEvent {

    public DefCtrlKeyState(InputEvent _action) {
        super(_action.isControlDown(), _action.isAltDown(),_action.isShiftDown());
    }

}
