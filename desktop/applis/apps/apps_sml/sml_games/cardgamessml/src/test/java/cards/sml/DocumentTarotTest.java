package cards.sml;

import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.consts.Suit;
import code.maths.Rate;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class DocumentTarotTest extends EquallableCardsSerialUtil {
    @Test
    public void t1() {
        CustList<GameTarot> ls_ = new CustList<GameTarot>();
        GameTarot g_ = new GameTarot();
        DealTarot d_ = new DealTarot();
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.HEART_1);
        h_.ajouter(CardTarot.HEART_7);
        d_.getDeal().add(h_);
        g_.setDeal(d_);
        TrickTarot pt_ = new TrickTarot();
        pt_.getCards().ajouter(CardTarot.HEART_8);
        pt_.getCards().ajouter(CardTarot.HEART_9);
        g_.setProgressingTrick(pt_);
        CustList<TrickTarot> done_ = new CustList<TrickTarot>();
        done_.add(new TrickTarot());
        g_.setTricks(done_);
        CustList<HandTarot> sw_ = new CustList<HandTarot>();
        sw_.add(new HandTarot());
        g_.getBids().add(BidTarot.SLAM);
        IdList<Handfuls> hf_ = new IdList<Handfuls>();
        hf_.add(Handfuls.FOUR);
        g_.getDeclaresHandfuls().add(hf_);
        IdList<Miseres> m_ = new IdList<Miseres>();
        m_.add(Miseres.SUIT);
        g_.getDeclaresMiseres().add(m_);
        g_.getHandfuls().add(new HandTarot());
        ls_.add(g_);
        CustList<GameTarot> o_ = saveResultTarot(ls_);
        assertEq(1,o_.size());
        assertEq(1,o_.get(0).getDeal().getDeal().size());
        assertEq(2,o_.get(0).getDeal().getDeal().get(0).getCards().size());
        assertEq(CardTarot.HEART_1,o_.get(0).getDeal().getDeal().get(0).getCards().get(0));
        assertEq(CardTarot.HEART_7,o_.get(0).getDeal().getDeal().get(0).getCards().get(1));
        assertEq(2,o_.get(0).getProgressingTrick().getCards().getCards().size());
        assertEq(CardTarot.HEART_8,o_.get(0).getProgressingTrick().getCards().getCards().get(0));
        assertEq(CardTarot.HEART_9,o_.get(0).getProgressingTrick().getCards().getCards().get(1));
    }
    @Test
    public void t2() {
        TricksHandsTarot t_ = new TricksHandsTarot();
        t_.setTricks(new CustList<TrickTarot>());
        t_.setDistribution(new DealTarot());
        t_.setCardsHandsAtInitialState(new CustList<HandTarot>());
        TricksHandsTarot o_ = saveTricksHandsTarot(t_);
        assertEq(0,o_.getTricks().size());
    }
    @Test
    public void t3() {
        RulesTarot d_ = new RulesTarot();
        IdMap<BidTarot, BoolVal> id_ = new IdMap<BidTarot, BoolVal>();
        id_.addEntry(BidTarot.FOLD,BoolVal.FALSE);
        d_.setAllowedBids(id_);
        IdMap<Handfuls, Integer> dec_ = new IdMap<Handfuls, Integer>();
        dec_.addEntry(Handfuls.FOUR,1);
        d_.setAllowedHandfuls(dec_);
        RulesTarot o_ = saveRulesTarot(d_);
        assertEq(1,o_.getAllowedBids().size());
        assertEq(BidTarot.FOLD,o_.getAllowedBids().getKey(0));
        assertEq(BoolVal.FALSE,o_.getAllowedBids().getValue(0));
        assertEq(1,o_.getAllowedHandfuls().size());
        assertEq(Handfuls.FOUR,o_.getAllowedHandfuls().getKey(0));
        assertEq(1,o_.getAllowedHandfuls().getValue(0));
    }
    @Test
    public void t4() {
        DisplayingTarot d_ = new DisplayingTarot();
        d_.getDisplaying().setDecreasing(true);
        d_.getDisplaying().setClockwise(true);
        IdList<Suit> s_ = new IdList<Suit>();
        s_.add(Suit.HEART);
        d_.getDisplaying().setSuits(s_);
        DisplayingTarot o_ = saveDisplayingTarot(d_);
        assertTrue(o_.getDisplaying().isDecreasing());
        assertTrue(o_.getDisplaying().isClockwise());
        assertEq(1,o_.getDisplaying().getSuits().size());
        assertEq(Suit.HEART,o_.getDisplaying().getSuits().get(0));
    }
    @Test
    public void t5() {
        DisplayingTarot d_ = new DisplayingTarot();
        d_.getDisplaying().setDecreasing(false);
        d_.getDisplaying().setClockwise(false);
        IdList<Suit> s_ = new IdList<Suit>();
        s_.add(Suit.SPADE);
        d_.getDisplaying().setSuits(s_);
        DisplayingTarot o_ = saveDisplayingTarot(d_);
        assertFalse(o_.getDisplaying().isDecreasing());
        assertFalse(o_.getDisplaying().isClockwise());
        assertEq(1,o_.getDisplaying().getSuits().size());
        assertEq(Suit.SPADE,o_.getDisplaying().getSuits().get(0));
    }
    @Test
    public void t6() {
        ResultsTarot ls_ = new ResultsTarot();
        GameTarot g_ = new GameTarot();
        DealTarot d_ = new DealTarot();
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.HEART_1);
        h_.ajouter(CardTarot.HEART_7);
        d_.getDeal().add(h_);
        g_.setDeal(d_);
        TrickTarot pt_ = new TrickTarot();
        pt_.getCards().ajouter(CardTarot.HEART_8);
        pt_.getCards().ajouter(CardTarot.HEART_9);
        g_.setProgressingTrick(pt_);
        CustList<TrickTarot> done_ = new CustList<TrickTarot>();
        done_.add(new TrickTarot());
        g_.setTricks(done_);
        ls_.setGame(g_);
        CustList<Longs> lgs_ = new CustList<Longs>();
        lgs_.add(Longs.newList(7));
        ls_.getRes().setScores(lgs_);
        ls_.getRes().setNicknames(new StringList());
        ls_.getRes().setSigmas(new CustList<Rate>());
        ResultsTarot o_ = saveResultTarot(ls_);
        assertEq(1,o_.getGame().getDeal().getDeal().size());
        assertEq(2,o_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardTarot.HEART_1,o_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(CardTarot.HEART_7,o_.getGame().getDeal().getDeal().get(0).getCards().get(1));
        assertEq(1,o_.getRes().getScores().size());
        assertEq(1,o_.getRes().getScores().get(0).size());
        assertEq(7,o_.getRes().getScores().get(0).get(0));
    }
    @Test
    public void t7() {
        GameTarot g_ = new GameTarot();
        g_.getRules().setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        g_.setDeal(new DealTarot());
        GameTarot o_ = saveGameTarot(g_);
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_KING,o_.getRules().getDealing());
    }
    @Test
    public void t8() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.HEART_1);
        h_.ajouter(CardTarot.HEART_7);
        HandTarot o_ = saveHandTarot(h_);
        assertEq(2,o_.getCards().size());
        assertEq(CardTarot.HEART_1,o_.getCards().get(0));
        assertEq(CardTarot.HEART_7,o_.getCards().get(1));
    }
    @Test
    public void t9() {
        RulesTarot d_ = new RulesTarot();
        d_.setDiscardAfterCall(true);
        RulesTarot o_ = saveRulesTarot(d_);
        assertTrue(o_.getDiscardAfterCall());
    }
    @Test
    public void t10() {
        RulesTarot d_ = new RulesTarot();
        d_.setDiscardAfterCall(false);
        RulesTarot o_ = saveRulesTarot(d_);
        assertFalse(o_.getDiscardAfterCall());
    }
}
