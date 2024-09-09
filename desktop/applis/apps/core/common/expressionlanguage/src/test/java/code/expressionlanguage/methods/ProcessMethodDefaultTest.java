package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.NullStruct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodDefaultTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return default(1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Object exmeth(){\n");
        xml_.append("  return default(null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertSame(NullStruct.NULL_VALUE,calculateNormal("pkg.Ex", id_, cont_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  return default((Integer)null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertSame(NullStruct.NULL_VALUE,calculateNormal("pkg.Ex", id_, cont_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Callee<int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Callee<T> {\n");
        xml_.append(" public staticCall T exmeth(){\n");
        xml_.append("  return default((T)0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  return staticCall(Callee<Integer>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Callee<T> {\n");
        xml_.append(" public staticCall T exmeth(){\n");
        xml_.append("  return default((T)null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertSame(NullStruct.NULL_VALUE,calculateNormal("pkg.Ex", id_, cont_));
    }

    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static char exmeth(){\n");
        xml_.append("  return default('1');\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq('1', getChar(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static boolean exmeth(){\n");
        xml_.append("  return default(true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertTrue(calculateNormal("pkg.Ex", id_, cont_));
    }

    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Character exmeth(){\n");
        xml_.append("  return default((Character)null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertSame(NullStruct.NULL_VALUE,calculateNormal("pkg.Ex", id_, cont_));
    }

    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  return default((Boolean)null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertSame(NullStruct.NULL_VALUE,calculateNormal("pkg.Ex", id_, cont_));
    }

    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int i = 0;\n");
        xml_.append("  switch(i){\n");
        xml_.append("   case 5;default(1);\n");
        xml_.append("   default;\n");
        xml_.append("  }\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
}
