package code.expressionlanguage.common;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.methods.ProcessMethodCommon;
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
        AnaClassArgumentMatching arg_ = new AnaClassArgumentMatching("$int");
        assertTrue(AnaTypeUtil.getQuickComponentType(arg_).getNames().isEmpty());
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
    @Test
    public void nextCharIsTest(){
        assertTrue(!StringExpUtil.nextCharIs("",-1,0,' '));
    }
    @Test
    public void nextPrintCharTest(){
        assertEq(-1,StringExpUtil.nextPrintChar(-1,0,""));
    }
    @Test
    public void tryToExtract1Test(){
        assertEq("",StringExpUtil.tryToExtract("",'(',')').getSecond());
    }
    @Test
    public void tryToExtract2Test(){
        assertEq("hello",StringExpUtil.tryToExtract("(hello)",'(',')').getSecond());
    }
    @Test
    public void tryToExtract3Test(){
        assertEq("",StringExpUtil.tryToExtract(")hello(",'(',')').getSecond());
    }
    @Test
    public void tryToExtract4Test(){
        assertEq("",StringExpUtil.tryToExtract("(hello",'(',')').getSecond());
    }
    @Test
    public void tryToExtract5Test(){
        assertEq("",StringExpUtil.tryToExtract("hello",'(',')').getSecond());
    }
    @Test
    public void tryToExtract6Test(){
        assertEq("hello",StringExpUtil.tryToExtract("hello()",'(',')').getFirst());
    }
    @Test
    public void toGeneHex1Test(){
        assertEq("0",StringExpUtil.toGeneHex(0));
    }
    @Test
    public void toGeneHex2Test(){
        assertEq("1",StringExpUtil.toGeneHex(1));
    }
    @Test
    public void toGeneHex3Test(){
        assertEq("9",StringExpUtil.toGeneHex(9));
    }
    @Test
    public void toGeneHex4Test(){
        assertEq("a",StringExpUtil.toGeneHex(10));
    }
    @Test
    public void toGeneHex5Test(){
        assertEq("f",StringExpUtil.toGeneHex(15));
    }
    @Test
    public void toGeneHex6Test(){
        assertEq("10",StringExpUtil.toGeneHex(16));
    }
    @Test
    public void toGeneHex7Test(){
        assertEq("11",StringExpUtil.toGeneHex(17));
    }
    @Test
    public void toGeneHex8Test(){
        assertEq("1e",StringExpUtil.toGeneHex(30));
    }
    @Test
    public void toGeneHex9Test(){
        assertEq("7f",StringExpUtil.toGeneHex(127));
    }
    @Test
    public void toGeneHex10Test(){
        assertEq("80",StringExpUtil.toGeneHex(128));
    }
    @Test
    public void toGeneHex11Test(){
        assertEq("ff",StringExpUtil.toGeneHex(255));
    }
    @Test
    public void toGeneHex12Test(){
        assertEq("100",StringExpUtil.toGeneHex(256));
    }
    @Test
    public void toGeneHex13Test(){
        assertEq("123",StringExpUtil.toGeneHex(291));
    }
    @Test
    public void toGeneHex14Test(){
        assertEq("ffffffff",StringExpUtil.toGeneHex(-1));
    }
    @Test
    public void toGeneHex15Test(){
        assertEq("fffffffe",StringExpUtil.toGeneHex(-2));
    }
    @Test
    public void toGeneHex16Test(){
        assertEq("fffffffa",StringExpUtil.toGeneHex(-6));
    }
    @Test
    public void toGeneHex17Test(){
        assertEq("fffffff9",StringExpUtil.toGeneHex(-7));
    }
    @Test
    public void toGeneHex18Test(){
        assertEq("fffffff0",StringExpUtil.toGeneHex(-16));
    }
    @Test
    public void toGeneHex19Test(){
        assertEq("ffffffef",StringExpUtil.toGeneHex(-17));
    }
    @Test
    public void toGeneHex20Test(){
        assertEq("ffffffea",StringExpUtil.toGeneHex(-22));
    }
    @Test
    public void toGeneHex21Test(){
        assertEq("ffffffe9",StringExpUtil.toGeneHex(-23));
    }
    @Test
    public void toGeneHex22Test(){
        assertEq("ffffffe5",StringExpUtil.toGeneHex(-27));
    }
    @Test
    public void toGeneHex23Test(){
        assertEq("ffffffe0",StringExpUtil.toGeneHex(-32));
    }
    @Test
    public void toGeneHex24Test(){
        assertEq("fffffedc",StringExpUtil.toGeneHex(-292));
    }
}
