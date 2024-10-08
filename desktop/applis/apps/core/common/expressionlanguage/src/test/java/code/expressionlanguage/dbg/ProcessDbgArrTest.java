package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.AbsractIdentifiableCommon;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgArrTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdNotLen(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){return new Ex<int>[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParam(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(73, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){return new Ex<int>[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInc(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(73, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        intsNotEx(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){return new Ex<int>[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        allArr(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(73, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=$lambda(int[],[],int);return l.call(new int[1],0);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=$lambda(int[],[]);return l.call(null);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=((int[])null)?.$lambda(int[],[]);return l.call();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){Fct l=$lambda(int[],[]);return (int)l.call();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=$lambda(int[],[]);return l.call(new int[1]);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(81, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[1].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"this.length==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[1].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"this.length==2");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[1].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=$lambda(int[],[]);return l.call(new int[1]);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringExiting(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(81, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_STD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(81, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.ARRAY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(81, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_STD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){Ex[] a = null;return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",129);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",139);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(91, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a[0]==3",cont_,"pkg/Ex",139);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,"pkg/Ex",129);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",139);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(91, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a[0]==3",cont_,"pkg/Ex",139);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringExiting(cont_,"pkg/Ex",129);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(86, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.ARRAY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void text26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringExiting(cont_,"pkg/Ex",129);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=$lambda(int[],[]);return l.call(new int[1]);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringExiting(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(81, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_STD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(81, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_STD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)static().$lambda(Int,len,int[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",143);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)static().$lambda(Int,len,int[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",153);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(105, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)static().$lambda(Int,len,int[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a[0]==3",cont_,"pkg/Ex",153);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)static().$lambda(Int,len,int[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,"pkg/Ex",143);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)static().$lambda(Int,len,int[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",153);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(105, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test33() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)static().$lambda(Int,len,int[]);return l.len(new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a[0]==3",cont_,"pkg/Ex",153);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void text34() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)static().$lambda(Int,len,int[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringExiting(cont_,"pkg/Ex",143);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(3, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(207, nowTrace(stack_));
        assertSame(StopDbgEnum.ARRAY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(100, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void text35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)static().$lambda(Int,len,int[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);public static int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringExiting(cont_,"pkg/Ex",143);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(100, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void text36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int this(int i){return 0;}public void this(int i){}public static int exmeth(){var l=$lambda(int,:);l.call(that(new Ex()[0]));return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void text37() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int this(int i){return 0;}public void this(int i){}public static int exmeth(){var l=$lambda(int,=,int);l.call(that(new Ex()[0]),0);return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void text38() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int this(int i){return 0;}public void this(int i){}public static int exmeth(){new Ex()[0];new Ex()[0]=0;that int v=that(new Ex()[0]);v++;v()+=1;return 0;}public static that int v(){return that(new Ex()[0]);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return (int)class(Int).getDeclaredMethods()[0].invoke(l,(Object)new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",180);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(124, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return (int)class(Int<int>).getDeclaredMethods()[0].invoke(l,(Object)new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",195);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(134, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return (int)class(Int<int>).getDeclaredMethods()[0].invoke(l,(Object)new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a[0]==3",cont_,"pkg/Ex",195);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test42() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return (int)class(Int).getDeclaredMethods()[0].invoke(l,(Object)new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,"pkg/Ex",180);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(124, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test43() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return (int)class(Int).getDeclaredMethods()[0].invoke(l,(Object)new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",190);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(129, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test44() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);return (int)class(Int).getDeclaredMethods()[0].invoke(l,(Object)new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a[0]==3",cont_,"pkg/Ex",190);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test45() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);var f=$lambda(Int,len,int[]);return f.call(l,new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",161);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(115, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test46() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);var f=$lambda(Int<int>,len,int[]);return f.call(l,new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(125, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test47() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);var f=$lambda(Int<int>,len,int[]);return f.call(l,new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a[0]==3",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test48() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);var f=$lambda(Int,len,int[]);return f.call(l,new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,"pkg/Ex",161);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(115, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test49() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);var f=$lambda(Int<int>,len,int[]);return f.call(l,new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(125, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test50() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int<int>)$lambda(int[],[]);var f=$lambda(Int<int>,len,int[]);return f.call(l,new int[]{2});}}public interface pkg.Int<T>{public int len(T[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a[0]==3",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test51() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(Int).getDeclaredMethods()[0].invokeDirect(new Int(),(Object)new int[1]);}}public class pkg.Int{public int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",161);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(95, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test52() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(Int<int>).getDeclaredMethods()[0].invokeDirect(new Int<int>(),(Object)new int[]{2});}}public class pkg.Int<T>{public int len(T[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(100, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test53() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(Int<int>).getDeclaredMethods()[0].invokeDirect(new Int<int>(),(Object)new int[]{2});}}public class pkg.Int<T>{public int len(T[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a[0]==3",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test54() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(Int).getDeclaredMethods()[0].invokeDirect(new Int(),(Object)new int[1]);}}public class pkg.Int{public int len(int[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,"pkg/Ex",161);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(95, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test55() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(Int<int>).getDeclaredMethods()[0].invokeDirect(new Int<int>(),(Object)new int[]{2});}}public class pkg.Int<T>{public int len(T[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("class(T)==class(int)&&a[0]==2",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(100, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test56() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(Int<int>).getDeclaredMethods()[0].invokeDirect(new Int<int>(),(Object)new int[]{2});}}public class pkg.Int<T>{public int len(T[] a){return a.length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a[0]==3",cont_,"pkg/Ex",176);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void text57() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int exmeth(){\n");
        xml_.append("  that int l=that(new Ext()[0]);\n");
        xml_.append("  Fct<int,int> fct = a -> a * l;\n");
        xml_.append("  var v = (Method)fct.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",(Object)3);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\");\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0,(Object)3);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"a\",0);\n");
        xml_.append("  class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambdaLocVars(\"a\",0);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int i){return 0;}\n");
        xml_.append(" public void this(int i){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void text58() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" static int exmeth(){\n");
        xml_.append("  that int l=that(((Int)$lambda(int[],[]))[new int[0]]);\n");
        xml_.append("  Fct<int,int> fct = a -> a * l;\n");
        xml_.append("  var v = (Method)fct.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int curr(int[] i){return i.length;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{int this(int[] i);public normal void this(int[] i){}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",301);
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(87, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(173, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_REF_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void text59() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" static int exmeth(){\n");
        xml_.append("  that int l=that(((Int)$lambda(int[],[]))[new int[0]]);\n");
        xml_.append("  Fct<int,int> fct = a -> a * l;\n");
        xml_.append("  var v = (Method)fct.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int curr(int[] i){return i.length;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{int this(int[] i);public normal void this(int[] i){}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,"pkg/Ex",301);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(87, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(173, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_REF_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void text60() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" static int exmeth(){\n");
        xml_.append("  that int l=that(((Int)new Ext().$lambda(Ext,curr,int[]))[new int[0]]);\n");
        xml_.append("  Fct<int,int> fct = a -> a * l;\n");
        xml_.append("  var v = (Method)fct.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int curr(int[] i){return i.length;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{int this(int[] i);public normal void this(int[] i){}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",317);
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(103, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(189, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_REF_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void text61() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" static int exmeth(){\n");
        xml_.append("  that int l=that(((Int)new Ext().$lambda(Ext,curr,int[]))[new int[0]]);\n");
        xml_.append("  Fct<int,int> fct = a -> a * l;\n");
        xml_.append("  var v = (Method)fct.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int curr(int[] i){return i.length;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{int this(int[] i);public normal void this(int[] i){}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,"pkg/Ex",317);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(103, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(189, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_REF_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test62() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return (int)class(Int).getDeclaredMethods()[0].invoke(l,(Object)1);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",180);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test63() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){Ex[] e=new[1];e[0]=new();return e[0].v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdGetThrownCondition(cont_,"value[0]+0==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(87, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test64() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){Ex[] e=new[1];e[0]=new();return e[0].v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdSetThrownCondition(cont_,"value[0]+0==0&&#value.v==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(79, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test65() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){Ex[] e=new[1];e[0]=new();return e[0].v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdGetThrownCondition(cont_,"{int value = 0;return #value[0]+0==value;}");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(87, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test66() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){Ex[] e=new[1];e[0]=new();return e[0].v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdSetThrownCondition(cont_,"{int value = 0;return #value[0]+0==value&&##value.v==value;}");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(79, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test67() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){Ex[] e=new[1];e[0]=new();return e[0].v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdGetThrownCondition(cont_,"value[0]+0==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test68() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){Ex[] e=new[1];e[0]=new();return e[0].v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdSetThrownCondition(cont_,"#value.v==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test69() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){int[] e={1};e[0]+=2;return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdCompoundGetThrownCondition(cont_,"value[0]+0==0&&this[value[0]]==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(67, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test70() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){int[] e={1};e[0]+=2;return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdCompoundSetThrownCondition(cont_,"value[0]+0==0&&this[value[0]]==1&&#value==3");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(70, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test71() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){int[] e={1};e[0]/=0;return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdCompoundSetErrThrownCondition(cont_,"value[0]+0==0&&this[value[0]]==1&&#value==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(70, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test72() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){int[] e=new[1];int[] f=new[1];e[0???1]=f[0???1];return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeGetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(94, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test73() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){int[] e=new[1];int[] f=new[1];e[0???1]=f[0???1];return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeSetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1&&#value.length==1&&#value[0]+0==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(92, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test74() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){int[] e=new[1];int[] f=new[1];e[0???1]+=f[0???1];return e[0];}}operator+ int[] (int[] a, int[] b) {int[] out = new int[a.length];iter(int i = 0; a.length; 1){out[i] = a[i] + b[i];}return out;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeCompoundGetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(85, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test75() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {int v;public static int exmeth(){int[] e=new[1];int[] f=new[1];e[0???1]+=f[0???1];return e[0];}}operator+ int[] (int[] a, int[] b) {int[] out = new int[a.length];iter(int i = 0; a.length; 1){out[i] = a[i] + b[i];}return out;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeCompoundSetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1&&#value.length==1&&#value[0]+0==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(92, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test76() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[][],[],int,int);int[][] e={{1,2},{3,4}};return f.call(e,1,0);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdGetArrArrIntsThrownCondition(cont_,"value[0]==1&&value[1]==0&&this[value[0]][value[1]]==3");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(115, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test77() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[][],[]=,int,int);int[][] e={{1,2},{3,4}};f.call(e,1,0,5);return e[1][0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdSetArrArrIntsThrownCondition(cont_,"value.length==2&&value[0]==1&&value[1]==0&&this[value[0]][value[1]]==3&&#value==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(109, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test78() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],[],Range);int[] e={1,2};return f.call(e,0???1)[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeGetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(101, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test79() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],[]=,Range);int[] e={1,2};f.call(e,0???1,new int[]{5});return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeSetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1&&#value.length==1&&#value[0]==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(95, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test80() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],[:],int,int);int[] e={1,2};return f.call(e,0,1)[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeGetThrownCondition(cont_,"value.step()==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(104, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test81() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],[:]=,int);int[] e={1,2};f.call(e,0,1,new int[]{5});return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeSetThrownCondition(cont_,"value.step()==1&&#value.length==1&&#value[0]==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(94, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test82() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int[] e={1,2};that int[] r=that(e[0???1]);return r[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeGetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(81, stack_.getCall(0).getTraceIndex());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(97, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test83() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int[] e={1,2};that int[] r=that(e[0???1]);r={3};return r[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeSetThrownCondition(cont_,"value.lower()==0&&value.step()==1&&value.upper()==1&&#value.length==1&&#value[0]==3");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(91, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test84() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],new,int,int);return f.call(1,2)[0][1];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInitArraysThrownCondition(cont_,"value[0]==1&&value[1]==2");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(90, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test85() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],[:]);int[] e={1,2};return f.call(e,0)[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeGetThrownCondition(cont_,"value.step()==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(96, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test86() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],[:]=);int[] e={1,2};f.call(e,0,new int[]{5});return e[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdRangeSetThrownCondition(cont_,"value.step()==1&&#value.length==1&&#value[0]==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(90, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test87() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){return new Ex<int>().exmeth();}public T exmeth(){return (new T[1][2])[0][1];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInitArraysThrownCondition(cont_,"value[0]==1&&value[1]==2");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(72, stack_.getCall(0).getTraceIndex());
        assertEq(108, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test88() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" static int exmeth(){\n");
        xml_.append("  that int l=that(((Int)new Ext().$lambda(Ext,curr,int[],int,int))[new int[1],0]);\n");
        xml_.append("  Fct<int,int> fct = a -> a * l;\n");
        xml_.append("  var v = (Method)fct.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0,(Object)3);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int curr(int[] i,int j, int r){i[j]=r;return r;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{public normal int this(int[] i,int j){return 0;}void this(int[] i,int j);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",398);
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(199, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_REF_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
        /*StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",129);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());*/
    }
    @Test
    public void test89() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" static int exmeth(){\n");
        xml_.append("  that int l=that(((Int)$lambda(int[],[]=,int))[new int[1],0]);\n");
        xml_.append("  Fct<int,int> fct = a -> a * l;\n");
        xml_.append("  var v = (Method)fct.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",0,(Object)3);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public void curr(int[] i, int r){i[0]=r;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{public normal int this(int[] i,int j){return 0;}void this(int[] i,int j);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",365);
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(180, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_REF_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
        /*StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var l=(Int)$lambda(int[],[]);return l.len(new int[1]);}}public interface pkg.Int{public int len(int[] a);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",129);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());*/
    }
    @Test
    public void test90() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {static int exmeth(){((Int<int>)$lambda(int[],[]=,int))[new int[1],0]=2;return 0;}}public interface pkg.Int<T>{public normal T this(T[] i,int j){return i[j];}void this(T[] i,int j);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",179);
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(90, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test91() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {static int exmeth(){((IntSub<int>)$lambda(int[],[]=,int))[new int[1],0]=2;return 0;}}public interface pkg.IntSub<S>:Int<S>{}public interface pkg.Int<T>{public normal T this(T[] i,int j){return i[j];}void this(T[] i,int j);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",221);
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(93, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test92() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {public int v;static int exmeth(){that int w=that(new Ext().v);Fct<int,int> fct = a -> a * w;var v = (Method)fct.metaInfo();v.getDeclaredAnonymousLambdaLocVars(\"w\",0,(Object)3);return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",33);
        writeCondition("value==3",cont_, cf("pkg.Ext", "v"));
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(147, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.FIELD,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test93() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {public int v;static int exmeth(){that int w=that(new Ext().v);Fct<int,int> fct = a -> a * w;var v = (Method)fct.metaInfo();v.getDeclaredAnonymousLambdaLocVars(\"w\",0,(Object)3);return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",33);
        writeCondition("value==2",cont_, cf("pkg.Ext", "v"));
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ext", id_, cont_);
        assertEq(0, stack_.nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test94() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {public int v;static int exmeth(){that int w=that(new Ext().v);Fct<int,int> fct = a -> a * w;var v = (Method)fct.metaInfo();v.getDeclaredAnonymousLambdaLocVars(\"w\",0);return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",33);
        read(cont_, cf("pkg.Ext", "v"));
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(81, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.FIELD,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(147, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.FIELD,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test95() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {public int v;static int exmeth(){that int w=that(new Ext().v);Fct<int,int> fct = a -> a * w;var v = (Method)fct.metaInfo();v.getDeclaredAnonymousLambdaLocVars(\"w\",(Object)3);return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",33);
        writeCondition("value==3",cont_, cf("pkg.Ext", "v"));
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(147, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.FIELD,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test96() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {public int v;static int exmeth(){that int w=that(new Ext().v);Fct<int,int> fct = a -> a * w;var v = (Method)fct.metaInfo();v.getDeclaredAnonymousLambdaLocVars(\"w\");return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",33);
        read(cont_, cf("pkg.Ext", "v"));
//        entering(cont_,"pkg/Ex",304);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(81, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.FIELD,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(147, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.FIELD,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test97() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {static int exmeth(){((IntSub<int>)new Ext().$lambda(Ext,curr,int[],int,int))[new int[1],0]=2;return 0;}public int curr(int[] i,int j, int r){i[j]=r;return r;}}public interface pkg.IntSub<S>:Int<S>{}public interface pkg.Int<T>{void this(T[] i,int j,T value);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",125);
        entering(cont_,"pkg/Ex",248);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(112, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(112, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test98() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {static int exmeth(){var a=new int[1];((IntSub<int>)new Ext().$lambda(Ext,curr,int[],int,int))[a,0]=2;return a[0];}public int curr(int[] i,int j, int r){i[j]=r;return r;}}public interface pkg.IntSub<S>:Int<S>{}public interface pkg.Int<T>{void this(T[] i,int j,T value);}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,"pkg/Ex",136);
        entering(cont_,"pkg/Ex",259);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ext", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(120, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(2, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(120, stack_.getCall(0).getTraceIndex());
        assertSame(StopDbgEnum.METHOD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(2, getNumber(stack_.getReturnedArgument()));
        assertNull(stack_.getCallingState());
    }
    @Test
    public void test99() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static Ex[] exmeth(){return new Ex[1].clone();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionClone(cont_,"this.length==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(66, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test100() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static Ex[] exmeth(){return new Ex[1].clone();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionClone(cont_,"this.length==2");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test101() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static Ex[] exmeth(){return new Ex[1].clone();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionClone(cont_,"");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(66, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test102() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static Ex[] exmeth(){var l=$lambda(Ex[],clone);return l.call(new Ex[1]);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionClone(cont_,"this.length==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(84, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test103() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static Ex[] exmeth(){return (Ex[])class(Ex[]).getDeclaredMethods()[0].invoke(new Ex[1]);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionClone(cont_,"this.length==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(98, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test104() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInherit(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test105() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex2[0].length;}}public class pkg.Ex2{}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInherit(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test106() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInheritCondition(cont_,"this.length==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test107() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[0].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInheritCondition(cont_,"this.length==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test108() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int field=0;public static int exmeth(){return new Ex[1].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"length==1+static(pkg.Ex).field");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(91, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    private void writeCondition(String _newValue,ResultContext _cont, ClassField _cf) {
        write(_cont, _cf);
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        WatchPointBlockPair p_ = pair(_cont, _cf);
        WatchPoint wp_ = p_.getValue();
        wp_.getResultWrite().analyze(p_,_newValue,"", "", _cont, new DefContextGenerator());
        assertEq(_newValue,wp_.getResultWrite().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_newValue, p_, _cont, type_, new DefContextGenerator(), true);
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultWrite().result(res_,_newValue);
    }
    private void write(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(false);
        pair(_cont, _cf).getValue().setWrite(true);
        pair(_cont, _cf).getValue().setCompoundRead(false);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }
    private void read(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(true);
        pair(_cont, _cf).getValue().setWrite(false);
        pair(_cont, _cf).getValue().setCompoundRead(false);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }

    private static WatchPointBlockPair pair(ResultContext _cont, ClassField _cf) {
        int n_ = _cont.getPageEl().getAnaClassBody(_cf.getClassName()).getNumberAll();
        return _cont.getPairWatch(true,n_,_cf.getFieldName());
    }

    private ClassField cf(String _cl, String _f) {
        return new ClassField(_cl,_f);
    }
    /*
    @Test
    public void calculate150() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<int,Fct<int,int>> fct = a -> b -> a * b;\n");
        xml_.append("  var v = class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"a\",(Object)3);\n");
        xml_.append("  return (int)v.invoke(null,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        assertEq(6, getNumber(calculateNormal("pkg.Ext", id_, args_, cont_)));
    }
     @Test
    public void calculate152() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<int,Fct<int,int>> fct = a -> b -> a * b;\n");
        xml_.append("  var v = class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"a\",(Object)3);\n");
        xml_.append("  return (int)v.getDeclaredAnonymousLambdaLocVars(\"a\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        assertEq(3, getNumber(calculateNormal("pkg.Ext", id_, args_, cont_)));
    }
    @Test
    public void calculate168() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  Fct<int,Fct<int,int>> fct = a -> b -> a * b;\n");
        xml_.append("  var v = class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"a\",0,(Object)3);\n");
        xml_.append("  return v.getDeclaredAnonymousLambdaLocVars(\"i\",0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE, ret_.getStruct());
    }

    @Test
    public void calculate170() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  Fct<int,Fct<int,int>> fct = a -> b -> a * b;\n");
        xml_.append("  var v = class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"a\",1,(Object)3);\n");
        xml_.append("  return v.getDeclaredAnonymousLambdaLocVars(\"a\",0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE, ret_.getStruct());
    }
*/
    private StandardNamedFunction enteringExiting(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = toggled(_cont, _clName, _id);
        _cont.getPair(s_).getValue().setEntry(true);
        _cont.getPair(s_).getValue().setExit(true);
        return s_;
    }
    private StandardNamedFunction toggled(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardType v_ = _cont.getContext().getStandards().getStandards().getVal(_clName);
        StandardNamedFunction s_ = _id.look(v_).first();
        _cont.toggleBreakPoint(v_,s_);
        return s_;
    }
    //
    private void stdThrownCondition(ResultContext _cont, String _condition) {
        std(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultLength().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void stdThrownConditionClone(ResultContext _cont, String _condition) {
        stdClone(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultClone().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultClone().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void stdGetThrownCondition(ResultContext _cont, String _condition) {
        stdGet(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultIntGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultIntGet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdGetArrArrIntsThrownCondition(ResultContext _cont, String _condition) {
        stdGetArrArrInt(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultIntGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultIntGet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdSetArrArrIntsThrownCondition(ResultContext _cont, String _condition) {
        stdSetArrArrInt(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultIntGetSet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultIntGetSet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void stdSetThrownCondition(ResultContext _cont, String _condition) {
        stdSet(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultIntSet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultIntSet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void conditionUnkThrown(ResultContext _cont) {
        unkThrown(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,"0==0","", "", _cont,new DefContextGenerator());
        assertEq("0==0",wp_.getResultLength().getResultStr());

//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr("0==0", p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, "0==0");
    }

    private void stdParamConditionThrown(ResultContext _cont, String _condition) {
        stdParam(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex<int>", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultLength().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdIncThrownCondition(ResultContext _cont, String _condition) {
        stdInc(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME_FAMILY);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultLength().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdCompoundGetThrownCondition(ResultContext _cont, String _condition) {
        stdGetInts(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultIntCompoundGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultIntCompoundGet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void stdCompoundSetThrownCondition(ResultContext _cont, String _condition) {
        stdSetInts(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultIntCompoundSet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultIntCompoundSet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdCompoundSetErrThrownCondition(ResultContext _cont, String _condition) {
        stdSetErrInts(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultIntCompoundSetErr().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultIntCompoundSetErr().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdRangeGetThrownCondition(ResultContext _cont, String _condition) {
        stdRangeGetInts(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultRangeGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultRangeGet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdInitArraysThrownCondition(ResultContext _cont, String _condition) {
        stdInits(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultInitArray().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultInitArray().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void stdRangeSetThrownCondition(ResultContext _cont, String _condition) {
        stdRangeSetInts(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultRangeSet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultRangeSet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdRangeCompoundGetThrownCondition(ResultContext _cont, String _condition) {
        stdRangeCompoundGetInts(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultRangeCompoundGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultRangeCompoundGet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void stdRangeCompoundSetThrownCondition(ResultContext _cont, String _condition) {
        stdRangeCompoundSetInts(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultRangeCompoundSet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultRangeCompoundSet().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }
    private void unkThrown(ResultContext _cont) {
        _cont.toggleArrPoint("", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(true);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }
    private void std(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(true);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdNotLen(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }
    private void ints(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(true);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void intsNotEx(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME_FAMILY);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME_FAMILY).getValue();
        disable(val_);
        val_.setLength(true);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }
    private void stdParam(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex<int>", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex<int>", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(true);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }
    private void stdInc(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex<?>", ExcPointBlockKey.SAME_FAMILY);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME_FAMILY).getValue();
        disable(val_);
        val_.setLength(true);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void allArr(ResultContext _cont) {
        _cont.toggleArrPoint("", ExcPointBlockKey.SAME_FAMILY);
        ArrPoint val_ = _cont.getPairArr("", ExcPointBlockKey.SAME_FAMILY).getValue();
        disable(val_);
        val_.setLength(true);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdGet(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(true);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdSet(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(true);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdGetInts(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(true);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdSetInts(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(true);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdSetErrInts(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(true);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdRangeGetInts(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(true);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdRangeSetInts(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(true);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
    }

    private void stdRangeCompoundGetInts(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(true);
        val_.setRangeCompoundSet(false);
    }

    private void stdRangeCompoundSetInts(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(false);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(true);
    }

    private void stdGetArrArrInt(ResultContext _cont) {
        _cont.toggleArrPoint("[[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(false);
        val_.setIntGet(true);
        val_.setIntSet(false);
        val_.setIntCompoundGet(false);
        val_.setIntCompoundSet(false);
        val_.setIntCompoundSetErr(false);
        val_.setRangeGet(false);
        val_.setRangeSet(false);
        val_.setRangeCompoundGet(false);
        val_.setRangeCompoundSet(false);
        val_.setIntGetSet(false);
    }

    private void stdSetArrArrInt(ResultContext _cont) {
        _cont.toggleArrPoint("[[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setIntGetSet(true);
        val_.setInitArray(true);
        val_.setIntSet(true);
    }

    private void stdInits(ResultContext _cont) {
        _cont.toggleArrPoint("[[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setInitArray(true);
    }
    private void unkThrownClone(ResultContext _cont) {
        _cont.toggleArrPoint("", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setClone(true);
    }
    private void stdClone(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setClone(true);
    }
    private void intsClone(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setClone(true);
    }

    private void intsNotExClone(ResultContext _cont) {
        _cont.toggleArrPoint("[int", ExcPointBlockKey.SAME_FAMILY);
        ArrPoint val_ = _cont.getPairArr("[int", ExcPointBlockKey.SAME_FAMILY).getValue();
        disable(val_);
        val_.setClone(true);
    }
    private void stdParamClone(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex<int>", ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex<int>", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setClone(true);
    }
    private void stdIncClone(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex<?>", ExcPointBlockKey.SAME_FAMILY);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME_FAMILY).getValue();
        disable(val_);
        val_.setClone(true);
    }

    private void allArrClone(ResultContext _cont) {
        _cont.toggleArrPoint("", ExcPointBlockKey.SAME_FAMILY);
        ArrPoint val_ = _cont.getPairArr("", ExcPointBlockKey.SAME_FAMILY).getValue();
        disable(val_);
        val_.setClone(true);
    }
    private void disable(ArrPoint _val) {
        _val.setLength(false);
        _val.setIntGet(false);
        _val.setIntSet(false);
        _val.setIntCompoundGet(false);
        _val.setIntCompoundSet(false);
        _val.setIntCompoundSetErr(false);
        _val.setRangeGet(false);
        _val.setRangeSet(false);
        _val.setRangeCompoundGet(false);
        _val.setRangeCompoundSet(false);
        _val.setIntGetSet(false);
        _val.setInitArray(false);
        _val.setClone(false);
    }

    private void enteringCondition(String _newValue,ResultContext _cont, String _file, int _offset) {
        AbsPairPoint p_ = entering(_cont, _file, _offset);
        MethodPointBlockPair wp_ = ((MethodPointBlockPair)p_);
        wp_.getValue().getResultEntry().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultEntry().getResultStr());
    }
    private void exitingCondition(String _newValue,ResultContext _cont, String _file, int _offset) {
        AbsPairPoint p_ = exiting(_cont, _file, _offset);
        MethodPointBlockPair wp_ = ((MethodPointBlockPair)p_);
        wp_.getValue().getResultExit().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultExit().getResultStr());
    }
    private AbsPairPoint entering(ResultContext _cont, String _file, int _offset) {
        AbsPairPoint p_ = _cont.toggleWatchPoint(_file, _offset);
        ((MethodPointBlockPair)p_).getValue().setEntry(true);
        ((MethodPointBlockPair)p_).getValue().setExit(false);
        return p_;
    }

    private AbsPairPoint exiting(ResultContext _cont, String _file, int _offset) {
        AbsPairPoint p_ = _cont.toggleWatchPoint(_file, _offset);
        ((MethodPointBlockPair)p_).getValue().setEntry(false);
        ((MethodPointBlockPair)p_).getValue().setExit(true);
        return p_;
    }

    private AbsPairPoint enteringExiting(ResultContext _cont, String _file, int _offset) {
        AbsPairPoint p_ = _cont.toggleWatchPoint(_file, _offset);
        ((MethodPointBlockPair)p_).getValue().setEntry(true);
        ((MethodPointBlockPair)p_).getValue().setExit(true);
        return p_;
    }
    private void stdInheritCondition(ResultContext _cont, String _condition) {
        stdInherit(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.INHERIT);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultLength().getResultStr());
    }
    private void stdInherit(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex", ExcPointBlockKey.INHERIT);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.INHERIT).getValue();
        disable(val_);
        val_.setLength(true);
    }
}
