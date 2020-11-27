package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodRefParamTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  exmeth2($that(t));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth2($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth(u:$that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  exmeth2(u:$that(t));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth2($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int... t){\n");
        xml_.append("  t={8};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  exmeth(8,$that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int s,$that $int... t){\n");
        xml_.append("  t={s};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={8};\n");
        xml_.append("  $int l = 0;\n");
        xml_.append("  exmeth($that(l),t);\n");
        xml_.append("  $return l;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int l,$int... t){\n");
        xml_.append("  l=t.length;\n");
        xml_.append(" }\n");
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
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int l = 2;\n");
        xml_.append("  exmeth($vararg($int),$that(l));\n");
        xml_.append("  $return l;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int l,$int... t){\n");
        xml_.append("  l=t.length;\n");
        xml_.append(" }\n");
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
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int a = 1;\n");
        xml_.append("  $int b = 2;\n");
        xml_.append("  exmeth($that(a),$that(b));\n");
        xml_.append("  $if (a != 2){\n");
        xml_.append("   $return 2;\n");
        xml_.append("  }\n");
        xml_.append("  $if (b != 1){\n");
        xml_.append("   $return 1;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int l,$that $int m){\n");
        xml_.append("  $int t = l;\n");
        xml_.append("  l = m;\n");
        xml_.append("  m = t;\n");
        xml_.append(" }\n");
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
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int a = 1;\n");
        xml_.append("  $int b = 2;\n");
        xml_.append("  exmeth(m:$that(a),l:$that(b),n:15);\n");
        xml_.append("  $if (a != 15){\n");
        xml_.append("   $return 2;\n");
        xml_.append("  }\n");
        xml_.append("  $if (b != 17){\n");
        xml_.append("   $return 1;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int l,$that $int m,$int n){\n");
        xml_.append("  l += n;\n");
        xml_.append("  m *= n;\n");
        xml_.append(" }\n");
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
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return exmeth($that(t));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int t){\n");
        xml_.append("  $return t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return exmeth($that(t));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int t){\n");
        xml_.append("  $return ++t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  exmeth($that(t),u);\n");
        xml_.append("  $return t.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that Compo t, Compo u){\n");
        xml_.append("  t+=u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $operator+ Compo (Compo a, Compo b){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+b.f;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that Compo t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+1;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  exmeth($that(t),$that(u));\n");
        xml_.append("  $return u.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that Compo t,$that Compo u){\n");
        xml_.append("  u=t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+1;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  exmeth($that(t),$that(u));\n");
        xml_.append("  $return u.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that Compo t,$that Compo u){\n");
        xml_.append("  u=++t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+1;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int i = 0;\n");
        xml_.append("  $new Compo($that(i));\n");
        xml_.append("  $return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f=15;\n");
        xml_.append(" $public Compo($that $int p){\n");
        xml_.append("  p = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return ($int)$class(Ex).getDeclaredMethods()[1].invoke($null,t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int t){\n");
        xml_.append("  $return t+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  ($int)$class(Ex).getDeclaredMethods()[1].invoke($null,t);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int t){\n");
        xml_.append("  $return t+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  ($int)$class(Ex).getDeclaredMethods()[1].invoke($null,t);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int t){\n");
        xml_.append("  $return t+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExParam<$int> i = $new ExParam<>();\n");
        xml_.append("  $int t=8;\n");
        xml_.append("  $int u=9;\n");
        xml_.append("  $class(ExParam<$int>).getDeclaredMethods()[0].invoke(i,t,u);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append(" $public $void exmeth($that T t,$that T u){\n");
        xml_.append("  t=u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long i = 10;\n");
        xml_.append("  $int t=8;\n");
        xml_.append("  $int u=9;\n");
        xml_.append("  $class(ExParam<$int>).getDeclaredMethods()[0].invoke(i,t,u);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append(" $public $void exmeth($that T t,$that T u){\n");
        xml_.append("  t=u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  $operator(+,Compo)($that(t),u);\n");
        xml_.append("  $return t.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $operator+ Compo ($that Compo a, Compo b){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+b.f;\n");
        xml_.append("  a = c;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth($id(Ex,~$int),$that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int i = 0;\n");
        xml_.append("  $new Compo($id(Compo,~$int),$that(i));\n");
        xml_.append("  $return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f=15;\n");
        xml_.append(" $public Compo($that $int p){\n");
        xml_.append("  p = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int i = 0;\n");
        xml_.append("  $new Compo($id(Compo,~$int),$that(i));\n");
        xml_.append("  $return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f=15;\n");
        xml_.append(" $public Compo($that $int p){\n");
        xml_.append("  $this($that(p),5);\n");
        xml_.append(" }\n");
        xml_.append(" $public Compo($that $int a, $int b){\n");
        xml_.append("  a = f+b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int i = 0;\n");
        xml_.append("  $new Compo($that(i));\n");
        xml_.append("  $return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f=15;\n");
        xml_.append(" $public Compo($that $int p){\n");
        xml_.append("  $this($id(Compo,~$int,$int),$that(p),5);\n");
        xml_.append(" }\n");
        xml_.append(" $public Compo($that $int a, $int b){\n");
        xml_.append("  a = f+b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t/=0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DivideZeroException", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=2;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t*8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=2;\n");
        xml_.append("  $return exmeth(t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t*8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  exmeth($that(t),u);\n");
        xml_.append("  $return t.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that Compo t, Compo u){\n");
        xml_.append("  t+=u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $operator+ Compo (Compo a, Compo b){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+b.f;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ Compo ($that Compo a,$that Compo b){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+b.f;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  exmeth($that(t),u);\n");
        xml_.append("  $return t.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that Compo t, Compo u){\n");
        xml_.append("  t&&=u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static $boolean $false(Compo a){\n");
        xml_.append("  $return $false;\n");
        xml_.append(" }\n");
        xml_.append(" $operator&& Compo (Compo a, Compo b){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+b.f;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append(" $operator&& Compo ($that Compo a,$that Compo b){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+b.f;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that Compo t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+1;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo ($that Compo a){\n");
        xml_.append("  Compo c = $new Compo();\n");
        xml_.append("  c.f = a.f+1;\n");
        xml_.append("  $return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $int u = exmeth($that(t));\n");
        xml_.append("  $if (u != t){\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int t){\n");
        xml_.append("  $return t+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int a = 1;\n");
        xml_.append("  $int b = 2;\n");
        xml_.append("  exmeth(l:$that(a),m:$that(b),n:15);\n");
        xml_.append("  $if (a != 16){\n");
        xml_.append("   $return 2;\n");
        xml_.append("  }\n");
        xml_.append("  $if (b != 30){\n");
        xml_.append("   $return 1;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int l,$that $int m,$int n){\n");
        xml_.append("  l += n;\n");
        xml_.append("  m *= n;\n");
        xml_.append(" }\n");
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
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int a = 1;\n");
        xml_.append("  $int b = 2;\n");
        xml_.append("  exmeth(l:$that(a),n:15,m:$that(b));\n");
        xml_.append("  $if (a != 16){\n");
        xml_.append("   $return 2;\n");
        xml_.append("  }\n");
        xml_.append("  $if (b != 30){\n");
        xml_.append("   $return 1;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int l,$that $int m,$int n){\n");
        xml_.append("  l += n;\n");
        xml_.append("  m *= n;\n");
        xml_.append(" }\n");
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
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int... t){\n");
        xml_.append("  t[0]=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={0};\n");
        xml_.append("  exmeth(t);\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int... t){\n");
        xml_.append("  t[0]=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth(u:$that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int... v){\n");
        xml_.append("  v[0]=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={0};\n");
        xml_.append("  exmeth(v:t);\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int... v){\n");
        xml_.append("  v[0]=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int... t){\n");
        xml_.append("  t={8};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $long... t){\n");
        xml_.append("  t={8l};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  exmeth(u:$that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int... u){\n");
        xml_.append("  u={8};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $long... v){\n");
        xml_.append("  v={8l};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={10};\n");
        xml_.append("  exmeth(u:t);\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int... u){\n");
        xml_.append("  u[0]=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int... v){\n");
        xml_.append("  v={9};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={10};\n");
        xml_.append("  exmeth(p:11,u:t);\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int p,$int... u){\n");
        xml_.append("  u[0]=8+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int p,$that $int... u){\n");
        xml_.append("  u={9+p};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={10};\n");
        xml_.append("  exmeth(u:t);\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int... u){\n");
        xml_.append("  u[0]=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int... u){\n");
        xml_.append("  u={9};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth(8,9);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int... u){\n");
        xml_.append("  $return u[0]+u[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int... u){\n");
        xml_.append("  u={10};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(17, getNumber(ret_));
    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={1};\n");
        xml_.append("  exmeth(10,$that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int... u){\n");
        xml_.append("  $return u[0]+u[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int p,$that $int... u){\n");
        xml_.append("  u={p};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={1};\n");
        xml_.append("  exmeth(10,u:$that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int... u){\n");
        xml_.append("  $return u[0]+u[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int p,$that $int... u){\n");
        xml_.append("  u={p};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={1};\n");
        xml_.append("  exmeth(10,u:$that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int p,$int... u){\n");
        xml_.append("  $return u[0]+u[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int p,$that $int... u){\n");
        xml_.append("  u={p};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $class(Ex).getDeclaredMethods()[1].invoke($null);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int t){\n");
        xml_.append("  $return t+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.BadArgNumber", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  s.exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=15;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $return s.exmeth(15);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=15;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(30, getNumber(ret_));
    }
    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  s.exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=15;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $return s.exmeth(15);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=15;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(30, getNumber(ret_));
    }
    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $lambda(Compo,$new,$int).call(15).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f=15;\n");
        xml_.append(" $public Compo($that $int p){\n");
        xml_.append("  p = f;\n");
        xml_.append(" }\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $lambda(Ex,exmeth,$int).call(7);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that String t){\n");
        xml_.append("  t+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("83", getString(ret_));
    }
    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int s;\n");
        xml_.append("  s=0;\n");
        xml_.append("  $for ($int t = 0; t < 8;){\n");
        xml_.append("   exmeth($that(t));\n");
        xml_.append("   s=t;\n");
        xml_.append("  }\n");
        xml_.append("  $return s;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $int t;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t=1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $int t=1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($that(Ex.t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $int t;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth($that($this.t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t=1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($that(Ex.t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $int t=1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth($that($this.t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static ExCont t = $new ExCont();\n");
        xml_.append(" $public $static $class ExCont {\n");
        xml_.append("  $int u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($that(Ex.t.u));\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" ExCont t = $new ExCont();\n");
        xml_.append(" $public $static $class ExCont {\n");
        xml_.append("  $int u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth($that($this.t.u));\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static ExCont t=$new ExCont();\n");
        xml_.append(" $public $static $class ExCont {\n");
        xml_.append("  $int u=1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($that(Ex.t.u));\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" ExCont t=$new ExCont();\n");
        xml_.append(" $public $static $class ExCont {\n");
        xml_.append("  $int u=1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth($that($this.t.u));\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static ExCont t = $new Ex().$new ExCont();\n");
        xml_.append(" $int u;\n");
        xml_.append(" $public $class ExCont {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($that(Ex.t.u));\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" ExCont t = $new ExCont();\n");
        xml_.append(" $int u;\n");
        xml_.append(" $public $class ExCont {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth($that($this.t.u));\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int u){\n");
        xml_.append("  u=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={0};\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int[] t){\n");
        xml_.append("  t={8};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={0};\n");
        xml_.append("  exmeth($that(t[0]));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={1};\n");
        xml_.append("  exmeth($that(t[0]));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument78Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = 10;\n");
        xml_.append("  exmeth(that(e));\n");
        xml_.append("  if (e.field == 11){\n");
        xml_.append("   return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(that ExClass t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExOhter sec = new ExOhter();\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = method(that(sec),10);\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExSub2 method(that ExOhter e, int s){\n");
        xml_.append("  return e+=s;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOhter {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExOhter i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOhter $(ExSub i){\n");
        xml_.append("  ExOhter e = new ExOhter();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExOhter i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15,getNumber(ret_));
    }
    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  s.exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $public $abstract $void exmeth($that $int t);\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=15;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $return s.exmeth(15);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int exmeth($int t);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t=15;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(30, getNumber(ret_));
    }
    @Test
    public void calculateArgument82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  s.exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $public $abstract $void exmeth($that $int t);\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  (t)=15;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={1};\n");
        xml_.append("  exmeth($that(t[$new ExCont()]));\n");
        xml_.append("  $return t[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int u;\n");
        xml_.append(" $public $static $int $(ExCont t){\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t;\n");
        xml_.append("  t={1,2};\n");
        xml_.append("  exmeth($that(t[$new ExCont()]));\n");
        xml_.append("  $return t[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t+=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int u=1;\n");
        xml_.append(" $public $static $int $(ExCont t){\n");
        xml_.append("  $return t.u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgumentFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int i=0;\n");
        xml_.append(" $public $void exmeth(){\n");
        xml_.append("  exmeth($that(Ex.$this));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth(){\n");
        xml_.append("  $that(t);\n");
        xml_.append("  exmeth($that(0));\n");
        xml_.append("  $final $int f = 0;\n");
        xml_.append("  $int arr = 0;\n");
        xml_.append("  exmeth($that(f));\n");
        xml_.append("  exmeth($that(arr[0]));\n");
        xml_.append("  exmeth($that(i));\n");
        xml_.append("  exmeth($that(Ex.i));\n");
        xml_.append("  $int v = 0;\n");
        xml_.append("  ($int x:$void)->{exmeth($that(v));}.call($that(f));\n");
        xml_.append("  ($int x:$void)->{exmeth($that(v));}.call(d:$that(f));\n");
        xml_.append("  $for ($int i=0;i<10;i++){\n");
        xml_.append("   ($int x:$void)->{exmeth($that(i));};\n");
        xml_.append("  }\n");
        xml_.append("  $for ($final $int i=0;i<10;){\n");
        xml_.append("   exmeth($that(i));\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth2($that $int t){\n");
        xml_.append("  ($int x:$void)->{$int v = t;};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int $this($that $int t){$return 0;}\n");
        xml_.append(" $public $void $this($that $int t){}\n");
        xml_.append(" $public $static $int $($that ExTwo t){$return 0;}\n");
        xml_.append(" $public $static $boolean $true($that ExTwo t){$return $false;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
