package code.mock;

import org.junit.Test;

public final class MockRandTest extends EquallableMockGuiUtil {
    @Test
    public void b1() {
        assertFalse(new MockRand(new MockGenerator(dbs(0.25))).edit());
    }
    @Test
    public void b12() {
        assertTrue(new MockRand(new MockGenerator(dbs(0.75))).edit());
    }
}
