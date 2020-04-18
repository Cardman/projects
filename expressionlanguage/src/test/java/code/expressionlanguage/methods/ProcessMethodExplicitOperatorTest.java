package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
}
