package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class LearntMoveEvent extends MouseAdapter {

    private ScenePanel scene;

    private String move;

    public LearntMoveEvent(ScenePanel _w, String _move) {
        scene = _w;
        move = _move;
    }

    @Override
    public void mouseReleased(MouseEvent _arg0) {
        scene.learntMove(move);
    }
}
