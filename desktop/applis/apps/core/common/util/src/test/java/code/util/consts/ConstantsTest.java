package code.util.consts;

import code.util.EquallableExUtil;
import org.junit.Test;

public final class ConstantsTest extends EquallableExUtil {
    @Test
    public void test() {
        assertEq(2, Constants.getDisplayLanguages().size());
        assertEq(2, Constants.getAvailableLanguages().size());
        assertNotNull(Constants.getDisplayLanguage(Constants.getAvailableLanguages().get(0)));
        assertNotNull(Constants.getDisplayLanguage(Constants.getAvailableLanguages().get(1)));
        assertNotNull(Constants.getDefaultLanguage());
    }
}
