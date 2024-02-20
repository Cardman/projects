package cards.tarot;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.ModeTarot;
import code.util.Bytes;
import code.util.CustList;
import code.util.IdList;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameTarotTeamsRelationTest extends CommonGameTarot {
    @Test
    public void teams1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_,(byte)taker_);
        CustList<Bytes> teams_ = g_.teams();
        assertEq(2, teams_.size());
        assertEq(1, teams_.get(0).size());
        assertEq(0, teams_.get(0).get(0));
        assertEq(2, teams_.get(1).size());
        assertEq(2, teams_.get(1).get(0));
        assertEq(1, teams_.get(1).get(1));
    }
    @Test
    public void teams2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(BidTarot.FOLD, rules_,(byte)taker_);
        CustList<Bytes> teams_ = g_.teams();
        assertEq(3, teams_.size());
        assertEq(2, teams_.get(0).size());
        assertEq(3, teams_.get(0).get(0));
        assertEq(0, teams_.get(0).get(1));
        assertEq(2, teams_.get(1).size());
        assertEq(4, teams_.get(1).get(0));
        assertEq(1, teams_.get(1).get(1));
        assertEq(2, teams_.get(2).size());
        assertEq(5, teams_.get(2).get(0));
        assertEq(2, teams_.get(2).get(1));
    }
    @Test
    public void teams3Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_,(byte)taker_);
        CustList<Bytes> teams_ = g_.teams();
        assertEq(2, teams_.size());
        assertEq(1, teams_.get(1).size());
        assertEq(3, teams_.get(1).get(0));
        assertEq(3, teams_.get(0).size());
        assertEq(1, teams_.get(0).get(0));
        assertEq(2, teams_.get(0).get(1));
        assertEq(0, teams_.get(0).get(2));
    }
    @Test
    public void teams4Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_, taker_, (byte)1);
        g_.determinerConfiances();
        CustList<Bytes> teams_ = g_.teams();
        assertEq(2, teams_.size());
        assertEq(2, teams_.get(1).size());
        assertEq(3, teams_.get(1).get(0));
        assertEq(1, teams_.get(1).get(1));
        assertEq(3, teams_.get(0).size());
        assertEq(2, teams_.get(0).get(0));
        assertEq(4, teams_.get(0).get(1));
        assertEq(0, teams_.get(0).get(2));
    }
    @Test
    public void teams5Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_, taker_, (byte)taker_);
        g_.determinerConfiances();
        CustList<Bytes> teams_ = g_.teams();
        assertEq(2, teams_.size());
        assertEq(1, teams_.get(1).size());
        assertEq(3, teams_.get(1).get(0));
        assertEq(4, teams_.get(0).size());
        assertEq(1, teams_.get(0).get(0));
        assertEq(2, teams_.get(0).get(1));
        assertEq(4, teams_.get(0).get(2));
        assertEq(0, teams_.get(0).get(3));
    }

    @Test
    public void isSameTeam1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_, taker_, (byte)1);
        Bytes set_ = new Bytes();
        set_.add((byte) 1);
        set_.add((byte) taker_);
        assertTrue(g_.isSameTeam(set_));
    }
    @Test
    public void isSameTeam2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_, taker_, (byte)1);
        Bytes set_ = new Bytes();
        set_.add((byte) 2);
        set_.add((byte) taker_);
        assertTrue(!g_.isSameTeam(set_));
    }
    @Test
    public void isSameTeam3Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_, taker_, (byte)1);
        Bytes set_ = new Bytes();
        set_.add((byte) 0);
        set_.add((byte) 2);
        set_.add((byte) 4);
        assertTrue(g_.isSameTeam(set_));
    }
    @Test
    public void isSameTeam4Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        GameTarotTeamsRelation g_ = teams(rules_, taker_, (byte)1);
        Bytes set_ = new Bytes();
        set_.add((byte) 2);
        set_.add((byte) 1);
        assertTrue(!g_.isSameTeam(set_));
    }

    private GameTarotTeamsRelation teams(RulesTarot _r, int _t, byte... _c) {
        return teams(BidTarot.GUARD,_r,_t,_c);
    }

    private GameTarotTeamsRelation teams(BidTarot _b, RulesTarot _r, int _t, byte... _c) {
        CustList<CustList<BoolVal>> conf_ = getConf(_b, _r, _t);
        Bytes calledPlayers_ = Bytes.newList(_c);
        return new GameTarotTeamsRelation((byte) _t, calledPlayers_,conf_, _r);
    }

    private static CustList<CustList<BoolVal>> getConf(BidTarot _b, RulesTarot _r, int _taker){
        return getConfi(_b, _r, _taker);
    }
}
