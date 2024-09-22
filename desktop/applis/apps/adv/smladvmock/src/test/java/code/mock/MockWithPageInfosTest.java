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
        assertEq(0,new MockCharacterCaseConverter().index("ABCDEF",'0'));
    }
    @Test
    public void converter2() {
        assertEq(10,new MockCharacterCaseConverter().index("ABCDEF",'A'));
    }
    @Test
    public void converter3() {
        assertEq(10,new MockCharacterCaseConverter().index("ABCDEF",'a'));
    }
    @Test
    public void converter4() {
        assertEq(-1,new MockCharacterCaseConverter().index("ABCDEF",' '));
    }

    @Test
    public void isMajHex1() {
        assertFalse(MockCharacterCaseConverter.isMajHex('A'-1));
    }
    @Test
    public void isMajHex2() {
        assertFalse(MockCharacterCaseConverter.isMajHex('F'+1));
    }
    @Test
    public void isMajHex3() {
        assertTrue(MockCharacterCaseConverter.isMajHex('A'));
    }
    @Test
    public void isMajHex4() {
        assertTrue(MockCharacterCaseConverter.isMajHex('F'));
    }
    @Test
    public void isMinHex1() {
        assertFalse(MockCharacterCaseConverter.isMinHex('a'-1));
    }
    @Test
    public void isMinHex2() {
        assertFalse(MockCharacterCaseConverter.isMinHex('f'+1));
    }
    @Test
    public void isMinHex3() {
        assertTrue(MockCharacterCaseConverter.isMinHex('a'));
    }
    @Test
    public void isMinHex4() {
        assertTrue(MockCharacterCaseConverter.isMinHex('f'));
    }
}
