package code.maths.litteral;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import code.util.StringMap;
import code.util.core.NumberUtil;
import org.junit.Test;

public class EvolvedMathFactoryTest extends EquallableMathUtil {

    @Test
    public void getFunctionsTest() {
        assertEq(1, NumberUtil.signum(EvolvedMathFactory.getFunctions().size()));
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        assertEq(1, NumberUtil.signum(EvolvedMathFactory.getFalseString().length()));
        assertEq(1, NumberUtil.signum(EvolvedMathFactory.getTrueString().length()));
        assertEq(1, NumberUtil.signum(EvolvedMathFactory.getSepartorSetChar()));
        assertTrue(e_.getMaxRandomNb().isZeroOrGt());
        assertTrue(!e_.getMaxRandomNb().isZero());
    }
    @Test
    public void evaluateDirectlyRateTest() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        assertEq(new Rate("0"), EvolvedMathFactory.evaluateDirectlyRate("0"));
    }
    @Test
    public void evaluateExpRate1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        EvolvedNumString n_ = EvolvedMathFactory.createNumericableString("0", new StringMap<String>());
        n_.evaluateExp(false);
        assertEq(new Rate("0"),n_.getResult());
        assertEq("0",n_.beforeEvaluated());
        assertEq(true,n_.isValid());
    }
    @Test
    public void evaluateExpRate2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put("VARIABLE","0");
        EvolvedNumString n_ = EvolvedMathFactory.createNumericableString("VARIABLE", vars_);
        n_.evaluateExp(false);
        assertEq(new Rate("0"),n_.getResult());
        assertEq("0",n_.beforeEvaluated());
        assertEq(true,n_.isValid());
    }
    @Test
    public void evaluateExpRate3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        EvolvedNumString n_ = EvolvedMathFactory.createNumericableString("++", vars_);
        n_.evaluateExp(false);
        assertEq(false,n_.isValid());
    }
    @Test
    public void evaluateDirectlyBooleanTest() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        assertEq(false, EvolvedMathFactory.evaluateDirectlyBoolean(MbOperationNode.FALSE_STRING));
    }
    @Test
    public void evaluateExpBoolean1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        EvolvedBooleanString n_ = EvolvedMathFactory.createBooleanString(MbOperationNode.FALSE_STRING, new StringMap<String>());
        n_.evaluateExp(false);
        assertEq(false,n_.isResult());
        assertEq(MbOperationNode.FALSE_STRING,n_.beforeEvaluated());
        assertEq(true,n_.isValid());
    }
    @Test
    public void evaluateExpBoolean2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put("VARIABLE",MbOperationNode.FALSE_STRING);
        EvolvedBooleanString n_ = EvolvedMathFactory.createBooleanString("VARIABLE", vars_);
        n_.evaluateExp(false);
        assertEq(false,n_.isResult());
        assertEq(MbOperationNode.FALSE_STRING,n_.beforeEvaluated());
        assertEq(true,n_.isValid());
    }
    @Test
    public void evaluateExpBoolean3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        EvolvedBooleanString n_ = EvolvedMathFactory.createBooleanString("++", vars_);
        n_.evaluateExp(false);
        assertEq(false,n_.isValid());
    }
    @Test
    public void evaluateNumericable1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("0"), EvolvedMathFactory.evaluateNumericable("0",vars_,new Rate("1")));
    }
    @Test
    public void evaluateNumericable2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("0"), EvolvedMathFactory.evaluateNumericable("++",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveExp1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"), EvolvedMathFactory.evaluatePositiveExp("0",vars_,new Rate("1")));
    }
    @Test
    public void evaluatePositiveExp2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"), EvolvedMathFactory.evaluatePositiveExp("-1",vars_,new Rate("1")));
    }
    @Test
    public void evaluatePositiveExp3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"), EvolvedMathFactory.evaluatePositiveExp("1",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveExp4Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"), EvolvedMathFactory.evaluatePositiveExp("++",vars_,new Rate("1")));
    }
    @Test
    public void evaluatePositiveOrZeroExp1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("0"), EvolvedMathFactory.evaluatePositiveOrZeroExp("-1",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveOrZeroExp2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"), EvolvedMathFactory.evaluatePositiveOrZeroExp("1",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveOrZeroExp3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"), EvolvedMathFactory.evaluatePositiveOrZeroExp("++",vars_,new Rate("1")));
    }
    @Test
    public void evaluateBoolean1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(false, EvolvedMathFactory.evaluateBoolean(MbOperationNode.FALSE_STRING,vars_,true));
    }
    @Test
    public void evaluateBoolean2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(true, EvolvedMathFactory.evaluateBoolean(MbOperationNode.TRUE_STRING,vars_,false));
    }
    @Test
    public void evaluateBoolean3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(true, EvolvedMathFactory.evaluateBoolean("++",vars_,true));
    }
}