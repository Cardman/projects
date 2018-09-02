package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;

public final class ProcessMethodReferenceTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$int,$int> f = $lambda(Ex,exmethtwo,$int):\n");
        xml_.append("  $return f;.call(5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<Ex,$int> f = $static(Ex).$lambda(Ex,$false,get):\n");
        xml_.append("  $return f;.call(instance;.):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<$int> f = instance;.$lambda(Ex,get):\n");
        xml_.append("  $return f;.call():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$int,$int> f = $lambda(Ex,exmethtwo,$int):\n");
        xml_.append("  $return f;.call(5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,$int> f = $static(Ex).$lambda(Ex<java.lang.Number>,$false,get):\n");
        xml_.append("  $return f;.call(instance;.):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number> f = $static(Ex).$lambda(Ex<java.lang.Number>,$false,get):\n");
        xml_.append("  $return $($int) f;.call(instance;.):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T get(){\n");
        xml_.append("  $return field;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number> f = $static(Ex).$lambda(Ex<java.lang.Number>,$false,get):\n");
        xml_.append("  $return f;.call(instance;.).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T get(){\n");
        xml_.append("  $return field;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number,java.lang.Number> f = $static(Ex).$lambda(Ex<java.lang.Number>,$false,sumWith,java.lang.Number):\n");
        xml_.append("  $return f;.call(instance;.,6i).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T sumWith(#T other){\n");
        xml_.append("  $return $(#T)(($(java.lang.Number)field;;;).intValue()+($(java.lang.Number)other;.;).intValue()):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(20, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public #T field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<java.lang.Number> f = instance;.$lambda(Ex<java.lang.Number>,get):\n");
        xml_.append("  $return f;.call().intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T get(){\n");
        xml_.append("  $return field;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return:\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $static(Ex).$lambda(Ex<#S>,$false,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#S)($($int)t;.+$($int)p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T>:Ex<#T> {\n");
        xml_.append(" $public $normal #T transform(#T p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#T)(2i*$($int)t;.+2i*$($int)p;.;):\n");
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
        assertEq(26, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  Ex<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $static(Ex).$lambda(Ex<#S>,$false,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#S)($($int)t;.+$($int)p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T>:Ex<#T> {\n");
        xml_.append(" $public $normal #T transform(#T p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#T)(2i*$($int)t;.+2i*$($int)p;.;):\n");
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
        assertEq(26, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $static(Ex).$lambda(Ex<#S>,$false,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $return $(#S)($($int)field;;;+$($int)p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T>:Ex<#T> {\n");
        xml_.append(" $public $normal #T transform(#T p){\n");
        xml_.append("  $return $(#T)(2i*$($int)field;;;+2i*$($int)p;.;):\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  Ex<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $static(Ex).$lambda(Ex<#S>,$false,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $return $(#S)($($int)field;;;+$($int)p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T>:Ex<#T> {\n");
        xml_.append(" $public $normal #T transform(#T p){\n");
        xml_.append("  $return $(#T)(2i*$($int)field;;;+2i*$($int)p;.;):\n");
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
        assertEq(14, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<#S,#S> f = sub;.$lambda(Ex<#S>,transform,#S):\n");
        xml_.append("  $return f;.call($(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#S)($($int)t;.+$($int)p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T>:Ex<#T> {\n");
        xml_.append(" $public $normal #T transform(#T p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#T)(2i*$($int)t;.+2i*$($int)p;.;):\n");
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
        assertEq(26, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<ExTwo<#S>,#S,#S> f = $static(ExTwo).$lambda(ExTwo<#S>,$false,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#S)($($int)t;.+$($int)p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T>:Ex<#T> {\n");
        xml_.append(" $public $normal #T transform(#T p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return $(#T)(2i*$($int)t;.+2i*$($int)p;.;):\n");
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
        assertEq(26, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<[$int,$int> f = $lambda(Ex,exmethtwo,$int...):\n");
        xml_.append("  $return f;.call($new [$int[](5i,13i)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:p;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
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
        assertEq(27, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$Fct<[$int,$int>,[java.lang.Object,java.lang.Object> f = $static($Fct).$lambda($Fct<[$int,$int>,$false,call,java.lang.Object...):\n");
        xml_.append("  $Fct<[$int,$int> g = $lambda(Ex,exmethtwo,$int...):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [$int[](5i,13i)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:p;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
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
        assertEq(27, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class($Fct).makeGeneric($class(java.lang.Number),$class($int)).getName():\n");
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
        assertEq("$Fct<java.lang.Number,$int>", (String)ret_.getObject());
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$Fct<[$int,$int>,[java.lang.Object,java.lang.Object> f = $static($Fct).$lambda($Fct<[$int,$int>,$false,call,java.lang.Object...):\n");
        xml_.append("  $Fct<[$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int...):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [$int[](5i,13i)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:p;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
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
        assertEq(27, (Number)ret_.getObject());
    }
    //Ignore Because of instance of call method by indirect reference
    @Ignore
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$Fct<Ex,[$int,$int>,[java.lang.Object,java.lang.Object> f = $static($Fct).$lambda($Fct<Ex,[$int,$int>,$false,call,java.lang.Object...):\n");
        xml_.append("  $Fct<Ex,[$int,$int> g = $static(Ex).$lambda(Ex,$false,exmethtwo,$int...):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[]($new Ex(),$new [$int[](5i,13i))):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int... p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:p;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
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
        assertEq(27, (Number)ret_.getObject());
    }
}
