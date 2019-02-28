package code.expressionlanguage.types;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.StringList;
import code.util.StringMap;


public final class PartTypeUtilTest {

    @Test
    public void getAllTypes1Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("String"), PartTypeUtil.getAllTypes("String",cont_));
    }
    @Test
    public void getAllTypes2Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map","String","Rate"), PartTypeUtil.getAllTypes("Map<String,Rate>",cont_));
    }
    @Test
    public void getAllTypes3Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map","String","Map<String,Rate>"), PartTypeUtil.getAllTypes("Map<String,Map<String,Rate>>",cont_));
    }
    @Test
    public void getAllTypes4Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("List","Boolean"), PartTypeUtil.getAllTypes("List<Boolean>",cont_));
    }
    @Test
    public void getAllTypes5Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("CustList","BooleanList"), PartTypeUtil.getAllTypes("CustList<BooleanList>",cont_));
    }
    @Test
    public void getAllTypes6Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Outer..Map"), PartTypeUtil.getAllTypes("Outer..Map",cont_));
    }
    @Test
    public void getAllTypes7Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map"), PartTypeUtil.getAllTypes("..Map",cont_));
    }
    @Test
    public void getAllTypes8Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map..Inner","String","Rate"), PartTypeUtil.getAllTypes("Map<String,Rate>..Inner",cont_));
    }
    @Test
    public void getAllTypes9Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map..Inner","String","Rate","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String,Rate>..Inner<Boolean,Number>",cont_));
    }
    @Test
    public void getAllTypes10Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map..Inner","String","Rate..Denominator","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String,Rate..Denominator>..Inner<Boolean,Number>",cont_));
    }
    @Test
    public void getAllTypes11Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map..Inner","String..Character","Rate","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String..Character,Rate>..Inner<Boolean,Number>",cont_));
    }
    @Test
    public void getAllTypes12Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map..Inner","String"), PartTypeUtil.getAllTypes("Map<String>..Inner",cont_));
    }
    @Test
    public void getAllTypes13Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("[String"), PartTypeUtil.getAllTypes("[String",cont_));
    }
    @Test
    public void getAllTypes14Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map","[String","Rate"), PartTypeUtil.getAllTypes("Map<[String,Rate>",cont_));
    }
    @Test
    public void getAllTypes15Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("[Map","String","Rate"), PartTypeUtil.getAllTypes("[Map<String,Rate>",cont_));
    }
    @Test
    public void getAllTypes16Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("[Map..Inner","String"), PartTypeUtil.getAllTypes("[Map<String>..Inner",cont_));
    }
    @Test
    public void getAllTypes17Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map..Inner","String","[Rate..Denominator","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String,[Rate..Denominator>..Inner<Boolean,Number>",cont_));
    }
    @Test
    public void getAllTypes18Test(){
        ContextEl cont_ = unfullValidateInheritingClassesDeps(new StringMap<String>());
        assertEq(new StringList("Map..Inner","[String..Character","Rate","Boolean","Number"), PartTypeUtil.getAllTypes("Map<[String..Character,Rate>..Inner<Boolean,Number>",cont_));
    }
    @Test
    public void process1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
         String solved_ = PartTypeUtil.processAnalyze("OuterTwo", "", context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterTwo", solved_);
    }
    @Test
    public void process2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<OuterFour>", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<pkgthree.OuterFour>", solved_);
    }
    @Test
    public void process3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.*;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterFive<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<OuterFive<OuterFour>>", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<pkgtwo.OuterFive<pkgthree.OuterFour>>", solved_);
    }
    @Test
    public void process4Test() {
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
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo..InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree", solved_);
    }
    @Test
    public void process5Test() {
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
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("Outer..InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree", solved_);
    }
    @Test
    public void process6Test() {
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
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public $class InnerThree:OuterThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $class InnerFive {\n");
        xml_.append("  $public $class InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("Outer..InnerThree..InnerInner", "",context_, root_);
        assertEq("pkgtwo.OuterThree..InnerFive..InnerInner", solved_);
    }
    @Test
    public void process7Test() {
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
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public $class InnerThree:OuterThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $class InnerFive {\n");
        xml_.append("  $public $class InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("..InnerThree..InnerInner", "pkg.Outer",context_, root_);
        assertEq("pkgtwo.OuterThree..InnerFive..InnerInner", solved_);
    }
    @Test
    public void process8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo<java.lang.Number> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("Outer..InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree", solved_);
    }
    @Test
    public void process9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.Number>..InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree", solved_);
    }
    @Test
    public void process10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.Number>..InnerThree<java.lang.String>", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree<java.lang.String>", solved_);
    }
    @Test
    public void process11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("..InnerThree<java.lang.String>", "pkg.Outer<#W>",context_, root_);
        assertEq("pkgtwo.OuterTwo<#W>..InnerThree<java.lang.String>", solved_);
    }
    @Test
    public void process12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#C>: OuterTwo<#C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<#C>..InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public $class InnerThree<#F>:OuterThree<#B>..InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<#B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#A> {\n");
        xml_.append(" $public $class InnerFive<#E> {\n");
        xml_.append("  $public $class InnerInner<#G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        context_.getAvailableVariables().add("D");
        context_.getAvailableVariables().add("H");
        context_.getAvailableVariables().add("I");
        String solved_ = PartTypeUtil.processAnalyze("Outer<#D>..InnerThree<#H>..InnerInner<#I>", "",context_, root_);
        assertEq("pkgtwo.OuterThree<#D>..InnerFive<#H>..InnerInner<#I>", solved_);
    }
    @Test
    public void process13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo[]", "", context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("[pkgtwo.OuterTwo", solved_);
    }
    @Test
    public void process14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#S>: OuterTwo<#S> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo<java.lang.Number>[]", "", context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("[pkgtwo.OuterTwo<java.lang.Number>", solved_);
    }
    @Test
    public void process15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#C>: OuterTwo<#C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<#C>..InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public $class InnerThree<#F>:OuterThree<#B>..InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<#B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#A> {\n");
        xml_.append(" $public $class InnerFive<#E> {\n");
        xml_.append("  $public $class InnerInner<#G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        context_.getAvailableVariables().add("D");
        context_.getAvailableVariables().add("H");
        context_.getAvailableVariables().add("I");
        String solved_ = PartTypeUtil.processAnalyze("Outer<#D[]>..InnerThree<#H[]>..InnerInner<#I[]>[]", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("[pkgtwo.OuterThree<[#D>..InnerFive<[#H>..InnerInner<[#I>", solved_);
    }
    @Test
    public void process16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("java.lang.$Fct<$void>", "", context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("java.lang.$Fct<$void>", solved_);
    }
    @Test
    public void process17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<?OuterFour>", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<?pkgthree.OuterFour>", solved_);
    }
    @Test
    public void process18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<!OuterFour>", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<!pkgthree.OuterFour>", solved_);
    }
    @Test
    public void process19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<?>", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<?>", solved_);
    }
    @Test
    public void process20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#S>: OuterTwo<#S> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo<?java.lang.Number>[]", "", context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("[pkgtwo.OuterTwo<?java.lang.Number>", solved_);
    }
    @Test
    public void process21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<?OuterFour[]>", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<?[pkgthree.OuterFour>", solved_);
    }
    @Test
    public void process22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<!OuterFour[]>", "",context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<![pkgthree.OuterFour>", solved_);
    }
    @Test
    public void process23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("java.lang.$Fct<?>", "", context_, root_);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq("java.lang.$Fct<?>", solved_);
    }
    @Test
    public void process24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo..InnerThree<Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:..InnerThree<Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo..InnerThree<Outer..Inner>", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree<pkg.Outer..Inner>", solved_);
    }
    @Test
    public void process1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("pkg.Outer<$void>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("$Fct<$void,$int>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("java.lang.$Fct<!java.lang.Number,?java.lang.Number>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("java.lang.$Fct<java.lang.Number,?java.lang.Number>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void processDepends1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterTwo", 0, context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterThree<OuterFour> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterThree<OuterFour>",0, context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.*;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterThree<OuterFive<OuterFour>> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterFive<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterThree<OuterFive<OuterFour>>", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends4Test() {
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
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterTwo..InnerThree",0,context_, root_, true);
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:Outer..InnerThree {\n");
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
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("Outer..InnerThree", 0,context_, root_, true);
        assertEq(1, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
    }
    @Test
    public void processDepends6Test() {
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
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public $class InnerThree:OuterThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $class InnerFive {\n");
        xml_.append("  $public $class InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("Outer..InnerThree..InnerInner", 0,context_, root_, true);
        assertEq(2, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
        assertTrue(solved_.containsStr("pkgtwo.OuterTwo..InnerThree"));
    }
    @Test
    public void processDepends7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append("  $public $class InnerSubTwo:..InnerThree..InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public $class InnerThree:OuterThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $class InnerFive {\n");
        xml_.append("  $public $class InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo..InnerSubTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("..InnerThree..InnerInner", 0,context_, root_, true);
        assertEq(2, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
        assertTrue(solved_.containsStr("pkgtwo.OuterTwo..InnerThree"));
    }
    @Test
    public void processDepends8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo<java.lang.Number> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:Outer..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("Outer..InnerThree", 0,context_, root_, true);
        assertEq(1, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
    }
    @Test
    public void processDepends9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:Outer<java.lang.Number>..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("Outer<java.lang.Number>..InnerThree", 0,context_, root_, true);
        assertEq(1, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
    }
    @Test
    public void processDepends10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:Outer<java.lang.Number>..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("Outer<java.lang.Number>..InnerThree<java.lang.String>",0,context_, root_, true);
        assertEq(1, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
    }
    @Test
    public void processDepends11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("..InnerThree<java.lang.String>", 0,context_, root_, true);
        assertEq(1, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
    }
    @Test
    public void processDepends12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#C>: OuterTwo<#C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<#C>..InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public $class InnerThree<#F>:OuterThree<#B>..InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<#B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#A> {\n");
        xml_.append(" $public $class InnerFive<#E> {\n");
        xml_.append("  $public $class InnerInner<#G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        context_.getAvailableVariables().add("D");
        context_.getAvailableVariables().add("H");
        context_.getAvailableVariables().add("I");
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("Outer<#D>..InnerThree<#H>..InnerInner<#I>", 0,context_, root_, true);
        assertEq(2, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
        assertTrue(solved_.containsStr("pkgtwo.OuterTwo..InnerThree"));
    }
    @Test
    public void processDepends13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterTwo[]", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#S>: OuterTwo<#S> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterTwo<java.lang.Number>[]", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#C>: OuterTwo<#C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<#C>..InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public $class InnerThree<#F>:OuterThree<#B>..InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<#B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#A> {\n");
        xml_.append(" $public $class InnerFive<#E> {\n");
        xml_.append("  $public $class InnerInner<#G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        context_.getAvailableVariables().add("D");
        context_.getAvailableVariables().add("H");
        context_.getAvailableVariables().add("I");
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("Outer<#D[]>..InnerThree<#H[]>..InnerInner<#I[]>[]", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(2, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
        assertTrue(solved_.containsStr("pkgtwo.OuterTwo..InnerThree"));
    }
    @Test
    public void processDepends16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("java.lang.$Fct<$void>", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterThree<OuterFour> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterThree<?OuterFour>", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterThree<OuterFour> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterThree<?>", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("pkgtwo.OuterThree;\n");
        xml_.append("pkgthree.OuterFour;\n");
        xml_.append("$public $class pkg.Outer: OuterThree<OuterFour> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterThree<!OuterFour>", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#S>: OuterTwo<#S> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("OuterTwo<?java.lang.Number>[]", 0,context_, root_, true);
        assertTrue(cl_.displayErrors(), cl_.isEmptyErrors());
        assertEq(0, solved_.size());
    }
    @Test
    public void processDepends21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo..InnerThree<java.lang.Number> {\n");
        xml_.append("  $public $class InnerSubTwo:..InnerThree<java.lang.Number>..InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public $class InnerThree<#T>:OuterThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:..InnerThree<java.lang.Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public $class InnerFive {\n");
        xml_.append("  $public $class InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClassesDeps(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer..InnerTwo..InnerSubTwo");
        
        StringList solved_ = PartTypeUtil.processAnalyzeDepends("..InnerThree<java.lang.Number>..InnerInner", 0,context_, root_, true);
        assertEq(2, solved_.size());
        assertTrue(solved_.containsStr("pkg.Outer"));
        assertTrue(solved_.containsStr("pkgtwo.OuterTwo..InnerThree"));
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
    private ContextEl unfullValidateInheritingClassesDeps(StringMap<String> _files) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClassesId(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
}
