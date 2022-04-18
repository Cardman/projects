package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public class MoveEvent implements AbsMouseListenerIntRel {

    private Battle battle;

    private String move;

    public MoveEvent(Battle _battle, String _move) {
        battle = _battle;
        move = _move;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.changeMove(move);
    }
}
