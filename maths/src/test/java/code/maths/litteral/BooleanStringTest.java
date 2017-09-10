package code.maths.litteral;
import static code.util.opers.EquallableUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertNull;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.maths.exceptions.FormatException;
import code.maths.exceptions.MathStringFormatException;
import code.util.StringMap;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class BooleanStringTest {

    Object[] booleanStrings() {
        return $($(BooleanString.TRUE,true),
                $(BooleanString.FALSE,false),
                $("1=1",true),
                $("1=abs(1)",true),
                $("1=2",false),
                $("1!=1",false),
                $("1!=abs(1)",false),
                $("1!=2",true),
                $("1<0",false),
                $("1<1",false),
                $("1<2",true),
                $("1<=0",false),
                $("1<=1",true),
                $("1<=2",true),
                $("1>0",true),
                $("1>1",false),
                $("1>2",false),
                $("1>=0",true),
                $("1>=1",true),
                $("1>=2",false),
                $("1=1&2<3",true),
                $("1=1&2<puis(3,2)",true),
                $("1=1&2>3",false),
                $("1!=1&2>3",false),
                $("1=1|2<3",true),
                $("1=1|2<puis(2,3)",true),
                $("1=1|2>3&4<8&1>0",true),
                $("(1!=1|2>3|5<6)&4<8&1>0",true),
                $("1=1|2>3",true),
                $("1!=1|2>3",false),
                $("1!=1|2<3",true),
                $("2<3|1!=1&4<3",true),
                $("2<3|1!=abs(1)&4<3",true),
                $("(2<3|1!=1)&4<3",false),
                $("(2<3|1!=abs(1))&4<3",false),
                $("(2<3|abs(1)!=1)&4<3",false),
                $("4<3&(2<3|abs(1)!=1)",false),
                $("4<3&(abs(1)!=1|2<3)",false),
                $("(2<3|1!=abs(1))&puis(2,2)<3",false),
                $("2<3&(((2<3|1!=abs(1))&puis(2,2)<3)|puis(2,2)<3)",false),
                $("2<3&(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))",false),
//                $("(2<3&1=1|2<4)=V",true),
                $("2<3&1=1|2<4",true),
                $("2<3&((1=1))|2<4",true),
                $("2<3&((1)=(1))|2<4",true),
                $("2<3&((1))=((1))|2<4",true),
                $("V&1=1|2<4",true),
                $("F|1=1|2<4",true),
                $("2<3&!(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))",true),
                $("2<3&!!(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))",false),
                $("((2<3)|1!=(abs(1)))&puis(2,2)<3",false),
                $("2<3&((1)=(1))|(2<4&6>=5)",true),
                $("1=1|(2<4&6>=5)",true),
                $("cardinal({V;F})=2",true),
                $("cardinal({1a;2b})=2",true),
                $("cardinal({V;F})=2&(V)",true),
                $("cardinal({1a;2b})=2&(F)",false),
                $("1<=1&(1+1=2&2+3=5|5<6)",true),
                $("1<=1&(1+1=2&(2+3=5|5<6))",true),
                $("1<=1|(1+1=2&1+1=2&(2+3=5|5<6))",true),
                $("1<=1|(1+1=2|1+1=2&1+1=2&(2+3=5|5<6&5<6))",true),
                $("1<=1&(1+1=2&1+1=2&(2+3=5|5<6))",true),
                $("1>=1&(1<=1|(1+1=2|(2+3=5|5<6)))",true),
                $("1<=1|(1+1=2|(2+3=5|5<6))",true),
//                $("(2<3)=(1=1)",true),
//                $("(-2<3)=(1=1)",true),
//                $("(2<3)=(1!=1)",false),
//                $("(2>3)=(1!=1)",true),
//                $("(2>3)=(1=1)",false),
//                $("(2<3)!=(1=1)",false),
//                $("(2>3)!=(1=1)",true),
//                $("(2<3)!=(1!=1)",true),
//                $("(2>3)!=(1!=1)",false),
//                $("(-2>3)!=(1!=1)",false),
                $("-1<4",true),
                $("-1>4",false),
                $("-1<=4",true),
                $("-1>=4",false),
                $("-1=4",false),
                $("-1!=4",true),
                $("1<-4",false),
                $("1>-4",true),
                $("1<=-4",false),
                $("1>=-4",true),
                $("1=-4",false),
                $("1!=-4",true),
                $("((1!=-4))",true),
                $("1+1=2|2+2=4|3+3=6",true),
                $("(V)",true),
                $("((V))",true),
                $("(F)",false),
                $("((F))",false),
                $("6+7>10&(1<2|4>3)",true),
                $("6+7>10&(V|4>3)",true),
                $("6+7>10&(1<2|V)",true),
                $("6+7<=10&(1<2|4>3)",false),
                $("6+7<=10&(V|4>3)",false),
                $("6+7<=10&(1<2|V)",false));
    }

    Object[] evaluateCheck() {
        return $($("1/2+1=3/2",true),
                $("1/0+1=2",true));
    }

    Object[] evaluateExpressionFail() {
        return $($("1/0+1=2"));
    }

    @Test(timeout=5000)
    @Parameters(method="booleanStrings")
    public void evaluateExp_noCheck1Test(String _numericString,boolean _valid) {
        BooleanString numeric_ = new BooleanString(_numericString);
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertEq(_valid,numeric_.getResult().booleanValue());
    }

    @Test
    public void evaluate1Test() {
        boolean default_ = true;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test
    public void evaluate2Test() {
        boolean default_ = false;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test
    public void evaluate3Test() {
        boolean default_ = false;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "1+1<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }


    @Test
    public void evaluate4Test() {
        boolean default_ = true;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "1+1>3";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test
    public void evaluate5Test() {
        boolean default_ = false;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "VARIABLE<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test
    public void evaluate6Test() {
        boolean default_ = true;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "VARIABLE>3";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test
    public void evaluate7Test() {
        boolean default_ = false;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "1:VARIABLE<3";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test
    public void evaluate8Test() {
        boolean default_ = true;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "1:VARIABLE<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test
    public void evaluate9Test() {
        boolean default_ = false;
        //NumericString.setCheckSyntax(true);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, true, default_));
    }

    @Test
    public void evaluate10Test() {
        boolean default_ = true;
        //NumericString.setCheckSyntax(true);
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, true, default_));
    }

    @Test
    public void evaluate11Test() {
        boolean default_ = false;
        //NumericString.setCheckSyntax(false);
        StringMap<String> variables_ = new StringMap<String>();
        String numericString_ = "1:1<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }

    @Test(timeout=5000)
    @Parameters(method="evaluateCheck")
    public void evaluateExp_check2Test(String _numericString,boolean _res) {
        BooleanString numeric_ = new BooleanString(_numericString);
        //NumericString.setCheckSyntax(true);
        numeric_.evaluateExp(true);
        assertEq(_res,numeric_.getResult().booleanValue());
    }

    @Test
    public void evaluateExp1FailTest() {
        BooleanString numeric_ = new BooleanString("");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test(expected=FormatException.class)
    @Parameters(method="evaluateExpressionFail")
    public void evaluateExp2FailTest(String _input) {
        BooleanString numeric_ = new BooleanString(_input);
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
    }

    @Test
    public void evaluateExp3FailTest() {
        BooleanString numeric_ = new BooleanString("(2*1+1):0<1");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp4FailTest() {
        BooleanString numeric_ = new BooleanString("1+1\"2");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp5FailTest() {
        BooleanString numeric_ = new BooleanString("(2*1+1)<1:0");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp6FailTest() {
        BooleanString numeric_ = new BooleanString("1<1:0");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp7FailTest() {
        BooleanString numeric_ = new BooleanString("F=");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp8FailTest() {
        BooleanString numeric_ = new BooleanString("1/2=");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp9FailTest() {
        BooleanString numeric_ = new BooleanString("R=S");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp10FailTest() {
        BooleanString numeric_ = new BooleanString("(1+1)&");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp11FailTest() {
        BooleanString numeric_ = new BooleanString("(1+1)|");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp12FailTest() {
        BooleanString numeric_ = new BooleanString("(1+1)&(1+1)&");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp13FailTest() {
        BooleanString numeric_ = new BooleanString("(1+1)|(1+1)|");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp14FailTest() {
        BooleanString numeric_ = new BooleanString("(1+1)|(1+1)&");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp15FailTest() {
        BooleanString numeric_ = new BooleanString("1+1=2&");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp16FailTest() {
        BooleanString numeric_ = new BooleanString("1+1=2|");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp17FailTest() {
        BooleanString numeric_ = new BooleanString("1+1=2&1+1=2&");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp18FailTest() {
        BooleanString numeric_ = new BooleanString("1+1=2|1+1=2|");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp19FailTest() {
        BooleanString numeric_ = new BooleanString("1+1=2|1+1=2&");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp20FailTest() {
        BooleanString numeric_ = new BooleanString("(1+1=2|2+2=4|3+3=6");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp21FailTest() {
        BooleanString numeric_ = new BooleanString("1+1=2|2+2=4|3+3=6)");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp22FailTest() {
        BooleanString numeric_ = new BooleanString("|2+2=4|3+3=6)");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test(expected=MathStringFormatException.class)
    public void evaluateExp23FailTest() {
        BooleanString numeric_ = new BooleanString("(2+2=4|3+3=6|");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
    }

    @Test(expected=MathStringFormatException.class)
    public void evaluateExp24FailTest() {
        BooleanString numeric_ = new BooleanString("(2+2=4|(2+2=4|3+3=6)|");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
    }

    @Test(expected=MathStringFormatException.class)
    public void evaluateExp25FailTest() {
        BooleanString numeric_ = new BooleanString("(V|(V|V)|");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
    }

    @Test
    public void evaluateExp26FailTest() {
        BooleanString numeric_ = new BooleanString("1/2<");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp27FailTest() {
        BooleanString numeric_ = new BooleanString("<1/2");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp28FailTest() {
        BooleanString numeric_ = new BooleanString("1/<2");
        ///NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp29FailTest() {
        BooleanString numeric_ = new BooleanString("1</2");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp30FailTest() {
        BooleanString numeric_ = new BooleanString("(V|V|V|V");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp31FailTest() {
        BooleanString numeric_ = new BooleanString("1!");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp32FailTest() {
        BooleanString numeric_ = new BooleanString("(V|V|V|(V|V|V|V");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }


    @Test
    public void evaluateExp33FailTest() {
        BooleanString numeric_ = new BooleanString("|(V|V)");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp34FailTest() {
        BooleanString numeric_ = new BooleanString("2+2=4|(2+2=4|3+3=6");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp35FailTest() {
        BooleanString numeric_ = new BooleanString("2+2<=");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp36FailTest() {
        BooleanString numeric_ = new BooleanString("2+2=-");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp37FailTest() {
        BooleanString numeric_ = new BooleanString("2+2=-1/");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp38FailTest() {
        BooleanString numeric_ = new BooleanString("2+2=-1/3&");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }

    @Test
    public void evaluateExp39FailTest() {
        BooleanString numeric_ = new BooleanString("2+2=");
        //NumericString.setCheckSyntax(false);
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
}
