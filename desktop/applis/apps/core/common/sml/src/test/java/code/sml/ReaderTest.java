package code.sml;

import org.junit.Test;

public class ReaderTest extends EquallableRowColUtil {

    @Test
    public void parse1Test() {
        String input_ = "<example/>";
        DocumentBuilder b_ = DocumentBuilder.newDocumentBuilder();
        DocumentResult res_ = b_.parse(input_);
        assertNull(res_.getLocation());
        Document doc_ = res_.getDocument();
        assertNotNull(doc_);
        Element elt_ = doc_.getDocumentElement();
        assertNotNull(elt_);
        assertEq("<example/>", res_.getInput());
        assertEq(0, res_.getFirstIndex());
        assertEq(0, res_.getEscaped().size());
//        assertEq(0, res_.getChs().size());
        assertEq(0, res_.getEncodes().size());
        assertEq("example", elt_.getTagName());
        assertEq(0, elt_.getAttributes().size());
    }

    @Test
    public void parse2Test() {
        String input_ = "\n<example/>";
        DocumentBuilder b_ = DocumentBuilder.newDocumentBuilder();
        DocumentResult res_ = b_.parse(input_);
        assertNull(res_.getLocation());
        Document doc_ = res_.getDocument();
        assertNotNull(doc_);
        Element elt_ = doc_.getDocumentElement();
        assertNotNull(elt_);
        assertEq("\n<example/>", res_.getInput());
        assertEq(0, res_.getEscaped().size());
//        assertEq(0, res_.getChs().size());
        assertEq(0, res_.getEncodes().size());
        assertEq("example", elt_.getTagName());
        assertEq(0, elt_.getAttributes().size());
    }
}
