package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodReflectionInfoTest extends ProcessMethodCommon {
    @Test
    public void processEl220Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class($int).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$int", ret_.getString());
    }
    @Test
    public void processEl221Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(java.lang.Integer).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Integer", ret_.getString());
    }
    @Test
    public void processEl222Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex", ret_.getString());
    }
    @Test
    public void processEl223Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex", ret_.getString());
    }
    @Test
    public void processEl224Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $new pkg.Ex<java.lang.Integer>().exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(#T).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Integer", ret_.getString());
    }
    @Test
    public void processEl225Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $new pkg.Ex<java.lang.Integer>().exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T>).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex<java.lang.Integer>", ret_.getString());
    }
    @Test
    public void processEl226Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass(\"\").getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.String", ret_.getString());
    }
    @Test
    public void processEl227Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass(1i).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Integer", ret_.getString());
    }
    @Test
    public void processEl228Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $Class exmeth(){\n");
        xml_.append("  $return $static($Class).getClass($null):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl229Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass($new pkg.Ex<java.lang.Integer>()).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T>).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex<java.lang.Integer>", ret_.getString());
    }
    @Test
    public void processEl230Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class($int[]).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[$int", ret_.getString());
    }
    @Test
    public void processEl231Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(java.lang.Integer[]).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[java.lang.Integer", ret_.getString());
    }
    @Test
    public void processEl232Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void processEl233Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void processEl234Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<java.lang.Integer>[]).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex<java.lang.Integer>", ret_.getString());
    }
    @Test
    public void processEl235Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $new pkg.Ex<java.lang.Integer>().exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(#T[]).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[java.lang.Integer", ret_.getString());
    }
    @Test
    public void processEl236Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $new pkg.Ex<java.lang.Integer>().exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T[]>).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex<[java.lang.Integer>", ret_.getString());
    }
    @Test
    public void processEl237Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass($new $int[]{1i}).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[$int", ret_.getString());
    }
    @Test
    public void processEl240Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class($void).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$void", ret_.getString());
    }

    @Test
    public void processEl277Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $static($Class).getAllClasses().length >= 50i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void processEl331Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($null).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("?",out_.getString());
    }
    @Test
    public void processEl332Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($true).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("?java.lang.String",out_.getString());
    }

    @Test
    public void processEl333Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("!java.lang.String",out_.getString());
    }
    @Test
    public void calculate1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("inst", ret_.getString());
    }
    @Test
    public void calculate2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].getType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$int", ret_.getString());
    }
    @Test
    public void calculate3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].getGenericType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$int", ret_.getString());
    }
    @Test
    public void calculate4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].getType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$int", ret_.getString());
    }
    @Test
    public void calculate5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].getDeclaringClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex", ret_.getString());
    }
    @Test
    public void calculate6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredFields()[0].getType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Number", ret_.getString());
    }
    @Test
    public void calculate7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredFields()[0].getGenericType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T", ret_.getString());
    }
    @Test
    public void calculate8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].getType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T", ret_.getString());
    }
    @Test
    public void calculate9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].getGenericType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T", ret_.getString());
    }
    @Test
    public void calculate10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredFields()[0].isStatic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isStatic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $protected $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isPrivate():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields()[0].isPrivate():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $private $static $final $int inst = 0:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("exmeth",ret_.getString());
    }
    @Test
    public void calculate23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$int",ret_.getString());
    }
    @Test
    public void calculate24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$int",ret_.getString());
    }
    @Test
    public void calculate25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredMethods()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Number",ret_.getString());
    }
    @Test
    public void calculate26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredMethods()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredMethods()[0].getDeclaringClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex<java.lang.Number>",ret_.getString());
    }
    @Test
    public void calculate30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getDeclaringClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex",ret_.getString());
    }
    @Test
    public void calculate31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p){\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $Class exmeth(){\n");
        xml_.append("  $return $Class.getOperators()[0].getDeclaringClass():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void calculate32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($byte e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($byte e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredMethods()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int exmeth(T e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Number",ret_.getString());
    }
    @Test
    public void calculate35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredMethods()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int exmeth(T e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int exmeth(T e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int exmeth(T e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $protected $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $protected $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isPrivate():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $private $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isPrivate():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $private $static $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isStatic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isStatic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isNormal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isNormal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isAbstract():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int exmethtwo($int e):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isAbstract():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int exmethtwo($int e):\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isVarargs():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int... e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[1].isVarargs():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($int... e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($byte e, $long... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($byte e, $long... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredMethods()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Number",ret_.getString());
    }
    @Test
    public void calculate59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredMethods()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($byte e, $long... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[$long",ret_.getString());
    }
    @Test
    public void calculate63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($byte e, $long... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[$long",ret_.getString());
    }
    @Test
    public void calculate64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredMethods()[0].getParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[java.lang.String",ret_.getString());
    }
    @Test
    public void calculate65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredMethods()[0].getGenericParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[#S",ret_.getString());
    }
    @Test
    public void calculate66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[#S",ret_.getString());
    }
    @Test
    public void calculate67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].getGenericParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public $int exmeth(T e, S... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[#S",ret_.getString());
    }
    @Test
    public void calculate68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0].isPolymorph():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  m;.setPolymorph($false):\n");
        xml_.append("  $return m;.isPolymorph():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }

    @Test
    public void calculate70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex",ret_.getString());
    }
    @Test
    public void calculate71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$void",ret_.getString());
    }
    @Test
    public void calculate72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$void",ret_.getString());
    }
    @Test
    public void calculate73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredConstructors()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$void",ret_.getString());
    }
    @Test
    public void calculate74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredConstructors()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$void",ret_.getString());
    }
    @Test
    public void calculate75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$void",ret_.getString());
    }
    @Test
    public void calculate76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$void",ret_.getString());
    }
    @Test
    public void calculate77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredConstructors()[0].getDeclaringClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex<java.lang.Number>",ret_.getString());
    }
    @Test
    public void calculate78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getDeclaringClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T exmeth($int e){\n");
        xml_.append("  $return (T)0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex",ret_.getString());
    }
    @Test
    public void calculate79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredConstructors()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex<java.lang.Number>",ret_.getString());
    }
    @Test
    public void calculate80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex",ret_.getString());
    }

    @Test
    public void calculate81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($byte e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($byte e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredConstructors()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public Ex(T e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Number",ret_.getString());
    }
    @Test
    public void calculate84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredConstructors()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public Ex(T e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public Ex(T e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public Ex(T e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[1].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[1].isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" $protected Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[1].isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" $protected Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].isPrivate():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" $private Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[1].isPrivate():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" $private Ex($short e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }


    @Test
    public void calculate95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].isVarargs():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int... e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculate96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[1].isVarargs():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int e){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int... e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($byte e, $long... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($byte e, $long... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("$byte",ret_.getString());
    }
    @Test
    public void calculate99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredConstructors()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("java.lang.Number",ret_.getString());
    }
    @Test
    public void calculate100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredConstructors()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericParameterTypes()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("#T",ret_.getString());
    }
    @Test
    public void calculate103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($byte e, $long... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[$long",ret_.getString());
    }
    @Test
    public void calculate104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($byte e, $long... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[$long",ret_.getString());
    }
    @Test
    public void calculate105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredConstructors()[0].getParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[java.lang.String",ret_.getString());
    }
    @Test
    public void calculate106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number,String>).getDeclaredConstructors()[0].getGenericParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[#S",ret_.getString());
    }
    @Test
    public void calculate107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[#S",ret_.getString());
    }
    @Test
    public void calculate108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors()[0].getGenericParameterTypes()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T,S> {\n");
        xml_.append(" $public Ex(T e, S... f){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[#S",ret_.getString());
    }
    @Test
    public void calculate109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields(\"inst\")[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("inst", ret_.getString());
    }
    @Test
    public void calculate110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredFields(\"ance\").length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("clone", ret_.getString());
    }
    @Test
    public void calculate112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods()[0].getParameterTypes().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods()[0].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate114Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods()[0].isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate115Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return !$class(pkg.Ex[]).getDeclaredMethods()[0].isStatic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate116Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void calculate117Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>[]).getDeclaredMethods()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex<java.lang.Number>", ret_.getString());
    }
    @Test
    public void calculate118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>[]).getDeclaredMethods()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex<#T>", ret_.getString());
    }
    @Test
    public void calculate119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods()[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void calculate120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods()[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void calculate121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("clone", ret_.getString());
    }
    @Test
    public void calculate122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].getParameterTypes().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate124Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate125Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return !$class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].isStatic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate126Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void calculate127Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>[]).getDeclaredMethods(\"clone\",$false,$false)[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex<java.lang.Number>", ret_.getString());
    }
    @Test
    public void calculate128Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>[]).getDeclaredMethods(\"clone\",$false,$false)[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex<#T>", ret_.getString());
    }
    @Test
    public void calculate129Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].getReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void calculate130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false)[0].getGenericReturnType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("[pkg.Ex", ret_.getString());
    }
    @Test
    public void calculate131Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"\",$false,$false).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate132Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$true).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate133Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$true,$false).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate134Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex[]).getDeclaredMethods(\"clone\",$false,$false,$class($int)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate135Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$false,$class($long))[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($long e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("exmeth",ret_.getString());
    }
    @Test
    public void calculate136Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$false,$class($long)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($long e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($byte e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate137Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($long)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($long... e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($byte e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate138Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($short),$class($int)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($short e,$int... f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($byte e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate139Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class($short),$class($int)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($short e,$int f){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmeth($byte e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate140Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$false,$class($short)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int exmeth($short e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int exmethtwo($short e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate141Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredMethods(\"exmeth\",$false,$false,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int exmeth(T e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate142Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$false,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int exmeth(T e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0,ret_.getNumber());
    }
    @Test
    public void calculate143Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$false,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int exmeth(Number e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate144Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($false,$class($long))[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($long e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Ex",ret_.getString());
    }
    @Test
    public void calculate145Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($false,$class($long)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($long e){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($byte e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate146Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($true,$class($long)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($long... e){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($byte e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate147Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($true,$class($short),$class($int)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($short e,$int... f){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($byte e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate148Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($false,$class($short),$class($int)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($short e,$int f){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($byte e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate149Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<Number>).getDeclaredConstructors($false,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public Ex(T e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate150Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($false,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public Ex(T e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0,ret_.getNumber());
    }
    @Test
    public void calculate151Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($false,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public Ex(Number e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate152Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($false,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0,ret_.getNumber());
    }
    @Test
    public void calculate153Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($true,$class(Number)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0,ret_.getNumber());
    }
    @Test
    public void calculate154Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($true).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0,ret_.getNumber());
    }
    @Test
    public void calculate155Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredConstructors($false).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
    @Test
    public void calculate156Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $Class.getOperators(\"-\",$class(Ex)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        xml_.append("$operator+ pkg.Ex(pkg.Ex p) {\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(0,ret_.getNumber());
    }
    @Test
    public void calculate157Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $Class.getOperators(\"+\",$class(Ex)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        xml_.append("$operator+ pkg.Ex(pkg.Ex p) {\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(1,ret_.getNumber());
    }
}
