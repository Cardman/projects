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
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
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
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
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
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
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
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
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
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
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
