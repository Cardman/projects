package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockProgramInfos;
import code.threads.FileStruct;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class StreamTextFileTest extends EquallableStreamUtil {
    @Test
    public void saveTextFile1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertFalse(StreamTextFile.saveTextFile("","",pr_.getStreams()));
    }
    @Test
    public void saveTextFile2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertTrue(StreamTextFile.saveTextFile("/tmp/hello.txt","content",pr_.getStreams()));
        assertEq("content", StringUtil.decode(StreamBinaryFile.loadFile("/tmp/hello.txt",pr_.getStreams())));
    }
    @Test
    public void logToFile1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertFalse(StreamTextFile.logToFile("","",pr_.getStreams()));
    }
    @Test
    public void logToFile2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertTrue(StreamTextFile.logToFile("/tmp/hello.txt","content",pr_.getStreams()));
        assertEq("content", StringUtil.decode(StreamBinaryFile.loadFile("/tmp/hello.txt",pr_.getStreams())));
    }
    @Test
    public void logToFile3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertTrue(StreamTextFile.saveTextFile("/tmp/hello.txt","hello_",pr_.getStreams()));
        assertTrue(StreamTextFile.logToFile("/tmp/hello.txt","_world",pr_.getStreams()));
        assertEq("hello__world", StringUtil.decode(StreamBinaryFile.loadFile("/tmp/hello.txt",pr_.getStreams())));
    }
    @Test
    public void contentsOfFile() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertTrue(StreamTextFile.saveTextFile("/tmp/hello.txt","hello_",pr_.getStreams()));
        assertTrue(StreamTextFile.logToFile("/tmp/hello.txt","_world",pr_.getStreams()));
        assertEq("hello__world",StreamTextFile.contentsOfFile("/tmp/hello.txt", pr_.getFileCoreStream(), pr_.getStreams()));
    }
    @Test
    public void contenuDocumentXmlExterne1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertTrue(StreamTextFile.saveTextFile("/tmp/hello.txt","<hello>",pr_.getStreams()));
        assertTrue(StreamTextFile.logToFile("/tmp/hello.txt","content",pr_.getStreams()));
        assertTrue(StreamTextFile.logToFile("/tmp/hello.txt","</hello>",pr_.getStreams()));
        assertEq("<hello>content</hello>",StreamTextFile.contenuDocumentXmlExterne("/tmp/hello.txt", pr_.getFileCoreStream(),pr_.getStreams()).export());
    }
    @Test
    public void contenuDocumentXmlExterne2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertTrue(StreamTextFile.saveTextFile("/tmp/hello.txt","",pr_.getStreams()));
        assertNull(StreamTextFile.contenuDocumentXmlExterne("/tmp/hello.txt", pr_.getFileCoreStream(),pr_.getStreams()));
    }
    @Test
    public void files1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/one",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp/hello.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp/hello2.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2/hello3.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2/hello4.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3/hello5.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3/hello6.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4/hello7.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4/hello8.txt",new FileStruct(StringUtil.encode(""),0));
        StringList f_ = StreamTextFile.files("/one", pr_.getFileCoreStream());
        assertEq(6, f_.size());
        assertTrue(StringUtil.contains(f_,"/tmp"));
        assertTrue(StringUtil.contains(f_,"/tmp/hello.txt"));
        assertTrue(StringUtil.contains(f_,"/tmp/hello2.txt"));
        assertTrue(StringUtil.contains(f_,"/tmp2"));
        assertTrue(StringUtil.contains(f_,"/tmp2/hello3.txt"));
        assertTrue(StringUtil.contains(f_,"/tmp2/hello4.txt"));
    }
    @Test
    public void files2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/one",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp/hello.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp/hello2.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2/hello3.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2/hello4.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3/hello5.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3/hello6.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4/hello7.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4/hello8.txt",new FileStruct(StringUtil.encode(""),0));
        StringList f_ = StreamTextFile.files("/one/", pr_.getFileCoreStream());
        assertEq(6, f_.size());
        assertTrue(StringUtil.contains(f_,"/tmp"));
        assertTrue(StringUtil.contains(f_,"/tmp/hello.txt"));
        assertTrue(StringUtil.contains(f_,"/tmp/hello2.txt"));
        assertTrue(StringUtil.contains(f_,"/tmp2"));
        assertTrue(StringUtil.contains(f_,"/tmp2/hello3.txt"));
        assertTrue(StringUtil.contains(f_,"/tmp2/hello4.txt"));
    }
    @Test
    public void allSortedFiles1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/one",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp/hello.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp/hello2.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2/hello3.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/one/tmp2/hello4.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3/hello5.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp3/hello6.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4/hello7.txt",new FileStruct(StringUtil.encode(""),0));
        pr_.getMockFileSet().getFiles().addEntry("/two/tmp4/hello8.txt",new FileStruct(StringUtil.encode(""),0));
        StringList f_ = StreamTextFile.allSortedFiles("/one", pr_.getFileCoreStream());
        assertEq(7,f_.size());
        assertEq("/one",f_.get(0));
        assertEq("/one/tmp",f_.get(1));
        assertEq("/one/tmp/hello.txt",f_.get(2));
        assertEq("/one/tmp/hello2.txt",f_.get(3));
        assertEq("/one/tmp2",f_.get(4));
        assertEq("/one/tmp2/hello3.txt",f_.get(5));
        assertEq("/one/tmp2/hello4.txt",f_.get(6));
    }
    @Test
    public void allSortedFiles2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/three",new FileStruct(null,0));
        StringList f_ = StreamTextFile.allSortedFiles("/three", pr_.getFileCoreStream());
        assertEq(1,f_.size());
        assertEq("/three",f_.get(0));
    }
}
