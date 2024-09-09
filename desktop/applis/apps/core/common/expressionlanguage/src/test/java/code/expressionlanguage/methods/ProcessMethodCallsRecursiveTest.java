package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.IntStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodCallsRecursiveTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1013Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int factrec($int l){\n");
        xml_.append("  $if(l<=0){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return l*factrec(l-1i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("factrec", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        assertEq(120, getNumber(calculateNormalParam("pkg.Ex", id_, new IntStruct(5), cont_)));
    }

    @Test
    public void calculateArgument1015Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return badMethod();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int badMethod(){\n");
        xml_.append("  $return 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1016Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }


    @Test
    public void calculateArgument1017Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   simpleMethod();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int simpleMethod(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1018Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return betterMethod();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int betterMethod(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1019Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return badMethod();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int badMethod(){\n");
        xml_.append("  $return 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1020Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return badMethod();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e);\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int badMethod(){\n");
        xml_.append("  $return 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(2, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1022Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int calling(){\n");
        xml_.append("  emptymeth();\n");
        xml_.append("  $return 18i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void emptymeth(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("calling");
        assertEq(18, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1023Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(t%2==0){\n");
        xml_.append("   t+=8;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append("  $elseif(t<0){\n");
        xml_.append("   t+=2;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append("  $if(t%2==0){\n");
        xml_.append("   t+=8;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t+=2;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1024Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).exmeth()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(t%2==0){\n");
        xml_.append("   t+=8;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append("  $elseif(t<0){\n");
        xml_.append("   t+=2;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append("  $if(t%2==0){\n");
        xml_.append("   t+=8;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t+=2;\n");
        xml_.append("   $return 1i+$($int)t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(25, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

}
