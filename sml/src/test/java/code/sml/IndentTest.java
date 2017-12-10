package code.sml;

import static code.sml.EquallableRowColUtil.assertEq;

import org.junit.Test;

@SuppressWarnings("static-method")
public class IndentTest {

    @Test
    public void indentWithoutTextNode1Test() {
        assertEq("<html/>", DocumentBuilder.indentWithoutTextNode("<html/>"));
    }

    @Test
    public void indentWithoutTextNode2Test() {
        assertEq("<html>\n</html>", DocumentBuilder.indentWithoutTextNode("<html></html>"));
    }

    @Test
    public void indentWithoutTextNode3Test() {
        assertEq("<html>\n\t<body/>\n</html>", DocumentBuilder.indentWithoutTextNode("<html><body/></html>"));
    }

    @Test
    public void indentWithoutTextNode4Test() {
        assertEq("<html>\n\t<head>\n\t</head>\n\t<body>\n\t</body>\n</html>", DocumentBuilder.indentWithoutTextNode("<html><head></head><body></body></html>"));
    }

    @Test
    public void indentWithoutTextNode5Test() {
        assertEq("<html>\n\t<head>\n\t\t<div/>\n\t</head>\n\t<body>\n\t\t<span/>\n\t</body>\n</html>", DocumentBuilder.indentWithoutTextNode("<html><head><div/></head><body><span/></body></html>"));
    }

    @Test
    public void indent1Test() {
        assertEq("<html/>", DocumentBuilder.indent("<html/>"));
    }

    @Test
    public void indent2Test() {
        assertEq("<html>\n</html>", DocumentBuilder.indent("<html></html>"));
    }

    @Test
    public void indent3Test() {
        assertEq("<html>\n\t<body/>\n</html>", DocumentBuilder.indent("<html><body/></html>"));
    }

    @Test
    public void indent4Test() {
        assertEq("<html>\n\t<head>\n\t</head>\n\t<body>\n\t</body>\n</html>", DocumentBuilder.indent("<html><head></head><body></body></html>"));
    }

    @Test
    public void indent5Test() {
        assertEq("<html>\n\t<head>\n\t\t<div/>\n\t</head>\n\t<body>\n\t\t<span/>\n\t</body>\n</html>", DocumentBuilder.indent("<html><head><div/></head><body><span/></body></html>"));
    }

    @Test
    public void indent6Test() {
        assertEq("<html>\n\t<head>\n\t\tContent\n\t</head>\n\t<body>\n\t\t<span/>\n\t</body>\n</html>", DocumentBuilder.indent("<html><head>Content</head><body><span/></body></html>"));
    }

}
