package aiki.gui.components.fight.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.fight.Battle;

public class FleeEvent extends MouseAdapter {

    private Battle battle;

    public FleeEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        battle.flee();
    }
}
