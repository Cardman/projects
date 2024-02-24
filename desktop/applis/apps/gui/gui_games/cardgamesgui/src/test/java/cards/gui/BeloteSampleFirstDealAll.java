package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.Suit;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.IntFirstDealBelote;
import code.mock.MockGameBelote;
import code.util.CustList;

public final class BeloteSampleFirstDealAll implements IntFirstDealBelote {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        DealBelote donne_=dealAll(0);
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }

    @Override
    public GameBelote deal(ContainerBelote _container) {
        DealBelote donne_=dealAll(0);
        RulesBelote regles_ = _container.getWindow().getReglesBelote();
        return new GameBelote(GameType.EDIT,donne_,regles_);
    }

    public static void playMockDealAll(MockGameBelote _mock) {
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextCard(_mock, CardBelote.SPADE_JACK);
        nextCard(_mock, CardBelote.DIAMOND_JACK);
        nextCard(_mock, CardBelote.CLUB_JACK);
        nextCard(_mock, CardBelote.HEART_JACK);
        nextCard(_mock, CardBelote.HEART_9);
        nextCard(_mock, CardBelote.SPADE_9);
        nextCard(_mock, CardBelote.DIAMOND_9);
        nextCard(_mock, CardBelote.CLUB_9);
        nextCard(_mock, CardBelote.HEART_1);
        nextCard(_mock, CardBelote.SPADE_1);
        nextCard(_mock, CardBelote.DIAMOND_1);
        nextCard(_mock, CardBelote.CLUB_1);
        nextCard(_mock, CardBelote.HEART_10);
        nextCard(_mock, CardBelote.SPADE_10);
        nextCard(_mock, CardBelote.DIAMOND_10);
        nextCard(_mock, CardBelote.CLUB_10);
        nextCard(_mock, CardBelote.HEART_KING);
        nextCard(_mock, CardBelote.SPADE_KING);
        nextCard(_mock, CardBelote.DIAMOND_KING);
        nextCard(_mock, CardBelote.CLUB_KING);
        nextCard(_mock, CardBelote.HEART_QUEEN);
        nextCard(_mock, CardBelote.SPADE_QUEEN);
        nextCard(_mock, CardBelote.DIAMOND_QUEEN);
        nextCard(_mock, CardBelote.CLUB_QUEEN);
        nextCard(_mock, CardBelote.HEART_8);
        nextCard(_mock, CardBelote.SPADE_8);
        nextCard(_mock, CardBelote.DIAMOND_8);
        nextCard(_mock, CardBelote.CLUB_8);
        nextCard(_mock, CardBelote.HEART_7);
        nextCard(_mock, CardBelote.SPADE_7);
        nextCard(_mock, CardBelote.DIAMOND_7);
        nextCard(_mock, CardBelote.CLUB_7);
    }

    public static void playMockDealAllSlam(MockGameBelote _mock) {
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.HEART, 162, BidBelote.SUIT));
        nextCard(_mock, CardBelote.HEART_JACK);
        nextCard(_mock, CardBelote.SPADE_JACK);
        nextCard(_mock, CardBelote.DIAMOND_JACK);
        nextCard(_mock, CardBelote.CLUB_JACK);
        nextCard(_mock, CardBelote.HEART_9);
        nextCard(_mock, CardBelote.SPADE_9);
        nextCard(_mock, CardBelote.DIAMOND_9);
        nextCard(_mock, CardBelote.CLUB_9);
        nextCard(_mock, CardBelote.HEART_1);
        nextCard(_mock, CardBelote.SPADE_1);
        nextCard(_mock, CardBelote.DIAMOND_1);
        nextCard(_mock, CardBelote.CLUB_1);
        nextCard(_mock, CardBelote.HEART_10);
        nextCard(_mock, CardBelote.SPADE_10);
        nextCard(_mock, CardBelote.DIAMOND_10);
        nextCard(_mock, CardBelote.CLUB_10);
        nextCard(_mock, CardBelote.HEART_KING);
        nextCard(_mock, CardBelote.SPADE_KING);
        nextCard(_mock, CardBelote.DIAMOND_KING);
        nextCard(_mock, CardBelote.CLUB_KING);
        nextCard(_mock, CardBelote.HEART_QUEEN);
        nextCard(_mock, CardBelote.SPADE_QUEEN);
        nextCard(_mock, CardBelote.DIAMOND_QUEEN);
        nextCard(_mock, CardBelote.CLUB_QUEEN);
        nextCard(_mock, CardBelote.HEART_8);
        nextCard(_mock, CardBelote.SPADE_8);
        nextCard(_mock, CardBelote.DIAMOND_8);
        nextCard(_mock, CardBelote.CLUB_8);
        nextCard(_mock, CardBelote.HEART_7);
        nextCard(_mock, CardBelote.SPADE_7);
        nextCard(_mock, CardBelote.DIAMOND_7);
        nextCard(_mock, CardBelote.CLUB_7);
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

    public static DealBelote dealAll(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        firstHands(hands_);
        hands_.get(0).ajouterCartes(HandBelote.create(new CardBelote[]{CardBelote.HEART_QUEEN,CardBelote.HEART_8,CardBelote.HEART_7}));
        hands_.get(1).ajouterCartes(HandBelote.create(new CardBelote[]{CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.SPADE_7}));
        hands_.get(2).ajouterCartes(HandBelote.create(new CardBelote[]{CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7}));
        hands_.get(3).ajouterCartes(HandBelote.create(new CardBelote[]{CardBelote.CLUB_QUEEN,CardBelote.CLUB_8,CardBelote.CLUB_7}));
        HandBelote hand_;
        hand_ = new HandBelote();
        hands_.add(hand_);
        return new DealBelote(hands_, (byte) _dealer);
    }

    private static void firstHands(CustList<HandBelote> _hands) {
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.SPADE_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_KING);
        _hands.add(hand_);
    }
}
