package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockProgramInfos;
import code.util.core.StringUtil;
import org.junit.Test;

public final class StreamBinaryFileTest extends EquallableStreamUtil {
    @Test
    public void writeFile1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.25)), fileSet(0, new long[0], "/"));
        assertFalse(StreamBinaryFile.writeFile("",new byte[0],pr_.getStreams()));
    }
    @Test
    public void writeFile2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        assertTrue(StreamBinaryFile.writeFile("/sample.txt",new byte[0],pr_.getStreams()));
    }
    @Test
    public void loadFile1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.25)), fileSet(0, new long[0], "/"));
        assertTrue(StreamBinaryFile.loadFile("",pr_.getStreams()).isNul());
    }
    @Test
    public void loadFile2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamBinaryFile.writeFile("/sample.txt", StringUtil.encode("hello"),pr_.getStreams());
        assertEq("hello",StringUtil.decode(StreamBinaryFile.loadFile("/sample.txt",pr_.getStreams()).getBytes()));
    }
}
