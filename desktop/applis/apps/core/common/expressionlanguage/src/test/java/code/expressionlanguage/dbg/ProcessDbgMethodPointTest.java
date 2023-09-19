package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgMethodPointTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        assertFalse(is(cont_, 40));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",40);
        assertTrue(is(cont_, 21));
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",40);
        cont_.toggleBreakPoint("pkg/Ex",40);
        assertFalse(is(cont_, 21));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",40);
        assertFalse(is(cont_, 58));
    }

    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",-1);
        assertFalse(is(cont_, 21));
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",40);
        cont_.toggleBreakPointEnabled("pkg/Ex",40);
        assertFalse(is(cont_, 21));
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",40);
        cont_.toggleBreakPointEnabled("pkg/Ex",40);
        cont_.toggleBreakPointEnabled("pkg/Ex",40);
        assertTrue(is(cont_, 21));
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPointEnabled("pkg/Ex",40);
        assertTrue(is(cont_, 21));
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPointEnabled("pkg/Ex",-1);
        assertFalse(is(cont_, 21));
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",40);
        cont_.toggleBreakPoint("pkg/Ex",77);
        assertTrue(is(cont_, 58));
    }

    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",40);
        cont_.toggleBreakPoint("pkg/Ex",77);
        cont_.toggleBreakPointEnabled("pkg/Ex",40);
        assertTrue(is(cont_, 58));
    }

    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPointEnabled("pkg/Ex",40);
        cont_.toggleBreakPointEnabled("pkg/Ex",77);
        assertTrue(is(cont_, 58));
    }

    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",66);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(55, now(stack_));
    }

    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",66);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",66);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(101, now(stack_));
    }

    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",66);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{return exmeth2();}catch(Object e){return 0;}}public static int exmeth2(){return 1/0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",97);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(133, now(stack_));
    }

    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{return exmeth2();}catch(Object e){return 0;}}public static int exmeth2(){return 1/0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",97);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{return exmeth2();}catch(Object e){return 0;}}public static int exmeth2(){return 1/0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",97);
        div(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.EXCEPTION,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(133, now(stack_));
    }

    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{return exmeth2();}catch(Object e){return 0;}}public static int exmeth2(){return 1/0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",97);
        div(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, next_.nbPages());
        assertEq(133, now(stack_));
        assertEq(59, next_.getCall(0).getTraceIndex());
    }

    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{return exmeth2();}catch(Object e){return 0;}}public static int exmeth2(){return 1/0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",97);
        div(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertSame(StopDbgEnum.EXCEPTION,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, next_.nbPages());
        assertEq(59, now(stack_));
        assertEq(133, stack_.getBreakPointInfo().getBreakPointMiddleInfo().getExiting().getTraceIndex());
    }

    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{return exmeth2();}catch(Object e){return 0;}}public static int exmeth2(){return 1/0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",97);
        div(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",90);
        cont_.toggleWatchPoint("pkg/Ex",32);
        write(cont_,cf("pkg.Ex","f"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(123, now(stack_));
        assertEq(77, stack_.getCall(0).getTraceIndex());
    }

    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",90);
        cont_.toggleWatchPoint("pkg/Ex",32);
        write(cont_,cf("pkg.Ex","f"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertSame(StopDbgEnum.FIELD,next_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, next_.nbPages());
        assertEq(86, now(next_));
    }

    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",90);
        cont_.toggleWatchPoint("pkg/Ex",32);
        write(cont_,cf("pkg.Ex","f"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        cont_.toggleWatchPoint("pkg/Ex",32);
        compoundRead(cont_,cf("pkg.Ex","f"));
        pair(cont_, cf("pkg.Ex", "f")).getValue().setEnabled(false);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(124, now(stack_));
        assertEq(77, stack_.getCall(0).getTraceIndex());
    }

    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        cont_.toggleWatchPoint("pkg/Ex",32);
        compoundRead(cont_,cf("pkg.Ex","f"));
        pair(cont_, cf("pkg.Ex", "f")).getValue().setEnabled(false);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("a==5&&f==3",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(125, now(stack_));
        assertEq(79, stack_.getCall(0).getTraceIndex());
    }

    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("a==5&&f==3",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("a==6",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("f==4",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("a==5&&f==3",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test33() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("a==6",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test34() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("f==4",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("a==5&&f==3&&value==7",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(159, now(stack_));
        assertEq(83, stack_.getCall(0).getTraceIndex());
    }

    @Test
    public void test36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("a==5&&f==3&&value==7",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test37() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("a==6",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test38() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("f==4",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("value==8",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("a==5&&f==3&&value==7",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("a==6",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test42() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("f==4",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test43() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3,e;public static int exmeth(){return new Ex()[5]=7;}public int exmeth2(int a){return f+a;}public void this(int a,int value){e=value;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("value==8",cont_,"pkg/Ex",125);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test44() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",90);
        cont_.toggleWatchPoint("pkg/Ex",32);
        write(cont_,cf("pkg.Ex","f"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalMute(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test45() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        cont_.toggleWatchPoint("pkg/Ex",32);
        compoundRead(cont_,cf("pkg.Ex","f"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(124, now(stack_));
        assertEq(77, stack_.getCall(0).getTraceIndex());
    }

    @Test
    public void test46() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        cont_.toggleWatchPoint("pkg/Ex",32);
        compoundRead(cont_,cf("pkg.Ex","f"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertSame(StopDbgEnum.FIELD,next_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, next_.nbPages());
        assertEq(124, next_.getBreakPointInfo().getBreakPointMiddleInfo().getExiting().getTraceIndex());
        assertEq(77, now(next_));
    }

    @Test
    public void test47() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        cont_.toggleWatchPoint("pkg/Ex",32);
        compoundRead(cont_,cf("pkg.Ex","f"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test48() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}{f=f;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        cont_.toggleWatchPoint("pkg/Ex",32);
        compoundRead(cont_,cf("pkg.Ex","f"));
        pair(cont_, cf("pkg.Ex", "f")).getValue().setEnabled(false);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(124, now(stack_));
        assertEq(77, stack_.getCall(0).getTraceIndex());
    }

    @Test
    public void test49() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}{f=f;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        cont_.toggleWatchPoint("pkg/Ex",32);
        compoundRead(cont_,cf("pkg.Ex","f"));
        pair(cont_, cf("pkg.Ex", "f")).getValue().setEnabled(false);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test50() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test51() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("",cont_,"pkg/Ex",91);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test52() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("b==5&&f==3",cont_,"pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, stack_.nbPages());
        assertEq(118, now(stack_));
        assertEq(72, stack_.getCall(0).getTraceIndex());
    }

    @Test
    public void test53() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("b==5&&f==3",cont_,"pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test54() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("b==6",cont_,"pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test55() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("f==4",cont_,"pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test56() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("b==5&&f==3",cont_,"pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test57() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("b==6",cont_,"pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test58() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("f==4",cont_,"pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test59() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){(:int)->0;return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",48);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test60() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){((:int)->0).call();return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",49);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test61() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){((:int)->0).call();return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",49);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test62() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){(:int)->0;return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",48);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test63() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){((:int)->0).call();return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",49);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
    }

    @Test
    public void test64() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){((:int)->0).call();return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",49);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test65() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){(switch(0){default;return 0;});return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",55);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test66() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){(switch(0){default;return 0;});return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",55);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test67() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){(switch(0){default;return 0;});return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",55);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test68() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){(switch(0){default;return 0;});return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",55);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test69() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2();}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",66);
        String idm_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(66, cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex")));
        cont_.getPair(idm_).getValue().setEnabled(true);
        cont_.getPair(idm_).getValue().setEnabled(false);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test70() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(S)==class(int)",cont_,"pkg/Ex",178);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test71() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(S)==class(long)",cont_,"pkg/Ex",178);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test72() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(S)==class(int)",cont_,"pkg/Ex",178);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test73() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(S)==class(long)",cont_,"pkg/Ex",178);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test74() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(T)==class(int)",cont_,"pkg/Ex",95);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test75() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(T)==class(long)",cont_,"pkg/Ex",95);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test76() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(T)==class(int)",cont_,"pkg/Ex",95);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test77() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().exmeth2(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(T)==class(long)",cont_,"pkg/Ex",95);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test78() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public class Inner<U>{public T a;public U b;public (T a,U b){this.a=a;this.b=b;}}public T c;public (T c){this.c=c;}public static int exmeth(){var o=new Ex<int>(2);var i=o.new Inner<int>(3,4);return i.c+i.a+i.b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("c==2&&a==3&&b==4&&class(U)==class(int)&&class(T)==class(int)&&this.a==0&&this.b==0",cont_,"pkg/Ex",68);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertSame(StopDbgEnum.METHOD_ENTRY, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertNull(stack_.getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test79() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public class Inner<U>{public T a;public U b;public (T a,U b){this.a=a;this.b=b;}}public T c;public (T c){this.c=c;}public static int exmeth(){var o=new Ex<int>(2);var i=o.new Inner<int>(3,4);return i.c+i.a+i.b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("c==2&&a==3&&b==4&&class(U)==class(int)&&class(T)==class(int)&&this.a==3&&this.b==4",cont_,"pkg/Ex",68);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertSame(StopDbgEnum.METHOD_EXIT, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertNull(stack_.getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test80() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(S)==class(int)",cont_,"pkg/Ex",264);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test81() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(S)==class(long)",cont_,"pkg/Ex",264);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test82() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(S)==class(int)",cont_,"pkg/Ex",264);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test83() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(S)==class(long)",cont_,"pkg/Ex",264);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test84() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(T)==class(int)",cont_,"pkg/Ex",181);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test85() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(T)==class(long)",cont_,"pkg/Ex",181);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test86() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(T)==class(int)",cont_,"pkg/Ex",181);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test87() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return (int)class(Ex).makeGeneric(class(int)).getDeclaredMethods(\"exmeth2\",null,null,null)[0].invoke(new Ex<int>(),5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(T)==class(long)",cont_,"pkg/Ex",181);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test88() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public class Inner<U>{public T a;public U b;public (T a,U b){this.a=a;this.b=b;}}public T c;public (T c){this.c=c;}public static int exmeth(){var o=new Ex<int>(2);var i=(Ex<int>.Inner<int>)class(Ex.Inner).makeGeneric(class(int),class(int)).getDeclaredConstructors()[0].newInstance(o,3,4);return i.c+i.a+i.b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("c==2&&a==3&&b==4&&class(U)==class(int)&&class(T)==class(int)&&this.a==0&&this.b==0",cont_,"pkg/Ex",68);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertSame(StopDbgEnum.METHOD_ENTRY, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertNull(stack_.getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test89() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public class Inner<U>{public T a;public U b;public (T a,U b){this.a=a;this.b=b;}}public T c;public (T c){this.c=c;}public static int exmeth(){var o=new Ex<int>(2);var i=(Ex<int>.Inner<int>)class(Ex.Inner).makeGeneric(class(int),class(int)).getDeclaredConstructors()[0].newInstance(o,3,4);return i.c+i.a+i.b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("c==2&&a==3&&b==4&&class(U)==class(int)&&class(T)==class(int)&&this.a==3&&this.b==4",cont_,"pkg/Ex",68);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertSame(StopDbgEnum.METHOD_EXIT, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertNull(stack_.getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test90() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(S)==class(int)",cont_,"pkg/Ex",204);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test91() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(S)==class(long)",cont_,"pkg/Ex",204);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test92() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(S)==class(int)",cont_,"pkg/Ex",204);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test93() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(S)==class(long)",cont_,"pkg/Ex",204);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test94() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(T)==class(int)",cont_,"pkg/Ex",121);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test95() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("class(T)==class(long)",cont_,"pkg/Ex",121);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test96() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(T)==class(int)",cont_,"pkg/Ex",121);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test97() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExSuper<T> {public static int exmeth(){return new Ex<int>().$lambda(Ex<int>,exmeth2,int).call(5);}public int exmeth2(int a){return f+a;}}public class pkg.ExSuper<S> {public int f=3;public int exmeth2(int b){return f+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("class(T)==class(long)",cont_,"pkg/Ex",121);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test98() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public class Inner<U>{public T a;public U b;public (T a,U b){this.a=a;this.b=b;}}public T c;public (T c){this.c=c;}public static int exmeth(){var o=new Ex<int>(2);var i=o.$lambda(Inner<int>,new,int,int).call(3,4);return i.c+i.a+i.b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("c==2&&a==3&&b==4&&class(U)==class(int)&&class(T)==class(int)&&this.a==0&&this.b==0",cont_,"pkg/Ex",68);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertSame(StopDbgEnum.METHOD_ENTRY, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertNull(stack_.getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test99() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public class Inner<U>{public T a;public U b;public (T a,U b){this.a=a;this.b=b;}}public T c;public (T c){this.c=c;}public static int exmeth(){var o=new Ex<int>(2);var i=o.$lambda(Inner<int>,new,int,int).call(3,4);return i.c+i.a+i.b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exitingCondition("c==2&&a==3&&b==4&&class(U)==class(int)&&class(T)==class(int)&&this.a==3&&this.b==4",cont_,"pkg/Ex",68);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertSame(StopDbgEnum.METHOD_EXIT, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertNull(stack_.getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }

    @Test
    public void test100() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {Ex2 v = new Ex2();public static void catching(){Ex e = new();e.v+=new Ex3();}}public class pkg.Ex2 {public int v;public static Ex3 $(Ex2 f){Ex3 o = new Ex3();o.v=f.v;return o;}public static Ex2 $(Ex3 f){Ex2 o = new Ex2();o.v=f.v;return o;}}public class pkg.Ex3 {public int v;operator+ Ex3(Ex3 f, Ex3 s){Ex3 o = new Ex3();o.v=f.v+s.v;return o;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",197);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(85, now(stack_));
        assertSame(StopDbgEnum.METHOD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}{f=f;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",91);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(91, cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex")));
        MethodPointBlockPair wp_ = cont_.getPair(id_);
        assertFalse(new MethodKeyString().keyString(wp_).isEmpty());
    }
    private void div(ResultContext _cont) {
        _cont.toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(), true);
        ExcPoint val_ = _cont.getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(), true).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(true);
    }
    private boolean is(ResultContext _cont, int _off) {
        return _cont.is(_cont.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex").getNumberFile()+"/"+_off);
    }

    private ClassField cf(String _cl, String _f) {
        return new ClassField(_cl,_f);
    }
    private void write(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(false);
        pair(_cont, _cf).getValue().setWrite(true);
        pair(_cont, _cf).getValue().setCompoundRead(false);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }

    private void compoundRead(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(false);
        pair(_cont, _cf).getValue().setWrite(false);
        pair(_cont, _cf).getValue().setCompoundRead(true);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }

    private WatchPointBlockPair pair(ResultContext _cont, ClassField _cf) {
        int n_ = _cont.getPageEl().getAnaClassBody(_cf.getClassName()).getNumberAll();
        return _cont.getPairWatch(true,n_,_cf.getFieldName());
    }

    private void enteringCondition(String _newValue,ResultContext _cont, String _file, int _offset) {
        entering(_cont, _file, _offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        MethodPointBlockPair wp_ = _cont.getPair(id_);
        wp_.getValue().getResultEntry().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultEntry().getResultStr());


//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_newValue, wp_, _cont, type_, new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getValue().getResultEntry().result(res_,_newValue);
    }
    private void exitingCondition(String _newValue,ResultContext _cont, String _file, int _offset) {
        exiting(_cont, _file, _offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        MethodPointBlockPair wp_ = _cont.getPair(id_);
        wp_.getValue().getResultExit().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultExit().getResultStr());

//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_newValue, wp_, _cont, type_, new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getValue().getResultExit().result(res_,_newValue);
    }
    private void entering(ResultContext _cont, String _file, int _offset) {
        _cont.toggleBreakPoint(_file,_offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
        _cont.getPair(id_).getValue().setEntry(true);
        _cont.getPair(id_).getValue().setExit(false);
    }

    private void exiting(ResultContext _cont, String _file, int _offset) {
        _cont.toggleBreakPoint(_file,_offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
        _cont.getPair(id_).getValue().setEntry(false);
        _cont.getPair(id_).getValue().setExit(true);
    }
}
