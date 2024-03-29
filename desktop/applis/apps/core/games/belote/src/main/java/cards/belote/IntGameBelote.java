package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.*;
import code.maths.montecarlo.*;
import code.util.*;

public interface IntGameBelote {
    BidBeloteSuit strategieContrat(GameBelote _game);
    BidBeloteSuit strategieContratUser(BidBeloteSuit _game);
    BidBeloteSuit currentBid();
    HandBelote strategieEcart(GameBelote _g);
    CardBelote discard(CardBelote _c);
    CardBelote restore(CardBelote _c);
    boolean annoncerUnChelem(GameBelote _g);
    CardBelote strategieJeuCarteUnique(GameBelote _game);
    CardBelote strategieJeuCarteUniqueUser(CardBelote _game);
    CardBelote currentCard();
    IdMap<Suit, CustList<HandBelote>> cartesPossibles(GameBeloteTrickInfo _info,
            HandBelote _curHand);
    IdMap<Hypothesis,IdMap<Suit,CustList<HandBelote>>> cartesCertaines(GameBeloteTrickInfo _info,
                                                                       IdMap<Suit, CustList<HandBelote>> _possible);
    DealBelote empiler(long _nb, GameBelote _game, AbstractGenerator _gene);
}
