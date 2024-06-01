package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.IdMap;
import code.util.core.BoolVal;
import org.junit.Test;

import cards.belote.enumerations.DealingBelote;


public class RulesBeloteTest extends EquallableBeloteUtil {

    @Test
    public void isValidRules_defaultRules1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_2_VS_2);
        assertTrue(rules_.isValidRules());
    }

    @Test
    public void isValidRules_defaultRules2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        assertTrue(rules_.isValidRules());
    }

    @Test
    public void isValidRules_defaultRules3Test() {
        RulesBelote rules_ = new RulesBelote(3);
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        assertTrue(rules_.isValidRules());
    }

    @Test
    public void isValidRules_defaultRules4Test() {
        RulesBelote rules_ = new RulesBelote(3);
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_24);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void copyTest() {
        RulesBelote rules_ = new RulesBelote();
        RulesBelote rulesTwo_ = new RulesBelote(rules_);
        rulesTwo_.setAllowedBids(new IdMap<BidBelote, BoolVal>());
        rulesTwo_.getCommon().setNbDeals(4);
        rulesTwo_.getCommon().setNbDeals(4);
        rulesTwo_.setAllowedDeclares(new IdMap<DeclaresBelote, BoolVal>());
        rulesTwo_.setAllowedDeclares(new IdMap<DeclaresBelote, BoolVal>());
        rulesTwo_.getCommon().setMixedCards(rules_.getCommon().getMixedCards());
        assertEq(0, rules_.getCommon().getNbDeals());
        assertNotSame(rules_,rulesTwo_);
    }
}
