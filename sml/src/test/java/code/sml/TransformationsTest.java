package code.sml;

import static code.sml.EquallableRowColUtil.assertEq;

import org.junit.Test;

import code.sml.exceptions.BadNumberedCharacterException;

@SuppressWarnings("static-method")
public class TransformationsTest {

    @Test
    public void encodeHtml1Test() {
        assertEq("without escaping", DocumentBuilder.encodeHtml("without escaping"));
    }
    @Test
    public void encodeHtml2Test() {
    	assertEq("&eacute; changes", DocumentBuilder.encodeHtml("&#233; changes"));
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
        assertEq(new String(new char[]{233}), DocumentBuilder.transformSpecialChars("&eacute;"));
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
        assertEq(new String(new char[]{233}), DocumentBuilder.transformSpecialChars("&#233;"));
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
        assertEq("&amp;", DocumentBuilder.transformSpecialChars("&amp;", true, false, false));
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
        assertEq("&", DocumentBuilder.transformSpecialChars("&amp;", true, false, false));
    }

    @Test(expected=BadNumberedCharacterException.class)
    public void transformSpecialChars1FailTest() {
        DocumentBuilder.transformSpecialChars("&#;");
    }

    @Test(expected=BadNumberedCharacterException.class)
    public void transformSpecialChars2FailTest() {
        DocumentBuilder.transformSpecialChars("&#a;");
    }
//    @Test
//    public void encodeUrlString1Test() {
//        assertEq("eacute", DocumentBuilder.encodeUrlString("eacute", false));
//    }

//    @Test
//    public void encodeUrlString2Test() {
//        assertEq("eacute", DocumentBuilder.encodeUrlString("eacute", true));
//    }

//    @Test
//    public void encodeUrlString3Test() {
//        assertEq("%%A5", DocumentBuilder.encodeUrlString("%25%A5", false));
//    }

//    @Test
//    public void encodeUrlString4Test() {
//        assertEq(new String(new char[]{'%',165}), DocumentBuilder.encodeUrlString("%25%A5", true));
//    }

    @Test
    public void encodeToHtml1Test() {
        assertEq("a", DocumentBuilder.encodeToHtml("a"));
    }

    @Test
    public void encodeToHtml2Test() {
//        assertEq("&eacute;", DocumentBuilder.encodeToHtml(new String(new char[]{233})));
        assertEq("&#233;", DocumentBuilder.encodeToHtml(new String(new char[]{233})));
    }

}
