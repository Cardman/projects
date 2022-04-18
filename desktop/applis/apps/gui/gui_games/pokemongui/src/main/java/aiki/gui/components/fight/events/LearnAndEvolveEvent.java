package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

public class LearnAndEvolveEvent implements AbsActionListener {

    private Battle battle;

    public LearnAndEvolveEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.learnAndEvolve();
    }
}
