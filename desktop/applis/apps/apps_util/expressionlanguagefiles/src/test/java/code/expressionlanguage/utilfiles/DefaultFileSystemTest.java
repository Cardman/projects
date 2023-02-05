package code.expressionlanguage.utilfiles;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.ContentTime;
import code.stream.core.OutputType;
import code.stream.core.ReadBinFiles;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import org.junit.Test;

public final class DefaultFileSystemTest extends EquallableElUtFilesUtil {
    @Test
    public void initTest() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        assertTrue(pr_.getFileCoreStream().newFile("/files").isDirectory());
    }
    @Test
    public void currDir() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        assertEq("/files/",f_.currentDir(g_));
    }
    @Test
    public void absol1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        assertTrue(f_.isAbsolute("/files",g_));
    }
    @Test
    public void absol2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        assertFalse(f_.isAbsolute("files",g_));
    }
    @Test
    public void absol3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        assertEq("/files",f_.absolutePath("/files",g_));
    }
    @Test
    public void absol4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/folder/");
        assertEq("/folder/files",f_.absolutePath("files",g_));
    }
    @Test
    public void chg1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        pr_.setCurrentPath("/files/");
        g_.setCurrentDir("/files/");
        f_.changeDir("/inexist",g_);
        assertEq("/files/",f_.currentDir(g_));
    }
    @Test
    public void chg2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        pr_.setCurrentPath("/files/");
        g_.setCurrentDir("/files/");
        f_.changeDir("inexist",g_);
        assertEq("/files/",f_.currentDir(g_));
    }
    @Test
    public void chg3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        pr_.getFileCoreStream().newFile("next/").mkdirs();
        f_.changeDir("next/",g_);
        assertEq("/files/next/",f_.currentDir(g_));
    }
    @Test
    public void chg4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        StreamTextFile.saveTextFile("next","",pr_.getStreams());
        f_.changeDir("next/",g_);
        assertEq("/files/",f_.currentDir(g_));
    }
    @Test
    public void dir() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        pr_.getFileCoreStream().newFile("next/").mkdirs();
        assertTrue(f_.isDirectory("next/",g_));
    }
    @Test
    public void mk1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.mkdirs("next/",g_));
        assertFalse(f_.mkdirs("next/",g_));
    }
    @Test
    public void mk2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.mkdirs("next//",g_));
    }
    @Test
    public void mk3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.mkdirs("/next/",g_));
    }
    @Test
    public void mk4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.mkdirs("/files/next/",g_));
    }
    @Test
    public void saveTxt1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","",g_));
    }
    @Test
    public void saveTxt2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_",g_));
        assertEq("_",f_.contentsOfFile("file",g_));
    }
    @Test
    public void saveTxt3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","",g_));
    }
    @Test
    public void saveBin1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.writeFile("file",new byte[0],g_));
    }
    @Test
    public void saveBin2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.writeFile("file", StringUtil.encode("_"),g_));
        assertEq("_",StringUtil.decode(f_.loadFile("file",g_).getBytes()));
    }
    @Test
    public void saveBin3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.writeFile("fi//le",new byte[0],g_));
    }
    @Test
    public void logTxt1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.logToFile("file","",g_));
    }
    @Test
    public void logTxt2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.logToFile("file","",g_));
    }
    @Test
    public void logTxt3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.logToFile("fi//le","",g_));
    }
    @Test
    public void del1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","",g_));
        assertFalse(f_.delete("file",g_));
    }
    @Test
    public void del2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_",g_));
        assertTrue(f_.delete("file",g_));
    }
    @Test
    public void del3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","",g_));
        assertFalse(f_.delete("fil//e",g_));
    }
    @Test
    public void ren1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","",g_));
        assertFalse(f_.rename("file","next",g_));
    }
    @Test
    public void ren2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_",g_));
        assertTrue(f_.rename("file","next",g_));
    }
    @Test
    public void ren3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","",g_));
        assertFalse(f_.rename("fil//e","next",g_));
    }
    @Test
    public void ren4() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("fi//le","",g_));
        assertFalse(f_.rename("file","n//ext",g_));
    }
    @Test
    public void isFile1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.25));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertFalse(f_.saveTextFile("file","",g_));
        assertFalse(f_.isFile("file",g_));
    }
    @Test
    public void isFile2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertTrue(f_.saveTextFile("file","_",g_));
        assertTrue(f_.isFile("file",g_));
    }
    @Test
    public void isFile3() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next/",g_);
        assertFalse(f_.isFile("next/",g_));
    }
    @Test
    public void parent1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        assertEq("",f_.getParentPath("/",g_));
    }
    @Test
    public void parent2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next/",g_);
        assertEq("/files",f_.getParentPath("/files/next/",g_));
    }
    @Test
    public void files1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/",g_);
        f_.saveTextFile("next2","_",g_);
        StringList fs_ = f_.getFiles("/files/", g_);
        assertEq(1, fs_.size());
        assertEq("/files/next2",fs_.get(0));
    }
    @Test
    public void files2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/",g_);
        f_.saveTextFile("next2","_",g_);
        assertEq(7,f_.lastModified("next2",g_));
        assertEq(1,f_.length("next2",g_));
        assertEq("next2",f_.getName("next2",g_));
        StringList fs_ = f_.getFiles("/files/next2", g_);
        assertNull(fs_);
    }
    @Test
    public void folders1() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/",g_);
        f_.saveTextFile("next2","_",g_);
        StringList fs_ = f_.getFolders("/files/", g_);
        assertEq(1, fs_.size());
        assertEq("/files/next1",fs_.get(0));
    }
    @Test
    public void folders2() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        f_.mkdirs("next1/",g_);
        f_.saveTextFile("next2","_",g_);
        StringList fs_ = f_.getFolders("/files/next2", g_);
        assertNull(fs_);
    }
    @Test
    public void roots() {
        CustomSeedGene law_ = new CustomSeedGene(dbs(0.75));
        MockProgramInfos pr_ = newMockProgramInfos(law_, new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultFileSystem f_ = new DefaultFileSystem(new DefaultUniformingString(), new DefaultNameValidating(new StringList()), pr_.getFileCoreStream(), pr_.getStreams());
        ExecutingOptions exec_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        exec_.setBaseFiles("/files/");
        f_.build(exec_, null);
        GuiContextEl g_ = newContext(newLgNamesGuiLight(pr_,exec_),law_);
        g_.setCurrentDir("/files/");
        pr_.setCurrentPath("/files/");
        StringList fs_ = f_.getRoots(g_);
        assertEq(1, fs_.size());
        assertEq("/",fs_.get(0));
    }
}
