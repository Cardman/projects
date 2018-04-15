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
public final class ProcessMethodCallsNoParamTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument1001Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$class(\"$int\",t;.):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(9, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument1002Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$class(\"$int\",t;.):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument1003Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$class(\"$int\",t;.):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(20, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument1004Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$class(\"$int\",t;.):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(20, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument1005Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  code.expressionlanguage.classes.PickableList p=$new code.expressionlanguage.classes.PickableList():\n");
        xml_.append("  p;.getList().add(0):\n");
        xml_.append("  p;.getList().add(2):\n");
        xml_.append("  $while(p;.removeAndExistAfter(1i)){\n");
        xml_.append("   $continue:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$class(\"$int\",t;.)+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(21, (Number)ret_.getObject());
    }
}
