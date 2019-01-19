package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;


public final class ProcessMethodCallsParamTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1006Test() {
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
        xml_.append("  $while(exmethparam(p;.)){\n");
        xml_.append("   $continue:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam(code.expressionlanguage.classes.PickableList l){\n");
        xml_.append("  $return l;.;removeAndExistAfter(1i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(21, ret_.getNumber());
    }

    @Test
    public void calculateArgument1007Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  code.expressionlanguage.classes.PickableList p=$new code.expressionlanguage.classes.PickableList():\n");
        xml_.append("  adding(p;.,0):\n");
        xml_.append("  adding(p;.,2):\n");
        xml_.append("  $while(exmethparam(p;.)){\n");
        xml_.append("   $continue:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void adding(code.expressionlanguage.classes.PickableList l,java.lang.Object o){\n");
        xml_.append("  l;.;getList().add(o;.;):\n");
        xml_.append("  $return:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam(code.expressionlanguage.classes.PickableList l){\n");
        xml_.append("  $return l;.;removeAndExistAfter(1i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(21, ret_.getNumber());
    }

    @Test
    public void calculateArgument1008Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  code.expressionlanguage.classes.PickableList p=$new code.expressionlanguage.classes.PickableList():\n");
        xml_.append("  adding(p;.,0):\n");
        xml_.append("  adding(p;.,2):\n");
        xml_.append("  $while(exmethparam(p;.)){\n");
        xml_.append("   $continue:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void adding(code.expressionlanguage.classes.PickableList l,java.lang.Object o){\n");
        xml_.append("  l;.;getList().add(o;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam(code.expressionlanguage.classes.PickableList l){\n");
        xml_.append("  $return l;.;removeAndExistAfter(1i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(21, ret_.getNumber());
    }


    @Test
    public void calculateArgument1009Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  code.expressionlanguage.classes.PickableList p=$new code.expressionlanguage.classes.PickableList():\n");
        xml_.append("  adding(p;.,0):\n");
        xml_.append("  adding(p;.,2):\n");
        xml_.append("  $while(exmethparam(p;.)){\n");
        xml_.append("   $continue:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   $if(i;%2==0i){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void adding(code.expressionlanguage.classes.PickableList l,java.lang.Object o){\n");
        xml_.append("  l;.;getList().add(o;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  t;.add(1i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam(code.expressionlanguage.classes.PickableList l){\n");
        xml_.append("  $return l;.;removeAndExistAfter(1i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(21, ret_.getNumber());
    }

    @Test
    public void calculateArgument1010Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  code.expressionlanguage.classes.PickableList p=$new code.expressionlanguage.classes.PickableList():\n");
        xml_.append("  adding(p;.,0):\n");
        xml_.append("  adding(p;.,2):\n");
        xml_.append("  $while(exmethparam(p;.)){\n");
        xml_.append("   $continue:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   $if(i;%2==0i){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("   $else{\n");
        xml_.append("    t;.+=i;+1:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void adding(code.expressionlanguage.classes.PickableList l,java.lang.Object o){\n");
        xml_.append("  l;.;getList().add(o;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  t;.add(1i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam(code.expressionlanguage.classes.PickableList l){\n");
        xml_.append("  $return l;.;removeAndExistAfter(1i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(23, ret_.getNumber());
    }

    @Test
    public void calculateArgument1011Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  code.expressionlanguage.classes.PickableList p=$new code.expressionlanguage.classes.PickableList():\n");
        xml_.append("  adding(p;.,0):\n");
        xml_.append("  adding(p;.,2):\n");
        xml_.append("  $while(exmethparam(p;.)){\n");
        xml_.append("   $continue:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   $int elt=5i:\n");
        xml_.append("   $if(i;%2==0i){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("   $else{\n");
        xml_.append("    t;.+=i;+1:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=elt;.:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void adding(code.expressionlanguage.classes.PickableList l,java.lang.Object o){\n");
        xml_.append("  l;.;getList().add(o;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  t;.add(1i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam(code.expressionlanguage.classes.PickableList l){\n");
        xml_.append("  $return l;.;removeAndExistAfter(1i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(38, ret_.getNumber());
    }


    @Test
    public void calculateArgument1012Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethsec()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethsec(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  code.expressionlanguage.classes.PickableList p=$new code.expressionlanguage.classes.PickableList():\n");
        xml_.append("  adding(p;.,0):\n");
        xml_.append("  adding(p;.,2):\n");
        xml_.append("  $do{\n");
        xml_.append("   t;.++:\n");
        xml_.append("  }\n");
        xml_.append("  $while(exmethparam(p;.)):\n");
        xml_.append("  $foreach($int i:exmethlist()){\n");
        xml_.append("   $if(i;%2==0i){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("   $else{\n");
        xml_.append("    t;.+=i;+1:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+p;.getList().size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void adding(code.expressionlanguage.classes.PickableList l,java.lang.Object o){\n");
        xml_.append("  l;.;getList().add(o;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static code.expressionlanguage.classes.Ints exmethlist(){\n");
        xml_.append("  code.expressionlanguage.classes.Ints t:\n");
        xml_.append("  t;.=$new code.expressionlanguage.classes.Ints():\n");
        xml_.append("  t;.add(8i):\n");
        xml_.append("  t;.add(2i):\n");
        xml_.append("  t;.add(1i):\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmethparam(code.expressionlanguage.classes.PickableList l){\n");
        xml_.append("  $return l;.;removeAndExistAfter(1i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(25, ret_.getNumber());
    }

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
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
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
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
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
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
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
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
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
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
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
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, ret_.getNumber());
    }
}
