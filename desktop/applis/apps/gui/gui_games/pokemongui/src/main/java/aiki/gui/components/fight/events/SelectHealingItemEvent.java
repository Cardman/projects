package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

public class SelectHealingItemEvent implements AbsActionListener {

    private Battle battle;

    public SelectHealingItemEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.selectHealingItem();
    }
}
