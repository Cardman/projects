package code.expressionlanguage.dbg;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
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
