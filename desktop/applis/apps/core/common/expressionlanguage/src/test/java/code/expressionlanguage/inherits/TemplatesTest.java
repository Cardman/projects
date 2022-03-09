package code.expressionlanguage.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.DefTypePairHash;
import code.expressionlanguage.common.Matching;
import code.expressionlanguage.common.OthTypePairHash;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.*;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;


public final class TemplatesTest extends ProcessMethodCommon {
    @Test
    public void getAllInnerTypesSingleDotted1Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int"),getAllInnerTypesSingleDotted("int", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted2Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int[]"),getAllInnerTypesSingleDotted("int[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted3Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int[][]"),getAllInnerTypesSingleDotted("int[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted4Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String"),getAllInnerTypesSingleDotted("String", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex"),getAllInnerTypesSingleDotted("pkg.Ex", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted6Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String[]"),getAllInnerTypesSingleDotted("String[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex[]"),getAllInnerTypesSingleDotted("pkg.Ex[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted8Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String[][]"),getAllInnerTypesSingleDotted("String[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex[][]"),getAllInnerTypesSingleDotted("pkg.Ex[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<String>"),getAllInnerTypesSingleDotted("pkg.Ex<String>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo>"),getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<String[]>"),getAllInnerTypesSingleDotted("pkg.Ex<String[]>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo[]>"),getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo[]>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<String>[]"),getAllInnerTypesSingleDotted("pkg.Ex<String>[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo>[]"),getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo>[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex","Inner"),getAllInnerTypesSingleDotted("pkg.Ex.Inner", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex"),getAllInnerTypesSingleDotted("Ex", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<pkg.ExTwo>"),getAllInnerTypesSingleDotted("Ex<pkg.ExTwo>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<pkg.ExTwo>[]"),getAllInnerTypesSingleDotted("Ex<pkg.ExTwo>[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex","Inner"),getAllInnerTypesSingleDotted("pkg.Ex.Inner", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex","Inner"),getAllInnerTypesSingleDotted("Ex.Inner", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<ExTwo>","Inner"),getAllInnerTypesSingleDotted("Ex<ExTwo>.Inner", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo<U> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo<pkg.ExThree>>"),getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo<pkg.ExThree>>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo<U> {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo<pkg.ExThree>.Inner>"),getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo<pkg.ExThree>.Inner>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex","Inner[]"),getAllInnerTypesSingleDotted("pkg.Ex.Inner[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex","Inner[]"),getAllInnerTypesSingleDotted("Ex.Inner[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedPageEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<ExTwo>","Inner[]"),getAllInnerTypesSingleDotted("Ex<ExTwo>.Inner[]", context_));
    }

    @Test
    public void quickFormat00Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.Number>";
        String second_ = "#S";
        assertEq("#S", quickFormat(context_, first_, second_));
    }
    @Test
    public void quickFormat0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "java.lang.String";
        assertEq(second_, quickFormat(context_, first_, second_));
    }
    @Test
    public void quickFormat1Test() {
        ContextEl context_ = simpleContextEl();
        String first_ = context_.getStandards().getCharSeq().getAliasString();
        String second_ = context_.getStandards().getNbAlias().getAliasInteger();
        assertEq(second_, quickFormat(context_, first_, second_));
    }
    @Test
    public void quickFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "java.lang.String";
        assertEq("java.lang.String", quickFormat(cont_, first_, second_));
    }

    @Test
    public void quickFormat3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number", quickFormat(cont_, first_, second_));
    }

    @Test
    public void quickFormat4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E", quickFormat(cont_, first_, second_));
    }
    @Test
    public void format1Test() {
        ContextEl context_ = simpleContextEl();
        String first_ = context_.getStandards().getCharSeq().getAliasString();
        String second_ = context_.getStandards().getNbAlias().getAliasInteger();
        assertEq(second_, format(context_, first_, second_));
    }

    @Test
    public void format2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:U&V,U:W,V:W,W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.Number,java.lang.Number,java.lang.Number>";
        String second_ = "#W";
        assertEq("java.lang.Number", format(cont_, first_, second_));
    }




    @Test
    public void format3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "?#W";
        assertEq("?#W", format(cont_, first_, second_));
    }


    @Test
    public void format_3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "!#W";
        assertEq("!#W", format(cont_, first_, second_));
    }


    @Test
    public void format4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "$int";
        String second_ = "java.lang.Number";
        assertEq("java.lang.Number", format(cont_, first_, second_));
    }

    @Test
    public void format5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "#W";
        assertEq("?java.lang.Number", format(cont_, first_, second_));
    }


    @Test
    public void format6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "?#W";
        assertEq("?#W", format(cont_, first_, second_));
    }



















































    
    @Test
    public void format7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "java.lang.String";
        assertEq("java.lang.String", format(cont_, first_, second_));
    }
    
    @Test
    public void format8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "java.lang.String";
        assertEq("java.lang.String", format(cont_, first_, second_));
    }

    @Test
    public void format9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E", format(cont_, first_, second_));
    }

    @Test
    public void format10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String>";
        String second_ = "#T";
        assertEq("java.lang.String", format(cont_, first_, second_));
    }

    @Test
    public void format11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#U";
        assertEq("java.lang.Object", format(cont_, first_, second_));
    }

    @Test
    public void format12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String", format(cont_, first_, second_));
    }

    @Test
    public void format13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String", format(cont_, first_, second_));
    }

    @Test
    public void format14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#V";
        assertEq("#V", format(cont_, first_, second_));
    }

    @Test
    public void format15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "code.util.CustList<#V>";
        assertEq("code.util.CustList<#V>", format(cont_, first_, second_));
    }

    @Test
    public void typePair1(){
        assertEq("Tmp<?>", OthTypePairHash.hcode("Tmp<?>"));
    }

    @Test
    public void typePair2(){
        assertEq("Tmp<Cl>", OthTypePairHash.hcode("Tmp<Cl>"));
    }

    @Test
    public void areTypePairs1(){
        Matching m_ = new Matching();
        m_.setArg("A");
        m_.setParam("B");
        Matching n_ = new Matching();
        n_.setArg("A");
        n_.setParam("B");
        assertTrue(new OthTypePairHash().areTypePairs(m_, n_));
    }

    @Test
    public void areTypePairs2(){
        Matching m_ = new Matching();
        m_.setArg("A");
        m_.setParam("B");
        Matching n_ = new Matching();
        n_.setArg("A");
        n_.setParam("C");
        assertTrue(!new OthTypePairHash().areTypePairs(m_, n_));
    }

    @Test
    public void areTypePairs3(){
        Matching m_ = new Matching();
        m_.setArg("B");
        m_.setParam("A");
        Matching n_ = new Matching();
        n_.setArg("C");
        n_.setParam("A");
        assertTrue(!new OthTypePairHash().areTypePairs(m_, n_));
    }

    @Test
    public void areTypePairs4(){
        Matching m_ = new Matching();
        m_.setArg("B");
        m_.setParam("D");
        Matching n_ = new Matching();
        n_.setArg("C");
        n_.setParam("A");
        assertTrue(!new OthTypePairHash().areTypePairs(m_, n_));
    }

    @Test
    public void areTypePairs5(){
        Matching m_ = new Matching();
        m_.setArg("A");
        m_.setParam("B");
        Matching n_ = new Matching();
        n_.setArg("A");
        n_.setParam("B");
        assertTrue(new DefTypePairHash().areTypePairs(m_, n_));
    }

    @Test
    public void areTypePairs6(){
        Matching m_ = new Matching();
        m_.setArg("A");
        m_.setParam("B");
        Matching n_ = new Matching();
        n_.setArg("A");
        n_.setParam("C");
        assertTrue(!new DefTypePairHash().areTypePairs(m_, n_));
    }

    @Test
    public void getGenericTypeByBases1Test() {
        ContextEl context_ = simpleContextEl();
        String t_ = getFullTypeByBases(context_, "java.lang.String", "java.lang.Object");
        assertEq("java.lang.Object", t_);
    }

    @Test
    public void getGenericTypeByBases2Test() {
        ContextEl context_ = simpleContextEl();
        String t_ = getFullTypeByBases(context_, "java.lang.Object", "java.lang.String");
        assertEq("",t_);
    }

















    @Test
    public void getGenericTypeByBases6Test() {
        ContextEl context_ = simpleContextEl();
        String t_ = getFullTypeByBases(context_, "java.lang.String", "java.lang.String");
        assertEq("java.lang.String", t_);
    }









    @Test
    public void getGenericTypeByBases8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.Ex", "pkg.Ex");
        assertEq("pkg.Ex", t_);
    }

    private static String getFullTypeByBasesTmp(ContextEl _cont, String _sub, String _sup) {
        return ExecInherits.getFullTypeByBases(_sub, _sup, _cont);
    }

    @Test
    public void getGenericTypeByBases9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.ExTwo", "pkg.Ex");
        assertEq("pkg.Ex", t_);
    }

    @Test
    public void getGenericTypeByBases10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.Ex", "pkg.ExTwo");
        assertEq("",t_);
    }






    @Test
    public void getGenericTypeByBases11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<W> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.ExTwo<#V>", "pkg.ExFive");
        assertEq("pkg.ExFive<#V>", t_);
    }



    @Test
    public void getGenericTypeByBases12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.ExTwo<#V>", "pkg.Ex");
        assertEq("pkg.Ex<#V>", t_);
    }

    @Test
    public void getGenericTypeByBases13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.Number>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.ExTwo", "pkg.Ex");
        assertEq("pkg.Ex<java.lang.Number>", t_);
    }
    @Test
    public void getGenericTypeByBases14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.Ex<#V>", "pkg.Ex");
        assertEq("pkg.Ex<#V>", t_);
    }
    @Test
    public void getGenericTypeByBases15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree<X> {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExThree<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.Ex<#V>", "pkg.ExThree");
        assertEq("pkg.ExThree<#V>", t_);
    }
    @Test
    public void correctNbParameters0Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertTrue(!correctNbParameters(cont_, "pkg"));
    }

    @Test
    public void correctNbParameters1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<W> {$public $static $class Inner<X> {}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertTrue(!correctNbParameters(cont_, "pkg.Ex<java.lang.Number>..Inner<java.lang.Number>"));
    }
    @Test
    public void correctNbParameters2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertTrue(!correctNbParameters(cont_, ""));
    }

    @Test
    public void isCorrectTemplate69Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExTwo<T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<?[#T>";
        String res_ = getFullTypeByBases(cont_, first_, second_);
        assertEq("pkg.ExTwo<?[java.lang.Number>", res_);
    }

    @Test
    public void getFullTypeByBases7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExTwo<T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<[#T>";
        String res_ = getFullTypeByBases(cont_, first_, second_);
        assertEq("pkg.ExTwo<?[java.lang.Number>", res_);
    }


    @Test
    public void getFullTypeByBases8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExTwo<T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<?[#T>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<?>",res_);
    }

    @Test
    public void getFullTypeByBases9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExTwo<T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<[#T>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<?[java.lang.Number>",res_);
    }


    @Test
    public void getFullTypeByBases10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExTwo<T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<#T>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<?java.lang.Number>",res_);
    }






    @Test
    public void getFullTypeByBases11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExTwo<T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.ExTwo<![#T>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<?>",res_);
    }


    @Test
    public void getFullTypeByBases12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:pkg.ExTwo<T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "[#T";
        String res_ = format(cont_, first_, second_);
        assertEq("[java.lang.Number",res_);
    }

    @Test
    public void getFullTypeByBases13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T,U>:pkg.ExTwo<T[],U[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S,R>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.String>";
        String second_ = "pkg.ExTwo<[#T,[#U>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<[java.lang.Number,[java.lang.String>",res_);
    }

    @Test
    public void getFullTypeByBases14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T,U,V>:pkg.ExTwo<T[],U[],V[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S,R,Q>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.String,java.lang.Object>";
        String second_ = "pkg.ExTwo<[#T,[#U,[#V>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<[java.lang.Number,[java.lang.String,[java.lang.Object>",res_);
    }


    @Test
    public void getFullTypeByBases15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T,U,V>:pkg.ExTwo<T[],U[],V[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S,R,Q>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.String,java.lang.Object>";
        String second_ = "pkg.ExTwo<[#T>..Inner<[#U,[#V>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<[java.lang.Number>..Inner<[java.lang.String,[java.lang.Object>",res_);
    }

    @Test
    public void getFullTypeByBases16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T,U,V>:pkg.ExTwo<T[],U[],V[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<S,R,Q>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.String,java.lang.Object>";
        String second_ = "pkg.ExTwo<[#T,[#U>..Inner<[#V>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<[java.lang.Number,[java.lang.String>..Inner<[java.lang.Object>",res_);
    }



































    @Test
    public void getAllGenericSuperTypes6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl cont_ = unfullValidateOverridingMethodsAna(files_);
        String className_ = StringExpUtil.getIdFromAllTypes("pkg.Ex<E>");
        RootBlock root_ = cont_.getAnaClassBody(className_);
        CustList<AnaFormattedRootBlock> superTypes_ = root_.getAllGenericSuperTypesInfo();
        assertEq(0, superTypes_.size());
    }

    @Test
    public void getAllGenericSuperTypes7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {}\n");
        xml_.append("$public $class pkg.Ex<E:java.lang.Number>:pkg.ExTwo<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl cont_ = unfullValidateOverridingMethodsAna(files_);
        String className_ = StringExpUtil.getIdFromAllTypes("pkg.Ex");
        RootBlock root_ = cont_.getAnaClassBody(className_);
        CustList<AnaFormattedRootBlock> superTypes_ = root_.getAllGenericSuperTypesInfo();
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.get(0).getFormatted());
    }

    @Test
    public void getOverridingFullTypeByBasesTest() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertEq("", getOverridingFullTypeByBases(cont_, "Inex", ""));
    }

    private static AnalyzedPageEl unfullValidateOverridingMethodsStd(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = getLgNames();
        KeyWords kwl_ = getKeyWords("en",lgName_);
        setOpts(opt_,IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(opt_,lgName_,kwl_,page_);;
        parseCustomFiles(_files, page_);
        assertTrue(page_.getMessages().displayErrors(), isEmptyErrors(page_));
        validateInheritingClasses(page_);
        assertTrue(page_.getMessages().displayErrors(), isEmptyErrors(page_));
        postValidation(page_,forwards_);
        return page_;
    }
    private static ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_,IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(opt_,lgName_,kw_,page_);;
        parseCustomFiles(_files, page_);
        assertTrue(page_.getMessages().displayErrors(), isEmptyErrors(page_));
        validateInheritingClasses(page_);
        assertTrue(page_.getMessages().displayErrors(), isEmptyErrors(page_));
        postValidation(page_,forwards_);
        return forwards_.generate();
    }
    private static AnalyzedPageEl unfullValidateOverridingMethodsAna(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_,IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(opt_,lgName_,kw_,page_);;
        parseCustomFiles(_files, page_);
        assertTrue(page_.getMessages().displayErrors(), isEmptyErrors(page_));
        validateInheritingClasses(page_);
        assertTrue(page_.getMessages().displayErrors(), isEmptyErrors(page_));
        postValidation(page_,forwards_);
        return page_;
    }

    private static ContextEl simpleContextEl() {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_,IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(opt_,lgName_,kw_,page_);;
        StringMap<String> files_ = page_.buildFiles();
        buildFilesBodies(page_, files_);
        parseFiles(page_);
        validateInheritingClasses(page_);
        validateIds(page_);
        validateOverridingInherit(page_);
        validateEl(page_);
        checkInterfaces(page_);
        return forwards_.generate();
    }

    private static String getOverridingFullTypeByBases(ContextEl _cont, String _sub, String _sup) {
        return ExecInherits.getFullObject(_sub, _sup, _cont);
    }

    private static String getFullTypeByBases(ContextEl _context, String _s, String _s2) {
        return getFullTypeByBasesTmp(_context, _s, _s2);
    }

    private static String quickFormat(ContextEl _context, String _first, String _second) {
        return ExecInherits.quickFormat(_first, _second, _context);
    }

    private static String format(ContextEl _context, String _first, String _second) {
        return ExecInherits.format(_first, _second, _context);
    }

    private static StringList getAllInnerTypesSingleDotted(String _type, AnalyzedPageEl _an) {
        return Templates.getAllInnerTypesSingleDotted(_type, _an);
    }

    private static boolean correctNbParameters(ContextEl _cont, String _pkg) {
        return ExecInherits.correctNbParameters(_pkg, _cont);
    }

}
