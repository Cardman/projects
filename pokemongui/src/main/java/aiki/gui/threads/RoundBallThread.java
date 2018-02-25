package aiki.gui.threads;
import javax.swing.SwingUtilities;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.util.consts.Constants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class RoundBallThread extends RoundThread {

    private String ball;

    /**@param _ball the used ball*/
    public RoundBallThread(FacadeGame _facade, Battle _battle, String _ball) {
        super(_facade, _battle);
        ball = _ball;
    }

    @Override
    public void run() {
        getBattle().initBall();
        while (getBattle().isKeepAnimation()) {
            Constants.sleep(5l);
            getBattle().moveBall(ball);
        }
        getFacade().endRoundFightSuccessBall();
//        getBattle().afterRound(true);
        SwingUtilities.invokeLater(new AfterRoundThread(getBattle()));
        //getBattle().afterRound();
    }
}
