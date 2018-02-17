package code.formathtml.render;

import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;

@SuppressWarnings("static-method")
public final class MetaDocumentTest {

    @Test
    public void newInstance1Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        MetaBlock root_ = out_.getRoot();
        assertEq(1, root_.getChildren().size());
        MetaComponent ch_ = root_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaSearchableLabel);
        MetaSearchableLabel search_ = (MetaSearchableLabel) ch_;
        assertEq("Hello World",search_.getText());
        assertEq(0, search_.getPartGroup());
        assertEq(0, search_.getRowGroup());
    }

    @Test
    public void newInstance2Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<br/>\n");
        doc_.append("World\n");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        MetaBlock root_ = out_.getRoot();
        assertEq(2, root_.getChildren().size());
        MetaComponent ch_ = root_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaSearchableLabel);
        MetaSearchableLabel search_ = (MetaSearchableLabel) ch_;
        assertEq("Hello",search_.getText());
        assertEq(0, search_.getPartGroup());
        assertEq(0, search_.getRowGroup());
        ch_ = root_.getLastChild();
        assertTrue(ch_ instanceof MetaLine);
        cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaSearchableLabel);
        search_ = (MetaSearchableLabel) ch_;
        assertEq("World",search_.getText());
        assertEq(0, search_.getPartGroup());
        assertEq(1, search_.getRowGroup());
    }

    @Test
    public void newInstance3Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<hr/>\n");
        doc_.append("World\n");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        MetaBlock root_ = out_.getRoot();
        assertEq(3, root_.getChildren().size());
        MetaComponent ch_ = root_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaSearchableLabel);
        MetaSearchableLabel search_ = (MetaSearchableLabel) ch_;
        assertEq("Hello",search_.getText());
        assertEq(0, search_.getPartGroup());
        assertEq(0, search_.getRowGroup());
        ch_ = root_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaSeparator);
        ch_ = root_.getLastChild();
        assertTrue(ch_ instanceof MetaLine);
        cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaSearchableLabel);
        search_ = (MetaSearchableLabel) ch_;
        assertEq("World",search_.getText());
        assertEq(1, search_.getPartGroup());
        assertEq(0, search_.getRowGroup());
    }
    @Test
    public void newInstance4Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<ol>\n");
        doc_.append("<li>First</li>\n");
        doc_.append("<li>Second</li>\n");
        doc_.append("</ol>\n");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        MetaBlock root_ = out_.getRoot();
        assertEq(3, root_.getChildren().size());
        MetaComponent ch_ = root_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer cont_ = (MetaContainer) ch_;
        assertEq(0, cont_.getChildren().size());
        ch_ = root_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaLine);
        cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        cont_ = (MetaContainer) ch_;
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        MetaContainer li_ = (MetaContainer) ch_;
    }
    @Test
    public void newInstance5Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<ol>\n");
        doc_.append("<li>First</li>\n");
        doc_.append("<li>Second</li>");
        doc_.append("</ol>\n");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        MetaBlock root_ = out_.getRoot();
    }
    @Test
    public void newInstance6Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<ol>\n");
        doc_.append("<li>First</li>");
        doc_.append("<li>Second</li>\n");
        doc_.append("</ol>\n");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        MetaBlock root_ = out_.getRoot();
    }
}
