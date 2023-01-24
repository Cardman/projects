package code.mock;

import org.junit.Test;

public final class MockWithPageInfosTest extends EquallableSmlAdvMockUtil {
    @Test
    public void test() {
        MockWithPageInfos m_ = new MockWithPageInfos();
        assertEq(0,m_.getPage().getFormatIdMap().size());
    }
}
