package code.network;

import cards.belote.*;
import cards.consts.*;
import cards.gui.containers.*;

public final class BeloteSampleFirstDealNetFourClassic extends BeloteSampleFirstDealNetAbs {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        DealBelote donne_=dealStdClassic(0);
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }
}
