package code.sml;

import code.util.CustList;
import org.junit.Test;

import static code.sml.EquallableRowColUtil.assertEq;
import static org.junit.Assert.*;

public class DocReqTest {

    @Test
    public void getElementById1Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getElementById(doc_, "groupId", "id", "ref");
        assertSame(doc_.getDocumentElement().getFirstChild(),elt_);
    }

    @Test
    public void getElementById2Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag groupId='two'><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getElementById(doc_, "groupId", "id", "two");
        assertSame(doc_.getDocumentElement(),elt_);
    }

    @Test
    public void getElementById3Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getElementById(doc_, "id", "groupId", "ref");
        assertSame(doc_.getDocumentElement().getFirstChild(),elt_);
    }

    @Test
    public void getElementById4Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag groupId='two'><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getElementById(doc_, "id", "groupId", "two");
        assertSame(doc_.getDocumentElement(),elt_);
    }

    @Test
    public void getElementById5Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag groupId='two'><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getElementById(doc_, "id", "groupId", "ref");
        assertSame(doc_.getDocumentElement().getFirstChild(),elt_);
    }

    @Test
    public void getElementById6Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getElementById(doc_, "id", "groupId", "unknown");
        assertNull(elt_);
    }
    @Test
    public void getFirstElementByAttribute1Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getFirstElementByAttribute(doc_, "id", "ref");
        assertSame(doc_.getDocumentElement().getFirstChild(),elt_);
    }
    @Test
    public void getFirstElementByAttribute2Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag><inner id='ref'/>Text</tag>");
        Element elt_ = DocumentBuilder.getFirstElementByAttribute(doc_, "id", "unknown");
        assertNull(elt_);
    }
    @Test
    public void getRowColOfNodeOrAttributeTest() {
        String xml_ = "<tag><inner id='ref'/>Text</tag>";
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax(xml_);
        assertNotNull(DocumentBuilder.getRowColOfNodeOrAttribute(xml_,null,0,"",4,true));
        assertNotNull(DocumentBuilder.getRowColOfNodeOrAttribute(xml_,doc_.getDocumentElement(),0,"",4,true));
    }
    @Test
    public void getDeepChildNodesDocOrder1Test() {
        String xml_ = "<tag><inner id='ref'/>Text</tag>";
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax(xml_);
        assertEq(3,DocumentBuilder.getDeepChildNodesDocOrder(doc_.getDocumentElement(),null).size());
    }

    @Test
    public void getDeepChildNodesDocOrder2Test() {
        String xml_ = "<tag/>";
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax(xml_);
        assertEq(1,DocumentBuilder.getDeepChildNodesDocOrder(doc_.getDocumentElement(),null).size());
    }

    @Test
    public void getDeepChildNodesDocOrder3Test() {
        String xml_ = "<tag><inner id='ref'>Inner</inner>Text</tag>";
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax(xml_);
        Element fr_ = doc_.getDocumentElement();
        assertEq(3,DocumentBuilder.getDeepChildNodesDocOrder(fr_,fr_.getLastChild()).size());
    }

    @Test
    public void getDeepChildNodesDocOrder4Test() {
        String xml_ = "<tag><inner id='ref'>Inner</inner>Text</tag>";
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax(xml_);
        Element fr_ = doc_.getDocumentElement();
        assertEq(0,DocumentBuilder.getDeepChildNodesDocOrder(fr_,fr_).size());
    }

    @Test
    public void getDeepChildNodesDocOrder5Test() {
        assertEq(0,DocumentBuilder.getDeepChildNodesDocOrder(null,null).size());
    }
    @Test
    public void rowColTest() {
        RowCol rcOne_ = new RowCol();
        RowCol rcTwo_ = new RowCol();
        assertTrue(rcOne_.eq(rcTwo_));
        rcOne_ = new RowCol();
        rcOne_.setCol(5);
        rcTwo_ = new RowCol();
        assertTrue(!rcOne_.eq(rcTwo_));
        assertTrue(rcOne_.cmp(rcTwo_) > CustList.EQ_CMP);
        rcOne_ = new RowCol();
        rcTwo_ = new RowCol();
        rcTwo_.setRow(5);
        assertTrue(!rcOne_.eq(rcTwo_));
        assertTrue(rcOne_.cmp(rcTwo_) < CustList.EQ_CMP);
    }
    @Test
    public void parseNullTest() {
        assertNull(DocumentBuilder.parseSax(null));
        assertNull(DocumentBuilder.parseNoTextDocument(null));
    }
    @Test
    public void encTest() {
        assertEq("&#9;",DocumentBuilder.encodeToHtml("\u0009"));
    }

    @Test
    public void tabTest() {
        assertEq(5,DocumentBuilder.newDocumentBuilder(5).getTabWidth());
        assertEq(0,DocumentBuilder.newDocumentBuilder(0).getTabWidth());
        assertEq(4,DocumentBuilder.newDocumentBuilder(-1).getTabWidth());
    }
}
