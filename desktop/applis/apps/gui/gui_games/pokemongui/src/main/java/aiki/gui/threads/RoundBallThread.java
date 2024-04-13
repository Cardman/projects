package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.game.fight.CatchingBallFoeAction;
import aiki.game.fight.enums.FightState;
import aiki.gui.components.fight.Battle;
import code.threads.ThreadUtil;
import code.util.EntryCust;
import code.util.IntMap;

/**This class thread is independant from EDT,
Thread safe class*/
public final class RoundBallThread extends RoundThread {

    private final IntMap<CatchingBallFoeAction> balls;

    /** */
    public RoundBallThread(FacadeGame _facade, Battle _battle, IntMap<CatchingBallFoeAction> _balls) {
        super(_facade, _battle);
        balls = _balls;
    }

    @Override
    public void run() {
        for (EntryCust<Integer, CatchingBallFoeAction> e: balls.entryList()) {
            getBattle().initBall();
            while (getBattle().isKeepAnimation()) {
                ThreadUtil.sleep(getBattle().getWindow().getThreadFactory(),5L);
                getBattle().moveBall(getBattle().getWindow().getImageFactory(), e.getKey(), e.getValue().getCatchingBall(), e.getValue().isCaught());
            }
        }
        if (getFacade().getFight().getState() != FightState.SURNOM) {
            round();
            getFacade().endRoundFightBall();
            animate();
        } else {
            getFacade().endRoundFightSuccessBall();
        }
//        getBattle().afterRound(true);
        getBattle().getFrames().getCompoFactory().invokeNow(new AfterRoundThread(getBattle()));
        //getBattle().afterRound();
    }
}
