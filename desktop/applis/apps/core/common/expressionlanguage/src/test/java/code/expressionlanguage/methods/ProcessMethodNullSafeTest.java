package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodNullSafeTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static final int v = 1 ?? 2;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return 1 ?? 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return null ?? 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return 1 ?? null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)(null ?? null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (Integer)(null ?? null)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object obj = null;\n");
        xml_.append("  return (Integer)(obj ?? obj)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)(null ?? null)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Callee<int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Callee<T> {\n");
        xml_.append(" public staticCall T exmeth(){\n");
        xml_.append("  return (T)(null ?? null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Callee<int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Callee<T> {\n");
        xml_.append(" public staticCall T exmeth(){\n");
        xml_.append("  Object obj = null;\n");
        xml_.append("  return (T)(obj ?? obj);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Boolean b = null;\n");
        xml_.append("  Boolean c = null;\n");
        xml_.append("  return b ?? c ? 1 : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Boolean b = true;\n");
        xml_.append("  Boolean c = null;\n");
        xml_.append("  return b ?? c ? 1 : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Boolean b = null;\n");
        xml_.append("  Boolean c = true;\n");
        xml_.append("  return b ?? c ? 1 : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Boolean b = true;\n");
        xml_.append("  Boolean c = false;\n");
        xml_.append("  return b ?? c ? 1 : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Boolean b = false;\n");
        xml_.append("  Boolean c = true;\n");
        xml_.append("  return b ?? c ? 1 : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  boolean d = true;\n");
        xml_.append("  return d ? b ?? c : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 1;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  boolean d = true;\n");
        xml_.append("  return d ? b ?? c : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 1;\n");
        xml_.append("  boolean d = true;\n");
        xml_.append("  return d ? b ?? c : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 1;\n");
        xml_.append("  boolean d = true;\n");
        xml_.append("  return d ? b ?? c : 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  boolean d = false;\n");
        xml_.append("  return d ? 2 : b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 1;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  boolean d = false;\n");
        xml_.append("  return d ? 2 : b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 1;\n");
        xml_.append("  boolean d = false;\n");
        xml_.append("  return d ? 2 : b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 1;\n");
        xml_.append("  boolean d = false;\n");
        xml_.append("  return d ? 2 : b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 2;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = null;\n");
        xml_.append("  return b ?? c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c = d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = b ?? c;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c = d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c = d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = b ?? c;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c = d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  return b ??= c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = b ??= c;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument35_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer[] b = {null};\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = b[0] ??= c;\n");
        xml_.append("  return b[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  return b ??= c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = b ??= c;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ??= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ??= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ??= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c ??= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c ??= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c ??= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ??= c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ??= c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ??= c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ?? d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ?? d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ?? d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ??= c ??= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ??= c ??= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ??= c ??= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ??= c ??= d;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ?? 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ??= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument63_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  v.elt(0) ??= 2;\n");
        xml_.append("  return v.val(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public that Integer elt(int i) {\n");
        xml_.append("  return that(v[i]);\n");
        xml_.append(" }\n");
        xml_.append(" public Integer val(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  var t = v[0] ??= 2;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  v[0] ??= 2;\n");
        xml_.append("  return v[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ?? 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = {3};\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ??= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = {3};\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  var t = v[0] ??= 2;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = {3};\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  v[0] ??= 2;\n");
        xml_.append("  return v[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = {3};\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ?? null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ??= null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  var t = v[0] ??= null;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  v[0] ??= null;\n");
        xml_.append("  return v[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object obj = null;\n");
        xml_.append("  return (Integer)(null ?? obj)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var i = 1 ?? 2;\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ?? b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = null;\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ?? b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ba", getString(ret_));
    }
    @Test
    public void calculateArgument78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = null;\n");
        xml_.append("  return a ?? b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ??= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = null;\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ??= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ba", getString(ret_));
    }
    @Test
    public void calculateArgument81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = null;\n");
        xml_.append("  return a ??= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ??= b;\n");
        xml_.append("  return a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = null;\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ??= b;\n");
        xml_.append("  return a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ba", getString(ret_));
    }
    @Test
    public void calculateArgument84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = null;\n");
        xml_.append("  String c = a ??= b;\n");
        xml_.append("  return a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ??= b;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = null;\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ??= b;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ba", getString(ret_));
    }
    @Test
    public void calculateArgument88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = null;\n");
        xml_.append("  String c = a ??= b;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  Object a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ??= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  Object a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ??= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int res;\n");
        xml_.append("  $var inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  $foreach(java.lang.Number e:inst??inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int res;\n");
        xml_.append("  $var inst=$new java.lang.Number[]{3,1,2};\n");
        xml_.append("  $foreach(java.lang.Number e:inst??inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument91_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (null ?? 2);\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument92_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int res;\n");
        xml_.append("  $var inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  $var b = $true;\n");
        xml_.append("  $foreach(java.lang.Number e:b?inst:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int res;\n");
        xml_.append("  $var inst=$new java.lang.Number[]{3,1,2};\n");
        xml_.append("  $foreach($var e:inst??inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int res;\n");
        xml_.append("  $var inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  $foreach($var e:inst??inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Number exmeth(){\n");
        xml_.append("  Number b = 3;\n");
        xml_.append("  Number c = 1;\n");
        xml_.append("  return b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Number exmeth(){\n");
        xml_.append("  Number b = null;\n");
        xml_.append("  Number c = 1;\n");
        xml_.append("  return b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Number exmeth(){\n");
        xml_.append("  Number b = 3;\n");
        xml_.append("  Number c = null;\n");
        xml_.append("  return b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Number exmeth(){\n");
        xml_.append("  Number b = null;\n");
        xml_.append("  Number c = null;\n");
        xml_.append("  return b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static char exmeth(){\n");
        xml_.append("  Character b = null;\n");
        xml_.append("  Character c = 2c;\n");
        xml_.append("  return b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getChar(ret_));

    }
    @Test
    public void calculateArgument100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static char exmeth(){\n");
        xml_.append("  Character b = 1c;\n");
        xml_.append("  Character c = 2c;\n");
        xml_.append("  return b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getChar(ret_));

    }
    @Test
    public void calculateArgument101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static char exmeth(){\n");
        xml_.append("  Character b = null;\n");
        xml_.append("  Character c = null;\n");
        xml_.append("  return b ?? c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getChar(ret_));

    }
    @Test
    public void calculateArgument102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  boolean v = true;\n");
        xml_.append("  return (Integer)(v?1:2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  return b ???= c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = b ???= c;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  return b ???= c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = b ???= c;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ???= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ???= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ?? c ???= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c ???= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c ???= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ?? c ???= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ???= c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument114Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ???= c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument115Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ???= c ?? d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument116Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ?? d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument117Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ?? d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ?? d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ???= c ???= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ???= c ???= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  return b ???= c ???= d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument124Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument125Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument126Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument127Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument128Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = 3;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument129Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = 2;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer b = null;\n");
        xml_.append("  Integer c = null;\n");
        xml_.append("  Integer d = 1;\n");
        xml_.append("  Integer e = b ???= c ???= d;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getNumber(ret_));

    }
    @Test
    public void calculateArgument131Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ???= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument132Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  var t = v[0] ???= 2;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument133Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  v[0] ???= 2;\n");
        xml_.append("  return v[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(2, getNumber(ret_));

    }
    @Test
    public void calculateArgument134Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ???= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = {3};\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument135Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  var t = v[0] ???= 2;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = {3};\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument136Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  v[0] ???= 2;\n");
        xml_.append("  return v[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = {3};\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(3, getNumber(ret_));

    }
    @Test
    public void calculateArgument137Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  return v[0] ???= null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument138Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  var t = v[0] ???= null;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument139Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var v = new ExIndexer();\n");
        xml_.append("  v[0] ???= null;\n");
        xml_.append("  return v[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExIndexer {\n");
        xml_.append(" public Integer[] v = new Integer[1];\n");
        xml_.append(" public Integer this(int i) {\n");
        xml_.append("  return v[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i) {\n");
        xml_.append("  v[i] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));

    }
    @Test
    public void calculateArgument140Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ???= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument141Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = null;\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ???= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ba", getString(ret_));
    }
    @Test
    public void calculateArgument142Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = null;\n");
        xml_.append("  return a ???= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument143Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ???= b;\n");
        xml_.append("  return a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument144Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = null;\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ???= b;\n");
        xml_.append("  return a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ba", getString(ret_));
    }
    @Test
    public void calculateArgument145Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = null;\n");
        xml_.append("  String c = a ???= b;\n");
        xml_.append("  return a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument146Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ???= b;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument147Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = null;\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  String c = a ???= b;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ba", getString(ret_));
    }
    @Test
    public void calculateArgument148Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"ab\";\n");
        xml_.append("  String b = null;\n");
        xml_.append("  String c = a ???= b;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument149Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  Object a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ???= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument150Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  Object a = \"ab\";\n");
        xml_.append("  String b = \"ba\";\n");
        xml_.append("  return a ???= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("ab", getString(ret_));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  return 1 ??;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  return 1 ??=;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  return 1 ?;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  Object b = \"\";\n");
        xml_.append("  return a ??= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth2(){\n");
        xml_.append(" }\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  return a ??= exmeth2();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  return a ?? a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static{\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  return a ?? a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth2(){\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  return exmeth2() ??= a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth2(){\n");
        xml_.append(" }\n");
        xml_.append(" static Object res;\n");
        xml_.append(" static{\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  res=exmeth2() ?? a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        assertEq("",getString(new Argument(getStaticField(cont_, new ClassField("pkg.Ex", "res")))));
    }
    @Test
    public void calculateArgument10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth2(){\n");
        xml_.append(" }\n");
        xml_.append(" static Object res;\n");
        xml_.append(" static{\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  res=a ?? exmeth2();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        assertEq("",getString(new Argument(getStaticField(cont_, new ClassField("pkg.Ex", "res")))));
    }
    @Test
    public void calculateArgument11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth2(){\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  (a ?? a) = a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument12FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  return 1 ???=;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument13FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  Object b = \"\";\n");
        xml_.append("  return a ???= b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument14FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth2(){\n");
        xml_.append(" }\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  return a ???= exmeth2();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument15FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth2(){\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  String a = \"\";\n");
        xml_.append("  return exmeth2() ???= a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
