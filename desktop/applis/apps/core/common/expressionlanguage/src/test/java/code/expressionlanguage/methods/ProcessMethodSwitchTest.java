package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodSwitchTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(1/0){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=\"10\";\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(cont_.getStandards().getContent().getCoreNames().getAliasDivisionZero(), calculateError("pkg.Ex",id_,cont_).getClassName(cont_));
    }
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case \"8\";\n");
        xml_.append("    t=\"10\";\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("110", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case \"8\"?:t==\"8\";\n");
        xml_.append("    t=\"10\";\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("110", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument2_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8?:t==8;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument_1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case \"8\"?:t==\"9\";\n");
        xml_.append("    t=\"10\";\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("18", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument_2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8?:t==9;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate_Argument1_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case \"8\"?:t==\"8\";\n");
        xml_.append("    t=\"10\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case \"8\"?:;\n");
        xml_.append("    t=\"101\";\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("110", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate_Argument2_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8?:t==8;\n");
        xml_.append("    t=10;\n");
        xml_.append("    $break;\n");
        xml_.append("   $case 8?:;\n");
        xml_.append("    t=101;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate_Argument_1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case \"8\"?:t==\"9\";\n");
        xml_.append("    t=\"10\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case \"8\"?:;\n");
        xml_.append("    t=\"101\";\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("1101", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate_Argument_2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8?:t==9;\n");
        xml_.append("    t=10;\n");
        xml_.append("    $break;\n");
        xml_.append("   $case 8?:;\n");
        xml_.append("    t=101;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(102, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(13, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }


    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(13, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=9;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=9;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $case 9;\n");
        xml_.append("    t=21;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(22, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=9;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $case 9;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }


    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $case 1;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $case 7;\n");
        xml_.append("   $case 1;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=9;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(13, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=9;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $default;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }


    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(13, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $default;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $return 10i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $default;\n");
        xml_.append("   $case $null;\n");
        xml_.append("    r=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 0i;\n");
        xml_.append("   $case $null;\n");
        xml_.append("    r=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    r=1i;\n");
        xml_.append("   $case 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $switch(t);\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $return 0i;\n");
        xml_.append("   $default;\n");
        xml_.append("    $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $return 0i;\n");
        xml_.append("   $default;\n");
        xml_.append("    $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $return 0i;\n");
        xml_.append("   $case 9;\n");
        xml_.append("   $default;\n");
        xml_.append("    $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t+=8;\n");
        xml_.append("   $case 9;\n");
        xml_.append("   $default;\n");
        xml_.append("    $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t)label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=1;\n");
        xml_.append("  $switch(t)label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $if(t==8i){\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    i=16;\n");
        xml_.append("   $default;\n");
        xml_.append("    i=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=1;\n");
        xml_.append("  $switch(t)label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $if(t==8i){\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    i=16;\n");
        xml_.append("   $default;\n");
        xml_.append("    i=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(13, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $long u;\n");
        xml_.append("  u=8;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=1;\n");
        xml_.append("  $switch(t)label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $switch(u)labeltwo{\n");
        xml_.append("     $case 10;\n");
        xml_.append("     $case 8;\n");
        xml_.append("      $if(u==10i){\n");
        xml_.append("       $break labeltwo;\n");
        xml_.append("      }\n");
        xml_.append("      $if(t==8i){\n");
        xml_.append("       $break label;\n");
        xml_.append("      }\n");
        xml_.append("      i=26;\n");
        xml_.append("     $default;\n");
        xml_.append("      i=22;\n");
        xml_.append("    }\n");
        xml_.append("    $if(t==8i){\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    i=16;\n");
        xml_.append("   $default;\n");
        xml_.append("    i=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $long u;\n");
        xml_.append("  u=10;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=1;\n");
        xml_.append("  $switch(t)label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    $switch(u)labeltwo{\n");
        xml_.append("     $case 10;\n");
        xml_.append("     $case 8;\n");
        xml_.append("      $if(u==10i){\n");
        xml_.append("       $break labeltwo;\n");
        xml_.append("      }\n");
        xml_.append("      $if(t==8i){\n");
        xml_.append("       $break label;\n");
        xml_.append("      }\n");
        xml_.append("      i=26;\n");
        xml_.append("     $default;\n");
        xml_.append("      i=22;\n");
        xml_.append("    }\n");
        xml_.append("    i=16;\n");
        xml_.append("    $if(t==10i){\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("   $default;\n");
        xml_.append("    i=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $final $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=8;\n");
        xml_.append("  $switch(i)label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $final $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=9;\n");
        xml_.append("  $switch(i)label{\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("    $break label;\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(13, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=9;\n");
        xml_.append("  $switch(i)label{\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument77_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=8;\n");
        xml_.append("  $switch(i)label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12/0;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument_77_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=8;\n");
        xml_.append("  $switch(i){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12/0;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmethCall(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=9;\n");
        xml_.append("  $switch(exmethCall())label{\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmethCall(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  $long p;\n");
        xml_.append("  i=9;\n");
        xml_.append("  $switch(exmethCall())label{\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("  }\n");
        xml_.append("  p = 5;\n");
        xml_.append("  $return 1i+$($int)t+$($int)p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(22, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmethCall(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t=14;\n");
        xml_.append("  $long i;\n");
        xml_.append("  $long p;\n");
        xml_.append("  i=9;\n");
        xml_.append("  $switch(exmethCall())label{\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("  }\n");
        xml_.append("  p = 5;\n");
        xml_.append("  $return 1i+$($int)t+$($int)p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(20, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmethCall(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t=14;\n");
        xml_.append("  $long i;\n");
        xml_.append("  $long p;\n");
        xml_.append("  i=9;\n");
        xml_.append("  $switch(exmethCall()){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("  }\n");
        xml_.append("  p = 5;\n");
        xml_.append("  $return 1i+$($int)t+$($int)p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(20, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=2i;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    r=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=2i;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    r=1i;\n");
        xml_.append("   $case 10;\n");
        xml_.append("    r=3i;\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(3, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int o=0;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    o=11;\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    o=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(8){\n");
        xml_.append("   $case $var i:i==8;\n");
        xml_.append("    t=\"10\";\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("10", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t=$switch(8){\n");
        xml_.append("   $case $var i:i==8;\n");
        xml_.append("    $return\"10\";\n");
        xml_.append("   $default i;\n");
        xml_.append("    $return\"8\";\n");
        xml_.append("  };\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("10", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t=$switch(9){\n");
        xml_.append("   $case $var i:i==8;\n");
        xml_.append("    $return\"10\";\n");
        xml_.append("   $default i;\n");
        xml_.append("    $return i+\"8\";\n");
        xml_.append("  };\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("98", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo t;\n");
        xml_.append("  t=ExTwo.ONE;\n");
        xml_.append("  $int o=12;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    o=11;\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE?:t==ExTwo.TWO;\n");
        xml_.append("    o=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(12, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo t;\n");
        xml_.append("  t=ExTwo.ONE;\n");
        xml_.append("  $int o=12;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    o=11;\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE?:t==ExTwo.ONE;\n");
        xml_.append("    o=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo t;\n");
        xml_.append("  t=ExTwo.ONE;\n");
        xml_.append("  $int o=12;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    o=11;\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE?:t==v();\n");
        xml_.append("    o=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo v(){\n");
        xml_.append("  $return ExTwo.TWO;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(12, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo t;\n");
        xml_.append("  t=ExTwo.ONE;\n");
        xml_.append("  $int o=12;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null;\n");
        xml_.append("    o=11;\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE?:t==v();\n");
        xml_.append("    o=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo v(){\n");
        xml_.append("  $return ExTwo.ONE;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
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
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $case \"8\";\n");
        xml_.append("   t=\"10\";\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument4_FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $case \"8\";\n");
        xml_.append("   t=\"10\";\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $default;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $default;\n");
        xml_.append("   t=\"10\";\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ExTwo.ONE;\n");
        xml_.append("   $case ExTwo.ONE;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex1:ExSup1:ExSup2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Ex2:ExSup1:ExSup2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExSup1 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExSup2 {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch($bool(t>0,(Ex1)$null,(Ex2)$null)){\n");
        xml_.append("   $case 0;\n");
        xml_.append("   $case 1;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(m()){\n");
        xml_.append("   $case 0;\n");
        xml_.append("   $case 0;\n");
        xml_.append("   $case 1;\n");
        xml_.append("    t=10;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append(" $static $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 0;\n");
        xml_.append("  $switch(2){\n");
        xml_.append("   $case 0;\n");
        xml_.append("   $case 1;\n");
        xml_.append("    t=10;\n");
        xml_.append("    $break;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 0;\n");
        xml_.append("  $switch(\"\"){\n");
        xml_.append("   $case 0;\n");
        xml_.append("   $case n();\n");
        xml_.append("   $case m();\n");
        xml_.append("   $case 1;\n");
        xml_.append("    t=10;\n");
        xml_.append("    $break;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append(" $static $int m(){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $static $void n(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
