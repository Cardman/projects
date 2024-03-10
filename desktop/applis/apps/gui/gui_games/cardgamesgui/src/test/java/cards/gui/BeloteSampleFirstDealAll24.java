package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.Suit;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.IntFirstDealBelote;
import code.mock.MockGameBelote;

public final class BeloteSampleFirstDealAll24 implements IntFirstDealBelote {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        DealBelote donne_=dealThreePlayers24Coinche();
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }

    @Override
    public GameBelote deal(ContainerBelote _container) {
        DealBelote donne_=dealThreePlayers24Coinche();
        RulesBelote regles_ = _container.getWindow().getReglesBelote();
        return new GameBelote(GameType.EDIT,donne_,regles_);
    }


    private static DealBelote dealThreePlayers24Coinche() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1,
                CardBelote.HEART_1,CardBelote.CLUB_1,CardBelote.DIAMOND_10));
        db_.getDeal().add(create(CardBelote.CLUB_9,
                CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_9,CardBelote.SPADE_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,
                CardBelote.CLUB_10));
        db_.getDeal().add(create(
                CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private static HandBelote create(CardBelote... _cb) {
        return HandBelote.create(_cb);
    }
    public static void playMockDealAll(MockGameBelote _mock) {
        nextBid(_mock, bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.HEART,162,BidBelote.SUIT));
        nextBid(_mock, bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextCard(_mock, CardBelote.HEART_JACK);
        nextCard(_mock, CardBelote.HEART_QUEEN);
        nextCard(_mock, CardBelote.CLUB_QUEEN);
        nextCard(_mock, CardBelote.HEART_9);
        nextCard(_mock, CardBelote.HEART_KING);
        nextCard(_mock, CardBelote.CLUB_KING);
        nextCard(_mock, CardBelote.HEART_1);
        nextCard(_mock, CardBelote.CLUB_9);
        nextCard(_mock, CardBelote.DIAMOND_JACK);
        nextCard(_mock, CardBelote.HEART_10);
        nextCard(_mock, CardBelote.SPADE_9);
        nextCard(_mock, CardBelote.SPADE_JACK);
        nextCard(_mock, CardBelote.DIAMOND_1);
        nextCard(_mock, CardBelote.DIAMOND_9);
        nextCard(_mock, CardBelote.DIAMOND_QUEEN);
        nextCard(_mock, CardBelote.DIAMOND_10);
        nextCard(_mock, CardBelote.SPADE_KING);
        nextCard(_mock, CardBelote.DIAMOND_KING);
        nextCard(_mock, CardBelote.SPADE_1);
        nextCard(_mock, CardBelote.SPADE_10);
        nextCard(_mock, CardBelote.CLUB_JACK);
        nextCard(_mock, CardBelote.CLUB_1);
        nextCard(_mock, CardBelote.SPADE_QUEEN);
        nextCard(_mock, CardBelote.CLUB_10);
    }

    private static void nextBid(MockGameBelote _m, BidBeloteSuit _bid) {
        _m.getBids().add(_bid);
    }

    private static void nextCard(MockGameBelote _m, CardBelote _bid) {
        _m.getCards().add(_bid);
    }

    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }

}
