package code.expressionlanguage;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.filenames.*;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.expressionlanguage.utilimpl.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.stream.BytesInfo;
import code.stream.core.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class MemoryReporterTest extends EquallableElUtUtil {
    @Test
    public void errs1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_);
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().exportErrs(e_, stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertTrue(d_.isEmpty());
    }
    @Test
    public void errs2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_);
        stds_.getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().exportErrs(e_, stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_);
        stds_.getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().exportErrs(e_, null).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_);
        stds_.getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        stds_.getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().exportErrs(e_, stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        opt_.setWarningShow(new WarningShow());
        opt_.getWarningShow().setTernary(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","{}");
        files_.addEntry("src/folder/file2.txt","public class pkg.Sample{ {int i = 0 == 0 ? 5 : 6;} }");
        ResultContext re_ = build(opt_, e_, new AnalysisMessages(), new KeyWords(), stds_, files_);
        stds_.getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        stds_.getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        MemoryReporter.buildError(re_.getReportedMessages(),e_,stds_.getCustAliases().getInfos(),"");
        MemoryReporter.buildWarning(re_.getReportedMessages(),e_,stds_.getCustAliases().getInfos(),"");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().exportErrs(e_, stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        opt_.setWarningShow(new WarningShow());
        opt_.getWarningShow().setTernary(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        ResultContext re_ = build(opt_, e_, new AnalysisMessages(), new KeyWords(), stds_, files_);
        stds_.getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        stds_.getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        MemoryReporter.buildError(re_.getReportedMessages(),e_,stds_.getCustAliases().getInfos(),"");
        MemoryReporter.buildWarning(re_.getReportedMessages(),e_,stds_.getCustAliases().getInfos(),"");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().exportErrs(e_, stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void cover1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_);
        stds_.getCustAliases().getInfos().getFileSystem().build("",new ReadBinFiles(new StringMap<ContentTime>(),new StringMap<ContentTime>(), OutputType.FOLDER));
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().export(e_,stds_.getCustAliases().getInfos().getFileSystem(), stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertTrue(d_.isEmpty());
    }
    @Test
    public void cover2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        ResultContext re_ = build(opt_, e_, new AnalysisMessages(), new KeyWords(), stds_, files_);
        stds_.getCustAliases().getInfos().getFileSystem().build("/",new ReadBinFiles(new StringMap<ContentTime>(),new StringMap<ContentTime>(), OutputType.FOLDER));
        stds_.getCustAliases().getInfos().getFileSystem().saveTextFile("file","content",(RunnableContextEl)re_.getContext());
        stds_.getCustAliases().getInfos().getLogger().log("folder2","file2","after",(RunnableContextEl)re_.getContext());
        stds_.getCustAliases().getInfos().getReporter().coverFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().export(e_,stds_.getCustAliases().getInfos().getFileSystem(), stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void cover3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_);
        stds_.getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().export(e_,null, null).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, null, new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        assertEq("", StringUtil.nullToEmpty(r_.conf("")));
        r_.getFiles("");
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        assertEq("_",r_.confTxt(""));
        r_.getBinFiles("");
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        pr_.getStreams().getTextFact().write("file","content",false);
        assertEq("_",r_.conf(""));
        MockZipFact zipFact_ = new MockZipFact();
        BytesInfo bs_ = new BytesInfo(zipFact_.zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("__/", (byte[]) null, 5), new MockNameFile("_", "-", 6), new MockNameFile("0", wrapInts(-1), 7)))), false);
        ReadFiles zippedFiles_ = StreamZipFile.getZippedFiles(new DefaultUniformingString(),bs_,zipFact_);
        r_.getSrc("",null,zippedFiles_);
        assertEq("_",r_.getFolderPath("_",null,null));
    }
    @Test
    public void koPaths1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        assertFalse(r_.koPaths("out_folder", e_));
        assertEq("out_folder",e_.getOutputFolder());
        assertEq("/",e_.getBaseFiles());
    }
    @Test
    public void koPaths2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setLogFolder("logger");
        e_.setCoverFolder("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setLogFolder("log/ger");
        e_.setCoverFolder("log/ger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setLogFolder("log//ger");
        e_.setCoverFolder("log//ger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setErrorsFolder("logger");
        e_.setFiles("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setSrcFolder("logger");
        e_.setResources("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setOutputZip("//");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setMainThread("//");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void errs7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, new UnitIssuer(pr_.getCompoFactory().newTextArea()));
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        opt_.setWarningShow(new WarningShow());
        opt_.getWarningShow().setTernary(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","{}");
        files_.addEntry("src/folder/file2.txt","public class pkg.Sample{ {int i = 0 == 0 ? 5 : 6;} }");
        ResultContext re_ = build(opt_, e_, new AnalysisMessages(), new KeyWords(), stds_, files_);
        stds_.getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        stds_.getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        MemoryReporter.buildError(re_.getReportedMessages(),e_,stds_.getCustAliases().getInfos(),"");
        MemoryReporter.buildWarning(re_.getReportedMessages(),e_,stds_.getCustAliases().getInfos(),"");
        String d_ = StringUtil.decode(stds_.getCustAliases().getInfos().getReporter().exportErrs(e_, stds_.getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
        assertTrue(stds_.getInterceptor().newMapStringStruct().isEmpty());
        stds_.getCustAliases().setAliasInfoTestCurrentClass(stds_.getCustAliases().getAliasInfoTestCurrentClass());
        stds_.getCustAliases().allAlias(stds_.getContent(),new StringMap<String>(),new StringMap<String>());
    }
    @Test
    public void cdmFactory() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct());
        assertTrue(f_.getInterceptor().newMapStringStruct().isEmpty());
        f_.getProgramInfos().getZipFact();
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getThreadFactory().sleep(0);
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getTechStreams().getTextFact().write("","",false);
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getZipFact().zipBinFiles(new StringMap<ContentTime>());
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getBinFact().writeFile("",new byte[0]);
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getTextFact().write("","",false);
        ExecutingOptions e_ = new ExecutingOptions(new MockAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        e_.setListGenerator(f_);
        e_.setCovering(true);
        assertTrue(e_.isCovering());
        e_.setCovering(false);
        assertFalse(e_.isCovering());
        e_.setHasArg(true);
        assertTrue(e_.isHasArg());
        e_.setHasArg(false);
        assertFalse(e_.isHasArg());
        e_.setArgs(new StringList());
        assertTrue(e_.getArgs().isEmpty());
        e_.setWarns(new StringList());
        assertTrue(e_.getWarns().isEmpty());
        e_.setOutput("");
        assertTrue(e_.getOutput().isEmpty());
        e_.setMessages(new StringMap<String>());
        assertTrue(e_.getMessages().isEmpty());
        e_.setKeyWords(new StringMap<String>());
        assertTrue(e_.getKeyWords().isEmpty());
        e_.setAliases(new StringMap<String>());
        assertTrue(e_.getAliases().isEmpty());
    }
}
