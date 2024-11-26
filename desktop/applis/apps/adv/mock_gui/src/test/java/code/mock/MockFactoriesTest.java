package code.mock;

import code.gui.images.AbstractImage;
import code.gui.initialize.AbsFrameFactory;
import code.stream.AbstractListRoot;
import code.stream.core.AbstractBinStreamIn;
import code.threads.AbstractDateFactory;
import code.threads.FileStruct;
import code.util.core.StringUtil;
import org.junit.Test;

public final class MockFactoriesTest extends EquallableMockGuiUtil {
    @Test
    public void f1() {
        AbstractImage res_ = init().getImageFactory().newImageFromBytes(wrapInts());
        assertNull(res_);
    }
    @Test
    public void f2() {
        AbstractImage res_ = init().getImageFactory().newImageFromBytes(wrapInts('1',';','0'));
        assertEq(1, res_.getHeight());
        assertEq(1, res_.getWidth());
        assertEq(0, res_.getRGB(0,0));
    }
    @Test
    public void f3() {
        AbstractImage res_ = init().getImageFactory().newImageFromBytes(wrapInts(-1));
        assertNull(res_);
    }
    @Test
    public void f4() {
        AbstractImage res_ = init().getImageFactory().newImageFromBytes(null);
        assertNull(res_);
    }
    @Test
    public void f5() {
        AbstractImage res_ = init().getImageFactory().newImageRgb(1,1);
        assertEq(1, res_.getHeight());
        assertEq(1, res_.getWidth());
        assertEq(0, res_.getRGB(0,0));
    }
    @Test
    public void f6() {
        MockProgramInfosSample pr_ = init();
        AbstractListRoot roots_ = pr_.getFileCoreStream().newFileList();
        assertEq(1, roots_.length());
        assertEq("/", roots_.path(0));
    }
    @Test
    public void f7() {
        MockProgramInfosSample pr_ = init();
        AbstractDateFactory roots_ = pr_.getThreadFactory().getDateFactory();
        assertEq(0,roots_.timeZone(0));
        assertEq("0",roots_.newDate(0).format(0,""));
        assertEq("0",roots_.newDate(0).format(roots_,""));
    }
    @Test
    public void f8() {
        MockProgramInfosSample pr_ = init();
        AbsFrameFactory roots_ = pr_.getFrameFactory();
        roots_.setCursor(pr_.getCompoFactory().newAbsolute(),0,0,new int[0]);
        pr_.getGenerator().pick();
//        assertTrue(pr_.getGeneStrCompo().isCust());
        pr_.getThreadFactory().newStartedThread(new MockRunnable());
        pr_.getThreadFactory().newStartedThread(new MockRunnable(),true);
        pr_.getThreadFactory().newThread();
        pr_.getThreadFactory().newThread(new MockRunnable());
        pr_.getThreadFactory().newThread(new MockRunnable(),true);
        assertFalse(pr_.getThreadFactory().newAtomicBoolean().get());
        assertFalse(pr_.getThreadFactory().newAtomicBoolean(false).get());
        assertEq(0,pr_.getThreadFactory().newAtomicInteger().get());
        assertEq(0,pr_.getThreadFactory().newAtomicInteger(0).get());
        assertEq(0,pr_.getThreadFactory().newAtomicLong().get());
        assertEq(0,pr_.getThreadFactory().newAtomicLong(0).get());
    }
    @Test
    public void f9() {
        AbstractImage res_ = init().getImageFactory().newImageFromBytes(wrapInts('2',';','0',';','0'));
        assertEq(1, res_.getHeight());
        assertEq(2, res_.getWidth());
        assertEq(0, res_.getRGB(0,0));
        assertEq(0, res_.getRGB(1,0));
    }
    @Test
    public void f10() {
        byte[] res_ = init().getImageFactory().decodeToImage(new int[][]{new int[]{1}});
        assertEq(3, res_.length);
    }
    @Test
    public void t1() {
        MockFileSet set_ = fileSet(0,new long[0],"/");
        set_.getFiles().put("/abc",new FileStruct(StringUtil.encode("abc"),0));
        AbstractBinStreamIn build_ = new MockBinFactory(set_, new MockTrueRand()).buildIn("abc");
        build_.read();
        build_.read();
        assertEq(3, build_.getBytes().length);
    }
    @Test
    public void t2() {
        MockFileSet set_ = fileSet(0,new long[0],"/");
        new MockBinFactory(set_, new MockTrueRand()).writeFile("abc", StringUtil.encode("abc"), false);
        assertEq("abc",StringUtil.decode(set_.getFiles().getVal("abc").getContent()));
    }
    @Test
    public void t5() {
        MockSampleFrame fr_ = new MockSampleFrame(init());
        fr_.getCommonFrame().setVisible(false);
        assertFalse(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void t6() {
        MockSampleFrame fr_ = new MockSampleFrame(init());
        fr_.getCommonFrame().setVisible(true);
        assertTrue(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void t7() {
        MockSampleFrame fr_ = new MockSampleFrame(init());
        fr_.dispatchExit();
        assertFalse(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void t8() {
        MockSampleFrame fr_ = new MockSampleFrame(init());
        fr_.init(init());
        fr_.changeLanguage("");
        assertEq("",fr_.getApplicationName());
        assertEq("",fr_.getCommonFrame().getTitle());
    }
    @Test
    public void t9() {
        MockSampleFrame fr_ = new MockSampleFrame(init());
//        fr_.setChangeable(true);
//        assertTrue(fr_.canChangeLanguage());
//        fr_.setChangeable(false);
//        assertFalse(fr_.canChangeLanguage());
        fr_.quit();
        assertFalse(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void t10() {
        MockProgramInfosSample init_ = init();
        assertEq("",init_.getLanguage());
    }
    private static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
}
