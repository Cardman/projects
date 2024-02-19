package cards.gui.containers;

import cards.consts.*;
import cards.tarot.*;
import code.maths.montecarlo.*;

public final class DefFirstDealTarot implements IntFirstDealTarot {
    @Override
    public GameTarot deal(ContainerTarot _container, RulesTarot _rules, long _nb) {
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        HandTarot pile_ = _container.chargerPileTarot();
        DealTarot donne_=new DealTarot(_nb);
        donne_.setDealer((byte) MonteCarloUtil.randomLong(_rules.getDealing().getId().getNombreJoueurs(),_container.getOwner().getGenerator()));
        donne_.initDonne(_rules, _container.getOwner().getGenerator(),pile_);
        return new GameTarot(GameType.RANDOM,donne_,_rules);
    }
}
