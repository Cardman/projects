package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public class AbilityFightEvent implements AbsMouseListenerIntRel {

    private Battle battle;

    private String ability;

    public AbilityFightEvent(Battle _battle, String _ability) {
        battle = _battle;
        ability = _ability;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.changeAbility(ability);
    }
}
