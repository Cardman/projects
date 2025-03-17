package cards.gui.containers;

import cards.consts.GameType;
import cards.belote.*;
import cards.consts.MixCardsChoice;
import code.maths.montecarlo.MonteCarloUtil;

public final class DefFirstDealBelote implements IntFirstDealBelote {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        HandBelote pile_;
        if (_rules.splitHand()) {
            pile_ = _container.chargerPileBeloteShort();
        } else {
            pile_ = _container.chargerPileBelote();
        }
        DealBelote donne_=new DealBelote(_nb);
        donne_.setDealer((int) MonteCarloUtil.randomLong(_rules.getDealing().getId().getNombreJoueurs(),_container.getOwner().getGenerator()));
        donne_.initDonne(_rules, _container.getOwner().getGenerator(),pile_);
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }

    @Override
    public GameBelote deal(ContainerBelote _container) {
        HandBelote pile_=HandBelote.pileBase();
        DealBelote donne_=new DealBelote(0L);
        RulesBelote regles_ = _container.getWindow().getReglesBelote();
        donne_.setDealer((int) MonteCarloUtil.randomLong(regles_.getDealing().getId().getNombreJoueurs(),_container.getWindow().getGenerator()));
        regles_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_, _container.getWindow().getGenerator(),pile_);
        return new GameBelote(GameType.EDIT,donne_,regles_);
    }
}
