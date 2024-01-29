package cards.president;

public interface IntGamePresident {
    HandPresident strategieEchange(GamePresident _game,byte _joueur);
    HandPresident strategieEchangeUser(HandPresident _hand);
    HandPresident currentSwitch();
    HandPresident playedCards(GamePresident _game);
    HandPresident playedCardsUser(HandPresident _game);
    HandPresident currentCards();
}
