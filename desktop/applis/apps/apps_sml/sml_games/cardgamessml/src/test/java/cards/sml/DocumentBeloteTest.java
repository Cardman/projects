package cards.sml;

import cards.consts.*;
import cards.belote.*;
import cards.belote.enumerations.*;
import code.maths.*;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class DocumentBeloteTest extends EquallableCardsSerialUtil {
    @Test
    public void t1() {
        CustList<GameBelote> ls_ = new CustList<GameBelote>();
        GameBelote g_ = new GameBelote();
        DealBelote d_ = new DealBelote();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        d_.getDeal().add(h_);
        g_.setDeal(d_);
        TrickBelote pt_ = new TrickBelote();
        pt_.getCards().ajouter(CardBelote.HEART_8);
        pt_.getCards().ajouter(CardBelote.HEART_9);
        g_.setProgressingTrick(pt_);
        CustList<TrickBelote> done_ = new CustList<TrickBelote>();
        done_.add(new TrickBelote());
        g_.setTricks(done_);
        CustList<HandBelote> sw_ = new CustList<HandBelote>();
        sw_.add(new HandBelote());
        g_.getDeclares().add(new DeclareHandBelote());
        g_.getDeclaresBeloteRebelote().add(new HandBelote());
        g_.getBids().add(new BidBeloteSuit());
        g_.setType(GameType.RANDOM);
        ls_.add(g_);
        CustList<GameBelote> o_ = saveResultBelote(ls_);
        assertEq(1,o_.size());
        assertEq(1,o_.get(0).getDeal().getDeal().size());
        assertEq(2,o_.get(0).getDeal().getDeal().get(0).getCards().size());
        assertEq(CardBelote.HEART_1,o_.get(0).getDeal().getDeal().get(0).getCards().get(0));
        assertEq(CardBelote.HEART_7,o_.get(0).getDeal().getDeal().get(0).getCards().get(1));
        assertEq(2,o_.get(0).getProgressingTrick().getCards().getCards().size());
        assertEq(CardBelote.HEART_8,o_.get(0).getProgressingTrick().getCards().getCards().get(0));
        assertEq(CardBelote.HEART_9,o_.get(0).getProgressingTrick().getCards().getCards().get(1));
    }
    @Test
    public void t2() {
        TricksHandsBelote t_ = new TricksHandsBelote();
        t_.setTricks(new CustList<TrickBelote>());
        t_.setDistribution(new DealBelote());
        t_.setCardsHandsAtInitialState(new CustList<HandBelote>());
//        t_.setRules(new RulesBelote());
//        t_.getRules().setAllowedBids(new IdMap<BidBelote, BoolVal>());
//        t_.getRules().setAllowedDeclares(new IdMap<DeclaresBelote, BoolVal>());
        t_.setBid(new BidBeloteSuit());
        TricksHandsBelote o_ = saveTricksHandsBelote(t_);
        assertEq(0,o_.getTricks().size());
    }
    @Test
    public void t3() {
        RulesBelote d_ = new RulesBelote();
        IdMap<BidBelote, BoolVal> id_ = new IdMap<BidBelote, BoolVal>();
        id_.addEntry(BidBelote.FOLD,BoolVal.FALSE);
        d_.setAllowedBids(id_);
        IdMap<DeclaresBelote, BoolVal> dec_ = new IdMap<DeclaresBelote, BoolVal>();
        dec_.addEntry(DeclaresBelote.UNDEFINED,BoolVal.FALSE);
        d_.setAllowedDeclares(dec_);
        RulesBelote o_ = saveRulesBelote(d_);
        assertEq(1,o_.getAllowedBids().size());
        assertEq(BidBelote.FOLD,o_.getAllowedBids().getKey(0));
        assertEq(BoolVal.FALSE,o_.getAllowedBids().getValue(0));
        assertEq(1,o_.getAllowedDeclares().size());
        assertEq(DeclaresBelote.UNDEFINED,o_.getAllowedDeclares().getKey(0));
        assertEq(BoolVal.FALSE,o_.getAllowedDeclares().getValue(0));
    }
    @Test
    public void t4() {
        DisplayingBelote d_ = new DisplayingBelote();
        d_.getDisplaying().setDecreasing(true);
        d_.getDisplaying().setClockwise(true);
        IdList<Suit> s_ = new IdList<Suit>();
        s_.add(Suit.HEART);
        d_.getDisplaying().setSuits(s_);
        DisplayingBelote o_ = saveDisplayingBelote(d_);
        assertTrue(o_.getDisplaying().isDecreasing());
        assertTrue(o_.getDisplaying().isClockwise());
        assertEq(1,o_.getDisplaying().getSuits().size());
        assertEq(Suit.HEART,o_.getDisplaying().getSuits().get(0));
    }
    @Test
    public void t5() {
        DisplayingBelote d_ = new DisplayingBelote();
        d_.getDisplaying().setDecreasing(false);
        d_.getDisplaying().setClockwise(false);
        IdList<Suit> s_ = new IdList<Suit>();
        s_.add(Suit.SPADE);
        d_.getDisplaying().setSuits(s_);
        DisplayingBelote o_ = saveDisplayingBelote(d_);
        assertFalse(o_.getDisplaying().isDecreasing());
        assertFalse(o_.getDisplaying().isClockwise());
        assertEq(1,o_.getDisplaying().getSuits().size());
        assertEq(Suit.SPADE,o_.getDisplaying().getSuits().get(0));
    }
    @Test
    public void t6() {
        ResultsBelote ls_ = new ResultsBelote();
        GameBelote g_ = new GameBelote();
        DealBelote d_ = new DealBelote();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        d_.getDeal().add(h_);
        g_.setDeal(d_);
        TrickBelote pt_ = new TrickBelote();
        pt_.getCards().ajouter(CardBelote.HEART_8);
        pt_.getCards().ajouter(CardBelote.HEART_9);
        g_.setProgressingTrick(pt_);
        CustList<TrickBelote> done_ = new CustList<TrickBelote>();
        done_.add(new TrickBelote());
        g_.setTricks(done_);
        g_.setType(GameType.RANDOM);
        ls_.setGame(g_);
        CustList<Longs> lgs_ = new CustList<Longs>();
        lgs_.add(Longs.newList(7));
        ls_.getRes().setScores(lgs_);
        ls_.getRes().setNicknames(new StringList());
        ls_.getRes().setSigmas(new CustList<Rate>());
        ResultsBelote o_ = saveResultBelote(ls_);
        assertEq(1,o_.getGame().getDeal().getDeal().size());
        assertEq(2,o_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardBelote.HEART_1,o_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(CardBelote.HEART_7,o_.getGame().getDeal().getDeal().get(0).getCards().get(1));
        assertEq(1,o_.getRes().getScores().size());
        assertEq(1,o_.getRes().getScores().get(0).size());
        assertEq(7,o_.getRes().getScores().get(0).get(0));
    }
    @Test
    public void t7() {
        GameBelote g_ = new GameBelote();
        g_.getRules().setDealing(DealingBelote.COINCHE_2_VS_2);
        g_.setDeal(new DealBelote());
        g_.setType(GameType.RANDOM);
        GameBelote o_ = saveGameBelote(g_);
        assertEq(DealingBelote.COINCHE_2_VS_2,o_.getRules().getDealing());
    }
    @Test
    public void t8() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        HandBelote o_ = saveHandBelote(h_);
        assertEq(2,o_.getCards().size());
        assertEq(CardBelote.HEART_1,o_.getCards().get(0));
        assertEq(CardBelote.HEART_7,o_.getCards().get(1));
    }
    @Test
    public void t9() {
        RulesBelote d_ = new RulesBelote();
        d_.setComptePointsClassique(true);
        d_.setSousCoupeAdv(true);
        d_.setUnderTrumpFoe(true);
        RulesBelote o_ = saveRulesBelote(d_);
        assertTrue(o_.isClassicCountPoints());
        assertTrue(o_.isUnderTrumpFoe());
    }
    @Test
    public void t10() {
        RulesBelote d_ = new RulesBelote();
        d_.setComptePointsClassique(false);
        d_.setSousCoupeAdv(false);
        d_.setUnderTrumpFoe(false);
        RulesBelote o_ = saveRulesBelote(d_);
        assertFalse(o_.isClassicCountPoints());
        assertFalse(o_.isUnderTrumpFoe());
    }
}
