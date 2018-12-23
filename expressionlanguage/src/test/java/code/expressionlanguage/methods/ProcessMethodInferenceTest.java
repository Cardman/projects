package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;

public final class ProcessMethodInferenceTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = {}:\n");
        xml_.append("  $if (arr;.length != 0i){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[])){\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = {1b}:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[])){\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[][] arr = {{}}:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[][])){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 0i){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class($int[])){\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[][] arr = {{2b}}:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class($int[])){\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  java.lang.Integer[][] arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 0i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package $final #T[][] array = {$new #T[]{}}:\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Number,java.lang.Integer> ex = $new ExTwo<java.lang.Number,java.lang.Integer>():\n");
        xml_.append("  java.lang.Number[][] arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Number[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 0i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T,#S:#T> {\n");
        xml_.append(" $package $final #T[][] array = {$new #S[]{}}:\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[][][] arr = {{{3b}}}:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 7i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[][][])){\n");
        xml_.append("   $return 6i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class($int[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i][0i]) != $class($int[])){\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = {{2i}}:\n");
        xml_.append("  java.lang.Integer[][] arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package #T[][] array = {$new #T[]{}}:\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = {{2i}}:\n");
        xml_.append("  java.lang.Integer[][] arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package #T[][] array = {$new #T[]{}}:\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>(), extwo = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = {{2i}}:\n");
        xml_.append("  java.lang.Integer[][] arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package #T[][] array = {$new #T[]{}}:\n");
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
        assertEq(0, ret_.getNumber());
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
        assertEq(0, ret_.getNumber());
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
        assertEq(0, ret_.getNumber());
    }

    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $int[] a:\n");
        xml_.append("  a;.=$new $int[2i]:\n");
        xml_.append("  a;.[0i]=8i:\n");
        xml_.append("  a;.[1i]=16i:\n");
        xml_.append("  $foreach($var i:a;.){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(24, ret_.getNumber());
    }
    @Test
    public void calculateArgument14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $int res:\n");
        xml_.append(" {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $foreach($var e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).getInstance());
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int[] arr(){\n");
        xml_.append("  $return {}:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = arr():\n");
        xml_.append("  $if (arr;.length != 0i){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[])){\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = ({}):\n");
        xml_.append("  $if (arr;.length != 0i){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[])){\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $foreach($int i:{8,16}){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(24, ret_.getNumber());
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>():\n");
        xml_.append("  ex;.array = {{2i}}:\n");
        xml_.append("  java.lang.Integer[][] arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package $final #T[][] array = {$new #T[]{}}:\n");
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
        xml_.append("  ex;.array = {{2i}}:\n");
        xml_.append("  java.lang.Integer[][] arr = ex;.array:\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 5i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i].length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr;.[0i][0i] != 2i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $package #T[][] array = {$new #T[]{}}:\n");
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
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var arr = {}:\n");
        xml_.append("  $if (arr;.length != 0i){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($int[])){\n");
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
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int arr = {}.length:\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<#U> :$iterable<#U>{\n");
        xml_.append(" $private #U[] list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $public (){\n");
        xml_.append("  list;;;=$new #U[0i]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(#U elt){\n");
        xml_.append("  add(length;;;,elt;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,#U elt){\n");
        xml_.append("  #U[] newlist=$new #U[length;;;+1i]:\n");
        xml_.append("  $iter($int i=0i:index;.;:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;]:\n");
        xml_.append("  }\n");
        xml_.append("  newlist;.[index;.;]=elt;.;:\n");
        xml_.append("  $iter($int i=index;.;+1i:length;;;+1i:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;-1i]:\n");
        xml_.append("  }\n");
        xml_.append("  length;;;++:\n");
        xml_.append("  list;;;=newlist;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #U get($int index){\n");
        xml_.append("  $return list;;;[index;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,#U elt){\n");
        xml_.append("  list;;;[index;.;]=elt;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;.;:length;;;-1i:1i){\n");
        xml_.append("   list;;;[i;]=list;;;[i;+1i]:\n");
        xml_.append("  }\n");
        xml_.append("  list;;;[length;;;-1i]=$null:\n");
        xml_.append("  length;;;--:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<#U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<#U>($this):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<#T> :$iterator<#T>{\n");
        xml_.append(" $private pkg.CustList<#T> list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $private $int index:\n");
        xml_.append(" $public (pkg.CustList<#T> i){\n");
        xml_.append("  list;;;=i;.;:\n");
        xml_.append("  length;;;=list;;;size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T next(){\n");
        xml_.append("  #T out=list;;;get(index;;;):\n");
        xml_.append("  index;;;++:\n");
        xml_.append("  $return out;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index;;;<length;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
