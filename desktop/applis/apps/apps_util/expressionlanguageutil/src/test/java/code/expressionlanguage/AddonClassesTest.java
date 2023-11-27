package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class AddonClassesTest extends EquallableElUtUtil {
    @Test
    public void executorServiceStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        stds_.getExecContent().getCustAliases().setAliasExecutorService("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasExecutorService(),new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean()).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void futureStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean(),2);
        ArgumentListCall list_ = one(new MockRunnableStruct(""));
        stds_.getExecContent().getCustAliases().setAliasFuture("");
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertEq(stds_.getExecContent().getCustAliases().getAliasFuture(),call(new FctExecutorServiceSubmit0(""),null,ctx_,essOne_, list_,st_).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void strMap() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        stds_.getExecContent().getCustAliases().setAliasTableStringObject("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasTableStringObject(),call(new FctTastr(new MockInterceptor()),null,null,null, null,null).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void key() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        stds_.getExecContent().getCustAliases().setAliasEntryStringObject("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasEntryStringObject(),entry_.getClassName(ctx_));
    }
    @Test
    public void scheduledExecutorServiceStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        stds_.getExecContent().getCustAliases().setAliasScheduledExecutorService("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasScheduledExecutorService(),new ScheduledExecutorServiceStruct(pr_.getThreadFactory()).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void thread() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasThread("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasThread(),call(new FctThread(stds_.getExecContent().getCustAliases(), ""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void threadSet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasThreadSet("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasThreadSet(),call(new FctThreadSet(new MockInterceptor()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void entryText() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasEntryText("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasEntryText(),call(new FctEntryText(),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void entryBinary() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasEntryBinary("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasEntryBinary(),call(new FctEntryBinary(),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void color() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasColor("");
        assertEq(stds_.getGuiAliases().getAliasColor(),call(new FctColor0(),null,ctx_,null,one(new IntStruct(0)),st_).getClassName(ctx_));
    }
    @Test
    public void font() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasFont("");
        assertEq(stds_.getGuiAliases().getAliasFont(),call(new FctFont0(),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void image() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasImage("");
        assertEq(stds_.getGuiAliases().getAliasImage(),call(new FctImage(stds_.getGuiExecutingBlocks()),null,ctx_,null,three(new IntStruct(1),new IntStruct(1),BooleanStruct.of(false)),st_).getClassName(ctx_));
    }
    @Test
    public void compo() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertEq("_",call(new FctImageLabel0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),"_"),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void render() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasRender("");
        assertEq(stds_.getGuiAliases().getAliasRender(),call(new FctRender(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void dims() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasDimension("");
        assertEq(stds_.getGuiAliases().getAliasDimension(),call(new FctDimension1(),null,ctx_,null,two(new IntStruct(1),new IntStruct(1)),st_).getClassName(ctx_));
    }
    @Test
    public void treeNode() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasTreeNode("");
        assertEq(stds_.getGuiAliases().getAliasTreeNode(),call(new FctTreeNode0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void buttonGroup() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasButtonGroup("");
        assertEq(stds_.getGuiAliases().getAliasButtonGroup(),call(new FctButtonGroup(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenuItem("");
        assertEq(stds_.getGuiAliases().getAliasMenuItem(),call(new FctMenuItem0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenuItemCheck("");
        assertEq(stds_.getGuiAliases().getAliasMenuItemCheck(),call(new FctMenuItemCheck0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenu("");
        assertEq(stds_.getGuiAliases().getAliasMenu(),call(new FctMenu0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenuBar("");
        assertEq(stds_.getGuiAliases().getAliasMenuBar(),call(new FctMenuBar(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void window1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasDialog("");
        assertEq(stds_.getGuiAliases().getAliasDialog(),call(new FctDialog(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void window2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasFrame("");
        assertEq(stds_.getGuiAliases().getAliasFrame(),call(new FctFrame(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void windowSet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasWindowSet("");
        assertEq(stds_.getGuiAliases().getAliasWindowSet(),call(new FctWindowSet(stds_.getExecContent().getCustAliases()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void callable1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static boolean run(){var g = new ExecutorService();var b=g.submit(null)==null;return b;}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertTrue(combo_);
    }
    @Test
    public void callable2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();var f = g.submit((Callable<String>)(:String)->\"RESULT\");return (String)f.wait();}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("RESULT",combo_);
    }
    @Test
    public void call() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Callable<String>{public String call(){return \"RESULT\";}}");
        ContextEl ctx_ = ctx(pr_, files_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        assertEq("RESULT",((EventStruct)ev_).call());
    }
    @Test
    public void callableCancel() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();var f = g.submit((Callable<String>)(:String)->\"RESULT\");f.cancel();return (String)f.wait();}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("RESULT",combo_);
    }
    @Test
    public void launchDbg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();var f = g.submit((Callable<String>)(:String)->\"RESULT\");return (String)f.wait();}}");
        ResultContext ctx_ = ctxRes(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 127);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(127,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(154,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
        assertEq("RESULT",dbg_.getStack().getReturnedArgument());
    }
    @Test
    public void launchDbg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();var f = g.submit((Callable<String>)(:String)->\"RESULT\");f.wait();return (String)f.wait();}}");
        ResultContext ctx_ = ctxRes(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 127);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(127,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(139,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
        assertEq("RESULT",dbg_.getStack().getReturnedArgument());
    }
    @Test
    public void launchDbg3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();var f = g.submit((Callable<String>)(:String)->\"RESULT\");f.cancel();return (String)f.wait();}}");
        ResultContext ctx_ = ctxRes(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 127);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void launchDbg4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();var f = g.submit((Callable<String>)(:String)->\"RESULT\");String r=(String)f.wait();f.cancel();return r;}}");
        ResultContext ctx_ = ctxRes(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 127);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(127,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(156,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
        assertEq("RESULT",dbg_.getStack().getReturnedArgument());
    }
    @Test
    public void launchDbg5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();var f = g.submit((Callable<String>)(:String)->\"RESULT\");g.submit(null);f.cancel();f.cancel();return (String)f.wait();}}");
        ResultContext ctx_ = ctxRes(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 127);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void launchDbg6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ExecutorService();g.shutdown();var f = g.submit((Callable<String>)(:String)->\"RESULT\");f.cancel();f.cancel();return (String)f.wait();}}");
        ResultContext ctx_ = ctxRes(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 140);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void launchDbgRun1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();var f = g.submit((Runnable)(:void)->{g;});f.wait();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 116);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(116,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(123,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void launchDbgRun2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();var f = g.submit((Runnable)(:void)->{g;});f.wait();f.wait();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 116);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(116,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(123,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void launchDbgRun3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();var f = g.submit((Runnable)(:void)->{g;});f.cancel();f.wait();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 116);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void launchDbgRun4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();var f = g.submit((Runnable)(:void)->{g;});f.wait();f.cancel();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 116);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(116,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(123,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void launchDbgRun5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();var f = g.submit((Runnable)(:void)->{g;});f.cancel();f.cancel();f.wait();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 116);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void launchDbgRun6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();g.shutdown();var f = g.submit((Runnable)(:void)->{g;});f.cancel();f.cancel();f.wait();}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 129);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void launchDbgExec1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();g.execute((Runnable)(:void)->{g;});g.execute(null);}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 109);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(109,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(81,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void launchDbgExec2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService();g.shutdown();g.execute((Runnable)(:void)->{g;});g.execute(null);}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 122);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void launchDbgExec3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){var g = new ExecutorService(2);g.shutdown();g.execute((Runnable)(:void)->{g;});g.execute(null);}}");
        ResultContext ctx_ = ctxResRun(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 123);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    private StackCallReturnValue launchDbg(ResultContext _ctx) {
        ExecRootBlock ex_ = _ctx.getContext().getClasses().getClassBody("pkg.Sample");
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ExecClassesUtil.tryInitStaticlyTypes(_ctx.getContext(), _ctx.getForwards().getOptions(), null, new CustomFoundMethod(form_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), new Parameters()), StepDbgActionEnum.DEBUG, false);
    }

    protected static StackCallReturnValue dbgContinueNormalValue(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP, false);
    }
    private ResultContext ctxRes(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockDbg(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }
    private ResultContext ctxResRun(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockDbgRun(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    private Struct ctxStr(MockProgramInfos _pr, StringMap<String> _p) {
        ContextEl ctx_ = ctx(_pr,_p);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ArgumentListCall.toStr(EventStruct.invoke(NullStruct.NULL_VALUE, ctx_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), resSt_, new ArgumentListCall()));
    }
    private ContextEl ctx(MockProgramInfos _p) {
        return ctx(_p,new StringMap<String>());
    }
    private ContextEl ctx(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }

    public static ResultContext buildMockDbg(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().callableType(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        _definedLgNames.getExecContent().getCustAliases().future(_definedLgNames.getContent());

        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass service_ = new StandardClass(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorService(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfExecutorService(new MockInterceptor(), _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory(), ""));
        service_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());

        StringList params_ = new StringList(_definedLgNames.getExecContent().getCustAliases().getAliasCallable() + "<?>");
        StandardMethod method_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorServiceSubmit(), params_, _definedLgNames.getExecContent().getCustAliases().getAliasFuture(), false, MethodModifier.FINAL,new StringList("a"),new FctExecutorServiceSubmit1(new MockInterceptor(), ""));
        StandardNamedFunction.addFct(methods_, method_);
        StandardMethod method2_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorServiceShutdown(), new StringList(), _definedLgNames.getExecContent().getCustAliases().getAliasFuture(), false, MethodModifier.FINAL, new FctExecutorServiceShutdown(""));
        StandardNamedFunction.addFct(methods_, method2_);

        StandardConstructor ctor_ = new StandardConstructor(new StringList(),false,new FctExecutorService0(new MockInterceptor(), _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory(), ""));
        StandardNamedFunction.addFct(constructors_, ctor_);
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getExecContent().getCustAliases().getAliasExecutorService(), service_);

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMockDbg(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getExecContent().getExecutingBlocks().callable(_definedLgNames.getExecContent().getCustAliases(),res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static ResultContext buildMockDbgRun(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().runnableType(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        _definedLgNames.getExecContent().getCustAliases().future(_definedLgNames.getContent());

        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass service_ = new StandardClass(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorService(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfExecutorService(new MockInterceptor(), _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory(), ""));
        service_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());

        StringList params_ = new StringList(_definedLgNames.getExecContent().getCustAliases().getAliasRunnable());
        StandardMethod method_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorServiceSubmit(), params_, _definedLgNames.getExecContent().getCustAliases().getAliasFuture(), false, MethodModifier.FINAL,new StringList("a"),new FctExecutorServiceSubmit0(""));
        StandardNamedFunction.addFct(methods_, method_);
        StandardMethod method2_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorServiceExecute(), params_, _definedLgNames.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList("a"),new FctExecutorServiceExecute0(""));
        StandardNamedFunction.addFct(methods_, method2_);
        StandardMethod method3_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorServiceShutdown(), new StringList(), _definedLgNames.getExecContent().getCustAliases().getAliasFuture(), false, MethodModifier.FINAL, new FctExecutorServiceShutdown(""));
        StandardNamedFunction.addFct(methods_, method3_);

        StandardConstructor ctor_ = new StandardConstructor(new StringList(),false,new FctExecutorService0(new MockInterceptor(), _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory(), ""));
        StandardNamedFunction.addFct(constructors_, ctor_);
        StandardConstructor ctor1_ = new StandardConstructor(new StringList(_definedLgNames.getPrimTypes().getAliasPrimInteger()),false,new StringList("a"),new FctExecutorService1(new MockInterceptor(), _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory(), ""));
        StandardNamedFunction.addFct(constructors_, ctor1_);
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getExecContent().getCustAliases().getAliasExecutorService(), service_);

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMockDbg(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getExecContent().getExecutingBlocks().runnable(_definedLgNames.getExecContent().getCustAliases(),res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }
    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().callableType(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        _definedLgNames.getExecContent().getCustAliases().future(_definedLgNames.getContent());

        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass service_ = new StandardClass(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorService(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfExecutorService(new MockInterceptor(), _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory(), ""));
        service_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());

        StringList params_ = new StringList(_definedLgNames.getExecContent().getCustAliases().getAliasCallable() + "<?>");
        StandardMethod method_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasExecutorServiceSubmit(), params_, _definedLgNames.getExecContent().getCustAliases().getAliasFuture(), false, MethodModifier.FINAL,new StringList("a"),new FctExecutorServiceSubmit1(new MockInterceptor(), ""));
        StandardNamedFunction.addFct(methods_, method_);

        StandardConstructor ctor_ = new StandardConstructor(new StringList(),false,new FctExecutorService0(new MockInterceptor(), _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory(), ""));
        StandardNamedFunction.addFct(constructors_, ctor_);
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getExecContent().getCustAliases().getAliasExecutorService(), service_);


//        _definedLgNames.getGuiAliases().color(_definedLgNames.getContent());
//        _definedLgNames.getGuiAliases().image(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());
//        _definedLgNames.getGuiAliases().font(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());
        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
//        stds_.getGuiExecutingBlocks().cellRender(stds_.getGuiAliases(), stds_.getContent(),res_.getContext().getClasses());
        stds_.getExecContent().getExecutingBlocks().callable(_definedLgNames.getExecContent().getCustAliases(),res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
}
