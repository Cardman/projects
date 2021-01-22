package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodUnamedCtorTest extends ProcessMethodCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new(\"str\"));\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder p){\n");
        xml_.append("  return p.toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("str", getString(ret_));
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new(\"str\"));\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder... p){\n");
        xml_.append("  return p[0].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("str", getString(ret_));
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public String exmeth(){\n");
        xml_.append("  return \"\"+f+\";\"+id(new(\"1\"));\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExInner<String> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2;2,1", getString(ret_));
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public String exmeth(){\n");
        xml_.append("  return \"\"+f+\";\"+id(new((T)1));\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExInner<T> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2;2,1", getString(ret_));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).exmeth(1);\n");
        xml_.append(" }\n");
        xml_.append(" public String exmeth(T p){\n");
        xml_.append("  return \"\"+f+\";\"+id(new(p));\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExInner<T> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2;2,1", getString(ret_));
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  var v = new Ex<String>(\"2\");\n");
        xml_.append("  return \"\"+v.id(v.new(\"1\"));\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExInner<String> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2,1", getString(ret_));
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  var v = new Ex<int>(2);\n");
        xml_.append("  return \"\"+v.id(v.new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExInner<T> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2,1", getString(ret_));
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(new(1){$id(int i){super(i);}});\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new StringBuilder[]{new(\"str\")});\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder... p){\n");
        xml_.append("  return p[0].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("str", getString(ret_));
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(ExAbs<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public abstract class pkg.ExAbs<S> {\n");
        xml_.append(" public S f;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T>:ExAbs<T> {\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(ExAbs<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public abstract class pkg.ExAbs<S> {\n");
        xml_.append(" public S f;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public static class ExStInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExStInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return \"\"+id(new(\"1\"));\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(Ex<int>.ExInner<String> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(ExStInner<String> p){\n");
        xml_.append("  return \"\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExOther<U> {\n");
        xml_.append(" public U f;\n");
        xml_.append(" public ExOther(U p){f = p;}\n");
        xml_.append(" public class ExOtherInner<V> {\n");
        xml_.append("  public V g;\n");
        xml_.append("  public ExOtherInner(V p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public String exmeth(){\n");
        xml_.append("  return \"\"+f+\";\"+id(new(\"1\"));\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExInner<String> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExOther<int>.ExOtherInner<String> p){\n");
        xml_.append("  return \"\"+p.f+\",\"+p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2;2,1", getString(ret_));
    }
    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(new(1){$id(int i){super(i);}});\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(ExFinal<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public final class pkg.ExFinal<S> {\n");
        xml_.append(" public S f;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).id(new(1){$id(int i){super(i);}});\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public String id(ExInner<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).id(new(1){$id(int i){super(i);}});\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public String id(String p){\n");
        xml_.append("  return \"\"+p;\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Number p){\n");
        xml_.append("  return \"\"+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public String exmeth(){\n");
        xml_.append("  return id(new(1){$id(int i){super(i);}});\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Ex<int> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public String exmeth(){\n");
        xml_.append("  return id(new(1){$id(int i){super(i);}});\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Ex<int> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Enum<EnEx> p){\n");
        xml_.append("  return \"\"+p.name();\n");
        xml_.append(" }\n");
        xml_.append(" public enum EnEx {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class ExInner<S> {\n");
        xml_.append("  public S g;\n");
        xml_.append("  public ExInner(S p){g = p;}\n");
        xml_.append(" }\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex<int>(2).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" public String exmeth(){\n");
        xml_.append("  return id(new(1){$id(int i){super(i);}});\n");
        xml_.append(" }\n");
        xml_.append(" public String id(Ex<int> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public String id($en p){\n");
        xml_.append("  return \"\"+p.name();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<?> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(int p){\n");
        xml_.append("  return \"\"+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new(\"str\"));\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder p){\n");
        xml_.append("  return p.toString();\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder[] p){\n");
        xml_.append("  return p[0].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("str", getString(ret_));
    }
    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new(\"str\"));\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder... p){\n");
        xml_.append("  return p[0].toString();\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder[]... p){\n");
        xml_.append("  return p[0][0].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("str", getString(ret_));
    }
    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new StringBuilder[]{new(\"str\")});\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder... p){\n");
        xml_.append("  return p[0].toString();\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder[]... p){\n");
        xml_.append("  return p[0][0].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("str", getString(ret_));
    }
    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new(\"str\"));\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder p){\n");
        xml_.append("  return p.toString();\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(int p, int q){\n");
        xml_.append("  return \"\"+p+q;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("str", getString(ret_));
    }
    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(1,2,new(\"str\"));\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(int n, int o,StringBuilder p){\n");
        xml_.append("  return \"\"+n+o+p;\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(int p, int q){\n");
        xml_.append("  return \"\"+p+q;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("12str", getString(ret_));
    }
    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).id(p:new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(ExOther<T> q){\n");
        xml_.append("  return \"\"+q.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOther<U> {\n");
        xml_.append(" public U f;\n");
        xml_.append(" public ExOther(U p){f = p;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<Ex<int>,String> fct = $lambda(Ex<int>,id,$id,staticCall,Ex<T>);\n");
        xml_.append("  return fct.call(new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<Ex<int>,String> fct = $lambda(Ex<int>,id,$id,staticCall,Ex<T>);\n");
        xml_.append("  return fct.call(a:{new(1)});\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public Ex(int p){f = (T)p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<ExOther<int>>).id(p:new(new(1)));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+((ExOther<?>)p.f).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOther<U> {\n");
        xml_.append(" public U f;\n");
        xml_.append(" public ExOther(U p){f = p;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public Ex(StringBuilder q){f = (T)q;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return staticCall(Ex<ExOther<int>>).id(p:new(p:new(1)));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+((ExOther<?>)p.f).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOther<U> {\n");
        xml_.append(" public U f;\n");
        xml_.append(" public ExOther(U p){f = p;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test33() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new[1]);\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder[] p){\n");
        xml_.append("  return \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test34() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new[1]);\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder[] p){\n");
        xml_.append("  return \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder p){\n");
        xml_.append("  return \"\"+p.toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(new[1]);\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder... p){\n");
        xml_.append("  return \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return id(p:new[1]);\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder[] p){\n");
        xml_.append("  return \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append(" public static String id(StringBuilder q){\n");
        xml_.append("  return \"\"+q.toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test37() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public String f;\n");
        xml_.append(" public Ex(StringBuilder[] p){\n");
        xml_.append("  f = \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex(new[1]).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test38() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public String f;\n");
        xml_.append(" public Ex(StringBuilder[] p){\n");
        xml_.append("  f = \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(StringBuilder q){\n");
        xml_.append("  f = \"\"+q.toString();\n");
        xml_.append(" }\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  return new Ex(p:new[1]).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<Ex<int>[],String> fct = $lambda(Ex<int>,id,$id,staticCall,Ex<T>[]);\n");
        xml_.append("  return fct.call(new[1]);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T>[] p){\n");
        xml_.append("  return \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<Ex<int>[],String> fct = $lambda(Ex<int>,id,$id,staticCall,Ex<T>[]);\n");
        xml_.append("  return fct.call(a:{new[1]});\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T>[] p){\n");
        xml_.append("  return \"\"+p.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Fct<Ex<int>,String> fct = staticCall(Ex<int>).$lambda(Ex<int>,id,$id,,Ex<T>);\n");
        xml_.append("  return fct.call(new(1));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void testFail() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex(T p){f = p;}\n");
        xml_.append(" public Ex(StringBuilder q){f = (T)q;}\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  id(new(\"str\"));\n");
        xml_.append("  id(new[1]);\n");
        xml_.append("  id(a:new(\"str\"));\n");
        xml_.append("  id(a:new[1]);\n");
        xml_.append("  new ExOther<int>(a:new(\"str\"));\n");
        xml_.append("  new ExOther<int>(a:new[1]);\n");
        xml_.append("  new ExOtherThree<int>(new(\"str\"),new(\"str\"));\n");
        xml_.append("  new ExOtherThree<int>(new[1],new(\"str\"));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOther<U> {\n");
        xml_.append(" public U f;\n");
        xml_.append(" public ExOther(U p){f = p;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOtherThree<U> {\n");
        xml_.append(" public U f;\n");
        xml_.append(" public ExOther(int p, U q){f = (U)p;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExArr {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex1<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ex1(T p){f = p;}\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  staticCall(Ex1<?>).$lambda(Ex1<?>,id,$id,,Ex1<T>);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall String id(Ex1<T> p){\n");
        xml_.append("  return \"\"+p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly("en",files_));
    }
}
