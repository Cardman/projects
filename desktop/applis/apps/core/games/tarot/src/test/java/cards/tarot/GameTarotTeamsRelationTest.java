package cards.tarot;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.ModeTarot;
import code.util.Bytes;
import code.util.CustList;
import code.util.EnumList;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class GameTarotTeamsRelationTest extends CommonGameTarot {
    @Test
    public void teams1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_,new Bytes(),conf_,rules_);
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.FOLD, rules_, taker_);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_,new Bytes(),conf_,rules_);
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_,new Bytes(),conf_,rules_);
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_, calledPlayers_,conf_,rules_);
        byte nbPl_ = (byte) rules_.getDealing().getId().getNombreJoueurs();
        for (byte p = 0; p < nbPl_; p++) {
            g_.determinerConfiance(p,nbPl_);
        }
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) taker_);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_, calledPlayers_,conf_,rules_);
        byte nbPl_ = (byte) rules_.getDealing().getId().getNombreJoueurs();
        for (byte p = 0; p < nbPl_; p++) {
            g_.determinerConfiance(p,nbPl_);
        }
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_, calledPlayers_,conf_,rules_);
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_, calledPlayers_,conf_,rules_);
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_, calledPlayers_,conf_,rules_);
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
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        int taker_ = getTaker(rules_,dealer_,bids_);
        CustList<CustList<Boolean>> conf_ = getConf(BidTarot.GUARD, rules_, taker_);
        Bytes calledPlayers_ = new Bytes();
        calledPlayers_.add((byte) 1);
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)taker_, calledPlayers_,conf_,rules_);
        Bytes set_ = new Bytes();
        set_.add((byte) 2);
        set_.add((byte) 1);
        assertTrue(!g_.isSameTeam(set_));
    }
    private static CustList<CustList<Boolean>> getConf(BidTarot _b, RulesTarot _r, int _taker){
        CustList<CustList<Boolean>> confidence_ = new CustList<CustList<Boolean>>();
        ModeTarot mode_ = _r.getMode();
        boolean b_ = false;
        if (mode_ == ModeTarot.NORMAL) {
            b_ = true;
        } else if (mode_ == ModeTarot.NORMAL_WITH_ONE_FOR_ONE) {
            b_ = true;
        } else if (mode_ == ModeTarot.NORMAL_WITH_MISERE) {
            b_ = true;
        }
        byte nbPl_ = (byte) _r.getDealing().getId().getNombreJoueurs();
        for (int i = 0; i< nbPl_; i++) {
            CustList<Boolean> c_ = new CustList<Boolean>();
            for (int j = 0; j< nbPl_; j++) {
                c_.add(i == j);
            }
            confidence_.add(c_);
        }
        if (!b_ || !_b.isJouerDonne()) {
            for (byte i = IndexConstants.FIRST_INDEX; i < nbPl_; i++) {
                for (byte p: _r.getDealing().getAppelesDetermines(i)) {
                    confidence_.get(i).set(p,true);
                }
                confidence_.get(i).set(i,true);
            }
        } else if (_r.getDealing().getAppel() == CallingCard.DEFINED) {
            Bytes attaquants_= _r.getDealing().getAppelesDetermines((byte) _taker);
            attaquants_.add((byte) _taker);
            Bytes defenseurs_=GameTarotTeamsRelation.autresJoueurs(attaquants_, nbPl_);
            for(byte j1_:attaquants_) {
                for(byte j2_:attaquants_) {
                    if(j1_==j2_) {
                        continue;
                    }
                    confidence_.get(j1_).set(j2_,true);
                }
            }
            for(byte j1_:defenseurs_) {
                for(byte j2_:defenseurs_) {
                    if(j1_==j2_) {
                        continue;
                    }
                    confidence_.get(j1_).set(j2_,true);
                }
            }
        } else if (_r.getDealing().getAppel() == CallingCard.WITHOUT) {
            Bytes defenseurs_=new Bytes();
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPl_; joueur_++) {
                if(joueur_==_taker) {
                    continue;
                }
                defenseurs_.add(joueur_);
            }
            for(byte j1_:defenseurs_) {
                for(byte j2_:defenseurs_) {
                    if(j1_==j2_) {
                        continue;
                    }
                    confidence_.get(j1_).set(j2_,true);
                }
            }
        }
        return confidence_;
    }
}
