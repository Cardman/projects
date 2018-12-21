package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public final class ProcessMethodSwitchTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t:\n");
        xml_.append("  t;.=\"8\":\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(\"8\"){\n");
        xml_.append("    t;.=\"10\":\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("110", ret_.getString());
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=10:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(11, ret_.getNumber());
    }

    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=10:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(13, ret_.getNumber());
    }


    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(13, ret_.getNumber());
    }

    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }

    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $case(9){\n");
        xml_.append("    t;.=21:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(22, ret_.getNumber());
    }

    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $case(9):\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }


    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $case(1):\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }

    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $case(7):\n");
        xml_.append("   $case(1):\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }

    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(13, ret_.getNumber());
    }

    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }


    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(13, ret_.getNumber());
    }

    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }

    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("    $break:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }

    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $return 10i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(0i):\n");
        xml_.append("   $case($null){\n");
        xml_.append("    r;.=1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
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
        assertEq(1, ret_.getNumber());
    }

    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case($null){\n");
        xml_.append("    r;.=1i:\n");
        xml_.append("   }\n");
        xml_.append("   $case(0i):\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
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
        assertEq(1, ret_.getNumber());
    }

    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $switch(t;.):\n");
        xml_.append("  $return r;.:\n");
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
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $return 0i:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    $return 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=10:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $return 0i:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    $return 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=10:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $return 0i:\n");
        xml_.append("   }\n");
        xml_.append("   $case(9):\n");
        xml_.append("   $default{\n");
        xml_.append("    $return 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.+=8:\n");
        xml_.append("   }\n");
        xml_.append("   $case(9):\n");
        xml_.append("   $default{\n");
        xml_.append("    $return 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.)label{\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("    $break label:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=1:\n");
        xml_.append("  $switch(t;.)label{\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $if(t;.==8i){\n");
        xml_.append("     $break label:\n");
        xml_.append("    }\n");
        xml_.append("    i;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    i;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=10:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=1:\n");
        xml_.append("  $switch(t;.)label{\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $if(t;.==8i){\n");
        xml_.append("     $break label:\n");
        xml_.append("    }\n");
        xml_.append("    i;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    i;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(13, ret_.getNumber());
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $long u:\n");
        xml_.append("  u;.=8:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=1:\n");
        xml_.append("  $switch(t;.)label{\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $switch(u;.)labeltwo{\n");
        xml_.append("     $case(10):\n");
        xml_.append("     $case(8){\n");
        xml_.append("      $if(u;.==10i){\n");
        xml_.append("       $break labeltwo:\n");
        xml_.append("      }\n");
        xml_.append("      $if(t;.==8i){\n");
        xml_.append("       $break label:\n");
        xml_.append("      }\n");
        xml_.append("      i;.=26:\n");
        xml_.append("     }\n");
        xml_.append("     $default{\n");
        xml_.append("      i;.=22:\n");
        xml_.append("     }\n");
        xml_.append("    }\n");
        xml_.append("    $if(t;.==8i){\n");
        xml_.append("     $break label:\n");
        xml_.append("    }\n");
        xml_.append("    i;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    i;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=10:\n");
        xml_.append("  $long u:\n");
        xml_.append("  u;.=10:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=1:\n");
        xml_.append("  $switch(t;.)label{\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    $switch(u;.)labeltwo{\n");
        xml_.append("     $case(10):\n");
        xml_.append("     $case(8){\n");
        xml_.append("      $if(u;.==10i){\n");
        xml_.append("       $break labeltwo:\n");
        xml_.append("      }\n");
        xml_.append("      $if(t;.==8i){\n");
        xml_.append("       $break label:\n");
        xml_.append("      }\n");
        xml_.append("      i;.=26:\n");
        xml_.append("     }\n");
        xml_.append("     $default{\n");
        xml_.append("      i;.=22:\n");
        xml_.append("     }\n");
        xml_.append("    }\n");
        xml_.append("    i;.=16:\n");
        xml_.append("    $if(t;.==10i){\n");
        xml_.append("     $break label:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    i;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)i;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $final $long t:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=8:\n");
        xml_.append("  $switch(i;.)label{\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("    $break label:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $final $long t:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=9:\n");
        xml_.append("  $switch(i;.)label{\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("    $break label:\n");
        xml_.append("   }\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("    $break label:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(13, ret_.getNumber());
    }
    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=9:\n");
        xml_.append("  $switch(i;.)label{\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("    $break label:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(17, ret_.getNumber());
    }
    @Test
    public void calculateArgumentFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $final $long t:\n");
        xml_.append("  $long i:\n");
        xml_.append("  i;.=9:\n");
        xml_.append("  $switch(i;.)label{\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("    $break label:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=10:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(8):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=10:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t:\n");
        xml_.append("  t;.=\"8\":\n");
        xml_.append("  $case(\"8\"){\n");
        xml_.append("   t;.=\"10\":\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $default:\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=10:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t:\n");
        xml_.append("  t;.=\"8\":\n");
        xml_.append("  $default{\n");
        xml_.append("   t;.=\"10\":\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
}
