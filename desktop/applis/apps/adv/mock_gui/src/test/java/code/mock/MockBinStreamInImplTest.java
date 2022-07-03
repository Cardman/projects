package code.mock;

import org.junit.Test;

public final class MockBinStreamInImplTest extends EquallableMockGuiUtil {
    @Test
    public void bytes1(){
        byte[] r_ = new byte[120];
        MockBinStreamInImpl mockBinStreamIn_ = new MockBinStreamInImpl(r_, 1024);
        assertEq(120,mockBinStreamIn_.read());
        assertEq(120,mockBinStreamIn_.getBytes().length);
    }
    @Test
    public void bytes2(){
        byte[] r_ = new byte[1025];
        MockBinStreamInImpl mockBinStreamIn_ = new MockBinStreamInImpl(r_, 1024);
        assertEq(1024,mockBinStreamIn_.read());
        assertEq(1024,mockBinStreamIn_.getBytes().length);
    }
    @Test
    public void bytes3(){
        byte[] r_ = new byte[1025];
        MockBinStreamInImpl mockBinStreamIn_ = new MockBinStreamInImpl(r_, 1024);
        mockBinStreamIn_.read();
        assertEq(1,mockBinStreamIn_.read());
        assertEq(1025,mockBinStreamIn_.getBytes().length);
    }
    @Test
    public void bytes4(){
        byte[] r_ = new byte[1024];
        MockBinStreamInImpl mockBinStreamIn_ = new MockBinStreamInImpl(r_, 1024);
        mockBinStreamIn_.read();
        assertEq(0,mockBinStreamIn_.read());
        assertEq(1024,mockBinStreamIn_.getBytes().length);
    }
    @Test
    public void bytes5(){
        byte[] r_ = new byte[1024];
        MockBinStreamInImpl mockBinStreamIn_ = new MockBinStreamInImpl(r_, 1024);
        mockBinStreamIn_.read();
        mockBinStreamIn_.read();
        assertEq(1024,mockBinStreamIn_.getBytes().length);
        mockBinStreamIn_.close();
        assertEq(0,mockBinStreamIn_.getBytes().length);
    }
    @Test
    public void bytes6(){
        MockBinStreamInImpl mockBinStreamIn_ = new MockBinStreamInImpl(null, 1024);
        assertEq(-2,mockBinStreamIn_.read());
    }
}
