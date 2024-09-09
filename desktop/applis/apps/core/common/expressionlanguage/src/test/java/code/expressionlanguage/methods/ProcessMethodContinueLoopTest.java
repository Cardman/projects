package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodContinueLoopTest extends
        ProcessMethodCommon {

    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $iter($int i=0i;4i;;1i){\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     $continue;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument481Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $iter($int i=0i;4i;;1i){\n");
        xml_.append("   $try{\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     $continue;\n");
        xml_.append("   }\n");
        xml_.append("   }$catch{\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $while(r<0){\n");
        xml_.append("   $switch(r){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     $continue;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $while(r<10){\n");
        xml_.append("   $switch(r){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     r+=100i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(100, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while(i<10){\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     r+=100i;\n");
        xml_.append("     i++;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(811, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     r+=100i;\n");
        xml_.append("     i++;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<9);\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(711, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }


    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $iter($int j=0i;2i;1i){\n");
        xml_.append("   i=0i;\n");
        xml_.append("   $while(i<10){\n");
        xml_.append("    $switch(i){\n");
        xml_.append("     $case 1i;\n");
        xml_.append("      r+=1i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $case 2i;\n");
        xml_.append("      r+=10i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $default;\n");
        xml_.append("      r+=100i;\n");
        xml_.append("      i++;\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1622, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $iter($int j=0i;2i;1i){\n");
        xml_.append("   i=0i;\n");
        xml_.append("   $do{\n");
        xml_.append("    $switch(i){\n");
        xml_.append("     $case 1i;\n");
        xml_.append("      r+=1i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $case 2i;\n");
        xml_.append("      r+=10i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $default;\n");
        xml_.append("      r+=100i;\n");
        xml_.append("      i++;\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("   $while(i<9);\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(1422, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $if($true){\n");
        xml_.append("   i=0i;\n");
        xml_.append("   $while(i<10){\n");
        xml_.append("    $switch(i){\n");
        xml_.append("     $case 1i;\n");
        xml_.append("      r+=1i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $case 2i;\n");
        xml_.append("      r+=10i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $default;\n");
        xml_.append("      r+=100i;\n");
        xml_.append("      i++;\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(811, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $if($true){\n");
        xml_.append("   i=0i;\n");
        xml_.append("   $do{\n");
        xml_.append("    $switch(i){\n");
        xml_.append("     $case 1i;\n");
        xml_.append("      r+=1i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $case 2i;\n");
        xml_.append("      r+=10i;\n");
        xml_.append("      i++;\n");
        xml_.append("      $continue;\n");
        xml_.append("     $default;\n");
        xml_.append("      r+=100i;\n");
        xml_.append("      i++;\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("   $while(i<9);\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(711, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while(i<10){\n");
        xml_.append("   $if(i==1i){\n");
        xml_.append("    r+=1i;\n");
        xml_.append("    i++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   $elseif(i==2i){\n");
        xml_.append("    r+=10i;\n");
        xml_.append("    i++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   $else{\n");
        xml_.append("    r+=100i;\n");
        xml_.append("    i++;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(811, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while(i<10){\n");
        xml_.append("   $if(i==1i){\n");
        xml_.append("    r+=1i;\n");
        xml_.append("    i++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   $elseif(i==2i){\n");
        xml_.append("    r+=10i;\n");
        xml_.append("    i++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $iter($int i=0i;4i;1i)label{\n");
        xml_.append("   $iter($int j=0i;4i;1i)labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     $continue label;\n");
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
    public void calculateArgument60Test() {
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
        xml_.append("     i++;");
        xml_.append("     $continue label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++;");
        xml_.append("   }\n");
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
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     r+=100i;\n");
        xml_.append("     i++;\n");
        xml_.append("   }\n");
        xml_.append("   $for($int ik=0;;){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<9);\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(711, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   $for($int ik=0;ik<1;){\n");
        xml_.append("    ik++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     r+=100i;\n");
        xml_.append("     i++;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<9);\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(711, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument61FailTest() {
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
        xml_.append("     $continue label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++:");
        xml_.append("   }\n");
        xml_.append("   i++:");
        xml_.append("  }\n");
        xml_.append("  $return o+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument62FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int o;\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $int j=0i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $return o+t;\n");
        xml_.append("  $while($false)label{\n");
        xml_.append("   j=0i;\n");
        xml_.append("   $while($false)labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     $continue label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++:");
        xml_.append("   }\n");
        xml_.append("   i++:");
        xml_.append("  }\n");
        xml_.append("  $return o+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument63FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int o;\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $int j=0i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $return o+t;\n");
        xml_.append("  $do two{\n");
        xml_.append("   t=0i;\n");
        xml_.append("  }\n");
        xml_.append("  $while($false){\n");
        xml_.append("   j=0i;\n");
        xml_.append("   $while($false)labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     $continue label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++:");
        xml_.append("   }\n");
        xml_.append("   i++:");
        xml_.append("  }\n");
        xml_.append("  $return o+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument64FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int o;\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $int j=0i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $while($false){\n");
        xml_.append("   j=0i;\n");
        xml_.append("   $while($false)labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     $continue label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++:");
        xml_.append("   }\n");
        xml_.append("   i++:");
        xml_.append("  }\n");
        xml_.append("  $return o+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument65FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $return 0i;\n");
        xml_.append("  $do t,y{\n");
        xml_.append("   $for($int ik=0;ik<1;){\n");
        xml_.append("    ik++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     r+=100i;\n");
        xml_.append("     i++;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<9):\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument66FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $return 0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   $switch(i){\n");
        xml_.append("    $case 1i;\n");
        xml_.append("     r+=1i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $case 2i;\n");
        xml_.append("     r+=10i;\n");
        xml_.append("     i++;\n");
        xml_.append("     $continue;\n");
        xml_.append("    $default;\n");
        xml_.append("     r+=100i;\n");
        xml_.append("     i++;\n");
        xml_.append("   }\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<9);\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument67FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do du{\n");
        xml_.append("   $for($int ik=0;ik<1;)du{\n");
        xml_.append("    ik++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<9);\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument68FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $iter($int $ik=0;1;1) {\n");
        xml_.append("   $for($int $ik=0;ik<1;){\n");
        xml_.append("    ik++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument69FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $for($int $ik=0;1;1) {\n");
        xml_.append("   $iter($int $ik=0;ik<1;1){\n");
        xml_.append("    ik++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument70FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $int r;\n");
        xml_.append("  r=0i;\n");
        xml_.append("  $int i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $return r;\n");
        xml_.append("  $iter($int $ik=0;1;1) {\n");
        xml_.append("   $iter[String](String $ik=0;1;1){\n");
        xml_.append("    ik++;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return r;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument71FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(){\n");
        xml_.append("  $continue;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
