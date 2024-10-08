package code.sml;

import code.util.CustList;
import org.junit.Test;


public class TransformationsTest extends EquallableRowColUtil {

    @Test
    public void encodeHtml1Test() {
        assertEq("without escaping", DocumentBuilder.encodeHtml("without escaping"));
    }
    @Test
    public void encodeHtml2Test() {
        CustList<EncodedChar> one_ = new CustList<EncodedChar>();
        one_.add(new EncodedChar("&eacute;", 'é'));
        assertEq("é changes", DocumentBuilder.encodeHtml("&eacute; changes",one_));
    }
    @Test
    public void encodeHtmll3Test() {
        CustList<EncodedChar> one_ = new CustList<EncodedChar>();
        one_.add(new EncodedChar("&eacute;", 'é'));
        assertEq("&unknown; changes", DocumentBuilder.encodeHtml("&unknown; changes",one_));
    }
    @Test
    public void encodeHtml4Test() {
        assertEq("&#233", DocumentBuilder.encodeHtml("&#233"));
    }
    @Test
    public void encodeHtml5Test() {
        assertEq("é", DocumentBuilder.encodeHtml("&#233;"));
    }
    @Test
    public void encodeHtml6Test() {
        assertEq("&eacute", DocumentBuilder.encodeHtml("&eacute"));
    }
    @Test
    public void encodeHtml7Test() {
        CustList<EncodedChar> one_ = new CustList<EncodedChar>();
        one_.add(new EncodedChar("&eacute;", 'é'));
        assertEq("&;", DocumentBuilder.encodeHtml("&;",one_));
    }
//    @Test
//    public void transformSpecialChars1Test() {
//        assertEq(String.valueOf(CharList.wrapCharArray((char)233)), DocumentBuilder.transformSpecialChars("&eacute;"));
//    }
//
//    @Test
//    public void transformSpecialChars2Test() {
//        assertEq("eacute", DocumentBuilder.transformSpecialChars("eacute"));
//    }
//
//    @Test
//    public void transformSpecialChars3Test() {
//        assertEq("&unknown;", DocumentBuilder.transformSpecialChars("&unknown;"));
//    }
//
//    @Test
//    public void transformSpecialChars4Test() {
//        assertEq("&eacute", DocumentBuilder.transformSpecialChars("&eacute"));
//    }
//
//    @Test
//    public void transformSpecialChars5Test() {
//        assertEq(String.valueOf(CharList.wrapCharArray((char)233)), DocumentBuilder.transformSpecialChars("&#233;"));
//    }
//
//    @Test
//    public void transformSpecialChars6Test() {
//        assertEq("&#", DocumentBuilder.transformSpecialChars("&#"));
//    }
//
//    @Test
//    public void transformSpecialChars7Test() {
//        assertEq("&", DocumentBuilder.transformSpecialChars("&"));
//    }
//
//    @Test
//    public void transformSpecialChars8Test() {
//        assertEq("&lt;", DocumentBuilder.transformSpecialChars("&lt;"));
//    }
//
//    @Test
//    public void transformSpecialChars9Test() {
//        assertEq("&gt;", DocumentBuilder.transformSpecialChars("&gt;"));
//    }
//
//    @Test
//    public void transformSpecialChars10Test() {
//        assertEq("&", DocumentBuilder.transformSpecialChars("&amp;", true, false));
//    }
//
//    @Test
//    public void transformSpecialChars11Test() {
//        assertEq("<", DocumentBuilder.transformSpecialChars("<"));
//    }
//
//    @Test
//    public void transformSpecialChars12Test() {
//        assertEq(">", DocumentBuilder.transformSpecialChars(">"));
//    }
//
//    @Test
//    public void transformSpecialChars13Test() {
//        assertEq("&", DocumentBuilder.transformSpecialChars("&amp;", true, false));
//    }
//    @Test
//    public void transformSpecialChars14Test() {
//        assertEq("&amp;", DocumentBuilder.transformSpecialChars("&amp;", false, false));
//    }
//    @Test
//    public void transformSpecialChars15Test() {
//        assertEq("&amp;", DocumentBuilder.transformSpecialChars("&amp;", false));
//    }
//    @Test
//    public void transformSpecialChars16Test() {
//        assertEq("&", DocumentBuilder.transformSpecialChars("&amp;", true));
//    }
//    @Test
//    public void transformSpecialChars17Test() {
//        assertEq("&inexist;", DocumentBuilder.transformSpecialChars("&inexist;", true,true));
//    }
//    @Test
//    public void transformSpecialChars0FailTest() {
//        assertEq("&;",DocumentBuilder.transformSpecialChars("&;"));
//    }
//    @Test
//    public void transformSpecialChars1FailTest() {
//        assertEq("",DocumentBuilder.transformSpecialChars("&#;").trim());
//    }
//
//    @Test
//    public void transformSpecialChars2FailTest() {
//        assertEq("1",DocumentBuilder.transformSpecialChars("&#a;"));
//    }
//
//    @Test
//    public void encodeToHtml1Test() {
//        assertEq("a", DocumentBuilder.encodeToHtml("a"));
//    }

//    @Test
//    public void possibleEncodes() {
//        assertNotNull(DocumentBuilder.possibleEncodes());
//    }

//    @Test
//    public void encodeToHtml2Test() {
//        assertEq("&#233;", DocumentBuilder.encodeToHtml(String.valueOf(CharList.wrapCharArray((char)233))));
//    }

//    @Test
//    public void resourcesMessagesUtil() {
//        assertEq("folder/lg/file",ResourcesMessagesUtil.getPropertiesPath("folder","lg","file"));
//    }
}
