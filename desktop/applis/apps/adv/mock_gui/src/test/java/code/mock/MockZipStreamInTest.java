package code.mock;

import org.junit.Test;

public final class MockZipStreamInTest extends EquallableMockGuiUtil {
    @Test
    public void t1() {
        MockZipStreamIn mockZipStreamIn_ = new MockZipStreamIn(new MockZipFact().zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("_","---")))));
        assertTrue(mockZipStreamIn_.hasNextEntry());
        assertEq("_",mockZipStreamIn_.getName());
        assertEq(0,mockZipStreamIn_.getTime());
        assertEq(3,mockZipStreamIn_.getSize());
        assertFalse(mockZipStreamIn_.isDirectory());
        assertEq(2,mockZipStreamIn_.read(new byte[0],0,0));
        assertEq(1,mockZipStreamIn_.read(new byte[0],0,0));
        assertEq(0,mockZipStreamIn_.read(new byte[0],0,0));
        byte[] readBytes_ = mockZipStreamIn_.getReadBytes();
        assertEq(3, readBytes_.length);
        mockZipStreamIn_.closeEntry();
        mockZipStreamIn_.close();
        assertFalse(mockZipStreamIn_.hasNextEntry());
    }
    @Test
    public void t2() {
        MockZipStreamIn mockZipStreamIn_ = new MockZipStreamIn(new MockZipFact().zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("_/","---")))));
        assertTrue(mockZipStreamIn_.hasNextEntry());
        assertEq("_/",mockZipStreamIn_.getName());
        assertEq(0,mockZipStreamIn_.getTime());
        assertTrue(mockZipStreamIn_.isDirectory());
        mockZipStreamIn_.closeEntry();
        mockZipStreamIn_.close();
    }
}
