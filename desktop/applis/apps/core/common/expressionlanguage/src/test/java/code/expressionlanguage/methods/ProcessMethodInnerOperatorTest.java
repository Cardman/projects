package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodInnerOperatorTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one + two != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $int (Ex<$int> a, Ex<$int> b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one + two != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ Ex<$int> (Ex<$int> a, Ex<$int> b){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+b.a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ((one + two).a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (pkg.Ex a){\n");
        xml_.append("  $return a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=8i;\n");
        xml_.append("  $if (+one != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator== $boolean (pkg.Ex a,pkg.Ex b){\n");
        xml_.append("  $return a.a == b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=8i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=8i;\n");
        xml_.append("  $if (one == two){\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator< $boolean (pkg.Ex a,pkg.Ex b){\n");
        xml_.append("  $return a.a < b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=8i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=9i;\n");
        xml_.append("  $if (one < two){\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator~ $int (pkg.Ex a){\n");
        xml_.append("  $return a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=8i;\n");
        xml_.append("  $if (~one != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ Ex<$int> (Ex<$int> a, Ex<$int> b){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+b.a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  one+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ Ex<$int> (Ex<$int> a){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+1;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  one++;\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ Ex<$int> (Ex<$int> a){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+1;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  ++one;\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ Ex<$int> (Ex<$int> a){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+1;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((one++).a != 5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ Ex<$int> (Ex<$int> a){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+1;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((++one).a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ Ex<$int> (Ex<$int> a, Ex<$int> b){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+b.a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ((one+=two).a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ Static(pkg.Static a) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,Static,+,$id,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,Static,+,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,Static,+,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,Static,+,$id,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,Static,+,$id,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static[],Static> f = $lambda($operator,Static,+,Static,Static...);\n");
        xml_.append("  $return f.call(s,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static[],Static> f = s.$lambda($operator,Static,+,Static...);\n");
        xml_.append("  $return f.call($new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static c,Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + c.field +b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  Static u = $new Static();\n");
        xml_.append("  u.field=4i;\n");
        xml_.append("  $Fct<Static,Static[],Static> f = s.$lambda($operator,Static,+,Static,Static...);\n");
        xml_.append("  $return f.call(u,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }

    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($class(Ex).getDeclaredMethods(\"+\",$true,$false,$class(Ex),$class(Ex))[0].invoke($null,one,two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($class(Ex).getDeclaredMethods()[0].invoke($null,one,two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator! $int (pkg.Ex a){\n");
        xml_.append("  $return a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if (!one != 5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator++ ExSub (pkg.Ex a){\n");
        xml_.append("  ExSub b = $new ExSub();\n");
        xml_.append("  b.a=a.a+1;\n");
        xml_.append("  $return b;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new ExSub();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((one++).a == 5i){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator++ ExSub (pkg.Ex a){\n");
        xml_.append("  ExSub b = $new ExSub();\n");
        xml_.append("  b.a=a.a+1;\n");
        xml_.append("  $return b;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new ExSub();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((++one).a == 6i){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one + two != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T> a, pkg.Ex<T> b){\n");
        xml_.append("  $return ($int)a.a+($int)b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one + two != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgumentArr1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T>[] a, pkg.Ex<T>[] b){\n");
        xml_.append("  $return ($int)a[0].a+($int)b[0].a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($new Ex<$int>[]{one} + $new Ex<$int>[]{two} != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgumentArr1_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall pkg.Ex<T>[] (pkg.Ex<T>[] a, pkg.Ex<T>[] b){\n");
        xml_.append("  Ex<T> one = $new Ex<>();\n");
        xml_.append("  one.a = (T)(($int)a[0].a+($int)b[0].a);\n");
        xml_.append("  $return {one};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  Ex<$int>[] v = $new Ex<$int>[]{one} + $new Ex<$int>[]{two};\n");
        xml_.append("  $if (v[0].a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgumentArr2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator- $staticCall $int (pkg.Ex<T>[] a){\n");
        xml_.append("  $return -($int)a[0].a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if (-$new Ex<$int>[]{one} != -5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T> a, $int b){\n");
        xml_.append("  $return ($int)a.a+b;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if (one + 3 != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int ($int a, pkg.Ex<T> b){\n");
        xml_.append("  $return a+($int)b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (5 + two != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExSuper<T> {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one + two != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper<S> {\n");
        xml_.append(" $public S a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.ExSuper<S> a, pkg.ExSuper<S> b){\n");
        xml_.append("  $return ($int)a.a+($int)b.a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall pkg.Ex<T> (pkg.Ex<T> a, pkg.Ex<T> b){\n");
        xml_.append("  pkg.Ex<T> out = $new pkg.Ex<T>();\n");
        xml_.append("  out.a=(T)(($int)a.a+($int)b.a);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ((one += two).a == 8i){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }

    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall pkg.ExThree<T> (pkg.ExTwo<T> a, pkg.Ex<T> b){\n");
        xml_.append("  pkg.ExThree<T> out = $new pkg.ExThree<T>();\n");
        xml_.append("  out.a=(T)(($int)a.a+($int)b.a);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int> one = $new ExTwo<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  ExFour<$int> three = one += two;\n");
        xml_.append("  $if (three.a == 8i){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U> {\n");
        xml_.append(" $public U a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<V> {\n");
        xml_.append(" $public V a;\n");
        xml_.append(" $public $static pkg.ExTwo<V> $(pkg.ExThree<V> a){\n");
        xml_.append("  pkg.ExTwo<V> out = $new pkg.ExTwo<V>();\n");
        xml_.append("  out.a=a.a;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<Y> {\n");
        xml_.append(" $public Y a;\n");
        xml_.append(" $public $static pkg.ExFour<Y> $(pkg.ExTwo<Y> a){\n");
        xml_.append("  pkg.ExFour<Y> out = $new pkg.ExFour<Y>();\n");
        xml_.append("  out.a=a.a;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }

    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall pkg.ExThree<T> (pkg.ExTwo<T> a, pkg.Ex<T> b){\n");
        xml_.append("  pkg.ExThree<T> out = $new pkg.ExThree<T>();\n");
        xml_.append("  out.a=(T)(($int)a.a+($int)b.a);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExFive<$int> one = $new ExFive<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  ExFour<$int> three = one += two;\n");
        xml_.append("  $if (three.a == 8i){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U> {\n");
        xml_.append(" $public U a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<V> {\n");
        xml_.append(" $public V a;\n");
        xml_.append(" $public $static pkg.ExFive<V> $(pkg.ExThree<V> a){\n");
        xml_.append("  pkg.ExFive<V> out = $new pkg.ExFive<V>();\n");
        xml_.append("  out.a=a.a;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive<W> {\n");
        xml_.append(" $public W a;\n");
        xml_.append(" $public $static pkg.ExTwo<W> $(pkg.ExFive<W> a){\n");
        xml_.append("  pkg.ExTwo<W> out = $new pkg.ExTwo<W>();\n");
        xml_.append("  out.a=a.a;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<Y> {\n");
        xml_.append(" $public Y a;\n");
        xml_.append(" $public $static pkg.ExFour<Y> $(pkg.ExTwo<Y> a){\n");
        xml_.append("  pkg.ExFour<Y> out = $new pkg.ExFour<Y>();\n");
        xml_.append("  out.a=a.a;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static pkg.ExFour<Y> $(pkg.ExFive<Y> a){\n");
        xml_.append("  pkg.ExFour<Y> out = $new pkg.ExFour<Y>();\n");
        xml_.append("  out.a=a.a;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }

    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator- $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a-b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one - two != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator* $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a*b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one * two != 15i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator/ $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a/b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=14i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one / two != 4i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator% $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a%b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=14i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one % two != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator& $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a&b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=3;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=5;\n");
        xml_.append("  $if ((one & two) != 1){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator| $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a|b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=3;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=5;\n");
        xml_.append("  $if ((one | two) != 7){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator^ $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a^b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=3;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=5;\n");
        xml_.append("  $if ((one ^ two) != 6){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator<< $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a<<b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=2;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one << two != 16){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator>> $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a>>b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=16;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one >> two != 2){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator<<< $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a<<<b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=2;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one <<< two != 16){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator>>> $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a>>>b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=16;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one >>> two != 2){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator<<<< $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a<<<<b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=2;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one <<<< two != 16){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator>>>> $staticCall $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a>>>>b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=16;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one >>>> two != 2){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator< $staticCall $boolean (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a<b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=3;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=2;\n");
        xml_.append("  $if (one < two){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator> $staticCall $boolean (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a>b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=2;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one > two){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator<= $staticCall $boolean (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a<=b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=3;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=2;\n");
        xml_.append("  $if (one <= two){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator>= $staticCall $boolean (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a>=b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=2;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3;\n");
        xml_.append("  $if (one >= two){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator== $staticCall $boolean (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a==b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=3;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=2;\n");
        xml_.append("  $if (one == two){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator!= $staticCall $boolean (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a!=b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=2;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=2;\n");
        xml_.append("  $if (one != two){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex a){\n");
        xml_.append("  $return a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if (+one != 5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator- $staticCall $int (pkg.Ex a){\n");
        xml_.append("  $return -a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if (-one != -5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator~ $staticCall $int (pkg.Ex a){\n");
        xml_.append("  $return ~a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if (~one != -6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator! $staticCall $int (pkg.Ex a){\n");
        xml_.append("  $return ~a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if (!one != -6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator++ $staticCall pkg.Ex (pkg.Ex a){\n");
        xml_.append("  pkg.Ex out = $new pkg.Ex();\n");
        xml_.append("  out.a = a.a+1;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((one++).a == 5){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator++ $staticCall pkg.Ex (pkg.Ex a){\n");
        xml_.append("  pkg.Ex out = $new pkg.Ex();\n");
        xml_.append("  out.a = a.a+1;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((++one).a == 6){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ $staticCall pkg.Ex<T> (pkg.Ex<T> a){\n");
        xml_.append("  pkg.Ex<T> out = $new pkg.Ex<T>();\n");
        xml_.append("  out.a = (T)(($int)a.a+1);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((one++).a == 5){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ $staticCall pkg.Ex<T> (pkg.Ex<T> a){\n");
        xml_.append("  pkg.Ex<T> out = $new pkg.Ex<T>();\n");
        xml_.append("  out.a = (T)(($int)a.a+1);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((++one).a == 6){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator-- $staticCall pkg.Ex<T> (pkg.Ex<T> a){\n");
        xml_.append("  pkg.Ex<T> out = $new pkg.Ex<T>();\n");
        xml_.append("  out.a = (T)(($int)a.a-1);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).catching();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T catching(){\n");
        xml_.append("  Ex<T> one = $new Ex<>();\n");
        xml_.append("  one.a=(T)5i;\n");
        xml_.append("  $if ((one--).a == 5){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return (T)0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator-- $staticCall pkg.Ex<T> (pkg.Ex<T> a){\n");
        xml_.append("  pkg.Ex<T> out = $new pkg.Ex<T>();\n");
        xml_.append("  out.a = (T)(($int)a.a-1);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).catching();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T catching(){\n");
        xml_.append("  Ex<T> one = $new Ex<>();\n");
        xml_.append("  one.a=(T)5i;\n");
        xml_.append("  $if ((--one).a == 4){\n");
        xml_.append("   $return (T)one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return (T)0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCaller<S> {\n");
        xml_.append(" $public $staticCall S catching(){\n");
        xml_.append("  Ex<S> one = $new Ex<S>();\n");
        xml_.append("  one.a=(S)5i;\n");
        xml_.append("  $if ((one++).a == 5){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return (S)0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ $staticCall pkg.Ex<T> (pkg.Ex<T> a){\n");
        xml_.append("  pkg.Ex<T> out = $new pkg.Ex<T>();\n");
        xml_.append("  out.a = (T)(($int)a.a+1);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return $staticCall(ExCaller<$int>).catching();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCaller<S> {\n");
        xml_.append(" $public $staticCall S catching(){\n");
        xml_.append("  Ex<S> one = $new Ex<S>();\n");
        xml_.append("  one.a=(S)5i;\n");
        xml_.append("  $if ((++one).a == 6){\n");
        xml_.append("   $return one.a;\n");
        xml_.append("  }\n");
        xml_.append("  $return (S)0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator++ $staticCall pkg.Ex<T> (pkg.Ex<T> a){\n");
        xml_.append("  pkg.Ex<T> out = $new pkg.Ex<T>();\n");
        xml_.append("  out.a = (T)(($int)a.a+1);\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return $staticCall(ExCaller<$int>).catching();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T> a, pkg.Ex<T> b){\n");
        xml_.append("  $return ($int)a.a+($int)b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex<$int>)(one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T> a, pkg.Ex<T> b){\n");
        xml_.append("  $return ($int)(T)(($int)a.a+($int)b.a);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex<$int>)($id($staticCall,Ex<T>,Ex<T>),one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $int (pkg.Ex<$int> a, pkg.Ex<$int> b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex<$int>)($id($static,Ex<$int>,Ex<$int>),one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $staticCall Static(pkg.Static a) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,Static,+,$id,$staticCall,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,Static,+,$id,$staticCall,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,Static,+,$id,$staticCall,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,Static,+,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b.field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,Static,+,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static[],Static> f = $lambda($operator,Static,+,Static,Static...);\n");
        xml_.append("  $return f.call(s,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static[],Static> f = s.$lambda($operator,Static,+,Static...);\n");
        xml_.append("  $return f.call($new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static(Static a, Static c,Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + c.field +b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  Static u = $new Static();\n");
        xml_.append("  u.field=4i;\n");
        xml_.append("  $Fct<Static,Static[],Static> f = s.$lambda($operator,Static,+,Static,Static...);\n");
        xml_.append("  $return f.call(u,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }

    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T> a, pkg.Ex<T> b){\n");
        xml_.append("  $return ($int)(T)(a.a+b.a);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($class(Ex<$int>).getDeclaredMethods(\"+\",$true,$false,$class(Ex<$int>),$class(Ex<$int>))[0].invoke($null,one,two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T> a, pkg.Ex<T> b){\n");
        xml_.append("  $return ($int)(T)(a.a+b.a);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($class(Ex<$int>).getDeclaredMethods()[0].invoke($null,one,two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T>... a){\n");
        xml_.append("  $return ($int)(T)(($int)a[0].a+($int)a[1].a);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex<$int>)($id($staticCall,Ex<T>...),one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $staticCall $int (pkg.Ex<T>... a){\n");
        xml_.append("  $return ($int)(T)(($int)a[0].a+($int)a[1].a);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<$int>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<$int>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex<$int>)(one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static c,Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + c.field +b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  Static u = $new Static();\n");
        xml_.append("  u.field=4i;\n");
        xml_.append("  $Fct<Static,Static[],Static> f = s.$lambda($operator,Static);\n");
        xml_.append("  $return f.call(u,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();

        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ Static(Static a, Static c,Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + c.field +b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $operator<> Static(Static a, Static c,Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + c.field +b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $operator<> Static(Static a, Static c,Static... b) {\n");
        xml_.append("  Static o = $new Static();\n");
        xml_.append("  o.field = a.field + c.field +b[0].field;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $operator(+,+,+)($null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator++ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ $int (pkg.Ex... a){\n");
        xml_.append("  $return a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0i;\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $operator+ $staticCall Static<T>(Static<T> a, T c,Static<T>... b) {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $staticCall Static<T>(Static<T> a) {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $operator<> $staticCall Static<T>(Static<T> a) {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $operator<> $staticCall Static<T>(Static<T> a,Static<T> b) {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $staticCall Static<T>($int a,Ex b) {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $staticCall Static<T>(Ex b) {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static<?> w = $new Static<$int>();\n");
        xml_.append("  Static<$int> s = $new Static<$int>();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  +w;\n");
        xml_.append("  s.$lambda($operator,Static<?>,+,$id,$staticCall,Static<T>,T,Static<T>...);\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
