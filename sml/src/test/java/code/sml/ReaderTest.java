package code.sml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static code.sml.EquallableRowColUtil.assertEq;

import org.junit.Test;

@SuppressWarnings("static-method")
public class ReaderTest {

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
        assertEq("example", elt_.getTagName());
        assertEq(0, elt_.getAttributes().size());
    }
}
