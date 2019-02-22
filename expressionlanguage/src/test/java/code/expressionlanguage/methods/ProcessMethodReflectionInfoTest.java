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
        xml_.append("  $return $static($Class).getAllClasses().length >= 10i:\n");
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
    public void processEl334Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($null).makeWildCard($null).getName():\n");
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
    public void processEl335Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($null).makeWildCard($true).getName():\n");
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
    public void processEl336Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($null).makeWildCard($false).getName():\n");
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
    public void processEl337Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).makeWildCard($true).getName():\n");
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
    public void processEl338Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).makeWildCard($false).getName():\n");
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
    public void processEl339Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($true).makeWildCard($true).getName():\n");
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
    public void processEl340Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).makeWildCard($false).getName():\n");
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
    public void processEl341Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(String).makeGeneric($class(Number)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl342Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class($Fct).makeGeneric():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl343Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(ExParam).makeGeneric($class(String)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:Number> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl344Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam).makeGeneric($class(Integer)).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:Number> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam<java.lang.Integer>",out_.getString());
    }
    @Test
    public void processEl345Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeGeneric().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.String",out_.getString());
    }
    @Test
    public void processEl346Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(String).getComponentType():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl347Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String[]).getComponentType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.String",out_.getString());
    }
    @Test
    public void processEl348Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String[]).makeWildCard($true).getComponentType().getName():\n");
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
    public void processEl349Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String[]).makeWildCard($false).getComponentType().getName():\n");
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
    public void processEl350Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(String[]).makeWildCard($null).getComponentType():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl351Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class($Fct).makeGeneric($class(String),$class(Number)).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.$Fct<java.lang.String,java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl352Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class($Fct).makeGeneric($class(String).makeWildCard($false),$class(Number).makeWildCard($true)).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.$Fct<java.lang.String,java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl353Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class($Fct).makeGeneric($class(String).makeWildCard($null)).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.$Fct<java.lang.Object>",out_.getString());
    }
    @Test
    public void processEl354Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).makeWildCard($false).getName():\n");
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
    public void processEl355Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(ExParam).makeGeneric($class(ExParam).getDeclaredFields()[0].getGenericType()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:Number> {\n");
        xml_.append(" $public T inst:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl356Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<Number>).getActualTypeArguments()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:Number> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.Number",out_.getString());
    }
    @Test
    public void processEl357Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<Number>).getTypeParameters()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:Number> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("#T",out_.getString());
    }
    @Test
    public void processEl358Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<Number>).getTypeParameters()[0].getBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.Object",out_.getString());
    }
    @Test
    public void processEl359Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<Number>).getTypeParameters()[0].getBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:Number> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.Number",out_.getString());
    }
    @Test
    public void processEl360Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<Number,ExParamTwo<Number>>).getTypeParameters()[1].getBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<S,T:ExParamTwo<S>> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParamTwo<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParamTwo<java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl361Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<ExFive>).getTypeParameters()[0].getBounds()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour",out_.getString());
    }
    @Test
    public void processEl362Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<Number,ExParamTwo<Number>>).getTypeParameters()[1].getGenericBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<S,T:ExParamTwo<S>> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParamTwo<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParamTwo<#S>",out_.getString());
    }
    @Test
    public void processEl363Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).getTypeParameters().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq(0,out_.getNumber());
    }
    @Test
    public void processEl364Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).getBounds().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq(0,out_.getNumber());
    }
    @Test
    public void processEl365Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam).getTypeParameters()[1].getBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<S,T:ExParamTwo<S>> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParamTwo<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParamTwo<#S>",out_.getString());
    }
    @Test
    public void processEl366Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(ExParam).getBounds().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<S,T:ExParamTwo<S>> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParamTwo<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq(0,out_.getNumber());
    }
    @Test
    public void processEl367Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(ExParam).getUpperBounds().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<S,T:ExParamTwo<S>> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParamTwo<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq(0,out_.getNumber());
    }
    @Test
    public void processEl368Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(ExParam).getLowerBounds().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<S,T:ExParamTwo<S>> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParamTwo<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq(0,out_.getNumber());
    }
    @Test
    public void processEl369Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<!String>).getActualTypeArguments()[0].getLowerBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.String",out_.getString());
    }
    @Test
    public void processEl370Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<?String>).getActualTypeArguments()[0].getUpperBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.String",out_.getString());
    }
    @Test
    public void processEl371Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam).getTypeParameters()[0].makeWildCard($false).getLowerBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("#T",out_.getString());
    }
    @Test
    public void processEl372Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam).getTypeParameters()[0].makeWildCard($true).getUpperBounds()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("#T",out_.getString());
    }
    @Test
    public void processEl373Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<String>).getTypeParameters()[0].getVariableOwner().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam<java.lang.String>",out_.getString());
    }
    @Test
    public void processEl374Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<String>).getTypeParameters()[0].getGenericVariableOwner().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam",out_.getString());
    }
    @Test
    public void processEl375Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam).getTypeParameters()[0].getVariableOwner().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam",out_.getString());
    }
    @Test
    public void processEl376Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam).getTypeParameters()[0].getGenericVariableOwner().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam",out_.getString());
    }
    @Test
    public void processEl377Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(ExParam<String>).getActualTypeArguments()[0].getVariableOwner():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl378Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(ExParam<String>).getActualTypeArguments()[0].getGenericVariableOwner():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl379Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExFive).getInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree",out_.getString());
    }
    @Test
    public void processEl380Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExFive).getInterfaces()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour",out_.getString());
    }
    @Test
    public void processEl381Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(ExThree).getInterfaces().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq(0,out_.getNumber());
    }
    @Test
    public void processEl382Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree:ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour",out_.getString());
    }
    @Test
    public void processEl383Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExSix).getInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSix:ExParam<ExFive> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam<pkg.ExFive>",out_.getString());
    }
    @Test
    public void processEl384Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExSix<ExFive>).getInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSix<T:ExThree&ExFour>:ExParam<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam<pkg.ExFive>",out_.getString());
    }
    @Test
    public void processEl385Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExFive).getGenericInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree",out_.getString());
    }
    @Test
    public void processEl386Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExFive).getGenericInterfaces()[1].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour",out_.getString());
    }
    @Test
    public void processEl387Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $class(ExThree).getGenericInterfaces().length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq(0,out_.getNumber());
    }
    @Test
    public void processEl388Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getGenericInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree:ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour",out_.getString());
    }
    @Test
    public void processEl389Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExSix).getGenericInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSix:ExParam<ExFive> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam<pkg.ExFive>",out_.getString());
    }
    @Test
    public void processEl390Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExSix<ExFive>).getGenericInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSix<T:ExThree&ExFour>:ExParam<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExParam<T:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam<#T>",out_.getString());
    }
    @Test
    public void processEl391Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree:ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour",out_.getString());
    }
    @Test
    public void processEl392Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getGenericSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree:ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour",out_.getString());
    }
    @Test
    public void processEl393Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree:ExFour<Number>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour<java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl394Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getGenericSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree:ExFour<Number>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour<java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl395Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<Number>).getSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>:ExFour<S>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour<java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl396Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<Number>).getGenericSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>:ExFour<S>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour<#S>",out_.getString());
    }
    @Test
    public void processEl397Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(ExThree).getSuperClass():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree:ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl398Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.Object",out_.getString());
    }
    @Test
    public void processEl399Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(ExThree).getGenericSuperClass():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree:ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl400Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getGenericSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.Object",out_.getString());
    }
    @Test
    public void processEl401Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(Object).getSuperClass():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl402Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(Object).getGenericSuperClass():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl403Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExSix).getInterfaces()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSix<T:ExThree&ExFour>:ExParam<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExParam<U:ExThree&ExFour> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFour{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFive:ExThree:ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExParam<#T>",out_.getString());
    }
    @Test
    public void processEl404Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getSuperClass().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>:ExFour<S>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExFour<#S>",out_.getString());
    }
    @Test
    public void processEl405Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree).getDeclaredClasses()[0].getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append(" $public $enum ExEnum{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree..ExEnum",out_.getString());
    }
    @Test
    public void processEl406Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree..ExEnum).getEnclosingType().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append(" $public $enum ExEnum{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree",out_.getString());
    }
    @Test
    public void processEl407Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(Number).getEnclosingType():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl408Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(ExTwo).getEnclosingType():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void processEl409Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(Number).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("java.lang.Number",out_.getString());
    }
    @Test
    public void processEl410Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExTwo).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExTwo",out_.getString());
    }
    @Test
    public void processEl411Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree..ExEnum).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append(" $public $enum ExEnum{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree..ExEnum",out_.getString());
    }
    @Test
    public void processEl412Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree..ExEnum[]).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append(" $public $enum ExEnum{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree..ExEnum[]",out_.getString());
    }
    @Test
    public void processEl413Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExTwo[]).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExTwo[]",out_.getString());
    }
    @Test
    public void processEl414Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<Number>).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl415Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<Number[]>).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<java.lang.Number[]>",out_.getString());
    }
    @Test
    public void processEl416Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<Number>[]).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<java.lang.Number>[]",out_.getString());
    }
    @Test
    public void processEl417Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<!Number>).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<!java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl418Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<!Number[]>).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<!java.lang.Number[]>",out_.getString());
    }
    @Test
    public void processEl419Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<?Number>).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<?java.lang.Number>",out_.getString());
    }
    @Test
    public void processEl420Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<?Number[]>).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<?java.lang.Number[]>",out_.getString());
    }
    @Test
    public void processEl421Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExThree<?>).getPrettyName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("pkg.ExThree<?>",out_.getString());
    }
    @Test
    public void processEl422Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree<?>).isInstance($new ExThree<Number>()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl423Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree).getDeclaredFields()[0].getGenericType().isInstance($new ExThree<Number>()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append(" S inst:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl424Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree<?Number>).isInstance($new ExThree<String>()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl425Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Number).isAssignableFrom($class(Integer)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl426Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(String).isAssignableFrom($class(Integer)):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl427Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree).isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl428Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree).isPublic():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl429Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree).isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl430Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree).isPackage():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl431Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree).isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl432Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExThree).isProtected():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$protected $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl433Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isPrivate():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl434Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isPrivate():\n");
        xml_.append(" }\n");
        xml_.append(" $private $static $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl435Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class($int).isPrimitive():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl436Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExTwo).isPrimitive():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl437Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExTwo[]).isArray():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl438Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExTwo).isArray():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl439Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExTwo).makeWildCard($false).isWildCard():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl440Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(ExTwo).isWildCard():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl441Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Number).isAbstract():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl442Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Integer).isAbstract():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl443Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Integer).isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl444Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Number).isFinal():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl445Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isStatic():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl446Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isStatic():\n");
        xml_.append(" }\n");
        xml_.append(" $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl447Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isClass():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl448Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isClass():\n");
        xml_.append(" }\n");
        xml_.append(" $static $interface ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl449Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isEnum():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl450Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isEnum():\n");
        xml_.append(" }\n");
        xml_.append(" $static $enum ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl451Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isInterface():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl452Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isInterface():\n");
        xml_.append(" }\n");
        xml_.append(" $static $interface ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }

    @Test
    public void processEl453Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).getDeclaredFields()[0].getGenericType().isTypeVariable():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree<T>{\n");
        xml_.append("  ..ExThree<T> inst:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl454Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).getDeclaredFields()[0].getGenericType().isTypeVariable():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree<T>{\n");
        xml_.append("  T inst:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl455Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).getDeclaredFields()[0].getGenericType().isVariable():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree<T>{\n");
        xml_.append("  ..ExThree<T> inst:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
    }
    @Test
    public void processEl456Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree<Number>).isVariable():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree<T>{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl457Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isAnnotation():\n");
        xml_.append(" }\n");
        xml_.append(" $static $class ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isFalse());
    }
    @Test
    public void processEl458Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(..ExThree).isAnnotation():\n");
        xml_.append(" }\n");
        xml_.append(" $static $annotation ExThree{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isTrue());
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
        xml_.append("  $return $Class.getOperators(\"-\",$false,$class(Ex)).length:\n");
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
        xml_.append("  $return $Class.getOperators(\"+\",$false,$class(Ex)).length:\n");
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
    @Test
    public void calculate158Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $Class exmeth(){\n");
        xml_.append("  $return $class($void).makeArray():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertTrue(out_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void calculate159Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeArray().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("[java.lang.String",out_.getString());
    }
    @Test
    public void calculate160Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($false).makeArray().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("![java.lang.String",out_.getString());
    }
    @Test
    public void calculate161Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($true).makeArray().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("?[java.lang.String",out_.getString());
    }
    @Test
    public void calculate162Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(String).makeWildCard($null).makeArray().getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.ExTwo", id_, args_, cont_);
        assertEq("?[java.lang.Object",out_.getString());
    }
    @Test
    public void calculate163Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $Class.getOperators(\"+\",$true,$class(Ex)).length:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        xml_.append("$operator+ pkg.Ex(pkg.Ex... p) {\n");
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
    @Test
    public void calculate164Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $ObjectsUtil.getParent($class(pkg.Ex).getDeclaredMethods()[0]):\n");
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
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void calculate165Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $ObjectsUtil.getParent($class(pkg.Ex).getDeclaredConstructors()[0]):\n");
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
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void calculate166Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $ObjectsUtil.getParent($class(pkg.Ex).getDeclaredFields()[0]):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int field:\n");
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
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void calculate167Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $ObjectsUtil.getParent($class(pkg.Ex)):\n");
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
        assertTrue(ret_.isNull());
        assertNull(cont_.getException());
    }
    @Test
    public void calculate168Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0] == $class(pkg.Ex).getDeclaredMethods()[0]:\n");
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
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate169Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0] == $class(pkg.Ex).getDeclaredMethods()[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeththree($int e){\n");
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
    public void calculate170Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0] == $class(pkg.ExTwo).getDeclaredMethods()[0]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeththree($int e){\n");
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
    public void calculate171Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Method m1 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  $Method m2 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  m1;.setPolymorph($false):\n");
        xml_.append("  $return m1;. != m2;.:\n");
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
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate172Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Method m1 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  $Method m2 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  m2;.setPolymorph($false):\n");
        xml_.append("  $return m1;. != m2;.:\n");
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
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate173Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Method m1 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  $Method m2 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  m1;.setPolymorph($false):\n");
        xml_.append("  m2;.setPolymorph($false):\n");
        xml_.append("  $return m1;. == m2;.:\n");
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
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate174Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Method m1 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  $Method m2 = $class(pkg.Ex).getDeclaredMethods()[0]:\n");
        xml_.append("  $return m1;. == m2;.:\n");
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
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculate175Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p){\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q){\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $Class.getOperators().length:\n");
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
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculate176Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors()[0]:\n");
        xml_.append("  $Constructor d = $class(pkg.Ex).getDeclaredConstructors()[1]:\n");
        xml_.append("  $return c;. != d;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int e){\n");
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
    public void calculate177Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors()[0]:\n");
        xml_.append("  $return c;. == c;.:\n");
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
    public void calculate178Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors()[0]:\n");
        xml_.append("  $Constructor d = $class(pkg.ExTwo).getDeclaredConstructors()[0]:\n");
        xml_.append("  $return c;. != d;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
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
    public void calculate179Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex).getDeclaredMethods()[0] == $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeththree($int e){\n");
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
    public void calculate180Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors()[0]:\n");
        xml_.append("  $return c;. != $null:\n");
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
    public void calculate181Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Field f = $class(pkg.Ex).getDeclaredFields()[0]:\n");
        xml_.append("  $Field g = $class(pkg.Ex).getDeclaredFields()[1]:\n");
        xml_.append("  $return f;. != g;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int field:\n");
        xml_.append(" $public $static $int fieldTwo:\n");
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
    public void calculate182Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Field f = $class(pkg.Ex).getDeclaredFields()[0]:\n");
        xml_.append("  $return f;. == f;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int field:\n");
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
    public void calculate183Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Field f = $class(pkg.Ex).getDeclaredFields()[0]:\n");
        xml_.append("  $Field g = $class(pkg.ExTwo).getDeclaredFields()[0]:\n");
        xml_.append("  $return f;. != g;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int field:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int field:\n");
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
    public void calculate184Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Field f = $class(pkg.Ex).getDeclaredFields()[0]:\n");
        xml_.append("  $return f;. != $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int field:\n");
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
    public void calculate185Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Ex) != $class(ExTwo):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
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
    public void calculate186Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Ex).getTypeParameters()[0] != $class(ExTwo).getTypeParameters()[0]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
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
    public void calculate187Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Ex) == $class(pkg.Ex):\n");
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
    public void calculate188Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class(Ex) != $null:\n");
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
}
