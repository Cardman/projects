package code.network;

import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.belote.RulesBelote;
import cards.consts.GameType;
import cards.gui.containers.ContainerBelote;

public final class BeloteSampleFirstDealNetFourAll extends BeloteSampleFirstDealNetAbs {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        DealBelote donne_=dealStdAll(0);
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }
}
