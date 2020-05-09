package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.methods.RootBlock;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


public final class PartTypeUtilTest extends ProcessMethodCommon {

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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo.InnerThree", "",context_, root_);
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
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("Outer.InnerThree", "",context_, root_);
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
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public $class InnerThree:OuterThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        String solved_ = PartTypeUtil.processAnalyze("Outer.InnerThree.InnerInner", "",context_, root_);
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
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public $class InnerThree:OuterThree.InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
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
        
        String solved_ = PartTypeUtil.processAnalyze("InnerThree.InnerInner", "pkg.Outer",context_, root_);
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
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("Outer.InnerThree", "",context_, root_);
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
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.Number>.InnerThree", "",context_, root_);
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
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.Number>.InnerThree<java.lang.String>", "",context_, root_);
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
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        
        String solved_ = PartTypeUtil.processAnalyze("InnerThree<java.lang.String>", "pkg.Outer<#U>",context_, root_);
        assertEq("pkgtwo.OuterTwo<#U>..InnerThree<java.lang.String>", solved_);
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
        xml_.append(" $public $class InnerTwo:OuterTwo<#C>.InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public $class InnerThree<#F>:OuterThree<#B>.InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<#B> {\n");
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
        
        context_.getAnalyzing().getAvailableVariables().addEntry("D",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("H",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("I",0);
        String solved_ = PartTypeUtil.processAnalyze("Outer<#D>.InnerThree<#H>.InnerInner<#I>", "",context_, root_);
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        xml_.append(" $public $class InnerTwo:OuterTwo<#C>.InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public $class InnerThree<#F>:OuterThree<#B>.InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<#B> {\n");
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
        
        context_.getAnalyzing().getAvailableVariables().addEntry("D",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("H",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("I",0);
        String solved_ = PartTypeUtil.processAnalyze("Outer<#D[]>.InnerThree<#H[]>.InnerInner<#I[]>[]", "",context_, root_);
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
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
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree<Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo.InnerThree<Outer.Inner>", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree<pkg.Outer..Inner>", solved_);
    }
    @Test
    public void process25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.D<#C>: OuterTwo<#C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<#C>.InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public $class InnerThree<#F>:OuterThree<#B>.InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<#B> {\n");
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
        RootBlock root_ = cl_.getClassBody("pkg.D");

        context_.getAnalyzing().getAvailableVariables().addEntry("D",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("H",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("I",0);
        String solved_ = PartTypeUtil.processAnalyze("D<#D[]>.InnerThree<#H[]>.InnerInner<#I[]>[]", "",context_, root_);
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
        assertEq("[pkgtwo.OuterThree<[#D>..InnerFive<[#H>..InnerInner<[#I>", solved_);
    }
    @Test
    public void process26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerTwo:OuterTwo.InnerThree<Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public $static $class InnerThree<T> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class InnerFour:InnerThree<Number> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo.InnerThree<Outer.Inner>", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree<pkg.Outer..Inner>", solved_);
    }
    @Test
    public void process27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");

        String solved_ = PartTypeUtil.processAnalyze("Outer.InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree", solved_);
    }
    @Test
    public void process28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#S>:InnerFour {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour {\n");
        xml_.append(" $public $class InnerInnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");

        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.String>.InnerThree<java.lang.Number>.InnerInnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.String>..InnerFour..InnerInnerThree", solved_);
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
    public void process5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");

        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.Number,java.lang.Number>.InnerThree", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");

        String solved_ = PartTypeUtil.processAnalyze("Outer.InnerThree", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");

        String solved_ = PartTypeUtil.processAnalyze("Outer.Inner", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");

        String solved_ = PartTypeUtil.processAnalyze("Outer..Inner", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public $class InnerThree<#S>:InnerFour {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour {\n");
        xml_.append(" $public $class InnerInnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");

        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.String>.InnerThree.InnerInnerThree", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void processLine1Test() {
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
        String solved_ = PartTypeUtil.processAnalyzeLine("OuterThree<OuterFour>", context_, root_);
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
        assertEq("pkgtwo.OuterThree<pkgthree.OuterFour>", solved_);
    }
    @Test
    public void processLine2Test() {
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
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append(" $public $static $class OuterThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyzeLine("OuterFour.OuterThree<OuterFour>", context_, root_);
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
        assertEq("pkgthree.OuterFour..OuterThree<pkgthree.OuterFour>", solved_);
    }
    @Test
    public void processLine1FailTest() {
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
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append(" $public $static $class OuterThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyzeLine("OuterFour.OuterThree<$void>", context_, root_);
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
        assertEq("", solved_);
    }
    @Test
    public void processLine2FailTest() {
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
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append(" $public $static $class OuterThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyzeLine("OuterFour.OuterThree<$void", context_, root_);
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
        assertEq("", solved_);
    }
    @Test
    public void processLine3FailTest() {
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
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append(" $public $static $class OuterThree<#T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        String solved_ = PartTypeUtil.processAnalyzeLine("OuterFour..OuterThree<$void>", context_, root_);
        assertTrue(cl_.displayErrors(), context_.isEmptyErrors());
        assertEq("", solved_);
    }

    @Test
    public void isKoForWord1Test() {
        assertTrue(!PartTypeUtil.isKoForWord("$int"));
    }

    @Test
    public void isKoForWord2Test() {
        assertTrue(PartTypeUtil.isKoForWord("$int+$int"));
    }

    @Test
    public void isKoForWord3Test() {
        assertTrue(PartTypeUtil.isKoForWord("1l"));
    }

    @Test
    public void isKoForWord4Test() {
        assertTrue(PartTypeUtil.isKoForWord(""));
    }

    @Test
    public void isKoForWord5Test() {
        assertTrue(!PartTypeUtil.isKoForWord("#int"));
    }
    @Test
    public void isCorrectType1Test() {
        assertTrue(PartTypeUtil.isCorrectType("$int"));
    }

    @Test
    public void isCorrectType2Test() {
        assertTrue(PartTypeUtil.isCorrectType("$int[]"));
    }

    @Test
    public void isCorrectType3Test() {
        assertTrue(PartTypeUtil.isCorrectType("pkg.Ex"));
    }
    @Test
    public void isCorrectType4Test() {
        assertTrue(PartTypeUtil.isCorrectType("pkg.Ex[]"));
    }
    @Test
    public void isCorrectType5Test() {
        assertTrue(!PartTypeUtil.isCorrectType("pkg()"));
    }
    @Test
    public void isCorrectType6Test() {
        assertTrue(!PartTypeUtil.isCorrectType("pkg.Ex()"));
    }
    @Test
    public void isCorrectType7Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<b"));
    }
    @Test
    public void isCorrectType8Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b>"));
    }
    @Test
    public void isCorrectType9Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b>[]"));
    }
    @Test
    public void isCorrectType10Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b>.c"));
    }
    @Test
    public void isCorrectType11Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<b>c"));
    }
    @Test
    public void isCorrectType12Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b,c>"));
    }
    @Test
    public void isCorrectType13Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b,c>[]"));
    }
    @Test
    public void isCorrectType14Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b,d>.c"));
    }
    @Test
    public void isCorrectType15Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<b,d>c"));
    }
    @Test
    public void isCorrectType16Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b,d[]>"));
    }
    @Test
    public void isCorrectType17Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b[],d>"));
    }
    @Test
    public void isCorrectType18Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b<e>,d>"));
    }
    @Test
    public void isCorrectType19Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<b<e,f>,d>"));
    }
    @Test
    public void isCorrectType20Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a>b"));
    }
    @Test
    public void isCorrectType21Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a?b:c"));
    }
    @Test
    public void isCorrectType22Test() {
        assertTrue(!PartTypeUtil.isCorrectType("?b"));
    }
    @Test
    public void isCorrectType24Test() {
        assertTrue(!PartTypeUtil.isCorrectType("!b"));
    }
    @Test
    public void isCorrectType25Test() {
        assertTrue(!PartTypeUtil.isCorrectType("?"));
    }
    @Test
    public void isCorrectType26Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<?b>"));
    }
    @Test
    public void isCorrectType27Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<!b>"));
    }
    @Test
    public void isCorrectType28Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<?b[]>"));
    }
    @Test
    public void isCorrectType29Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<!b[]>"));
    }
    @Test
    public void isCorrectType30Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<c,?b[]>"));
    }
    @Test
    public void isCorrectType31Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<c,!b[]>"));
    }
    @Test
    public void isCorrectType32Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<#c,?b[]>"));
    }
    @Test
    public void isCorrectType33Test() {
        assertTrue(PartTypeUtil.isCorrectType("a<#c,!b[]>"));
    }
    @Test
    public void isCorrectType34Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<b>.1"));
    }
    @Test
    public void isCorrectType35Test() {
        assertTrue(!PartTypeUtil.isCorrectType("#1"));
    }
    @Test
    public void isCorrectType36Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<>"));
    }
    @Test
    public void isCorrectType37Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<b*c>"));
    }
    @Test
    public void isCorrectType38Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<#b*c>"));
    }
    @Test
    public void isCorrectType39Test() {
        assertTrue(!PartTypeUtil.isCorrectType("?a"));
    }
    @Test
    public void isCorrectType40Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<!>"));
    }
    @Test
    public void isCorrectType41Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a[b]"));
    }
    @Test
    public void isCorrectType42Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a b"));
    }
    @Test
    public void isCorrectType43Test() {
        assertTrue(!PartTypeUtil.isCorrectType("c<a b>"));
    }
    @Test
    public void isCorrectType44Test() {
        assertTrue(PartTypeUtil.isCorrectType("c<a#>"));
    }
    @Test
    public void isCorrectType45Test() {
        assertTrue(PartTypeUtil.isCorrectType("c<#a#>"));
    }
    @Test
    public void isCorrectType46Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a,b"));
    }
    @Test
    public void isCorrectType47Test() {
        assertTrue(!PartTypeUtil.isCorrectType("#a.b"));
    }
    @Test
    public void isCorrectType48Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a.#b"));
    }
    @Test
    public void isCorrectType49Test() {
        assertTrue(!PartTypeUtil.isCorrectType("#a<b>"));
    }
    @Test
    public void isCorrectType50Test() {
        assertTrue(!PartTypeUtil.isCorrectType("a<b..c,>"));
    }
}
