package cards.president;

public final class DefGamePresident implements IntGamePresident {
    @Override
    public HandPresident strategieEchange(GamePresident _game, byte _joueur) {
        return _game.strategieEchange(_joueur);
    }

    @Override
    public HandPresident strategieEchangeUser(HandPresident _hand) {
        return _hand;
    }

    @Override
    public HandPresident currentSwitch() {
        return new HandPresident();
    }

    @Override
    public HandPresident playedCards(GamePresident _game) {
        return _game.playedCards();
    }

    @Override
    public HandPresident playedCardsUser(HandPresident _game) {
        return _game;
    }

    @Override
    public HandPresident currentCards() {
        return new HandPresident();
    }
}
