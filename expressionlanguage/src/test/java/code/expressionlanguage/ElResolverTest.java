package code.expressionlanguage;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import code.expressionlanguage.classes.BeanOne;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.util.NatTreeMap;

@SuppressWarnings("static-method")
public class ElResolverTest {
    private static final String ARR_INT = "[I";
    private static final String ARR_INTEGER = "[java.lang.Integer";

    @Test
    public void getOperationsSequence1Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(3, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(5));
        assertEq(")", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("4", values_.getVal(4));
        assertEq("3", values_.getVal(6));
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs(4,3).abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(8));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(9));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence3Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs(4+3).abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(8));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4+3)", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(9));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence4Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\""+ARR_INT+"\"),4+3).abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(22));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg(\""+ARR_INT+"\"),4+3)", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(23));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence5Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\""+ARR_INT+"\"),'[').abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(22));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg(\""+ARR_INT+"\"),'[')", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(23));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence6Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\""+ARR_INT+"\"),'[').abs(4,3)+8";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(31));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg(\""+ARR_INT+"\"),'[').abs(4,3)", values_.getVal(0));
        assertEq("8", values_.getVal(32));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence7Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(1+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(1+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence8Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\u9fcb'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\u9fcb'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence9Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\''+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\''+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence10Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"ab\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"ab\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence11Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-6*8";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-6", values_.getVal(0));
        assertEq("8", values_.getVal(3));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence12Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-abs(8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("abs(8)", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence13Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\""+ARR_INT+"\"),'[').abs(4,3)+8-9";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(33));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg(\""+ARR_INT+"\"),'[').abs(4,3)+8", values_.getVal(0));
        assertEq("9", values_.getVal(34));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence14Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1.8-abs(9)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(3));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1.8", values_.getVal(0));
        assertEq("abs(9)", values_.getVal(4));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence15Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1.8";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.8", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence16Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"18\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("\"18\"", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence17Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "18";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("18", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence18Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "(4+3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(0));
        assertEq(")", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("4+3", values_.getVal(1));
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence19Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(4+3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("4+3", values_.getVal(10));
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence20Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\""+ARR_INT+"\"),4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(17));
        assertEq(",", opers_.getVal(19));
        assertEq(")", opers_.getVal(21));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg(\""+ARR_INT+"\")", values_.getVal(4));
        assertEq("4", values_.getVal(18));
        assertEq("3", values_.getVal(20));
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence21Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v;";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        assertSame(ConstType.LOOP_VAR, seq_.getConstType());
    }

    @Test
    public void getOperationsSequence22Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v;.";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        assertSame(ConstType.LOC_VAR, seq_.getConstType());
    }

    @Test
    public void getOperationsSequence23Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence24Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs(4,3)[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(8));
        assertEq("]", opers_.getVal(10));
        assertEq(9, seq_.getPriority());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", values_.getVal(0));
        assertEq("0", values_.getVal(9));
        assertEq(ElResolver.ARR_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence25Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs(4,3)[14][5]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(12));
        assertEq("]", opers_.getVal(14));
        assertEq(9, seq_.getPriority());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14]", values_.getVal(0));
        assertEq("5", values_.getVal(13));
        assertEq(ElResolver.ARR_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence26Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"a b\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"a b\"+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence27Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v.a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq("a", values_.getVal(2));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence28Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("call()", values_.getVal(5));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence29Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.;call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("call()", values_.getVal(6));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence30Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;;call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("call()", values_.getVal(5));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence31Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("call()", values_.getVal(4));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence32Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(11));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.call()", values_.getVal(0));
        assertEq("call()", values_.getVal(12));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence33Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.;call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(12));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;call()", values_.getVal(0));
        assertEq("call()", values_.getVal(13));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence34Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;;call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(11));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;call()", values_.getVal(0));
        assertEq("call()", values_.getVal(12));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence35Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;call().call()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(10));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;call()", values_.getVal(0));
        assertEq("call()", values_.getVal(11));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence36Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(22));
        assertEq(")", opers_.getVal(26));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer", values_.getVal(0));
        assertEq("\"8\"", values_.getVal(23));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence37Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "call().$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("call()", values_.getVal(0));
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(7));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence38Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$new java.lang.Integer(\"8\").intValue()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(27));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(0));
        assertEq("intValue()", values_.getVal(28));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence39Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$new "+ARR_INTEGER+"(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(23));
        assertEq(")", opers_.getVal(27));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new "+ARR_INTEGER, values_.getVal(0));
        assertEq("\"8\"", values_.getVal(24));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence40Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "new.java.lang.Integer(\"8\").intValue()+5";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(37));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("new.java.lang.Integer(\"8\").intValue()", values_.getVal(0));
        assertEq("5", values_.getVal(38));
    
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence41Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(5));
        assertEq("]", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("0", values_.getVal(6));
    
        assertEq(ElResolver.ARR_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence42Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(6));
        assertEq("]", opers_.getVal(8));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("0", values_.getVal(7));
    
        assertEq(ElResolver.ARR_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence43Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(5));
        assertEq("]", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("0", values_.getVal(6));
    
        assertEq(ElResolver.ARR_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence44Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(4));
        assertEq("]", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("0", values_.getVal(5));
    
        assertEq(ElResolver.ARR_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence45Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("f[0]", values_.getVal(5));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence46Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("f[0]", values_.getVal(6));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence47Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("f[0]", values_.getVal(5));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence48Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("f[0]", values_.getVal(4));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence49Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("f()[0]", values_.getVal(5));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence50Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("f()[0]", values_.getVal(6));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence51Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("f()[0]", values_.getVal(5));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence52Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("f()[0]", values_.getVal(4));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence53Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$static$pkg$classname.field";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(21));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$static$pkg$classname", values_.getVal(0));
        assertEq("field", values_.getVal(22));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence54Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "--1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-1", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence55Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-1", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence56Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-1.0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-1.0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence57Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1--1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1", values_.getVal(0));
        assertEq("-1", values_.getVal(2));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence58Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence59Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!!a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("!a", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence60Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "b!=a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(3));
    
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence61Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "b<=a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("<=", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(3));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence62Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "b>=a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(">=", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(3));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence63Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "b=a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("b", values_.getVal(0));
        assertEq("a", values_.getVal(2));
    
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence64Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"\\\"string\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("\"\\\"string\"", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence65Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "'\\''";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("'\\''", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence66Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "'\\\\'";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("'\\\\'", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence67Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(\"\\\"string\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(20));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("\"\\\"string\"", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence68Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt('\\'')";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("'\\''", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence69Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt('\\\\')";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("'\\\\'", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence70Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(1.0)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("1.0", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence71Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\"java.lang.Object\"),$firstopt(4),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(31));
        assertEq(",", opers_.getVal(44));
        assertEq(")", opers_.getVal(46));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg(\"java.lang.Object\")", values_.getVal(4));
        assertEq("$firstopt(4)", values_.getVal(32));
        assertEq("3", values_.getVal(45));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence72Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\"java.lang.Object\"),$firstopt(4;.;),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(31));
        assertEq(",", opers_.getVal(47));
        assertEq(")", opers_.getVal(49));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg(\"java.lang.Object\")", values_.getVal(4));
        assertEq("$firstopt(4;.;)", values_.getVal(32));
        assertEq("3", values_.getVal(48));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence73Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "abs($vararg(\"java.lang.Object\"),$firstopt(4;.),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(31));
        assertEq(",", opers_.getVal(46));
        assertEq(")", opers_.getVal(48));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(4, values_.size());
        assertEq("abs", values_.getVal(0));
        assertEq("$vararg(\"java.lang.Object\")", values_.getVal(4));
        assertEq("$firstopt(4;.)", values_.getVal(32));
        assertEq("3", values_.getVal(47));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence74Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;.)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence75Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;.;)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.;", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence76Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(12));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence77Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;;)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;;", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    //optional parameter with qualified access
    @Test
    public void getOperationsSequence78Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;.t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.t", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence79Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;.;t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(15));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;.;t", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence80Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(13));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;t", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }



    @Test
    public void getOperationsSequence81Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$firstopt(v;;t)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(9));
        assertEq(")", opers_.getVal(14));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$firstopt", values_.getVal(0));
        assertEq("v;;t", values_.getVal(10));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence82Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-10";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-10", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence83Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", values_.getVal(1));
    
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence84Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-1d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-1d", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence85Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-1.0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-1.0d", values_.getVal(0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence86Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a&b!=c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b!=c", values_.getVal(2));
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence87Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v; a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;", values_.getVal(0));
        assertEq(" a", values_.getVal(2));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence88Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v; ";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertSame(ConstType.LOOP_VAR, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence89Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a .b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a ", values_.getVal(0));
        assertEq("b", values_.getVal(3));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence90Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\u9Fcb'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\u9Fcb'+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence91Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"\\u9Fcb\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\u9Fcb\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence92Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\n'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\n'+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence93Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"\\n\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\n\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence94Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\r'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\r'+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence95Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"\\r\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\r\"+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence96Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\b'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\b'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence97Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"\\b\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\b\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence98Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\t'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\t'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence99Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"\\t\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\t\"+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence100Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"\\\"string\"+\"\\\"string\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(10));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("\"\\\"string\"", values_.getVal(0));
        assertEq("\"\\\"string\"", values_.getVal(11));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence101Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"\\\\\\\"string\"+\"\\\"string\"";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(12));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("\"\\\\\\\"string\"", values_.getVal(0));
        assertEq("\"\\\"string\"", values_.getVal(13));
    
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence102Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\f'+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("('\\f'+8)", values_.getVal(2));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence103Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"\\f\"+8)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("*", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("6", values_.getVal(0));
        assertEq("(\"\\f\"+8)", values_.getVal(2));
    
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence104Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!!field";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("!field", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence105Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!field!=anotherfield";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!field", values_.getVal(0));
        assertEq("anotherfield", values_.getVal(8));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence106Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "field!=!anotherfield";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("field", values_.getVal(0));
        assertEq("!anotherfield", values_.getVal(7));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence107Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!field!=!anotherfield";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!=", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!field", values_.getVal(0));
        assertEq("!anotherfield", values_.getVal(8));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence108Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v;.news.a()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;.news", values_.getVal(0));
        assertEq("a()", values_.getVal(8));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence109Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "news.a()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("news", values_.getVal(0));
        assertEq("a()", values_.getVal(5));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence110Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("f", values_.getVal(5));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence111Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.;f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("f", values_.getVal(6));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence112Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;;f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("f", values_.getVal(5));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence113Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;f";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("f", values_.getVal(4));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence114Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "+a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("", values_.getVal(0));
        assertEq("a", values_.getVal(1));
    
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence115Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a|b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("|", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b", values_.getVal(2));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence116Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a&b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b", values_.getVal(2));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence117Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a|b&c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("|", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b&c", values_.getVal(2));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence118Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a&b|c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("|", opers_.getVal(3));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("a&b", values_.getVal(0));
        assertEq("c", values_.getVal(4));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence119Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!a|b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("|", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", values_.getVal(0));
        assertEq("b", values_.getVal(3));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence120Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!a&b";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", values_.getVal(0));
        assertEq("b", values_.getVal(3));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence121Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!a|b&c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("|", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a", values_.getVal(0));
        assertEq("b&c", values_.getVal(3));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence122Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "!a&b|c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("|", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("!a&b", values_.getVal(0));
        assertEq("c", values_.getVal(5));
    
        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence123Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "(a|b)&c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("&", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("(a|b)", values_.getVal(0));
        assertEq("c", values_.getVal(6));
    
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence124Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "(a|b)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(0));
        assertEq(")", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a|b", values_.getVal(1));
    
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence125Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v;.[0i].array[0i]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;.[0i]", values_.getVal(0));
        assertEq("array[0i]", values_.getVal(8));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence126Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;.$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(5));
    
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence127Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "var;$new java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("$new java.lang.Integer(\"8\")", values_.getVal(4));
        assertEq(ElResolver.DOT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence128Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence129Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence130Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence131Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.0e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence132Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence133Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence134Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".1e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence135Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".1e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence136Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence137Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence138Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1e2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1e2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence139Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1e-2";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1e-2", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence140Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence141Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1e-2+.5";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-.1e-2", values_.getVal(0));
        assertEq(".5", values_.getVal(7));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence142Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1e-2d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1e-2d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence143Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1bs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(3, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(5));
        assertEq(")", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("1bs", values_.getVal(0));
        assertEq("4", values_.getVal(4));
        assertEq("3", values_.getVal(6));
        assertEq(ElResolver.FCT_OPER_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence144Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = " !a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("a", values_.getVal(2));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence145Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "! a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("!", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" a", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence146Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "-- a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("- a", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence147Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "- -a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" -a", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence148Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = " --a";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-a", values_.getVal(2));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence149Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = " v;";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertSame(ConstType.LOOP_VAR, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        assertEq(1, seq_.getOffset());
    }

    @Test
    public void checkSyntaxDelimiters1Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "{6*('\\u9fcb'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first().intValue());
        assertEq(11, d_.getDelStringsChars().last().intValue());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters2Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "`6*('\\u9fcb'+8)`";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '`', '`');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first().intValue());
        assertEq(11, d_.getDelStringsChars().last().intValue());
        assertEq(1, d_.getIndexBegin());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters3Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "`6*('`'+8)`";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '`', '`');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first().intValue());
        assertEq(6, d_.getDelStringsChars().last().intValue());
        assertEq(1, d_.getIndexBegin());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters4Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "{6*('}'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first().intValue());
        assertEq(6, d_.getDelStringsChars().last().intValue());
        assertEq(1, d_.getIndexBegin());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters5Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = " {6*('\\u9fcb'+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 2, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first().intValue());
        assertEq(12, d_.getDelStringsChars().last().intValue());
        assertEq(2, d_.getIndexBegin());
        assertEq(15, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters6Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = " {6*(\"//\"+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 2, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first().intValue());
        assertEq(8, d_.getDelStringsChars().last().intValue());
        assertEq(2, d_.getIndexBegin());
        assertEq(11, d_.getIndexEnd());
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntaxDelimiters1FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "{6*('\\u9fcb'+8)";
        ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntaxDelimiters2FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "{6*('\\u9fcb'+8){";
        ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntaxDelimiters3FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "{6*('\\u9gcb'+8)}";
        ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}');
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax1FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\u9gcb'+8)";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax2FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('\\g'+8)";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax3FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('ab'+8)";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax4FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*('a'+[8)]";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax5FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*['a'+(8])";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax7FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"t\\u98\"+[8])";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax8FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6*(\"t\\u98 \"+[8])";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax9FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "6 1*(\"te\"+[8])";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax10FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$static.a";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax11FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a.$static";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax12FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1< =2";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax13FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1> =2";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax14FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1! =2";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax15FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v ;";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax16FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v; .";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax17FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v;. ;";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax18FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v; ;";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax19FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v;  ;";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax20FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "'\\";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax21FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"\\";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax22FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"\\u9fc";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax23FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "'\\u9fc";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax24FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"\\g9fc";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax25FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "'\\g9fc";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax26FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "\"\\u9fcb";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax27FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "'\\u9fcb";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax28FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1)";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax29FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "(1";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax30FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1]";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax31FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "[1";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax32FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "v.";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax33FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$new java.lang.Integer";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax34FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "new.java.lang.Integer(?java";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax35FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "a,b";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax36FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "integer[?java";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax37FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "integer\\n";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax38FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1 .0";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax39FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1. 0";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax40FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "$static$pkg$classname";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax41FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = ".1.0";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax42FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1e.0";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax43FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1e";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax44FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1.0e.2";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax45FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1.0e5.2";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void checkSyntax46FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        BeanOne b_ = new BeanOne();
        addBean(conf_, b_);
        String el_ = "1.0.2";
        ElResolver.checkSyntax(el_, conf_, 0);
    }

    private static void addImportingPage(ContextEl _conf, boolean _rendering) {
        _conf.setAnalyzing(new PageEl());
    }

    private static void addBean(ContextEl _conf, Object _bean) {
        _conf.getLastPage().setGlobalArgumentObj(_bean);
    }
    private ContextEl contextEl() {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        return cont_;
    }
}
