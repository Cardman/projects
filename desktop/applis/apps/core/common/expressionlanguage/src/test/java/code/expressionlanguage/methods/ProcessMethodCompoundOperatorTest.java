package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodCompoundOperatorTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator& pkg.Ex (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+b.a;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex one = new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  one &= two;\n");
        xml_.append("  if (one.a != 8i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+b.a;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex one = new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  one += two;\n");
        xml_.append("  if (one.a != 8i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+b.a;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex[] one = {new Ex()};\n");
        xml_.append("  one[0].a=5i;\n");
        xml_.append("  Ex two = new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  one[0] += two;\n");
        xml_.append("  if (one[0].a != 8i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+b.a;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  ExCont one = new ExCont();\n");
        xml_.append("  one.field.a=5i;\n");
        xml_.append("  ExCont two = new ExCont();\n");
        xml_.append("  two.field.a=3i;\n");
        xml_.append("  one.field += two.field;\n");
        xml_.append("  if (one.field.a != 8i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex one = new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex bk = one ++;\n");
        xml_.append("  if (bk.a != 5i){\n");
        xml_.append("   return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  if (one.a != 6i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex[] one = {new Ex()};\n");
        xml_.append("  one[0].a=5i;\n");
        xml_.append("  Ex bk = one[0] ++;\n");
        xml_.append("  if (bk.a != 5i){\n");
        xml_.append("   return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  if (one[0].a != 6i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  ExCont one = new ExCont();\n");
        xml_.append("  one.field.a=5i;\n");
        xml_.append("  Ex bk = one.field ++;\n");
        xml_.append("  if (bk.a != 5i){\n");
        xml_.append("   return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  if (one.field.a != 6i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex one = new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex bk = ++ one;\n");
        xml_.append("  if (bk.a != 6i){\n");
        xml_.append("   return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  if (one.a != 6i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex[] one = {new Ex()};\n");
        xml_.append("  one[0].a=5i;\n");
        xml_.append("  Ex bk = ++ one[0];\n");
        xml_.append("  if (bk.a != 6i){\n");
        xml_.append("   return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  if (one[0].a != 6i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  ExCont one = new ExCont();\n");
        xml_.append("  one.field.a=5i;\n");
        xml_.append("  Ex bk = ++ one.field;\n");
        xml_.append("  if (bk.a != 6i){\n");
        xml_.append("   return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  if (one.field.a != 6i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+b.a;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  Ex two = new Ex();\n");
        xml_.append("  two.a = 2;\n");
        xml_.append("  for (Ex i = new Ex(); i.a < 10; i += two){\n");
        xml_.append("   sum += i.a;\n");
        xml_.append("  }\n");
        xml_.append("  if (sum != 20i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (Ex i = new Ex(); i.a < 4; ){\n");
        xml_.append("   Ex bk = i++;\n");
        xml_.append("   if (bk.a + 1 != i.a){\n");
        xml_.append("    return 2;\n");
        xml_.append("   }\n");
        xml_.append("   sum += bk.a;\n");
        xml_.append("  }\n");
        xml_.append("  if (sum != 6){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (Ex i = new Ex(); i.a < 4; ){\n");
        xml_.append("   Ex bk = ++i;\n");
        xml_.append("   if (bk.a != i.a){\n");
        xml_.append("    return 2;\n");
        xml_.append("   }\n");
        xml_.append("   sum += bk.a;\n");
        xml_.append("  }\n");
        xml_.append("  if (sum != 10){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public String field;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  ExCont one = new ExCont();\n");
        xml_.append("  one.field=\"5i\";\n");
        xml_.append("  ExCont two = new ExCont();\n");
        xml_.append("  two.field=\"3i\";\n");
        xml_.append("  one.field += two.field;\n");
        xml_.append("  if (one.field != \"5i3i\"){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Singleton {\n");
        xml_.append(" static ExCont one(){ return ExCont.instance;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append(" static final ExCont instance = new ExCont();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Singleton.one;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  ExCont stInfo = one();\n");
        xml_.append("  Ex bk = stInfo.field ++;\n");
        xml_.append("  out = stInfo.field.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int res = out;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Singleton {\n");
        xml_.append(" static Ex one(){ return ExCont.instance;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" static Ex instance = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Singleton.one;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  Ex bk = ExCont.instance ++;\n");
        xml_.append("  out = ExCont.instance.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int res = out;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append(" static final ExCont one = new ExCont();\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExCont.one;] pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  one.field.a=5i;\n");
        xml_.append("  Ex bk = one.field ++;\n");
        xml_.append("  out = one.field.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSingleton {\n");
        xml_.append(" static final ExSingleton one = new ExSingleton();\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExSingleton.one;] pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  one.field.a=5i;\n");
        xml_.append("  Ex bk = one.field ++;\n");
        xml_.append("  out = one.field.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Singleton {\n");
        xml_.append(" static ExCont one(){ return ExCont.instance;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append(" static final ExCont instance = new ExCont();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Singleton.one;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  ExCont stInfo = one();\n");
        xml_.append("  Ex bk = ++stInfo.field ;\n");
        xml_.append("  out = bk.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int res = out;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Singleton {\n");
        xml_.append(" static Ex one(){ return ExCont.instance;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" static Ex instance = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Singleton.one;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  Ex bk = ++ExCont.instance ;\n");
        xml_.append("  out = bk.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int res = out;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" static int instance = 2;\n");
        xml_.append(" static int zero = 0;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExCont.*;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  Object bk = instance /= zero;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" private static Object field;\n");
        xml_.append(" private static Object res;\n");
        xml_.append(" static{\n");
        xml_.append("  try {\n");
        xml_.append("   field = out;\n");
        xml_.append("  } catch (Object e){\n");
        xml_.append("   res = e;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static Object catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        Struct exc_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("$core.DefErrorClass",exc_.getClassName(cont_));
        CausingErrorStruct cause_ = (CausingErrorStruct)exc_;
        assertEq("$core.DivideZero",cause_.getCause().getClassName(cont_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" static int instance = 6;\n");
        xml_.append(" static int un = 2;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExCont.*;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  out = instance /= un;\n");
        xml_.append("  out += instance + un;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int res = out;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        assertEq(8, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int field = 6;\n");
        xml_.append(" static final ExCont instance = new ExCont();\n");
        xml_.append(" static int un = 2;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExCont.*;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  out = instance.field /= un;\n");
        xml_.append("  out += instance.field + un;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int res = out;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        assertEq(8, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int field = 2;\n");
        xml_.append(" static final ExCont instance = new ExCont();\n");
        xml_.append(" static int zero = 0;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExCont.*;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  Object bk = instance.field /= zero;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" private static Object field;\n");
        xml_.append(" private static Object res;\n");
        xml_.append(" static{\n");
        xml_.append("  try {\n");
        xml_.append("   field = out;\n");
        xml_.append("  } catch (Object e){\n");
        xml_.append("   res = e;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static Object catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        Struct exc_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("$core.DefErrorClass",exc_.getClassName(cont_));
        CausingErrorStruct cause_ = (CausingErrorStruct)exc_;
        assertEq("$core.DivideZero",cause_.getCause().getClassName(cont_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" static int instance = 2;\n");
        xml_.append(" static int zero = 0;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExCont.*;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  Object bk = instance %= zero;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" private static Object field;\n");
        xml_.append(" private static Object res;\n");
        xml_.append(" static{\n");
        xml_.append("  try {\n");
        xml_.append("   field = out;\n");
        xml_.append("  } catch (Object e){\n");
        xml_.append("   res = e;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static Object catching(){\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_,"pkg.Apply");
        MethodId id_ = getMethodId("catching");
        Struct exc_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("$core.DefErrorClass",exc_.getClassName(cont_));
        CausingErrorStruct cause_ = (CausingErrorStruct)exc_;
        assertEq("$core.DivideZero",cause_.getCause().getClassName(cont_));
    }

    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator++ $int(pkg.Ex a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a.inst+1;\n");
        xml_.append("  $return o.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static pkg.Ex $($int a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return (pkg.Ex.res++).inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(6, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator++ $int(pkg.Ex a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a.inst+1;\n");
        xml_.append("  $return o.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static pkg.Ex $($int a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return (++pkg.Ex.res).inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(7, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator++ $int(pkg.Ex a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a.inst+1;\n");
        xml_.append("  $return o.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static pkg.Ex $($int a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  pkg.Ex.res++;\n");
        xml_.append("  $return pkg.Ex.res.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(7, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator++ $int(pkg.Ex a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a.inst+1;\n");
        xml_.append("  $return o.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static pkg.Ex $($int a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ++pkg.Ex.res;\n");
        xml_.append("  $return pkg.Ex.res.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(7, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator<< pkg.Ex (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+b.a;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex one = new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  one <<= two;\n");
        xml_.append("  if (one.a != 8i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSingleton {\n");
        xml_.append(" static final ExSingleton one = new ExSingleton();\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class [pkg.ExSingleton.one;] pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  one.field.a=5i;\n");
        xml_.append("  Ex bk = one.field ++;\n");
        xml_.append("  out = one.field.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Singleton {\n");
        xml_.append(" static Ex one(){ return ExCont.instance;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" static final Ex instance = new Ex();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Singleton.one;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  Ex bk = ExCont.instance ++;\n");
        xml_.append("  out = ExCont.instance.a;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator++ pkg.Ex (pkg.Ex a){\n");
        xml_.append(" pkg.Ex out = new pkg.Ex();\n");
        xml_.append(" out.a=a.a+1;\n");
        xml_.append(" return out;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Singleton {\n");
        xml_.append(" static ExCont one(){ return ExCont.instance;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" public Ex field = new Ex();\n");
        xml_.append(" static final ExCont instance = new ExCont();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int a;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Singleton.one;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  ExCont stInfo = one();\n");
        xml_.append("  Ex bk = ++stInfo.field ;\n");
        xml_.append("  out = bk;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" static int instance = 2;\n");
        xml_.append(" static int zero = 0;\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.ExCont.*;] pkg.Initializer {\n");
        xml_.append(" public static int out;\n");
        xml_.append(" static{\n");
        xml_.append("  Ex bk = instance /= zero;\n");
        xml_.append(" }\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class [static pkg.Initializer.out;] pkg.Apply {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  return out;\n");
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
        xml_.append(" public int a;\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  Ex one = new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  one <<= two;\n");
        xml_.append("  if (one.a != 8i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_));
    }
}
