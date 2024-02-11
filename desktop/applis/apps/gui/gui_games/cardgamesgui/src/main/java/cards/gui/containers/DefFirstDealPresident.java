package cards.gui.containers;

import cards.consts.GameType;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.Bytes;

public final class DefFirstDealPresident implements IntFirstDealPresident {
    @Override
    public GamePresident deal(ContainerPresident _container, RulesPresident _rules, long _nb) {
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        HandPresident pile_ = _container.chargerPilePresident(_rules.getNbStacks());
        DealPresident donne_=new DealPresident(_nb);
//        donne_.setRandomDealer(_rules,_container.getOwner().getGenerator());
        donne_.setDealer((byte) MonteCarloUtil.randomLong(_rules.getNbPlayers(),_container.getOwner().getGenerator()));
        donne_.initDonne(_rules,_container.getOwner().getGenerator(),pile_);
        return new GamePresident(GameType.RANDOM,donne_,_rules, Bytes.newList());
    }
}
