package code.expressionlanguage.methods;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ClassesTest {
    @Test
    public void emptyClassesTest() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        cont_.getOptions().setMultipleAffectations(false);
        InitializationLgNames.initAdvStandards(cont_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        xml_.append(" $public $static $class InnerTwo:pkgtwo.OuterTwo..InnerThree {\n");
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
    public void getMethodBodiesByFormattedId1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger()), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#F"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E... i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getMethodBodiesByFormattedId8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.String"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getMethodBodiesByFormattedId10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(java.lang.Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(2, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
        assertTrue(!methods_.first().isVarargs());
        assertEq("instancemethod", methods_.last().getName());
        assertEq(1, methods_.last().getId().getParametersTypes().size());
        assertEq("java.lang.Object", methods_.last().getId().getParametersTypes().first());
        assertTrue(!methods_.last().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(0, methods_.first().getId().getParametersTypes().size());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,$int j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }
    @Test
    public void getMethodBodiesByFormattedId15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }
    @Test
    public void getMethodBodiesByFormattedId16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkgtwo.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }
    public static CustList<MethodBlock> getMethodBodiesByFormattedId(ContextEl _context, boolean _static, String _genericClassName, String _methodName, StringList _parametersTypes, boolean _vararg) {
        CustList<MethodBlock> ms_ = new CustList<MethodBlock>();
        for (GeneMethod g: TypeUtil.getMethodBodiesByFormattedId(_context, _static, _genericClassName, _methodName, _parametersTypes, _vararg)) {
            if (g instanceof MethodBlock) {
                ms_.add((MethodBlock) g);
            }
        }
        return ms_;
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
        assertTrue(str_.getInstance() instanceof Integer);
        assertEq(2, ((Number)str_.getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertTrue(str_.getInstance() instanceof Integer);
        assertEq(5, ((Number)str_.getInstance()).intValue());
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
        assertTrue(str_.getInstance() instanceof Integer);
        assertEq(1, ((Number)str_.getInstance()).intValue());
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
        assertTrue(str_.getInstance() instanceof Integer);
        assertEq(1, ((Number)str_.getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myt"));
        assertTrue(str_.getInstance() instanceof Integer);
        assertEq(3, ((Number)str_.getInstance()).intValue());
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
        assertTrue(str_.getInstance() instanceof Integer);
        assertEq(127, ((Number)str_.getInstance()).intValue());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertTrue(str_.getInstance() instanceof Integer);
        assertEq(129, ((Number)str_.getInstance()).intValue());
    }
    private ContextEl validateStaticFields(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        cont_.getOptions().setMultipleAffectations(false);
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateSingleParameterizedClasses(cont_);
        classes_.validateIds(cont_);
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.initStaticFields(cont_);
        return cont_;
    }
    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        cont_.getOptions().setMultipleAffectations(false);
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateIds(cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private ContextEl unfullValidateInheritingClasses(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        cont_.getOptions().setMultipleAffectations(false);
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private ContextEl failValidateInheritingClasses(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        cont_.getOptions().setMultipleAffectations(false);
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), !classes_.isEmptyErrors());
        return cont_;
    }
    private ContextEl unfullValidateOverridingClasses(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        cont_.getOptions().setMultipleAffectations(false);
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateSingleParameterizedClasses(cont_);
        classes_.validateIds(cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
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
}
