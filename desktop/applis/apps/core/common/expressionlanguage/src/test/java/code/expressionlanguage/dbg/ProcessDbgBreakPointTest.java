package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgBreakPointTest extends ProcessDbgCommon {
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
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),71));
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test3() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test4() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),80));
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",-1,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),80));
    }

    @Test
    public void test6() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",71,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test7() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",71,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test8() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",71,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test9() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",-1,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test10() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",58,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),57));
    }

    @Test
    public void test11() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",58,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",71,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),57));
    }

    @Test
    public void test12() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPointEnabled("pkg/Ex",58,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),57));
    }

    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringBuilder xml2_ = new StringBuilder();
        xml2_.append("public class pkg.Ex2 {\n");
        xml2_.append(" public static int exmeth(){\n");
        xml2_.append("  int t = 8;\n");
        xml2_.append("  int u = 3;\n");
        xml2_.append("  return Math.mod(t,u);\n");
        xml2_.append(" }\n");
        xml2_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        files_.put("pkg/Ex2", xml2_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",58,cont_);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }
    private ExecFileBlock file(ResultContext _cont) {
        return _cont.getContext().getClasses().getDebugMapping().getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"));
    }
}
