package cards.president;

import cards.consts.GameType;
import cards.president.enumerations.CardPresident;
import code.util.Bytes;
import code.util.CustList;
import org.junit.Test;

import static org.junit.Assert.*;

public final class GamePresidentSimulateTest {
    @Test
    public void simuler1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        d_.setDealer((byte) 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        SimulatingPresidentAbrupt s_ = new SimulatingPresidentAbrupt(g_);
        g_.simulate(2,s_);
        assertTrue(!g_.isEnded());
    }
    @Test
    public void simuler2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        d_.setDealer((byte) 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        SimulatingPresidentNormal s_ = new SimulatingPresidentNormal();
        g_.simulate(2,s_);
        assertTrue(g_.isEnded());
    }

    static CustList<HandPresident> deal1() {
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
