package code.mock;

import org.junit.Test;

public final class MockTextStreamInTest extends EquallableMockGuiUtil {

    @Test
    public void t5() {
        assertTrue(new MockNameValidating().ok("a.b"));
    }
    @Test
    public void t6() {
        assertTrue(new MockNameValidating().ok("a.b$"));
    }
}
