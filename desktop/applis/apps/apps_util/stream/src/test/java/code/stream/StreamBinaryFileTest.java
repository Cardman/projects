package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockBinStreamInImpl;
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
    @Test
    public void read1() {
        assertEq(1,StreamBinaryFile.next(new byte[]{'_'},1));
        assertEq(0,StreamBinaryFile.next(new byte[]{'\n'},1));
        assertEq(-1,StreamBinaryFile.next(new byte[]{},-1));
        assertEq(1,StreamBinaryFile.next(new byte[]{},0));
        assertEq(1,StreamBinaryFile.ext(new byte[]{'_'},1).length);
        assertEq(0,StreamBinaryFile.ext(new byte[]{'\n'},1).length);
        assertEq(0,StreamBinaryFile.ext(new byte[]{},-1).length);
        assertEq(0,StreamBinaryFile.ext(new byte[]{},0).length);
        assertEq('_',StreamBinaryFile.ext(new byte[]{'_'},1)[0]);
        assertEq("_\n",StringUtil.decode(StreamBinaryFile.readBytes(new MockBinStreamInImpl(StringUtil.encode("_\n"),1)).getBytes()));
    }
    @Test
    public void read2() {
        assertNull(StreamBinaryFile.readBytes(new MockBinStreamInImpl(new BytesInfo(new byte[0],true),1)));
    }
}
