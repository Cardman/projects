package code.expressionlanguage.analyze.types;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;


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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
         String solved_ = processAnalyze("OuterTwo", "", context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyze("OuterThree<OuterFour>", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.OuterFive<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyze("OuterThree<OuterFive<OuterFour>>", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyze("OuterTwo.InnerThree", "",context_, root_);
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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyze("Outer.InnerThree", "",context_, root_);
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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyze("Outer.InnerThree.InnerInner", "",context_, root_);
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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("InnerThree.InnerInner", "pkg.Outer",context_, root_);
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
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("Outer.InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree", solved_);
    }
    @Test
    public void process9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("Outer<java.lang.Number>.InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree", solved_);
    }
    @Test
    public void process10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree<V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("Outer<java.lang.Number>.InnerThree<java.lang.String>", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree<java.lang.String>", solved_);
    }
    @Test
    public void process11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree<V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("InnerThree<java.lang.String>", "pkg.Outer<#U>",context_, root_);
        assertEq("pkgtwo.OuterTwo<#U>..InnerThree<java.lang.String>", solved_);
    }
    @Test
    public void process12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<C>: OuterTwo<C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<C>.InnerThree<C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<B>:OuterThree<B> {\n");
        xml_.append(" $public $class InnerThree<F>:OuterThree<B>.InnerFive<F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<A> {\n");
        xml_.append(" $public $class InnerFive<E> {\n");
        xml_.append("  $public $class InnerInner<G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        context_.getAnalyzing().getAvailableVariables().addEntry("D",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("H",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("I",0);
        String solved_ = processAnalyze("Outer<D>.InnerThree<H>.InnerInner<I>", "",context_, root_);
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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterTwo[]", "", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("[pkgtwo.OuterTwo", solved_);
    }
    @Test
    public void process14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<S>: OuterTwo<S> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterTwo<java.lang.Number>[]", "", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("[pkgtwo.OuterTwo<java.lang.Number>", solved_);
    }
    @Test
    public void process15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<C>: OuterTwo<C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<C>.InnerThree<C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<B>:OuterThree<B> {\n");
        xml_.append(" $public $class InnerThree<F>:OuterThree<B>.InnerFive<F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<A> {\n");
        xml_.append(" $public $class InnerFive<E> {\n");
        xml_.append("  $public $class InnerInner<G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        context_.getAnalyzing().getAvailableVariables().addEntry("D",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("H",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("I",0);
        String solved_ = processAnalyze("Outer<D[]>.InnerThree<H[]>.InnerInner<I[]>[]", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("[pkgtwo.OuterThree<[#D>..InnerFive<[#H>..InnerInner<[#I>", solved_);
    }
    @Test
    public void process16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("java.lang.$Fct<$void>", "", context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterThree<?OuterFour>", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterThree<!OuterFour>", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterThree<?>", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("pkgtwo.OuterThree<?>", solved_);
    }
    @Test
    public void process20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<S>: OuterTwo<S> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterTwo<?java.lang.Number>[]", "", context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterThree<?OuterFour[]>", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("OuterThree<!OuterFour[]>", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("pkgtwo.OuterThree<![pkgthree.OuterFour>", solved_);
    }
    @Test
    public void process23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("java.lang.$Fct<?>", "", context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyze("OuterTwo.InnerThree<Outer.Inner>", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree<pkg.Outer..Inner>", solved_);
    }
    @Test
    public void process25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.D<C>: OuterTwo<C> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<C>.InnerThree<C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<B>:OuterThree<B> {\n");
        xml_.append(" $public $class InnerThree<F>:OuterThree<B>.InnerFive<F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree<B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<A> {\n");
        xml_.append(" $public $class InnerFive<E> {\n");
        xml_.append("  $public $class InnerInner<G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.D");

        context_.getAnalyzing().getAvailableVariables().addEntry("D",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("H",0);
        context_.getAnalyzing().getAvailableVariables().addEntry("I",0);
        String solved_ = processAnalyze("D<D[]>.InnerThree<H[]>.InnerInner<I[]>[]", "",context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyze("OuterTwo.InnerThree<Outer.Inner>", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree<pkg.Outer..Inner>", solved_);
    }
    @Test
    public void process27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $static $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("Outer.InnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo..InnerThree", solved_);
    }
    @Test
    public void process28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree<S>:InnerFour {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour {\n");
        xml_.append(" $public $class InnerInnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("Outer<java.lang.String>.InnerThree<java.lang.Number>.InnerInnerThree", "",context_, root_);
        assertEq("pkgtwo.OuterTwo<java.lang.String>..InnerFour..InnerInnerThree", solved_);
    }
    @Test
    public void process1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("pkg.Outer<$void>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("$Fct<$void,$int>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("java.lang.$Fct<!java.lang.Number,?java.lang.Number>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        
        String solved_ = processAnalyze("java.lang.$Fct<java.lang.Number,?java.lang.Number>", "", context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("Outer<java.lang.Number,java.lang.Number>.InnerThree", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("Outer.InnerThree", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:OuterTwo<java.lang.Number>.InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour:InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("Outer.Inner", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("Outer..Inner", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree<S>:InnerFour {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour {\n");
        xml_.append(" $public $class InnerInnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("Outer<java.lang.String>.InnerThree.InnerInnerThree", "",context_, root_);
        assertEq("", solved_);
    }
    @Test
    public void process10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<U>: OuterTwo<U> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<T> {\n");
        xml_.append(" $public $class InnerThree<S>:InnerFour {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerFour {\n");
        xml_.append(" $public $class InnerInnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");

        String solved_ = processAnalyze("?java.lang.String", "",context_, root_);
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterThree<OuterFour>", context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append(" $public $static $class OuterThree<T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterFour.OuterThree<OuterFour>", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("pkgthree.OuterFour..OuterThree<pkgthree.OuterFour>", solved_);
    }
    @Test
    public void processLine3Test() {
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);

        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterThree<?OuterFour>", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("pkgtwo.OuterThree<?pkgthree.OuterFour>", solved_);
    }
    @Test
    public void processLine4Test() {
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);

        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterThree<?>", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("pkgtwo.OuterThree<?>", solved_);
    }
    @Test
    public void processLine5Test() {
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        context_.getAnalyzing().getAvailableVariables().clear();
        context_.getAnalyzing().getAvailableVariables().addEntry("T",0);
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterThree<T>", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("pkgtwo.OuterThree<#T>", solved_);
    }
    @Test
    public void processLine6Test() {
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
        xml_.append("$public $class pkgtwo.OuterThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgthree.OuterFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);

        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("$Fct<~OuterFour>", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("java.lang.$Fct<~pkgthree.OuterFour>", solved_);
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
        xml_.append(" $public $static $class OuterThree<T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterFour.OuterThree<$void>", context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append(" $public $static $class OuterThree<T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterFour.OuterThree<$void", context_, root_);
        assertTrue( isEmptyErrors(context_));
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
        xml_.append(" $public $static $class OuterThree<T> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateInheriting(files_);
        
        RootBlock root_ = context_.getAnalyzing().getAnaClassBody("pkg.Outer");
        String solved_ = processAnalyzeLine("OuterFour..OuterThree<$void>", context_, root_);
        assertTrue( isEmptyErrors(context_));
        assertEq("", solved_);
    }

    @Test
    public void isKoForWord1Test() {
        assertTrue(!isKoForWord("$int"));
    }

    @Test
    public void isKoForWord2Test() {
        assertTrue(isKoForWord("$int+$int"));
    }

    @Test
    public void isKoForWord3Test() {
        assertTrue(isKoForWord("1l"));
    }

    @Test
    public void isKoForWord4Test() {
        assertTrue(isKoForWord(""));
    }

    @Test
    public void isKoForWord5Test() {
        assertTrue(!isKoForWord("#int"));
    }
    @Test
    public void isCorrectType1Test() {
        assertTrue(isCorrectType("$int"));
    }

    @Test
    public void isCorrectType2Test() {
        assertTrue(isCorrectType("$int[]"));
    }

    @Test
    public void isCorrectType3Test() {
        assertTrue(isCorrectType("pkg.Ex"));
    }
    @Test
    public void isCorrectType4Test() {
        assertTrue(isCorrectType("pkg.Ex[]"));
    }
    @Test
    public void isCorrectType5Test() {
        assertTrue(!isCorrectType("pkg()"));
    }
    @Test
    public void isCorrectType6Test() {
        assertTrue(!isCorrectType("pkg.Ex()"));
    }
    @Test
    public void isCorrectType7Test() {
        assertTrue(!isCorrectType("a<b"));
    }
    @Test
    public void isCorrectType8Test() {
        assertTrue(isCorrectType("a<b>"));
    }
    @Test
    public void isCorrectType9Test() {
        assertTrue(isCorrectType("a<b>[]"));
    }
    @Test
    public void isCorrectType10Test() {
        assertTrue(isCorrectType("a<b>.c"));
    }
    @Test
    public void isCorrectType11Test() {
        assertTrue(!isCorrectType("a<b>c"));
    }
    @Test
    public void isCorrectType12Test() {
        assertTrue(isCorrectType("a<b,c>"));
    }
    @Test
    public void isCorrectType13Test() {
        assertTrue(isCorrectType("a<b,c>[]"));
    }
    @Test
    public void isCorrectType14Test() {
        assertTrue(isCorrectType("a<b,d>.c"));
    }
    @Test
    public void isCorrectType15Test() {
        assertTrue(!isCorrectType("a<b,d>c"));
    }
    @Test
    public void isCorrectType16Test() {
        assertTrue(isCorrectType("a<b,d[]>"));
    }
    @Test
    public void isCorrectType17Test() {
        assertTrue(isCorrectType("a<b[],d>"));
    }
    @Test
    public void isCorrectType18Test() {
        assertTrue(isCorrectType("a<b<e>,d>"));
    }
    @Test
    public void isCorrectType19Test() {
        assertTrue(isCorrectType("a<b<e,f>,d>"));
    }
    @Test
    public void isCorrectType20Test() {
        assertTrue(!isCorrectType("a>b"));
    }
    @Test
    public void isCorrectType21Test() {
        assertTrue(!isCorrectType("a?b:c"));
    }
    @Test
    public void isCorrectType22Test() {
        assertTrue(!isCorrectType("?b"));
    }
    @Test
    public void isCorrectType24Test() {
        assertTrue(!isCorrectType("!b"));
    }
    @Test
    public void isCorrectType25Test() {
        assertTrue(!isCorrectType("?"));
    }
    @Test
    public void isCorrectType26Test() {
        assertTrue(isCorrectType("a<?b>"));
    }
    @Test
    public void isCorrectType27Test() {
        assertTrue(isCorrectType("a<!b>"));
    }
    @Test
    public void isCorrectType28Test() {
        assertTrue(isCorrectType("a<?b[]>"));
    }
    @Test
    public void isCorrectType29Test() {
        assertTrue(isCorrectType("a<!b[]>"));
    }
    @Test
    public void isCorrectType30Test() {
        assertTrue(isCorrectType("a<c,?b[]>"));
    }
    @Test
    public void isCorrectType31Test() {
        assertTrue(isCorrectType("a<c,!b[]>"));
    }
    @Test
    public void isCorrectType32Test() {
        assertTrue(isCorrectType("a<c,?b[]>"));
    }
    @Test
    public void isCorrectType33Test() {
        assertTrue(isCorrectType("a<c,!b[]>"));
    }
    @Test
    public void isCorrectType34Test() {
        assertTrue(!isCorrectType("a<b>.1"));
    }
    @Test
    public void isCorrectType35Test() {
        assertTrue(!isCorrectType("1"));
    }
    @Test
    public void isCorrectType36Test() {
        assertTrue(!isCorrectType("a<>"));
    }
    @Test
    public void isCorrectType37Test() {
        assertTrue(!isCorrectType("a<b*c>"));
    }
    @Test
    public void isCorrectType38Test() {
        assertTrue(!isCorrectType("a<b*c>"));
    }
    @Test
    public void isCorrectType39Test() {
        assertTrue(!isCorrectType("?a"));
    }
    @Test
    public void isCorrectType40Test() {
        assertTrue(!isCorrectType("a<!>"));
    }
    @Test
    public void isCorrectType41Test() {
        assertTrue(!isCorrectType("a[b]"));
    }
    @Test
    public void isCorrectType42Test() {
        assertTrue(!isCorrectType("a b"));
    }
    @Test
    public void isCorrectType43Test() {
        assertTrue(!isCorrectType("c<a b>"));
    }
    @Test
    public void isCorrectType44Test() {
        assertTrue(isCorrectType("c<a#>"));
    }
    @Test
    public void isCorrectType45Test() {
        assertTrue(isCorrectType("c<a#>"));
    }
    @Test
    public void isCorrectType46Test() {
        assertTrue(!isCorrectType("a,b"));
    }
    @Test
    public void isCorrectType47Test() {
        assertTrue(isCorrectType("#a.b"));
    }
    @Test
    public void isCorrectType48Test() {
        assertTrue(isCorrectType("a.#b"));
    }
    @Test
    public void isCorrectType49Test() {
        assertTrue(isCorrectType("#a<b>"));
    }
    @Test
    public void isCorrectType50Test() {
        assertTrue(!isCorrectType("a<b..c,>"));
    }
    @Test
    public void isCorrectType51Test() {
        assertTrue(!AnaPartTypeUtil.isCorrectType("a.new", new StringList("new")));
    }
    @Test
    public void isCorrectType52Test() {
        assertTrue(AnaPartTypeUtil.isCorrectType("a.int", new StringList("new")));
    }
    @Test
    public void isCorrectType53Test() {
        assertTrue(!isCorrectType("~t"));
    }
    @Test
    public void isCorrectType54Test() {
        assertTrue(!isCorrectType("[]"));
    }
    private static boolean isCorrectType(String _input) {
        return AnaPartTypeUtil.isCorrectType(_input,new StringList());
    }

    private static boolean isKoForWord(String _type) {
        return AnaPartTypeUtil.isKoForWord(_type, new StringList());
    }

    private static String processAnalyze(String _input, String _globalType, AnalyzedTestContext _an, RootBlock _rooted) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setImportingTypes(_rooted);
        AnaResultPartType anaResultPartType_ = AnaPartTypeUtil.processAnalyze(_input, false, _globalType, _rooted, _rooted, 0, new CustList<PartOffset>(), page_);
        AnaPartType partType_ = anaResultPartType_.getPartType();
        if (partType_ == null) {
            return "";
        }
        if (!AnaPartTypeUtil.checkParametersCount(partType_, page_)){
            return "";
        }
        return anaResultPartType_.getResult();
    }

    private static String processAnalyzeLine(String _input, AnalyzedTestContext _an, RootBlock _rooted) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setImportingTypes(_rooted);
        return AnaPartTypeUtil.processAnalyzeLine(_input,false,"", _rooted, _rooted, 0,new CustList<PartOffset>(), page_).getResult();
    }
}
