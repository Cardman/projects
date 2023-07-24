package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.ExcPoint;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgExceptionTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.NO);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(69, nowTrace(stack_));
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.NO);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        npe(cont_, ConditionReturn.NO);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        npe(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw 10;\n");
        xml_.append("  } catch (10) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        nbe(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(75, nowTrace(stack_));
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw 10;\n");
        xml_.append("  } catch (10) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        nbe(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw 10;\n");
        xml_.append("  } catch (8) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  } catch (10) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        nbe(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(104, nowTrace(stack_));
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw 10;\n");
        xml_.append("  } catch (8) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  } catch (10) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        nbe(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (int e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(110, nowTrace(stack_));
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (int e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw null;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  } catch {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        unk(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(113, nowTrace(stack_));
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw null;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  } catch {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        unk(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (:) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
    }
    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (:) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.YES);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.CALL_EX);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(69, nowTrace(stack_));
    }
    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.CALL_EX);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(77, nowTrace(next_));
    }
    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return 1/0;\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        div(cont_, ConditionReturn.CALL_EX);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        std(cont_, ConditionReturn.NO);
        condition("v==5",cont_,"pkg.Ex");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(67, nowTrace(stack_));
    }
    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        std(cont_, ConditionReturn.NO);
        condition("v==5",cont_,"pkg.Ex");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        std(cont_, ConditionReturn.NO);
        condition("v==4",cont_,"pkg.Ex");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw null;\n");
        xml_.append("  } catch {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        unk(cont_, ConditionReturn.NO);
        condition("0==0",cont_,"");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(67, nowTrace(stack_));
    }
    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw null;\n");
        xml_.append("  } catch {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        unk(cont_, ConditionReturn.NO);
        condition("0==0",cont_,"");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        std(cont_, ConditionReturn.NO);
        condition("",cont_,"pkg.Ex");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(67, nowTrace(stack_));
    }
    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        std(cont_, ConditionReturn.NO);
        condition("",cont_,"pkg.Ex");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    private void npe(ResultContext _cont, ConditionReturn _cond) {
        _cont.getContext().getClasses().getDebugMapping().getExceptions().addEntry(_cont.getContext().getStandards().getCoreNames().getAliasNullPe(), _cond);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasNullPe(),_cont);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasNullPe()).getValue().setConditionReturn(_cond);
    }

    private void div(ResultContext _cont, ConditionReturn _cond) {
        _cont.getContext().getClasses().getDebugMapping().getExceptions().addEntry(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(), _cond);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(),_cont);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero()).getValue().setConditionReturn(_cond);
    }

    private void nbe(ResultContext _cont, ConditionReturn _cond) {
        _cont.getContext().getClasses().getDebugMapping().getExceptions().addEntry(_cont.getContext().getStandards().getNbAlias().getAliasInteger(), _cond);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleExcPoint(_cont.getContext().getStandards().getNbAlias().getAliasInteger(),_cont);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairExc(_cont.getContext().getStandards().getNbAlias().getAliasInteger()).getValue().setConditionReturn(_cond);
    }

    private void unk(ResultContext _cont, ConditionReturn _cond) {
        _cont.getContext().getClasses().getDebugMapping().getExceptions().addEntry("", _cond);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleExcPoint("",_cont);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairExc("").getValue().setConditionReturn(_cond);
    }

    private void std(ResultContext _cont, ConditionReturn _cond) {
        _cont.getContext().getClasses().getDebugMapping().getExceptions().addEntry("pkg.Ex", _cond);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleExcPoint("pkg.Ex",_cont);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairExc("pkg.Ex").getValue().setConditionReturn(_cond);
    }
    private void condition(String _newValue,ResultContext _cont, String _cf) {
        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        ExcPoint wp_ = _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairExc(_cf).getValue();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_newValue, _cf, _cont, type_, new DefContextGenerator());
        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
        wp_.getResult().setResult(ResultContextLambda.okOrNull(res_));
        wp_.getResult().setResultStr(ResultContextLambda.okOrEmpty(res_,_newValue));
    }
}
