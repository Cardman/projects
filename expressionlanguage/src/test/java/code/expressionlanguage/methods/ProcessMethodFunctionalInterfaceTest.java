package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;

public final class ProcessMethodFunctionalInterfaceTest extends ProcessMethodCommon {

    @Test
    public void calculateObjTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Interface test(){\n");
        xml_.append("  $return (Interface) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.getStruct() instanceof AbstractFunctionalInstance);
    }

    @Test
    public void calculateObj2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $ObjectsUtil.getParent((Interface) $static().$lambda($math,plus,$int,$int)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
        assertNull(getException(cont_));
    }

    @Test
    public void calculateObj3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $var ex = (Interface) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append("  $return ex;. == (Interface) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
        assertNull(getException(cont_));
    }

    @Test
    public void calculateObj4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $var ex = (Interface) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append("  $return ex;. == ex;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
        assertNull(getException(cont_));
    }
    @Test
    public void calculateRetTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $var inte = (Interface) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append("  inte;.field = 5:\n");
        xml_.append("  inte;.field2 = 10:\n");
        xml_.append("  $return inte;.field + inte;.field2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $static $int field:\n");
        xml_.append(" $static $int field2:\n");
        xml_.append(" $int opTwo($int a,$int b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(15, ret_.getNumber());
    }
    @Test
    public void calculate0Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Interface res = (Interface) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append("  $return res;.opTwo(1,2):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(3, ret_.getNumber());
    }

    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) $static().$lambda(Apply,gene):\n");
        xml_.append("  $return res;.opTwo():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String gene(){\n");
        xml_.append("  $return \"simple\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" Object opTwo():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("simple", ret_.getString());
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Interface<$int> res = (Interface<$int>) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append("  $return res;.opTwo(1,2):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#T> {\n");
        xml_.append(" #T opTwo(#T a,#T b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(3, ret_.getNumber());
    }
    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) $staticCall().$lambda(Apply<#T>,sum,#T,#T):\n");
        xml_.append("  $return res;.opTwo((#T)1,(#T)2):\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)a;.;,($int)b;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E> {\n");
        xml_.append(" #E opTwo(#E a,#E b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(3, ret_.getNumber());
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T>():\n");
        xml_.append("  app;.field = (#T) 3:\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) app;.$lambda(Apply<#T>,sum,#T,#T):\n");
        xml_.append("  $return res;.opTwo((#T)1,(#T)2):\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a;.;,($int)b;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E> {\n");
        xml_.append(" #E opTwo(#E a,#E b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(6, ret_.getNumber());
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T>():\n");
        xml_.append("  app;.field = (#T) 3:\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) app;.$lambda(Apply<#T>,sum,#T,#T):\n");
        xml_.append("  $return res;.opDef((#T)1,(#T)2):\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a;.;,($int)b;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a;.;,b;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T>():\n");
        xml_.append("  app;.field = (#T) 3:\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) app;.$lambda(Apply<#T>,sum,#T,#T):\n");
        xml_.append("  $return res;.opDef((#T)1,(#T)2):\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a;.;,($int)b;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a;.;,b;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b):\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculate7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T,#U:#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int,$int>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T,#U>():\n");
        xml_.append("  app;.field = (#T) 3:\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) app;.$lambda(Apply<#T,#U>,sum,#U,#U):\n");
        xml_.append("  $return res;.opDef((#T)1,(#T)2):\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a;.;,($int)b;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a;.;,b;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b):\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculate8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T,#U:#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int,$int>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T,#U>():\n");
        xml_.append("  app;.field = (#T) 3:\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) app;.$lambda(Apply<#T,#U>,sum,#U,#U):\n");
        xml_.append("  $return res;.opDef((#T)1,(#T)2):\n");
        xml_.append(" }\n");
        xml_.append(" $public #U sum(#T a,#T b){\n");
        xml_.append("  $return (#U)$math.plus(($int)field, $math.plus(($int)a;.;,($int)b;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a;.;,b;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b):\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }

    @Test
    public void calculate9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T,#U:#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int,$int>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T,#U>():\n");
        xml_.append("  app;.field = (#T) 3:\n");
        xml_.append("  SupInterface<#T> res = (Interface<#T>) app;.$lambda(Apply<#T,#U>,sum,#U,#U):\n");
        xml_.append("  $return res;.opDef((#T)1,(#T)2):\n");
        xml_.append(" }\n");
        xml_.append(" $public #U sum(#T a,#T b){\n");
        xml_.append("  $return (#U)$math.plus(($int)field, $math.plus(($int)a;.;,($int)b;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a;.;,b;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b):\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }

    @Test
    public void calculate10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Interface res = (Interface) ($static().$lambda($math,plus,$int,$int)):\n");
        xml_.append("  $return res;.opTwo(1,2):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(3, ret_.getNumber());
    }
    @Test
    public void calculate11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) $static().$lambda(Apply,gene,String...):\n");
        xml_.append("  $return res;.opTwo(\"call\",\"here\"):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String gene(String... args){\n");
        xml_.append("  $return \"simple \"+args;.;[0]+\" \"+args;.;[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" Object opTwo(String... args):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("simple call here", ret_.getString());
    }
    @Test
    public void calculate12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) $static().$lambda(Apply,gene,String...):\n");
        xml_.append("  $return res;.opTwo():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String gene(String... args){\n");
        xml_.append("  $return \"simple \"+args;.;length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" Object opTwo(String... args):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("simple 0", ret_.getString());
    }
    @Test
    public void calculate13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<T> {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $staticCall(Apply<java.lang.String>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T test(){\n");
        xml_.append("  Interface<T> res = (Interface<T>) $staticCall().$lambda(Apply<T>,gene,T...):\n");
        xml_.append("  $return res;.opTwo((T)\"call\",(T)\"here\"):\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T gene(T... args){\n");
        xml_.append("  $return (T)(\"simple \"+args;.;[0]+\" \"+args;.;[1]):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<T> {\n");
        xml_.append(" T opTwo(T... args):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ =getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("simple call here", ret_.getString());
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<T> {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $staticCall(Apply<java.lang.String>).test():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T test(){\n");
        xml_.append("  Interface<T> res = (Interface<T>) $staticCall().$lambda(Apply<T>,gene,T...):\n");
        xml_.append("  $return res;.opTwo():\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T gene(T... args){\n");
        xml_.append("  $return (T)(\"simple \"+args;.;length):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<T> {\n");
        xml_.append(" T opTwo(T... args):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ =getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("simple 0", ret_.getString());
    }
    @Test
    public void calculate1ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) $static().$lambda(Apply,gene):\n");
        xml_.append("  $return res;.opTwo():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" String opTwo():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate2ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) $static().$lambda(Apply,gene):\n");
        xml_.append("  $return res;.opTwo():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" String opTwo():\n");
        xml_.append(" String opThree():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate3ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  String res = (String) $static().$lambda(Apply,gene):\n");
        xml_.append("  $return res;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" String opTwo():\n");
        xml_.append(" String opThree():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate4ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface<?> res = (Interface<?>) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append("  $return res;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#T> {\n");
        xml_.append(" #T opTwo(#T a,#T b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate5ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) $static().$lambda(Apply,gene):\n");
        xml_.append("  $return res;.opTwo():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" Interface(){}\n");
        xml_.append(" {}\n");
        xml_.append(" $int f:\n");
        xml_.append(" Object opTwo():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate6ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Enum res = (Enum) $static().$lambda(Apply,gene):\n");
        xml_.append("  $return res;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.Enum {\n");
        xml_.append(" :\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate7ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static pkg.Outer.Interface test(){\n");
        xml_.append("  $return (pkg.Outer.Interface) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $interface Interface {\n");
        xml_.append("  $int opTwo($int a,$int b):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void calculate1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Inexist) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#T> {\n");
        xml_.append(" #T opTwo(#T a,#T b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $($void) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#T> {\n");
        xml_.append(" #T opTwo(#T a,#T b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(void) $static().$lambda($math,plus,$int,$int):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#T> {\n");
        xml_.append(" #T opTwo(#T a,#T b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $new $void():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#T> {\n");
        xml_.append(" #T opTwo(#T a,#T b):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
}
