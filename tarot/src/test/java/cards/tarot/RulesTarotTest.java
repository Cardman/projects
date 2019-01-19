package cards.tarot;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.tarot.enumerations.DealingTarot;


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
}