package code.network;

import cards.consts.GameType;
import cards.gui.containers.ContainerTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.RulesTarot;

public final class TarotSampleFirstDealNetFour extends TarotSampleFirstDealNetAbs {
    @Override
    public GameTarot deal(ContainerTarot _container, RulesTarot _rules, long _nb) {
        return new GameTarot(GameType.RANDOM,new DealTarot(dealFourPlayers(), 3),_rules);
    }
}
