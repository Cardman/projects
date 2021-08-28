package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

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
