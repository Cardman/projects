package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class RateStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new();}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("0"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new(\"\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertFalse(resSt_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = Rate.parseRate(\"\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertSame(NullStruct.NULL_VALUE, field(ev_, new ClassField("pkg.Sample","r")));
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){String s = \"\";r = new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertFalse(resSt_.calls());
    }
    @Test
    public void init6() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){String s = \"2\";r = new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void init7() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){String s = null;r = new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertFalse(resSt_.calls());
    }
    @Test
    public void init8() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new(null);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertFalse(resSt_.calls());
    }
    @Test
    public void sum1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"1\") + new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void sum2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = a + b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }

    @Test
    public void diff1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"1\") - new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-1"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void diff2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = a - b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-1"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mult1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"2\") * new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("6"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mult2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"3\");public Rate r;public void run(){r = a * b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("6"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"2\") / new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2/3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"3\");public Rate r;public void run(){r = a / b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2/3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new LgInt(\"7\") / new LgInt(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new LgInt(\"7\");public LgInt b=new LgInt(\"3\");public LgInt r;public void run(){r = a / b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div5() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"2\") / new Rate(\"0\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void div6() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"0\");public Rate r;public void run(){r = a / b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void div7() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new LgInt(\"7\") / new LgInt(\"0\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void div8() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new LgInt(\"7\");public LgInt b=new LgInt(\"0\");public LgInt r;public void run(){r = a / b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void div9() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"2\") / new LgInt(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2/3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div10() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new LgInt(\"2\") / new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2/3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div11() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public LgInt b=new(\"3\");public Rate r;public void run(){r = a / b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2/3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void div12() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new(\"2\");public Rate b=new(\"3\");public Rate r;public void run(){r = a / b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2/3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"2\") % new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"3\");public Rate r;public void run(){r = a % b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new LgInt(\"7\") % new LgInt(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("1"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new LgInt(\"7\");public LgInt b=new LgInt(\"3\");public LgInt r;public void run(){r = a % b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("1"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod5() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"2\") % new Rate(\"0\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void mod6() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"0\");public Rate r;public void run(){r = a % b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void mod7() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new LgInt(\"7\") % new LgInt(\"0\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void mod8() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new LgInt(\"7\");public LgInt b=new LgInt(\"0\");public LgInt r;public void run(){r = a % b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());

    }
    @Test
    public void mod9() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Rate(\"2\") % new LgInt(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod10() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new LgInt(\"2\") % new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod11() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public LgInt b=new(\"3\");public Rate r;public void run(){r = a % b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void mod12() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new(\"2\");public Rate b=new(\"3\");public Rate r;public void run(){r = a % b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void opp1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = - new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void opp2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = - b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void opp3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = - new LgInt(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void opp4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public LgInt b=new(\"2\");public LgInt r;public void run(){r = - b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void id1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = + new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void id2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = + b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void dec1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = -- b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void dec2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r =  b --;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("2"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void dec3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b;public Rate r;public void run(){r =  b --;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void dec4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b;public Rate r;public void run(){r =  -- b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void inc1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = ++ b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("3"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("3"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void inc2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = b ++;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("2"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("3"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void inc3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b;public Rate r;public void run(){r =  b ++;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void inc4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b;public Rate r;public void run(){r =  ++ b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void add1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = b += a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("3"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("3"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void add2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=null;public Rate r;public void run(){r = b += a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void sub1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public Rate r;public void run(){r = b -= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void sub2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=null;public Rate r;public void run(){r = b -= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void prod1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"3\");public Rate b=new(\"2\");public Rate r;public void run(){r = b *= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("6"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("6"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void prod2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=null;public Rate r;public void run(){r = b *= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void quot1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"3\");public Rate b=new(\"7\");public Rate r;public void run(){r = b /= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("7/3"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("7/3"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void quot2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=null;public Rate r;public void run(){r = b /= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void quot3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new(\"3\");public LgInt b=new(\"7\");public LgInt r;public void run(){r = b /= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("2"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("2"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void quot4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new(\"1\");public LgInt b=null;public LgInt r;public void run(){r = b /= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void rem1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"3\");public Rate b=new(\"7\");public Rate r;public void run(){r = b %= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void rem2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=null;public Rate r;public void run(){r = b %= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void rem3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new(\"3\");public LgInt b=new(\"7\");public LgInt r;public void run(){r = b %= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(ev_, new ClassField("pkg.Sample","b"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void rem4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt a=new(\"1\");public LgInt b=null;public LgInt r;public void run(){r = b %= a;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(resSt_.calls());
    }
    @Test
    public void eq1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public boolean r;public void run(){r = a == b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void eq2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"2\");public boolean r;public void run(){r = a == b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }

    @Test
    public void eq3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public boolean r;public void run(){r = a == null;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void lt1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"1\") < new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void lt2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public boolean r;public void run(){r = a < b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void lt3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"2\") < new Rate(\"1\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void lt4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"1\");public boolean r;public void run(){r = a < b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void lt5() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"3\") < new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void lt6() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"3\");public Rate b=new(\"3\");public boolean r;public void run(){r = a < b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void le1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"1\") <= new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void le2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public boolean r;public void run(){r = a <= b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void le3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"2\") <= new Rate(\"1\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void le4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"1\");public boolean r;public void run(){r = a <= b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void le5() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"3\") <= new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void le6() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"3\");public Rate b=new(\"3\");public boolean r;public void run(){r = a <= b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void gt1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"1\") > new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void gt2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public boolean r;public void run(){r = a > b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void gt3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"2\") > new Rate(\"1\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void gt4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"1\");public boolean r;public void run(){r = a > b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void gt5() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"3\") > new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void gt6() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"3\");public Rate b=new(\"3\");public boolean r;public void run(){r = a > b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void ge1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"1\") >= new Rate(\"2\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void ge2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"1\");public Rate b=new(\"2\");public boolean r;public void run(){r = a >= b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void ge3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"2\") >= new Rate(\"1\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void ge4() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"2\");public Rate b=new(\"1\");public boolean r;public void run(){r = a >= b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void ge5() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Rate(\"3\") >= new Rate(\"3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void ge6() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new(\"3\");public Rate b=new(\"3\");public boolean r;public void run(){r = a >= b;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convSum() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Sample2(new(\"1\")) + new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convDiff() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Sample2(new(\"1\")) - new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-1"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convMult() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Sample2(new(\"2\")) * new Sample2(new(\"3\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("6"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convDiv1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Sample2(new(\"2\")) / new Sample2(new(\"3\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2/3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convDiv2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new Sample2(new(\"7\")) / new Sample2(new(\"3\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public LgInt r;(LgInt r){this.r=r;}public static LgInt $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convMod1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = new Sample2(new(\"2\")) % new Sample2(new(\"3\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convMod2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new Sample2(new(\"7\")) % new Sample2(new(\"3\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public LgInt r;(LgInt r){this.r=r;}public static LgInt $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("1"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convLt1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"1\")) < new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convLt2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"2\")) < new Sample2(new(\"1\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convLe1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"1\")) <= new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convLe2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"2\")) <= new Sample2(new(\"1\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convGt1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"2\")) > new Sample2(new(\"1\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convGt2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"1\")) > new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convGe1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"2\")) >= new Sample2(new(\"1\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertTrue(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convGe2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public boolean r;public void run(){r = new Sample2(new(\"1\")) >= new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertFalse(field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void convUn() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = - new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("-2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convId() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate r;public void run(){r = + new Sample2(new(\"2\"));}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample", "r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }

    @Test
    public void convDec1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"1\"));public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){r = -- b;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }

    @Test
    public void convDec2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"1\"));public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){r = b --;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("2"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }

    @Test
    public void convInc1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"1\"));public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){r = ++ b;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("3"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("3"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }

    @Test
    public void convInc2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"1\"));public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){r = b ++;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("2"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("3"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convAdd() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"1\"));public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){r = b += a;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("3"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("3"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convSub() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"1\"));public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){r = b -= a;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convProd() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"3\"));public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){r = b *= a;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("6"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("6"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convQuot1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"3\"));public Sample2 b=new(new Rate(\"7\"));public Sample2 r;public void run(){r = b /= a;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("7/3"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("7/3"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convQuot2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new LgInt(\"3\"));public Sample2 b=new(new LgInt(\"7\"));public Sample2 r;public void run(){r = b /= a;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public LgInt r;(LgInt r){this.r=r;}public static LgInt $(Sample2 s){return s.r;}public static Sample2 $(LgInt s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("2"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("2"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convRem1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new Rate(\"3\"));public Sample2 b=new(new Rate(\"7\"));public Sample2 r;public void run(){r = b %= a;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}public static Rate $(Sample2 s){return s.r;}public static Sample2 $(Rate s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void convRem2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Sample2 a=new(new LgInt(\"3\"));public Sample2 b=new(new LgInt(\"7\"));public Sample2 r;public void run(){r = b %= a;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public LgInt r;(LgInt r){this.r=r;}public static LgInt $(Sample2 s){return s.r;}public static Sample2 $(LgInt s){return new(s);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = lgInst(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(ev_, ctx_, resSt_);
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","r")),new ClassField("pkg.Sample2","r"))).getRate());
        assertEq(Rate.newRate("1"),((RateStruct) field(field(ev_, new ClassField("pkg.Sample","b")),new ClassField("pkg.Sample2","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void den1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new Rate(\"2/3\").den();}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void den2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){Rate r2=new(\"2/3\"); r = r2.den();}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("3"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void den3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){Rate r2=null; r = r2.den();}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertFalse(resSt_.calls());
    }
    @Test
    public void num1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = new Rate(\"2/3\").num();}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void num2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){Rate r2=new(\"2/3\"); r = r2.num();}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        RateStruct rate_ = (RateStruct) field(ev_, new ClassField("pkg.Sample","r"));
        assertEq(Rate.newRate("2"),rate_.getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void num3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){Rate r2=null; r = r2.num();}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertFalse(resSt_.calls());
    }
    @Test
    public void str1() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public String r;public void run(){r = StringUtil.valueOf(new Rate(\"2/3\"));}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertEq("2/3",field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void str2() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public String r;public void run(){Rate r2=new(\"2/3\"); r = StringUtil.valueOf(r2);}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertEq("2/3",field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void str3() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public String r;public void run(){r = \"\"+new Rate(\"2/3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertEq("2/3",field(ev_, new ClassField("pkg.Sample","r")));
        assertTrue(resSt_.calls());
    }
    @Test
    public void defInst() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public LgInt r;public void run(){r = (LgInt)class(Rate).defaultInstance();*new Rate(\"2/3\");}}");
        ContextEl ctx_ = build(opt_, e_, files_).getContext();
        Struct ev_ = quick(ctx_);
        //InterruptibleContextEl r_ = new InterruptibleContextEl(new MockAtomicBoolean(), ctx_.getExecutionInfos());
        
        String base_ = StringExpUtil.getIdFromAllTypes(ev_.getClassName(ctx_));
        ExecOverrideInfo mId_ = getRedirections(ctx_,base_);
        Argument arg_ = new Argument(ev_);
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        invoke(arg_, mId_.getClassName(), ctx_, mId_.getPair(), resSt_, new ArgumentListCall());
        assertEq(Rate.zero(),((RateStruct) field(ev_, new ClassField("pkg.Sample","r"))).getRate());
        assertTrue(resSt_.calls());
    }
    @Test
    public void err() {
        MockProgramInfos pr_ = prs();
        
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions();
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample:Runnable{public Rate a=new Rate(\"1\");public Sample2 b=new(new Rate(\"2\"));public Sample2 r;public void run(){a+b;a-b;a*b;a/b;a%b;}}");
        files_.addEntry("src/sample2.txt","public class pkg.Sample2{public Rate r;(Rate r){this.r=r;}}");
        ReportedMessages ctx_ = build(opt_, e_, files_).getReportedMessages();
        assertFalse(ctx_.isAllEmptyErrors());
    }
    private Struct instance(ContextEl _rCtor, ExecRootBlock _ex, ContextEl _ctx) {
        StackCall resStCtor_ = StackCall.newInstance(InitPhase.NOTHING, _rCtor);
        Argument result_ = ProcessMethod.calculate(new CustomFoundConstructor(_ctx,new ExecFormattedRootBlock(_ex),new Argument()), _ctx,resStCtor_).getValue();
        return ArgumentListCall.toStr(result_);
    }

    public static void invoke(Argument _global, ExecFormattedRootBlock _class, ContextEl _cont, ExecTypeFunction _pair, StackCall _stackCall, ArgumentListCall _argList) {
        Parameters parameters_ = ExecTemplates.wrapAndCall(_pair, _class, _global, _cont, _stackCall, _argList);
        ProcessMethod.calculate(new CustomFoundMethod(_global, _class, _pair, parameters_), _cont, _stackCall);
    }

    private Struct lgInst(ContextEl _ctx) {
        ExecRootBlock ex_ = _ctx.getClasses().getClassBody("pkg.Sample");
        return instance(_ctx, ex_, _ctx);
    }


    private Struct quick(ContextEl _ctx) {
        ExecRootBlock ex_ = _ctx.getClasses().getClassBody("pkg.Sample");
        return _ctx.getInit().processInit(_ctx, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(ex_), "", -1);
    }

    private void invoke(Struct _inst, ContextEl _ctx, StackCall _stack) {
        String base_ = StringExpUtil.getIdFromAllTypes(_inst.getClassName(_ctx));
        ExecRootBlock cl_ = _ctx.getExecutionInfos().getClasses().getClassBody("pkg.Runnable");
        ExecOverridableBlock a_ = ExecClassesUtil.getMethodBodiesById(cl_, new MethodId(MethodAccessKind.INSTANCE, "run", new CustList<String>())).first();
        ExecOverrideInfo mId_ = _ctx.getClasses().getRedirections().get(cl_.getNumberType()).getVal(a_,base_);
        Argument arg_ = new Argument(_inst);
        Parameters parameters_ = ExecTemplates.wrapAndCall(mId_.getPair(), mId_.getClassName(), arg_, _ctx, _stack, new ArgumentListCall());
        ProcessMethod.calculate(new CustomFoundMethod(arg_, mId_.getClassName(), mId_.getPair(), parameters_), _ctx, _stack);
    }

    private static Struct field(Struct _ev, ClassField _id) {
        return ((FieldableStruct)_ev).getEntryStruct(_id).getStruct();
    }

    private MockProgramInfos prs() {
        MockProgramInfos prs_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(prs_);
        return prs_;
    }

    private ExecOverrideInfo getRedirections(ContextEl _r, String _id) {
        ExecRootBlock cl_ = _r.getExecutionInfos().getClasses().getClassBody("pkg.Runnable");
        ExecOverridableBlock a_ = ExecClassesUtil.getMethodBodiesById(cl_, new MethodId(MethodAccessKind.INSTANCE, "run", new CustList<String>())).first();
        return _r.getClasses().getRedirections().get(cl_.getNumberType()).getVal(a_,_id);
    }
}
