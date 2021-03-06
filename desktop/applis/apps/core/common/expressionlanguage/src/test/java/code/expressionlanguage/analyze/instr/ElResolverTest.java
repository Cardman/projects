package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.*;

import code.expressionlanguage.common.*;

import code.expressionlanguage.methods.ProcessMethodCommon;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;


public final class ElResolverTest extends ProcessMethodCommon {

    @Test
    public void getOperationsSequence1Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(3, opers_.size());
        assertEq("(", getVal(opers_, 3));
        assertEq(",", getVal(opers_, 5));
        assertEq(")", getVal(opers_, 7));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("abs", getVal(values_, 0));
        assertEq("4", getVal(values_, 4));
        assertEq("3", getVal(values_, 6));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence2Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3).abs(4,3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 8));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", getVal(values_, 0));
        assertEq("abs(4,3)", getVal(values_, 9));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence3Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4+3).abs(4,3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 8));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4+3)", getVal(values_, 0));
        assertEq("abs(4,3)", getVal(values_, 9));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence4Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg([$int),4+3).abs(4,3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 23));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),4+3)", getVal(values_, 0));
        assertEq("abs(4,3)", getVal(values_, 24));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence5Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg([$int),'[').abs(4,3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 23));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[')", getVal(values_, 0));
        assertEq("abs(4,3)", getVal(values_, 24));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence6Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg([$int),'[').abs(4,3)+8";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", getVal(opers_, 32));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[').abs(4,3)", getVal(values_, 0));
        assertEq("8", getVal(values_, 33));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence7Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(1+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(1+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence8Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\u9fcb'+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\u9fcb'+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence9Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\''+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\''+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence10Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"ab\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"ab\"+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence11Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-6*8";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-6", getVal(values_, 0));
        assertEq("8", getVal(values_, 3));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence12Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-abs(8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("abs(8)", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence13Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg([$int),'[').abs(4,3)+8-9";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 34));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[').abs(4,3)+8", getVal(values_, 0));
        assertEq("9", getVal(values_, 35));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence14Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.8-abs(9)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 3));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1.8", getVal(values_, 0));
        assertEq("abs(9)", getVal(values_, 4));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence15Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.8";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.8", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence16Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"18\"";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18", getVal(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence17Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "18";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence18Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "(4+3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 0));
        assertEq(")", getVal(opers_, 4));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("", getVal(values_, 0));
        assertEq("4+3", getVal(values_, 1));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence19Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(4+3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 13));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("4+3", getVal(values_, 10));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence20Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg([$int),4,3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("(", getVal(opers_, 3));
        assertEq(",", getVal(opers_, 18));
        assertEq(",", getVal(opers_, 20));
        assertEq(")", getVal(opers_, 22));
        StrTypes values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", getVal(values_, 0));
        assertEq("$vararg([$int)", getVal(values_, 4));
        assertEq("4", getVal(values_, 19));
        assertEq("3", getVal(values_, 21));
        assertTrue(seq_.isCall());
    }


    @Test
    public void getOperationsSequence22Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v;.";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence23Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", getVal(values_, 0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence24Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence25Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[14][5]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14]", getVal(values_, 0));
        assertEq("[5]", getVal(values_, 12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2411Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[0,1]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", getVal(values_, 0));
        assertEq("[0,1]", getVal(values_, 8));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2511Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[14][5,0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14]", getVal(values_, 0));
        assertEq("[5,0]", getVal(values_, 12));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2512Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[14,0][5]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14,0]", getVal(values_, 0));
        assertEq("[5]", getVal(values_, 14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2513Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[14,0][5,1]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14,0]", getVal(values_, 0));
        assertEq("[5,1]", getVal(values_, 14));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence26Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"a b\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"a b\"+8)", getVal(values_, 2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.BeanOne {\n");
        xml_.append(" $public pkg.Composite composite;\n");
        xml_.append(" {\n");
        xml_.append("  composite.integer;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.BeanOne");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.BeanOne");
        AbsBk field_ = r_.getFirstChild();
        AnalyzedPageEl page_ = conf_.getAnalyzing();
        page_.setCurrentBlock(field_);
        RootBlock rTwo_ = getAnaClassBody(conf_, "pkg.Composite");
        page_.setCurrentBlock(rTwo_.getFirstChild());
        AbsBk b_ = field_.getNextSibling().getFirstChild();
        page_.setCurrentBlock(b_);
        Line l_ = (Line) b_;
        String el_ = l_.getExpression();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 9));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("composite", getVal(values_, 0));
        assertEq("integer", getVal(values_, 10));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence28Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.call()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence29Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.;call()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence32Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.call().call()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 11));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.call()", getVal(values_, 0));
        assertEq("call()", getVal(values_, 12));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence33Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.;call().call()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 12));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;call()", getVal(values_, 0));
        assertEq("call()", getVal(values_, 13));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence34Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;;call().call()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 11));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;call()", getVal(values_, 0));
        assertEq("call()", getVal(values_, 12));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence35Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;call().call()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 10));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;call()", getVal(values_, 0));
        assertEq("call()", getVal(values_, 11));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence36Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer(\"8\")";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 22));
        assertEq(")", getVal(opers_, 26));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", getVal(values_, 0));
        assertEq("\"8\"", getVal(values_, 23));
    
        assertTrue(seq_.isCall());
    }


    @Test
    public void getOperationsSequence36_Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer(\"8\"){}";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 22));
        assertEq(")", getVal(opers_, 26));
    }

    @Test
    public void getOperationsSequence37Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "call().$new java.lang.Integer(\"8\")";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("call()", getVal(values_, 0));
        assertEq("$new java.lang.Integer(\"8\")", getVal(values_, 7));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence38Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer(\"8\").intValue()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 27));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer(\"8\")", getVal(values_, 0));
        assertEq("intValue()", getVal(values_, 28));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence39Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer[]{8i}";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("{", getVal(opers_, 24));
        assertEq("}", getVal(opers_, 27));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer[]", getVal(values_, 0));
        assertEq("8i", getVal(values_, 25));
        assertTrue(seq_.isInstance());
    }

    @Test
    public void getOperationsSequence40Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer(\"8\").intValue()+5";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", getVal(opers_, 38));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer(\"8\").intValue()", getVal(values_, 0));
        assertEq("5", getVal(values_, 39));
    
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence41Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 5));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence42Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.;[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence43Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;;[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 5));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence44Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 4));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 4));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence45Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.f[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.f", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence46Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.;f[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 7));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;f", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 7));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence47Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;;f[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;f", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 6));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence48Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;f[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;f", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 5));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence49Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.f()[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 8));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.f()", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 8));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence50Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.;f()[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 9));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;f()", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 9));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence51Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;;f()[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 8));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;f()", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 8));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence52Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;f()[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 7));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;f()", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 7));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence53Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$static(pkg.classname).field";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 22));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$static(pkg.classname)", getVal(values_, 0));
        assertEq("field", getVal(values_, 23));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence54Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "- -1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" -1", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence55Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence56Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-1.0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence57Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1- -1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1", getVal(values_, 0));
        assertEq(" -1", getVal(values_, 2));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence58Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence59Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!!a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("!a", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence60Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "b!=a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", getVal(values_, 0));
        assertEq("a", getVal(values_, 3));
    
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence61Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "b<=a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("<=", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", getVal(values_, 0));
        assertEq("a", getVal(values_, 3));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence62Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "b>=a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(">=", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", getVal(values_, 0));
        assertEq("a", getVal(values_, 3));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence63Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "b==a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", getVal(values_, 0));
        assertEq("a", getVal(values_, 3));
    
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence64Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\\\"string\"";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("\"string", getVal(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence65Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\''";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertSame(ConstType.CHARACTER, seq_.getConstType());
        assertEq("'", getVal(values_, 0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence66Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\\\'";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertSame(ConstType.CHARACTER, seq_.getConstType());
        assertEq("\\", getVal(values_, 0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence67Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(\"\\\"string\")";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 20));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("\"\\\"string\"", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence68Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt('\\'')";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 14));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("'\\''", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence69Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt('\\\\')";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 14));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("'\\\\'", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence70Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(1.0)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 13));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("1.0", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence71Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg(java.lang.Object),$firstopt(4),3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("(", getVal(opers_, 3));
        assertEq(",", getVal(opers_, 29));
        assertEq(",", getVal(opers_, 42));
        assertEq(")", getVal(opers_, 44));
        StrTypes values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", getVal(values_, 0));
        assertEq("$vararg(java.lang.Object)", getVal(values_, 4));
        assertEq("$firstopt(4)", getVal(values_, 30));
        assertEq("3", getVal(values_, 43));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence72Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg(java.lang.Object),$firstopt(4;.;),3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("(", getVal(opers_, 3));
        assertEq(",", getVal(opers_, 29));
        assertEq(",", getVal(opers_, 45));
        assertEq(")", getVal(opers_, 47));
        StrTypes values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", getVal(values_, 0));
        assertEq("$vararg(java.lang.Object)", getVal(values_, 4));
        assertEq("$firstopt(4;.;)", getVal(values_, 30));
        assertEq("3", getVal(values_, 46));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence73Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs($vararg(java.lang.Object),$firstopt(4;.),3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("(", getVal(opers_, 3));
        assertEq(",", getVal(opers_, 29));
        assertEq(",", getVal(opers_, 44));
        assertEq(")", getVal(opers_, 46));
        StrTypes values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", getVal(values_, 0));
        assertEq("$vararg(java.lang.Object)", getVal(values_, 4));
        assertEq("$firstopt(4;.)", getVal(values_, 30));
        assertEq("3", getVal(values_, 45));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence74Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;.)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 13));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;.", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence75Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;.;)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 14));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;.;", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence76Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 12));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence77Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;;)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 13));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;;", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence78Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;.t)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 14));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;.t", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence79Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;.;t)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 15));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;.;t", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence80Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;t)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 13));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;t", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }



    @Test
    public void getOperationsSequence81Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$firstopt(v;;t)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 9));
        assertEq(")", getVal(opers_, 14));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", getVal(values_, 0));
        assertEq("v;;t", getVal(values_, 10));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence82Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-10";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("10", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence83Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence84Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-1d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1d", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence85Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-1.0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0d", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence86Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "a&&b!=c";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", getVal(values_, 0));
        assertEq("b!=c", getVal(values_, 3));
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence90Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\u9Fcb'+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\u9Fcb'+8)", getVal(values_, 2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence91Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"\\u9Fcb\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"\\u9Fcb\"+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence92Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\n'+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\n'+8)", getVal(values_, 2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence93Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"\\n\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"\\n\"+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence94Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\r'+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\r'+8)", getVal(values_, 2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence95Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"\\r\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"\\r\"+8)", getVal(values_, 2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence96Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\b'+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\b'+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence97Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"\\b\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"\\b\"+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence98Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\t'+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\t'+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence99Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"\\t\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"\\t\"+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence100Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\\\"string\"+\"\\\"string\"";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", getVal(opers_, 10));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("\"\\\"string\"", getVal(values_, 0));
        assertEq("\"\\\"string\"", getVal(values_, 11));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence101Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\\\\\\\"string\"+\"\\\"string\"";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", getVal(opers_, 12));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("\"\\\\\\\"string\"", getVal(values_, 0));
        assertEq("\"\\\"string\"", getVal(values_, 13));
    
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence102Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\f'+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("('\\f'+8)", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence103Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"\\f\"+8)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", getVal(values_, 0));
        assertEq("(\"\\f\"+8)", getVal(values_, 2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence104Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!!field";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("!field", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence105Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!field!=anotherfield";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!field", getVal(values_, 0));
        assertEq("anotherfield", getVal(values_, 8));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence106Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "field!=!anotherfield";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("field", getVal(values_, 0));
        assertEq("!anotherfield", getVal(values_, 7));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence107Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!field!=!anotherfield";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!field", getVal(values_, 0));
        assertEq("!anotherfield", getVal(values_, 8));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence108Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v;.news.a()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 7));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;.news", getVal(values_, 0));
        assertEq("a()", getVal(values_, 8));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.BeanOne {\n");
        xml_.append(" $public pkg.Composite composite;\n");
        xml_.append(" {\n");
        xml_.append("  composite.getOverridenFour(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $void getOverridenFour($int p){}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.BeanOne");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.BeanOne");
        AbsBk field_ = r_.getFirstChild();
        AnalyzedPageEl page_ = conf_.getAnalyzing();
        page_.setCurrentBlock(field_);
        AbsBk b_ = field_.getNextSibling().getFirstChild();
        page_.setCurrentBlock(b_);
        Line l_ = (Line) b_;
        String el_ = l_.getExpression();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 9));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("composite", getVal(values_, 0));
        assertEq("getOverridenFour(0)", getVal(values_, 10));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence110Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.f";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence111Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.;f";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence114Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "+a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", getVal(values_, 1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence115Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "a||b";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", getVal(values_, 0));
        assertEq("b", getVal(values_, 3));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence116Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "a&&b";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", getVal(values_, 0));
        assertEq("b", getVal(values_, 3));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence117Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "a||b&&c";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", getVal(values_, 0));
        assertEq("b&&c", getVal(values_, 3));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence118Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "a&&b||c";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", getVal(opers_, 4));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a&&b", getVal(values_, 0));
        assertEq("c", getVal(values_, 6));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence119Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!a||b";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", getVal(values_, 0));
        assertEq("b", getVal(values_, 4));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence120Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!a&&b";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", getVal(values_, 0));
        assertEq("b", getVal(values_, 4));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence121Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!a||b&&c";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", getVal(values_, 0));
        assertEq("b&&c", getVal(values_, 4));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence122Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "!a&&b||c";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("||", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a&&b", getVal(values_, 0));
        assertEq("c", getVal(values_, 7));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence123Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "(a||b)&&c";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&&", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("(a||b)", getVal(values_, 0));
        assertEq("c", getVal(values_, 8));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence124Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "(a|b)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 0));
        assertEq(")", getVal(opers_, 4));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("", getVal(values_, 0));
        assertEq("a|b", getVal(values_, 1));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence125Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v;.[0i].array[0i]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 13));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;.[0i].array", getVal(values_, 0));
        assertEq("[0i]", getVal(values_, 13));
    
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence126Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "var;.$new java.lang.Integer(\"8\")";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence128Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1e2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1e2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence129Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1e-2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1e-2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence130Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.0e2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0e2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence131Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.0e-2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0e-2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence132Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.e2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.e2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence133Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.e-2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.e-2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence134Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = ".1e2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence135Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = ".1e-2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence136Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = ".1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence137Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence138Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1e2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e2", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence139Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1e-2";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence140Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence141Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1e-2+.5";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-.1e-2", getVal(values_, 0));
        assertEq(".5", getVal(values_, 7));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence142Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1e-2d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence143Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "lbs(4,3)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(3, opers_.size());
        assertEq("(", getVal(opers_, 3));
        assertEq(",", getVal(opers_, 5));
        assertEq(")", getVal(opers_, 7));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("lbs", getVal(values_, 0));
        assertEq("4", getVal(values_, 4));
        assertEq("3", getVal(values_, 6));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence144Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = " !a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", getVal(values_, 2));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence145Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "! a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" a", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence146Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "- - a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" - a", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence147Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "- -a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" -a", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence148Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = " - -a";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" -a", getVal(values_, 2));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence150Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1_0e-2d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0e-2d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence151Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1e-2_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2_0d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence152Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1_0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence153Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence154Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1e1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e1_0d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence155Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence156Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = ".1_0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence157Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = ".1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0d", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence158Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence159Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence160Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.d", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
 
    @Test
    public void getOperationsSequence161Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.2_0e1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".2_0e1_0d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence162Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-1.2_0e1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.2_0e1_0d", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence163Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.1_0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.1_0", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    
    @Test
    public void getOperationsSequence164Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1.1_0d";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.1_0d", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence165Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math)abs()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 22));
        assertEq(")", getVal(opers_, 23));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$classchoice($math)abs", getVal(values_, 0));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence166Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v+=b";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+=", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v", getVal(values_, 0));
        assertEq("b", getVal(values_, 3));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence167Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v++";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("++", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", getVal(values_, 0));
        assertEq(ElResolver.POST_INCR_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence168Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math)abs";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$classchoice($math)abs", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence169Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math)abs$.field";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 23));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$classchoice($math)abs$", getVal(values_, 0));
        assertEq("field", getVal(values_, 24));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence170Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$this()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 5));
        assertEq(")", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this", getVal(values_, 0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence171Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$this ()";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 6));
        assertEq(")", getVal(opers_, 7));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this ", getVal(values_, 0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence172Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$this";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this", getVal(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence173Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$($int)1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", getVal(values_, 7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence174Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$($int) 1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" 1", getVal(values_, 7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence175Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = " $($int)1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", getVal(values_, 8));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence176Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-$($int)1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$($int)1", getVal(values_, 1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence177Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$($int)$($byte)1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", getVal(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$($byte)1", getVal(values_, 7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence178Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof $byte";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence179Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp>";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp>", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence180Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp,three.Sec>";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp,three.Sec>", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence181Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence182Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>==$true";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", getVal(opers_, 42));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", getVal(values_, 0));
        assertEq("$true", getVal(values_, 44));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence183Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v; $instanceof $byte";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", getVal(opers_, 3));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v; ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence184Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v $instanceof $byte";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence185Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v() $instanceof $byte";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", getVal(opers_, 4));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v() ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence186Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$true==1 $instanceof pkg.List<two.Tmp<three.Sec>>";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", getVal(values_, 7));
        assertEq("$true", getVal(values_, 0));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence187Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof #T";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof #T", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence188Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg . One";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg . One", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence189Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces(pkg.MyClass)(arg;.)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 24));
        assertEq(")", getVal(opers_, 30));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$interfaces(pkg.MyClass)", getVal(values_, 0));
        assertEq("arg;.", getVal(values_, 25));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence190Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", getVal(opers_, 0));
        assertEq("]", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("", getVal(values_, 0));
        assertEq("0", getVal(values_, 1));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(seq_.isArray());
    }

    @Test
    public void getOperationsSequence191Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-.1e-2-.5";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-.1e-2", getVal(values_, 0));
        assertEq(".5", getVal(values_, 7));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence192Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence193Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>==$true";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("==", getVal(opers_, 56));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", getVal(values_, 0));
        assertEq("$true", getVal(values_, 58));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence194Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v; $instanceof $byte[]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte[]", getVal(opers_, 3));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v; ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence195Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t;.=8;\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = pkg.Ex.exmeth(0i);\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.getAnalyzing().setImporting(r_);
        conf_.getAnalyzing().setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", getVal(values_, 0));
        assertEq(" pkg.Ex.exmeth(0i)", getVal(values_, 6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.Ex", d_.getDelKeyWordStaticExtract().first());
    }

    @Test
    public void getOperationsSequence196Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t;.=8;\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = Ex.exmeth(0i);\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.getAnalyzing().setImporting(r_);
        conf_.getAnalyzing().setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", getVal(values_, 0));
        assertEq(" Ex.exmeth(0i)", getVal(values_, 6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.Ex", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence197Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t;.=8;\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $String inst = $Class.forName(\"\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.getAnalyzing().setImporting(r_);
        conf_.getAnalyzing().setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", getVal(values_, 0));
        assertEq(" $Class.forName(\"\")", getVal(values_, 6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("java.lang.$Class", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence198Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = Ex.exmeth(0i);\n");
        xml_.append(" $public $static $class Ex{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.getAnalyzing().setImporting(r_);
        conf_.getAnalyzing().setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", getVal(values_, 0));
        assertEq(" Ex.exmeth(0i)", getVal(values_, 6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExTwo..Ex", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence199Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int Ex = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t;.=8;\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int Ex = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t;.=8;\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = pkg.ExThree.Ex.inst;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.getAnalyzing().setImporting(r_);
        conf_.getAnalyzing().setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", getVal(values_, 0));
        assertEq(" pkg.ExThree.Ex.inst", getVal(values_, 6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExThree.Ex", d_.getDelKeyWordStaticExtract().first());
    }
    @Test
    public void getOperationsSequence200Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t;.=8;\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = ExTwo.Ex.exmeth(0i);\n");
        xml_.append(" $public $static $class Ex{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        conf_.getAnalyzing().setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.getAnalyzing().setImporting(r_);
        conf_.getAnalyzing().setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 5));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("inst ", getVal(values_, 0));
        assertEq(" ExTwo.Ex.exmeth(0i)", getVal(values_, 6));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExTwo..Ex", d_.getDelKeyWordStaticExtract().first());
    }


    @Test
    public void getOperationsSequence201Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "a<b>c";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("<", getVal(opers_, 1));
        assertEq(">", getVal(opers_, 3));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("a", getVal(values_, 0));
        assertEq("b", getVal(values_, 2));
        assertEq("c", getVal(values_, 4));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence202Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer[8i]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", getVal(opers_, 22));
        assertEq("]", getVal(opers_, 25));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", getVal(values_, 0));
        assertEq("8i", getVal(values_, 23));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence203Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer[8i][]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", getVal(opers_, 22));
        assertEq("]", getVal(opers_, 25));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", getVal(values_, 0));
        assertEq("8i", getVal(values_, 23));
        assertTrue(seq_.isInstance());
        assertEq(1, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence204Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer[8i][5i]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("[", getVal(opers_, 22));
        assertEq("]", getVal(opers_, 25));
        assertEq("[", getVal(opers_, 26));
        assertEq("]", getVal(opers_, 29));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("$new java.lang.Integer", getVal(values_, 0));
        assertEq("8i", getVal(values_, 23));
        assertEq("5i", getVal(values_, 27));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence205Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer[8i][][]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", getVal(opers_, 22));
        assertEq("]", getVal(opers_, 25));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", getVal(values_, 0));
        assertEq("8i", getVal(values_, 23));
        assertTrue(seq_.isInstance());
        assertEq(2, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence206Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new List<java.lang.Integer>[8i]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", getVal(opers_, 28));
        assertEq("]", getVal(opers_, 31));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new List<java.lang.Integer>", getVal(values_, 0));
        assertEq("8i", getVal(values_, 29));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence207Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new List<java.lang.Integer[]>[8i]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", getVal(opers_, 30));
        assertEq("]", getVal(opers_, 33));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new List<java.lang.Integer[]>", getVal(values_, 0));
        assertEq("8i", getVal(values_, 31));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence208Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new List<java.lang.Integer[]>[8i][]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", getVal(opers_, 30));
        assertEq("]", getVal(opers_, 33));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new List<java.lang.Integer[]>", getVal(values_, 0));
        assertEq("8i", getVal(values_, 31));
        assertTrue(seq_.isInstance());
        assertEq(1, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence209Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer(8i)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 22));
        assertEq(")", getVal(opers_, 25));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", getVal(values_, 0));
        assertEq("8i", getVal(values_, 23));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence210Test() {
        AnalyzedTestContext conf_ = contextEl();

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
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", getVal(opers_, 17));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("composite.integer", getVal(values_, 0));
        assertEq("int", getVal(values_, 18));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence211Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "integer=1=0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 7));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("integer", getVal(values_, 0));
        assertEq("1=0", getVal(values_, 8));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertTrue(!seq_.isCallDbArray());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence212Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp>[]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp>[]", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence213Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp>[ ]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp>[ ]", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence214Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof $byte[ ]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte[ ]", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence215Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 $instanceof pkg.List<two.Tmp> []";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp> []", getVal(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", getVal(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence216Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[0](1,2)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[0]", getVal(values_, 0));
        assertEq("(1,2)", getVal(values_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence216___Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[0](1)";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[0]", getVal(values_, 0));
        assertEq("(1)", getVal(values_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence216_Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[0]((1,2))";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[0]", getVal(values_, 0));
        assertEq("((1,2))", getVal(values_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence217_Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[0](-(1,2))";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[0]", getVal(values_, 0));
        assertEq("(-(1,2))", getVal(values_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence218_Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "((1))";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", getVal(opers_, 0));
        assertEq(")", getVal(opers_, 4));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("", getVal(values_, 0));
        assertEq("(1)", getVal(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence219_Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "(1)=5";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", getVal(opers_, 3));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("(1)", getVal(values_, 0));
        assertEq("5", getVal(values_, 4));
        assertEq(ElResolver.AFF_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence217Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "abs(4,3)[0]{1}";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[0]", getVal(values_, 0));
        assertEq("{1}", getVal(values_, 11));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence218Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "0x1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1", getVal(values_, 0));
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
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "0x1f";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1f", getVal(values_, 0));
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
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "0x1p0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1p0", getVal(values_, 0));
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
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "0x1fp0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1fp0", getVal(values_, 0));
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
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "0x1.2p0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1.2p0", getVal(values_, 0));
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
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "0x1f.2p0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("0x1f.2p0", getVal(values_, 0));
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
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "`18`";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18", getVal(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence225Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "`18``36`";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18`36", getVal(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence226Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "tab[0]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 3));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("tab", getVal(values_, 0));
        assertEq("[0]", getVal(values_, 3));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(!seq_.isArray());
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence227Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "tab[0][1]";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", getVal(opers_, 6));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("tab[0]", getVal(values_, 0));
        assertEq("[1]", getVal(values_, 6));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(!seq_.isArray());
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence228Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "3*";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", getVal(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("3", getVal(values_, 0));
        assertEq("", getVal(values_, 2));
        assertEq(ElResolver.MULT_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence229Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "4. ";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_, 0);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("4. ", getVal(values_, 0));
    }

    @Test
    public void checkSyntax231FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "[";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax232FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "++";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax233FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "`";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax234FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\n\"";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax235FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\t'";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax236FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\r\"";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(-1, d_.getBadOffset());
    }
    @Test
    public void checkSyntax237FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "--";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax238FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "-";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax239FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "+";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax240FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1+$)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax241FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1+$(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax242FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1+$()";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax243FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "(}";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax244FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "}";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(0, d_.getBadOffset());
    }

    @Test
    public void checkSyntax247FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "([v )";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax248FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = " ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax249FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(2, d_.getBadOffset());
    }

    @Test
    public void checkSyntax250FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(2, d_.getBadOffset());
    }

    @Test
    public void checkSyntax251FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$()";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax252FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$vararg(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax253FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$class)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax254FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$class(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax255FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$instanceof(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax256FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$id)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax257FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$id(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax258FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$lambda)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax259FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$lambda(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax260FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$static(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax261FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$static() ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax262FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$super";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(6, d_.getBadOffset());
    }

    @Test
    public void checkSyntax263FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$super,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(6, d_.getBadOffset());
    }

    @Test
    public void checkSyntax264FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess(MyClass)method,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(26, d_.getBadOffset());
    }

    @Test
    public void checkSyntax265FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess(MyClass)method";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(26, d_.getBadOffset());
    }

    @Test
    public void checkSyntax266FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess(MyClass)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax267FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess(MyClass";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax268FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax269FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax270FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(10, d_.getBadOffset());
    }

    @Test
    public void checkSyntax271FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax272FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax273FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that.method";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax274FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that.method,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax275FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$thisaccess(MyClass) ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(21, d_.getBadOffset());
    }

    @Test
    public void checkSyntax276FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$superaccess(MyClass)method,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(27, d_.getBadOffset());
    }

    @Test
    public void checkSyntax278FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$superaccess(MyClass)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax279FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$superaccess(MyClass";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax280FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$superaccess(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax281FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$superaccess,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax282FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$superaccess";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax283FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$superaccess(MyClass) ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(22, d_.getBadOffset());
    }

    @Test
    public void checkSyntax284FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces(MyClass),";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax285FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces(MyClass) ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(21, d_.getBadOffset());
    }

    @Test
    public void checkSyntax286FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces(MyClass)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax287FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces(MyClass";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax288FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces( ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(13, d_.getBadOffset());
    }

    @Test
    public void checkSyntax289FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax290FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(10, d_.getBadOffset());
    }

    @Test
    public void checkSyntax291FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$interfaces(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax292FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice,";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax293FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$static()  ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax294FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$bool";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax295FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$bool ean()";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax296FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$defaultValue)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(14, d_.getBadOffset());
    }

    @Test
    public void checkSyntax297FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$defaultValue(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(14, d_.getBadOffset());
    }

    @Test
    public void checkSyntax298FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1+explicit(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax299FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1+explicit()";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax300FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1+explicit)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(11, d_.getBadOffset());
    }
    @Test
    public void checkSyntax301FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "explicit(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax302FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "explicit()";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax303FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "explicit)";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax304FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$values(MyClass";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(15, d_.getBadOffset());
    }

    @Test
    public void checkSyntax305FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$values";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax306FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$valueOf";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax307FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$valueOf(";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(8, d_.getBadOffset());
    }
    @Test
    public void checkSyntax1Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1==0";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(1, d_.getAllowedOperatorsIndexes().size());
        assertEq(1, d_.getAllowedOperatorsIndexes().first());
    }
    @Test
    public void checkSyntax2Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1 ";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }
    @Test
    public void checkSyntaxDelimiters1Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('\\u9fcb'+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters2Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('\\u9fcb'+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters3Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('`'+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(6, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters4Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('}'+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(6, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters5Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = " {6*('\\u9fcb'+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 2);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first());
        assertEq(12, d_.getDelStringsChars().last());
        assertEq(2, d_.getIndexBegin());
        assertEq(15, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters6Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = " {6*(\"//\"+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 2);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first());
        assertEq(8, d_.getDelStringsChars().last());
        assertEq(2, d_.getIndexBegin());
        assertEq(11, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters7Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = " {$new $int[]{1i,3i}}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 2);
        assertEq(3, d_.getAllowedOperatorsIndexes().size());
        assertEq(13, d_.getAllowedOperatorsIndexes().first());
        assertEq(16, d_.getAllowedOperatorsIndexes().get(1));
        assertEq(19, d_.getAllowedOperatorsIndexes().last());
        assertEq(2, d_.getIndexBegin());
        assertEq(19, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters1FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('\\u9fcb'+8)";
        assertEq(15, checkSyntaxDelimiters(conf_, el_, 1).getBadOffset());
    }
    @Test
    public void checkSyntaxDelimiters4FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('\\u9fcb'+8\\";
        assertEq(15, checkSyntaxDelimiters(conf_, el_, 1).getBadOffset());
    }
    @Test
    public void checkSyntaxDelimiters5FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('\\u9fcb'+8\\ ";
        assertEq(16, checkSyntaxDelimiters(conf_, el_, 1).getBadOffset());
    }
    @Test
    public void checkSyntaxDelimiters8Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*(`string`+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters9Test() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*(`string``after`+8)}";
        Delimiters d_ = checkSyntaxDelimiters(conf_, el_, 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(18, d_.getDelStringsChars().last());
        assertEq(1, d_.getIndexBegin());
        assertEq(21, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters2FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('\\u9fcb'+8){";
        assertEq(16, checkSyntaxDelimiters(conf_, el_, 1).getBadOffset());
    }

    @Test
    public void checkSyntaxDelimiters3FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "{6*('\\u9gcb'+8)}";
        assertEq(-1, checkSyntaxDelimiters(conf_, el_, 1).getBadOffset());
    }

    @Test
    public void checkSyntax1FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\u9gcb'+8)";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax2FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('\\g'+8)";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax3FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('ab'+8)";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax4FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*('a'+[8)]";
        assertEq(9, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax5FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*['a'+(8])";
        assertEq(9, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax7FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"t\\u98\"+[8])";
        assertEq(15, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax8FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "6*(\"t\\u98 \"+[8])";
        assertEq(16, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax10FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$static.a";
        assertEq(7, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax12FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1< =2";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax13FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1> =2";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax14FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1! =2";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax15FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v '";
        assertEq(3, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax16FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v; .";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax17FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v;. ;";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax18FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v; ;";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax19FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "v;  ;";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax20FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\";
        assertEq(2, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax20_FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'''\\";
        assertEq(4, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax21FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\\";
        assertEq(2, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax21_FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\"\"\\";
        assertEq(4, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax22FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\\u9fc";
        assertEq(6, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax22_FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\"\"\\u9fc";
        assertEq(8, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax23FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\u9fc";
        assertEq(6, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax23_FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'''\\u9fc";
        assertEq(8, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax24FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\\g9fc";
        assertEq(6, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax25FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\g9fc";
        assertEq(6, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax26FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "\"\\u9fcb";
        assertEq(7, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax27FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\u9fcb";
        assertEq(7, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax28FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1)";
        assertEq(1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax29FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "(1";
        assertEq(2, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax30FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1]";
        assertEq(1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax31FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "[1";
        assertEq(2, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax33FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer";
        assertEq(21, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax34FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new java.lang.Integer(?java";
        assertEq(28, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax35FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "a,b";
        assertEq(1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax36FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "integer[?java";
        assertEq(13, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax40FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$static(pkg$classname";
        assertEq(22, checkSyntax(conf_, el_).getBadOffset());
    }


    @Test
    public void checkSyntax77FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$vararg";
        assertEq(7, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax78FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$vararg+4";
        assertEq(9, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax79FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\u9";
        assertEq(4, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax80FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "'\\u9'";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax81FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math";
        assertEq(18, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax82FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math$$";
        assertEq(20, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax83FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice(";
        assertEq(12, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax84FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math$abs$";
        assertEq(23, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax85FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math$abs)";
        assertEq(22, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax86FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math$abs) ";
        assertEq(24, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax87FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice ";
        assertEq(12, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax88FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that.";
        assertEq(5, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax89FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$this(";
        assertEq(6, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax90FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that.call";
        assertEq(10, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax91FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that.call$";
        assertEq(11, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax92FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$that.call$$";
        assertEq(12, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax93FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$classchoice($math$abs$$abs;)";
        assertEq(28, checkSyntax(conf_, el_).getBadOffset());
    }
    @Test
    public void checkSyntax94FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "_1";
        Delimiters d_ = checkSyntax(conf_, el_);
        assertEq(0, d_.getDelNumbers().size());
    }

    @Test
    public void checkSyntax95FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "1e1 ";
        assertEq(-1, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax96FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new $interfaces ";
        assertEq(5, checkSyntax(conf_, el_).getBadOffset());
    }

    @Test
    public void checkSyntax97FailTest() {
        AnalyzedTestContext conf_ = contextEl();

        String el_ = "$new $interfaces( ";
        assertEq(5, checkSyntax(conf_, el_).getBadOffset());
    }

    private static AnalyzedTestContext prepare(StringMap<String> _files) {
        AnalyzedTestContext conf_ = contextEl();
        parseCustomFiles(_files, conf_);
        validateInheritingClasses(conf_);
        validateIds(conf_);
        validateOverridingInherit(conf_);
        return conf_;
    }

    private static OperationsSequence getOperationsSequence(AnalyzedTestContext _conf, String _el, Delimiters _d, int _offset) {
        return ElResolver.getOperationsSequence(_offset, _el, _d, _conf.getAnalyzing());
    }
    private static AnalyzedTestContext contextEl() {
        return ctxAna();
    }

    private static Delimiters checkSyntax(AnalyzedTestContext _conf, String _el) {
        return ElResolver.checkSyntax(_el, 0, _conf.getAnalyzing());
    }

    private static Delimiters checkSyntaxDelimiters(AnalyzedTestContext _conf, String _el, int _minIndex) {
        return ElResolver.checkSyntaxDelimiters(_el, _minIndex, _conf.getAnalyzing());
    }

    private static void setGlobalType(AnalyzedTestContext _conf, String _globalClass) {
        _conf.getAnalyzing().setGlobalClass(_globalClass);
        _conf.getAnalyzing().setGlobalType(_conf.getAnalyzing().getAnaClassBody(StringExpUtil.getIdFromAllTypes(_globalClass)));
    }

    private static RootBlock getAnaClassBody(AnalyzedTestContext _classes, String _className) {
        for (RootBlock r: _classes.getAnalyzing().getFoundTypes()) {
            if (StringUtil.quickEq(r.getFullName(),StringExpUtil.getIdFromAllTypes(_className))) {
                return r;
            }
        }
        return null;
    }

    private static String getVal(StrTypes _opers, int _i) {
        for (IndexStrPart e:_opers.getValues()) {
            if (_i == e.getIndex()) {
                return e.getPart();
            }
        }
        return null;
    }


}
