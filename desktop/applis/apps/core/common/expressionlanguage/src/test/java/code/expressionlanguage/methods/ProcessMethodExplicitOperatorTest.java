package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodExplicitOperatorTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return a.a+b.a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)(one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return a.a+b.a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)($id(Ex,Ex),one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex... b){\n");
        xml_.append(" $return a.a+b[0].a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)($id(Ex,Ex...),one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex... b){\n");
        xml_.append(" $return a.a+b[0].a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)(one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex... b){\n");
        xml_.append(" $return a.a+b[0].a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)(one, $new Ex[]{ two}) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex... b){\n");
        xml_.append(" $return a.a+b.length;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ($operator(+)(one) != 5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex... b){\n");
        xml_.append(" $return a.a+b[0].a+b[1].a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)(one, two,two) != 11i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex... b){\n");
        xml_.append(" $return a.a+b[0].a+b[1].a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)($vararg(pkg.Ex),one, $firstopt(two),two) != 11i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex... b){\n");
        xml_.append(" $return a.a+(b.length==0?3:2);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ($operator(+)($vararg(pkg.Ex),one) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator+ $int (Ex a, Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)(one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator+ $int (Ex a, Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)($id($static,Ex,Ex),one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex... b){\n");
        xml_.append("  $return a.a+b[0].a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)($id($static,Ex,Ex...),one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex... b){\n");
        xml_.append("  $return a.a+b[0].a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)(one, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex... b){\n");
        xml_.append("  $return a.a+b[0].a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)(one, $new Ex[]{ two}) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex... b){\n");
        xml_.append("  $return a.a+b.length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ($operator(+,Ex)(one) != 5i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex... b){\n");
        xml_.append("  $return a.a+b[0].a+b[1].a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)(one, two,two) != 11i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex... b){\n");
        xml_.append("  $return a.a+b[0].a+b[1].a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)($vararg(pkg.Ex),one, $firstopt(two),two) != 11i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex... b){\n");
        xml_.append("  $return a.a+(b.length==0?3:2);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  $if ($operator(+,Ex)($vararg(pkg.Ex),one) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $public $static $int res = res();\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res(){\n");
        xml_.append("  $if ($operator(+,Ex)($null, $null) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  Ex.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $operator+ $int (Ex a, Ex b){\n");
        xml_.append("  $return 8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExTwo");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.ExTwo", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $public $static $int res = res();\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res(){\n");
        xml_.append("  $if ((Ex)$null+$null != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  Ex.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $operator+ $int (Ex a, Ex b){\n");
        xml_.append("  $return 8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExTwo");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.ExTwo", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $public $static $int res = res();\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res(){\n");
        xml_.append("  Ex e = $null;\n");
        xml_.append("  e++;\n");
        xml_.append("  $if (e.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  Ex.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $operator++ Ex (Ex a){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e.a=8;\n");
        xml_.append("  $return e;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExTwo");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.ExTwo", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $public $static $int res = res();\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res(){\n");
        xml_.append("  Ex e = $null;\n");
        xml_.append("  e+=$null;\n");
        xml_.append("  $if (e.a != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  Ex.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append(" $operator+ Ex (Ex a,Ex b){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e.a=8;\n");
        xml_.append("  $return e;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExTwo");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.ExTwo", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator+ $int (Ex a, Ex b, Ex c){\n");
        xml_.append("  $return a.a+b.a+c.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  Ex three = $new Ex();\n");
        xml_.append("  three.a=2i;\n");
        xml_.append("  $if ($operator(+,Ex)(one, two, three) != 10i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return a.a+b.a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+)(one, two, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $operator+ $int (Ex a, Ex b){\n");
        xml_.append("  $return a.a+b.a;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if ($operator(+,Ex)(one, two, two) != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
