package code.formathtml.render;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.sml.DocumentBuilder;
import code.sml.DocumentResult;


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
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel search_ = (MetaPlainLabel) ch_;
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
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel search_ = (MetaPlainLabel) ch_;
        assertEq("Hello",search_.getText());
        assertEq(0, search_.getPartGroup());
        assertEq(0, search_.getRowGroup());
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaEndLine);
        ch_ = root_.getLastChild();
        assertTrue(ch_ instanceof MetaLine);
        cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        search_ = (MetaPlainLabel) ch_;
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
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel search_ = (MetaPlainLabel) ch_;
        assertEq("Hello",search_.getText());
        assertEq(0, search_.getPartGroup());
        assertEq(0, search_.getRowGroup());
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaEndLine);
        ch_ = root_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaSeparator);
        ch_ = root_.getLastChild();
        assertTrue(ch_ instanceof MetaLine);
        cont_ = (MetaContainer) ch_;
        assertEq(1, cont_.getChildren().size());
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        search_ = (MetaPlainLabel) ch_;
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
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer ol_ = (MetaContainer) ch_;
        assertEq(2, ol_.getChildren().size());
        ch_ = ol_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        MetaContainer li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        MetaNumberedLabel nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("First", lab_.getText());
        ch_ = ol_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Second", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
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
        assertEq(3, root_.getChildren().size());
        MetaComponent ch_ = root_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer cont_ = (MetaContainer) ch_;
        assertEq(0, cont_.getChildren().size());
        ch_ = root_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaLine);
        cont_ = (MetaContainer) ch_;
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer ol_ = (MetaContainer) ch_;
        assertEq(2, ol_.getChildren().size());
        ch_ = ol_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        MetaContainer li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        MetaNumberedLabel nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("First", lab_.getText());
        ch_ = ol_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Second", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
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
        assertEq(3, root_.getChildren().size());
        MetaComponent ch_ = root_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer cont_ = (MetaContainer) ch_;
        assertEq(0, cont_.getChildren().size());
        ch_ = root_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaLine);
        cont_ = (MetaContainer) ch_;
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer ol_ = (MetaContainer) ch_;
        assertEq(2, ol_.getChildren().size());
        ch_ = ol_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        MetaContainer li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        MetaNumberedLabel nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("First", lab_.getText());
        ch_ = ol_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Second", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
    }

    @Test
    public void newInstance7Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<ol>\n");
        doc_.append("<li>First\n");
        doc_.append("<ol>\n");
        doc_.append("<li>One</li>\n");
        doc_.append("<li>Two</li>\n");
        doc_.append("</ol>\n");
        doc_.append("</li>\n");
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
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer ol_ = (MetaContainer) ch_;
        assertEq(2, ol_.getChildren().size());
        ch_ = ol_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        MetaContainer li_ = (MetaContainer) ch_;
        assertEq(3, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        MetaNumberedLabel nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("First", lab_.getText());
        ch_ = li_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(3, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentNbLabel);
        ch_ = line_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer olSec_ = (MetaContainer) ch_;
        ch_ = olSec_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer lineSec_ = (MetaContainer) ch_;
        assertEq(2, lineSec_.getChildren().size());
        ch_ = lineSec_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = lineSec_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("One", lab_.getText());
        ch_ = olSec_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        lineSec_ = (MetaContainer) ch_;
        assertEq(2, lineSec_.getChildren().size());
        ch_ = lineSec_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = lineSec_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Two", lab_.getText());        
        ch_ = ol_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Second", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
    }
    @Test
    public void newInstance8Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<ol>\n");
        doc_.append("<li>First line<br/>\n");
        doc_.append("Next line\n");
        doc_.append("</li>\n");
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
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer ol_ = (MetaContainer) ch_;
        assertEq(2, ol_.getChildren().size());
        ch_ = ol_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        MetaContainer li_ = (MetaContainer) ch_;
        assertEq(2, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(3, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        MetaNumberedLabel nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = line_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("First line", lab_.getText());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaEndLine);
        ch_ = li_.getLastChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentNbLabel);
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Next line", lab_.getText());
        ch_ = ol_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Second", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
    }
    @Test
    public void newInstance9Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<ol>\n");
        doc_.append("<li>First\n");
        doc_.append("<ol>\n");
        doc_.append("<li>One</li>\n");
        doc_.append("<li>Two</li>\n");
        doc_.append("</ol>\n");
        doc_.append("End Sublist\n");
        doc_.append("</li>\n");
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
        assertEq(2, cont_.getChildren().size());
        ch_ = cont_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = cont_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer ol_ = (MetaContainer) ch_;
        assertEq(2, ol_.getChildren().size());
        ch_ = ol_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        MetaContainer li_ = (MetaContainer) ch_;
        MetaContainer liSup_ = li_;
        assertEq(3, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        MetaNumberedLabel nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("First", lab_.getText());
        ch_ = li_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(3, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaIndentNbLabel);
        ch_ = line_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaIndentLabel);
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaOrderedList);
        MetaContainer olSec_ = (MetaContainer) ch_;
        ch_ = olSec_.getFirstChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer lineSec_ = (MetaContainer) ch_;
        assertEq(2, lineSec_.getChildren().size());
        ch_ = lineSec_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("1", nb_.getNumber());
        ch_ = lineSec_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("One", lab_.getText());
        ch_ = olSec_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        lineSec_ = (MetaContainer) ch_;
        assertEq(2, lineSec_.getChildren().size());
        ch_ = lineSec_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = lineSec_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Two", lab_.getText());
        MetaComponent innerLine_ = liSup_.getLastChild();
        assertTrue(innerLine_ instanceof MetaLine);
        assertEq(2, ((MetaContainer) innerLine_).getChildren().size());
        MetaComponent ind_ = innerLine_.getFirstChild();
        assertTrue(ind_ instanceof MetaIndentNbLabel);
        innerLine_ = innerLine_.getLastChild();
        assertTrue(innerLine_ instanceof MetaPlainLabel);
        assertEq("End Sublist",((MetaPlainLabel)innerLine_).getText());
        ch_ = ol_.getLastChild();
        assertTrue(ch_ instanceof MetaListItem);
        li_ = (MetaContainer) ch_;
        assertEq(1, li_.getChildren().size());
        ch_ = li_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(2, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaNumberedLabel);
        nb_ = (MetaNumberedLabel) ch_;
        assertEq("2", nb_.getNumber());
        ch_ = line_.getLastChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Second", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
    }
    @Test
    public void newInstance10Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<table>\n");
        doc_.append("<tr><td>One</td><td>Two</td></tr>\n");
        doc_.append("<tr><td>Three</td><td>Four</td></tr>\n");
        doc_.append("</table>\n");
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
        assertTrue(ch_ instanceof MetaTable);
        MetaTable table_ = (MetaTable) ch_;
        assertEq(2, table_.getRemainders().size());
        assertEq(1, table_.getRemainders().first());
        assertEq(3, table_.getRemainders().last());
        assertEq(4, table_.getChildren().size());
        ch_ = table_.getFirstChild();
        assertTrue(ch_ instanceof MetaCell);
        MetaCell td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("One", lab_.getText());
        ch_ = table_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaCell);
        td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Two", lab_.getText());
        ch_ = table_.getChildren().get(2);
        assertTrue(ch_ instanceof MetaCell);
        td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Three", lab_.getText());
        ch_ = table_.getLastChild();
        assertTrue(ch_ instanceof MetaCell);
        td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Four", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
    }
    @Test
    public void newInstance11Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<table>\n");
        doc_.append("<caption>Title</caption>\n");
        doc_.append("<tr><td>One</td><td>Two</td></tr>\n");
        doc_.append("<tr><td>Three</td><td>Four</td></tr>\n");
        doc_.append("</table>\n");
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
        assertTrue(ch_ instanceof MetaTable);
        MetaTable table_ = (MetaTable) ch_;
        assertEq(3, table_.getRemainders().size());
        assertEq(0, table_.getRemainders().first());
        assertEq(2, table_.getRemainders().get(1));
        assertEq(4, table_.getRemainders().last());
        assertEq(5, table_.getChildren().size());
        ch_ = table_.getFirstChild();
        assertTrue(ch_ instanceof MetaCaption);
        MetaCaption caption_ = (MetaCaption) ch_;
        assertEq(1, caption_.getChildren().size());
        ch_ = caption_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        MetaPlainLabel lab_ = (MetaPlainLabel) ch_;
        assertEq("Title", lab_.getText());
        ch_ = table_.getChildren().get(1);
        assertTrue(ch_ instanceof MetaCell);
        MetaCell td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        MetaContainer line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("One", lab_.getText());
        ch_ = table_.getChildren().get(2);
        assertTrue(ch_ instanceof MetaCell);
        td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Two", lab_.getText());
        ch_ = table_.getChildren().get(3);
        assertTrue(ch_ instanceof MetaCell);
        td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Three", lab_.getText());
        ch_ = table_.getLastChild();
        assertTrue(ch_ instanceof MetaCell);
        td_ = (MetaCell) ch_;
        assertEq(1, td_.getChildren().size());
        ch_ = td_.getFirstChild();
        assertTrue(ch_ instanceof MetaLine);
        line_ = (MetaContainer) ch_;
        assertEq(1, line_.getChildren().size());
        ch_ = line_.getFirstChild();
        assertTrue(ch_ instanceof MetaPlainLabel);
        lab_ = (MetaPlainLabel) ch_;
        assertEq("Four", lab_.getText());
        ch_ = root_.getChildren().last();
        assertTrue(ch_ instanceof MetaLine);
    }
}
