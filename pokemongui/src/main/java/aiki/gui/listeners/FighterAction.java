package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.game.fight.enums.ActionType;
import aiki.gui.components.fight.Battle;

public class FighterAction extends MouseAdapter {

    private Battle battle;

    private ActionType action;

    public FighterAction(Battle _battle, ActionType _action) {
        battle = _battle;
        action = _action;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        battle.changeAction(action);
    }
}
