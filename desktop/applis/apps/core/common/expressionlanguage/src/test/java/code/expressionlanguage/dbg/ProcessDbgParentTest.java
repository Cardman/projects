package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.expressionlanguage.exec.dbg.ParPoint;
import code.expressionlanguage.exec.dbg.ParPointBlockPair;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgParentTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v(){return 0;}public static int exmeth(){return new Ex().v();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(89, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",32);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
        assertSame(StopDbgEnum.PARENT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
        assertEq(77, nowTrace(stack_));
        assertSame(StopDbgEnum.FIELD,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        anyPar(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdWide(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){Ex e=null;return e.v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        anyPar(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(80, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){Ex e=null;return e.v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        unkThrown(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(80, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().new Inner().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInner(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(109, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().new Inner().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionInner(cont_,"value==1&&Class.getClass($parent)==class(Ex)&&$parent.v==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(109, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().new Inner().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionInner(cont_,"value==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){Ex e=null;return e.v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        anyParDisableOne(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){Ex e=null;return e.v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        anyParDisableTwo(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"value==0&&v==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(97, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"value==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){new Ex().v=2;return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(71, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v=1;public static int exmeth(){new Ex().v=2;return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(73, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public int this(int i){return 0;}public void this(int i){}public static int exmeth(){new Ex()[0]=2;return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(130, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public int this(int i){return 0;}public void this(int i){}public static int exmeth(){return new Ex()[0];}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(134, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public int this(int i){return 0;}public void this(int i){}public static int exmeth(){new Ex()[0]+=2;return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(127, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().new Inner().$parent.v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionInner(cont_,"value==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(109, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{public int v(){return this.v;}}public static int exmeth(){return new Ex().new Inner().v();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionInner(cont_,"value==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(80, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{public int v(){return Ex.this.v;}}public static int exmeth(){return new Ex().new Inner().v();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionInner(cont_,"value==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(78, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){var f=new Ex().$lambda(Ex,,v);return f.call();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"value==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(100, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public static int exmeth(){var f=new Ex().$lambda(Ex,,v,int);f.call(2);return 0;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_,"value==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(97, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v(){return 0;}public static int exmeth(){var f=new Ex().$lambda(Ex,v);return f.call();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(111, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v(){return 0;}public static int exmeth(){var f=$lambda(Ex[],[]);return f.call(new Ex[0]);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        std(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){var f=$lambda(Ex.Inner,,$parent);return f.call(new Ex().new Inner()).v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionInner(cont_,"value==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(123, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownConditionWide(cont_,"value==0&&v==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(97, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        conditionUnkThrownAny(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(97, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInheritThrownCondition(cont_,"value==0&&v==0");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(97, nowTrace(stack_));
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v;public class Inner{}public static int exmeth(){return new Ex().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInheritThrownCondition(cont_,"value==1");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    private void stdThrownCondition(ResultContext _cont, String _condition) {
        std(_cont);
        ParPointBlockPair p_ = _cont.getPairPar("pkg.Ex", ExcPointBlockKey.SAME);
        ParPoint wp_ = p_.getValue();
        wp_.getResultGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultGet().getResultStr());
    }

    private void stdInheritThrownCondition(ResultContext _cont, String _condition) {
        stdInherit(_cont);
        ParPointBlockPair p_ = _cont.getPairPar("pkg.Ex", ExcPointBlockKey.INHERIT);
        ParPoint wp_ = p_.getValue();
        wp_.getResultGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultGet().getResultStr());
    }
    private void stdThrownConditionInner(ResultContext _cont, String _condition) {
        stdInner(_cont);
        ParPointBlockPair p_ = _cont.getPairPar("pkg.Ex..Inner", ExcPointBlockKey.SAME);
        ParPoint wp_ = p_.getValue();
        wp_.getResultGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultGet().getResultStr());
    }
    private void conditionUnkThrown(ResultContext _cont, String _cond) {
        unkThrown(_cont);
        ParPointBlockPair p_ = _cont.getPairPar("", ExcPointBlockKey.SAME);
        ParPoint wp_ = p_.getValue();
        wp_.getResultGet().analyze(p_,_cond,"", "", _cont,new DefContextGenerator());
        assertEq(_cond,wp_.getResultGet().getResultStr());
    }

    private void conditionUnkThrownAny(ResultContext _cont) {
        anyPar(_cont);
        ParPointBlockPair p_ = _cont.getPairPar("", ExcPointBlockKey.SAME_FAMILY);
        ParPoint wp_ = p_.getValue();
        wp_.getResultGet().analyze(p_,"0==0","", "", _cont,new DefContextGenerator());
        assertEq("0==0",wp_.getResultGet().getResultStr());
    }
    private void stdThrownConditionWide(ResultContext _cont, String _condition) {
        stdWide(_cont);
        ParPointBlockPair p_ = _cont.getPairPar("pkg.Ex", ExcPointBlockKey.SAME_FAMILY);
        ParPoint wp_ = p_.getValue();
        wp_.getResultGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultGet().getResultStr());
    }
    private void unkThrown(ResultContext _cont) {
        _cont.toggleParPoint("",ExcPointBlockKey.SAME);
        ParPoint val_ = _cont.getPairPar("", ExcPointBlockKey.SAME).getValue();
        val_.setGet(true);
    }
    private void std(ResultContext _cont) {
        _cont.toggleParPoint("pkg.Ex",ExcPointBlockKey.SAME);
        ParPoint val_ = _cont.getPairPar("pkg.Ex", ExcPointBlockKey.SAME).getValue();
        val_.setGet(true);
    }
    private void stdInherit(ResultContext _cont) {
        _cont.toggleParPoint("pkg.Ex",ExcPointBlockKey.INHERIT);
        ParPoint val_ = _cont.getPairPar("pkg.Ex", ExcPointBlockKey.INHERIT).getValue();
        val_.setGet(true);
    }

    private void stdInner(ResultContext _cont) {
        _cont.toggleParPoint("pkg.Ex..Inner",ExcPointBlockKey.SAME);
        ParPoint val_ = _cont.getPairPar("pkg.Ex..Inner", ExcPointBlockKey.SAME).getValue();
        val_.setGet(true);
    }
    private void anyPar(ResultContext _cont) {
        _cont.toggleParPoint("",ExcPointBlockKey.SAME_FAMILY);
        ParPoint val_ = _cont.getPairPar("", ExcPointBlockKey.SAME_FAMILY).getValue();
        val_.setGet(true);
    }
    private void stdWide(ResultContext _cont) {
        _cont.toggleParPoint("pkg.Ex",ExcPointBlockKey.SAME_FAMILY);
        ParPoint val_ = _cont.getPairPar("pkg.Ex", ExcPointBlockKey.SAME_FAMILY).getValue();
        val_.setGet(true);
    }
    private void anyParDisableOne(ResultContext _cont) {
        _cont.toggleParPoint("",ExcPointBlockKey.SAME_FAMILY);
        ParPoint val_ = _cont.getPairPar("", ExcPointBlockKey.SAME_FAMILY).getValue();
        val_.setGet(false);
    }

    private void anyParDisableTwo(ResultContext _cont) {
        _cont.toggleParPoint("",ExcPointBlockKey.SAME_FAMILY);
        _cont.getPairPar("", ExcPointBlockKey.SAME_FAMILY).getValue().setEnabled(false);
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
}
