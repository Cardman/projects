package cards.gui.containers;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.Ints;

public final class DefFirstDealPresident implements IntFirstDealPresident {
    @Override
    public GamePresident deal(ContainerPresident _container, RulesPresident _rules, long _nb) {
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        HandPresident pile_ = _container.chargerPilePresident(_rules.getNbStacks());
        DealPresident donne_=new DealPresident(_nb);
//        donne_.setRandomDealer(_rules,_container.getOwner().getGenerator());
        donne_.setDealer((int) MonteCarloUtil.randomLong(_rules.getNbPlayers(),_container.getOwner().getGenerator()));
        donne_.initDonne(_rules,_container.getOwner().getGenerator(),pile_);
        return new GamePresident(GameType.RANDOM,donne_,_rules, Ints.newList());
    }

    @Override
    public GamePresident deal(ContainerPresident _container) {
        RulesPresident regles_ = _container.getWindow().getReglesPresident();
        HandPresident pile_=HandPresident.stack(regles_.getNbStacks());
        DealPresident donne_=new DealPresident(0L);
//        donne_.setRandomDealer(regles_,container.getWindow().getGenerator());
        donne_.setDealer((int) MonteCarloUtil.randomLong(regles_.getNbPlayers(),_container.getWindow().getGenerator()));
        regles_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_,_container.getWindow().getGenerator(),pile_);
        return new GamePresident(GameType.EDIT,donne_,regles_, new Ints());
    }
}
