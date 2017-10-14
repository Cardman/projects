package code.expressionlanguage;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.classes.CmpList;
import code.expressionlanguage.classes.CustBigInt;
import code.expressionlanguage.classes.CustEqList;
import code.expressionlanguage.classes.CustSecEqList;
import code.expressionlanguage.classes.EnumNumber;
import code.expressionlanguage.classes.GoodCmp;
import code.expressionlanguage.classes.IThree;
import code.expressionlanguage.classes.ITwo;
import code.expressionlanguage.classes.MyCmpClass;
import code.expressionlanguage.classes.MyEqClass;
import code.expressionlanguage.classes.MyImpl;
import code.expressionlanguage.classes.StrangeCmp;
import code.expressionlanguage.classes.Templating;
import code.expressionlanguage.classes.TemplatingBis;
import code.expressionlanguage.classes.TransitiveTemplating;
import code.expressionlanguage.methods.Classes;
import code.util.AbEqList;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.IdList;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Cmp;
import code.util.ints.Listable;

@SuppressWarnings("static-method")
public class TemplatesTest {
    private static final String PUBLIC_ACCESS = "PUBLIC";
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
    private static final String ID_LIST = IdList.class.getName();
    private static final String ENUM_LIST = EnumList.class.getName();
    private static final String ENUM_MAP = EnumMap.class.getName();
    private static final String CMP = Cmp.class.getName();
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
    public void format9Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E",Templates.format(first_, second_, classes_));
    }

    @Test
    public void format10Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        String first_ = "pkg.Ex<java.lang.String>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.format(first_, second_, classes_));
    }

    @Test
    public void format11Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T,#U&gt;'/>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#U";
        assertEq("java.lang.Object",Templates.format(first_, second_, classes_));
    }

    @Test
    public void format12Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T,#U&gt;'/>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String",Templates.format(first_, second_, classes_));
    }

    @Test
    public void format13Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T,#U&gt;'/>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        String first_ = CUST_LIST+"<java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.Object",Templates.format(first_, second_, classes_));
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
    public void getTypesByBases6Test() {
        StringList t_ = Templates.getTypesByBases("java.lang.String", "java.lang.String", null);
        assertEq(0, t_.size());
    }

    @Test
    public void getTypesByBases7Test() {
        StringList t_ = Templates.getTypesByBases(Listable.class.getName(), Listable.class.getName(), null);
        assertEq(0, t_.size());
    }

    @Test
    public void getTypesByBases8Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringList t_ = Templates.getTypesByBases("pkg.Ex", "pkg.Ex", classes_);
        assertEq(0, t_.size());
    }

    @Test
    public void getTypesByBases9Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringList t_ = Templates.getTypesByBases("pkg.ExTwo", "pkg.Ex", classes_);
        assertEq(0, t_.size());
    }

    @Test
    public void getTypesByBases10Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringList t_ = Templates.getTypesByBases("pkg.Ex", "pkg.ExTwo", classes_);
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
    public void isCorrectWrite24Test() {
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<<java.lang.String>>"));
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
        m_.setArg("#E");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect8Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect9Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect10Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
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
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect18Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.io.Serializable");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.util.ArrayList<java.lang.Object>"));
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
        m_.setArg(ARR_STRING);
        m_.setParam(ARR_OBJECT);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
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
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect39Test() {
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
    public void isCorrect46Test() {
        Mapping m_ = new Mapping();
        m_.setArg(ARR_ITHREE);
        m_.setParam(ARR_ITWO);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect47Test() {
        Mapping m_ = new Mapping();
        m_.setArg("["+MyImpl.class.getName());
        m_.setParam(ARR_ITWO);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect48Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam(CUST_BIG_INT);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("J", new StringList(CUST_BIG_INT));
        t_.put("I", new StringList("#J"));
        t_.put("H", new StringList("#I"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect49Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam("#J");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("J", new StringList(CUST_BIG_INT));
        t_.put("I", new StringList("#J"));
        t_.put("H", new StringList("#I"));
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect50Test() {
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam(Object.class.getName());
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("H", new StringList());
        m_.setMapping(t_);
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect51Test() {
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
        assertTrue(Templates.isCorrect(m_,null));
    }

    @Test
    public void isCorrect52Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect54Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Number");
        m_.setParam("pkg.Ex");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect55Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect56Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect57Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect58Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect59Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect60Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect61Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<enum access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect62Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<enum access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect63Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#T>");
        m_.setParam("pkg.Ex<#T>");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect64Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<#T>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect65Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#T>");
        m_.setParam("pkg.Ex<#T>");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect66Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<#T>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect67Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("S", new StringList("java.lang.Object"));
        m_.setArg(ID_LIST+"<#S>");
        m_.setParam(CUST_LIST+"<#S>");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect68Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("S", new StringList("java.lang.Object"));
        m_.setArg(CUST_LIST+"<#S>");
        m_.setParam(ID_LIST+"<#S>");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect69Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F:[java.lang.Object&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E:[java.lang.Object&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("pkg.ExTwo<#T>");
        m_.setParam("pkg.Ex<#T>");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect70Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F:[java.lang.Object&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E:[java.lang.Object&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("pkg.Ex<#T>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect72Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F:[java.lang.Object&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E:[java.lang.Object&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.getMapping().put("S", new StringList("[[java.lang.Object"));
        m_.setArg("pkg.Ex<#S>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect73Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("[[pkg.ExTwo<#T>");
        m_.setParam("[pkg.Ex<#T>");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect74Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;#E&gt;' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("[pkg.Ex<#T>");
        m_.setParam("[[pkg.ExTwo<#T>");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect75Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;java.lang.Object&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex<java.lang.Object>");
        assertTrue(Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect76Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#F&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Ex&lt;java.lang.Object&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<java.lang.Object>");
        m_.setParam("pkg.ExTwo");
        assertTrue(!Templates.isCorrect(m_, classes_));
    }

    @Test
    public void isCorrect77Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.getClasses();
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
    public void isCorrectTemplate50Test() {
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("J", new StringList(CUST_BIG_INT));
        t_.put("I", new StringList("#J"));
        t_.put("H", new StringList("#I"));
        assertTrue(Templates.isCorrectTemplate(TRANSITIVE_TEMPLATING+"<#J,#I,#H>", t_,null));
    }

    @Test
    public void isCorrectTemplate51Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("pkg.Ex", t_,classes_));
    }

    @Test
    public void isCorrectTemplate52Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<enum access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("pkg.Ex", t_,classes_));
    }

    @Test
    public void isCorrectTemplate53Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("pkg.Ex", t_,classes_));
    }

    @Test
    public void isCorrectTemplate54Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex", t_,classes_));
    }

    @Test
    public void isCorrectTemplate55Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<enum access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex", t_,classes_));
    }

    @Test
    public void isCorrectTemplate56Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex", t_,classes_));
    }

    @Test
    public void isCorrectTemplate57Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("pkg.Ex<java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate58Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("pkg.Ex<java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate59Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate60Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate61Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate62Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate63Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate64Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate65Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate66Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate67Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate68Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate69Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<[java.lang.Integer>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate70Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<[java.lang.Integer>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate71Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<[java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate72Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[java.lang.Number&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<[java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate73Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate74Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate75Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate76Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<#E>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate77Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<pkg.ExTwo>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate78Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<pkg.ExTwo>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate79Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<[pkg.ExTwo>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate80Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExThree&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplate("[pkg.Ex<[pkg.ExTwo>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate81Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<[pkg.ExThree>", t_,classes_));
    }

    @Test
    public void isCorrectTemplate82Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplate("[pkg.Ex<[pkg.ExThree>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("java.lang.Object", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("$int", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[java.lang.Object", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll4Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll("[$int", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll5Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll(CUST_LIST+"<java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll6Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll(CUST_LIST+"<[java.lang.Object>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll7Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll(CUST_LIST+"<"+CUST_LIST+"<[java.lang.Object>>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll8Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll(ENUM_LIST+"<"+ENUM_LIST+"<java.lang.Object>>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll9Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll(CUST_LIST+"<"+ENUM_LIST+"<java.lang.Object>>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll10Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll(CUST_LIST+"<"+CUST_LIST+"<"+CUST_LIST+">>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll11Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(Templates.isCorrectTemplateAll(CUST_LIST+"<"+CUST_LIST+"<pkg.ExThree>>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll12Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!Templates.isCorrectTemplateAll(CUST_LIST+"<"+CUST_LIST+"<pkg.Ex>>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll13Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(Templates.isCorrectTemplateAll(CUST_LIST+"<"+CUST_LIST+"<#E>>", t_,classes_));
    }

    @Test
    public void isCorrectTemplateAll14Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#T:[pkg.ExTwo&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExThree'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!Templates.isCorrectTemplateAll(CUST_LIST+"<"+CUST_LIST+"<#F>>", t_,classes_));
    }

    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(_files, cont_);
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
}
