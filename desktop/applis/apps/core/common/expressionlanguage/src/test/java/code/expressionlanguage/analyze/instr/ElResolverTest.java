package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.NumberInfos;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;


public final class ElResolverTest extends ProcessMethodCommon {

    @Test
    public void getOperationsSequence1Test() {
        OperationsSequence seq_ = test("abs(4,3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(3, opers_.size());
        assertEq(3, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(5, key(opers_, 1));
        assertEq(",", val(opers_, 1));
        assertEq(7, key(opers_, 2));
        assertEq(")", val(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(4, key(values_, 0));
        assertEq("4", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq("3", val(values_, 1));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence2Test() {
        OperationsSequence seq_ = test("abs(4,3).abs(4,3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(8, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)", val(values_, 0));
        assertEq(9, key(values_, 1));
        assertEq("abs(4,3)", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence3Test() {
        OperationsSequence seq_ = test("abs(4+3).abs(4,3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(8, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4+3)", val(values_, 0));
        assertEq(9, key(values_, 1));
        assertEq("abs(4,3)", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence4Test() {
        OperationsSequence seq_ = test("abs($vararg([$int),4+3).abs(4,3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(23, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs($vararg([$int),4+3)", val(values_, 0));
        assertEq(24, key(values_, 1));
        assertEq("abs(4,3)", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence5Test() {
        OperationsSequence seq_ = test("abs($vararg([$int),'[').abs(4,3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(23, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs($vararg([$int),'[')", val(values_, 0));
        assertEq(24, key(values_, 1));
        assertEq("abs(4,3)", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence6Test() {
        OperationsSequence seq_ = test("abs($vararg([$int),'[').abs(4,3)+8");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(32, key(opers_, 0));
        assertEq("+", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs($vararg([$int),'[').abs(4,3)", val(values_, 0));
        assertEq(33, key(values_, 1));
        assertEq("8", val(values_, 1));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence7Test() {
        OperationsSequence seq_ = test("6*(1+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(1+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence8Test() {
        OperationsSequence seq_ = test("6*('\\u9fcb'+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\u9fcb'+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence9Test() {
        OperationsSequence seq_ = test("6*('\\''+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\''+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence10Test() {
        OperationsSequence seq_ = test("6*(\"ab\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"ab\"+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence11Test() {
        OperationsSequence seq_ = test("-6*8");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("-6", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("8", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence12Test() {
        OperationsSequence seq_ = test("-abs(8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("abs(8)", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence13Test() {
        OperationsSequence seq_ = test("abs($vararg([$int),'[').abs(4,3)+8-9");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(34, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs($vararg([$int),'[').abs(4,3)+8", val(values_, 0));
        assertEq(35, key(values_, 1));
        assertEq("9", val(values_, 1));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence14Test() {
        OperationsSequence seq_ = test("1.8-abs(9)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(3, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.8", val(values_, 0));
        assertEq(4, key(values_, 1));
        assertEq("abs(9)", val(values_, 1));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence15Test() {
        OperationsSequence seq_ = test("1.8");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.8", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence16Test() {
        OperationsSequence seq_ = test("\"18\"");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("18", val(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence17Test() {
        OperationsSequence seq_ = test("18");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("18", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence18Test() {
        OperationsSequence seq_ = test("(4+3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(4, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("4+3", val(values_, 0));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence19Test() {
        OperationsSequence seq_ = test("$firstopt(4+3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(13, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("4+3", val(values_, 0));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence20Test() {
        OperationsSequence seq_ = test("abs($vararg([$int),4,3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq(3,key(opers_,0));
        assertEq("(",val(opers_,0));
        assertEq(18,key(opers_,1));
        assertEq(",",val(opers_,1));
        assertEq(20,key(opers_,2));
        assertEq(",",val(opers_,2));
        assertEq(22,key(opers_,3));
        assertEq(")",val(opers_,3));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq(4, key(values_, 0));
        assertEq("$vararg([$int)", val(values_, 0));
        assertEq(19, key(values_, 1));
        assertEq("4", val(values_, 1));
        assertEq(21, key(values_, 2));
        assertEq("3", val(values_, 2));
        assertTrue(seq_.isCall());
    }


    @Test
    public void getOperationsSequence22Test() {
        OperationsSequence seq_ = test("v;.");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence23Test() {
        OperationsSequence seq_ = test("v");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v", val(values_, 0));

        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence24Test() {
        OperationsSequence seq_ = test("abs(4,3)[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(8, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("[0]", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence25Test() {
        OperationsSequence seq_ = test("abs(4,3)[14][5]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(12, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[14]", val(values_, 0));
        assertEq(12, key(values_, 1));
        assertEq("[5]", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2411Test() {
        OperationsSequence seq_ = test("abs(4,3)[0,1]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(8, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("[0,1]", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2511Test() {
        OperationsSequence seq_ = test("abs(4,3)[14][5,0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(12, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[14]", val(values_, 0));
        assertEq(12, key(values_, 1));
        assertEq("[5,0]", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2512Test() {
        OperationsSequence seq_ = test("abs(4,3)[14,0][5]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(14, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[14,0]", val(values_, 0));
        assertEq(14, key(values_, 1));
        assertEq("[5]", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence2513Test() {
        OperationsSequence seq_ = test("abs(4,3)[14,0][5,1]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(14, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[14,0]", val(values_, 0));
        assertEq(14, key(values_, 1));
        assertEq("[5,1]", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence26Test() {
        OperationsSequence seq_ = test("6*(\"a b\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"a b\"+8)", val(values_, 1));

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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.BeanOne");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.BeanOne");
        AbsBk field_ = r_.getFirstChild();
        conf_.setCurrentBlock(field_);
        RootBlock rTwo_ = getAnaClassBody(conf_, "pkg.Composite");
        conf_.setCurrentBlock(rTwo_.getFirstChild());
        AbsBk b_ = field_.getNextSibling().getFirstChild();
        conf_.setCurrentBlock(b_);
        Line l_ = (Line) b_;
        String el_ = l_.getExpression();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("composite", val(values_, 0));
        assertEq(10, key(values_, 1));
        assertEq("integer", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence28Test() {
        OperationsSequence seq_ = test("var;.call()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence29Test() {
        OperationsSequence seq_ = test("var;.;call()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence32Test() {
        OperationsSequence seq_ = test("var;.call().call()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(11, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.call()", val(values_, 0));
        assertEq(12, key(values_, 1));
        assertEq("call()", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence33Test() {
        OperationsSequence seq_ = test("var;.;call().call()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(12, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.;call()", val(values_, 0));
        assertEq(13, key(values_, 1));
        assertEq("call()", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence34Test() {
        OperationsSequence seq_ = test("var;;call().call()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(11, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;;call()", val(values_, 0));
        assertEq(12, key(values_, 1));
        assertEq("call()", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence35Test() {
        OperationsSequence seq_ = test("var;call().call()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(10, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;call()", val(values_, 0));
        assertEq(11, key(values_, 1));
        assertEq("call()", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence36Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer(\"8\")");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(26, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(23, key(values_, 0));
        assertEq("\"8\"", val(values_, 0));

        assertTrue(seq_.isCall());
    }


    @Test
    public void getOperationsSequence36_Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer(\"8\"){}");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(26, key(opers_, 1));
        assertEq(")", val(opers_, 1));
    }

    @Test
    public void getOperationsSequence37Test() {
        OperationsSequence seq_ = test("call().$new java.lang.Integer(\"8\")");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("call()", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq("$new java.lang.Integer(\"8\")", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence38Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer(\"8\").intValue()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(27, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("$new java.lang.Integer(\"8\")", val(values_, 0));
        assertEq(28, key(values_, 1));
        assertEq("intValue()", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence39Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer[]{8i}");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(24, key(opers_, 0));
        assertEq("{", val(opers_, 0));
        assertEq(27, key(opers_, 1));
        assertEq("}", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(25, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertTrue(seq_.isInstance());
    }

    @Test
    public void getOperationsSequence40Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer(\"8\").intValue()+5");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(38, key(opers_, 0));
        assertEq("+", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("$new java.lang.Integer(\"8\").intValue()", val(values_, 0));
        assertEq(39, key(values_, 1));
        assertEq("5", val(values_, 1));

        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence41Test() {
        OperationsSequence seq_ = test("var;.[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.", val(values_, 0));
        assertEq(5, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence42Test() {
        OperationsSequence seq_ = test("var;.;[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.;", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence43Test() {
        OperationsSequence seq_ = test("var;;[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;;", val(values_, 0));
        assertEq(5, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence44Test() {
        OperationsSequence seq_ = test("var;[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(4, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;", val(values_, 0));
        assertEq(4, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence45Test() {
        OperationsSequence seq_ = test("var;.f[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.f", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence46Test() {
        OperationsSequence seq_ = test("var;.;f[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(7, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.;f", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence47Test() {
        OperationsSequence seq_ = test("var;;f[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;;f", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence48Test() {
        OperationsSequence seq_ = test("var;f[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;f", val(values_, 0));
        assertEq(5, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence49Test() {
        OperationsSequence seq_ = test("var;.f()[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(8, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.f()", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence50Test() {
        OperationsSequence seq_ = test("var;.;f()[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;.;f()", val(values_, 0));
        assertEq(9, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence51Test() {
        OperationsSequence seq_ = test("var;;f()[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(8, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;;f()", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("[0]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence52Test() {
        OperationsSequence seq_ = test("var;f()[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(7, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("var;f()", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq("[0]", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence53Test() {
        OperationsSequence seq_ = test("$static(pkg.classname).field");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("$static(pkg.classname)", val(values_, 0));
        assertEq(23, key(values_, 1));
        assertEq("field", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence54Test() {
        OperationsSequence seq_ = test("- -1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(" -1", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence55Test() {
        OperationsSequence seq_ = test("-1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("1", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence56Test() {
        OperationsSequence seq_ = test("-1.0");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("1.0", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence57Test() {
        OperationsSequence seq_ = test("1- -1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq(" -1", val(values_, 1));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence58Test() {
        OperationsSequence seq_ = test("!a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("!", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("a", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence59Test() {
        OperationsSequence seq_ = test("!!a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("!", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("!a", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence60Test() {
        OperationsSequence seq_ = test("b!=a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("!=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("b", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("a", val(values_, 1));

        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence61Test() {
        OperationsSequence seq_ = test("b<=a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("<=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("b", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("a", val(values_, 1));

        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence62Test() {
        OperationsSequence seq_ = test("b>=a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq(">=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("b", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("a", val(values_, 1));

        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence63Test() {
        OperationsSequence seq_ = test("b==a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("==", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("b", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("a", val(values_, 1));

        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence64Test() {
        OperationsSequence seq_ = test("\"\\\"string\"");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("\"string", val(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence65Test() {
        OperationsSequence seq_ = test("'\\''");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertSame(ConstType.CHARACTER, seq_.getConstType());
        assertEq(0, key(values_, 0));
        assertEq("'", val(values_, 0));

        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence66Test() {
        OperationsSequence seq_ = test("'\\\\'");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertSame(ConstType.CHARACTER, seq_.getConstType());
        assertEq(0, key(values_, 0));
        assertEq("\\", val(values_, 0));
    
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence67Test() {
        OperationsSequence seq_ = test("$firstopt(\"\\\"string\")");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(20, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("\"\\\"string\"", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence68Test() {
        OperationsSequence seq_ = test("$firstopt('\\'')");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(14, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("'\\''", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence69Test() {
        OperationsSequence seq_ = test("$firstopt('\\\\')");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(14, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("'\\\\'", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence70Test() {
        OperationsSequence seq_ = test("$firstopt(1.0)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(13, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("1.0", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence71Test() {
        OperationsSequence seq_ = test("abs($vararg(java.lang.Object),$firstopt(4),3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq(3,key(opers_,0));
        assertEq("(",val(opers_,0));
        assertEq(29,key(opers_,1));
        assertEq(",",val(opers_,1));
        assertEq(42,key(opers_,2));
        assertEq(",",val(opers_,2));
        assertEq(44,key(opers_,3));
        assertEq(")",val(opers_,3));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq(4, key(values_, 0));
        assertEq("$vararg(java.lang.Object)", val(values_, 0));
        assertEq(30, key(values_, 1));
        assertEq("$firstopt(4)", val(values_, 1));
        assertEq(43, key(values_, 2));
        assertEq("3", val(values_, 2));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence72Test() {
        OperationsSequence seq_ = test("abs($vararg(java.lang.Object),$firstopt(4;.;),3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq(3,key(opers_,0));
        assertEq("(",val(opers_,0));
        assertEq(29,key(opers_,1));
        assertEq(",",val(opers_,1));
        assertEq(45,key(opers_,2));
        assertEq(",",val(opers_,2));
        assertEq(47,key(opers_,3));
        assertEq(")",val(opers_,3));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq(4, key(values_, 0));
        assertEq("$vararg(java.lang.Object)", val(values_, 0));
        assertEq(30, key(values_, 1));
        assertEq("$firstopt(4;.;)", val(values_, 1));
        assertEq(46, key(values_, 2));
        assertEq("3", val(values_, 2));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence73Test() {
        OperationsSequence seq_ = test("abs($vararg(java.lang.Object),$firstopt(4;.),3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq(3,key(opers_,0));
        assertEq("(",val(opers_,0));
        assertEq(29,key(opers_,1));
        assertEq(",",val(opers_,1));
        assertEq(44,key(opers_,2));
        assertEq(",",val(opers_,2));
        assertEq(46,key(opers_,3));
        assertEq(")",val(opers_,3));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq(4, key(values_, 0));
        assertEq("$vararg(java.lang.Object)", val(values_, 0));
        assertEq(30, key(values_, 1));
        assertEq("$firstopt(4;.)", val(values_, 1));
        assertEq(45, key(values_, 2));
        assertEq("3", val(values_, 2));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence74Test() {
        OperationsSequence seq_ = test("$firstopt(v;.)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(13, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;.", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence75Test() {
        OperationsSequence seq_ = test("$firstopt(v;.;)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(14, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;.;", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence76Test() {
        OperationsSequence seq_ = test("$firstopt(v;)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(12, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence77Test() {
        OperationsSequence seq_ = test("$firstopt(v;;)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(13, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;;", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence78Test() {
        OperationsSequence seq_ = test("$firstopt(v;.t)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(14, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;.t", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence79Test() {
        OperationsSequence seq_ = test("$firstopt(v;.;t)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(15, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;.;t", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence80Test() {
        OperationsSequence seq_ = test("$firstopt(v;t)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(13, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;t", val(values_, 0));

        assertTrue(seq_.isCall());
    }



    @Test
    public void getOperationsSequence81Test() {
        OperationsSequence seq_ = test("$firstopt(v;;t)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(14, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(10, key(values_, 0));
        assertEq("v;;t", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence82Test() {
        OperationsSequence seq_ = test("-10");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("10", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence83Test() {
        OperationsSequence seq_ = test("-a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("a", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence84Test() {
        OperationsSequence seq_ = test("-1d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("1d", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence85Test() {
        OperationsSequence seq_ = test("-1.0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("1.0d", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence86Test() {
        OperationsSequence seq_ = test("a&&b!=c");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("&&", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("a", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("b!=c", val(values_, 1));
        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence90Test() {
        OperationsSequence seq_ = test("6*('\\u9Fcb'+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\u9Fcb'+8)", val(values_, 1));

        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence91Test() {
        OperationsSequence seq_ = test("6*(\"\\u9Fcb\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"\\u9Fcb\"+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence92Test() {
        OperationsSequence seq_ = test("6*('\\n'+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\n'+8)", val(values_, 1));

        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence93Test() {
        OperationsSequence seq_ = test("6*(\"\\n\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"\\n\"+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence94Test() {
        OperationsSequence seq_ = test("6*('\\r'+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\r'+8)", val(values_, 1));

        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence95Test() {
        OperationsSequence seq_ = test("6*(\"\\r\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"\\r\"+8)", val(values_, 1));

        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence96Test() {
        OperationsSequence seq_ = test("6*('\\b'+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\b'+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence97Test() {
        OperationsSequence seq_ = test("6*(\"\\b\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"\\b\"+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence98Test() {
        OperationsSequence seq_ = test("6*('\\t'+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\t'+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence99Test() {
        OperationsSequence seq_ = test("6*(\"\\t\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"\\t\"+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence100Test() {
        OperationsSequence seq_ = test("\"\\\"string\"+\"\\\"string\"");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(10, key(opers_, 0));
        assertEq("+", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("\"\\\"string\"", val(values_, 0));
        assertEq(11, key(values_, 1));
        assertEq("\"\\\"string\"", val(values_, 1));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence101Test() {
        OperationsSequence seq_ = test("\"\\\\\\\"string\"+\"\\\"string\"");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(12, key(opers_, 0));
        assertEq("+", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("\"\\\\\\\"string\"", val(values_, 0));
        assertEq(13, key(values_, 1));
        assertEq("\"\\\"string\"", val(values_, 1));

        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence102Test() {
        OperationsSequence seq_ = test("6*('\\f'+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("('\\f'+8)", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence103Test() {
        OperationsSequence seq_ = test("6*(\"\\f\"+8)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("6", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("(\"\\f\"+8)", val(values_, 1));

        assertEq(ElResolver.MULT_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence104Test() {
        OperationsSequence seq_ = test("!!field");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("!", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("!field", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence105Test() {
        OperationsSequence seq_ = test("!field!=anotherfield");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("!=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("!field", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("anotherfield", val(values_, 1));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence106Test() {
        OperationsSequence seq_ = test("field!=!anotherfield");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("!=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("field", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq("!anotherfield", val(values_, 1));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence107Test() {
        OperationsSequence seq_ = test("!field!=!anotherfield");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("!=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("!field", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("!anotherfield", val(values_, 1));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }


    @Test
    public void getOperationsSequence108Test() {
        OperationsSequence seq_ = test("v;.news.a()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(7, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v;.news", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("a()", val(values_, 1));

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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.BeanOne");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.BeanOne");
        AbsBk field_ = r_.getFirstChild();
        conf_.setCurrentBlock(field_);
        AbsBk b_ = field_.getNextSibling().getFirstChild();
        conf_.setCurrentBlock(b_);
        Line l_ = (Line) b_;
        String el_ = l_.getExpression();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(9, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("composite", val(values_, 0));
        assertEq(10, key(values_, 1));
        assertEq("getOverridenFour(0)", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }


    @Test
    public void getOperationsSequence110Test() {
        OperationsSequence seq_ = test("var;.f");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence111Test() {
        OperationsSequence seq_ = test("var;.;f");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence114Test() {
        OperationsSequence seq_ = test("+a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("+", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("a", val(values_, 0));

        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence115Test() {
        OperationsSequence seq_ = test("a||b");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("||", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("a", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("b", val(values_, 1));

        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence116Test() {
        OperationsSequence seq_ = test("a&&b");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("&&", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("a", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("b", val(values_, 1));

        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence117Test() {
        OperationsSequence seq_ = test("a||b&&c");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("||", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("a", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("b&&c", val(values_, 1));

        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence118Test() {
        OperationsSequence seq_ = test("a&&b||c");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(4, key(opers_, 0));
        assertEq("||", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("a&&b", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq("c", val(values_, 1));

        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence119Test() {
        OperationsSequence seq_ = test("!a||b");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("||", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("!a", val(values_, 0));
        assertEq(4, key(values_, 1));
        assertEq("b", val(values_, 1));

        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence120Test() {
        OperationsSequence seq_ = test("!a&&b");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("&&", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("!a", val(values_, 0));
        assertEq(4, key(values_, 1));
        assertEq("b", val(values_, 1));

        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence121Test() {
        OperationsSequence seq_ = test("!a||b&&c");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("||", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("!a", val(values_, 0));
        assertEq(4, key(values_, 1));
        assertEq("b&&c", val(values_, 1));

        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence122Test() {
        OperationsSequence seq_ = test("!a&&b||c");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("||", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("!a&&b", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq("c", val(values_, 1));

        assertEq(ElResolver.OR_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence123Test() {
        OperationsSequence seq_ = test("(a||b)&&c");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("&&", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("(a||b)", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("c", val(values_, 1));

        assertEq(ElResolver.AND_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence124Test() {
        OperationsSequence seq_ = test("(a|b)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(4, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("a|b", val(values_, 0));

        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence125Test() {
        OperationsSequence seq_ = test("v;.[0i].array[0i]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(13, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v;.[0i].array", val(values_, 0));
        assertEq(13, key(values_, 1));
        assertEq("[0i]", val(values_, 1));

        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence126Test() {
        OperationsSequence seq_ = test("var;.$new java.lang.Integer(\"8\")");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
    }

    @Test
    public void getOperationsSequence128Test() {
        OperationsSequence seq_ = test("1e2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1e2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence129Test() {
        OperationsSequence seq_ = test("1e-2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1e-2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence130Test() {
        OperationsSequence seq_ = test("1.0e2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.0e2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence131Test() {
        OperationsSequence seq_ = test("1.0e-2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.0e-2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence132Test() {
        OperationsSequence seq_ = test("1.e2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.e2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence133Test() {
        OperationsSequence seq_ = test("1.e-2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.e-2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence134Test() {
        OperationsSequence seq_ = test(".1e2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq(".1e2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence135Test() {
        OperationsSequence seq_ = test(".1e-2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq(".1e-2", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence136Test() {
        OperationsSequence seq_ = test(".1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq(".1", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence137Test() {
        OperationsSequence seq_ = test("-.1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence138Test() {
        OperationsSequence seq_ = test("-.1e2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1e2", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence139Test() {
        OperationsSequence seq_ = test("-.1e-2");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1e-2", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence140Test() {
        OperationsSequence seq_ = test("1.");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence141Test() {
        OperationsSequence seq_ = test("-.1e-2+.5");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("+", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("-.1e-2", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq(".5", val(values_, 1));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence142Test() {
        OperationsSequence seq_ = test("-.1e-2d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1e-2d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence143Test() {
        OperationsSequence seq_ = test("lbs(4,3)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(3, opers_.size());
        assertEq(3, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(5, key(opers_, 1));
        assertEq(",", val(opers_, 1));
        assertEq(7, key(opers_, 2));
        assertEq(")", val(opers_, 2));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(4, key(values_, 0));
        assertEq("4", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq("3", val(values_, 1));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence144Test() {
        OperationsSequence seq_ = test(" !a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("!", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(2, key(values_, 0));
        assertEq("a", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence145Test() {
        OperationsSequence seq_ = test("! a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("!", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(" a", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence146Test() {
        OperationsSequence seq_ = test("- - a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(" - a", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence147Test() {
        OperationsSequence seq_ = test("- -a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(" -a", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence148Test() {
        OperationsSequence seq_ = test(" - -a");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(2, key(values_, 0));
        assertEq(" -a", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence150Test() {
        OperationsSequence seq_ = test("-.1_0e-2d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1_0e-2d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence151Test() {
        OperationsSequence seq_ = test("-.1e-2_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1e-2_0d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence152Test() {
        OperationsSequence seq_ = test("-.1_0");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1_0", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence153Test() {
        OperationsSequence seq_ = test("-.1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1_0d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence154Test() {
        OperationsSequence seq_ = test("-.1e1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".1e1_0d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence155Test() {
        OperationsSequence seq_ = test("1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1_0d", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence156Test() {
        OperationsSequence seq_ = test(".1_0");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq(".1_0", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence157Test() {
        OperationsSequence seq_ = test(".1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq(".1_0d", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence158Test() {
        OperationsSequence seq_ = test("-1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("1_0d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence159Test() {
        OperationsSequence seq_ = test("1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1_0d", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence160Test() {
        OperationsSequence seq_ = test("1.d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.d", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
 
    @Test
    public void getOperationsSequence161Test() {
        OperationsSequence seq_ = test("-.2_0e1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq(".2_0e1_0d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence162Test() {
        OperationsSequence seq_ = test("-1.2_0e1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("1.2_0e1_0d", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence163Test() {
        OperationsSequence seq_ = test("1.1_0");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.1_0", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    
    @Test
    public void getOperationsSequence164Test() {
        OperationsSequence seq_ = test("1.1_0d");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1.1_0d", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence165Test() {
        OperationsSequence seq_ = test("$classchoice($math)abs()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(23, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(0, values_.size());
//        assertEq("$classchoice($math)abs", getVal(values_, 0));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence166Test() {
        OperationsSequence seq_ = test("v+=b");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("+=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("b", val(values_, 1));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence167Test() {
        OperationsSequence seq_ = test("v++");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("++", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v", val(values_, 0));
        assertEq(ElResolver.POST_INCR_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence168Test() {
        OperationsSequence seq_ = test("$classchoice($math)abs");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("$classchoice($math)abs", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence169Test() {
        OperationsSequence seq_ = test("$classchoice($math)abs$.field");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(23, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("$classchoice($math)abs$", val(values_, 0));
        assertEq(24, key(values_, 1));
        assertEq("field", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence170Test() {
        OperationsSequence seq_ = test("$this()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(6, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(0, values_.size());
//        assertEq("$this", getVal(values_, 0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence171Test() {
        OperationsSequence seq_ = test("$this ()");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(7, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(0, values_.size());
//        assertEq("$this ", getVal(values_, 0));
        assertTrue(seq_.isCall());
    }
    @Test
    public void getOperationsSequence172Test() {
        OperationsSequence seq_ = test("$this");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("$this", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence173Test() {
        OperationsSequence seq_ = test("$($int)1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("$($int)", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(7, key(values_, 0));
        assertEq("1", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence174Test() {
        OperationsSequence seq_ = test("$($int) 1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("$($int)", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(7, key(values_, 0));
        assertEq(" 1", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence175Test() {
        OperationsSequence seq_ = test(" $($int)1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("$($int)", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(8, key(values_, 0));
        assertEq("1", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence176Test() {
        OperationsSequence seq_ = test("-$($int)1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("$($int)1", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence177Test() {
        OperationsSequence seq_ = test("$($int)$($byte)1");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("$($int)", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(7, key(values_, 0));
        assertEq("$($byte)1", val(values_, 0));
        assertEq(ElResolver.UNARY_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence178Test() {
        OperationsSequence seq_ = test("1 $instanceof $byte");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof $byte", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence179Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp>");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg.List<two.Tmp>", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence180Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp,three.Sec>");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg.List<two.Tmp,three.Sec>", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence181Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp<three.Sec>>");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence182Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp<three.Sec>>==$true");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(42, key(opers_, 0));
        assertEq("==", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", val(values_, 0));
        assertEq(44, key(values_, 1));
        assertEq("$true", val(values_, 1));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence183Test() {
        OperationsSequence seq_ = test("v; $instanceof $byte");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(3, key(opers_, 0));
        assertEq("$instanceof $byte", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v; ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence184Test() {
        OperationsSequence seq_ = test("v $instanceof $byte");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof $byte", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence185Test() {
        OperationsSequence seq_ = test("v() $instanceof $byte");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(4, key(opers_, 0));
        assertEq("$instanceof $byte", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v() ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence186Test() {
        OperationsSequence seq_ = test("$true==1 $instanceof pkg.List<two.Tmp<three.Sec>>");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("==", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("$true", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>", val(values_, 1));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence187Test() {
        OperationsSequence seq_ = test("1 $instanceof #T");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof #T", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence188Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg . One");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg . One", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence189Test() {
        OperationsSequence seq_ = test("$interfaces(pkg.MyClass)(arg;.)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(24, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(30, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(25, key(values_, 0));
        assertEq("arg;.", val(values_, 0));
        assertTrue(seq_.isCall());
    }

    @Test
    public void getOperationsSequence190Test() {
        OperationsSequence seq_ = test("[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("[", val(opers_, 0));
        assertEq(2, key(opers_, 1));
        assertEq("]", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("0", val(values_, 0));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(seq_.isArray());
    }

    @Test
    public void getOperationsSequence191Test() {
        OperationsSequence seq_ = test("-.1e-2-.5");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("-", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("-.1e-2", val(values_, 0));
        assertEq(7, key(values_, 1));
        assertEq(".5", val(values_, 1));
        assertEq(ElResolver.ADD_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence192Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence193Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>==$true");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(56, key(opers_, 0));
        assertEq("==", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 $instanceof pkg.List<two.Tmp<three.Sec>>..Inner<other>", val(values_, 0));
        assertEq(58, key(values_, 1));
        assertEq("$true", val(values_, 1));
        assertEq(ElResolver.EQ_PRIO, seq_.getPriority());
    }
    @Test
    public void getOperationsSequence194Test() {
        OperationsSequence seq_ = test("v; $instanceof $byte[]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(3, key(opers_, 0));
        assertEq("$instanceof $byte[]", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("v; ", val(values_, 0));
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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.setCurrentBlock(b_);
        conf_.setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.setImporting(r_);
        conf_.setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("inst ", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq(" pkg.Ex.exmeth(0i)", val(values_, 1));
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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.setCurrentBlock(b_);
        conf_.setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.setImporting(r_);
        conf_.setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("inst ", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq(" Ex.exmeth(0i)", val(values_, 1));
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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.setCurrentBlock(b_);
        conf_.setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.setImporting(r_);
        conf_.setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("inst ", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq(" $Class.forName(\"\")", val(values_, 1));
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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.setCurrentBlock(b_);
        conf_.setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.setImporting(r_);
        conf_.setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("inst ", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq(" Ex.exmeth(0i)", val(values_, 1));
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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.setCurrentBlock(b_);
        conf_.setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.setImporting(r_);
        conf_.setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("inst ", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq(" pkg.ExThree.Ex.inst", val(values_, 1));
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
        AnalyzedPageEl conf_ = prepare(files_);

        setGlobalType(conf_, "pkg.ExTwo");
        RootBlock r_ = getAnaClassBody(conf_, "pkg.ExTwo");
        AbsBk b_ = r_.getFirstChild();
        conf_.setCurrentBlock(b_);
        conf_.setImportingAcces(new TypeAccessor("pkg.ExTwo"));
        conf_.setImporting(r_);
        conf_.setImportingTypes(r_);
        FieldBlock l_ = (FieldBlock) b_;
        String el_ = l_.getValue();
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(5, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("inst ", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq(" ExTwo.Ex.exmeth(0i)", val(values_, 1));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertEq("", seq_.getExtractType());
        assertEq(1, d_.getDelKeyWordStaticExtract().size());
        assertEq("pkg.ExTwo..Ex", d_.getDelKeyWordStaticExtract().first());
    }


    @Test
    public void getOperationsSequence201Test() {
        OperationsSequence seq_ = test("a<b>c");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("<", val(opers_, 0));
        assertEq(3, key(opers_, 1));
        assertEq(">", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(3, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("a", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("b", val(values_, 1));
        assertEq(4, key(values_, 2));
        assertEq("c", val(values_, 2));

        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(!seq_.isInstanceTest());
    }

    @Test
    public void getOperationsSequence202Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer[8i]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq("[", val(opers_, 0));
        assertEq(25, key(opers_, 1));
        assertEq("]", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(23, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence203Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer[8i][]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq("[", val(opers_, 0));
        assertEq(25, key(opers_, 1));
        assertEq("]", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(23, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertTrue(seq_.isInstance());
        assertEq(1, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence204Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer[8i][5i]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(4, opers_.size());
        assertEq(22,key(opers_,0));
        assertEq("[",val(opers_,0));
        assertEq(25,key(opers_,1));
        assertEq("]",val(opers_,1));
        assertEq(26,key(opers_,2));
        assertEq("[",val(opers_,2));
        assertEq(29,key(opers_,3));
        assertEq("]",val(opers_,3));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(23, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertEq(27, key(values_, 1));
        assertEq("5i", val(values_, 1));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence205Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer[8i][][]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq("[", val(opers_, 0));
        assertEq(25, key(opers_, 1));
        assertEq("]", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(23, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertTrue(seq_.isInstance());
        assertEq(2, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence206Test() {
        OperationsSequence seq_ = test("$new List<java.lang.Integer>[8i]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(28, key(opers_, 0));
        assertEq("[", val(opers_, 0));
        assertEq(31, key(opers_, 1));
        assertEq("]", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(29, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence207Test() {
        OperationsSequence seq_ = test("$new List<java.lang.Integer[]>[8i]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(30, key(opers_, 0));
        assertEq("[", val(opers_, 0));
        assertEq(33, key(opers_, 1));
        assertEq("]", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(31, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence208Test() {
        OperationsSequence seq_ = test("$new List<java.lang.Integer[]>[8i][]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(30, key(opers_, 0));
        assertEq("[", val(opers_, 0));
        assertEq(33, key(opers_, 1));
        assertEq("]", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(31, key(values_, 0));
        assertEq("8i", val(values_, 0));
        assertTrue(seq_.isInstance());
        assertEq(1, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence209Test() {
        OperationsSequence seq_ = test("$new java.lang.Integer(8i)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(22, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(25, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(23, key(values_,0));
        assertEq("8i", val(values_,0));
        assertTrue(seq_.isInstance());
        assertEq(0, seq_.getCountArrays());
    }

    @Test
    public void getOperationsSequence210Test() {
        AnalyzedPageEl conf_ = contextEl();

        String el_ = "composite.integer.int";
        Delimiters d_ = new Delimiters();
        d_.getAllowedOperatorsIndexes().add(new OperatorOffsetLength(9,1));
        d_.getAllowedOperatorsIndexes().add(new OperatorOffsetLength(17,1));
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
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(17, key(opers_, 0));
        assertEq(".", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("composite.integer", val(values_, 0));
        assertEq(18, key(values_, 1));
        assertEq("int", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence211Test() {
        OperationsSequence seq_ = test("integer=1=0");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(7, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("integer", val(values_, 0));
        assertEq(8, key(values_, 1));
        assertEq("1=0", val(values_, 1));
        assertEq(ElResolver.AFF_PRIO, seq_.getPriority());
        assertTrue(!seq_.isCallDbArray());
        assertEq(0, seq_.getCountArrays());
    }
    @Test
    public void getOperationsSequence212Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp>[]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg.List<two.Tmp>[]", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence213Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp>[ ]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg.List<two.Tmp>[ ]", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence214Test() {
        OperationsSequence seq_ = test("1 $instanceof $byte[ ]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof $byte[ ]", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence215Test() {
        OperationsSequence seq_ = test("1 $instanceof pkg.List<two.Tmp> []");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(2, key(opers_, 0));
        assertEq("$instanceof pkg.List<two.Tmp> []", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("1 ", val(values_, 0));
        assertEq(ElResolver.CMP_PRIO, seq_.getPriority());
        assertTrue(seq_.isInstanceTest());
    }
    @Test
    public void getOperationsSequence216Test() {
        OperationsSequence seq_ = test("abs(4,3)[0](1,2)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(11, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[0]", val(values_, 0));
        assertEq(11, key(values_, 1));
        assertEq("(1,2)", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence216___Test() {
        OperationsSequence seq_ = test("abs(4,3)[0](1)");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(11, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[0]", val(values_, 0));
        assertEq(11, key(values_, 1));
        assertEq("(1)", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence216_Test() {
        OperationsSequence seq_ = test("abs(4,3)[0]((1,2))");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(11, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[0]", val(values_, 0));
        assertEq(11, key(values_, 1));
        assertEq("((1,2))", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence217_Test() {
        OperationsSequence seq_ = test("abs(4,3)[0](-(1,2))");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(11, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[0]", val(values_, 0));
        assertEq(11, key(values_, 1));
        assertEq("(-(1,2))", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence218_Test() {
        OperationsSequence seq_ = test("((1))");
        StrTypes opers_ = seq_.getOperators();
        assertEq(2, opers_.size());
        assertEq(0, key(opers_, 0));
        assertEq("(", val(opers_, 0));
        assertEq(4, key(opers_, 1));
        assertEq(")", val(opers_, 1));
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(1, key(values_, 0));
        assertEq("(1)", val(values_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence219_Test() {
        OperationsSequence seq_ = test("(1)=5");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(3, key(opers_, 0));
        assertEq("=", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("(1)", val(values_, 0));
        assertEq(4, key(values_, 1));
        assertEq("5", val(values_, 1));
        assertEq(ElResolver.AFF_PRIO,seq_.getPriority());
    }
    @Test
    public void getOperationsSequence217Test() {
        OperationsSequence seq_ = test("abs(4,3)[0]{1}");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(11, key(opers_, 0));
        assertEq("", val(opers_, 0));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("abs(4,3)[0]", val(values_, 0));
        assertEq(11, key(values_, 1));
        assertEq("{1}", val(values_, 1));
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence218Test() {
        AnalyzedPageEl conf_ = contextEl();

        String el_ = "0x1";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("0x1", val(values_, 0));
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
        AnalyzedPageEl conf_ = contextEl();

        String el_ = "0x1f";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("0x1f", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('i', ni_.get(0).getSuffix());
        assertEq("1F", ni_.get(0).getIntPart().toString());
        assertEq("", ni_.get(0).getDecimalPart().toString());
        assertEq("", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence220Test() {
        AnalyzedPageEl conf_ = contextEl();

        String el_ = "0x1p0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("0x1p0", val(values_, 0));
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
        AnalyzedPageEl conf_ = contextEl();

        String el_ = "0x1fp0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("0x1fp0", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('d', ni_.get(0).getSuffix());
        assertEq("1F", ni_.get(0).getIntPart().toString());
        assertEq("", ni_.get(0).getDecimalPart().toString());
        assertEq("0", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence222Test() {
        AnalyzedPageEl conf_ = contextEl();

        String el_ = "0x1.2p0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("0x1.2p0", val(values_, 0));
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
        AnalyzedPageEl conf_ = contextEl();

        String el_ = "0x1f.2p0";
        Delimiters d_ = checkSyntax(conf_, el_);
        OperationsSequence seq_ = getOperationsSequence(conf_, el_, d_);
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("0x1f.2p0", val(values_, 0));
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
        CustList<NumberInfos> ni_ = d_.getNbInfos();
        assertEq(1, ni_.size());
        assertEq(16, ni_.get(0).getBase());
        assertEq('d', ni_.get(0).getSuffix());
        assertEq("1F", ni_.get(0).getIntPart().toString());
        assertEq("2", ni_.get(0).getDecimalPart().toString());
        assertEq("0", ni_.get(0).getExponentialPart().toString());
    }

    @Test
    public void getOperationsSequence224Test() {
        OperationsSequence seq_ = test("`18`");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("18", val(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence225Test() {
        OperationsSequence seq_ = test("`18``36`");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("18`36", val(values_, 0));
        assertSame(ConstType.STRING, seq_.getConstType());
        assertEq(ElResolver.CONST_PRIO, seq_.getPriority());
    }

    @Test
    public void getOperationsSequence226Test() {
        OperationsSequence seq_ = test("tab[0]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(3, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("tab", val(values_, 0));
        assertEq(3, key(values_, 1));
        assertEq("[0]", val(values_, 1));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(!seq_.isArray());
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence227Test() {
        OperationsSequence seq_ = test("tab[0][1]");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(6, key(opers_, 0));
        assertEq("", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("tab[0]", val(values_, 0));
        assertEq(6, key(values_, 1));
        assertEq("[1]", val(values_, 1));
        assertTrue(!seq_.isCallDbArray());
        assertTrue(!seq_.isArray());
        assertEq(ElResolver.FCT_OPER_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence228Test() {
        OperationsSequence seq_ = test("3*");
        StrTypes opers_ = seq_.getOperators();
        assertEq(1, opers_.size());
        assertEq(1, key(opers_, 0));
        assertEq("*", val(opers_, 0));
        StrTypes values_ = seq_.getValues();
        assertEq(2, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("3", val(values_, 0));
        assertEq(2, key(values_, 1));
        assertEq("", val(values_, 1));
        assertEq(ElResolver.MULT_PRIO,seq_.getPriority());
    }

    @Test
    public void getOperationsSequence229Test() {
        OperationsSequence seq_ = test("4. ");
        StrTypes opers_ = seq_.getOperators();
        assertEq(0, opers_.size());
        StrTypes values_ = seq_.getValues();
        assertEq(1, values_.size());
        assertEq(0, key(values_, 0));
        assertEq("4. ", val(values_, 0));
    }

    @Test
    public void checkSyntax231FailTest() {
        Delimiters d_ = tst("[");
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax232FailTest() {
        Delimiters d_ = tst("++");
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax233FailTest() {
        Delimiters d_ = tst("`");
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax234FailTest() {
        Delimiters d_ = tst("\"\n\"");
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax235FailTest() {
        Delimiters d_ = tst("'\t'");
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax236FailTest() {
        Delimiters d_ = tst("\"\r\"");
        assertEq(-1, d_.getBadOffset());
    }
    @Test
    public void checkSyntax237FailTest() {
        Delimiters d_ = tst("--");
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax238FailTest() {
        Delimiters d_ = tst("-");
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax239FailTest() {
        Delimiters d_ = tst("+");
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }

    @Test
    public void checkSyntax240FailTest() {
        Delimiters d_ = tst("1+$)");
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax241FailTest() {
        Delimiters d_ = tst("1+$(");
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax242FailTest() {
        Delimiters d_ = tst("1+$()");
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax243FailTest() {
        Delimiters d_ = tst("(}");
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax244FailTest() {
        Delimiters d_ = tst("}");
        assertEq(0, d_.getBadOffset());
    }

    @Test
    public void checkSyntax247FailTest() {
        Delimiters d_ = tst("([v )");
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax248FailTest() {
        Delimiters d_ = tst(" ");
        assertEq(1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax249FailTest() {
        Delimiters d_ = tst("$)");
        assertEq(2, d_.getBadOffset());
    }

    @Test
    public void checkSyntax250FailTest() {
        Delimiters d_ = tst("$(");
        assertEq(2, d_.getBadOffset());
    }

    @Test
    public void checkSyntax251FailTest() {
        Delimiters d_ = tst("$()");
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax252FailTest() {
        Delimiters d_ = tst("$vararg(");
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax253FailTest() {
        Delimiters d_ = tst("$class)");
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax254FailTest() {
        Delimiters d_ = tst("$class(");
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax255FailTest() {
        Delimiters d_ = tst("$instanceof(");
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax256FailTest() {
        Delimiters d_ = tst("$id)");
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax257FailTest() {
        Delimiters d_ = tst("$id(");
        assertEq(4, d_.getBadOffset());
    }

    @Test
    public void checkSyntax258FailTest() {
        Delimiters d_ = tst("$lambda)");
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax259FailTest() {
        Delimiters d_ = tst("$lambda(");
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax260FailTest() {
        Delimiters d_ = tst("$static(");
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax261FailTest() {
        Delimiters d_ = tst("$static() ");
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax262FailTest() {
        Delimiters d_ = tst("$super");
        assertEq(6, d_.getBadOffset());
    }

    @Test
    public void checkSyntax263FailTest() {
        Delimiters d_ = tst("$super,");
        assertEq(6, d_.getBadOffset());
    }

    @Test
    public void checkSyntax264FailTest() {
        Delimiters d_ = tst("$thisaccess(MyClass)method,");
        assertEq(26, d_.getBadOffset());
    }

    @Test
    public void checkSyntax265FailTest() {
        Delimiters d_ = tst("$thisaccess(MyClass)method");
        assertEq(26, d_.getBadOffset());
    }

    @Test
    public void checkSyntax266FailTest() {
        Delimiters d_ = tst("$thisaccess(MyClass)");
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax267FailTest() {
        Delimiters d_ = tst("$thisaccess(MyClass");
        assertEq(18, d_.getBadOffset());
    }

    @Test
    public void checkSyntax268FailTest() {
        Delimiters d_ = tst("$thisaccess(");
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax269FailTest() {
        Delimiters d_ = tst("$thisaccess,");
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax270FailTest() {
        Delimiters d_ = tst("$thisaccess");
        assertEq(10, d_.getBadOffset());
    }

    @Test
    public void checkSyntax271FailTest() {
        Delimiters d_ = tst("$that,");
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax272FailTest() {
        Delimiters d_ = tst("$that");
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax273FailTest() {
        Delimiters d_ = tst("$that.method");
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax274FailTest() {
        Delimiters d_ = tst("$that.method,");
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax275FailTest() {
        Delimiters d_ = tst("$thisaccess(MyClass) ");
        assertEq(21, d_.getBadOffset());
    }

    @Test
    public void checkSyntax276FailTest() {
        Delimiters d_ = tst("$superaccess(MyClass)method,");
        assertEq(27, d_.getBadOffset());
    }

    @Test
    public void checkSyntax278FailTest() {
        Delimiters d_ = tst("$superaccess(MyClass)");
        assertEq(21, d_.getBadOffset());
    }

    @Test
    public void checkSyntax279FailTest() {
        Delimiters d_ = tst("$superaccess(MyClass");
        assertEq(19, d_.getBadOffset());
    }

    @Test
    public void checkSyntax280FailTest() {
        Delimiters d_ = tst("$superaccess(");
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax281FailTest() {
        Delimiters d_ = tst("$superaccess,");
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax282FailTest() {
        Delimiters d_ = tst("$superaccess");
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax283FailTest() {
        Delimiters d_ = tst("$superaccess(MyClass) ");
        assertEq(22, d_.getBadOffset());
    }

    @Test
    public void checkSyntax284FailTest() {
        Delimiters d_ = tst("$interfaces(MyClass),");
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax285FailTest() {
        Delimiters d_ = tst("$interfaces(MyClass) ");
        assertEq(21, d_.getBadOffset());
    }

    @Test
    public void checkSyntax286FailTest() {
        Delimiters d_ = tst("$interfaces(MyClass)");
        assertEq(20, d_.getBadOffset());
    }

    @Test
    public void checkSyntax287FailTest() {
        Delimiters d_ = tst("$interfaces(MyClass");
        assertEq(18, d_.getBadOffset());
    }

    @Test
    public void checkSyntax288FailTest() {
        Delimiters d_ = tst("$interfaces( ");
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax289FailTest() {
        Delimiters d_ = tst("$interfaces,");
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax290FailTest() {
        Delimiters d_ = tst("$interfaces");
        assertEq(10, d_.getBadOffset());
    }

    @Test
    public void checkSyntax291FailTest() {
        Delimiters d_ = tst("$interfaces(");
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax292FailTest() {
        Delimiters d_ = tst("$classchoice,");
        assertEq(12, d_.getBadOffset());
    }

    @Test
    public void checkSyntax293FailTest() {
        Delimiters d_ = tst("$static()  ");
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax294FailTest() {
        Delimiters d_ = tst("$bool");
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax295FailTest() {
        Delimiters d_ = tst("$bool ean()");
        assertEq(5, d_.getBadOffset());
    }

    @Test
    public void checkSyntax296FailTest() {
        Delimiters d_ = tst("$defaultValue)");
        assertEq(14, d_.getBadOffset());
    }

    @Test
    public void checkSyntax297FailTest() {
        Delimiters d_ = tst("$defaultValue(");
        assertEq(14, d_.getBadOffset());
    }

    @Test
    public void checkSyntax298FailTest() {
        Delimiters d_ = tst("1+explicit(");
        assertEq(11, d_.getBadOffset());
    }

    @Test
    public void checkSyntax299FailTest() {
        Delimiters d_ = tst("1+explicit()");
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax300FailTest() {
        Delimiters d_ = tst("1+explicit)");
        assertEq(11, d_.getBadOffset());
    }
    @Test
    public void checkSyntax301FailTest() {
        Delimiters d_ = tst("explicit(");
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax302FailTest() {
        Delimiters d_ = tst("explicit()");
        assertEq(-1, d_.getBadOffset());
    }

    @Test
    public void checkSyntax303FailTest() {
        Delimiters d_ = tst("explicit)");
        assertEq(9, d_.getBadOffset());
    }

    @Test
    public void checkSyntax304FailTest() {
        Delimiters d_ = tst("$values(MyClass");
        assertEq(15, d_.getBadOffset());
    }

    @Test
    public void checkSyntax305FailTest() {
        Delimiters d_ = tst("$values");
        assertEq(7, d_.getBadOffset());
    }

    @Test
    public void checkSyntax306FailTest() {
        Delimiters d_ = tst("$valueOf");
        assertEq(8, d_.getBadOffset());
    }

    @Test
    public void checkSyntax307FailTest() {
        Delimiters d_ = tst("$valueOf(");
        assertEq(8, d_.getBadOffset());
    }
    @Test
    public void checkSyntax1Test() {
        Delimiters d_ = tst("1==0");
        assertEq(1, d_.getAllowedOperatorsIndexes().size());
        assertEq(1, d_.getAllowedOperatorsIndexes().first().getOffset());
    }
    @Test
    public void checkSyntax2Test() {
        Delimiters d_ = tst("1 ");
        assertEq(0, d_.getAllowedOperatorsIndexes().size());
    }
    @Test
    public void checkSyntaxDelimiters1Test() {
        Delimiters d_ = del("{6*('\\u9fcb'+8)}", 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters2Test() {
        Delimiters d_ = del("{6*('\\u9fcb'+8)}", 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(14, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters3Test() {
        Delimiters d_ = del("{6*('`'+8)}", 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(6, d_.getDelStringsChars().last());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters4Test() {
        Delimiters d_ = del("{6*('}'+8)}", 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(6, d_.getDelStringsChars().last());
        assertEq(9, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters5Test() {
        Delimiters d_ = del(" {6*('\\u9fcb'+8)}", 2);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first());
        assertEq(12, d_.getDelStringsChars().last());
        assertEq(15, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters6Test() {
        Delimiters d_ = del(" {6*(\"//\"+8)}", 2);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(5, d_.getDelStringsChars().first());
        assertEq(8, d_.getDelStringsChars().last());
        assertEq(11, d_.getIndexEnd());
    }

    @Test
    public void checkSyntaxDelimiters7Test() {
        Delimiters d_ = del(" {$new $int[]{1i,3i}}", 2);
        assertEq(3, d_.getAllowedOperatorsIndexes().size());
        assertEq(13, d_.getAllowedOperatorsIndexes().first().getOffset());
        assertEq(16, d_.getAllowedOperatorsIndexes().get(1).getOffset());
        assertEq(19, d_.getAllowedOperatorsIndexes().last().getOffset());
        assertEq(19, d_.getIndexEnd());
    }

    private Delimiters del(String _el, int _minIndex) {
        AnalyzedPageEl conf_ = contextEl();

        return checkSyntaxDelimiters(conf_, _el, _minIndex);
    }

    @Test
    public void checkSyntaxDelimiters1FailTest() {
        int badOffset_ = getBadOffsetDel("{6*('\\u9fcb'+8)");
        assertEq(15, badOffset_);
    }
    @Test
    public void checkSyntaxDelimiters4FailTest() {
        int badOffset_ = getBadOffsetDel("{6*('\\u9fcb'+8\\");
        assertEq(15, badOffset_);
    }
    @Test
    public void checkSyntaxDelimiters5FailTest() {
        int badOffset_ = getBadOffsetDel("{6*('\\u9fcb'+8\\ ");
        assertEq(16, badOffset_);
    }
    @Test
    public void checkSyntaxDelimiters8Test() {
        Delimiters d_ = del("{6*(`string`+8)}", 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(11, d_.getDelStringsChars().last());
        assertEq(14, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters9Test() {
        Delimiters d_ = del("{6*(`string``after`+8)}", 1);
        assertEq(2, d_.getDelStringsChars().size());
        assertEq(4, d_.getDelStringsChars().first());
        assertEq(18, d_.getDelStringsChars().last());
        assertEq(21, d_.getIndexEnd());
    }
    @Test
    public void checkSyntaxDelimiters2FailTest() {
        int badOffset_ = getBadOffsetDel("{6*('\\u9fcb'+8){");
        assertEq(16, badOffset_);
    }

    @Test
    public void checkSyntaxDelimiters3FailTest() {
        int badOffset_ = getBadOffsetDel("{6*('\\u9gcb'+8)}");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax1FailTest() {
        int badOffset_ = getBadOffset("6*('\\u9gcb'+8)");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax2FailTest() {
        int badOffset_ = getBadOffset("6*('\\g'+8)");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax3FailTest() {
        int badOffset_ = getBadOffset("6*('ab'+8)");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax4FailTest() {
        int badOffset_ = getBadOffset("6*('a'+[8)]");
        assertEq(9, badOffset_);
    }

    @Test
    public void checkSyntax5FailTest() {
        int badOffset_ = getBadOffset("6*['a'+(8])");
        assertEq(9, badOffset_);
    }

    @Test
    public void checkSyntax7FailTest() {
        int badOffset_ = getBadOffset("6*(\"t\\u98\"+[8])");
        assertEq(15, badOffset_);
    }

    @Test
    public void checkSyntax8FailTest() {
        int badOffset_ = getBadOffset("6*(\"t\\u98 \"+[8])");
        assertEq(16, badOffset_);
    }

    @Test
    public void checkSyntax10FailTest() {
        int badOffset_ = getBadOffset("$static.a");
        assertEq(7, badOffset_);
    }

    @Test
    public void checkSyntax12FailTest() {
        int badOffset_ = getBadOffset("1< =2");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax13FailTest() {
        int badOffset_ = getBadOffset("1> =2");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax14FailTest() {
        int badOffset_ = getBadOffset("1! =2");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax15FailTest() {
        int badOffset_ = getBadOffset("v '");
        assertEq(3, badOffset_);
    }

    @Test
    public void checkSyntax16FailTest() {
        int badOffset_ = getBadOffset("v; .");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax17FailTest() {
        int badOffset_ = getBadOffset("v;. ;");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax18FailTest() {
        int badOffset_ = getBadOffset("v; ;");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax19FailTest() {
        int badOffset_ = getBadOffset("v;  ;");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax20FailTest() {
        int badOffset_ = getBadOffset("'\\");
        assertEq(2, badOffset_);
    }

    @Test
    public void checkSyntax20_FailTest() {
        int badOffset_ = getBadOffset("'''\\");
        assertEq(4, badOffset_);
    }

    @Test
    public void checkSyntax21FailTest() {
        int badOffset_ = getBadOffset("\"\\");
        assertEq(2, badOffset_);
    }

    @Test
    public void checkSyntax21_FailTest() {
        int badOffset_ = getBadOffset("\"\"\"\\");
        assertEq(4, badOffset_);
    }

    @Test
    public void checkSyntax22FailTest() {
        int badOffset_ = getBadOffset("\"\\u9fc");
        assertEq(6, badOffset_);
    }

    @Test
    public void checkSyntax22_FailTest() {
        int badOffset_ = getBadOffset("\"\"\"\\u9fc");
        assertEq(8, badOffset_);
    }

    @Test
    public void checkSyntax23FailTest() {
        int badOffset_ = getBadOffset("'\\u9fc");
        assertEq(6, badOffset_);
    }

    @Test
    public void checkSyntax23_FailTest() {
        int badOffset_ = getBadOffset("'''\\u9fc");
        assertEq(8, badOffset_);
    }

    @Test
    public void checkSyntax24FailTest() {
        int badOffset_ = getBadOffset("\"\\g9fc");
        assertEq(6, badOffset_);
    }

    @Test
    public void checkSyntax25FailTest() {
        int badOffset_ = getBadOffset("'\\g9fc");
        assertEq(6, badOffset_);
    }

    @Test
    public void checkSyntax26FailTest() {
        int badOffset_ = getBadOffset("\"\\u9fcb");
        assertEq(7, badOffset_);
    }

    @Test
    public void checkSyntax27FailTest() {
        int badOffset_ = getBadOffset("'\\u9fcb");
        assertEq(7, badOffset_);
    }

    @Test
    public void checkSyntax28FailTest() {
        int badOffset_ = getBadOffset("1)");
        assertEq(1, badOffset_);
    }

    @Test
    public void checkSyntax29FailTest() {
        int badOffset_ = getBadOffset("(1");
        assertEq(2, badOffset_);
    }

    @Test
    public void checkSyntax30FailTest() {
        int badOffset_ = getBadOffset("1]");
        assertEq(1, badOffset_);
    }

    @Test
    public void checkSyntax31FailTest() {
        int badOffset_ = getBadOffset("[1");
        assertEq(2, badOffset_);
    }

    @Test
    public void checkSyntax33FailTest() {
        int badOffset_ = getBadOffset("$new java.lang.Integer");
        assertEq(21, badOffset_);
    }

    @Test
    public void checkSyntax34FailTest() {
        int badOffset_ = getBadOffset("$new java.lang.Integer(?java");
        assertEq(28, badOffset_);
    }

    @Test
    public void checkSyntax35FailTest() {
        int badOffset_ = getBadOffset("a,b");
        assertEq(1, badOffset_);
    }

    @Test
    public void checkSyntax36FailTest() {
        int badOffset_ = getBadOffset("integer[?java");
        assertEq(13, badOffset_);
    }

    @Test
    public void checkSyntax40FailTest() {
        int badOffset_ = getBadOffset("$static(pkg$classname");
        assertEq(22, badOffset_);
    }


    @Test
    public void checkSyntax77FailTest() {
        int badOffset_ = getBadOffset("$vararg");
        assertEq(7, badOffset_);
    }

    @Test
    public void checkSyntax78FailTest() {
        int badOffset_ = getBadOffset("$vararg+4");
        assertEq(9, badOffset_);
    }

    @Test
    public void checkSyntax79FailTest() {
        int badOffset_ = getBadOffset("'\\u9");
        assertEq(4, badOffset_);
    }

    @Test
    public void checkSyntax80FailTest() {
        int badOffset_ = getBadOffset("'\\u9'");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax81FailTest() {
        int badOffset_ = getBadOffset("$classchoice($math");
        assertEq(17, badOffset_);
    }

    @Test
    public void checkSyntax82FailTest() {
        int badOffset_ = getBadOffset("$classchoice($math$$");
        assertEq(19, badOffset_);
    }

    @Test
    public void checkSyntax83FailTest() {
        int badOffset_ = getBadOffset("$classchoice(");
        assertEq(12, badOffset_);
    }

    @Test
    public void checkSyntax84FailTest() {
        int badOffset_ = getBadOffset("$classchoice($math$abs$");
        assertEq(22, badOffset_);
    }

    @Test
    public void checkSyntax85FailTest() {
        int badOffset_ = getBadOffset("$classchoice($math$abs)");
        assertEq(23, badOffset_);
    }

    @Test
    public void checkSyntax86FailTest() {
        int badOffset_ = getBadOffset("$classchoice($math$abs) ");
        assertEq(24, badOffset_);
    }

    @Test
    public void checkSyntax87FailTest() {
        int badOffset_ = getBadOffset("$classchoice ");
        assertEq(12, badOffset_);
    }

    @Test
    public void checkSyntax88FailTest() {
        int badOffset_ = getBadOffset("$that.");
        assertEq(6, badOffset_);
    }

    @Test
    public void checkSyntax89FailTest() {
        int badOffset_ = getBadOffset("$this(");
        assertEq(6, badOffset_);
    }

    @Test
    public void checkSyntax90FailTest() {
        int badOffset_ = getBadOffset("$that.call");
        assertEq(10, badOffset_);
    }

    @Test
    public void checkSyntax91FailTest() {
        int badOffset_ = getBadOffset("$that.call$");
        assertEq(11, badOffset_);
    }

    @Test
    public void checkSyntax92FailTest() {
        int badOffset_ = getBadOffset("$that.call$$");
        assertEq(12, badOffset_);
    }

    @Test
    public void checkSyntax93FailTest() {
        int badOffset_ = getBadOffset("$classchoice($math$abs$$abs;)");
        assertEq(29, badOffset_);
    }
    @Test
    public void checkSyntax94FailTest() {
        Delimiters d_ = tst("_1");
        assertEq(0, d_.getDelNumbers().size());
    }

    @Test
    public void checkSyntax95FailTest() {
        int badOffset_ = getBadOffset("1e1 ");
        assertEq(-1, badOffset_);
    }

    @Test
    public void checkSyntax96FailTest() {
        int badOffset_ = getBadOffset("$new $interfaces ");
        assertEq(5, badOffset_);
    }

    @Test
    public void checkSyntax97FailTest() {
        int badOffset_ = getBadOffset("$new $interfaces( ");
        assertEq(5, badOffset_);
    }

    private int getBadOffsetDel(String _el) {
        AnalyzedPageEl conf_ = contextEl();

        return checkSyntaxDelimiters(conf_, _el, 1).getBadOffset();
    }

    private int getBadOffset(String _el) {
        AnalyzedPageEl conf_ = contextEl();

        return checkSyntax(conf_, _el).getBadOffset();
    }

    private Delimiters tst(String _el) {
        AnalyzedPageEl conf_ = contextEl();

        return checkSyntax(conf_, _el);
    }

    private OperationsSequence test(String _el) {
        AnalyzedPageEl conf_ = contextEl();

        Delimiters d_ = checkSyntax(conf_, _el);
        return getOperationsSequence(conf_, _el, d_);
    }

    private static AnalyzedPageEl prepare(StringMap<String> _files) {
        AnalyzedPageEl conf_ = contextEl();
        parseCustomFiles(_files, conf_);
        validateInheritingClasses(conf_);
        validateIds(conf_);
        validateOverridingInherit(conf_);
        return conf_;
    }

    private static OperationsSequence getOperationsSequence(AnalyzedPageEl _conf, String _el, Delimiters _d) {
        return ElResolver.getOperationsSequence(0, _el, _d, _conf,null);
    }
    private static AnalyzedPageEl contextEl() {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_,IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        getForwards(opt_,lgName_,kw_,page_);
        return page_;
    }

    private static Delimiters checkSyntax(AnalyzedPageEl _conf, String _el) {
        ResultExpression res_ = new ResultExpression();
        StringComment str_ = new StringComment(_el,new CustList<CommentDelimiters>());
        res_.partsAbsol(str_.getStringParts());
        res_.setAnalyzedString(_el);
        _conf.setSumOffset(0);
        _conf.zeroOffset();
        ElRetrieverAnonymous.commonCheckQuick(0, _conf,res_);
        _conf.setCurrentParts(res_.getParts());
        _conf.setCurrentNumbers(res_.getNumbers());
        return ElResolver.checkSyntax(res_, 0, _conf);
    }

    private static Delimiters checkSyntaxDelimiters(AnalyzedPageEl _conf, String _el, int _minIndex) {
        ResultExpression res_ = new ResultExpression();
        StringComment str_ = new StringComment(_el,new CustList<CommentDelimiters>());
        res_.partsAbsol(str_.getStringParts());
        res_.setAnalyzedString(_el);
        _conf.setSumOffset(0);
        _conf.zeroOffset();
        ElRetrieverAnonymous.commonCheckQuick(_minIndex, _conf,res_);
        _conf.setCurrentParts(res_.getParts());
        _conf.setCurrentNumbers(res_.getNumbers());
        return ElResolver.checkSyntaxDelimiters(res_, _minIndex, _conf);
    }

    private static void setGlobalType(AnalyzedPageEl _conf, String _globalClass) {
        _conf.setGlobalType(new AnaFormattedRootBlock(_conf,_globalClass));
    }

    private static RootBlock getAnaClassBody(AnalyzedPageEl _classes, String _className) {
        return _classes.getAnaClassBody(StringExpUtil.getIdFromAllTypes(_className));
    }

    private static int key(StrTypes _opers, int _i) {
        return _opers.getKey(_i);
    }
    private static String val(StrTypes _opers, int _i) {
        return _opers.getValue(_i);
    }

}
