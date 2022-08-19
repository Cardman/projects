package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.EnumMap;
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
    public void copyTest() {
        RulesBelote rules_ = new RulesBelote();
        RulesBelote rulesTwo_ = new RulesBelote(rules_);
        rulesTwo_.setAllowedBids(new EnumMap<BidBelote, BoolVal>());
        rulesTwo_.getCommon().setNbDeals(4);
        rulesTwo_.getCommon().setNbDeals(4);
        rulesTwo_.setAllowedDeclares(new EnumMap<DeclaresBelote, BoolVal>());
        rulesTwo_.setAllowedDeclares(new EnumMap<DeclaresBelote, BoolVal>());
        rulesTwo_.getCommon().setMixedCards(rules_.getCommon().getMixedCards());
        assertEq(0, rules_.getCommon().getNbDeals());
        assertNotSame(rules_,rulesTwo_);
    }
}
