package code.expressionlanguage.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.analyze.blocks.Classes;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


public final class TemplatesTest extends ProcessMethodCommon {
    @Test
    public void getAllInnerTypesSingleDotted1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int"),Templates.getAllInnerTypesSingleDotted("int", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int[]"),Templates.getAllInnerTypesSingleDotted("int[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted3Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("int[][]"),Templates.getAllInnerTypesSingleDotted("int[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String"),Templates.getAllInnerTypesSingleDotted("String", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex"),Templates.getAllInnerTypesSingleDotted("pkg.Ex", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted6Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String[]"),Templates.getAllInnerTypesSingleDotted("String[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex[]"),Templates.getAllInnerTypesSingleDotted("pkg.Ex[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted8Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("String[][]"),Templates.getAllInnerTypesSingleDotted("String[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex[][]"),Templates.getAllInnerTypesSingleDotted("pkg.Ex[][]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<String>"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<String>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo>"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<String[]>"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<String[]>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo[]>"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo[]>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<String>[]"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<String>[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo>[]"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo>[]", context_));
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
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex","Inner"),Templates.getAllInnerTypesSingleDotted("pkg.Ex.Inner", context_));
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
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex"),Templates.getAllInnerTypesSingleDotted("Ex", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<pkg.ExTwo>"),Templates.getAllInnerTypesSingleDotted("Ex<pkg.ExTwo>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<pkg.ExTwo>[]"),Templates.getAllInnerTypesSingleDotted("Ex<pkg.ExTwo>[]", context_));
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
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex","Inner"),Templates.getAllInnerTypesSingleDotted("pkg.Ex.Inner", context_));
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
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex","Inner"),Templates.getAllInnerTypesSingleDotted("Ex.Inner", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<ExTwo>","Inner"),Templates.getAllInnerTypesSingleDotted("Ex<ExTwo>.Inner", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo<#U> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo<pkg.ExThree>>"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo<pkg.ExThree>>", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo<#U> {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex<pkg.ExTwo<pkg.ExThree>.Inner>"),Templates.getAllInnerTypesSingleDotted("pkg.Ex<pkg.ExTwo<pkg.ExThree>.Inner>", context_));
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
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("pkg.Ex","Inner[]"),Templates.getAllInnerTypesSingleDotted("pkg.Ex.Inner[]", context_));
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
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex","Inner[]"),Templates.getAllInnerTypesSingleDotted("Ex.Inner[]", context_));
    }
    @Test
    public void getAllInnerTypesSingleDotted27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {\n");
        xml_.append(" public class Inner {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethodsStd(files_);
        assertEq(new StringList("Ex<ExTwo>","Inner[]"),Templates.getAllInnerTypesSingleDotted("Ex<ExTwo>.Inner[]", context_));
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
        assertEq("S",Templates.quickFormat(first_, second_, context_));
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
        assertEq(second_,Templates.quickFormat(first_, second_, context_));
    }
    @Test
    public void quickFormat1Test() {
        ContextEl context_ = simpleContextEl();
        String first_ = context_.getStandards().getAliasString();
        String second_ = context_.getStandards().getAliasInteger();
        assertEq(second_,Templates.quickFormat(first_, second_, context_));
    }
    @Test
    public void quickFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "java.lang.String";
        assertEq("java.lang.String",Templates.quickFormat(first_, second_, cont_));
    }

    @Test
    public void quickFormat3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number",Templates.quickFormat(first_, second_, cont_));
    }

    @Test
    public void quickFormat4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E",Templates.quickFormat(first_, second_, cont_));
    }
    @Test
    public void format1Test() {
        ContextEl context_ = simpleContextEl();
        String first_ = context_.getStandards().getAliasString();
        String second_ = context_.getStandards().getAliasInteger();
        assertEq(second_,Templates.format(first_, second_, context_));
    }

    @Test
    public void format2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:#U&#V,#U:#W,#V:#W,#W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.Number,java.lang.Number,java.lang.Number>";
        String second_ = "#W";
        assertEq("java.lang.Number",Templates.format(first_, second_, cont_));
    }




    @Test
    public void format3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "?#W";
        assertEq("",Templates.format(first_, second_, cont_));
    }


    @Test
    public void format4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "$int";
        String second_ = "java.lang.Number";
        assertEq("java.lang.Number",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "#W";
        assertEq("?java.lang.Number",Templates.format(first_, second_, cont_));
    }


    @Test
    public void format6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#W> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "?#W";
        assertEq("",Templates.format(first_, second_, cont_));
    }



















































    
    @Test
    public void format7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "java.lang.String";
        assertEq("java.lang.String",Templates.format(first_, second_, cont_));
    }
    
    @Test
    public void format8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "java.lang.String";
        assertEq("java.lang.String",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#U";
        assertEq("java.lang.Object",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#V";
        assertEq("#V",Templates.format(first_, second_, cont_));
    }

    @Test
    public void format15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "code.util.CustList<#V>";
        assertEq("code.util.CustList<#V>",Templates.format(first_, second_, cont_));
    }
    @Test
    public void getGenericTypeByBases1Test() {
        ContextEl context_ = simpleContextEl();
        String t_ = Templates.getFullTypeByBases("java.lang.String", "java.lang.Object", context_);
        assertEq("java.lang.Object", t_);
    }

    @Test
    public void getGenericTypeByBases2Test() {
        ContextEl context_ = simpleContextEl();
        String t_ = Templates.getFullTypeByBases("java.lang.Object", "java.lang.String", context_);
        assertEq("",t_);
    }

















    @Test
    public void getGenericTypeByBases6Test() {
        ContextEl context_ = simpleContextEl();
        String t_ = Templates.getFullTypeByBases("java.lang.String", "java.lang.String", context_);
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
        String t_ = Templates.getFullTypeByBases("pkg.Ex", "pkg.Ex", cont_);
        assertEq("pkg.Ex", t_);
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
        String t_ = Templates.getFullTypeByBases("pkg.ExTwo", "pkg.Ex", cont_);
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
        String t_ = Templates.getFullTypeByBases("pkg.Ex", "pkg.ExTwo", cont_);
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
        String t_ = Templates.getFullTypeByBases("pkg.ExTwo<#V>", "pkg.ExFive", cont_);
        assertEq("pkg.ExFive<#V>", t_);
    }



    @Test
    public void getGenericTypeByBases12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#U> :pkg.Ex<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = Templates.getFullTypeByBases("pkg.ExTwo<#V>", "pkg.Ex", cont_);
        assertEq("pkg.Ex<#V>", t_);
    }

    @Test
    public void getGenericTypeByBases13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.Number>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = Templates.getFullTypeByBases("pkg.ExTwo", "pkg.Ex", cont_);
        assertEq("pkg.Ex<java.lang.Number>", t_);
    }
    @Test
    public void getGenericTypeByBases14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#U> :pkg.Ex<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = Templates.getFullTypeByBases("pkg.Ex<#V>", "pkg.Ex", cont_);
        assertEq("pkg.Ex<#V>", t_);
    }
    @Test
    public void getGenericTypeByBases15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree<#X> {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T>:pkg.ExThree<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#U> :pkg.Ex<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String t_ = Templates.getFullTypeByBases("pkg.Ex<#V>", "pkg.ExThree", cont_);
        assertEq("pkg.ExThree<#V>", t_);
    }
    @Test
    public void correctNbParameters0Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertTrue(!Templates.correctNbParameters("pkg",cont_));
    }
    @Test
    public void correctNbParameters1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#W> {$public $static $class Inner<#X> {}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertTrue(!Templates.correctNbParameters("pkg.Ex<java.lang.Number>..Inner<java.lang.Number>",cont_));
    }


    @Test
    public void isCorrectTemplate69Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T>:pkg.ExTwo<#T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<?[#T>";
        String res_ = Templates.getFullTypeByBases(first_, second_, cont_);
        assertEq("pkg.ExTwo<?[java.lang.Number>", res_);
    }

    @Test
    public void getFullTypeByBases7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T>:pkg.ExTwo<#T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<[#T>";
        String res_ = Templates.getFullTypeByBases(first_, second_, cont_);
        assertEq("pkg.ExTwo<?[java.lang.Number>", res_);
    }


    @Test
    public void getFullTypeByBases8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T>:pkg.ExTwo<#T[]> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#S>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.ExTwo<?[#T>";
        String res_ = Templates.format(first_, second_, cont_);
        assertEq("",res_);
    }



















































    @Test
    public void getAllGenericSuperTypes6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String className_ = StringExpUtil.getIdFromAllTypes("pkg.Ex<#E>");
        GeneType root_ = cont_.getClassBody(className_);
        StringList superTypes_ = root_.getAllGenericSuperTypes();
        assertEq(0, superTypes_.size());
    }

    @Test
    public void getAllGenericSuperTypes7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number> {}\n");
        xml_.append("$public $class pkg.Ex<#E:java.lang.Number>:pkg.ExTwo<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String className_ = StringExpUtil.getIdFromAllTypes("pkg.Ex");
        GeneType root_ = cont_.getClassBody(className_);
        StringList superTypes_ = root_.getAllGenericSuperTypes();
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.get(0));
    }

    private static ContextEl unfullValidateOverridingMethodsStd(StringMap<String> _files) {
        ContextEl cont_ = contextEnElDefault();
        return getContextEl(_files, cont_);
    }
    private static ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = getSimpleContextEl();
        return getContextEl(_files, cont_);
    }

    private static ContextEl getContextEl(StringMap<String> _files, ContextEl _cont) {
        Classes classes_ = _cont.getClasses();
        _cont.setAnalyzing();
        Classes.buildPredefinedBracesBodies(_cont);
        CustList<RootBlock> foundTypes_ = _cont.getAnalyzing().getFoundTypes();
        _cont.setAnalyzing();
        _cont.getAnalyzing().getPreviousFoundTypes().addAllElts(foundTypes_);
        Classes.tryBuildBracedClassesBodies(_files, _cont, false);
        assertTrue(classes_.displayErrors(), _cont.isEmptyErrors());
        assertTrue(classes_.displayErrors(), _cont.isEmptyErrors());
        Classes.validateInheritingClasses(_cont);
        assertTrue(classes_.displayErrors(), _cont.isEmptyErrors());
        return _cont;
    }

    private static ContextEl simpleContextEl() {
        ContextEl cont_ = getSimpleContextEl();
        cont_.setAnalyzing();
        Classes.buildPredefinedBracesBodies(cont_);
        CustList<RootBlock> foundTypes_ = cont_.getAnalyzing().getFoundTypes();
        cont_.setAnalyzing();
        cont_.getAnalyzing().getPreviousFoundTypes().addAllElts(foundTypes_);
        return cont_;
    }
}
