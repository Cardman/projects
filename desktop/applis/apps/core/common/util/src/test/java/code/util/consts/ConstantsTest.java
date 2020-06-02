package code.util.consts;

import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public final class ConstantsTest {
    @Test
    public void test() {
        assertEq(2, Constants.getAvailableLanguages().size());
        for (String s: Constants.getAvailableLanguages()) {
            assertNotNull(Constants.getDisplayLanguage(s));
        }
        assertNotNull(Constants.getDefaultLanguage());
    }
}
