package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;

/**This class thread is independant from EDT,
Thread safe class*/
public final class RoundFleeThread extends RoundThread {

    public RoundFleeThread(FacadeGame _facade, Battle _battle) {
        super(_facade, _battle);
    }

    @Override
    public void run() {
        round();
        getFacade().endRoundFightFlee();
        animate();
//        getBattle().afterRound(true);
        getBattle().getFrames().getCompoFactory().invokeNow(new AfterRoundThread(getBattle()));
        //getBattle().afterRound();
    }
}
