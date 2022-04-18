package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class LearntMoveEvent implements AbsActionListener {

    private ScenePanel scene;

    private String move;

    public LearntMoveEvent(ScenePanel _w, String _move) {
        scene = _w;
        move = _move;
    }

    @Override
    public void action() {
        scene.learntMove(move);
    }
}
