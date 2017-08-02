package code.expressionlanguage;

import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Mapping;
import code.expressionlanguage.Templates;
import code.expressionlanguage.classes.CmpList;
import code.expressionlanguage.classes.CustBigInt;
import code.expressionlanguage.classes.CustTemp;
import code.expressionlanguage.classes.EnumNumber;
import code.expressionlanguage.classes.GoodCmp;
import code.expressionlanguage.classes.StrangeCmp;
import code.expressionlanguage.classes.Templating;
import code.expressionlanguage.classes.TemplatingBis;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Cmp;
import code.util.ints.Listable;

@SuppressWarnings("static-method")
public class TemplatesTest {
//    private static final String ARR_INT = "[I";
//    private static final String ARR_OBJECT = "[Ljava.lang.Object;";
//    private static final String ARR_STRING = "[Ljava.lang.String;";
//    private static final String ARR_ENUM = "[Lexpressionlanguage.classes.EnumNumber;";
//    private static final String ARR_VAR_E = "[L#E;";
//    private static final String ARR_VAR_F = "[L#F;";
//    private static final String ARR_VAR_S = "[L#S;";
//    private static final String ARR_VAR_T = "[L#T;";
    private static final String ARR_INT = "[$int";
    private static final String ARR_OBJECT = "[java.lang.Object";
    private static final String ARR_STRING = "[java.lang.String";
    private static final String ENUM = EnumNumber.class.getName();
    private static final String CUST_BIG_INT = CustBigInt.class.getName();
    private static final String TEMPLATING = Templating.class.getName();
    private static final String TEMPLATING_BIS = TemplatingBis.class.getName();
    private static final String CMP_LIST = CmpList.class.getName();
    private static final String STRANGE_CMP_LIST = StrangeCmp.class.getName();
    private static final String GOOD_CMP_LIST = GoodCmp.class.getName();
    private static final String ARR_ENUM = "["+ENUM;
    private static final String ARR_VAR_E = "[#E";
    private static final String ARR_VAR_F = "[#F";
    private static final String ARR_VAR_S = "[#S";
    private static final String ARR_VAR_T = "[#T";
    private static final String STRING_LIST = StringList.class.getName();
    private static final String CUST_LIST = CustList.class.getName();
    private static final String ENUM_LIST = EnumList.class.getName();
    private static final String ENUM_MAP = EnumMap.class.getName();
    private static final String CMP = Cmp.class.getName();
    private static final String TMPL = CustTemp.class.getName();

    @Test
    public void format1Test() {
        String first_ = String.class.getName();
        String second_ = Integer.class.getName();
        assertEq(second_,Templates.format(first_, second_, null));
    }

    @Test
    public void format2Test() {
        String first_ = CUST_LIST+"<"+String.class.getName()+">";
        String second_ = Integer.class.getName();
        assertEq(second_,Templates.format(first_, second_, null));
    }

    @Test
    public void format3Test() {
        String first_ = CUST_LIST+"<E>";
        String second_ = Integer.class.getName();
        assertEq(second_,Templates.format(first_, second_, null));
    }

    @Test
    public void format4Test() {
        String first_ = CUST_LIST+"<#E>";
        String second_ = Integer.class.getName();
        assertEq(second_,Templates.format(first_, second_, null));
    }

    @Test
    public void format5Test() {
        String first_ = CUST_LIST+"<"+Integer.class.getName()+">";
        String second_ = "T";
        assertEq(Integer.class.getName(),Templates.format(first_, second_, null));
    }

    @Test
    public void format6Test() {
        String first_ = CUST_LIST+"<"+Integer.class.getName()+">";
        String second_ = "#T";
        assertEq(Integer.class.getName(),Templates.format(first_, second_, null));
    }

    @Test
    public void format7Test() {
        String first_ = CUST_LIST+"<#E>";
        String second_ = "T";
        assertEq("#E",Templates.format(first_, second_, null));
    }

    @Test
    public void format8Test() {
        String first_ = CUST_LIST+"<#E>";
        String second_ = "#T";
        assertEq("#E",Templates.format(first_, second_, null));
    }

    @Test
    public void eqTypes1Test() {
        String first_ = String.class.getName();
        String second_ = Integer.class.getName();
        assertTrue(!Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes2Test() {
        String first_ = CUST_LIST+"<"+String.class.getName()+">";
        String second_ = CUST_LIST+"<"+Integer.class.getName()+">";
        assertTrue(!Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes3Test() {
        String first_ = CUST_LIST+"<?~"+Integer.class.getName()+">";
        String second_ = CUST_LIST+"<"+Integer.class.getName()+">";
        assertTrue(!Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes4Test() {
        String first_ = CUST_LIST+"<"+Integer.class.getName()+">";
        String second_ = CUST_LIST+"<?~"+Integer.class.getName()+">";
        assertTrue(!Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes5Test() {
        String first_ = CUST_LIST+"<?~"+String.class.getName()+">";
        String second_ = CUST_LIST+"<?~"+Integer.class.getName()+">";
        assertTrue(!Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes6Test() {
        String first_ = TMPL+"<"+String.class.getName()+","+Integer.class.getName()+">";
        String second_ = TMPL+"<"+Integer.class.getName()+","+String.class.getName()+">";
        assertTrue(!Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes7Test() {
        String first_ = TMPL+"<?~"+String.class.getName()+"&"+Integer.class.getName()+","+String.class.getName()+">";
        String second_ = TMPL+"<?~"+Integer.class.getName()+"&"+String.class.getName()+","+String.class.getName()+">";
        assertTrue(Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes8Test() {
        String first_ = TMPL+"<"+String.class.getName()+","+Integer.class.getName()+">";
        String second_ = TMPL+"<"+String.class.getName()+","+Integer.class.getName()+">";
        assertTrue(Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes9Test() {
        String first_ = TMPL+"<?~"+String.class.getName()+"&"+String.class.getName()+","+Integer.class.getName()+">";
        String second_ = TMPL+"<?~"+String.class.getName()+","+Integer.class.getName()+">";
        assertTrue(Templates.eqTypes(first_, second_));
    }

    @Test
    public void eqTypes10Test() {
        String first_ = TMPL+"<?~"+String.class.getName()+"&"+Integer.class.getName()+","+Integer.class.getName()+">";
        String second_ = TMPL+"<?~"+String.class.getName()+","+Integer.class.getName()+">";
        assertTrue(!Templates.eqTypes(first_, second_));
    }

    @Test
    public void isCorrectTemplate1Test() {
        assertTrue(Templates.isCorrectTemplate(ENUM_LIST+"<"+ENUM+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate2Test() {
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<java.lang.String>", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate3Test() {
        assertTrue(Templates.isCorrectTemplate("java.util.List<? ~ java.lang.Object>", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate4Test() {
        assertTrue(Templates.isCorrectTemplate("java.util.List<? ~ java.lang.String>", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate5Test() {
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<? ~ java.lang.String>", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate6Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(Templates.isCorrectTemplate("java.util.List<#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate7Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate8Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(Templates.isCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate9Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>",CMP+"<#E>"));
        assertTrue(Templates.isCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate10Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CMP+"<E>","java.lang.Enum<#E>"));
        assertTrue(Templates.isCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate11Test() {
        assertTrue(Templates.isCorrectTemplate("java.util.List<java.lang.Object>", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate12Test() {
        assertTrue(Templates.isCorrectTemplate("java.util.List<java.lang.String>", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate13Test() {
        assertTrue(Templates.isCorrectTemplate("java.util.List<"+ARR_OBJECT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate14Test() {
        assertTrue(Templates.isCorrectTemplate("java.util.List<"+ARR_STRING+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate15Test() {
        assertTrue(Templates.isCorrectTemplate("java.util.List<"+ARR_INT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate16Test() {
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<"+ARR_OBJECT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate17Test() {
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<"+ARR_STRING+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate18Test() {
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<"+ARR_INT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate19Test() {
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<"+ARR_ENUM+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate20Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(Templates.isCorrectTemplate("java.util.List<"+ARR_VAR_E+">", t_,null));
    }

    @Test
    public void isCorrectTemplate21Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<"+ARR_VAR_E+">", t_,null));
    }

    @Test
    public void isCorrectTemplate22Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
//        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(Templates.isCorrectTemplate(ENUM_MAP+"<"+ENUM+","+CUST_BIG_INT+">", t_,null));
    }

    @Test
    public void isCorrectTemplate23Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(Templates.isCorrectTemplate(TEMPLATING+"<java.math.BigInteger,"+CUST_BIG_INT+">", t_,null));
    }

    @Test
    public void isCorrectTemplate24Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(!Templates.isCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",java.math.BigInteger>", t_,null));
    }

    @Test
    public void isCorrectTemplate25Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate(TEMPLATING+"<java.math.BigInteger,"+CUST_BIG_INT+">", t_,null));
    }

    @Test
    public void isCorrectTemplate26Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",java.math.BigInteger>", t_,null));
    }

    @Test
    public void isCorrectTemplate27Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        assertTrue(Templates.isCorrectTemplate("java.util.List<"+ARR_VAR_T+">", t_,null));
    }

    @Test
    public void isCorrectTemplate28Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("F", new StringList("java.lang.Enum<#F>"));
        assertTrue(Templates.isCorrectTemplate("java.util.List<"+ARR_VAR_F+">", t_,null));
    }
    @Test
    public void isCorrectTemplate29Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>",CMP+"<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrectTemplate(TEMPLATING_BIS+"<#T>", t_, null));
    }

    @Test
    public void isCorrectTemplate30Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>",CMP+"<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrectTemplate(ENUM_LIST+"<#T>", t_, null));
    }
    @Test
    public void isCorrectTemplate31Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrectTemplate(TEMPLATING_BIS+"<#T>", t_, null));
    }
    
    @Test
    public void isCorrectTemplate32Test() {
        assertTrue(!Templates.isCorrectTemplate(CMP_LIST+"<"+STRANGE_CMP_LIST+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate33Test() {
        assertTrue(Templates.isCorrectTemplate(CMP_LIST+"<"+GOOD_CMP_LIST+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate34Test() {
        assertTrue(Templates.isCorrectTemplate(CMP_LIST+"<?>", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate35Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST+"<? ~ java.lang.Enum<#T>&"+CMP+"<#T>>", t_,null));
    }

    @Test
    public void isCorrectTemplate36Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrectTemplate(CUST_LIST+"<? ~ java.lang.Enum<#T>&java.lang.Enum<#T>>", t_,null));
    }

    @Test
    public void isCorrectTemplate37Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrectTemplate(CUST_LIST+"<"+CUST_LIST+"<? ~ java.lang.Enum<#T>&java.lang.Enum<#T>>>", t_,null));
    }

    @Test
    public void isCorrectTemplate38Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrectTemplate(CUST_LIST+"<? ~ java.lang.Enum<#T>&"+CMP+"<#T>>", t_,null));
    }

    @Test
    public void isCorrect1Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.Object");
        assertTrue(Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect2Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.String");
        m_.setParam("java.lang.Object");
        assertTrue(Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect3Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.String");
        assertTrue(!Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect4Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.Object>");
        m_.setParam("java.util.List<java.lang.Object>");
        assertTrue(Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect5Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.String>");
        m_.setParam("java.util.List<java.lang.Object>");
        assertTrue(!Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect6Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.Object>");
        m_.setParam("java.util.List<java.lang.String>");
        assertTrue(!Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect7Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<? ~ java.lang.Object>");
        m_.setParam("java.util.List<? ~ java.lang.Object>");
        assertTrue(Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect8Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<? ~ java.lang.String>");
        m_.setParam("java.util.List<? ~ java.lang.Object>");
        assertTrue(Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect9Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<? ~ java.lang.Object>");
        m_.setParam("java.util.List<? ~ java.lang.String>");
        assertTrue(!Templates.isCorrect(m_, null));
    }

    @Test
    public void isCorrect10Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<? ~ java.lang.Enum<#E>>");
        m_.setParam(CUST_LIST+"<? ~ java.lang.Enum<#E>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect11Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<java.lang.Enum<#E>>");
        m_.setParam(CUST_LIST+"<java.lang.Enum<#E>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect12Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<#E>");
        m_.setParam(CUST_LIST+"<#E>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect13Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<#F>");
        m_.setParam(CUST_LIST+"<#F>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("F", new StringList("java.lang.Enum<#F>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect14Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<#F>");
        m_.setParam("java.util.Collection<#F>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("F", new StringList("java.lang.Enum<#F>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect15Test() {
        Mapping m_ = new Mapping();
        m_.setArg(STRING_LIST);
        m_.setParam(CUST_LIST+"<java.lang.String>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect16Test() {
        Mapping m_ = new Mapping();
        m_.setArg(STRING_LIST);
        m_.setParam(CUST_LIST+"<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect17Test() {
        Mapping m_ = new Mapping();
        m_.setArg(STRING_LIST);
        m_.setParam(CUST_LIST+"<? ~ java.lang.String>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect18Test() {
        Mapping m_ = new Mapping();
        m_.setArg(STRING_LIST);
        m_.setParam(CUST_LIST+"<? ~ java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect19Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<? ~ java.lang.Enum<#T>>");
        m_.setParam(CUST_LIST+"<? ~ java.lang.Enum<#T>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect20Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam("#S");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }
    @Test
    public void isCorrect21Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#S");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect22Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<java.lang.String>");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect23Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~ "+ARR_STRING+">");
        m_.setParam(CUST_LIST+"<? ~ "+ARR_OBJECT+">");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect24Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<? ~ java.lang.Enum<#T>&"+CMP+"<#T>>");
        m_.setParam(CUST_LIST+"<? ~ java.lang.Enum<#T>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect25Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<? ~ java.lang.Enum<#T>>");
        m_.setParam(CUST_LIST+"<? ~ java.lang.Enum<#T>&"+CMP+"<#T>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect26Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~ "+ARR_OBJECT+">");
        m_.setParam(CUST_LIST+"<? ~ "+ARR_STRING+">");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect27Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<#U>");
        m_.setParam(CUST_LIST+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("U", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect28Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~ #T>");
        m_.setParam(CUST_LIST+"<? ~ #S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }
    @Test
    public void isCorrect29Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<#T>");
        m_.setParam(CUST_LIST+"<? ~ #S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect30Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<java.lang.Integer>");
        m_.setParam(CUST_LIST+"<? ~ #S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("S", new StringList("java.lang.Number"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }
    @Test
    public void isCorrect31Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~ #S>");
        m_.setParam(CUST_LIST+"<java.lang.Number>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("S", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect32Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam(CUST_LIST+"<? ~ #S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#U"));
        t_.put("U", new StringList(CUST_LIST+"<? ~ #S>"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect33Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam(CUST_LIST+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#U"));
        t_.put("U", new StringList(CUST_LIST+"<#S>"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect34Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam(Listable.class.getName()+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#U"));
        t_.put("U", new StringList(CUST_LIST+"<#S>"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }
 
    @Test
    public void isCorrect35Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam(CUST_LIST+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#U"));
        t_.put("U", new StringList(Listable.class.getName()+"<#S>"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect36Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_T);
        m_.setParam(ARR_VAR_S);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect37Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect38Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~"+ARR_VAR_S+">");
        m_.setParam(CUST_LIST+"<? ~"+ARR_VAR_T+">");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect39Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~"+ARR_VAR_T+">");
        m_.setParam(CUST_LIST+"<? ~"+ARR_VAR_S+">");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect40Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<?>");
        m_.setParam(CUST_LIST+"<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect41Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<?>");
        m_.setParam("java.lang.Iterable<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect42Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Iterable<?>");
        m_.setParam(CUST_LIST+"<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect43Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<?>");
        m_.setParam(CUST_LIST+"<? ~ java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect44Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<?>");
        m_.setParam("java.lang.Iterable<? ~ java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect45Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Iterable<?>");
        m_.setParam(CUST_LIST+"<? ~ java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }
}
