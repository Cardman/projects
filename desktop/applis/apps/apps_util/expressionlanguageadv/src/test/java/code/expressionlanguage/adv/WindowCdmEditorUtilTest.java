package code.expressionlanguage.adv;

import org.junit.Test;

public final class WindowCdmEditorUtilTest extends EquallableElAdvUtil {
    @Test
    public void lineSeparator1() {
        assertEq("\r\n",WindowCdmEditor.lineSeparator("a\r\nb"));
    }
    @Test
    public void lineSeparator2() {
        assertEq("\r",WindowCdmEditor.lineSeparator("a\rb"));
    }
    @Test
    public void lineSeparator3() {
        assertEq("\n",WindowCdmEditor.lineSeparator("a\nb"));
    }
    @Test
    public void lineSeparator4() {
        assertEq("\r",WindowCdmEditor.lineSeparator("a\r"));
    }
}
