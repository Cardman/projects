package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodSwitchExpTest extends ProcessMethodCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case null:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case null:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return {5};\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 2:\n");
        xml_.append("    return 6;\n");
        xml_.append("   case null:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case ONE:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case ONE:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case ONE:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case null:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case null:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case ONE:\n");
        xml_.append("    return 6;\n");
        xml_.append("   case null:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case null:\n");
        xml_.append("    return 6;\n");
        xml_.append("   case ONE:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case null:\n");
        xml_.append("   case ONE:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case MyEnum e:\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i:\n");
        xml_.append("    return i;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case MyEnum e:\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i:\n");
        xml_.append("    return i;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case MyEnum e:\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i:\n");
        xml_.append("    return i;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case null:\n");
        xml_.append("    return 10;\n");
        xml_.append("   case MyEnum e:\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i:\n");
        xml_.append("    return i;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case MyEnum e:\n");
        xml_.append("    return e.ordinal();\n");
        xml_.append("   case int i:\n");
        xml_.append("    return i;\n");
        xml_.append("   case null:\n");
        xml_.append("    return 10;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return switch(a+1){\n");
        xml_.append("     case 11:\n");
        xml_.append("      return 5;\n");
        xml_.append("     default:\n");
        xml_.append("      return 2;\n");
        xml_.append("    };\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return switch(a+1){\n");
        xml_.append("     case 11:\n");
        xml_.append("      return {5};\n");
        xml_.append("     default:\n");
        xml_.append("      return {2};\n");
        xml_.append("    };\n");
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return (T)5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case int i:\n");
        xml_.append("    return (T)(i+1);\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return (T)5;\n");
        xml_.append("   default:\n");
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
        xml_.append("  default:\n");
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
        xml_.append("  default:\n");
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
        xml_.append("  default:\n");
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
        xml_.append("    case 10:\n");
        xml_.append("     return 5+([a]);\n");
        xml_.append("    default:\n");
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
        xml_.append("    case 10:\n");
        xml_.append("     return 5+([a])+switch[int](a+1){\n");
        xml_.append("      case 11:\n");
        xml_.append("       return ([a])+a;\n");
        xml_.append("      default:\n");
        xml_.append("       return 0;\n");
        xml_.append("     };\n");
        xml_.append("    default:\n");
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
        xml_.append("   default:\n");
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
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("   case 10:\n");
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
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("   case 10:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case \"10\":\n");
        xml_.append("    return \"5\";\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("})\n");
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default:\n");
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
        xml_.append("   case int[] i:\n");
        xml_.append("    return (T)(i[0]+1);\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case int[] i:\n");
        xml_.append("    return (T)(i[0]+1);\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case int[] i:\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case int[] i:\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("})\n");
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("    case 10:\n");
        xml_.append("     return 5;\n");
        xml_.append("    default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    int v = 0;\n");
        xml_.append("    switch (a+1) {\n");
        xml_.append("     case 9:\n");
        xml_.append("      v++;\n");
        xml_.append("    }\n");
        xml_.append("    return 5 + v;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    int v = 0;\n");
        xml_.append("    switch (a+1) {\n");
        xml_.append("     case 11:\n");
        xml_.append("      v++;\n");
        xml_.append("    }\n");
        xml_.append("    return 5 + v;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("   case int[] i:\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case int[] i:\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d:\n");
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
        xml_.append("   case int[] i:\n");
        xml_.append("    return i[0]+1;\n");
        xml_.append("   default d:\n");
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
    public void testAss() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
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
        xml_.append("  (switch[int](null){default: return 0;});\n");
        xml_.append("  (switch[int]((FinAbs)null){default: return 0;});\n");
        xml_.append("  (switch[int}((FinAbs)null){default: return 0;});\n");
        xml_.append("  (switch[int});\n");
        xml_.append("  (switch);\n");
        xml_.append("  (switch[int]((Object)null){default d: return that(null);});\n");
        xml_.append("  (switch[int]((Object)null){default d: that(null);});\n");
        xml_.append("  (switch[int]((Object)null){default d: that(null);));\n");
        xml_.append("  (switch[int][(Object)null]{default d: that(null);});\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly("en",files_));
    }
}
