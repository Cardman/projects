package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
}
