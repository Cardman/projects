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
public final class ProcessMethodForeachArrayTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $int[] a:\n");
        xml_.append("  a;.=$new $int[2i]:\n");
        xml_.append("  a;.[0i]=8i:\n");
        xml_.append("  a;.[1i]=16i:\n");
        xml_.append("  $foreach($int i:a;.){\n");
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
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $int[] a:\n");
        xml_.append("  a;.=$new $int[0i]:\n");
        xml_.append("  $foreach($int i:a;.){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
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
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $int[] a:\n");
        xml_.append("  a;.=$new $int[2i]:\n");
        xml_.append("  a;.[0i]=8b:\n");
        xml_.append("  a;.[1i]=16b:\n");
        xml_.append("  $foreach($int i:a;.){\n");
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
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  java.lang.Integer[] a:\n");
        xml_.append("  a;.=$new java.lang.Integer[4i]:\n");
        xml_.append("  a;.[0i]=$null:\n");
        xml_.append("  a;.[1i]=1i:\n");
        xml_.append("  a;.[2i]=$null:\n");
        xml_.append("  a;.[3i]=$null:\n");
        xml_.append("  $foreach(java.lang.Integer i:a;.){\n");
        xml_.append("   $if(i;==$null){\n");
        xml_.append("    t;.+=i;;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
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
        assertEq(5, ret_.getNumber());
    }

    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $int[] a:\n");
        xml_.append("  a;.=$new $int[2i]:\n");
        xml_.append("  a;.[0i]=8i:\n");
        xml_.append("  a;.[0i]+=6i:\n");
        xml_.append("  a;.[1i]=16i:\n");
        xml_.append("  $foreach($int i:a;.){\n");
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
        assertEq(30, ret_.getNumber());
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  java.lang.Integer[] a:\n");
        xml_.append("  a;.=$new java.lang.Integer[4i]:\n");
        xml_.append("  a;.[0i]=$null:\n");
        xml_.append("  a;.[1i]=1i:\n");
        xml_.append("  a;.[2i]=$null:\n");
        xml_.append("  a;.[3i]=$null:\n");
        xml_.append("  $foreach(java.lang.Integer i:a;.){\n");
        xml_.append("   $if(i;==$null){\n");
        xml_.append("    t;.+=([i]):\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
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
        assertEq(5, ret_.getNumber());
    }
}
