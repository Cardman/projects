package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.MemoryLogger;
import code.expressionlanguage.utilcompo.MemoryReporter;
import code.expressionlanguage.utilcompo.TechInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockAtomicBoolean;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.stream.BytesInfo;
import code.stream.StreamTextFile;
import code.stream.core.OutputType;
import code.stream.core.ReadFiles;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import org.junit.Test;

public final class DefaultReporterTest extends EquallableElUtFilesUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        assertEq("", StringUtil.nullToEmpty(r_.conf("")));
        r_.getFiles("");
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        assertEq("",r_.confTxt(""));
        r_.getBinFiles("");
    }
    @Test
    public void extract() {
        assertEq("",DefaultReporter.extract(""));
    }
    @Test
    public void folderPath1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        assertEq("/",r_.getFolderPath("",null,new ReadFiles(new StringMap<String>(), OutputType.FOLDER)));
    }
    @Test
    public void folderPath2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        assertEq("/files/",r_.getFolderPath("/files/folder",null,new ReadFiles(new StringMap<String>(), OutputType.ZIP)));
    }
    @Test
    public void koPaths1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        assertFalse(r_.koPaths("out_folder/", e_));
        assertEq("out_folder/out",e_.getOutputFolder());
        assertEq("/",e_.getBaseFiles());
    }
    @Test
    public void koPaths2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLogFolder("logger");
        e_.setCoverFolder("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLogFolder("log/ger");
        e_.setCoverFolder("log/ger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLogFolder("log//ger");
        e_.setCoverFolder("log//ger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setErrorsFolder("logger");
        e_.setFiles("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setSrcFolder("logger");
        e_.setResources("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setOutputZip("//");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setMainThread("//");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), true, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setOutputZip("__");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), false, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        assertFalse(r_.koPaths("out_folder/", e_));
        assertEq("",e_.getOutputFolder());
        assertEq("/out_folder/files/",e_.getBaseFiles());
    }
    @Test
    public void src1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), false, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        pr_.getFileCoreStream().newFile(e_.getResources()+"/").mkdirs();
        StreamTextFile.saveTextFile(e_.getResources()+"/next","_",pr_.getStreams());
        StringMap<String> src_ = r_.getSrc("", e_, new ReadFiles(new StringMap<String>(), OutputType.FOLDER));
        assertEq(1, src_.size());
        assertEq("_",src_.getVal(e_.getResources()+"/next"));
    }
    @Test
    public void src2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), false, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        StringMap<String> other_ = new StringMap<String>();
        other_.addEntry("not","_");
        other_.addEntry(e_.getSrcFolder()+"/next","_");
        StringMap<String> src_ = r_.getSrc("", e_, new ReadFiles(other_, OutputType.FOLDER));
        assertEq(1, src_.size());
        assertEq("_",src_.getVal(e_.getSrcFolder()+"/next"));
    }
    @Test
    public void src3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), false, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        StringMap<String> other_ = new StringMap<String>();
        other_.addEntry("not","_");
        other_.addEntry(e_.getResources()+"/next","_");
        StringMap<String> src_ = r_.getSrc("", e_, new ReadFiles(other_, OutputType.ZIP));
        assertEq(1, src_.size());
        assertEq("_",src_.getVal(e_.getResources()+"/next"));
    }
    @Test
    public void src4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), false, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        ExecutingOptions e_ = new ExecutingOptions();
        StringMap<String> other_ = new StringMap<String>();
        other_.addEntry("not","_");
        other_.addEntry(e_.getSrcFolder()+"/next","_");
        StringMap<String> src_ = r_.getSrc("", e_, new ReadFiles(other_, OutputType.ZIP));
        assertEq(1, src_.size());
        assertEq("_",src_.getVal(e_.getSrcFolder()+"/next"));
    }
    @Test
    public void operations() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        DefaultReporter r_ = new DefaultReporter(pr_, new DefaultNameValidating(new StringList()), new DefaultUniformingString(), false, new TechInfos(pr_.getThreadFactory(), pr_.getStreams()), pr_.getFileCoreStream());
        DefaultLogger log_ = new DefaultLogger(new UnitIssuer(pr_.getCompoFactory().newTextArea()), pr_.getFileCoreStream(), pr_.getStreams());
        log_.getIssuer().log("");
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setCoverFolder(StringUtil.replaceBackSlashDot(e_.getCoverFolder()));
        e_.setErrorsFolder(StringUtil.replaceBackSlashDot(e_.getErrorsFolder()));
        e_.setOutput("/");
        r_.errorFile(e_,"err","0");
        r_.coverFile(e_,"cov","1");
        assertEq("0",StreamTextFile.contentsOfFile(e_.getOutput()+e_.getErrorsFolder()+"err", pr_.getFileCoreStream(), pr_.getStreams()));
        assertEq("1",StreamTextFile.contentsOfFile(e_.getOutput()+e_.getCoverFolder()+"cov", pr_.getFileCoreStream(), pr_.getStreams()));
        assertTrue(r_.exportErrs(e_,null).isNul());
        assertTrue(r_.export(e_,null,null).isNul());
        log_.logErr("/other","log1","2");
        log_.log("/other","log2","3",null);
        assertEq("2",StreamTextFile.contentsOfFile("/other/log1", pr_.getFileCoreStream(), pr_.getStreams()));
        assertEq("3",StreamTextFile.contentsOfFile("/other/log2", pr_.getFileCoreStream(), pr_.getStreams()));
    }
}
