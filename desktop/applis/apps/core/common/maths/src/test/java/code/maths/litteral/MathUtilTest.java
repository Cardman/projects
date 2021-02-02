package code.maths.litteral;

import code.maths.EquallableMathUtil;
import org.junit.Test;

import code.maths.Rate;
import code.util.StringMap;


public class MathUtilTest extends EquallableMathUtil {
    @Test
    public void evaluateExp1Test(){
        Argument a_ = MathUtil.processEl("1", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2Test(){
        Argument a_ = MathUtil.processEl(".0", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp3Test(){
        Argument a_ = MathUtil.processEl("-.0", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp4Test(){
        Argument a_ = MathUtil.processEl("-.5", false, new StringMap<String>());
        assertEq(new Rate("-1/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp5Test(){
        Argument a_ = MathUtil.processEl(".5", false, new StringMap<String>());
        assertEq(new Rate("1/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp6Test(){
        Argument a_ = MathUtil.processEl("1.", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp7Test(){
        Argument a_ = MathUtil.processEl("1.5", false, new StringMap<String>());
        assertEq(new Rate("3/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp8Test(){
        Argument a_ = MathUtil.processEl("-1.", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp9Test(){
        Argument a_ = MathUtil.processEl("-1.5", false, new StringMap<String>());
        assertEq(new Rate("-3/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp10Test(){
        Argument a_ = MathUtil.processEl("0.0", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp11Test(){
        Argument a_ = MathUtil.processEl("0.2", false, new StringMap<String>());
        assertEq(new Rate("1/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp12Test(){
        Argument a_ = MathUtil.processEl("15/7", false, new StringMap<String>());
        assertEq(new Rate("15/7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp13Test(){
        Argument a_ = MathUtil.processEl("0.1", false, new StringMap<String>());
        assertEq(new Rate("1/10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp14Test(){
        Argument a_ = MathUtil.processEl("0.2", false, new StringMap<String>());
        assertEq(new Rate("1/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp15Test(){
        Argument a_ = MathUtil.processEl("15/5", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp16Test(){
        Argument a_ = MathUtil.processEl("-3", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp17Test(){
        Argument a_ = MathUtil.processEl("1+2*5*7", false, new StringMap<String>());
        assertEq(new Rate("71"),a_.getRateVal());
    }
    @Test
    public void evaluateExp18Test(){
        Argument a_ = MathUtil.processEl("1-2*5*7", false, new StringMap<String>());
        assertEq(new Rate("-69"),a_.getRateVal());
    }
    @Test
    public void evaluateExp19Test(){
        Argument a_ = MathUtil.processEl("1+3*5", false, new StringMap<String>());
        assertEq(new Rate("16"),a_.getRateVal());
    }
    @Test
    public void evaluateExp20Test(){
        Argument a_ = MathUtil.processEl("1+3+5", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp21Test(){
        Argument a_ = MathUtil.processEl("1-3+5", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp22Test(){
        Argument a_ = MathUtil.processEl("1-3*5", false, new StringMap<String>());
        assertEq(new Rate("-14"),a_.getRateVal());
    }
    @Test
    public void evaluateExp23Test(){
        Argument a_ = MathUtil.processEl("-3*5", false, new StringMap<String>());
        assertEq(new Rate("-15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp24Test(){
        Argument a_ = MathUtil.processEl("-3-5", false, new StringMap<String>());
        assertEq(new Rate("-8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp25Test(){
        Argument a_ = MathUtil.processEl("3-5", false, new StringMap<String>());
        assertEq(new Rate("-2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp26Test(){
        Argument a_ = MathUtil.processEl("1+4", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp27Test(){
        Argument a_ = MathUtil.processEl("1+4/5", false, new StringMap<String>());
        assertEq(new Rate("9/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp28Test(){
        Argument a_ = MathUtil.processEl("5*3", false, new StringMap<String>());
        assertEq(new Rate("15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp29Test(){
        Argument a_ = MathUtil.processEl("5*3/7", false, new StringMap<String>());
        assertEq(new Rate("15/7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp30Test(){
        Argument a_ = MathUtil.processEl("5:3/7", false, new StringMap<String>());
        assertEq(new Rate("35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp31Test(){
        Argument a_ = MathUtil.processEl("div(5,3/7)", false, new StringMap<String>());
        assertEq(new Rate("35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp32Test(){
        Argument a_ = MathUtil.processEl("5:-3/7", false, new StringMap<String>());
        assertEq(new Rate("-35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp33Test(){
        Argument a_ = MathUtil.processEl("div(5,-3/7)", false, new StringMap<String>());
        assertEq(new Rate("-35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp34Test(){
        Argument a_ = MathUtil.processEl("8-6", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp35Test(){
        Argument a_ = MathUtil.processEl("--5", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp36Test(){
        Argument a_ = MathUtil.processEl("---5", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp37Test(){
        Argument a_ = MathUtil.processEl("(--5+8)", false, new StringMap<String>());
        assertEq(new Rate("13"),a_.getRateVal());
    }
    @Test
    public void evaluateExp38Test(){
        Argument a_ = MathUtil.processEl("(---5+8)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp39Test(){
        Argument a_ = MathUtil.processEl("1*--5+8", false, new StringMap<String>());
        assertEq(new Rate("13"),a_.getRateVal());
    }
    @Test
    public void evaluateExp40Test(){
        Argument a_ = MathUtil.processEl("1*---5+8", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp41Test(){
        Argument a_ = MathUtil.processEl("9--5", false, new StringMap<String>());
        assertEq(new Rate("14"),a_.getRateVal());
    }
    @Test
    public void evaluateExp42Test(){
        Argument a_ = MathUtil.processEl("13---4", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp43Test(){
        Argument a_ = MathUtil.processEl("3*8+6", false, new StringMap<String>());
        assertEq(new Rate("30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp44Test(){
        Argument a_ = MathUtil.processEl("(3*8+6)", false, new StringMap<String>());
        assertEq(new Rate("30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp45Test(){
        Argument a_ = MathUtil.processEl("1-2*-3", false, new StringMap<String>());
        assertEq(new Rate("7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp46Test(){
        Argument a_ = MathUtil.processEl("1-2*-3*5", false, new StringMap<String>());
        assertEq(new Rate("31"),a_.getRateVal());
    }
    @Test
    public void evaluateExp47Test(){
        Argument a_ = MathUtil.processEl("1-2*3*-5", false, new StringMap<String>());
        assertEq(new Rate("31"),a_.getRateVal());
    }
    @Test
    public void evaluateExp48Test(){
        Argument a_ = MathUtil.processEl("1-2*3*5", false, new StringMap<String>());
        assertEq(new Rate("-29"),a_.getRateVal());
    }
    @Test
    public void evaluateExp49Test(){
        Argument a_ = MathUtil.processEl("2*(1-2*-3)", false, new StringMap<String>());
        assertEq(new Rate("14"),a_.getRateVal());
    }
    @Test
    public void evaluateExp50Test(){
        Argument a_ = MathUtil.processEl("2*(1-2*-3*5)", false, new StringMap<String>());
        assertEq(new Rate("62"),a_.getRateVal());
    }
    @Test
    public void evaluateExp51Test(){
        Argument a_ = MathUtil.processEl("2*(1-2*3*-5)", false, new StringMap<String>());
        assertEq(new Rate("62"),a_.getRateVal());
    }
    @Test
    public void evaluateExp52Test(){
        Argument a_ = MathUtil.processEl("2*(1-2*3*5)", false, new StringMap<String>());
        assertEq(new Rate("-58"),a_.getRateVal());
    }
    @Test
    public void evaluateExp53Test(){
        Argument a_ = MathUtil.processEl("((3))", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp54Test(){
        Argument a_ = MathUtil.processEl("3*(8+6)", false, new StringMap<String>());
        assertEq(new Rate("42"),a_.getRateVal());
    }
    @Test
    public void evaluateExp55Test(){
        Argument a_ = MathUtil.processEl("quot(5,6)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp56Test(){
        Argument a_ = MathUtil.processEl("-1*quot(5,6)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp57Test(){
        Argument a_ = MathUtil.processEl("-quot(5,6)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp58Test(){
        Argument a_ = MathUtil.processEl("quot(15,6)", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp59Test(){
        Argument a_ = MathUtil.processEl("quot(-15,6)", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp60Test(){
        Argument a_ = MathUtil.processEl("mod(15,6)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp61Test(){
        Argument a_ = MathUtil.processEl("mod(15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp62Test(){
        Argument a_ = MathUtil.processEl("mod(-15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp63Test(){
        Argument a_ = MathUtil.processEl("mod(-5,6)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp64Test(){
        Argument a_ = MathUtil.processEl("modtaux(15,6)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp65Test(){
        Argument a_ = MathUtil.processEl("modtaux(15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp66Test(){
        Argument a_ = MathUtil.processEl("modtaux(-15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp67Test(){
        Argument a_ = MathUtil.processEl("modtaux(-5,6)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp68Test(){
        Argument a_ = MathUtil.processEl("puis(3,4)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp69Test(){
        Argument a_ = MathUtil.processEl("puis(-3,3)", false, new StringMap<String>());
        assertEq(new Rate("-27"),a_.getRateVal());
    }
    @Test
    public void evaluateExp70Test(){
        Argument a_ = MathUtil.processEl("puis(-2,-3)", false, new StringMap<String>());
        assertEq(new Rate("-1/8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp71Test(){
        Argument a_ = MathUtil.processEl("puis(2,-3)", false, new StringMap<String>());
        assertEq(new Rate("1/8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp72Test(){
        Argument a_ = MathUtil.processEl("puis(0,0)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp73Test(){
        Argument a_ = MathUtil.processEl("puis(0,1/2)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp74Test(){
        Argument a_ = MathUtil.processEl("puis(9,1/2)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp75Test(){
        Argument a_ = MathUtil.processEl("puis(9,-1/2)", false, new StringMap<String>());
        assertEq(new Rate("1/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp76Test(){
        Argument a_ = MathUtil.processEl("puis(-9,1/2)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp77Test(){
        Argument a_ = MathUtil.processEl("puis(-9,-1/2)", false, new StringMap<String>());
        assertEq(new Rate("1/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp78Test(){
        Argument a_ = MathUtil.processEl("puis(-27,1/3)", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp79Test(){
        Argument a_ = MathUtil.processEl("puis(-27,2/3)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp80Test(){
        Argument a_ = MathUtil.processEl("puis(-27,-1/3)", false, new StringMap<String>());
        assertEq(new Rate("-1/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp81Test(){
        Argument a_ = MathUtil.processEl("puis(-27,-2/3)", false, new StringMap<String>());
        assertEq(new Rate("1/9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp82Test(){
        Argument a_ = MathUtil.processEl("3*puis(1+2,2)--9", false, new StringMap<String>());
        assertEq(new Rate("36"),a_.getRateVal());
    }
    @Test
    public void evaluateExp83Test(){
        Argument a_ = MathUtil.processEl("3*puis(1+2,2):3--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp84Test(){
        Argument a_ = MathUtil.processEl("3*div(puis(1+2,2),3)--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp85Test(){
        Argument a_ = MathUtil.processEl("3*puis(1+2,5-3):3--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp86Test(){
        Argument a_ = MathUtil.processEl("3*div(puis(1+2,5-3),3)--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp87Test(){
        Argument a_ = MathUtil.processEl("abs(9)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp88Test(){
        Argument a_ = MathUtil.processEl("abs(-5)", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp89Test(){
        Argument a_ = MathUtil.processEl("abs(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp90Test(){
        Argument a_ = MathUtil.processEl("sgn(9)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp91Test(){
        Argument a_ = MathUtil.processEl("sgn(-5)", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp92Test(){
        Argument a_ = MathUtil.processEl("sgn(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp93Test(){
        Argument a_ = MathUtil.processEl("ent(-5)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp94Test(){
        Argument a_ = MathUtil.processEl("ent(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp95Test(){
        Argument a_ = MathUtil.processEl("ent(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp96Test(){
        Argument a_ = MathUtil.processEl("ent(4)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp97Test(){
        Argument a_ = MathUtil.processEl("ent(6/5)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp98Test(){
        Argument a_ = MathUtil.processEl("troncature(-5)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp99Test(){
        Argument a_ = MathUtil.processEl("troncature(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("-2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp100Test(){
        Argument a_ = MathUtil.processEl("troncature(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp101Test(){
        Argument a_ = MathUtil.processEl("troncature(4)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp102Test(){
        Argument a_ = MathUtil.processEl("troncature(6/5)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp103Test(){
        Argument a_ = MathUtil.processEl("num(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp104Test(){
        Argument a_ = MathUtil.processEl("num(4)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp105Test(){
        Argument a_ = MathUtil.processEl("num(6/5)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp106Test(){
        Argument a_ = MathUtil.processEl("den(0)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp107Test(){
        Argument a_ = MathUtil.processEl("den(4)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp108Test(){
        Argument a_ = MathUtil.processEl("den(6/5)", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp109Test(){
        Argument a_ = MathUtil.processEl("num(-5)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp110Test(){
        Argument a_ = MathUtil.processEl("num(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp111Test(){
        Argument a_ = MathUtil.processEl("den(-5)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp112Test(){
        Argument a_ = MathUtil.processEl("den(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp113Test(){
        Argument a_ = MathUtil.processEl("caracferme(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp114Test(){
        Argument a_ = MathUtil.processEl("caracferme(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp115Test(){
        Argument a_ = MathUtil.processEl("caracferme(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp116Test(){
        Argument a_ = MathUtil.processEl("caracferme(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp117Test(){
        Argument a_ = MathUtil.processEl("caracferme(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp118Test(){
        Argument a_ = MathUtil.processEl("caracferme(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp119Test(){
        Argument a_ = MathUtil.processEl("caracferme(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp120Test(){
        Argument a_ = MathUtil.processEl("caracferme(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp121Test(){
        Argument a_ = MathUtil.processEl("caracferme(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp122Test(){
        Argument a_ = MathUtil.processEl("caracferme(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp123Test(){
        Argument a_ = MathUtil.processEl("caracouvert(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp124Test(){
        Argument a_ = MathUtil.processEl("caracouvert(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp125Test(){
        Argument a_ = MathUtil.processEl("caracouvert(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp126Test(){
        Argument a_ = MathUtil.processEl("caracouvert(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp127Test(){
        Argument a_ = MathUtil.processEl("caracouvert(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp128Test(){
        Argument a_ = MathUtil.processEl("caracouvert(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp129Test(){
        Argument a_ = MathUtil.processEl("caracouvert(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp130Test(){
        Argument a_ = MathUtil.processEl("caracouvert(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp131Test(){
        Argument a_ = MathUtil.processEl("caracouvert(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp132Test(){
        Argument a_ = MathUtil.processEl("caracouvert(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp133Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp134Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp135Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp136Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp137Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp138Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp139Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp140Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp141Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp142Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertg(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp143Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp144Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp145Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp146Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp147Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp148Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp149Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp150Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp151Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp152Test(){
        Argument a_ = MathUtil.processEl("caracsemiouvertd(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp153Test(){
        Argument a_ = MathUtil.processEl("caracdroiteouvert(2,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp154Test(){
        Argument a_ = MathUtil.processEl("caracdroiteouvert(1,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp155Test(){
        Argument a_ = MathUtil.processEl("caracdroiteouvert(0,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp156Test(){
        Argument a_ = MathUtil.processEl("caracdroiteferme(2,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp157Test(){
        Argument a_ = MathUtil.processEl("caracdroiteferme(1,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp158Test(){
        Argument a_ = MathUtil.processEl("caracdroiteferme(0,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp159Test(){
        Argument a_ = MathUtil.processEl("caracgaucheouvert(2,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp160Test(){
        Argument a_ = MathUtil.processEl("caracgaucheouvert(1,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp161Test(){
        Argument a_ = MathUtil.processEl("caracgaucheouvert(0,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp162Test(){
        Argument a_ = MathUtil.processEl("caracgaucheferme(2,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp163Test(){
        Argument a_ = MathUtil.processEl("caracgaucheferme(1,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp164Test(){
        Argument a_ = MathUtil.processEl("caracgaucheferme(0,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp165Test(){
        Argument a_ = MathUtil.processEl("puis(1+5,1+2)", false, new StringMap<String>());
        assertEq(new Rate("216"),a_.getRateVal());
    }
    @Test
    public void evaluateExp166Test(){
        Argument a_ = MathUtil.processEl("puis(1+5,1*2)", false, new StringMap<String>());
        assertEq(new Rate("36"),a_.getRateVal());
    }
    @Test
    public void evaluateExp167Test(){
        Argument a_ = MathUtil.processEl("abs(4+6)", false, new StringMap<String>());
        assertEq(new Rate("10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp168Test(){
        Argument a_ = MathUtil.processEl("abs((4+6))", false, new StringMap<String>());
        assertEq(new Rate("10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp169Test(){
        Argument a_ = MathUtil.processEl("min(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp170Test(){
        Argument a_ = MathUtil.processEl("max(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp171Test(){
        Argument a_ = MathUtil.processEl("min(1+7,1+5)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp172Test(){
        Argument a_ = MathUtil.processEl("max(1+7,1+5)", false, new StringMap<String>());
        assertEq(new Rate("8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp173Test(){
        Argument a_ = MathUtil.processEl("moy(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp174Test(){
        Argument a_ = MathUtil.processEl("var(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp175Test(){
        Argument a_ = MathUtil.processEl("min(4+19,max(12,15))", false, new StringMap<String>());
        assertEq(new Rate("15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp176Test(){
        Argument a_ = MathUtil.processEl("2*min(4+19,max(12,15))", false, new StringMap<String>());
        assertEq(new Rate("30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp177Test(){
        Argument a_ = MathUtil.processEl("(4+12)*2", false, new StringMap<String>());
        assertEq(new Rate("32"),a_.getRateVal());
    }
    @Test
    public void evaluateExp178Test(){
        Argument a_ = MathUtil.processEl("2:-min(4+19,max(12,15))", false, new StringMap<String>());
        assertEq(new Rate("-2/15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp179Test(){
        Argument a_ = MathUtil.processEl("3:-min(4+19,max(12,15))", false, new StringMap<String>());
        assertEq(new Rate("-1/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp180Test(){
        Argument a_ = MathUtil.processEl("puis(4*div(1,(3060)),1/4)", false, new StringMap<String>());
        assertEq(new Rate("29/153"),a_.getRateVal());
    }
    @Test
    public void evaluateExp181Test(){
        Argument a_ = MathUtil.processEl("puis(3,(1+1)*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp182Test(){
        Argument a_ = MathUtil.processEl("puis(3,2*(1+1))", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp183Test(){
        Argument a_ = MathUtil.processEl("puis(2*1+1,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp184Test(){
        Argument a_ = MathUtil.processEl("puis(3,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp185Test(){
        Argument a_ = MathUtil.processEl("puis(2+1*(1+0),2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp186Test(){
        Argument a_ = MathUtil.processEl("min(2,2+1*1,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp187Test(){
        Argument a_ = MathUtil.processEl("2*min(2,2+1*1,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp188Test(){
        Argument a_ = MathUtil.processEl("1+2+3", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp189Test(){
        Argument a_ = MathUtil.processEl("1+(2+3)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp190Test(){
        Argument a_ = MathUtil.processEl("1+2+(3+0)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp191Test(){
        Argument a_ = MathUtil.processEl("1+2*-3+3", false, new StringMap<String>());
        assertEq(new Rate("-2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp192Test(){
        Argument a_ = MathUtil.processEl("2*-3+1+2+3", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp193Test(){
        Argument a_ = MathUtil.processEl("2*-3+1+2+3+4", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp194Test(){
        Argument a_ = MathUtil.processEl("abs(2*-3+1)+(1+2+3+4)", false, new StringMap<String>());
        assertEq(new Rate("15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp195Test(){
        Argument a_ = MathUtil.processEl("abs(2*-3+1)+(1+2-3+4)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp196Test(){
        Argument a_ = MathUtil.processEl("abs(2*-3+1)+(1+2-3+4+00)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp197Test(){
        Argument a_ = MathUtil.processEl("abs(2*-3+1)+(1+2-3+4+001)", false, new StringMap<String>());
        assertEq(new Rate("10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp198Test(){
        Argument a_ = MathUtil.processEl("12-7*2*3", false, new StringMap<String>());
        assertEq(new Rate("-30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp199Test(){
        Argument a_ = MathUtil.processEl("2*6-7*2*3", false, new StringMap<String>());
        assertEq(new Rate("-30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp200Test(){
        Argument a_ = MathUtil.processEl("2*(2*6-7*2*3)", false, new StringMap<String>());
        assertEq(new Rate("-60"),a_.getRateVal());
    }
    @Test
    public void evaluateExp201Test(){
        Argument a_ = MathUtil.processEl("2*-3+1+2-3", false, new StringMap<String>());
        assertEq(new Rate("-6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp202Test(){
        Argument a_ = MathUtil.processEl("2+2*(5+0*1)", false, new StringMap<String>());
        assertEq(new Rate("12"),a_.getRateVal());
    }
    @Test
    public void evaluateExp203Test(){
        Argument a_ = MathUtil.processEl("2+2*(5+(0*1+0))", false, new StringMap<String>());
        assertEq(new Rate("12"),a_.getRateVal());
    }
    @Test
    public void evaluateExp204Test(){
        Argument a_ = MathUtil.processEl("cardinal({})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp205Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_1})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp206Test(){
        Argument a_ = MathUtil.processEl("cardinal(union({MY_STRING_1;MY_STRING_3},{MY_STRING_2;MY_STRING_3}))", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp207Test(){
        Argument a_ = MathUtil.processEl("cardinal({my_string;MY_STRING})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp208Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_cardinal;MY_STRING_CARDINAL})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp209Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp210Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp211Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_ONE;MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp212Test(){
        Argument a_ = MathUtil.processEl("cardinal(inter({MY_STRING_ONE},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp213Test(){
        Argument a_ = MathUtil.processEl("cardinal(inter({MY_STRING_ONE},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp214Test(){
        Argument a_ = MathUtil.processEl("cardinal(inter({MY_STRING_ONE},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp215Test(){
        Argument a_ = MathUtil.processEl("cardinal(inter({},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp216Test(){
        Argument a_ = MathUtil.processEl("cardinal(inter({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp217Test(){
        Argument a_ = MathUtil.processEl("cardinal(union({MY_STRING_ONE},{}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp218Test(){
        Argument a_ = MathUtil.processEl("cardinal(union({},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp219Test(){
        Argument a_ = MathUtil.processEl("cardinal(union({},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp220Test(){
        Argument a_ = MathUtil.processEl("cardinal(union({MY_STRING_ONE},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp221Test(){
        Argument a_ = MathUtil.processEl("cardinal(union({MY_STRING_ONE},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp222Test(){
        Argument a_ = MathUtil.processEl("cardinal(union({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}))", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp223Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp224Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp225Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp226Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_ONE},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp227Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_ONE},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp228Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_ONE},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp229Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_ONE},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp230Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_THREE},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp231Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_THREE;MY_STRING_ONE},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp232Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_THREE;MY_STRING_ONE;MY_STRING_FOUR},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp233Test(){
        Argument a_ = MathUtil.processEl("cardinal(complementaire({MY_STRING_THREE;MY_STRING_ONE;MY_STRING_FIVE},{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_FOUR}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp234Test(){
        Argument a_ = MathUtil.processEl("egalnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp235Test(){
        Argument a_ = MathUtil.processEl("egalnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp236Test(){
        Argument a_ = MathUtil.processEl("inclusnum({},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp237Test(){
        Argument a_ = MathUtil.processEl("inclusnum({},{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp238Test(){
        Argument a_ = MathUtil.processEl("inclusnum({MY_STRING_ONE},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp239Test(){
        Argument a_ = MathUtil.processEl("inclusnum({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp240Test(){
        Argument a_ = MathUtil.processEl("inclusnum({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp241Test(){
        Argument a_ = MathUtil.processEl("inclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp242Test(){
        Argument a_ = MathUtil.processEl("inclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp243Test(){
        Argument a_ = MathUtil.processEl("noninclusnum({},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp244Test(){
        Argument a_ = MathUtil.processEl("noninclusnum({},{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp245Test(){
        Argument a_ = MathUtil.processEl("noninclusnum({MY_STRING_ONE},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp246Test(){
        Argument a_ = MathUtil.processEl("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp247Test(){
        Argument a_ = MathUtil.processEl("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp248Test(){
        Argument a_ = MathUtil.processEl("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp249Test(){
        Argument a_ = MathUtil.processEl("noninclusnum({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp250Test(){
        Argument a_ = MathUtil.processEl("egalnum({MY_STRING_ONE},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp251Test(){
        Argument a_ = MathUtil.processEl("egalnum({},{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp252Test(){
        Argument a_ = MathUtil.processEl("egalnum(inter({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp253Test(){
        Argument a_ = MathUtil.processEl("egalnum(inter({MY_STRING_ONE},{MY_STRING_TWO}),{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp254Test(){
        Argument a_ = MathUtil.processEl("egalnum(inter({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp255Test(){
        Argument a_ = MathUtil.processEl("egalnum(union({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp256Test(){
        Argument a_ = MathUtil.processEl("egalnum(union({MY_STRING_ONE},{MY_STRING_TWO}),{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp257Test(){
        Argument a_ = MathUtil.processEl("egalnum(union({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_THREE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp258Test(){
        Argument a_ = MathUtil.processEl("differentnum({MY_STRING_ONE},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp259Test(){
        Argument a_ = MathUtil.processEl("differentnum({},{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp260Test(){
        Argument a_ = MathUtil.processEl("differentnum({MY_STRING_TWO},{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp261Test(){
        Argument a_ = MathUtil.processEl("differentnum(inter({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp262Test(){
        Argument a_ = MathUtil.processEl("differentnum(inter({MY_STRING_ONE},{MY_STRING_TWO}),{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp263Test(){
        Argument a_ = MathUtil.processEl("differentnum(inter({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp264Test(){
        Argument a_ = MathUtil.processEl("differentnum(union({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp265Test(){
        Argument a_ = MathUtil.processEl("differentnum(union({MY_STRING_ONE},{MY_STRING_TWO}),{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp266Test(){
        Argument a_ = MathUtil.processEl("differentnum(union({12my_string},{MY_STRING_TWO}),{12my_string;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp267Test(){
        Argument a_ = MathUtil.processEl("differentnum(union({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_THREE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2681Test(){
        Argument a_ = MathUtil.processEl("+1", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2682Test(){
        Argument a_ = MathUtil.processEl("+-1", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2683Test(){
        Argument a_ = MathUtil.processEl("-+1", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2684Test(){
        Argument a_ = MathUtil.processEl(" -1", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2685Test(){
        Argument a_ = MathUtil.processEl(" +1", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2686Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_ONE\\;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2687Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_ONE\\\\MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2688Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_ONE{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2689Test(){
        Argument a_ = MathUtil.processEl("cardinal({MY_STRING_ONE\\}MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void eval1(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8:VARIABLE";
        assertEq(new Rate("4"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval2(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "STR_ONE;STR_TWO");
        String numericString_ = "cardinal({VARIABLE})";
        assertEq(new Rate("2"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval3(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "");
        String numericString_ = "cardinal({VARIABLE})";
        assertEq(new Rate("0"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval4(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "{STR_ONE;STR_TWO}");
        String numericString_ = "cardinal(VARIABLE)";
        assertEq(new Rate("2"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval5(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "{STR_ONE}");
        String numericString_ = "cardinal(VARIABLE)";
        assertEq(new Rate("1"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval6(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "{}");
        String numericString_ = "cardinal(VARIABLE)";
        assertEq(new Rate("0"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void check1(){
        Argument a_ = MathUtil.processEl("1:0", true, new StringMap<String>());
        assertEq(MathType.RATE.name(), a_.getArgClass().name());

    }
    @Test
    public void evaluateExp_1FailTest(){
        assertTrue(MathUtil.processEl("(2*1+1):0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_2FailTest(){
        assertTrue(MathUtil.processEl("(2*1+1):0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_3FailTest(){
        assertTrue(MathUtil.processEl("1+(2*1+1):0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_4FailTest(){
        assertTrue(MathUtil.processEl("1+(2*1+1):0+8", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_5FailTest(){
        assertTrue(MathUtil.processEl("1+((2*1+1):0)+8", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_6FailTest(){
        assertTrue(MathUtil.processEl("(1:0)+4", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_7FailTest(){
        assertTrue(MathUtil.processEl("div(1,0)+4", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_8FailTest(){
        assertTrue(MathUtil.processEl("1:0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_9FailTest(){
        assertTrue(MathUtil.processEl("1:-0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_10FailTest(){
        assertTrue(MathUtil.processEl("div(1,-0)+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_11FailTest(){
        assertTrue(MathUtil.processEl("1+2*5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_12FailTest(){
        assertTrue(MathUtil.processEl("1-2*5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_13FailTest(){
        assertTrue(MathUtil.processEl("1+3:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_14FailTest(){
        assertTrue(MathUtil.processEl("1+3+5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_15FailTest(){
        assertTrue(MathUtil.processEl("1-3+5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_16FailTest(){
        assertTrue(MathUtil.processEl("1-3:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_17FailTest(){
        assertTrue(MathUtil.processEl("-3*5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_18FailTest(){
        assertTrue(MathUtil.processEl("-3-5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_19FailTest(){
        assertTrue(MathUtil.processEl("3-5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_20FailTest(){
        assertTrue(MathUtil.processEl("1:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_21FailTest(){
        assertTrue(MathUtil.processEl("1:-0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_22FailTest(){
        assertTrue(MathUtil.processEl("quot(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_23FailTest(){
        assertTrue(MathUtil.processEl("quot(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_24FailTest(){
        assertTrue(MathUtil.processEl("mod(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_25FailTest(){
        assertTrue(MathUtil.processEl("mod(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_26FailTest(){
        assertTrue(MathUtil.processEl("modtaux(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_27FailTest(){
        assertTrue(MathUtil.processEl("modtaux(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_28FailTest(){
        assertTrue(MathUtil.processEl("abs(1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_29FailTest(){
        assertTrue(MathUtil.processEl("quot(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_30FailTest(){
        assertTrue(MathUtil.processEl("quot(1,1:0+1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_31FailTest(){
        assertTrue(MathUtil.processEl("2*quot(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_32FailTest(){
        assertTrue(MathUtil.processEl("2*quot(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_33FailTest(){
        assertTrue(MathUtil.processEl("2*quot(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_34FailTest(){
        assertTrue(MathUtil.processEl("quot(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_35FailTest(){
        assertTrue(MathUtil.processEl("quot(1:0+1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_36FailTest(){
        assertTrue(MathUtil.processEl("-1:0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_37FailTest(){
        assertTrue(MathUtil.processEl("puis(0,-1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_38FailTest(){
        assertTrue(MathUtil.processEl("-1:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_39FailTest(){
        assertTrue(MathUtil.processEl("(-1:0+1)+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_1FailTest(){
        assertTrue(MathUtil.processEl("(2*1+1):0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_2FailTest(){
        assertTrue(MathUtil.processEl("(2*1+1):0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_3FailTest(){
        assertTrue(MathUtil.processEl("1+(2*1+1):0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_4FailTest(){
        assertTrue(MathUtil.processEl("1+(2*1+1):0+8", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_5FailTest(){
        assertTrue(MathUtil.processEl("1+((2*1+1):0)+8", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_6FailTest(){
        assertTrue(MathUtil.processEl("(1:0)+4", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_7FailTest(){
        assertTrue(MathUtil.processEl("div(1,0)+4", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_8FailTest(){
        assertTrue(MathUtil.processEl("1:0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_9FailTest(){
        assertTrue(MathUtil.processEl("1:-0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_10FailTest(){
        assertTrue(MathUtil.processEl("div(1,-0)+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_11FailTest(){
        assertTrue(MathUtil.processEl("1+2*5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_12FailTest(){
        assertTrue(MathUtil.processEl("1-2*5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_13FailTest(){
        assertTrue(MathUtil.processEl("1+3:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_14FailTest(){
        assertTrue(MathUtil.processEl("1+3+5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_15FailTest(){
        assertTrue(MathUtil.processEl("1-3+5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_16FailTest(){
        assertTrue(MathUtil.processEl("1-3:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_17FailTest(){
        assertTrue(MathUtil.processEl("-3*5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_18FailTest(){
        assertTrue(MathUtil.processEl("-3-5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_19FailTest(){
        assertTrue(MathUtil.processEl("3-5:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_20FailTest(){
        assertTrue(MathUtil.processEl("1:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_21FailTest(){
        assertTrue(MathUtil.processEl("1:-0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_22FailTest(){
        assertTrue(MathUtil.processEl("quot(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_23FailTest(){
        assertTrue(MathUtil.processEl("quot(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_24FailTest(){
        assertTrue(MathUtil.processEl("mod(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_25FailTest(){
        assertTrue(MathUtil.processEl("mod(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_26FailTest(){
        assertTrue(MathUtil.processEl("modtaux(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_27FailTest(){
        assertTrue(MathUtil.processEl("modtaux(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_28FailTest(){
        assertTrue(MathUtil.processEl("abs(1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_29FailTest(){
        assertTrue(MathUtil.processEl("quot(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_30FailTest(){
        assertTrue(MathUtil.processEl("quot(1,1:0+1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_31FailTest(){
        assertTrue(MathUtil.processEl("2*quot(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_32FailTest(){
        assertTrue(MathUtil.processEl("2*quot(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_33FailTest(){
        assertTrue(MathUtil.processEl("2*quot(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_34FailTest(){
        assertTrue(MathUtil.processEl("quot(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_35FailTest(){
        assertTrue(MathUtil.processEl("quot(1:0+1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_36FailTest(){
        assertTrue(MathUtil.processEl("-1:0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_37FailTest(){
        assertTrue(MathUtil.processEl("puis(0,-1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_38FailTest(){
        assertTrue(MathUtil.processEl("-1:0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_39FailTest(){
        assertTrue(MathUtil.processEl("(-1:0+1)+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_unknownNbArgFct_1Fail(){
        assertTrue(MathUtil.processEl("min()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_unknownFct_1Fail(){
        assertTrue(MathUtil.processEl("string(0,-1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_unknownVar_1Fail(){
        assertTrue(MathUtil.processEl("VAR__UNDEFINED+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_ltLt_Fail(){
        assertTrue(MathUtil.processEl("1<2<3", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_minMin_Fail(){
        assertTrue(MathUtil.processEl("--", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_plusPlus_Fail(){
        assertTrue(MathUtil.processEl("++", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_eq_Fail(){
        assertTrue(MathUtil.processEl("V!F", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_eq_2_Fail(){
        assertTrue(MathUtil.processEl("V!", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_set_1_Fail(){
        assertTrue(MathUtil.processEl("cardinal({", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_set_2_Fail(){
        assertTrue(MathUtil.processEl("cardinal({\\", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_set_3_Fail(){
        assertTrue(MathUtil.processEl("cardinal({\\a", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_sep_Fail(){
        assertTrue(MathUtil.processEl("1,2", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_1_Fail(){
        assertTrue(MathUtil.processEl("cardinal({}", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_2_Fail(){
        assertTrue(MathUtil.processEl("cardinal({}))", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_3_Fail(){
        assertTrue(MathUtil.processEl("cardinal({},{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_4_Fail(){
        assertTrue(MathUtil.processEl("cardinal(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_5_Fail(){
        assertTrue(MathUtil.processEl("puis(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_6_Fail(){
        assertTrue(MathUtil.processEl("puis({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_7_Fail(){
        assertTrue(MathUtil.processEl("puis(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_8_Fail(){
        assertTrue(MathUtil.processEl("mod(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_9_Fail(){
        assertTrue(MathUtil.processEl("mod({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_10_Fail(){
        assertTrue(MathUtil.processEl("mod(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_11_Fail(){
        assertTrue(MathUtil.processEl("quot(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_12_Fail(){
        assertTrue(MathUtil.processEl("quot({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_13_Fail(){
        assertTrue(MathUtil.processEl("quot(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_14_Fail(){
        assertTrue(MathUtil.processEl("modtaux(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_15_Fail(){
        assertTrue(MathUtil.processEl("modtaux({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_16_Fail(){
        assertTrue(MathUtil.processEl("modtaux(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_17_Fail(){
        assertTrue(MathUtil.processEl("div(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_18_Fail(){
        assertTrue(MathUtil.processEl("div({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_19_Fail(){
        assertTrue(MathUtil.processEl("div(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_20_Fail(){
        assertTrue(MathUtil.processEl("ent({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_21_Fail(){
        assertTrue(MathUtil.processEl("ent({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_22_Fail(){
        assertTrue(MathUtil.processEl("abs({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_23_Fail(){
        assertTrue(MathUtil.processEl("abs({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_24_Fail(){
        assertTrue(MathUtil.processEl("troncature({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_25_Fail(){
        assertTrue(MathUtil.processEl("troncature({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_26_Fail(){
        assertTrue(MathUtil.processEl("num({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_27_Fail(){
        assertTrue(MathUtil.processEl("num({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_28_Fail(){
        assertTrue(MathUtil.processEl("den({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_29_Fail(){
        assertTrue(MathUtil.processEl("den({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_30_Fail(){
        assertTrue(MathUtil.processEl("max()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_31_Fail(){
        assertTrue(MathUtil.processEl("max({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_32_Fail(){
        assertTrue(MathUtil.processEl("min()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_33_Fail(){
        assertTrue(MathUtil.processEl("min({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_34_Fail(){
        assertTrue(MathUtil.processEl("moy()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_35_Fail(){
        assertTrue(MathUtil.processEl("moy({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_36_Fail(){
        assertTrue(MathUtil.processEl("var()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_37_Fail(){
        assertTrue(MathUtil.processEl("var({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_38_Fail(){
        assertTrue(MathUtil.processEl("sgn({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_39_Fail(){
        assertTrue(MathUtil.processEl("sgn({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_40_Fail(){
        assertTrue(MathUtil.processEl("caracferme({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_41_Fail(){
        assertTrue(MathUtil.processEl("caracferme({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_42_Fail(){
        assertTrue(MathUtil.processEl("caracferme(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_43_Fail(){
        assertTrue(MathUtil.processEl("caracferme(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_44_Fail(){
        assertTrue(MathUtil.processEl("caracouvert({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_45_Fail(){
        assertTrue(MathUtil.processEl("caracouvert({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_46_Fail(){
        assertTrue(MathUtil.processEl("caracouvert(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_47_Fail(){
        assertTrue(MathUtil.processEl("caracouvert(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_48_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertg({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_49_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertg({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_50_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertg(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_51_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertg(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_52_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertd({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_53_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertd({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_54_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertd(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_55_Fail(){
        assertTrue(MathUtil.processEl("caracsemiouvertd(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_56_Fail(){
        assertTrue(MathUtil.processEl("caracdroiteouvert({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_57_Fail(){
        assertTrue(MathUtil.processEl("caracdroiteouvert({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_58_Fail(){
        assertTrue(MathUtil.processEl("caracdroiteouvert(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_59_Fail(){
        assertTrue(MathUtil.processEl("caracdroiteferme({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_60_Fail(){
        assertTrue(MathUtil.processEl("caracdroiteferme({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_61_Fail(){
        assertTrue(MathUtil.processEl("caracdroiteferme(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_62_Fail(){
        assertTrue(MathUtil.processEl("caracgaucheouvert({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_63_Fail(){
        assertTrue(MathUtil.processEl("caracgaucheouvert({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_64_Fail(){
        assertTrue(MathUtil.processEl("caracgaucheouvert(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_65_Fail(){
        assertTrue(MathUtil.processEl("caracgaucheferme({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_66_Fail(){
        assertTrue(MathUtil.processEl("caracgaucheferme({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_67_Fail(){
        assertTrue(MathUtil.processEl("caracgaucheferme(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_68_Fail(){
        assertTrue(MathUtil.processEl("inter(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_69_Fail(){
        assertTrue(MathUtil.processEl("inter({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_70_Fail(){
        assertTrue(MathUtil.processEl("inter(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_71_Fail(){
        assertTrue(MathUtil.processEl("union(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_72_Fail(){
        assertTrue(MathUtil.processEl("union({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_73_Fail(){
        assertTrue(MathUtil.processEl("union(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_74_Fail(){
        assertTrue(MathUtil.processEl("complementaire(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_75_Fail(){
        assertTrue(MathUtil.processEl("complementaire({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_76_Fail(){
        assertTrue(MathUtil.processEl("complementaire(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_77_Fail(){
        assertTrue(MathUtil.processEl("inclusnum(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_78_Fail(){
        assertTrue(MathUtil.processEl("inclusnum({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_79_Fail(){
        assertTrue(MathUtil.processEl("inclusnum(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_80_Fail(){
        assertTrue(MathUtil.processEl("noninclusnum(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_81_Fail(){
        assertTrue(MathUtil.processEl("noninclusnum({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_82_Fail(){
        assertTrue(MathUtil.processEl("noninclusnum(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_83_Fail(){
        assertTrue(MathUtil.processEl("egalnum(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_84_Fail(){
        assertTrue(MathUtil.processEl("egalnum({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_85_Fail(){
        assertTrue(MathUtil.processEl("egalnum(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_86_Fail(){
        assertTrue(MathUtil.processEl("differentnum(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_87_Fail(){
        assertTrue(MathUtil.processEl("differentnum({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_88_Fail(){
        assertTrue(MathUtil.processEl("differentnum(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp268Test(){
        Argument a_ = MathUtil.processEl("V", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp269Test(){
        Argument a_ = MathUtil.processEl("F", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp270Test(){
        Argument a_ = MathUtil.processEl("1=1", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp271Test(){
        Argument a_ = MathUtil.processEl("1=abs(1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp272Test(){
        Argument a_ = MathUtil.processEl("1=2", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp273Test(){
        Argument a_ = MathUtil.processEl("1!=1", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp274Test(){
        Argument a_ = MathUtil.processEl("1!=abs(1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp275Test(){
        Argument a_ = MathUtil.processEl("1!=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp276Test(){
        Argument a_ = MathUtil.processEl("1<0", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp277Test(){
        Argument a_ = MathUtil.processEl("1<1", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp278Test(){
        Argument a_ = MathUtil.processEl("1<2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp279Test(){
        Argument a_ = MathUtil.processEl("1<=0", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp280Test(){
        Argument a_ = MathUtil.processEl("1<=1", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp281Test(){
        Argument a_ = MathUtil.processEl("1<=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp282Test(){
        Argument a_ = MathUtil.processEl("1>0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp283Test(){
        Argument a_ = MathUtil.processEl("1>1", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp284Test(){
        Argument a_ = MathUtil.processEl("1>2", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp285Test(){
        Argument a_ = MathUtil.processEl("1>=0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp286Test(){
        Argument a_ = MathUtil.processEl("1>=1", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp287Test(){
        Argument a_ = MathUtil.processEl("1>=2", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp288Test(){
        Argument a_ = MathUtil.processEl("1=1&2<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp289Test(){
        Argument a_ = MathUtil.processEl("1=1&2<puis(3,2)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp290Test(){
        Argument a_ = MathUtil.processEl("1=1&2>3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp291Test(){
        Argument a_ = MathUtil.processEl("1!=1&2>3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp292Test(){
        Argument a_ = MathUtil.processEl("1=1|2<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp293Test(){
        Argument a_ = MathUtil.processEl("1=1|2<puis(2,3)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp294Test(){
        Argument a_ = MathUtil.processEl("1=1|2>3&4<8&1>0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp295Test(){
        Argument a_ = MathUtil.processEl("(1!=1|2>3|5<6)&4<8&1>0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp296Test(){
        Argument a_ = MathUtil.processEl("1=1|2>3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp297Test(){
        Argument a_ = MathUtil.processEl("1!=1|2>3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp298Test(){
        Argument a_ = MathUtil.processEl("1!=1|2<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp299Test(){
        Argument a_ = MathUtil.processEl("2<3|1!=1&4<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp300Test(){
        Argument a_ = MathUtil.processEl("2<3|1!=abs(1)&4<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp301Test(){
        Argument a_ = MathUtil.processEl("(2<3|1!=1)&4<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp302Test(){
        Argument a_ = MathUtil.processEl("(2<3|1!=abs(1))&4<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp303Test(){
        Argument a_ = MathUtil.processEl("(2<3|abs(1)!=1)&4<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp304Test(){
        Argument a_ = MathUtil.processEl("4<3&(2<3|abs(1)!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp305Test(){
        Argument a_ = MathUtil.processEl("4<3&(abs(1)!=1|2<3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp306Test(){
        Argument a_ = MathUtil.processEl("(2<3|1!=abs(1))&puis(2,2)<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp307Test(){
        Argument a_ = MathUtil.processEl("2<3&(((2<3|1!=abs(1))&puis(2,2)<3)|puis(2,2)<3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp308Test(){
        Argument a_ = MathUtil.processEl("2<3&(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp309Test(){
        Argument a_ = MathUtil.processEl("(2<3&1=1|2<4)=V", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp310Test(){
        Argument a_ = MathUtil.processEl("2<3&1=1|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp311Test(){
        Argument a_ = MathUtil.processEl("2<3&((1=1))|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp312Test(){
        Argument a_ = MathUtil.processEl("2<3&((1)=(1))|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp313Test(){
        Argument a_ = MathUtil.processEl("2<3&((1))=((1))|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp314Test(){
        Argument a_ = MathUtil.processEl("V&1=1|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp315Test(){
        Argument a_ = MathUtil.processEl("F|1=1|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp316Test(){
        Argument a_ = MathUtil.processEl("2<3&!(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp317Test(){
        Argument a_ = MathUtil.processEl("2<3&!!(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp318Test(){
        Argument a_ = MathUtil.processEl("((2<3)|1!=(abs(1)))&puis(2,2)<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp319Test(){
        Argument a_ = MathUtil.processEl("2<3&((1)=(1))|(2<4&6>=5)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp320Test(){
        Argument a_ = MathUtil.processEl("1=1|(2<4&6>=5)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp321Test(){
        Argument a_ = MathUtil.processEl("cardinal({V;F})=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp322Test(){
        Argument a_ = MathUtil.processEl("cardinal({1a;2b})=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp323Test(){
        Argument a_ = MathUtil.processEl("cardinal({V;F})=2&(V)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp324Test(){
        Argument a_ = MathUtil.processEl("cardinal({1a;2b})=2&(F)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp325Test(){
        Argument a_ = MathUtil.processEl("1<=1&(1+1=2&2+3=5|5<6)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp326Test(){
        Argument a_ = MathUtil.processEl("1<=1&(1+1=2&(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp327Test(){
        Argument a_ = MathUtil.processEl("1<=1|(1+1=2&1+1=2&(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp328Test(){
        Argument a_ = MathUtil.processEl("1<=1|(1+1=2|1+1=2&1+1=2&(2+3=5|5<6&5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp329Test(){
        Argument a_ = MathUtil.processEl("1<=1&(1+1=2&1+1=2&(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp330Test(){
        Argument a_ = MathUtil.processEl("1>=1&(1<=1|(1+1=2|(2+3=5|5<6)))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp331Test(){
        Argument a_ = MathUtil.processEl("1<=1|(1+1=2|(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp332Test(){
        Argument a_ = MathUtil.processEl("(2<3)=(1=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp333Test(){
        Argument a_ = MathUtil.processEl("(-2<3)=(1=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp334Test(){
        Argument a_ = MathUtil.processEl("(2<3)=(1!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp335Test(){
        Argument a_ = MathUtil.processEl("(2>3)=(1!=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp336Test(){
        Argument a_ = MathUtil.processEl("(2>3)=(1=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp337Test(){
        Argument a_ = MathUtil.processEl("(2<3)!=(1=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp338Test(){
        Argument a_ = MathUtil.processEl("(2>3)!=(1=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp339Test(){
        Argument a_ = MathUtil.processEl("(2<3)!=(1!=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp340Test(){
        Argument a_ = MathUtil.processEl("(2>3)!=(1!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp341Test(){
        Argument a_ = MathUtil.processEl("(-2>3)!=(1!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp342Test(){
        Argument a_ = MathUtil.processEl("-1<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp343Test(){
        Argument a_ = MathUtil.processEl("-1>4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp344Test(){
        Argument a_ = MathUtil.processEl("-1<=4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp345Test(){
        Argument a_ = MathUtil.processEl("-1>=4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp346Test(){
        Argument a_ = MathUtil.processEl("-1=4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp347Test(){
        Argument a_ = MathUtil.processEl("-1!=4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp348Test(){
        Argument a_ = MathUtil.processEl("1<-4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp349Test(){
        Argument a_ = MathUtil.processEl("1>-4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp350Test(){
        Argument a_ = MathUtil.processEl("1<=-4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp351Test(){
        Argument a_ = MathUtil.processEl("1>=-4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp352Test(){
        Argument a_ = MathUtil.processEl("1=-4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp353Test(){
        Argument a_ = MathUtil.processEl("1!=-4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp354Test(){
        Argument a_ = MathUtil.processEl("((1!=-4))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp355Test(){
        Argument a_ = MathUtil.processEl("1+1=2|2+2=4|3+3=6", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp356Test(){
        Argument a_ = MathUtil.processEl("(V)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp357Test(){
        Argument a_ = MathUtil.processEl("((V))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp358Test(){
        Argument a_ = MathUtil.processEl("(F)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp359Test(){
        Argument a_ = MathUtil.processEl("((F))", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp360Test(){
        Argument a_ = MathUtil.processEl("6+7>10&(1<2|4>3)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp361Test(){
        Argument a_ = MathUtil.processEl("6+7>10&(V|4>3)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp362Test(){
        Argument a_ = MathUtil.processEl("6+7>10&(1<2|V)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp363Test(){
        Argument a_ = MathUtil.processEl("6+7<=10&(1<2|4>3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp364Test(){
        Argument a_ = MathUtil.processEl("6+7<=10&(V|4>3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp365Test(){
        Argument a_ = MathUtil.processEl("6+7<=10&(1<2|V)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp366Test(){
        Argument a_ = MathUtil.processEl("1+1=2|1:0=8", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp367Test(){
        Argument a_ = MathUtil.processEl("1+1!=2&1:0=8", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp368Test(){
        Argument a_ = MathUtil.processEl("!V", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp369Test(){
        Argument a_ = MathUtil.processEl("!F", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp370Test(){
        Argument a_ = MathUtil.processEl("!!V", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp371Test(){
        Argument a_ = MathUtil.processEl("!!F", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp372Test(){
        Argument a_ = MathUtil.processEl("! !V", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp373Test(){
        Argument a_ = MathUtil.processEl("! !F", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp374Test(){
        Argument a_ = MathUtil.processEl("F&V ", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp375Test(){
        Argument a_ = MathUtil.processEl("V&F", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp376Test(){
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put("VARIABLE","F");
        Argument a_ = MathUtil.processEl("VARIABLE", false, vars_);
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp377Test(){
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put("VARIABLE","V");
        Argument a_ = MathUtil.processEl("VARIABLE", false, vars_);
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp378Test(){
        Argument a_ = MathUtil.processEl("{}={}", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp379Test(){
        Argument a_ = MathUtil.processEl("{}={ONE}", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp_no_op_56_Fail(){
        assertTrue(MathUtil.processEl("1#2", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_57_Fail(){
        assertTrue(MathUtil.processEl("div(2,2)div(3,3)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_58_Fail(){
        assertTrue(MathUtil.processEl("div(2,2)div", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_59_Fail(){
        assertTrue(MathUtil.processEl("1!=2!=3", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_60_Fail(){
        assertTrue(MathUtil.processEl("1!={}", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_61_Fail(){
        assertTrue(MathUtil.processEl("(div(2,2)div)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_62_Fail(){
        assertTrue(MathUtil.processEl("(1+div(2,2)div)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_63_Fail(){
        assertTrue(MathUtil.processEl("!1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_64_Fail(){
        assertTrue(MathUtil.processEl("1&2", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_65_Fail(){
        assertTrue(MathUtil.processEl("-V", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_66_Fail(){
        assertTrue(MathUtil.processEl("V<1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_67_Fail(){
        assertTrue(MathUtil.processEl("1<V", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_68_Fail(){
        assertTrue(MathUtil.processEl("(1,2)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_69_Fail(){
        assertTrue(MathUtil.processEl("V+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_70_Fail(){
        assertTrue(MathUtil.processEl("1+V", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_71_Fail(){
        assertTrue(MathUtil.processEl("V*1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_72_Fail(){
        assertTrue(MathUtil.processEl("1*V", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_73_Fail(){
        assertTrue(MathUtil.processEl("1/0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_empty_Fail(){
        assertTrue(MathUtil.processEl("", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_empty2_Fail(){
        assertEq("", MathUtil.processEl("", false, new StringMap<String>()).getErr().getString());
    }
    @Test
    public void evaluateExp_empty3_Fail(){
        assertEq(0, MathUtil.processEl("", false, new StringMap<String>()).getErr().getIndex());
    }
}