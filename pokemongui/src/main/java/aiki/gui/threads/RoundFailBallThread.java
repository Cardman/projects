package aiki.gui.threads;
import javax.swing.SwingUtilities;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.gui.CustComponent;
import code.gui.ThreadUtil;

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
            ThreadUtil.sleep(5l);
            getBattle().moveBall(ball);
        }
        round();
        getFacade().endRoundFightBall();
        animate();
//        getBattle().afterRound(true);
        CustComponent.invokeLater(new AfterRoundThread(getBattle()));
        //getBattle().afterRound();
    }
}
