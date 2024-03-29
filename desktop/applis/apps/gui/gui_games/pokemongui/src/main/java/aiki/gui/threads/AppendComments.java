package aiki.gui.threads;
import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;

public final class AppendComments implements Runnable {

    private FacadeGame facade;

    private Battle battle;

    public AppendComments(FacadeGame _facade, Battle _battle) {
        facade = _facade;
        battle = _battle;
    }

    @Override
    public void run() {
        if (facade.getFight().getTemp().isKeepRound()) {
            battle.appendComments();
        }
    }
}
