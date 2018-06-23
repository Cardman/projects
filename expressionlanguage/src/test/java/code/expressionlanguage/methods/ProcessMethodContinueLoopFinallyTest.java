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
public final class ProcessMethodContinueLoopFinallyTest extends
        ProcessMethodCommon {

    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=0i:4i:1i){\n");
        xml_.append("   $try{\n");
        xml_.append("    t;.+=1i:\n");
        xml_.append("    $if(i;%2=0){\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    t;.+=10i:\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    t;.+=100i:\n");
        xml_.append("   }\n");
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
        assertEq(424, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=0i:4i:1i){\n");
        xml_.append("   $try{\n");
        xml_.append("    t;.+=1i:\n");
        xml_.append("    $if(i;%2=0){\n");
        xml_.append("     $continue:\n");
        xml_.append("    }\n");
        xml_.append("    t;.+=10i:\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    t;.+=100i:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=1000i:\n");
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
        assertEq(2424, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=0i:4i:1i){\n");
        xml_.append("   $try{\n");
        xml_.append("    $try{\n");
        xml_.append("     t;.+=1i:\n");
        xml_.append("     $if(i;%2=0){\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     t;.+=10i:\n");
        xml_.append("    }\n");
        xml_.append("    $finally{\n");
        xml_.append("     t;.+=100i:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    t;.+=1000i:\n");
        xml_.append("   }\n");
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
        assertEq(4424, (Number)ret_.getObject());
    }


    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=0i:4i:1i){\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $try{\n");
        xml_.append("     t;.+=1i:\n");
        xml_.append("     $if(i;%2=0){\n");
        xml_.append("      $continue:\n");
        xml_.append("     }\n");
        xml_.append("     t;.+=10i:\n");
        xml_.append("    }\n");
        xml_.append("    $finally{\n");
        xml_.append("     t;.+=100i:\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
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
        assertEq(424, (Number)ret_.getObject());
    }
}
