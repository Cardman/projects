package code.expressionlanguage.common;

import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.util.StringList;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class StringExpUtilTest extends ProcessMethodCommon {

    @Test
    public void getAllTypes1Test(){
        assertEq(new StringList("String"), StringExpUtil.getAllTypes("String"));
    }
    @Test
    public void getAllTypes2Test(){
        assertEq(new StringList("Map","String","Rate"), StringExpUtil.getAllTypes("Map<String,Rate>"));
    }
    @Test
    public void getAllTypes3Test(){
        assertEq(new StringList("Map","String","Map<String,Rate>"), StringExpUtil.getAllTypes("Map<String,Map<String,Rate>>"));
    }
    @Test
    public void getAllTypes4Test(){
        assertEq(new StringList("List","Boolean"), StringExpUtil.getAllTypes("List<Boolean>"));
    }
    @Test
    public void getAllTypes5Test(){
        assertEq(new StringList("CustList","BooleanList"), StringExpUtil.getAllTypes("CustList<BooleanList>"));
    }
    @Test
    public void getAllTypes6Test(){
        assertEq(new StringList("Outer..Map"), StringExpUtil.getAllTypes("Outer..Map"));
    }
    @Test
    public void getAllTypes7Test(){
        assertEq(new StringList("..Map"), StringExpUtil.getAllTypes("..Map"));
    }
    @Test
    public void getAllTypes8Test(){
        assertEq(new StringList("Map..Inner","String","Rate"), StringExpUtil.getAllTypes("Map<String,Rate>..Inner"));
    }
    @Test
    public void getAllTypes9Test(){
        assertEq(new StringList("Map..Inner","String","Rate","Boolean","Number"), StringExpUtil.getAllTypes("Map<String,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes10Test(){
        assertEq(new StringList("Map..Inner","String","Rate..Denominator","Boolean","Number"), StringExpUtil.getAllTypes("Map<String,Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes11Test(){
        assertEq(new StringList("Map..Inner","String..Character","Rate","Boolean","Number"), StringExpUtil.getAllTypes("Map<String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes12Test(){
        assertEq(new StringList("Map..Inner","String"), StringExpUtil.getAllTypes("Map<String>..Inner"));
    }
    @Test
    public void getAllTypes13Test(){
        assertEq(new StringList("[String"), StringExpUtil.getAllTypes("[String"));
    }
    @Test
    public void getAllTypes14Test(){
        assertEq(new StringList("Map","[String","Rate"), StringExpUtil.getAllTypes("Map<[String,Rate>"));
    }
    @Test
    public void getAllTypes15Test(){
        assertEq(new StringList("[Map","String","Rate"), StringExpUtil.getAllTypes("[Map<String,Rate>"));
    }
    @Test
    public void getAllTypes16Test(){
        assertEq(new StringList("[Map..Inner","String"), StringExpUtil.getAllTypes("[Map<String>..Inner"));
    }
    @Test
    public void getAllTypes17Test(){
        assertEq(new StringList("Map..Inner","String","[Rate..Denominator","Boolean","Number"), StringExpUtil.getAllTypes("Map<String,[Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes18Test(){
        assertEq(new StringList("Map..Inner","[String..Character","Rate","Boolean","Number"), StringExpUtil.getAllTypes("Map<[String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllTypes19Test(){
        assertEq(new StringList("Map","Map<[String..Character,Rate>","Number"), StringExpUtil.getAllTypes("Map<Map<[String..Character,Rate>,Number>"));
    }

    @Test
    public void getQuickComponentType1Test() {
        ClassArgumentMatching arg_ = new ClassArgumentMatching("$int");
        assertTrue(StringExpUtil.getQuickComponentType(arg_).getNames().isEmpty());
    }
    @Test
    public void getAllSepCommaTypes1Test(){
        assertEq(new StringList("Number","<<","$int"), StringExpUtil.getAllSepCommaTypes("Number,<<,$int"));
    }

    @Test
    public void getAllSepCommaTypes2Test(){
        assertEq(new StringList("Number<Map<$int,$long>>","Number"), StringExpUtil.getAllSepCommaTypes("Number<Map<$int,$long>>,Number"));
    }

    @Test
    public void getAllInnerTypes1Test(){
        assertEq(new StringList("String"), StringExpUtil.getAllInnerTypes("String"));
    }
    @Test
    public void getAllInnerTypes2Test(){
        assertEq(new StringList("Map<String,Rate>"), StringExpUtil.getAllInnerTypes("Map<String,Rate>"));
    }
    @Test
    public void getAllInnerTypes3Test(){
        assertEq(new StringList("Map<String,Map<String,Rate>>"), StringExpUtil.getAllInnerTypes("Map<String,Map<String,Rate>>"));
    }
    @Test
    public void getAllInnerTypes4Test(){
        assertEq(new StringList("List<Boolean>"), StringExpUtil.getAllInnerTypes("List<Boolean>"));
    }
    @Test
    public void getAllInnerTypes5Test(){
        assertEq(new StringList("CustList<BooleanList>"), StringExpUtil.getAllInnerTypes("CustList<BooleanList>"));
    }
    @Test
    public void getAllInnerTypes6Test(){
        assertEq(new StringList("Outer","Map"), StringExpUtil.getAllInnerTypes("Outer..Map"));
    }
    @Test
    public void getAllInnerTypes7Test(){
        assertEq(new StringList("","Map"), StringExpUtil.getAllInnerTypes("..Map"));
    }
    @Test
    public void getAllInnerTypes8Test(){
        assertEq(new StringList("Map<String,Rate>","Inner"), StringExpUtil.getAllInnerTypes("Map<String,Rate>..Inner"));
    }
    @Test
    public void getAllInnerTypes9Test(){
        assertEq(new StringList("Map<String,Rate>","Inner<Boolean,Number>"), StringExpUtil.getAllInnerTypes("Map<String,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes10Test(){
        assertEq(new StringList("Map<String,Rate..Denominator>","Inner<Boolean,Number>"), StringExpUtil.getAllInnerTypes("Map<String,Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes11Test(){
        assertEq(new StringList("Map<String..Character,Rate>","Inner<Boolean,Number>"), StringExpUtil.getAllInnerTypes("Map<String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes12Test(){
        assertEq(new StringList("Map<String>","Inner"), StringExpUtil.getAllInnerTypes("Map<String>..Inner"));
    }
    @Test
    public void getAllInnerTypes13Test(){
        assertEq(new StringList("[String"), StringExpUtil.getAllInnerTypes("[String"));
    }
    @Test
    public void getAllInnerTypes14Test(){
        assertEq(new StringList("Map<[String,Rate>"), StringExpUtil.getAllInnerTypes("Map<[String,Rate>"));
    }
    @Test
    public void getAllInnerTypes15Test(){
        assertEq(new StringList("[Map<String,Rate>"), StringExpUtil.getAllInnerTypes("[Map<String,Rate>"));
    }
    @Test
    public void getAllInnerTypes16Test(){
        assertEq(new StringList("[Map<String>","Inner"), StringExpUtil.getAllInnerTypes("[Map<String>..Inner"));
    }
    @Test
    public void getAllInnerTypes17Test(){
        assertEq(new StringList("Map<String,[Rate..Denominator>","Inner<Boolean,Number>"), StringExpUtil.getAllInnerTypes("Map<String,[Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllInnerTypes18Test(){
        assertEq(new StringList("Map<[String..Character,Rate>","Inner<Boolean,Number>"), StringExpUtil.getAllInnerTypes("Map<[String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes1Test(){
        assertEq(new StringList("String"), StringExpUtil.getAllPartInnerTypes("String"));
    }
    @Test
    public void getAllPartInnerTypes2Test(){
        assertEq(new StringList("Map<String,Rate>"), StringExpUtil.getAllPartInnerTypes("Map<String,Rate>"));
    }
    @Test
    public void getAllPartInnerTypes3Test(){
        assertEq(new StringList("Map<String,Map<String,Rate>>"), StringExpUtil.getAllPartInnerTypes("Map<String,Map<String,Rate>>"));
    }
    @Test
    public void getAllPartInnerTypes4Test(){
        assertEq(new StringList("List<Boolean>"), StringExpUtil.getAllPartInnerTypes("List<Boolean>"));
    }
    @Test
    public void getAllPartInnerTypes5Test(){
        assertEq(new StringList("CustList<BooleanList>"), StringExpUtil.getAllPartInnerTypes("CustList<BooleanList>"));
    }
    @Test
    public void getAllPartInnerTypes6Test(){
        assertEq(new StringList("Outer","..","Map"), StringExpUtil.getAllPartInnerTypes("Outer..Map"));
    }
    @Test
    public void getAllPartInnerTypes7Test(){
        assertEq(new StringList("","..","Map"), StringExpUtil.getAllPartInnerTypes("..Map"));
    }
    @Test
    public void getAllPartInnerTypes8Test(){
        assertEq(new StringList("Map<String,Rate>","..","Inner"), StringExpUtil.getAllPartInnerTypes("Map<String,Rate>..Inner"));
    }
    @Test
    public void getAllPartInnerTypes9Test(){
        assertEq(new StringList("Map<String,Rate>","..","Inner<Boolean,Number>"), StringExpUtil.getAllPartInnerTypes("Map<String,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes10Test(){
        assertEq(new StringList("Map<String,Rate..Denominator>","..","Inner<Boolean,Number>"), StringExpUtil.getAllPartInnerTypes("Map<String,Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes11Test(){
        assertEq(new StringList("Map<String..Character,Rate>","..","Inner<Boolean,Number>"), StringExpUtil.getAllPartInnerTypes("Map<String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes12Test(){
        assertEq(new StringList("Map<String>","..","Inner"), StringExpUtil.getAllPartInnerTypes("Map<String>..Inner"));
    }
    @Test
    public void getAllPartInnerTypes13Test(){
        assertEq(new StringList("[String"), StringExpUtil.getAllPartInnerTypes("[String"));
    }
    @Test
    public void getAllPartInnerTypes14Test(){
        assertEq(new StringList("Map<[String,Rate>"), StringExpUtil.getAllPartInnerTypes("Map<[String,Rate>"));
    }
    @Test
    public void getAllPartInnerTypes15Test(){
        assertEq(new StringList("[Map<String,Rate>"), StringExpUtil.getAllPartInnerTypes("[Map<String,Rate>"));
    }
    @Test
    public void getAllPartInnerTypes16Test(){
        assertEq(new StringList("[Map<String>","..","Inner"), StringExpUtil.getAllPartInnerTypes("[Map<String>..Inner"));
    }
    @Test
    public void getAllPartInnerTypes17Test(){
        assertEq(new StringList("Map<String,[Rate..Denominator>","..","Inner<Boolean,Number>"), StringExpUtil.getAllPartInnerTypes("Map<String,[Rate..Denominator>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes18Test(){
        assertEq(new StringList("Map<[String..Character,Rate>","..","Inner<Boolean,Number>"), StringExpUtil.getAllPartInnerTypes("Map<[String..Character,Rate>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes19Test(){
        assertEq(new StringList("Map<Rate<Int>>","..","Inner<Boolean,Number>"), StringExpUtil.getAllPartInnerTypes("Map<Rate<Int>>..Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes20Test(){
        assertEq(new StringList("Map<Rate<Int>>","-","Inner<Boolean,Number>"), StringExpUtil.getAllPartInnerTypes("Map<Rate<Int>>-Inner<Boolean,Number>"));
    }
    @Test
    public void getAllPartInnerTypes21Test(){
        assertEq(new StringList("Map<Rate<Int>>","..","Inner<Boolean-Number>"), StringExpUtil.getAllPartInnerTypes("Map<Rate<Int>>..Inner<Boolean-Number>"));
    }

}
