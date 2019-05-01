package code.sml;

import org.junit.Test;

import static code.sml.EquallableRowColUtil.assertEq;

public class ExportTextTest {

    @Test
    public void getTextContent1Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent2Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>e</tag>").getDocumentElement();
        assertEq("e",elt_.getTextContent());
    }

    @Test
    public void getTextContent3Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=''/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent4Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=''>e</tag>").getDocumentElement();
        assertEq("e",elt_.getTextContent());
    }

    @Test
    public void getTextContent5Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"\"/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent6Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq("e",elt_.getTextContent());
    }

    @Test
    public void getTextContent7Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent8Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq("e",elt_.getTextContent());
    }

    @Test
    public void getTextContent9Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent10Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq("e",elt_.getTextContent());
    }

    @Test
    public void getTextContent11Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&lt;'/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent12Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&gt;'/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent13Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&amp;'/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent14Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent15Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&lt;</tag>").getDocumentElement();
        assertEq("<",elt_.getTextContent());
    }

    @Test
    public void getTextContent16Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&gt;</tag>").getDocumentElement();
        assertEq(">",elt_.getTextContent());
    }

    @Test
    public void getTextContent17Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&amp;</tag>").getDocumentElement();
        assertEq("&",elt_.getTextContent());
    }

    @Test
    public void getTextContent18Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&#50;</tag>").getDocumentElement();
        assertEq("2",elt_.getTextContent());
    }

    @Test
    public void getTextContent19Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("2",elt_.getTextContent());
    }

    @Test
    public void getTextContent20Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq("23",elt_.getTextContent());
    }

    @Test
    public void getTextContent21Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("32",elt_.getTextContent());
    }

    @Test
    public void getTextContent22Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='1' b='2'/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent23Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag b='2' a='1'/>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent24Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><inner/></tag>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }

    @Test
    public void getTextContent25Test() {
        Element elt_ = DocumentBuilder.parseNoTextDocument("<tag><inner/></tag>").getDocumentElement();
        assertEq("",elt_.getTextContent());
    }
}
