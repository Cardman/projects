package code.util.consts;

import code.util.EquallableExUtil;
import org.junit.Test;

public final class ConstantsTest extends EquallableExUtil {
    @Test
    public void test() {
        assertEq(2, Constants.getAvailableLanguages().size());
        for (String s: Constants.getAvailableLanguages()) {
            assertNotNull(Constants.getDisplayLanguage(s));
        }
        assertNotNull(Constants.getDefaultLanguage());
    }
}
