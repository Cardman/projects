package code.expressionlanguage.filenames;

import code.util.StringList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class DefaultNameValidatingTest {
    @Test
    public void ok1() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok(""));
    }
    @Test
    public void ok2() {
        assertTrue(!new DefaultNameValidating(new StringList("0")).ok("0"));
    }
    @Test
    public void ok3() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok(">"));
    }
    @Test
    public void ok4() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok("."));
    }
    @Test
    public void ok5() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok(".."));
    }
    @Test
    public void ok6() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok(" 0"));
    }
    @Test
    public void ok7() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok("0 "));
    }
    @Test
    public void ok8() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok(" "));
    }
    @Test
    public void ok9() {
        assertTrue(new DefaultNameValidating(new StringList()).ok("0"+(char)160+"1"));
    }
    @Test
    public void ok10() {
        assertTrue(new DefaultNameValidating(new StringList()).ok("0 1"));
    }
    @Test
    public void ok11() {
        assertTrue(new DefaultNameValidating(new StringList()).ok("0-1"));
    }
    @Test
    public void ok12() {
        assertTrue(new DefaultNameValidating(new StringList()).ok("0_1"));
    }
    @Test
    public void ok13() {
        assertTrue(!new DefaultNameValidating(new StringList()).ok("0."));
    }
    @Test
    public void ok14() {
        assertTrue(new DefaultNameValidating(new StringList()).ok("0.1"));
    }
    @Test
    public void okPath1() {
        assertTrue(new DefaultNameValidating(new StringList()).okPath("0.1",'/'));
    }
    @Test
    public void okPath2() {
        assertTrue(new DefaultNameValidating(new StringList()).okPath("0/1",'/'));
    }
    @Test
    public void okPath3() {
        assertTrue(!new DefaultNameValidating(new StringList()).okPath("0/>",'/'));
    }
    @Test
    public void okPath4() {
        assertTrue(!new DefaultNameValidating(new StringList()).okPath(">/0",'/'));
    }
}
