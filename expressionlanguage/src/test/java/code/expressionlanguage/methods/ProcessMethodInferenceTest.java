package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;

public final class ProcessMethodInferenceTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  [$int arr = $new [[]():\n");
        xml_.append("  $if (arr;.length != 0i){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([$int)){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  [$int arr = $new [[](1b):\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([$int)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i] != 1b){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  [[$int arr = $new [[]($new [[]()):\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[$int)){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 0i){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([$int)){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  [[$int arr = $new [[]($new [[](2b)):\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[$int)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([$int)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2b){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  [[java.lang.Integer arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[java.lang.Integer)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 0i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package $final [[#T array = $new [[]($new [#T[]()):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Number,java.lang.Integer> ex = $new ExTwo<java.lang.Number,java.lang.Integer>():\n");
        xml_.append("  [[java.lang.Number arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[java.lang.Number)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 0i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T,#S:#T> {\n");
        xml_.append(" $package $final [[#T array = $new [[]($new [#S[]()):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  [[[$int arr = $new [[]($new [[]($new [[](3b))):\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 7i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[[$int)){\n");
        xml_.append("   $return 6i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([[$int)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i][0i]) != $class([$int)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i][0i] != 3b){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = $new [[]($new [[](2i)):\n");
        xml_.append("  [[java.lang.Integer arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[java.lang.Integer)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package [[#T array = $new [[]($new [#T[]()):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = $new [[]($new [[](2i)):\n");
        xml_.append("  [[java.lang.Integer arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[java.lang.Integer)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package [[#T array = $new [[]($new [#T[]()):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>(), extwo = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = $new [[]($new [[](2i)):\n");
        xml_.append("  [[java.lang.Integer arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[java.lang.Integer)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package [[#T array = $new [[]($new [#T[]()):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i:\n");
        xml_.append("  $for($var i=0i:i;<=4i:i;++){\n");
        xml_.append("   res;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $if (res;. != 10i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i:\n");
        xml_.append("  $int j = 0i:\n");
        xml_.append("  $for(:j;.<=4i:j;.++){\n");
        xml_.append("   res;.+=j;.:\n");
        xml_.append("  }\n");
        xml_.append("  $if (res;. != 10i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = $new [[]($new [[](2i)):\n");
        xml_.append("  [[java.lang.Integer arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[java.lang.Integer)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package $final [[#T array = $new [[]($new [#T[]()):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var extwo:\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = $new [[]($new [[](2i)):\n");
        xml_.append("  [[java.lang.Integer arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class([[java.lang.Integer)){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class([java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package [[#T array = $new [[]($new [#T[]()):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i:\n");
        xml_.append("  $int j = 0i:\n");
        xml_.append("  $for($var i:j;.<=4i:j;.++){\n");
        xml_.append("   res;.+=j;.:\n");
        xml_.append("  }\n");
        xml_.append("  $if (res;. != 10i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }

}
