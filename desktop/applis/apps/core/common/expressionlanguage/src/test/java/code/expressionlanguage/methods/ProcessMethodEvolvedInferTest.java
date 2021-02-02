package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodEvolvedInferTest extends ProcessMethodCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  To<int> v = new From<>(2);\n");
        xml_.append("  return v.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.From<S> {\n");
        xml_.append(" public S f;\n");
        xml_.append(" public From(S p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.To<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public To(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static To<T> $(From<T> p){\n");
        xml_.append("  return new(p.f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  To<int> v = new From<>(2);\n");
        xml_.append("  return v.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.From<S> {\n");
        xml_.append(" public S f;\n");
        xml_.append(" public From(S p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static To<S> $(From<S> p){\n");
        xml_.append("  return new(p.f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.To<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public To(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  To<int> v = new From<>(2);\n");
        xml_.append("  return v.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.From<S>:FromSup<S> {\n");
        xml_.append(" public From(S p){\n");
        xml_.append("  super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.FromSup<S2> {\n");
        xml_.append(" public S2 f;\n");
        xml_.append(" public FromSup(S2 p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.To<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public To(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static To<T> $(FromSup<T> p){\n");
        xml_.append("  return new(p.f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  To<int> v = new From<>(2);\n");
        xml_.append("  return v.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.From<S>:FromSup<S> {\n");
        xml_.append(" public From(S p){\n");
        xml_.append("  super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.FromSup<S2> {\n");
        xml_.append(" public S2 f;\n");
        xml_.append(" public FromSup(S2 p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static To<S2> $(FromSup<S2> p){\n");
        xml_.append("  return new(p.f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.To<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public To(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  To<int> v = new From<>(2);\n");
        xml_.append("  return v.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.From<S>:FromSup<S> {\n");
        xml_.append(" public From(S p){\n");
        xml_.append("  super(p);\n");
        xml_.append(" }\n");
        xml_.append(" public static ToSub<S> $(From<S> p){\n");
        xml_.append("  return new(p.f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.FromSup<S2> {\n");
        xml_.append(" public S2 f;\n");
        xml_.append(" public FromSup(S2 p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.To<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public To(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ToSub<T2>:To<T2> {\n");
        xml_.append(" public ToSub(T2 p){\n");
        xml_.append("  super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  FctIntBool<int> v = p -> p % 2 == 0;\n");
        xml_.append("  return v.app(2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  FctIntBool<int> v = p -> p % 2 == 0;\n");
        xml_.append("  return v.app(2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,S,boolean> p){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  FctIntBool<int> v = p -> p % 2 == 0;\n");
        xml_.append("  return v.app(2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public static FctIntBool<S> $(int p){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  FctIntBool<int> v = staticCall(Ex<>).$lambda(pair);\n");
        xml_.append("  return v.app(2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean pair(int p){\n");
        xml_.append("  return p % 2 == 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  FctIntBool<int> v = staticCall(Ex<>).$lambda(pair);\n");
        xml_.append("  return v.app(2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean pair(int p){\n");
        xml_.append("  return p % 2 == 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public static FctIntBool<S> $(int p){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return app(p -> p % 2 == 0,2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return app(staticCall(Ex<>).$lambda(pair),2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean pair(int p){\n");
        xml_.append("  return p % 2 == 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  FctIntBool<int> v = staticCall(Ex<>).$lambda(pair);\n");
        xml_.append("  return v.app(2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean pair(int p){\n");
        xml_.append("  return p % 2 == 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,S,boolean> p){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return app(p -> p % 2 == 0,2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctTwoIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g,g);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctTwoIntBool<S2> {\n");
        xml_.append(" public static FctTwoIntBool<S2> $(Fct<S2,S2,boolean> p){\n");
        xml_.append("  return (FctTwoIntBool<S2>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S2 o,S2 p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return app(staticCall(Ex<>).$lambda(pair),2)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctTwoIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g,g);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean pair(int p){\n");
        xml_.append("  return p % 2 == 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctTwoIntBool<S2> {\n");
        xml_.append(" public static FctTwoIntBool<S2> $(Fct<S2,S2,boolean> p){\n");
        xml_.append("  return (FctTwoIntBool<S2>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S2 o,S2 p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return app((p,q) -> p > q,2,1)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctTwoIntBool<int,int> f, int g, int h){\n");
        xml_.append("  return f.app(g,h);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctTwoIntBool<S2,S3> {\n");
        xml_.append(" public static FctTwoIntBool<S2,S3> $(Fct<S2,S3,boolean> p){\n");
        xml_.append("  return (FctTwoIntBool<S2,S3>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S2 o,S3 p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return app(staticCall(Ex<>).$lambda(gt),2,1)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctIntBool<int> f, int g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean app(FctTwoIntBool<int,int> f, int g, int h){\n");
        xml_.append("  return f.app(g,h);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean gt(int p,int q){\n");
        xml_.append("  return p > q;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctTwoIntBool<S2,S3> {\n");
        xml_.append(" public static FctTwoIntBool<S2,S3> $(Fct<S2,S3,boolean> p){\n");
        xml_.append("  return (FctTwoIntBool<S2,S3>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S2 o,S3 p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Ex<>).app((int p,int q:boolean) -> p > q,2,1)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall boolean app(FctIntBool<U> f, U g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall boolean app(FctTwoIntBool<U,U> f, U g, U h){\n");
        xml_.append("  return f.app(g,h);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctTwoIntBool<S2,S3> {\n");
        xml_.append(" public static FctTwoIntBool<S2,S3> $(Fct<S2,S3,boolean> p){\n");
        xml_.append("  return (FctTwoIntBool<S2,S3>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S2 o,S3 p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).app((p,q) -> p > q,2,1)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall boolean app(FctIntBool<U> f, U g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall boolean app(FctTwoIntBool<U,U> f, U g, U h){\n");
        xml_.append("  return f.app(g,h);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctTwoIntBool<S2,S3> {\n");
        xml_.append(" public static FctTwoIntBool<S2,S3> $(Fct<S2,S3,boolean> p){\n");
        xml_.append("  return (FctTwoIntBool<S2,S3>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S2 o,S3 p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(Ex<int>).app(staticCall(Ex<>).$lambda(gt),2,1)?0:1;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall boolean app(FctIntBool<U> f, U g){\n");
        xml_.append("  return f.app(g);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall boolean app(FctTwoIntBool<U,U> f, U g, U h){\n");
        xml_.append("  return f.app(g,h);\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean gt(int p,int q){\n");
        xml_.append("  return p > q;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctIntBool<S> {\n");
        xml_.append(" public static FctIntBool<S> $(Fct<S,boolean> p){\n");
        xml_.append("  return (FctIntBool<S>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S p);\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.FctTwoIntBool<S2,S3> {\n");
        xml_.append(" public static FctTwoIntBool<S2,S3> $(Fct<S2,S3,boolean> p){\n");
        xml_.append("  return (FctTwoIntBool<S2,S3>)p;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean app(S2 o,S3 p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

}
