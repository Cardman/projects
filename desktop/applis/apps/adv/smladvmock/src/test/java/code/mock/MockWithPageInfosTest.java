package code.mock;

import org.junit.Test;

public final class MockWithPageInfosTest extends EquallableSmlAdvMockUtil {
    @Test
    public void test() {
        MockWithPageInfos m_ = new MockWithPageInfos();
        assertEq(0,m_.getPage().getFormatIdMap().size());
    }
    @Test
    public void converter1() {
        assertEq('0',new MockCharacterCaseConverter().index("ABCDEF",'0'));
    }
    @Test
    public void converter2() {
        assertEq('A',new MockCharacterCaseConverter().index("ABCDEF",'A'));
    }
    @Test
    public void converter3() {
        assertEq('a',new MockCharacterCaseConverter().index("ABCDEF",'a'));
    }
    @Test
    public void converter4() {
        assertEq(-1,new MockCharacterCaseConverter().index("ABCDEF",' '));
    }
}
