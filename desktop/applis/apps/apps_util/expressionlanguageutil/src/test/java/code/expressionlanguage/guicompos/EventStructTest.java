package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.FctInterrupt;
import code.expressionlanguage.utilcompo.stds.FctThreadCurrent;
import code.expressionlanguage.utilcompo.stds.FctThreadEnd;
import code.expressionlanguage.utilcompo.stds.FctThreadPrint0;
import code.expressionlanguage.utilimpl.*;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EventStructTest extends EquallableElUtUtil {
    @Test
    public void run1() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){}}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
    }
    @Test
    public void run2() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run3() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;}}");
        ContextEl ctx_ =ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run4() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(EventStruct.invoke(NullStruct.NULL_VALUE, (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = ctx_.getStandards().newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,((LgNamesGui)ctx_.getStandards()).getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run5() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(EventStruct.invoke(NullStruct.NULL_VALUE, (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = ctx_.getStandards().newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,((LgNamesGui)ctx_.getStandards()).getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
    }
    @Test
    public void run6() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(EventStruct.invoke(NullStruct.NULL_VALUE, (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        LgNamesGui.newGuiFunctionnal(ctx_, new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_, ((LgNamesGui)ctx_.getStandards()).getExecContent().getExecutingBlocks().getRunMethod());
//        EventStruct.callMethod( (RunnableContextEl) ctx_,NullStruct.NULL_VALUE,new CustList<Argument>());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run7() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public enum pkg.Sample:Runnable{ONE;public int i=2;(){i=i;name();ordinal();ObjectsUtil.getParent(this);ObjectsUtil.setParent(this,this);}public void run(){}}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "ONE", 0);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
    }
    @Test
    public void run8() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){Thread.interrupt();}}");
        ContextEl ctx_ = ctxThreadRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run9() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){Thread.currentThread().end();}}");
        ContextEl ctx_ = ctxThreadRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run10() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){} public static Inner fct(){Thread.print(\"\");return (Inner)new Sample().$lambda(Runnable,run);}static interface Inner{void fct();}}");
        files_.addEntry("src/base.txt","public class $core.InfoTest{public int nbThreads;}public class $core.Result{}public class $core.ExecutedTest{}public class $core.Table<K,V>{}public class $core.List<E>{}public class $core.Execute{public static Table<Class,List<ExecutedTest>> groupClass(InfoTest i){return null;}public static Object groupClassMethod(InfoTest i,Table<Class,List<ExecutedTest>> j){return null;} public static Object tests(InfoTest i,Table<Class,Table<Method,Result>> j){return null;}}");
        ContextEl ctx_ = ctxThreadRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(EventStruct.invoke(NullStruct.NULL_VALUE, ctx_, et_, st_, new ArgumentListCall()));
        assertEq("pkg.Sample..Inner",lda_.getClassName(ctx_));
        assertFalse(st_.isFailInit());
        LgNamesUtilsContent execCont_ = ((LgNamesGui) ctx_.getStandards()).getExecContent();
        execCont_.getExecutingBlocks().groupClassTests(execCont_.getCustAliases(), ctx_.getClasses());
        execCont_.getExecutingBlocks().groupClassMethodTests(ctx_.getStandards().getContent(),execCont_.getCustAliases(), ctx_.getClasses());
        execCont_.getExecutingBlocks().exec(ctx_.getStandards().getContent(),execCont_.getCustAliases(), ctx_.getClasses());
        Struct it_ = execCont_.getExecutingBlocks().infoTests(ctx_, 2);
        Struct gr_ = execCont_.getExecutingBlocks().groupClass(it_, ctx_);
        Struct grMe_ = execCont_.getExecutingBlocks().groupClassMethod(it_, gr_, ctx_);
        Struct res_ = execCont_.getExecutingBlocks().executeTests(it_, grMe_, ctx_);
        assertSame(NullStruct.NULL_VALUE,res_);
    }
    @Test
    public void run11() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Outer{public class Sample:Runnable{public int i=2;(){i=i;ObjectsUtil.getParent(this);}public void run(){ObjectsUtil.setParent(this,Outer.this);}}}");
        ContextEl ctx_ = ctxRunnableUt(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex1_ = ctx_.getClasses().getClassBody("pkg.Outer");
        ExecRootBlock ex2_ = ctx_.getClasses().getClassBody("pkg.Outer..Sample");
        Struct ev1_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex1_), "", -1);
        Struct ev2_ = ctx_.getInit().processInit(ctx_, ev1_, new ExecFormattedRootBlock(ex2_), "", -1);
        ((EventStruct)ev2_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt1() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt2() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxAction(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).action(new KeyActionEvent(0),"");
        assertFalse(st_.isFailInit());
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
        ev_.randCode();
    }
    @Test
    public void evt3() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public enum pkg.Sample:Runnable{ONE;public int i=2;(){i=i;name();ordinal();ObjectsUtil.getParent(this);ObjectsUtil.setParent(this,this);} public void run(){}}");
        files_.addEntry("src/0","public interface pkg.Runnable{public void run();}");
        ContextEl ctx_ = ctxAction(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).action(new KeyActionEvent(0),"");
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt4() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxAllInts(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).mouseClicked(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(false,false,false,0));
        ((EventStruct)ev_).mouseEntered(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseExited(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseMoved(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mousePressed(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseDragged(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseReleased(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseWheelMoved(new CoreMouseLocation(0, 0), new KeyActionEvent(0), new CoreMouseButtons(true,true,true,1),new MockMouseWheel(1));
        ((EventStruct)ev_).keyPressed(new KeyActionEvent(0),'0',0);
        ((EventStruct)ev_).keyReleased(new KeyActionEvent(0),'0',0);
        ((EventStruct)ev_).keyTyped(new KeyActionEvent(0),'0');
        ((EventStruct)ev_).valueChanged(new MockMutableTreeNode(""));
        ((EventStruct)ev_).valueChanged(0,0);
        ((EventStruct)ev_).valueChanged(new SelectionInfo(0,0,true));
        ((EventStruct)ev_).stateChanged();
        ((EventStruct)ev_).focusGained();
        ((EventStruct)ev_).focusLost();
        ((EventStruct)ev_).windowActivated();
        ((EventStruct)ev_).windowClosed();
        ((EventStruct)ev_).windowClosing();
        ((EventStruct)ev_).windowDeactivated();
        ((EventStruct)ev_).windowDeiconified();
        ((EventStruct)ev_).windowIconified();
        ((EventStruct)ev_).windowOpened();
        assertFalse(st_.isFailInit());
        ((LgNamesGui)ctx_.getStandards()).getGuiExecutingBlocks().getEventClose().windowClosing();
    }
    @Test
    public void evt5() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Outer{public class Sample:Runnable{public int i=2;(){i=i;ObjectsUtil.getParent(this);}public void run(){ObjectsUtil.setParent(this,Outer.this);}}}");
        ContextEl ctx_ = ctxRunnable(pr_,files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex1_ = ctx_.getClasses().getClassBody("pkg.Outer");
        ExecRootBlock ex2_ = ctx_.getClasses().getClassBody("pkg.Outer..Sample");
        Struct ev1_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex1_), "", -1);
        Struct ev2_ = ctx_.getInit().processInit(ctx_, ev1_, new ExecFormattedRootBlock(ex2_), "", -1);
        ((EventStruct)ev2_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt6() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = exOpt(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = ctx(pr_,files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(EventStruct.invoke(NullStruct.NULL_VALUE, (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = stds_.newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
        assertFalse(st_.isFailInit());
        stds_.getGuiExecutingBlocks().getPairPaintMethod();
        stds_.getGuiExecutingBlocks().getMainArgs();
    }
    @Test
    public void evt7() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(EventStruct.invoke(NullStruct.NULL_VALUE, (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = ctx_.getStandards().newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,((LgNamesGui)ctx_.getStandards()).getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
//    @Test
//    public void paint1() {
//        MockProgramInfos pr_ = prs();
//        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
//        Options opt_ = new Options();
//        opt_.setCovering(true);
//        ExecutingOptions e_ = exOpt();
//        e_.setLightProgramInfos(pr_);
//        StringMap<String> files_ = new StringMap<String>();
//        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
//        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
//        StackCall st_ = stack(ctx_);
//        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
//        Struct lab_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(img_), st_);
//        CustList<Argument> args_ = new CustList<Argument>();
//        args_.add(new Argument(new StringStruct("")));
//        args_.add(new Argument(new IntStruct(0)));
//        args_.add(new Argument(lab_));
//        args_.add(new Argument(BooleanStruct.of(true)));
//        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
//        assertFalse(st_.isFailInit());
//    }
//
//    @Test
//    public void paint2() {
//        MockProgramInfos pr_ = prs();
//        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
//        Options opt_ = new Options();
//        opt_.setCovering(true);
//        ExecutingOptions e_ = exOpt();
//        e_.setLightProgramInfos(pr_);
//        StringMap<String> files_ = new StringMap<String>();
//        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
//        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
//        StackCall st_ = stack(ctx_);
//        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
//        Struct lab_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(img_), st_);
//        CustList<Argument> args_ = new CustList<Argument>();
//        args_.add(new Argument(new StringStruct("")));
//        args_.add(new Argument(new IntStruct(0)));
//        args_.add(new Argument(lab_));
//        args_.add(new Argument(BooleanStruct.of(false)));
//        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
//        assertFalse(st_.isFailInit());
//    }
//    @Test
//    public void paint3() {
//        MockProgramInfos pr_ = prs();
//        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
//        Options opt_ = new Options();
//        opt_.setCovering(true);
//        ExecutingOptions e_ = exOpt();
//        e_.setLightProgramInfos(pr_);
//        StringMap<String> files_ = new StringMap<String>();
//        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
//        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
//        StackCall st_ = stack(ctx_);
//        CustList<Argument> args_ = new CustList<Argument>();
//        args_.add(new Argument(new StringStruct("")));
//        args_.add(new Argument(new IntStruct(0)));
//        args_.add(new Argument(NullStruct.NULL_VALUE));
//        args_.add(new Argument(BooleanStruct.of(true)));
//        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
//        assertFalse(st_.isFailInit());
//    }
//    @Test
//    public void paint4() {
//        MockProgramInfos pr_ = prs();
//        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
//        Options opt_ = new Options();
//        opt_.setCovering(true);
//        ExecutingOptions e_ = exOpt();
//        e_.setLightProgramInfos(pr_);
//        StringMap<String> files_ = new StringMap<String>();
//        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
//        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
//        StackCall st_ = stack(ctx_);
//        CustList<Argument> args_ = new CustList<Argument>();
//        args_.add(new Argument(new StringStruct("")));
//        args_.add(new Argument(new IntStruct(0)));
//        args_.add(new Argument(NullStruct.NULL_VALUE));
//        args_.add(new Argument(BooleanStruct.of(false)));
//        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
//        assertFalse(st_.isFailInit());
//    }
//    @Test
//    public void convert() {
//        MockProgramInfos pr_ = prs();
//        LgNamesGui stds_ = newLgNamesGuiSampleFull(pr_, null);
//        Options opt_ = new Options();
//        opt_.setCovering(true);
//        ExecutingOptions e_ = exOpt();
//        e_.setLightProgramInfos(pr_);
//        StringMap<String> files_ = new StringMap<String>();
//        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
//        assertEq("",new DefSpecSelectionCtx(((RunnableContextEl)ctx_).getInterrupt(),ctx_.getExecutionInfos(),new StringList()).convertStr(new StringStruct("")));
//    }
    @Test
    public void actWrap1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctActionWrap("", stds_.getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        assertSame(NullStruct.NULL_VALUE,e_);
    }
    @Test
    public void actWrap2() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        Struct ae_ = call(new FctActionWrap("", ((LgNamesGui)ctx_.getStandards()).getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(ev_), st_);
        assertSame(ev_,call(new FctActionArg(),null,ctx_,ae_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        Struct ae_ = call(new FctActionWrap("", ((LgNamesGui)ctx_.getStandards()).getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(ev_), st_);
        call(new FctActionEnabled1(),null,ctx_,ae_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctActionEnabled0(),null,ctx_,ae_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = prs();
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = ctxRunnable(pr_, files_);
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        Struct ae_ = call(new FctActionWrap("", ((LgNamesGui)ctx_.getStandards()).getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(ev_), st_);
        call(new FctActionEnabled1(),null,ctx_,ae_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctActionEnabled0(),null,ctx_,ae_,null,st_));
    }
    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }
    private Struct ctxStr(MockProgramInfos _pr, StringMap<String> _p) {
        ContextEl ctx_ = ctx(_pr,_p);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ArgumentListCall.toStr(EventStruct.invoke(NullStruct.NULL_VALUE, (RunnableContextEl) ctx_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), resSt_, new ArgumentListCall()));
    }
    private ContextEl ctx(MockProgramInfos _p) {
        return ctx(_p,new StringMap<String>());
    }
    private ContextEl ctx(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }
    private ContextEl ctxRunnable(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockRunnable(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }

    private ContextEl ctxRunnableUt(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesUtils stds_ = newLgNamesGuiSampleUt(_p, null);
//        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
//        new CustFileBuilder(stds_.getContent(), stds_.getExecContent().getCustAliases(), new CustAliasGroups(stds_.getExecContent().getCustAliases(),stds_.getContent())).getDefaultAliasGroups().allMergeTableTypeMethodNames(new StringMap<String>());
        return buildMockRunnable(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }
    private ContextEl ctxAction(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockAction(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }

    private ContextEl ctxAllInts(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockAllInts(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }
    private ContextEl ctxThreadRunnable(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMockThreadRunnable(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }
    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().runnableType(_definedKw, _definedLgNames.getContent())+_definedLgNames.getExecContent().getCustAliases().assertType(_definedKw, _definedLgNames.getContent())+_definedLgNames.getExecContent().getCustAliases().diff(_definedKw, _definedLgNames.getContent())+_definedLgNames.getExecContent().getCustAliases().eltDiff(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        Classes.tryInit(res_);
        return res_;
    }

    public static ResultContext buildMockRunnable(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().runnableType(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        _definedLgNames.getExecContent().getExecutingBlocks().runnable(_definedLgNames.getExecContent().getCustAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static ResultContext buildMockAction(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getGuiAliases().actionListener(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();
        _definedLgNames.getGuiAliases().actionEvent(_definedLgNames.getContent(), _definedLgNames.getExecContent().getCustAliases(), _definedLgNames.getGuiExecutingBlocks());

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        _definedLgNames.getGuiExecutingBlocks().action(_definedLgNames.getGuiAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static ResultContext buildMockAllInts(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        _definedLgNames.getGuiAliases().feedInts(_definedKw, _definedLgNames.getContent(),s_);
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();
        _definedLgNames.getGuiAliases().buildEvents(_definedLgNames.getContent(), _definedLgNames.getExecContent().getCustAliases(), _definedLgNames.getGuiExecutingBlocks());
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasTreeNode(),fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardType.addType(_definedLgNames.getStandards(), _definedLgNames.getGuiAliases().getAliasTreeNode(), stdcl_);
        StandardClass stdcl2_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasGrList(),fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl2_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardType.addType(_definedLgNames.getStandards(), _definedLgNames.getGuiAliases().getAliasGrList(), stdcl2_);
        _definedLgNames.getGuiAliases().color(_definedLgNames.getContent());
        _definedLgNames.getGuiAliases().image(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());
        _definedLgNames.getGuiAliases().font(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        _definedLgNames.getGuiExecutingBlocks().initEventClose((GuiContextEl) res_.getContext());
        _definedLgNames.getGuiExecutingBlocks().allInts(_definedLgNames.getGuiAliases(), _definedLgNames.getContent(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }
    public static ResultContext buildMockThreadRunnable(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getExecContent().getCustAliases().runnableType(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(_definedLgNames.getExecContent().getCustAliases().getAliasThread(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardMethod method_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasInterrupt(), new StringList(), _definedLgNames.getContent().getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new FctInterrupt());
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasEnd(), new StringList(), _definedLgNames.getContent().getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new FctThreadEnd());
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasCurrentThread(), new StringList(), _definedLgNames.getExecContent().getCustAliases().getAliasThread(), false, MethodModifier.STATIC,new FctThreadCurrent(_definedLgNames.getExecContent().getCustAliases()));
        StandardNamedFunction.addFct(methods_, method_);
        StringList params_ = new StringList(_definedLgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(_definedLgNames.getExecContent().getCustAliases().getAliasPrint(), params_, _definedLgNames.getContent().getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(""),new FctThreadPrint0(_definedLgNames.getExecContent().getCustAliases()));
        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_definedLgNames.getStandards(), _definedLgNames.getExecContent().getCustAliases().getAliasThread(), stdcl_);

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        _definedLgNames.getExecContent().getExecutingBlocks().runnable(_definedLgNames.getExecContent().getCustAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }
    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }

    public static LgNamesUtils newLgNamesGuiSampleUt(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesUtils stds_ = newLgNamesUt(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
}
