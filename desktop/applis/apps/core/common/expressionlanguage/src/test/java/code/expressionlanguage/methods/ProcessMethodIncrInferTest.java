package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodIncrInferTest extends ProcessMethodCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(2);\n");
        xml_.append("  return res.f;\n");
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
        xml_.append("  var res = staticCall(ExParam<>).inst(2,x->x%2==0);\n");
        xml_.append("  return res?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public staticCall boolean inst(T p, Fct<T,boolean> f){\n");
        xml_.append("  return f.call(p);\n");
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
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(2);\n");
        xml_.append("  return res.f;\n");
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
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(2,x->x%2==0);\n");
        xml_.append("  return res?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public staticCall boolean inst(T p, Fct<T,boolean> f){\n");
        xml_.append("  return f.call(p);\n");
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
        xml_.append("  ExParam<ExParam<int>> res = new ExParam<>(staticCall(ExParam<>).inst(2));\n");
        xml_.append("  return res.f.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(){\n");
        xml_.append(" }\n");
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
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  ExParam<ExParam<int>> res = staticCall(ExParam<>).inst(staticCall(ExParam<>).inst(2));\n");
        xml_.append("  return res.f.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(){\n");
        xml_.append(" }\n");
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
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(p:2);\n");
        xml_.append("  return res.f;\n");
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
        xml_.append(" public staticCall ExParam<T> inst(T o, T q){\n");
        xml_.append("  return new(o);\n");
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
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(p:2);\n");
        xml_.append("  return res.f;\n");
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
        xml_.append(" public staticCall ExParam<T> inst(T o, T q){\n");
        xml_.append("  return new(o);\n");
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
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(2);\n");
        xml_.append("  return res.f;\n");
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
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(p:2);\n");
        xml_.append("  return res.f;\n");
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
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(2);\n");
        xml_.append("  return res.f;\n");
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
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(p:2);\n");
        xml_.append("  return res.f;\n");
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
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(2,4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(2,q:4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(p:2,q:4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(q:2,p:4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(p:2,q:4);\n");
        xml_.append("  return res.f-res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }

    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(ExParam<>).inst(q:2,p:4);\n");
        xml_.append("  return res.f-res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
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
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(2,4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(2,q:4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(p:2,q:4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(q:2,p:4);\n");
        xml_.append("  return res.f+res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(p:2,q:4);\n");
        xml_.append("  return res.f-res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }

    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var res = staticCall(<>).inst(q:2,p:4);\n");
        xml_.append("  return res.f-res.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T,S> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public S g;\n");
        xml_.append(" public ExParam(T p, S q){\n");
        xml_.append("  f = p;\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T,S> inst(T p, S q){\n");
        xml_.append("  return new(p,q);\n");
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
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  ExParam<int> res = staticCall(ExParam<>).inst();\n");
        xml_.append("  return res.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f = (T)2;\n");
        xml_.append(" public staticCall ExParam<T> inst(){\n");
        xml_.append("  return new();\n");
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
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  ExParam<int> res = staticCall(ExParamTwo<>).inst(2,x->3*x);\n");
        xml_.append("  return res.f;\n");
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
        xml_.append("public class pkg.ExParamTwo<T,S> {\n");
        xml_.append(" public staticCall ExParam<S> inst(T p,Fct<T,S> f){\n");
        xml_.append("  return new(f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
}
