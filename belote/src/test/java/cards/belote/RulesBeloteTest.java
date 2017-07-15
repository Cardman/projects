package cards.belote;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.belote.enumerations.DealingBelote;

@SuppressWarnings("static-method")
public class RulesBeloteTest {

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
}
