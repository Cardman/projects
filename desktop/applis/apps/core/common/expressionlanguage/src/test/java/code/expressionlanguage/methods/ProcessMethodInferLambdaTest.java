package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodInferLambdaTest extends ProcessMethodCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(Ex e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $return 14;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f){\n");
        xml_.append("  $return f.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int> f = $new Ex(14).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex> c = $staticCall().$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f = 14;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex> c = $staticCall().$lambda($new);\n");
        xml_.append("  $return c.call().exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f = 14;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda($new));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex> f){\n");
        xml_.append("  $return f.call().f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<$int,Ex> f,$int e){\n");
        xml_.append("  $return f.call(e).f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int g;\n");
        xml_.append("  $public Inner($int p){\n");
        xml_.append("   g = p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $int exmethtwo(){\n");
        xml_.append("   $return f*g;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int,Inner> c = $staticCall(Inner).$lambda($new);\n");
        xml_.append("  $return c.call($new Ex(5),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(70, getNumber(ret_));
    }

    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int g;\n");
        xml_.append("  $public Inner($int p){\n");
        xml_.append("   g = p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $int exmethtwo(){\n");
        xml_.append("   $return f*g;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<ExSub,$int,Inner> c = $staticCall(Inner).$lambda($new);\n");
        xml_.append("  $return c.call($new ExSub(5),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(70, getNumber(ret_));
    }

    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int g = 14;\n");
        xml_.append("  $public $int exmethtwo(){\n");
        xml_.append("   $return f*g;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,Inner> c = $staticCall(Inner).$lambda($new);\n");
        xml_.append("  $return c.call($new Ex(5)).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(70, getNumber(ret_));
    }

    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int g = 14;\n");
        xml_.append("  $public $int exmethtwo(){\n");
        xml_.append("   $return f*g;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<ExSub,Inner> c = $staticCall(Inner).$lambda($new);\n");
        xml_.append("  $return c.call($new ExSub(5)).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(70, getNumber(ret_));
    }

    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ref().call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $Fct<$int,$int> ref(){\n");
        xml_.append("  $return $staticCall().$lambda(exmethtwo);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String pr($Fct<Ex,String> f){\n");
        xml_.append("  $return f.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int pr($Fct<Ex,~$int> f){\n");
        xml_.append("  $return $that(f.call($null));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int pr($Fct<Ex,~$int> f,Ex e){\n");
        xml_.append("  $return $that(f.call(e));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f){\n");
        xml_.append("  $return f.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmethtwo(){\n");
        xml_.append("  $return $that(f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $byte pr($Fct<Ex,$byte> f){\n");
        xml_.append("  $return f.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda($new),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> f, $int e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Ex pr($Fct<$int,~Ex> f, $int e){\n");
        xml_.append("  $return $that(f.call(e));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda($new),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,?> f, $int e){\n");
        xml_.append("  $return (Ex)f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr(f:$staticCall().$lambda(exmethtwo),e:$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> g){\n");
        xml_.append("  $return g.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($Fct<$int,$int>)$staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return 14;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(Object p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+($int)t+($int)p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda($new),14).f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> f,$int e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> f){\n");
        xml_.append("  $return f.call(-1);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr(f:$staticCall().$lambda($new),e:14).f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> f,$int e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> f){\n");
        xml_.append("  $return f.call(-1);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSup {\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<ExSup,$int> f = $staticCall(ExSup).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExSup($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $byte exmethtwo($int p){\n");
        xml_.append("  $return ($byte)f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSup {\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExSup($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSup {\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<ExSup,$int> f = $staticCall(ExSup).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExSup($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSup {\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> f = $staticCall(ExSup).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExSup($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new ExUtil($staticCall().$lambda(exmethtwo),$new Ex(14)).pr();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExUtil {\n");
        xml_.append(" $public $Fct<Ex,$int> f;\n");
        xml_.append(" $public Ex e;\n");
        xml_.append(" $public ExUtil($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append("  $this.e=e;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExUtil($Fct<Ex,$int> f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int pr(){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new ExUtil(f:$staticCall().$lambda(exmethtwo),e:$new Ex(14)).pr();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExUtil {\n");
        xml_.append(" $public $Fct<Ex,$int> f;\n");
        xml_.append(" $public Ex e;\n");
        xml_.append(" $public ExUtil($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append("  $this.e=e;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExUtil($Fct<Ex,$int> g){\n");
        xml_.append("  $this.f=g;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int pr(){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test33() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new $int[]{5,9});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $return p[0]+p[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test34() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<~Ex,$int> f,$that Ex e){\n");
        xml_.append("  $return f.call($that(e));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f){\n");
        xml_.append("  $return f.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(Ex e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn(T p){\n");
        xml_.append("  $Fct<Ex<T>,T> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public T exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn(T p){\n");
        xml_.append("  $Fct<Ex<T>,T> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test37() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn(T p){\n");
        xml_.append("  $Fct<Ex<T>,T,T> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(p),p);\n");
        xml_.append(" }\n");
        xml_.append(" $public T exmethtwo(T f){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test38() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<$int>,$int> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public T exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExSup<T> {\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<$int>,$int> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public ExSup(S p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public S exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExSup<T> {\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<$int>,$int> f = $staticCall(ExSup<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public ExSup(S p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public S exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,ExCont> c = $staticCall(ExCont).$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExCont($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $private ExCont(String p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test42() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,ExCont> c = $staticCall(ExCont).$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExCont($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExCont(String p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test43() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<String,StringBuilder> c = $staticCall(StringBuilder).$lambda($new);\n");
        xml_.append("  $return c.call(\"14\").length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test44() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Fct<String,StringBuilder>,$int> f = $static().$lambda(Ex,get,Object);\n");
        xml_.append("  $return f.call(a:{$staticCall(StringBuilder).$lambda($new)});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Object args){\n");
        xml_.append("  $return args == $null ? 0 : 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void test45() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f){\n");
        xml_.append("  $return f.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($int f){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test46() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex s;\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $new Ex(14);\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$that(v));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<~Ex,$int> f,$that Ex e){\n");
        xml_.append("  $return f.call($that(e));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<~Ex,$int> f){\n");
        xml_.append("  $return f.call($that(s));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($int f){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($that Ex e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($that $int e){\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test47() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExSup<T> {\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<$int>,$int> f = $staticCall(ExSup<$int>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public ExSup(S p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public S exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test48() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall(ExSub).$lambda($new),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> f, $int e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,ExSub> f, $int e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Ex pr($Fct<$int,~Ex> f, $int e){\n");
        xml_.append("  $return $that(f.call(e));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test49() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall(Ex).$lambda($new),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> f, $int e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,ExSub> f, $int e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Ex pr($Fct<$int,~Ex> f, $int e){\n");
        xml_.append("  $return $that(f.call(e));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test50() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub2 {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int g;\n");
        xml_.append("  $public Inner($int p){\n");
        xml_.append("   g = p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $int exmethtwo(){\n");
        xml_.append("   $return g;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int g;\n");
        xml_.append("  $public Inner($int p){\n");
        xml_.append("   g = p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $int exmethtwo(){\n");
        xml_.append("   $return f*g;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall(ExSub.Inner).$lambda($new));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<ExSub,$int,Inner> c){\n");
        xml_.append("  $return c.call($new ExSub(5),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<ExSub2,$int,Inner> c){\n");
        xml_.append("  $return c.call($new ExSub2(),14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(70, getNumber(ret_));
    }

    @Test
    public void test51() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new ExUtil($new Ex(14),$staticCall().$lambda(exmethtwo)).pr();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExUtil {\n");
        xml_.append(" $public $Fct<Ex,$int> f;\n");
        xml_.append(" $public Ex e;\n");
        xml_.append(" $public ExUtil(Ex e,$Fct<Ex,$int> f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append("  $this.e=e;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExUtil($Fct<Ex,$int> f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int pr(){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test52() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new ExUtil($new Ex(14),$staticCall().$lambda(exmethtwo)).pr();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExUtil {\n");
        xml_.append(" $public $Fct<Ex,$int> f;\n");
        xml_.append(" $public Ex e;\n");
        xml_.append(" $public ExUtil(Ex e,$Fct<Ex,$int> f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append("  $this.e=e;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExUtil(Ex e,$int f){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int pr(){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test53() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall().$lambda(exmethtwo),$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($int f,Ex e){\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test54() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new ExUtil(e:$new Ex(14),f:$staticCall().$lambda(exmethtwo)).pr();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExUtil {\n");
        xml_.append(" $public $Fct<Ex,$int> f;\n");
        xml_.append(" $public Ex e;\n");
        xml_.append(" $public ExUtil(Ex e,$Fct<Ex,$int> f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append("  $this.e=e;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExUtil(Ex e,$int f){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int pr(){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test55() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr(f:$staticCall().$lambda(exmethtwo),e:$new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex,$int> f,Ex e){\n");
        xml_.append("  $return f.call(e);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($int f,Ex e){\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test56() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int g;\n");
        xml_.append(" $public Ex($int q){\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int inn(T q){\n");
        xml_.append("  $Fct<Ex<?>,$int> f = $staticCall(Ex<?>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<T>(($int)q));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test57() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int g;\n");
        xml_.append(" $public Ex($int q){\n");
        xml_.append("  g = q;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int inn(T q){\n");
        xml_.append("  $Fct<Ex<?>,$int> f = $staticCall(Ex<?>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<T>(($int)q));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(Ex<?> p){\n");
        xml_.append("  $return p.g;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test58() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExParam {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExParam($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T:ExParam> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn($new ExParam(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int inn(T p){\n");
        xml_.append("  $Fct<T,$int> f = $staticCall(T).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test59() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return ($int)f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test60() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct f = $new Ex(14).$lambda(exmethtwo);\n");
        xml_.append("  $return ($int)f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }


    @Test
    public void test61() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct c = $staticCall().$lambda($new);\n");
        xml_.append("  $return ((Ex)c.call(14)).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test62() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f=14;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct c = $staticCall().$lambda($new);\n");
        xml_.append("  $return ((Ex)c.call()).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test63() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn(T p){\n");
        xml_.append("  $Fct<T,Ex<T>> e = $staticCall(Ex<>).$lambda($new);\n");
        xml_.append("  $Fct<Ex<T>,T> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(e.call(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test64() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = (T) p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn($int p){\n");
        xml_.append("  $Fct<$int,Ex<T>> e = $staticCall(Ex<>).$lambda($new);\n");
        xml_.append("  $Fct<Ex<T>,T> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(e.call(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test65() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex2<U> {}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn(T p){\n");
        xml_.append("  Ex<T> i = $staticCall().pr($staticCall(Ex<>).$lambda($new),p);\n");
        xml_.append("  $Fct<Ex<T>,T> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall Ex<T> pr($Fct<T,Ex<T>> e, T f){\n");
        xml_.append("  $return e.call(f);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall Ex<T> pr($Fct<T,Ex2<T>> e, T f){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test66() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] f = $new T[14];\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).inn().length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T[] inn(){\n");
        xml_.append("  $Fct<Ex<T>> e = $staticCall(Ex<>).$lambda($new);\n");
        xml_.append("  $Fct<Ex<T>,T[]> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(e.call());\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T[] exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }


    @Test
    public void test67() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S>:Ex<S> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] f = $new T[14];\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).inn().length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T[] inn(){\n");
        xml_.append("  $Fct<Ex<T>> e = $staticCall(ExTwo<>).$lambda($new);\n");
        xml_.append("  $Fct<Ex<T>,T[]> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(e.call());\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T[] exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test68() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<?> f = $new Ex(14).$lambda(exmethtwo);\n");
        xml_.append("  $return ($int)f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test69() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<?,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return ($int)f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }


    @Test
    public void test70() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return 10+$super.exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new ExSub(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void test71() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return 10+$super.exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex s = $new ExSub(14);\n");
        xml_.append("  $Fct<$int> f = s.$lambda(exmethtwo);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void test72() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int> f = $new Ex(0).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $return 14;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test73() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[],$int[]> f = $staticCall($int[]).$lambda(clone);\n");
        xml_.append("  $return f.call($new $int[2]).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test74() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int[]> f = $new $int[2].$lambda(clone);\n");
        xml_.append("  $return f.call().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test75() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int[]> f = $staticCall($int[]).$lambda($new);\n");
        xml_.append("  $return f.call(2).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test76() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  $Fct<$int,T[]> f = $staticCall(T[]).$lambda($new);\n");
        xml_.append("  $return f.call(2).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test76_() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth(2);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmeth(T p){\n");
        xml_.append("  $Fct<$int,T[]> f = $staticCall(T[]).$lambda($new);\n");
        xml_.append("  T[] a = f.call(1);\n");
        xml_.append("  a[0] = p;\n");
        xml_.append("  $return a[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void test76__() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth(2);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmeth(T p){\n");
        xml_.append("  $Fct<$int,T[]> f = $lambda(T[],$new,$int);\n");
        xml_.append("  T[] a = f.call(1);\n");
        xml_.append("  a[0] = p;\n");
        xml_.append("  $return a[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void test77() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return 10+$super.exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> f = $staticCall().$lambda($that.exmethtwo);\n");
        xml_.append("  $return f.call($new ExSub(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void test78() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return 10+$super.exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex s = $new ExSub(14);\n");
        xml_.append("  $Fct<$int> f = s.$lambda($that.exmethtwo);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void fail1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(Ex e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int> f = $new Ex(14).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSup {\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<ExSup,$int> f = $staticCall().$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex(14));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSup {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public ExSup($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn(T p){\n");
        xml_.append("  $Fct<Ex<T>,T> f = $staticCall(Ex<?>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int inn($int p){\n");
        xml_.append("  $Fct<Ex<$int>,$int> f = $staticCall(Ex<?>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call($new Ex<>(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmethtwo(Ex<$int> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex> c = $staticCall().$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex> c = $staticCall().$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Object c = $staticCall().$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var c = $staticCall().$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<ExAbs,$void> g = $staticCall(ExAbs).$lambda($that.m);\n");
        xml_.append("  $staticCall(<>).$lambda($new);\n");
        xml_.append("  $staticCall().$lambda($new);\n");
        xml_.append("  $static().$lambda($new);\n");
        xml_.append("  $staticCall($int[]).$lambda();\n");
        xml_.append("  $new $int[1].$lambda();\n");
        xml_.append("  $lambda($new).f;\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract $void m();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex(String p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int g;\n");
        xml_.append("  $public Inner($int p){\n");
        xml_.append("   g = p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $int exmethtwo(Inner e){\n");
        xml_.append("   $return f*g;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex exmeth(){\n");
        xml_.append("  $int res = pr2(((Ex.Inner)$null).$lambda(exmethtwo));\n");
        xml_.append("  $return pr($staticCall().$lambda($new));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<$int,Ex> e){\n");
        xml_.append("  $return e.call(0);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex pr($Fct<String,Ex> e){\n");
        xml_.append("  $return e.call(\"0\");\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr2($Fct<Ex.Inner,$int> e){\n");
        xml_.append("  $return e.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr2($Fct<Ex,$int> e){\n");
        xml_.append("  $return e.call($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(Ex e){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex>[] c = $staticCall().$lambda($new);\n");
        xml_.append("  $return c.call(14).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T f;\n");
        xml_.append(" $public Ex(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T inn(T p){\n");
        xml_.append("  $Fct<?,Ex<?>> e = $staticCall(Ex<>).$lambda($new);\n");
        xml_.append("  $Fct<Ex<T>,T> f = $staticCall(Ex<>).$lambda(exmethtwo);\n");
        xml_.append("  $return f.call(e.call(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T exmethtwo(Ex<T> e){\n");
        xml_.append("  $return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pr($staticCall(Ex<>).$lambda($new));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex<$int>> f){\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex<String>> f){\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Simple {}\n");
        xml_.append("$public $class pkg.ExTwo<T:Simple>:Ex<T> {}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<Object>> p = $staticCall(ExTwo<>).$lambda($new);\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex<$int>> f){\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int pr($Fct<Ex<String>> f){\n");
        xml_.append("  $return -1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int f=14;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct c = $staticCall(Ex<>).$lambda($new);\n");
        xml_.append("  $return ((Ex<?>)c.call()).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void fail17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int f=14;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<?,?> c = $staticCall(Ex<>).$lambda($new);\n");
        xml_.append("  $return ((Ex<?>)c.call()).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int f=14;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<?,?> c = $staticCall(Ex<>).$lambda($new);\n");
        xml_.append("  $return ((Ex<?>)c.call()).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo(){\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
}
