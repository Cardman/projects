package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


public final class ProcessMethodCallsNoParamTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument1001Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculateArgument1002Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculateArgument1006Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(t < exmethlist()){\n");
        xml_.append("   t+=1;\n");
        xml_.append("  }\n");
        xml_.append("  $elseif(t < exmethlist() + 4){\n");
        xml_.append("   t+=2;\n");
        xml_.append("  }\n");
        xml_.append("  $elseif(t < exmethlist() + 7){\n");
        xml_.append("   t+=3;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t+=4;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethlist(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmethsec");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument1007Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return exmethsec();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String exmethsec(){\n");
        xml_.append("  $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+';'+st[1].toString()+';'+st.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:3,11:68\npkg.Ex.$static exmeth();pkg/Ex:6,31:151\npkg.Ex.$static exmethsec();2", getString(ret_));
    }
    @Test
    public void calculateArgument1008Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return exmethsec();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String exmethsec(){\n");
        xml_.append("  $final $stack[]\tst = $stack.current();\n");
        xml_.append("  $return st[0].toString()+';'+st[1].toString()+';'+st.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:3,11:68\npkg.Ex.$static exmeth();pkg/Ex:6,33:151\npkg.Ex.$static exmethsec();2", getString(ret_));
    }
    @Test
    public void calculateArgument1009Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $staticCall String exmeth(){\n");
        xml_.append("  $return exmethsec();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String exmethsec(){\n");
        xml_.append("  $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+';'+st[1].toString()+';'+st.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = new MethodId(MethodAccessKind.STATIC_CALL,"exmeth", new StringList());
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:3,11:72\npkg.Ex.$staticCall exmeth();pkg/Ex:6,31:155\npkg.Ex.$static exmethsec();2", getString(ret_));
    }
}
