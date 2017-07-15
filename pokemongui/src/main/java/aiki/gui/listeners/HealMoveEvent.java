package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class HealMoveEvent extends MouseAdapter {

    private ScenePanel window;

    private String move;

    public HealMoveEvent(ScenePanel _window, String _move) {
        window = _window;
        move = _move;
    }

    @Override
    public void mouseReleased(MouseEvent _arg0) {
        window.healMove(move);
    }
}
