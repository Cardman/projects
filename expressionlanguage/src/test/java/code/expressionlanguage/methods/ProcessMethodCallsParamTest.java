package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


public final class ProcessMethodCallsParamTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1013Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  java.lang.StringBuilder str = $new java.lang.StringBuilder():\n");
        xml_.append("  $int test = 5i:\n");
        xml_.append("  $if (test;. == 6i && exmeth(str;.,test;.)){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return str;.length():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmeth(java.lang.StringBuilder p, $int cst){\n");
        xml_.append("  p;.;append(\"feed\"):\n");
        xml_.append("  $return cst;.; > 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }

    @Test
    public void calculateArgument1014Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  java.lang.StringBuilder str = $new java.lang.StringBuilder():\n");
        xml_.append("  $int test = 3i:\n");
        xml_.append("  $if (test;. == 3i && exmeth(str;.,test;.)){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return str;.length():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmeth(java.lang.StringBuilder p, $int cst){\n");
        xml_.append("  p;.;append(\"feed\"):\n");
        xml_.append("  $return cst;.; > 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, ret_.getNumber());
    }

    @Test
    public void calculateArgument1015Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  java.lang.StringBuilder str = $new java.lang.StringBuilder():\n");
        xml_.append("  $int test = 4i:\n");
        xml_.append("  $if (test;. == 4i && exmeth(str;.,test;.)){\n");
        xml_.append("   $return 1i+str;.length():\n");
        xml_.append("  }\n");
        xml_.append("  $return str;.length():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmeth(java.lang.StringBuilder p, $int cst){\n");
        xml_.append("  p;.;append(\"feed\"):\n");
        xml_.append("  $return cst;.; > 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(5, ret_.getNumber());
    }

    @Test
    public void calculateArgument1016Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  java.lang.StringBuilder str = $new java.lang.StringBuilder():\n");
        xml_.append("  $int test = 6i:\n");
        xml_.append("  $if (test;. == 6i || exmeth(str;.,test;.)){\n");
        xml_.append("   $return 1i+str;.length():\n");
        xml_.append("  }\n");
        xml_.append("  $return str;.length():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmeth(java.lang.StringBuilder p, $int cst){\n");
        xml_.append("  p;.;append(\"feed\"):\n");
        xml_.append("  $return cst;.; > 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }

    @Test
    public void calculateArgument1017Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  java.lang.StringBuilder str = $new java.lang.StringBuilder():\n");
        xml_.append("  $int test = 6i:\n");
        xml_.append("  $if (test;. == 3i || exmeth(str;.,test;.)){\n");
        xml_.append("   $return 1i+str;.length():\n");
        xml_.append("  }\n");
        xml_.append("  $return str;.length():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmeth(java.lang.StringBuilder p, $int cst){\n");
        xml_.append("  p;.;append(\"feed\"):\n");
        xml_.append("  $return cst;.; > 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(5, ret_.getNumber());
    }

    @Test
    public void calculateArgument1018Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  java.lang.StringBuilder str = $new java.lang.StringBuilder():\n");
        xml_.append("  $int test = -4i:\n");
        xml_.append("  $if (test;. == -3i || exmeth(str;.,test;.)){\n");
        xml_.append("   $return 1i+str;.length():\n");
        xml_.append("  }\n");
        xml_.append("  $return str;.length():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmeth(java.lang.StringBuilder p, $int cst){\n");
        xml_.append("  p;.;append(\"feed\"):\n");
        xml_.append("  $return cst;.; > 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, ret_.getNumber());
    }
    @Test
    public void calculateArgument1019Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $int i=0:\n");
        xml_.append("  $int m=2:\n");
        xml_.append("  $do{\n");
        xml_.append("   t;.++:\n");
        xml_.append("   i;.++:\n");
        xml_.append("  }\n");
        xml_.append("  $while(exmethparam(i;.,m;.)):\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   $if(i;%2==0i){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("   $else{\n");
        xml_.append("    t;.+=i;+1:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int[] exmethlist(){\n");
        xml_.append("  $int[] t:\n");
        xml_.append("  t;.=$new $int[3]:\n");
        xml_.append("  t;.[0]=8i:\n");
        xml_.append("  t;.[1]=2i:\n");
        xml_.append("  t;.[2]=1i:\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam($int i,$int m){\n");
        xml_.append("  $return i;.;<m;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(24, ret_.getNumber());
    }
    @Test
    public void calculateArgument1020Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0:\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   $foreach($int j:exmethlisttwo()){\n");
        xml_.append("    t;.+=i;+j;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int[] exmethlist(){\n");
        xml_.append("  $int[] t:\n");
        xml_.append("  t;.=$new $int[3]:\n");
        xml_.append("  t;.[0]=8i:\n");
        xml_.append("  t;.[1]=2i:\n");
        xml_.append("  t;.[2]=1i:\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int[] exmethlisttwo(){\n");
        xml_.append("  $int[] t:\n");
        xml_.append("  t;.=$new $int[2]:\n");
        xml_.append("  t;.[0]=2i:\n");
        xml_.append("  t;.[1]=4i:\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(40, ret_.getNumber());
    }
    @Test
    public void calculateArgument1021Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0:\n");
        xml_.append("  $iter($int i=exinit():exto()::exstep()){\n");
        xml_.append("   $foreach($int j:exmethlist()){\n");
        xml_.append("    t;.+=i;+j;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int[] exmethlist(){\n");
        xml_.append("  $int[] t:\n");
        xml_.append("  t;.=$new $int[3]:\n");
        xml_.append("  t;.[0]=8i:\n");
        xml_.append("  t;.[1]=2i:\n");
        xml_.append("  t;.[2]=1i:\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exinit(){\n");
        xml_.append("  $return 2:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exto(){\n");
        xml_.append("  $return 4:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exstep(){\n");
        xml_.append("  $return 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(40, ret_.getNumber());
    }
}
