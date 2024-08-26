package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.WatchResults;
import code.expressionlanguage.exec.dbg.ExcPoint;
import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divThrown(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divThrown(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        npeThrown(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        npe(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbe(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbe(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbe(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbe(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        unkCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        unkCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divCaught(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        div(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        div(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        div(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_, "v==5");
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_, "v==5");
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_, "v==4");
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        conditionUnkThrown(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        conditionUnkThrown(cont_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_, "");
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdThrownCondition(cont_, "");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdIncThrownCondition(cont_, "v==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(70, nowTrace(stack_));
    }
    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdIncThrownCondition(cont_, "v==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdIncThrownCondition(cont_, "v==4");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrown(cont_, "v==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(70, nowTrace(stack_));
    }
    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrown(cont_, "v==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrown(cont_, "v==4");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test33() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbeCaughtCondition(cont_, "this-2==8");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test34() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbeCaughtCondition(cont_, "this-2==7");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new int[]{10};\n");
        xml_.append("  } catch (int[] a) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbeaCaughtCondition(cont_, "this[0]-2==8");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new int[]{10};\n");
        xml_.append("  } catch (int[] a) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbeaCaughtCondition(cont_, "this[0]-2==7");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test37() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbeCaughtCondition(cont_, "{static class Loc{public int v = 8;}return this-2==new Loc().v;}");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test38() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        nbeCaughtCondition(cont_, "{static class Loc{public int v = 7;}return this-2==new Loc().v;}");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrown(cont_, "v-1==4");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(70, nowTrace(stack_));
    }
    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrown(cont_, "v-1==4");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex<int>(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final T v;\n");
        xml_.append(" public(T v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrown(cont_, "v-1==3");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test42() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return bad();\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int bad(){\n");
        xml_.append("  return 1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divThrown(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(68, stack_.getCall(0).getTraceIndex());
        assertEq(154, stack_.getCall(1).getTraceIndex());
    }
    @Test
    public void test43() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return bad();\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int bad(){\n");
        xml_.append("  return 1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divThrownProp(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(1, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(154, stack_.getBreakPointInfo().getBreakPointMiddleInfo().getExiting().getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test44() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return bad();\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int bad(){\n");
        xml_.append("  return 1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test45() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   return bad();\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int bad(){\n");
        xml_.append("  return 1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divThrownProp(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(1, dbgContinueNormalValueStepRet(stack_, cont_.getContext()).nbPages());
        assertEq(154, stack_.getBreakPointInfo().getBreakPointMiddleInfo().getExiting().getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test46() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int catching(){try{return 1/0;}catch(Error e:e == v()){return 1;}}public static Object v(){throw null;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        divThrown(cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(62, now(stack_));
        assertSame(StopDbgEnum.EXCEPTION,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(85, now(stack_));
        assertSame(StopDbgEnum.EXCEPTION,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(cont_.getContext().getStandards().getCoreNames().getAliasDivisionZero(),getTrueException(stack_).getClassName(cont_.getContext()));
    }
    @Test
    public void test47() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int catching(){try{return 1/0;}catch(Error e:e == v()){return 1;}}public static Object v(){throw null;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        unkThrown(cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(132, now(stack_));
        assertSame(StopDbgEnum.EXCEPTION,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(cont_.getContext().getStandards().getCoreNames().getAliasDivisionZero(),getTrueException(stack_).getClassName(cont_.getContext()));
    }
    @Test
    public void test48() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        any(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test49() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw null;\n");
        xml_.append("  } catch {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        any(cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, nowTrace(stack_));
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test50() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownCondition(cont_, "v==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(67, nowTrace(stack_));
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test51() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex2(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public final int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownCondition(cont_, "");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test52() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInherit(cont_);
        ExcPoint val_ = cont_.getPairExc("pkg.Ex", ExcPointBlockKey.INHERIT).getValue();
        val_.getResultThrown().getEnabled().set(false);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test53() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownCondition(cont_, "v==4");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test54() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:Ex1:Ex2 {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex1 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownConditionInt1(cont_, "(((Ex)this).v*=2)==10");
        stdInehritThrownConditionInt2(cont_, "(++((Ex)this).v)==6");
        cont_.getPairExc("pkg.Ex1", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(1);
        cont_.getPairExc("pkg.Ex2", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(2);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(10, NumParsers.convertToNumber(WatchResults.dynamicAnalyze("((Ex)this).v",stack_,cont_,new DefContextGenerator()).getWatchedObject()).intStruct());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test55() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:Ex1:Ex2 {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex1 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownConditionInt1(cont_, "(((Ex)this).v*=2)==10");
        stdInehritThrownConditionInt2(cont_, "(++((Ex)this).v)==6");
        cont_.getPairExc("pkg.Ex1", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(2);
        cont_.getPairExc("pkg.Ex2", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(1);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(6, NumParsers.convertToNumber(WatchResults.dynamicAnalyze("((Ex)this).v",stack_,cont_,new DefContextGenerator()).getWatchedObject()).intStruct());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test56() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:Ex1:Ex2 {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex1 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownConditionInt1(cont_, "(((Ex)this).v*=2)==10");
        stdInehritThrownConditionInt2(cont_, "(++((Ex)this).v)==6");
        cont_.getPairExc("pkg.Ex1", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(1);
        cont_.getPairExc("pkg.Ex2", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(1);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(10, NumParsers.convertToNumber(WatchResults.dynamicAnalyze("((Ex)this).v",stack_,cont_,new DefContextGenerator()).getWatchedObject()).intStruct());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test57() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:Ex1:Ex2 {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex1 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownConditionInt1(cont_, "(((Ex)this).v*=2)==9");
        stdInehritThrownConditionInt2(cont_, "(++((Ex)this).v)==11");
        cont_.getPairExc("pkg.Ex1", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(1);
        cont_.getPairExc("pkg.Ex2", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(2);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(11, NumParsers.convertToNumber(WatchResults.dynamicAnalyze("((Ex)this).v",stack_,cont_,new DefContextGenerator()).getWatchedObject()).intStruct());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test58() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:Ex1:Ex2 {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try{\n");
        xml_.append("   throw new Ex(5);\n");
        xml_.append("  } catch (Object e) {\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public int v;\n");
        xml_.append(" public(int v){\n");
        xml_.append("  this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex1 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdInehritThrownConditionInt1(cont_, "(((Ex)this).v*=2)==12");
        stdInehritThrownConditionInt2(cont_, "(++((Ex)this).v)==7");
        cont_.getPairExc("pkg.Ex1", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(2);
        cont_.getPairExc("pkg.Ex2", ExcPointBlockKey.INHERIT).getValue().getResultThrown().getPref().set(1);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(12, NumParsers.convertToNumber(WatchResults.dynamicAnalyze("((Ex)this).v",stack_,cont_,new DefContextGenerator()).getWatchedObject()).intStruct());
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test59() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){try{throw new Ex<?int>[]{new Ex<int>(5)};} catch (Object e) {return 1;}}public final T v;public(T v){this.v=v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrownWcSub(cont_, "this[0].v+0==5");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(61, nowTrace(stack_));
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test60() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){try{throw new Ex<!int>[]{new Ex<int>(5)};} catch (Object e) {return 1;}}public T v;public(T v){this.v=v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrownWcSup(cont_, "(this[0].v=6)==6");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(61, nowTrace(stack_));
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test61() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){try{throw new Ex<?int>[]{new Ex<int>(5)};} catch (Object e) {return 1;}}public final T v;public(T v){this.v=v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrownWcSub(cont_, "{static class ExSub{int v;}return this[0].v+new ExSub().v==5;}");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(61, nowTrace(stack_));
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test62() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {public static int exmeth(){try{throw new Ex<!int>[]{new Ex<int>(5)};} catch (Object e) {return 1;}}public T v;public(T v){this.v=v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        stdParamConditionThrownWcSup(cont_, "{static class ExSub{int v;}return (this[0].v=6+new ExSub().v)==6;}");
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(61, nowTrace(stack_));
        assertEq(0,dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    private void stdThrownCondition(ResultContext _cont, String _condition) {
        std(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("pkg.Ex", ExcPointBlockKey.SAME);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdInehritThrownCondition(ResultContext _cont, String _condition) {
        stdInherit(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("pkg.Ex", ExcPointBlockKey.INHERIT);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdInehritThrownConditionInt1(ResultContext _cont, String _condition) {
        stdInheritInt1(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("pkg.Ex1", ExcPointBlockKey.INHERIT);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
    }

    private void stdInehritThrownConditionInt2(ResultContext _cont, String _condition) {
        stdInheritInt2(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("pkg.Ex2", ExcPointBlockKey.INHERIT);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
    }
    private void conditionUnkThrown(ResultContext _cont) {
        unkThrown(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("", ExcPointBlockKey.SAME);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,"0==0","", "", _cont,new DefContextGenerator());
        assertEq("0==0",wp_.getResultThrown().getResultStr());

//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc("0==0", p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, "0==0");
    }

    private void stdParamConditionThrown(ResultContext _cont, String _condition) {
        stdParam(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("pkg.Ex<int>", ExcPointBlockKey.SAME);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void stdParamConditionThrownWcSub(ResultContext _cont, String _condition) {
        stdParamWcSub(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("[pkg.Ex<?int>", ExcPointBlockKey.SAME);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
    }
    private void stdParamConditionThrownWcSup(ResultContext _cont, String _condition) {
        stdParamWcSup(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("[pkg.Ex<!int>", ExcPointBlockKey.SAME);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
    }
    private void nbeCaughtCondition(ResultContext _cont, String _condition) {
        nbe(_cont);
        String cf_ = _cont.getContext().getStandards().getNbAlias().getAliasInteger();
        ExcPointBlockPair p_ = _cont.getPairExc(cf_, ExcPointBlockKey.SAME);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultCaught().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultCaught().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultCaught().result(res_, _condition);
    }

    private void nbeaCaughtCondition(ResultContext _cont, String _condition) {
        nbea(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("[int", ExcPointBlockKey.SAME);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultCaught().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultCaught().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultCaught().result(res_, _condition);
    }

    private void stdIncThrownCondition(ResultContext _cont, String _condition) {
        stdInc(_cont);
        ExcPointBlockPair p_ = _cont.getPairExc("pkg.Ex", ExcPointBlockKey.SAME_FAMILY);
        ExcPoint wp_ = p_.getValue();
        wp_.getResultThrown().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultThrown().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeExc(_condition, p_, _cont, _cont.getPageEl().getAliasPrimBoolean(), new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultThrown().result(res_, _condition);
    }

    private void npeThrown(ResultContext _cont) {
        _cont.toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasNullPe(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasNullPe(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }

    private void npe(ResultContext _cont) {
        _cont.toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasNullPe(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasNullPe(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(false);
        val_.setCaught(true);
        val_.setPropagated(false);
    }

    private void divThrown(ResultContext _cond) {
        _cond.toggleExcPoint(_cond.getContext().getStandards().getCoreNames().getAliasDivisionZero(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cond.getPairExc(_cond.getContext().getStandards().getCoreNames().getAliasDivisionZero(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }

    private void divThrownProp(ResultContext _cond) {
        _cond.toggleExcPoint(_cond.getContext().getStandards().getCoreNames().getAliasDivisionZero(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cond.getPairExc(_cond.getContext().getStandards().getCoreNames().getAliasDivisionZero(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(true);
    }

    private void divCaught(ResultContext _cont) {
        _cont.toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(false);
        val_.setCaught(true);
        val_.setPropagated(false);
    }


    private void div(ResultContext _cont) {
        _cont.toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(true);
        val_.setPropagated(false);
    }

    private void nbe(ResultContext _cont) {
        _cont.toggleExcPoint(_cont.getContext().getStandards().getNbAlias().getAliasInteger(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc(_cont.getContext().getStandards().getNbAlias().getAliasInteger(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(false);
        val_.setCaught(true);
        val_.setPropagated(false);
    }

    private void nbea(ResultContext _cont) {
        _cont.toggleExcPoint("[int",ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc("[int", ExcPointBlockKey.SAME).getValue();
        val_.setThrown(false);
        val_.setCaught(true);
        val_.setPropagated(false);
    }
    private void unkCaught(ResultContext _cont) {
        _cont.toggleExcPoint("",ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc("", ExcPointBlockKey.SAME).getValue();
        val_.setThrown(false);
        val_.setCaught(true);
        val_.setPropagated(false);
    }
    private void unkThrown(ResultContext _cont) {
        _cont.toggleExcPoint("",ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc("", ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }
    private void std(ResultContext _cont) {
        _cont.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc("pkg.Ex", ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }

    private void stdParam(ResultContext _cont) {
        _cont.toggleExcPoint("pkg.Ex<int>",ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc("pkg.Ex<int>", ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }
    private void stdInc(ResultContext _cont) {
        _cont.toggleExcPoint("pkg.Ex<?>",ExcPointBlockKey.SAME_FAMILY);
        ExcPoint val_ = _cont.getPairExc("pkg.Ex", ExcPointBlockKey.SAME_FAMILY).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }
    private void any(ResultContext _cont) {
        _cont.toggleExcPoint("",ExcPointBlockKey.SAME_FAMILY);
        ExcPoint val_ = _cont.getPairExc("", ExcPointBlockKey.SAME_FAMILY).getValue();
        val_.setThrown(false);
        val_.setCaught(true);
        val_.setPropagated(false);
    }


    private void stdInherit(ResultContext _cont) {
        _cont.toggleExcPoint("pkg.Ex",ExcPointBlockKey.INHERIT);
        ExcPoint val_ = _cont.getPairExc("pkg.Ex", ExcPointBlockKey.INHERIT).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }

    private void stdInheritInt1(ResultContext _cont) {
        _cont.toggleExcPoint("pkg.Ex1",ExcPointBlockKey.INHERIT);
        ExcPoint val_ = _cont.getPairExc("pkg.Ex1", ExcPointBlockKey.INHERIT).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }

    private void stdInheritInt2(ResultContext _cont) {
        _cont.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.INHERIT);
        ExcPoint val_ = _cont.getPairExc("pkg.Ex2", ExcPointBlockKey.INHERIT).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }

    private void stdParamWcSub(ResultContext _cont) {
        _cont.toggleExcPoint("[pkg.Ex<?int>",ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc("[pkg.Ex<?int>", ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }
    private void stdParamWcSup(ResultContext _cont) {
        _cont.toggleExcPoint("[pkg.Ex<!int>",ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc("[pkg.Ex<!int>", ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }
}
