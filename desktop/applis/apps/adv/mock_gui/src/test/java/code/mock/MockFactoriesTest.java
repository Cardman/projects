package code.mock;

import code.gui.images.AbstractImage;
import code.gui.initialize.AbsFrameFactory;
import code.stream.AbstractListRoot;
import code.stream.core.AbstractBinStreamIn;
import code.stream.core.AbstractTextStreamIn;
import code.threads.AbstractDateFactory;
import code.threads.FileStruct;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
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
        AbstractImage res_ = init().getImageFactory().newImageFromBytes(wrapInts('A','A','A','B','A','A','A','A'));
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
        assertTrue(pr_.getGeneStrCompo().isCust());
        new MockAdvGraphicListGeneratorGene<String>().createMult(pr_.getImageFactory(),null);
        new MockAdvGraphicListGeneratorGene<String>().createSimple(pr_.getImageFactory(),null);
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
    public void t1() {
        MockFileSet set_ = fileSet(0,new long[0],"/");
        set_.getFiles().put("/abc",new FileStruct(StringUtil.encode("abc"),0));
        AbstractBinStreamIn build_ = new MockBinFactory(set_).buildIn("abc");
        build_.read();
        build_.read();
        assertEq(3, build_.getBytes().length);
    }
    @Test
    public void t2() {
        MockFileSet set_ = fileSet(0,new long[0],"/");
        new MockBinFactory(set_).writeFile("abc", StringUtil.encode("abc"));
        assertEq("abc",StringUtil.decode(set_.getFiles().getVal("abc").getContent()));
    }
    @Test
    public void t3() {
        StringMap<String> f = new StringMap<String>();
        f.put("abc","abc");
        AbstractTextStreamIn build_ = new MockTextFactory(f).buildIn("abc");
        StringBuilder res_ = new StringBuilder();
        res_.append((char)build_.read());
        res_.append((char)build_.read());
        res_.append((char)build_.read());
        assertEq("abc",res_.toString());
    }
    @Test
    public void t4() {
        StringMap<String> f = new StringMap<String>();
        new MockTextFactory(f).write("abc","abc",false);
        assertEq("abc",f.getVal("abc"));
    }
    private static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
}
