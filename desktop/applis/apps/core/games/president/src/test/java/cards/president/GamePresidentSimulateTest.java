package cards.president;

import cards.consts.GameType;
import cards.president.enumerations.CardPresident;
import code.maths.montecarlo.DefaultGenerator;
import code.util.CustList;
import code.util.Ints;
import org.junit.Test;

public final class GamePresidentSimulateTest extends EquallablePresidentUtil {
    @Test
    public void simuler0Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = new Ints();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        d_.setDealer(3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        AbstractSimulatingPresident s_ = new SimulatingPresidentAbrupt();
        simulateLoc(g_, s_);
        assertTrue(!g_.isEnded());
    }
    @Test
    public void simuler1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = new Ints();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        d_.setDealer(3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        AbstractSimulatingPresident s_ = new SimulatingPresidentAbruptFirst();
        simulateLoc(g_, s_);
        assertTrue(!g_.isEnded());
    }
    @Test
    public void simuler2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = new Ints();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        d_.setDealer(3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        AbstractSimulatingPresident s_ = new SimulatingPresidentNormal();
        simulateLoc(g_, s_);
        assertTrue(g_.isEnded());
        assertEq(2, s_.getHistory().size());
    }

    private static void simulateLoc(GamePresident _g, AbstractSimulatingPresident _s) {
        _g.simulate(2,_s, DefaultGenerator.oneElt());
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
