package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.dbg.ArrPoint;
import code.expressionlanguage.exec.dbg.ArrPointBlockPair;
import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(81, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[1].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringExiting(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(81, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_STD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(81, nowTrace(stack_));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringExiting(cont_,"pkg/Ex",129);
        ints(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, nowTrace(stack_));
        assertSame(StopDbgEnum.METHOD_ABS_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(86, nowTrace(stack_));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
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
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",(Object)3);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int curr(int[] i){return i.length;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{int this(int[] i);public normal void this(int[] i){}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        entering(cont_,"pkg/Ex",309);
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
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"l\",(Object)3);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public int curr(int[] i){return i.length;}\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int{int this(int[] i);public normal void this(int[] i){}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        exiting(cont_,"pkg/Ex",309);
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
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
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", true);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,_condition,"",_cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultLength().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void conditionUnkThrown(ResultContext _cont) {
        unkThrown(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("", true);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,"0==0","",_cont,new DefContextGenerator());
        assertEq("0==0",wp_.getResultLength().getResultStr());

//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr("0==0", p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, "0==0");
    }

    private void stdParamConditionThrown(ResultContext _cont, String _condition) {
        stdParam(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex<int>", true);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,_condition,"",_cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultLength().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdIncThrownCondition(ResultContext _cont, String _condition) {
        stdInc(_cont);
        ArrPointBlockPair p_ = _cont.getPairArr("[pkg.Ex", false);
        ArrPoint wp_ = p_.getValue();
        wp_.getResultLength().analyze(p_,_condition,"",_cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultLength().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeArr(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void unkThrown(ResultContext _cont) {
        _cont.toggleArrPoint("",true);
        ArrPoint val_ = _cont.getPairArr("", true).getValue();
        val_.setLength(true);
    }
    private void std(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex",true);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", true).getValue();
        val_.setLength(true);
    }

    private void stdNotLen(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex",true);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", true).getValue();
        val_.setLength(false);
    }
    private void ints(ResultContext _cont) {
        _cont.toggleArrPoint("[int",true);
        ArrPoint val_ = _cont.getPairArr("[int", true).getValue();
        val_.setLength(true);
    }

    private void intsNotEx(ResultContext _cont) {
        _cont.toggleArrPoint("[int",false);
        ArrPoint val_ = _cont.getPairArr("[int", false).getValue();
        val_.setLength(true);
    }
    private void stdParam(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex<int>",true);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex<int>", true).getValue();
        val_.setLength(true);
    }
    private void stdInc(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex<?>",false);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", false).getValue();
        val_.setLength(true);
    }

    private void allArr(ResultContext _cont) {
        _cont.toggleArrPoint("",false);
        ArrPoint val_ = _cont.getPairArr("", false).getValue();
        val_.setLength(true);
    }

    private void enteringCondition(String _newValue,ResultContext _cont, String _file, int _offset) {
        entering(_cont, _file, _offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
        MethodPointBlockPair wp_ = _cont.getPair(id_);
        wp_.getValue().getResultEntry().analyze(wp_,_newValue,"",_cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultEntry().getResultStr());
    }
    private void exitingCondition(String _newValue,ResultContext _cont, String _file, int _offset) {
        exiting(_cont, _file, _offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
        MethodPointBlockPair wp_ = _cont.getPair(id_);
        wp_.getValue().getResultExit().analyze(wp_,_newValue,"",_cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultExit().getResultStr());
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

    private void enteringExiting(ResultContext _cont, String _file, int _offset) {
        _cont.toggleBreakPoint(_file,_offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
        _cont.getPair(id_).getValue().setEntry(true);
        _cont.getPair(id_).getValue().setExit(true);
    }
}
