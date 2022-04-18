package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

public class RoundAllThrowersEvent implements AbsActionListener {

    private Battle battle;

    public RoundAllThrowersEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.roundAllThrowers();
    }
}
