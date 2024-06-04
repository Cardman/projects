package code.stream;

import code.gui.EquallableGuiFctUtil;
import code.gui.events.MockProgramInfosSecSample;
import code.maths.montecarlo.CustomSeedGene;
import code.threads.FileStruct;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class PathsUtilTest extends EquallableGuiFctUtil {

    @Test
    public void abs1() {
        MockProgramInfosSecSample pr_ = init();
        pr_.getStreams().getBinFact().writeFile("/sample.txt", StringUtil.encode("hello"));
        assertTrue(PathsUtil.abs(pr_.getFileCoreStream().newFile("/sample.txt"), pr_.getFileCoreStream()).isNul());
    }

    @Test
    public void abs2() {
        MockProgramInfosSecSample pr_ = init();
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.getStreams().getBinFact().writeFile("/tmp/sample.txt", new byte[0]);
        FileListInfo res_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/tmp"), pr_.getFileCoreStream());
        assertFalse(res_.isNul());
        assertEq(1,res_.getNames().length);
        assertEq("/tmp/sample.txt",res_.getNames()[0].getAbsolutePath());
    }
    @Test
    public void mk1() {
        MockProgramInfosSecSample pr_ = init();
        assertTrue(PathsUtil.mkdirs("/tmp/", pr_.getFileCoreStream()));
    }
    @Test
    public void mk2() {
        MockProgramInfosSecSample pr_ = init();
        assertTrue(PathsUtil.mkdirs("tmp", pr_.getFileCoreStream()));
    }
    @Test
    public void isAbsolute1() {
        MockProgramInfosSecSample pr_ = init();
        assertFalse(PathsUtil.isAbsolute("tmp", pr_.getFileCoreStream()));
    }
    @Test
    public void isAbsolute2() {
        MockProgramInfosSecSample pr_ = init();
        assertTrue(PathsUtil.isAbsolute("/tmp", pr_.getFileCoreStream()));
    }
    @Test
    public void listRootsAbPath() {
        MockProgramInfosSecSample pr_ = init();
        StringList roots_ = PathsUtil.listRootsAbPath(pr_.getFileCoreStream());
        assertEq(1,roots_.size());
        assertEq("/",roots_.get(0));
    }
    @Test
    public void getRelativeRootPath1() {
        MockProgramInfosSecSample pr_ = init("c:/");
        assertEq("tmp/",PathsUtil.getRelativeRootPath("c:/tmp/",pr_.getFileCoreStream()));
    }
    @Test
    public void getRelativeRootPath2() {
        MockProgramInfosSecSample pr_ = init("c:/");
        assertEq("d:/tmp/",PathsUtil.getRelativeRootPath("d:/tmp/",pr_.getFileCoreStream()));
    }
}
