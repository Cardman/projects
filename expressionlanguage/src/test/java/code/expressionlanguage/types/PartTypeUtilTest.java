package code.expressionlanguage.types;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.sml.RowCol;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public final class PartTypeUtilTest {

    @Test
    public void getAllTypes1Test(){
        assertEq(new StringList("String"), PartTypeUtil.getAllTypes("String"));
    }
    @Test
    public void getAllTypes2Test(){
        assertEq(new StringList("Map","String","Rate"), PartTypeUtil.getAllTypes("Map<String,Rate>"));
    }
    @Test
    public void getAllTypes3Test(){
        assertEq(new StringList("Map","String","Map<String,Rate>"), PartTypeUtil.getAllTypes("Map<String,Map<String,Rate>>"));
    }
    @Test
    public void getAllTypes4Test(){
        assertEq(new StringList("List","Boolean"), PartTypeUtil.getAllTypes("List<Boolean>"));
    }
    @Test
    public void getAllTypes5Test(){
        assertEq(new StringList("CustList","BooleanList"), PartTypeUtil.getAllTypes("CustList<BooleanList>"));
    }
    @Test
    public void getAllTypes6Test(){
        assertEq(new StringList("Outer..Map"), PartTypeUtil.getAllTypes("Outer..Map"));
    }
    @Test
    public void getAllTypes7Test(){
        assertEq(new StringList("Map"), PartTypeUtil.getAllTypes("..Map"));
    }
    @Test
    public void getAllTypes8Test(){
        assertEq(new StringList("Map..Inner","String","Rate"), PartTypeUtil.getAllTypes("Map<String,Rate>..Inner"));
    }
    @Test
    public void getAllTypes9Test(){
        assertEq(new StringList("Map..Inner","String","Rate","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes10Test(){
        assertEq(new StringList("Map..Inner","String","Rate..Denominator","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String,Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes11Test(){
        assertEq(new StringList("Map..Inner","String..Character","Rate","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes12Test(){
        assertEq(new StringList("Map..Inner","String"), PartTypeUtil.getAllTypes("Map<String>..Inner"));
    }
    @Test
    public void getAllTypes13Test(){
        assertEq(new StringList("[String"), PartTypeUtil.getAllTypes("[String"));
    }
    @Test
    public void getAllTypes14Test(){
        assertEq(new StringList("Map","[String","Rate"), PartTypeUtil.getAllTypes("Map<[String,Rate>"));
    }
    @Test
    public void getAllTypes15Test(){
        assertEq(new StringList("[Map","String","Rate"), PartTypeUtil.getAllTypes("[Map<String,Rate>"));
    }
    @Test
    public void getAllTypes16Test(){
        assertEq(new StringList("[Map..Inner","String"), PartTypeUtil.getAllTypes("[Map<String>..Inner"));
    }
    @Test
    public void getAllTypes17Test(){
        assertEq(new StringList("Map..Inner","String","[Rate..Denominator","Boolean","Number"), PartTypeUtil.getAllTypes("Map<String,[Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes18Test(){
        assertEq(new StringList("Map..Inner","[String..Character","Rate","Boolean","Number"), PartTypeUtil.getAllTypes("Map<[String..Character,Rate>..Inner<Boolean,Number>"));
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
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo", "", context_, root_, rc_);
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
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<OuterFour>", "",context_, root_, rc_);
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
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("OuterThree<OuterFive<OuterFour>>", "",context_, root_, rc_);
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
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public {} $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("OuterTwo..InnerThree", "",context_, root_, rc_);
        assertEq("pkgtwo.OuterTwo..InnerThree", solved_);
    }
    @Test
    public void process5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo {\n");
        xml_.append(" $public {} $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("Outer..InnerThree", "",context_, root_, rc_);
        assertEq("pkgtwo.OuterTwo..InnerThree", solved_);
    }
    @Test
    public void process6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public {} $class InnerThree:OuterThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public {} $class InnerFive {\n");
        xml_.append("  $public {} $class InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("Outer..InnerThree..InnerInner", "",context_, root_, rc_);
        assertEq("pkgtwo.OuterThree..InnerFive..InnerInner", solved_);
    }
    @Test
    public void process7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo:OuterThree {\n");
        xml_.append(" $public {} $class InnerThree:OuterThree..InnerFive {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree {\n");
        xml_.append(" $public {} $class InnerFive {\n");
        xml_.append("  $public {} $class InnerInner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("..InnerThree..InnerInner", "pkg.Outer",context_, root_, rc_);
        assertEq("pkgtwo.OuterThree..InnerFive..InnerInner", solved_);
    }
    @Test
    public void process8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer: OuterTwo<java.lang.Number> {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public {} $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("Outer..InnerThree", "",context_, root_, rc_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree", solved_);
    }
    @Test
    public void process9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public {} $class InnerThree {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.Number>..InnerThree", "",context_, root_, rc_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree", solved_);
    }
    @Test
    public void process10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public {} $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("Outer<java.lang.Number>..InnerThree<java.lang.String>", "",context_, root_, rc_);
        assertEq("pkgtwo.OuterTwo<java.lang.Number>..InnerThree<java.lang.String>", solved_);
    }
    @Test
    public void process11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#U>: OuterTwo<#U> {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo<java.lang.Number>..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#T> {\n");
        xml_.append(" $public {} $class InnerThree<#V> {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree<java.lang.String> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        String solved_ = PartTypeUtil.processAnalyze("..InnerThree<java.lang.String>", "pkg.Outer<#W>",context_, root_, rc_);
        assertEq("pkgtwo.OuterTwo<#W>..InnerThree<java.lang.String>", solved_);
    }
    @Test
    public void process12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("$public $class pkg.Outer<#C>: OuterTwo<#C> {\n");
        xml_.append(" $public {} $class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerTwo:OuterTwo<#C>..InnerThree<#C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterTwo<#B>:OuterThree<#B> {\n");
        xml_.append(" $public {} $class InnerThree<#F>:OuterThree<#B>..InnerFive<#F> {\n");
        xml_.append(" }\n");
        xml_.append(" $public {} $class InnerFour:..InnerThree<#B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.OuterThree<#A> {\n");
        xml_.append(" $public {} $class InnerFive<#E> {\n");
        xml_.append("  $public {} $class InnerInner<#G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateInheritingClasses(files_);
        Classes cl_ = context_.getClasses();
        RootBlock root_ = cl_.getClassBody("pkg.Outer");
        RowCol rc_ = new RowCol();
        context_.getAvailableVariables().add("D");
        context_.getAvailableVariables().add("H");
        context_.getAvailableVariables().add("I");
        String solved_ = PartTypeUtil.processAnalyze("Outer<#D>..InnerThree<#H>..InnerInner<#I>", "",context_, root_, rc_);
        assertEq("pkgtwo.OuterThree<#D>..InnerFive<#H>..InnerInner<#I>", solved_);
    }
    private ContextEl unfullValidateInheritingClasses(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
}
