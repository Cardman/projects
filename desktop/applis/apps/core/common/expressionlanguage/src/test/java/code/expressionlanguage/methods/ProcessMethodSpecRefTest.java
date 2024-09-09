package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodSpecRefTest extends ProcessMethodCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<Ex,long> r = $lambda(Ex,,null);\n");
        xml_.append("  Fct<Ex,String> s = $lambda(Ex,,this);\n");
        xml_.append("  r.call(new Ex());\n");
        xml_.append("  return s.call(new Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("pkg.Ex", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" String field = \"info\";\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<Ex,String> s = $lambda(Ex,,this);\n");
        xml_.append("  return s.call(new Ex());\n");
        xml_.append(" }\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("info", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" String field = \"info\";\n");
        xml_.append(" public static long exmeth(){\n");
        xml_.append("  Fct<Ex,long> r = $lambda(Ex,,null);\n");
        xml_.append("  return r.call(new Ex());\n");
        xml_.append(" }\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public long null(){\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<long> r = new Ex().$lambda(Ex,,null);\n");
        xml_.append("  Fct<String> s = new Ex().$lambda(Ex,,this);\n");
        xml_.append("  r.call();\n");
        xml_.append("  return s.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("pkg.Ex", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" String field = \"info\";\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<String> s = new Ex().$lambda(Ex,,this);\n");
        xml_.append("  return s.call();\n");
        xml_.append(" }\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("info", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" String field = \"info\";\n");
        xml_.append(" public static long exmeth(){\n");
        xml_.append("  Fct<long> r = new Ex().$lambda(Ex,,null);\n");
        xml_.append("  return r.call();\n");
        xml_.append(" }\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public long null(){\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
}
