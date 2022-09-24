package cards.belote.tsts;

import cards.belote.AbstractSimulatingBelote;
import cards.belote.DisplayingBelote;
import cards.belote.GameBelote;

public abstract class TstsSimulatingBelote extends AbstractSimulatingBelote {
    private final TstsStoppedSimuInt stoppedSimuInt;
    protected TstsSimulatingBelote(DisplayingBelote _d, GameBelote _g, TstsStoppedSimuInt _s) {
        super(_d, _g);
        stoppedSimuInt = _s;
    }

    @Override
    public boolean stopped() {
        return stoppedSimuInt.stopped();
    }
}
