package code.expressionlanguage.dbg;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgWatchPointTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",36,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","s")));
    }

    @Test
    public void test5() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",-1,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",34,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",34,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",34,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",34,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",-1,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",36,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","s")));
    }

    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",34,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("pkg/Ex",36,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",34,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","s")));
    }

    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",34,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPointEnabled("pkg/Ex",36,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","f")));
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().isWatch(cf("pkg.Ex","s")));
    }

    @Test
    public void test() {
        assertEq(".",new WatchPointBlockPairKeyString().keyString(new WatchPointBlockPair(new ClassField("",""),null)));
    }
    private ClassField cf(String _cl, String _f) {
        return new ClassField(_cl,_f);
    }
}
