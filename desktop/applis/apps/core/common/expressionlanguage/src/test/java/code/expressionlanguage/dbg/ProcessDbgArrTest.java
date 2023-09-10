package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.dbg.ArrPoint;
import code.expressionlanguage.exec.dbg.ArrPointBlockPair;
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
}
