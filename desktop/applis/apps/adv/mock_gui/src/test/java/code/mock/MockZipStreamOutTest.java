package code.mock;

import org.junit.Test;

public final class MockZipStreamOutTest extends EquallableMockGuiUtil {
    @Test
    public void t1() {
        MockZipStreamOut mockZipStreamOut_ = new MockZipStreamOut();
        mockZipStreamOut_.putNextEmptyEntry("a/",1);
        assertEq(13,mockZipStreamOut_.byteArray().length);
        mockZipStreamOut_.close();
    }
    @Test
    public void t2() {
        MockZipStreamOut mockZipStreamOut_ = new MockZipStreamOut();
        mockZipStreamOut_.putNextEntry("a/",1,new byte[2]);
        assertEq(15,mockZipStreamOut_.byteArray().length);
        mockZipStreamOut_.close();
    }
}
