package cards.president;

import cards.consts.GameType;
import cards.president.enumerations.CardPresident;
import code.util.*;
import org.junit.Test;

import static cards.president.EquallablePresidentUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ResultsPresidentTest {
    @Test
    public void hasToCalculateScores1Test() {
        assertTrue(ResultsPresident.hasToCalculateScores(GameType.EDIT,0,0));
    }
    @Test
    public void hasToCalculateScores2Test() {
        assertTrue(!ResultsPresident.hasToCalculateScores(GameType.EDIT,1,0));
    }
    @Test
    public void hasToCalculateScores3Test() {
        assertTrue(ResultsPresident.hasToCalculateScores(GameType.RANDOM,0,0));
    }
    @Test
    public void hasToCalculateScores4Test() {
        assertTrue(!ResultsPresident.hasToCalculateScores(GameType.RANDOM,1,0));
    }
    @Test
    public void calculateScoresTest() {
        ResultsPresident res_ = new ResultsPresident();
        res_.setUser((byte) 0);
        res_.setScores(new CustList<Longs>());
        res_.calculateScores(new Shorts(),GameType.RANDOM,1,0);
        assertEq(0,res_.getScores().size());
    }

    @Test
    public void initialize1Test() {
        GamePresident g_ = getSimpleDeal();
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(g_);
        res_.setUser((byte) 0);
        res_.initialize(new StringList("1","2","3","4"),new CustList<Longs>());
        assertEq(4, res_.getScores().get(0).size());
    }

    @Test
    public void initialize2Test() {
        GamePresident g_ = getSimpleDeal();
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(g_);
        res_.setUser((byte) 0);
        res_.initialize(new StringList("1","2","3","4"),new CustList<Longs>(), g_.getNewRanks());
        assertEq(4, res_.getScores().get(0).size());
    }

    @Test
    public void initPartie1Test() {
        GamePresident g_ = getSimpleDeal();
        HandPresident cards_ = g_.getCards();
        CustList<TrickPresident> plisFaits_=g_.unionPlis();
        g_.restituerMainsDepartRejouerDonne(plisFaits_,g_.getNombreDeJoueurs());
        g_.initPartie();
        assertTrue(cards_.validStack(1));
    }

    @Test
    public void initPartie2Test() {
        GamePresident g_ = getSimpleDeal2();
        HandPresident cards_ = g_.getCards();
        CustList<TrickPresident> plisFaits_=g_.unionPlis();
        g_.restituerMainsDepartRejouerDonne(plisFaits_,g_.getNombreDeJoueurs());
        g_.initPartie();
        assertTrue(cards_.validStack(1));
    }

    GamePresident getSimpleDeal2() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        rk_.add((byte) 1);
        rk_.add((byte) 4);
        rk_.add((byte) 3);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        played_.ajouter(CardPresident.CLUB_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_4);
        played_.ajouter(CardPresident.HEART_4);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_7);
        played_.ajouter(CardPresident.CLUB_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_8);
        played_.ajouter(CardPresident.CLUB_8);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        played_.ajouter(CardPresident.DIAMOND_JACK);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        played_.ajouter(CardPresident.DIAMOND_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_5);
        played_.ajouter(CardPresident.SPADE_5);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_6);
        played_.ajouter(CardPresident.SPADE_6);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_QUEEN);
        played_.ajouter(CardPresident.SPADE_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_5);
        played_.ajouter(CardPresident.DIAMOND_5);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_6);
        played_.ajouter(CardPresident.DIAMOND_6);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_QUEEN);
        played_.ajouter(CardPresident.DIAMOND_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_1);
        played_.ajouter(CardPresident.CLUB_1);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_2);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_7);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_10);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_KING);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        return g_;
    }

    GamePresident getSimpleDeal() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_4);
        played_.ajouter(CardPresident.DIAMOND_4);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_4);
        played_.ajouter(CardPresident.HEART_4);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_8);
        played_.ajouter(CardPresident.HEART_8);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_8);
        played_.ajouter(CardPresident.CLUB_8);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_QUEEN);
        played_.ajouter(CardPresident.DIAMOND_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_QUEEN);
        played_.ajouter(CardPresident.CLUB_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_1);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_1);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_JACK);
        played_.ajouter(CardPresident.SPADE_JACK);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_JACK);
        played_.ajouter(CardPresident.HEART_JACK);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_5);
        played_.ajouter(CardPresident.HEART_5);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_5);
        played_.ajouter(CardPresident.DIAMOND_5);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_6);
        played_.ajouter(CardPresident.DIAMOND_6);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_6);
        played_.ajouter(CardPresident.CLUB_6);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_2);
        played_.ajouter(CardPresident.CLUB_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        return g_;
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
