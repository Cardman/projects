package cards.gui;

import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.consts.*;
import cards.gui.containers.*;
import code.mock.MockGameTarot;
import code.util.*;
import code.util.core.BoolVal;

public final class TarotSampleFirstDeal implements IntFirstDealTarot {
    @Override
    public GameTarot deal(ContainerTarot _container, RulesTarot _rules, long _nb) {
        DealTarot donne_=deal7(4);
        return new GameTarot(GameType.RANDOM,donne_,_rules);
    }

    @Override
    public GameTarot deal(ContainerTarot _container) {
        RulesTarot regles_ = _container.getWindow().getReglesTarot();
        DealTarot donne_=deal7(4);
        return new GameTarot(GameType.EDIT,donne_,regles_);
    }

    public static void simu1(MockGameTarot _mock) {
        nextBid(_mock, BidTarot.SLAM);
        nextSlam(_mock, BoolVal.TRUE);
        nextHandful(_mock, Handfuls.TWO, CardTarot.TRUMP_21, CardTarot.TRUMP_20, CardTarot.TRUMP_19, CardTarot.TRUMP_18, CardTarot.TRUMP_17, CardTarot.TRUMP_16, CardTarot.TRUMP_14, CardTarot.TRUMP_10, CardTarot.TRUMP_4, CardTarot.TRUMP_1);
        nextMisere(_mock, Miseres.LOW_CARDS);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextCard(_mock, CardTarot.TRUMP_21);
        nextCard(_mock, CardTarot.TRUMP_3);
        nextCard(_mock, CardTarot.EXCUSE);
        nextCard(_mock, CardTarot.TRUMP_5);
        nextCard(_mock, CardTarot.TRUMP_7);
        nextCard(_mock, CardTarot.TRUMP_20);
        nextCard(_mock, CardTarot.TRUMP_6);
        nextCard(_mock, CardTarot.TRUMP_2);
        nextCard(_mock, CardTarot.TRUMP_9);
        nextCard(_mock, CardTarot.TRUMP_11);
        nextCard(_mock, CardTarot.TRUMP_19);
        nextCard(_mock, CardTarot.TRUMP_13);
        nextCard(_mock, CardTarot.TRUMP_8);
        nextCard(_mock, CardTarot.TRUMP_15);
        nextCard(_mock, CardTarot.CLUB_3);
        nextCard(_mock, CardTarot.TRUMP_18);
        nextCard(_mock, CardTarot.CLUB_4);
        nextCard(_mock, CardTarot.TRUMP_12);
        nextCard(_mock, CardTarot.SPADE_3);
        nextCard(_mock, CardTarot.CLUB_6);
        nextCard(_mock, CardTarot.TRUMP_17);
        nextCard(_mock, CardTarot.SPADE_4);
        nextCard(_mock, CardTarot.SPADE_1);
        nextCard(_mock, CardTarot.DIAMOND_5);
        nextCard(_mock, CardTarot.HEART_5);
        nextCard(_mock, CardTarot.TRUMP_16);
        nextCard(_mock, CardTarot.HEART_4);
        nextCard(_mock, CardTarot.SPADE_2);
        nextCard(_mock, CardTarot.SPADE_8);
        nextCard(_mock, CardTarot.SPADE_6);
        nextCard(_mock, CardTarot.TRUMP_14);
        nextCard(_mock, CardTarot.CLUB_5);
        nextCard(_mock, CardTarot.HEART_2);
        nextCard(_mock, CardTarot.CLUB_8);
        nextCard(_mock, CardTarot.HEART_6);
        nextCard(_mock, CardTarot.TRUMP_10);
        nextCard(_mock, CardTarot.DIAMOND_7);
        nextCard(_mock, CardTarot.CLUB_7);
        nextCard(_mock, CardTarot.DIAMOND_8);
        nextCard(_mock, CardTarot.SPADE_7);
        nextCard(_mock, CardTarot.TRUMP_4);
        nextCard(_mock, CardTarot.HEART_7);
        nextCard(_mock, CardTarot.HEART_3);
        nextCard(_mock, CardTarot.DIAMOND_9);
        nextCard(_mock, CardTarot.SPADE_9);
        nextCard(_mock, CardTarot.HEART_KING);
        nextCard(_mock, CardTarot.HEART_8);
        nextCard(_mock, CardTarot.HEART_9);
        nextCard(_mock, CardTarot.HEART_10);
        nextCard(_mock, CardTarot.DIAMOND_10);
        nextCard(_mock, CardTarot.HEART_QUEEN);
        nextCard(_mock, CardTarot.CLUB_10);
        nextCard(_mock, CardTarot.SPADE_5);
        nextCard(_mock, CardTarot.HEART_JACK);
        nextCard(_mock, CardTarot.DIAMOND_JACK);
        nextCard(_mock, CardTarot.SPADE_KING);
        nextCard(_mock, CardTarot.SPADE_10);
        nextCard(_mock, CardTarot.CLUB_9);
        nextCard(_mock, CardTarot.CLUB_JACK);
        nextCard(_mock, CardTarot.DIAMOND_QUEEN);
        nextCard(_mock, CardTarot.SPADE_QUEEN);
        nextCard(_mock, CardTarot.SPADE_JACK);
        nextCard(_mock, CardTarot.DIAMOND_KNIGHT);
        nextCard(_mock, CardTarot.HEART_KNIGHT);
        nextCard(_mock, CardTarot.DIAMOND_KING);
        nextCard(_mock, CardTarot.TRUMP_1);
        nextCard(_mock, CardTarot.SPADE_KNIGHT);
        nextCard(_mock, CardTarot.CLUB_KNIGHT);
        nextCard(_mock, CardTarot.CLUB_QUEEN);
        nextCard(_mock, CardTarot.CLUB_KING);
    }
    private static void nextBid(MockGameTarot _m, BidTarot _bid) {
        _m.getBids().add(_bid);
    }

    private static void nextCall(MockGameTarot _m, CardTarot _bid) {
        _m.getCalls().add(create(_bid));
    }

    private static void nextSlam(MockGameTarot _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private static void nextDiscard(MockGameTarot _m, CardTarot _bid) {
        _m.getDiscard().add(_bid);
    }
    private static void nextDiscardIa(MockGameTarot _m, CardTarot... _bid) {
        _m.getDiscardIa().add(create(_bid));
    }
    private static void nextDiscardVarIa(MockGameTarot _m, boolean _slam, CardTarot... _bid) {
        CallDiscard c_ = new CallDiscard();
        c_.setEcartAFaire(create(_bid));
        c_.setChelem(_slam);
        _m.getDiscardVarIa().add(c_);
    }
    private static void nextDiscardVarIaCall(MockGameTarot _m, boolean _slam, CardTarot _card, CardTarot... _bid) {
        CallDiscard c_ = new CallDiscard();
        c_.setCarteAppelee(create(_card));
        c_.setEcartAFaire(create(_bid));
        c_.setChelem(_slam);
        _m.getDiscardVarIa().add(c_);
    }
    private static void nextCard(MockGameTarot _m, CardTarot _bid) {
        _m.getCards().add(_bid);
    }

    private static void nextNoHandful(MockGameTarot _m) {
        _m.getHandfuls().add(new IdList<Handfuls>());
        _m.getHandfulsCard().add(new HandTarot());
    }

    private static void nextHandful(MockGameTarot _m, Handfuls _h, CardTarot... _ct) {
        _m.getHandfuls().add(new IdList<Handfuls>(_h));
        _m.getHandfulsCard().add(create(_ct));
    }
    private static HandTarot create(CardTarot... _cb) {
        return HandTarot.create(_cb);
    }
    private static void nextMisere(MockGameTarot _m, Miseres... _mis) {
        _m.getMiseres().add(new IdList<Miseres>(_mis));
    }
    private static DealTarot deal7(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.TRUMP_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }
}
