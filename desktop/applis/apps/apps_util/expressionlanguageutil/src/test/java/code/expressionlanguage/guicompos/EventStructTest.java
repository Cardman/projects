package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.expressionlanguage.fwd.opers.AnaLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.expressionlanguage.utilimpl.*;
import code.gui.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class EventStructTest extends EquallableElUtUtil {
    @Test
    public void run1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((RunnableStruct)ev_).run();
        assertFalse(st_.isFailInit());
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
    }
    @Test
    public void run2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((RunnableStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((RunnableStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = CustAliases.newFunctional(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecutingBlocks().getRunMethod(), ctx_);
        ((RunnableFunctionalInstance)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = CustAliases.newFunctional(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecutingBlocks().getRunMethod(), ctx_);
        ((RunnableFunctionalInstance)ev_).run();
        assertFalse(st_.isFailInit());
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
    }
    @Test
    public void run6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        CustAliases.newFunctional(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecutingBlocks().getRunMethod(), ctx_);
        RunnableFunctionalInstance.callMethod( (RunnableContextEl) ctx_,NullStruct.NULL_VALUE,new CustList<Argument>());
        assertFalse(st_.isFailInit());
    }
}
