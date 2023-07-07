package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.WatchPoint;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgFieldTest extends ProcessDbgCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",27,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(27, now(stack_));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",27,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(82, now(stack_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(83, now(next_));
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v,w;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=e.w;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",29,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "w"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(94, now(stack_));
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(2, next_.nbPages());
        assertEq(257, now(next_));
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=new Ex3();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static Ex3 $(Ex2 f){\n");
        xml_.append("  Ex3 o = new Ex3();\n");
        xml_.append("  o.v=f.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(Ex3 f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex3 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" operator+ Ex3(Ex3 f, Ex3 s){\n");
        xml_.append("  Ex3 o = new Ex3();\n");
        xml_.append("  o.v=f.v+s.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(94, now(stack_));
    }

    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=new Ex3();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static Ex3 $(Ex2 f){\n");
        xml_.append("  Ex3 o = new Ex3();\n");
        xml_.append("  o.v=f.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(Ex3 f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex3 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" operator+ Ex3(Ex3 f, Ex3 s){\n");
        xml_.append("  Ex3 o = new Ex3();\n");
        xml_.append("  o.v=f.v+s.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(2, next_.nbPages());
        assertEq(297, now(next_));
    }

    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=new Ex3();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static Ex3 $(Ex2 f){\n");
        xml_.append("  Ex3 o = new Ex3();\n");
        xml_.append("  o.v=f.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(Ex3 f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex3 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" operator+ Ex3(Ex3 f, Ex3 s){\n");
        xml_.append("  Ex3 o = new Ex3();\n");
        xml_.append("  o.v=f.v+s.v;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex2.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",118,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(132, now(stack_));
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex2.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",118,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgNormalInfoKeep("pkg.Ex", id_,cont_,stack_).getStack();
        assertEq(2, next_.nbPages());
        assertEq(132, now(next_));
    }
    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex2.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",118,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgNormalInfoKeep("pkg.Ex", id_,cont_,stack_).getStack();
        assertEq(0,  dbgNormalInfoKeep("pkg.Ex", id_,cont_,next_).getStack().nbPages());
    }

    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v??=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(82, now(stack_));
    }
    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v??=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(83, now(next_));
    }
    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v??=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v???=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(82, now(stack_));
    }
    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v???=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(104, now(stack_));
    }
    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(105, now(next_));
    }
    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that Ex2 f=that(e.v);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(116, now(stack_));
    }

    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that Ex2 f=that(e.v);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(2, next_.nbPages());
        assertEq(279, now(next_));
    }

    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that Ex2 f=that(e.v);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }
    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  int e = 1;\n");
        xml_.append("  that int f=that(e);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex2 e = new();\n");
        xml_.append("  that Ex2 f=that(e);\n");
        xml_.append("  f+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e[0]+=2;\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  v=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e?.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(83, now(stack_));
    }
    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e?.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(84, now(next_));
    }
    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e?.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test33() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = null;\n");
        xml_.append("  e?.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test34() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()+=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(124, now(stack_));
    }
    @Test
    public void test35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()+=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(85, now(next_));
    }
    @Test
    public void test36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()+=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test37() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()+=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that Ex2 r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(136, now(stack_));
    }

    @Test
    public void test38() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()+=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that Ex2 r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(2, next_.nbPages());
        assertEq(302, now(next_));
    }

    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" Ex2 v = new Ex2();\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()+=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that Ex2 r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" public int v;\n");
        xml_.append(" public static int $(Ex2 f){\n");
        xml_.append("  return f.v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex2 $(int f){\n");
        xml_.append("  Ex2 o = new Ex2();\n");
        xml_.append("  o.v=f;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        compoundReadWrite(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(82, now(stack_));
    }
    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test42() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex2.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",118,cont_);
        read(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test43() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(83, now(stack_));
    }
    @Test
    public void test44() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test45() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex2.v=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",120,cont_);
        write(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test46() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex2).getDeclaredFields(\"v\")[0].get(null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",159,cont_);
        read(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test47() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex2).getDeclaredFields(\"v\")[0].set(null,0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",161,cont_);
        read(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test48() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex2).getDeclaredFields(\"v\")[0].get(null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",159,cont_);
        read(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test49() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex2).getDeclaredFields(\"v\")[0].get(null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",159,cont_);
        read(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test50() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex2).getDeclaredFields(\"v\")[0].set(null,0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",161,cont_);
        write(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test51() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex2).getDeclaredFields(\"v\")[0].set(null,0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append(" static int v;\n");
        xml_.append(" static{\n");
        xml_.append("  v+=1/1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",161,cont_);
        write(cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test52() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredFields(\"v\")[0].get(new Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test53() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredFields(\"v\")[0].get(new Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test54() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredFields(\"v\")[0].set(new Ex(),1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test55() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredFields(\"v\")[0].set(new Ex(),1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test56() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var f = $lambda(int,:);\n");
        xml_.append("  f.call(that(new Ex().v));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test57() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var f = $lambda(int,:);\n");
        xml_.append("  f.call(that(new Ex().v));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test58() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var f = $lambda(int,:);\n");
        xml_.append("  f.call(that(new Ex().v));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()).nbPages());
    }

    @Test
    public void test59() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var f = $lambda(int,=,int);\n");
        xml_.append("  f.call(that(new Ex().v),1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test60() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var f = $lambda(int,=,int);\n");
        xml_.append("  f.call(that(new Ex().v),1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test61() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  int l;\n");
        xml_.append("  var f = $lambda(int,:);\n");
        xml_.append("  f.call(that(l));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test62() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  int l;\n");
        xml_.append("  var f = $lambda(int,=,int);\n");
        xml_.append("  f.call(that(l),1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test63() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v=e.v+1;\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        nothing(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test64() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(85, now(stack_));
    }
    @Test
    public void test65() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test66() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  int w = 0;\n");
        xml_.append("  return that(w);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test67() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r();\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(121, now(stack_));
    }
    @Test
    public void test68() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r();\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test69() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.r()=2;\n");
        xml_.append(" }\n");
        xml_.append(" public that int r(){\n");
        xml_.append("  int w = 0;\n");
        xml_.append("  return that(w);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test70() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(98, now(stack_));
    }
    @Test
    public void test71() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(104, now(next_));
    }
    @Test
    public void test72() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test73() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(105, now(stack_));
    }
    @Test
    public void test74() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  that int f=that(e.v);\n");
        xml_.append("  f=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test75() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  int w = 2;\n");
        xml_.append("  that int f=that(w);\n");
        xml_.append("  f=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test76() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v);l.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test77() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v);l.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test78() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v,int);l.call(2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test79() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v,int);l.call(2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test80() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=new Ex().$lambda(Ex,,v);l.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test81() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=new Ex().$lambda(Ex,,v);l.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test82() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=new Ex().$lambda(Ex,,v,int);l.call(2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test83() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=new Ex().$lambda(Ex,,v,int);l.call(2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test84() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v);l.call(new Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test85() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v);l.call(new Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        read(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test86() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v,int);l.call(new Ex(),2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test87() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l=$lambda(Ex,,v,int);l.call(new Ex(),2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",27,cont_);
        write(cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test88() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        readCondition("class(Ex<U>)==class(Ex<int>)&&v==1",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test89() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        readCondition("class(Ex<U>)==class(Ex<int>)&&v==1",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test90() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        readCondition("v==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test91() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        writeCondition("class(Ex<U>)==class(Ex<int>)&&value==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test92() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        writeCondition("class(Ex<U>)==class(Ex<int>)&&value==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test93() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        writeCondition("value==4",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test94() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        compoundReadCondition("class(Ex<U>)==class(Ex<int>)&&v==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test95() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        compoundReadCondition("class(Ex<U>)==class(Ex<int>)&&v==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test96() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        compoundReadCondition("v==4",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test97() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        compoundWriteCondition("class(Ex<U>)==class(Ex<int>)&&value==5",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test98() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        compoundWriteCondition("class(Ex<U>)==class(Ex<int>)&&value==5",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test99() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        compoundWriteCondition("value==6",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test100() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex2<U> {\n");
        xml_.append(" ExSub<U> v=new();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append(" public static ExSub<T> $(int i){\n");
        xml_.append("  ExSub<T> e = new();\n");
        xml_.append("  e.v = (T) i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExSub<T> i){\n");
        xml_.append("  return (int)i.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new Ex2<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",36,cont_);
        compoundWriteCondition("class(Ex<U>)==class(Ex<int>)&&value.v==5",cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(2, stack_.nbPages());
    }

    @Test
    public void test101() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex2<U> {\n");
        xml_.append(" ExSub<U> v=new();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append(" public static ExSub<T> $(int i){\n");
        xml_.append("  ExSub<T> e = new();\n");
        xml_.append("  e.v = (T) i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExSub<T> i){\n");
        xml_.append("  return (int)i.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new Ex2<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",36,cont_);
        compoundWriteCondition("class(Ex<U>)==class(Ex<int>)&&value.v==5",cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test102() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex2<U> {\n");
        xml_.append(" ExSub<U> v=new();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append(" public static ExSub<T> $(int i){\n");
        xml_.append("  ExSub<T> e = new();\n");
        xml_.append("  e.v = (T) i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExSub<T> i){\n");
        xml_.append("  return (int)i.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new Ex2<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",36,cont_);
        compoundWriteCondition("value.v==4",cont_, cf("pkg.Ex2", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test103() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        writeCondition("{return(:boolean)->{int value=1;return class(Ex<U>)==class(Ex<int>)&&#value==4-value;}.call();}",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test104() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        writeCondition("{return(:boolean)->{int value=1;return class(Ex<U>)==class(Ex<int>)&&#value==4-value;}.call();}",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test105() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",28,cont_);
        writeCondition("",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test106() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U w,v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",30,cont_);
        writeCondition("",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test107() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" U w;\n");
        xml_.append(" U v=(U)1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<T>:Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = new ExSub<int>();\n");
        xml_.append("  l.v=l.v+2;\n");
        xml_.append("  l.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        writeCondition("v==v",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }
    @Test
    public void test108() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  var l = Integer.MAX_VALUE;\n");
        xml_.append(" }\n");
        xml_.append(" public static void catching2(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",78,cont_);
        readCondition("0==0",cont_, cf("$core.Integer", "MAX_VALUE"));
        MethodId id_ = getMethodId("catching2");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test109() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        readCondition("v==1",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test110() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        readCondition("v==1",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test111() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        readCondition("v==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test112() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        writeCondition("value==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test113() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        writeCondition("value==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test114() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        writeCondition("value==4",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test115() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        compoundReadCondition("v==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test116() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        compoundReadCondition("v==3",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test117() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        compoundReadCondition("v==4",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test118() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        compoundWriteCondition("value==5",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(1, stack_.nbPages());
    }

    @Test
    public void test119() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        compoundWriteCondition("value==5",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test120() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=1;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex.v=Ex.v+2;\n");
        xml_.append("  Ex.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        compoundWriteCondition("value==6",cont_, cf("pkg.Ex", "v"));
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormalCheck("pkg.ExCaller", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    private void readCondition(String _newValue,ResultContext _cont, ClassField _cf) {
        read(_cont, _cf);
        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        WatchPoint wp_ = _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_newValue, _cf, _cont, type_, new DefContextGenerator(), false);
        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
        wp_.getResultRead().setResult(ResultContextLambda.okOrNull(res_));
        wp_.getResultRead().setResultStr(ResultContextLambda.okOrEmpty(res_,_newValue));
    }
    private void writeCondition(String _newValue,ResultContext _cont, ClassField _cf) {
        write(_cont, _cf);
        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        WatchPoint wp_ = _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_newValue, _cf, _cont, type_, new DefContextGenerator(), true);
        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
        wp_.getResultWrite().setResult(ResultContextLambda.okOrNull(res_));
        wp_.getResultWrite().setResultStr(ResultContextLambda.okOrEmpty(res_,_newValue));
    }
    private void compoundReadCondition(String _newValue,ResultContext _cont, ClassField _cf) {
        compoundRead(_cont, _cf);
        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        WatchPoint wp_ = _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_newValue, _cf, _cont, type_, new DefContextGenerator(), false);
        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
        wp_.getResultCompoundRead().setResult(ResultContextLambda.okOrNull(res_));
        wp_.getResultCompoundRead().setResultStr(ResultContextLambda.okOrEmpty(res_,_newValue));
    }
    private void compoundWriteCondition(String _newValue,ResultContext _cont, ClassField _cf) {
        compoundWrite(_cont, _cf);
        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        WatchPoint wp_ = _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_newValue, _cf, _cont, type_, new DefContextGenerator(), true);
        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
        wp_.getResultCompoundWrite().setResult(ResultContextLambda.okOrNull(res_));
        wp_.getResultCompoundWrite().setResultStr(ResultContextLambda.okOrEmpty(res_,_newValue));
    }
    private void compoundReadWrite(ResultContext _cont, ClassField _cf) {
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setWrite(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundRead(true);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundWrite(true);
    }

    private void read(ResultContext _cont, ClassField _cf) {
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setRead(true);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setWrite(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundWrite(false);
    }

    private void write(ResultContext _cont, ClassField _cf) {
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setWrite(true);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundWrite(false);
    }

    private void compoundRead(ResultContext _cont, ClassField _cf) {
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setWrite(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundRead(true);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundWrite(false);
    }

    private void compoundWrite(ResultContext _cont, ClassField _cf) {
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setWrite(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundWrite(true);
    }

    private void nothing(ResultContext _cont, ClassField _cf) {
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setWrite(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundRead(false);
        _cont.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPairWatch(_cf).getValue().setCompoundWrite(false);
    }
    private ClassField cf(String _cl, String _f) {
        return new ClassField(_cl,_f);
    }
}
