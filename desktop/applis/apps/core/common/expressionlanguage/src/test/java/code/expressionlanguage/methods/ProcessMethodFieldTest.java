package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.IntStruct;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodFieldTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1025Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1026Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1027Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=1000i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(1110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1028Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=1000i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(1110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1029Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append("  $if($true){\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=1000i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(1110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1030Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   myf+=100i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=1000i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(1210, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1031Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   myf+=100i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=1000i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(1210, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1032Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
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
    public void calculateArgument1033Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(code.expressionlanguage.exceptions.BadIndexException e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
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
    public void calculateArgument1033_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(code.expressionlanguage.exceptions.BadIndexException e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int badMethod(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }$catch{\n");
        xml_.append("   $return 1;\n");
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
    public void calculateArgument1034Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(java.lang.Exception e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    badMethod();\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
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
    public void calculateArgument1035Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(java.lang.Exception e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    badMethod();\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    t=10i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
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
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1036Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(java.lang.Exception e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    badMethod();\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    t=10i;\n");
        xml_.append("    badMethod();\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
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
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1037Test() {
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
        ContextEl cont_ = contextElDefault(files_, 12);
        MethodId id_ = getMethodId("factrec", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        assertEq(39916800, getNumber(calculateNormalParam("pkg.Ex", id_, new IntStruct(11), cont_)));
    }

    @Test
    public void calculateArgument1038Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(java.lang.Exception e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
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
    public void calculateArgument1039Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return badMethod();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(java.lang.Exception e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    $throw exception();\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   $return 10i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int badMethod(){\n");
        xml_.append("  $return 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.Object exception(){\n");
        xml_.append("  $return $new java.lang.String();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(11, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1040Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1041Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1042Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        xml_.append(" $static{\n");
        xml_.append("  $try{\n");
        xml_.append("   myf+=100i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   myf+=1000i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(1110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1043Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        xml_.append(" $static{\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    myf+=100i;\n");
        xml_.append("    $return;\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    myf+=1000i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   myf+=10000i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(11110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1044Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int new=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return new;\n");
        xml_.append(" }\n");
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
        xml_.append(" $static{\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    new+=100i;\n");
        xml_.append("    $return;\n");
        xml_.append("   }\n");
        xml_.append("   $finally{\n");
        xml_.append("    new+=1000i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   new+=10000i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(11110, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1045Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int factrec($int l){\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(l<=0){\n");
        xml_.append("    $return 1i;\n");
        xml_.append("   }\n");
        xml_.append("   $return l*factrec(l-1i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.StackOverFlow e){\n");
        xml_.append("   $return -1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElDefault(files_, 2);
        MethodId id_ = getMethodId("factrec", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        assertEq(-2, getNumber(calculateNormalParam("pkg.Ex", id_, new IntStruct(2), cont_)));
    }
    @Test
    public void calculateArgument1046Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return pkg.ExTwo.getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1047Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExTwo.getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1048Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkgtwo.ExTwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExTwo.getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument1049Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $int loc = 0i;\n");
        xml_.append("  $if(loc == 0i){\n");
        xml_.append("   $int myf = 10i;\n");
        xml_.append("   loc += myf;\n");
        xml_.append("  }\n");
        xml_.append("  $return myf+loc;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(20, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1050Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo e = $new ExTwo();\n");
        xml_.append("  $return e.getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myf=2i;\n");
        xml_.append(" $public $normal $int getstatic(){\n");
        xml_.append("  $int loc = 0i;\n");
        xml_.append("  $if(loc == 0i){\n");
        xml_.append("   $int myf = 10i;\n");
        xml_.append("   loc += myf;\n");
        xml_.append("  }\n");
        xml_.append("  $return myf+loc;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(20, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1051Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo e = $new ExTwo();\n");
        xml_.append("  $return e.getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myf=2i;\n");
        xml_.append(" $public $normal $int getstatic(){\n");
        xml_.append("  $int loc = 0i;\n");
        xml_.append("  $if(loc == 0i){\n");
        xml_.append("   $int myf = 10i;\n");
        xml_.append("   loc += $this.myf;\n");
        xml_.append("  }\n");
        xml_.append("  $return myf+loc;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(12, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1052Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $int loc = 0i;\n");
        xml_.append("  $if(loc == 0i){\n");
        xml_.append("   $int myf = 10i;\n");
        xml_.append("   loc += ExTwo.myf;\n");
        xml_.append("  }\n");
        xml_.append("  $return myf+loc;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(12, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1053Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=2i,mys;\n");
        xml_.append(" $static{\n");
        xml_.append("  mys=3i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $int loc = 0i;\n");
        xml_.append("  $if(loc == 0i){\n");
        xml_.append("   $int myf = 10i;\n");
        xml_.append("   loc += ExTwo.myf+mys;\n");
        xml_.append("  }\n");
        xml_.append("  $return myf+loc;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(15, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1054Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf,mys=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf=3i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $int loc = 0i;\n");
        xml_.append("  $if(loc == 0i){\n");
        xml_.append("   $int myf = 10i;\n");
        xml_.append("   loc += ExTwo.myf+mys;\n");
        xml_.append("  }\n");
        xml_.append("  $return myf+loc;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(16, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1055Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean res = ExTwo.myfb;\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean myfb;\n");
        xml_.append(" $static{\n");
        xml_.append("  $final $int myf;\n");
        xml_.append("  $if ($math.random() < 0.5){\n");
        xml_.append("   myf = ExThree.myf;\n");
        xml_.append("  } $else {\n");
        xml_.append("   myf = ExFour.myf;\n");
        xml_.append("  }\n");
        xml_.append("  myfb = myf > 1 && myf < 4;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=2i;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int myf=3i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.Ex");
        MethodId id_ = getMethodId("exmeth");
        assertTrue(calculateNormal("pkg.Ex", id_, cont_));
    }
    @Test
    public void calculateArgument10555Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean res = ExTwo.myfb&&ExTwo.myfb2;\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean myfb2;\n");
        xml_.append(" $public $static $final $boolean myfb;\n");
        xml_.append(" $static{\n");
        xml_.append("  $final $int myf;\n");
        xml_.append("  $if ($math.random() < 0.5){\n");
        xml_.append("   myf = ExThree.myf;\n");
        xml_.append("  } $else {\n");
        xml_.append("   myf = ExFour.myf;\n");
        xml_.append("  }\n");
        xml_.append("  myfb = myf > 1 && myf < 4;\n");
        xml_.append("  myfb2 = $true;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=2i;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int myf=3i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.Ex");
        MethodId id_ = getMethodId("exmeth");
        assertTrue(calculateNormal("pkg.Ex", id_, cont_));
    }

    @Test
    public void calculateArgument1055_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return ExTwo.myfb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean myfb;\n");
        xml_.append(" $static{\n");
        xml_.append("  $final $int myf;\n");
        xml_.append("  $if ($math.random() < 0.5){\n");
        xml_.append("   myf = ExThree.myf;\n");
        xml_.append("  } $else {\n");
        xml_.append("   myf = ExFour.myf;\n");
        xml_.append("  }\n");
        xml_.append("  myfb = myf > 1 && myf < 4;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=2i;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int myf=3i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElTypes(files_);
        assertFalse(stateMismatchSuccessful(cont_, "pkg.Ex"));
        assertFalse(stateMismatchSuccessful(cont_, "pkg.ExTwo"));
        assertFalse(stateMismatchSuccessful(cont_, "pkg.ExThree"));
        assertFalse(stateMismatchSuccessful(cont_, "pkg.ExFour"));
        MethodId id_ = getMethodId("exmeth");
        assertTrue(calculateNormal("pkg.Ex", id_, cont_));
    }

    @Test
    public void calculateArgument1056Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1057Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=100i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $return;\n");
        xml_.append("  }$catch(Object i){\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   myf+=100i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  myf+=1000i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
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
        assertEq(1210, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1058Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf;\n");
        xml_.append(" $static{\n");
        xml_.append("  $try{\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   myf=100i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(108, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1059Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf;\n");
        xml_.append(" $static{\n");
        xml_.append("  $try{\n");
        xml_.append("   myf=100i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(108, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1060Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf;\n");
        xml_.append(" $static{\n");
        xml_.append("  $return;\n");
        xml_.append("  $try{\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
