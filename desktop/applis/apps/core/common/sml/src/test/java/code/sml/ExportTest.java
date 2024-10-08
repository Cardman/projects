package code.sml;

import org.junit.Test;

public class ExportTest extends EquallableRowColUtil {

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
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq("<tag a=\"&#34;&#34;&#39;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted8Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq("<tag a=\"&#34;&#34;&#39;\">e</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted9Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq("<tag a=\"&#39;&#34;&#39;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted10Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq("<tag a=\"&#39;&#34;&#39;\">e</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted11Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>lt&60;<tag a='&lt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#60;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted12Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>gt&62;<tag a='&gt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#62;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted13Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>amp&38;<tag a='&amp;'/>").getDocumentElement();
        assertEq("<tag a=\"&#38;\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted14Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq("<tag a=\"2\"/>",elt_.exportSorted());
    }

    @Test
    public void exportSorted15Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>lt&60;<tag>&lt;</tag>").getDocumentElement();
        assertEq("<tag>&#60;</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted16Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>gt&62;<tag>&gt;</tag>").getDocumentElement();
        assertEq("<tag>&#62;</tag>",elt_.exportSorted());
    }

    @Test
    public void exportSorted17Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<>amp&38;<tag>&amp;</tag>").getDocumentElement();
        assertEq("<tag>&#38;</tag>",elt_.exportSorted());
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

    @Test
    public void eqDocs1Test() {
        assertTrue(DocumentBuilder.equalsDocs("<tag><one/><two/></tag>","<tag><one/><two/></tag>"));
    }

    @Test
    public void eqDocs2Test() {
        assertTrue(!DocumentBuilder.equalsDocs("<tag><one/><two/></tag>","<tag><two/><one/></tag>"));
    }

    @Test
    public void eqDocs3Test() {
        assertTrue(DocumentBuilder.equalsDocs("<tag a='1' b='2'/>","<tag a='1' b='2'/>"));
    }

    @Test
    public void eqDocs4Test() {
        assertTrue(DocumentBuilder.equalsDocs("<tag a='1' b='2'/>","<tag b='2' a='1'/>"));
    }

    @Test
    public void eqDocs5Test() {
        assertTrue(!DocumentBuilder.equalsDocs("<tag a='1' b='2'","<tag a='1' b='2'/>"));
    }

    @Test
    public void eqDocs6Test() {
        assertTrue(!DocumentBuilder.equalsDocs("<tag a='1' b='2'/>","<tag b='2' a='1'"));
    }
    @Test
    public void exportFull1Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq("<tag/>",elt_.export());
    }

    @Test
    public void exportFull2Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag>e</tag>").getDocumentElement();
        assertEq("<tag>e</tag>",elt_.export());
    }

    @Test
    public void exportFull3Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag a=''/>").getDocumentElement();
        assertEq("<tag a=\"\"/>",elt_.export());
    }

    @Test
    public void exportFull4Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag a=''>e</tag>").getDocumentElement();
        assertEq("<tag a=\"\">e</tag>",elt_.export());
    }

    @Test
    public void exportFull5Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag a=\"\"/>").getDocumentElement();
        assertEq("<tag a=\"\"/>",elt_.export());
    }

    @Test
    public void exportFull6Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq("<tag a=\"\">e</tag>",elt_.export());
    }

    @Test
    public void exportFull7Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq("<tag a=\"&#34;&#34;&#39;\"/>",elt_.export());
    }

    @Test
    public void exportFull8Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq("<tag a=\"&#34;&#34;&#39;\">e</tag>",elt_.export());
    }

    @Test
    public void exportFull9Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq("<tag a=\"&#39;&#34;&#39;\"/>",elt_.export());
    }

    @Test
    public void exportFull10Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq("<tag a=\"&#39;&#34;&#39;\">e</tag>",elt_.export());
    }

    @Test
    public void exportFull11Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>lt&60;<tag a='&lt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#60;\"/>",elt_.export());
    }

    @Test
    public void exportFull12Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>gt&62;<tag a='&gt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#62;\"/>",elt_.export());
    }

    @Test
    public void exportFull13Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>amp&38;<tag a='&amp;'/>").getDocumentElement();
        assertEq("<tag a=\"&#38;\"/>",elt_.export());
    }

    @Test
    public void exportFull14Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq("<tag a=\"2\"/>",elt_.export());
    }

    @Test
    public void exportFull15Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>lt&60;<tag>&lt;</tag>").getDocumentElement();
        assertEq("<tag>&#60;</tag>",elt_.export());
    }

    @Test
    public void exportFull16Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>gt&62;<tag>&gt;</tag>").getDocumentElement();
        assertEq("<tag>&#62;</tag>",elt_.export());
    }

    @Test
    public void exportFull17Test() {
        Element elt_ =  DocumentBuilder.parseSax("<>amp&38;<tag>&amp;</tag>").getDocumentElement();
        assertEq("<tag>&#38;</tag>",elt_.export());
    }

    @Test
    public void exportFull18Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag>&#50;</tag>").getDocumentElement();
        assertEq("<tag>2</tag>",elt_.export());
    }

    @Test
    public void exportFull19Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("<tag><inner>2</inner></tag>",elt_.export());
    }

    @Test
    public void exportFull20Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq("<tag><inner>2</inner><two>3</two></tag>",elt_.export());
    }

    @Test
    public void exportFull21Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("<tag><two>3</two><inner>2</inner></tag>",elt_.export());
    }

    @Test
    public void exportFull22Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag a='1' b='2'/>").getDocumentElement();
        assertEq("<tag a=\"1\" b=\"2\"/>",elt_.export());
    }

    @Test
    public void exportFull23Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag b='2' a='1'/>").getDocumentElement();
        assertEq("<tag b=\"2\" a=\"1\"/>",elt_.export());
    }

    @Test
    public void exportFull24Test() {
        Element elt_ =  DocumentBuilder.parseSax("<tag><inner/></tag>").getDocumentElement();
        assertEq("<tag><inner/></tag>",elt_.export());
    }

    @Test
    public void exportDocFull24Test() {
        Document elt_ =  DocumentBuilder.parseSax("<tag><inner/></tag>");
        assertEq("<tag><inner/></tag>",elt_.export());
    }
    @Test
    public void exportNoText1Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag/>").getDocumentElement();
        assertEq("<tag/>",elt_.export());
    }

    @Test
    public void exportNoText3Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag a=''/>").getDocumentElement();
        assertEq("<tag a=\"\"/>",elt_.export());
    }

    @Test
    public void exportNoText5Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag a=\"\"/>").getDocumentElement();
        assertEq("<tag a=\"\"/>",elt_.export());
    }

    @Test
    public void exportNoText7Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<>quot&34;apos&39;<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq("<tag a=\"&#34;&#34;&#39;\"/>",elt_.export());
    }

    @Test
    public void exportNoText9Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<>quot&34;apos&39;<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq("<tag a=\"&#39;&#34;&#39;\"/>",elt_.export());
    }

    @Test
    public void exportNoText11Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<>lt&60;<tag a='&lt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#60;\"/>",elt_.export());
    }

    @Test
    public void exportNoText12Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<>gt&62;<tag a='&gt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#62;\"/>",elt_.export());
    }

    @Test
    public void exportNoText13Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<>amp&38;<tag a='&amp;'/>").getDocumentElement();
        assertEq("<tag a=\"&#38;\"/>",elt_.export());
    }

    @Test
    public void exportNoText14Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag a='&#50;'/>").getDocumentElement();
        assertEq("<tag a=\"2\"/>",elt_.export());
    }

    @Test
    public void exportNoText22Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag a='1' b='2'/>").getDocumentElement();
        assertEq("<tag a=\"1\" b=\"2\"/>",elt_.export());
    }

    @Test
    public void exportNoText23Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag b='2' a='1'/>").getDocumentElement();
        assertEq("<tag b=\"2\" a=\"1\"/>",elt_.export());
    }

    @Test
    public void exportNoText24Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><inner/></tag>").getDocumentElement();
        assertEq("<tag><inner/></tag>",elt_.export());
    }

    @Test
    public void exportNoText25Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one/><two/></tag>").getDocumentElement();
        assertEq("<tag><one/><two/></tag>",elt_.export());
    }

    @Test
    public void exportNoText26Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertEq("<tag><one><inner/></one><two/></tag>",elt_.export());
    }

    @Test
    public void exportDocNoText26Test() {
        Document elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>");
        assertEq("<tag><one><inner/></one><two/></tag>",elt_.export());
    }

    @Test
    public void getSibling1Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertSame(elt_.getFirstChild(),elt_.getLastChild().getPreviousSibling());
    }

    @Test
    public void getSibling2Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertNull(elt_.getFirstChild().getPreviousSibling());
    }

    @Test
    public void getSibling3Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertNull(elt_.getLastChild().getLastChild());
    }

    @Test
    public void getSibling4Test() {
        Element elt_ =  DocumentBuilder.parseNoTextDocument("<tag><one><inner/></one><two/></tag>").getDocumentElement();
        assertNull(elt_.getPreviousSibling());
    }

    @Test
    public void openTag1Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq("<tag></tag>",elt_.openTag());
    }

    @Test
    public void openTag2Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag>e</tag>").getDocumentElement();
        assertEq("<tag>e</tag>",elt_.openTag());
    }

    @Test
    public void openTag3Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag a=''/>").getDocumentElement();
        assertEq("<tag a=\"\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag4Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag a=''>e</tag>").getDocumentElement();
        assertEq("<tag a=\"\">e</tag>",elt_.openTag());
    }

    @Test
    public void openTag5Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag a=\"\"/>").getDocumentElement();
        assertEq("<tag a=\"\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag6Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag a=\"\">e</tag>").getDocumentElement();
        assertEq("<tag a=\"\">e</tag>",elt_.openTag());
    }

    @Test
    public void openTag7Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a='\"&quot;&apos;'/>").getDocumentElement();
        assertEq("<tag a=\"&#34;&#34;&#39;\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag8Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a='\"&quot;&apos;'>e</tag>").getDocumentElement();
        assertEq("<tag a=\"&#34;&#34;&#39;\">e</tag>",elt_.openTag());
    }

    @Test
    public void openTag9Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a=\"'&quot;&apos;\"/>").getDocumentElement();
        assertEq("<tag a=\"&#39;&#34;&#39;\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag10Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>quot&34;apos&39;<tag a=\"'&quot;&apos;\">e</tag>").getDocumentElement();
        assertEq("<tag a=\"&#39;&#34;&#39;\">e</tag>",elt_.openTag());
    }

    @Test
    public void openTag11Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>lt&60;<tag a='&lt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#60;\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag12Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>gt&62;<tag a='&gt;'/>").getDocumentElement();
        assertEq("<tag a=\"&#62;\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag13Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>amp&38;<tag a='&amp;'/>").getDocumentElement();
        assertEq("<tag a=\"&#38;\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag14Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag a='&#50;'/>").getDocumentElement();
        assertEq("<tag a=\"2\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag15Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>lt&60;<tag>&lt;</tag>").getDocumentElement();
        assertEq("<tag>&#60;</tag>",elt_.openTag());
    }

    @Test
    public void openTag16Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>gt&62;<tag>&gt;</tag>").getDocumentElement();
        assertEq("<tag>&#62;</tag>",elt_.openTag());
    }

    @Test
    public void openTag17Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<>amp&38;<tag>&amp;</tag>").getDocumentElement();
        assertEq("<tag>&#38;</tag>",elt_.openTag());
    }

    @Test
    public void openTag18Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag>&#50;</tag>").getDocumentElement();
        assertEq("<tag>2</tag>",elt_.openTag());
    }

    @Test
    public void openTag19Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("<tag><inner>2</inner></tag>",elt_.openTag());
    }

    @Test
    public void openTag20Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag><inner>&#50;</inner><two>&#51;</two></tag>").getDocumentElement();
        assertEq("<tag><inner>2</inner><two>3</two></tag>",elt_.openTag());
    }

    @Test
    public void openTag21Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag><two>&#51;</two><inner>&#50;</inner></tag>").getDocumentElement();
        assertEq("<tag><two>3</two><inner>2</inner></tag>",elt_.openTag());
    }

    @Test
    public void openTag22Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag a='1' b='2'/>").getDocumentElement();
        assertEq("<tag a=\"1\" b=\"2\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag23Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag b='2' a='1'/>").getDocumentElement();
        assertEq("<tag b=\"2\" a=\"1\"></tag>",elt_.openTag());
    }

    @Test
    public void openTag24Test() {
        FullElement elt_ = (FullElement)DocumentBuilder.parseSax("<tag><inner/></tag>").getDocumentElement();
        assertEq("<tag><inner></inner></tag>",elt_.openTag());
    }
}
