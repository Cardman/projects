package cards.president;

import code.maths.montecarlo.DefaultGenerator;
import code.util.core.BoolVal;
import org.junit.Test;

import cards.consts.GameType;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.util.*;


public class CheckerGamePresidentWithRulesTest extends EquallablePresidentUtil {

    @Test
    public void check1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0, g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check3Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(1,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check4Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.HAS_TO_EQUAL, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check5Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.HAS_TO_EQUAL, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check6Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(1,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check6_Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.play(cards(CardPresident.CLUB_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(1,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }
    @Test
    public void check7Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(1,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getProgressingTrick().getEntameur());
        assertEq(2,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check8Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(2,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check9Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(6,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(2,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check10Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(6,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(2,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check11Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(7,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check12Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.HAS_TO_EQUAL, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(8,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check13Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(12,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(9).getEntameur());
        assertEq(2,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(10).getEntameur());
        assertEq(1,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(0,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check14Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(12,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(9).getEntameur());
        assertEq(2,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(10).getEntameur());
        assertEq(1,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(0,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(2,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check15Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(9,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check16Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.PASS, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(8,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check17Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(17,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(9).getEntameur());
        assertEq(2,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(10).getEntameur());
        assertEq(1,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(0,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(12).getEntameur());
        assertEq(2,g_.getTricks().get(12).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(13).getEntameur());
        assertEq(2,g_.getTricks().get(13).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(14).getEntameur());
        assertEq(2,g_.getTricks().get(14).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(15).getEntameur());
        assertEq(0,g_.getTricks().get(15).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(16).getEntameur());
        assertEq(2,g_.getTricks().get(16).getNombreDeCartesParJoueur());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check18Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        rk_ = g_.getNewRanks();
        d_ = new DefGamePresident().empiler(1, g_, DefaultGenerator.oneElt());
        g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check19Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        rk_ = g_.getNewRanks();
        d_ = new DefGamePresident().empiler(1, g_, DefaultGenerator.oneElt());
        g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(!g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check20Test() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(3, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
        assertEq(3, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check21Test() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(!g_.readyToPlay());
//        assertEq(3, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
        assertEq(3, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check22Test() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(3, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
        assertEq(3, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check23Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 3);
        rk_.add((byte) 4);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(!g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check24Test() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        HandPresident h_;
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(3, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
        assertEq(3, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(2,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check25Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 4);
        rk_.add((byte) 3);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal5();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(!g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check26Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 4);
        rk_.add((byte) 3);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal5();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(1, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check27Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 3);
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 4);
        CustList<HandPresident> hs_ = deal5();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check28Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(17,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(9).getEntameur());
        assertEq(2,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(10).getEntameur());
        assertEq(1,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(0,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(12).getEntameur());
        assertEq(2,g_.getTricks().get(12).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(13).getEntameur());
        assertEq(2,g_.getTricks().get(13).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(14).getEntameur());
        assertEq(2,g_.getTricks().get(14).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(15).getEntameur());
        assertEq(0,g_.getTricks().get(15).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(16).getEntameur());
        assertEq(2,g_.getTricks().get(16).getNombreDeCartesParJoueur());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check29Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(false);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(17,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(9).getEntameur());
        assertEq(2,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(10).getEntameur());
        assertEq(1,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(0,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(12).getEntameur());
        assertEq(2,g_.getTricks().get(12).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(13).getEntameur());
        assertEq(2,g_.getTricks().get(13).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(14).getEntameur());
        assertEq(2,g_.getTricks().get(14).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(15).getEntameur());
        assertEq(0,g_.getTricks().get(15).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(16).getEntameur());
        assertEq(2,g_.getTricks().get(16).getNombreDeCartesParJoueur());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check30Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(17,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(2,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(1,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(6).getEntameur());
        assertEq(2,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(7).getEntameur());
        assertEq(1,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(9).getEntameur());
        assertEq(1,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(10).getEntameur());
        assertEq(2,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(0,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(12).getEntameur());
        assertEq(2,g_.getTricks().get(12).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(13).getEntameur());
        assertEq(2,g_.getTricks().get(13).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(14).getEntameur());
        assertEq(2,g_.getTricks().get(14).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(15).getEntameur());
        assertEq(0,g_.getTricks().get(15).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(16).getEntameur());
        assertEq(2,g_.getTricks().get(16).getNombreDeCartesParJoueur());
        assertEq(3,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check31Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setLoosingIfFinishByBestCards(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9,CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10,CardPresident.SPADE_10));

        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_1,CardPresident.CLUB_1));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));

        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(20,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(1,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(4,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(6).getEntameur());
        assertEq(4,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(7).getEntameur());
        assertEq(2,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(8).getEntameur());
        assertEq(2,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(9).getEntameur());
        assertEq(1,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(10).getEntameur());
        assertEq(0,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(2,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(12).getEntameur());
        assertEq(1,g_.getTricks().get(12).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(13).getEntameur());
        assertEq(0,g_.getTricks().get(13).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(14).getEntameur());
        assertEq(2,g_.getTricks().get(14).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(15).getEntameur());
        assertEq(2,g_.getTricks().get(15).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(16).getEntameur());
        assertEq(2,g_.getTricks().get(16).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(17).getEntameur());
        assertEq(1,g_.getTricks().get(17).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(18).getEntameur());
        assertEq(0,g_.getTricks().get(18).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(19).getEntameur());
        assertEq(8,g_.getTricks().get(19).getNombreDeCartesParJoueur());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check32Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setLoosingIfFinishByBestCards(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9,CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8,CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10,CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_1,CardPresident.CLUB_1));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));

        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.TRUE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(g_.isReversed());
        assertEq(19,g_.getTricks().size());
        assertEq(1,g_.getTricks().get(0).getEntameur());
        assertEq(1,g_.getTricks().get(0).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(1).getEntameur());
        assertEq(1,g_.getTricks().get(1).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(2).getEntameur());
        assertEq(1,g_.getTricks().get(2).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(3).getEntameur());
        assertEq(2,g_.getTricks().get(3).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(4).getEntameur());
        assertEq(4,g_.getTricks().get(4).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(5).getEntameur());
        assertEq(1,g_.getTricks().get(5).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(6).getEntameur());
        assertEq(4,g_.getTricks().get(6).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(7).getEntameur());
        assertEq(4,g_.getTricks().get(7).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(8).getEntameur());
        assertEq(1,g_.getTricks().get(8).getNombreDeCartesParJoueur());
        assertEq(0,g_.getTricks().get(9).getEntameur());
        assertEq(0,g_.getTricks().get(9).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(10).getEntameur());
        assertEq(2,g_.getTricks().get(10).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(11).getEntameur());
        assertEq(1,g_.getTricks().get(11).getNombreDeCartesParJoueur());
        assertEq(1,g_.getTricks().get(12).getEntameur());
        assertEq(0,g_.getTricks().get(12).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(13).getEntameur());
        assertEq(2,g_.getTricks().get(13).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(14).getEntameur());
        assertEq(2,g_.getTricks().get(14).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(15).getEntameur());
        assertEq(2,g_.getTricks().get(15).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(16).getEntameur());
        assertEq(1,g_.getTricks().get(16).getNombreDeCartesParJoueur());
        assertEq(3,g_.getTricks().get(17).getEntameur());
        assertEq(0,g_.getTricks().get(17).getNombreDeCartesParJoueur());
        assertEq(2,g_.getTricks().get(18).getEntameur());
        assertEq(8,g_.getTricks().get(18).getNombreDeCartesParJoueur());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check33Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setSwitchCards(false);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        rk_ = g_.getNewRanks();
        d_ = new DefGamePresident().empiler(1, g_, DefaultGenerator.oneElt());
        g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check34Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setSwitchCards(false);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        rk_ = g_.getNewRanks();
        d_ = new DefGamePresident().empiler(1, g_, DefaultGenerator.oneElt());
        g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check35Test() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(false);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        HandPresident h_;
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(3, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
        assertEq(3, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(2,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check36Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check37Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.SKIPPED, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check37_Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrick(cards(CardPresident.HEART_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.SKIPPED, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertEq(3, g_.getProgressingTrick().total());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check38Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.NO_SKIP);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(3, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check39Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLooserStartsFirst(false);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        rk_ = g_.getNewRanks();
        d_ = new DefGamePresident().empiler(1, g_, DefaultGenerator.oneElt());
        g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
        assertTrue(g_.readyToPlay());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(2,g_.getProgressingTrick().getEntameur());
        assertEq(0,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check40Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.HAS_TO_EQUAL, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal((byte)3));
        assertEq(0, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }

    @Test
    public void check41Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(g_.getError().isEmpty());
//        assertEq(4, g_.getPassOrFinish().size());
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(0));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(1));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(2));
//        assertEq(BoolVal.FALSE,g_.getPassOrFinish().get(3));
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal((byte)0));
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal((byte)1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal((byte)2));
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal((byte)3));
        assertEq(2, g_.nextPlayer());
        assertTrue(!g_.isReversed());
        assertEq(0,g_.getTricks().size());
        assertEq(1,g_.getProgressingTrick().getEntameur());
        assertEq(1,g_.getProgressingTrick().getNombreDeCartesParJoueur());
    }
    @Test
    public void check1FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }


    @Test
    public void check2FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check3FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check4FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7,CardPresident.HEART_9));
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check5FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setHasToPlay(true);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        //
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check6FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(false);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        HandPresident h_;
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.DIAMOND_3);
        g_.getSwitchedCards().add(invalid_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check7FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        HandPresident h_;
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.DIAMOND_3);
        g_.getSwitchedCards().set((byte) 0, invalid_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check8FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        HandPresident invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.CLUB_1);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 1).ajouterCartes(invalid_);
        invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.SPADE_3);
        g_.getSwitchedCards().set((byte) 1, invalid_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check9FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.SPADE_1);
        invalid_.ajouter(CardPresident.CLUB_1);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 1).ajouterCartes(invalid_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check10FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        HandPresident invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.CLUB_2);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 1).ajouterCartes(invalid_);
        invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.DIAMOND_3);
        g_.getSwitchedCards().set((byte) 1, invalid_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check11FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.SPADE_1);
        invalid_.ajouter(CardPresident.CLUB_1);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 1).ajouterCartes(invalid_);
        invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.SPADE_3);
        invalid_.ajouter(CardPresident.SPADE_4);
        g_.getSwitchedCards().set((byte) 1, invalid_);
        g_.getDeal().hand((byte) 2).ajouterCartes(invalid_);
        g_.getDeal().hand((byte) 1).supprimerCartes(g_.getSwitchedCards().get((byte)1));
        g_.getDeal().hand((byte) 2).supprimerCartes(g_.getSwitchedCards().get((byte)2));
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check12FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 2);
        rk_.add((byte) 1);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.SPADE_1);
        invalid_.ajouter(CardPresident.CLUB_1);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 1).ajouterCartes(invalid_);
        invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.SPADE_3);
        g_.getSwitchedCards().set((byte) 1, invalid_);
        g_.getDeal().hand((byte) 2).ajouterCartes(invalid_);
        g_.getDeal().hand((byte) 1).supprimerCartes(g_.getSwitchedCards().get((byte)1));
        g_.getDeal().hand((byte) 2).supprimerCartes(g_.getSwitchedCards().get((byte)2));
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check13FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = badDeal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check14FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.SPADE_1);
        invalid_.ajouter(CardPresident.CLUB_1);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 0).ajouterCartes(invalid_);
        invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.HEART_6);
        g_.getSwitchedCards().set((byte) 0, invalid_);
        g_.getDeal().hand((byte) 2).ajouterCartes(invalid_);
        g_.getDeal().hand((byte) 0).supprimerCartes(g_.getSwitchedCards().get((byte)0));
        g_.getDeal().hand((byte) 2).supprimerCartes(g_.getSwitchedCards().get((byte)2));
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check15FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.CLUB_2);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 0).ajouterCartes(invalid_);
        invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.DIAMOND_3);
        invalid_.ajouter(CardPresident.CLUB_3);
        g_.getSwitchedCards().set((byte) 0, invalid_);
        g_.getDeal().hand((byte) 2).ajouterCartes(invalid_);
        g_.getDeal().hand((byte) 0).supprimerCartes(g_.getSwitchedCards().get((byte)0));
        g_.getDeal().hand((byte) 2).supprimerCartes(g_.getSwitchedCards().get((byte)2));
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check16FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check17FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check18FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check19FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.getDeal().getDeal().add(new HandPresident());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check20FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        g_.getProgressingTrick().ajouter(played_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check21FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 3);
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        //
        g_.getTricks().add(new TrickPresident((byte) 2));
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check22FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        r_.setNbStacks(2);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check23FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setSwitchCards(true);
        r_.setLooserStartsFirst(true);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 2);
        rk_.add((byte) 3);
        rk_.add((byte) 4);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        //
        transientFields(g_);
        HandPresident invalid_ = new HandPresident();
        invalid_.ajouter(CardPresident.CLUB_2);
        g_.getSwitchedCards().set((byte) 2, invalid_);
        g_.getDeal().hand((byte) 0).ajouterCartes(invalid_);
        invalid_=new HandPresident();
        invalid_.ajouter(CardPresident.CLUB_3);
        g_.getSwitchedCards().set((byte) 1, invalid_);
        g_.getDeal().hand((byte) 2).ajouterCartes(invalid_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check24FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = badDeal2();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check25FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = badDeal3();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check26FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        rk_.add((byte) 1);
        rk_.add((byte) 4);
        rk_.add((byte) 3);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal5();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        HandPresident del_ = new HandPresident();
        del_.ajouter(CardPresident.DIAMOND_8);
        del_.ajouter(CardPresident.HEART_8);
        g_.getDeal().hand().supprimerCartes(del_);
        HandPresident add_ = new HandPresident();
        add_.ajouter(CardPresident.SPADE_7);
        add_.ajouter(CardPresident.CLUB_7);
        g_.getDeal().hand().ajouterCartes(add_);
        g_.getSwitchedCards().set((byte) 1, add_);
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check27FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        //
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_5);
        played_.ajouter(CardPresident.SPADE_5);
        g_.getTricks().last().ajouter(played_);
        g_.getDeal().hand((byte)2).supprimerCartes(played_);
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check28FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.getDeal().hand().ajouter(CardPresident.WHITE);
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check29FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        //
        transientFields(g_);
        g_.getTricks().last().ajouter();
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check30FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        //
        transientFields(g_);
        g_.getProgressingTrick().getCards().add(0,new HandPresident());
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check31FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        //
        transientFields(g_);
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        g_.getTricks().last().ajouter(played_);
        g_.getDeal().hand((byte)2).supprimerCartes(played_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check32FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        rk_ = g_.getNewRanks();
        d_ = new DefGamePresident().empiler(1, g_, DefaultGenerator.oneElt());
        g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        g_.getSwitchedCards().clear();
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check33FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        rk_ = g_.getNewRanks();
        d_ = new DefGamePresident().empiler(1, g_, DefaultGenerator.oneElt());
        g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        transientFields(g_);
        g_.getScores().clear();
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check34FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        r_.setNbStacks(-1);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check35FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        r_.setNbPlayers(-1);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check36FailTest() {
        RulesPresident r_ = new RulesPresident(3);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        transientFields(g_);
        r_.setNbPlayers(513);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertTrue(!g_.getError().isEmpty());
    }

    @Test
    public void check37FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.getTricks().last().ajouter(cards());
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertFalse(g_.getError().isEmpty());
    }

    @Test
    public void check38FailTest() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.getTricks().removeQuicklyLast();
        //
        transientFields(g_);
        //
        CheckerGamePresidentWithRules.check(g_);
        assertFalse(g_.getError().isEmpty());
    }
    static void transientFields(GamePresident _g) {
        CheckerGamePresidentWithRules.cancelStarter(_g.getTricks());
        _g.getProgressingTrick().setEntameur(-1);
//        _g.getPassOrFinish().clear();
        _g.getLastStatus().clear();
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

    static CustList<HandPresident> deal3() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
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

    static CustList<HandPresident> deal4() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
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
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.HEART_QUEEN);
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
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        return hs_;
    }

    static CustList<HandPresident> deal5() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
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
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_9);
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

    static CustList<HandPresident> badDeal1() {
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
        h_.ajouter(CardPresident.CLUB_1);
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

    static CustList<HandPresident> badDeal2() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
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
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        return hs_;
    }

    static CustList<HandPresident> badDeal3() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
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
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.CLUB_KING);
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
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        return hs_;
    }

    public static HandPresident cards(CardPresident... _cards) {
        return HandPresident.create(_cards);
    }
}
