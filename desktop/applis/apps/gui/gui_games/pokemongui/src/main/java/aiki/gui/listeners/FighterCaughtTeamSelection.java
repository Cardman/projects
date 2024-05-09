package aiki.gui.listeners;
import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

public final class FighterCaughtTeamSelection implements AbsActionListener {

    private final Battle battle;

    public FighterCaughtTeamSelection(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.chooseFighterCaughtTeam();
    }

}
