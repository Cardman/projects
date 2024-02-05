package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdMap;

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

    @Override
    public IdMap<Suit, CustList<HandBelote>> cartesPossibles(GameBeloteTrickInfo _info, HandBelote _curHand) {
        return _info.cartesPossibles(_curHand);
    }

    @Override
    public IdMap<Hypothesis,IdMap<Suit,CustList<HandBelote>>> cartesCertaines(GameBeloteTrickInfo _info, IdMap<Suit, CustList<HandBelote>> _possible) {
        return _info.cartesCertaines(_possible);
    }
}
