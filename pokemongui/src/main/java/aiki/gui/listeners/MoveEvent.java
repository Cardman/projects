package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.fight.Battle;

public class MoveEvent extends MouseAdapter {

    private Battle battle;

    private String move;

    public MoveEvent(Battle _battle, String _move) {
        battle = _battle;
        move = _move;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        battle.changeMove(move);
    }
}
