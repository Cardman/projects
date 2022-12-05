package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockProgramInfos;
import code.threads.FileStruct;
import code.util.core.StringUtil;
import org.junit.Test;

public final class PathsUtilTest extends EquallableStreamUtil {

    @Test
    public void abs1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamBinaryFile.writeFile("/sample.txt", StringUtil.encode("hello"),pr_.getStreams());
        assertTrue(PathsUtil.abs(pr_.getFileCoreStream().newFile("/sample.txt"), pr_.getFileCoreStream()).isNul());
    }

    @Test
    public void abs2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        StreamBinaryFile.writeFile("/tmp/sample.txt", new byte[0],pr_.getStreams());
        FileListInfo res_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/tmp"), pr_.getFileCoreStream());
        assertFalse(res_.isNul());
        assertEq(1,res_.getNames().length);
        assertEq("/tmp/sample.txt",res_.getNames()[0].getAbsolutePath());
    }
}
