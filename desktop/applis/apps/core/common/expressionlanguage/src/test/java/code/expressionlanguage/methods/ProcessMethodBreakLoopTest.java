package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(29, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(30, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(30, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(29, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(29, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(457, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(467, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(467, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!isEmptyErrors(cont_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!isEmptyErrors(cont_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!isEmptyErrors(cont_));
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
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!isEmptyErrors(cont_));
    }
}
