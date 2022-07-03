package code.mock;

import org.junit.Test;

public final class MockBufferedReaderTest extends EquallableMockGuiUtil {
    @Test
    public void read() {
        MockBufferedReader r_ = new MockBufferedReader();
        r_.getInstrs().add("__");
        r_.getInstrs().add("_");
        assertEq("__",r_.readLine());
        assertEq("_",r_.readLine());
        assertNull(r_.readLine());
    }
}
