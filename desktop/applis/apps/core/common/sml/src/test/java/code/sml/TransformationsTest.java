package code.sml;

import code.sml.util.ResourcesMessagesUtil;
import code.util.CharList;
import org.junit.Test;
import static code.sml.EquallableRowColUtil.assertEq;


public class TransformationsTest {

    @Test
    public void encodeHtml1Test() {
        assertEq("without escaping", DocumentBuilder.encodeHtml("without escaping"));
    }
    @Test
    public void encodeHtml2Test() {
        assertEq("&#233; changes", DocumentBuilder.encodeHtml("&eacute; changes"));
    }
    @Test
    public void encodeHtmll3Test() {
        assertEq("&unknown; changes", DocumentBuilder.encodeHtml("&unknown; changes"));
    }
    @Test
    public void encodeHtml4Test() {
        assertEq("&#233", DocumentBuilder.encodeHtml("&#233"));
    }
    @Test
    public void encodeHtml5Test() {
        assertEq("&#233;", DocumentBuilder.encodeHtml("&#233;"));
    }
    @Test
    public void encodeHtml6Test() {
        assertEq("&eacute", DocumentBuilder.encodeHtml("&eacute"));
    }
    @Test
    public void transformSpecialChars1Test() {
        assertEq(String.valueOf(CharList.wrapCharArray((char)233)), DocumentBuilder.transformSpecialChars("&eacute;"));
    }

    @Test
    public void transformSpecialChars2Test() {
        assertEq("eacute", DocumentBuilder.transformSpecialChars("eacute"));
    }

    @Test
    public void transformSpecialChars3Test() {
        assertEq("&unknown;", DocumentBuilder.transformSpecialChars("&unknown;"));
    }

    @Test
    public void transformSpecialChars4Test() {
        assertEq("&eacute", DocumentBuilder.transformSpecialChars("&eacute"));
    }

    @Test
    public void transformSpecialChars5Test() {
        assertEq(String.valueOf(CharList.wrapCharArray((char)233)), DocumentBuilder.transformSpecialChars("&#233;"));
    }

    @Test
    public void transformSpecialChars6Test() {
        assertEq("&#", DocumentBuilder.transformSpecialChars("&#"));
    }

    @Test
    public void transformSpecialChars7Test() {
        assertEq("&", DocumentBuilder.transformSpecialChars("&"));
    }

    @Test
    public void transformSpecialChars8Test() {
        assertEq("&lt;", DocumentBuilder.transformSpecialChars("&lt;"));
    }

    @Test
    public void transformSpecialChars9Test() {
        assertEq("&gt;", DocumentBuilder.transformSpecialChars("&gt;"));
    }

    @Test
    public void transformSpecialChars10Test() {
        assertEq("&", DocumentBuilder.transformSpecialChars("&amp;", true, false));
    }

    @Test
    public void transformSpecialChars11Test() {
        assertEq("<", DocumentBuilder.transformSpecialChars("<"));
    }

    @Test
    public void transformSpecialChars12Test() {
        assertEq(">", DocumentBuilder.transformSpecialChars(">"));
    }

    @Test
    public void transformSpecialChars13Test() {
        assertEq("&", DocumentBuilder.transformSpecialChars("&amp;", true, false));
    }
    @Test
    public void transformSpecialChars14Test() {
        assertEq("&amp;", DocumentBuilder.transformSpecialChars("&amp;", false, false));
    }
    @Test
    public void transformSpecialChars15Test() {
        assertEq("&amp;", DocumentBuilder.transformSpecialChars("&amp;", false));
    }
    @Test
    public void transformSpecialChars16Test() {
        assertEq("&", DocumentBuilder.transformSpecialChars("&amp;", true));
    }
    @Test
    public void transformSpecialChars1FailTest() {
        assertEq("",DocumentBuilder.transformSpecialChars("&#;").trim());
    }

    @Test
    public void transformSpecialChars2FailTest() {
        assertEq("1",DocumentBuilder.transformSpecialChars("&#a;"));
    }

    @Test
    public void encodeToHtml1Test() {
        assertEq("a", DocumentBuilder.encodeToHtml("a"));
    }

    @Test
    public void encodeToHtml2Test() {
        assertEq("&#233;", DocumentBuilder.encodeToHtml(String.valueOf(CharList.wrapCharArray((char)233))));
    }

    @Test
    public void resourcesMessagesUtil() {
        assertEq("folder/lg/file.properties",ResourcesMessagesUtil.getPropertiesPath("folder","lg","file"));
    }
}
