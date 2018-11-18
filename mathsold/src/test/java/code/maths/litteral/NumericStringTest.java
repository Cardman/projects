package code.maths.litteral;
import static code.maths.EquallableMathUtil.assertEq;

import org.junit.Ignore;
import org.junit.Test;

import code.maths.Rate;
import code.util.StringMap;

@SuppressWarnings("static-method")
@Ignore
public class NumericStringTest {
    @Test
    public void evaluate1(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8";
        assertEq(new Rate("8"), NumericString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate2(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8+8";
        assertEq(new Rate("16"), NumericString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate3(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8:VARIABLE";
        assertEq(new Rate("4"), NumericString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate4(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "1");
        String numericString_ = "8:0";
        assertEq(new Rate("12"), NumericString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate5(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "8:VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate6(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "1");
        String numericString_ = "";
        assertEq(new Rate("12"), NumericString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate7(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        String numericString_ = "";
        assertEq(new Rate("12"), NumericString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp1(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8";
        assertEq(new Rate("8"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp2(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8+8";
        assertEq(new Rate("16"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp3(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8:VARIABLE";
        assertEq(new Rate("4"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp4(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "1");
        String numericString_ = "8:0";
        assertEq(new Rate("12"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp5(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "8:VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp6(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "1");
        String numericString_ = "";
        assertEq(new Rate("12"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp7(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp8(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "-1");
        String numericString_ = "VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveExp9(){
        Rate default_ = new Rate("-12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "-1");
        String numericString_ = "VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluatePositiveExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp1(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8";
        assertEq(new Rate("8"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp2(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8+8";
        assertEq(new Rate("16"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp3(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8:VARIABLE";
        assertEq(new Rate("4"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp4(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "1");
        String numericString_ = "8:0";
        assertEq(new Rate("12"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp5(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "8:VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp6(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "1");
        String numericString_ = "";
        assertEq(new Rate("12"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp7(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "VARIABLE";
        assertEq(new Rate("0"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp8(){
        Rate default_ = new Rate("12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "-1");
        String numericString_ = "VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluatePositiveOrZeroExp9(){
        Rate default_ = new Rate("-12");
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "-1");
        String numericString_ = "VARIABLE";
        assertEq(new Rate("12"), NumericString.evaluatePositiveOrZeroExp(numericString_, variables_, false, default_));
    }
    @Test
    public void deleteZeroDivider1Test(){
        assertEq("1/2",NumericString.deleteZeroDivider(false, "1/2").toNumberString());
    }
    @Test
    public void deleteZeroDivider2Test(){
        assertEq("1/2",NumericString.deleteZeroDivider(true, "1/2").toNumberString());
    }
    @Test
    public void deleteZeroDivider3Test(){
        assertEq("1",NumericString.deleteZeroDivider(true, "1/0").toNumberString());
    }
    @Test
    public void deleteZeroDivider1Fail(){
        NumericString.deleteZeroDivider(false,"1/0");
    }
    @Test
    public void deleteZeroDivider2Fail(){
        NumericString.deleteZeroDivider(true,"1/STRING");
    }
    @Test
    public void deleteZeroDivider3Fail(){
        NumericString.deleteZeroDivider(true,"STRING/1");
    }
    @Test
    public void deleteZeroDivider4Fail(){
        NumericString.deleteZeroDivider(true,"1/2/3");
    }
    @Test
    public void evaluateExp_Check1Test(){
        NumericString numeric_ = new NumericString("1/2+1");
        numeric_.evaluateExp(true);
        assertEq(new Rate("3/2"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check2Test(){
        NumericString numeric_ = new NumericString("1/0+1");
        numeric_.evaluateExp(true);
        assertEq(new Rate("2"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check3Test(){
        NumericString numeric_ = new NumericString("1:0+1");
        numeric_.evaluateExp(true);
        assertEq(new Rate("2"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check4Test(){
        NumericString numeric_ = new NumericString("1:-0+1");
        numeric_.evaluateExp(true);
        assertEq(new Rate("2"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check5Test(){
        NumericString numeric_ = new NumericString("1/0");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check6Test(){
        NumericString numeric_ = new NumericString("1/-0");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check7Test(){
        NumericString numeric_ = new NumericString("1:0");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check8Test(){
        NumericString numeric_ = new NumericString("1:-0");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check9Test(){
        NumericString numeric_ = new NumericString("div(1,0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check10Test(){
        NumericString numeric_ = new NumericString("div(1,-0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check11Test(){
        NumericString numeric_ = new NumericString("quot(1,0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check12Test(){
        NumericString numeric_ = new NumericString("quot(1,-0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check13Test(){
        NumericString numeric_ = new NumericString("mod(1,0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check14Test(){
        NumericString numeric_ = new NumericString("mod(1,-0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check15Test(){
        NumericString numeric_ = new NumericString("modtaux(1,0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check16Test(){
        NumericString numeric_ = new NumericString("modtaux(1,-0)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check17Test(){
        NumericString numeric_ = new NumericString("puis(0,-1)");
        numeric_.evaluateExp(true);
        assertEq(new Rate("1"),numeric_.getResult());
    }
    @Test
    public void evaluateExp_Check1Fail(){
        NumericString numeric_ = new NumericString("1/string(1)");
        numeric_.evaluateExp(true);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_1FailTest(){
        NumericString numeric_ = new NumericString("(2*1+1):0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_2FailTest(){
        NumericString numeric_ = new NumericString("(2*1+1):0+1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_3FailTest(){
        NumericString numeric_ = new NumericString("1+(2*1+1):0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_4FailTest(){
        NumericString numeric_ = new NumericString("1+(2*1+1):0+8");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_5FailTest(){
        NumericString numeric_ = new NumericString("1+((2*1+1):0)+8");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_6FailTest(){
        NumericString numeric_ = new NumericString("(1:0)+4");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_7FailTest(){
        NumericString numeric_ = new NumericString("div(1,0)+4");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_8FailTest(){
        NumericString numeric_ = new NumericString("1:0+1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_9FailTest(){
        NumericString numeric_ = new NumericString("1:-0+1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_10FailTest(){
        NumericString numeric_ = new NumericString("div(1,-0)+1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_11FailTest(){
        NumericString numeric_ = new NumericString("1+2*5:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_12FailTest(){
        NumericString numeric_ = new NumericString("1-2*5:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_13FailTest(){
        NumericString numeric_ = new NumericString("1+3:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_14FailTest(){
        NumericString numeric_ = new NumericString("1+3+5:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_15FailTest(){
        NumericString numeric_ = new NumericString("1-3+5:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_16FailTest(){
        NumericString numeric_ = new NumericString("1-3:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_17FailTest(){
        NumericString numeric_ = new NumericString("-3*5:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_18FailTest(){
        NumericString numeric_ = new NumericString("-3-5:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_19FailTest(){
        NumericString numeric_ = new NumericString("3-5:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_20FailTest(){
        NumericString numeric_ = new NumericString("1:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_21FailTest(){
        NumericString numeric_ = new NumericString("1:-0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_22FailTest(){
        NumericString numeric_ = new NumericString("quot(1,0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_23FailTest(){
        NumericString numeric_ = new NumericString("quot(1,-0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_24FailTest(){
        NumericString numeric_ = new NumericString("mod(1,0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_25FailTest(){
        NumericString numeric_ = new NumericString("mod(1,-0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_26FailTest(){
        NumericString numeric_ = new NumericString("modtaux(1,0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_27FailTest(){
        NumericString numeric_ = new NumericString("modtaux(1,-0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_28FailTest(){
        NumericString numeric_ = new NumericString("string(0,-1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_29FailTest(){
        NumericString numeric_ = new NumericString("abs(1:0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_30FailTest(){
        NumericString numeric_ = new NumericString("quot(1,1:0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_31FailTest(){
        NumericString numeric_ = new NumericString("quot(1,1:0+1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_32FailTest(){
        NumericString numeric_ = new NumericString("2*quot(1:0,1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_33FailTest(){
        NumericString numeric_ = new NumericString("2*quot(1,1:0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_34FailTest(){
        NumericString numeric_ = new NumericString("2*quot(1,0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_35FailTest(){
        NumericString numeric_ = new NumericString("quot(1:0,1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_36FailTest(){
        NumericString numeric_ = new NumericString("(1:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_37FailTest(){
        NumericString numeric_ = new NumericString("1:0)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_38FailTest(){
        NumericString numeric_ = new NumericString("quot(1:0+1,1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_39FailTest(){
        NumericString numeric_ = new NumericString("-1:0+1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_40FailTest(){
        NumericString numeric_ = new NumericString("puis(0,-1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_41FailTest(){
        NumericString numeric_ = new NumericString("-1:0");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_42FailTest(){
        NumericString numeric_ = new NumericString("(-1:0+1)+1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_43FailTest(){
        NumericString numeric_ = new NumericString("(2*1+1):0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_44FailTest(){
        NumericString numeric_ = new NumericString("(2*1+1):0+1");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_45FailTest(){
        NumericString numeric_ = new NumericString("1+(2*1+1):0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_46FailTest(){
        NumericString numeric_ = new NumericString("1+(2*1+1):0+8");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_47FailTest(){
        NumericString numeric_ = new NumericString("1+((2*1+1):0)+8");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_48FailTest(){
        NumericString numeric_ = new NumericString("(1:0)+4");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_49FailTest(){
        NumericString numeric_ = new NumericString("div(1,0)+4");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_50FailTest(){
        NumericString numeric_ = new NumericString("1:0+1");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_51FailTest(){
        NumericString numeric_ = new NumericString("1:-0+1");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_52FailTest(){
        NumericString numeric_ = new NumericString("div(1,-0)+1");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_53FailTest(){
        NumericString numeric_ = new NumericString("1+2*5:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_54FailTest(){
        NumericString numeric_ = new NumericString("1-2*5:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_55FailTest(){
        NumericString numeric_ = new NumericString("1+3:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_56FailTest(){
        NumericString numeric_ = new NumericString("1+3+5:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_57FailTest(){
        NumericString numeric_ = new NumericString("1-3+5:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_58FailTest(){
        NumericString numeric_ = new NumericString("1-3:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_59FailTest(){
        NumericString numeric_ = new NumericString("-3*5:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_60FailTest(){
        NumericString numeric_ = new NumericString("-3-5:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_61FailTest(){
        NumericString numeric_ = new NumericString("3-5:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_62FailTest(){
        NumericString numeric_ = new NumericString("1:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_63FailTest(){
        NumericString numeric_ = new NumericString("1:-0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_64FailTest(){
        NumericString numeric_ = new NumericString("quot(1,0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_65FailTest(){
        NumericString numeric_ = new NumericString("quot(1,-0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_66FailTest(){
        NumericString numeric_ = new NumericString("mod(1,0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_67FailTest(){
        NumericString numeric_ = new NumericString("mod(1,-0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_68FailTest(){
        NumericString numeric_ = new NumericString("modtaux(1,0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_69FailTest(){
        NumericString numeric_ = new NumericString("modtaux(1,-0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_70FailTest(){
        NumericString numeric_ = new NumericString("string(0,-1)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_71FailTest(){
        NumericString numeric_ = new NumericString("abs(1:0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_72FailTest(){
        NumericString numeric_ = new NumericString("quot(1,1:0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_73FailTest(){
        NumericString numeric_ = new NumericString("quot(1,1:0+1)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_74FailTest(){
        NumericString numeric_ = new NumericString("2*quot(1:0,1)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_75FailTest(){
        NumericString numeric_ = new NumericString("2*quot(1,1:0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_76FailTest(){
        NumericString numeric_ = new NumericString("2*quot(1,0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_77FailTest(){
        NumericString numeric_ = new NumericString("quot(1:0,1)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_78FailTest(){
        NumericString numeric_ = new NumericString("(1:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_79FailTest(){
        NumericString numeric_ = new NumericString("1:0)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_80FailTest(){
        NumericString numeric_ = new NumericString("quot(1:0+1,1)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_81FailTest(){
        NumericString numeric_ = new NumericString("-1:0+1");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_82FailTest(){
        NumericString numeric_ = new NumericString("puis(0,-1)");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_83FailTest(){
        NumericString numeric_ = new NumericString("-1:0");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_84FailTest(){
        NumericString numeric_ = new NumericString("(-1:0+1)+1");
        numeric_.evaluateExp(false);
        assertEq(true, numeric_.isError());
    }
    @Test
    public void evaluateExp_85FailTest(){
        NumericString numeric_ = new NumericString("1/0+1");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp_86FailTest(){
        NumericString numeric_ = new NumericString("2*1/0");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp_87FailTest(){
        NumericString numeric_ = new NumericString("2*1/0+1");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp_88FailTest(){
        NumericString numeric_ = new NumericString("1+1+1/0");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp_89FailTest(){
        NumericString numeric_ = new NumericString("2*(1+1+1/0)");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp_90FailTest(){
        NumericString numeric_ = new NumericString("1/0");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp_91FailTest(){
        NumericString numeric_ = new NumericString("1/-0");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp_92Fail(){
        NumericString numeric_ = new NumericString("cardinal({A;B)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_93Fail(){
        NumericString numeric_ = new NumericString("cardinal(A;B})");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_94Fail(){
        NumericString numeric_ = new NumericString("cardinal({A;B");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_95Fail(){
        NumericString numeric_ = new NumericString("cardinal(A;B}");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_96Fail(){
        NumericString numeric_ = new NumericString("1:A");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_97Fail(){
        NumericString numeric_ = new NumericString("1/A");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_98Fail(){
        NumericString numeric_ = new NumericString("-");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_99Fail(){
        NumericString numeric_ = new NumericString("1/-");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_100Fail(){
        NumericString numeric_ = new NumericString("1/");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_101Fail(){
        NumericString numeric_ = new NumericString("fct");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_102Fail(){
        NumericString numeric_ = new NumericString("fct(");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_103Fail(){
        NumericString numeric_ = new NumericString("fct(1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_104Fail(){
        NumericString numeric_ = new NumericString("fct(1,");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_105Fail(){
        NumericString numeric_ = new NumericString("fct(1,fct(1,");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_106Fail(){
        NumericString numeric_ = new NumericString("fct(1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_107Fail(){
        NumericString numeric_ = new NumericString("fct(1,1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_108Fail(){
        NumericString numeric_ = new NumericString("abs");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_109Fail(){
        NumericString numeric_ = new NumericString("abs(");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_110Fail(){
        NumericString numeric_ = new NumericString("abs(1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_111Fail(){
        NumericString numeric_ = new NumericString("abs(1,");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_112Fail(){
        NumericString numeric_ = new NumericString("abs(1,abs(1)");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_113Fail(){
        NumericString numeric_ = new NumericString("abs(1,1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_114Fail(){
        NumericString numeric_ = new NumericString("abs(1,abs(1,1");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_115Fail(){
        NumericString numeric_ = new NumericString("4*(3*8+6");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_116Fail(){
        NumericString numeric_ = new NumericString("");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_117Fail(){
        NumericString numeric_ = new NumericString("(");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_118Fail(){
        NumericString numeric_ = new NumericString(")");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_119Fail(){
        NumericString numeric_ = new NumericString("2a");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_120Fail(){
        NumericString numeric_ = new NumericString("22a");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_121Fail(){
        NumericString numeric_ = new NumericString("2ab");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_122Fail(){
        NumericString numeric_ = new NumericString("2.{A;B}");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_123Fail(){
        NumericString numeric_ = new NumericString(".{A;B}");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_124Fail(){
        NumericString numeric_ = new NumericString("1--");
        numeric_.evaluateExp(false);
        numeric_.getResult();
    }
    @Test
    public void evaluateExp_125Fail(){
        NumericString numeric_ = new NumericString("--");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp1Test(){
        NumericString numeric_ = new NumericString("1");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp2Test(){
        NumericString numeric_ = new NumericString(".0");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp3Test(){
        NumericString numeric_ = new NumericString("-.0");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp4Test(){
        NumericString numeric_ = new NumericString("-.5");
        numeric_.evaluateExp(false);
        assertEq("-1/2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp5Test(){
        NumericString numeric_ = new NumericString(".5");
        numeric_.evaluateExp(false);
        assertEq("1/2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp6Test(){
        NumericString numeric_ = new NumericString("1.");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp7Test(){
        NumericString numeric_ = new NumericString("1.5");
        numeric_.evaluateExp(false);
        assertEq("3/2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp8Test(){
        NumericString numeric_ = new NumericString("-1.");
        numeric_.evaluateExp(false);
        assertEq("-1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp9Test(){
        NumericString numeric_ = new NumericString("-1.5");
        numeric_.evaluateExp(false);
        assertEq("-3/2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp10Test(){
        NumericString numeric_ = new NumericString("0.0");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp11Test(){
        NumericString numeric_ = new NumericString("0.2");
        numeric_.evaluateExp(false);
        assertEq("1/5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp12Test(){
        NumericString numeric_ = new NumericString("15/7");
        numeric_.evaluateExp(false);
        assertEq("15/7",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp13Test(){
        NumericString numeric_ = new NumericString("0.1");
        numeric_.evaluateExp(false);
        assertEq("1/10",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp14Test(){
        NumericString numeric_ = new NumericString("0.2");
        numeric_.evaluateExp(false);
        assertEq("1/5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp15Test(){
        NumericString numeric_ = new NumericString("15/5");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp16Test(){
        NumericString numeric_ = new NumericString("-3");
        numeric_.evaluateExp(false);
        assertEq("-3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp17Test(){
        NumericString numeric_ = new NumericString("1+2*5*7");
        numeric_.evaluateExp(false);
        assertEq("71",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp18Test(){
        NumericString numeric_ = new NumericString("1-2*5*7");
        numeric_.evaluateExp(false);
        assertEq("-69",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp19Test(){
        NumericString numeric_ = new NumericString("1+3*5");
        numeric_.evaluateExp(false);
        assertEq("16",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp20Test(){
        NumericString numeric_ = new NumericString("1+3+5");
        numeric_.evaluateExp(false);
        assertEq("9",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp21Test(){
        NumericString numeric_ = new NumericString("1-3+5");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp22Test(){
        NumericString numeric_ = new NumericString("1-3*5");
        numeric_.evaluateExp(false);
        assertEq("-14",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp23Test(){
        NumericString numeric_ = new NumericString("-3*5");
        numeric_.evaluateExp(false);
        assertEq("-15",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp24Test(){
        NumericString numeric_ = new NumericString("-3-5");
        numeric_.evaluateExp(false);
        assertEq("-8",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp25Test(){
        NumericString numeric_ = new NumericString("3-5");
        numeric_.evaluateExp(false);
        assertEq("-2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp26Test(){
        NumericString numeric_ = new NumericString("1+4");
        numeric_.evaluateExp(false);
        assertEq("5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp27Test(){
        NumericString numeric_ = new NumericString("1+4/5");
        numeric_.evaluateExp(false);
        assertEq("9/5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp28Test(){
        NumericString numeric_ = new NumericString("5*3");
        numeric_.evaluateExp(false);
        assertEq("15",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp29Test(){
        NumericString numeric_ = new NumericString("5*3/7");
        numeric_.evaluateExp(false);
        assertEq("15/7",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp30Test(){
        NumericString numeric_ = new NumericString("5:3/7");
        numeric_.evaluateExp(false);
        assertEq("35/3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp31Test(){
        NumericString numeric_ = new NumericString("div(5,3/7)");
        numeric_.evaluateExp(false);
        assertEq("35/3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp32Test(){
        NumericString numeric_ = new NumericString("5:-3/7");
        numeric_.evaluateExp(false);
        assertEq("-35/3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp33Test(){
        NumericString numeric_ = new NumericString("div(5,-3/7)");
        numeric_.evaluateExp(false);
        assertEq("-35/3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp34Test(){
        NumericString numeric_ = new NumericString("8-6");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp35Test(){
        NumericString numeric_ = new NumericString("--5");
        numeric_.evaluateExp(false);
        assertEq("5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp36Test(){
        NumericString numeric_ = new NumericString("---5");
        numeric_.evaluateExp(false);
        assertEq("-5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp37Test(){
        NumericString numeric_ = new NumericString("(--5+8)");
        numeric_.evaluateExp(false);
        assertEq("13",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp38Test(){
        NumericString numeric_ = new NumericString("(--5");
        numeric_.evaluateExp(false);
        assertEq("(5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp39Test(){
        NumericString numeric_ = new NumericString("--5)");
        numeric_.evaluateExp(false);
        assertEq("5)",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp40Test(){
        NumericString numeric_ = new NumericString("(5");
        numeric_.evaluateExp(false);
        assertEq("(5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp41Test(){
        NumericString numeric_ = new NumericString("5)");
        numeric_.evaluateExp(false);
        assertEq("5)",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp42Test(){
        NumericString numeric_ = new NumericString("(---5+8)");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp43Test(){
        NumericString numeric_ = new NumericString("1*--5+8");
        numeric_.evaluateExp(false);
        assertEq("13",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp44Test(){
        NumericString numeric_ = new NumericString("1*---5+8");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp45Test(){
        NumericString numeric_ = new NumericString("9--5");
        numeric_.evaluateExp(false);
        assertEq("14",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp46Test(){
        NumericString numeric_ = new NumericString("13---4");
        numeric_.evaluateExp(false);
        assertEq("9",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp47Test(){
        NumericString numeric_ = new NumericString("3*8+6");
        numeric_.evaluateExp(false);
        assertEq("30",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp48Test(){
        NumericString numeric_ = new NumericString("(3*8+6)");
        numeric_.evaluateExp(false);
        assertEq("30",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp49Test(){
        NumericString numeric_ = new NumericString("(3*8+6)(3*8+6)");
        numeric_.evaluateExp(false);
        assertEq("900",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp50Test(){
        NumericString numeric_ = new NumericString("1-2*-3");
        numeric_.evaluateExp(false);
        assertEq("7",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp51Test(){
        NumericString numeric_ = new NumericString("1-2*-3*5");
        numeric_.evaluateExp(false);
        assertEq("31",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp52Test(){
        NumericString numeric_ = new NumericString("1-2*3*-5");
        numeric_.evaluateExp(false);
        assertEq("31",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp53Test(){
        NumericString numeric_ = new NumericString("1-2*3*5");
        numeric_.evaluateExp(false);
        assertEq("-29",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp54Test(){
        NumericString numeric_ = new NumericString("2*(1-2*-3)");
        numeric_.evaluateExp(false);
        assertEq("14",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp55Test(){
        NumericString numeric_ = new NumericString("2*(1-2*-3*5)");
        numeric_.evaluateExp(false);
        assertEq("62",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp56Test(){
        NumericString numeric_ = new NumericString("2*(1-2*3*-5)");
        numeric_.evaluateExp(false);
        assertEq("62",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp57Test(){
        NumericString numeric_ = new NumericString("2*(1-2*3*5)");
        numeric_.evaluateExp(false);
        assertEq("-58",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp58Test(){
        NumericString numeric_ = new NumericString("((3))");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp59Test(){
        NumericString numeric_ = new NumericString("(3+9");
        numeric_.evaluateExp(false);
        assertEq("(12",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp60Test(){
        NumericString numeric_ = new NumericString("3+9)");
        numeric_.evaluateExp(false);
        assertEq("12)",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp61Test(){
        NumericString numeric_ = new NumericString("(3+9+6");
        numeric_.evaluateExp(false);
        assertEq("(18",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp62Test(){
        NumericString numeric_ = new NumericString("3+9+6)");
        numeric_.evaluateExp(false);
        assertEq("18)",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp63Test(){
        NumericString numeric_ = new NumericString("(12");
        numeric_.evaluateExp(false);
        assertEq("(12",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp64Test(){
        NumericString numeric_ = new NumericString("12)");
        numeric_.evaluateExp(false);
        assertEq("12)",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp65Test(){
        NumericString numeric_ = new NumericString("((");
        numeric_.evaluateExp(false);
        assertEq("((",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp66Test(){
        NumericString numeric_ = new NumericString("))");
        numeric_.evaluateExp(false);
        assertEq("))",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp67Test(){
        NumericString numeric_ = new NumericString("((3+9)");
        numeric_.evaluateExp(false);
        assertEq("(12",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp68Test(){
        NumericString numeric_ = new NumericString("(3+9))");
        numeric_.evaluateExp(false);
        assertEq("12)",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp69Test(){
        NumericString numeric_ = new NumericString("((3+9");
        numeric_.evaluateExp(false);
        assertEq("((12",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp70Test(){
        NumericString numeric_ = new NumericString("3+9))");
        numeric_.evaluateExp(false);
        assertEq("12))",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp71Test(){
        NumericString numeric_ = new NumericString("4(3*8+6)");
        numeric_.evaluateExp(false);
        assertEq("120",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp72Test(){
        NumericString numeric_ = new NumericString("3*(8+6)");
        numeric_.evaluateExp(false);
        assertEq("42",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp73Test(){
        NumericString numeric_ = new NumericString("quot(5,6)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp74Test(){
        NumericString numeric_ = new NumericString("-1*quot(5,6)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp75Test(){
        NumericString numeric_ = new NumericString("-quot(5,6)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp76Test(){
        NumericString numeric_ = new NumericString("quot(15,6)");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp77Test(){
        NumericString numeric_ = new NumericString("quot(-15,6)");
        numeric_.evaluateExp(false);
        assertEq("-3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp78Test(){
        NumericString numeric_ = new NumericString("quot(15,6)quot(-15,6)");
        numeric_.evaluateExp(false);
        assertEq("-6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp79Test(){
        NumericString numeric_ = new NumericString("mod(15,6)");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp80Test(){
        NumericString numeric_ = new NumericString("mod(15,5)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp81Test(){
        NumericString numeric_ = new NumericString("mod(-15,5)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp82Test(){
        NumericString numeric_ = new NumericString("mod(-5,6)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp83Test(){
        NumericString numeric_ = new NumericString("modtaux(15,6)");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp84Test(){
        NumericString numeric_ = new NumericString("modtaux(15,5)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp85Test(){
        NumericString numeric_ = new NumericString("modtaux(-15,5)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp86Test(){
        NumericString numeric_ = new NumericString("modtaux(-5,6)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp87Test(){
        NumericString numeric_ = new NumericString("puis(3,4)");
        numeric_.evaluateExp(false);
        assertEq("81",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp88Test(){
        NumericString numeric_ = new NumericString("puis(-3,3)");
        numeric_.evaluateExp(false);
        assertEq("-27",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp89Test(){
        NumericString numeric_ = new NumericString("puis(-2,-3)");
        numeric_.evaluateExp(false);
        assertEq("-1/8",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp90Test(){
        NumericString numeric_ = new NumericString("puis(2,-3)");
        numeric_.evaluateExp(false);
        assertEq("1/8",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp91Test(){
        NumericString numeric_ = new NumericString("puis(0,0)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp92Test(){
        NumericString numeric_ = new NumericString("puis(0,1/2)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp93Test(){
        NumericString numeric_ = new NumericString("3puis(0,0)");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp94Test(){
        NumericString numeric_ = new NumericString("puis(9,1/2)");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp95Test(){
        NumericString numeric_ = new NumericString("puis(9,-1/2)");
        numeric_.evaluateExp(false);
        assertEq("1/3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp96Test(){
        NumericString numeric_ = new NumericString("puis(-9,1/2)");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp97Test(){
        NumericString numeric_ = new NumericString("puis(-9,-1/2)");
        numeric_.evaluateExp(false);
        assertEq("1/3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp98Test(){
        NumericString numeric_ = new NumericString("puis(-27,1/3)");
        numeric_.evaluateExp(false);
        assertEq("-3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp99Test(){
        NumericString numeric_ = new NumericString("puis(-27,2/3)");
        numeric_.evaluateExp(false);
        assertEq("9",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp100Test(){
        NumericString numeric_ = new NumericString("puis(-27,-1/3)");
        numeric_.evaluateExp(false);
        assertEq("-1/3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp101Test(){
        NumericString numeric_ = new NumericString("puis(-27,-2/3)");
        numeric_.evaluateExp(false);
        assertEq("1/9",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp102Test(){
        NumericString numeric_ = new NumericString("3*puis(1+2,2)--9");
        numeric_.evaluateExp(false);
        assertEq("36",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp103Test(){
        NumericString numeric_ = new NumericString("3*puis(1+2,2):3--9");
        numeric_.evaluateExp(false);
        assertEq("18",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp104Test(){
        NumericString numeric_ = new NumericString("3*div(puis(1+2,2),3)--9");
        numeric_.evaluateExp(false);
        assertEq("18",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp105Test(){
        NumericString numeric_ = new NumericString("3*puis(1+2,5-3):3--9");
        numeric_.evaluateExp(false);
        assertEq("18",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp106Test(){
        NumericString numeric_ = new NumericString("3*div(puis(1+2,5-3),3)--9");
        numeric_.evaluateExp(false);
        assertEq("18",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp107Test(){
        NumericString numeric_ = new NumericString("abs(9)");
        numeric_.evaluateExp(false);
        assertEq("9",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp108Test(){
        NumericString numeric_ = new NumericString("abs(-5)");
        numeric_.evaluateExp(false);
        assertEq("5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp109Test(){
        NumericString numeric_ = new NumericString("abs(0)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp110Test(){
        NumericString numeric_ = new NumericString("sgn(9)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp111Test(){
        NumericString numeric_ = new NumericString("sgn(-5)");
        numeric_.evaluateExp(false);
        assertEq("-1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp112Test(){
        NumericString numeric_ = new NumericString("sgn(0)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp113Test(){
        NumericString numeric_ = new NumericString("ent(-5)");
        numeric_.evaluateExp(false);
        assertEq("-5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp114Test(){
        NumericString numeric_ = new NumericString("ent(-5/2)");
        numeric_.evaluateExp(false);
        assertEq("-3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp115Test(){
        NumericString numeric_ = new NumericString("ent(0)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp116Test(){
        NumericString numeric_ = new NumericString("ent(4)");
        numeric_.evaluateExp(false);
        assertEq("4",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp117Test(){
        NumericString numeric_ = new NumericString("ent(6/5)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp118Test(){
        NumericString numeric_ = new NumericString("troncature(-5)");
        numeric_.evaluateExp(false);
        assertEq("-5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp119Test(){
        NumericString numeric_ = new NumericString("troncature(-5/2)");
        numeric_.evaluateExp(false);
        assertEq("-2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp120Test(){
        NumericString numeric_ = new NumericString("troncature(0)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp121Test(){
        NumericString numeric_ = new NumericString("troncature(4)");
        numeric_.evaluateExp(false);
        assertEq("4",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp122Test(){
        NumericString numeric_ = new NumericString("troncature(6/5)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp123Test(){
        NumericString numeric_ = new NumericString("num(0)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp124Test(){
        NumericString numeric_ = new NumericString("num(4)");
        numeric_.evaluateExp(false);
        assertEq("4",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp125Test(){
        NumericString numeric_ = new NumericString("num(6/5)");
        numeric_.evaluateExp(false);
        assertEq("6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp126Test(){
        NumericString numeric_ = new NumericString("den(0)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp127Test(){
        NumericString numeric_ = new NumericString("den(4)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp128Test(){
        NumericString numeric_ = new NumericString("den(6/5)");
        numeric_.evaluateExp(false);
        assertEq("5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp129Test(){
        NumericString numeric_ = new NumericString("num(-5)");
        numeric_.evaluateExp(false);
        assertEq("-5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp130Test(){
        NumericString numeric_ = new NumericString("num(-5/2)");
        numeric_.evaluateExp(false);
        assertEq("-5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp131Test(){
        NumericString numeric_ = new NumericString("den(-5)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp132Test(){
        NumericString numeric_ = new NumericString("den(-5/2)");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp133Test(){
        NumericString numeric_ = new NumericString("caracferme(2,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp134Test(){
        NumericString numeric_ = new NumericString("caracferme(1,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp135Test(){
        NumericString numeric_ = new NumericString("caracferme(3,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp136Test(){
        NumericString numeric_ = new NumericString("caracferme(0,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp137Test(){
        NumericString numeric_ = new NumericString("caracferme(4,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp138Test(){
        NumericString numeric_ = new NumericString("caracferme(2,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp139Test(){
        NumericString numeric_ = new NumericString("caracferme(1,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp140Test(){
        NumericString numeric_ = new NumericString("caracferme(3,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp141Test(){
        NumericString numeric_ = new NumericString("caracferme(0,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp142Test(){
        NumericString numeric_ = new NumericString("caracferme(4,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp143Test(){
        NumericString numeric_ = new NumericString("caracouvert(2,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp144Test(){
        NumericString numeric_ = new NumericString("caracouvert(1,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp145Test(){
        NumericString numeric_ = new NumericString("caracouvert(3,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp146Test(){
        NumericString numeric_ = new NumericString("caracouvert(0,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp147Test(){
        NumericString numeric_ = new NumericString("caracouvert(4,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp148Test(){
        NumericString numeric_ = new NumericString("caracouvert(2,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp149Test(){
        NumericString numeric_ = new NumericString("caracouvert(1,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp150Test(){
        NumericString numeric_ = new NumericString("caracouvert(3,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp151Test(){
        NumericString numeric_ = new NumericString("caracouvert(0,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp152Test(){
        NumericString numeric_ = new NumericString("caracouvert(4,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp153Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(2,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp154Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(1,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp155Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(3,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp156Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(0,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp157Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(4,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp158Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(2,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp159Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(1,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp160Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(3,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp161Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(0,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp162Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertg(4,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp163Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(2,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp164Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(1,1,3)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp165Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(3,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp166Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(0,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp167Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(4,1,3)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp168Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(2,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp169Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(1,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp170Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(3,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp171Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(0,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp172Test(){
        NumericString numeric_ = new NumericString("caracsemiouvertd(4,3,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp173Test(){
        NumericString numeric_ = new NumericString("caracdroiteouvert(2,1)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp174Test(){
        NumericString numeric_ = new NumericString("caracdroiteouvert(1,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp175Test(){
        NumericString numeric_ = new NumericString("caracdroiteouvert(0,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp176Test(){
        NumericString numeric_ = new NumericString("caracdroiteferme(2,1)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp177Test(){
        NumericString numeric_ = new NumericString("caracdroiteferme(1,1)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp178Test(){
        NumericString numeric_ = new NumericString("caracdroiteferme(0,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp179Test(){
        NumericString numeric_ = new NumericString("caracgaucheouvert(2,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp180Test(){
        NumericString numeric_ = new NumericString("caracgaucheouvert(1,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp181Test(){
        NumericString numeric_ = new NumericString("caracgaucheouvert(0,1)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp182Test(){
        NumericString numeric_ = new NumericString("caracgaucheferme(2,1)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp183Test(){
        NumericString numeric_ = new NumericString("caracgaucheferme(1,1)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp184Test(){
        NumericString numeric_ = new NumericString("caracgaucheferme(0,1)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp185Test(){
        NumericString numeric_ = new NumericString("puis(1+5,1+2)");
        numeric_.evaluateExp(false);
        assertEq("216",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp186Test(){
        NumericString numeric_ = new NumericString("puis(1+5,1*2)");
        numeric_.evaluateExp(false);
        assertEq("36",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp187Test(){
        NumericString numeric_ = new NumericString("abs(4+6)");
        numeric_.evaluateExp(false);
        assertEq("10",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp188Test(){
        NumericString numeric_ = new NumericString("abs((4+6))");
        numeric_.evaluateExp(false);
        assertEq("10",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp189Test(){
        NumericString numeric_ = new NumericString("min(1+5,1+7)");
        numeric_.evaluateExp(false);
        assertEq("6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp190Test(){
        NumericString numeric_ = new NumericString("max(1+5,1+7)");
        numeric_.evaluateExp(false);
        assertEq("8",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp191Test(){
        NumericString numeric_ = new NumericString("min(1+7,1+5)");
        numeric_.evaluateExp(false);
        assertEq("6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp192Test(){
        NumericString numeric_ = new NumericString("max(1+7,1+5)");
        numeric_.evaluateExp(false);
        assertEq("8",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp193Test(){
        NumericString numeric_ = new NumericString("moy(1+5,1+7)");
        numeric_.evaluateExp(false);
        assertEq("7",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp194Test(){
        NumericString numeric_ = new NumericString("var(1+5,1+7)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp195Test(){
        NumericString numeric_ = new NumericString("min(4+19,max(12,15))");
        numeric_.evaluateExp(false);
        assertEq("15",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp196Test(){
        NumericString numeric_ = new NumericString("2*min(4+19,max(12,15))");
        numeric_.evaluateExp(false);
        assertEq("30",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp197Test(){
        NumericString numeric_ = new NumericString("(4+12)*2");
        numeric_.evaluateExp(false);
        assertEq("32",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp198Test(){
        NumericString numeric_ = new NumericString("2:-min(4+19,max(12,15))");
        numeric_.evaluateExp(false);
        assertEq("-2/15",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp199Test(){
        NumericString numeric_ = new NumericString("3:-min(4+19,max(12,15))");
        numeric_.evaluateExp(false);
        assertEq("-1/5",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp200Test(){
        NumericString numeric_ = new NumericString("puis(4*div(1,(3060)),1/4)");
        numeric_.evaluateExp(false);
        assertEq("29/153",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp201Test(){
        NumericString numeric_ = new NumericString("puis(3,(1+1)*2)");
        numeric_.evaluateExp(false);
        assertEq("81",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp202Test(){
        NumericString numeric_ = new NumericString("puis(3,2*(1+1))");
        numeric_.evaluateExp(false);
        assertEq("81",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp203Test(){
        NumericString numeric_ = new NumericString("puis(2*1+1,2+1*2)");
        numeric_.evaluateExp(false);
        assertEq("81",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp204Test(){
        NumericString numeric_ = new NumericString("puis(3,2+1*2)");
        numeric_.evaluateExp(false);
        assertEq("81",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp205Test(){
        NumericString numeric_ = new NumericString("puis(2+1*(1+0),2+1*2)");
        numeric_.evaluateExp(false);
        assertEq("81",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp206Test(){
        NumericString numeric_ = new NumericString("min(2,2+1*1,2+1*2)");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp207Test(){
        NumericString numeric_ = new NumericString("2*min(2,2+1*1,2+1*2)");
        numeric_.evaluateExp(false);
        assertEq("4",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp208Test(){
        NumericString numeric_ = new NumericString("1+2+3");
        numeric_.evaluateExp(false);
        assertEq("6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp209Test(){
        NumericString numeric_ = new NumericString("1+(2+3)");
        numeric_.evaluateExp(false);
        assertEq("6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp210Test(){
        NumericString numeric_ = new NumericString("1+2+(3+0)");
        numeric_.evaluateExp(false);
        assertEq("6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp211Test(){
        NumericString numeric_ = new NumericString("1+2*-3+3");
        numeric_.evaluateExp(false);
        assertEq("-2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp212Test(){
        NumericString numeric_ = new NumericString("2*-3+1+2+3");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp213Test(){
        NumericString numeric_ = new NumericString("2*-3+1+2+3+4");
        numeric_.evaluateExp(false);
        assertEq("4",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp214Test(){
        NumericString numeric_ = new NumericString("abs(2*-3+1)+(1+2+3+4)");
        numeric_.evaluateExp(false);
        assertEq("15",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp215Test(){
        NumericString numeric_ = new NumericString("abs(2*-3+1)+(1+2-3+4)");
        numeric_.evaluateExp(false);
        assertEq("9",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp216Test(){
        NumericString numeric_ = new NumericString("abs(2*-3+1)+(1+2-3+4+00)");
        numeric_.evaluateExp(false);
        assertEq("9",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp217Test(){
        NumericString numeric_ = new NumericString("abs(2*-3+1)+(1+2-3+4+001)");
        numeric_.evaluateExp(false);
        assertEq("10",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp218Test(){
        NumericString numeric_ = new NumericString("12-7*2*3");
        numeric_.evaluateExp(false);
        assertEq("-30",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp219Test(){
        NumericString numeric_ = new NumericString("2*6-7*2*3");
        numeric_.evaluateExp(false);
        assertEq("-30",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp220Test(){
        NumericString numeric_ = new NumericString("2*(2*6-7*2*3)");
        numeric_.evaluateExp(false);
        assertEq("-60",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp221Test(){
        NumericString numeric_ = new NumericString("2*-3+1+2-3");
        numeric_.evaluateExp(false);
        assertEq("-6",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp222Test(){
        NumericString numeric_ = new NumericString("2+2*(5+0*1)");
        numeric_.evaluateExp(false);
        assertEq("12",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp223Test(){
        NumericString numeric_ = new NumericString("2+2*(5+(0*1+0))");
        numeric_.evaluateExp(false);
        assertEq("12",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp224Test(){
        NumericString numeric_ = new NumericString("cardinal({})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp225Test(){
        NumericString numeric_ = new NumericString("cardinal({MY_STRING_1})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp226Test(){
        NumericString numeric_ = new NumericString("cardinal(union({MY_STRING_1;MY_STRING_3},{MY_STRING_2;MY_STRING_3}))");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp227Test(){
        NumericString numeric_ = new NumericString("cardinal({my_string;MY_STRING})");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp228Test(){
        NumericString numeric_ = new NumericString("cardinal({MY_STRING_cardinal;MY_STRING_CARDINAL})");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp229Test(){
        NumericString numeric_ = new NumericString("cardinal({MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp230Test(){
        NumericString numeric_ = new NumericString("cardinal({MY_STRING_ONE;MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp231Test(){
        NumericString numeric_ = new NumericString("cardinal({MY_STRING_ONE;MY_STRING_TWO;MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp232Test(){
        NumericString numeric_ = new NumericString("cardinal(inter({MY_STRING_ONE},{MY_STRING_ONE}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp233Test(){
        NumericString numeric_ = new NumericString("cardinal(inter({MY_STRING_ONE},{MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp234Test(){
        NumericString numeric_ = new NumericString("cardinal(inter({MY_STRING_ONE},{}))");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp235Test(){
        NumericString numeric_ = new NumericString("cardinal(inter({},{MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp236Test(){
        NumericString numeric_ = new NumericString("cardinal(inter({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp237Test(){
        NumericString numeric_ = new NumericString("cardinal(union({MY_STRING_ONE},{}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp238Test(){
        NumericString numeric_ = new NumericString("cardinal(union({},{MY_STRING_ONE}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp239Test(){
        NumericString numeric_ = new NumericString("cardinal(union({},{}))");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp240Test(){
        NumericString numeric_ = new NumericString("cardinal(union({MY_STRING_ONE},{MY_STRING_ONE}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp241Test(){
        NumericString numeric_ = new NumericString("cardinal(union({MY_STRING_ONE},{MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp242Test(){
        NumericString numeric_ = new NumericString("cardinal(union({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}))");
        numeric_.evaluateExp(false);
        assertEq("3",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp243Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({},{}))");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp244Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({},{MY_STRING_ONE}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp245Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({},{MY_STRING_ONE;MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp246Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_ONE},{}))");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp247Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_ONE},{MY_STRING_ONE}))");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp248Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_ONE},{MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp249Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_ONE},{MY_STRING_ONE;MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp250Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_THREE},{MY_STRING_ONE;MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp251Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_THREE;MY_STRING_ONE},{MY_STRING_ONE;MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp252Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_THREE;MY_STRING_ONE;MY_STRING_FOUR},{MY_STRING_ONE;MY_STRING_TWO}))");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp253Test(){
        NumericString numeric_ = new NumericString("cardinal(complementaire({MY_STRING_THREE;MY_STRING_ONE;MY_STRING_FIVE},{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_FOUR}))");
        numeric_.evaluateExp(false);
        assertEq("2",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp254Test(){
        NumericString numeric_ = new NumericString("egalnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp255Test(){
        NumericString numeric_ = new NumericString("egalnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp256Test(){
        NumericString numeric_ = new NumericString("inclusnum(vide,vide)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp257Test(){
        NumericString numeric_ = new NumericString("inclusnum(vide,{MY_STRING_ONE;MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp258Test(){
        NumericString numeric_ = new NumericString("inclusnum({MY_STRING_ONE},{MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp259Test(){
        NumericString numeric_ = new NumericString("inclusnum({MY_STRING_ONE;MY_STRING_TWO},vide)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp260Test(){
        NumericString numeric_ = new NumericString("inclusnum({MY_STRING_ONE;MY_STRING_TWO},{})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp261Test(){
        NumericString numeric_ = new NumericString("inclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp262Test(){
        NumericString numeric_ = new NumericString("inclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp263Test(){
        NumericString numeric_ = new NumericString("noninclusnum(vide,vide)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp264Test(){
        NumericString numeric_ = new NumericString("noninclusnum(vide,{MY_STRING_ONE;MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp265Test(){
        NumericString numeric_ = new NumericString("noninclusnum({MY_STRING_ONE},{MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp266Test(){
        NumericString numeric_ = new NumericString("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},vide)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp267Test(){
        NumericString numeric_ = new NumericString("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},{})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp268Test(){
        NumericString numeric_ = new NumericString("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp269Test(){
        NumericString numeric_ = new NumericString("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp270Test(){
        NumericString numeric_ = new NumericString("egalnum({MY_STRING_ONE},vide)");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp271Test(){
        NumericString numeric_ = new NumericString("egalnum(vide,{MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp272Test(){
        NumericString numeric_ = new NumericString("egalnum(inter({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp273Test(){
        NumericString numeric_ = new NumericString("egalnum(inter({MY_STRING_ONE},{MY_STRING_TWO}),{})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp274Test(){
        NumericString numeric_ = new NumericString("egalnum(inter({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp275Test(){
        NumericString numeric_ = new NumericString("egalnum(union({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp276Test(){
        NumericString numeric_ = new NumericString("egalnum(union({MY_STRING_ONE},{MY_STRING_TWO}),{MY_STRING_ONE;MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp277Test(){
        NumericString numeric_ = new NumericString("egalnum(union({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_THREE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp278Test(){
        NumericString numeric_ = new NumericString("differentnum({MY_STRING_ONE},vide)");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp279Test(){
        NumericString numeric_ = new NumericString("differentnum(vide,{MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp280Test(){
        NumericString numeric_ = new NumericString("differentnum({MY_STRING_TWO},{MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("1",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp281Test(){
        NumericString numeric_ = new NumericString("differentnum(inter({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp282Test(){
        NumericString numeric_ = new NumericString("differentnum(inter({MY_STRING_ONE},{MY_STRING_TWO}),{})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp283Test(){
        NumericString numeric_ = new NumericString("differentnum(inter({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp284Test(){
        NumericString numeric_ = new NumericString("differentnum(union({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp285Test(){
        NumericString numeric_ = new NumericString("differentnum(union({MY_STRING_ONE},{MY_STRING_TWO}),{MY_STRING_ONE;MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp286Test(){
        NumericString numeric_ = new NumericString("differentnum(union({12my_string},{MY_STRING_TWO}),{12my_string;MY_STRING_TWO})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
    @Test
    public void evaluateExp287Test(){
        NumericString numeric_ = new NumericString("differentnum(union({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_THREE})");
        numeric_.evaluateExp(false);
        assertEq("0",numeric_.display());
        assertEq(false, numeric_.isError());
    }
}