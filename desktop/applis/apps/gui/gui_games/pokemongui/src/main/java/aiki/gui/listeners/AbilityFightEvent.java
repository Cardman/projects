package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.fight.Battle;

public class AbilityFightEvent extends MouseAdapter {

    private Battle battle;

    private String ability;

    public AbilityFightEvent(Battle _battle, String _ability) {
        battle = _battle;
        ability = _ability;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        battle.changeAbility(ability);
    }
}
