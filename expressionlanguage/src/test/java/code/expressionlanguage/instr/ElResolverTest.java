package code.expressionlanguage.instr;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.*;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


public final class ElResolverTest extends ProcessMethodCommon{

    @Test
    public void getOperationsSequence1Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(3, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(5));
        assertEq(")", opers_.getVal(7));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("4", values_.getVal(4));
        assertEq("3", values_.getVal(6));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence2Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3).abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(8));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(9));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence3Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4+3).abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(8));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4+3)", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(9));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence4Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg([$int),4+3).abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(23));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),4+3)", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(24));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence5Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg([$int),'[').abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(23));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[')", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(24));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence6Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg([$int),'[').abs(4,3)+8";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(32));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[').abs(4,3)", values_.getVal(0));
        assertEq("8", values_.getVal(33));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence7Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(1+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(1+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence8Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\u9fcb'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\u9fcb'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence9Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\''+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\''+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence10Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"ab\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"ab\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence11Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-6*8";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-6", values_.getVal(0));
        assertEq("8", values_.getVal(3));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence12Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-abs(8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("abs(8)", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence13Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg([$int),'[').abs(4,3)+8-9";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(34));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[').abs(4,3)+8", values_.getVal(0));
        assertEq("9", values_.getVal(35));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence14Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.8-abs(9)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(3));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1.8", values_.getVal(0));
        assertEq("abs(9)", values_.getVal(4));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence15Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.8";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.8", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence16Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"18\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18", values_.getVal(0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence17Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "18";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence18Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(4+3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(0));
        assertEq(")", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("4+3", values_.getVal(1));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence19Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(4+3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("4+3", values_.getVal(10));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence20Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg([$int),4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(18));
        assertEq(",", opers_.getVal(20));
        assertEq(")", opers_.getVal(22));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg([$int)", values_.getVal(4));
        assertEq("4", values_.getVal(19));
        assertEq("3", values_.getVal(21));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence21Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v;";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        assertSame(ConstType.LOOP_VAR, seq_.getConstType());
    }

    @Test
    public void getOperationsSequence22Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v;.";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        assertSame(ConstType.LOC_VAR, seq_.getConstType());
    }

    @Test
    public void getOperationsSequence23Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence24Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", values_.getVal(0));
        assertEq("[0]", values_.getVal(8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence25Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[14][5]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14]", values_.getVal(0));
        assertEq("[5]", values_.getVal(12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2411Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[0,1]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", values_.getVal(0));
        assertEq("[0,1]", values_.getVal(8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2511Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[14][5,0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14]", values_.getVal(0));
        assertEq("[5,0]", values_.getVal(12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2512Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[14,0][5]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14,0]", values_.getVal(0));
        assertEq("[5]", values_.getVal(14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2513Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[14,0][5,1]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14,0]", values_.getVal(0));
        assertEq("[5,1]", values_.getVal(14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence26Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"a b\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"a b\"+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.BeanOne {\n");
        xml_.append(" $public pkg.Composite composite:\n");
        xml_.append(" {\n");
        xml_.append("  composite.integer:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.BeanOne");
        RootBlock r_ = classes_.getClassBody("pkg.BeanOne");
        Block field_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(field_);
        ((FieldBlock)field_).retrieveNames(conf_, new StringList());
        RootBlock rTwo_ = classes_.getClassBody("pkg.Composite");
        conf_.getAnalyzing().setCurrentBlock(rTwo_.getFirstChild());
        ((FieldBlock)rTwo_.getFirstChild()).retrieveNames(conf_, new StringList());
        Block b_ = field_.getNextSibling().getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        Line l_ = (Line) b_;
        String el_ = l_.getExpression();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(9));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("composite", values_.getVal(0));
        assertEq("integer", values_.getVal(10));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence28Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("call()", values_.getVal(5));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence29Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.;call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("call()", values_.getVal(6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence30Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;;call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("call()", values_.getVal(5));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence31Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("call()", values_.getVal(4));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence32Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(11));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.call()", values_.getVal(0));
        assertEq("call()", values_.getVal(12));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence33Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.;call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(12));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;call()", values_.getVal(0));
        assertEq("call()", values_.getVal(13));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence34Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;;call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(11));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;call()", values_.getVal(0));
        assertEq("call()", values_.getVal(12));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence35Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(10));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;call()", values_.getVal(0));
        assertEq("call()", values_.getVal(11));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence36Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(22));
        assertEq(")", opers_.getVal(26));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", values_.getVal(0));
        assertEq("\"8\"", values_.getVal(23));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence37Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "call().$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("call()", values_.getVal(0));
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(7));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence38Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer(\"8\").intValue()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(27));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(0));
        assertEq("intValue()", values_.getVal(28));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence39Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer[]{8i}";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("{", opers_.getVal(24));
        assertEq("}", opers_.getVal(27));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer[]", values_.getVal(0));
        assertEq("8i", values_.getVal(25));
        assertTrue(seq_.isInstance());
    }

    @Test
    public void getOperationsSequence40Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer(\"8\").intValue()+5";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(38));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer(\"8\").intValue()", values_.getVal(0));
        assertEq("5", values_.getVal(39));
    
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence41Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("[0]", values_.getVal(5));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence42Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("[0]", values_.getVal(6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence43Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("[0]", values_.getVal(5));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence44Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("[0]", values_.getVal(4));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence45Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.f", values_.getVal(0));
        assertEq("[0]", values_.getVal(6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence46Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(7));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;f", values_.getVal(0));
        assertEq("[0]", values_.getVal(7));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence47Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;f", values_.getVal(0));
        assertEq("[0]", values_.getVal(6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence48Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;f", values_.getVal(0));
        assertEq("[0]", values_.getVal(5));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence49Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(8));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(8));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence50Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(9));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(9));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence51Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(8));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(8));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence52Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(7));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(7));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence53Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static(pkg.classname).field";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(22));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$static(pkg.classname)", values_.getVal(0));
        assertEq("field", values_.getVal(23));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence54Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "- -1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" -1", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence55Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence56Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-1.0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence57Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1- -1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1", values_.getVal(0));
        assertEq(" -1", values_.getVal(2));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence58Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence59Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!!a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("!a", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence60Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "b!=a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(3));
    
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence61Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "b<=a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("<=", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(3));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence62Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "b>=a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(">=", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(3));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence63Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "b==a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(3));
    
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence64Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\\\"string\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("\"string", values_.getVal(0));
        assertSame(ConstType.STRING, seq_.getConstType());
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence65Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\''";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertSame(ConstType.CHARACTER, seq_.getConstType());
        assertEq("'", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence66Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\\\'";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertSame(ConstType.CHARACTER, seq_.getConstType());
        assertEq("\\", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence67Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(\"\\\"string\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(20));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("\"\\\"string\"", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence68Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt('\\'')";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("'\\''", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence69Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt('\\\\')";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("'\\\\'", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence70Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(1.0)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("1.0", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence71Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg(java.lang.Object),$firstopt(4),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(29));
        assertEq(",", opers_.getVal(42));
        assertEq(")", opers_.getVal(44));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg(java.lang.Object)", values_.getVal(4));
        assertEq("$firstopt(4)", values_.getVal(30));
        assertEq("3", values_.getVal(43));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence72Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg(java.lang.Object),$firstopt(4;.;),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(29));
        assertEq(",", opers_.getVal(45));
        assertEq(")", opers_.getVal(47));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg(java.lang.Object)", values_.getVal(4));
        assertEq("$firstopt(4;.;)", values_.getVal(30));
        assertEq("3", values_.getVal(46));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence73Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs($vararg(java.lang.Object),$firstopt(4;.),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(29));
        assertEq(",", opers_.getVal(44));
        assertEq(")", opers_.getVal(46));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg(java.lang.Object)", values_.getVal(4));
        assertEq("$firstopt(4;.)", values_.getVal(30));
        assertEq("3", values_.getVal(45));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence74Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;.)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence75Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;.;)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.;", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence76Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(12));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence77Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;;)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;;", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    //optional parameter with qualified access
    @Test
    public void getOperationsSequence78Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;.t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.t", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence79Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;.;t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(15));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.;t", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence80Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;t", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }



    @Test
    public void getOperationsSequence81Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$firstopt(v;;t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;;t", values_.getVal(10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence82Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-10";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("10", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence83Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence84Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-1d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1d", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence85Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-1.0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0d", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence86Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a&&b!=c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b!=c", values_.getVal(3));
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence87Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v; a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;", values_.getVal(0));
        assertEq(" a", values_.getVal(2));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence88Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v; ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertSame(ConstType.LOOP_VAR, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence89Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a .b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a ", values_.getVal(0));
        assertEq("b", values_.getVal(3));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence90Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\u9Fcb'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\u9Fcb'+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence91Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"\\u9Fcb\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\u9Fcb\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence92Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\n'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\n'+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence93Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"\\n\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\n\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence94Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\r'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\r'+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence95Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"\\r\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\r\"+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence96Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\b'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\b'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence97Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"\\b\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\b\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence98Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\t'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\t'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence99Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"\\t\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\t\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence100Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\\\"string\"+\"\\\"string\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(10));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("\"\\\"string\"", values_.getVal(0));
        assertEq("\"\\\"string\"", values_.getVal(11));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence101Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\\\\\\\"string\"+\"\\\"string\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(12));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("\"\\\\\\\"string\"", values_.getVal(0));
        assertEq("\"\\\"string\"", values_.getVal(13));
    
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence102Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\f'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\f'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence103Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"\\f\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\f\"+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence104Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!!field";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("!field", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence105Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!field!=anotherfield";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!field", values_.getVal(0));
        assertEq("anotherfield", values_.getVal(8));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence106Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "field!=!anotherfield";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("field", values_.getVal(0));
        assertEq("!anotherfield", values_.getVal(7));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence107Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!field!=!anotherfield";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!field", values_.getVal(0));
        assertEq("!anotherfield", values_.getVal(8));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence108Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v;.news.a()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(7));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;.news", values_.getVal(0));
        assertEq("a()", values_.getVal(8));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.BeanOne {\n");
        xml_.append(" $public pkg.Composite composite:\n");
        xml_.append(" {\n");
        xml_.append("  composite.getOverridenFour(0):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $void getOverridenFour($int p){}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.BeanOne");
        RootBlock r_ = classes_.getClassBody("pkg.BeanOne");
        Block field_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(field_);
        ((FieldBlock)field_).retrieveNames(conf_, new StringList());
        Block b_ = field_.getNextSibling().getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        Line l_ = (Line) b_;
        String el_ = l_.getExpression();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(9));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("composite", values_.getVal(0));
        assertEq("getOverridenFour(0)", values_.getVal(10));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence110Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("f", values_.getVal(5));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence111Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.;f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("f", values_.getVal(6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence112Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;;f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("f", values_.getVal(5));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence113Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("f", values_.getVal(4));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence114Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "+a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence115Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a||b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b", values_.getVal(3));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence116Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a&&b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b", values_.getVal(3));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence117Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a||b&&c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b&&c", values_.getVal(3));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence118Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a&&b||c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a&&b", values_.getVal(0));
        assertEq("c", values_.getVal(6));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence119Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!a||b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", values_.getVal(0));
        assertEq("b", values_.getVal(4));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence120Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!a&&b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", values_.getVal(0));
        assertEq("b", values_.getVal(4));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence121Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!a||b&&c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", values_.getVal(0));
        assertEq("b&&c", values_.getVal(4));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence122Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!a&&b||c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a&&b", values_.getVal(0));
        assertEq("c", values_.getVal(7));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence123Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(a||b)&&c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("(a||b)", values_.getVal(0));
        assertEq("c", values_.getVal(8));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence124Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(a|b)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(0));
        assertEq(")", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a|b", values_.getVal(1));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence125Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v;.[0i].array[0i]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(13));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;.[0i].array", values_.getVal(0));
        assertEq("[0i]", values_.getVal(13));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence126Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(5));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence127Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(4));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence128Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence129Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence130Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence131Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence132Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence133Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence134Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".1e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence135Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".1e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence136Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence137Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence138Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e2", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence139Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence140Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence141Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1e-2+.5";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-.1e-2", values_.getVal(0));
        assertEq(".5", values_.getVal(7));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence142Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1e-2d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence143Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1bs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(3, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(5));
        assertEq(")", opers_.getVal(7));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("1bs", values_.getVal(0));
        assertEq("4", values_.getVal(4));
        assertEq("3", values_.getVal(6));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence144Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " !a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", values_.getVal(2));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence145Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "! a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" a", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence146Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "- - a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" - a", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence147Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "- -a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" -a", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence148Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " - -a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" -a", values_.getVal(2));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence149Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " v;";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertSame(ConstType.LOOP_VAR, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        assertEq(1, seq_.getOffset());
    }

    @Test
    public void getOperationsSequence150Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1_0e-2d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0e-2d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence151Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1e-2_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2_0d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence152Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1_0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence153Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence154Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1e1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e1_0d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence155Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence156Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".1_0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence157Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence158Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence159Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence160Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
 
    @Test
    public void getOperationsSequence161Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.2_0e1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".2_0e1_0d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence162Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-1.2_0e1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.2_0e1_0d", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence163Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.1_0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.1_0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    
    @Test
    public void getOperationsSequence164Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence165Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math)abs()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(22));
        assertEq(")", opers_.getVal(23));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$classchoice($math)abs", values_.getVal(0));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence166Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v+=b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+=", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq("b", values_.getVal(3));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence167Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v++";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("++", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq(ElResolver.POST_INCR_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence168Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math)abs";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$classchoice($math)abs", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence169Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math)abs$.field";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(23));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$classchoice($math)abs$", values_.getVal(0));
        assertEq("field", values_.getVal(24));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence170Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$this()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(5));
        assertEq(")", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this", values_.getVal(0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence171Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$this ()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(6));
        assertEq(")", opers_.getVal(7));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this ", values_.getVal(0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence172Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$this";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence173Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$($int)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", values_.getVal(7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence174Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$($int) 1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" 1", values_.getVal(7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence175Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " $($int)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", values_.getVal(8));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence176Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-$($int)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$($int)1", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence177Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$($int)$($byte)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(0));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$($byte)1", values_.getVal(7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence178Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence179Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp>", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence180Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp,three.Sec>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp,three.Sec>", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence181Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence182Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>==$true";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", opers_.getVal(42));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", values_.getVal(0));
        assertEq("$true", values_.getVal(44));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence183Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v; $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(3));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v; ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence184Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence185Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v() $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(4));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v() ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence186Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$true==1 $instanceof pkg.List<two.Tmp<three.Sec>>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", values_.getVal(7));
        assertEq("$true", values_.getVal(0));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence187Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof #T";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof #T", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence188Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg . One";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg . One", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence189Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces(pkg.MyClass)(arg;.)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(24));
        assertEq(")", opers_.getVal(30));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$interfaces(pkg.MyClass)", values_.getVal(0));
        assertEq("arg;.", values_.getVal(25));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence190Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(0));
        assertEq("]", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("", values_.getVal(0));
        assertEq("0", values_.getVal(1));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(seq_.isArray());
    }

    @Test
    public void getOperationsSequence191Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-.1e-2-.5";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-.1e-2", values_.getVal(0));
        assertEq(".5", values_.getVal(7));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence192Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence193Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>==$true";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", opers_.getVal(56));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", values_.getVal(0));
        assertEq("$true", values_.getVal(58));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence194Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v; $instanceof $byte[]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte[]", opers_.getVal(3));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v; ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence195Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = pkg.Ex.exmeth(0i):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImporting(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", values_.getVal(0));
        assertEq(" pkg.Ex.exmeth(0i)", values_.getVal(6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.Ex", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence196Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = Ex.exmeth(0i):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImporting(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", values_.getVal(0));
        assertEq(" Ex.exmeth(0i)", values_.getVal(6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.Ex", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence197Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $String inst = $Class.forName(\"\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImporting(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", values_.getVal(0));
        assertEq(" $Class.forName(\"\")", values_.getVal(6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("java.lang.$Class", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence198Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = ..Ex.exmeth(0i):\n");
        xml_.append(" $public $static $class Ex{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImporting(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", values_.getVal(0));
        assertEq(" ..Ex.exmeth(0i)", values_.getVal(6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExTwo..Ex", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence199Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int Ex = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int Ex = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = pkg.ExThree.Ex.inst:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImporting(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", values_.getVal(0));
        assertEq(" pkg.ExThree.Ex.inst", values_.getVal(6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExThree.Ex", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence200Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = ExTwo..Ex.exmeth(0i):\n");
        xml_.append(" $public $static $class Ex{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_, false);
        classes_.validateInheritingClasses(conf_, false);
        classes_.validateIds(conf_,false);
        classes_.validateOverridingInherit(conf_, false);
        addImportingPage(conf_);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImporting(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(5));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", values_.getVal(0));
        assertEq(" ExTwo..Ex.exmeth(0i)", values_.getVal(6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExTwo..Ex", d_.getDelKeyWordStaticExtract().first());
    }

    @Test
    public void getOperationsSequence201Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a<b>c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("<", opers_.getVal(1));
        assertEq(">", opers_.getVal(3));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b", values_.getVal(2));
        assertEq("c", values_.getVal(4));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence202Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer[8i]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(22));
        assertEq("]", opers_.getVal(25));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", values_.getVal(0));
        assertEq("8i", values_.getVal(23));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence203Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer[8i][]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(22));
        assertEq("]", opers_.getVal(25));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", values_.getVal(0));
        assertEq("8i", values_.getVal(23));
        assertTrue(seq_.isInstance());
        assertEq(1, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence204Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer[8i][5i]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("[", opers_.getVal(22));
        assertEq("]", opers_.getVal(25));
        assertEq("[", opers_.getVal(26));
        assertEq("]", opers_.getVal(29));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("$new java.lang.Integer", values_.getVal(0));
        assertEq("8i", values_.getVal(23));
        assertEq("5i", values_.getVal(27));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence205Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer[8i][][]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(22));
        assertEq("]", opers_.getVal(25));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", values_.getVal(0));
        assertEq("8i", values_.getVal(23));
        assertTrue(seq_.isInstance());
        assertEq(2, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence206Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new List<java.lang.Integer>[8i]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(28));
        assertEq("]", opers_.getVal(31));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new List<java.lang.Integer>", values_.getVal(0));
        assertEq("8i", values_.getVal(29));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence207Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new List<java.lang.Integer[]>[8i]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(30));
        assertEq("]", opers_.getVal(33));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new List<java.lang.Integer[]>", values_.getVal(0));
        assertEq("8i", values_.getVal(31));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence208Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new List<java.lang.Integer[]>[8i][]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(30));
        assertEq("]", opers_.getVal(33));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new List<java.lang.Integer[]>", values_.getVal(0));
        assertEq("8i", values_.getVal(31));
        assertTrue(seq_.isInstance());
        assertEq(1, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence209Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer(8i)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(22));
        assertEq(")", opers_.getVal(25));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", values_.getVal(0));
        assertEq("8i", values_.getVal(23));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence210Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        addBeanClassName(conf_, "code.expressionlanguage.classes.BeanOne");
        String el_ = "composite.integer.int";
        Delimiters d_ = new Delimiters();
        d_.getAllowedOperatorsIndexes().add(9);
        d_.getAllowedOperatorsIndexes().add(17);
        VariableInfo vi_ = new VariableInfo();
        vi_.setFirstChar(0);
        vi_.setLastChar(9);
        d_.getVariables().add(vi_);
        vi_ = new VariableInfo();
        vi_.setFirstChar(10);
        vi_.setLastChar(17);
        d_.getVariables().add(vi_);
        vi_ = new VariableInfo();
        vi_.setFirstChar(18);
        vi_.setLastChar(21);
        d_.getVariables().add(vi_);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(17));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("composite.integer", values_.getVal(0));
        assertEq("int", values_.getVal(18));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence211Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        addBeanClassName(conf_, "code.expressionlanguage.classes.BeanOne");
        String el_ = "integer=1=0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(7));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("integer", values_.getVal(0));
        assertEq("1=0", values_.getVal(8));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertTrue(!seq_.isCallDbArray());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence212Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp>[]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp>[]", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence213Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp>[ ]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp>[ ]", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence214Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof $byte[ ]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte[ ]", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence215Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 $instanceof pkg.List<two.Tmp> []";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp> []", opers_.getVal(2));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence216Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[0](1)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[0]", values_.getVal(0));
        assertEq("(1)", values_.getVal(11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence217Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "abs(4,3)[0]{1}";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[0]", values_.getVal(0));
        assertEq("{1}", values_.getVal(11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence218Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "0x1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('i', ni_.get(0).getSuffix());
        assertEq("1", ni_.get(0).getIntPart().toString());
        assertEq("", ni_.get(0).getDecimalPart().toString());
        assertEq("", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence219Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "0x1f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1f", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('i', ni_.get(0).getSuffix());
        assertEq("1f", ni_.get(0).getIntPart().toString());
        assertEq("", ni_.get(0).getDecimalPart().toString());
        assertEq("", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence220Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "0x1p0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1p0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('d', ni_.get(0).getSuffix());
        assertEq("1", ni_.get(0).getIntPart().toString());
        assertEq("", ni_.get(0).getDecimalPart().toString());
        assertEq("0", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence221Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "0x1fp0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1fp0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('d', ni_.get(0).getSuffix());
        assertEq("1f", ni_.get(0).getIntPart().toString());
        assertEq("", ni_.get(0).getDecimalPart().toString());
        assertEq("0", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence222Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "0x1.2p0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1.2p0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('d', ni_.get(0).getSuffix());
        assertEq("1", ni_.get(0).getIntPart().toString());
        assertEq("2", ni_.get(0).getDecimalPart().toString());
        assertEq("0", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence223Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "0x1f.2p0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1f.2p0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('d', ni_.get(0).getSuffix());
        assertEq("1f", ni_.get(0).getIntPart().toString());
        assertEq("2", ni_.get(0).getDecimalPart().toString());
        assertEq("0", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence224Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "`18`";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18", values_.getVal(0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence225Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "`18``36`";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18`36", values_.getVal(0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence226Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "tab[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(3));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("tab", values_.getVal(0));
        assertEq("[0]", values_.getVal(3));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(!seq_.isArray());
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence227Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "tab[0][1]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("tab[0]", values_.getVal(0));
        assertEq("[1]", values_.getVal(6));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(!seq_.isArray());
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence228Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "3*";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("3", values_.getVal(0));
        assertEq("", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence229Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "4. ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        IntTreeMap<String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        IntTreeMap<String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("4. ", values_.getVal(0));
    }

    @Test
    public void checkSyntax230FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "0x2. ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax231FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "[";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax232FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "++";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax233FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "`";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax234FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\n\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax235FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\t'";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax236FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\r\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(-1, d_.getBadOffset());
    }
    @Test
    public void checkSyntax237FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "--";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax238FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax239FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "+";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax240FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1+$)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax241FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1+$(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax242FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1+$()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax243FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(}";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax244FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "}";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(0, d_.getBadOffset());
    }

    @Test
    public void checkSyntax245FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-(java.lang.Object[)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(19, d_.getBadOffset());
    }
    @Test
    public void checkSyntax246FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(java.lang.Object[ )";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(19, d_.getBadOffset());
    }
    @Test
    public void checkSyntax247FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "([v )";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax248FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax249FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(2, d_.getBadOffset());
    }

    @Test
    public void checkSyntax250FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(2, d_.getBadOffset());
    }

    @Test
    public void checkSyntax251FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(3, d_.getBadOffset());
    }

    @Test
    public void checkSyntax252FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$vararg(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax253FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$class)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax254FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$class(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax255FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$instanceof(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax256FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$id)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax257FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$id(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax258FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$lambda)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax259FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$lambda(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax260FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax261FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static() ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax262FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$super";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(6, d_.getBadOffset());
    }

    @Test
    public void checkSyntax263FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$super,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(6, d_.getBadOffset());
    }

    @Test
    public void checkSyntax264FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess(MyClass)method,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(26, d_.getBadOffset());
    }

    @Test
    public void checkSyntax265FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess(MyClass)method";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(26, d_.getBadOffset());
    }

    @Test
    public void checkSyntax266FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess(MyClass)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax267FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess(MyClass";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax268FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax269FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax270FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(10, d_.getBadOffset());
    }

    @Test
    public void checkSyntax271FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax272FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax273FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that.method";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax274FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that.method,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax275FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$thisaccess(MyClass) ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(21, d_.getBadOffset());
    }

    @Test
    public void checkSyntax276FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$superaccess(MyClass)method,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(27, d_.getBadOffset());
    }

    @Test
    public void checkSyntax278FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$superaccess(MyClass)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax279FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$superaccess(MyClass";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax280FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$superaccess(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax281FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$superaccess,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax282FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$superaccess";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax283FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$superaccess(MyClass) ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(22, d_.getBadOffset());
    }

    @Test
    public void checkSyntax284FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces(MyClass),";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax285FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces(MyClass) ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(21, d_.getBadOffset());
    }

    @Test
    public void checkSyntax286FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces(MyClass)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax287FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces(MyClass";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax288FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces( ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(13, d_.getBadOffset());
    }

    @Test
    public void checkSyntax289FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax290FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(10, d_.getBadOffset());
    }

    @Test
    public void checkSyntax291FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$interfaces(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax292FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice,";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax293FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static()  ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax294FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$bool";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax295FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$bool ean()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax296FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$defaultValue)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(14, d_.getBadOffset());
    }

    @Test
    public void checkSyntax297FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$defaultValue(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(14, d_.getBadOffset());
    }

    @Test
    public void checkSyntax298FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1+explicit(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax299FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1+explicit()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax300FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1+explicit)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(11, d_.getBadOffset());
    }
    @Test
    public void checkSyntax301FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "explicit(";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax302FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "explicit()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(10, d_.getBadOffset());
    }

    @Test
    public void checkSyntax303FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "explicit)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(9, d_.getBadOffset());
    }
    @Test
    public void checkSyntaxDelimiters10Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " {$new $int[]\\{1i,3i\\}}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 2, '{', '}');
        Ints esc_ = d_.getEscapings();
        assertEq(2, esc_.size());
        assertEq(13, esc_.first());
        assertEq(20, esc_.last());
    }
    @Test
    public void checkSyntax1Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        addBeanClassName(conf_, "code.expressionlanguage.classes.BeanOne");
        String el_ = "1==0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(1, d_.getAllowedOperatorsIndexes().size());
        assertEq(1, d_.getAllowedOperatorsIndexes().first());
    }
    @Test
    public void checkSyntaxDelimiters1Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('\\u9fcb'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters2Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('\\u9fcb'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters3Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('`'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(6, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters4Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('}'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(6, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters5Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " {6*('\\u9fcb'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 2, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first());
        assertEq(12, d_.getDelStringsChars().last());
        assertEq(2, d_.getIndexBegin());
        assertEq(15, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters6Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " {6*(\"//\"+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 2, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first());
        assertEq(8, d_.getDelStringsChars().last());
        assertEq(2, d_.getIndexBegin());
        assertEq(11, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters7Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = " {$new $int[]\\{1i,3i\\}}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 2, '{', '}');
        assertEq(3, d_.getAllowedOperatorsIndexes().size());
        assertEq(14, d_.getAllowedOperatorsIndexes().first());
        assertEq(17, d_.getAllowedOperatorsIndexes().get(1));
        assertEq(21, d_.getAllowedOperatorsIndexes().last());
        assertEq(2, d_.getIndexBegin());
        assertEq(21, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters1FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('\\u9fcb'+8)";
        assertEq(15, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }
    @Test
    public void checkSyntaxDelimiters4FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('\\u9fcb'+8\\";
        assertEq(14, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }
    @Test
    public void checkSyntaxDelimiters5FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('\\u9fcb'+8\\ ";
        assertEq(14, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }
    @Test
    public void checkSyntaxDelimiters8Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*(`string`+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters9Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*(`string``after`+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(18, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(21, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters2FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('\\u9fcb'+8){";
        assertEq(15, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }

    @Test
    public void checkSyntaxDelimiters3FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "{6*('\\u9gcb'+8)}";
        assertEq(8, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }

    @Test
    public void checkSyntax1FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\u9gcb'+8)";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax2FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('\\g'+8)";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax3FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('ab'+8)";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax4FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*('a'+[8)]";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax5FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*['a'+(8])";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax7FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"t\\u98\"+[8])";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax8FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6*(\"t\\u98 \"+[8])";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax9FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "6 1*(\"te\"+[8])";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax10FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static.a";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax12FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1< =2";
        assertEq(-1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax13FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1> =2";
        assertEq(-1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax14FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1! =2";
        assertEq(-1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax15FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v '";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax16FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v; .";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax17FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v;. ;";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax18FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v; ;";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax19FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "v;  ;";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax20FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax21FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\\";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax22FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\\u9fc";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax23FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\u9fc";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax24FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\\g9fc";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax25FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\g9fc";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax26FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "\"\\u9fcb";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax27FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\u9fcb";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax28FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1)";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax29FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(1";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax30FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1]";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax31FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "[1";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax33FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer";
        assertEq(21, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax34FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$new java.lang.Integer(?java";
        assertEq(28, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax35FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "a,b";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax36FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "integer[?java";
        assertEq(13, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax37FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "integer\\n";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax38FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1 .0";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax39FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1. 0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax40FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static(pkg$classname";
        assertEq(22, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax41FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".1.0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax42FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1e.0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax43FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1e";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax44FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0e.2";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax45FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0e5.2";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax46FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0.2";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax47FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1  .0";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax48FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0e5df";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax49FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0df";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax50FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1df";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax51FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.df";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax52FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.d.f";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax53FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1d.";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax54FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1df.";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax55FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0df";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax56FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0dgf";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax57FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0g";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax58FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "4g";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax59FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1ee2";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax60FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0e1g";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax61FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1f2";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax62FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".0e1g";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax63FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".0e1ff";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax64FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "4.g";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax65FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".4g";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax66FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "4.ff";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax67FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".4ff";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax68FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "4f1";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax69FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ".4g";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax70FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1.0e4d.5";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax71FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1. .0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax72FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1..0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax73FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1e .0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax74FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "2e 0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax75FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "2e ";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax76FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = ". 1";
        assertEq(0, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax77FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$vararg";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax78FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$vararg+4";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax79FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\u9";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax80FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "'\\u9'";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax81FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math";
        assertEq(18, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax82FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math$$";
        assertEq(20, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax83FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice(";
        assertEq(12, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax84FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math$abs$";
        assertEq(23, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax85FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math$abs)";
        assertEq(22, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax86FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math$abs) ";
        assertEq(24, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax87FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice ";
        assertEq(12, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax88FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that.";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax89FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$this(";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax90FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that.call";
        assertEq(10, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax91FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that.call$";
        assertEq(11, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax92FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$that.call$$";
        assertEq(12, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax93FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$classchoice($math$abs$$abs;)";
        assertEq(28, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }
    @Test
    public void checkSyntax94FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "_1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        assertEq(0, d_.getDelNumbers().size());
    }

    private static void addImportingPage(ContextEl _conf) {
        _conf.setAnalyzing(new AnalyzedPageEl());
    }

    private static void addBeanClassName(ContextEl _conf, String _bean) {
        _conf.setGlobalClass(_bean);
    }
    private ContextEl contextEl() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        return InitializationLgNames.buildStdOne(opt_);
    }
}
