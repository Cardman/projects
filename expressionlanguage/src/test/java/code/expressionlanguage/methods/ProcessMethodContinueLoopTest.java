package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;

public final class ProcessMethodContinueLoopTest extends
        ProcessMethodCommon {

    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $for($int i=0i:4i::1i){\n");
        xml_.append("   $switch(i;){\n");
        xml_.append("    $case(1i){\n");
        xml_.append("     r;.+=1i:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $case(2i){\n");
        xml_.append("     r;.+=10i:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $default{\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(11, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $while(r;.<0){\n");
        xml_.append("   $switch(r;.){\n");
        xml_.append("    $case(1i){\n");
        xml_.append("     r;.+=1i:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $case(2i){\n");
        xml_.append("     r;.+=10i:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $default{\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $while(r;.<10){\n");
        xml_.append("   $switch(r;.){\n");
        xml_.append("    $case(1i){\n");
        xml_.append("     r;.+=1i:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $case(2i){\n");
        xml_.append("     r;.+=10i:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $default{\n");
        xml_.append("     r;.+=100i:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(100, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  i;.=0i:\n");
        xml_.append("  $while(i;.<10){\n");
        xml_.append("   $switch(i;.){\n");
        xml_.append("    $case(1i){\n");
        xml_.append("     r;.+=1i:\n");
        xml_.append("     i;.++:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $case(2i){\n");
        xml_.append("     r;.+=10i:\n");
        xml_.append("     i;.++:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $default{\n");
        xml_.append("     r;.+=100i:\n");
        xml_.append("     i;.++:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(811, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  i;.=0i:\n");
        xml_.append("  $do{\n");
        xml_.append("   $switch(i;.){\n");
        xml_.append("    $case(1i){\n");
        xml_.append("     r;.+=1i:\n");
        xml_.append("     i;.++:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $case(2i){\n");
        xml_.append("     r;.+=10i:\n");
        xml_.append("     i;.++:\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    $default{\n");
        xml_.append("     r;.+=100i:\n");
        xml_.append("     i;.++:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $while(i;.<9):\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(711, (Number)ret_.getObject());
    }


    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  $for($int i=0i:2i:1i){\n");
        xml_.append("   i;.=0i:\n");
        xml_.append("   $while(i;.<10){\n");
        xml_.append("    $switch(i;.){\n");
        xml_.append("     $case(1i){\n");
        xml_.append("      r;.+=1i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $case(2i){\n");
        xml_.append("      r;.+=10i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $default{\n");
        xml_.append("      r;.+=100i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("     }\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1622, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  $for($int i=0i:2i:1i){\n");
        xml_.append("   i;.=0i:\n");
        xml_.append("   $do{\n");
        xml_.append("    $switch(i;.){\n");
        xml_.append("     $case(1i){\n");
        xml_.append("      r;.+=1i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $case(2i){\n");
        xml_.append("      r;.+=10i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $default{\n");
        xml_.append("      r;.+=100i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("     }\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("   $while(i;.<9):\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1422, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  $if($true){\n");
        xml_.append("   i;.=0i:\n");
        xml_.append("   $while(i;.<10){\n");
        xml_.append("    $switch(i;.){\n");
        xml_.append("     $case(1i){\n");
        xml_.append("      r;.+=1i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $case(2i){\n");
        xml_.append("      r;.+=10i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $default{\n");
        xml_.append("      r;.+=100i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("     }\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(811, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  $if($true){\n");
        xml_.append("   i;.=0i:\n");
        xml_.append("   $do{\n");
        xml_.append("    $switch(i;.){\n");
        xml_.append("     $case(1i){\n");
        xml_.append("      r;.+=1i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $case(2i){\n");
        xml_.append("      r;.+=10i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     $default{\n");
        xml_.append("      r;.+=100i:\n");
        xml_.append("      i;.++:\n");
        xml_.append("     }\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("   $while(i;.<9):\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(711, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  i;.=0i:\n");
        xml_.append("  $while(i;.<10){\n");
        xml_.append("   $if(i;.=1i){\n");
        xml_.append("    r;.+=1i:\n");
        xml_.append("    i;.++:\n");
        xml_.append("    $continue:\n");
        xml_.append("   }\n");
        xml_.append("   $elseif(i;.=2i){\n");
        xml_.append("    r;.+=10i:\n");
        xml_.append("    i;.++:\n");
        xml_.append("    $continue:\n");
        xml_.append("   }\n");
        xml_.append("   $else{\n");
        xml_.append("    r;.+=100i:\n");
        xml_.append("    i;.++:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(811, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t:\n");
        xml_.append("  t;.=$null:\n");
        xml_.append("  $int r:\n");
        xml_.append("  r;.=0i:\n");
        xml_.append("  $int i:\n");
        xml_.append("  i;.=0i:\n");
        xml_.append("  $while(i;.<10){\n");
        xml_.append("   $if(i;.=1i){\n");
        xml_.append("    r;.+=1i:\n");
        xml_.append("    i;.++:\n");
        xml_.append("    $continue:\n");
        xml_.append("   }\n");
        xml_.append("   $elseif(i;.=2i){\n");
        xml_.append("    r;.+=10i:\n");
        xml_.append("    i;.++:\n");
        xml_.append("    $continue:\n");
        xml_.append("   }\n");
        xml_.append("   i;.++:\n");
        xml_.append("  }\n");
        xml_.append("  $return r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(11, (Number)ret_.getObject());
    }
}
