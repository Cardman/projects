package cards.tarot.tsts;

import cards.tarot.AbstractSimulatingTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.GameTarot;

public abstract class TstsTarotSimulating extends AbstractSimulatingTarot {
    private final TstsTarotStoppedSimuInt stoppedTarotSimuInt;
    protected TstsTarotSimulating(DisplayingTarot _d, GameTarot _g, TstsTarotStoppedSimuInt _s) {
        super(_d, _g);
        this.stoppedTarotSimuInt = _s;
    }

    @Override
    public boolean stopped() {
        return stoppedTarotSimuInt.stopped();
    }
}
