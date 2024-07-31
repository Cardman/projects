package code.maths.litteral;

import code.maths.EquallableMathUtil;
import code.util.CustList;
import org.junit.Test;

import code.maths.Rate;
import code.util.StringMap;


public class MathUtilTest extends EquallableMathUtil {
    @Test
    public void tryGet(){
        assertNotNull(MbNumParsers.tryGet(new CustList<MbOperationNode>(),0));
        assertNotNull(MbArgument.ofNullable(null));
    }
    @Test
    public void evaluateExp1Test(){
        MbArgument a_ = MathUtil.processEl("1", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2Test(){
        MbArgument a_ = MathUtil.processEl(".0", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp3Test(){
        MbArgument a_ = MathUtil.processEl("-.0", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp4Test(){
        MbArgument a_ = MathUtil.processEl("-.5", false, new StringMap<String>());
        assertEq(new Rate("-1/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp5Test(){
        MbArgument a_ = MathUtil.processEl(".5", false, new StringMap<String>());
        assertEq(new Rate("1/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp6Test(){
        MbArgument a_ = MathUtil.processEl("1.", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp7Test(){
        MbArgument a_ = MathUtil.processEl("1.5", false, new StringMap<String>());
        assertEq(new Rate("3/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp8Test(){
        MbArgument a_ = MathUtil.processEl("-1.", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp9Test(){
        MbArgument a_ = MathUtil.processEl("-1.5", false, new StringMap<String>());
        assertEq(new Rate("-3/2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp10Test(){
        MbArgument a_ = MathUtil.processEl("0.0", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp11Test(){
        MbArgument a_ = MathUtil.processEl("0.2", false, new StringMap<String>());
        assertEq(new Rate("1/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp12Test(){
        MbArgument a_ = MathUtil.processEl("15/7", false, new StringMap<String>());
        assertEq(new Rate("15/7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp13Test(){
        MbArgument a_ = MathUtil.processEl("0.1", false, new StringMap<String>());
        assertEq(new Rate("1/10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp14Test(){
        MbArgument a_ = MathUtil.processEl("0.2", false, new StringMap<String>());
        assertEq(new Rate("1/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp15Test(){
        MbArgument a_ = MathUtil.processEl("15/5", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp16Test(){
        MbArgument a_ = MathUtil.processEl("-3", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp17Test(){
        MbArgument a_ = MathUtil.processEl("1+2*5*7", false, new StringMap<String>());
        assertEq(new Rate("71"),a_.getRateVal());
    }
    @Test
    public void evaluateExp18Test(){
        MbArgument a_ = MathUtil.processEl("1-2*5*7", false, new StringMap<String>());
        assertEq(new Rate("-69"),a_.getRateVal());
    }
    @Test
    public void evaluateExp19Test(){
        MbArgument a_ = MathUtil.processEl("1+3*5", false, new StringMap<String>());
        assertEq(new Rate("16"),a_.getRateVal());
    }
    @Test
    public void evaluateExp20Test(){
        MbArgument a_ = MathUtil.processEl("1+3+5", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp21Test(){
        MbArgument a_ = MathUtil.processEl("1-3+5", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp22Test(){
        MbArgument a_ = MathUtil.processEl("1-3*5", false, new StringMap<String>());
        assertEq(new Rate("-14"),a_.getRateVal());
    }
    @Test
    public void evaluateExp23Test(){
        MbArgument a_ = MathUtil.processEl("-3*5", false, new StringMap<String>());
        assertEq(new Rate("-15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp24Test(){
        MbArgument a_ = MathUtil.processEl("-3-5", false, new StringMap<String>());
        assertEq(new Rate("-8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp25Test(){
        MbArgument a_ = MathUtil.processEl("3-5", false, new StringMap<String>());
        assertEq(new Rate("-2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp26Test(){
        MbArgument a_ = MathUtil.processEl("1+4", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp27Test(){
        MbArgument a_ = MathUtil.processEl("1+4/5", false, new StringMap<String>());
        assertEq(new Rate("9/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp28Test(){
        MbArgument a_ = MathUtil.processEl("5*3", false, new StringMap<String>());
        assertEq(new Rate("15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp29Test(){
        MbArgument a_ = MathUtil.processEl("5*3/7", false, new StringMap<String>());
        assertEq(new Rate("15/7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp30Test(){
        MbArgument a_ = MathUtil.processEl("5:3/7", false, new StringMap<String>());
        assertEq(new Rate("35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp31Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.DIV_FCT+"(5,3/7)", false, new StringMap<String>());
        assertEq(new Rate("35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp32Test(){
        MbArgument a_ = MathUtil.processEl("5:-3/7", false, new StringMap<String>());
        assertEq(new Rate("-35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp33Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.DIV_FCT+"(5,-3/7)", false, new StringMap<String>());
        assertEq(new Rate("-35/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp34Test(){
        MbArgument a_ = MathUtil.processEl("8-6", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp35Test(){
        MbArgument a_ = MathUtil.processEl("--5", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp36Test(){
        MbArgument a_ = MathUtil.processEl("---5", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp37Test(){
        MbArgument a_ = MathUtil.processEl("(--5+8)", false, new StringMap<String>());
        assertEq(new Rate("13"),a_.getRateVal());
    }
    @Test
    public void evaluateExp38Test(){
        MbArgument a_ = MathUtil.processEl("(---5+8)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp39Test(){
        MbArgument a_ = MathUtil.processEl("1*--5+8", false, new StringMap<String>());
        assertEq(new Rate("13"),a_.getRateVal());
    }
    @Test
    public void evaluateExp40Test(){
        MbArgument a_ = MathUtil.processEl("1*---5+8", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp41Test(){
        MbArgument a_ = MathUtil.processEl("9--5", false, new StringMap<String>());
        assertEq(new Rate("14"),a_.getRateVal());
    }
    @Test
    public void evaluateExp42Test(){
        MbArgument a_ = MathUtil.processEl("13---4", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp43Test(){
        MbArgument a_ = MathUtil.processEl("3*8+6", false, new StringMap<String>());
        assertEq(new Rate("30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp44Test(){
        MbArgument a_ = MathUtil.processEl("(3*8+6)", false, new StringMap<String>());
        assertEq(new Rate("30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp45Test(){
        MbArgument a_ = MathUtil.processEl("1-2*-3", false, new StringMap<String>());
        assertEq(new Rate("7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp46Test(){
        MbArgument a_ = MathUtil.processEl("1-2*-3*5", false, new StringMap<String>());
        assertEq(new Rate("31"),a_.getRateVal());
    }
    @Test
    public void evaluateExp47Test(){
        MbArgument a_ = MathUtil.processEl("1-2*3*-5", false, new StringMap<String>());
        assertEq(new Rate("31"),a_.getRateVal());
    }
    @Test
    public void evaluateExp48Test(){
        MbArgument a_ = MathUtil.processEl("1-2*3*5", false, new StringMap<String>());
        assertEq(new Rate("-29"),a_.getRateVal());
    }
    @Test
    public void evaluateExp49Test(){
        MbArgument a_ = MathUtil.processEl("2*(1-2*-3)", false, new StringMap<String>());
        assertEq(new Rate("14"),a_.getRateVal());
    }
    @Test
    public void evaluateExp50Test(){
        MbArgument a_ = MathUtil.processEl("2*(1-2*-3*5)", false, new StringMap<String>());
        assertEq(new Rate("62"),a_.getRateVal());
    }
    @Test
    public void evaluateExp51Test(){
        MbArgument a_ = MathUtil.processEl("2*(1-2*3*-5)", false, new StringMap<String>());
        assertEq(new Rate("62"),a_.getRateVal());
    }
    @Test
    public void evaluateExp52Test(){
        MbArgument a_ = MathUtil.processEl("2*(1-2*3*5)", false, new StringMap<String>());
        assertEq(new Rate("-58"),a_.getRateVal());
    }
    @Test
    public void evaluateExp53Test(){
        MbArgument a_ = MathUtil.processEl("((3))", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp54Test(){
        MbArgument a_ = MathUtil.processEl("3*(8+6)", false, new StringMap<String>());
        assertEq(new Rate("42"),a_.getRateVal());
    }
    @Test
    public void evaluateExp55Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.QUOT+"(5,6)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp56Test(){
        MbArgument a_ = MathUtil.processEl("-1*"+MbOperationNode.QUOT+"(5,6)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp57Test(){
        MbArgument a_ = MathUtil.processEl("-"+MbOperationNode.QUOT+"(5,6)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp58Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.QUOT+"(15,6)", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp59Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.QUOT+"(-15,6)", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp60Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MOD+"(15,6)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp61Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MOD+"(15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp62Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MOD+"(-15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp63Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MOD+"(-5,6)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp64Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MODTAUX+"(15,6)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp65Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MODTAUX+"(15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp66Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MODTAUX+"(-15,5)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp67Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MODTAUX+"(-5,6)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp68Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(3,4)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp69Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-3,3)", false, new StringMap<String>());
        assertEq(new Rate("-27"),a_.getRateVal());
    }
    @Test
    public void evaluateExp70Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-2,-3)", false, new StringMap<String>());
        assertEq(new Rate("-1/8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp71Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(2,-3)", false, new StringMap<String>());
        assertEq(new Rate("1/8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp72Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(0,0)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp73Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(0,1/2)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp74Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(9,1/2)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp75Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(9,-1/2)", false, new StringMap<String>());
        assertEq(new Rate("1/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp76Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-9,1/2)", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp77Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-9,-1/2)", false, new StringMap<String>());
        assertEq(new Rate("1/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp78Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-27,1/3)", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp79Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-27,2/3)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp80Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-27,-1/3)", false, new StringMap<String>());
        assertEq(new Rate("-1/3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp81Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(-27,-2/3)", false, new StringMap<String>());
        assertEq(new Rate("1/9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp82Test(){
        MbArgument a_ = MathUtil.processEl("3*"+MbOperationNode.PUIS+"(1+2,2)--9", false, new StringMap<String>());
        assertEq(new Rate("36"),a_.getRateVal());
    }
    @Test
    public void evaluateExp83Test(){
        MbArgument a_ = MathUtil.processEl("3*"+MbOperationNode.PUIS+"(1+2,2):3--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp84Test(){
        MbArgument a_ = MathUtil.processEl("3*"+MbOperationNode.DIV_FCT+"("+MbOperationNode.PUIS+"(1+2,2),3)--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp85Test(){
        MbArgument a_ = MathUtil.processEl("3*"+MbOperationNode.PUIS+"(1+2,5-3):3--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp86Test(){
        MbArgument a_ = MathUtil.processEl("3*"+MbOperationNode.DIV_FCT+"("+MbOperationNode.PUIS+"(1+2,5-3),3)--9", false, new StringMap<String>());
        assertEq(new Rate("18"),a_.getRateVal());
    }
    @Test
    public void evaluateExp87Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(9)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp88Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(-5)", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp89Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp90Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.SGN+"(9)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp91Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.SGN+"(-5)", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp92Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.SGN+"(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp93Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ENT+"(-5)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp94Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ENT+"(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("-3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp95Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ENT+"(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp96Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ENT+"(4)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp97Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ENT+"(6/5)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp98Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRONC+"(-5)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp99Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRONC+"(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("-2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp100Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRONC+"(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp101Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRONC+"(4)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp102Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRONC+"(6/5)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp103Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NUM+"(0)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp104Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NUM+"(4)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp105Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NUM+"(6/5)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp106Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.DEN+"(0)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp107Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.DEN+"(4)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp108Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.DEN+"(6/5)", false, new StringMap<String>());
        assertEq(new Rate("5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp109Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NUM+"(-5)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp110Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NUM+"(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("-5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp111Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.DEN+"(-5)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp112Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.DEN+"(-5/2)", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp113Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp114Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp115Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp116Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp117Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp118Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp119Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp120Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp121Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp122Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_FERME+"(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp123Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp124Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp125Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp126Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp127Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp128Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp129Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp130Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp131Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp132Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp133Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp134Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp135Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp136Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp137Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp138Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp139Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp140Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp141Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp142Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp143Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(2,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp144Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(1,1,3)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp145Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(3,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp146Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(0,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp147Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(4,1,3)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp148Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(2,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp149Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(1,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp150Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(3,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp151Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(0,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp152Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(4,3,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp153Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_DROITE_OUVERT+"(2,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp154Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_DROITE_OUVERT+"(1,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp155Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_DROITE_OUVERT+"(0,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp156Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_DROITE_FERME+"(2,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp157Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_DROITE_FERME+"(1,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp158Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_DROITE_FERME+"(0,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp159Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_OUVERT+"(2,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp160Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_OUVERT+"(1,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp161Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_OUVERT+"(0,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp162Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_FERME+"(2,1)", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp163Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_FERME+"(1,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp164Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_FERME+"(0,1)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp165Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(1+5,1+2)", false, new StringMap<String>());
        assertEq(new Rate("216"),a_.getRateVal());
    }
    @Test
    public void evaluateExp166Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(1+5,1*2)", false, new StringMap<String>());
        assertEq(new Rate("36"),a_.getRateVal());
    }
    @Test
    public void evaluateExp167Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(4+6)", false, new StringMap<String>());
        assertEq(new Rate("10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp168Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"((4+6))", false, new StringMap<String>());
        assertEq(new Rate("10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp169Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MIN+"(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp170Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MAX+"(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp171Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MIN+"(1+7,1+5)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp172Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MAX+"(1+7,1+5)", false, new StringMap<String>());
        assertEq(new Rate("8"),a_.getRateVal());
    }
    @Test
    public void evaluateExp173Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MOY+"(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("7"),a_.getRateVal());
    }
    @Test
    public void evaluateExp174Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.VAR+"(1+5,1+7)", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp175Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MIN+"(4+19,"+MbOperationNode.MAX+"(12,15))", false, new StringMap<String>());
        assertEq(new Rate("15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp176Test(){
        MbArgument a_ = MathUtil.processEl("2*"+MbOperationNode.MIN+"(4+19,"+MbOperationNode.MAX+"(12,15))", false, new StringMap<String>());
        assertEq(new Rate("30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp177Test(){
        MbArgument a_ = MathUtil.processEl("(4+12)*2", false, new StringMap<String>());
        assertEq(new Rate("32"),a_.getRateVal());
    }
    @Test
    public void evaluateExp178Test(){
        MbArgument a_ = MathUtil.processEl("2:-"+MbOperationNode.MIN+"(4+19,"+MbOperationNode.MAX+"(12,15))", false, new StringMap<String>());
        assertEq(new Rate("-2/15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp179Test(){
        MbArgument a_ = MathUtil.processEl("3:-"+MbOperationNode.MIN+"(4+19,"+MbOperationNode.MAX+"(12,15))", false, new StringMap<String>());
        assertEq(new Rate("-1/5"),a_.getRateVal());
    }
    @Test
    public void evaluateExp180Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(4*"+MbOperationNode.DIV_FCT+"(1,(3060)),1/4)", false, new StringMap<String>());
        assertEq(new Rate("29/153"),a_.getRateVal());
    }
    @Test
    public void evaluateExp181Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(3,(1+1)*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp182Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(3,2*(1+1))", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp183Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(2*1+1,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp184Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(3,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp185Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.PUIS+"(2+1*(1+0),2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("81"),a_.getRateVal());
    }
    @Test
    public void evaluateExp186Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.MIN+"(2,2+1*1,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp187Test(){
        MbArgument a_ = MathUtil.processEl("2*"+MbOperationNode.MIN+"(2,2+1*1,2+1*2)", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp188Test(){
        MbArgument a_ = MathUtil.processEl("1+2+3", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp189Test(){
        MbArgument a_ = MathUtil.processEl("1+(2+3)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp190Test(){
        MbArgument a_ = MathUtil.processEl("1+2+(3+0)", false, new StringMap<String>());
        assertEq(new Rate("6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp191Test(){
        MbArgument a_ = MathUtil.processEl("1+2*-3+3", false, new StringMap<String>());
        assertEq(new Rate("-2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp192Test(){
        MbArgument a_ = MathUtil.processEl("2*-3+1+2+3", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp193Test(){
        MbArgument a_ = MathUtil.processEl("2*-3+1+2+3+4", false, new StringMap<String>());
        assertEq(new Rate("4"),a_.getRateVal());
    }
    @Test
    public void evaluateExp194Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(2*-3+1)+(1+2+3+4)", false, new StringMap<String>());
        assertEq(new Rate("15"),a_.getRateVal());
    }
    @Test
    public void evaluateExp195Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(2*-3+1)+(1+2-3+4)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp196Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(2*-3+1)+(1+2-3+4+00)", false, new StringMap<String>());
        assertEq(new Rate("9"),a_.getRateVal());
    }
    @Test
    public void evaluateExp197Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.ABS+"(2*-3+1)+(1+2-3+4+001)", false, new StringMap<String>());
        assertEq(new Rate("10"),a_.getRateVal());
    }
    @Test
    public void evaluateExp198Test(){
        MbArgument a_ = MathUtil.processEl("12-7*2*3", false, new StringMap<String>());
        assertEq(new Rate("-30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp199Test(){
        MbArgument a_ = MathUtil.processEl("2*6-7*2*3", false, new StringMap<String>());
        assertEq(new Rate("-30"),a_.getRateVal());
    }
    @Test
    public void evaluateExp200Test(){
        MbArgument a_ = MathUtil.processEl("2*(2*6-7*2*3)", false, new StringMap<String>());
        assertEq(new Rate("-60"),a_.getRateVal());
    }
    @Test
    public void evaluateExp201Test(){
        MbArgument a_ = MathUtil.processEl("2*-3+1+2-3", false, new StringMap<String>());
        assertEq(new Rate("-6"),a_.getRateVal());
    }
    @Test
    public void evaluateExp202Test(){
        MbArgument a_ = MathUtil.processEl("2+2*(5+0*1)", false, new StringMap<String>());
        assertEq(new Rate("12"),a_.getRateVal());
    }
    @Test
    public void evaluateExp203Test(){
        MbArgument a_ = MathUtil.processEl("2+2*(5+(0*1+0))", false, new StringMap<String>());
        assertEq(new Rate("12"),a_.getRateVal());
    }
    @Test
    public void evaluateExp204Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp205Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_1})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp206Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.UNION+"({MY_STRING_1;MY_STRING_3},{MY_STRING_2;MY_STRING_3}))", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp207Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({my_string;MY_STRING})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp208Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_cardinal;MY_STRING_CARDINAL})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp209Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp210Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp211Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_ONE;MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp212Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.INTER+"({MY_STRING_ONE},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp213Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.INTER+"({MY_STRING_ONE},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp214Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.INTER+"({MY_STRING_ONE},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp215Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.INTER+"({},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp216Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.INTER+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp217Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.UNION+"({MY_STRING_ONE},{}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp218Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.UNION+"({},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp219Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.UNION+"({},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp220Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.UNION+"({MY_STRING_ONE},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp221Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.UNION+"({MY_STRING_ONE},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp222Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.UNION+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}))", false, new StringMap<String>());
        assertEq(new Rate("3"),a_.getRateVal());
    }
    @Test
    public void evaluateExp223Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp224Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp225Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp226Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_ONE},{}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp227Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_ONE},{MY_STRING_ONE}))", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp228Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_ONE},{MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp229Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_ONE},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp230Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_THREE},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp231Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_THREE;MY_STRING_ONE},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp232Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_THREE;MY_STRING_ONE;MY_STRING_FOUR},{MY_STRING_ONE;MY_STRING_TWO}))", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp233Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"("+MbOperationNode.COMPL+"({MY_STRING_THREE;MY_STRING_ONE;MY_STRING_FIVE},{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_FOUR}))", false, new StringMap<String>());
        assertEq(new Rate("2"),a_.getRateVal());
    }
    @Test
    public void evaluateExp234Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp235Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp236Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.INCL+"({},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp237Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.INCL+"({},{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp238Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.INCL+"({MY_STRING_ONE},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp239Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.INCL+"({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp240Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.INCL+"({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp241Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.INCL+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp242Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.INCL+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp243Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_INCL+"({},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp244Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_INCL+"({},{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp245Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_INCL+"({MY_STRING_ONE},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp246Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_INCL+"({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp247Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_INCL+"({MY_STRING_ONE;MY_STRING_TWO},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp248Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_INCL+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp249Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_INCL+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp250Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"({MY_STRING_ONE},{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp251Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"({},{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp252Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"("+MbOperationNode.INTER+"({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp253Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"("+MbOperationNode.INTER+"({MY_STRING_ONE},{MY_STRING_TWO}),{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp254Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"("+MbOperationNode.INTER+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp255Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"("+MbOperationNode.UNION+"({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp256Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"("+MbOperationNode.UNION+"({MY_STRING_ONE},{MY_STRING_TWO}),{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp257Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.EQ_NUM+"("+MbOperationNode.UNION+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_THREE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp258Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"({MY_STRING_ONE},{})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp259Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"({},{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp260Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"({MY_STRING_TWO},{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp261Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"("+MbOperationNode.INTER+"({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp262Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"("+MbOperationNode.INTER+"({MY_STRING_ONE},{MY_STRING_TWO}),{})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp263Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"("+MbOperationNode.INTER+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp264Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"("+MbOperationNode.UNION+"({MY_STRING_ONE},{MY_STRING_ONE}),{MY_STRING_ONE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp265Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"("+MbOperationNode.UNION+"({MY_STRING_ONE},{MY_STRING_TWO}),{MY_STRING_ONE;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp266Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"("+MbOperationNode.UNION+"({12my_string},{MY_STRING_TWO}),{12my_string;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp267Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"("+MbOperationNode.UNION+"({MY_STRING_ONE;MY_STRING_TWO},{MY_STRING_TWO;MY_STRING_THREE}),{MY_STRING_ONE;MY_STRING_TWO;MY_STRING_THREE})", false, new StringMap<String>());
        assertEq(new Rate("0"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2681Test(){
        MbArgument a_ = MathUtil.processEl("+1", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2682Test(){
        MbArgument a_ = MathUtil.processEl("+-1", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2683Test(){
        MbArgument a_ = MathUtil.processEl("-+1", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2684Test(){
        MbArgument a_ = MathUtil.processEl(" -1", false, new StringMap<String>());
        assertEq(new Rate("-1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2685Test(){
        MbArgument a_ = MathUtil.processEl(" +1", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2686Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_ONE\\;MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2687Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_ONE\\\\MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2688Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_ONE{MY_STRING_TWO})", false, new StringMap<String>());
        assertEq(new Rate("1"),a_.getRateVal());
    }
    @Test
    public void evaluateExp2689Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({MY_STRING_ONE\\}MY_STRING_TWO})", false, new StringMap<String>());
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
        String numericString_ = MbOperationNode.CARD+"({VARIABLE})";
        assertEq(new Rate("2"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval3(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "");
        String numericString_ = MbOperationNode.CARD+"({VARIABLE})";
        assertEq(new Rate("0"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval4(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "{STR_ONE;STR_TWO}");
        String numericString_ = MbOperationNode.CARD+"(VARIABLE)";
        assertEq(new Rate("2"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval5(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "{STR_ONE}");
        String numericString_ = MbOperationNode.CARD+"(VARIABLE)";
        assertEq(new Rate("1"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void eval6(){
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "{}");
        String numericString_ = MbOperationNode.CARD+"(VARIABLE)";
        assertEq(new Rate("0"), MathUtil.processEl(numericString_, false, variables_).getRateVal());
    }
    @Test
    public void check1(){
        MbArgument a_ = MathUtil.processEl("1:0", true, new StringMap<String>());
        assertSame(MathType.RATE, a_.getArgClass());

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
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(1,0)+4", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(1,-0)+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_23FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_24FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOD+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_25FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOD+"(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_26FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MODTAUX+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_27FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MODTAUX+"(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_28FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.ABS+"(1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_29FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_30FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,1:0+1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_31FailTest(){
        assertTrue(MathUtil.processEl("2*"+MbOperationNode.QUOT+"(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_32FailTest(){
        assertTrue(MathUtil.processEl("2*"+MbOperationNode.QUOT+"(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_33FailTest(){
        assertTrue(MathUtil.processEl("2*"+MbOperationNode.QUOT+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_34FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_35FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1:0+1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_36FailTest(){
        assertTrue(MathUtil.processEl("-1:0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_37FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.PUIS+"(0,-1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(1,0)+4", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(1,-0)+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_23FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_24FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOD+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_25FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOD+"(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_26FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MODTAUX+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_27FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.MODTAUX+"(1,-0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_28FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.ABS+"(1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_29FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_30FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,1:0+1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_31FailTest(){
        assertTrue(MathUtil.processEl("2*"+MbOperationNode.QUOT+"(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_32FailTest(){
        assertTrue(MathUtil.processEl("2*"+MbOperationNode.QUOT+"(1,1:0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_33FailTest(){
        assertTrue(MathUtil.processEl("2*"+MbOperationNode.QUOT+"(1,0)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_34FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1:0,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_35FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1:0+1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_36FailTest(){
        assertTrue(MathUtil.processEl("-1:0+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_DivZero_37FailTest(){
        assertTrue(MathUtil.processEl(MbOperationNode.PUIS+"(0,-1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl(MbOperationNode.MIN+"()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl(MbOperationNode.TRUE_STRING+"!"+MbOperationNode.FALSE_STRING, false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_eq_2_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.TRUE_STRING+"!", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_set_1_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARD+"({", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_set_2_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARD+"({\\", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_set_3_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARD+"({\\a", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_sep_Fail(){
        assertTrue(MathUtil.processEl("1,2", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_1_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARD+"({}", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_2_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARD+"({}))", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_3_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARD+"({},{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_4_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARD+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_5_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.PUIS+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_6_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.PUIS+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_7_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.PUIS+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_8_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOD+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_9_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOD+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_10_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOD+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_11_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_12_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_13_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.QUOT+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_14_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MODTAUX+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_15_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MODTAUX+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_16_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MODTAUX+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_17_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_18_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_19_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_20_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.ENT+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_21_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.ENT+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_22_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.ABS+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_23_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.ABS+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_24_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.TRONC+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_25_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.TRONC+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_26_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NUM+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_27_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NUM+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_28_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.DEN+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_29_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.DEN+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_30_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MAX+"()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_31_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MAX+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_32_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MIN+"()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_33_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MIN+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_34_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOY+"()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_35_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.MOY+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_36_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.VAR+"()", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_37_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.VAR+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_38_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.SGN+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_39_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.SGN+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_40_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_FERME+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_41_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_FERME+"({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_42_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_FERME+"(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_43_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_FERME+"(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_44_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_45_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_46_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_47_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_OUVERT+"(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_48_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_49_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_50_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_51_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_G+"(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_52_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_53_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"({},1,1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_54_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(1,{},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_55_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_SEMI_OUVERT_D+"(1,1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_56_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_DROITE_OUVERT+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_57_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_DROITE_OUVERT+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_58_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_DROITE_OUVERT+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_59_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_DROITE_FERME+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_60_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_DROITE_FERME+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_61_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_DROITE_FERME+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_62_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_OUVERT+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_63_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_OUVERT+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_64_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_OUVERT+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_65_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_FERME+"({})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_66_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_FERME+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_67_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.CARAC_GAUCHE_FERME+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_68_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.INTER+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_69_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.INTER+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_70_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.INTER+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_71_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.UNION+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_72_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.UNION+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_73_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.UNION+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_74_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.COMPL+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_75_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.COMPL+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_76_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.COMPL+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_77_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.INCL+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_78_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.INCL+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_79_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.INCL+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }

    @Test
    public void evaluateExp_bad_call_80_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NON_INCL+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_81_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NON_INCL+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_82_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NON_INCL+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_83_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.EQ_NUM+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_84_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.EQ_NUM+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_85_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.EQ_NUM+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_86_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"(1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_87_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"({},1)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_bad_call_88_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.NON_EQ_NUM+"(1,{})", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp268Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRUE_STRING, false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp269Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.FALSE_STRING, false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp270Test(){
        MbArgument a_ = MathUtil.processEl("1=1", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp271Test(){
        MbArgument a_ = MathUtil.processEl("1="+MbOperationNode.ABS+"(1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp272Test(){
        MbArgument a_ = MathUtil.processEl("1=2", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp273Test(){
        MbArgument a_ = MathUtil.processEl("1!=1", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp274Test(){
        MbArgument a_ = MathUtil.processEl("1!="+MbOperationNode.ABS+"(1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp275Test(){
        MbArgument a_ = MathUtil.processEl("1!=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp276Test(){
        MbArgument a_ = MathUtil.processEl("1<0", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp277Test(){
        MbArgument a_ = MathUtil.processEl("1<1", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp278Test(){
        MbArgument a_ = MathUtil.processEl("1<2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp279Test(){
        MbArgument a_ = MathUtil.processEl("1<=0", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp280Test(){
        MbArgument a_ = MathUtil.processEl("1<=1", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp281Test(){
        MbArgument a_ = MathUtil.processEl("1<=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp282Test(){
        MbArgument a_ = MathUtil.processEl("1>0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp283Test(){
        MbArgument a_ = MathUtil.processEl("1>1", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp284Test(){
        MbArgument a_ = MathUtil.processEl("1>2", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp285Test(){
        MbArgument a_ = MathUtil.processEl("1>=0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp286Test(){
        MbArgument a_ = MathUtil.processEl("1>=1", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp287Test(){
        MbArgument a_ = MathUtil.processEl("1>=2", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp288Test(){
        MbArgument a_ = MathUtil.processEl("1=1&2<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp289Test(){
        MbArgument a_ = MathUtil.processEl("1=1&2<"+MbOperationNode.PUIS+"(3,2)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp290Test(){
        MbArgument a_ = MathUtil.processEl("1=1&2>3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp291Test(){
        MbArgument a_ = MathUtil.processEl("1!=1&2>3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp292Test(){
        MbArgument a_ = MathUtil.processEl("1=1|2<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp293Test(){
        MbArgument a_ = MathUtil.processEl("1=1|2<"+MbOperationNode.PUIS+"(2,3)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp294Test(){
        MbArgument a_ = MathUtil.processEl("1=1|2>3&4<8&1>0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp295Test(){
        MbArgument a_ = MathUtil.processEl("(1!=1|2>3|5<6)&4<8&1>0", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp296Test(){
        MbArgument a_ = MathUtil.processEl("1=1|2>3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp297Test(){
        MbArgument a_ = MathUtil.processEl("1!=1|2>3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp298Test(){
        MbArgument a_ = MathUtil.processEl("1!=1|2<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp299Test(){
        MbArgument a_ = MathUtil.processEl("2<3|1!=1&4<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp300Test(){
        MbArgument a_ = MathUtil.processEl("2<3|1!="+MbOperationNode.ABS+"(1)&4<3", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp301Test(){
        MbArgument a_ = MathUtil.processEl("(2<3|1!=1)&4<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp302Test(){
        MbArgument a_ = MathUtil.processEl("(2<3|1!="+MbOperationNode.ABS+"(1))&4<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp303Test(){
        MbArgument a_ = MathUtil.processEl("(2<3|"+MbOperationNode.ABS+"(1)!=1)&4<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp304Test(){
        MbArgument a_ = MathUtil.processEl("4<3&(2<3|"+MbOperationNode.ABS+"(1)!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp305Test(){
        MbArgument a_ = MathUtil.processEl("4<3&("+MbOperationNode.ABS+"(1)!=1|2<3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp306Test(){
        MbArgument a_ = MathUtil.processEl("(2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp307Test(){
        MbArgument a_ = MathUtil.processEl("2<3&(((2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3)|"+MbOperationNode.PUIS+"(2,2)<3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp308Test(){
        MbArgument a_ = MathUtil.processEl("2<3&(((2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3)|((2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3))", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp309Test(){
        MbArgument a_ = MathUtil.processEl("(2<3&1=1|2<4)="+MbOperationNode.TRUE_STRING, false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp310Test(){
        MbArgument a_ = MathUtil.processEl("2<3&1=1|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp311Test(){
        MbArgument a_ = MathUtil.processEl("2<3&((1=1))|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp312Test(){
        MbArgument a_ = MathUtil.processEl("2<3&((1)=(1))|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp313Test(){
        MbArgument a_ = MathUtil.processEl("2<3&((1))=((1))|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp314Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRUE_STRING+"&1=1|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp315Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.FALSE_STRING+"|1=1|2<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp316Test(){
        MbArgument a_ = MathUtil.processEl("2<3&!(((2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3)|((2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp317Test(){
        MbArgument a_ = MathUtil.processEl("2<3&!!(((2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3)|((2<3|1!="+MbOperationNode.ABS+"(1))&"+MbOperationNode.PUIS+"(2,2)<3))", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp318Test(){
        MbArgument a_ = MathUtil.processEl("((2<3)|1!=("+MbOperationNode.ABS+"(1)))&"+MbOperationNode.PUIS+"(2,2)<3", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp319Test(){
        MbArgument a_ = MathUtil.processEl("2<3&((1)=(1))|(2<4&6>=5)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp320Test(){
        MbArgument a_ = MathUtil.processEl("1=1|(2<4&6>=5)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp321Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({"+MbOperationNode.TRUE_STRING+";"+MbOperationNode.FALSE_STRING+"})=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp322Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({1a;2b})=2", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp323Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({"+MbOperationNode.TRUE_STRING+";"+MbOperationNode.FALSE_STRING+"})=2&("+MbOperationNode.TRUE_STRING+")", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp324Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.CARD+"({1a;2b})=2&("+MbOperationNode.FALSE_STRING+")", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp325Test(){
        MbArgument a_ = MathUtil.processEl("1<=1&(1+1=2&2+3=5|5<6)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp326Test(){
        MbArgument a_ = MathUtil.processEl("1<=1&(1+1=2&(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp327Test(){
        MbArgument a_ = MathUtil.processEl("1<=1|(1+1=2&1+1=2&(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp328Test(){
        MbArgument a_ = MathUtil.processEl("1<=1|(1+1=2|1+1=2&1+1=2&(2+3=5|5<6&5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp329Test(){
        MbArgument a_ = MathUtil.processEl("1<=1&(1+1=2&1+1=2&(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp330Test(){
        MbArgument a_ = MathUtil.processEl("1>=1&(1<=1|(1+1=2|(2+3=5|5<6)))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp331Test(){
        MbArgument a_ = MathUtil.processEl("1<=1|(1+1=2|(2+3=5|5<6))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp332Test(){
        MbArgument a_ = MathUtil.processEl("(2<3)=(1=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp333Test(){
        MbArgument a_ = MathUtil.processEl("(-2<3)=(1=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp334Test(){
        MbArgument a_ = MathUtil.processEl("(2<3)=(1!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp335Test(){
        MbArgument a_ = MathUtil.processEl("(2>3)=(1!=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp336Test(){
        MbArgument a_ = MathUtil.processEl("(2>3)=(1=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp337Test(){
        MbArgument a_ = MathUtil.processEl("(2<3)!=(1=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp338Test(){
        MbArgument a_ = MathUtil.processEl("(2>3)!=(1=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp339Test(){
        MbArgument a_ = MathUtil.processEl("(2<3)!=(1!=1)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp340Test(){
        MbArgument a_ = MathUtil.processEl("(2>3)!=(1!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp341Test(){
        MbArgument a_ = MathUtil.processEl("(-2>3)!=(1!=1)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp342Test(){
        MbArgument a_ = MathUtil.processEl("-1<4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp343Test(){
        MbArgument a_ = MathUtil.processEl("-1>4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp344Test(){
        MbArgument a_ = MathUtil.processEl("-1<=4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp345Test(){
        MbArgument a_ = MathUtil.processEl("-1>=4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp346Test(){
        MbArgument a_ = MathUtil.processEl("-1=4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp347Test(){
        MbArgument a_ = MathUtil.processEl("-1!=4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp348Test(){
        MbArgument a_ = MathUtil.processEl("1<-4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp349Test(){
        MbArgument a_ = MathUtil.processEl("1>-4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp350Test(){
        MbArgument a_ = MathUtil.processEl("1<=-4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp351Test(){
        MbArgument a_ = MathUtil.processEl("1>=-4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp352Test(){
        MbArgument a_ = MathUtil.processEl("1=-4", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp353Test(){
        MbArgument a_ = MathUtil.processEl("1!=-4", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp354Test(){
        MbArgument a_ = MathUtil.processEl("((1!=-4))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp355Test(){
        MbArgument a_ = MathUtil.processEl("1+1=2|2+2=4|3+3=6", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp356Test(){
        MbArgument a_ = MathUtil.processEl("("+MbOperationNode.TRUE_STRING+")", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp357Test(){
        MbArgument a_ = MathUtil.processEl("(("+MbOperationNode.TRUE_STRING+"))", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp358Test(){
        MbArgument a_ = MathUtil.processEl("("+MbOperationNode.FALSE_STRING+")", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp359Test(){
        MbArgument a_ = MathUtil.processEl("(("+MbOperationNode.FALSE_STRING+"))", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp360Test(){
        MbArgument a_ = MathUtil.processEl("6+7>10&(1<2|4>3)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp361Test(){
        MbArgument a_ = MathUtil.processEl("6+7>10&("+MbOperationNode.TRUE_STRING+"|4>3)", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp362Test(){
        MbArgument a_ = MathUtil.processEl("6+7>10&(1<2|"+MbOperationNode.TRUE_STRING+")", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp363Test(){
        MbArgument a_ = MathUtil.processEl("6+7<=10&(1<2|4>3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp364Test(){
        MbArgument a_ = MathUtil.processEl("6+7<=10&("+MbOperationNode.TRUE_STRING+"|4>3)", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp365Test(){
        MbArgument a_ = MathUtil.processEl("6+7<=10&(1<2|"+MbOperationNode.TRUE_STRING+")", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp366Test(){
        MbArgument a_ = MathUtil.processEl("1+1=2|1:0=8", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp367Test(){
        MbArgument a_ = MathUtil.processEl("1+1!=2&1:0=8", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp368Test(){
        MbArgument a_ = MathUtil.processEl("!"+MbOperationNode.TRUE_STRING, false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp369Test(){
        MbArgument a_ = MathUtil.processEl("!"+MbOperationNode.FALSE_STRING, false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp370Test(){
        MbArgument a_ = MathUtil.processEl("!!"+MbOperationNode.TRUE_STRING, false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp371Test(){
        MbArgument a_ = MathUtil.processEl("!!"+MbOperationNode.FALSE_STRING, false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp372Test(){
        MbArgument a_ = MathUtil.processEl("! !"+MbOperationNode.TRUE_STRING, false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp373Test(){
        MbArgument a_ = MathUtil.processEl("! !"+MbOperationNode.FALSE_STRING, false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp374Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.FALSE_STRING+"&"+MbOperationNode.TRUE_STRING+" ", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp375Test(){
        MbArgument a_ = MathUtil.processEl(MbOperationNode.TRUE_STRING+"&"+MbOperationNode.FALSE_STRING, false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp376Test(){
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put("VARIABLE",MbOperationNode.FALSE_STRING);
        MbArgument a_ = MathUtil.processEl("VARIABLE", false, vars_);
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp377Test(){
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put("VARIABLE",MbOperationNode.TRUE_STRING);
        MbArgument a_ = MathUtil.processEl("VARIABLE", false, vars_);
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp378Test(){
        MbArgument a_ = MathUtil.processEl("{}={}", false, new StringMap<String>());
        assertEq(true,a_.isBoolVal());
    }
    @Test
    public void evaluateExp379Test(){
        MbArgument a_ = MathUtil.processEl("{}={ONE}", false, new StringMap<String>());
        assertEq(false,a_.isBoolVal());
    }
    @Test
    public void evaluateExp_no_op_56_Fail(){
        assertTrue(MathUtil.processEl("1#2", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_57_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(2,2)"+MbOperationNode.DIV_FCT+"(3,3)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_58_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.DIV_FCT+"(2,2)"+MbOperationNode.DIV_FCT, false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl("("+MbOperationNode.DIV_FCT+"(2,2)"+MbOperationNode.DIV_FCT+")", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_62_Fail(){
        assertTrue(MathUtil.processEl("(1+"+MbOperationNode.DIV_FCT+"(2,2)"+MbOperationNode.DIV_FCT+")", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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
        assertTrue(MathUtil.processEl("-"+MbOperationNode.TRUE_STRING, false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_66_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.TRUE_STRING+"<1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_67_Fail(){
        assertTrue(MathUtil.processEl("1<"+MbOperationNode.TRUE_STRING, false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_68_Fail(){
        assertTrue(MathUtil.processEl("(1,2)", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_69_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.TRUE_STRING+"+1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_70_Fail(){
        assertTrue(MathUtil.processEl("1+"+MbOperationNode.TRUE_STRING, false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_71_Fail(){
        assertTrue(MathUtil.processEl(MbOperationNode.TRUE_STRING+"*1", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_72_Fail(){
        assertTrue(MathUtil.processEl("1*"+MbOperationNode.TRUE_STRING, false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_73_Fail(){
        assertTrue(MathUtil.processEl("1/0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
    }
    @Test
    public void evaluateExp_no_op_74_Fail(){
        assertTrue(MathUtil.processEl("1_0", false, new StringMap<String>()).getErr() instanceof ErrorStatus);
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