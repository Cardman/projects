package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodRefLoopTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument_0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $return arr[0];\n");
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
    public void calculateArgument_2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $return arr[1];\n");
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
    public void calculateArgument_3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $return arr[2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument_4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $return arr[3];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument_5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $return arr.length==4&&arr[0]==1&&arr[1]==2&&arr[2]==3&&arr[3]==4;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isTrue(ret_.getStruct()));
    }
    @Test
    public void calculateArgument_6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int[] arr = {1,2,3,4};\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e *= 2;\n");
        xml_.append("  }\n");
        xml_.append("  $return arr.length==4&&arr[0]==2&&arr[1]==4&&arr[2]==6&&arr[3]==8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isTrue(ret_.getStruct()));
    }
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $int s = 0;\n");
        xml_.append("  $for ($int e : arr){\n");
        xml_.append("   $if (([e]) + 1 != e) {\n");
        xml_.append("    $return -1;\n");
        xml_.append("   }\n");
        xml_.append("   s += e;\n");
        xml_.append("  }\n");
        xml_.append("  $return s;\n");
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
    public void calculateArgument00Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $int s = 0;\n");
        xml_.append("  $for ($int e : arr){\n");
        xml_.append("   $if (([e]) + 1 != e) {\n");
        xml_.append("   }\n");
        xml_.append("   s += e;\n");
        xml_.append("  }\n");
        xml_.append("  $return arr.length==4&&arr[0]==1&&arr[1]==2&&arr[2]==3&&arr[3]==4;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isTrue(ret_.getStruct()));
    }
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void exmeth(){\n");
        xml_.append("  $int[] arr = $null;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = 0;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.util.exceptions.NullObjectException", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $int s = 0;\n");
        xml_.append("  $for ($int e : arr){\n");
        xml_.append("   $if (([e]) + 1 != e) {\n");
        xml_.append("    $return -1;\n");
        xml_.append("   }\n");
        xml_.append("   s += e;\n");
        xml_.append("  }\n");
        xml_.append("  $return s;\n");
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
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int v = 0;\n");
        xml_.append("  $int s = 0;\n");
        xml_.append("  $for ($that $int e = $that(v); e < 4;e++){\n");
        xml_.append("    s += e;\n");
        xml_.append("  }\n");
        xml_.append("  $if (v != 4) {\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $return s;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $foreach ($that $int e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $int s = 0;\n");
        xml_.append("  $for ($int e : arr){\n");
        xml_.append("   $if (([e]) + 1 != e) {\n");
        xml_.append("    $return -1;\n");
        xml_.append("   }\n");
        xml_.append("   s += e;\n");
        xml_.append("  }\n");
        xml_.append("  $return s;\n");
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
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int v = 0;\n");
        xml_.append("  $int s = 0;\n");
        xml_.append("  $for ($that $int e = $that(v); e < 4;e++){\n");
        xml_.append("    s += ([e]);\n");
        xml_.append("  }\n");
        xml_.append("  $if (v != 4) {\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $return s;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int v = 0;\n");
        xml_.append("  $int s = 0;\n");
        xml_.append("  $for ($that $var e = $that(v); e < 4;e++){\n");
        xml_.append("    s += e;\n");
        xml_.append("  }\n");
        xml_.append("  $if (v != 4) {\n");
        xml_.append("   $return -1;\n");
        xml_.append("  }\n");
        xml_.append("  $return s;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgumentFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] arr = {0,0,0,0};\n");
        xml_.append("  $int v = 1;\n");
        xml_.append("  $for ($that Object;;){\n");
        xml_.append("    v++;\n");
        xml_.append("  }\n");
        xml_.append("  $for ($that){\n");
        xml_.append("    v++;\n");
        xml_.append("  }\n");
        xml_.append("  $for ($that Object e : arr){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $for ($that Object e,Object f : $new $iterableTable<$int,$int>(){}){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $for ($that Object e: $new $iterable<$int>(){}){\n");
        xml_.append("    e = v++;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
        ForMutableIterativeLoop.nullToErr(null);
    }
}
