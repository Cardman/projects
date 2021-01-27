package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class ProcessMethodDiamondTest extends ProcessMethodCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(ExParam<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
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
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  ExParam<int> res = staticCall(ExParam<>).inst();\n");
        xml_.append("  return res.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(){\n");
        xml_.append("  return new((T)2);\n");
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
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(ExParam<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(String p){\n");
        xml_.append("  return new((T)p);\n");
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
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  ExParam<int> res = staticCall(ExParam<>).inst(2);\n");
        xml_.append("  return res.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(int p){\n");
        xml_.append("  return new((T)p);\n");
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
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(ExParam<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T>:ExSuper<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuper<S> {\n");
        xml_.append(" public staticCall ExParam<S> inst(S p){\n");
        xml_.append("  return new(p);\n");
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
        xml_.append("  return exmeth(new From<>(2));\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(To<int> t){\n");
        xml_.append("  return t.f;\n");
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
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return exmeth(new From<>(2));\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(To<int> t){\n");
        xml_.append("  return t.f;\n");
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
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;staticCall;static pkg.Inexist.i;static pkg.Inexist.*;staticCall pkg.Inexist.inst;staticCall pkg.Inexist.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
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
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst2(T p){\n");
        xml_.append("  return new(p);\n");
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
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
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
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(String p){\n");
        xml_.append("  return new((T)p);\n");
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
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;staticCall;static pkg.Inexist.cust;static pkg.Fields.cust;staticCall pkg.Inexist.inst;staticCall pkg.Inexist.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst(2).f+cust;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Fields {\n");
        xml_.append(" public static final int cust = 0;\n");
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
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;staticCall;static pkg.Inexist.i;static pkg.Inexist.*;staticCall pkg.Inexist.inst;staticCall pkg.Inexist.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst($id(ExParam,T),2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
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
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;staticCall;static pkg.Inexist.i;static pkg.Inexist.*;staticCall pkg.Inexist.inst;staticCall pkg.Inexist.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExParam<int> inst(int p){\n");
        xml_.append("  return new(p);\n");
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
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;staticCall;static pkg.Inexist.i;static pkg.Inexist.*;staticCall pkg.Inexist.inst;staticCall pkg.Inexist.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst($id(ExParam,static,int),2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExParam<int> inst(int p){\n");
        xml_.append("  return new(p);\n");
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
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;staticCall;static pkg.Inexist.i;static pkg.Inexist.*;staticCall pkg.Inexist.inst;staticCall pkg.Inexist.*;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(<>).inst($id(ExParam,T),2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(int p){\n");
        xml_.append("  return new((T)p);\n");
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
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return exmeth(new From<>(2));\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(To<int> t){\n");
        xml_.append("  return t.f;\n");
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
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<U> {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return exmeth(new From<>(2));\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(To<int> t){\n");
        xml_.append("  return t.f;\n");
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
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return staticCall(ExParam<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExParam<int> inst(int p){\n");
        xml_.append("  return new(p);\n");
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


}
