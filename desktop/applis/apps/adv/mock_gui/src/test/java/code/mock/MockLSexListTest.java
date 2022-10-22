package code.mock;

import org.junit.Test;

public final class MockLSexListTest extends EquallableMockGuiUtil {
    @Test
    public void list() {
        assertEq(1,new MockLSexList().all().size());
    }
}
