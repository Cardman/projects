package cards.gui.containers;

import cards.consts.GameType;
import cards.belote.*;
import code.maths.montecarlo.MonteCarloUtil;

public final class DefFirstDealBelote implements IntFirstDealBelote {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        HandBelote pile_ = _container.chargerPileBelote();
        DealBelote donne_=new DealBelote(_nb);
        donne_.setDealer((byte) MonteCarloUtil.randomLong(_rules.getDealing().getId().getNombreJoueurs(),_container.getOwner().getGenerator()));
        donne_.initDonne(_rules,_container.getDisplayingBelote(),_container.getOwner().getGenerator(),pile_);
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }
}
