package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.threads.ThreadUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class RoundFailBallThread extends RoundThread {

    private String ball;

    /**@param _ball the used ball*/
    public RoundFailBallThread(FacadeGame _facade, Battle _battle, String _ball) {
        super(_facade, _battle);
        ball = _ball;
    }

    @Override
    public void run() {
        getBattle().initBall();
        while (getBattle().isKeepAnimation()) {
            ThreadUtil.sleep(getBattle().getWindow().getThreadFactory(),5l);
            getBattle().moveBall(getBattle().getWindow().getImageFactory(),ball);
        }
        round();
        getFacade().endRoundFightBall();
        animate();
//        getBattle().afterRound(true);
        getBattle().getFrames().getCompoFactory().invokeNow(new AfterRoundThread(getBattle()));
        //getBattle().afterRound();
    }
}
