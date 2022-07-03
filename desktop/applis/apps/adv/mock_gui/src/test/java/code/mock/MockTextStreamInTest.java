package code.mock;

import org.junit.Test;

public final class MockTextStreamInTest extends EquallableMockGuiUtil {
    @Test
    public void t1() {
        MockTextStreamIn mockTextStreamIn_ = new MockTextStreamIn("_");
        assertEq('_',mockTextStreamIn_.read());
    }
    @Test
    public void t2() {
        MockTextStreamIn mockTextStreamIn_ = new MockTextStreamIn("_-");
        mockTextStreamIn_.read();
        assertEq('-',mockTextStreamIn_.read());
    }
    @Test
    public void t3() {
        MockTextStreamIn mockTextStreamIn_ = new MockTextStreamIn("_-");
        mockTextStreamIn_.read();
        mockTextStreamIn_.read();
        assertEq(-1,mockTextStreamIn_.read());
        mockTextStreamIn_.close();
    }
    @Test
    public void t4() {
        MockTextStreamIn mockTextStreamIn_ = new MockTextStreamIn(null);
        assertEq(-2,mockTextStreamIn_.read());
    }
}
