package code.maths.litteral;
import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

import code.util.StringMap;

@SuppressWarnings("static-method")
@Ignore
public class BooleanStringTest {
    @Test
    public void evaluateExp_noCheck1Test(){
        BooleanString numeric_ = new BooleanString(BooleanString.TRUE);
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck2Test(){
        BooleanString numeric_ = new BooleanString(BooleanString.FALSE);
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck3Test(){
        BooleanString numeric_ = new BooleanString("1=1");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck4Test(){
        BooleanString numeric_ = new BooleanString("1=abs(1)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck5Test(){
        BooleanString numeric_ = new BooleanString("1=2");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck6Test(){
        BooleanString numeric_ = new BooleanString("1!=1");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck7Test(){
        BooleanString numeric_ = new BooleanString("1!=abs(1)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck8Test(){
        BooleanString numeric_ = new BooleanString("1!=2");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck9Test(){
        BooleanString numeric_ = new BooleanString("1<0");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck10Test(){
        BooleanString numeric_ = new BooleanString("1<1");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck11Test(){
        BooleanString numeric_ = new BooleanString("1<2");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck12Test(){
        BooleanString numeric_ = new BooleanString("1<=0");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck13Test(){
        BooleanString numeric_ = new BooleanString("1<=1");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck14Test(){
        BooleanString numeric_ = new BooleanString("1<=2");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck15Test(){
        BooleanString numeric_ = new BooleanString("1>0");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck16Test(){
        BooleanString numeric_ = new BooleanString("1>1");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck17Test(){
        BooleanString numeric_ = new BooleanString("1>2");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck18Test(){
        BooleanString numeric_ = new BooleanString("1>=0");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck19Test(){
        BooleanString numeric_ = new BooleanString("1>=1");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck20Test(){
        BooleanString numeric_ = new BooleanString("1>=2");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck21Test(){
        BooleanString numeric_ = new BooleanString("1=1&2<3");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck22Test(){
        BooleanString numeric_ = new BooleanString("1=1&2<puis(3,2)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck23Test(){
        BooleanString numeric_ = new BooleanString("1=1&2>3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck24Test(){
        BooleanString numeric_ = new BooleanString("1!=1&2>3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck25Test(){
        BooleanString numeric_ = new BooleanString("1=1|2<3");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck26Test(){
        BooleanString numeric_ = new BooleanString("1=1|2<puis(2,3)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck27Test(){
        BooleanString numeric_ = new BooleanString("1=1|2>3&4<8&1>0");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck28Test(){
        BooleanString numeric_ = new BooleanString("(1!=1|2>3|5<6)&4<8&1>0");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck29Test(){
        BooleanString numeric_ = new BooleanString("1=1|2>3");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck30Test(){
        BooleanString numeric_ = new BooleanString("1!=1|2>3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck31Test(){
        BooleanString numeric_ = new BooleanString("1!=1|2<3");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck32Test(){
        BooleanString numeric_ = new BooleanString("2<3|1!=1&4<3");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck33Test(){
        BooleanString numeric_ = new BooleanString("2<3|1!=abs(1)&4<3");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck34Test(){
        BooleanString numeric_ = new BooleanString("(2<3|1!=1)&4<3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck35Test(){
        BooleanString numeric_ = new BooleanString("(2<3|1!=abs(1))&4<3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck36Test(){
        BooleanString numeric_ = new BooleanString("(2<3|abs(1)!=1)&4<3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck37Test(){
        BooleanString numeric_ = new BooleanString("4<3&(2<3|abs(1)!=1)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck38Test(){
        BooleanString numeric_ = new BooleanString("4<3&(abs(1)!=1|2<3)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck39Test(){
        BooleanString numeric_ = new BooleanString("(2<3|1!=abs(1))&puis(2,2)<3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck40Test(){
        BooleanString numeric_ = new BooleanString("2<3&(((2<3|1!=abs(1))&puis(2,2)<3)|puis(2,2)<3)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck41Test(){
        BooleanString numeric_ = new BooleanString("2<3&(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck42Test(){
        BooleanString numeric_ = new BooleanString("(2<3&1=1|2<4)=V");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck43Test(){
        BooleanString numeric_ = new BooleanString("2<3&1=1|2<4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck44Test(){
        BooleanString numeric_ = new BooleanString("2<3&((1=1))|2<4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck45Test(){
        BooleanString numeric_ = new BooleanString("2<3&((1)=(1))|2<4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck46Test(){
        BooleanString numeric_ = new BooleanString("2<3&((1))=((1))|2<4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck47Test(){
        BooleanString numeric_ = new BooleanString("V&1=1|2<4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck48Test(){
        BooleanString numeric_ = new BooleanString("F|1=1|2<4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck49Test(){
        BooleanString numeric_ = new BooleanString("2<3&!(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck50Test(){
        BooleanString numeric_ = new BooleanString("2<3&!!(((2<3|1!=abs(1))&puis(2,2)<3)|((2<3|1!=abs(1))&puis(2,2)<3))");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck51Test(){
        BooleanString numeric_ = new BooleanString("((2<3)|1!=(abs(1)))&puis(2,2)<3");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck52Test(){
        BooleanString numeric_ = new BooleanString("2<3&((1)=(1))|(2<4&6>=5)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck53Test(){
        BooleanString numeric_ = new BooleanString("1=1|(2<4&6>=5)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck54Test(){
        BooleanString numeric_ = new BooleanString("cardinal({V;F})=2");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck55Test(){
        BooleanString numeric_ = new BooleanString("cardinal({1a;2b})=2");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck56Test(){
        BooleanString numeric_ = new BooleanString("cardinal({V;F})=2&(V)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck57Test(){
        BooleanString numeric_ = new BooleanString("cardinal({1a;2b})=2&(F)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck58Test(){
        BooleanString numeric_ = new BooleanString("1<=1&(1+1=2&2+3=5|5<6)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck59Test(){
        BooleanString numeric_ = new BooleanString("1<=1&(1+1=2&(2+3=5|5<6))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck60Test(){
        BooleanString numeric_ = new BooleanString("1<=1|(1+1=2&1+1=2&(2+3=5|5<6))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck61Test(){
        BooleanString numeric_ = new BooleanString("1<=1|(1+1=2|1+1=2&1+1=2&(2+3=5|5<6&5<6))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck62Test(){
        BooleanString numeric_ = new BooleanString("1<=1&(1+1=2&1+1=2&(2+3=5|5<6))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck63Test(){
        BooleanString numeric_ = new BooleanString("1>=1&(1<=1|(1+1=2|(2+3=5|5<6)))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck64Test(){
        BooleanString numeric_ = new BooleanString("1<=1|(1+1=2|(2+3=5|5<6))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck65Test(){
        BooleanString numeric_ = new BooleanString("(2<3)=(1=1)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck66Test(){
        BooleanString numeric_ = new BooleanString("(-2<3)=(1=1)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck67Test(){
        BooleanString numeric_ = new BooleanString("(2<3)=(1!=1)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck68Test(){
        BooleanString numeric_ = new BooleanString("(2>3)=(1!=1)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck69Test(){
        BooleanString numeric_ = new BooleanString("(2>3)=(1=1)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck70Test(){
        BooleanString numeric_ = new BooleanString("(2<3)!=(1=1)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck71Test(){
        BooleanString numeric_ = new BooleanString("(2>3)!=(1=1)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck72Test(){
        BooleanString numeric_ = new BooleanString("(2<3)!=(1!=1)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck73Test(){
        BooleanString numeric_ = new BooleanString("(2>3)!=(1!=1)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck74Test(){
        BooleanString numeric_ = new BooleanString("(-2>3)!=(1!=1)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck75Test(){
        BooleanString numeric_ = new BooleanString("-1<4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck76Test(){
        BooleanString numeric_ = new BooleanString("-1>4");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck77Test(){
        BooleanString numeric_ = new BooleanString("-1<=4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck78Test(){
        BooleanString numeric_ = new BooleanString("-1>=4");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck79Test(){
        BooleanString numeric_ = new BooleanString("-1=4");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck80Test(){
        BooleanString numeric_ = new BooleanString("-1!=4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck81Test(){
        BooleanString numeric_ = new BooleanString("1<-4");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck82Test(){
        BooleanString numeric_ = new BooleanString("1>-4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck83Test(){
        BooleanString numeric_ = new BooleanString("1<=-4");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck84Test(){
        BooleanString numeric_ = new BooleanString("1>=-4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck85Test(){
        BooleanString numeric_ = new BooleanString("1=-4");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck86Test(){
        BooleanString numeric_ = new BooleanString("1!=-4");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck87Test(){
        BooleanString numeric_ = new BooleanString("((1!=-4))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck88Test(){
        BooleanString numeric_ = new BooleanString("1+1=2|2+2=4|3+3=6");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck89Test(){
        BooleanString numeric_ = new BooleanString("(V)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck90Test(){
        BooleanString numeric_ = new BooleanString("((V))");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck91Test(){
        BooleanString numeric_ = new BooleanString("(F)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck92Test(){
        BooleanString numeric_ = new BooleanString("((F))");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck93Test(){
        BooleanString numeric_ = new BooleanString("6+7>10&(1<2|4>3)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck94Test(){
        BooleanString numeric_ = new BooleanString("6+7>10&(V|4>3)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck95Test(){
        BooleanString numeric_ = new BooleanString("6+7>10&(1<2|V)");
        numeric_.evaluateExp(false);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck96Test(){
        BooleanString numeric_ = new BooleanString("6+7<=10&(1<2|4>3)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck97Test(){
        BooleanString numeric_ = new BooleanString("6+7<=10&(V|4>3)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_noCheck98Test(){
        BooleanString numeric_ = new BooleanString("6+7<=10&(1<2|V)");
        numeric_.evaluateExp(false);
        assertEq(false,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluate1(){
        boolean default_ = true;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate2(){
        boolean default_ = false;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "8";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate3(){
        boolean default_ = false;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "1+1<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate4(){
        boolean default_ = true;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "1+1>3";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate5(){
        boolean default_ = false;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "VARIABLE<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate6(){
        boolean default_ = true;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "2");
        String numericString_ = "VARIABLE>3";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate7(){
        boolean default_ = false;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "1:VARIABLE<3";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate8(){
        boolean default_ = true;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "1:VARIABLE<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluate9(){
        boolean default_ = false;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "";
        assertEq(false, BooleanString.evaluate(numericString_, variables_, true, default_));
    }
    @Test
    public void evaluate10(){
        boolean default_ = true;
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put("VARIABLE", "0");
        String numericString_ = "";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, true, default_));
    }
    @Test
    public void evaluate11(){
        boolean default_ = false;
        StringMap<String> variables_ = new StringMap<String>();
        String numericString_ = "1:1<3";
        assertEq(true, BooleanString.evaluate(numericString_, variables_, false, default_));
    }
    @Test
    public void evaluateExp_check1Test(){
        BooleanString numeric_ = new BooleanString("1/2+1=3/2");
        numeric_.evaluateExp(true);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp_check2Test(){
        BooleanString numeric_ = new BooleanString("1/0+1=2");
        numeric_.evaluateExp(true);
        assertEq(true,numeric_.getResult().booleanValue());
    }
    @Test
    public void evaluateExp1Fail(){
        BooleanString numeric_ = new BooleanString("");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp2FailTest(){
        BooleanString numeric_ = new BooleanString("1/0+1=2");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp3Fail(){
        BooleanString numeric_ = new BooleanString("(2*1+1):0<1");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp4Fail(){
        BooleanString numeric_ = new BooleanString("1+1\"2");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp5Fail(){
        BooleanString numeric_ = new BooleanString("(2*1+1)<1:0");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp6Fail(){
        BooleanString numeric_ = new BooleanString("1<1:0");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp7Fail(){
        BooleanString numeric_ = new BooleanString("F=");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp8Fail(){
        BooleanString numeric_ = new BooleanString("1/2=");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp9Fail(){
        BooleanString numeric_ = new BooleanString("R=S");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp10Fail(){
        BooleanString numeric_ = new BooleanString("(1+1)&");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp11Fail(){
        BooleanString numeric_ = new BooleanString("(1+1)|");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp12Fail(){
        BooleanString numeric_ = new BooleanString("(1+1)&(1+1)&");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp13Fail(){
        BooleanString numeric_ = new BooleanString("(1+1)|(1+1)|");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp14Fail(){
        BooleanString numeric_ = new BooleanString("(1+1)|(1+1)&");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp15Fail(){
        BooleanString numeric_ = new BooleanString("1+1=2&");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp16Fail(){
        BooleanString numeric_ = new BooleanString("1+1=2|");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp17Fail(){
        BooleanString numeric_ = new BooleanString("1+1=2&1+1=2&");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp18Fail(){
        BooleanString numeric_ = new BooleanString("1+1=2|1+1=2|");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp19Fail(){
        BooleanString numeric_ = new BooleanString("1+1=2|1+1=2&");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp20Fail(){
        BooleanString numeric_ = new BooleanString("(1+1=2|2+2=4|3+3=6");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp21Fail(){
        BooleanString numeric_ = new BooleanString("1+1=2|2+2=4|3+3=6)");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp22Fail(){
        BooleanString numeric_ = new BooleanString("|2+2=4|3+3=6)");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp23Fail(){
        BooleanString numeric_ = new BooleanString("(2+2=4|3+3=6|");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp24Fail(){
        BooleanString numeric_ = new BooleanString("(2+2=4|(2+2=4|3+3=6)|");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp25Fail(){
        BooleanString numeric_ = new BooleanString("(V|(V|V)|");
        numeric_.evaluateExp(false);
    }
    @Test
    public void evaluateExp26Fail(){
        BooleanString numeric_ = new BooleanString("1/2<");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp27Fail(){
        BooleanString numeric_ = new BooleanString("<1/2");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp28Fail(){
        BooleanString numeric_ = new BooleanString("1/<2");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp29Fail(){
        BooleanString numeric_ = new BooleanString("1</2");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp30Fail(){
        BooleanString numeric_ = new BooleanString("(V|V|V|V");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp31Fail(){
        BooleanString numeric_ = new BooleanString("1!");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp32Fail(){
        BooleanString numeric_ = new BooleanString("(V|V|V|(V|V|V|V");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp33Fail(){
        BooleanString numeric_ = new BooleanString("|(V|V)");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp34Fail(){
        BooleanString numeric_ = new BooleanString("2+2=4|(2+2=4|3+3=6");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp35Fail(){
        BooleanString numeric_ = new BooleanString("2+2<=");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp36Fail(){
        BooleanString numeric_ = new BooleanString("2+2=-");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp37Fail(){
        BooleanString numeric_ = new BooleanString("2+2=-1/");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp38Fail(){
        BooleanString numeric_ = new BooleanString("2+2=-1/3&");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
    @Test
    public void evaluateExp39Fail(){
        BooleanString numeric_ = new BooleanString("2+2=");
        numeric_.evaluateExp(false);
        assertNull(numeric_.getResult());
    }
}