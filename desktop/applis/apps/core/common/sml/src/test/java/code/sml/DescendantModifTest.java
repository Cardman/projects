package code.sml;

import org.junit.Test;

public class DescendantModifTest extends EquallableRowColUtil {

    @Test
    public void replaceChild1Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        elt_.replaceChild(doc_.createElement("child"),elt_.getFirstChild());
        Node ch_ = elt_.getFirstChild();
        assertEq("child",((Element)ch_).getTagName());
    }

    @Test
    public void replaceChild2Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node f_ = elt_.getFirstChild();
        Node next_ = f_.getNextSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,f_);
        Node ch_ = elt_.getFirstChild();
        assertEq("child",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
    }

    @Test
    public void replaceChild3Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node l_ = elt_.getLastChild();
        Node prev_ = l_.getPreviousSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,l_);
        Node ch_ = elt_.getLastChild();
        assertEq("child",((Element)ch_).getTagName());
        assertSame(prev_, ch_.getPreviousSibling());
    }

    @Test
    public void replaceChild4Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,rem_);
        Node ch_ = elt_.getFirstChild().getNextSibling();
        assertEq("child",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
        assertSame(prev_, ch_.getPreviousSibling());
    }

    @Test
    public void replaceChild5Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,elt_);
        Node ch_ = elt_.getFirstChild().getNextSibling();
        assertEq("second",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
        assertSame(prev_, ch_.getPreviousSibling());
    }
    @Test
    public void replaceChildNo1Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        elt_.replaceChild(doc_.createElement("child"),elt_.getFirstChild());
        Node ch_ = elt_.getFirstChild();
        assertEq("child",((Element)ch_).getTagName());
    }

    @Test
    public void replaceChildNo2Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node f_ = elt_.getFirstChild();
        Node next_ = f_.getNextSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,f_);
        Node ch_ = elt_.getFirstChild();
        assertEq("child",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
    }

    @Test
    public void replaceChildNo3Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node l_ = elt_.getLastChild();
        Node prev_ = l_.getPreviousSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,l_);
        Node ch_ = elt_.getLastChild();
        assertEq("child",((Element)ch_).getTagName());
        assertSame(prev_, ch_.getPreviousSibling());
    }

    @Test
    public void replaceChildNo4Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,rem_);
        Node ch_ = elt_.getFirstChild().getNextSibling();
        assertEq("child",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
        assertSame(prev_, ch_.getPreviousSibling());
    }

    @Test
    public void replaceChildNo5Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        Element new_ = doc_.createElement("child");
        elt_.replaceChild(new_,elt_);
        Node ch_ = elt_.getFirstChild().getNextSibling();
        assertEq("second",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
        assertSame(prev_, ch_.getPreviousSibling());
    }

    @Test
    public void removeChild1Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        elt_.removeChild(elt_.getFirstChild());
        Node ch_ = elt_.getFirstChild();
        assertNull(ch_);
    }

    @Test
    public void removeChild2Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node f_ = elt_.getFirstChild();
        Node next_ = f_.getNextSibling();
        elt_.removeChild(f_);
        assertNull(next_.getPreviousSibling());
    }

    @Test
    public void removeChild3Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node l_ = elt_.getLastChild();
        Node prev_ = l_.getPreviousSibling();
        elt_.removeChild(l_);
        assertNull(prev_.getNextSibling());
    }

    @Test
    public void removeChild4Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        elt_.removeChild(rem_);
        assertSame(next_, prev_.getNextSibling());
        assertSame(prev_, next_.getPreviousSibling());
    }

    @Test
    public void removeChild5Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        elt_.removeChild(elt_);
        Node ch_ = elt_.getFirstChild().getNextSibling();
        assertEq("second",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
        assertSame(prev_, ch_.getPreviousSibling());
    }
    @Test
    public void removeChildNo1Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        elt_.removeChild(elt_.getFirstChild());
        Node ch_ = elt_.getFirstChild();
        assertNull(ch_);
    }

    @Test
    public void removeChildNo2Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node f_ = elt_.getFirstChild();
        Node next_ = f_.getNextSibling();
        elt_.removeChild(f_);
        assertNull(next_.getPreviousSibling());
    }

    @Test
    public void removeChildNo3Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node l_ = elt_.getLastChild();
        Node prev_ = l_.getPreviousSibling();
        elt_.removeChild(l_);
        assertNull(prev_.getNextSibling());
    }

    @Test
    public void removeChildNo4Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        elt_.removeChild(rem_);
        assertSame(next_, prev_.getNextSibling());
        assertSame(prev_, next_.getPreviousSibling());
    }

    @Test
    public void removeChildNo5Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/><three/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Node rem_ = elt_.getFirstChild().getNextSibling();
        Node prev_ = rem_.getPreviousSibling();
        Node next_ = rem_.getNextSibling();
        elt_.removeChild(elt_);
        Node ch_ = elt_.getFirstChild().getNextSibling();
        assertEq("second",((Element)ch_).getTagName());
        assertSame(next_, ch_.getNextSibling());
        assertSame(prev_, ch_.getPreviousSibling());
    }

    @Test
    public void insertBefore1Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        elt_.insertBefore(new_,first_);
        assertSame(first_,new_.getNextSibling());
        assertSame(new_,first_.getPreviousSibling());
    }

    @Test
    public void insertBefore2Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        Node last_ = elt_.getLastChild();
        elt_.insertBefore(new_,last_);
        assertSame(last_,new_.getNextSibling());
        assertSame(new_,last_.getPreviousSibling());
        assertSame(first_,new_.getPreviousSibling());
        assertSame(new_,first_.getNextSibling());
    }

    @Test
    public void insertBefore3Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        elt_.insertBefore(new_,elt_);
        assertSame(elt_.getFirstChild(), elt_.getLastChild());
    }

    @Test
    public void insertBeforeNo1Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        elt_.insertBefore(new_,first_);
        assertSame(first_,new_.getNextSibling());
        assertSame(new_,first_.getPreviousSibling());
    }

    @Test
    public void insertBeforeNo2Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        Node last_ = elt_.getLastChild();
        elt_.insertBefore(new_,last_);
        assertSame(last_,new_.getNextSibling());
        assertSame(new_,last_.getPreviousSibling());
        assertSame(first_,new_.getPreviousSibling());
        assertSame(new_,first_.getNextSibling());
    }

    @Test
    public void insertBeforeNo3Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        elt_.insertBefore(new_,elt_);
        assertSame(elt_.getFirstChild(), elt_.getLastChild());
    }

    @Test
    public void insertAfter1Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        elt_.insertAfter(new_,first_);
        assertSame(first_,new_.getPreviousSibling());
        assertSame(new_,first_.getNextSibling());
    }

    @Test
    public void insertAfter2Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        Node last_ = elt_.getLastChild();
        elt_.insertAfter(new_,first_);
        assertSame(last_,new_.getNextSibling());
        assertSame(new_,last_.getPreviousSibling());
        assertSame(first_,new_.getPreviousSibling());
        assertSame(new_,first_.getNextSibling());
    }

    @Test
    public void insertAfter3Test() {
        Document doc_ = DocumentBuilder.parseSax("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        elt_.insertAfter(new_,elt_);
        assertSame(elt_.getFirstChild(), elt_.getLastChild());
    }

    @Test
    public void insertAfterNo1Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        elt_.insertAfter(new_,first_);
        assertSame(first_,new_.getPreviousSibling());
        assertSame(new_,first_.getNextSibling());
    }

    @Test
    public void insertAfterNo2Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/><second/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        Node first_ = elt_.getFirstChild();
        Node last_ = elt_.getLastChild();
        elt_.insertAfter(new_,first_);
        assertSame(last_,new_.getNextSibling());
        assertSame(new_,last_.getPreviousSibling());
        assertSame(first_,new_.getPreviousSibling());
        assertSame(new_,first_.getNextSibling());
    }

    @Test
    public void insertAfterNo3Test() {
        Document doc_ = DocumentBuilder.parseNoTextDocument("<tag><first/></tag>");
        Element elt_ = doc_.getDocumentElement();
        Element new_ = doc_.createElement("child");
        elt_.insertAfter(new_,elt_);
        assertSame(elt_.getFirstChild(), elt_.getLastChild());
    }

}
