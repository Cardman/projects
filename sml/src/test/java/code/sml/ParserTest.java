package code.sml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

@SuppressWarnings("static-method")
public class ParserTest {

    @Test
    public void parseSax1Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>e</tag>"));
    }

    @Test
    public void parseSax2Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>\u00E9</tag>"));
    }

    @Test
    public void parseSax3Test() {
        assertNull(DocumentBuilder.parseSax("<tag>"));
    }

    @Test
    public void parseSax4Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag/>"));
    }

    @Test
    public void parseSax5Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#233;</tag>"));
    }

    @Test
    public void parseSax6Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&eacute;</tag>"));
    }

    @Test
    public void parseSax7Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml1Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>e</tag>"));
    }

    @Test
    public void parseSaxHtml2Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>\u00E9</tag>"));
    }

    @Test
    public void parseSaxHtml3Test() {
        assertNull(DocumentBuilder.parseSax("<tag>"));
    }

    @Test
    public void parseSaxHtml4Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag/>"));
    }

    @Test
    public void parseSaxHtml5Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#233;</tag>"));
    }

    @Test
    public void parseSaxHtml6Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&eacute;</tag>"));
    }

    @Test
    public void parseSaxHtml7Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#0;</tag>"));
    }
}
