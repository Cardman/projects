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
import code.gui.images.MetaDimension;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class EventStructTest extends EquallableElUtUtil {
    @Test
    public void run1() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
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
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
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
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
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
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = stds_.newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        ((RunnableFunctionalInstance)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run5() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = stds_.newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        ((RunnableFunctionalInstance)ev_).run();
        assertFalse(st_.isFailInit());
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
        ((FieldableStruct)ev_).getFields();
        ((FieldableStruct)ev_).getEntryStruct(new ClassField("",""));
    }
    @Test
    public void run6() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){throw null;} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        CustAliases.newFunctional(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        RunnableFunctionalInstance.callMethod( (RunnableContextEl) ctx_,NullStruct.NULL_VALUE,new CustList<Argument>());
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run7() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public enum pkg.Sample:Runnable{ONE;public int i=2;(){i=i;name();ordinal();ObjectsUtil.getParent(this);ObjectsUtil.setParent(this,this);}public void run(){}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "ONE", 0);
        ((RunnableStruct)ev_).run();
        assertFalse(st_.isFailInit());
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
        ((FieldableStruct)ev_).getFields();
        ((WithParentStruct)ev_).setParent(NullStruct.NULL_VALUE);
    }
    @Test
    public void run8() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){Thread.interrupt();}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((RunnableStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run9() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){Thread.currentThread().end();}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((RunnableStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void run10() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public void run(){} public static Fct fct(){Thread.print(\"\");return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        CustAliases.newFunctional(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        RunnableFunctionalInstance.callMethod( (RunnableContextEl) ctx_,NullStruct.NULL_VALUE,new CustList<Argument>());
        assertFalse(st_.isFailInit());
        stds_.getExecContent().getExecutingBlocks().getExecuteMethodPair();
    }
    @Test
    public void run11() {
        MockProgramInfos pr_ = prs();
        LgNamesUtils stds_ = newLgNamesUtSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Outer{public class Sample:Runnable{public int i=2;(){i=i;ObjectsUtil.getParent(this);}public void run(){ObjectsUtil.setParent(this,Outer.this);}}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex1_ = ctx_.getClasses().getClassBody("pkg.Outer");
        ExecRootBlock ex2_ = ctx_.getClasses().getClassBody("pkg.Outer..Sample");
        Struct ev1_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex1_), "", -1);
        Struct ev2_ = ctx_.getInit().processInit(ctx_, ev1_, new ExecFormattedRootBlock(ex2_), "", -1);
        ((RunnableStruct)ev2_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt1() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt2() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).action(new KeyActionEvent(0),"");
        assertFalse(st_.isFailInit());
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
        ev_.randCode();
        ((FieldableStruct)ev_).getEntryStruct(new ClassField("",""));
        ((FieldableStruct)ev_).getFields();
        ((FieldableStruct)ev_).getParent();
        ((WithParentStruct)ev_).setParent(NullStruct.NULL_VALUE);
    }
    @Test
    public void evt3() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public enum pkg.Sample:Runnable{ONE;public int i=2;(){i=i;name();ordinal();ObjectsUtil.getParent(this);ObjectsUtil.setParent(this,this);} public void run(){}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).action(new KeyActionEvent(0),"");
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt4() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        ((EventStruct)ev_).mouseClicked(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(false,false,false,0));
        ((EventStruct)ev_).mouseEntered(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseExited(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseMoved(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mousePressed(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseDragged(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseReleased(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventStruct)ev_).mouseWheelMoved(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1),new MockMouseWheel(1));
        ((EventStruct)ev_).keyPressed(new KeyActionEvent(0),'0',0);
        ((EventStruct)ev_).keyReleased(new KeyActionEvent(0),'0',0);
        ((EventStruct)ev_).keyTyped(new KeyActionEvent(0),'0');
        ((EventStruct)ev_).valueChanged(new MockMutableTreeNode(""));
        ((EventStruct)ev_).valueChanged(0,0);
        ((EventStruct)ev_).valueChanged(new SelectionInfo(0,0,true));
        ((EventStruct)ev_).stateChanged();
        ((EventStruct)ev_).windowActivated();
        ((EventStruct)ev_).windowClosed();
        ((EventStruct)ev_).windowClosing();
        ((EventStruct)ev_).windowDeactivated();
        ((EventStruct)ev_).windowDeiconified();
        ((EventStruct)ev_).windowIconified();
        ((EventStruct)ev_).windowOpened();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt5() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Outer{public class Sample:Runnable{public int i=2;(){i=i;ObjectsUtil.getParent(this);}public void run(){ObjectsUtil.setParent(this,Outer.this);}}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
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
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = stds_.newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        ((EventFunctionalInstance)ev_).run();
        ((EventFunctionalInstance)ev_).action(new KeyActionEvent(0),"");
        ((EventFunctionalInstance)ev_).mouseClicked(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(false,false,false,0));
        ((EventFunctionalInstance)ev_).mouseEntered(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventFunctionalInstance)ev_).mouseExited(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventFunctionalInstance)ev_).mouseMoved(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventFunctionalInstance)ev_).mousePressed(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventFunctionalInstance)ev_).mouseDragged(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventFunctionalInstance)ev_).mouseReleased(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1));
        ((EventFunctionalInstance)ev_).mouseWheelMoved(new MockMouseCoords(0, 0), new KeyActionEvent(0), new MockMouseButtons(true,true,true,1),new MockMouseWheel(1));
        ((EventFunctionalInstance)ev_).keyPressed(new KeyActionEvent(0),'0',0);
        ((EventFunctionalInstance)ev_).keyReleased(new KeyActionEvent(0),'0',0);
        ((EventFunctionalInstance)ev_).keyTyped(new KeyActionEvent(0),'0');
        ((EventFunctionalInstance)ev_).valueChanged(new MockMutableTreeNode(""));
        ((EventFunctionalInstance)ev_).valueChanged(0,0);
        ((EventFunctionalInstance)ev_).valueChanged(new SelectionInfo(0,0,true));
        ((EventFunctionalInstance)ev_).stateChanged();
        ((EventFunctionalInstance)ev_).windowActivated();
        ((EventFunctionalInstance)ev_).windowClosed();
        ((EventFunctionalInstance)ev_).windowClosing();
        ((EventFunctionalInstance)ev_).windowDeactivated();
        ((EventFunctionalInstance)ev_).windowDeiconified();
        ((EventFunctionalInstance)ev_).windowIconified();
        ((EventFunctionalInstance)ev_).windowOpened();
        ev_.randCode();
        assertFalse(ev_.sameReference(NullStruct.NULL_VALUE));
        assertTrue(ev_.sameReference(ev_));
        ((FieldableStruct)ev_).getFields();
        ((FieldableStruct)ev_).getEntryStruct(new ClassField("",""));
        assertFalse(st_.isFailInit());
        stds_.getGuiExecutingBlocks().getPairPaintRefresh();
        stds_.getGuiExecutingBlocks().getPairPaintMethod();
        stds_.getGuiExecutingBlocks().getPairPaintAdd();
        stds_.getGuiExecutingBlocks().getPairPaintSet();
        stds_.getGuiExecutingBlocks().getMainArgs();
        stds_.getGuiExecutingBlocks().getEventClose().windowClosing();
    }
    @Test
    public void evt7() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        ExecOverridableBlock f_ = ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "fct", new CustList<String>())).first();
        ExecTypeFunction et_ = new ExecTypeFunction(ex_,f_);
        Struct lda_ = str(RunnableStruct.invoke(NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), (RunnableContextEl) ctx_, et_, st_, new ArgumentListCall()));
        Struct ev_ = stds_.newFullFunctionalInstance(new ExecFormattedRootBlock(ex_), (LambdaStruct) lda_,stds_.getExecContent().getExecutingBlocks().getRunMethod(), ctx_);
        ((EventFunctionalInstance)ev_).run();
        assertFalse(st_.isFailInit());
    }
    @Test
    public void paint1() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
        Struct lab_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(img_), st_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(new StringStruct("")));
        args_.add(new Argument(new IntStruct(0)));
        args_.add(new Argument(lab_));
        args_.add(new Argument(BooleanStruct.of(true)));
        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
        assertFalse(st_.isFailInit());
    }

    @Test
    public void paint2() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(true)), st_);
        Struct lab_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(img_), st_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(new StringStruct("")));
        args_.add(new Argument(new IntStruct(0)));
        args_.add(new Argument(lab_));
        args_.add(new Argument(BooleanStruct.of(false)));
        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
        assertFalse(st_.isFailInit());
    }
    @Test
    public void paint3() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(new StringStruct("")));
        args_.add(new Argument(new IntStruct(0)));
        args_.add(new Argument(NullStruct.NULL_VALUE));
        args_.add(new Argument(BooleanStruct.of(true)));
        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
        assertFalse(st_.isFailInit());
    }
    @Test
    public void paint4() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public int i=2;(){i=i;}public void run(){} public static Fct fct(){return new Sample().$lambda(Runnable,run);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(new StringStruct("")));
        args_.add(new Argument(new IntStruct(0)));
        args_.add(new Argument(NullStruct.NULL_VALUE));
        args_.add(new Argument(BooleanStruct.of(false)));
        new DefSpecSelectionStruct((RunnableContextEl) ctx_,NullStruct.NULL_VALUE).execute(args_,new MetaDimension(1,1));
        assertFalse(st_.isFailInit());
    }
    @Test
    public void convert() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        assertEq("",new DefSpecSelectionCtx(((RunnableContextEl)ctx_).getInterrupt(),ctx_.getExecutionInfos(),new StringList()).convertStr(new StringStruct("")));
    }
    @Test
    public void actWrap1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctActionWrap("", stds_.getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        assertSame(NullStruct.NULL_VALUE,e_);
    }
    @Test
    public void actWrap2() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        Struct ae_ = call(new FctActionWrap("", stds_.getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(ev_), st_);
        assertSame(ev_,call(new FctActionArg(),null,ctx_,ae_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        Struct ae_ = call(new FctActionWrap("", stds_.getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(ev_), st_);
        call(new FctActionEnabled1(),null,ctx_,ae_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctActionEnabled0(),null,ctx_,ae_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = prs();
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        Struct ev_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
        Struct ae_ = call(new FctActionWrap("", stds_.getGuiExecutingBlocks().getCompoFactory()), null, ctx_, null, one(ev_), st_);
        call(new FctActionEnabled1(),null,ctx_,ae_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctActionEnabled0(),null,ctx_,ae_,null,st_));
    }
    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }

}
