package code.expressionlanguage.inherits;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


public final class TemplatesTest extends ProcessMethodCommon {
    @Test
    public void getAllInnerTypesSingleDotted1Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int"),getAllInnerTypesSingleDotted("int", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted2Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int[]"),getAllInnerTypesSingleDotted("int[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted3Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int[][]"),getAllInnerTypesSingleDotted("int[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted4Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String"),getAllInnerTypesSingleDotted("String", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex"),getAllInnerTypesSingleDotted("pkg.Ex", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted6Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String[]"),getAllInnerTypesSingleDotted("String[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex[]"),getAllInnerTypesSingleDotted("pkg.Ex[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted8Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String[][]"),getAllInnerTypesSingleDotted("String[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex[][]"),getAllInnerTypesSingleDotted("pkg.Ex[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo>"),getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo[]>"),getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo[]>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
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
        AnalyzedTestContext context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<ExTwo>","Inner[]"),getAllInnerTypesSingleDotted("Ex<ExTwo>.Inner[]", context_));
    }

    @Test
    public void quickFormat00Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.Number>";
        String second_ = "#S";
        assertEq("S", quickFormat(context_, first_, second_));
    }
    @Test
    public void quickFormat0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "java.lang.String";
        assertEq(second_, quickFormat(context_, first_, second_));
    }
    @Test
    public void quickFormat1Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        String first_ = context_.getStandards().getAliasString();
        String second_ = context_.getStandards().getAliasInteger();
        assertEq(second_, quickFormat(context_, first_, second_));
    }
    @Test
    public void quickFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E", quickFormat(cont_, first_, second_));
    }
    @Test
    public void format1Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        String first_ = context_.getStandards().getAliasString();
        String second_ = context_.getStandards().getAliasInteger();
        assertEq(second_, format(context_, first_, second_));
    }

    @Test
    public void format2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:U&V,U:W,V:W,W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "?#W";
        assertEq("?java.lang.Number", format(cont_, first_, second_));
    }


    @Test
    public void format_3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "!#W";
        assertEq("!java.lang.Number", format(cont_, first_, second_));
    }


    @Test
    public void format4Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "?#W";
        assertEq("?java.lang.Number", format(cont_, first_, second_));
    }



















































    
    @Test
    public void format7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "code.util.CustList<#V>";
        assertEq("code.util.CustList<#V>", format(cont_, first_, second_));
    }
    @Test
    public void getGenericTypeByBases1Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        String t_ = getFullTypeByBases(context_, "java.lang.String", "java.lang.Object");
        assertEq("java.lang.Object", t_);
    }

    @Test
    public void getGenericTypeByBases2Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        String t_ = getFullTypeByBases(context_, "java.lang.Object", "java.lang.String");
        assertEq("",t_);
    }

















    @Test
    public void getGenericTypeByBases6Test() {
        AnalyzedTestContext context_ = simpleContextEl();
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.Ex", "pkg.Ex");
        assertEq("pkg.Ex", t_);
    }

    private static String getFullTypeByBasesTmp(ContextEl cont_, String _sub, String _sup) {
        return ExecTemplates.getFullTypeByBases(_sub, _sup, cont_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String t_ = getFullTypeByBases(cont_, "pkg.Ex<#V>", "pkg.ExThree");
        assertEq("pkg.ExThree<#V>", t_);
    }
    @Test
    public void correctNbParameters0Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertTrue(!correctNbParameters(cont_, "pkg"));
    }

    @Test
    public void correctNbParameters1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<W> {$public $static $class Inner<X> {}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertTrue(!correctNbParameters(cont_, "pkg.Ex<java.lang.Number>..Inner<java.lang.Number>"));
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<?[#T>";
        String res_ = format(cont_, first_, second_);
        assertEq("pkg.ExTwo<?[java.lang.Number>",res_);
    }



















































    @Test
    public void getAllGenericSuperTypes6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String className_ = StringExpUtil.getIdFromAllTypes("pkg.Ex<E>");
        RootBlock root_ = cont_.getAnalyzing().getAnaClassBody(className_);
        StringList superTypes_ = root_.getAllGenericSuperTypes();
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
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String className_ = StringExpUtil.getIdFromAllTypes("pkg.Ex");
        RootBlock root_ = cont_.getAnalyzing().getAnaClassBody(className_);
        StringList superTypes_ = root_.getAllGenericSuperTypes();
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.get(0));
    }

    @Test
    public void getOverridingFullTypeByBasesTest() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertEq("", getOverridingFullTypeByBases(cont_, "Inex", ""));
    }

    private static String getOverridingFullTypeByBases(AnalyzedTestContext cont_, String _sub, String _sup) {
        return ExecTemplates.getOverridingFullTypeByBases(_sub, _sup,cont_.getContext());
    }

    private static AnalyzedTestContext unfullValidateOverridingMethodsStd(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxLgAna("en");
        ClassesUtil.tryBuildAllBracedClassesBodies(_files, cont_.getContext(), new StringMap<ExecFileBlock>());
        assertTrue(cont_.getAnalyzing().getMessages().displayErrors(), isEmptyErrors(cont_));
        ClassesUtil.validateInheritingClasses(cont_.getContext());
        assertTrue(cont_.getAnalyzing().getMessages().displayErrors(), isEmptyErrors(cont_));
        return cont_;
    }
    private static AnalyzedTestContext unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = ctx();
        ClassesUtil.tryBuildAllBracedClassesBodies(_files, cont_, new StringMap<ExecFileBlock>());
        assertTrue(cont_.getAnalyzing().getMessages().displayErrors(), isEmptyErrors(cont_));
        ClassesUtil.validateInheritingClasses(cont_);
        assertTrue(cont_.getAnalyzing().getMessages().displayErrors(), isEmptyErrors(cont_));
        return new AnalyzedTestContext(cont_, cont_.getAnalyzing());
    }

    private static AnalyzedTestContext simpleContextEl() {
        ContextEl cont_ = ctx();
        AnalyzedPageEl page_ = cont_.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        StringMap<String> files_ = stds_.buildFiles(page_);
        StringMap<FileBlock> out_ = new StringMap<FileBlock>();
        StringMap<ExecFileBlock> outExec_ = new StringMap<ExecFileBlock>();
        ClassesUtil.buildFilesBodies(cont_, files_,true,out_,outExec_);
        ClassesUtil.parseFiles(cont_,out_,outExec_);
        ClassesUtil.validateInheritingClasses(cont_);
        ClassesUtil.validateIds(cont_);
        ClassesUtil.validateOverridingInherit(cont_);
        ClassesUtil.validateEl(cont_);
        AnaTypeUtil.checkInterfaces(cont_);
        return new AnalyzedTestContext(cont_,page_);
    }

    private static String getFullTypeByBases(AnalyzedTestContext context_, String s, String s2) {
        return getFullTypeByBasesTmp(context_.getContext(), s, s2);
    }

    private static String quickFormat(AnalyzedTestContext context_, String first_, String second_) {
        return ExecTemplates.quickFormat(first_, second_, context_.getContext());
    }

    private static String format(AnalyzedTestContext context_, String first_, String second_) {
        return ExecTemplates.format(first_, second_, context_.getContext());
    }

    private static StringList getAllInnerTypesSingleDotted(String _type, AnalyzedTestContext _an) {
        return Templates.getAllInnerTypesSingleDotted(_type,_an.getContext());
    }

    private static boolean correctNbParameters(AnalyzedTestContext cont_, String pkg) {
        return ExecTemplates.correctNbParameters(pkg, cont_.getContext());
    }

}
