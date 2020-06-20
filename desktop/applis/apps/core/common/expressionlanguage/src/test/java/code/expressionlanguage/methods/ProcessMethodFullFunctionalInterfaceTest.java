package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.Classes;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.FullFunctionalInstance;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;

public final class ProcessMethodFullFunctionalInterfaceTest extends ProcessMethodCommon {

    @Test
    public void calculateObjTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Interface test(){\n");
        xml_.append("  $return (Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.getStruct() instanceof FullFunctionalInstance);
        assertEq(0, ((FullFunctionalInstance)ret_.getStruct()).getFields().size());
    }

    @Test
    public void calculateObj2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $ObjectsUtil.getParent((Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)()));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
        assertNull(getException(cont_));
    }

    @Test
    public void calculateObj3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $var ex = (Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append("  $return ex == (Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
        assertNull(getException(cont_));
    }

    @Test
    public void calculateObj4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $var ex = (Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append("  $return ex == ex;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
        assertNull(getException(cont_));
    }
    @Test
    public void calculateRetTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $var inte = (Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)(5,10));\n");
        xml_.append("  $return inte.field + inte.field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int field;\n");
        xml_.append(" $int field2;\n");
        xml_.append(" Interface($int field,$int field2){\n");
        xml_.append("  $this.field=field;\n");
        xml_.append("  $this.field2=field2;\n");
        xml_.append(" }\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateReturnTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $var inte = (Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)(5,10));\n");
        xml_.append("  $return inte.opThree(6,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int field;\n");
        xml_.append(" $int field2;\n");
        xml_.append(" Interface($int field,$int field2){\n");
        xml_.append("  $this.field=field;\n");
        xml_.append("  $this.field2=field2;\n");
        xml_.append(" }\n");
        xml_.append(" $normal $int opThree($int a,$int b){\n");
        xml_.append("  $return opTwo(a,b)+field+field2;\n");
        xml_.append(" }\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculate0Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Interface res = (Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo(1,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }

    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) ($static().$lambda(Apply,gene),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String gene(){\n");
        xml_.append("  $return \"simple\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" Object opTwo();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("simple", getString(ret_));
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Interface<$int> res = (Interface<$int>) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo(1,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#T> {\n");
        xml_.append(" #T opTwo(#T a,#T b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }

    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) ($staticCall().$lambda(Apply<#T>,sum,#T,#T),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo((#T)1,(#T)2);\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)a,($int)b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E> {\n");
        xml_.append(" #E opTwo(#E a,#E b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public #T field;\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T>();\n");
        xml_.append("  app.field = (#T) 3;\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) (app.$lambda(Apply<#T>,sum,#T,#T),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo((#T)1,(#T)2);\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a,($int)b));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E> {\n");
        xml_.append(" #E opTwo(#E a,#E b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public #T field;\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T>();\n");
        xml_.append("  app.field = (#T) 3;\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) (app.$lambda(Apply<#T>,sum,#T,#T),$interfaces(Interface)());\n");
        xml_.append("  $return res.opDef((#T)1,(#T)2);\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a,($int)b));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T> {\n");
        xml_.append(" $public #T field;\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T>();\n");
        xml_.append("  app.field = (#T) 3;\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) (app.$lambda(Apply<#T>,sum,#T,#T),$interfaces(SupInterface)(),$interfaces(Interface)());\n");
        xml_.append("  $return res.opDef((#T)1,(#T)2);\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a,($int)b));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b);\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculate7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T,#U:#T> {\n");
        xml_.append(" $public #T field;\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int,$int>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T,#U>();\n");
        xml_.append("  app.field = (#T) 3;\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) (app.$lambda(Apply<#T,#U>,sum,#U,#U),$interfaces(SupInterface)(),$interfaces(Interface)());\n");
        xml_.append("  $return res.opDef((#T)1,(#T)2);\n");
        xml_.append(" }\n");
        xml_.append(" $public #T sum(#T a,#T b){\n");
        xml_.append("  $return (#T)$math.plus(($int)field, $math.plus(($int)a,($int)b));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b);\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculate8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T,#U:#T> {\n");
        xml_.append(" $public #T field;\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int,$int>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T,#U>();\n");
        xml_.append("  app.field = (#T) 3;\n");
        xml_.append("  Interface<#T> res = (Interface<#T>) (app.$lambda(Apply<#T,#U>,sum,#U,#U),$interfaces(SupInterface)(),$interfaces(Interface)());\n");
        xml_.append("  $return res.opDef((#T)1,(#T)2);\n");
        xml_.append(" }\n");
        xml_.append(" $public #U sum(#T a,#T b){\n");
        xml_.append("  $return (#U)$math.plus(($int)field, $math.plus(($int)a,($int)b));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b);\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculate9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<#T,#U:#T> {\n");
        xml_.append(" $public #T field;\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(pkg.Apply<$int,$int>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall #T test(){\n");
        xml_.append("  $var app = $new Apply<#T,#U>();\n");
        xml_.append("  app.field = (#T) 3;\n");
        xml_.append("  SupInterface<#T> res = (Interface<#T>) (app.$lambda(Apply<#T,#U>,sum,#U,#U),$interfaces(SupInterface)(),$interfaces(Interface)());\n");
        xml_.append("  $return res.opDef((#T)1,(#T)2);\n");
        xml_.append(" }\n");
        xml_.append(" $public #U sum(#T a,#T b){\n");
        xml_.append("  $return (#U)$math.plus(($int)field, $math.plus(($int)a,($int)b));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<#E>:SupInterface<#E> {\n");
        xml_.append(" $normal #E opDef(#E a,#E b){\n");
        xml_.append("  $return (#E)(4+($int)opTwo(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" #E opTwo(#E a,#E b);\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SupInterface<#S> {\n");
        xml_.append(" #S opDef(#S a,#S b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculate10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return ($(Interface2) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)(),$interfaces(Interface2)())).opTwo(1,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface2:Interface {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculate11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Interface<$int> res = (Interface<$int>) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)(5,8));\n");
        xml_.append("  $return res.opThree(1,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" T field2;\n");
        xml_.append(" Interface(T field,T field2){\n");
        xml_.append("  $this.field=field;\n");
        xml_.append("  $this.field2=field2;\n");
        xml_.append(" }\n");
        xml_.append(" $normal T opThree(T a,T b){\n");
        xml_.append("  $return (T)(($int)opTwo(a,b)+($int)field+($int)field2);\n");
        xml_.append(" }\n");
        xml_.append(" T opTwo(T a,T b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculate12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return $staticCall(Caller<$int>).test(5,8);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Caller<S> {\n");
        xml_.append(" $public $staticCall $int test(S a, S b){\n");
        xml_.append("  Interface<S> res = (Interface<S>) ($staticCall().$lambda(Caller<S>,pl,S,S),$interfaces(Interface)(a,b));\n");
        xml_.append("  $return ($int)res.opThree((S)1,(S)2);\n");
        xml_.append(" }\n");
        xml_.append(" $staticCall S pl(S a,S b){\n");
        xml_.append("  $return (S)(($int)a+($int)b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" T field2;\n");
        xml_.append(" Interface(T field,T field2){\n");
        xml_.append("  $this.field=field;\n");
        xml_.append("  $this.field2=field2;\n");
        xml_.append(" }\n");
        xml_.append(" $normal T opThree(T a,T b){\n");
        xml_.append("  $return (T)(($int)opTwo(a,b)+($int)field+($int)field2);\n");
        xml_.append(" }\n");
        xml_.append(" T opTwo(T a,T b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculate13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<T> {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $staticCall(Apply<java.lang.String>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T test(){\n");
        xml_.append("  Interface<T> res = (Interface<T>) ($staticCall().$lambda(Apply<T>,gene,T...),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo((T)\"call\",(T)\"here\");\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T gene(T... args){\n");
        xml_.append("  $return (T)(\"simple \"+args[0]+\" \"+args[1]);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<T> {\n");
        xml_.append(" T opTwo(T... args);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ =getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("simple call here", getString(ret_));
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply<T> {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $staticCall(Apply<java.lang.String>).test();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T test(){\n");
        xml_.append("  Interface<T> res = (Interface<T>) ($staticCall().$lambda(Apply<T>,gene,T...),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall T gene(T... args){\n");
        xml_.append("  $return (T)(\"simple \"+args.length);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<T> {\n");
        xml_.append(" T opTwo(T... args);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ =getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("simple 0", getString(ret_));
    }
    @Test
    public void calculate1ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) ($static().$lambda(Apply,gene),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo(5);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" String opTwo($int a);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate2ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) ($static().$lambda(Apply,gene),$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" String opTwo();\n");
        xml_.append(" String opThree();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate3ExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  Interface res = (Interface) (5,$interfaces(Interface)());\n");
        xml_.append("  $return res.opTwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object gene(){\n");
        xml_.append("  $return \"simple\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" String opTwo();\n");
        xml_.append(" String opThree();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void calculate0FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static pkg.Outer.Interface test(){\n");
        xml_.append("  $return (pkg.Outer.Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Outer.Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $interface Interface {\n");
        xml_.append("  $int opTwo($int a,$int b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Interface) ($interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" Object opTwo();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Interface) ($interfaces(Enum)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.Enum {;\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Enum) ($interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.Enum {;\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Interface) ($interfaces(Interface2)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Interface) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface2)(),$interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface2:Interface {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  $return ($(Interface2) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)())).opTwo(1,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface2:Interface {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Interface2) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface2)(),$interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface2:Interface {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Apply) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Apply) ($static().$lambda($math,plus,$int,$int),$interfaces(Apply)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $(Interface<?>) ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return ($static().$lambda($math,plus,$int,$int),$interfaces(Interface)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate12FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return (Interface)($static().$lambda($math,plus,$int,$int),5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
}
