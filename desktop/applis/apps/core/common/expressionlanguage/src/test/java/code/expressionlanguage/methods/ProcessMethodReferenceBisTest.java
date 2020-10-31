package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;

public final class ProcessMethodReferenceBisTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int[],$int> f = $static().$lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return f.call($new $int[]{5i,13i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int[],$int> g = $static().$lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int[],$int> g = $new Ex().$lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }

    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<Ex,$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<Ex,$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<Ex,$int[],$int> g = $lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new Ex(),$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }

    @Test
    public void calculateArgument22_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  $Fct<$int[],$int> g = $lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return $($int) g.call($new $int[]{5i,13i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }

    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Object,$boolean> f = $lambda(ExTwo,,$instanceof);\n");
        xml_.append("  Object n = $new ExTwo();\n");
        xml_.append("  $return f.call(n)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Object,$boolean> f = $lambda(ExTwo,,$instanceof);\n");
        xml_.append("  Object n = $new ExThree();\n");
        xml_.append("  $return f.call(n)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  $Fct<Object,$boolean> f = $lambda(ExTwo<S>,,$instanceof);\n");
        xml_.append("  Object n = $new ExTwo<S>();\n");
        xml_.append("  $return f.call(n)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<U> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  $Fct<Object,$boolean> f = $lambda(ExTwo<S>,,$instanceof);\n");
        xml_.append("  Object n = $new ExThree<S>();\n");
        xml_.append("  $return f.call(n)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<U> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S,V> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int,String>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  $Fct<Object,$boolean> f = $lambda(ExTwo<S>,,$instanceof);\n");
        xml_.append("  Object n = $new ExTwo<V>();\n");
        xml_.append("  $return f.call(n)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<U> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  $Fct<Object,$boolean> f = $lambda(ExTwo<S>,,$instanceof);\n");
        xml_.append("  Object n = $new ExSub<S>();\n");
        xml_.append("  $return f.call(n)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<W>:ExTwo<W> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<U> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$boolean> f = $new ExTwo().$lambda(ExThree,,$instanceof);\n");
        xml_.append("  $return f.call()?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$boolean> f = $new ExThree().$lambda(ExTwo,,$instanceof);\n");
        xml_.append("  $return f.call()?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int... p){\n");
        xml_.append("  $for ($int i: p){\n");
        xml_.append("   field += i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex> c = $lambda(Ex,$new,$int...);\n");
        xml_.append("  Ex instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $new Ex().$lambda(Inner,$new,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int q,$int... p){\n");
        xml_.append("  field += q;\n");
        xml_.append("  $for ($int i: p){\n");
        xml_.append("   field += i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int[],Ex> c = $lambda(Ex,$new,$int,$int...);\n");
        xml_.append("  Ex instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(25, getNumber(ret_));
    }
    @Test
    public void calculateArgument99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int q,$int... p){\n");
        xml_.append("   field += q;\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int[],Ex.Inner> c = $new Ex().$lambda(Inner,$new,$int,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(25, getNumber(ret_));
    }
    @Test
    public void calculateArgument100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int... p){\n");
        xml_.append("  $for ($int i: p){\n");
        xml_.append("   field += i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex> c = $lambda(Ex,$new,$id,$int...);\n");
        xml_.append("  Ex instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $new Ex().$lambda(Ex.Inner,$new,$id,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int q,$int... p){\n");
        xml_.append("  field += q;\n");
        xml_.append("  $for ($int i: p){\n");
        xml_.append("   field += i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int[],Ex> c = $lambda(Ex,$new,$id,$int,$int...);\n");
        xml_.append("  Ex instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(25, getNumber(ret_));
    }
    @Test
    public void calculateArgument103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int q,$int... p){\n");
        xml_.append("   field += q;\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int[],Ex.Inner> c = $new Ex().$lambda(Ex.Inner,$new,$id,$int,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(25, getNumber(ret_));
    }
    @Test
    public void calculateArgument1031Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class $interfaces(pkg.Ex.IntOne,pkg.Ex.IntTwo) Inner:IntOne:IntTwo {\n");
        xml_.append("  $public $int field = IntOne.b+IntTwo.b;\n");
        xml_.append("  $public Inner($int q,$int... p){\n");
        xml_.append("   field += q;\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $interface IntOne {\n");
        xml_.append("  $public $static $int b;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $interface IntTwo {\n");
        xml_.append("  $public $static $int b;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int[],Ex.Inner> c = $new Ex().$lambda(Ex.Inner,$new,$id,$int,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(25, getNumber(ret_));
    }
    @Test
    public void calculateArgument1032Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {\n");
        xml_.append(" $public $class $interfaces(pkg.Annot.IntOne,pkg.Annot.IntTwo) Inner:IntOne:IntTwo {\n");
        xml_.append("  $public $int field = IntOne.b+IntTwo.b;\n");
        xml_.append("  $public Inner($int q,$int... p){\n");
        xml_.append("   field += q;\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $interface IntOne {\n");
        xml_.append("  $public $static $int b;\n");
        xml_.append(" }\n");
        xml_.append(" $public $interface IntTwo {\n");
        xml_.append("  $public $static $int b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int q,$int... p){\n");
        xml_.append("   field += q;\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int[],Ex.Inner> c = $new Ex().$lambda(Ex.Inner,$new,$id,$int,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get()+$new pkg.Annot.Inner(6).get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(31, getNumber(ret_));
    }
    @Test
    public void calculateArgument104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:Number> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  $for (T i: p){\n");
        xml_.append("   field += i.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer[],Ex<Integer>> c = $lambda(Ex<Integer>,$new,$id,T...);\n");
        xml_.append("  Ex<Integer> instance = c.call($new Integer[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:Number> {\n");
        xml_.append(" $public $class Inner<U:Number> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner(T q,U... p){\n");
        xml_.append("   field += q.intValue();\n");
        xml_.append("   $for (U i: p){\n");
        xml_.append("    field += i.intValue();\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer[],Ex<Integer>.Inner<Integer>> c = $new Ex<Integer>().$lambda(Ex<Integer>.Inner<Integer>,$new,$id,T,U...);\n");
        xml_.append("  Ex<Integer>.Inner<Integer> instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(25, getNumber(ret_));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Fct<$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int[],$int> g = $static().$lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{8i,$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadArgNumber(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Fct<$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int[],$int> g = $static().$lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{8i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasCastType(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $new Ex().$lambda(Ex.Inner,$new,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner<T,U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner<?,!Number>> c = $new Ex().$lambda(Inner<?,!Number>,$new,$int...);\n");
        xml_.append("  Ex.Inner<?,!Number> instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument12FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $abstract $class Inner<T,U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner<?,!Number>> c = $new Ex().$lambda(Inner<?,!Number>,$new,$int...);\n");
        xml_.append("  Ex.Inner<?,!Number> instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument13FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex<?,!Number>.Inner> c = $new Ex<?,!Number>().$lambda(Inner,$new,$int...);\n");
        xml_.append("  Ex<?,!Number>.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument14FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $abstract $class Inner<T,U:CharSequence> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner<?,Number>> c = $new Ex().$lambda(Inner<?,Number>,$new,$int...);\n");
        xml_.append("  Ex.Inner<?,Number> instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument15FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $new Ex[1].$lambda(Inner,$new,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument16FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $new Ex[1].$lambda(Inner,$new,$int...);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument17FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<S> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex<?>.Inner<!Number>> c = $lambda(Ex<?>.Inner<!Number>,$new,$int...);\n");
        xml_.append("  Ex<?>.Inner<!Number> instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument18FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $lambda(Ex.Inner,$new,String...);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument19FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $abstract $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $lambda(Ex.Inner,$new,String...);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument20FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $abstract $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex.Inner> c = $lambda(Ex.Inner,$new,String...,$int);\n");
        xml_.append("  Ex.Inner instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument21FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<S> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner($int... p){\n");
        xml_.append("   $for ($int i: p){\n");
        xml_.append("    field += i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex<?>.Inner<!Number>> c = $new Ex<Number>().$lambda(Ex<?>.Inner<!Number>,$new,$id,$int...);\n");
        xml_.append("  Ex<?>.Inner<!Number> instance = c.call($new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument22FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:Number> {\n");
        xml_.append(" $public $class Inner<U:Number> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner(T q,U... p){\n");
        xml_.append("   field += q.intValue();\n");
        xml_.append("   $for (U i: p){\n");
        xml_.append("    field += i.intValue();\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer[],Ex<Integer>.Inner<?>> c = $new Ex<Integer>().$lambda(Ex<Integer>.Inner<?>,$new,$id,T,U...);\n");
        xml_.append("  Ex<Integer>.Inner<?> instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument23FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:Number> {\n");
        xml_.append(" $public $class Inner<U:Number> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public Inner(T q,U... p){\n");
        xml_.append("   field += q.intValue();\n");
        xml_.append("   $for (U i: p){\n");
        xml_.append("    field += i.intValue();\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer[],Ex<Integer>.Inner<Integer>> c = $new Ex<Integer>().$lambda(Ex<Integer>.Inner<Integer>,$new,$id,T...,U);\n");
        xml_.append("  Ex<Integer>.Inner<?> instance = c.call(12,$new $int[]{5,8});\n");
        xml_.append("  \"\".$lambda(Number,,$parent);\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
