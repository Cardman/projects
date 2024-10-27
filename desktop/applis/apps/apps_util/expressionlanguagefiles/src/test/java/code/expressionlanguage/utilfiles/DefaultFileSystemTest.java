package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFalseRand;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import org.junit.Test;

public final class DefaultFileSystemTest extends EquallableElUtFilesUtil {
    @Test
    public void initTest() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        assertTrue(pr_.getFileCoreStream().newFile("/files").isDirectory());
    }
    @Test
    public void absol1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        assertTrue(f_.isAbsoluteFct("/files"));
    }
    @Test
    public void absol2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        assertFalse(f_.isAbsoluteFct("files"));
    }
    @Test
    public void absol3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        assertEq("/files",f_.absolutePath("/files","/files/"));
    }
    @Test
    public void absol4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        

        assertEq("/folder/files",f_.absolutePath("files","/folder/"));
    }
    @Test
    public void chg1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        pr_.setCurrentPath("/files/");
        
        assertEq("/files/",f_.changeDir("/inexist","/files/"));
    }
    @Test
    public void chg2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        pr_.setCurrentPath("/files/");
        
        assertEq("/files/",f_.changeDir("inexist","/files/"));
    }
    @Test
    public void chg3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        pr_.getFileCoreStream().newFile("next/").mkdirs();
        assertEq("/files/next/",f_.changeDir("next/","/files/"));
    }
    @Test
    public void chg4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        StreamTextFile.saveTextFile("next","",pr_.getStreams());
        assertEq("/files/",f_.changeDir("next/","/files/"));
    }
    @Test
    public void dir() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        pr_.getFileCoreStream().newFile("next/").mkdirs();
        assertTrue(f_.isDirectory("next/","/files/"));
    }
    @Test
    public void mk1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.mkdirs("next/","/files/"));
        assertFalse(f_.mkdirs("next/","/files/"));
    }
    @Test
    public void mk2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.mkdirs("next//","/files/"));
    }
    @Test
    public void mk3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.mkdirs("/next/","/files/"));
    }
    @Test
    public void mk4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.mkdirs("/files/next/","/files/"));
    }
    @Test
    public void saveTxt1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}),new MockFalseRand());
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","","/files/"));
    }
    @Test
    public void saveTxt2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_","/files/"));
        assertEq("_",f_.contentsOfFile("file","/files/"));
    }
    @Test
    public void saveTxt3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","","/files/"));
    }
    @Test
    public void saveBin1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}),new MockFalseRand());
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.writeFile("file",new byte[0],"/files/"));
    }
    @Test
    public void saveBin2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.writeFile("file", StringUtil.encode("_"),"/files/"));
        assertEq("_",StringUtil.decode(f_.loadFile("file","/files/").getBytes()));
    }
    @Test
    public void saveBin3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.writeFile("fi//le",new byte[0],"/files/"));
    }
    @Test
    public void logTxt1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}),new MockFalseRand());
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.logToFile("file","","/files/"));
    }
    @Test
    public void logTxt2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.logToFile("file","","/files/"));
    }
    @Test
    public void logTxt3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.logToFile("fi//le","","/files/"));
    }
    @Test
    public void del1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}),new MockFalseRand());
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","","/files/"));
        assertFalse(f_.delete("file","/files/"));
    }
    @Test
    public void del2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_","/files/"));
        assertTrue(f_.delete("file","/files/"));
    }
    @Test
    public void del3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","","/files/"));
        assertFalse(f_.delete("fil//e","/files/"));
    }
    @Test
    public void ren1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}),new MockFalseRand());
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","","/files/"));
        assertFalse(f_.rename("file","next","/files/"));
    }
    @Test
    public void ren2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_","/files/"));
        assertTrue(f_.rename("file","next","/files/"));
    }
    @Test
    public void ren3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","","/files/"));
        assertFalse(f_.rename("fil//e","next","/files/"));
    }
    @Test
    public void ren4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","","/files/"));
        assertFalse(f_.rename("file","n//ext","/files/"));
    }
    @Test
    public void isFile1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}),new MockFalseRand());
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","","/files/"));
        assertFalse(f_.isFile("file","/files/"));
    }
    @Test
    public void isFile2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_","/files/"));
        assertTrue(f_.isFile("file","/files/"));
    }
    @Test
    public void isFile3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next/","/files/");
        assertFalse(f_.isFile("next/","/files/"));
    }
    @Test
    public void parent1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        assertEq("",f_.getParentPath("/","/files/"));
    }
    @Test
    public void parent2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next/","/files/");
        assertEq("/files",f_.getParentPath("/files/next/","/files/"));
    }
    @Test
    public void files1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/","/files/");
        f_.saveTextFile("next2","_","/files/");
        StringList fs_ = f_.getFiles("/files/", "/files/");
        assertEq(1, fs_.size());
        assertEq("/files/next2",fs_.get(0));
    }
    @Test
    public void files2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/","/files/");
        f_.saveTextFile("next2","_","/files/");
        assertEq(6,f_.lastModified("next2","/files/"));
        assertEq(1,f_.length("next2","/files/"));
        assertEq("next2",f_.getName("next2","/files/"));
        StringList fs_ = f_.getFiles("/files/next2", "/files/");
        assertNull(fs_);
    }
    @Test
    public void folders1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/","/files/");
        f_.saveTextFile("next2","_","/files/");
        StringList fs_ = f_.getFolders("/files/", "/files/");
        assertEq(1, fs_.size());
        assertEq("/files/next1",fs_.get(0));
    }
    @Test
    public void folders2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/","/files/");
        f_.saveTextFile("next2","_","/files/");
        StringList fs_ = f_.getFolders("/files/next2", "/files/");
        assertNull(fs_);
    }
    @Test
    public void roots() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = exOpt(pr_);
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        
        
        pr_.setCurrentPath("/files/");
        StringList fs_ = f_.getRoots();
        assertEq(1, fs_.size());
        assertEq("/",fs_.get(0));
    }
}
