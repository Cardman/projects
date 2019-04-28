package code.sml;

import org.junit.Test;

import static code.sml.EquallableRowColUtil.assertEq;

public class DescendantTest {

    @Test
    public void getElementsByTagNameFull1Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull2Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull3Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=''/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull4Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=''>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull5Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull6Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull7Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull8Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull9Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull10Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull11Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&lt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull12Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&gt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull13Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&amp;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull14Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull15Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&lt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull16Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&gt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull17Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&amp;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull18Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&#50;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull19Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(2,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull20Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq(3,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull21Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(3,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull22Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='1' b='2'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull23Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag b='2' a='1'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFull24Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner/></tag>").getDocumentElement();
        assertEq(2,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText1Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText2Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText3Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=''/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText4Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=''>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText5Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText6Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText7Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText8Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText9Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText10Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText11Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&lt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText12Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&gt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText13Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&amp;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText14Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&#50;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText15Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&lt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText16Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&gt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText17Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&amp;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText18Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&#50;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText19Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(2,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText20Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq(3,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText21Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(3,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText22Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='1' b='2'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText23Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag b='2' a='1'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText24Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner/></tag>").getDocumentElement();
        assertEq(2,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameNoText25Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertEq(4,elt_.getElementsByTagName().size());
    }

    @Test
    public void getElementsByTagNameFullFilter1Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter2Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter3Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=''/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter4Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=''>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter5Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter6Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter7Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter8Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter9Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter10Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter11Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&lt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter12Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&gt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter13Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&amp;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter14Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter15Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&lt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter16Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&gt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter17Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&amp;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter18Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&#50;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter19Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter20Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter21Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter22Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='1' b='2'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter23Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag b='2' a='1'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameFullFilter24Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner/></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter1Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter2Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter3Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=''/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter4Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=''>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter5Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter6Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter7Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter8Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter9Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter10Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter11Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&lt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter12Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&gt;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter13Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&amp;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter14Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&#50;'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter15Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&lt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter16Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&gt;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter17Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&amp;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter18Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&#50;</tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter19Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter20Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter21Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter22Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='1' b='2'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter23Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag b='2' a='1'/>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter24Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner/></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getElementsByTagNameNoTextFilter25Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertEq(1,elt_.getElementsByTagName("tag").size());
    }

    @Test
    public void getDescNodesFull1Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull2Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>e</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull3Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=''/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull4Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=''>e</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull5Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"\"/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull6Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull7Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull8Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull9Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull10Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull11Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&lt;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull12Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&gt;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull13Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&amp;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull14Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull15Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&lt;</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull16Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&gt;</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull17Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&amp;</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull18Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag>&#50;</tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull19Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(3,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull20Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq(5,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull21Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(5,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull22Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag a='1' b='2'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull23Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag b='2' a='1'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesFull24Test() {
        Element elt_ =DocumentBuilder.parseSax("<tag><inner/></tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText1Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText2Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>e</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText3Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=''/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText4Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=''>e</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText5Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"\"/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText6Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText7Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText8Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText9Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText10Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText11Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&lt;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText12Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&gt;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText13Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&amp;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText14Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='&#50;'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText15Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&lt;</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText16Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&gt;</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText17Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&amp;</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText18Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag>&#50;</tag>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText19Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText20Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq(3,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText21Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq(3,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText22Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag a='1' b='2'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText23Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag b='2' a='1'/>").getDocumentElement();
        assertEq(1,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText24Test() {
        Element elt_ =DocumentBuilder.parseNoTextDocument("<tag><inner/></tag>").getDocumentElement();
        assertEq(2,elt_.getDescNodes().size());
    }

    @Test
    public void getDescNodesNoText25Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertEq(4,elt_.getDescNodes().size());
    }
}
