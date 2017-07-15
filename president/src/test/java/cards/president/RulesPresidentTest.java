package cards.president;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class RulesPresidentTest {

    Object[] repartitions() {
        return $($(3),$(4),$(5),$(6),$(7),$(8),
                $(9),$(10),$(11),$(12),$(13),$(14),
                $(15),$(16));
    }

    @Test
    @Parameters(method="repartitions")
    public void isValidRules_defaultRules1Test(int _nbPlayers) {
        RulesPresident rules_ = new RulesPresident(_nbPlayers);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
}
