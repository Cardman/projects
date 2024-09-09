package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodEvolvedOperatorTest extends ProcessMethodCommon {
    @Test
    public void calc1() {
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
        xml_.append("  ((Ex<$int>)one)+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc2() {
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
        xml_.append("  ((Ex<$int>)one)++;\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calc2_() {
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
        xml_.append("  $operator(++,Ex<$int>)=(one);\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  ((Ex<$int>)one)??=two;\n");
        xml_.append("  $if (one.a != 5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $null;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  ((Ex<$int>)one)??=two;\n");
        xml_.append("  $if (one.a != 3i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc5() {
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
        xml_.append("  ((Ex<$int>)(one))+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $static $int $(Ex<T> a){\n");
        xml_.append("  $return ($int)a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  ((Ex<$int>)one)++;\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $static $int $(Ex<T> a){\n");
        xml_.append("  $return ($int)a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  ++((Ex<$int>)one);\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $static $int $(Ex<T> a){\n");
        xml_.append("  $return ($int)a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((((Ex<$int>)one)++).a != 5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $static $int $(Ex<T> a){\n");
        xml_.append("  $return ($int)a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((++((Ex<$int>)one)).a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $static $int $(Ex<T> a){\n");
        xml_.append("  $return ($int)a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).catching();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int catching(){\n");
        xml_.append("  Ex<T> one = $new Ex<>();\n");
        xml_.append("  one.a=(T)5i;\n");
        xml_.append("  ((Ex<T>)one)++;\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $static $int $(Ex<T> a){\n");
        xml_.append("  $return ($int)a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ((++(Ex<$int>)one).a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ $int (Ex<$int> a, Ex<$int> b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  ((Ex<$int>)one)+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $static $int $(Ex<T> a){\n");
        xml_.append("  $return ($int)a.a;\n");
        xml_.append(" }\n");
        xml_.append(" $static Ex<T> $($int a){\n");
        xml_.append("  Ex<T> o = $new Ex<>();\n");
        xml_.append("  o.a=(T)a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex<$int> one = $new Ex<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  ((Ex<$int>)one)+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc14() {
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
        xml_.append("  (Ex<$int>)one+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calc15() {
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
        xml_.append("  ++(Ex<$int>)one;\n");
        xml_.append("  $if (one.a != 6i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void cdm() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T a;\n");
        xml_.append(" $operator+ Ex<$int> (Ex<$int> a, Ex<$int> b){\n");
        xml_.append("  Ex<$int> o = $new Ex<>();\n");
        xml_.append("  o.a=a.a+b.a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int> one = $new ExTwo<>();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex<$int> two = $new Ex<>();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  ((Ex<$int>)one)+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S>:pkg.Ex<S> {\n");
        xml_.append(" $static pkg.ExTwo<S> $(pkg.Ex<S> a){\n");
        xml_.append("  pkg.ExTwo<S> p = $new pkg.ExTwo<S>();\n");
        xml_.append("  p.a=a.a;\n");
        xml_.append("  $return p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calcFail() {
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
        xml_.append("  StringBuilder str = (($int)one)+=two;\n");
        xml_.append("  $if (one.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
