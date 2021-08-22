package aiki.gui.listeners;

import aiki.game.fight.enums.ActionType;
import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class FighterAction extends AbsMouseListenerRel {

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
