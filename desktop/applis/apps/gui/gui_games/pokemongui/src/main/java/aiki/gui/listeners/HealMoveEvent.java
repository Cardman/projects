package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class HealMoveEvent extends AbsMouseListenerRel {

    private ScenePanel window;

    private String move;

    public HealMoveEvent(ScenePanel _window, String _move) {
        window = _window;
        move = _move;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        window.healMove(move);
    }
}
