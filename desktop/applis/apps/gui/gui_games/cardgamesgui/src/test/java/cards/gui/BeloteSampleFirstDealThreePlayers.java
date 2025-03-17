package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.Suit;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.IntFirstDealBelote;
import code.mock.MockGameBelote;
import code.util.core.BoolVal;

public final class BeloteSampleFirstDealThreePlayers implements IntFirstDealBelote {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        DealBelote donne_=dealThreePlayers();
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }

    @Override
    public GameBelote deal(ContainerBelote _container) {
        DealBelote donne_=dealThreePlayers();
        RulesBelote regles_ = _container.getWindow().getReglesBelote();
        return new GameBelote(GameType.EDIT,donne_,regles_);
    }
    public static void mock(MockGameBelote _mock) {
        nextBid(_mock,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscardIa(_mock, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(_mock, BoolVal.TRUE);
        nextCard(_mock,CardBelote.HEART_JACK);
        nextCard(_mock,CardBelote.HEART_7);
        nextCard(_mock,CardBelote.SPADE_7);
        nextCard(_mock,CardBelote.HEART_9);
        nextCard(_mock,CardBelote.HEART_8);
        nextCard(_mock,CardBelote.SPADE_8);
        nextCard(_mock,CardBelote.HEART_1);
        nextCard(_mock,CardBelote.HEART_QUEEN);
        nextCard(_mock,CardBelote.CLUB_JACK);
        nextCard(_mock,CardBelote.HEART_10);
        nextCard(_mock,CardBelote.HEART_KING);
        nextCard(_mock,CardBelote.SPADE_JACK);
        nextCard(_mock,CardBelote.DIAMOND_1);
        nextCard(_mock,CardBelote.CLUB_7);
        nextCard(_mock,CardBelote.DIAMOND_9);
        nextCard(_mock,CardBelote.DIAMOND_10);
        nextCard(_mock,CardBelote.SPADE_9);
        nextCard(_mock,CardBelote.DIAMOND_JACK);
        nextCard(_mock,CardBelote.SPADE_1);
        nextCard(_mock,CardBelote.SPADE_10);
        nextCard(_mock,CardBelote.DIAMOND_QUEEN);
        nextCard(_mock,CardBelote.CLUB_1);
        nextCard(_mock,CardBelote.CLUB_10);
        nextCard(_mock,CardBelote.DIAMOND_KING);
    }
    private static void nextSlam(MockGameBelote _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private static void nextDiscard(MockGameBelote _m, CardBelote _bid) {
        _m.getDiscard().add(_bid);
    }
    private static void nextDiscardIa(MockGameBelote _m, CardBelote... _bid) {
        _m.getDiscardIa().add(create(_bid));
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

    public static DealBelote dealThreePlayers() {
        DealBelote db_ = new DealBelote();
        db_.setDealer(1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    private static HandBelote create(CardBelote... _cb) {
        return HandBelote.create(_cb);
    }

}
