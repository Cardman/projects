package code.expressionlanguage.methods;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import code.expressionlanguage.structs.*;
import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;


public final class ClassesTest {
    @Test
    public void emptyClassesTest() {
        StringMap<String> files_ = new StringMap<String>();
        Options opt_ = new Options();
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getMemoryError());
    }

    @Test
    public void resolve1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }

    @Test
    public void resolve2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }

    @Test
    public void resolve3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }

    @Test
    public void resolve4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.ExThree;\n");
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }

    @Test
    public void resolve5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }

    @Test
    public void resolve6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }

    @Test
    public void resolve7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("pkgthree.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        xml_.append("$public $class pkgthree.ExFour<#T> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:ExThree<#T>> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        CustList<TypeVar> types_ = context_.getClassBody("pkg.ExTwo").getParamTypesMapValues();
        assertEq(1, types_.size());
        assertEq("T", types_.first().getName());
        assertEq(1, types_.first().getConstraints().size());
        assertEq("pkg.ExThree<#T>", types_.first().getConstraints().first());
    }
    @Test
    public void resolve9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.ExThree;\n");
        xml_.append("$public $class pkg.ExTwo<#T:ExThree<#T>> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        CustList<TypeVar> types_ = context_.getClassBody("pkg.ExTwo").getParamTypesMapValues();
        assertEq(1, types_.size());
        assertEq("T", types_.first().getName());
        assertEq(1, types_.first().getConstraints().size());
        assertEq("pkgtwo.ExThree<#T>", types_.first().getConstraints().first());
    }
    @Test
    public void resolve10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T:ExThree<#T>> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        CustList<TypeVar> types_ = context_.getClassBody("pkg.ExTwo").getParamTypesMapValues();
        assertEq(1, types_.size());
        assertEq("T", types_.first().getName());
        assertEq(1, types_.first().getConstraints().size());
        assertEq("pkgtwo.ExThree<#T>", types_.first().getConstraints().first());
    }
    @Test
    public void resolve11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:pkg.Outer..Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        unfullValidateInheritingClasses(files_);
    }
    @Test
    public void resolve12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:Outer..Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        unfullValidateInheritingClasses(files_);
    }
    @Test
    public void resolve13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:..Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        unfullValidateInheritingClasses(files_);
    }
    @Test
    public void resolve14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        unfullValidateInheritingClasses(files_);
    }
    @Test
    public void resolve15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        unfullValidateInheritingClasses(files_);
    }

    @Test
    public void resolve16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<pkg.ExThree<?#T>>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<pkg.ExThree<?>>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<pkg.ExThree<!#T>>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<pkg.ExThree<?#T[]>>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<pkg.ExThree<!#T[]>>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.ExTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.Ex", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkg.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkg.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo..InnerThree;pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo..InnerThree;pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo..InnerThree;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClassBody("pkg.Outer..InnerTwo").getAllSuperClasses();
        assertEq(2, types_.size());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.first());
        assertEq(context_.getStandards().getAliasObject(), types_.last());
    }
    @Test
    public void resolve1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T:ExFour<#T>> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :ExFour<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E:pkg.Ex<#E>> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E:pkg.Ex<#E>> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExThree<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.ExTwo<#S,#T:pkg.Ex<#S>>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:pkg.Outer..Inner {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<?#T>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<?>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.Ex;\n");
        xml_.append("$public $class pkg.ExTwo<#T> :Ex<!#T>{}\n");
        xml_.append("$public $class pkg.ExThree<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve12FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve13FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:pkgtwo.OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve14FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:pkgtwo.OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve15FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo..InnerThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve16FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve17FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:pkgtwo.OuterTwo..InnerThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve18FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:pkgtwo.OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }

    @Test
    public void getAllOverridingMethods1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        InterfaceBlock i_ = (InterfaceBlock) context_.getClassBody("pkg.ExFour");
        ObjectMap<MethodId, StringList> sgn_ = toList(TypeUtil.getAllInstanceSignatures(i_, context_));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExFour"),sgn_.getVal(new MethodId(false, "absgetter", new StringList())));
    }

    @Test
    public void getAllOverridingMethods2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        InterfaceBlock i_ = (InterfaceBlock) context_.getClassBody("pkg.Ex");
        ObjectMap<MethodId, StringList> sgn_ = toList(TypeUtil.getAllInstanceSignatures(i_, context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex","pkg.ExFour"),sgn_.getVal(new MethodId(false, "absgetter", new StringList())));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex"),sgn_.getVal(new MethodId(false, "absgetter", new StringList())));
    }

    @Test
    public void getAllOverridingMethods3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        InterfaceBlock i_ = (InterfaceBlock) context_.getClassBody("pkg.Ex");
        ObjectMap<MethodId, StringList> sgn_ = toList(TypeUtil.getAllInstanceSignatures(i_, context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new MethodId(false,"absgetter", new StringList())));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new MethodId(false,"absgetter", new StringList())));
    }
    @Test
    public void calculateStaticField1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=2i:\n");
        xml_.append(" $public $static $final $int mys=myf;;;+3i:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, (((NumberStruct)str_).getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(0, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=$static(pkg.ExThree).myf;;;:\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(0, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=1i:\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(1, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=1i:\n");
        xml_.append(" $public $static $final $int myt=mys;;;+2i:\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(1, (((NumberStruct)str_).getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myt"));
        assertEq(3, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=meth():\n");
        xml_.append(" $public $static $final $int mys=myf;;;+2i:\n");
        xml_.append(" $public $static $int meth(){\n");
        xml_.append("  $return 5i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(0, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=$static(java.lang.Byte).MAX_VALUE:\n");
        xml_.append(" $public $static $final $int mys=myf;;;+2i:\n");
        xml_.append(" $public $static $int meth(){\n");
        xml_.append("  $return 5i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(127, (((NumberStruct)str_).getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(129, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=2i,mys=myf;;;+3i:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, (((NumberStruct)str_).getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte myf=2i,mys=myf;;;+3i:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, (((NumberStruct)str_).getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=meth(),mys=5i:\n");
        xml_.append(" $public $static $int meth(){\n");
        xml_.append("  $return 5i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=myf;;;+3i:\n");
        xml_.append(" $public $static $final $int myf=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, (((NumberStruct)str_).getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=\"\".toString().length()+myf+1:\n");
        xml_.append(" $public $static $final $int myf=1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(1, (((NumberStruct)str_).getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(2, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void calculateStaticField13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=($int)$math.random():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(0, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Character ch=$null:\n");
        xml_.append(" $public $static $final $int field=\"\".splitChars(ch).length:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "ch"));
        assertSame(NullStruct.NULL_VALUE,str_);
    }

    @Test
    public void calculateStaticField16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne^iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne|iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne&iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne<<iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne>>iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne<<<iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne>>>iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne<<<<iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne>>>>iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne^iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo^iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne&iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo&iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne|iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo|iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne<<iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo<<iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne>>iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo>>iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne<<<iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField36Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo<<<iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField37Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne>>>iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField38Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo>>>iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField39Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne<<<<iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo<<<<iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField41Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne>>>>iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField42Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo>>>>iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }

    @Test
    public void calculateStaticField43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int field=~iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(-1,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int field=-iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int field=+iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int field=~iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }

    @Test
    public void calculateStaticField47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int field=-iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }

    @Test
    public void calculateStaticField48Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int field=+iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }

    @Test
    public void calculateStaticField49Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne^iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField50Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne&iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField51Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne|iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField52Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne<<iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField53Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne>>iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField54Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne<<<iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField55Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne>>>iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField56Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne<<<<iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField57Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne>>>>iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=~iOne:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(-1,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=-iOne:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=+iOne:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne!=iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne==iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }

    @Test
    public void calculateStaticField63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=!prfield:\n");
        xml_.append(" $public $static $final $boolean prfield=iOne!=iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(4, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField64Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=!prfield:\n");
        xml_.append(" $public $static $final $boolean prfield=iOne==iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(4, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField65Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne*iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField66Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne/iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField67Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne%iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField68Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne!=iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField69Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne==iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne&&iTwo:\n");
        xml_.append(" $public $static $final $boolean iOne=$true:\n");
        xml_.append(" $public $static $final $boolean iTwo=$true:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne||iTwo:\n");
        xml_.append(" $public $static $final $boolean iOne=$false:\n");
        xml_.append(" $public $static $final $boolean iTwo=$false:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne<iTwo:\n");
        xml_.append(" $public $static $final String iOne=\"\":\n");
        xml_.append(" $public $static $final String iTwo=\"\":\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField73Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne<=iTwo:\n");
        xml_.append(" $public $static $final String iOne=\"\":\n");
        xml_.append(" $public $static $final String iTwo=\"\":\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField74Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>iTwo:\n");
        xml_.append(" $public $static $final String iOne=\"\":\n");
        xml_.append(" $public $static $final String iTwo=\"\":\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField75Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>=iTwo:\n");
        xml_.append(" $public $static $final String iOne=\"\":\n");
        xml_.append(" $public $static $final String iTwo=\"\":\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField76Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final String field=iOne+iTwo:\n");
        xml_.append(" $public $static $final String iOne=\"\":\n");
        xml_.append(" $public $static $final String iTwo=\"\":\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq("",((StringStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField77Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final String field=iOne.substring(0):\n");
        xml_.append(" $public $static $final String iOne=\"\":\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq("",((StringStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField78Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne<iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne<=iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(!((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>=iTwo:\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Character ch=$null:\n");
        xml_.append(" $public $static $final $int field=\"\".splitChars($vararg($char),$firstopt(ch)).length:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "ch"));
        assertSame(NullStruct.NULL_VALUE,str_);
    }
    @Test
    public void calculateStaticField83Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final String ch=$null:\n");
        xml_.append(" $public $static $final $int field=\"\".splitStrings($vararg(String),0,$firstopt(ch)).length:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "ch"));
        assertSame(NullStruct.NULL_VALUE,str_);
    }
    @Test
    public void calculateStaticField84Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=\"\".splitChars($vararg($char),$firstopt(ch)).length:\n");
        xml_.append(" $public $static $final Character ch=$null:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "ch"));
        assertSame(NullStruct.NULL_VALUE,str_);
    }
    @Test
    public void calculateStaticField85Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=\"\".splitStrings($vararg(String),0,$firstopt(ch)).length:\n");
        xml_.append(" $public $static $final String ch=$null:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "ch"));
        assertSame(NullStruct.NULL_VALUE,str_);
    }
    @Test
    public void calculateStaticField86Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=(iOne>=iTwo):\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(((BooleanStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField87Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=(iOne>=iTwo):\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField88Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=(iTwo>=iOne):\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField89Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=$bool(iTwo>=iOne,2,5):\n");
        xml_.append(" $public $static $final $int iOne=0:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(2, ((NumberStruct)str_).getInstance());
    }
    @Test
    public void calculateStaticField90Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=$bool(iOne,2,5):\n");
        xml_.append(" $public $static $final Boolean iOne=$null:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField91Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne+iTwo:\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField92Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iTwo+iOne:\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField93Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne*iTwo:\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField94Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iTwo*iOne:\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField95Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne+iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField96Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo+iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField97Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iOne*iTwo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField98Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null:\n");
        xml_.append(" $public $static $final $int iTwo=0:\n");
        xml_.append(" $public $static $final $int field=iTwo*iOne:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField99Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;;;:\n");
        xml_.append(" $public $static $final $int mys=$static(pkg.ExThree).mys;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;;;:\n");
        xml_.append(" $public $static $final $int mys=$static(pkg.ExTwo).mys;;;:\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(0, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField100Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=~-1b:\n");
        xml_.append(" $public $static $final $boolean mys=$true&&m():\n");
        xml_.append(" $public $static $boolean m(){$return $true:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(0, (((NumberStruct)str_).getInstance()).intValue());
    }
    @Test
    public void validateElFailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$public $static $int inner(){$return 0:}$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){$this():}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$public $static $int inner:$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$public $static $class Inn{}$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){((String)$null<(String)$null):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($vararg($int)):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$new $int[]{$vararg($int)}:$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("@Annot($vararg($int))\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0<\"\"):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(\"\"<0):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0<$false):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl12FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false<0):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl13FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false+0):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl14FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0+$false):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl15FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false-0):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl16FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0-$false):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl17FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false*0):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl18FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0*$false):$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl19FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$int $v = 0:$int $v = 0:$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl20FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $true&0:\n");
        xml_.append(" $int a2 = 0&$true:\n");
        xml_.append(" $int a3 = $true|0:\n");
        xml_.append(" $int a4 = 0|$true:\n");
        xml_.append(" $int a5 = $true^0:\n");
        xml_.append(" $int a6 = 0^$true:\n");
        xml_.append(" $int a7 = $true<<0:\n");
        xml_.append(" $int a8 = 0<<$true:\n");
        xml_.append(" $int a9 = $true<<<0:\n");
        xml_.append(" $int a10 = 0<<<$true:\n");
        xml_.append(" $int a11 = $true<<<<0:\n");
        xml_.append(" $int a12 = 0<<<<$true:\n");
        xml_.append(" $int a13 = $true>>0:\n");
        xml_.append(" $int a14 = 0>>$true:\n");
        xml_.append(" $int a15 = $true>>>0:\n");
        xml_.append(" $int a16 = 0>>>$true:\n");
        xml_.append(" $int a17 = $true>>>>0:\n");
        xml_.append(" $int a18 = 0>>>>$true:\n");
        xml_.append(" $int a19 = -$true:\n");
        xml_.append(" $int a20 = ~$true:\n");
        xml_.append(" $int a21 = $true&&0:\n");
        xml_.append(" $int a22 = 0&&$true:\n");
        xml_.append(" $int a23 = \"\"+(ExTwo)$null:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl21FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[\"\"]:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl31FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[]{\"\"}:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl32FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = {\"\"}:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl33FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[]{\"\"}.len:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void validateEl34FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[]{\"\"}.clo:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    private ContextEl validateStaticFields(StringMap<String> _files) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.initStaticFields(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private ContextEl unfullValidateInheritingClasses(StringMap<String> _files) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private void failValidateInheritingClasses(StringMap<String> _files) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), !classes_.isEmptyErrors());
    }

    private ObjectMap<MethodId, StringList> toList(ObjectMap<MethodId, EqList<ClassMethodId>> _m) {
        ObjectMap<MethodId, StringList> m_ = new ObjectMap<MethodId, StringList>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _m.entryList()) {
            StringList l_ = new StringList();
            for (ClassMethodId c: e.getValue()) {
                l_.add(c.getClassName());
            }
            m_.put(e.getKey(), l_);
        }
        return m_;
    }

    private ObjectMap<MethodId, EqList<ClassMethodId>> toId(ObjectMap<MethodId, StringList> _m) {
        ObjectMap<MethodId, EqList<ClassMethodId>> m_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (EntryCust<MethodId, StringList> e: _m.entryList()) {
            EqList<ClassMethodId> l_ = new EqList<ClassMethodId>();
            for (String c: e.getValue()) {
                l_.add(new ClassMethodId(c, e.getKey()));
            }
            m_.put(e.getKey(), l_);
        }
        return m_;
    }
    protected static ContextEl contextEl(int... _m) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }

}
