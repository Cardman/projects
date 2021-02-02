package code.util.consts;

import code.util.EquallableExUtil;
import org.junit.Test;

public final class LanguagesTest  extends EquallableExUtil {
    @Test
    public void test() {
        assertEq(2, Languages.getLanguages().size());
        assertEq(2, Languages.getDisplayLanguages().size());
    }
}
