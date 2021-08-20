package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class AbilityFightEvent extends AbsMouseListenerRel {

    private Battle battle;

    private String ability;

    public AbilityFightEvent(Battle _battle, String _ability) {
        battle = _battle;
        ability = _ability;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        battle.changeAbility(ability);
    }
}
