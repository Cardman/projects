package cards.belote;

import cards.belote.enumerations.CardBelote;

public final class DefGameBelote implements IntGameBelote{
    @Override
    public BidBeloteSuit strategieContrat(GameBelote _game) {
        return _game.strategieContrat();
    }

    @Override
    public BidBeloteSuit strategieContratUser(BidBeloteSuit _game) {
        return _game;
    }

    @Override
    public BidBeloteSuit currentBid() {
        return new BidBeloteSuit();
    }

    @Override
    public CardBelote strategieJeuCarteUnique(GameBelote _game) {
        return _game.strategieJeuCarteUnique();
    }

    @Override
    public CardBelote strategieJeuCarteUniqueUser(CardBelote _game) {
        return _game;
    }

    @Override
    public CardBelote currentCard() {
        return CardBelote.WHITE;
    }
}
