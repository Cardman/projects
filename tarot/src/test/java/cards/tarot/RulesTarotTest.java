package cards.tarot;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import cards.tarot.enumerations.DealingTarot;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class RulesTarotTest {

    Object[] repartitions() {
        return $($(DealingTarot.DEAL_1_VS_2),
                    $(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL),$(DealingTarot.DEAL_2_VS_2_CALL_KING),
                    $(DealingTarot.DEAL_2_VS_2_CALL_CHAR),
                    $(DealingTarot.DEAL_2_VS_3_CALL_CHAR),$(DealingTarot.DEAL_2_VS_3_CALL_KING),
                    $(DealingTarot.DEAL_2_VS_4_CALL_CHAR),$(DealingTarot.DEAL_2_VS_4_CALL_KING),
                    $(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL));
    }

    @Test
    @Parameters(method="repartitions")
    public void isValidRules_defaultRules1Test(DealingTarot _r) {
        RulesTarot rules_ = new RulesTarot(_r);
        rules_.setRepartition(_r);
        assertTrue(rules_.isValidRules());
    }
}
