package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class HealMoveEvent implements AbsActionListener {

    private ScenePanel window;

    private String move;

    public HealMoveEvent(ScenePanel _window, String _move) {
        window = _window;
        move = _move;
    }

    @Override
    public void action() {
        window.healMove(move);
    }
}
