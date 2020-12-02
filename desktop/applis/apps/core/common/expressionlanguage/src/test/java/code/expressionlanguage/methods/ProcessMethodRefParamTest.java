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
    public void calculateArgument85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $int u=$that(t);\n");
        xml_.append("  u += 8;\n");
        xml_.append("  $return t;\n");
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
    public void calculateArgument86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $int u=$that(t);\n");
        xml_.append("  u = 8;\n");
        xml_.append("  $return t;\n");
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
    public void calculateArgument86_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $int u=$that(t);\n");
        xml_.append("  (u) = 8;\n");
        xml_.append("  $return t;\n");
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
    public void calculateArgument87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $int u=$that(t);\n");
        xml_.append("  u++;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $int u=$that(t);\n");
        xml_.append("  ++u;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  $that Compo v = $that(t);\n");
        xml_.append("  v += u;\n");
        xml_.append("  $return t.f;\n");
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
    public void calculateArgument90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  $that Compo u = $that(t);\n");
        xml_.append("  u++;\n");
        xml_.append("  $return t.f;\n");
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
    public void calculateArgument91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  $that Compo u = $that(t);\n");
        xml_.append("  ++u;\n");
        xml_.append("  $return t.f;\n");
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
    public void calculateArgument92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  $that Compo v = $that(t);\n");
        xml_.append("  $return (v += u).f;\n");
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
    public void calculateArgument93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $var u=$that(t);\n");
        xml_.append("  u += 8;\n");
        xml_.append("  $return t;\n");
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
    public void calculateArgument94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $int u=$that(t);\n");
        xml_.append("  $that $int v=$that(u);\n");
        xml_.append("  v += 8;\n");
        xml_.append("  $return t;\n");
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
    public void calculateArgument95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  {\n");
        xml_.append("   $that $int u=$that(t);\n");
        xml_.append("   u += 8;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
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
    public void calculateArgument96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"1\";\n");
        xml_.append("  {\n");
        xml_.append("   $that String u=$that(t);\n");
        xml_.append("   u += \"8\";\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("18", getString(ret_));
    }
    @Test
    public void calculateArgument97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $var u=$that(t);\n");
        xml_.append("  u += 8;\n");
        xml_.append("  $return u;\n");
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
    public void calculateArgument98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $var u=$that(t),v=$that(t);\n");
        xml_.append("  u += 8;\n");
        xml_.append("  v *= 2;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }
    @Test
    public void calculateArgument99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1;\n");
        xml_.append("  $that $var u=$that(t),v=$that(t);\n");
        xml_.append("  v *= 2;\n");
        xml_.append("  u += 8;\n");
        xml_.append("  $return t;\n");
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
    public void calculateArgument100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that(exmeth());\n");
        xml_.append("  u=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that(exmeth());\n");
        xml_.append("  u=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 8;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ($int)$class(Ex).getDeclaredMethods()[1].invoke($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that($operator(+,Ex)($null,$null));\n");
        xml_.append("  u=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $that $int(Ex a, Ex b){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that($operator(+)($null,$null));\n");
        xml_.append("  u=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$operator+ $that $int(pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return $that(pkg.Ex.t);\n");
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
    public void calculateArgument105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that(exmeth($id(Ex,~,$static)));\n");
        xml_.append("  u=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument106Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $intern{~getter():~getter(Ex)|~getter(ExTwo)};\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=getter($id(Ex,~));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return $new Ex(9).inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $that $int getter(){\n");
        xml_.append("  $return $that(inst);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $package ($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $that $int getter(){\n");
        xml_.append("  $return $that(sec);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("getter");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }
    @Test
    public void calculateArgument107Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> :pkg.ExTwo<S>{\n");
        xml_.append(" $intern{getter(~S):getter(Ex,~S)|getter(ExTwo,~T)};\n");
        xml_.append(" $public S inst=(S)2i;\n");
        xml_.append(" $public (S i){\n");
        xml_.append("  $super((S)16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  S v = (S)(($int)i+10);\n");
        xml_.append("  inst=(S)(($int)inst+($int)getter($that(v)));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return $new Ex<$int>(9).inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S getter($that S t){\n");
        xml_.append("  $return inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T sec;\n");
        xml_.append(" $package (T i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T getter($that T t){\n");
        xml_.append("  $return sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("getter");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }
    @Test
    public void calculateArgument108Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=getter($id(Ex,~));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  ExTwo e = $new Ex(9);\n");
        xml_.append("  $return e.getter($id(ExTwo,~));\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $that $int getter(){\n");
        xml_.append("  $return $that(inst);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $package ($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $that $int getter()$intern(Ex:~getter(ExTwo);ExTwo:~getter(ExTwo)){\n");
        xml_.append("  $return $that(sec);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("getter");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument109Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<V> :pkg.ExTwo<V,V>{\n");
        xml_.append(" $public V inst=(V)2i;\n");
        xml_.append(" $public (V i){\n");
        xml_.append("  $super((V)16i,(V)20i);\n");
        xml_.append("  inst=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  ExTwo<$int,$int> e = $new Ex<$int>(9);\n");
        xml_.append("  $int p = 25;\n");
        xml_.append("  $return e.getter($id(ExTwo,~T),$that(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal V getter2($that V v){\n");
        xml_.append("  $return (V)(($int)inst+($int)v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal V getter3($that V v){\n");
        xml_.append("  $return (V)(($int)inst*($int)v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T,U> {\n");
        xml_.append(" $public T sec;\n");
        xml_.append(" $public U th;\n");
        xml_.append(" $package (T i,U j){\n");
        xml_.append("  sec=i;\n");
        xml_.append("  th=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T getter($that T t)$intern(Ex:getter2(Ex,~V);ExTwo:getter(ExTwo,~T)){\n");
        xml_.append("  $return sec;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U getter($that U u)$intern(Ex:getter3(Ex,~V);ExTwo:getter(ExTwo,~U)){\n");
        xml_.append("  $return th;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("getter");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }
    @Test
    public void calculateArgument110Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<V> :pkg.ExTwo<V,V>{\n");
        xml_.append(" $public V inst=(V)2i;\n");
        xml_.append(" $public (V i){\n");
        xml_.append("  $super((V)16i,(V)20i);\n");
        xml_.append("  inst=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  ExTwo<$int,$int> e = $new Ex<$int>(9);\n");
        xml_.append("  $return e.getter($id(ExTwo,T),25);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal V getter2(V v){\n");
        xml_.append("  $return (V)(($int)inst+($int)v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal V getter3($that V v){\n");
        xml_.append("  $return (V)(($int)inst*($int)v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T,U> {\n");
        xml_.append(" $public T sec;\n");
        xml_.append(" $public U th;\n");
        xml_.append(" $package (T i,U j){\n");
        xml_.append("  sec=i;\n");
        xml_.append("  th=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T getter(T t)$intern(Ex:getter2(Ex,V);ExTwo:getter(ExTwo,T)){\n");
        xml_.append("  $return sec;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U getter($that U u)$intern(Ex:getter3(Ex,~V);ExTwo:getter(ExTwo,~U)){\n");
        xml_.append("  $return th;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("getter");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }
    @Test
    public void calculateArgument111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $return s.exmeth(15);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $public $that $int exmeth($int u){\n");
        xml_.append("  $int t=8;\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($int t){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $public $that $int exmeth($int u){\n");
        xml_.append("  $int t=15;\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument112Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" {\n");
        xml_.append("  exmeth($that(inst));\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst+=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $void exmeth($that $int t){\n");
        xml_.append("  t++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  Ex e = $new Ex(9);\n");
        xml_.append("  $return e.inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("getter");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that($operator(+)($null,$null));\n");
        xml_.append("  u=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$operator+ $that $int(pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return $that(pkg.Ex.t);\n");
        xml_.append("}\n");
        xml_.append("$operator+ $int(pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return pkg.Ex.t;\n");
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
    public void calculateArgument114Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=2;\n");
        xml_.append("  $return s.exmeth(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $int f=15;\n");
        xml_.append(" $public $abstract $that $int exmeth($int t);\n");
        xml_.append(" $public $abstract $that $int exmeth($long t);\n");
        xml_.append(" $public $int exmeth2($int t, $int u){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $int t=15;\n");
        xml_.append(" $public $that $int exmeth($int u){\n");
        xml_.append("  $int v = t * u;\n");
        xml_.append("  $return $that(v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth($long u){\n");
        xml_.append("  $int v = t + ($int)u;\n");
        xml_.append("  $return $that(v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth2($int t, $int u){\n");
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
    public void calculateArgument115Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $long inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=getter($id(Ex,~));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $long getter(){\n");
        xml_.append("  ExTwo e = $new Ex(9);\n");
        xml_.append("  $return e.getter($id(ExTwo,~));\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $that $long getter(){\n");
        xml_.append("  $return $that(inst);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $package ($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $that $int getter()$intern(Ex:~getter(Ex);ExTwo:~getter(ExTwo)){\n");
        xml_.append("  $return $that(sec);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument116Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $that $void getter(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument117Test() {
        StringMap<String> files_ = new StringMap<String>();

        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $that $void() {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  (exmeth())=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex.exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  (Ex.exmeth())=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()+=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  (exmeth())+=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument124Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex.exmeth()+=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument125Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 1;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  (Ex.exmeth())+=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument126Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = method()+=10;\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static that ExOhter method(){\n");
        xml_.append("  return that(sec);\n");
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
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument127Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = method()+=10;\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static that ExOhter method(){\n");
        xml_.append("  return that(sec);\n");
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
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument128Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument129Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  $this.exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e.exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth(){\n");
        xml_.append("  Ex.$this.exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument131Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static Compo t = $new Compo();\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  exmeth()++;\n");
        xml_.append("  $return t.f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Compo exmeth(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument132Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = $new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  Compo v = $new Compo();\n");
        xml_.append("  v.f = t.f+u.f;\n");
        xml_.append("  $operator(+,Compo)(t,u)=v;\n");
        xml_.append("  $return Compo.res.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static Compo res;\n");
        xml_.append(" $operator+ $that Compo (Compo a, Compo b){\n");
        xml_.append("  $return $that(res);\n");
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
    public void calculateArgument133Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo(8);\n");
        xml_.append("  Compo u = $new Compo(3);\n");
        xml_.append("  Compo v = $new Compo(t.f+u.f);\n");
        xml_.append("  $operator(+,Compo)(t,0)+=v;\n");
        xml_.append("  $return Compo.res.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static Compo res = $new Compo(10);\n");
        xml_.append(" $public Compo ($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ Compo (Compo a, Compo b){\n");
        xml_.append("  $return $new Compo(a.f+b.f);\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $that Compo (Compo a, $int b){\n");
        xml_.append("  $return $that(res);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(21, getNumber(ret_));
    }
    @Test
    public void calculateArgument134Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Compo t = $new Compo(8);\n");
        xml_.append("  $operator(+,Compo)(t,0)++;\n");
        xml_.append("  $return Compo.res.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static Compo res = $new Compo(10);\n");
        xml_.append(" $public Compo ($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $that Compo (Compo a, $int b){\n");
        xml_.append("  $return $that(res);\n");
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
    public void calculateArgument135Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 8;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()++;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument136Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 8;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ++exmeth();\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument137Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 8;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth()++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument138Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 8;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ++exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument139Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static String t = \"1\";\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  exmeth()+=\"8\";\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that String exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that String exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("18", getString(ret_));
    }
    @Test
    public void calculateArgument140Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Compo t = $new Compo(8);\n");
        xml_.append("  $operator(+,Compo)(t,0)+=\"v\";\n");
        xml_.append("  $return Compo.res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public $static String res = \"10\";\n");
        xml_.append(" $public Compo ($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $that String (Compo a, $int b){\n");
        xml_.append("  $return $that(res);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("10v", getString(ret_));
    }
    @Test
    public void calculateArgument141Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $classchoice(Ex)exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument142Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $superaccess(Ex)exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
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
    public void calculateArgument143Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static String t = \"1\";\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $classchoice(Ex)exmeth()+=\"8\";\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that String exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that String exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("18", getString(ret_));
    }
    @Test
    public void calculateArgument144Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static String t = \"1\";\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $superaccess(Ex)exmeth()+=\"8\";\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that String exmeth(){\n");
        xml_.append("  $return $that(exmeth2());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that String exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("18", getString(ret_));
    }
    @Test
    public void calculateArgument145Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static Solo<$int> t;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()=$new Solo<>(8);\n");
        xml_.append("  $return t.info;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class Solo<T>{\n");
        xml_.append("  $public T info;\n");
        xml_.append("  $public Solo(T p){\n");
        xml_.append("   info = p;\n");
        xml_.append("  }\n");
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
    public void calculateArgument146Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static Solo<$int> t;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()=same($new Solo<>(8));\n");
        xml_.append("  $return t.info;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Solo<$int> same(Solo<$int> p){\n");
        xml_.append("  $return p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class Solo<T>{\n");
        xml_.append("  $public T info;\n");
        xml_.append("  $public Solo(T p){\n");
        xml_.append("   info = p;\n");
        xml_.append("  }\n");
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
    public void calculateArgument147Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static Solo<$int> t;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()=same($new Solo<>(8));\n");
        xml_.append("  $return t.info;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth(){\n");
        xml_.append("  $return $that(exmeth2($new Solo<>(8)));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth2(Solo<$int> s){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Solo<$int> same(Solo<$int> p){\n");
        xml_.append("  $return p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class Solo<T>{\n");
        xml_.append("  $public T info;\n");
        xml_.append("  $public Solo(T p){\n");
        xml_.append("   info = p;\n");
        xml_.append("  }\n");
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
    public void calculateArgument148Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static Solo<$int> t;\n");
        xml_.append(" $static Object v;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()=same($new Solo<>(8));\n");
        xml_.append("  $return t.info;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth(){\n");
        xml_.append("  $return $that(exmeth2($new Solo<>(8),0));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth2(Solo<$int> s,$int u){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Object exmeth2(Solo<$long> s, $long u){\n");
        xml_.append("  $return $that(v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Solo<$int> same(Solo<$int> p){\n");
        xml_.append("  $return p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class Solo<T>{\n");
        xml_.append("  $public T info;\n");
        xml_.append("  $public Solo(T p){\n");
        xml_.append("   info = p;\n");
        xml_.append("  }\n");
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
    public void calculateArgument149Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static Solo<$int> t;\n");
        xml_.append(" $static Object v;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth()=same($new Solo<>(8));\n");
        xml_.append("  $return t.info;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth(){\n");
        xml_.append("  $return $that(exmeth2($new Solo<>(8)));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Solo<$int> exmeth2(Solo<$int> s){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that Object exmeth2(Solo<$long> s){\n");
        xml_.append("  $int i = 1/0;\n");
        xml_.append("  $return $that(v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Solo<$int> same(Solo<$int> p){\n");
        xml_.append("  $return p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class Solo<T>{\n");
        xml_.append("  $public T info;\n");
        xml_.append("  $public Solo(T p){\n");
        xml_.append("   info = p;\n");
        xml_.append("  }\n");
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
    public void calculateArgument150Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont e = $new ExCont();\n");
        xml_.append("  $int t = 0;\n");
        xml_.append("  $int res = e[$that(t)]+=20;\n");
        xml_.append("  $if (res != 30){\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $if (e[0] != 10){\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $if (e[1] != 30){\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int[] t={10,10};\n");
        xml_.append(" $public $int $this($that $int p){\n");
        xml_.append("  $return t[p++];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($that $int p){\n");
        xml_.append("  t[p]=$value;\n");
        xml_.append("  p++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return t[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  t[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument151Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont e = $new ExCont();\n");
        xml_.append("  $int t = 0;\n");
        xml_.append("  $int res = e[$id(ExCont,~$int),$that(t)]+=20;\n");
        xml_.append("  $if (res != 30){\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $if (e[$id(ExCont,$int),0] != 10){\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $if (e[$id(ExCont,$int),1] != 30){\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int[] t={10,10};\n");
        xml_.append(" $public $int $this($that $int p){\n");
        xml_.append("  $return t[p++];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($that $int p){\n");
        xml_.append("  t[p]=$value;\n");
        xml_.append("  p++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return t[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  t[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgumentFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int i=0;\n");
        xml_.append(" $public $void exmeth(){\n");
        xml_.append("  exmeth($that(Ex.$this));\n");
        xml_.append(" }\n");
        xml_.append(" $static{$that(t);}\n");
        xml_.append(" $public $static $int exmeth2(){\n");
        xml_.append("  $return $that(i);\n");
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
        xml_.append("  arr[a:0];\n");
        xml_.append("  arr[$that(v)];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth2($that $int t){\n");
        xml_.append("  ($int x:$void)->{$int v = t;};\n");
        xml_.append("  $that $int r = $that(0);\n");
        xml_.append("  $that $int v = r = $that(0);\n");
        xml_.append("  $that $int s = 2;\n");
        xml_.append("  $that $int u += 2;\n");
        xml_.append("  $that $int x, w = 0;\n");
        xml_.append("  $that $int y = (0];\n");
        xml_.append("  $that $int z = $that(t), a = $that(z);\n");
        xml_.append("  $that $int za = ($that(t));\n");
        xml_.append("  $that(za) = $that(t);\n");
        xml_.append("  $that $int zb = $that(za), $that(za) = $that(t);\n");
        xml_.append("  $that;\n");
        xml_.append("  ($int x:$void)->{$int v = x;};\n");
        xml_.append("  Object s2 = $null;\n");
        xml_.append("  $that $int s3 = $that(s2);\n");
        xml_.append("  $classchoice(ExTwo)$true($null)=$false;\n");
        xml_.append("  $superaccess(ExTwo)$true($null)=$false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $that $int $this($that $int t){$return 0;}\n");
        xml_.append(" $public $that $void $this($that $int t){}\n");
        xml_.append(" $public $static $int $($that ExTwo t){$return 0;}\n");
        xml_.append(" $public $static $boolean $true($that ExTwo t){$return $false;}\n");
        xml_.append(" $public $static $boolean $true(ExTwo t){$return $false;}\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Sup {\n");
        xml_.append(" $int f=15;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Sup s = $new Sub();\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=2;\n");
        xml_.append("  $return s.exmeth(t,t);\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $that $int exmeth($int t,$long u);\n");
        xml_.append(" $public $that $int exmeth($long t,$int u){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth2($int t, $int u){\n");
        xml_.append("  $return 8*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup {\n");
        xml_.append(" $int t=15;\n");
        xml_.append(" $public $that $int exmeth($int u,$long v){\n");
        xml_.append("  $int v = t * u;\n");
        xml_.append("  $return $that(v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $that $int exmeth($long u,$int v){\n");
        xml_.append("  $int v = t + ($int)u;\n");
        xml_.append("  $return $that(v);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth2($int t, $int u){\n");
        xml_.append("  $return 2*t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
