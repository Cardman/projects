package code.util.consts;

import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;

public final class LanguagesTest {
    @Test
    public void test() {
        assertEq(2, Languages.getLanguages().size());
        assertEq(2, Languages.getDisplayLanguages().size());
    }
}
