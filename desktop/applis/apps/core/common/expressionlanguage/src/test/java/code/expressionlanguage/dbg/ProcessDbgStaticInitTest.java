package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgStaticInitTest extends ProcessDbgCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=2;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",34);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(34, now(stack_));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=2;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",34);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long w;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex");
        cont_.toggleBreakPoint("pkg/Ex",66);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(66, now(stack_));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long w;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex");
        cont_.toggleBreakPoint("pkg/Ex",66);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=2;\n");
        xml_.append(" static int w=2;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",34);
        cont_.toggleBreakPoint("pkg/Ex",66);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(34, now(stack_));
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=2;\n");
        xml_.append(" static int w=2;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",34);
        cont_.toggleBreakPoint("pkg/Ex",51);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(51, now(next_));
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=2;\n");
        xml_.append(" static int w=2;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",34);
        cont_.toggleBreakPoint("pkg/Ex",51);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        StackCall next2_ = tryInitStaticlyTypes(next_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(0, next2_.nbPages());
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long w=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex");
        cont_.toggleBreakPoint("pkg/Ex",66);
        cont_.toggleBreakPoint("pkg/Ex",97);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(66, now(stack_));
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long w=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex");
        cont_.toggleBreakPoint("pkg/Ex",66);
        cont_.toggleBreakPoint("pkg/Ex",97);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(97, now(next_));
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long w=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex");
        cont_.toggleBreakPoint("pkg/Ex",66);
        cont_.toggleBreakPoint("pkg/Ex",97);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        StackCall next2_ = tryInitStaticlyTypes(next_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(0, next2_.nbPages());
    }

    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex2{\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.toggleBreakPoint("pkg/Ex",66);
        cont_.toggleBreakPoint("pkg/Ex2",66);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(66, now(stack_));
        assertEq("pkg/Ex", stack_.getLastPage().getFile().getFileName());
    }

    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex2{\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.toggleBreakPoint("pkg/Ex",66);
        cont_.toggleBreakPoint("pkg/Ex2",66);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(66, now(next_));
        assertEq("pkg/Ex2", next_.getLastPage().getFile().getFileName());
    }

    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex2{\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long x;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex","pkg.Ex2");
        cont_.toggleBreakPoint("pkg/Ex",66);
        cont_.toggleBreakPoint("pkg/Ex2",66);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        StackCall next2_ = tryInitStaticlyTypes(next_, cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(0, next2_.nbPages());
    }

    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static long v=Math.random(4);\n");
        xml_.append(" static long w;\n");
        xml_.append(" public static long catching(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_,"pkg.Ex");
        cont_.toggleBreakPoint("pkg/Ex",13);
        updateStaType(cont_, "pkg/Ex",13,true);
        cont_.toggleBreakPoint("pkg/Ex",66);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        StackCall next_ = tryInitStaticlyTypes(stack_, cont_.getForwards().getOptions(), cont_.getContext());
        StackCall n_ = tryInitStaticlyTypes(tryInitStaticlyTypes(next_, cont_.getForwards().getOptions(), cont_.getContext()), cont_.getForwards().getOptions(), cont_.getContext());
        assertEq(0, n_.nbPages());
        assertEq(0, tryInitStaticlyTypes(n_,cont_.getForwards().getOptions(), cont_.getContext()).nbPages());
    }
}
