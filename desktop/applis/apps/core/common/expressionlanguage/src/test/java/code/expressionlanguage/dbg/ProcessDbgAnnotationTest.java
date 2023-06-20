package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgAnnotationTest extends ProcessDbgCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",57,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(57, now(stack_));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",57,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" int sample()2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",45,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(4, stack_.nbPages());
        assertEq(45, now(stack_));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" int sample()2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",45,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" int sample()2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(MyAnnot).getDeclaredMethods()[0].getDefaultValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",45,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(45, now(stack_));
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" int sample()2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(MyAnnot).getDeclaredMethods()[0].getDefaultValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",45,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(ExParam).getDeclaredMethods()[0].getAnnotationsParameters();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam {\n");
        xml_.append(" public static void catching(@MyAnnot int p){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",218,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(218, now(stack_));
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(ExParam).getDeclaredMethods()[0].getAnnotationsParameters();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam {\n");
        xml_.append(" public static void catching(@MyAnnot int p){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",218,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" ONE;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredFields()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",56,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(56, now(stack_));
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" ONE;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredFields()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",56,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

}
