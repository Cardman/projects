package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodBreakLoopTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=0i;4i;1i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(23, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument300Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=0i;4i;1i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $try{\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   }$catch{\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(23, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(23, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(23, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $return t;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(23, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==3){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    i+=1i;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(23, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(29, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $if(i>10i){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $return p;\n");
        xml_.append("  } $elseif(i>1i){\n");
        xml_.append("    p=10i;\n");
        xml_.append("    $return p;\n");
        xml_.append("  }\n");
        xml_.append("  $while($true){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=7i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(30, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $if(i>10i){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $return p;\n");
        xml_.append("  } $elseif(i>1i){\n");
        xml_.append("    p=10i;\n");
        xml_.append("    $return p;\n");
        xml_.append("  }\n");
        xml_.append("  $while($true){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=7i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   $if(i==5){\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(30, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  } $while($true);\n");
        xml_.append("  $return t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(29, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   $if(i==5){\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  } $while($true);\n");
        xml_.append("  $return t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(29, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=0i;4i;1i)label{\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(23, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=0i;4i;1i)label{\n");
        xml_.append("   $iter($int j=0i;4i;1i)labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     $break labeltwo;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(457, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int o;\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $int j=0i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $while($true)label{\n");
        xml_.append("   j=0i;\n");
        xml_.append("   $while($true)labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     $break labeltwo;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++;");
        xml_.append("   }\n");
        xml_.append("   i++;");
        xml_.append("  }\n");
        xml_.append("  $return o+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(467, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int o;\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $int j=0i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $while($true)label{\n");
        xml_.append("   j=0i;\n");
        xml_.append("   $do labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     $break labeltwo;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++;");
        xml_.append("   }$while($true);\n");
        xml_.append("   i++;");
        xml_.append("  }\n");
        xml_.append("  $return o+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(467, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument44FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $switch($true)label{\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $switch($true)label{\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  ([p])++;\n");
        xml_.append("  $do{\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
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
        xml_.append(" $public $static $void catching(){\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
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
        xml_.append(" $public $static $void catching(){\n");
        xml_.append("  $break;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
