package code.maths.litteral;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import code.util.StringMap;
import org.junit.Test;

public class EvolvedMathFactoryTest extends EquallableMathUtil {

    @Test
    public void getFunctionsTest() {
        assertTrue(EvolvedMathFactory.getFunctions().size() > 0);
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        assertTrue(e_.getFalseString().length() > 0);
        assertTrue(e_.getTrueString().length() > 0);
        assertTrue(e_.getSepartorSetChar() > 0);
        assertTrue(e_.getMaxRandomNb().isZeroOrGt());
        assertTrue(!e_.getMaxRandomNb().isZero());
    }
    @Test
    public void evaluateDirectlyRateTest() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        assertEq(new Rate("0"), e_.evaluateDirectlyRate("0"));
    }
    @Test
    public void evaluateExpRate1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        EvolvedNumString n_ = e_.createNumericableString("0", new StringMap<String>());
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
        EvolvedNumString n_ = e_.createNumericableString("VARIABLE", vars_);
        n_.evaluateExp(false);
        assertEq(new Rate("0"),n_.getResult());
        assertEq("0",n_.beforeEvaluated());
        assertEq(true,n_.isValid());
    }
    @Test
    public void evaluateExpRate3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        EvolvedNumString n_ = e_.createNumericableString("++", vars_);
        n_.evaluateExp(false);
        assertEq(false,n_.isValid());
    }
    @Test
    public void evaluateDirectlyBooleanTest() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        assertEq(false,e_.evaluateDirectlyBoolean("F"));
    }
    @Test
    public void evaluateExpBoolean1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        EvolvedBooleanString n_ = e_.createBooleanString("F", new StringMap<String>());
        n_.evaluateExp(false);
        assertEq(false,n_.getResult());
        assertEq("F",n_.beforeEvaluated());
        assertEq(true,n_.isValid());
    }
    @Test
    public void evaluateExpBoolean2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put("VARIABLE","F");
        EvolvedBooleanString n_ = e_.createBooleanString("VARIABLE", vars_);
        n_.evaluateExp(false);
        assertEq(false,n_.getResult());
        assertEq("F",n_.beforeEvaluated());
        assertEq(true,n_.isValid());
    }
    @Test
    public void evaluateExpBoolean3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        EvolvedBooleanString n_ = e_.createBooleanString("++", vars_);
        n_.evaluateExp(false);
        assertEq(false,n_.isValid());
    }
    @Test
    public void evaluateNumericable1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("0"),e_.evaluateNumericable("0",vars_,new Rate("1")));
    }
    @Test
    public void evaluateNumericable2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("0"),e_.evaluateNumericable("++",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveExp1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"),e_.evaluatePositiveExp("0",vars_,new Rate("1")));
    }
    @Test
    public void evaluatePositiveExp2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"),e_.evaluatePositiveExp("-1",vars_,new Rate("1")));
    }
    @Test
    public void evaluatePositiveExp3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"),e_.evaluatePositiveExp("1",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveExp4Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"),e_.evaluatePositiveExp("++",vars_,new Rate("1")));
    }
    @Test
    public void evaluatePositiveOrZeroExp1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("0"),e_.evaluatePositiveOrZeroExp("-1",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveOrZeroExp2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"),e_.evaluatePositiveOrZeroExp("1",vars_,new Rate("0")));
    }
    @Test
    public void evaluatePositiveOrZeroExp3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(new Rate("1"),e_.evaluatePositiveOrZeroExp("++",vars_,new Rate("1")));
    }
    @Test
    public void evaluateBoolean1Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(false,e_.evaluateBoolean("F",vars_,true));
    }
    @Test
    public void evaluateBoolean2Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(true,e_.evaluateBoolean("V",vars_,false));
    }
    @Test
    public void evaluateBoolean3Test() {
        EvolvedMathFactory e_ = new EvolvedMathFactory();
        StringMap<String> vars_ = new StringMap<String>();
        assertEq(true,e_.evaluateBoolean("++",vars_,true));
    }
}