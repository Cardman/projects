package aiki.gui.listeners;

import aiki.game.fight.enums.ActionType;
import aiki.gui.components.fight.Battle;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public class FighterAction implements AbsMouseListenerIntRel {

    private Battle battle;

    private ActionType action;

    public FighterAction(Battle _battle, ActionType _action) {
        battle = _battle;
        action = _action;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.changeAction(action);
    }
}
