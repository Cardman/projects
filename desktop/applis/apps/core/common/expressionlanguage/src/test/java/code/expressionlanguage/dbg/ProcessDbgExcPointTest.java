package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.dbg.ExcKeyString;
import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.sample.ElInterceptorStdCaller;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgExcPointTest extends ProcessDbgCommon {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        assertFalse(cont_.isExc("pkg.Ex", ExcPointBlockKey.SAME));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        assertFalse(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
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
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME);
        assertFalse(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex3",ExcPointBlockKey.SAME);
        assertFalse(cont_.isExc("pkg.Ex3",ExcPointBlockKey.SAME));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        assertFalse(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPointEnabled("pkg.Ex3",ExcPointBlockKey.SAME);
        assertFalse(cont_.isExc("pkg.Ex3",ExcPointBlockKey.SAME));
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
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
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
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
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
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
    }

    @Test
    public void test13() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("",ExcPointBlockKey.SAME));
    }
    @Test
    public void test14() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPoint("pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
    }
    @Test
    public void test15() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex",ExcPointBlockKey.SAME));
    }

    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME);
        assertFalse(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME_FAMILY));
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
    }

    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME_FAMILY));
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
    }

    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        cont_.toggleExcPoint("pkg.Ex2",ExcPointBlockKey.SAME);
        cont_.toggleExcPointEnabled("pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
    }

    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int f,s;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleExcPointEnabled("pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        cont_.toggleExcPointEnabled("pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME_FAMILY));
        assertTrue(cont_.isExc("pkg.Ex2",ExcPointBlockKey.SAME));
    }
    @Test
    public void test() {
        assertEq("2",new ExcKeyString().keyString(new ExcPointBlockPair(ExcPointBlockKey.SAME,"",new ElInterceptorStdCaller(),false)));
        assertEq("1",new ExcKeyString().keyString(new ExcPointBlockPair(ExcPointBlockKey.SAME_FAMILY,"",new ElInterceptorStdCaller(),false)));
    }
}
