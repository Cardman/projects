package cards.gui;

import cards.consts.*;
import cards.gui.containers.*;
import cards.president.*;
import cards.president.enumerations.*;
import code.mock.MockGamePresident;
import code.util.*;

public final class PresidentSampleFirstDeal implements IntFirstDealPresident {

    @Override
    public GamePresident deal(ContainerPresident _container, RulesPresident _rules, long _nb) {
        return new GamePresident(GameType.RANDOM,new DealPresident(deal2(), 0),_rules,new Ints());
    }

    @Override
    public GamePresident deal(ContainerPresident _container) {
        RulesPresident regles_ = _container.getWindow().getReglesPresident();
        return new GamePresident(GameType.EDIT,new DealPresident(deal2(), 0),regles_,new Ints());
    }

    public static void mockGameAfter(MockGamePresident _mock) {
        mockGame(_mock);
        _mock.getStacks().add(new DealPresident(deal2(), 0));
        _mock.getStacks().add(new DealPresident(deal2(), 0));
        _mock.getSw().add(create(CardPresident.SPADE_2,CardPresident.CLUB_2));
        _mock.getSw().add(create(CardPresident.DIAMOND_2));
        mockGame(_mock);
    }
    public static void mockGame(MockGamePresident _mock) {
        nextCard(_mock, create(CardPresident.SPADE_3));
        nextCard(_mock, create(CardPresident.HEART_3));
        nextCard(_mock, create(CardPresident.DIAMOND_3));
        nextCard(_mock, create(CardPresident.CLUB_3));
        nextCard(_mock, create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        nextCard(_mock, create(CardPresident.SPADE_4,CardPresident.HEART_4));
        nextCard(_mock, create(CardPresident.CLUB_7));
        nextCard(_mock, create(CardPresident.HEART_7));
        nextCard(_mock, create(CardPresident.DIAMOND_7));
        nextCard(_mock, create(CardPresident.SPADE_7));
        nextCard(_mock, create(CardPresident.HEART_8,CardPresident.DIAMOND_8));
        nextCard(_mock, create(CardPresident.SPADE_8,CardPresident.CLUB_8));
        nextCard(_mock, create(CardPresident.SPADE_9));
        nextCard(_mock, create(CardPresident.HEART_9));
        nextCard(_mock, create(CardPresident.DIAMOND_9));
        nextCard(_mock, create(CardPresident.CLUB_9));
        nextCard(_mock, create(CardPresident.SPADE_10));
        nextCard(_mock, create(CardPresident.CLUB_10));
        nextCard(_mock, create(CardPresident.HEART_10));
        nextCard(_mock, create(CardPresident.DIAMOND_10));
        nextCard(_mock, create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        nextCard(_mock, create(CardPresident.HEART_KING));
        nextCard(_mock, create(CardPresident.DIAMOND_KING));
        nextCard(_mock, create(CardPresident.SPADE_KING));
        nextCard(_mock, create(CardPresident.CLUB_KING));
        nextCard(_mock, create(CardPresident.HEART_1));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_1));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_2));
        nextCard(_mock, create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK));
        nextCard(_mock, create(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        nextCard(_mock, create(CardPresident.DIAMOND_2));
        nextCard(_mock, create(CardPresident.SPADE_5,CardPresident.HEART_5));
        nextCard(_mock, create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        nextCard(_mock, create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        nextCard(_mock, create(CardPresident.SPADE_6,CardPresident.CLUB_6));
        nextCard(_mock, create(CardPresident.SPADE_2,CardPresident.CLUB_2));
    }

    private static void nextCard(MockGamePresident _m, HandPresident _bid) {
        _m.getCards().add(_bid);
    }
    private static HandPresident create(CardPresident... _cb) {
        HandPresident c_ = new HandPresident();
        c_.setCards(new IdList<CardPresident>(_cb));
        return c_;
    }

    static CustList<HandPresident> deal2() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }
}
