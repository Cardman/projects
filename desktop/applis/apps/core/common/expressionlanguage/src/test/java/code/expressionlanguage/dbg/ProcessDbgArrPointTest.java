package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgArrPointTest extends ProcessDbgCommon {
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
        assertFalse(cont_.isArr("pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        assertFalse(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME);
        assertFalse(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex3",ExcPointBlockKey.SAME);
        assertFalse(cont_.isArr("[pkg.Ex3",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        assertFalse(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPointEnabled("[pkg.Ex3",ExcPointBlockKey.SAME);
        assertFalse(cont_.isArr("[pkg.Ex3",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME);
        assertFalse(cont_.isArr("[pkg.Ex2", ExcPointBlockKey.SAME_FAMILY));
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME_FAMILY));
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        cont_.toggleArrPoint("[pkg.Ex2",ExcPointBlockKey.SAME);
        cont_.toggleArrPointEnabled("[pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
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
        cont_.toggleArrPointEnabled("[pkg.Ex2",ExcPointBlockKey.SAME_FAMILY);
        cont_.toggleArrPointEnabled("[pkg.Ex2",ExcPointBlockKey.SAME);
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME_FAMILY));
        assertTrue(cont_.isArr("[pkg.Ex2",ExcPointBlockKey.SAME));
    }
}
