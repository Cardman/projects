package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class MoveEvent extends AbsMouseListenerRel {

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
