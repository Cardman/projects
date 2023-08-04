package code.expressionlanguage.utilimpl;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.gui.unit.LightTestableFrame;
import code.expressionlanguage.gui.unit.MemoryProgressingTests;
import code.expressionlanguage.gui.unit.ProgTestBar;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.MemInputFiles;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.AdvGraphicListGeneratorStruct;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockAdvGraphicListGenerator;
import code.mock.MockFileSet;
import code.mock.MockInterceptor;
import code.mock.MockProgramInfos;
import code.stream.BytesInfo;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class RunningShowProgessTest extends EquallableElUtImplUtil {

    @Test
    public void progress() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode(""), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        LgNamesGui stds_ = new LgNamesGui(infos_, new MockInterceptor());
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setLg("en");
        exec_.setLightProgramInfos(pr_);
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(messages(), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct(),mem_, bar_));
        exec_.setListGenerator(progTest_.getFactory());
        stds_.getExecContent().setExecutingOptions(exec_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"2\")) > new Sample2(new(\"1\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        AbstractAtomicBoolean a_ = pr_.getThreadFactory().newAtomicBoolean();
        progTest_.setStop(a_);

        ResultContext res_ = CustContextFactory.buildDefKw(new Options(), stds_.getExecContent().getExecutingOptions(), stds_, files_);
        ContextEl rCont_ = res_.getContext();
        String infoTest_ = stds_.getExecContent().getCustAliases().getAliasInfoTest();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(infoTest_, rCont_.getClasses());
        Struct infoStruct_ = ArgumentListCall.toStr(ProcessMethod.calculate(new CustomFoundConstructor(rCont_,className_, new Argument()),rCont_, StackCall.newInstance(InitPhase.NOTHING,rCont_)).getValue());
        ShowUpdates sh_ = new ShowUpdates(infoStruct_,(RunnableContextEl) rCont_,progTest_,stds_);
        sh_.run();
        assertEq(0, bar_.getMin());
        assertEq(0, bar_.getMax());
        assertEq(0, bar_.getCurrent());
        assertEq("0/0", bar_.getDoneTestsCount());
        assertEq("", bar_.getCurrentMethod());
        assertEq("0", bar_.calls());
    }
    @Test
    public void end() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode(""), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        LgNamesGui stds_ = new LgNamesGui(infos_, new MockInterceptor());
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setLightProgramInfos(pr_);
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(messages(), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        LightTestableFrame fram_ = new LightTestableFrame(pr_, null, new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct(), mem_, bar_);
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(fram_);
        exec_.setListGenerator(progTest_.getFactory());
        stds_.getExecContent().setExecutingOptions(exec_);
        stds_.getExecContent().updateTranslations(pr_.getTranslations(),pr_.getLanguage(),"en");
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"2\")) > new Sample2(new(\"1\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        AbstractAtomicBoolean a_ = pr_.getThreadFactory().newAtomicBoolean();
        progTest_.setStop(a_);
        ResultContext res_ = CustContextFactory.buildDefKw(new Options(), stds_.getExecContent().getExecutingOptions(), stds_, files_);
        fram_.setResults(res_.getContext(),new Argument(),stds_);
        assertEq(0, bar_.getMin());
        assertEq(0, bar_.getMax());
        assertEq(0, bar_.getCurrent());
        assertEq("", bar_.getDoneTestsCount());
        assertEq("", bar_.getCurrentMethod());
        assertEq("", bar_.calls());
    }
}
