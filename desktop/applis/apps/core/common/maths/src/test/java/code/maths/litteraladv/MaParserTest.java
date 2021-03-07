package code.maths.litteraladv;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import code.util.CustList;
import code.util.Replacement;
import code.util.StringList;
import code.util.StringMap;
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
        assertEq("#-", oneVar("a","a","-"));
    }
    @Test
    public void evaluateExp20Test(){
        assertEq("8", noVar("puis(2,3)"));
    }
    @Test
    public void evaluateExp21Test(){
        assertEq("8", oneVar("puis2(2,3)","#puis2","puis"));
    }
    @Test
    public void evaluateExp22Test(){
        assertEq("9", noVar("puis(3,2)"));
    }
    @Test
    public void evaluateExp23Test(){
        assertEq("9", oneVar("puis2(3,2)","#puis2","puis"));
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
        assertEq("8", noVar("puis(2,3) "));
    }
    @Test
    public void evaluateExp34Test(){
        assertEq("8", noVar("puis(2, 3)"));
    }
    @Test
    public void evaluateExp35Test(){
        assertEq("#0", noVar("puis(2,2,3)"));
    }
    @Test
    public void evaluateExp36Test(){
        assertEq("1", noVar("vrai()"));
    }
    @Test
    public void evaluateExp37Test(){
        assertEq("#1", noVar("1+vrai()"));
    }
    @Test
    public void evaluateExp38Test(){
        assertEq("#6", noVar("vrai()+1"));
    }
    @Test
    public void evaluateExp39Test(){
        assertEq("0", noVar("faux()"));
    }
    @Test
    public void evaluateExp40Test(){
        assertEq("#1", noVar("1+faux()"));
    }
    @Test
    public void evaluateExp41Test(){
        assertEq("#6", noVar("faux()+1"));
    }
    @Test
    public void evaluateExp42Test(){
        assertEq("#0", noVar("puis(vrai(),faux())"));
    }
    @Test
    public void evaluateExp43Test(){
        assertEq("0", noVar("puis(0,3)"));
    }
    @Test
    public void evaluateExp44Test(){
        assertEq("1/2", noVar("puis(2,-1)"));
    }
    @Test
    public void evaluateExp45Test(){
        assertEq("#0", noVar("puis(0,-1)"));
    }
    @Test
    public void evaluateExp46Test(){
        assertEq("#0", noVar("(0,-1)"));
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
        assertEq("1", noVar("vrai()&1+1=2"));
    }
    @Test
    public void evaluateExp53Test(){
        assertEq("9", noVar("1+puis(2,3)"));
    }
    @Test
    public void evaluateExp54Test(){
        assertEq("64", noVar("puis(puis(2,3),2)"));
    }
    @Test
    public void evaluateExp55Test(){
        assertEq("#0", noVar("puis(3,2)vrai()"));
    }
    @Test
    public void evaluateExp56Test(){
        assertEq("0", noVar("faux()=vrai()"));
    }
    @Test
    public void evaluateExp57Test(){
        assertEq("1", noVar("vrai()=vrai()"));
    }
    @Test
    public void evaluateExp58Test(){
        assertEq("0", noVar("1+1=1"));
    }
    @Test
    public void evaluateExp59Test(){
        assertEq("1", noVar("faux()!vrai()"));
    }
    @Test
    public void evaluateExp60Test(){
        assertEq("0", noVar("vrai()!vrai()"));
    }
    @Test
    public void evaluateExp61Test(){
        assertEq("0", noVar("0>1"));
    }
    @Test
    public void evaluateExp62Test(){
        assertEq("#1", noVar("0>vrai()"));
    }
    @Test
    public void evaluateExp63Test(){
        assertEq("#6", noVar("vrai()>0"));
    }
    @Test
    public void evaluateExp64Test(){
        assertEq("#0", noVar("!0"));
    }
    @Test
    public void evaluateExp65Test(){
        assertEq("#0", noVar("-vrai()"));
    }
    @Test
    public void evaluateExp66Test(){
        assertEq("1", noVar("quot(5,3)"));
    }
    @Test
    public void evaluateExp67Test(){
        assertEq("2", noVar("mod(5,3)"));
    }
    @Test
    public void evaluateExp68Test(){
        assertEq("#0", noVar("quot(5,0)"));
    }
    @Test
    public void evaluateExp69Test(){
        assertEq("#0", noVar("mod(5,0)"));
    }
    @Test
    public void evaluateExp70Test(){
        assertEq("#0", noVar("_"));
    }
    @Test
    public void evaluateExp71Test(){
        assertEq("0", noVar("1=vrai()"));
    }
    @Test
    public void evaluateExp72Test(){
        assertEq("0", noVar("vrai()=1"));
    }
    @Test
    public void evaluateExp73Test(){
        assertEq("#1", noVar("1&2"));
    }
    @Test
    public void evaluateExp74Test(){
        assertEq("#6", noVar("vrai()&2"));
    }
    @Test
    public void evaluateExp75Test(){
        assertEq("#-1", noVar("puis(3,2) vrai"));
    }
    @Test
    public void evaluateExp76Test(){
        assertEq("#0", noVar("\\"));
    }
    @Test
    public void evaluateExp77Test(){
        assertEq("#0", noVar("quot(5,vrai())"));
    }
    @Test
    public void evaluateExp78Test(){
        assertEq("#0", noVar("mod(5,vrai())"));
    }
    @Test
    public void evaluateExp79Test(){
        assertEq("#0", noVar("quot(5,2,5)"));
    }
    @Test
    public void evaluateExp80Test(){
        assertEq("#0", noVar("mod(5,2,5)"));
    }
    @Test
    public void evaluateExp81Test(){
        assertEq("#0", noVar("vrai(1)"));
    }
    @Test
    public void evaluateExp82Test(){
        assertEq("#0", noVar("faux(1)"));
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
        assertEq("#", dupVar("","a","1"));
    }
    @Test
    public void evaluateExp92Test(){
        assertEq("#", oneVar("","\\a","1"));
    }
    @Test
    public void evaluateExp93Test(){
        assertEq("#", oneVar("","#a","a"));
    }
    @Test
    public void evaluateExp94Test(){
        assertEq("#", oneVar("","#\\a","a"));
    }
    @Test
    public void evaluateExp95Test(){
        assertEq("#", oneVar("","#a","\\a"));
    }
    @Test
    public void evaluateExp96Test(){
        assertEq("#", oneVar("","#puis2","puis2"));
    }
    @Test
    public void evaluateExp97Test(){
        assertEq("#", oneVar("","#\\puis2","puis"));
    }
    @Test
    public void evaluateExp98Test(){
        assertEq("#", dupVar("","#puis2","puis"));
    }
    @Test
    public void evaluateExp99Test(){
        assertEq("1", oneVar("vrai2()","#vrai2","vrai"));
    }
    @Test
    public void evaluateExp100Test(){
        assertEq("0", oneVar("faux2()","#faux2","faux"));
    }
    @Test
    public void evaluateExp101Test(){
        assertEq("1", oneVar("quot2(5,3)","#quot2","quot"));
    }
    @Test
    public void evaluateExp102Test(){
        assertEq("2", oneVar("mod2(5,3)","#mod2","mod"));
    }
    @Test
    public void evaluateExp103Test(){
        assertEq("#", oneVar("","","0"));
    }
    @Test
    public void evaluateExp104Test(){
        assertEq("#", oneVar("","0",""));
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
        assertEq("1", noVar("abs(1)"));
    }
    @Test
    public void evaluateExp118Test(){
        assertEq("1", noVar("sgn(1)"));
    }
    @Test
    public void evaluateExp119Test(){
        assertEq("1", noVar("num(1)"));
    }
    @Test
    public void evaluateExp120Test(){
        assertEq("1", noVar("den(1)"));
    }
    @Test
    public void evaluateExp121Test(){
        assertEq("1", noVar("ent(1)"));
    }
    @Test
    public void evaluateExp122Test(){
        assertEq("1", noVar("troncature(1)"));
    }
    @Test
    public void evaluateExp123Test(){
        assertEq("0", noVar("modtaux(1,1)"));
    }
    @Test
    public void evaluateExp124Test(){
        assertEq("#0", noVar("modtaux(1,0)"));
    }
    @Test
    public void evaluateExp125Test(){
        assertEq("#0", noVar("abs(vrai())"));
    }
    @Test
    public void evaluateExp126Test(){
        assertEq("#0", noVar("sgn(vrai())"));
    }
    @Test
    public void evaluateExp127Test(){
        assertEq("#0", noVar("num(vrai())"));
    }
    @Test
    public void evaluateExp128Test(){
        assertEq("#0", noVar("den(vrai())"));
    }
    @Test
    public void evaluateExp129Test(){
        assertEq("#0", noVar("ent(vrai())"));
    }
    @Test
    public void evaluateExp130Test(){
        assertEq("#0", noVar("troncature(vrai())"));
    }
    @Test
    public void evaluateExp131Test(){
        assertEq("#0", noVar("modtaux(1,vrai())"));
    }
    @Test
    public void evaluateExp132Test(){
        assertEq("#0", noVar("abs(1,1)"));
    }
    @Test
    public void evaluateExp133Test(){
        assertEq("#0", noVar("sgn(1,1)"));
    }
    @Test
    public void evaluateExp134Test(){
        assertEq("#0", noVar("num(1,1)"));
    }
    @Test
    public void evaluateExp135Test(){
        assertEq("#0", noVar("den(1,1)"));
    }
    @Test
    public void evaluateExp136Test(){
        assertEq("#0", noVar("ent(1,1)"));
    }
    @Test
    public void evaluateExp137Test(){
        assertEq("#0", noVar("troncature(1,1)"));
    }
    @Test
    public void evaluateExp138Test(){
        assertEq("#0", noVar("modtaux(1)"));
    }
    @Test
    public void evaluateExp139Test(){
        assertEq("(-1,2,1,15)", noVar("bezout(5,3)"));
    }
    @Test
    public void evaluateExp140Test(){
        assertEq("#0", noVar("bezout(5,vrai())"));
    }
    @Test
    public void evaluateExp141Test(){
        assertEq("#0", noVar("bezout(5)"));
    }
    @Test
    public void evaluateExp142Test(){
        assertEq("1", noVar("bezout(5,3)=bezout(5,3)"));
    }
    @Test
    public void evaluateExp143Test(){
        assertEq("0", noVar("bezout(5,3)=bezout(3,5)"));
    }
    @Test
    public void evaluateExp144Test(){
        assertEq("0", noVar("bezout(5,4)=bezout(5,3)"));
    }
    @Test
    public void evaluateExp145Test(){
        assertEq("0", noVar("bezout(4,6)=bezout(5,3)"));
    }
    @Test
    public void evaluateExp146Test(){
        assertEq("0", noVar("bezout(4,6)=3"));
    }
    @Test
    public void evaluateExp147Test(){
        assertEq("0", noVar("bezout(4,6)=bezout(2,3)"));
    }
    @Test
    public void evaluateExp148Test(){
        assertEq("0", noVar("bezout(-1,1)=bezout(-2,1)"));
    }
    @Test
    public void evaluateExp149Test(){
        assertEq("-1", noVar("lg(0)"));
    }
    @Test
    public void evaluateExp150Test(){
        assertEq("4", noVar("lg(bezout(4,6))"));
    }
    @Test
    public void evaluateExp151Test(){
        assertEq("#0", noVar("lg()"));
    }
    @Test
    public void evaluateExp152Test(){
        assertEq("-1", noVar("bezout(5,3)[-4]"));
    }
    @Test
    public void evaluateExp153Test(){
        assertEq("2", noVar("bezout(5,3)[-3]"));
    }
    @Test
    public void evaluateExp154Test(){
        assertEq("1", noVar("bezout(5,3)[-2]"));
    }
    @Test
    public void evaluateExp155Test(){
        assertEq("15", noVar("bezout(5,3)[-1]"));
    }
    @Test
    public void evaluateExp156Test(){
        assertEq("-1", noVar("bezout(5,3)[0]"));
    }
    @Test
    public void evaluateExp157Test(){
        assertEq("2", noVar("bezout(5,3)[1]"));
    }
    @Test
    public void evaluateExp158Test(){
        assertEq("1", noVar("bezout(5,3)[2]"));
    }
    @Test
    public void evaluateExp159Test(){
        assertEq("15", noVar("bezout(5,3)[3]"));
    }
    @Test
    public void evaluateExp160Test(){
        assertEq("#0", noVar("bezout(5,3)[4]"));
    }
    @Test
    public void evaluateExp161Test(){
        assertEq("#0", noVar("bezout(5,3)[-5]"));
    }
    @Test
    public void evaluateExp162Test(){
        assertEq("#0", noVar("bezout(5,3)[1/2]"));
    }
    @Test
    public void evaluateExp163Test(){
        assertEq("#0", noVar("vrai()[0]"));
    }
    @Test
    public void evaluateExp164Test(){
        assertEq("#0", noVar("bezout(5,3)[1,2]"));
    }
    @Test
    public void evaluateExp165Test(){
        assertEq("#13", noVar("bezout(5,3)[0)"));
    }
    @Test
    public void evaluateExp166Test(){
        assertEq("#10", noVar("bezout(5,3]"));
    }
    @Test
    public void evaluateExp167Test(){
        assertEq("-1", noVar("bezout(5,3)[bezout(5,3)[0]+1]"));
    }
    @Test
    public void evaluateExp168Test(){
        assertEq("#0", noVar("bezout(5,3)[vrai()]"));
    }
    @Test
    public void evaluateExp169Test(){
        assertEq("1", noVar("prem(17)"));
    }
    @Test
    public void evaluateExp170Test(){
        assertEq("0", noVar("prem(16)"));
    }
    @Test
    public void evaluateExp171Test(){
        assertEq("#0", noVar("prem(1/6)"));
    }
    @Test
    public void evaluateExp172Test(){
        assertEq("#0", noVar("prem(vrai())"));
    }
    @Test
    public void evaluateExp173Test(){
        assertEq("#0", noVar("prem(1,6)"));
    }
    @Test
    public void evaluateExp174Test(){
        assertEq("(1)", noVar("divs(1)"));
    }
    @Test
    public void evaluateExp175Test(){
        assertEq("(1,2,3,6)", noVar("divs(6)"));
    }
    @Test
    public void evaluateExp176Test(){
        assertEq("1", noVar("divs(1)[0]"));
    }
    @Test
    public void evaluateExp177Test(){
        assertEq("1", noVar("divs(1)[-1]"));
    }
    @Test
    public void evaluateExp178Test(){
        assertEq("#0", noVar("divs(1)[1]"));
    }
    @Test
    public void evaluateExp179Test(){
        assertEq("#0", noVar("divs(1)[-2]"));
    }
    @Test
    public void evaluateExp180Test(){
        assertEq("#0", noVar("divs(1)[0,0]"));
    }
    @Test
    public void evaluateExp181Test(){
        assertEq("#0", noVar("divs(1)[1/2]"));
    }
    @Test
    public void evaluateExp182Test(){
        assertEq("#0", noVar("divs(1)[vrai()]"));
    }
    @Test
    public void evaluateExp183Test(){
        assertEq("#0", noVar("divs(1/2)"));
    }
    @Test
    public void evaluateExp184Test(){
        assertEq("#0", noVar("divs(1,2)"));
    }
    @Test
    public void evaluateExp185Test(){
        assertEq("#0", noVar("divs(vrai())"));
    }
    @Test
    public void evaluateExp186Test(){
        assertEq("#0", noVar("divs(1,vrai())"));
    }
    @Test
    public void evaluateExp187Test(){
        assertEq("1", noVar("lg(divs(1))"));
    }
    @Test
    public void evaluateExp188Test(){
        assertEq("1", noVar("divs(1)=divs(1)"));
    }
    @Test
    public void evaluateExp189Test(){
        assertEq("0", noVar("divs(5)=divs(7)"));
    }
    @Test
    public void evaluateExp190Test(){
        assertEq("0", noVar("divs(5)=divs(4)"));
    }
    @Test
    public void evaluateExp191Test(){
        assertEq("0", noVar("divs(5)=2"));
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
        assertEq("#0", noVar("quot(5/3,0)"));
    }

    @Test
    public void nullVarTest(){
        assertEq("#", nullVar());
    }
    @Test
    public void nullMapTest(){
        assertEq("#", nullMap());
    }
    @Test
    public void notAll(){
        StringMap<String> aliases_ = new StringMap<String>();
        aliases_.addEntry("vrai","vrai");
        aliases_.addEntry("faux","vrai");
        aliases_.addEntry("puis","vrai");
        aliases_.addEntry("quot","vrai");
        aliases_.addEntry("mod","vrai");
        assertEq("#", MaParser.notAll("",aliases_,new StringList("vrai","faux","puis","quot","mod")));
    }
    @Test
    public void test(){
        assertTrue(MaNumParsers.eqNb(null,null));
        assertTrue(!MaNumParsers.eqNb(null,new MaRateStruct(Rate.zero())));
        assertTrue(!MaNumParsers.eqNb(new MaRateStruct(Rate.zero()),null));
        assertNotNull(MaNumParsers.toRate(null));
        assertNull(MaNumParsers.tryGet(new CustList<MaOperationNode>(),0));
        MaStackOperators m_ = new MaStackOperators();
        m_.add(0,' ');
        assertEq(0,m_.ind());
        assertEq(' ',m_.oper());
        assertNotNull(new Rate("1").getNumeratorCopy());
        assertNotNull(new Rate("1").getDenominatorCopy());
    }
    private static String noVar(String _el) {
        return MaParser.processEl(_el, new CustList<Replacement>());
    }
    private static String nullVar() {
        CustList<Replacement> conf_ = new CustList<Replacement>();
        conf_.add(null);
        return MaParser.processEl("", conf_);
    }
    private static String nullMap() {
        return MaParser.processEl("", null);
    }
    private static String oneVar(String _el,String _var,String _value) {
        CustList<Replacement> conf_ = new CustList<Replacement>();
        Replacement rep_ = new Replacement();
        rep_.setOldString(_var);
        rep_.setNewString(_value);
        conf_.add(rep_);
        return MaParser.processEl(_el, conf_);
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
        return MaParser.processEl(_el, conf_);
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
        return MaParser.processEl(_el, conf_);
    }
}
