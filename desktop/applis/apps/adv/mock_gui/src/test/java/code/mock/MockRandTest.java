package code.mock;

import code.maths.montecarlo.*;
import org.junit.Test;

public final class MockRandTest extends EquallableMockGuiUtil {
    @Test
    public void b1() {
        assertFalse(new MockRand(new DefaultGenerator(0.25)).edit());
    }
    @Test
    public void b12() {
        assertTrue(new MockRand(new DefaultGenerator(0.75)).edit());
    }
    @Test
    public void b123() {
        assertTrue(new MockTrueRand().edit());
    }
}
