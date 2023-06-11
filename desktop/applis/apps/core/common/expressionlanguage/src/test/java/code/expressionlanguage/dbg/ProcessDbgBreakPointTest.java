package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
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
    @Test
    public void test14() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",62,cont_);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),71));
    }

    @Test
    public void test15() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointEnabled("pkg/Ex",71,cont_, false);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test16() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointEnabled("pkg/Ex",71,cont_, false);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointEnabled("pkg/Ex",71,cont_, true);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test17() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointEnabled("pkg/Ex",71,cont_, false);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointEnabled("pkg/Ex",58,cont_, true);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test18() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointEnabled("pkg/Ex",71,cont_, false);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointEnabled("pkg/Ex",-1,cont_, true);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(cont_),70));
    }

    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringBuilder xml2_ = new StringBuilder();
        xml2_.append("@Ex3\n");
        xml2_.append("public class pkg.Ex2 {\n");
        xml2_.append(" public static int exmeth(){\n");
        xml2_.append("  int t = 8;\n");
        xml2_.append("  int u = 3;\n");
        xml2_.append("  return Math.mod(t,u);\n");
        xml2_.append(" }\n");
        xml2_.append("}\n");
        StringBuilder xml3_ = new StringBuilder();
        xml3_.append("public annotation pkg.Ex3 {\n");
        xml3_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        files_.put("pkg/Ex2", xml2_.toString());
        files_.put("pkg/Ex3", xml3_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",1,cont_);
        assertTrue(ResultExpressionOperationNode.enabledTypeBp(13,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex")));
    }

    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringBuilder xml2_ = new StringBuilder();
        xml2_.append("@Ex3\n");
        xml2_.append("public class pkg.Ex2 {\n");
        xml2_.append(" public static int exmeth(){\n");
        xml2_.append("  int t = 8;\n");
        xml2_.append("  int u = 3;\n");
        xml2_.append("  return Math.mod(t,u);\n");
        xml2_.append(" }\n");
        xml2_.append("}\n");
        StringBuilder xml3_ = new StringBuilder();
        xml3_.append("public annotation pkg.Ex3 {\n");
        xml3_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        files_.put("pkg/Ex2", xml2_.toString());
        files_.put("pkg/Ex3", xml3_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",1,cont_);
        assertFalse(ResultExpressionOperationNode.enabledTypeBp(1,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex2")));
    }

    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringBuilder xml2_ = new StringBuilder();
        xml2_.append("@Ex3\n");
        xml2_.append("public class pkg.Ex2 {\n");
        xml2_.append(" public static int exmeth(){\n");
        xml2_.append("  int t = 8;\n");
        xml2_.append("  int u = 3;\n");
        xml2_.append("  return Math.mod(t,u);\n");
        xml2_.append(" }\n");
        xml2_.append("}\n");
        StringBuilder xml3_ = new StringBuilder();
        xml3_.append("public annotation pkg.Ex3 {\n");
        xml3_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        files_.put("pkg/Ex2", xml2_.toString());
        files_.put("pkg/Ex3", xml3_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",42,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",1,cont_);
        assertFalse(ResultExpressionOperationNode.enabledTypeBp(42,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex")));
    }
    @Test
    public void test22() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex",13,cont_, true);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex2",13,cont_, false);
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(cont_,"pkg/Ex"),13).isInstanceType());
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(cont_,"pkg/Ex2"),13).isInstanceType());
    }

    @Test
    public void test23() {
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex",13,cont_, false);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex2",13,cont_, true);
        assertFalse(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(cont_,"pkg/Ex"),13).isStaticType());
        assertTrue(cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(cont_,"pkg/Ex2"),13).isStaticType());
    }
    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringBuilder xml2_ = new StringBuilder();
        xml2_.append("public class pkg.Ex2{\n");
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
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",59,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",71,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",59,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex2",71,cont_);
        CustList<BreakPointBlockPair> ls_ = cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().bp(file(cont_), cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex").getMetrics(4), 70);
        assertEq(1,ls_.size());
    }
    private ExecFileBlock file(ResultContext _cont) {
        return file(_cont,"pkg/Ex");
    }
    private ExecFileBlock file(ResultContext _cont, String _name) {
        return _cont.getContext().getClasses().getDebugMapping().getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal(_name));
    }
}
