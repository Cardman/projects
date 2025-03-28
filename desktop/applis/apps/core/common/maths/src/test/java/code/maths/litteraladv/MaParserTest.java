package code.maths.litteraladv;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import code.maths.geo.*;
import code.maths.litteralcom.StrTypes;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefDoubleToStrConverter;
import code.maths.montecarlo.DefaultGenerator;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.Replacement;
import org.junit.Test;

public final class MaParserTest extends EquallableMathUtil {
    @Test
    public void evaluateExp1Test(){
        assertEq("1", noVar("1"));
    }
    @Test
    public void evaluateExp2Test(){
        assertEq("-1", noVar("-1"));
    }
    @Test
    public void evaluateExp3Test(){
        assertEq("1/2", noVar("1/2"));
    }
    @Test
    public void evaluateExp4Test(){
        assertEq("-1/2", noVar("-1/2"));
    }
    @Test
    public void evaluateExp5Test(){
        assertEq("3", noVar("1+2"));
    }
    @Test
    public void evaluateExp6Test(){
        assertEq("-1", noVar("1-2"));
    }
    @Test
    public void evaluateExp7Test(){
        assertEq("6", noVar("2*3"));
    }
    @Test
    public void evaluateExp8Test(){
        assertEq("1/2", noVar("1:2"));
    }
    @Test
    public void evaluateExp9Test(){
        assertEq("7", noVar("1+2*3"));
    }
    @Test
    public void evaluateExp10Test(){
        assertEq("9", noVar("(1+2)*3"));
    }
    @Test
    public void evaluateExp11Test(){
        assertEq("1", oneVar("a","a","1"));
    }
    @Test
    public void evaluateExp12Test(){
        assertEq("9", oneVar("(a+2)*3","a","1"));
    }
    @Test
    public void evaluateExp13Test(){
        assertEq("21", oneVar("(b+2)*3","b","5"));
    }
    @Test
    public void evaluateExp14Test(){
        assertEq("21", twoVars("(b+c)*3","b","5","c","2"));
    }
    @Test
    public void evaluateExp15Test(){
        assertEq("21", twoVars("(c+b)*3","c","4","b","3"));
    }
    @Test
    public void evaluateExp16Test(){
        assertEq("#0", noVar("1/0"));
    }
    @Test
    public void evaluateExp17Test(){
        assertEq("#1", noVar("1:0"));
    }
    @Test
    public void evaluateExp18Test(){
        assertEq("#0", oneVar("a","b","0"));
    }
    @Test
    public void evaluateExp19Test(){
        assertEq("#a#1", oneVar("a","a","-"));
    }
    @Test
    public void evaluateExp20Test(){
        assertEq("8", noVar("(2,3,^)"));
    }
    @Test
    public void evaluateExp21Test(){
        assertEq("8", oneVar("(2,3,^)","puis2","puis"));
    }
    @Test
    public void evaluateExp22Test(){
        assertEq("9", noVar("(3,2,^)"));
    }
    @Test
    public void evaluateExp23Test(){
        assertEq("9", oneVar("(3,2,^)","puis2","puis"));
    }
    @Test
    public void evaluateExp24Test(){
        assertEq("1", noVar("1+1=2"));
    }
    @Test
    public void evaluateExp25Test(){
        assertEq("0", noVar("1+1!2"));
    }
    @Test
    public void evaluateExp26Test(){
        assertEq("1", noVar("1>0|1:0>0"));
    }
    @Test
    public void evaluateExp27Test(){
        assertEq("1", noVar("0<1<2"));
    }
    @Test
    public void evaluateExp28Test(){
        assertEq("1", noVar("0=1-1=2-2"));
    }
    @Test
    public void evaluateExp29Test(){
        assertEq("1", noVar("0<=1<=2"));
    }
    @Test
    public void evaluateExp30Test(){
        assertEq("1", noVar("0>-1>-2"));
    }
    @Test
    public void evaluateExp31Test(){
        assertEq("1", noVar("0>=-1>=-2"));
    }
    @Test
    public void evaluateExp32Test(){
        assertEq("1", noVar("!(1+1!2)"));
    }
    @Test
    public void evaluateExp33Test(){
        assertEq("8", noVar("(2,3,^) "));
    }
    @Test
    public void evaluateExp34Test(){
        assertEq("8", noVar("(2, 3,^)"));
    }
    @Test
    public void evaluateExp35Test(){
        assertEq("#7", noVar("(2,2,3,^)"));
    }
    @Test
    public void evaluateExp36Test(){
        assertEq("1", noVar("(&)"));
    }
    @Test
    public void evaluateExp37Test(){
        assertEq("#1", noVar("1+(&)"));
    }
    @Test
    public void evaluateExp38Test(){
        assertEq("#3", noVar("(&)+1"));
    }
    @Test
    public void evaluateExp39Test(){
        assertEq("0", noVar("(|)"));
    }
    @Test
    public void evaluateExp40Test(){
        assertEq("#1", noVar("1+(|)"));
    }
    @Test
    public void evaluateExp41Test(){
        assertEq("#3", noVar("(|)+1"));
    }
    @Test
    public void evaluateExp42Test(){
        assertEq("#9", noVar("((&),(|),^)"));
    }
    @Test
    public void evaluateExp43Test(){
        assertEq("0", noVar("(0,3,^)"));
    }
    @Test
    public void evaluateExp44Test(){
        assertEq("1/2", noVar("(2,-1,^)"));
    }
    @Test
    public void evaluateExp45Test(){
        assertEq("#6", noVar("(0,-1,^)"));
    }
    @Test
    public void evaluateExp46Test(){
        assertEq("#2", noVar("(0,(&))"));
    }
    @Test
    public void evaluateExp47Test(){
        assertEq("0", noVar("!(1+1=2)"));
    }
    @Test
    public void evaluateExp48Test(){
        assertEq("1", noVar("--1"));
    }
    @Test
    public void evaluateExp49Test(){
        assertEq("1", noVar("+1"));
    }
    @Test
    public void evaluateExp50Test(){
        assertEq("1", noVar("- -1"));
    }
    @Test
    public void evaluateExp51Test(){
        assertEq("#2", noVar("--"));
    }
    @Test
    public void evaluateExp52Test(){
        assertEq("1", noVar("(&)&1+1=2"));
    }
    @Test
    public void evaluateExp53Test(){
        assertEq("9", noVar("1+(2,3,^)"));
    }
    @Test
    public void evaluateExp54Test(){
        assertEq("64", noVar("((2,3,^),2,^)"));
    }
    @Test
    public void evaluateExp55Test(){
        assertEq("#8", noVar("(3,2,^)(&)"));
    }
    @Test
    public void evaluateExp56Test(){
        assertEq("0", noVar("(|)=(&)"));
    }
    @Test
    public void evaluateExp57Test(){
        assertEq("1", noVar("(&)=(&)"));
    }
    @Test
    public void evaluateExp58Test(){
        assertEq("0", noVar("1+1=1"));
    }
    @Test
    public void evaluateExp59Test(){
        assertEq("1", noVar("(|)!(&)"));
    }
    @Test
    public void evaluateExp60Test(){
        assertEq("0", noVar("(&)!(&)"));
    }
    @Test
    public void evaluateExp61Test(){
        assertEq("0", noVar("0>1"));
    }
    @Test
    public void evaluateExp62Test(){
        assertEq("#1", noVar("0>(&)"));
    }
    @Test
    public void evaluateExp63Test(){
        assertEq("#3", noVar("(&)>0"));
    }
    @Test
    public void evaluateExp64Test(){
        assertEq("#0", noVar("!0"));
    }
    @Test
    public void evaluateExp65Test(){
        assertEq("#0", noVar("-(&)"));
    }
    @Test
    public void evaluateExp66Test(){
        assertEq("1", noVar("(5,3,/)"));
    }
    @Test
    public void evaluateExp67Test(){
        assertEq("2", noVar("(5,3,%)"));
    }
    @Test
    public void evaluateExp68Test(){
        assertEq("#5", noVar("(5,0,/)"));
    }
    @Test
    public void evaluateExp69Test(){
        assertEq("#5", noVar("(5,0,%)"));
    }
    @Test
    public void evaluateExp70Test(){
        assertEq("#0", noVar("_"));
    }
    @Test
    public void evaluateExp71Test(){
        assertEq("0", noVar("1=(&)"));
    }
    @Test
    public void evaluateExp72Test(){
        assertEq("0", noVar("(&)=1"));
    }
    @Test
    public void evaluateExp73Test(){
        assertEq("#1", noVar("1&2"));
    }
    @Test
    public void evaluateExp74Test(){
        assertEq("#3", noVar("(&)&2"));
    }
    @Test
    public void evaluateExp75Test(){
        assertEq("#0", noVar("(3,2,^) vrai"));
    }
    @Test
    public void evaluateExp76Test(){
        assertEq("#0", noVar("\\"));
    }
    @Test
    public void evaluateExp77Test(){
        assertEq("#7", noVar("(5,(&),/)"));
    }
    @Test
    public void evaluateExp78Test(){
        assertEq("#7", noVar("(5,(&),%)"));
    }
    @Test
    public void evaluateExp79Test(){
        assertEq("#5", noVar("(5,2,//)"));
    }
    @Test
    public void evaluateExp80Test(){
        assertEq("#5", noVar("(5,2,%%%%)"));
    }
    @Test
    public void evaluateExp81Test(){
        assertEq("#1", noVar("(&,1)"));
    }
    @Test
    public void evaluateExp82Test(){
        assertEq("#1", noVar("(|,1)"));
    }
    @Test
    public void evaluateExp83Test(){
        assertEq("1", noVar("1+1!1"));
    }
    @Test
    public void evaluateExp84Test(){
        assertEq("#2", noVar("1:"));
    }
    @Test
    public void evaluateExp85Test(){
        assertEq("#2", noVar("(1"));
    }
    @Test
    public void evaluateExp86Test(){
        assertEq("#3", noVar("(1))"));
    }
    @Test
    public void evaluateExp87Test(){
        assertEq("#1", noVar("1,2"));
    }
    @Test
    public void evaluateExp88Test(){
        assertEq("1/2", noVar(".5"));
    }
    @Test
    public void evaluateExp89Test(){
        assertEq("1/2", noVar(" .5 "));
    }
    @Test
    public void evaluateExp90Test(){
        assertEq("#0", noVar(""));
    }
    @Test
    public void evaluateExp91Test(){
        assertEq("##a#1", dupVar("","a","1"));
    }
    @Test
    public void evaluateExp92Test(){
        assertEq("###\\a#1", oneVar("","\\a","1"));
    }
    @Test
    public void evaluateExp93Test(){
        assertEq("####a#a", oneVar("","#a","a"));
    }
    @Test
    public void evaluateExp94Test(){
        assertEq("####\\a#a", oneVar("","#\\a","a"));
    }
    @Test
    public void evaluateExp95Test(){
        assertEq("####a#\\a", oneVar("","#a","\\a"));
    }
    @Test
    public void evaluateExp96Test(){
        assertEq("####puis2#puis2", oneVar("","#puis2","puis2"));
    }
    @Test
    public void evaluateExp97Test(){
        assertEq("####\\puis2#puis", oneVar("","#\\puis2","puis"));
    }
    @Test
    public void evaluateExp98Test(){
        assertEq("####puis2#puis", dupVar("","#puis2","puis"));
    }
    @Test
    public void evaluateExp99Test(){
        assertEq("1", oneVar("(&)","vrai2","vrai"));
    }
    @Test
    public void evaluateExp100Test(){
        assertEq("0", oneVar("(|)","faux2","faux"));
    }
    @Test
    public void evaluateExp101Test(){
        assertEq("1", oneVar("(5,3,/)","quot2","quot"));
    }
    @Test
    public void evaluateExp102Test(){
        assertEq("2", oneVar("(5,3,%)","mod2","mod"));
    }
    @Test
    public void evaluateExp103Test(){
        assertEq("####", oneVar("","","0"));
    }
    @Test
    public void evaluateExp104Test(){
        assertEq("####", oneVar("","0",""));
    }
    @Test
    public void evaluateExp105Test(){
        assertEq("21", twoVars("(c+b)*3","c","4","b","c-1"));
    }

    @Test
    public void evaluateExp106Test(){
        assertEq("21", twoVars("(c+b)*3","c","b+1","b","3"));
    }

    @Test
    public void evaluateExp107Test(){
        assertEq("#1", twoVars("(c+b)*3","c","b","b","c"));
    }
    @Test
    public void evaluateExp108Test(){
        assertEq("1/2", noVar("0.5"));
    }
    @Test
    public void evaluateExp109Test(){
        assertEq("1/2", noVar(" 0.5 "));
    }
    @Test
    public void evaluateExp110Test(){
        assertEq("-1/2", noVar("1/-2"));
    }
    @Test
    public void evaluateExp111Test(){
        assertEq("1", oneVar("1","1","1/1"));
    }
    @Test
    public void evaluateExp112Test(){
        assertEq("1", oneVar("1a","1a","1"));
    }
    @Test
    public void evaluateExp113Test(){
        assertEq("#0", noVar("0 ()"));
    }
    @Test
    public void evaluateExp114Test(){
        assertEq("#0", noVar(". a"));
    }
    @Test
    public void evaluateExp115Test(){
        assertEq("#0", noVar(". "));
    }
    @Test
    public void evaluateExp116Test(){
        assertEq("#0", noVar("."));
    }
    @Test
    public void evaluateExp117Test(){
        assertEq("1", noVar("(1,|)"));
    }
    @Test
    public void evaluateExp118Test(){
        assertEq("1", noVar("(1,-)"));
    }
    @Test
    public void evaluateExp119Test(){
        assertEq("1", noVar("(1,/0)"));
    }
    @Test
    public void evaluateExp120Test(){
        assertEq("1", noVar("(1,/1)"));
    }
    @Test
    public void evaluateExp121Test(){
        assertEq("1", noVar("(1,0/)"));
    }
    @Test
    public void evaluateExp122Test(){
        assertEq("1", noVar("(1,1/)"));
    }
    @Test
    public void evaluateExp123Test(){
        assertEq("0", noVar("(1/2,1/2,%)"));
    }
    @Test
    public void evaluateExp124Test(){
        assertEq("#7", noVar("(1/2,0,%)"));
    }
    @Test
    public void evaluateExp125Test(){
        assertEq("#5", noVar("((&),|)"));
    }
    @Test
    public void evaluateExp126Test(){
        assertEq("#5", noVar("((&),-)"));
    }
    @Test
    public void evaluateExp127Test(){
        assertEq("#5", noVar("((&),/0)"));
    }
    @Test
    public void evaluateExp128Test(){
        assertEq("#5", noVar("((&),/1)"));
    }
    @Test
    public void evaluateExp129Test(){
        assertEq("#5", noVar("((&),0/)"));
    }
    @Test
    public void evaluateExp130Test(){
        assertEq("#5", noVar("((&),1/)"));
    }
    @Test
    public void evaluateExp131Test(){
        assertEq("#9", noVar("(1/2,(&),%)"));
    }
    @Test
    public void evaluateExp132Test(){
        assertEq("#5", noVar("(1,1,|)"));
    }
    @Test
    public void evaluateExp133Test(){
        assertEq("#10", noVar("(1,(1,||),-)"));
    }
    @Test
    public void evaluateExp134Test(){
        assertEq("#5", noVar("(1,1,/0)"));
    }
    @Test
    public void evaluateExp135Test(){
        assertEq("#5", noVar("(1,1,/1)"));
    }
    @Test
    public void evaluateExp136Test(){
        assertEq("#5", noVar("(1,1,0/)"));
    }
    @Test
    public void evaluateExp137Test(){
        assertEq("#5", noVar("(1,1,1/)"));
    }
    @Test
    public void evaluateExp138Test(){
        assertEq("#3", noVar("(1,%)"));
    }
    @Test
    public void evaluateExp139Test(){
        assertEq("(-1,2,1,15)", noVar("(5,3,/%)"));
    }
    @Test
    public void evaluateExp140Test(){
        assertEq("#7", noVar("(5,(&),/%)"));
    }
    @Test
    public void evaluateExp141Test(){
        assertEq("#3", noVar("(5,/%)"));
    }
    @Test
    public void evaluateExp142Test(){
        assertEq("1", noVar("(5,3,/%)=(5,3,/%)"));
    }
    @Test
    public void evaluateExp143Test(){
        assertEq("0", noVar("(5,3,/%)=(3,5,/%)"));
    }
    @Test
    public void evaluateExp144Test(){
        assertEq("0", noVar("(5,4,/%)=(5,3,/%)"));
    }
    @Test
    public void evaluateExp145Test(){
        assertEq("0", noVar("(4,6,/%)=(5,3,/%)"));
    }
    @Test
    public void evaluateExp146Test(){
        assertEq("0", noVar("(4,6,/%)=3"));
    }
    @Test
    public void evaluateExp147Test(){
        assertEq("0", noVar("(4,6,/%)=(2,3,/%)"));
    }
    @Test
    public void evaluateExp148Test(){
        assertEq("0", noVar("(-1,1,/%)=(-2,1,/%)"));
    }
    @Test
    public void evaluateExp149Test(){
        assertEq("-1", noVar("0[]"));
    }
    @Test
    public void evaluateExp150Test(){
        assertEq("4", noVar("(4,6,/%)[]"));
    }
    @Test
    public void evaluateExp151Test(){
        assertEq("0", noVar("()[]"));
    }
    @Test
    public void evaluateExp152Test(){
        assertEq("-1", noVar("(5,3,/%)[-4]"));
    }
    @Test
    public void evaluateExp153Test(){
        assertEq("2", noVar("(5,3,/%)[-3]"));
    }
    @Test
    public void evaluateExp154Test(){
        assertEq("1", noVar("(5,3,/%)[-2]"));
    }
    @Test
    public void evaluateExp155Test(){
        assertEq("15", noVar("(5,3,/%)[-1]"));
    }
    @Test
    public void evaluateExp156Test(){
        assertEq("-1", noVar("(5,3,/%)[0]"));
    }
    @Test
    public void evaluateExp157Test(){
        assertEq("2", noVar("(5,3,/%)[1]"));
    }
    @Test
    public void evaluateExp158Test(){
        assertEq("1", noVar("(5,3,/%)[2]"));
    }
    @Test
    public void evaluateExp159Test(){
        assertEq("15", noVar("(5,3,/%)[3]"));
    }
    @Test
    public void evaluateExp160Test(){
        assertEq("#9", noVar("(5,3,/%)[4]"));
    }
    @Test
    public void evaluateExp161Test(){
        assertEq("#9", noVar("(5,3,/%)[-5]"));
    }
    @Test
    public void evaluateExp162Test(){
        assertEq("#9", noVar("(5,3,/%)[1/2]"));
    }
    @Test
    public void evaluateExp163Test(){
        assertEq("#4", noVar("(&)[0]"));
    }
    @Test
    public void evaluateExp164Test(){
        assertEq("#11", noVar("(5,3,/%)[1,2]"));
    }
    @Test
    public void evaluateExp165Test(){
        assertEq("#10", noVar("(5,3,/%)[0)"));
    }
    @Test
    public void evaluateExp166Test(){
        assertEq("#7", noVar("(5,3,/%]"));
    }
    @Test
    public void evaluateExp167Test(){
        assertEq("-1", noVar("(5,3,/%)[(5,3,/%)[0]+1]"));
    }
    @Test
    public void evaluateExp168Test(){
        assertEq("#9", noVar("(5,3,/%)[(&)]"));
    }
    @Test
    public void evaluateExp169Test(){
        assertEq("1", noVar("(17,/)"));
    }
    @Test
    public void evaluateExp170Test(){
        assertEq("0", noVar("(16,/)"));
    }
    @Test
    public void evaluateExp171Test(){
        assertEq("#5", noVar("(1/6,/)"));
    }
    @Test
    public void evaluateExp172Test(){
        assertEq("#5", noVar("((&),/)"));
    }
    @Test
    public void evaluateExp173Test(){
        assertEq("#5", noVar("(1,6,\\/)"));
    }
    @Test
    public void evaluateExp174Test(){
        assertEq("(1)", noVar("(1,||)"));
    }
    @Test
    public void evaluateExp175Test(){
        assertEq("(1,2,3,6)", noVar("(6,||)"));
    }
    @Test
    public void evaluateExp176Test(){
        assertEq("1", noVar("(1,||)[0]"));
    }
    @Test
    public void evaluateExp177Test(){
        assertEq("1", noVar("(1,||)[-1]"));
    }
    @Test
    public void evaluateExp178Test(){
        assertEq("#7", noVar("(1,||)[1]"));
    }
    @Test
    public void evaluateExp179Test(){
        assertEq("#7", noVar("(1,||)[-2]"));
    }
    @Test
    public void evaluateExp180Test(){
        assertEq("#9", noVar("(1,||)[0,0]"));
    }
    @Test
    public void evaluateExp181Test(){
        assertEq("#7", noVar("(1,||)[1/2]"));
    }
    @Test
    public void evaluateExp182Test(){
        assertEq("#7", noVar("(1,||)[(&)]"));
    }
    @Test
    public void evaluateExp183Test(){
        assertEq("#5", noVar("(1/2,||)"));
    }
    @Test
    public void evaluateExp184Test(){
        assertEq("#5", noVar("(1,2,||)"));
    }
    @Test
    public void evaluateExp185Test(){
        assertEq("#5", noVar("((&),||)"));
    }
    @Test
    public void evaluateExp186Test(){
        assertEq("#7", noVar("(1,(&),||)"));
    }
    @Test
    public void evaluateExp187Test(){
        assertEq("1", noVar("(1,||)[]"));
    }
    @Test
    public void evaluateExp188Test(){
        assertEq("1", noVar("(1,||)=(1,||)"));
    }
    @Test
    public void evaluateExp189Test(){
        assertEq("0", noVar("(5,||)=(7,||)"));
    }
    @Test
    public void evaluateExp190Test(){
        assertEq("0", noVar("(5,||)=(4,||)"));
    }
    @Test
    public void evaluateExp191Test(){
        assertEq("0", noVar("(5,||)=2"));
    }
    @Test
    public void evaluateExp192Test(){
        assertEq("3", noVar("1 +2"));
    }
    @Test
    public void evaluateExp193Test(){
        assertEq("#0", noVar("1 /2"));
    }
    @Test
    public void evaluateExp194Test(){
        assertEq("#0", noVar("1/ .2"));
    }
    @Test
    public void evaluateExp195Test(){
        assertEq("#7", noVar("(5/3,0,/)"));
    }
    @Test
    public void evaluateExp196Test(){
        assertEq("2", noVar("2!"));
    }
    @Test
    public void evaluateExp197Test(){
        assertEq("2", noVar("2!!"));
    }
    @Test
    public void evaluateExp198Test(){
        assertEq("-2", noVar("-2!"));
    }
    @Test
    public void evaluateExp199Test(){
        assertEq("1", noVar("2=2!"));
    }
    @Test
    public void evaluateExp200Test(){
        assertEq("3", noVar("(2!)+1"));
    }
    @Test
    public void evaluateExp201Test(){
        assertEq("3", noVar("(2!!)+1"));
    }
    @Test
    public void evaluateExp202Test(){
        assertEq("1", noVar("2!=2"));
    }
    @Test
    public void evaluateExp203Test(){
        assertEq("#6", noVar("(2,||)!"));
    }
    @Test
    public void evaluateExp204Test(){
        assertEq("6", noVar("(2,4,<=)"));
    }
    @Test
    public void evaluateExp205Test(){
        assertEq("#6", noVar("(-2,4,<=)"));
    }
    @Test
    public void evaluateExp206Test(){
        assertEq("#6", noVar("(2,-4,<=)"));
    }
    @Test
    public void evaluateExp207Test(){
        assertEq("#10", noVar("(2,(2,||),<=)"));
    }
    @Test
    public void evaluateExp208Test(){
        assertEq("#3", noVar("(2,<=)"));
    }
    @Test
    public void evaluateExp209Test(){
        assertEq("[[2 1]]", noVar("(2,&)"));
    }
    @Test
    public void evaluateExp210Test(){
        assertEq("[[2 1][3 1]]", noVar("(6,&)"));
    }
    @Test
    public void evaluateExp211Test(){
        assertEq("2", noVar("(162,&)[0][0]"));
    }
    @Test
    public void evaluateExp212Test(){
        assertEq("1", noVar("(162,&)[0][1]"));
    }
    @Test
    public void evaluateExp213Test(){
        assertEq("3", noVar("(162,&)[1][0]"));
    }
    @Test
    public void evaluateExp214Test(){
        assertEq("4", noVar("(162,&)[1][1]"));
    }
    @Test
    public void evaluateExp215Test(){
        assertEq("2", noVar("(162,&)[0,0]"));
    }
    @Test
    public void evaluateExp216Test(){
        assertEq("1", noVar("(162,&)[0,1]"));
    }
    @Test
    public void evaluateExp217Test(){
        assertEq("3", noVar("(162,&)[1,0]"));
    }
    @Test
    public void evaluateExp218Test(){
        assertEq("4", noVar("(162,&)[1,1]"));
    }
    @Test
    public void evaluateExp219Test(){
        assertEq("[2 1]", noVar("(162,&)[0]"));
    }
    @Test
    public void evaluateExp220Test(){
        assertEq("[3 4]", noVar("(162,&)[1]"));
    }
    @Test
    public void evaluateExp221Test(){
        assertEq("1", noVar("(2,&)!(6,&)"));
    }
    @Test
    public void evaluateExp222Test(){
        assertEq("1", noVar("(2,&)!(3,&)"));
    }
    @Test
    public void evaluateExp224Test(){
        assertEq("1", noVar("(2,&)!(4,&)"));
    }
    @Test
    public void evaluateExp225Test(){
        assertEq("1", noVar("(2,&)=(2,&)"));
    }
    @Test
    public void evaluateExp226Test(){
        assertEq("2", noVar("(162,&)[-2][-2]"));
    }
    @Test
    public void evaluateExp227Test(){
        assertEq("1", noVar("(162,&)[-2][-1]"));
    }
    @Test
    public void evaluateExp228Test(){
        assertEq("3", noVar("(162,&)[-1][-2]"));
    }
    @Test
    public void evaluateExp229Test(){
        assertEq("4", noVar("(162,&)[-1][-1]"));
    }
    @Test
    public void evaluateExp230Test(){
        assertEq("2", noVar("(162,&)[-2,-2]"));
    }
    @Test
    public void evaluateExp231Test(){
        assertEq("1", noVar("(162,&)[-2,-1]"));
    }
    @Test
    public void evaluateExp232Test(){
        assertEq("3", noVar("(162,&)[-1,-2]"));
    }
    @Test
    public void evaluateExp233Test(){
        assertEq("4", noVar("(162,&)[-1,-1]"));
    }
    @Test
    public void evaluateExp234Test(){
        assertEq("6", noVar("("+Long.MAX_VALUE+",&)[]"));
    }
    @Test
    public void evaluateExp235Test(){
        assertEq("2", noVar("("+Long.MAX_VALUE+",&)[0][]"));
    }
    @Test
    public void evaluateExp236Test(){
        assertEq("1", noVar("((1,&),-)"));
    }
    @Test
    public void evaluateExp237Test(){
        assertEq("-1", noVar("((-1,&),-)"));
    }
    @Test
    public void evaluateExp238Test(){
        assertEq("0", noVar("((0,&),-)"));
    }
    @Test
    public void evaluateExp239Test(){
        assertEq("1", noVar("(2,&)[0]!(3,&)[0]"));
    }
    @Test
    public void evaluateExp240Test(){
        assertEq("1", noVar("(2,&)[0]!(4,&)[0]"));
    }
    @Test
    public void evaluateExp241Test(){
        assertEq("1", noVar("(2,&)[0]=(2,&)[0]"));
    }
    @Test
    public void evaluateExp242Test(){
        assertEq("1", noVar("(2,&)!(3,&)[0]"));
    }
    @Test
    public void evaluateExp243Test(){
        assertEq("1", noVar("(2,&)[0]!(4,&)"));
    }
    @Test
    public void evaluateExp244Test(){
        assertEq("1", noVar("(2)!(-2)"));
    }
    @Test
    public void evaluateExp245Test(){
        assertEq("-[[2 1]]", noVar("(-2,&)"));
    }
    @Test
    public void evaluateExp246Test(){
        assertEq("#8", noVar("((2,||),&)"));
    }
    @Test
    public void evaluateExp247Test(){
        assertEq("#15", noVar("((2,||),(2,||),&)"));
    }
    @Test
    public void evaluateExp248Test(){
        assertEq("#11", noVar("(162,&)[-1,2]"));
    }
    @Test
    public void evaluateExp249Test(){
        assertEq("#12", noVar("(162,&)[-1][2]"));
    }
    @Test
    public void evaluateExp250Test(){
        assertEq("#8", noVar("(162,&)[2]"));
    }
    @Test
    public void evaluateExp251Test(){
        assertEq("#8", noVar("(162,&)[1/2,1]"));
    }
    @Test
    public void evaluateExp252Test(){
        assertEq("#10", noVar("(162,&)[1,1/2]"));
    }
    @Test
    public void evaluateExp253Test(){
        assertEq("#8", noVar("(162,&)[1/2][1]"));
    }
    @Test
    public void evaluateExp254Test(){
        assertEq("#11", noVar("(162,&)[1][1/2]"));
    }
    @Test
    public void evaluateExp255Test(){
        assertEq("#11", noVar("(162,&)[-1,-3]"));
    }
    @Test
    public void evaluateExp256Test(){
        assertEq("#12", noVar("(162,&)[-1][-3]"));
    }
    @Test
    public void evaluateExp257Test(){
        assertEq("#8", noVar("(162,&)[-3]"));
    }
    @Test
    public void evaluateExp258Test(){
        assertEq("#10", noVar("(162,&)[1,(2,||)]"));
    }
    @Test
    public void evaluateExp259Test(){
        assertEq("#8", noVar("(162,&)[(2,||),1]"));
    }
    @Test
    public void evaluateExp260Test(){
        assertEq("#11", noVar("(162,||)[0,0]"));
    }
    @Test
    public void evaluateExp261Test(){
        assertEq("#13", noVar("(162,||)[0,0,0]"));
    }
    @Test
    public void evaluateExp262Test(){
        assertEq("#8", noVar("(162,&)[-3,-1]"));
    }
    @Test
    public void evaluateExp263Test(){
        assertEq("#8", noVar("(162,&)[2,-1]"));
    }
    @Test
    public void evaluateExp264Test(){
        assertEq("((0,0,0,8,151),(0,0,1,7,151),(0,0,2,6,151),(0,0,3,5,151),(0,0,4,4,151),(0,1,1,6,151),(0,1,2,5,151),(0,1,3,4,151),(0,2,2,4,151),(0,2,3,3,151),(1,1,1,5,151),(1,1,2,4,151),(1,1,3,3,151),(1,2,2,3,151),(2,2,2,2,151))", noVar("(8,8,8,8,8,&&)"));
    }
    @Test
    public void evaluateExp265Test(){
        assertEq("((0,0,0,0,15,3789),(0,0,0,1,14,3789),(0,0,0,2,13,3789),(0,0,0,3,12,3789),(0,0,0,4,11,3789),(0,0,0,5,10,3789),(0,0,0,6,9,3789),(0,0,0,7,8,3789),(0,0,1,1,13,3789),(0,0,1,2,12,3789),(0,0,1,3,11,3789),(0,0,1,4,10,3789),(0,0,1,5,9,3789),(0,0,1,6,8,3789),(0,0,1,7,7,3789),(0,0,2,2,11,3789),(0,0,2,3,10,3789),(0,0,2,4,9,3789),(0,0,2,5,8,3789),(0,0,2,6,7,3789),(0,0,3,3,9,3789),(0,0,3,4,8,3789),(0,0,3,5,7,3789),(0,0,3,6,6,3789),(0,0,4,4,7,3789),(0,0,4,5,6,3789),(0,0,5,5,5,3789),(0,1,1,1,12,3789),(0,1,1,2,11,3789),(0,1,1,3,10,3789),(0,1,1,4,9,3789),(0,1,1,5,8,3789),(0,1,1,6,7,3789),(0,1,2,2,10,3789),(0,1,2,3,9,3789),(0,1,2,4,8,3789),(0,1,2,5,7,3789),(0,1,2,6,6,3789),(0,1,3,3,8,3789),(0,1,3,4,7,3789),(0,1,3,5,6,3789),(0,1,4,4,6,3789),(0,1,4,5,5,3789),(0,2,2,2,9,3789),(0,2,2,3,8,3789),(0,2,2,4,7,3789),(0,2,2,5,6,3789),(0,2,3,3,7,3789),(0,2,3,4,6,3789),(0,2,3,5,5,3789),(0,2,4,4,5,3789),(0,3,3,3,6,3789),(0,3,3,4,5,3789),(0,3,4,4,4,3789),(1,1,1,1,11,3789),(1,1,1,2,10,3789),(1,1,1,3,9,3789),(1,1,1,4,8,3789),(1,1,1,5,7,3789),(1,1,1,6,6,3789),(1,1,2,2,9,3789),(1,1,2,3,8,3789),(1,1,2,4,7,3789),(1,1,2,5,6,3789),(1,1,3,3,7,3789),(1,1,3,4,6,3789),(1,1,3,5,5,3789),(1,1,4,4,5,3789),(1,2,2,2,8,3789),(1,2,2,3,7,3789),(1,2,2,4,6,3789),(1,2,2,5,5,3789),(1,2,3,3,6,3789),(1,2,3,4,5,3789),(1,2,4,4,4,3789),(1,3,3,3,5,3789),(1,3,3,4,4,3789),(2,2,2,2,7,3789),(2,2,2,3,6,3789),(2,2,2,4,5,3789),(2,2,3,3,5,3789),(2,2,3,4,4,3789),(2,3,3,3,4,3789),(3,3,3,3,3,3789))", noVar("(15,22,14,14,14,14,&&)"));
    }
    @Test
    public void evaluateExp266Test(){
        assertEq("(0,0,0,8,151)", noVar("(8,8,8,8,8,&&)[0]"));
    }
    @Test
    public void evaluateExp267Test(){
        assertEq("151", noVar("(8,8,8,8,8,&&)[0,4]"));
    }
    @Test
    public void evaluateExp268Test(){
        assertEq("151", noVar("(8,8,8,8,8,&&)[0][4]"));
    }
    @Test
    public void evaluateExp269Test(){
        assertEq("15", noVar("(8,8,8,8,8,&&)[]"));
    }
    @Test
    public void evaluateExp270Test(){
        assertEq("0", noVar("(8,8,8,8,8,&&)=(8,8,8,9,7,&&)"));
    }
    @Test
    public void evaluateExp271Test(){
        assertEq("(0,0,0,8,151)", noVar("(8,8,8,8,8,&&)[-15]"));
    }
    @Test
    public void evaluateExp272Test(){
        assertEq("151", noVar("(8,8,8,8,8,&&)[-15,-1]"));
    }
    @Test
    public void evaluateExp273Test(){
        assertEq("151", noVar("(8,8,8,8,8,&&)[-15][-1]"));
    }
    @Test
    public void evaluateExp274Test(){
        assertEq("8", noVar("(8,8,8,8,8,&&)[0][3]"));
    }
    @Test
    public void evaluateExp275Test(){
        assertEq("8", noVar("(8,8,8,8,8,&&)[0,3]"));
    }
    @Test
    public void evaluateExp276Test(){
        assertEq("#17", noVar("(8,8,8,8,8,&&)[0,5]"));
    }
    @Test
    public void evaluateExp277Test(){
        assertEq("#15", noVar("(8,8,8,8,8,&&)[15][4]"));
    }
    @Test
    public void evaluateExp278Test(){
        assertEq("#15", noVar("(8,8,8,8,8,&&)[15,4]"));
    }
    @Test
    public void evaluateExp279Test(){
        assertEq("1", noVar("(8,8,8,8,8,&&)=(8,8,8,8,8,&&)"));
    }
    @Test
    public void evaluateExp280Test(){
        assertEq("0", noVar("(8,8,8,8,8,&&)=(15,22,14,14,14,14,&&)"));
    }
    @Test
    public void evaluateExp281Test(){
        assertEq("0", noVar("(8,8,8,8,8,&&)=8"));
    }
    @Test
    public void evaluateExp282Test(){
        assertEq("#7", noVar("(8,1/2,&&)"));
    }
    @Test
    public void evaluateExp283Test(){
        assertEq("#1", noVar("(&&)"));
    }
    @Test
    public void evaluateExp284Test(){
        assertEq("#12", noVar("(1,0,(2,||),<=)"));
    }
    @Test
    public void evaluateExp285Test(){
        assertEq("1", noVar("(1,0,2,<=,<=)"));
    }
    @Test
    public void evaluateExp286Test(){
        assertEq("#9", noVar("(1,0,(&),<=,<=)"));
    }
    @Test
    public void evaluateExp287Test(){
        assertEq("1", noVar("(1,0,2,<,<)"));
    }
    @Test
    public void evaluateExp288Test(){
        assertEq("#9", noVar("(1,0,(&),<,<)"));
    }
    @Test
    public void evaluateExp289Test(){
        assertEq("1", noVar("(1,0,2,<,<=)"));
    }
    @Test
    public void evaluateExp290Test(){
        assertEq("#9", noVar("(1,0,(&),<,<=)"));
    }
    @Test
    public void evaluateExp291Test(){
        assertEq("1", noVar("(1,0,2,<=,<)"));
    }
    @Test
    public void evaluateExp292Test(){
        assertEq("#9", noVar("(1,0,(&),<=,<)"));
    }
    @Test
    public void evaluateExp293Test(){
        assertEq("1", noVar("(1,0,==<)"));
    }
    @Test
    public void evaluateExp294Test(){
        assertEq("#7", noVar("(1,(&),==<)"));
    }
    @Test
    public void evaluateExp295Test(){
        assertEq("1", noVar("(1,0,==>)"));
    }
    @Test
    public void evaluateExp296Test(){
        assertEq("#7", noVar("(1,(&),==>)"));
    }
    @Test
    public void evaluateExp297Test(){
        assertEq("1", noVar("(1,2,>==)"));
    }
    @Test
    public void evaluateExp298Test(){
        assertEq("#7", noVar("(1,(&),>==)"));
    }
    @Test
    public void evaluateExp299Test(){
        assertEq("1", noVar("(1,2,<==)"));
    }
    @Test
    public void evaluateExp300Test(){
        assertEq("#7", noVar("(1,(&),<==)"));
    }
    @Test
    public void evaluateExp301Test(){
        assertEq("1", noVar("((1<>1,2<>1),?)>0"));
    }
    @Test
    public void evaluateExp302Test(){
        assertEq("1", noVar("(1<>1,2<>1,?)>0"));
    }
    @Test
    public void evaluateExp303Test(){
        assertEq("3", noVar("(1<>2,3<>4,5<>6)[]"));
    }
    @Test
    public void evaluateExp304Test(){
        assertEq("2", noVar("(1<>2,3<>4,5<>6)[0][]"));
    }
    @Test
    public void evaluateExp305Test(){
        assertEq("4", noVar("(1<>2,3<>4,5<>6)[1][1]"));
    }
    @Test
    public void evaluateExp306Test(){
        assertEq("4", noVar("(1<>2,3<>4,5<>6)[1,1]"));
    }
    @Test
    public void evaluateExp307Test(){
        assertEq("1", noVar("((1<>1,2<>1),2,?)>0"));
    }
    @Test
    public void evaluateExp308Test(){
        assertEq("#10", noVar("((&),2<>1,?)"));
    }
    @Test
    public void evaluateExp309Test(){
        assertEq("#1", noVar("(?)"));
    }
    @Test
    public void evaluateExp310Test(){
        assertEq("#0", noVar("1<>2<>3"));
    }
    @Test
    public void evaluateExp311Test(){
        assertEq("#4", noVar("((),?)"));
    }
    @Test
    public void evaluateExp312Test(){
        assertEq("#6", noVar("((),1,?)"));
    }
    @Test
    public void evaluateExp313Test(){
        assertEq("#12", noVar("((1<>1),1/2,?)"));
    }
    @Test
    public void evaluateExp314Test(){
        assertEq("#11", noVar("((1<>1),-1,?)"));
    }
    @Test
    public void evaluateExp315Test(){
        assertEq("1", noVar("((1<>1,2<>1),0,?)>0"));
    }
    @Test
    public void evaluateExp316Test(){
        assertEq("#5", noVar("((&),?)"));
    }
    @Test
    public void evaluateExp317Test(){
        assertEq("#12", noVar("((1<>1),(&),?)"));
    }
    @Test
    public void evaluateExp318Test(){
        assertEq("3", noVar("(1<>2,3<>4,5<>6)[1][0]"));
    }
    @Test
    public void evaluateExp319Test(){
        assertEq("3", noVar("(1<>2,3<>4,5<>6)[1,0]"));
    }
    @Test
    public void evaluateExp320Test(){
        assertEq("2", noVar("(1<>2,3<>4,5<>6)[-3][]"));
    }
    @Test
    public void evaluateExp321Test(){
        assertEq("4", noVar("(1<>2,3<>4,5<>6)[-2][-1]"));
    }
    @Test
    public void evaluateExp322Test(){
        assertEq("4", noVar("(1<>2,3<>4,5<>6)[-2,-1]"));
    }
    @Test
    public void evaluateExp323Test(){
        assertEq("3", noVar("(1<>2,3<>4,5<>6)[-2][-2]"));
    }
    @Test
    public void evaluateExp324Test(){
        assertEq("3", noVar("(1<>2,3<>4,5<>6)[1,-2]"));
    }
    @Test
    public void evaluateExp325Test(){
        assertEq("#17", noVar("(1<>2,3<>4,5<>6)[-4][]"));
    }
    @Test
    public void evaluateExp326Test(){
        assertEq("#21", noVar("(1<>2,3<>4,5<>6)[-2][-3]"));
    }
    @Test
    public void evaluateExp327Test(){
        assertEq("#17", noVar("(1<>2,3<>4,5<>6)[-4,-1]"));
    }
    @Test
    public void evaluateExp328Test(){
        assertEq("#20", noVar("(1<>2,3<>4,5<>6)[-3,-3]"));
    }
    @Test
    public void evaluateExp329Test(){
        assertEq("#7", noVar("((2,||)<>2)"));
    }
    @Test
    public void evaluateExp330Test(){
        assertEq("#2", noVar("(2<>(2,||))"));
    }
    @Test
    public void evaluateExp331Test(){
        assertEq("#2", noVar("(2<>1/2)"));
    }
    @Test
    public void evaluateExp332Test(){
        assertEq("#2", noVar("(2<>-2)"));
    }
    @Test
    public void evaluateExp333Test(){
        assertEq("1", noVar("(2<>2)=(2<>2)"));
    }
    @Test
    public void evaluateExp334Test(){
        assertEq("0", noVar("(2<>2)=(2<>3)"));
    }
    @Test
    public void evaluateExp335Test(){
        assertEq("0", noVar("(2<>2)=(3<>2)"));
    }
    @Test
    public void evaluateExp336Test(){
        assertEq("0", noVar("(2<>2)=(3<>2,3<>2)"));
    }
    @Test
    public void evaluateExp337Test(){
        assertEq("0", noVar("(2<>2)=2"));
    }
    @Test
    public void evaluateExp338Test(){
        assertEq("0", noVar("(2<>2)[0]=2"));
    }
    @Test
    public void evaluateExp339Test(){
        assertEq("1", noVar("(2<>2)[0]=(2<>2)[0]"));
    }
    @Test
    public void evaluateExp340Test(){
        assertEq("0", noVar("(2<>2)[0]=(2<>1)[0]"));
    }
    @Test
    public void evaluateExp341Test(){
        assertEq("0", noVar("(2<>2)[0]=(1<>2)[0]"));
    }
    @Test
    public void evaluateExp342Test(){
        assertEq("2<>2", noVar("(2<>2)[0]"));
    }
    @Test
    public void evaluateExp343Test(){
        assertEq("(2<>2,3<>3)", noVar("(2<>2,3<>3)"));
    }
    @Test
    public void evaluateExp344Test(){
        assertEq("1", noVar("((1,2),?)>0"));
    }
    @Test
    public void evaluateExp345Test(){
        assertEq("1", noVar("(1,2,?)>0"));
    }
    @Test
    public void evaluateExp346Test(){
        assertEq("1", noVar("(1,?)>0"));
    }
    @Test
    public void evaluateExp347Test(){
        assertEq("(1,3,2,2/3)", noVar("(1,2,3,<>-|)"));
    }
    @Test
    public void evaluateExp348Test(){
        assertEq("(1,3,2,4/5)", noVar("(1<>2,2<>1,3<>2,<>-|)"));
    }
    @Test
    public void evaluateExp349Test(){
        assertEq("0", noVar("(1,2,3,<>-|)=(1<>2,2<>1,3<>2,<>-|)"));
    }
    @Test
    public void evaluateExp350Test(){
        assertEq("0", noVar("(1,3,3,<>-|)=(1,1,3,<>-|)"));
    }
    @Test
    public void evaluateExp351Test(){
        assertEq("0", noVar("(1,3,<>-|)=(1,4,<>-|)"));
    }
    @Test
    public void evaluateExp352Test(){
        assertEq("0", noVar("(2,4,<>-|)=(1,4,<>-|)"));
    }
    @Test
    public void evaluateExp353Test(){
        assertEq("1", noVar("(1,2,3,<>-|)[0]"));
    }
    @Test
    public void evaluateExp354Test(){
        assertEq("3", noVar("(1,2,3,<>-|)[1]"));
    }
    @Test
    public void evaluateExp355Test(){
        assertEq("2", noVar("(1,2,3,<>-|)[2]"));
    }
    @Test
    public void evaluateExp356Test(){
        assertEq("2/3", noVar("(1,2,3,<>-|)[3]"));
    }
    @Test
    public void evaluateExp357Test(){
        assertEq("1", noVar("(1,2,3,<>-|)[-4]"));
    }
    @Test
    public void evaluateExp358Test(){
        assertEq("3", noVar("(1,2,3,<>-|)[-3]"));
    }
    @Test
    public void evaluateExp359Test(){
        assertEq("2", noVar("(1,2,3,<>-|)[-2]"));
    }
    @Test
    public void evaluateExp360Test(){
        assertEq("2/3", noVar("(1,2,3,<>-|)[-1]"));
    }
    @Test
    public void evaluateExp361Test(){
        assertEq("#13", noVar("(1,2,3,<>-|)[4]"));
    }
    @Test
    public void evaluateExp362Test(){
        assertEq("#13", noVar("(1,2,3,<>-|)[-5]"));
    }
    @Test
    public void evaluateExp363Test(){
        assertEq("#5", noVar("((&),<>-|)"));
    }
    @Test
    public void evaluateExp364Test(){
        assertEq("4", noVar("(1,2,3,<>-|)[]"));
    }
    @Test
    public void evaluateExp365Test(){
        assertEq("(1,3,2,2/3)", noVar("((1,2,3),<>-|)"));
    }
    @Test
    public void evaluateExp366Test(){
        assertEq("0", noVar("(<>-|)=0"));
    }
    @Test
    public void evaluateExp367Test(){
        assertEq("1", noVar("3((&),1,1/0)"));
    }
    @Test
    public void evaluateExp368Test(){
        assertEq("1", noVar("3((|),1/0,1)"));
    }
    @Test
    public void evaluateExp369Test(){
        assertEq("#5", noVar("3((|))"));
    }
    @Test
    public void evaluateExp370Test(){
        assertEq("#2", noVar("3(0,1,2)"));
    }
    @Test
    public void evaluateExp371Test(){
        assertEq("#7", noVar("(1,1,2,<,>)"));
    }
    @Test
    public void evaluateExp372Test(){
        assertEq("#7", noVar("(1,1,2,>,>)"));
    }
    @Test
    public void evaluateExp373Test(){
        assertEq("1", noVar("(2,&)!(-2,&)"));
    }
    @Test
    public void evaluateExp374Test(){
        assertEq("(3,1,;)", noVar("(1,0,;)+(2,1,;)"));
    }
    @Test
    public void evaluateExp375Test(){
        assertEq("(-2,0,;):(-4,-2,;)", noVar("(1,0,;):(2,1,;)"));
    }
    @Test
    public void evaluateExp376Test(){
        assertEq("(2,;)", noVar("((2,1,;),(1,0,;),/)"));
    }
    @Test
    public void evaluateExp377Test(){
        assertEq("(1,;)", noVar("((2,1,;),(1,0,;),%)"));
    }
    @Test
    public void evaluateExp378Test(){
        assertEq("(3,1,;)", noVar("(3<>1,1<>0,;)"));
    }
    @Test
    public void evaluateExp379Test(){
        assertEq("(1,0,0,;)", noVar("(1<>2,;)"));
    }
    @Test
    public void evaluateExp380Test(){
        assertEq("(1,;):(1,0,;)", noVar("((2,1,;):(1,0,;),(1,;),%)"));
    }
    @Test
    public void evaluateExp381Test(){
        assertEq("(2,;)", noVar("((2,1,;):(1,0,;),0/)"));
    }
    @Test
    public void evaluateExp382Test(){
        assertEq("(2,1,0,;)", noVar("(1,0,;)*(2,1,;)"));
    }
    @Test
    public void evaluateExp383Test(){
        assertEq("(-1,-1,;)", noVar("(1,0,;)-(2,1,;)"));
    }
    @Test
    public void evaluateExp384Test(){
        assertEq("#7", noVar("(1,0,;):(0,;)"));
    }
    @Test
    public void evaluateExp385Test(){
        assertEq("(0,;)", noVar("((1,1,;),(-1,;))"));
    }
    @Test
    public void evaluateExp386Test(){
        assertEq("2", noVar("((1,1,;),1)"));
    }
    @Test
    public void evaluateExp387Test(){
        assertEq("1", noVar("(1,2,;)[0]"));
    }
    @Test
    public void evaluateExp388Test(){
        assertEq("2", noVar("(1,2,;)[]"));
    }
    @Test
    public void evaluateExp389Test(){
        assertEq("1", noVar("(1,2,;)[-2]"));
    }
    @Test
    public void evaluateExp390Test(){
        assertEq("#8", noVar("(1,2,;)[2]"));
    }
    @Test
    public void evaluateExp391Test(){
        assertEq("(2,1,;)", noVar("((2,1,;):(1,0,;),/0)"));
    }
    @Test
    public void evaluateExp392Test(){
        assertEq("(1,0,;)", noVar("((2,1,;):(1,0,;),/1)"));
    }
    @Test
    public void evaluateExp393Test(){
        assertEq("#15", noVar("((1,;):(1,1,;),(-1,;))"));
    }
    @Test
    public void evaluateExp394Test(){
        assertEq("#15", noVar("((1,;):(1,1,;),-1)"));
    }
    @Test
    public void evaluateExp395Test(){
        assertEq("#5", noVar("(1,;)+(1,||)"));
    }
    @Test
    public void evaluateExp396Test(){
        assertEq("(2,0,;)", noVar("((2,1,;)<>0,;)"));
    }
    @Test
    public void evaluateExp397Test(){
        assertEq("2<>1", noVar("(2,1,;)<>0"));
    }
    @Test
    public void evaluateExp398Test(){
        assertEq("#15", noVar("((1,;):(1,1,;),(1,||))"));
    }
    @Test
    public void evaluateExp399Test(){
        assertEq("#8", noVar("((1,||),;)"));
    }
    @Test
    public void evaluateExp400Test(){
        assertEq("#7", noVar("(2,1,;)<>2"));
    }
    @Test
    public void evaluateExp401Test(){
        assertEq("#7", noVar("(2,1,;)<>1/2"));
    }
    @Test
    public void evaluateExp402Test(){
        assertEq("#15", noVar("(2,1,;):(1,0,;)<>2"));
    }
    @Test
    public void evaluateExp403Test(){
        assertEq("2<>1", noVar("(2,1,;)<>-2"));
    }
    @Test
    public void evaluateExp404Test(){
        assertEq("0", noVar("((2,2,;)<>-2)=((2,2,;)<>-1)"));
    }
    @Test
    public void evaluateExp405Test(){
        assertEq("0", noVar("((2,2,;)<>-2)=((1,2,;)<>-2)"));
    }
    @Test
    public void evaluateExp406Test(){
        assertEq("1", noVar("((2,2,;)<>-2)=((2,2,;)<>-2)"));
    }
    @Test
    public void evaluateExp407Test(){
        assertEq("0", noVar("((2,2,;)<>-2)=3"));
    }
    @Test
    public void evaluateExp408Test(){
        assertEq("#7", noVar("(2,1,;)<>-3"));
    }
    @Test
    public void evaluateExp409Test(){
        assertEq("#7", noVar("(2,1,;)<>(1,||)"));
    }
    @Test
    public void evaluateExp410Test(){
        assertEq("#2", noVar("(1<>(1,||),;)"));
    }
    @Test
    public void evaluateExp411Test(){
        assertEq("#2", noVar("(1<>1/2,;)"));
    }
    @Test
    public void evaluateExp412Test(){
        assertEq("#2", noVar("(1<>-2,;)"));
    }
    @Test
    public void evaluateExp413Test(){
        assertEq("#1", noVar("(1<>2<>3,;)"));
    }
    @Test
    public void evaluateExp414Test(){
        assertEq("#16", noVar("((1<>2,;),(0,;),/)"));
    }
    @Test
    public void evaluateExp415Test(){
        assertEq("#16", noVar("((1<>2,;),(0,;),%)"));
    }
    @Test
    public void evaluateExp416Test(){
        assertEq("#30", noVar("((1<>2,;):(1<>1,1<>0,;),(0,;),%)"));
    }
    @Test
    public void evaluateExp417Test(){
        assertEq("(1,0,0,;)", noVar("((1<>1,;),2,^)"));
    }
    @Test
    public void evaluateExp418Test(){
        assertEq("(0,;)", noVar("((0,;),2,^)"));
    }
    @Test
    public void evaluateExp419Test(){
        assertEq("(1,;):(1,0,;)", noVar("((1<>1,;),-1,^)"));
    }
    @Test
    public void evaluateExp420Test(){
        assertEq("#10", noVar("((0,;),-1,^)"));
    }
    @Test
    public void evaluateExp421Test(){
        assertEq("#14", noVar("((1<>1,;),1/2,^)"));
    }
    @Test
    public void evaluateExp422Test(){
        assertEq("#17", noVar("((1<>1,;),(1,||),^)"));
    }
    @Test
    public void evaluateExp423Test(){
        assertEq("((-1,0,;),(1,;),(1,;),(1,0,1,0,;))", noVar("((1<>1,;),(1<>2,1<>0,;),/%)"));
    }
    @Test
    public void evaluateExp424Test(){
        assertEq("(-1,0,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[0]"));
    }
    @Test
    public void evaluateExp425Test(){
        assertEq("(1,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[1]"));
    }
    @Test
    public void evaluateExp426Test(){
        assertEq("(1,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[2]"));
    }
    @Test
    public void evaluateExp427Test(){
        assertEq("(1,0,1,0,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[3]"));
    }
    @Test
    public void evaluateExp428Test(){
        assertEq("(-1,0,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[-4]"));
    }
    @Test
    public void evaluateExp429Test(){
        assertEq("(1,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[-3]"));
    }
    @Test
    public void evaluateExp430Test(){
        assertEq("(1,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[-2]"));
    }
    @Test
    public void evaluateExp431Test(){
        assertEq("(1,0,1,0,;)", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[-1]"));
    }
    @Test
    public void evaluateExp432Test(){
        assertEq("#28", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[4]"));
    }
    @Test
    public void evaluateExp433Test(){
        assertEq("#28", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[-5]"));
    }
    @Test
    public void evaluateExp434Test(){
        assertEq("4", noVar("((1<>1,;),(1<>2,1<>0,;),/%)[]"));
    }
    @Test
    public void evaluateExp435Test(){
        assertEq("0", noVar("(1<>1,;)=(1<>2,1<>0,;)"));
    }
    @Test
    public void evaluateExp436Test(){
        assertEq("1", noVar("(1<>1,;)=(1<>1,;)"));
    }
    @Test
    public void evaluateExp437Test(){
        assertEq("0", noVar("(1<>1,;):(1<>2,;)=(1<>1,;)"));
    }
    @Test
    public void evaluateExp438Test(){
        assertEq("1", noVar("(1<>1,;):(1<>2,;)=(1<>1,;):(1<>2,;)"));
    }
    @Test
    public void evaluateExp439Test(){
        assertEq("0", noVar("(1<>1,;):(1<>2,;)=(1,||)"));
    }
    @Test
    public void evaluateExp440Test(){
        assertEq("(-1,0,;)", noVar("-(1<>1,;)"));
    }
    @Test
    public void evaluateExp441Test(){
        assertEq("#0", noVar("+(1<>1,;)"));
    }
    @Test
    public void evaluateExp442Test(){
        assertEq("0", noVar("((5<>1,;),(3<>1,;),/%)=((3<>1,;),(5<>1,;),/%)"));
    }
    @Test
    public void evaluateExp443Test(){
        assertEq("0", noVar("((-1<>1,;),(1<>1,;),/%)=((-2<>1,;),(1<>1,;),/%)"));
    }
    @Test
    public void evaluateExp444Test(){
        assertEq("0", noVar("((1<>1,;),(1<>2,1<>0,;),/%)=((-2<>1,;),(1<>1,;),/%)"));
    }
    @Test
    public void evaluateExp445Test(){
        assertEq("0", noVar("((1<>1,;),(1<>2,1<>0,;),/%)=((1<>1,;),(1<>2,2<>0,;),/%)"));
    }
    @Test
    public void evaluateExp446Test(){
        assertEq("1", noVar("((5<>1,;),(3<>1,;),/%)=((5<>1,;),(3<>1,;),/%)"));
    }
    @Test
    public void evaluateExp447Test(){
        assertEq("0", noVar("((1<>2,1<>0,;),(1<>1,;),/%)=((-2<>1,;),(1<>1,;),/%)"));
    }
    @Test
    public void evaluateExp448Test(){
        assertEq("0", noVar("((1<>2,1<>0,;),(1<>1,;),/%)=((1<>1,;),(1<>2,2<>0,;),/%)"));
    }
    @Test
    public void evaluateExp449Test(){
        assertEq("0", noVar("((1<>2,1<>0,;),(1<>1,;),/%)=((1<>1,;),(-2<>1,;),/%)"));
    }
    @Test
    public void evaluateExp450Test(){
        assertEq("0", noVar("((1<>2,1<>0,;),(1<>1,;),/%)=((1<>2,2<>0,;),(1<>1,;),/%)"));
    }
    @Test
    public void evaluateExp451Test(){
        assertEq("0", noVar("((1<>2,1<>0,;),(1<>1,;),/%)=((1<>2,1<>0,;),(1<>1,1<>0,;),/%)"));
    }
    @Test
    public void evaluateExp452Test(){
        assertEq("0", noVar("((1<>2,1<>0,;),(1<>1,;),/%)=(1,||)"));
    }
    @Test
    public void evaluateExp453Test(){
        assertEq("[[-1 1][1 1]]", noVar("((1<>2,-1<>0,;),&)"));
    }
    @Test
    public void evaluateExp454Test(){
        assertEq("((1,1,;),(1,-1,;))", noVar("((1<>2,-1<>0,;),||)"));
    }
    @Test
    public void evaluateExp455Test(){
        assertEq("[-1 1]", noVar("((1<>2,-1<>0,;),&)[0]"));
    }
    @Test
    public void evaluateExp456Test(){
        assertEq("-1", noVar("((1<>2,-1<>0,;),&)[0][0]"));
    }
    @Test
    public void evaluateExp457Test(){
        assertEq("2", noVar("((1<>2,-1<>0,;),&)[]"));
    }
    @Test
    public void evaluateExp458Test(){
        assertEq("2", noVar("((1<>2,-1<>0,;),&)[0][]"));
    }
    @Test
    public void evaluateExp459Test(){
        assertEq("-1", noVar("((1<>2,-1<>0,;),&)[0,0]"));
    }
    @Test
    public void evaluateExp460Test(){
        assertEq("1", noVar("((1<>2,-1<>0,;),&)[0][1]"));
    }
    @Test
    public void evaluateExp461Test(){
        assertEq("1", noVar("((1<>2,-1<>0,;),&)[0,1]"));
    }
    @Test
    public void evaluateExp462Test(){
        assertEq("(1,1,;)", noVar("((1<>2,-1<>0,;),||)[0]"));
    }
    @Test
    public void evaluateExp463Test(){
        assertEq("2", noVar("((1<>2,-1<>0,;),||)[]"));
    }
    @Test
    public void evaluateExp464Test(){
        assertEq("[-1 1]", noVar("((1<>2,-1<>0,;),&)[-2]"));
    }
    @Test
    public void evaluateExp465Test(){
        assertEq("-1", noVar("((1<>2,-1<>0,;),&)[-2][-2]"));
    }
    @Test
    public void evaluateExp466Test(){
        assertEq("2", noVar("((1<>2,-1<>0,;),&)[-2][]"));
    }
    @Test
    public void evaluateExp467Test(){
        assertEq("-1", noVar("((1<>2,-1<>0,;),&)[-2,-2]"));
    }
    @Test
    public void evaluateExp468Test(){
        assertEq("1", noVar("((1<>2,-1<>0,;),&)[-2][-1]"));
    }
    @Test
    public void evaluateExp469Test(){
        assertEq("1", noVar("((1<>2,-1<>0,;),&)[-2,-1]"));
    }
    @Test
    public void evaluateExp470Test(){
        assertEq("(1,1,;)", noVar("((1<>2,-1<>0,;),||)[-2]"));
    }
    @Test
    public void evaluateExp471Test(){
        assertEq("#22", noVar("((1<>2,-1<>0,;),&)[-2,2]"));
    }
    @Test
    public void evaluateExp472Test(){
        assertEq("#19", noVar("((1<>2,-1<>0,;),&)[2,0]"));
    }
    @Test
    public void evaluateExp473Test(){
        assertEq("#19", noVar("((1<>2,-1<>0,;),&)[2]"));
    }
    @Test
    public void evaluateExp474Test(){
        assertEq("#23", noVar("((1<>2,-1<>0,;),&)[-2][2]"));
    }
    @Test
    public void evaluateExp475Test(){
        assertEq("#20", noVar("((1<>2,-1<>0,;),||)[-3]"));
    }
    @Test
    public void evaluateExp476Test(){
        assertEq("0", noVar("((1<>2,-1<>0,;),&)[0]=((1<>2,-1<>0,;),&)[1]"));
    }
    @Test
    public void evaluateExp477Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),&)[0]=((1<>2,-1<>0,;),&)[1]"));
    }
    @Test
    public void evaluateExp478Test(){
        assertEq("1", noVar("((1<>2,-1<>0,;),&)[0]=((1<>2,-1<>0,;),&)[0]"));
    }
    @Test
    public void evaluateExp479Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),&)[0]=((1<>2,-1<>0,;),&)"));
    }
    @Test
    public void evaluateExp480Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),&)=((1<>2,-1<>0,;),&)"));
    }
    @Test
    public void evaluateExp481Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),&)=((1<>2,-4<>1,4<>0,;),&)"));
    }
    @Test
    public void evaluateExp482Test(){
        assertEq("1", noVar("((1<>2,-2<>1,1<>0,;),&)=((1<>2,-2<>1,1<>0,;),&)"));
    }
    @Test
    public void evaluateExp483Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),&)=((1<>2,-4<>1,4<>0,;),&)[0]"));
    }
    @Test
    public void evaluateExp484Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),||)=((1<>2,-4<>1,4<>0,;),||)"));
    }
    @Test
    public void evaluateExp485Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),||)=((1<>1,-1<>0,;),||)"));
    }
    @Test
    public void evaluateExp486Test(){
        assertEq("1", noVar("((1<>2,-2<>1,1<>0,;),||)=((1<>2,-2<>1,1<>0,;),||)"));
    }
    @Test
    public void evaluateExp487Test(){
        assertEq("0", noVar("((1<>2,-2<>1,1<>0,;),||)=((1<>2,-2<>1,1<>0,;),&)"));
    }
    @Test
    public void evaluateExp488Test(){
        assertEq("(2,-2,;)", noVar("((1<>2,-2<>1,1<>0,;),')"));
    }
    @Test
    public void evaluateExp489Test(){
        assertEq("(2,;)", noVar("((1<>2,-2<>1,1<>0,;),'')"));
    }
    @Test
    public void evaluateExp490Test(){
        assertEq("#21", noVar("((1<>2,-2<>1,1<>0,;),)"));
    }
    @Test
    public void evaluateExp491Test(){
        assertEq("#5", noVar("((&),')"));
    }
    @Test
    public void evaluateExp492Test(){
        assertEq("(1,0,-1,;)", noVar("(1=>0,-1=>0,0=>-1,;)"));
    }
    @Test
    public void evaluateExp493Test(){
        assertEq("0", noVar("(1=>0)=1"));
    }
    @Test
    public void evaluateExp494Test(){
        assertEq("0", noVar("(1=>0)=(-1=>0)"));
    }
    @Test
    public void evaluateExp495Test(){
        assertEq("0", noVar("(1=>0)=(1=>1)"));
    }
    @Test
    public void evaluateExp496Test(){
        assertEq("1", noVar("(1=>0)=(1=>0)"));
    }
    @Test
    public void evaluateExp497Test(){
        assertEq("1=>0", noVar("1=>0"));
    }
    @Test
    public void evaluateExp498Test(){
        assertEq("#13", noVar("(1=>0,-1=>0,0=>(1,||),;)"));
    }
    @Test
    public void evaluateExp499Test(){
        assertEq("#18", noVar("(1=>0,-1=>0,(1,||)=>-1,;)"));
    }
    @Test
    public void evaluateExp500Test(){
        assertEq("{1;2;3}", noVar("{1;2;3}"));
    }
    @Test
    public void evaluateExp501Test(){
        assertEq("{1,2;3,4}", noVar("{1,2;3,4}"));
    }
    @Test
    public void evaluateExp502Test(){
        assertEq("3", noVar("{1;2;3}[]"));
    }
    @Test
    public void evaluateExp503Test(){
        assertEq("2", noVar("{1;2;3}[1]"));
    }
    @Test
    public void evaluateExp504Test(){
        assertEq("2", noVar("{1;2;3}[-2]"));
    }
    @Test
    public void evaluateExp505Test(){
        assertEq("2", noVar("{1,2;3,4}[]"));
    }
    @Test
    public void evaluateExp506Test(){
        assertEq("2", noVar("{1,2;3,4}[1][]"));
    }
    @Test
    public void evaluateExp507Test(){
        assertEq("3,4", noVar("{1,2;3,4}[1]"));
    }
    @Test
    public void evaluateExp508Test(){
        assertEq("3,4", noVar("{1,2;3,4}[-1]"));
    }
    @Test
    public void evaluateExp509Test(){
        assertEq("3", noVar("{1,2;3,4}[1][0]"));
    }
    @Test
    public void evaluateExp510Test(){
        assertEq("3", noVar("{1,2;3,4}[-1][-2]"));
    }
    @Test
    public void evaluateExp511Test(){
        assertEq("3", noVar("{1,2,3;4,5,6}[0][]"));
    }
    @Test
    public void evaluateExp512Test(){
        assertEq("#0", noVar("[1;2]"));
    }
    @Test
    public void evaluateExp513Test(){
        assertEq("#13", noVar("{1,2,3;4,5,6}+{1,2;3,4}"));
    }
    @Test
    public void evaluateExp514Test(){
        assertEq("#8", noVar("{1;2;3}[3]"));
    }
    @Test
    public void evaluateExp515Test(){
        assertEq("#8", noVar("{1;2;3}[-4]"));
    }
    @Test
    public void evaluateExp516Test(){
        assertEq("#13", noVar("{1,2;3,4}[1][2]"));
    }
    @Test
    public void evaluateExp517Test(){
        assertEq("#14", noVar("{1,2;3,4}[-1][-3]"));
    }
    @Test
    public void evaluateExp518Test(){
        assertEq("3", noVar("{1,2;3,4}[1,0]"));
    }
    @Test
    public void evaluateExp519Test(){
        assertEq("3", noVar("{1,2;3,4}[-1,-2]"));
    }
    @Test
    public void evaluateExp520Test(){
        assertEq("#12", noVar("{1,2;3,4}[1,2]"));
    }
    @Test
    public void evaluateExp521Test(){
        assertEq("#10", noVar("{1,2;3,4}[2,-2]"));
    }
    @Test
    public void evaluateExp522Test(){
        assertEq("0", noVar("{1,2;3,4}=2"));
    }
    @Test
    public void evaluateExp523Test(){
        assertEq("0", noVar("{1,2;3,4}={1,2;3,5}"));
    }
    @Test
    public void evaluateExp524Test(){
        assertEq("1", noVar("{1,2;3,4}={1,2;3,4}"));
    }
    @Test
    public void evaluateExp525Test(){
        assertEq("0", noVar("{1,2;3,4}[0]=2"));
    }
    @Test
    public void evaluateExp526Test(){
        assertEq("0", noVar("{1,2;3,4}[1]={1,2;3,5}[1]"));
    }
    @Test
    public void evaluateExp527Test(){
        assertEq("1", noVar("{1,2;3,4}[0]={1,2;3,4}[0]"));
    }
    @Test
    public void evaluateExp528Test(){
        assertEq("#3", noVar("{1;3,4}"));
    }
    @Test
    public void evaluateExp529Test(){
        assertEq("#7", noVar("{1,2,3;4,5}"));
    }
    @Test
    public void evaluateExp530Test(){
        assertEq("#7", noVar("{1,2,3;4}"));
    }
    @Test
    public void evaluateExp531Test(){
        assertEq("#1", noVar("{{1,2}}"));
    }
    @Test
    public void evaluateExp532Test(){
        assertEq("#0", noVar("{}"));
    }
    @Test
    public void evaluateExp533Test(){
        assertEq("#3", noVar("{1,{1}}"));
    }
    @Test
    public void evaluateExp534Test(){
        assertEq("#1", noVar("{;1}"));
    }
    @Test
    public void evaluateExp535Test(){
        assertEq("#5", noVar("(1/2,#)"));
    }
    @Test
    public void evaluateExp536Test(){
        assertEq("#4", noVar("(-2,#)"));
    }
    @Test
    public void evaluateExp537Test(){
        assertEq("{1,0;0,1}", noVar("(2,#)"));
    }
    @Test
    public void evaluateExp538Test(){
        assertEq("#13", noVar("{1,2,3;4,5,6}+{1,2;3,4;5,6}"));
    }
    @Test
    public void evaluateExp539Test(){
        assertEq("{2,4,6;8,10,12}", noVar("{1,2,3;4,5,6}+{1,2,3;4,5,6}"));
    }
    @Test
    public void evaluateExp540Test(){
        assertEq("#13", noVar("{1,2,3;4,5,6}-{1,2;3,4}"));
    }
    @Test
    public void evaluateExp541Test(){
        assertEq("#13", noVar("{1,2,3;4,5,6}-{1,2;3,4;5,6}"));
    }
    @Test
    public void evaluateExp542Test(){
        assertEq("{1,2,3;4,5,6}", noVar("{2,4,6;8,10,12}-{1,2,3;4,5,6}"));
    }
    @Test
    public void evaluateExp543Test(){
        assertEq("#13", noVar("{1,2,3;4,5,6}*{1,2,3;4,5,6}"));
    }
    @Test
    public void evaluateExp544Test(){
        assertEq("{22,28;49,64}", noVar("{1,2,3;4,5,6}*{1,2;3,4;5,6}"));
    }
    @Test
    public void evaluateExp545Test(){
        assertEq("{23/2717,4/247,-27/2717,89/2717;3/5434,11/247,469/5434,183/2717;-135/5434,-1/247,631/5434,-84/2717}", noVar("({1,1,-2;2,4,-2;-1,3,6;4,7,-5},-1,^)"));
    }
    @Test
    public void evaluateExp546Test(){
        assertEq("{23/2717,4/247,-27/2717,89/2717;3/5434,11/247,469/5434,183/2717;-135/5434,-1/247,631/5434,-84/2717}", noVar("(3,#):{1,1,-2;2,4,-2;-1,3,6;4,7,-5}"));
    }
    @Test
    public void evaluateExp547Test(){
        assertEq("#5", noVar("(4,#):{1,1,-2;2,4,-2;-1,3,6;4,7,-5}"));
    }
    @Test
    public void evaluateExp548Test(){
        assertEq("#5", noVar("(4,#):0"));
    }
    @Test
    public void evaluateExp549Test(){
        assertEq("{1,1,-2;2,4,-2;-1,3,6;4,7,-5}", noVar("({1,1,-2;2,4,-2;-1,3,6;4,7,-5},1,^)"));
    }
    @Test
    public void evaluateExp550Test(){
        assertEq("#33", noVar("({1,1,-2;2,4,-2;-1,3,6;4,7,-5},2,^)"));
    }
    @Test
    public void evaluateExp551Test(){
        assertEq("{7,10;15,22}", noVar("({1,2;3,4},2,^)"));
    }
    @Test
    public void evaluateExp552Test(){
        assertEq("{-2,1;3/2,-1/2}", noVar("({1,2;3,4},-1,^)"));
    }
    @Test
    public void evaluateExp553Test(){
        assertEq("{-1,-2;-3,-4}", noVar("-{1,2;3,4}"));
    }
    @Test
    public void evaluateExp554Test(){
        assertEq("{1,3;2,4}", noVar("+{1,2;3,4}"));
    }
    @Test
    public void evaluateExp555Test(){
        assertEq("#35", noVar("({1,1,-2;2,4,-2;-1,3,6;4,7,-5},1/2,^)"));
    }
    @Test
    public void evaluateExp556Test(){
        assertEq("#61", noVar("({1,1,-2;2,4,-2;-1,3,6;4,7,-5},{1,1,-2;2,4,-2;-1,3,6;4,7,-5},^)"));
    }
    @Test
    public void evaluateExp557Test(){
        assertEq("(1,-3,2,;)", noVar("({1,0;0,2},&)"));
    }
    @Test
    public void evaluateExp558Test(){
        assertEq("2", noVar("({1,0;0,2},|)"));
    }
    @Test
    public void evaluateExp559Test(){
        assertEq("2", noVar("({1,0;0,2},||)"));
    }
    @Test
    public void evaluateExp560Test(){
        assertEq("3", noVar("({1,0;0,2},/)"));
    }
    @Test
    public void evaluateExp561Test(){
        assertEq("{6,0;0,11}", noVar("((1,2,3,;),{1,0;0,2})"));
    }
    @Test
    public void evaluateExp562Test(){
        assertEq("#11", noVar("((1,2,3,;),{1,0})"));
    }
    @Test
    public void evaluateExp563Test(){
        assertEq("(3/2,5/2,.)", noVar("(((1,1,.),(3,2,.),(2,4,.),|||),#)"));
    }
    @Test
    public void evaluateExp564Test(){
        assertEq("#31", noVar("(((1,1,.),(2,2,.),(3,3,.),|||),#)"));
    }
    @Test
    public void evaluateExp565Test(){
        assertEq("1", noVar("(((1,1,.),(3,2,.),(2,4,.),|||),/)"));
    }
    @Test
    public void evaluateExp566Test(){
        assertEq("0", noVar("(((1,1,.),(2,2,.),(3,3,.),|||),/)"));
    }
    @Test
    public void evaluateExp567Test(){
        assertEq("#39", noVar("(((1,1,.),(2,2,.),(3,3,.),(4,4,.),|||),#)"));
    }
    @Test
    public void evaluateExp568Test(){
        assertEq("0", noVar("(((1,1,.),(2,2,.),(3,3,.),(4,4,.),|||),/)"));
    }
    @Test
    public void evaluateExp569Test(){
        assertEq("(2,7/3,.)", noVar("(((1,1,.),(3,2,.),(2,4,.),|||),*)"));
    }
    @Test
    public void evaluateExp570Test(){
        assertEq("((0,0,.),(0,1,.),(1,2,.),(2,2,.),(2,0,.),|||)", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),||)"));
    }
    @Test
    public void evaluateExp571Test(){
        assertEq("0", noVar("((1,1,.),(3,2,.),(2,4,.),|||)=((1,1,.),(3,2,.),(2,4,.),(2,5,.),|||)"));
    }
    @Test
    public void evaluateExp572Test(){
        assertEq("0", noVar("((1,1,.),(3,2,.),(2,4,.),|||)=((1,1,.),(3,2,.),(2,5,.),|||)"));
    }
    @Test
    public void evaluateExp573Test(){
        assertEq("0", noVar("((1,1,.),(3,2,.),(2,4,.),|||)=4"));
    }
    @Test
    public void evaluateExp574Test(){
        assertEq("1", noVar("((1,1,.),(3,2,.),(2,4,.),|||)=((1,1,.),(3,2,.),(2,4,.),|||)"));
    }
    @Test
    public void evaluateExp575Test(){
        assertEq("0", noVar("(1,1,.)=4"));
    }
    @Test
    public void evaluateExp576Test(){
        assertEq("0", noVar("(1,1,.)=(1,2,.)"));
    }
    @Test
    public void evaluateExp577Test(){
        assertEq("0", noVar("(1,1,.)=(2,1,.)"));
    }
    @Test
    public void evaluateExp578Test(){
        assertEq("1", noVar("(1,1,.)=(1,1,.)"));
    }
    @Test
    public void evaluateExp579Test(){
        assertEq("#10", noVar("(1,(1,||),.)"));
    }
    @Test
    public void evaluateExp580Test(){
        assertEq("#39", noVar("(((1,1,.),(2,2,.),(3,3,.),(4,4,.),|||),*)"));
    }
    @Test
    public void evaluateExp581Test(){
        assertEq("#8", noVar("((1,||),*)"));
    }
    @Test
    public void evaluateExp582Test(){
        assertEq("#9", noVar("((1,1,.),|||)"));
    }
    @Test
    public void evaluateExp583Test(){
        assertEq("#32", noVar("((1,1,.),(2,2,.),(2,2,.),(1,||),|||)"));
    }
    @Test
    public void evaluateExp584Test(){
        assertEq("1", noVar("(1,2,.)[0]"));
    }
    @Test
    public void evaluateExp585Test(){
        assertEq("2", noVar("(1,2,.)[1]"));
    }
    @Test
    public void evaluateExp586Test(){
        assertEq("2", noVar("(1,2,.)[]"));
    }
    @Test
    public void evaluateExp587Test(){
        assertEq("1", noVar("(1,2,.)[-2]"));
    }
    @Test
    public void evaluateExp588Test(){
        assertEq("2", noVar("(1,2,.)[-1]"));
    }
    @Test
    public void evaluateExp589Test(){
        assertEq("#8", noVar("(1,2,.)[2]"));
    }
    @Test
    public void evaluateExp590Test(){
        assertEq("#8", noVar("(1,2,.)[-3]"));
    }
    @Test
    public void evaluateExp591Test(){
        assertEq("1", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[0][1]"));
    }
    @Test
    public void evaluateExp592Test(){
        assertEq("1", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[0,1]"));
    }
    @Test
    public void evaluateExp593Test(){
        assertEq("1", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[-3][-1]"));
    }
    @Test
    public void evaluateExp594Test(){
        assertEq("1", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[-3,-1]"));
    }
    @Test
    public void evaluateExp595Test(){
        assertEq("#30", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[3][1]"));
    }
    @Test
    public void evaluateExp596Test(){
        assertEq("#30", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[3,1]"));
    }
    @Test
    public void evaluateExp597Test(){
        assertEq("#33", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[2][2]"));
    }
    @Test
    public void evaluateExp598Test(){
        assertEq("#32", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[2,2]"));
    }
    @Test
    public void evaluateExp599Test(){
        assertEq("1", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[-3][0]"));
    }
    @Test
    public void evaluateExp600Test(){
        assertEq("1", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[-3,0]"));
    }
    @Test
    public void evaluateExp601Test(){
        assertEq("3", noVar("((1,1,.),(3,2,.),(2,4,.),|||)[]"));
    }
    @Test
    public void evaluateExp602Test(){
        assertEq("#45", noVar("(((1,1,.),(3,2,.),|||),((1,1,.),(3,2,.),|||),/)"));
    }
    @Test
    public void evaluateExp603Test(){
        assertEq("(0,0,.)", noVar("(((1,1,.),(-1,-1,.),|||),((1,-1,.),(-1,1,.),|||),/)"));
    }
    @Test
    public void evaluateExp604Test(){
        assertEq("(-3,2,1,-)", noVar("((1,2,.),(5,8,.),|||)"));
    }
    @Test
    public void evaluateExp605Test(){
        assertEq("0", noVar("((1,2,.),(5,8,.),|||)=0"));
    }
    @Test
    public void evaluateExp606Test(){
        assertEq("0", noVar("(-3,2,1,-)=(-3,2,2,-)"));
    }
    @Test
    public void evaluateExp607Test(){
        assertEq("0", noVar("(-3,2,1,-)=(-3,1,1,-)"));
    }
    @Test
    public void evaluateExp608Test(){
        assertEq("0", noVar("(-3,2,1,-)=(-1,2,1,-)"));
    }
    @Test
    public void evaluateExp609Test(){
        assertEq("1", noVar("(-3,2,1,-)=(-3,2,1,-)"));
    }
    @Test
    public void evaluateExp610Test(){
        assertEq("#24", noVar("((1,1,.),(3,2,.),(1,||),|||)"));
    }
    @Test
    public void evaluateExp611Test(){
        assertEq("-3", noVar("((1,2,.),(5,8,.),|||)[0]"));
    }
    @Test
    public void evaluateExp612Test(){
        assertEq("2", noVar("((1,2,.),(5,8,.),|||)[1]"));
    }
    @Test
    public void evaluateExp613Test(){
        assertEq("1", noVar("((1,2,.),(5,8,.),|||)[2]"));
    }
    @Test
    public void evaluateExp614Test(){
        assertEq("-3", noVar("((1,2,.),(5,8,.),|||)[-3]"));
    }
    @Test
    public void evaluateExp615Test(){
        assertEq("2", noVar("((1,2,.),(5,8,.),|||)[-2]"));
    }
    @Test
    public void evaluateExp616Test(){
        assertEq("1", noVar("((1,2,.),(5,8,.),|||)[-1]"));
    }
    @Test
    public void evaluateExp617Test(){
        assertEq("#22", noVar("((1,2,.),(5,8,.),|||)[3]"));
    }
    @Test
    public void evaluateExp618Test(){
        assertEq("#22", noVar("((1,2,.),(5,8,.),|||)[-4]"));
    }
    @Test
    public void evaluateExp619Test(){
        assertEq("3", noVar("((1,2,.),(5,8,.),|||)[]"));
    }
    @Test
    public void evaluateExp620Test(){
        assertEq("#13", noVar("(-3,2,(1,||),-)"));
    }
    @Test
    public void evaluateExp621Test(){
        assertEq("0", noVar("(((1,1,.),(3,2,.),|||),((1,1,.),(3,2,.),|||),^)"));
    }
    @Test
    public void evaluateExp622Test(){
        assertEq("1", noVar("(((1,1,.),(-1,-1,.),|||),((1,-1,.),(-1,1,.),|||),^)"));
    }
    @Test
    public void evaluateExp623Test(){
        assertEq("1", noVar("(((1,1,.),(-1,-1,.),-),((1,-1,.),(-1,1,.),-),^)"));
    }
    @Test
    public void evaluateExp624Test(){
        assertEq("0", noVar("(((3,2,.),(5,4,.),-),((0,0,.),(4,2,.),-),^)"));
    }
    @Test
    public void evaluateExp625Test(){
        assertEq("1", noVar("(((1,1,.),(-1,-1,.),-),(0,0,.),^)"));
    }
    @Test
    public void evaluateExp626Test(){
        assertEq("0", noVar("(((1,1,.),(-1,-1,.),-),(-1,1,.),^)"));
    }
    @Test
    public void evaluateExp627Test(){
        assertEq("1", noVar("(((1,1,.),(-1,-1,.),-),((1,-1,.),(-1,1,.),-),^^)"));
    }
    @Test
    public void evaluateExp628Test(){
        assertEq("0", noVar("(((3,2,.),(5,4,.),-),((0,0,.),(4,2,.),-),^^)"));
    }
    @Test
    public void evaluateExp629Test(){
        assertEq("1", noVar("(((1,1,.),(-1,-1,.),-),((1,-1,.),(-1,1,.),-),^^^)"));
    }
    @Test
    public void evaluateExp630Test(){
        assertEq("0", noVar("(((3,2,.),(5,4,.),-),((0,0,.),(4,2,.),-),^^^)"));
    }
    @Test
    public void evaluateExp631Test(){
        assertEq("#25", noVar("(((1,1,.),(-1,-1,.),-),1,^)"));
    }
    @Test
    public void evaluateExp632Test(){
        assertEq("#11", noVar("((1,1,.),1,-)"));
    }
    @Test
    public void evaluateExp633Test(){
        assertEq("#23", noVar("(((3,2,.),(5,4,.),-),1,^^)"));
    }
    @Test
    public void evaluateExp634Test(){
        assertEq("#25", noVar("(((1,1,.),(-1,-1,.),-),1,^^^)"));
    }
    @Test
    public void evaluateExp635Test(){
        assertEq("0", noVar("((3,2,.),(5,4,.),-)=1"));
    }
    @Test
    public void evaluateExp636Test(){
        assertEq("0", noVar("((3,2,.),(5,4,.),-)=((0,0,.),(4,2,.),-)"));
    }
    @Test
    public void evaluateExp637Test(){
        assertEq("0", noVar("((3,2,.),(5,4,.),-)=((3,2,.),(4,2,.),-)"));
    }
    @Test
    public void evaluateExp638Test(){
        assertEq("1", noVar("((3,2,.),(5,4,.),-)=((3,2,.),(5,4,.),-)"));
    }
    @Test
    public void evaluateExp639Test(){
        assertEq("((3,2,.),(5,4,.),-)", noVar("((3,2,.),(5,4,.),-)"));
    }
    @Test
    public void evaluateExp640Test(){
        assertEq("2", noVar("((3,2,.),(5,4,.),-)[]"));
    }
    @Test
    public void evaluateExp641Test(){
        assertEq("(3,2,.)", noVar("((3,2,.),(5,4,.),-)[0]"));
    }
    @Test
    public void evaluateExp642Test(){
        assertEq("(5,4,.)", noVar("((3,2,.),(5,4,.),-)[1]"));
    }
    @Test
    public void evaluateExp643Test(){
        assertEq("(3,2,.)", noVar("((3,2,.),(5,4,.),-)[-2]"));
    }
    @Test
    public void evaluateExp644Test(){
        assertEq("(5,4,.)", noVar("((3,2,.),(5,4,.),-)[-1]"));
    }
    @Test
    public void evaluateExp645Test(){
        assertEq("#20", noVar("((3,2,.),(5,4,.),-)[2]"));
    }
    @Test
    public void evaluateExp646Test(){
        assertEq("#20", noVar("((3,2,.),(5,4,.),-)[-3]"));
    }
    @Test
    public void evaluateExp647Test(){
        assertEq("3", noVar("((3,2,.),(5,4,.),-)[0,0]"));
    }
    @Test
    public void evaluateExp648Test(){
        assertEq("4", noVar("((3,2,.),(5,4,.),-)[1,1]"));
    }
    @Test
    public void evaluateExp649Test(){
        assertEq("3", noVar("((3,2,.),(5,4,.),-)[-2,-2]"));
    }
    @Test
    public void evaluateExp650Test(){
        assertEq("4", noVar("((3,2,.),(5,4,.),-)[-1,-1]"));
    }
    @Test
    public void evaluateExp651Test(){
        assertEq("#22", noVar("((3,2,.),(5,4,.),-)[1,2]"));
    }
    @Test
    public void evaluateExp652Test(){
        assertEq("#23", noVar("((3,2,.),(5,4,.),-)[-2,-3]"));
    }
    @Test
    public void evaluateExp653Test(){
        assertEq("#20", noVar("((3,2,.),(5,4,.),-)[2,2]"));
    }
    @Test
    public void evaluateExp654Test(){
        assertEq("#20", noVar("((3,2,.),(5,4,.),-)[-3,-3]"));
    }
    @Test
    public void evaluateExp655Test(){
        assertEq("1", noVar("(((3,2,.),(1,1,.),|||),(2,3/2,.),^)"));
    }
    @Test
    public void evaluateExp656Test(){
        assertEq("0", noVar("(((3,2,.),(1,1,.),|||),(2,2,.),^)"));
    }
    @Test
    public void evaluateExp657Test(){
        assertEq("#25", noVar("(((3,2,.),(1,1,.),|||),1,^)"));
    }
    @Test
    public void evaluateExp658Test(){
        assertEq("#39", noVar("(((3,2,.),(1,1,.),(1,1,.),(1,1,.),|||),|)"));
    }
    @Test
    public void evaluateExp659Test(){
        assertEq("#31", noVar("(((1,1,.),(1,2,.),(1,3,.),|||),|)"));
    }
    @Test
    public void evaluateExp660Test(){
        assertEq("(1/9,1/3,1,-)", noVar("(((1,1,.),(3,2,.),(2,4,.),|||),|)"));
    }
    @Test
    public void evaluateExp661Test(){
        assertEq("1", noVar("((1,1,.),(1,2,.),.)"));
    }
    @Test
    public void evaluateExp662Test(){
        assertEq("#16", noVar("((1,1,.),(1,||),.)"));
    }
    @Test
    public void evaluateExp663Test(){
        assertEq("((0,0,.),(0,1,.),(1,2,.),(2,2,.),(2,0,.),|||)", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),')"));
    }
    @Test
    public void evaluateExp664Test(){
        assertEq("(((0,1,.),(1,1,.),(1,2,.),|||))", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)"));
    }
    @Test
    public void evaluateExp665Test(){
        assertEq("((0,1,.),(1,1,.),(1,2,.),|||)", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0]"));
    }
    @Test
    public void evaluateExp666Test(){
        assertEq("((0,1,.),(1,1,.),(1,2,.),|||)", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[-1]"));
    }
    @Test
    public void evaluateExp667Test(){
        assertEq("#58", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[1]"));
    }
    @Test
    public void evaluateExp668Test(){
        assertEq("#58", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[-2]"));
    }
    @Test
    public void evaluateExp669Test(){
        assertEq("(0,1,.)", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0,0]"));
    }
    @Test
    public void evaluateExp670Test(){
        assertEq("(0,1,.)", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[-1,-3]"));
    }
    @Test
    public void evaluateExp671Test(){
        assertEq("#60", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0,3]"));
    }
    @Test
    public void evaluateExp672Test(){
        assertEq("#60", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0,-4]"));
    }
    @Test
    public void evaluateExp673Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0,0,0]"));
    }
    @Test
    public void evaluateExp674Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[-1,-3,-2]"));
    }
    @Test
    public void evaluateExp675Test(){
        assertEq("#62", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0,0,2]"));
    }
    @Test
    public void evaluateExp676Test(){
        assertEq("#62", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0,0,-3]"));
    }
    @Test
    public void evaluateExp677Test(){
        assertEq("1", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[]"));
    }
    @Test
    public void evaluateExp678Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)=0"));
    }
    @Test
    public void evaluateExp679Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)=(((0,0,.),(0,1,.),(1,1,.),|||),&)"));
    }
    @Test
    public void evaluateExp680Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)=(((0,0,.),(0,1,.),(1,1,.),(1,3,.),(2,3,.),(2,0,.),|||),&)"));
    }
    @Test
    public void evaluateExp681Test(){
        assertEq("1", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)=(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)"));
    }
    @Test
    public void evaluateExp682Test(){
        assertEq("#60", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[0,3,1]"));
    }
    @Test
    public void evaluateExp683Test(){
        assertEq("#58", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[1,3,1]"));
    }
    @Test
    public void evaluateExp684Test(){
        assertEq("#58", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[1,3]"));
    }
    @Test
    public void evaluateExp685Test(){
        assertEq("#62", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[1,3,1/2]"));
    }
    @Test
    public void evaluateExp686Test(){
        assertEq("#63", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),&)[1,3,1,1]"));
    }
    @Test
    public void evaluateExp687Test(){
        assertEq("1", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),(3/2,3/2,.),^)"));
    }
    @Test
    public void evaluateExp688Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),(0,2,.),^)"));
    }
    @Test
    public void evaluateExp689Test(){
        assertEq("1", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),(3/2,3/2,.),^^)"));
    }
    @Test
    public void evaluateExp690Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),(0,2,.),^^)"));
    }
    @Test
    public void evaluateExp691Test(){
        assertEq("#57", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),1,^)"));
    }
    @Test
    public void evaluateExp692Test(){
        assertEq("#57", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,2,.),(2,2,.),(2,0,.),|||),1,^^)"));
    }
    @Test
    public void evaluateExp693Test(){
        assertEq("(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)"));
    }
    @Test
    public void evaluateExp694Test(){
        assertEq("(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[0]"));
    }
    @Test
    public void evaluateExp695Test(){
        assertEq("#37", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[5]"));
    }
    @Test
    public void evaluateExp696Test(){
        assertEq("#37", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[-6]"));
    }
    @Test
    public void evaluateExp697Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)=0"));
    }
    @Test
    public void evaluateExp698Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)=((1,1,.),(2,4,.),(0,3,.),(3,2,.),%%)"));
    }
    @Test
    public void evaluateExp699Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)"));
    }
    @Test
    public void evaluateExp700Test(){
        assertEq("#8", noVar("((1,||),%%)"));
    }
    @Test
    public void evaluateExp701Test(){
        assertEq("()", noVar("(%%)"));
    }
    @Test
    public void evaluateExp702Test(){
        assertEq("2", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[0][]"));
    }
    @Test
    public void evaluateExp703Test(){
        assertEq("((0,0,.),(1,5,.),(2,4,.),(3,2,.),|||)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[1]"));
    }
    @Test
    public void evaluateExp704Test(){
        assertEq("((0,0,.):((1,5,.),(2,4,.),(3,2,.)),(2,4,.):((3,2,.),(0,0,.),(1,5,.)),(1,5,.):((2,4,.),(0,0,.)),(3,2,.):((0,0,.),(2,4,.)))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]"));
    }
    @Test
    public void evaluateExp705Test(){
        assertEq("((0,0,.):(((-1/3,8/3,.),(3/4,17/8,.),-),((3/4,17/8,.),(-1/3,8/3,.),-)),(2,4,.):(((3/4,17/8,.),(-1/3,8/3,.),-),((-1/3,8/3,.),(3/4,17/8,.),-)),(1,5,.):(((-1/3,8/3,.),(-1/3,8/3,.),-),((-1/3,8/3,.),(-1/3,8/3,.),-)),(3,2,.):(((3/4,17/8,.),(3/4,17/8,.),-),((3/4,17/8,.),(3/4,17/8,.),-)))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]"));
    }
    @Test
    public void evaluateExp706Test(){
        assertEq("((0,0,.):(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||)),(2,4,.):(((2,4,.),(0,0,.),(3,2,.),|||),((0,0,.),(2,4,.),(1,5,.),|||)),(1,5,.):(((0,0,.),(2,4,.),(1,5,.),|||),((0,0,.),(2,4,.),(1,5,.),|||)),(3,2,.):(((2,4,.),(0,0,.),(3,2,.),|||),((2,4,.),(0,0,.),(3,2,.),|||)))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]"));
    }
    @Test
    public void evaluateExp707Test(){
        assertEq("((0,0,.),((1,5,.),(2,4,.),(3,2,.)))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]"));
    }
    @Test
    public void evaluateExp708Test(){
        assertEq("((0,0,.),(((-1/3,8/3,.),(3/4,17/8,.),-),((3/4,17/8,.),(-1/3,8/3,.),-)))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]"));
    }
    @Test
    public void evaluateExp709Test(){
        assertEq("((0,0,.),(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||)))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]"));
    }
    @Test
    public void evaluateExp710Test(){
        assertEq("(0,0,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][0]"));
    }
    @Test
    public void evaluateExp711Test(){
        assertEq("(0,0,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][0]"));
    }
    @Test
    public void evaluateExp712Test(){
        assertEq("(0,0,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][0]"));
    }
    @Test
    public void evaluateExp713Test(){
        assertEq("((1,5,.),(2,4,.),(3,2,.))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1]"));
    }
    @Test
    public void evaluateExp714Test(){
        assertEq("(((-1/3,8/3,.),(3/4,17/8,.),-),((3/4,17/8,.),(-1/3,8/3,.),-))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1]"));
    }
    @Test
    public void evaluateExp715Test(){
        assertEq("(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][1]"));
    }
    @Test
    public void evaluateExp716Test(){
        assertEq("(0,0,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][-4][-2]"));
    }
    @Test
    public void evaluateExp717Test(){
        assertEq("(0,0,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][-4][-2]"));
    }
    @Test
    public void evaluateExp718Test(){
        assertEq("(0,0,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][-4][-2]"));
    }
    @Test
    public void evaluateExp719Test(){
        assertEq("((1,5,.),(2,4,.),(3,2,.))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][-4][-1]"));
    }
    @Test
    public void evaluateExp720Test(){
        assertEq("(((-1/3,8/3,.),(3/4,17/8,.),-),((3/4,17/8,.),(-1/3,8/3,.),-))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][-4][-1]"));
    }
    @Test
    public void evaluateExp721Test(){
        assertEq("(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][-4][-1]"));
    }
    @Test
    public void evaluateExp722Test(){
        assertEq("(1,5,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1][0]"));
    }
    @Test
    public void evaluateExp723Test(){
        assertEq("((-1/3,8/3,.),(3/4,17/8,.),-)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1][0]"));
    }
    @Test
    public void evaluateExp724Test(){
        assertEq("((0,0,.),(2,4,.),(1,5,.),|||)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][1][0]"));
    }
    @Test
    public void evaluateExp725Test(){
        assertEq("(1,5,.)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1][-3]"));
    }
    @Test
    public void evaluateExp726Test(){
        assertEq("((-1/3,8/3,.),(3/4,17/8,.),-)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1][-2]"));
    }
    @Test
    public void evaluateExp727Test(){
        assertEq("((0,0,.),(2,4,.),(1,5,.),|||)", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][1][-2]"));
    }
    @Test
    public void evaluateExp728Test(){
        assertEq("5", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[]"));
    }
    @Test
    public void evaluateExp729Test(){
        assertEq("3", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1][]"));
    }
    @Test
    public void evaluateExp730Test(){
        assertEq("2", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1][]"));
    }
    @Test
    public void evaluateExp731Test(){
        assertEq("2", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][1][]"));
    }
    @Test
    public void evaluateExp732Test(){
        assertEq("2", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][]"));
    }
    @Test
    public void evaluateExp733Test(){
        assertEq("4", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][]"));
    }
    @Test
    public void evaluateExp734Test(){
        assertEq("4", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][]"));
    }
    @Test
    public void evaluateExp735Test(){
        assertEq("4", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][]"));
    }
    @Test
    public void evaluateExp736Test(){
        assertEq("#46", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1][3]"));
    }
    @Test
    public void evaluateExp737Test(){
        assertEq("#46", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1][2]"));
    }
    @Test
    public void evaluateExp738Test(){
        assertEq("#46", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][1][2]"));
    }
    @Test
    public void evaluateExp739Test(){
        assertEq("#43", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][2]"));
    }
    @Test
    public void evaluateExp740Test(){
        assertEq("#43", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][2]"));
    }
    @Test
    public void evaluateExp741Test(){
        assertEq("#43", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0][2]"));
    }
    @Test
    public void evaluateExp742Test(){
        assertEq("#40", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][4]"));
    }
    @Test
    public void evaluateExp743Test(){
        assertEq("#40", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][4]"));
    }
    @Test
    public void evaluateExp744Test(){
        assertEq("#40", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][4]"));
    }
    @Test
    public void evaluateExp745Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1]=0"));
    }
    @Test
    public void evaluateExp746Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][1][1]"));
    }
    @Test
    public void evaluateExp747Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][2][1]"));
    }
    @Test
    public void evaluateExp748Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1]"));
    }
    @Test
    public void evaluateExp749Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[3][0][1]"));
    }
    @Test
    public void evaluateExp750Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1]=0"));
    }
    @Test
    public void evaluateExp751Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][1][1]"));
    }
    @Test
    public void evaluateExp752Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][2][1]"));
    }
    @Test
    public void evaluateExp753Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1]"));
    }
    @Test
    public void evaluateExp754Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0][1]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[2][0][1]"));
    }
    @Test
    public void evaluateExp755Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]=0"));
    }
    @Test
    public void evaluateExp756Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][1]"));
    }
    @Test
    public void evaluateExp757Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][2]"));
    }
    @Test
    public void evaluateExp758Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]"));
    }
    @Test
    public void evaluateExp759Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[2][0]"));
    }
    @Test
    public void evaluateExp760Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]=0"));
    }
    @Test
    public void evaluateExp761Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][1]"));
    }
    @Test
    public void evaluateExp762Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][2]"));
    }
    @Test
    public void evaluateExp763Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]"));
    }
    @Test
    public void evaluateExp764Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[3][0]"));
    }
    @Test
    public void evaluateExp765Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]=0"));
    }
    @Test
    public void evaluateExp766Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][1]"));
    }
    @Test
    public void evaluateExp767Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][2]"));
    }
    @Test
    public void evaluateExp768Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]"));
    }
    @Test
    public void evaluateExp769Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[4][0]"));
    }
    @Test
    public void evaluateExp770Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]"));
    }
    @Test
    public void evaluateExp771Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0]"));
    }
    @Test
    public void evaluateExp772Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0]"));
    }
    @Test
    public void evaluateExp773Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[4]"));
    }
    @Test
    public void evaluateExp774Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]"));
    }
    @Test
    public void evaluateExp775Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]=((0,0,.),(2,4,.),(1,5,.),(3,3,.),%%)[4]"));
    }
    @Test
    public void evaluateExp776Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]=((1,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]"));
    }
    @Test
    public void evaluateExp777Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4]=0"));
    }
    @Test
    public void evaluateExp778Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[3]"));
    }
    @Test
    public void evaluateExp779Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]"));
    }
    @Test
    public void evaluateExp780Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]=((0,0,.),(2,4,.),(1,5,.),(3,3,.),%%)[3]"));
    }
    @Test
    public void evaluateExp781Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]=((1,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]"));
    }
    @Test
    public void evaluateExp782Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3]=0"));
    }
    @Test
    public void evaluateExp783Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[2]"));
    }
    @Test
    public void evaluateExp784Test(){
        assertEq("1", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]"));
    }
    @Test
    public void evaluateExp785Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]=((0,0,.),(2,4,.),(1,5,.),(3,3,.),%%)[2]"));
    }
    @Test
    public void evaluateExp786Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]=((1,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]"));
    }
    @Test
    public void evaluateExp787Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2]=0"));
    }
    @Test
    public void evaluateExp788Test(){
        assertEq("((1,5,.),(2,4,.),(3,2,.))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,0,.)]"));
    }
    @Test
    public void evaluateExp789Test(){
        assertEq("(((-1/3,8/3,.),(3/4,17/8,.),-),((3/4,17/8,.),(-1/3,8/3,.),-))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][(0,0,.)]"));
    }
    @Test
    public void evaluateExp790Test(){
        assertEq("(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,0,.)]"));
    }
    @Test
    public void evaluateExp791Test(){
        assertEq("#40", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[0][(0,0,.)]"));
    }
    @Test
    public void evaluateExp792Test(){
        assertEq("#7", noVar("(0,0,0,-)"));
    }
    @Test
    public void evaluateExp793Test(){
        assertEq("#17", noVar("((2,4,.),(2,4,.),|||)"));
    }
    @Test
    public void evaluateExp794Test(){
        assertEq("{-1,1/2;1,1}", noVar("({1,1;2,2},-)"));
    }
    @Test
    public void evaluateExp795Test(){
        assertEq("{-1,0,-2;-1,1,0;1,0,1}", noVar("({3,0,4;2,1,4;-2,0,-3},-)"));
    }
    @Test
    public void evaluateExp796Test(){
        assertEq("-(((0,0,.),(2,4,.),(1,5,.),|||),((2,4,.),(0,0,.),(3,2,.),|||))", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%%)"));
    }
    @Test
    public void evaluateExp797Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%%),-)"));
    }
    @Test
    public void evaluateExp798Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%),-)"));
    }
    @Test
    public void evaluateExp799Test(){
        assertEq("0", noVar("((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)=((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%%)"));
    }
    @Test
    public void evaluateExp800Test(){
        assertEq("(-1,-2,-)", noVar("-(1,2,-)"));
    }
    @Test
    public void evaluateExp801Test(){
        assertEq("(1,-2,-)", noVar("((1,2,-),*)"));
    }
    @Test
    public void evaluateExp802Test(){
        assertEq("(1/5,-2/5,-)", noVar("((1,2,-),-1,^^)"));
    }
    @Test
    public void evaluateExp803Test(){
        assertEq("(-3,4,-)", noVar("((1,2,-),2,^^)"));
    }
    @Test
    public void evaluateExp804Test(){
        assertEq("#13", noVar("((1,2,-),1/2,^^)"));
    }
    @Test
    public void evaluateExp805Test(){
        assertEq("1", noVar("((1,2,-),0,^^)"));
    }
    @Test
    public void evaluateExp806Test(){
        assertEq("1", noVar("((1,2,-),0,^^)=1"));
    }
    @Test
    public void evaluateExp807Test(){
        assertEq("1", noVar("1=((1,2,-),0,^^)"));
    }
    @Test
    public void evaluateExp808Test(){
        assertEq("9", noVar("((3,0,-),2,^^)"));
    }
    @Test
    public void evaluateExp809Test(){
        assertEq("(2,4,-)", noVar("(1,2,-)+(1,2,-)"));
    }
    @Test
    public void evaluateExp810Test(){
        assertEq("(-3,4,-)", noVar("(1,2,-)*(1,2,-)"));
    }
    @Test
    public void evaluateExp811Test(){
        assertEq("(1,2,-)", noVar("(2,4,-)-(1,2,-)"));
    }
    @Test
    public void evaluateExp812Test(){
        assertEq("(1/5,-2/5,-)", noVar("(1,0,-):(1,2,-)"));
    }
    @Test
    public void evaluateExp813Test(){
        assertEq("#7", noVar("(1,0,-):(0,0,-)"));
    }
    @Test
    public void evaluateExp814Test(){
        assertEq("#7", noVar("(1,0,-):(1,||)"));
    }
    @Test
    public void evaluateExp815Test(){
        assertEq("(1,2,-)", noVar("+(1,2,-)"));
    }
    @Test
    public void evaluateExp816Test(){
        assertEq("(1,-2/3,-)", noVar("((1,1,1,;):(1,0,1,;),(0,2,-))"));
    }
    @Test
    public void evaluateExp817Test(){
        assertEq("#21", noVar("((1,1,1,;):(1,0,4,;),(0,2,-))"));
    }
    @Test
    public void evaluateExp818Test(){
        assertEq("0", noVar("((0,;),(0,2,-))"));
    }
    @Test
    public void evaluateExp819Test(){
        assertEq("(0,-1,-)", noVar("(1,0,-):(0,1,-)"));
    }
    @Test
    public void evaluateExp820Test(){
        assertEq("#16", noVar("((1,2,-),(1,||),^^)"));
    }
    @Test
    public void evaluateExp821Test(){
        assertEq("(1,2)", noVar("((1,2,-),//)"));
    }
    @Test
    public void evaluateExp822Test(){
        assertEq("2", noVar("((1,2,-),//)[]"));
    }
    @Test
    public void evaluateExp823Test(){
        assertEq("1", noVar("((1,2,-),//)[0]"));
    }
    @Test
    public void evaluateExp824Test(){
        assertEq("2", noVar("((1,2,-),//)[1]"));
    }
    @Test
    public void evaluateExp825Test(){
        assertEq("#8", noVar("((1,||),//)"));
    }
    @Test
    public void evaluateExp826Test(){
        assertEq("0", noVar("((1<>2,2<>1),(1<>2),=)"));
    }
    @Test
    public void evaluateExp827Test(){
        assertEq("0", noVar("((1<>2),(1<>2,2<>1),=)"));
    }
    @Test
    public void evaluateExp828Test(){
        assertEq("1", noVar("((1<>2),(1<>2),=)"));
    }
    @Test
    public void evaluateExp829Test(){
        assertEq("0", noVar("((1<>2,2<>1),(2<>3,1<>2),=)"));
    }
    @Test
    public void evaluateExp830Test(){
        assertEq("1", noVar("((1<>2,2<>1),(2<>1,1<>2),=)"));
    }
    @Test
    public void evaluateExp831Test(){
        assertEq("1", noVar("(((0,0,.),(1,1,.),(1,0,.),|||),((1,1,.),(1,0,.),(0,0,.),|||),=)"));
    }
    @Test
    public void evaluateExp832Test(){
        assertEq("1", noVar("(((0,0,.),(1,1,.),(1,0,.),|||),((0,0,.),(1,0,.),(1,1,.),|||),=)"));
    }
    @Test
    public void evaluateExp833Test(){
        assertEq("1", noVar("(((0,1,.),(1,1,.),(1,0,.),|||),((1,1,.),(1,0,.),(0,1,.),|||),=)"));
    }
    @Test
    public void evaluateExp834Test(){
        assertEq("1", noVar("(((0,1,.),(1,1,.),(1,0,.),|||),((0,1,.),(1,0,.),(1,1,.),|||),=)"));
    }
    @Test
    public void evaluateExp835Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,0,.),|||),((0,1,.),(1,0,.),(1,1,.),|||),=)"));
    }
    @Test
    public void evaluateExp836Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%%)[3][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp837Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%%)[3][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp838Test(){
        assertEq("0", noVar("({1,0}[0],0,=)"));
    }
    @Test
    public void evaluateExp839Test(){
        assertEq("0", noVar("({1,0}[0],{0,1}[0],=)"));
    }
    @Test
    public void evaluateExp840Test(){
        assertEq("1", noVar("({1,0}[0],{1,0}[0],=)"));
    }
    @Test
    public void evaluateExp841Test(){
        assertEq("0", noVar("(((0,0,.),(0,1,.),(1,1,.),(1,0,.),|||),0,=)"));
    }
    @Test
    public void evaluateExp842Test(){
        assertEq("0", noVar("((1,2,1,;),(1,2,/%),=)"));
    }
    @Test
    public void evaluateExp843Test(){
        assertEq("0", noVar("((1,||),(1,&)[0],=)"));
    }
    @Test
    public void evaluateExp844Test(){
        assertEq("0", noVar("((1<>1,<>-|),1,=)"));
    }
    @Test
    public void evaluateExp845Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[2][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp846Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[2][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp847Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp848Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,1,.)],=)"));
    }
    @Test
    public void evaluateExp849Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,1,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp850Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%%)[3],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3],=)"));
    }
    @Test
    public void evaluateExp851Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[4][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp852Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[4][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp853Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp854Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,0,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,1,.)],=)"));
    }
    @Test
    public void evaluateExp855Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,1,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,0,.)],=)"));
    }
    @Test
    public void evaluateExp856Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(1,5,.)],((0,0,.),(2,4,.),(1,5,.),(3,2,.),(4,1,.),%%)[4][(1,5,.)],=)"));
    }
    @Test
    public void evaluateExp857Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%),((0,0,.),(2,4,.),(3,2,.),(1,5,.),%%),=)"));
    }
    @Test
    public void evaluateExp858Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][(0,1,.)],0,=)"));
    }
    @Test
    public void evaluateExp859Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][(0,1,.)],0,=)"));
    }
    @Test
    public void evaluateExp860Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][(0,1,.)],0,=)"));
    }
    @Test
    public void evaluateExp861Test(){
        assertEq("0", noVar("(0,((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][(0,1,.)],=)"));
    }
    @Test
    public void evaluateExp862Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%),((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%%),=)"));
    }
    @Test
    public void evaluateExp863Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%),0,=)"));
    }
    @Test
    public void evaluateExp864Test(){
        assertEq("0", noVar("((1<>1),0,=)"));
    }
    @Test
    public void evaluateExp865Test(){
        assertEq("#3", noVar("(0,=)"));
    }
    @Test
    public void evaluateExp866Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2],((0,0,.),(2,4,.),(3,2,.),(1,5,.),%%)[2],=)"));
    }
    @Test
    public void evaluateExp867Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3],((0,0,.),(2,4,.),(3,2,.),(1,5,.),%%)[3],=)"));
    }
    @Test
    public void evaluateExp868Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4],((0,0,.),(2,4,.),(3,2,.),(1,5,.),%%)[4],=)"));
    }
    @Test
    public void evaluateExp869Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4],((0,0,.),(2,4,.),(1,5,.),(3,3,.),%%)[4],=)"));
    }
    @Test
    public void evaluateExp870Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2],((0,0,.),(2,4,.),(1,5,.),(3,3,.),%%)[2],=)"));
    }
    @Test
    public void evaluateExp871Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2],0,=)"));
    }
    @Test
    public void evaluateExp872Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3],0,=)"));
    }
    @Test
    public void evaluateExp873Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4],0,=)"));
    }
    @Test
    public void evaluateExp874Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0],((0,0,.),(2,4,.),(3,2,.),(1,5,.),%%)[2][1],=)"));
    }
    @Test
    public void evaluateExp875Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0],((0,0,.),(2,4,.),(3,2,.),(1,5,.),%%)[3][1],=)"));
    }
    @Test
    public void evaluateExp876Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0],((0,0,.),(2,4,.),(3,2,.),(1,5,.),%%)[4][1],=)"));
    }
    @Test
    public void evaluateExp877Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0],=)"));
    }
    @Test
    public void evaluateExp878Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0],=)"));
    }
    @Test
    public void evaluateExp879Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0],=)"));
    }
    @Test
    public void evaluateExp880Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][0],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[2][3],=)"));
    }
    @Test
    public void evaluateExp881Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][0],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[3][3],=)"));
    }
    @Test
    public void evaluateExp882Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][0],((0,0,.),(2,4,.),(1,5,.),(3,2,.),%%)[4][3],=)"));
    }
    @Test
    public void evaluateExp883Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),-),((2,4,.),(0,0,.),-),=)"));
    }
    @Test
    public void evaluateExp884Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),-),((2,4,.),(1,0,.),-),=)"));
    }
    @Test
    public void evaluateExp885Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),-),0,=)"));
    }
    @Test
    public void evaluateExp886Test(){
        assertEq("1", noVar("(((0,0,.),(2,4,.),|||),((4,8,.),(0,0,.),|||),=)"));
    }
    @Test
    public void evaluateExp887Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),|||),((2,4,.),(1,0,.),|||),=)"));
    }
    @Test
    public void evaluateExp888Test(){
        assertEq("0", noVar("(((0,0,.),(2,4,.),|||),0,=)"));
    }
    @Test
    public void evaluateExp889Test(){
        assertEq("1", noVar("((1,2,1,-),(2,4,2,-),=)"));
    }
    @Test
    public void evaluateExp890Test(){
        assertEq("0", noVar("(((1,2,;),||),((2,1,;),||),=)"));
    }
    @Test
    public void evaluateExp891Test(){
        assertEq("0", noVar("(((1,2,;),||),0,=)"));
    }
    @Test
    public void evaluateExp892Test(){
        assertEq("0", noVar("((1,||),(1,1,.),=)"));
    }
    @Test
    public void evaluateExp893Test(){
        assertEq("0", noVar("(1=>2,1<>1,=)"));
    }
    @Test
    public void evaluateExp894Test(){
        assertEq("0", noVar("(((1,2,-),//),(9,&)[0],=)"));
    }
    @Test
    public void evaluateExp895Test(){
        assertEq("0", noVar("((1,2,;)<>0,((2,1,;),&)[0],=)"));
    }
    @Test
    public void evaluateExp896Test(){
        assertEq("#1", noVar(" !0"));
    }
    @Test
    public void evaluateExp897Test(){
        assertEq("#1", noVar(" -(&)"));
    }
    @Test
    public void evaluateExp898Test(){
        assertEq("#4", noVar("1+( !0)"));
    }
    @Test
    public void evaluateExp899Test(){
        assertEq("#4", noVar("1+( -(&))"));
    }
    @Test
    public void evaluateExp900Test(){
        assertEq("#1", noVar(" _"));
    }
    @Test
    public void evaluateExp901Test(){
        assertEq("#1", noVar(" 1/0"));
    }
    @Test
    public void evaluateExp902Test(){
        assertEq("#3", noVar("1+ _"));
    }
    @Test
    public void evaluateExp903Test(){
        assertEq("#3", noVar("1+ 1/0"));
    }
    @Test
    public void evaluateExp904Test(){
        assertEq("#5", noVar("((&) !)"));
    }
    @Test
    public void evaluateExp905Test(){
        assertEq("#8", noVar("1+ ((&) !)"));
    }
    @Test
    public void evaluateExp906Test(){
        assertEq("#1", noVar(" 0()"));
    }
    @Test
    public void evaluateExp907Test(){
        assertEq("#3", noVar("1+ 0()"));
    }
    @Test
    public void evaluateExp908Test(){
        assertEq("#10", noVar("((2,||),2,<=)"));
    }
    @Test
    public void evaluateExp909Test(){
        assertEq("#13", noVar("(-3,(1,||),2,-)"));
    }
    @Test
    public void evaluateExp910Test(){
        assertEq("#13", noVar("((1,||),-3,2,-)"));
    }
    @Test
    public void evaluateExp911Test(){
        assertEq("#9", noVar("(1,(&),0,<=,<=)"));
    }
    @Test
    public void evaluateExp912Test(){
        assertEq("#9", noVar("((&),1,0,<=,<=)"));
    }
    @Test
    public void evaluateExp913Test(){
        assertEq("#9", noVar("(1,(&),0,<=,<)"));
    }
    @Test
    public void evaluateExp914Test(){
        assertEq("#9", noVar("((&),1,0,<=,<)"));
    }
    @Test
    public void evaluateExp915Test(){
        assertEq("#9", noVar("(1,(&),0,<,<=)"));
    }
    @Test
    public void evaluateExp916Test(){
        assertEq("#9", noVar("((&),1,0,<,<=)"));
    }
    @Test
    public void evaluateExp917Test(){
        assertEq("#9", noVar("(1,(&),0,<,<)"));
    }
    @Test
    public void evaluateExp918Test(){
        assertEq("#9", noVar("((&),1,0,<,<)"));
    }
    @Test
    public void evaluateExp919Test(){
        assertEq("#7", noVar("((&),1,==<)"));
    }
    @Test
    public void evaluateExp920Test(){
        assertEq("#7", noVar("((&),1,==>)"));
    }
    @Test
    public void evaluateExp921Test(){
        assertEq("#7", noVar("((&),1,>==)"));
    }
    @Test
    public void evaluateExp922Test(){
        assertEq("#7", noVar("((&),1,<==)"));
    }
    @Test
    public void evaluateExp923Test(){
        assertEq("#12", noVar("((1<>2,;),0,/)"));
    }
    @Test
    public void evaluateExp924Test(){
        assertEq("#12", noVar("((1<>2,;),0,%)"));
    }
    @Test
    public void evaluateExp925Test(){
        assertEq("#12", noVar("((1<>2,;),0,/%)"));
    }
    @Test
    public void evaluateExp926Test(){
        assertEq("#26", noVar("((1<>2,;):(1<>2,1<>0,;),0,%)"));
    }
    @Test
    public void evaluateExp927Test(){
        assertEq("#10", noVar("(2,(1,||),^)"));
    }
    @Test
    public void evaluateExp928Test(){
        assertEq("1", noVar("(1,*)"));
    }
    @Test
    public void evaluateExp929Test(){
        assertEq("-1", noVar("(-1,*)"));
    }
    @Test
    public void evaluateExp930Test(){
        assertEq("#13", noVar("((1,1,1,-),0,/)"));
    }
    @Test
    public void evaluateExp931Test(){
        assertEq("#23", noVar("(((1,1,.),(1,0,.),.),0,^^^)"));
    }
    @Test
    public void evaluateExp932Test(){
        assertEq("{-1;1}", noVar("({1,1;1,1},<)"));
    }
    @Test
    public void evaluateExp933Test(){
        assertEq("{1;1}", noVar("({1,1;1,1},>)"));
    }
    @Test
    public void evaluateExp934Test(){
        assertEq("{-1,1;1,1}", noVar("({1,1;1,1},<>)"));
    }
    @Test
    public void evaluateExp935Test(){
        assertEq("{1,-1;1,1}", noVar("({1,1;1,1},><)"));
    }
    @Test
    public void evaluateExp936Test(){
        assertEq("{0;0}", noVar("({1,0;0,1;0,0},<)"));
    }
    @Test
    public void evaluateExp937Test(){
        assertEq("{1,0;0,1;0,0}", noVar("({1,0;0,1;0,0},>)"));
    }
    @Test
    public void evaluateExp938Test(){
        assertEq("{0;0;1}", noVar("({1,0,0;0,1,0},<)"));
    }
    @Test
    public void evaluateExp939Test(){
        assertEq("{1,0;0,1}", noVar("({1,0,0;0,1,0},>)"));
    }
    @Test
    public void evaluateExp940Test(){
        assertEq("{1,0;0,1}", noVar("({0,0;0,0;0,0},<)"));
    }
    @Test
    public void evaluateExp941Test(){
        assertEq("{0;0;0}", noVar("({0,0;0,0;0,0},>)"));
    }
    @Test
    public void evaluateExp942Test(){
        assertEq("{1,0,0;0,1,0;0,0,1}", noVar("({0,0,0;0,0,0},<)"));
    }
    @Test
    public void evaluateExp943Test(){
        assertEq("{0;0}", noVar("({0,0,0;0,0,0},>)"));
    }
    @Test
    public void evaluateExp944Test(){
        assertEq("#8", noVar("((1,||),<)"));
    }
    @Test
    public void evaluateExp945Test(){
        assertEq("#8", noVar("((1,||),>)"));
    }
    @Test
    public void evaluateExp946Test(){
        assertEq("#15", noVar("({0,0,0;0,0,0},<>)"));
    }
    @Test
    public void evaluateExp947Test(){
        assertEq("#15", noVar("({0,0;0,0;0,0},><)"));
    }
    @Test
    public void evaluateExp948Test(){
        assertEq("#8", noVar("((1,||),<>)"));
    }
    @Test
    public void evaluateExp949Test(){
        assertEq("#8", noVar("((1,||),><)"));
    }
    @Test
    public void evaluateExp950Test(){
        assertEq("{1,0;0,1}", noVar("({1,0;0,1},<>)"));
    }
    @Test
    public void evaluateExp951Test(){
        assertEq("{1,0;0,1}", noVar("({1,0;0,1},><)"));
    }
    @Test
    public void evaluateExp952Test(){
        assertEq("{1,0;0,1}", noVar("({0,0;0,0},<>)"));
    }
    @Test
    public void evaluateExp953Test(){
        assertEq("{1,0;0,1}", noVar("({0,0;0,0},><)"));
    }
    @Test
    public void nullVarTest(){
        assertEq("####", nullVar());
    }
    @Test
    public void nullMapTest(){
        assertEq("#####", nullMap());
    }
//    @Test
//    public void notAll(){
//        StringMap<String> aliases_ = new StringMap<String>();
//        aliases_.addEntry("vrai","vrai");
//        aliases_.addEntry("faux","vrai");
//        aliases_.addEntry("puis","vrai");
//        aliases_.addEntry("quot","vrai");
//        aliases_.addEntry("mod","vrai");
//        assertEq("#", MaParser.notAll("",aliases_,new StringList("vrai","faux","puis","quot","mod")));
//    }
    @Test
    public void test(){
//        assertEqRate(null, null);
//        assertNotEq(null, new MaRateStruct(Rate.zero()));
//        assertNotEq(new MaRateStruct(Rate.zero()), null);
        MaStruct ms_ = MaNullStruct.NULL_VALUE;
        assertTrue(!ms_.sameReference(null));
        assertTrue(ms_.sameReference(MaNullStruct.NULL_VALUE));
        assertEq("",ms_.displayRsult());
        assertSame(MaNullStruct.NULL_VALUE,MaNumParsers.tryGet(new CustList<MaOperationNode>(),0));
        MaStackOperators m_ = new MaStackOperators();
        m_.add(0,' ');
        assertEq(0,m_.ind());
        assertEq(' ',m_.oper());
        assertNotNull(new Rate("1").getNumeratorCopy());
        assertNotNull(new Rate("1").getDenominatorCopy());
        assertTrue(Edge.eqEdgesMath(new RatePoint(Rate.one(),Rate.one()),new CustList<Edge>(),new RatePoint(Rate.one(),Rate.one()),new CustList<Edge>()));
        assertTrue(Polygon.eqPolygonsMath(new RatePoint(Rate.one(), Rate.one()), new CustList<Polygon>(), new RatePoint(Rate.one(), Rate.one()), new CustList<Polygon>()));
        assertTrue(RatePoint.eqPtsMath(new RatePoint(Rate.one(),Rate.one()),new CustList<RatePoint>(),new RatePoint(Rate.one(),Rate.one()),new CustList<RatePoint>()));
        assertTrue(!Edge.eqEdgesMath(new RatePoint(Rate.one(),Rate.one()),new CustList<Edge>(new Edge(new RatePoint(Rate.one(),Rate.one()),new RatePoint(Rate.one(),Rate.one()))),new RatePoint(Rate.one(),Rate.one()),new CustList<Edge>()));
        assertTrue(!Polygon.eqPolygonsMath(new RatePoint(Rate.one(), Rate.one()), new CustList<Polygon>(new Polygon(new Rect(Rate.one(),Rate.one(),Rate.one(),Rate.one()))), new RatePoint(Rate.one(), Rate.one()), new CustList<Polygon>()));
        assertTrue(!RatePoint.eqPtsMath(new RatePoint(Rate.one(),Rate.one()),new CustList<RatePoint>(new RatePoint(Rate.one(),Rate.one())),new RatePoint(Rate.one(),Rate.one()),new CustList<RatePoint>()));
        IdMap<RatePoint, IdList<RatePoint>> ptsOne_ = new IdMap<RatePoint,IdList<RatePoint>>();
        ptsOne_.addEntry(new RatePoint(Rate.one(),Rate.one()),new IdList<RatePoint>());
        IdMap<RatePoint, IdList<RatePoint>> ptsTwo_ = new IdMap<RatePoint,IdList<RatePoint>>();
        ptsTwo_.addEntry(new RatePoint(Rate.one(),Rate.one()),new IdList<RatePoint>(new RatePoint(Rate.one(),Rate.one())));
        assertTrue(!RatePoint.eqPtsMath(ptsOne_,ptsTwo_));
        IdMap<RatePoint, CustList<Triangle>> polsOne_ = new IdMap<RatePoint,CustList<Triangle>>();
        polsOne_.addEntry(new RatePoint(Rate.one(),Rate.one()),new CustList<Triangle>());
        IdMap<RatePoint, CustList<Triangle>> polsTwo_ = new IdMap<RatePoint,CustList<Triangle>>();
        polsTwo_.addEntry(new RatePoint(Rate.one(),Rate.one()),new CustList<Triangle>(new Triangle(new RatePoint(Rate.zero(),Rate.zero()),new RatePoint(Rate.zero(),Rate.one()),new RatePoint(Rate.one(),Rate.one()))));
        assertTrue(!Polygon.eqPolygonsMath(polsOne_,polsTwo_));
        IdMap<RatePoint, CustList<Edge>> edgesOne_ = new IdMap<RatePoint,CustList<Edge>>();
        edgesOne_.addEntry(new RatePoint(Rate.one(),Rate.one()),new CustList<Edge>());
        IdMap<RatePoint, CustList<Edge>> edgesTwo_ = new IdMap<RatePoint,CustList<Edge>>();
        assertTrue(!Edge.eqEdgesMath(edgesOne_,edgesTwo_));
        assertEq(0, StrTypes.offset(new StrTypes(),0));
        assertEq("", StrTypes.value(new StrTypes(),0));
        assertEq("",ConstantMaOperation.nb(new CustList<StringBuilder>(),0));
    }
    private static String noVar(String _el) {
        return processEl(new MaUserInput(_el,new CustList<Replacement>(),true));
    }
    private static String nullVar() {
        CustList<Replacement> conf_ = new CustList<Replacement>();
        Replacement r_ = new Replacement();
        r_.setNewString("");
        r_.setOldString("");
        conf_.add(r_);
        return processEl(new MaUserInput("", conf_,true));
    }
    private static String nullMap() {
        return processEl(new MaUserInput("", new CustList<Replacement>(),false));
    }
    private static String oneVar(String _el,String _var,String _value) {
        CustList<Replacement> conf_ = new CustList<Replacement>();
        Replacement rep_ = new Replacement();
        rep_.setOldString(_var);
        rep_.setNewString(_value);
        conf_.add(rep_);
        return processEl(new MaUserInput(_el, conf_,true));
    }
    private static String dupVar(String _el,String _var,String _value) {
        CustList<Replacement> conf_ = new CustList<Replacement>();
        Replacement rep_ = new Replacement();
        rep_.setOldString(_var);
        rep_.setNewString(_value);
        conf_.add(rep_);
        rep_ = new Replacement();
        rep_.setOldString(_var);
        rep_.setNewString(_value);
        conf_.add(rep_);
        return processEl(new MaUserInput(_el, conf_,true));
    }
    private static String twoVars(String _el,String _var,String _value,String _var2,String _value2) {
        CustList<Replacement> conf_ = new CustList<Replacement>();
        Replacement rep_ = new Replacement();
        rep_.setOldString(_var);
        rep_.setNewString(_value);
        conf_.add(rep_);
        rep_ = new Replacement();
        rep_.setOldString(_var2);
        rep_.setNewString(_value2);
        conf_.add(rep_);
        return processEl(new MaUserInput(_el, conf_,true));
    }

    private static String processEl(MaUserInput _input) {
        return MaParser.processEl(DefaultGenerator.oneElt(),new CustList<String>(),new CustomSeedGene(),_input, new DefDoubleToStrConverter());
    }
}
