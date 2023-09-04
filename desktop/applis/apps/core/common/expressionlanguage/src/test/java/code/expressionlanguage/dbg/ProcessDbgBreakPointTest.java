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
        assertEq("-1/0",new BreakPointBlockPairKeyString().keyString(new BreakPointBlockPair(null,-1,0,new ElInterceptorStdCaller(),false)));
        assertEq("-1/0",new BreakPointBlockPairKeyIdString().keyString(new BreakPointBlockKey(null,-1,0)));
        assertEq("-1/0",new ExecFileBlockTraceIndexKeyString().keyString(new ExecFileBlockTraceIndex(null,-1,0)));
        assertEq("",new ExecFileBlockTraceIndexKeyString().keyString(new ExecFileBlockFct("","")));
        assertEq("",new ExecFileBlockFct("","").valueStr());
        assertFalse(StringUtil.nullToEmpty(new ExecFileBlockTraceIndex(null,-1,0).valueStr()+"_").isEmpty());
        assertFalse(new BpcKeyString().keyString(new BreakPointCondition(new ElInterceptorStdCaller(),new BreakPointBlockKey(null,-1,0),0,0)).isEmpty());
        assertFalse(new BpcKeyString().keyString(new BreakPointCondition(new ElInterceptorStdCaller(),new BreakPointBlockKey(null,-1,0),10,0)).isEmpty());
        assertEq(-1, NumberUtil.signum(new CmpMethodPair().compare(new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "0", ""),2,null),new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "1", ""),2, null))));
        assertEq(1, NumberUtil.signum(new CmpMethodPair().compare(new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "0", ""),3,null),new MethodPointBlockPairRootBlock(pair(new ElInterceptorStdCaller(), "1", ""),2, null))));
    }
    @Test
    public void pref1() {
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),new ElInterceptorStdCaller());
        assertEq(0,BreakPointBlockList.pref(ls_,false));
    }
    @Test
    public void pref2() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", false);
        assertEq(0,e_.getValue().result(false).getPref().get());
    }

    @Test
    public void pref3() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", false);
        MethodPointBlockPair f_ = add(i_, ls_, "1", false);
        assertEq(0,e_.getValue().result(false).getPref().get());
        assertEq(1,f_.getValue().result(false).getPref().get());
    }

    @Test
    public void pref4() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", false);
        MethodPointBlockPair f_ = add(i_, ls_, "1", false);
        MethodPointBlockPair g_ = add(i_, ls_, "2", false);
        assertEq(0,e_.getValue().result(false).getPref().get());
        assertEq(1,f_.getValue().result(false).getPref().get());
        assertEq(2,g_.getValue().result(false).getPref().get());
    }

    @Test
    public void pref5() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", false);
        MethodPointBlockPair f_ = add(i_, ls_, "1", false);
        f_.getValue().result(false).getPref().set(2);
        MethodPointBlockPair g_ = add(i_, ls_, "2", false);
        assertEq(0,e_.getValue().result(false).getPref().get());
        assertEq(2,f_.getValue().result(false).getPref().get());
        assertEq(1,g_.getValue().result(false).getPref().get());
    }

    @Test
    public void pref6() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", false);
        MethodPointBlockPair f_ = add(i_, ls_, "1", false);
        f_.getValue().result(false).getPref().set(3);
        MethodPointBlockPair g_ = add(i_, ls_, "2", false);
        MethodPointBlockPair h_ = add(i_, ls_, "3", false);
        assertEq(0,e_.getValue().result(false).getPref().get());
        assertEq(3,f_.getValue().result(false).getPref().get());
        assertEq(1,g_.getValue().result(false).getPref().get());
        assertEq(2,h_.getValue().result(false).getPref().get());
    }

    @Test
    public void pref7() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", false);
        e_.getValue().result(false).getPref().set(1);
        MethodPointBlockPair f_ = add(i_, ls_, "1", false);
        assertEq(1,e_.getValue().result(false).getPref().get());
        assertEq(0,f_.getValue().result(false).getPref().get());
    }
    @Test
    public void pref8() {
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),new ElInterceptorStdCaller());
        assertEq(0,BreakPointBlockList.pref(ls_,true));
    }
    @Test
    public void pref9() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", true);
        assertEq(0,e_.getValue().result(true).getPref().get());
    }

    @Test
    public void pref10() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", true);
        MethodPointBlockPair f_ = add(i_, ls_, "1", true);
        assertEq(0,e_.getValue().result(true).getPref().get());
        assertEq(1,f_.getValue().result(true).getPref().get());
    }

    @Test
    public void pref11() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", true);
        MethodPointBlockPair f_ = add(i_, ls_, "1", true);
        MethodPointBlockPair g_ = add(i_, ls_, "2", true);
        assertEq(0,e_.getValue().result(true).getPref().get());
        assertEq(1,f_.getValue().result(true).getPref().get());
        assertEq(2,g_.getValue().result(true).getPref().get());
    }

    @Test
    public void pref12() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", true);
        MethodPointBlockPair f_ = add(i_, ls_, "1", true);
        f_.getValue().result(true).getPref().set(2);
        MethodPointBlockPair g_ = add(i_, ls_, "2", true);
        assertEq(0,e_.getValue().result(true).getPref().get());
        assertEq(2,f_.getValue().result(true).getPref().get());
        assertEq(1,g_.getValue().result(true).getPref().get());
    }

    @Test
    public void pref13() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", true);
        MethodPointBlockPair f_ = add(i_, ls_, "1", true);
        f_.getValue().result(true).getPref().set(3);
        MethodPointBlockPair g_ = add(i_, ls_, "2", true);
        MethodPointBlockPair h_ = add(i_, ls_, "3", true);
        assertEq(0,e_.getValue().result(true).getPref().get());
        assertEq(3,f_.getValue().result(true).getPref().get());
        assertEq(1,g_.getValue().result(true).getPref().get());
        assertEq(2,h_.getValue().result(true).getPref().get());
    }

    @Test
    public void pref14() {
        ElInterceptorStdCaller i_ = new ElInterceptorStdCaller();
        ConcList<MethodPointBlockPair> ls_ = new ConcList<MethodPointBlockPair>(new MethodKeyString(),i_);
        MethodPointBlockPair e_ = add(i_, ls_, "0", true);
        e_.getValue().result(true).getPref().set(1);
        MethodPointBlockPair f_ = add(i_, ls_, "1", true);
        assertEq(1,e_.getValue().result(true).getPref().get());
        assertEq(0,f_.getValue().result(true).getPref().get());
    }
    private static MethodPointBlockPair add(ElInterceptorStdCaller _i, ConcList<MethodPointBlockPair> _ls, String _k, boolean _exit) {
        MethodPointBlockPair e_ = pair(_i, "", _k);
        e_.getValue().result(_exit).getPref().set(BreakPointBlockList.pref(_ls, _exit));
        _ls.add(e_);
        return e_;
    }

    private static MethodPointBlockPair pair(ElInterceptorStdCaller _i, String _sgn, String _k) {
        return new MethodPointBlockPair(null, _i, _sgn, true, _k);
    }

    private ExecFileBlock file(ResultContext _cont) {
        return file(_cont,"pkg/Ex");
    }
    private ExecFileBlock file(ResultContext _cont, String _name) {
        return _cont.getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal(_name));
    }
}
