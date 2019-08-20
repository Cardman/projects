package code.expressionlanguage.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ErrorType;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;


public final class TemplatesTest {

    private static final String ARR_OBJECT = "[java.lang.Object";
    private static final String ARR_STRING = "[java.lang.String";















    private static final String ARR_VAR_S = "[#S";
    private static final String ARR_VAR_T = "[#T";

    @Test
    public void getAllTypes1Test(){
        assertEq(new StringList("String"), Templates.getAllTypes("String"));
    }
    @Test
    public void getAllTypes2Test(){
        assertEq(new StringList("Map","String","Rate"), Templates.getAllTypes("Map<String,Rate>"));
    }
    @Test
    public void getAllTypes3Test(){
        assertEq(new StringList("Map","String","Map<String,Rate>"), Templates.getAllTypes("Map<String,Map<String,Rate>>"));
    }
    @Test
    public void getAllTypes4Test(){
        assertEq(new StringList("List","Boolean"), Templates.getAllTypes("List<Boolean>"));
    }
    @Test
    public void getAllTypes5Test(){
        assertEq(new StringList("CustList","BooleanList"), Templates.getAllTypes("CustList<BooleanList>"));
    }
    @Test
    public void getAllTypes6Test(){
        assertEq(new StringList("Outer..Map"), Templates.getAllTypes("Outer..Map"));
    }
    @Test
    public void getAllTypes7Test(){
        assertEq(new StringList("..Map"), Templates.getAllTypes("..Map"));
    }
    @Test
    public void getAllTypes8Test(){
        assertEq(new StringList("Map..Inner","String","Rate"), Templates.getAllTypes("Map<String,Rate>..Inner"));
    }
    @Test
    public void getAllTypes9Test(){
        assertEq(new StringList("Map..Inner","String","Rate","Boolean","Number"), Templates.getAllTypes("Map<String,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes10Test(){
        assertEq(new StringList("Map..Inner","String","Rate..Denominator","Boolean","Number"), Templates.getAllTypes("Map<String,Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes11Test(){
        assertEq(new StringList("Map..Inner","String..Character","Rate","Boolean","Number"), Templates.getAllTypes("Map<String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes12Test(){
        assertEq(new StringList("Map..Inner","String"), Templates.getAllTypes("Map<String>..Inner"));
    }
    @Test
    public void getAllTypes13Test(){
        assertEq(new StringList("[String"), Templates.getAllTypes("[String"));
    }
    @Test
    public void getAllTypes14Test(){
        assertEq(new StringList("Map","[String","Rate"), Templates.getAllTypes("Map<[String,Rate>"));
    }
    @Test
    public void getAllTypes15Test(){
        assertEq(new StringList("[Map","String","Rate"), Templates.getAllTypes("[Map<String,Rate>"));
    }
    @Test
    public void getAllTypes16Test(){
        assertEq(new StringList("[Map..Inner","String"), Templates.getAllTypes("[Map<String>..Inner"));
    }
    @Test
    public void getAllTypes17Test(){
        assertEq(new StringList("Map..Inner","String","[Rate..Denominator","Boolean","Number"), Templates.getAllTypes("Map<String,[Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes18Test(){
        assertEq(new StringList("Map..Inner","[String..Character","Rate","Boolean","Number"), Templates.getAllTypes("Map<[String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes19Test(){
        assertEq(new StringList("Map","Map<[String..Character,Rate>","Number"), Templates.getAllTypes("Map<Map<[String..Character,Rate>,Number>"));
    }
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
    public void getAllInnerTypes1Test(){
        assertEq(new StringList("String"), Templates.getAllInnerTypes("String"));
    }
    @Test
    public void getAllInnerTypes2Test(){
        assertEq(new StringList("Map<String,Rate>"), Templates.getAllInnerTypes("Map<String,Rate>"));
    }
    @Test
    public void getAllInnerTypes3Test(){
        assertEq(new StringList("Map<String,Map<String,Rate>>"), Templates.getAllInnerTypes("Map<String,Map<String,Rate>>"));
    }
    @Test
    public void getAllInnerTypes4Test(){
        assertEq(new StringList("List<Boolean>"), Templates.getAllInnerTypes("List<Boolean>"));
    }
    @Test
    public void getAllInnerTypes5Test(){
        assertEq(new StringList("CustList<BooleanList>"), Templates.getAllInnerTypes("CustList<BooleanList>"));
    }
    @Test
    public void getAllInnerTypes6Test(){
        assertEq(new StringList("Outer","Map"), Templates.getAllInnerTypes("Outer..Map"));
    }
    @Test
    public void getAllInnerTypes7Test(){
        assertEq(new StringList("","Map"), Templates.getAllInnerTypes("..Map"));
    }
    @Test
    public void getAllInnerTypes8Test(){
        assertEq(new StringList("Map<String,Rate>","Inner"), Templates.getAllInnerTypes("Map<String,Rate>..Inner"));
    }
    @Test
    public void getAllInnerTypes9Test(){
        assertEq(new StringList("Map<String,Rate>","Inner<Boolean,Number>"), Templates.getAllInnerTypes("Map<String,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes10Test(){
        assertEq(new StringList("Map<String,Rate..Denominator>","Inner<Boolean,Number>"), Templates.getAllInnerTypes("Map<String,Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes11Test(){
        assertEq(new StringList("Map<String..Character,Rate>","Inner<Boolean,Number>"), Templates.getAllInnerTypes("Map<String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes12Test(){
        assertEq(new StringList("Map<String>","Inner"), Templates.getAllInnerTypes("Map<String>..Inner"));
    }
    @Test
    public void getAllInnerTypes13Test(){
        assertEq(new StringList("[String"), Templates.getAllInnerTypes("[String"));
    }
    @Test
    public void getAllInnerTypes14Test(){
        assertEq(new StringList("Map<[String,Rate>"), Templates.getAllInnerTypes("Map<[String,Rate>"));
    }
    @Test
    public void getAllInnerTypes15Test(){
        assertEq(new StringList("[Map<String,Rate>"), Templates.getAllInnerTypes("[Map<String,Rate>"));
    }
    @Test
    public void getAllInnerTypes16Test(){
        assertEq(new StringList("[Map<String>","Inner"), Templates.getAllInnerTypes("[Map<String>..Inner"));
    }
    @Test
    public void getAllInnerTypes17Test(){
        assertEq(new StringList("Map<String,[Rate..Denominator>","Inner<Boolean,Number>"), Templates.getAllInnerTypes("Map<String,[Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes18Test(){
        assertEq(new StringList("Map<[String..Character,Rate>","Inner<Boolean,Number>"), Templates.getAllInnerTypes("Map<[String..Character,Rate>..Inner<Boolean,Number>"));
    }

    @Test
    public void getAllSepCommaTypes1Test(){
        assertEq(new StringList("Number","<<","$int"), Templates.getAllSepCommaTypes("Number,<<,$int"));
    }

    @Test
    public void getAllSepCommaTypes2Test(){
        assertEq(new StringList("Number<Map<$int,$long>>","Number"), Templates.getAllSepCommaTypes("Number<Map<$int,$long>>,Number"));
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
        assertNull(Templates.format(first_, second_, cont_));
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
    public void wildCardFormat1Test() {
        ContextEl context_ = simpleContextEl();
        String first_ = context_.getStandards().getAliasString();
        String second_ = context_.getStandards().getAliasInteger();
        assertEq(second_,Templates.wildCardFormatReturn(false, first_, second_, context_));
    }


    @Test
    public void wildCardFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }


    @Test
    public void wildCardFormat5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<java.lang.Object>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<java.lang.Object>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }


    @Test
    public void wildCardFormat9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#U";
        assertEq("java.lang.Object",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "[#T";
        assertEq("[java.lang.String",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "code.util.CustList<#V>";
        assertEq("code.util.CustList<#V>",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.Number>";
        String second_ = "code.util.CustList<#T>";
        assertEq("code.util.CustList<java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }
    @Test
    public void wildCardFormat17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E:java.lang.Number,#F> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.ExTwo<java.lang.Number,java.lang.Number>";
        String second_ = "pkg.Ex<#T,#T>";
        assertEq("pkg.Ex<java.lang.Number,java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertEq("pkg.Ex<?java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<!java.lang.Number>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Number>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertEq("pkg.Ex<!java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<!java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Number>",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<java.lang.Object>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?java.lang.Number>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<java.lang.Object>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Number>",Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<#T>";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "#T";
        assertEq("java.lang.Object",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "[#T";
        assertEq("[java.lang.Object",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "[#T";
        assertEq("[java.lang.Number",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Object",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "[#T";
        assertEq("[java.lang.Object",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "#T";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "[#T";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "#T";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "[#T";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number",Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "[#T";
        assertEq("[java.lang.Number",Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#V";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, cont_));
    }
    @Test
    public void wildCardFormat50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number",Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#V";
        assertEq("java.lang.Object",Templates.wildCardFormatReturn(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T,#U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "code.util.CustList<#V>";
        assertEq("code.util.CustList<#V>",Templates.wildCardFormatParam(false, first_, second_, cont_));
    }

    @Test
    public void wildCardFormat53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?#E>";
        String second_ = "[#T";
        assertEq("[#E",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex";
        String second_ = "pkg.Ex<[#T>";
        assertEq("java.lang.Object",Templates.wildCardFormatReturn(false, first_, second_, context_));
    }

    @Test
    public void wildCardFormat55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex";
        String second_ = "pkg.Ex<[#T>";
        assertNull(Templates.wildCardFormatParam(false, first_, second_, context_));
    }
    @Test
    public void reflectFormat1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<![java.lang.Object>",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<?>",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<[#E>";
        assertEq("pkg.Ex<[#E>",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "[#E";
        assertEq("[#E",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Object>",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>",Templates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Object>",Templates.reflectFormat(first_,second_,context_));
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
        assertNull(t_);
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
        assertNull(t_);
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
    public void tryInfer1Test() {
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
        String inferred_ = Templates.tryInfer("pkg.ExTwo",new StringMap<String>(), "pkg.ExFive<java.lang.Number>", cont_);
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive {}\n");
        xml_.append("$public $interface pkg.ExFive {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertNull(Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive", cont_));
    }

    @Test
    public void tryInfer3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive {}\n");
        xml_.append("$public $interface pkg.ExFive {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        xml_.append("$public $class pkg.ExOther{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertNull(Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExOther", cont_));
    }

    @Test
    public void tryInfer4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<java.lang.Number>>", cont_);
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<?W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<?java.lang.Number>>", cont_);
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<!W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<!java.lang.Number>>", cont_);
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<?>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertNull(Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<?>>", cont_));
    }

    @Test
    public void tryInfer8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<W[]>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<[java.lang.Number>>", cont_);
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<?W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertNull(Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<!java.lang.Number>>", cont_));
    }

    @Test
    public void tryInfer10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<!W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertNull(Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<?java.lang.Number>>", cont_));
    }

    @Test
    public void tryInfer11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<W[]>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertNull(Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<java.lang.Number>>", cont_));
    }

    @Test
    public void tryInfer12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<ExIter<W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        xml_.append("$public $class pkg.ExIter<Z> :$iterable<Z>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        assertNull(Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExFive<java.lang.$iterable<java.lang.Number>>", cont_));
    }
    @Test
    public void tryInfer13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<ExIter<W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        xml_.append("$public $class pkg.ExIter<Z> :$iterable<Z>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = Templates.tryInfer("pkg.ExTwo", new StringMap<String>(),"pkg.ExTwo<java.lang.$iterable<java.lang.Number>>", cont_);
        assertEq("pkg.ExTwo<java.lang.$iterable<java.lang.Number>>", inferred_);
    }
    @Test
    public void getInferForm1Test() {
        assertNull(Templates.getInferForm("java.lang.Number"));
    }
    @Test
    public void getInferForm2Test() {
        assertNull(Templates.getInferForm("java.lang.Number>"));
    }
    @Test
    public void getInferForm3Test() {
        assertNull(Templates.getInferForm("java.lang[].Number<>"));
    }
    @Test
    public void getInferForm4Test() {
        assertEq("java.lang.$iterable",Templates.getInferForm("java.lang.$iterable<>"));
    }
    @Test
    public void getInferForm5Test() {
        assertEq("java.lang.$iterable",Templates.getInferForm("java.lang .$iterable<>"));
    }
    @Test
    public void getInferForm6Test() {
        assertEq("java.lang.ExClass..Inner",Templates.getInferForm("java.lang.ExClass..Inner<>"));
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
    public void isCorrect1Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.Object");
        assertTrue(Templates.isCorrect(m_, context_));
    }

    @Test
    public void isCorrect2Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.String");
        m_.setParam("java.lang.Object");
        assertTrue(Templates.isCorrect(m_, context_));
    }

    @Test
    public void isCorrect3Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.String");
        assertTrue(!Templates.isCorrect(m_, context_));
    }

    @Test
    public void isCorrect4Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.Object>");
        m_.setParam("java.util.List<java.lang.Object>");
        assertTrue(Templates.isCorrect(m_, context_));
    }

    @Test
    public void isCorrect5Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.String>");
        m_.setParam("java.util.List<java.lang.Object>");
        assertTrue(!Templates.isCorrect(m_, context_));
    }

    @Test
    public void isCorrect6Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.Object>");
        m_.setParam("java.util.List<java.lang.String>");
        assertTrue(!Templates.isCorrect(m_, context_));
    }

    @Test
    public void isCorrect7Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect8Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect9Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect10Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }











































































    @Test
    public void isCorrect11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T>:ExTwo<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.Ex<java.lang.Number>>");
        m_.setParam("java.lang.$Fct<pkg.ExTwo<java.lang.Number>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T>:ExTwo<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.ExTwo<java.lang.Number>,$int>");
        m_.setParam("java.lang.$Fct<pkg.Ex<java.lang.Number>,$int>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect13Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<$void>");
        m_.setParam("java.lang.$Fct<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect14Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<java.lang.Object>");
        m_.setParam("$Fct<$void>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect15Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<$void>");
        m_.setParam("$Fct<$void>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T>:ExTwo<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        m_.setParam("$Fct<pkg.ExTwo<java.lang.Number>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect17Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T>:ExTwo<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        m_.setParam("pkg.ExTwo<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T>:ExTwo<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect20Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam("#S");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect21Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#S");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T>:ExTwo<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect23Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_STRING);
        m_.setParam(ARR_OBJECT);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }


    @Test
    public void isCorrect26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect36Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_T);
        m_.setParam(ARR_VAR_S);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect37Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect38Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect39Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_T);
        m_.setParam(ARR_VAR_S);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    

    @Test
    public void isCorrect40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }


    @Test
    public void isCorrect41Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect42Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect48Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect49Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }



















































    @Test
    public void isCorrect50Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam(context_.getStandards().getAliasObject());
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("H", new StringList());
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect51Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect52Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect53Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect54Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Number");
        m_.setParam("pkg.Ex");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect55Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect56Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect57Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#T>");
        m_.setParam("pkg.Ex<#T>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect64Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<#T>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect65Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#T>");
        m_.setParam("pkg.Ex<#T>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect66Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<#T>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect67Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("S", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#S>");
        m_.setParam("pkg.Ex<#S>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect68Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect69Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.getMapping().put("U", new StringList("#T"));
        m_.getMapping().put("V", new StringList("#U"));
        m_.setArg("#V");
        m_.setParam("#T");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.getMapping().put("U", new StringList("#T"));
        m_.getMapping().put("V", new StringList("#U"));
        m_.getMapping().put("W", new StringList("#U"));
        m_.getMapping().put("X", new StringList("#V","#W"));
        m_.setArg("#X");
        m_.setParam("#T");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<#S>:Param<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.getMapping().put("U", new StringList("#T"));
        m_.getMapping().put("X", new StringList("#V","#W"));
        m_.setArg("#X");
        m_.setParam("#T");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect73Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("[[pkg.ExTwo<#T>");
        m_.setParam("[pkg.Ex<#T>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect74Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#E> :pkg.Ex<#E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("[pkg.Ex<#T>");
        m_.setParam("[[pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect75Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.Object>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex<java.lang.Object>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect76Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.Object>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<java.lang.Object>");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect77Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("#U");
        m_.setParam("pkg.ExTwo<java.lang.Number>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect78Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("#U");
        m_.setParam("pkg.Ex<java.lang.Number>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("[#U");
        m_.setParam("[pkg.ExTwo<java.lang.Number>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("[#U");
        m_.setParam("[pkg.Ex<java.lang.Number>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U,#V:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("java.lang.Object"));
        m_.getMapping().put("V", new StringList("pkg.ExTwo<#U>"));
        m_.setArg("[#V");
        m_.setParam("[pkg.Ex<#U>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U,#V:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("java.lang.Object"));
        m_.getMapping().put("V", new StringList("pkg.ExTwo<#U>"));
        m_.setArg("[#V");
        m_.setParam("[pkg.ExThree<#U>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect83Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<?,?>");
        m_.setParam("$Fct<?,?>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect84Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<?,java.lang.Number>");
        m_.setParam("$Fct<?,?>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect85Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<java.lang.Number,?>");
        m_.setParam("$Fct<?,?>");
        assertTrue(Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect86Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<?,?>");
        m_.setParam("$Fct<?,java.lang.Number>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect87Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<?,?>");
        m_.setParam("$Fct<java.lang.Number,?>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect88Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E,#F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex<?java.lang.Integer,java.lang.Integer>");
        m_.setParam("pkg.Ex<?java.lang.Number,java.lang.Number>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect89Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U,#V:pkg.ExTwo<#U>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("java.lang.Object"));
        m_.getMapping().put("V", new StringList("pkg.ExTwo<#U>"));
        m_.setArg("[#V");
        m_.setParam("[pkg.ExThree<#U>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }

    @Test
    public void isCorrect90Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct<$void>");
        m_.setParam("$Fct");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect91Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct");
        m_.setParam("$Fct<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect92Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$Fct");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect93Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam("[#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect94Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("[#T");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect95Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Number");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect96Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$int");
        m_.setParam("pkg.Ex<java.lang.Number>");
        assertTrue(!Templates.isCorrect(m_, cont_));
    }
    @Test
    public void isCorrect97Test() {
        ContextEl context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }

    @Test
    public void isCorrect98Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?$int>");
        m_.setParam("pkg.Param<?java.lang.Integer>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect99Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?$int>");
        m_.setParam("pkg.Param<?$long>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect100Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!java.lang.Integer>");
        m_.setParam("pkg.Param<!$int>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrect101Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<#T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!$long>");
        m_.setParam("pkg.Param<!$int>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,context_));
    }
    @Test
    public void isCorrectTemplate48Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("java.lang.$Fct", t_,cont_));
    }
    @Test
    public void isCorrectTemplate49Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("java.lang.$Fct<?>", t_,cont_));
    }
    @Test
    public void isCorrectTemplate50Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("java.lang.$Fct<?,?>", t_,cont_));
    }
    @Test
    public void isCorrectTemplate51Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex", t_,cont_));
    }

    @Test
    public void isCorrectTemplate52Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex", t_,cont_));
    }

    @Test
    public void isCorrectTemplate53Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex", t_,cont_));
    }

    @Test
    public void isCorrectTemplate54Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex", t_,cont_));
    }

    @Test
    public void isCorrectTemplate55Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex", t_,cont_));
    }

    @Test
    public void isCorrectTemplate56Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex", t_,cont_));
    }

    @Test
    public void isCorrectTemplate57Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex<java.lang.Object>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex<java.lang.Object>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<java.lang.Object>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<java.lang.Object>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate64Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate65Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate66Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate67Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<java.lang.Object>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate68Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<java.lang.Object>", t_,cont_));
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
    public void isCorrectTemplate70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<java.lang.Integer>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<java.lang.Object>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<java.lang.Object>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate73Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate74Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate75Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate76Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<#E>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate77Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<pkg.ExTwo>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate78Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("[pkg.Ex<pkg.ExTwo>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#U> {}\n");
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("pkg.ExTwo<pkg.Ex<java.lang.String>>", t_,cont_));
    }

    @Test
    public void isCorrectTemplate80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#U> {}\n");
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("pkg.ExTwo<java.lang.Number,java.lang.String>", t_,cont_,false));
    }

    @Test
    public void isCorrectTemplate81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex<java.lang.Number>", t_,cont_,false));
    }

    @Test
    public void isCorrectTemplate82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("pkg.Ex<java.lang.String>", t_,cont_,false));
    }

    @Test
    public void isCorrectTemplate83Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("pkg.Ex<pkg.Ex>", t_,cont_,false));
    }
    @Test
    public void isCorrectTemplate84Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        xml_.append("$public $class pkg.ExTwo<#U,#S> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("pkg.ExTwo<pkg.Ex,pkg.Ex<java.lang.Object>>", t_,cont_,false));
    }
    @Test
    public void isCorrectTemplate85Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        xml_.append("$public $class pkg.ExTwo<#U> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("pkg.ExTwo<pkg.Ex<java.lang.Object>>", t_,cont_,true));
    }
    @Test
    public void isCorrectTemplateAll1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("java.lang.Object", t_,cont_));
    }

    @Test
    public void isCorrectTemplateAll2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll(cont_.getStandards().getAliasPrimInteger(), t_,cont_));
    }

    @Test
    public void isCorrectTemplateAll3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[java.lang.Object", t_,cont_));
    }

    @Test
    public void isCorrectTemplateAll4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[$int", t_,cont_));
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
        assertNull(res_);
    }















































    @Test
    public void isCorrectTemplateAll11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex<#E>", t_,cont_));
    }

















    @Test
    public void isCorrectTemplateAll13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(Templates.isCorrectTemplateAll("pkg.Ex<#E>", t_,cont_));
    }


    @Test
    public void isCorrectTemplateAll14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#T:pkg.ExTwo<?#T>> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll("pkg.Ex<pkg.ExTwo<?pkg.ExTwo<?java.lang.Number>>>", t_,cont_));
    }
















    @Test
    public void getAllGenericSuperTypes6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        String className_ = Templates.getIdFromAllTypes("pkg.Ex<#E>");
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
        String className_ = Templates.getIdFromAllTypes("pkg.Ex");
        GeneType root_ = cont_.getClassBody(className_);
        StringList superTypes_ = root_.getAllGenericSuperTypes();
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.get(0));
    }
    @Test
    public void setCheckedElements1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Struct[] instance_ = new Struct[1];
        instance_[0] = new IntStruct(0);
        ArrayStruct arr_ = new ArrayStruct(instance_,"[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(Argument.createVoid());
        Templates.setCheckedElements(args_,arr_,cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void okArgs1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(true,"method", new StringList("$int"),true);
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!Templates.okArgs(id_,"",args_, cont_,null));
        assertNotNull(cont_.getException());
    }
    @Test
    public void okArgs2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(true,"method", new StringList("java.lang.Number"),true);
        Struct[] instance_ = new Struct[1];
        instance_[0] = new StringStruct("");
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!Templates.okArgs(id_,"",args_, cont_,null));
        assertNotNull(cont_.getException());
    }
    @Test
    public void getErrorWhenContain1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        assertSame(ErrorType.NPE, Templates.getErrorWhenContain(arr_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, cont_));
    }
    @Test
    public void getErrorWhenContain2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        assertSame(ErrorType.CAST, Templates.getErrorWhenContain(arr_, new StringStruct(""), NullStruct.NULL_VALUE, cont_));
    }
    @Test
    public void getErrorWhenIndex1Test() {
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        assertSame(ErrorType.NPE, Templates.getErrorWhenIndex(arr_, NullStruct.NULL_VALUE));
    }
    @Test
    public void getErrorWhenIndex2Test() {
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        assertSame(ErrorType.CAST, Templates.getErrorWhenIndex(arr_, new StringStruct("")));
    }
    private static ContextEl unfullValidateOverridingMethodsStd(StringMap<String> _files) {
        ContextEl cont_ = contextEnElDefault();
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private static ContextEl contextEnElDefault() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne("en", opt_);
    }
    private static ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
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
        return cont_;
    }
    
    private static ContextEl simpleContextEl() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        return InitializationLgNames.buildStdOne(opt_);
    }
}
