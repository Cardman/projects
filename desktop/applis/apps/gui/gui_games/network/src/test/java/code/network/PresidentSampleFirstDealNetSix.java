package code.network;

import cards.consts.GameType;
import cards.gui.containers.ContainerPresident;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.RulesPresident;
import code.util.Ints;

public final class PresidentSampleFirstDealNetSix extends PresidentSampleFirstDealNetAbs {
    @Override
    public GamePresident deal(ContainerPresident _container, RulesPresident _rules, long _nb) {
        return new GamePresident(GameType.RANDOM,new DealPresident(dealSixPlayers(), 5),_rules,new Ints());
    }
}
