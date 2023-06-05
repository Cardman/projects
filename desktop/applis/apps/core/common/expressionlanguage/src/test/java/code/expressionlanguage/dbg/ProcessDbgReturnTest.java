package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgReturnTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",87,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, now(stack_));
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",87,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",86,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, now(stack_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",86,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v = 2;\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  exmethRef() = 3;\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmethRef(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",146,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(146, now(stack_));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v = 2;\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  exmethRef() = 3;\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmethRef(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",146,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
}
