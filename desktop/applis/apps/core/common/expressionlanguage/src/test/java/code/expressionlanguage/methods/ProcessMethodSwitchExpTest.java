package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodSwitchExpTest extends ProcessMethodCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  return switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 9;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case null;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer a = 2;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case null;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch (a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  return switch [int[]](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return {5};\n");
        xml_.append("   default;\n");
        xml_.append("    return {1};\n");
        xml_.append("  }[0];\n");
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
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 2;\n");
        xml_.append("    return 6;\n");
        xml_.append("   case null;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = MyEnum.ONE;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case ONE;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = MyEnum.TWO;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case ONE;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case ONE;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = MyEnum.ONE;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case null;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case null;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case ONE;\n");
        xml_.append("    return 6;\n");
        xml_.append("   case null;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = MyEnum.ONE;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case null;\n");
        xml_.append("    return 6;\n");
        xml_.append("   case ONE;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  MyEnum a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case null;\n");
        xml_.append("   case ONE;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case MyEnum e;\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i;\n");
        xml_.append("    return i;\n");
        xml_.append("   default d;\n");
        xml_.append("    return 0;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object a = MyEnum.TWO;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case MyEnum e;\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i;\n");
        xml_.append("    return i;\n");
        xml_.append("   default d;\n");
        xml_.append("    return 0;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object a = 1;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case MyEnum e;\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i;\n");
        xml_.append("    return i;\n");
        xml_.append("   default d;\n");
        xml_.append("    return 0;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case null;\n");
        xml_.append("    return 10;\n");
        xml_.append("   case MyEnum e;\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i;\n");
        xml_.append("    return i;\n");
        xml_.append("   default d;\n");
        xml_.append("    return 0;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE,TWO,THREE}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Object a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case MyEnum e;\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i;\n");
        xml_.append("    return i;\n");
        xml_.append("   case null;\n");
        xml_.append("    return 10;\n");
        xml_.append("   default d;\n");
        xml_.append("    return 0;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return switch(a+1){\n");
        xml_.append("     case 11;\n");
        xml_.append("      return 5;\n");
        xml_.append("     default;\n");
        xml_.append("      return 2;\n");
        xml_.append("    };\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int[] t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return switch(a+1){\n");
        xml_.append("     case 11;\n");
        xml_.append("      return {5};\n");
        xml_.append("     default;\n");
        xml_.append("      return {2};\n");
        xml_.append("    };\n");
        xml_.append("   default;\n");
        xml_.append("    return {1};\n");
        xml_.append("  };\n");
        xml_.append("  return t[0];\n");
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
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  }) = 8;\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } = 8);\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  ++switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  };\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ++switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  }++);\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  }++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  that int r = that(switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  });\n");
        xml_.append("  r = 8;\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += 8);\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void test33() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += 8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void test34() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int field = 7;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (switch[int](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } /= 0);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.DivideZero", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (switch[Compo](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += new Compo(8));\n");
        xml_.append("  return field.f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator+ Compo (Compo a, Compo b){\n");
        xml_.append("   return new Compo(a.f+b.f);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void test36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (switch[Compo](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += new Compo(8)).f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator+ Compo (Compo a, Compo b){\n");
        xml_.append("   return new Compo(a.f+b.f);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void test37() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (switch[Compo](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } ++);\n");
        xml_.append("  return field.f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator++ Compo (Compo a){\n");
        xml_.append("   return new Compo(a.f+1);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test38() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (switch[Compo](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } ++).f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator++ Compo (Compo a){\n");
        xml_.append("   return new Compo(a.f+1);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  ++ switch[Compo](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  };\n");
        xml_.append("  return field.f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator++ Compo (Compo a){\n");
        xml_.append("   return new Compo(a.f+1);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (++switch[Compo](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  }).f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator++ Compo (Compo a){\n");
        xml_.append("   return new Compo(a.f+1);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String field = \"7\";\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  (switch[String](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += 8);\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("78", getString(ret_));
    }
    @Test
    public void test42() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String field = \"7\";\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return switch[String](10) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += 8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("78", getString(ret_));
    }
    @Test
    public void test43() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall T exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  T t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return (T)5;\n");
        xml_.append("   default;\n");
        xml_.append("    return (T)1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test44() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).exmeth(10);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall T exmeth(T p){\n");
        xml_.append("  T t = switch(p) {\n");
        xml_.append("   case int i;\n");
        xml_.append("    return (T)(i+1);\n");
        xml_.append("   default d;\n");
        xml_.append("    return (T)d;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void test45() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex<int>().exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public T exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  T t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return (T)5;\n");
        xml_.append("   default;\n");
        xml_.append("    return (T)1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test46() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default;\n");
        xml_.append("   return 0;\n");
        xml_.append(" });\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int f;\n");
        xml_.append(" public Ex(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex(5);\n");
        xml_.append("  Ex g = new Ex(7);\n");
        xml_.append("  return (e+g).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test47() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default;\n");
        xml_.append("   return new pkg.Int(){public int field(){return 0;}}.field();\n");
        xml_.append(" });\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int f;\n");
        xml_.append(" public Ex(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex(5);\n");
        xml_.append("  Ex g = new Ex(7);\n");
        xml_.append("  return (e+g).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test48() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default;\n");
        xml_.append("   public class Loc : pkg.Int {public int field(){return 0;}}\n");
        xml_.append("   return new Loc(){public int field(){return 0;}}.field();\n");
        xml_.append(" });\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int f;\n");
        xml_.append(" public Ex(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex(5);\n");
        xml_.append("  Ex g = new Ex(7);\n");
        xml_.append("  return (e+g).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test49() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 0;\n");
        xml_.append("  for (int a: {9,10}) {\n");
        xml_.append("   t += switch[int](a) {\n");
        xml_.append("    case 10;\n");
        xml_.append("     return 5+([a]);\n");
        xml_.append("    default;\n");
        xml_.append("     return 1+([a]);\n");
        xml_.append("   };\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void test50() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 0;\n");
        xml_.append("  for (int a: {9,10}) {\n");
        xml_.append("   t += switch[int](a) {\n");
        xml_.append("    case 10;\n");
        xml_.append("     return 5+([a])+switch[int](a+1){\n");
        xml_.append("      case 11;\n");
        xml_.append("       return ([a])+a;\n");
        xml_.append("      default;\n");
        xml_.append("       return 0;\n");
        xml_.append("     };\n");
        xml_.append("    default;\n");
        xml_.append("     return 1+([a]);\n");
        xml_.append("   };\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }
    @Test
    public void test51() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return switch(0) {\n");
        xml_.append("   default;\n");
        xml_.append("    return 1/0;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.DivideZero", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test52() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test53() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  return switch(a) {\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("  };\n");
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
    public void test54() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer a = null;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test55() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Integer a = null;\n");
        xml_.append("  (switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test56() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  String a = \"10\";\n");
        xml_.append("  String t = switch(a) {\n");
        xml_.append("   case \"10\";\n");
        xml_.append("    return \"5\";\n");
        xml_.append("   default;\n");
        xml_.append("    return \"1\";\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }
    @Test
    public void test57() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int t = switch(10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return t;\n");
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
    public void test58() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("@Annot(switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("})\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ((Annot)class(Ex).getAnnotations()[0]).info();\n");
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
    public void test59() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("@pkg.Annot(switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("})\n");
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default;\n");
        xml_.append("   public class Loc : pkg.Int {public int field(){return 0;}}\n");
        xml_.append("   return new Loc(){public int field(){return 0;}}.field();\n");
        xml_.append(" });\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int f;\n");
        xml_.append(" public Ex(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ((Annot)Class.getOperators()[0].getAnnotations()[0]).info();\n");
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
    public void test60() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).exmeth(10);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall T exmeth(int... p){\n");
        xml_.append("  T t = switch(p) {\n");
        xml_.append("   case int[] i;\n");
        xml_.append("    return (T)(i[0]+1);\n");
        xml_.append("   default d;\n");
        xml_.append("    return (T)d;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void test61() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Ex<int>).getDeclaredMethods()[1].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall T exmeth(int... p){\n");
        xml_.append("  T t = switch(p) {\n");
        xml_.append("   case int[] i;\n");
        xml_.append("    return (T)(i[0]+1);\n");
        xml_.append("   default d;\n");
        xml_.append("    return (T)d;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test62() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(int... p){\n");
        xml_.append("  int t = switch(p) {\n");
        xml_.append("   case int[] i;\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d;\n");
        xml_.append("    return (int)d;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test63() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append(" public int exmeth(int... p){\n");
        xml_.append("  int t = switch(p) {\n");
        xml_.append("   case int[] i;\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d;\n");
        xml_.append("    return (int)d;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test64() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("@pkg.Annot(switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("})\n");
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default;\n");
        xml_.append("   public class Loc : pkg.Int {public int field(){return 0;}}\n");
        xml_.append("   return new Loc(){public int field(){return 0;}}.field();\n");
        xml_.append(" });\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int f;\n");
        xml_.append(" public Ex(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return Class.getOperators()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void test65() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int t = switch(10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Ex).getDeclaredFields()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test66() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("@Annot(switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("})\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Ex).getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test67() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @Annot(switch[int](10) {\n");
        xml_.append("    case 10;\n");
        xml_.append("     return 5;\n");
        xml_.append("    default;\n");
        xml_.append("     return 1;\n");
        xml_.append(" })\n");
        xml_.append(" public Ex() {\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Ex).getDeclaredConstructors()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test68() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE(\n");
        xml_.append("  switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append(" );MyEnum(int t){}}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(MyEnum).getDeclaredFields()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test69() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE(\n");
        xml_.append("  switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append(" ){ONE(int i){super(i);}};MyEnum(int t){}}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(MyEnum).getDeclaredFields()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test70() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int,int> t = (int a) -> switch[int](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t.call(10);\n");
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
    public void test71() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Long).getDeclaredMethods()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void test72() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Long).getDeclaredConstructors()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void test73() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Long).getDeclaredFields()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void test74() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Long).getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void test75() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    int v = 0;\n");
        xml_.append("    switch (a+1) {\n");
        xml_.append("     case 9;\n");
        xml_.append("      v++;\n");
        xml_.append("    }\n");
        xml_.append("    return 5 + v;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test76() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    int v = 0;\n");
        xml_.append("    switch (a+1) {\n");
        xml_.append("     case 11;\n");
        xml_.append("      v++;\n");
        xml_.append("    }\n");
        xml_.append("    return 5 + v;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void test77() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{ONE(\n");
        xml_.append("  switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append(" ){ONE(int i){super(i);}};MyEnum(int t){}}\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(MyEnum..ONE).getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test78() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{\n");
        xml_.append("  @Annot(\n");
        xml_.append("  switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append("  )\n");
        xml_.append("  ONE{}\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(MyEnum..ONE).getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test79() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{\n");
        xml_.append("  @Annot(\n");
        xml_.append("  switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append("  )\n");
        xml_.append("  ONE{}\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(MyEnum).getDeclaredFields()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test80() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" enum MyEnum{\n");
        xml_.append("  @Annot(\n");
        xml_.append("  switch[int](10) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append("  )\n");
        xml_.append("  ONE\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(MyEnum).getDeclaredFields()[0].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test84() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods().length;\n");
        xml_.append(" }\n");
        xml_.append(" @Annot(\n");
        xml_.append("  switch[int](new int[1]) {\n");
        xml_.append("   case int[] i;\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d;\n");
        xml_.append("    return (int)d;\n");
        xml_.append("  }\n");
        xml_.append(" )\n");
        xml_.append(" public int exmeth(int... p){\n");
        xml_.append("  return p.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test85() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" normal int field(){return 0;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return exmeth(10)+class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousTypes()[0].getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(int... p){\n");
        xml_.append("  int q = switch[int](p) {\n");
        xml_.append("   case int[] i;\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d;\n");
        xml_.append("    return (int)d;\n");
        xml_.append("  } + new @Annot(0) Int(){}.field();\n");
        xml_.append("  return q;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test86() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" normal int field(){return 0;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return exmeth(10)+class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousTypes()[0].getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(int... p){\n");
        xml_.append("  int q = new @Annot(0) Int(){}.field() + switch[int](p) {\n");
        xml_.append("   case int[] i;\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d;\n");
        xml_.append("    return (int)d;\n");
        xml_.append("  };\n");
        xml_.append("  return q;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test87() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot:@AnnotTwo](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test88() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot:@AnnotTwo](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotationsParameters()[0].length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test89() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[:@Annot:@AnnotTwo](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test90() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot:](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test91() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int::@AnnotTwo](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotationsParameters()[0].length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void test92() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int::@AnnotTwo](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void test93() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot:](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ex).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotationsParameters()[0].length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void test94() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[:@Annot](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test95() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
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
    public void test96() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = switch[ExOhter](0){default;return that(method());}+=10;\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static that ExOhter method(){\n");
        xml_.append("  return that(sec);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOhter {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExOhter i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOhter $(ExSub i){\n");
        xml_.append("  ExOhter e = new ExOhter();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExOhter i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void test97() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = switch[ExOhter](0){default;return that(method());}+=10;\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static that ExOhter method(){\n");
        xml_.append("  return that(sec);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOhter {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExOhter i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOhter $(ExSub i){\n");
        xml_.append("  ExOhter e = new ExOhter();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExOhter i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void test98() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,10);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth2(){\n");
        xml_.append("  return switch(9) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test99() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<int>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,10);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int exmeth2(){\n");
        xml_.append("  return switch(9) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test100() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<int>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(new Ex<int>(),10);\n");
        xml_.append(" }\n");
        xml_.append(" public int exmeth2(){\n");
        xml_.append("  return switch(9) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test101() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,MyEnum.TWO);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth2(){\n");
        xml_.append("  return switch(MyEnum.ONE) {\n");
        xml_.append("   case TWO;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append(" public enum MyEnum{\n");
        xml_.append("  ONE,TWO,THREE\n");
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
    public void test102() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<int>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,MyEnum.TWO);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int exmeth2(){\n");
        xml_.append("  return switch(MyEnum.ONE) {\n");
        xml_.append("   case TWO;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append(" public enum MyEnum{\n");
        xml_.append("  ONE,TWO,THREE\n");
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
    public void test103() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<int>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(new Ex<int>(),MyEnum.TWO);\n");
        xml_.append(" }\n");
        xml_.append(" public int exmeth2(){\n");
        xml_.append("  return switch(MyEnum.ONE) {\n");
        xml_.append("   case TWO;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append(" public enum MyEnum{\n");
        xml_.append("  ONE,TWO,THREE\n");
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
    public void test104() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth2(Object p){\n");
        xml_.append("  return switch(p) {\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   default t;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test105() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<Integer>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int exmeth2(T p){\n");
        xml_.append("  return switch(p) {\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   default t;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test106() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<Integer>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(new Ex<Integer>(),5);\n");
        xml_.append(" }\n");
        xml_.append(" public int exmeth2(T p){\n");
        xml_.append("  return switch(p) {\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   default t;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test107() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(0,5);\n");
        xml_.append(" }\n");
        xml_.append(" public int exmeth2(T p){\n");
        xml_.append("  return switch(p) {\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   default t;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.BadCast", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test108() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<Integer>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(new Ex<Integer>(),\"5\");\n");
        xml_.append(" }\n");
        xml_.append(" public int exmeth2(T p){\n");
        xml_.append("  return switch(p) {\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   default t;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.BadCast", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test109() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var m = class(Ex<int>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0];\n");
        xml_.append("  m.invoke(new Ex<int>(),5);\n");
        xml_.append("  m.getDeclaredAnonymousLambdaLocVars(\"p\",0,(Object)null);\n");
        xml_.append("  return (int)m.invoke(new Ex<int>(),5);\n");
        xml_.append(" }\n");
        xml_.append(" public int exmeth2(T p){\n");
        xml_.append("  return switch(p) {\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   default t;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.NullObject", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test110() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex<Integer>).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,\"5\");\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int exmeth2(T p){\n");
        xml_.append("  return switch(p) {\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   default t;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.BadCast", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test111() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods(\".1\",true,false,class(int))[0].invoke(null,10);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth2(){\n");
        xml_.append("  return switch(9) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
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
    public void test112() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,\"TWO\");\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth2(){\n");
        xml_.append("  return switch(MyEnum.ONE) {\n");
        xml_.append("   case TWO;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append(" public enum MyEnum{\n");
        xml_.append("  ONE,TWO,THREE\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.BadCast", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test113() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int) class(Ex).getDeclaredMethods()[1].getDeclaredSwitchMethods()[0].invoke(null,\"10\");\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth2(){\n");
        xml_.append("  return switch(9) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append(" public enum MyEnum{\n");
        xml_.append("  ONE,TWO,THREE\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("$core.BadCast", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void test114() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
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
    public void test115() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(t:switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
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
    public void test116() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(2,switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int s,int t){\n");
        xml_.append("  return s+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void test117() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(2,t:switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int s,int t){\n");
        xml_.append("  return s+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void test118() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(new int[]{switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }});\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[] t){\n");
        xml_.append("  return t[0];\n");
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
    public void test119() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return {5};\n");
        xml_.append("   default;\n");
        xml_.append("    return {1};\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[] t){\n");
        xml_.append("  return t[0];\n");
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
    public void test120() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int... t){\n");
        xml_.append("  return t[0];\n");
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
    public void test121() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int b = 11;\n");
        xml_.append("  int t = id(switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  },switch(b) {\n");
        xml_.append("   case 11;\n");
        xml_.append("    return 7;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int... t){\n");
        xml_.append("  return t[0]+t[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test122() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(new int[]{switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }});\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int... t){\n");
        xml_.append("  return t[0];\n");
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
    public void test123() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(new int[]{switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }});\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[]... t){\n");
        xml_.append("  return t[0][0];\n");
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
    public void test124() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(new int[]{switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }});\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[] t){\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[]... t){\n");
        xml_.append("  return t[0][0];\n");
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
    public void test125() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(new int[][]{new int[]{switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }}});\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int... t){\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[]... t){\n");
        xml_.append("  return t[0][0];\n");
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
    public void test126() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(t:switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return {5};\n");
        xml_.append("   default;\n");
        xml_.append("    return {1};\n");
        xml_.append("  },u:7);\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[] t,int u){\n");
        xml_.append("  return t[0]+u;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int[] s){\n");
        xml_.append("  return s[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test127() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int t;\n");
        xml_.append(" public Ex(int t){\n");
        xml_.append("  this.t = t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = new Ex(switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }).t;\n");
        xml_.append("  return t;\n");
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
    public void test128() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int t;\n");
        xml_.append(" public Ex(int t){\n");
        xml_.append("  this.t = t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = new Ex(t:switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }).t;\n");
        xml_.append("  return t;\n");
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
    public void test129() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int t;\n");
        xml_.append(" public Ex(int t){\n");
        xml_.append("  this.t = t;\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(int u, int s){\n");
        xml_.append("  this.t = u + s;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = new Ex(7,switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }).t;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test130() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int t;\n");
        xml_.append(" public Ex(int t){\n");
        xml_.append("  this.t = t;\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(int u, int s){\n");
        xml_.append("  this.t = u + s;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = new Ex(u:switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  },s:7).t;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test131() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int t;\n");
        xml_.append(" public Ex(String t){\n");
        xml_.append("  this.t = t.length();\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(int u, int s){\n");
        xml_.append("  this.t = u + s;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = new Ex(switch[int](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  },7).t;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test132() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int t;\n");
        xml_.append(" public Ex(String u){\n");
        xml_.append("  this.t = u.length();\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(int u, int s){\n");
        xml_.append("  this.t = u + s;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = new Ex(u:switch[int](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  },s:7).t;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void test133() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(switch[int](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(String t){\n");
        xml_.append("  return t.length();\n");
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
    public void test134() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = id(t:switch[int](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  });\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(int t){\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static int id(String t){\n");
        xml_.append("  return t.length();\n");
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
    public void test135() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  Fct<int,int> f = staticCall(Ex<>).$lambda(id);\n");
        xml_.append("  int t = f.call(a:{switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  }});\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int id(int t){\n");
        xml_.append("  return t;\n");
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
    public void test136() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  Fct<int[],int[]> f = staticCall(Ex<>).$lambda(id);\n");
        xml_.append("  int[] t = f.call(switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return {5};\n");
        xml_.append("   default;\n");
        xml_.append("    return {1};\n");
        xml_.append("  });\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int[] id(int[] t){\n");
        xml_.append("  return t;\n");
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
    public void testAss() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void fail1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  (switch(0));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly("en",files_));
    }
    @Test
    public void fail2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public abstract final class pkg.FinAbs {}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  (switch[int](null){default; return 0;});\n");
        xml_.append("  (switch[int]((FinAbs)null){default; return 0;});\n");
        xml_.append("  (switch[int}((FinAbs)null){default; return 0;});\n");
        xml_.append("  (switch[int});\n");
        xml_.append("  (switch);\n");
        xml_.append("  (switch[int]((Object)null){default d; return that(null);});\n");
        xml_.append("  (switch[int]((Object)null){default d; that(null);});\n");
        xml_.append("  (switch[int]((Object)null){default d; that(null);));\n");
        xml_.append("  (switch[int][(Object)null]{default d; that(null);});\n");
        xml_.append("  (switch[int:::]((Object)null){});\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly("en",files_));
    }
}
