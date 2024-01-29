package cards.belote;

import cards.belote.enumerations.CardBelote;

public interface IntGameBelote {
    BidBeloteSuit strategieContrat(GameBelote _game);
    BidBeloteSuit strategieContratUser(BidBeloteSuit _game);
    BidBeloteSuit currentBid();
    CardBelote strategieJeuCarteUnique(GameBelote _game);
    CardBelote strategieJeuCarteUniqueUser(CardBelote _game);
    CardBelote currentCard();
}
