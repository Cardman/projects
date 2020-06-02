package aiki.gui.threads;
import javax.swing.SwingUtilities;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.gui.CustComponent;

/**This class thread is independant from EDT,
Thread safe class*/
public final class RoundKoUserThread extends RoundThread {

    public RoundKoUserThread(FacadeGame _facade, Battle _battle) {
        super(_facade, _battle);
    }

    @Override
    public void run() {
        round();
        getFacade().endRoundFightKoUser();
        animate();
//        getBattle().afterRound(true);
        CustComponent.invokeLater(new AfterRoundThread(getBattle()));
        //getBattle().afterRound();
    }
}
