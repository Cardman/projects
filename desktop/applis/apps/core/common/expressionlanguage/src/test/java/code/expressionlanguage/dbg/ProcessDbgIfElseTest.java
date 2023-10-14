package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgIfElseTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t > u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en", files_);
        cont_.toggleBreakPoint("pkg/Ex",85);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(83, now(stack_));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t > u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en", files_);
        cont_.toggleBreakPoint("pkg/Ex",85);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",85);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(83, now(stack_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",85);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t > u){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(128, now(stack_));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t > u){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t == u){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(128, now(stack_));
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t == u){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t == u){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(128, now(stack_));
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t == u){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t == u()){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append(" public static int u(){return 3;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(128, now(stack_));
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t == u()){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append(" public static int u(){return 3;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t != u()){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append(" public static int u(){return 3;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(128, now(stack_));
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else if (t != u()){\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append(" public static int u(){return 3;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",120);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(119, now(stack_));
    }
    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  if (t < u) {\n");
        xml_.append("   u += Math.mod(t,u);\n");
        xml_.append("  } else {\n");
        xml_.append("   t += Math.mod(t,u);\n");
        xml_.append("  }\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",120);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;if (t > u) {u += Math.mod(t,u);} else {t += Math.mod(t,u);}return Math.mod(t,u);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en", files_);
        cont_.toggleBreakPoint("pkg/Ex",77);
        analyze(cont_,"t > u","pkg/Ex",77);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(77, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;if (t > u) {if (t > u) {u += Math.mod(t,u);}} else {t += Math.mod(t,u);}return Math.mod(t,u);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en", files_);
        cont_.toggleBreakPoint("pkg/Ex",84);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(84, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;if (t > u) {if (t > u) {u += Math.mod(t,u);}} else {t += Math.mod(t,u);}return Math.mod(t,u);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en", files_);
        cont_.toggleBreakPoint("pkg/Ex",84);
        analyze(cont_,"t > u","pkg/Ex",84);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(84, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    static void analyze(ResultContext _cont, String _cond, String _file, int _caret) {
        FileBlock bl_ = _cont.getPageEl().getPreviousFilesBodies().getVal(_file);
        BreakPointBlockPair pair_ = _cont.getPair(_cont.getFiles().getVal(bl_), _caret);
        pair_.getValue().getResultStd().analyze(pair_,_cond,"","",_cont,new DefContextGenerator());
    }
}