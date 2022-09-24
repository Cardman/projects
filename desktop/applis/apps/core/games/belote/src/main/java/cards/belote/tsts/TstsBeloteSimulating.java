package cards.belote.tsts;

import cards.belote.AbstractSimulatingBelote;
import cards.belote.DisplayingBelote;
import cards.belote.GameBelote;

public abstract class TstsBeloteSimulating extends AbstractSimulatingBelote {
    private final TstsBeloteStoppedSimuInt stoppedBeloteSimuInt;
    protected TstsBeloteSimulating(DisplayingBelote _d, GameBelote _g, TstsBeloteStoppedSimuInt _s) {
        super(_d, _g);
        stoppedBeloteSimuInt = _s;
    }

    @Override
    public boolean stopped() {
        return stoppedBeloteSimuInt.stopped();
    }
}
