package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.EnumMap;
import org.junit.Test;

import cards.belote.enumerations.DealingBelote;


public class RulesBeloteTest extends EquallableBeloteUtil {

    @Test
    public void isValidRules_defaultRules1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.CLASSIC_2_VS_2);
        assertTrue(rules_.isValidRules());
    }

    @Test
    public void isValidRules_defaultRules2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        assertTrue(rules_.isValidRules());
    }
    @Test
    public void copyTest() {
        RulesBelote rules_ = new RulesBelote();
        RulesBelote rulesTwo_ = new RulesBelote(rules_);
        rulesTwo_.setAllowedBids(new EnumMap<BidBelote, Boolean>());
        rulesTwo_.setNbDeals(4);
        rulesTwo_.setNombreParties(4);
        rulesTwo_.setAllowedDeclares(new EnumMap<DeclaresBelote, Boolean>());
        rulesTwo_.setAnnoncesAutorisees(new EnumMap<DeclaresBelote, Boolean>());
        rulesTwo_.setMixedCards(rules_.getMixedCards());
        assertEq(0,rules_.getNbDeals());
        assertNotSame(rules_,rulesTwo_);
    }
}
