package code.mock;

import org.junit.Test;

public final class MockActionTest extends EquallableMockGuiUtil {
    @Test
    public void action() {
        MockWithActionSample a_ = new MockWithActionSample();
        new MockAction(1, a_).action();
        assertEq(1,a_.getNb());
    }
}
