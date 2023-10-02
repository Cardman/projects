package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.sample.ElInterceptorStdCaller;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
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
        assertFalse(cont_.is(file(cont_),71));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        assertTrue(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.toggleBreakPoint("pkg/Ex",71);
        assertFalse(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        assertFalse(cont_.is(file(cont_),80));
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
        cont_.toggleBreakPoint("pkg/Ex",-1);
        assertFalse(cont_.is(file(cont_),80));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.toggleBreakPointEnabled("pkg/Ex",71);
        assertFalse(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.toggleBreakPointEnabled("pkg/Ex",71);
        cont_.toggleBreakPointEnabled("pkg/Ex",71);
        assertTrue(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPointEnabled("pkg/Ex",71);
        assertTrue(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPointEnabled("pkg/Ex",-1);
        assertFalse(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.toggleBreakPoint("pkg/Ex",58);
        assertTrue(cont_.is(file(cont_),57));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.toggleBreakPoint("pkg/Ex",58);
        cont_.toggleBreakPointEnabled("pkg/Ex",71);
        assertTrue(cont_.is(file(cont_),57));
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
        cont_.toggleBreakPointEnabled("pkg/Ex",71);
        cont_.toggleBreakPointEnabled("pkg/Ex",58);
        assertTrue(cont_.is(file(cont_),57));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.toggleBreakPoint("pkg/Ex2",58);
        assertTrue(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",62);
        assertFalse(cont_.is(file(cont_),71));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.breakPointEnabled("pkg/Ex",71, false);
        assertFalse(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.breakPointEnabled("pkg/Ex",71, false);
        cont_.breakPointEnabled("pkg/Ex",71, true);
        assertTrue(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.breakPointEnabled("pkg/Ex",71, false);
        cont_.breakPointEnabled("pkg/Ex",58, true);
        assertFalse(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.breakPointEnabled("pkg/Ex",71, false);
        cont_.breakPointEnabled("pkg/Ex",-1, true);
        assertFalse(cont_.is(file(cont_),70));
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
        cont_.toggleBreakPoint("pkg/Ex",13);
        cont_.toggleBreakPoint("pkg/Ex2",1);
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
        cont_.toggleBreakPoint("pkg/Ex",13);
        cont_.toggleBreakPoint("pkg/Ex2",1);
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
        cont_.toggleBreakPoint("pkg/Ex",42);
        cont_.toggleBreakPoint("pkg/Ex2",1);
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
        cont_.toggleBreakPoint("pkg/Ex",13);
        cont_.toggleBreakPoint("pkg/Ex2",13);
        cont_.breakPointInstanceType("pkg/Ex",13, true);
        cont_.breakPointInstanceType("pkg/Ex2",13, false);
        assertTrue(cont_.getPair(file(cont_,"pkg/Ex"),13).getValue().isInstanceType());
        assertFalse(cont_.getPair(file(cont_,"pkg/Ex2"),13).getValue().isInstanceType());
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
        cont_.toggleBreakPoint("pkg/Ex",13);
        cont_.toggleBreakPoint("pkg/Ex2",13);
        cont_.breakPointStaticType("pkg/Ex",13, false);
        cont_.breakPointStaticType("pkg/Ex2",13, true);
        assertFalse(cont_.getPair(file(cont_,"pkg/Ex"),13).getValue().isStaticType());
        assertTrue(cont_.getPair(file(cont_,"pkg/Ex2"),13).getValue().isStaticType());
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
        cont_.toggleBreakPoint("pkg/Ex",59);
        cont_.toggleBreakPoint("pkg/Ex",71);
        cont_.toggleBreakPoint("pkg/Ex2",59);
        cont_.toggleBreakPoint("pkg/Ex2",71);
        CustList<BreakPointBlockPair> ls_ = cont_.bp(file(cont_), cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex").getMetrics(4), 70);
        assertEq(1,ls_.size());
    }
    @Test
    public void test25() {
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
        assertEq("pkg.Ex.static exmeth()",ResultExpressionOperationNode.beginPartFct(53,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"),cont_.getPageEl().getDisplayedStrings()));
    }
    @Test
    public void test26() {
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
        assertEq("",ResultExpressionOperationNode.beginPartFct(22,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"),cont_.getPageEl().getDisplayedStrings()));
        assertEq("",ResultExpressionOperationNode.beginPartFctKey(22,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex")));
    }
    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ((int t,int u:int)->t+u).call(8,3);\n");
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
        assertEq("pkg.Ex.static .1(int,int)",ResultExpressionOperationNode.beginPartFct(80,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"),cont_.getPageEl().getDisplayedStrings()));
    }
    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return switch(10){default;return 1;};\n");
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
        assertEq("pkg.Ex.static .1(int)",ResultExpressionOperationNode.beginPartFct(86,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"),cont_.getPageEl().getDisplayedStrings()));
    }
    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex2(pkg.Ex2 a){\n");
        xml_.append(" ((int t,int u:int)->t+u).call(8,3);return null;\n");
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
        assertEq("static +(pkg.Ex2).static .1(int,int)",ResultExpressionOperationNode.beginPartFct(51,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"),cont_.getPageEl().getDisplayedStrings()));
    }
    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex2(pkg.Ex2 a){\n");
        xml_.append(" (switch(10){default;return 1;});return null;\n");
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
        assertEq("static +(pkg.Ex2).static .1(int)",ResultExpressionOperationNode.beginPartFct(58,cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"),cont_.getPageEl().getDisplayedStrings()));
    }
    @Test
    public void test() {
        assertEq("-1/0",new BreakPointBlockPairKeyString().keyString(new BreakPointBlockPair(null,-1,0,new ElInterceptorStdCaller(), false,false)));
        assertEq("-1/0",new BreakPointBlockPairKeyIdString().keyString(new BreakPointBlockKey(null,-1,0)));
        assertEq("-1/0",new ExecFileBlockTraceIndexKeyString().keyString(new ExecFileBlockTraceIndex(null,-1,0)));
        assertEq("",new ExecFileBlockTraceIndexKeyString().keyString(new ExecFileBlockFct("","")));
        assertEq("",new ExecFileBlockFct("","").valueStr());
        assertFalse(StringUtil.nullToEmpty(new ExecFileBlockTraceIndex(null,-1,0).valueStr()+"_").isEmpty());
        assertFalse(new BpcKeyString().keyString(new BreakPointCondition(new ElInterceptorStdCaller(), null, new BreakPointBlockKey(null,-1,0),0,0)).isEmpty());
        assertFalse(new BpcKeyString().keyString(new BreakPointCondition(new ElInterceptorStdCaller(), null, new BreakPointBlockKey(null,-1,0),10,0)).isEmpty());
        assertEq(-1, NumberUtil.signum(new CmpMethodPair().compare(new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "0", ""),2,null),new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "1", ""),2, null))));
        assertEq(1, NumberUtil.signum(new CmpMethodPair().compare(new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "0", ""),3,null),new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "1", ""),2, null))));
    }
    @Test
    public void pref1() {
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),new ElInterceptorStdCaller());
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_ENTRY));
    }
    @Test
    public void pref2() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_ENTRY);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
    }

    @Test
    public void pref3() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_ENTRY);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_ENTRY);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(1,f_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
    }

    @Test
    public void pref4() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_ENTRY);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_ENTRY);
        MethodPointBlockPair g_ = add(i_, ls_, "2", MethodPoint.BPC_ENTRY);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(1,f_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(2,g_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
    }

    @Test
    public void pref5() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_ENTRY);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_ENTRY);
        f_.getValue().result(MethodPoint.BPC_ENTRY).getPref().set(2);
        MethodPointBlockPair g_ = add(i_, ls_, "2", MethodPoint.BPC_ENTRY);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(2,f_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(1,g_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
    }

    @Test
    public void pref6() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_ENTRY);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_ENTRY);
        f_.getValue().result(MethodPoint.BPC_ENTRY).getPref().set(3);
        MethodPointBlockPair g_ = add(i_, ls_, "2", MethodPoint.BPC_ENTRY);
        MethodPointBlockPair h_ = add(i_, ls_, "3", MethodPoint.BPC_ENTRY);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(3,f_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(1,g_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(2,h_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
    }

    @Test
    public void pref7() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_ENTRY);
        e_.getValue().result(MethodPoint.BPC_ENTRY).getPref().set(1);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_ENTRY);
        assertEq(1,e_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
        assertEq(0,f_.getValue().result(MethodPoint.BPC_ENTRY).getPref().get());
    }
    @Test
    public void pref8() {
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),new ElInterceptorStdCaller());
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_EXIT));
    }
    @Test
    public void pref9() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_EXIT);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
    }

    @Test
    public void pref10() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_EXIT);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_EXIT);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(1,f_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
    }

    @Test
    public void pref11() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_EXIT);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_EXIT);
        MethodPointBlockPair g_ = add(i_, ls_, "2", MethodPoint.BPC_EXIT);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(1,f_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(2,g_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
    }

    @Test
    public void pref12() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_EXIT);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_EXIT);
        f_.getValue().result(MethodPoint.BPC_EXIT).getPref().set(2);
        MethodPointBlockPair g_ = add(i_, ls_, "2", MethodPoint.BPC_EXIT);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(2,f_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(1,g_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
    }

    @Test
    public void pref13() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_EXIT);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_EXIT);
        f_.getValue().result(MethodPoint.BPC_EXIT).getPref().set(3);
        MethodPointBlockPair g_ = add(i_, ls_, "2", MethodPoint.BPC_EXIT);
        MethodPointBlockPair h_ = add(i_, ls_, "3", MethodPoint.BPC_EXIT);
        assertEq(0,e_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(3,f_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(1,g_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(2,h_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
    }

    @Test
    public void pref14() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_EXIT);
        e_.getValue().result(MethodPoint.BPC_EXIT).getPref().set(1);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_EXIT);
        assertEq(1,e_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
        assertEq(0,f_.getValue().result(MethodPoint.BPC_EXIT).getPref().get());
    }
    @Test
    public void prefs1() {
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),new ElInterceptorStdCaller());
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_ENTRY,""));
    }
    @Test
    public void prefs2() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = addList(i_, ls_, "0", MethodPoint.BPC_ENTRY,"1",1, 4);
        assertEq(1,e_.getValue().result(MethodPoint.BPC_ENTRY).pref("1"));
    }
    @Test
    public void prefs3() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = addList(i_, ls_, "0", MethodPoint.BPC_ENTRY,"1",1, 4);
        assertEq(4,e_.getValue().result(MethodPoint.BPC_ENTRY).pref("2"));
    }
    @Test
    public void prefs4() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        addList(i_, ls_, "0", MethodPoint.BPC_ENTRY,"1",1, 4);
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_ENTRY,"1"));
    }
    @Test
    public void prefs5() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        addList(i_, ls_, "0", MethodPoint.BPC_ENTRY,"1",0, 4);
        assertEq(1,BreakPointBlockList.pref(ls_,MethodPoint.BPC_ENTRY,"1"));
    }
    @Test
    public void prefs6() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = addList(i_, ls_, "0", MethodPoint.BPC_ENTRY,"1",1, 4);
        assertEq(1,e_.getValue().result(MethodPoint.BPC_ENTRY).mapPrefs().size());
    }
    @Test
    public void prefs7() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        addList(i_, ls_, "0", MethodPoint.BPC_ENTRY,"2",0, 4);
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_ENTRY,"1"));
    }
    @Test
    public void prefs8() {
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),new ElInterceptorStdCaller());
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_EXIT,""));
    }
    @Test
    public void prefs9() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = addList(i_, ls_, "0", MethodPoint.BPC_EXIT,"1",1, 4);
        assertEq(1,e_.getValue().result(MethodPoint.BPC_EXIT).pref("1"));
    }
    @Test
    public void prefs10() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = addList(i_, ls_, "0", MethodPoint.BPC_EXIT,"1",1, 4);
        assertEq(4,e_.getValue().result(MethodPoint.BPC_EXIT).pref("2"));
    }
    @Test
    public void prefs11() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        addList(i_, ls_, "0", MethodPoint.BPC_EXIT,"1",1, 4);
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_EXIT,"1"));
    }
    @Test
    public void prefs12() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        addList(i_, ls_, "0", MethodPoint.BPC_EXIT,"1",0, 4);
        assertEq(1,BreakPointBlockList.pref(ls_,MethodPoint.BPC_EXIT,"1"));
    }
    @Test
    public void prefs13() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = addList(i_, ls_, "0", MethodPoint.BPC_EXIT,"1",1, 4);
        assertEq(1,e_.getValue().result(MethodPoint.BPC_EXIT).mapPrefs().size());
    }
    @Test
    public void prefs14() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        addList(i_, ls_, "0", MethodPoint.BPC_EXIT,"2",0, 4);
        assertEq(0,BreakPointBlockList.pref(ls_,MethodPoint.BPC_EXIT,"1"));
    }
    @Test
    public void prefs15() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<ArrPointBlockPair> ls_ = new ConcList<ArrPointBlockPair>(new ArrKeyString(),i_);
        addArrList(i_, ls_, "0", ArrPoint.BPC_LENGTH,"2",0, 4);
        assertEq(1,BreakPointBlockList.prefsArr(ls_,ArrPoint.BPC_LENGTH).size());
    }
    @Test
    public void prefs16() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<ExcPointBlockPair> ls_ = new ConcList<ExcPointBlockPair>(new ExcKeyString(),i_);
        addExcList(i_, ls_, "0", ArrPoint.BPC_LENGTH,"2",0, 4);
        assertEq(1,BreakPointBlockList.prefsExc(ls_,ArrPoint.BPC_LENGTH).size());
    }
    @Test
    public void prefs17() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<ParPointBlockPair> ls_ = new ConcList<ParPointBlockPair>(new ParKeyString(),i_);
        addParList(i_, ls_, "0", ArrPoint.BPC_LENGTH,"2",0, 4);
        assertEq(1,BreakPointBlockList.prefsPar(ls_).size());
    }

    @Test
    public void pref18() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", MethodPoint.BPC_ENTRY);
        StringMap<Integer> elts_ = new StringMap<Integer>();
        elts_.addEntry("0",0);
        e_.getValue().getResultEntry().prefsMap(elts_);
        MethodPointBlockPair f_ = add(i_, ls_, "1", MethodPoint.BPC_ENTRY);
        StringMap<Integer> elts2_ = new StringMap<Integer>();
        elts2_.addEntry("1",1);
        f_.getValue().getResultEntry().prefsMap(elts2_);
        assertEq(1,BreakPointBlockList.prefBpc(BreakPointBlockList.prefsMeths(ls_,MethodPoint.BPC_ENTRY),"0"));
    }

    private static MethodPointBlockPair add(ElInterceptorStdCaller _i, ConcList<MethodPointBlockPair> _ls, String _k, int _exit) {
        MethodPointBlockPair e_ = pair(_i, "", _k);
        e_.getValue().result(_exit).getPref().set(BreakPointBlockList.pref(_ls, _exit));
        _ls.add(e_);
        return e_;
    }

    private static ArrPointBlockPair addArr(ElInterceptorStdCaller _i, ConcList<ArrPointBlockPair> _ls, String _k, int _exit) {
        ArrPointBlockPair e_ = pairArr(_i, "", _k);
        e_.getValue().resultBpc(_exit).getPref().set(BreakPointBlockList.prefIn(BreakPointBlockList.prefsArr(_ls, _exit)));
        _ls.add(e_);
        return e_;
    }
    private static MethodPointBlockPair addList(ElInterceptorStdCaller _i, ConcList<MethodPointBlockPair> _ls, String _k, int _exit, String _cl, int _prCl, int _def) {
        MethodPointBlockPair e_ = pair(_i, "", _k);
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry(_cl,_prCl);
        BreakPointCondition bpc_ = e_.getValue().result(_exit);
        bpc_.getPref().set(_def);
        bpc_.prefsMap(m_);
        _ls.add(e_);
        return e_;
    }
    private static ArrPointBlockPair addArrList(ElInterceptorStdCaller _i, ConcList<ArrPointBlockPair> _ls, String _k, int _exit, String _cl, int _prCl, int _def) {
        ArrPointBlockPair e_ = pairArr(_i, "", _k);
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry(_cl,_prCl);
        BreakPointCondition bpc_ = e_.getValue().resultBpc(_exit);
        bpc_.getPref().set(_def);
        bpc_.prefsMap(m_);
        _ls.add(e_);
        return e_;
    }
    private static ExcPointBlockPair addExcList(ElInterceptorStdCaller _i, ConcList<ExcPointBlockPair> _ls, String _k, int _exit, String _cl, int _prCl, int _def) {
        ExcPointBlockPair e_ = pairExc(_i, "", _k);
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry(_cl,_prCl);
        BreakPointCondition bpc_ = e_.getValue().resultBpc(_exit);
        bpc_.getPref().set(_def);
        bpc_.prefsMap(m_);
        _ls.add(e_);
        return e_;
    }
    private static ParPointBlockPair addParList(ElInterceptorStdCaller _i, ConcList<ParPointBlockPair> _ls, String _k, int _exit, String _cl, int _prCl, int _def) {
        ParPointBlockPair e_ = pairPar(_i, "", _k);
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry(_cl,_prCl);
        BreakPointCondition bpc_ = e_.getValue().getResultGet();
        bpc_.getPref().set(_def);
        bpc_.prefsMap(m_);
        _ls.add(e_);
        return e_;
    }
    private static MethodPointBlockPair pair(ElInterceptorStdCaller _i, String _sgn, String _k) {
        return new MethodPointBlockPair(null, _i, _sgn, true, _k);
    }
    private static ArrPointBlockPair pairArr(ElInterceptorStdCaller _i, String _sgn, String _k) {
        return new ArrPointBlockPair(ExcPointBlockKey.INHERIT, _sgn, _i, true);
    }
    private static ExcPointBlockPair pairExc(ElInterceptorStdCaller _i, String _sgn, String _k) {
        return new ExcPointBlockPair(ExcPointBlockKey.INHERIT, _sgn, _i, true);
    }
    private static ParPointBlockPair pairPar(ElInterceptorStdCaller _i, String _sgn, String _k) {
        return new ParPointBlockPair(ExcPointBlockKey.INHERIT, _sgn, _i, true,null);
    }

    private ExecFileBlock file(ResultContext _cont) {
        return file(_cont,"pkg/Ex");
    }
    private ExecFileBlock file(ResultContext _cont, String _name) {
        return _cont.getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal(_name));
    }
}
