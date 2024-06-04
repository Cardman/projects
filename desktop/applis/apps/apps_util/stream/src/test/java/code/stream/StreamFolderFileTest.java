package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockNameFile;
import code.mock.MockProgramInfos;
import code.mock.MockZipFact;
import code.stream.core.ContentTime;
import code.stream.core.ReadBinFiles;
import code.threads.FileStruct;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import org.junit.Test;

public final class StreamFolderFileTest extends EquallableStreamUtil {
    @Test
    public void mkdirs1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        assertFalse(StreamFolderFile.makeParent("/tmp",pr_.getFileCoreStream()));
    }
    @Test
    public void mkdirs2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        assertTrue(StreamFolderFile.makeParent("/tmp/",pr_.getFileCoreStream()));
    }
    @Test
    public void mkdirs3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        assertFalse(StreamFolderFile.makeParent("tmp",pr_.getFileCoreStream()));
    }
    @Test
    public void tempFolder() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"),"/tmp/");
        assertEq("/tmp/folder",StreamFolderFile.getTempFolder(pr_,"folder"));
        assertTrue(pr_.getFileCoreStream().newFile("/tmp/folder").isDirectory());
    }
    @Test
    public void getCurrentPath() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp");
        assertEq("/tmp/",StreamFolderFile.getCurrentPath(pr_.getFileCoreStream()));
    }
    @Test
    public void getFiles1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/one/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/two/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/one/tmp/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/one/tmp2/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/two/tmp3/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/two/tmp4/",pr_.getFileCoreStream());
        StreamTextFile.saveTextFile("/one/tmp/hello.txt","0",pr_.getStreams());
        StreamTextFile.saveTextFile("/one/tmp/hello2.txt","1",pr_.getStreams());
        StreamTextFile.saveTextFile("/one/tmp2/hello3.txt","2",pr_.getStreams());
        StreamTextFile.saveTextFile("/one/tmp2/hello4.txt","3",pr_.getStreams());
        StreamBinaryFile.writeFile("/one/tmp2/bad", NumberUtil.wrapByteArray((byte)-17,(byte)-65),pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp3/hello5.txt","4",pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp3/hello6.txt","5",pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp4/hello7.txt","6",pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp4/hello8.txt","7",pr_.getStreams());
        StringMap<String> f_ = StreamFolderFile.getFiles("/one/", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(4,f_.size());
        assertEq("0",f_.getVal("tmp/hello.txt"));
        assertEq("1",f_.getVal("tmp/hello2.txt"));
        assertEq("2",f_.getVal("tmp2/hello3.txt"));
        assertEq("3",f_.getVal("tmp2/hello4.txt"));
    }
    @Test
    public void getFiles2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamBinaryFile.writeFile("/one",pr_.getStreams().getZipFact().zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("tmp/", (byte[]) null,5), new MockNameFile("tmp/hello.txt", "0",6), new MockNameFile("tmp/hello2.txt", "1",7),new MockNameFile("tmp2/", (byte[]) null,8), new MockNameFile("tmp2/hello3.txt", "2",9), new MockNameFile("tmp2/hello4.txt", "3",10)))),pr_.getStreams());
        StringMap<String> f_ = StreamFolderFile.getFiles("/one", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(4,f_.size());
        assertEq("0",f_.getVal("tmp/hello.txt"));
        assertEq("1",f_.getVal("tmp/hello2.txt"));
        assertEq("2",f_.getVal("tmp2/hello3.txt"));
        assertEq("3",f_.getVal("tmp2/hello4.txt"));
    }
    @Test
    public void getBinFiles1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/one/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/two/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/one/tmp/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/one/tmp2/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/two/tmp3/",pr_.getFileCoreStream());
        StreamFolderFile.makeParent("/two/tmp4/",pr_.getFileCoreStream());
        StreamTextFile.saveTextFile("/one/tmp/hello.txt","0",pr_.getStreams());
        StreamTextFile.saveTextFile("/one/tmp/hello2.txt","1",pr_.getStreams());
        StreamTextFile.saveTextFile("/one/tmp2/hello3.txt","2",pr_.getStreams());
        StreamTextFile.saveTextFile("/one/tmp2/hello4.txt","3",pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp3/hello5.txt","4",pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp3/hello6.txt","5",pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp4/hello7.txt","6",pr_.getStreams());
        StreamTextFile.saveTextFile("/two/tmp4/hello8.txt","7",pr_.getStreams());
        ReadBinFiles f_ = StreamFolderFile.getBinFiles("/one/", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(4,f_.getZipFiles().size());
        assertEq("0",StringUtil.decode(f_.getZipFiles().getVal("tmp/hello.txt").getContent()));
        assertEq("1",StringUtil.decode(f_.getZipFiles().getVal("tmp/hello2.txt").getContent()));
        assertEq("2",StringUtil.decode(f_.getZipFiles().getVal("tmp2/hello3.txt").getContent()));
        assertEq("3",StringUtil.decode(f_.getZipFiles().getVal("tmp2/hello4.txt").getContent()));
        assertEq(2,f_.getZipFolders().size());
        assertTrue(StringUtil.contains(f_.getZipFolders().getKeys(),"tmp"));
        assertTrue(StringUtil.contains(f_.getZipFolders().getKeys(),"tmp2"));
    }
    @Test
    public void getBinFiles2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamBinaryFile.writeFile("/one",pr_.getStreams().getZipFact().zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("tmp/", (byte[]) null,5), new MockNameFile("tmp/hello.txt", "0",6), new MockNameFile("tmp/hello2.txt", "1",7),new MockNameFile("tmp2/", (byte[]) null,8), new MockNameFile("tmp2/hello3.txt", "2",9), new MockNameFile("tmp2/hello4.txt", "3",10)))),pr_.getStreams());
        ReadBinFiles f_ = StreamFolderFile.getBinFiles("/one", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(4,f_.getZipFiles().size());
        assertEq("0",StringUtil.decode(f_.getZipFiles().getVal("tmp/hello.txt").getContent()));
        assertEq("1",StringUtil.decode(f_.getZipFiles().getVal("tmp/hello2.txt").getContent()));
        assertEq("2",StringUtil.decode(f_.getZipFiles().getVal("tmp2/hello3.txt").getContent()));
        assertEq("3",StringUtil.decode(f_.getZipFiles().getVal("tmp2/hello4.txt").getContent()));
        assertEq(2,f_.getZipFolders().size());
        assertTrue(StringUtil.contains(f_.getZipFolders().getKeys(),"tmp"));
        assertTrue(StringUtil.contains(f_.getZipFolders().getKeys(),"tmp2"));
    }
    @Test
    public void noExt() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StringMap<ContentTime> elts_ = new StringMap<ContentTime>();
        StreamFolderFile.tryExtract(pr_.getStreams(), elts_,"","",pr_.getFileCoreStream().newFile("//"));
        assertEq(0,elts_.size());
    }
    @Test
    public void getFilesNames() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/one/",pr_.getFileCoreStream());
        StreamTextFile.saveTextFile("/one/hello.txt","",pr_.getStreams());
        pr_.setCurrentPath("/one/");
        StringList list_ = StreamFolderFile.getFilesNames(pr_.getFileCoreStream(), StringUtil.wrapStringArray("hello.txt"));
        assertEq(1,list_.size());
        assertEq("/one/hello.txt",list_.get(0));
    }
}
