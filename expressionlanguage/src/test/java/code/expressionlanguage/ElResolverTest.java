package code.expressionlanguage;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.NatTreeMap;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ElResolverTest {

    @Test
    public void getOperationsSequence1Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence2Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence3Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence4Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "abs($vararg([$int),4+3).abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(23));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),4+3)", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(24));
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence5Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "abs($vararg([$int),'[').abs(4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(23));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[')", values_.getVal(0));
        assertEq("abs(4,3)", values_.getVal(24));
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence6Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "abs($vararg([$int),'[').abs(4,3)+8";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+", opers_.getVal(32));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[').abs(4,3)", values_.getVal(0));
        assertEq("8", values_.getVal(33));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence7Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        String el_ = "abs($vararg([$int),'[').abs(4,3)+8-9";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(34));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs($vararg([$int),'[').abs(4,3)+8", values_.getVal(0));
        assertEq("9", values_.getVal(35));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence14Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence19Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence20Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "abs($vararg([$int),4,3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(18));
        assertEq(",", opers_.getVal(20));
        assertEq(")", opers_.getVal(22));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
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
        addImportingPage(conf_, false);
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
        String el_ = "abs(4,3)[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(8));
        assertTrue(seq_.isDot());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)", values_.getVal(0));
        assertEq("[0]", values_.getVal(8));
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence25Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "abs(4,3)[14][5]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(12));
        assertTrue(seq_.isDot());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("abs(4,3)[14]", values_.getVal(0));
        assertEq("[5]", values_.getVal(12));
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence26Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence28Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence29Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence30Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence31Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence32Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }


    @Test
    public void getOperationsSequence33Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence34Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence35Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence36Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence37Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }


    @Test
    public void getOperationsSequence38Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }


    @Test
    public void getOperationsSequence39Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$new [java.lang.Integer(\"8\")";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(23));
        assertEq(")", opers_.getVal(27));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$new [java.lang.Integer", values_.getVal(0));
        assertEq("\"8\"", values_.getVal(24));
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence40Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        String el_ = "var;.[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.", values_.getVal(0));
        assertEq("[0]", values_.getVal(5));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence42Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;.;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;", values_.getVal(0));
        assertEq("[0]", values_.getVal(6));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence43Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;", values_.getVal(0));
        assertEq("[0]", values_.getVal(5));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence44Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;", values_.getVal(0));
        assertEq("[0]", values_.getVal(4));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence45Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;.f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.f", values_.getVal(0));
        assertEq("[0]", values_.getVal(6));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence46Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;.;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;f", values_.getVal(0));
        assertEq("[0]", values_.getVal(7));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence47Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;f", values_.getVal(0));
        assertEq("[0]", values_.getVal(6));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence48Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;f[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;f", values_.getVal(0));
        assertEq("[0]", values_.getVal(5));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence49Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;.f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(8));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(8));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence50Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;.;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(9));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;.;f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(9));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence51Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(8));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;;f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(8));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence52Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "var;f()[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("var;f()", values_.getVal(0));
        assertEq("[0]", values_.getVal(7));
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence53Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$static(pkg.classname).field";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(22));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$static(pkg.classname)", values_.getVal(0));
        assertEq("field", values_.getVal(23));
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence54Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence62Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence63Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence68Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence69Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence70Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence71Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "abs($vararg(java.lang.Object),$firstopt(4),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(29));
        assertEq(",", opers_.getVal(42));
        assertEq(")", opers_.getVal(44));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
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
        addImportingPage(conf_, false);
        String el_ = "abs($vararg(java.lang.Object),$firstopt(4;.;),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(29));
        assertEq(",", opers_.getVal(45));
        assertEq(")", opers_.getVal(47));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
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
        addImportingPage(conf_, false);
        String el_ = "abs($vararg(java.lang.Object),$firstopt(4;.),3)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
//        assertEq(0, opers_.size());
        assertEq(4, opers_.size());
        assertEq("(", opers_.getVal(3));
        assertEq(",", opers_.getVal(29));
        assertEq(",", opers_.getVal(44));
        assertEq(")", opers_.getVal(46));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
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
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence75Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence76Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence77Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    //optional parameter with qualified access
    @Test
    public void getOperationsSequence78Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence79Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence80Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }



    @Test
    public void getOperationsSequence81Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence82Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence88Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence90Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence109Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }


    @Test
    public void getOperationsSequence110Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence111Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence112Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence113Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence114Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence125Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v;.[0i].array[0i]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("", opers_.getVal(13));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v;.[0i].array", values_.getVal(0));
        assertEq("[0i]", values_.getVal(13));
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence126Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    
        assertTrue(seq_.isDot());
    }

    @Test
    public void getOperationsSequence127Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        assertTrue(seq_.isDot());
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
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence144Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
    public void getOperationsSequence150Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1_0e-2d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1_0e-2d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence151Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1e-2_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1e-2_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence152Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1_0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1_0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence153Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence154Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1e1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.1e1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence155Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence156Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".1_0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence157Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(".1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence158Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence159Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence160Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
 
    @Test
    public void getOperationsSequence161Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.2_0e1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-.2_0e1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence162Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-1.2_0e1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("-1.2_0e1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence163Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.1_0";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.1_0", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    
    @Test
    public void getOperationsSequence164Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.1_0d";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1.1_0d", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence165Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math)abs()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(22));
        assertEq(")", opers_.getVal(23));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$classchoice($math)abs", values_.getVal(0));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence166Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v+=b";
        conf_.setRootAffect(true);
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        conf_.setAnalyzingRoot(true);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("+=", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq("b", values_.getVal(3));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence167Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v++";
        conf_.setRootAffect(true);
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        conf_.setAnalyzingRoot(true);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("++", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v", values_.getVal(0));
        assertEq(ElResolver.POST_INCR_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence168Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math)abs";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$classchoice($math)abs", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence169Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math)abs$.field";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(23));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$classchoice($math)abs$", values_.getVal(0));
        assertEq("field", values_.getVal(24));
        assertTrue(seq_.isDot());
    }
    @Test
    public void getOperationsSequence170Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$this()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(5));
        assertEq(")", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this", values_.getVal(0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence171Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$this ()";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(6));
        assertEq(")", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this ", values_.getVal(0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence172Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$this";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$this", values_.getVal(0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence173Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$($int)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", values_.getVal(7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence174Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$($int) 1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(" 1", values_.getVal(7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence175Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = " $($int)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(1));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1", values_.getVal(8));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence176Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-$($int)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$($int)1", values_.getVal(1));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence177Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$($int)$($byte)1";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$($int)", opers_.getVal(0));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("$($byte)1", values_.getVal(7));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence178Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence179Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof pkg.List<two.Tmp>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp>", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence180Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof pkg.List<two.Tmp,three.Sec>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp,three.Sec>", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence181Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence182Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>=$true";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(42));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", values_.getVal(0));
        assertEq("$true", values_.getVal(43));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence183Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v; $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(3));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v; ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence184Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence185Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v() $instanceof $byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof $byte", opers_.getVal(4));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("v() ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence186Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$true=1 $instanceof pkg.List<two.Tmp<three.Sec>>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", values_.getVal(6));
        assertEq("$true", values_.getVal(0));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence187Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof #T";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof #T", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence188Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof pkg . One";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg . One", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence189Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$interfaces(pkg.MyClass)(arg;.)";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("(", opers_.getVal(24));
        assertEq(")", opers_.getVal(30));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("$interfaces(pkg.MyClass)", values_.getVal(0));
        assertEq("arg;.", values_.getVal(25));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence190Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "[0]";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("[", opers_.getVal(0));
        assertEq("]", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("", values_.getVal(0));
        assertEq("0", values_.getVal(1));
    
        assertTrue(seq_.isArray());
    }

    @Test
    public void getOperationsSequence191Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "-.1e-2-.5";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("-", opers_.getVal(6));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("-.1e-2", values_.getVal(0));
        assertEq(".5", values_.getVal(7));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence192Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", opers_.getVal(2));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq("1 ", values_.getVal(0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence193Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>=$true";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("=", opers_.getVal(56));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", values_.getVal(0));
        assertEq("$true", values_.getVal(57));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence194Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v; $instanceof [$byte";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq("$instanceof [$byte", opers_.getVal(3));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
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
        Classes.tryBuildBracedClassesBodies(files_, conf_);
        classes_.validateInheritingClasses(conf_);
        classes_.validateSingleParameterizedClasses(conf_);
        classes_.validateIds(conf_);
        classes_.validateOverridingInherit(conf_);
        addImportingPage(conf_, false);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(" pkg.Ex", values_.getVal(0));
        assertEq("exmeth(0i)", values_.getVal(8));
        assertTrue(seq_.isDot());
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
        Classes.tryBuildBracedClassesBodies(files_, conf_);
        classes_.validateInheritingClasses(conf_);
        classes_.validateSingleParameterizedClasses(conf_);
        classes_.validateIds(conf_);
        classes_.validateOverridingInherit(conf_);
        addImportingPage(conf_, false);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(3));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(" Ex", values_.getVal(0));
        assertEq("exmeth(0i)", values_.getVal(4));
        assertTrue(seq_.isDot());
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
        Classes.tryBuildBracedClassesBodies(files_, conf_);
        classes_.validateInheritingClasses(conf_);
        classes_.validateSingleParameterizedClasses(conf_);
        classes_.validateIds(conf_);
        classes_.validateOverridingInherit(conf_);
        addImportingPage(conf_, false);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(7));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(" $Class", values_.getVal(0));
        assertEq("forName(\"\")", values_.getVal(8));
        assertTrue(seq_.isDot());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("$Class", d_.getDelKeyWordStaticExtract().first());
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
        Classes.tryBuildBracedClassesBodies(files_, conf_);
        classes_.validateInheritingClasses(conf_);
        classes_.validateSingleParameterizedClasses(conf_);
        classes_.validateIds(conf_);
        classes_.validateOverridingInherit(conf_);
        addImportingPage(conf_, false);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(5));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(" ..Ex", values_.getVal(0));
        assertEq("exmeth(0i)", values_.getVal(6));
        assertTrue(seq_.isDot());
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
        xml_.append("$public $class pkg.Ex.Ex {\n");
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
        xml_.append(" $public $static $int inst = pkg.Ex.Ex.inst:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl conf_ = contextEl();
        Classes classes_ = conf_.getClasses();
        Classes.buildPredefinedBracesBodies(conf_);
        Classes.tryBuildBracedClassesBodies(files_, conf_);
        classes_.validateInheritingClasses(conf_);
        classes_.validateSingleParameterizedClasses(conf_);
        classes_.validateIds(conf_);
        classes_.validateOverridingInherit(conf_);
        addImportingPage(conf_, false);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(10));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(" pkg.Ex.Ex", values_.getVal(0));
        assertEq("inst", values_.getVal(11));
        assertTrue(seq_.isDot());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.Ex", d_.getDelKeyWordStaticExtract().first());
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
        Classes.tryBuildBracedClassesBodies(files_, conf_);
        classes_.validateInheritingClasses(conf_);
        classes_.validateSingleParameterizedClasses(conf_);
        classes_.validateIds(conf_);
        classes_.validateOverridingInherit(conf_);
        addImportingPage(conf_, false);
        conf_.setGlobalClass("pkg.ExTwo");
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        Block b_ = r_.getFirstChild();
        conf_.getAnalyzing().setCurrentBlock(b_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(".", opers_.getVal(10));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(" ExTwo..Ex", values_.getVal(0));
        assertEq("exmeth(0i)", values_.getVal(11));
        assertTrue(seq_.isDot());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExTwo..Ex", d_.getDelKeyWordStaticExtract().first());
    }

    @Test
    public void getOperationsSequence201Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "a<b>c";
        Delimiters d_ = ElResolver.checkSyntax(el_, conf_, 0);
        OperationsSequence seq_ = ElResolver.getOperationsSequence(0, el_, conf_, d_);
        NatTreeMap<Integer,String> opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq("<", opers_.getVal(1));
        assertEq(">", opers_.getVal(3));
        NatTreeMap<Integer,String> values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq("a", values_.getVal(0));
        assertEq("b", values_.getVal(2));
        assertEq("c", values_.getVal(4));
    
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }
    @Test
    public void checkSyntaxDelimiters1Test() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
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
        String el_ = " {6*(\"//\"+8)}";
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(el_, conf_, 2, '{', '}');
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first().intValue());
        assertEq(8, d_.getDelStringsChars().last().intValue());
        assertEq(2, d_.getIndexBegin());
        assertEq(11, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters1FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "{6*('\\u9fcb'+8)";
        assertEq(15, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }

    @Test
    public void checkSyntaxDelimiters2FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "{6*('\\u9fcb'+8){";
        assertEq(15, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }

    @Test
    public void checkSyntaxDelimiters3FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "{6*('\\u9gcb'+8)}";
        assertEq(8, ElResolver.checkSyntaxDelimiters(el_, conf_, 1, '{', '}').getBadOffset());
    }

    @Test
    public void checkSyntax1FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6*('\\u9gcb'+8)";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax2FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6*('\\g'+8)";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax3FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6*('ab'+8)";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax4FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6*('a'+[8)]";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax5FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6*['a'+(8])";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax7FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6*(\"t\\u98\"+[8])";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax8FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6*(\"t\\u98 \"+[8])";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax9FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "6 1*(\"te\"+[8])";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax10FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$static.a";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax11FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "a.$static";
        assertEq(8, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax12FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1< =2";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax13FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1> =2";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax14FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1! =2";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax15FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v '";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax16FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v; .";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax17FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v;. ;";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax18FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v; ;";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax19FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v;  ;";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax20FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "'\\";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax21FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "\"\\";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax22FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "\"\\u9fc";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax23FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "'\\u9fc";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax24FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "\"\\g9fc";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax25FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "'\\g9fc";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax26FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "\"\\u9fcb";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax27FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "'\\u9fcb";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax28FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1)";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax29FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "(1";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax30FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1]";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax31FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "[1";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax32FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "v.";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax33FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$new java.lang.Integer";
        assertEq(21, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax34FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "new.java.lang.Integer(?java";
        assertEq(27, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax35FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "a,b";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax36FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "integer[?java";
        assertEq(13, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax37FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "integer\\n";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax38FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1 .0";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax39FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1. 0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax40FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$static(pkg$classname";
        assertEq(22, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax41FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".1.0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax42FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1e.0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax43FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1e";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax44FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0e.2";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax45FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0e5.2";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax46FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0.2";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax47FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1  .0";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax48FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0e5df";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax49FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0df";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax50FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1df";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax51FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.df";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax52FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.d.f";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax53FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1d.";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax54FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1df.";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax55FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0df";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax56FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0dgf";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax57FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0g";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax58FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "4g";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax59FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1ee2";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax60FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0e1g";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax61FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1f2";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax62FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".0e1g";
        assertEq(4, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax63FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".0e1ff";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax64FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "4.g";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax65FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".4g";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax66FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "4.ff";
        assertEq(3, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax67FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".4ff";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax68FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "4f1";
        assertEq(1, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax69FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ".4g";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax70FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1.0e4d.5";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax71FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1. .0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax72FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1..0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax73FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "1e .0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax74FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "2e 0";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax75FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "2e ";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax76FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = ". 1";
        assertEq(0, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax77FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$vararg";
        assertEq(7, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax78FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$vararg+4";
        assertEq(9, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax79FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "'\\u9";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax80FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "'\\u9'";
        assertEq(2, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax81FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math";
        assertEq(18, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax82FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math$$";
        assertEq(20, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax83FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice(";
        assertEq(12, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax84FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math$abs$";
        assertEq(23, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax85FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math$abs)";
        assertEq(22, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax86FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math$abs) ";
        assertEq(24, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax87FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice ";
        assertEq(12, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax88FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$that.";
        assertEq(5, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax89FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$this(";
        assertEq(6, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax90FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$that.call";
        assertEq(10, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax91FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$that.call$";
        assertEq(11, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax92FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$that.call$$";
        assertEq(12, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    @Test
    public void checkSyntax93FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_, false);
        String el_ = "$classchoice($math$abs$$abs;)";
        assertEq(28, ElResolver.checkSyntax(el_, conf_, 0).getBadOffset());
    }

    private static void addImportingPage(ContextEl _conf, boolean _rendering) {
        _conf.setAnalyzing(new AnalyzedPageEl());
    }

    private ContextEl contextEl() {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        return cont_;
    }
}
