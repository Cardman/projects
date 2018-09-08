package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int):\n");
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
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,get):\n");
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
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int):\n");
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
        xml_.append("  $Fct<Ex<java.lang.Number>,$int> f = $lambda(Ex<java.lang.Number>,get):\n");
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
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number> f = $lambda(Ex<java.lang.Number>,get):\n");
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
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number> f = $lambda(Ex<java.lang.Number>,get):\n");
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
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number,java.lang.Number> f = $lambda(Ex<java.lang.Number>,sumWith,java.lang.Number):\n");
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
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(Ex<#S>,transform,#S):\n");
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
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(Ex<#S>,transform,#S):\n");
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
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(Ex<#S>,transform,#S):\n");
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
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(Ex<#S>,transform,#S):\n");
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
        xml_.append("  $Fct<ExTwo<#S>,#S,#S> f = $lambda(ExTwo<#S>,transform,#S):\n");
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
        xml_.append("  $Fct<[$int,$int> f = $static().$lambda(Ex,exmethtwo,$int...):\n");
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
        xml_.append("  $Fct<$Fct<[$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<[$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<[$int,$int> g = $static().$lambda(Ex,exmethtwo,$int...):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[]($new [$int[](5i,13i))):\n");
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
        xml_.append("  $Fct<$Fct<$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[](5i)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
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
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$Fct<Ex,$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<Ex,$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<Ex,$int,$int> g = $lambda(Ex,exmethtwo,$int):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[]($new Ex(),5i)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
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
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$Fct<[$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<[$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<[$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int...):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[]($new [$int[](5i,13i))):\n");
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

    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<$Fct<Ex,[$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<Ex,[$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<Ex,[$int,$int> g = $lambda(Ex,exmethtwo,$int...):\n");
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
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S:java.lang.Number> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(Ex<#S>,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<#S,$int> f = $lambda(#S,intValue):\n");
        xml_.append("  $return $(#S)($($int)t;.+f;.call(p;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number>:Ex<#T> {\n");
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
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S:java.lang.Number> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(Ex<#S>,$that,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<#S,$int> f = $lambda(#S,intValue):\n");
        xml_.append("  $return $(#S)($($int)t;.+f;.call(p;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number>:Ex<#T> {\n");
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
        assertEq(13, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S:java.lang.Number> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(Ex<#S>,$classchoice,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<#S,$int> f = $lambda(#S,intValue):\n");
        xml_.append("  $return $(#S)($($int)t;.+f;.call(p;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number>:Ex<#T> {\n");
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
        assertEq(13, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S:java.lang.Number> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<Ex<#S>,#S,#S> f = $lambda(ExTwo<#S>,$super,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<#S,$int> f = $lambda(#S,intValue):\n");
        xml_.append("  $return $(#S)($($int)t;.+f;.call(p;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number>:Ex<#T> {\n");
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
        assertEq(13, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#S:java.lang.Number> {\n");
        xml_.append(" $public #S field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>():\n");
        xml_.append("  $return sub;.exmethtwo($null).intValue():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S exmethtwo(#S p){\n");
        xml_.append("  ExTwo<#S> sub = $new ExTwo<#S>():\n");
        xml_.append("  sub;.field;;;=$(#S)2i:\n");
        xml_.append("  $Fct<ExTwo<#S>,#S,#S> f = $lambda(ExTwo<#S>,$super,transform,#S):\n");
        xml_.append("  $return f;.call(sub;.,$(#S)5i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #S transform(#S p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $Fct<#S,$int> f = $lambda(#S,intValue):\n");
        xml_.append("  $return $(#S)($($int)t;.+f;.call(p;.;)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number>:Ex<#T> {\n");
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
        assertEq(13, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<$int> f = instance;.$lambda(Ex,$that,get):\n");
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
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<$int> f = instance;.$lambda(Ex,$classchoice,get):\n");
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
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i:\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return superfield;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<$int> f = instance;.$lambda(Ex,$super,get):\n");
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
        assertEq(15, (Number)ret_.getObject());
    }

    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i:\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return superfield;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<$int> f = instance;.$lambda(ExTwo,get):\n");
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
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i:\n");
        xml_.append(" $public $abstract $int get():\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<$int> f = instance;.$lambda(ExTwo,get):\n");
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
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex> c = $lambda(Ex,$new):\n");
        xml_.append("  Ex instance = c;.call():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $return instance;.get():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;;;:\n");
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
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,Ex..Inner> c = $lambda(Ex..Inner,$new):\n");
        xml_.append("  Ex..Inner instance = c;.call($new Ex()):\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $return instance;.get():\n");
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
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex..Inner> c = $new Ex().$lambda(Inner,$new):\n");
        xml_.append("  Ex..Inner instance = c;.call():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $return instance;.get():\n");
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
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex..Inner> c = $new Ex().$lambda(Inner,$new,$int):\n");
        xml_.append("  Ex..Inner instance = c;.call(14i):\n");
        xml_.append("  $return instance;.get():\n");
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
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public {} $class Inner<#T> {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex..Inner<java.lang.String>> c = $new Ex().$lambda(Inner<java.lang.String>,$new,$int):\n");
        xml_.append("  Ex..Inner<java.lang.String> instance = c;.call(14i):\n");
        xml_.append("  $return instance;.get():\n");
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
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,[java.lang.String> c = $lambda([java.lang.String,$new,$int):\n");
        xml_.append("  [java.lang.String instance = c;.call(1i):\n");
        xml_.append("  $return instance;.length:\n");
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
        assertEq(1, (Number)ret_.getObject());
    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex<java.lang.String>..Inner> c = $new Ex<java.lang.String>().$lambda(Inner,$new,$int):\n");
        xml_.append("  Ex<java.lang.String>..Inner instance = c;.call(14i):\n");
        xml_.append("  $return instance;.get():\n");
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
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public {} $class Inner<#U> {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(#T p){\n");
        xml_.append("  $Fct<$int,Ex<#T>..Inner<#T>> c = $this.$lambda(Inner<#T>,$new,$int):\n");
        xml_.append("  Ex<#T>..Inner<#T> instance = c;.call(14i):\n");
        xml_.append("  $return instance;.get():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call2(#T p){\n");
        xml_.append("  $return call(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>():\n");
        xml_.append("  $return instance;.call2(\"\"):\n");
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
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public {} $class Inner<#U> {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(#T p){\n");
        xml_.append("  $Fct<$int,Ex<#T>..Inner<#T>> c = $this.$lambda(Inner<#T>,$new,$int):\n");
        xml_.append("  Ex<#T>..Inner<#T> instance = c;.call(14i):\n");
        xml_.append("  $return instance;.get():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call2(#T p){\n");
        xml_.append("  $return (call(p;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>():\n");
        xml_.append("  $return instance;.call2(\"\"):\n");
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
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public {} $class Inner<#U> {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(#T p){\n");
        xml_.append("  $Fct<$int,Ex<#T>..Inner<#T>> c = $this.$lambda(Inner<#T>,$new,$int):\n");
        xml_.append("  Ex<#T>..Inner<#T> instance = c;.call(14i):\n");
        xml_.append("  $return instance;.get():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>():\n");
        xml_.append("  $return instance;.call(\"\"):\n");
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
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public {} $class Inner<#U> {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T call(#T p){\n");
        xml_.append("  $Fct<$int,[#T> c = $lambda([#T,$new,$int):\n");
        xml_.append("  [#T instance = c;.call(1i):\n");
        xml_.append("  instance;.[0i]=p;.;:\n");
        xml_.append("  $return instance;.[0i]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>():\n");
        xml_.append("  $return instance;.call(\"sample\"):\n");
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
        assertEq("sample", (String)ret_.getObject());
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method f = $class($Fct).makeGeneric($class($int),$class($int)).getDeclaredMethods()[0i]:\n");
        xml_.append("  $Fct<$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int):\n");
        xml_.append("  $return $($int) f;.invoke(g;.,$(java.lang.Object)$new [java.lang.Object[](5i)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
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
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Method,java.lang.Object,[java.lang.Object,java.lang.Object> f = $lambda($Method,invoke,java.lang.Object,java.lang.Object...):\n");
        xml_.append("  $Method g = $class(Ex).getDeclaredMethods(\"exmethtwo\",$true,$false,$class($int))[0i]:\n");
        xml_.append("  $return $($int) f;.call(g;.,$null,$new [java.lang.Object[](5i)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
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
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public {} $class Inner<#U> {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T call(#T p){\n");
        xml_.append("  [#T a = $new [#T(1i):\n");
        xml_.append("  $Fct<[#T,[#T> c = $lambda([#T,clone):\n");
        xml_.append("  [#T instance = c;.call(a;.):\n");
        xml_.append("  instance;.[0i]=p;.;:\n");
        xml_.append("  $return instance;.[0i]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>():\n");
        xml_.append("  $return instance;.call(\"sample\"):\n");
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
        assertEq("sample", (String)ret_.getObject());
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public {} $class Inner<#U> {\n");
        xml_.append("  $public $int field:\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field;;;=p;.;:\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;;;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T call(#T p){\n");
        xml_.append("  $Fct<[#T> c = $new [#T(1i).$lambda([#T,clone):\n");
        xml_.append("  [#T instance = c;.call():\n");
        xml_.append("  instance;.[0i]=p;.;:\n");
        xml_.append("  $return instance;.[0i]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>():\n");
        xml_.append("  $return instance;.call(\"sample\"):\n");
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
        assertEq("sample", (String)ret_.getObject());
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i:\n");
        xml_.append(" $public $abstract $int get():\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  Ex instance = $new Ex():\n");
        xml_.append("  instance;.field=14i:\n");
        xml_.append("  $Fct<$int> f = instance;.$lambda(Ex,$super,get):\n");
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Fct<[$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<[$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<[$int,$int> g = $static().$lambda(Ex,exmethtwo,$int...):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[](8i,$new [$int[](5i,13i))):\n");
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
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Fct<[$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<[$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<[$int,$int> g = $static().$lambda(Ex,exmethtwo,$int...):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[](8i)):\n");
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
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Fct<$int,$int>,[java.lang.Object,java.lang.Object> f = $lambda($Fct<$int,$int>,call,java.lang.Object...):\n");
        xml_.append("  $Fct<$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int):\n");
        xml_.append("  $return $($int) f;.call(g;.,$new [java.lang.Object[]($null)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
}
