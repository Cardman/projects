package cards.tarot;
import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import cards.tarot.enumerations.*;
import code.util.EnumList;
import code.util.EnumMap;
import org.junit.Test;


public class RulesTarotTest {
    @Test
    public void isValidRules_defaultRules1Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules2Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules3Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_2_CALL_KING);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules4Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules5Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules6Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules7Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_CHAR);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_CALL_CHAR);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules8Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isValidRules_defaultRules9Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isNotValidTest(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedHandfuls().put(Handfuls.ONE,0);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void isNotValidRules1Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedHandfuls().put(Handfuls.TWO,1);
        assertTrue(!rules_.isValidRules());
    }
    @Test
    public void isNotValidRules2Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_3);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,19);
        assertTrue(!rules_.isValidRules());
    }
    @Test
    public void isNotValidRules3Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,23);
        assertTrue(!rules_.isValidRules());
    }
    @Test
    public void isNotValidRules4Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedHandfuls().put(Handfuls.ONE,-1);
        assertTrue(!rules_.isValidRules());
    }
    @Test
    public void isNotValidRules5Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedHandfuls().removeKey(Handfuls.ONE);
        assertTrue(!rules_.isValidRules());
    }
    @Test
    public void isNotValidRules6Test(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedBids().removeKey(BidTarot.GUARD);
        assertTrue(!rules_.isValidRules());
    }
    @Test
    public void getCurrentAllowedHandfulsTest(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedHandfuls().put(Handfuls.ONE,0);
        assertEq(3,rules_.getCurrentAllowedHandfuls().size());
    }
    @Test
    public void getContratsAutorisesTest(){
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        rules_.getAllowedBids().put(BidTarot.TAKE,false);
        assertEq(4,rules_.getContratsAutorises().size());
    }
    @Test
    public void copyTest() {
        RulesTarot rules_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        RulesTarot rulesTwo_ = new RulesTarot(rules_);
        rulesTwo_.setMiseres(new EnumList<Miseres>());
        rulesTwo_.setAllowedBids(new EnumMap<BidTarot, Boolean>());
        rulesTwo_.setPoigneesAutorisees(new EnumMap<Handfuls, Integer>());
        rulesTwo_.setEndDealTarot(rules_.getEndDealTarot());
        rulesTwo_.setNbDeals(4);
        rulesTwo_.setMixedCards(rules_.getMixedCards());
        rulesTwo_.setAllowedHandfuls(rules_.getAllowedHandfuls());
        assertEq(0,rules_.getNbDeals());
        assertNotSame(rules_,rulesTwo_);
    }
}