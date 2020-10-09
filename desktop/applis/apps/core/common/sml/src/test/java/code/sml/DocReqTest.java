package code.sml;

import code.util.core.SortConstants;
import org.junit.Test;

import static code.sml.EquallableRowColUtil.assertEq;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

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
        assertTrue(rcOne_.cmp(rcTwo_) > SortConstants.EQ_CMP);
        rcOne_ = new RowCol();
        rcTwo_ = new RowCol();
        rcTwo_.setRow(5);
        assertTrue(!rcOne_.eq(rcTwo_));
        assertTrue(rcOne_.cmp(rcTwo_) < SortConstants.EQ_CMP);
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

    @Test
    public void text1Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag>Text</tag>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        Text t_ = (Text) elt_.getFirstChild();
        t_.appendData(" appended");
        assertEq("Text appended",t_.getData());
    }

    @Test
    public void text2Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag>Text</tag>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        Text t_ = (Text) elt_.getFirstChild();
        t_.setTextContent("New");
        assertEq("New",t_.getData());
        t_.setData("New");
        assertEq("New",t_.getData());
    }

    @Test
    public void text3Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag>Text</tag>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        Text t_ = (Text) elt_.getFirstChild();
        t_.deleteData(2,2);
        assertEq("Te",t_.getData());
    }
    @Test
    public void text4Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag>Text</tag>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        Text t_ = (Text) elt_.getFirstChild();
        t_.insertData(2,"xt Te");
        assertEq("Text Text",t_.getData());
    }
    @Test
    public void text5Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag>Text</tag>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        Text t_ = (Text) elt_.getFirstChild();
        t_.replaceData(0,4, "Replace");
        assertEq("Replace",t_.getData());
    }
    @Test
    public void text6Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag>Text</tag>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        Text t_ = (Text) elt_.getFirstChild();
        assertEq("xt",t_.substringData(2,2));
    }

    @Test
    public void text7Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag>Text</tag>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        Text t_ = (Text) elt_.getFirstChild();
        assertEq(4,t_.getLength());
    }

    @Test
    public void text8Test() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag/>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        elt_.appendChild(doc_.createTextNode("Text"));
        Text t_ = (Text) elt_.getFirstChild();
        assertEq("Text",t_.getData());
    }

    @Test
    public void textTest() {
        CoreDocument doc_ = (CoreDocument) DocumentBuilder.parseSax("<tag/>");
        FullElement elt_ = (FullElement) doc_.getDocumentElement();
        elt_.appendChild(doc_.createTextNode("Text"));
        Text t_ = (Text) elt_.getFirstChild();
        t_.appendChild(doc_.createTextNode("Sec"));
        t_.removeChild(t_.getFirstChild());
        t_.replaceChild(t_.getFirstChild(),t_.getFirstChild());
        t_.insertAfter(t_.getFirstChild(),t_.getFirstChild());
        t_.insertBefore(t_.getFirstChild(),t_.getFirstChild());
        assertTrue(!t_.hasChildNodes());
        assertTrue(!t_.hasAttributes());
        assertEq(0,t_.getChildElements().size());
        assertEq(0,t_.getChildNodes().size());
        assertNull(t_.getAttributes());
    }
}
