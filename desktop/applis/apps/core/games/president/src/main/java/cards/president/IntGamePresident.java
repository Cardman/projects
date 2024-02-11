package cards.president;

import code.maths.montecarlo.AbstractGenerator;

public interface IntGamePresident {
    HandPresident strategieEchange(GamePresident _game,byte _joueur);
    HandPresident strategieEchangeUser(HandPresident _hand);
    HandPresident currentSwitch();
    HandPresident playedCards(GamePresident _game);
    HandPresident playedCardsUser(HandPresident _game);
    HandPresident currentCards();
    DealPresident empiler(long _nb, GamePresident _game, AbstractGenerator _gene);
}
