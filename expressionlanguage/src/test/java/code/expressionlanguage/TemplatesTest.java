package code.expressionlanguage;

import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.classes.CmpList;
import code.expressionlanguage.classes.CustBigInt;
import code.expressionlanguage.classes.CustEqList;
import code.expressionlanguage.classes.CustSecEqList;
import code.expressionlanguage.classes.CustTemp;
import code.expressionlanguage.classes.EnumNumber;
import code.expressionlanguage.classes.GoodCmp;
import code.expressionlanguage.classes.IOne;
import code.expressionlanguage.classes.IThree;
import code.expressionlanguage.classes.ITwo;
import code.expressionlanguage.classes.MyCmpClass;
import code.expressionlanguage.classes.MyEqClass;
import code.expressionlanguage.classes.MyImpl;
import code.expressionlanguage.classes.StrangeCmp;
import code.expressionlanguage.classes.Templating;
import code.expressionlanguage.classes.TemplatingBis;
import code.expressionlanguage.classes.TransitiveTemplating;
import code.expressionlanguage.classes.TwoBounds;
import code.util.AbEqList;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Cmp;
import code.util.ints.Listable;

@SuppressWarnings("static-method")
public class TemplatesTest {
    private static final String ARR_INT = "[$int";
    private static final String ARR_OBJECT = "[java.lang.Object";
    private static final String ARR_STRING = "[java.lang.String";
    private static final String ARR_ITHREE = "["+IThree.class.getName();
    private static final String ARR_ITWO = "["+ITwo.class.getName();
    private static final String ENUM = EnumNumber.class.getName();
    private static final String CUST_BIG_INT = CustBigInt.class.getName();
    private static final String TEMPLATING = Templating.class.getName();
    private static final String TEMPLATING_BIS = TemplatingBis.class.getName();
    private static final String TRANSITIVE_TEMPLATING = TransitiveTemplating.class.getName();
    private static final String CMP_LIST = CmpList.class.getName();
    private static final String CUST_EQ_LIST = CustEqList.class.getName();
    private static final String CUST_SEC_LIST = CustSecEqList.class.getName();
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
    private static final String MY_EQ_CLASS = MyEqClass.class.getName();
    private static final String MY_CMP_CLASS = MyCmpClass.class.getName();

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
        assertEq("T",Templates.format(first_, second_, null));
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
        assertEq("T",Templates.format(first_, second_, null));
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
    public void getTypesByBases1Test() {
        StringList t_ = Templates.getTypesByBases("java.lang.String", "java.lang.Object", null);
        assertEq(0, t_.size());
    }

    @Test
    public void getTypesByBases2Test() {
        StringList t_ = Templates.getTypesByBases("java.lang.Object", "java.lang.String", null);
        assertNull(t_);
    }

    @Test
    public void getTypesByBases3Test() {
        StringList t_ = Templates.getTypesByBases(StringList.class.getName(), Listable.class.getName(), null);
        assertEq(1, t_.size());
        assertEq("java.lang.String", t_.get(0));
    }

    @Test
    public void getTypesByBases4Test() {
        StringList t_ = Templates.getTypesByBases(StringList.class.getName(), Iterable.class.getName(), null);
        assertEq(1, t_.size());
        assertEq("java.lang.String", t_.get(0));
    }

    @Test
    public void getTypesByBases5Test() {
        StringList t_ = Templates.getTypesByBases(Listable.class.getName(), Iterable.class.getName(), null);
        assertNull(t_);
    }

    @Test
    public void isCorrectWrite1Test() {
        assertTrue(Templates.isCorrectWrite("java.lang.String"));
    }

    @Test
    public void isCorrectWrite2Test() {
        assertTrue(Templates.isCorrectWrite(PrimitiveTypeUtil.PRIM_INT));
    }

    @Test
    public void isCorrectWrite3Test() {
        assertTrue(Templates.isCorrectWrite("[java.lang.String"));
    }

    @Test
    public void isCorrectWrite4Test() {
        assertTrue(Templates.isCorrectWrite("["+PrimitiveTypeUtil.PRIM_INT));
    }

    @Test
    public void isCorrectWrite5Test() {
        assertTrue(Templates.isCorrectWrite("#E"));
    }

    @Test
    public void isCorrectWrite6Test() {
        assertTrue(Templates.isCorrectWrite("[#E"));
    }

    @Test
    public void isCorrectWrite7Test() {
        assertTrue(Templates.isCorrectWrite("code.util.CustList<#E>"));
    }

    @Test
    public void isCorrectWrite8Test() {
        assertTrue(Templates.isCorrectWrite("code.util.CustList<[#E>"));
    }

    @Test
    public void isCorrectWrite9Test() {
        assertTrue(Templates.isCorrectWrite("code.util.CustList<["+PrimitiveTypeUtil.PRIM_INT+">"));
    }

    @Test
    public void isCorrectWrite10Test() {
        assertTrue(Templates.isCorrectWrite("code.util.CustList<java.lang.String>"));
    }

    @Test
    public void isCorrectWrite11Test() {
        assertTrue(Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String>>"));
    }

    @Test
    public void isCorrectWrite12Test() {
        assertTrue(Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String>,java.lang.String>"));
    }

    @Test
    public void isCorrectWrite13Test() {
        assertTrue(Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String,java.lang.String>>"));
    }

    @Test
    public void isCorrectWrite14Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String,java.lang.String>"));
    }

    @Test
    public void isCorrectWrite15Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String,java.lang.String>>>"));
    }

    @Test
    public void isCorrectWrite16Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<"+PrimitiveTypeUtil.PRIM_INT+">"));
    }

    @Test
    public void isCorrectWrite17Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<code.util.CustList<"+PrimitiveTypeUtil.PRIM_INT+",java.lang.String>>"));
    }

    @Test
    public void isCorrectWrite18Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<["+PrimitiveTypeUtil.PRIM_INT+".int>"));
    }

    @Test
    public void isCorrectWrite19Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<code.util.CustList<["+PrimitiveTypeUtil.PRIM_INT+".int,java.lang.String>>"));
    }

    @Test
    public void isCorrectWrite20Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<#E.int>"));
    }

    @Test
    public void isCorrectWrite21Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<#E<java.lang.String>>"));
    }

    @Test
    public void isCorrectWrite22Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<,>"));
    }

    @Test
    public void isCorrectWrite23Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<java.lang;String>"));
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
    public void isCorrectTemplate39Test() {
        assertTrue(!Templates.isCorrectTemplate(ENUM_LIST, new StringMap<StringList>(),null));
    }
    
    @Test
    public void isCorrectTemplate40Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.math.BigInteger"));
        assertTrue(!Templates.isCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate41Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CUST_BIG_INT));
        assertTrue(Templates.isCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate42Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate43Test() {
        assertTrue(!Templates.isCorrectTemplate(CMP_LIST+"<"+MY_EQ_CLASS+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate44Test() {
        assertTrue(Templates.isCorrectTemplate(CMP_LIST+"<"+MY_CMP_CLASS+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate45Test() {
        assertTrue(!Templates.isCorrectTemplate(CMP_LIST+"<"+CMP+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate46Test() {
        assertTrue(!Templates.isCorrectTemplate(CUST_SEC_LIST+"<"+AbEqList.class.getName()+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate47Test() {
        assertTrue(!Templates.isCorrectTemplate(CUST_EQ_LIST+"<"+Cmp.class.getName()+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isCorrectTemplate48Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CMP));
        assertTrue(!Templates.isCorrectTemplate(CUST_EQ_LIST+"<#E>", t_,null));
    }

    @Test
    public void isCorrectTemplate49Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CMP));
        t_.put("F", new StringList("#E"));
        assertTrue(!Templates.isCorrectTemplate(CUST_EQ_LIST+"<#F>", t_,null));
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
    
    @Test
    public void isCorrect46Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~ "+ARR_ITHREE+">");
        m_.setParam(CUST_LIST+"<? ~ "+ARR_ITWO+">");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect47Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<? ~ ["+MyImpl.class.getName()+">");
        m_.setParam(CUST_LIST+"<? ~ "+ARR_ITWO+">");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect1Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.Object");
        assertTrue(Templates.isSimpleCorrect(m_, null));
    }

    @Test
    public void isSimpleCorrect2Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.String");
        m_.setParam("java.lang.Object");
        assertTrue(Templates.isSimpleCorrect(m_, null));
    }

    @Test
    public void isSimpleCorrect3Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.String");
        assertTrue(!Templates.isSimpleCorrect(m_, null));
    }

    @Test
    public void isSimpleCorrect4Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.Object>");
        m_.setParam("java.util.List<java.lang.Object>");
        assertTrue(Templates.isSimpleCorrect(m_, null));
    }

    @Test
    public void isSimpleCorrect5Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.String>");
        m_.setParam("java.util.List<java.lang.Object>");
        assertTrue(!Templates.isSimpleCorrect(m_, null));
    }

    @Test
    public void isSimpleCorrect6Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<java.lang.Object>");
        m_.setParam("java.util.List<java.lang.String>");
        assertTrue(!Templates.isSimpleCorrect(m_, null));
    }

    @Test
    public void isSimpleCorrect7Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect8Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect9Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect10Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect11Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<java.lang.Enum<#E>>");
        m_.setParam(CUST_LIST+"<java.lang.Enum<#E>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect12Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<#E>");
        m_.setParam(CUST_LIST+"<#E>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect13Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ENUM_LIST+"<#F>");
        m_.setParam(CUST_LIST+"<#F>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("F", new StringList("java.lang.Enum<#F>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect14Test() {
        Mapping m_ = new Mapping();
        m_.setArg("java.util.List<#F>");
        m_.setParam("java.util.Collection<#F>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("F", new StringList("java.lang.Enum<#F>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect15Test() {
        Mapping m_ = new Mapping();
        m_.setArg(STRING_LIST);
        m_.setParam(CUST_LIST+"<java.lang.String>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect16Test() {
        Mapping m_ = new Mapping();
        m_.setArg(STRING_LIST);
        m_.setParam(CUST_LIST+"<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect17Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect18Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.io.Serializable");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.util.ArrayList<java.lang.Object>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect20Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam("#S");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }
    @Test
    public void isSimpleCorrect21Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#S");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect22Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<java.lang.String>");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect23Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_STRING);
        m_.setParam(ARR_OBJECT);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect27Test() {
        Mapping m_ = new Mapping();
        m_.setArg(CUST_LIST+"<#U>");
        m_.setParam(CUST_LIST+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("U", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect33Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam(CUST_LIST+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#U"));
        t_.put("U", new StringList(CUST_LIST+"<#S>"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect34Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam(Listable.class.getName()+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#U"));
        t_.put("U", new StringList(CUST_LIST+"<#S>"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }
 
    @Test
    public void isSimpleCorrect35Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam(CUST_LIST+"<#S>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#U"));
        t_.put("U", new StringList(Listable.class.getName()+"<#S>"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect36Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_T);
        m_.setParam(ARR_VAR_S);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect37Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect38Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect39Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_T);
        m_.setParam(ARR_VAR_S);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }
    
    @Test
    public void isSimpleCorrect46Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_ITHREE);
        m_.setParam(ARR_ITWO);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect47Test() {
        Mapping m_ = new Mapping();
        m_.setArg("["+MyImpl.class.getName());
        m_.setParam(ARR_ITWO);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect48Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam(CUST_BIG_INT);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("J", new StringList(CUST_BIG_INT));
        t_.put("I", new StringList("#J"));
        t_.put("H", new StringList("#I"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect49Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam("#J");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("J", new StringList(CUST_BIG_INT));
        t_.put("I", new StringList("#J"));
        t_.put("H", new StringList("#I"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect50Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam(Object.class.getName());
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("H", new StringList());
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrect51Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam("#J");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("J", new StringList(CUST_BIG_INT));
        t_.put("I", new StringList("#J"));
        t_.put("K", new StringList("#J"));
        t_.put("L", new StringList("#K"));
        t_.put("H", new StringList("#L","#I"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrect(m_,null));
    }

    @Test
    public void isSimpleCorrectTemplate1Test() {
        assertTrue(Templates.isSimpleCorrectTemplate(ENUM_LIST+"<"+ENUM+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate2Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST+"<java.lang.String>", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate6Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate7Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate8Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(Templates.isSimpleCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate9Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>",CMP+"<#E>"));
        assertTrue(Templates.isSimpleCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate10Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CMP+"<E>","java.lang.Enum<#E>"));
        assertTrue(Templates.isSimpleCorrectTemplate(ENUM_LIST+"<#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate11Test() {
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<java.lang.Object>", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate12Test() {
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<java.lang.String>", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate13Test() {
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<"+ARR_OBJECT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate14Test() {
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<"+ARR_STRING+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate15Test() {
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<"+ARR_INT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate16Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST+"<"+ARR_OBJECT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate17Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST+"<"+ARR_STRING+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate18Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST+"<"+ARR_INT+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate19Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST+"<"+ARR_ENUM+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate20Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<"+ARR_VAR_E+">", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate21Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST+"<"+ARR_VAR_E+">", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate22Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isSimpleCorrectTemplate(ENUM_MAP+"<"+ENUM+","+CUST_BIG_INT+">", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate23Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(Templates.isSimpleCorrectTemplate(TEMPLATING+"<java.math.BigInteger,"+CUST_BIG_INT+">", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate24Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        assertTrue(!Templates.isSimpleCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",java.math.BigInteger>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate25Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isSimpleCorrectTemplate(TEMPLATING+"<java.math.BigInteger,"+CUST_BIG_INT+">", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate26Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isSimpleCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",java.math.BigInteger>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate27Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<"+ARR_VAR_T+">", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate28Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("F", new StringList("java.lang.Enum<#F>"));
        assertTrue(Templates.isSimpleCorrectTemplate("java.util.List<"+ARR_VAR_F+">", t_,null));
    }
    @Test
    public void isSimpleCorrectTemplate29Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>",CMP+"<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrectTemplate(TEMPLATING_BIS+"<#T>", t_, null));
    }

    @Test
    public void isSimpleCorrectTemplate30Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>",CMP+"<#T>"));
        m_.setMapping(t_);
        assertTrue(Templates.isSimpleCorrectTemplate(ENUM_LIST+"<#T>", t_, null));
    }
    @Test
    public void isSimpleCorrectTemplate31Test() {
        Mapping m_ = new Mapping();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Enum<#T>"));
        m_.setMapping(t_);
        assertTrue(!Templates.isSimpleCorrectTemplate(TEMPLATING_BIS+"<#T>", t_, null));
    }
    
    @Test
    public void isSimpleCorrectTemplate32Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(CMP_LIST+"<"+STRANGE_CMP_LIST+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate33Test() {
        assertTrue(Templates.isSimpleCorrectTemplate(CMP_LIST+"<"+GOOD_CMP_LIST+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate39Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(ENUM_LIST, new StringMap<StringList>(),null));
    }
    
    @Test
    public void isSimpleCorrectTemplate40Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.math.BigInteger"));
        assertTrue(!Templates.isSimpleCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",#E>", t_,null));
    }
    
    @Test
    public void isSimpleCorrectTemplate41Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CUST_BIG_INT));
        assertTrue(Templates.isSimpleCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate42Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isSimpleCorrectTemplate(TEMPLATING+"<"+CUST_BIG_INT+",#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate43Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(CMP_LIST+"<"+MY_EQ_CLASS+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate44Test() {
        assertTrue(Templates.isSimpleCorrectTemplate(CMP_LIST+"<"+MY_CMP_CLASS+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate45Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(CMP_LIST+"<"+CMP+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate46Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(CUST_SEC_LIST+"<"+AbEqList.class.getName()+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate47Test() {
        assertTrue(!Templates.isSimpleCorrectTemplate(CUST_EQ_LIST+"<"+Cmp.class.getName()+">", new StringMap<StringList>(),null));
    }

    @Test
    public void isSimpleCorrectTemplate48Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CMP));
        assertTrue(!Templates.isSimpleCorrectTemplate(CUST_EQ_LIST+"<#E>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate49Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList(CMP));
        t_.put("F", new StringList("#E"));
        assertTrue(!Templates.isSimpleCorrectTemplate(CUST_EQ_LIST+"<#F>", t_,null));
    }

    @Test
    public void isSimpleCorrectTemplate50Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("J", new StringList(CUST_BIG_INT));
        t_.put("I", new StringList("#J"));
        t_.put("H", new StringList("#I"));
        assertTrue(Templates.isSimpleCorrectTemplate(TRANSITIVE_TEMPLATING+"<#J,#I,#H>", t_,null));
    }

    @Test
    public void getClassBounds1Test() {
        EqList<StringList> bounds_;
        bounds_ = Templates.getClassBounds(TEMPLATING+"<java.math.BigInteger,"+CUST_BIG_INT+">", null);
        assertEq(2, bounds_.size());
        assertEq(1, bounds_.get(0).size());
        assertEq(Number.class.getName(), bounds_.get(0).first());
        assertEq(1, bounds_.get(1).size());
        assertEq(Number.class.getName(), bounds_.get(1).first());
    }

    @Test
    public void getClassBounds2Test() {
        EqList<StringList> bounds_;
        bounds_ = Templates.getClassBounds(CustList.class.getName()+"<"+MY_CMP_CLASS+">", null);
        assertEq(1, bounds_.size());
        assertEq(1, bounds_.get(0).size());
        assertEq(Object.class.getName(), bounds_.get(0).first());
    }

    @Test
    public void getClassBounds3Test() {
        EqList<StringList> bounds_;
        bounds_ = Templates.getClassBounds("java.math.BigInteger", null);
        assertEq(0, bounds_.size());
    }

    @Test
    public void getClassBounds4Test() {
        EqList<StringList> bounds_;
        bounds_ = Templates.getClassBounds(CMP, null);
        assertNull(bounds_);
    }

    @Test
    public void getClassLeftMostBounds1Test() {
        StringList bounds_;
        bounds_ = Templates.getClassLeftMostBounds(TEMPLATING, null);
        assertEq(2, bounds_.size());
        assertEq(Number.class.getName(), bounds_.get(0));
        assertEq(Number.class.getName(), bounds_.get(1));
    }

    @Test
    public void getClassLeftMostBounds2Test() {
        StringList bounds_;
        bounds_ = Templates.getClassLeftMostBounds(CustList.class.getName(), null);
        assertEq(1, bounds_.size());
        assertEq(Object.class.getName(), bounds_.get(0));
    }

    @Test
    public void getClassLeftMostBounds3Test() {
        StringList bounds_;
        bounds_ = Templates.getClassLeftMostBounds("java.math.BigInteger", null);
        assertEq(0, bounds_.size());
    }
    
    @Test
    public void getClassLeftMostBounds4Test() {
        StringList bounds_;
        bounds_ = Templates.getClassLeftMostBounds(CMP_LIST, null);
        assertEq(1, bounds_.size());
        assertEq(Cmp.class.getName(), bounds_.get(0));
    }

    @Test
    public void getClassLeftMostBounds5Test() {
        StringList bounds_;
        bounds_ = Templates.getClassLeftMostBounds(TwoBounds.class.getName(), null);
        assertEq(1, bounds_.size());
        assertEq(IOne.class.getName(), bounds_.get(0));
    }
}
