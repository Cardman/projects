package code.sml;

import org.junit.Test;

import static code.sml.EquallableRowColUtil.assertEq;

public class ExportTest {

    @Test
    public void exportSorted1Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq("<tag/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted2Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>e</tag>").getDocumentElement();
        assertEq("<tag>e</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted3Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=''/>").getDocumentElement();
        assertEq("<tag a=\"\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted4Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=''>e</tag>").getDocumentElement();
        assertEq("<tag a=\"\">e</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted5Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"\"/>").getDocumentElement();
        assertEq("<tag a=\"\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted6Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq("<tag a=\"\">e</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted7Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq("<tag a=\"&quot;&quot;&apos;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted8Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq("<tag a=\"&quot;&quot;&apos;\">e</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted9Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq("<tag a=\"&apos;&quot;&apos;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted10Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq("<tag a=\"&apos;&quot;&apos;\">e</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted11Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&lt;'/>").getDocumentElement();
        assertEq("<tag a=\"&lt;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted12Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&gt;'/>").getDocumentElement();
        assertEq("<tag a=\"&gt;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted13Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&amp;'/>").getDocumentElement();
        assertEq("<tag a=\"&amp;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted14Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq("<tag a=\"2\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted15Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&lt;</tag>").getDocumentElement();
        assertEq("<tag>&lt;</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted16Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&gt;</tag>").getDocumentElement();
        assertEq("<tag>&gt;</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted17Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&amp;</tag>").getDocumentElement();
        assertEq("<tag>&amp;</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted18Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag>&#50;</tag>").getDocumentElement();
        assertEq("<tag>2</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted19Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("<tag><inner>2</inner></tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted20Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq("<tag><inner>2</inner><two>3</two></tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted21Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("<tag><two>3</two><inner>2</inner></tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted22Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='1' b='2'/>").getDocumentElement();
        assertEq("<tag a=\"1\" b=\"2\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted23Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag b='2' a='1'/>").getDocumentElement();
        assertEq("<tag a=\"1\" b=\"2\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted24Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag><inner/></tag>").getDocumentElement();
        assertEq("<tag><inner/></tag>",elt_.exportSorted());
    }

}
