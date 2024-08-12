package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.filenames.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.options.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.stream.BytesInfo;
import code.stream.core.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class MemoryReporterTest extends EquallableElUtUtil {
    @Test
    public void errs1() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        ResultContext res_ = ctxErrNoWarn(pr_, files_);
        String d_ = StringUtil.decode(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().exportErrs(e_, ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertTrue(d_.isEmpty());
    }
    @Test
    public void errs2() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        ResultContext res_ = ctxErrNoWarn(pr_, files_);
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().exportErrs(e_, ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs3() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        ResultContext res_ = ctxErrNoWarn(pr_, files_);
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().exportErrs(e_, null).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs4() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("folder/file.txt","{}");
        ResultContext res_ = ctxErrNoWarn(pr_, files_);
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().exportErrs(e_, ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs5() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","{}");
        files_.addEntry("src/folder/file2.txt","public class pkg.Sample{ {int i = 0 == 0 ? 5 : 6;} }");
        ResultContext re_ = ctxErr(pr_, files_, null);
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        MemoryReporter.buildError(re_.getReportedMessages(),e_,((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos(),"");
        MemoryReporter.buildWarning(re_.getReportedMessages(),e_,((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos(),"");
        String d_ = StringUtil.decode(((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().exportErrs(e_, ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void errs6() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        ResultContext re_ = ctxErr(pr_, files_, null);
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        MemoryReporter.buildError(re_.getReportedMessages(),e_,((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos(),"");
        MemoryReporter.buildWarning(re_.getReportedMessages(),e_,((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos(),"");
        String d_ = StringUtil.decode(((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().exportErrs(e_, ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void cover1() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        ResultContext res_ = ctxNoWarn(pr_, files_);
        e_.setBaseFiles("");
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getFileSystem().build(e_, new ReadBinFiles(new StringMap<ContentTime>(),new StringMap<ContentTime>(), OutputType.FOLDER));
        String d_ = StringUtil.decode(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().export(e_,((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getFileSystem(), ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertTrue(d_.isEmpty());
    }
    @Test
    public void cover2() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        e_.setBaseFiles("/");
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        ResultContext re_ = ctxNoWarn(pr_, files_);
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getFileSystem().build(e_, new ReadBinFiles(new StringMap<ContentTime>(),new StringMap<ContentTime>(), OutputType.FOLDER));
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getFileSystem().saveTextFile("file","content",((RunnableContextEl)re_.getContext()).getCurrentDir());
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().log("folder2","file2","after",(RunnableContextEl)re_.getContext());
        ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().coverFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().export(e_,((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getFileSystem(), ((LgNamesUtils)re_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void cover3() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","public class pkg.Sample{}");
        ResultContext res_ = ctxNoWarn(pr_, files_);
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        String d_ = StringUtil.decode(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().export(e_,null, null).getBytes());
        assertFalse(d_.isEmpty());
    }
    @Test
    public void init1() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, null, new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        assertEq("", StringUtil.nullToEmpty(r_.conf("")));
        r_.getFiles("");
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        assertEq("_",r_.confTxt(""));
        r_.getBinFiles("");
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = prs();
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
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        assertFalse(r_.koPaths("out_folder", e_));
        assertEq("out_folder",e_.getOutputFolder());
        assertEq("/",e_.getBaseFiles());
    }
    @Test
    public void koPaths2() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        e_.setLogFolder("logger");
        e_.setCoverFolder("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths3() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        e_.setLogFolder("log/ger");
        e_.setCoverFolder("log/ger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths4() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        e_.setLogFolder("log//ger");
        e_.setCoverFolder("log//ger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths5() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        e_.setErrorsFolder("logger");
        e_.setFiles("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths6() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        e_.setSrcFolder("logger");
        e_.setResources("logger");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths7() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        e_.setOutputZip("//");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void koPaths8() {
        MockProgramInfos pr_ = prs();
        MemoryReporter r_ = new MemoryReporter(pr_, StringUtil.encode("_"), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true), new DefaultNameValidating(new StringList()), new DefaultUniformingString());
        ExecutingOptions e_ = exOpt(pr_);
        e_.setMainThread("//");
        assertTrue(r_.koPaths("", e_));
    }
    @Test
    public void errs7() {
        MockProgramInfos pr_ = prs();
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/folder/file.txt","{}");
        files_.addEntry("src/folder/file2.txt","public class pkg.Sample{ {int i = 0 == 0 ? 5 : 6;} }");
        ResultContext res_ = ctxErr(pr_, files_, new UnitIssuer(pr_.getCompoFactory().newTextArea()));
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger().logErr("folder","errs.txt","conte");
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().errorFile(e_,"folder/file.txt","{}");
        MemoryReporter.buildError(res_.getReportedMessages(),e_,((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos(),"");
        MemoryReporter.buildWarning(res_.getReportedMessages(),e_,((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos(),"");
        String d_ = StringUtil.decode(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getReporter().exportErrs(e_, ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getInfos().getLogger()).getBytes());
        assertFalse(d_.isEmpty());
        assertTrue(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getInterceptor().newMapStringStruct().isEmpty());
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().setAliasInfoTestCurrentClass(((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().getAliasInfoTestCurrentClass());
        ((LgNamesUtils)res_.getForwards().getGenerator()).getExecContent().getCustAliases().otherAlias(((LgNamesUtils)res_.getForwards().getGenerator()).getContent(),new StringMap<String>());
    }
    @Test
    public void cdmFactory() {
        MockProgramInfos pr_ = prs();
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        assertTrue(f_.getInterceptor().newMapStringStruct().isEmpty());
        f_.getProgramInfos().getZipFact();
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getThreadFactory().sleep(0);
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getTechStreams().getTextFact().write("","",false);
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getZipFact().zipBinFiles(new StringMap<ContentTime>());
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getBinFact().writeFile("",new byte[0]);
        new TechInfos(pr_.getThreadFactory(),pr_.getStreams()).getTextFact().write("","",false);
        ExecutingOptions e_ = exOpt(pr_);
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

    private ResultContext ctxErr(MockProgramInfos _p, StringMap<String> _files, AbstractIssuer _iss) {
        update(_p);
        LgNamesUtils stds_ = newLgNamesGuiSampleGr(_p, _iss);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        opt_.setWarningShow(new WarningShow());
        opt_.getWarningShow().setTernary(true);
        return buildMockErr(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    private ResultContext ctxErrNoWarn(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesUtils stds_ = newLgNamesGuiSampleGr(_p, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        return buildMockErr(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    private ResultContext ctxNoWarn(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesUtils stds_ = newLgNamesGuiSampleGr(_p, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        opt_.setCovering(true);
        opt_.setGettingErrors(true);
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }
    public static ResultContext buildMockErr(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        ValidatorStandard.setupOverrides(page_);
        return commonMockErr(_exec, _definedLgNames, _files, page_, forwards_);
    }

    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        ValidatorStandard.setupOverrides(page_);
        return commonMock(_exec, _definedLgNames, _files, page_, forwards_);
    }
    public static LgNamesUtils newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesUtils stds_ = newLgNamesUt(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }

}
