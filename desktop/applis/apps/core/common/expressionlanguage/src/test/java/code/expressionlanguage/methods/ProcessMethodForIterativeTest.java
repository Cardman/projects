package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


public final class ProcessMethodForIterativeTest extends ProcessMethodCommon {


    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=4i;0i;-1i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=4i;1i;;-1i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=0i;4i;;1i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=4i;1i;;-2i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=1i;4i;;2i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=4i;1i;;2i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=1i;4i;;-2i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=1i;1i;0i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=1i;4i;1i);\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=0i;4i;;1i){\n");
        xml_.append("   $iter($int j=0i;4i;;i){\n");
        xml_.append("    t+=j;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
    }
}
