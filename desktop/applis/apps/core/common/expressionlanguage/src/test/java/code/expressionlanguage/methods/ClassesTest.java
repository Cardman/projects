package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.classes.CustLgNames;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.*;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;


public final class ClassesTest extends ProcessMethodCommon {
    @Test
    public void emptyClassesTest() {
        StringMap<String> files_ = new StringMap<String>();
        Options opt_ = new Options();
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyStdError());
        assertTrue(cont_.isEmptyErrors());
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        assertEq(0, AssignmentsUtil.getOrEmptyBefore(new CustList<StringMap<AssignmentBefore>>(),0).size());
        assertEq(0, AssignmentsUtil.getOrEmpty(new CustList<StringMap<Assignment>>(),0).size());
        assertEq(0, AssignmentsUtil.getOrEmptyBool(new CustList<StringMap<BooleanAssignment>>(),0).size());
        assertEq(0, AssignmentsUtil.getOrEmptySimple(new CustList<StringMap<SimpleAssignment>>(),0).size());
        assertEq(0, new AssignedVariables().getLastFieldsOrEmpty().size());
        assertEq(0, new AssignedVariables().getLastVariablesOrEmpty().size());
        assertEq(0, new AssignedVariables().getLastMutableLoopOrEmpty().size());
    }

    @Test
    public void failStd(){
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        lgName_.setAliasVoid("");
        Options opts_ = new Options();
        ContextEl out_ = ContextFactory.build(-1,lk_, di_, opts_, a_,kw_, lgName_,4);
        Classes.validateAll(new StringMap<String>(),out_);
        assertTrue(!out_.getClasses().isEmptyStdError());
        assertTrue(!out_.isEmptyErrors());
    }

    @Test
    public void failStd2(){
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        lgName_.setAliasPrimBoolean("$byte");
        lgName_.setAliasMaxValueField("MIN_VALUE");
        lgName_.setAliasLe("ge");
        lgName_.setAliasBoolean("java.lang.Byte");
        Options opts_ = new Options();
        ContextEl out_ = ContextFactory.build(-1,lk_, di_, opts_, a_,kw_, lgName_,4);
        Classes.validateAll(new StringMap<String>(),out_);
        assertTrue(!out_.getClasses().isEmptyStdError());
    }

    @Test
    public void failMessage(){
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        a_.setDefaultPkgNoMatch("");
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        lgName_.setAliasVoid("");
        Options opts_ = new Options();
        ContextEl out_ = ContextFactory.build(-1,lk_, di_, opts_, a_,kw_, lgName_,4);
        Classes.validateAll(new StringMap<String>(),out_);
        assertTrue(!out_.getClasses().isEmptyMessageError());
        assertTrue(!out_.isEmptyErrors());
    }
    @Test
    public void failMessage2(){
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        a_.setDefaultPkgNoMatch("");
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        ContextEl out_ = ContextFactory.build(-1,lk_, di_, opts_, a_,kw_, lgName_,4);
        Classes.validateAll(new StringMap<String>(),out_);
        assertTrue(!out_.getClasses().isEmptyMessageError());
        assertTrue(!out_.isEmptyErrors());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkg.Ex<#T>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<#T>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<#T>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<#T>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<#T>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkg.Ex<#T>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<#T>", types_.last());
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
        xml_.append(" $public $class InnerTwo:pkg.Outer.Inner {\n");
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
        xml_.append(" $public $class InnerTwo:Outer.Inner {\n");
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
        xml_.append(" $public $class InnerTwo:Inner {\n");
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
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<pkg.ExThree<?#T>>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<pkg.ExThree<?>>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<pkg.ExThree<!#T>>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<pkg.ExThree<?[#T>>", types_.last());
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
        StringList types_ = context_.getClasses().getClassBody("pkg.ExTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.ExTwo<#T>", types_.first());
        assertEq("pkgtwo.Ex<pkg.ExThree<![#T>>", types_.last());
    }
    @Test
    public void resolve21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkg.OuterTwo..InnerThree", types_.last());
    }
    @Test
    public void resolve22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkg.OuterTwo..InnerThree", types_.last());
    }
    @Test
    public void resolve23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.last());
    }
    @Test
    public void resolve24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree", types_.last());
    }
    @Test
    public void resolve25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve31_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterThree.*;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree:OuterTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve31__Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterThree.Int.*;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree:OuterTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterTwo:OuterFour {\n");
        xml_.append(" $public $static $class Int:IntSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterFour {\n");
        xml_.append(" $public $static $class IntSup {\n");
        xml_.append("  $public $static $class InnerThree<#T> {\n");
        xml_.append("  }\n");
        xml_.append("  $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterFour..IntSup..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve36Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.InnerThree.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree..InnerFive", types_.last());
    }
    @Test
    public void resolve37Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree..InnerFive", types_.last());
    }
    @Test
    public void resolve38Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.InnerThree.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree..InnerFive", types_.last());
    }
    @Test
    public void resolve39Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<InnerFour> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class InnerFour {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<pkgtwo.OuterThree..InnerFour>", types_.last());
    }
    @Test
    public void resolve41Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<InnerFour> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class InnerFour {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<pkgtwo.OuterThree..InnerFour>", types_.last());
    }
    @Test
    public void resolve42Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<$int> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<$int>", types_.last());
    }
    @Test
    public void resolve43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<InnerThree<pkgtwo.OuterThree>> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<pkgtwo.OuterTwo..InnerThree<pkgtwo.OuterThree>>", types_.last());
    }
    @Test
    public void resolve44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<$Fct<$void>> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.$Fct<$void>>", types_.last());
    }
    @Test
    public void resolve45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:pkgtwo.OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterThree..InnerThree", types_.last());
    }
    @Test
    public void resolve46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:pkg.OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo:OuterThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterThree {\n");
        xml_.append(" $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkg.OuterThree..InnerThree", types_.last());
    }
    @Test
    public void resolve47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkg.Outer:OuterThree {\n");
        xml_.append(" $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $static $class InnerTwo:pkg.OuterThree.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterTwo:OuterThree {\n");
        xml_.append("}\n");
        xml_.append("$class pkg.OuterThree {\n");
        xml_.append(" $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkg.OuterThree..InnerThree", types_.last());
    }
    @Test
    public void resolve48Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkg.Outer:OuterThree {\n");
        xml_.append(" $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $interface InnerTwo:pkg.OuterThree.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterTwo:OuterThree {\n");
        xml_.append("}\n");
        xml_.append("$class pkg.OuterThree {\n");
        xml_.append(" $interface InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesSingle(files_);
        StringList types_ = ((InterfaceBlock) context_.getClassBody("pkg.Outer..InnerTwo")).getImportedDirectSuperInterfaces();
        assertTrue(StringList.contains(types_, "pkg.OuterThree..InnerThree"));
    }
    @Test
    public void resolve80FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.*;pkgtwo.*;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $final $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve81FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.OuterTwo;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $final $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve82FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.*;pkgtwo.*;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $final $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }

    @Test
    public void resolve39FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTw.InnerThree.InnerFiv;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerFiv {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve38FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<Str> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve37FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTw.InnerThree.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerFiv {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve36_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        StringList types_ = context_.getClasses().getClassBody("pkg.Outer..InnerTwo").getAllGenericClasses();
        assertEq(2, types_.size());
        assertEq("pkg.Outer..InnerTwo", types_.first());
        assertEq("pkgtwo.OuterTwo..InnerThree<java.lang.Number>", types_.last());
    }
    @Test
    public void resolve36FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.OuterTwo;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $final $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve35FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTw.InnerThree.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerFiv {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve34FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.InnerThree.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerFiv {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve33FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree<InnerFive> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve32FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.*;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:Number.Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
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
        xml_.append("$public $class pkg.Outer:pkg.Outer.Inner {\n");
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
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        xml_.append(" $public $class InnerTwo:pkgtwo.OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        unfullValidateInheritingClasses(files_);
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
        xml_.append(" $public $class InnerTwo:pkgtwo.OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve15__FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer: CharSequence {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
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
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        xml_.append(" $public $static $class InnerTwo:pkgtwo.OuterTwo.InnerThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree {\n");
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
        xml_.append(" $public $static $class InnerTwo:pkgtwo.OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve19FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:#OuterTwo.InnerThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve20FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:?OuterTwo.InnerThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve21FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo: {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve22FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:a,b {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve23FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:? {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve24FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<?,?> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve40FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:$iterable<$Fct<?OuterTwo.InnerThree.InnerFive>> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $protected $static $class InnerThree {\n");
        xml_.append("  $protected $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve41FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.Inexist;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:Number {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve42FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgtwo.OuterTwo.Str;pkgtwo.OuterTwo;] pkg.Outer {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<Str> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append("  $public $static $class InnerFive {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve43FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<$void> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve43__FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:InnerThree.InnerFive {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree{\n");
        xml_.append("  $public $static $class InnerFour {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve44__FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;pkgtwo.OuterTwo;] pkg.Outer:OuterTwo.InnerFive {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterF:Number {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree{\n");
        xml_.append("  $public $static $class InnerFour {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve44FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<$Fct<$void,$int>> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve45FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree<Number> {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:InnerThree<pkgtwo.OuterThree.InnerFour<Number>> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree<#S> {\n");
        xml_.append(" $public $class InnerFour<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve46FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo<#E>:InnerThree<#E<Number>> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve47FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;$static pkgtwo.OuterTwo.InnerThree;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo<#E>:$void {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve48FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static;pkgtwo.OuterTwo;] pkg.Outer:pkgtwo.OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:pkgtwo.OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve49FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkg.Outer {\n");
        xml_.append(" $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $static $class InnerTwo:pkgtwo.OuterThree.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.OuterThree {\n");
        xml_.append(" $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve50FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkg.Outer {\n");
        xml_.append(" $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $static $class InnerTwo:pkgtwo.OuterThree.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.OuterThree {\n");
        xml_.append(" $private $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve51FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.ExEnumBis {\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int<T:Number> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sub:Sup<Sub.Inner> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Sup<T> {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve52FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Cl {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve53FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Cla {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve54FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Int:Cl {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve55FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Int {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl:Int {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve56FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne {\n");
        xml_.append(" $public $class InnerOne:OuterTwo.InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $class InnerTwo:OuterOne.InnerOne {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve57FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne {\n");
        xml_.append(" $public $class InnerOne:OuterTwo.InnerTwo:OuterTwo.InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve58FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne {\n");
        xml_.append(" $public $class InnerOne:OuterTwo.InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve59FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne {\n");
        xml_.append(" $public $class InnerOne:OuterTwo.InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $final $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve60FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne {\n");
        xml_.append(" $public $interface InnerOne:OuterTwo.InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve61FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne:OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $final $class pkg.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve62FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne:OuterTwo:OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve63FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne:OuterTwo:OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve64FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne:java.lang.$en:java.lang.$Enum<Number>:java.lang.$Annotation:java.lang.Object {\n");
        xml_.append(" $public $class Inner:java.lang.$en:java.lang.$Enum<Number>:java.lang.$Annotation:java.lang.Object  {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve65FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:String&Number> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve67FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:Abs&Number> {\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Abs {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve68FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:Abs&AbsTwo> {\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Abs {\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.AbsTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve69FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:ExEnum&ExFinal> {\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.ExEnum {;\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExFinal {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve70FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:ExFinal<Number>&ExFinal<Object>&Object[]&$int> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFinal<#U> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve71FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:#U,#U:#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot<#V> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve711FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:#V,#U:#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot<#V> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve72FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne<#T:$void> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve73FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve74FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer: $Annotation {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve75FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $class Inner:InnerTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append("  $public $class InnerThree {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void resolve75_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $static $class Inner:InnerTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo {\n");
        xml_.append("  $public $static $class InnerThree {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl ctx_ = unfullValidateInheritingClassesSingle(files_);
        assertEq(1, ctx_.getClasses().getClassBody("pkg.Outer..Inner").getImportedDirectSuperTypes().size());
        assertEq("pkg.Outer..InnerTwo..InnerThree", ctx_.getClasses().getClassBody("pkg.Outer..Inner").getImportedDirectSuperTypes().first());
    }
    @Test
    public void resolve76_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $static $class Inner:InnerTwo.InnerThree.InnerFour {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo {\n");
        xml_.append("  $public $static $class InnerThree {\n");
        xml_.append("   $public $static $class InnerFour {\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl ctx_ = unfullValidateInheritingClassesSingle(files_);
        assertEq(1, ctx_.getClasses().getClassBody("pkg.Outer..Inner").getImportedDirectSuperTypes().size());
        assertEq("pkg.Outer..InnerTwo..InnerThree..InnerFour", ctx_.getClasses().getClassBody("pkg.Outer..Inner").getImportedDirectSuperTypes().first());
    }
    @Test
    public void resolve76FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterOne {\n");
        xml_.append(" $public $class InnerOne:OuterTwo.InnerTwo:OuterTwo.InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append("ONE{};\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnot{\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInterface{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        failValidateInheritingClassesSingle(files_);
    }
    @Test
    public void calculateStaticField1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=2i;\n");
        xml_.append(" $public $static $final $int mys=myf+3i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, ((NumberStruct)str_).intStruct());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;\n");
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
        xml_.append(" $public $static $final $int mys=$static(pkg.ExThree).myf;\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;\n");
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
        xml_.append(" $public $static $final $int mys=1i;\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(1, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=1i;\n");
        xml_.append(" $public $static $final $int myt=mys+2i;\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(1, ((NumberStruct)str_).intStruct());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myt"));
        assertEq(3, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=meth();\n");
        xml_.append(" $public $static $final $int mys=myf+2i;\n");
        xml_.append(" $public $static $int meth(){\n");
        xml_.append("  $return 5i;\n");
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
        xml_.append(" $public $static $final $int myf=$static(java.lang.Byte).MAX_VALUE;\n");
        xml_.append(" $public $static $final $int mys=myf+2i;\n");
        xml_.append(" $public $static $int meth(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(127, ((NumberStruct)str_).intStruct());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(129, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=2i,mys=myf+3i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, ((NumberStruct)str_).intStruct());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte myf=2i,mys=myf+3i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, ((NumberStruct)str_).intStruct());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf=meth(),mys=5i;\n");
        xml_.append(" $public $static $int meth(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=myf+3i;\n");
        xml_.append(" $public $static $final $int myf=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(2, ((NumberStruct)str_).intStruct());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(5, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int mys=\"\".toString().length()+myf+1;\n");
        xml_.append(" $public $static $final $int myf=1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(1, ((NumberStruct)str_).intStruct());
        str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "mys"));
        assertEq(2, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=($int)$math.random();\n");
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
        xml_.append(" $public $static $final Character ch=$null;\n");
        xml_.append(" $public $static $final $int field=\"\".splitChars(ch).length;\n");
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
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne^iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne|iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne&iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne<<iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne>>iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne<<<iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne>>>iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne<<<<iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne>>>>iTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne^iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo^iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne&iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo&iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne|iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo|iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne<<iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo<<iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne>>iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo>>iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne<<<iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo<<<iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne>>>iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo>>>iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne<<<<iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo<<<<iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne>>>>iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo>>>>iOne;\n");
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
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int field=~iOne;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(-1,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int field=-iOne;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int field=+iOne;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int field=~iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int field=-iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int field=+iOne;\n");
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
        xml_.append(" $public $static $final $int field=iOne^iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField50Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne&iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField51Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne|iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField52Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne<<iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField53Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne>>iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField54Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne<<<iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField55Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne>>>iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField56Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne<<<<iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField57Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne>>>>iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=~iOne;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(-1,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=-iOne;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=+iOne;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(2, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }

    @Test
    public void calculateStaticField61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne!=iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne==iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }

    @Test
    public void calculateStaticField63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=!prfield;\n");
        xml_.append(" $public $static $final $boolean prfield=iOne!=iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(4, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField64Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=!prfield;\n");
        xml_.append(" $public $static $final $boolean prfield=iOne==iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(4, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField65Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne*iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField66Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne/iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField67Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne%iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField67_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=iOne??iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(0,((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField68Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne!=iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField69Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne==iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne&&iTwo;\n");
        xml_.append(" $public $static $final $boolean iOne=$true;\n");
        xml_.append(" $public $static $final $boolean iTwo=$true;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne||iTwo;\n");
        xml_.append(" $public $static $final $boolean iOne=$false;\n");
        xml_.append(" $public $static $final $boolean iTwo=$false;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne<iTwo;\n");
        xml_.append(" $public $static $final String iOne=\"\";\n");
        xml_.append(" $public $static $final String iTwo=\"\";\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField73Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne<=iTwo;\n");
        xml_.append(" $public $static $final String iOne=\"\";\n");
        xml_.append(" $public $static $final String iTwo=\"\";\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField74Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>iTwo;\n");
        xml_.append(" $public $static $final String iOne=\"\";\n");
        xml_.append(" $public $static $final String iTwo=\"\";\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField75Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>=iTwo;\n");
        xml_.append(" $public $static $final String iOne=\"\";\n");
        xml_.append(" $public $static $final String iTwo=\"\";\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField76Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final String field=iOne+iTwo;\n");
        xml_.append(" $public $static $final String iOne=\"\";\n");
        xml_.append(" $public $static $final String iTwo=\"\";\n");
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
        xml_.append(" $public $static $final String field=iOne.substring(0);\n");
        xml_.append(" $public $static $final String iOne=\"\";\n");
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
        xml_.append(" $public $static $final $boolean field=iOne<iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne<=iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isFalse(str_));
    }
    @Test
    public void calculateStaticField81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=iOne>=iTwo;\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Character ch=$null;\n");
        xml_.append(" $public $static $final $int field=\"\".splitChars($vararg($char),$firstopt(ch)).length;\n");
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
        xml_.append(" $public $static $final String ch=$null;\n");
        xml_.append(" $public $static $final $int field=\"\".splitStrings($vararg(CharSequence),0,$firstopt(ch)).length;\n");
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
        xml_.append(" $public $static $final $int field=\"\".splitChars($vararg($char),$firstopt(ch)).length;\n");
        xml_.append(" $public $static $final Character ch=$null;\n");
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
        xml_.append(" $public $static $final $int field=\"\".splitStrings($vararg(CharSequence),0,$firstopt(ch)).length;\n");
        xml_.append(" $public $static $final String ch=$null;\n");
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
        xml_.append(" $public $static $final $boolean field=(iOne>=iTwo);\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertTrue(BooleanStruct.isTrue(str_));
    }
    @Test
    public void calculateStaticField87Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean field=(iOne>=iTwo);\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
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
        xml_.append(" $public $static $final $boolean field=(iTwo>=iOne);\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
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
        xml_.append(" $public $static $final $int field=$bool(iTwo>=iOne,2,5);\n");
        xml_.append(" $public $static $final $int iOne=0;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(3, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "field"));
        assertEq(2, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField90Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int field=$bool(iOne,2,5);\n");
        xml_.append(" $public $static $final Boolean iOne=$null;\n");
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
        xml_.append(" $public $static $final $int field=iOne+iTwo;\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
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
        xml_.append(" $public $static $final $int field=iTwo+iOne;\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
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
        xml_.append(" $public $static $final $int field=iOne*iTwo;\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
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
        xml_.append(" $public $static $final $int field=iTwo*iOne;\n");
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne+iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo+iOne;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iOne*iTwo;\n");
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
        xml_.append(" $public $static $final Integer iOne=$null;\n");
        xml_.append(" $public $static $final $int iTwo=0;\n");
        xml_.append(" $public $static $final $int field=iTwo*iOne;\n");
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
        xml_.append(" $public $static $final $int myf=$static(pkg.ExThree).myf;\n");
        xml_.append(" $public $static $final $int mys=$static(pkg.ExThree).mys;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int myf=$static(pkg.ExTwo).myf;\n");
        xml_.append(" $public $static $final $int mys=$static(pkg.ExTwo).mys;\n");
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
        xml_.append(" $public $static $final $int myf=~-1y;\n");
        xml_.append(" $public $static $final $boolean mys=$true&&m();\n");
        xml_.append(" $public $static $boolean m(){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        Struct str_ = ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo", "myf"));
        assertEq(0, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void calculateStaticField101Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=Byte.MIN_VALUE;\n");
        xml_.append(" $public $static $final Byte a2=Byte.MAX_VALUE;\n");
        xml_.append(" $public $static $final $short a3=Short.MIN_VALUE;\n");
        xml_.append(" $public $static $final Short a4=Short.MAX_VALUE;\n");
        xml_.append(" $public $static $final $char a5=Character.MIN_VALUE;\n");
        xml_.append(" $public $static $final Character a6=Character.MAX_VALUE;\n");
        xml_.append(" $public $static $final $int a7=Integer.MIN_VALUE;\n");
        xml_.append(" $public $static $final Integer a8=Integer.MAX_VALUE;\n");
        xml_.append(" $public $static $final $long a9=Long.MIN_VALUE;\n");
        xml_.append(" $public $static $final Long a10=Long.MAX_VALUE;\n");
        xml_.append(" $public $static $final $float a11=Float.MIN_VALUE;\n");
        xml_.append(" $public $static $final Float a12=Float.MAX_VALUE;\n");
        xml_.append(" $public $static $final $double a13=Double.MIN_VALUE;\n");
        xml_.append(" $public $static $final Double a14=Double.MAX_VALUE;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(14, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField102Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=0xAa.0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField103Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=0b110p1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField104Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=0b101p-1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField105Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=0110p1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField106Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=0101p-1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField107Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=0xA;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField108Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=0xFFFFFFFFFFFFFFFFxl;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField109Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=0xFFFFFFFFFFFFFFFFxL;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField110Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=0xFFFFFFFFxi;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField111Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=0xFFFFFFFFxI;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField112Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0xFFFFxs;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField113Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0xFFFFxS;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField114Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0xFFxy;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField115Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0xFFxY;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField116Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=0xFFFFxc;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField117Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=0xFFFFxC;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField118Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=012l;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField119Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=0b1111111111111111111111111111111111111111111111111111111111111111l;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField120Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=0b1111111111111111111111111111111111111111111111111111111111111111L;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField121Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=0b1i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField122Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=0b1I;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField123Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0b1s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField124Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0b1S;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField125Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=0b1c;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField126Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=0b1C;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField127Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0b1y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField128Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0b1Y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }

    @Test
    public void calculateStaticField129Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=01L;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField130Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=01I;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField131Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=01S;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField132Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=01Y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField133Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=01C;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField134Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=01c;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField135Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=0077777C;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField136Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=0177777c;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField137Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=007777777777i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField138Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=017777777777i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField139Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=027777777777i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField140Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=037777777777i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField141Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0077777s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField142Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0177777s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField143Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0277777s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField144Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=0377777s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField145Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0077y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField146Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0177y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField147Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0277y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField148Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0377y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField149Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=1Y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField150Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=1S;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField151Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=1C;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField152Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=-9223372036854775808l;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        assertEq(Long.MIN_VALUE, ((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).longStruct());
    }
    @Test
    public void calculateStaticField153Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=- -9223372036854775808L;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
        assertEq(Long.MIN_VALUE, ((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).longStruct());
    }
    @Test
    public void calculateStaticField154Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=-128y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField155Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $char a1=65535c;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField156Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=-32768s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField157Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=-2147483648;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField158Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=-128Y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField159Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $short a1=-32768S;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField160Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=-2147483648I;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField161Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a1=0y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField167Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=01.3d;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField168Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=01._3d;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField169Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $long a1=0x1xy;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField170Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=-+1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField171Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=-~1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(1, ctx_.getClasses().staticFieldCount());
    }
    @Test
    public void calculateStaticField172Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=java.lang.Integer.MAX_VALUE;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField173Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean a1=java.lang.Integer.MAX_VALUE $instanceof java.lang.Integer;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField174Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $int m(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExSuper {\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=pkg.ExTwo.CST.$super.m();\n");
        xml_.append(" $public $final $int m(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField175Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $int m(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExSuper {\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=pkg.ExTwo.CST.$classchoice(ExSuper)m();\n");
        xml_.append(" $public $final $int m(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField176Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $int m(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExSuper {\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=pkg.ExTwo.CST.$that.m();\n");
        xml_.append(" $public $final $int m(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(1,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField177Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $final $int n(){\n");
        xml_.append("   $return 3;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=pkg.ExTwo.CST.$new Inner().n();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(3,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField178Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $int m(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExSuper {\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=(pkg.ExTwo.CST.$super.m());\n");
        xml_.append(" $public $final $int m(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField179Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $int m(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExSuper {\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=(pkg.ExTwo.CST.$classchoice(ExSuper)m());\n");
        xml_.append(" $public $final $int m(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField180Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $int m(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:ExSuper {\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=(pkg.ExTwo.CST.$that.m());\n");
        xml_.append(" $public $final $int m(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(1,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField181Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $final $int n(){\n");
        xml_.append("   $return 3;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $final ExTwo CST = $new ExTwo();\n");
        xml_.append(" $public $static $final $int a1=(pkg.ExTwo.CST.$new Inner().n());\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(3,((NumberStruct) ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField182Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean a1=(5!=4);\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }

    @Test
    public void calculateStaticField187Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean a1=(0s&0s)==0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField188Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $short a4;\n");
        xml_.append(" $public $static $double a3;\n");
        xml_.append(" $public $static $float a2;\n");
        xml_.append(" $public $static $char a1=0c;\n");
        xml_.append(" $static {a1+=5c;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDot();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }

    @Test
    public void calculateStaticField190Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int myf=0;\n");
        xml_.append(" $public $static $final $int mys=myf+3i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = validateStaticFields(files_);
        assertEq(0, ctx_.getClasses().staticFieldCount());
    }

    @Test
    public void validateE0FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int outer(){$return $void;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }

    @Test
    public void validateEE0FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static{\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }

    @Test
    public void validateEE_0FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $void outer(){$static{}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateElFailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$public $static $int inner(){$return 0;}$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){$this();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$public $static $int inner;$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$public $static $class Inn{}$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }

    @Test
    public void validateEl5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){((String)$null<(String)$null);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($vararg($int));$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$new $int[]{$vararg($int)};$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
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
        xml_.append(" $public $static $int outer(){$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0<\"\");$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(\"\"<0);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0<$false);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl12FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false<0);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl13FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false+0);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl14FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0+$false);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl15FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false-0);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl16FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0-$false);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl17FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){($false*0);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl18FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){(0*$false);$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

    @Test
    public void validateEl20FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $true&0;\n");
        xml_.append(" $int a2 = 0&$true;\n");
        xml_.append(" $int a3 = $true|0;\n");
        xml_.append(" $int a4 = 0|$true;\n");
        xml_.append(" $int a5 = $true^0;\n");
        xml_.append(" $int a6 = 0^$true;\n");
        xml_.append(" $int a7 = $true<<0;\n");
        xml_.append(" $int a8 = 0<<$true;\n");
        xml_.append(" $int a9 = $true<<<0;\n");
        xml_.append(" $int a10 = 0<<<$true;\n");
        xml_.append(" $int a11 = $true<<<<0;\n");
        xml_.append(" $int a12 = 0<<<<$true;\n");
        xml_.append(" $int a13 = $true>>0;\n");
        xml_.append(" $int a14 = 0>>$true;\n");
        xml_.append(" $int a15 = $true>>>0;\n");
        xml_.append(" $int a16 = 0>>>$true;\n");
        xml_.append(" $int a17 = $true>>>>0;\n");
        xml_.append(" $int a18 = 0>>>>$true;\n");
        xml_.append(" $int a19 = -$true;\n");
        xml_.append(" $int a20 = ~$true;\n");
        xml_.append(" $int a21 = $true&&0;\n");
        xml_.append(" $int a22 = 0&&$true;\n");
        xml_.append(" $int a23 = $false||0;\n");
        xml_.append(" $int a24 = 0||$false;\n");
        xml_.append(" $int a25 = \"\"+(ExTwo)$null;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl21FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[\"\"];\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl31FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[]{\"\"};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl32FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = {\"\"};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl33FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[]{\"\"}.len;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl34FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int a1 = $new $int[]{\"\"}.clo;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl35FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int[] a1 = {\"\"};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl36FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" {({});}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl37FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int[] a1 = {{}};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl38FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" Object a1 = {{}};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl39FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" {([unknown]) = {};}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl40FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" {([unknown]) = $bool($true,5,4);}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl41FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" {$return $bool($true,5,4);}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl42FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $void m(){$return $bool($true,5,4);}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl43FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $void m(){}\n");
        xml_.append(" {$bool(m(),m(),m());tab[,];tab[\"\"];tab[-12345678912l];tab[12345678912l];}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl44FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" {$static(pkgtwo.ExThree).m();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl45FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" {$vararg($int);$firstopt(0);($firstopt(0));$id(Object);($id(Object));}\n");
        xml_.append(" {m($firstopt(0),$vararg($int),$id(Object));}\n");
        xml_.append(" {m($id(Inexist));}\n");
        xml_.append(" {m($id(Object,Object...,Object));}\n");
        xml_.append(" {m(m());}\n");
        xml_.append(" $void m(){}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl46FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner<T:Number> {}\n");
        xml_.append(" {$new ExTwo().$new Inner<String>();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl47FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner<T:Number> {}\n");
        xml_.append(" {$new ExTwo().$new InnerInner();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl48FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo<T,U> {\n");
        xml_.append(" $public $class Inner {}\n");
        xml_.append(" {$new ExTwo<?,!Number>().$new Inner();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl49FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner {}\n");
        xml_.append(" {$new ExTwo[1].$new Inner();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl50FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner {}\n");
        xml_.append(" {$new ExTwo().$new ..Inner();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl51FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExThree {}\n");
        xml_.append("$public $enum pkg.ExTwo {;\n");
        xml_.append(" {$new ExTwo();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl52FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExThree {}\n");
        xml_.append("$public $enum pkg.ExTwo {;\n");
        xml_.append(" {$new ExThree();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl53FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {}\n");
        xml_.append("$public $enum pkg.ExTwo {;\n");
        xml_.append(" {$new ExThree();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl54FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.ExThree {}\n");
        xml_.append("$public $enum pkg.ExTwo {;\n");
        xml_.append(" {$new ExThree();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl55FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {;\n");
        xml_.append(" {$new Inexist();$new $int();}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl57FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {;\n");
        xml_.append(" {$int v = 5;$if($true){$var v = 5;}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl58FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $byte a3=Short.MIN_VALUE;\n");
        xml_.append(" $public $static $final Byte a4=Short.MAX_VALUE;\n");
        xml_.append(" $public $static $final $short a5=Integer.MIN_VALUE;\n");
        xml_.append(" $public $static $final Short a6=Integer.MAX_VALUE;\n");
        xml_.append(" $public $static $final $char a7=Integer.MIN_VALUE;\n");
        xml_.append(" $public $static $final Character a8=Integer.MAX_VALUE;\n");
        xml_.append(" $public $static $final $int a9=Long.MIN_VALUE;\n");
        xml_.append(" $public $static $final Integer a10=Long.MAX_VALUE;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl59FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$operator+ $int(pkg.ExThree p, pkg.ExThree q) {$return 0;}\n");
        xml_.append("$operator++ $int(pkg.ExThree p) {$return 0;}\n");
        xml_.append("$public $class pkg.ExThree {}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo p = $null;\n");
        xml_.append("  p += 1;\n");
        xml_.append("  $int i = 1;\n");
        xml_.append("  i &= $true;\n");
        xml_.append("  $boolean j = $true;\n");
        xml_.append("  j &= 1;\n");
        xml_.append("  $boolean k = $true;\n");
        xml_.append("  k -= 1;\n");
        xml_.append("  $int l = 5;\n");
        xml_.append("  l -= $true;\n");
        xml_.append("  String m = \"\";\n");
        xml_.append("  m &= \"\";\n");
        xml_.append("  m ++;\n");
        xml_.append("  ExThree n = $null;\n");
        xml_.append("  n += (ExThree)$null;\n");
        xml_.append("  n ++;\n");
        xml_.append("  ([o]) += 8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl60FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$enum pkgtwo.ExTwo {;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $values(pkgtwo.ExTwo);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl61FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkgtwo.ExTwo {;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $values(ExTwo);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl62FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$enum pkgtwo.ExTwo {;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $valueOf(pkgtwo.ExTwo,\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl63FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkgtwo.ExTwo {;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $valueOf(ExTwo,\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl64FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$enum pkgtwo.ExTwo {;\n");
        xml_.append("}\n");
        xml_.append("$interface pkgtwo.ExInt {;\n");
        xml_.append("}\n");
        xml_.append("$annotation pkgtwo.ExAnnotation {;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $valueOf(pkgtwo.ExTwo,2);\n");
        xml_.append("  $Fct<$int> f = $null;\n");
        xml_.append("  f.caller(5);\n");
        xml_.append("  $new $int[1].clo();\n");
        xml_.append("  pkgtwo.ExInt i;\n");
        xml_.append("  pkgtwo.ExAnnotation j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl65FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $abstract $void m() {}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply:ExTwo {\n");
        xml_.append(" $static {\n");
        xml_.append("  Apply a = $new Apply();\n");
        xml_.append("  a.$super.m();\n");
        xml_.append(" }\n");
        xml_.append(" $public $void m() {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl66FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $abstract $void m() {}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply:ExTwo {\n");
        xml_.append(" $static {\n");
        xml_.append("  Apply a = $new Apply();\n");
        xml_.append("  a.$classchoice(ExTwo)m();\n");
        xml_.append(" }\n");
        xml_.append(" $public $void m() {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl67FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:ExThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void o() {}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final String CST = $null;\n");
        xml_.append(" $static $final $int LEN = CST.length();\n");
        xml_.append(" $static $final $int RES = \"\".len();\n");
        xml_.append(" $static {\n");
        xml_.append("  m().$classchoice(ExTwo)m(n());\n");
        xml_.append("  m(n());\n");
        xml_.append("  m().n();\n");
        xml_.append("  $new ExTwo().$classchoice(Apply)m();\n");
        xml_.append("  m().$superaccess(ExThree)o();\n");
        xml_.append("  $superaccess(ExThree)o(m());\n");
        xml_.append("  p($vararg($int),$firstopt(\"\"));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void m() {}\n");
        xml_.append(" $public $static $void n() {}\n");
        xml_.append(" $public $static $void p($int... i) {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl68FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $abstract $void m() {}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply:ExTwo {\n");
        xml_.append(" $static {\n");
        xml_.append("  Apply a = $new Apply();\n");
        xml_.append("  a.$superaccess(ExTwo)m();\n");
        xml_.append(" }\n");
        xml_.append(" $public $void m() {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl69FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = -92233720368547758088L;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl70FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = 09L;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl71FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $char cst = 09C;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl72FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 09I;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl73FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0400Y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl74FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 01111Y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl75FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0400000S;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl76FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 01111111S;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl77FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0200000C;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl78FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 01111111C;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl79FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 040000000000I;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl80FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0111111111111I;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl81FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 02000000000000000000000L;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl82FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0111111111111111111111111L;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl83FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 111111111111111111;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl84FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = -111111111111111111;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl85FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 11111111s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl86FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = -11111111s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl87FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 11111111c;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl88FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = -11111111c;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl89FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 1111y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl90FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = -1111y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl91FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = -9223372036854775808;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl92FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $float cst = 1e100f;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl93FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $float cst = -1e100f;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl94FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = 0b11111111111111111111111111111111111111111111111111111111111111111l;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl95FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0b111111111111111111111111111111111i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl96FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0b11111111111111111s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl97FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0b11111111111111111c;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl98FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 0b111111111y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl99FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $int cst = 01$;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl100FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public classe pkg.Apply {\n");
        xml_.append(" static final entier4 cst = 0y;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ct_ = getFrContextEl();
        Classes.validateAll(files_, ct_);
        assertTrue(!ct_.isEmptyErrors());
    }
    @Test
    public void validateEl101FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public classe pkg.Apply {\n");
        xml_.append(" static final entier4 cst = 0x;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ct_ = getFrContextEl();
        Classes.validateAll(files_, ct_);
        assertTrue(!ct_.isEmptyErrors());
    }

    @Test
    public void validateEl102FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $int a;\n");
        xml_.append(" $static {a.$static;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl103FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $int a;\n");
        xml_.append(" $static {a.;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl104FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {$long l = 0x0x;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl105FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {$long l = 0x0x+1;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl106FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {$long l = 0x0xy.1;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl107FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = 0xffffffffffffffff0l;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl108FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = 0xffffffff0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl109FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = 0xffff0xc;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl110FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = 0xffff0s;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl111FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final $long cst = 0xff0xy;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl112FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {a.$static;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl113FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {a.;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl114FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1<<<<;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl115FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1<<<;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl116FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1<<;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl117FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = $true&&;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl118FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1<;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl119FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1&;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl120FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1+;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl121FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1-;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl122FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = 1-+;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl123FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = pkg.MAX_VALUE;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDot();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl124FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = pkg;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDot();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl125FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = java.lang.Integer.MAX;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDot();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl126FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = java.lang.Integer;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDot();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl127FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = java.lang;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDot();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl128FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = java. .lang;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDot();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }

    @Test
    public void validateEl131FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = java.lang.Integer;\n");
        xml_.append(" $static $final Object otherOne = $null;\n");
        xml_.append(" $static $final Object otherTwo;\n");
        xml_.append(" $static $final Object otherThree = $classchoice(Apply)otherTwo = $null;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl132FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void n($iterable<Apply>) {}\n");
        xml_.append(" $public $static $void n($int[]) {}\n");
        xml_.append(" $public $static $void p($int...) {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl133FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$operator $int[]{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void n($iterable<Apply>) {}\n");
        xml_.append(" $public $static $void n($int[]) {}\n");
        xml_.append(" $public $static $void p($int...) {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl134FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$operator $int{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void n($iterable<Apply>) {}\n");
        xml_.append(" $public $static $void n($int[]) {}\n");
        xml_.append(" $public $static $void p($int...) {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl135FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p($int... i) {$int;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl136FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p($int... i) {$int[];}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl137FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p($int... i) {$int ;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl138FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p($int... i) {$int[] ;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl139FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$iter($int){}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl140FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$iter($int[]){}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl142FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$iter($int ){}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl143FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$throw;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl144FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$iter($int ){$int j;}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl145FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$foreach;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl146FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$iter;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl147FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$for;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl152FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$int i}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl153FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $class\tInner{}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl154FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $class $interfaces Inner{}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl155FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Apply {\n");
        xml_.append(" $public $class $interfaces Inner{}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl156FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Apply {\n");
        xml_.append(" $public $classe Inner{}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl157FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $classe Inner{}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl158FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $classe Inner");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl159FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $int v = m());}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl160FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $int v = m[]];}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl161FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $int v = m({}});}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl162FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $class");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl163FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $int v = m({}}}});}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl164FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $int v = m({}}});}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl165FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $class ");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl166FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $String v = a/*b*/: ");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply1 {\n");
        xml_.append(" $public $String v = a//b: ");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply2 {\n");
        xml_.append(" $public $String v = ` ");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply3 {\n");
        xml_.append(" $public $String v = \"\\");
        files_.put("pkg/ExFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply4 {\n");
        xml_.append(" $public $String v = '\\");
        files_.put("pkg/ExSix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply5 {\n");
        xml_.append(" /* *");
        files_.put("pkg/ExSeven", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }

    @Test
    public void validateEl169FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Ex e = $new ExSub();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  $return e[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $protected $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p]*2;\n");
        xml_.append(" }\n");
        xml_.append(" $protected $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $protected $long $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $protected $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl170FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Ex e = $new ExSub();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  $return e[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $protected $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p]*2;\n");
        xml_.append(" }\n");
        xml_.append(" $protected $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p]*2;\n");
        xml_.append(" }\n");
        xml_.append(" $protected $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $protected $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $protected $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $protected $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl171FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Ex e = $new ExSub();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  $return e[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $protected $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p]*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $protected $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl172FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $protected $static $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $protected $static $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl173FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $protected $final $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $protected $abstract $void $this($int p);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl174FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $protected $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }

    @Test
    public void validateEl180FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p($int... i) {$new Integer($false);}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl181FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p($int... i) {$new Integer($(Integer)$null);$new Integer($id(Integer,$int),0);\"\".format($id(CharSequence,CharSequence...),\"\",\"\");}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
    }

}
