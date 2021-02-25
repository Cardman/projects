package code.sml;

import code.sml.indexes.DocumentIndexer;
import org.junit.Test;

public class IndentTest extends EquallableRowColUtil {

    @Test
    public void indentWithoutTextNode1Test() {
        assertEq("<html/>", DocumentIndexer.indentWithoutTextNode("<html/>"));
    }

    @Test
    public void indentWithoutTextNode2Test() {
        assertEq("<html>\n</html>", DocumentIndexer.indentWithoutTextNode("<html></html>"));
    }

    @Test
    public void indentWithoutTextNode3Test() {
        assertEq("<html>\n\t<body/>\n</html>", DocumentIndexer.indentWithoutTextNode("<html><body/></html>"));
    }

    @Test
    public void indentWithoutTextNode4Test() {
        assertEq("<html>\n\t<head>\n\t</head>\n\t<body>\n\t</body>\n</html>", DocumentIndexer.indentWithoutTextNode("<html><head></head><body></body></html>"));
    }

    @Test
    public void indentWithoutTextNode5Test() {
        assertEq("<html>\n\t<head>\n\t\t<div/>\n\t</head>\n\t<body>\n\t\t<span/>\n\t</body>\n</html>", DocumentIndexer.indentWithoutTextNode("<html><head><div/></head><body><span/></body></html>"));
    }

    @Test
    public void indent1Test() {
        assertEq("<html/>", DocumentIndexer.indent("<html/>"));
    }

    @Test
    public void indent2Test() {
        assertEq("<html>\n</html>", DocumentIndexer.indent("<html></html>"));
    }

    @Test
    public void indent3Test() {
        assertEq("<html>\n\t<body/>\n</html>", DocumentIndexer.indent("<html><body/></html>"));
    }

    @Test
    public void indent4Test() {
        assertEq("<html>\n\t<head>\n\t</head>\n\t<body>\n\t</body>\n</html>", DocumentIndexer.indent("<html><head></head><body></body></html>"));
    }

    @Test
    public void indent5Test() {
        assertEq("<html>\n\t<head>\n\t\t<div/>\n\t</head>\n\t<body>\n\t\t<span/>\n\t</body>\n</html>", DocumentIndexer.indent("<html><head><div/></head><body><span/></body></html>"));
    }

    @Test
    public void indent6Test() {
        assertEq("<html>\n\t<head>\n\t\tContent\n\t</head>\n\t<body>\n\t\t<span/>\n\t</body>\n</html>", DocumentIndexer.indent("<html><head>Content</head><body><span/></body></html>"));
    }

}
