package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodRefRangeTest extends ProcessMethodCommon {
    @Test
    public void calculate1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculate3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculate5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4???-1];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculate6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4???-1];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4???-1];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]=o[2???4???-1];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculate9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???]=o[1???];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculate10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???]=o[1???];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???]=o[1???];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???]=o[1???];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculate13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????-1]=o[1???];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculate14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????-1]=o[1???];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????-1]=o[1???];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????-1]=o[1???];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }

    @Test
    public void calculate17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????1]=o[1???];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculate18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????1]=o[1???];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????1]=o[1???];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????1]=o[1???];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculate21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3???-1]=o[1???3];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3???-1]=o[1???3];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculate23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3???-1]=o[1???3];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3???-1]=o[1???3];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateComp1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]+=o[2???4];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateComp2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]+=o[2???4];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateComp3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]+=o[2???4];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateComp4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???3]+=o[2???4];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateSemi1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  t[1???3]++;\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculateSemi2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  t[1???3]++;\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculateSemi3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  t[1???3]++;\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateSemi4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  t[1???3]++;\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateSemiPre1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  ++t[1???3];\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculateSemiPre2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  ++t[1???3];\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculateSemiPre3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  ++t[1???3];\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateSemiPre4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  ++t[1???3];\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }

    @Test
    public void calculateCompRet1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  int[] r = t[1???3]+=o[2???4];\n");
        xml_.append("  return r[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateCompRet2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  int[] r = t[1???3]+=o[2???4];\n");
        xml_.append("  return r[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateCompRet3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  int[] r = t[1???3]+=o[2???4];\n");
        xml_.append("  return r.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateSemiRet1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] r = t[1???3]++;\n");
        xml_.append("  return r[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateSemiRet2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] r = t[1???3]++;\n");
        xml_.append("  return r[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateSemiRet3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] r = t[1???3]++;\n");
        xml_.append("  return r.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateSemiPreRet1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] r = ++t[1???3];\n");
        xml_.append("  return r[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculateSemiPreRet2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] r = ++t[1???3];\n");
        xml_.append("  return r[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculateSemiPreRet3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] r = ++t[1???3];\n");
        xml_.append("  return r.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateParam1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculateParam2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculateParam3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateParam4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateCompParam1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t+=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateCompParam2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t+=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateCompParam3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t+=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateCompParam4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ int[] (int[] a, int[] b) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + b[i];\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  update(that(t[1???3]),o[2???4]);\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t, int[] v){\n");
        xml_.append("  t+=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateSemiParam1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculateSemiParam2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculateSemiParam3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateSemiParam4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateSemiPreParam1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  ++t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculateSemiPreParam2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  ++t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculateSemiPreParam3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  ++t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateSemiPreParam4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ int[] (int[] a) {\n");
        xml_.append(" int[] out = new int[a.length];\n");
        xml_.append(" iter(int i = 0; a.length; 1){\n");
        xml_.append("  out[i] = a[i] + 1;\n");
        xml_.append(" }\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  update(that(t[1???3]));\n");
        xml_.append("  return t[3];\n");
        xml_.append(" }\n");
        xml_.append(" public static void update(that int[] t){\n");
        xml_.append("  ++t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateInit() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static{\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  ExArr.ARR[1???3]=o[2???4];\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExArr.ARR[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExArr {\n");
        xml_.append(" public static final int[] ARR = {2,4,6,8};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate1Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1???]=o[2???4];\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(),err_.getClassName());
        assertEq("3!=2",((StringStruct)err_.getMessage()).getInstance());
    }
    @Test
    public void calculate2Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[1??????-1]=o[2???4];\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(),err_.getClassName());
        assertEq("3!=2",((StringStruct)err_.getMessage()).getInstance());
    }
    @Test
    public void calculate3Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[0???5]=o[2???4];\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(),err_.getClassName());
        assertEq("5>=4",((StringStruct)err_.getMessage()).getInstance());
    }
    @Test
    public void calculate4Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  int[] o = {1,3,5,7};\n");
        xml_.append("  t[5???]=o[2???4];\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(),err_.getClassName());
        assertEq("5>=4",((StringStruct)err_.getMessage()).getInstance());
    }
    @Test
    public void calculate5Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object[] t = new int[]{2,4,6,8};\n");
        xml_.append("  String[] o = {\"1\",\"3\",\"5\",\"7\"};\n");
        xml_.append("  t[1???]=o[2???4];\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasStore(),err_.getClassName());
        assertEq("$core.String!=int",((StringStruct)err_.getMessage()).getInstance());
    }
    @Test
    public void calculate6Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object[] t = new int[]{2,4,6,8};\n");
        xml_.append("  String[] o = {\"1\",\"3\",null,\"7\"};\n");
        xml_.append("  t[1???]=o[2???4];\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),err_.getClassName());
    }
    @Test
    public void calculate7Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object[] t = new int[]{2,4,6,8};\n");
        xml_.append("  t[1???]=null;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),err_.getClassName());
    }
    @Test
    public void calculate8Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object[] t = new int[]{2,4,6,8};\n");
        xml_.append("  t[(Range)null]=null;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),err_.getClassName());
    }
    @Test
    public void calculate9Ex() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object[] t = null;\n");
        xml_.append("  t[(Range)null]=null;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        ErrorStruct err_ = (ErrorStruct) ret_.getStruct();
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),err_.getClassName());
    }
    @Test
    public void lda1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int[],int,int[],int[]> l = $lambda(int[],[:]=);\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  l.call(t,1, new int[]{1,3,5});\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void lda2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  Fct<int,int[],int[]> l = t.$lambda(int[],[:]=);\n");
        xml_.append("  l.call(1, new int[]{1,3,5});\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void lda3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  Fct<int[],int,int[],int[]> l = $lambda(int[],[:]=);\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  l.call(t, 5,new int[]{5,7});\n");
        xml_.append("  return {0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(), ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void lda4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  Fct<int,int[],int[]> l = t.$lambda(int[],[:]=);\n");
        xml_.append("  l.call(5,new int[]{5,7});\n");
        xml_.append("  return {0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(), ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void lda5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  Fct<int[],int,int[],int[]> l = $lambda(int[],[:]=);\n");
        xml_.append("  int[] t = null;\n");
        xml_.append("  l.call(t,5,new int[]{5,7});\n");
        xml_.append("  return {0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void lda6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  Fct<int,int[],int[]> l = t.$lambda(int[],[:]=);\n");
        xml_.append("  l.call(-1,new int[]{5,7});\n");
        xml_.append("  return {0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(), ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void lda7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  Fct<int[],int,int[],int[]> l = $lambda(int[],[:]=);\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  l.call(t,-1,new int[]{5,7});\n");
        xml_.append("  return {0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(), ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void lda8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int[],int,int,int[],int[]> l = $lambda(int[],[:]=,int);\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  l.call(t,1,1,new int[]{3,5,7});\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void lda9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  Fct<int,int,int[],int[]> l = t.$lambda(int[],[:]=,int);\n");
        xml_.append("  l.call(1,1,new int[]{3,5,7});\n");
        xml_.append("  return t[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void lda10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  Fct<int,int,int[],int[]> l = t.$lambda(int[],[:]=,int);\n");
        xml_.append("  l.call(-1,1,new int[]{5,7});\n");
        xml_.append("  return {0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(), ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void lda11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int[],Range,int[],int[]> l = $lambda(int[],[]=,Range);\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  l.call(t,1???3, new int[]{5,7});\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void lda12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  Fct<Range,int[],int[]> l = t.$lambda(int[],[]=,Range);\n");
        xml_.append("  l.call(1???3, new int[]{5,7});\n");
        xml_.append("  return t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void lda13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  Fct<int[],Range,int[],int[]> l = $lambda(int[],[]=,Range);\n");
        xml_.append("  int[] t = {2,4,6,8};\n");
        xml_.append("  return l.call(t,null, new int[]{5,7});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), ret_.getStruct().getClassName(cont_));
    }

}
