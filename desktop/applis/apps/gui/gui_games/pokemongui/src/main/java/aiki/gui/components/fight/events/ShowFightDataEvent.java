package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

public class ShowFightDataEvent implements AbsActionListener {

    private Battle battle;

    public ShowFightDataEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.showFightData();
    }
}
