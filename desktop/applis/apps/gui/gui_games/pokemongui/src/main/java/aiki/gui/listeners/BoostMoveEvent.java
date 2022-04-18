package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class BoostMoveEvent implements AbsActionListener {

    private ScenePanel window;

    private String move;

    public BoostMoveEvent(ScenePanel _window, String _move) {
        window = _window;
        move = _move;
    }

    @Override
    public void action() {
        window.boostMove(move);
    }
}
