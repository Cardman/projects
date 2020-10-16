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
    @Test
    public void toGeneHex25Test(){
        assertEq("80000000",StringExpUtil.toGeneHex(Integer.MIN_VALUE));
    }
    @Test
    public void toGeneOct1Test(){
        assertEq("0",StringExpUtil.toGeneOct(0));
    }
    @Test
    public void toGeneOct2Test(){
        assertEq("1",StringExpUtil.toGeneOct(1));
    }
    @Test
    public void toGeneOct3Test(){
        assertEq("11",StringExpUtil.toGeneOct(9));
    }
    @Test
    public void toGeneOct4Test(){
        assertEq("12",StringExpUtil.toGeneOct(10));
    }
    @Test
    public void toGeneOct5Test(){
        assertEq("7",StringExpUtil.toGeneOct(7));
    }
    @Test
    public void toGeneOct6Test(){
        assertEq("10",StringExpUtil.toGeneOct(8));
    }
    @Test
    public void toGeneOct7Test(){
        assertEq("21",StringExpUtil.toGeneOct(17));
    }
    @Test
    public void toGeneOct8Test(){
        assertEq("36",StringExpUtil.toGeneOct(30));
    }
    @Test
    public void toGeneOct9Test(){
        assertEq("177",StringExpUtil.toGeneOct(127));
    }
    @Test
    public void toGeneOct10Test(){
        assertEq("200",StringExpUtil.toGeneOct(128));
    }
    @Test
    public void toGeneOct11Test(){
        assertEq("377",StringExpUtil.toGeneOct(255));
    }
    @Test
    public void toGeneOct12Test(){
        assertEq("400",StringExpUtil.toGeneOct(256));
    }
    @Test
    public void toGeneOct13Test(){
        assertEq("443",StringExpUtil.toGeneOct(291));
    }
    @Test
    public void toGeneOct14Test(){
        assertEq("37777777777",StringExpUtil.toGeneOct(-1));
    }
    @Test
    public void toGeneOct15Test(){
        assertEq("37777777776",StringExpUtil.toGeneOct(-2));
    }
    @Test
    public void toGeneOct16Test(){
        assertEq("37777777772",StringExpUtil.toGeneOct(-6));
    }
    @Test
    public void toGeneOct17Test(){
        assertEq("37777777771",StringExpUtil.toGeneOct(-7));
    }
    @Test
    public void toGeneOct18Test(){
        assertEq("37777777760",StringExpUtil.toGeneOct(-16));
    }
    @Test
    public void toGeneOct19Test(){
        assertEq("37777777757",StringExpUtil.toGeneOct(-17));
    }
    @Test
    public void toGeneOct20Test(){
        assertEq("37777777752",StringExpUtil.toGeneOct(-22));
    }
    @Test
    public void toGeneOct21Test(){
        assertEq("37777777751",StringExpUtil.toGeneOct(-23));
    }
    @Test
    public void toGeneOct22Test(){
        assertEq("37777777745",StringExpUtil.toGeneOct(-27));
    }
    @Test
    public void toGeneOct23Test(){
        assertEq("37777777740",StringExpUtil.toGeneOct(-32));
    }
    @Test
    public void toGeneOct24Test(){
        assertEq("37777777334",StringExpUtil.toGeneOct(-292));
    }
    @Test
    public void toGeneOct25Test(){
        assertEq("20000000000",StringExpUtil.toGeneOct(Integer.MIN_VALUE));
    }
    @Test
    public void toGeneOct26Test(){
        assertEq("20000000001",StringExpUtil.toGeneOct(Integer.MIN_VALUE+1));
    }
    @Test
    public void toGeneBin1Test(){
        assertEq("0",StringExpUtil.toGeneBin(0));
    }
    @Test
    public void toGeneBin2Test(){
        assertEq("1",StringExpUtil.toGeneBin(1));
    }
    @Test
    public void toGeneBin3Test(){
        assertEq("10",StringExpUtil.toGeneBin(2));
    }
    @Test
    public void toGeneBin4Test(){
        assertEq("11",StringExpUtil.toGeneBin(3));
    }
    @Test
    public void toGeneBin5Test(){
        assertEq("100",StringExpUtil.toGeneBin(4));
    }
    @Test
    public void toGeneBin6Test(){
        assertEq("11111111111111111111111111111111",StringExpUtil.toGeneBin(-1));
    }
    @Test
    public void toGeneBin7Test(){
        assertEq("11111111111111111111111111111110",StringExpUtil.toGeneBin(-2));
    }
    @Test
    public void toGeneBin8Test(){
        assertEq("11111111111111111111111111111101",StringExpUtil.toGeneBin(-3));
    }
    @Test
    public void toGeneBin9Test(){
        assertEq("11111111111111111111111111111100",StringExpUtil.toGeneBin(-4));
    }
    @Test
    public void toGeneBin10Test(){
        assertEq("10000000000000000000000000000000",StringExpUtil.toGeneBin(Integer.MIN_VALUE));
    }
    @Test
    public void toLongGeneHex1Test(){
        assertEq("0",StringExpUtil.toLongGeneHex(0));
    }
    @Test
    public void toLongGeneHex2Test(){
        assertEq("1",StringExpUtil.toLongGeneHex(1));
    }
    @Test
    public void toLongGeneHex3Test(){
        assertEq("9",StringExpUtil.toLongGeneHex(9));
    }
    @Test
    public void toLongGeneHex4Test(){
        assertEq("a",StringExpUtil.toLongGeneHex(10));
    }
    @Test
    public void toLongGeneHex5Test(){
        assertEq("f",StringExpUtil.toLongGeneHex(15));
    }
    @Test
    public void toLongGeneHex6Test(){
        assertEq("10",StringExpUtil.toLongGeneHex(16));
    }
    @Test
    public void toLongGeneHex7Test(){
        assertEq("11",StringExpUtil.toLongGeneHex(17));
    }
    @Test
    public void toLongGeneHex8Test(){
        assertEq("1e",StringExpUtil.toLongGeneHex(30));
    }
    @Test
    public void toLongGeneHex9Test(){
        assertEq("7f",StringExpUtil.toLongGeneHex(127));
    }
    @Test
    public void toLongGeneHex10Test(){
        assertEq("80",StringExpUtil.toLongGeneHex(128));
    }
    @Test
    public void toLongGeneHex11Test(){
        assertEq("ff",StringExpUtil.toLongGeneHex(255));
    }
    @Test
    public void toLongGeneHex12Test(){
        assertEq("100",StringExpUtil.toLongGeneHex(256));
    }
    @Test
    public void toLongGeneHex13Test(){
        assertEq("123",StringExpUtil.toLongGeneHex(291));
    }
    @Test
    public void toLongGeneHex14Test(){
        assertEq("ffffffffffffffff",StringExpUtil.toLongGeneHex(-1));
    }
    @Test
    public void toLongGeneHex15Test(){
        assertEq("fffffffffffffffe",StringExpUtil.toLongGeneHex(-2));
    }
    @Test
    public void toLongGeneHex16Test(){
        assertEq("fffffffffffffffa",StringExpUtil.toLongGeneHex(-6));
    }
    @Test
    public void toLongGeneHex17Test(){
        assertEq("fffffffffffffff9",StringExpUtil.toLongGeneHex(-7));
    }
    @Test
    public void toLongGeneHex18Test(){
        assertEq("fffffffffffffff0",StringExpUtil.toLongGeneHex(-16));
    }
    @Test
    public void toLongGeneHex19Test(){
        assertEq("ffffffffffffffef",StringExpUtil.toLongGeneHex(-17));
    }
    @Test
    public void toLongGeneHex20Test(){
        assertEq("ffffffffffffffea",StringExpUtil.toLongGeneHex(-22));
    }
    @Test
    public void toLongGeneHex21Test(){
        assertEq("ffffffffffffffe9",StringExpUtil.toLongGeneHex(-23));
    }
    @Test
    public void toLongGeneHex22Test(){
        assertEq("ffffffffffffffe5",StringExpUtil.toLongGeneHex(-27));
    }
    @Test
    public void toLongGeneHex23Test(){
        assertEq("ffffffffffffffe0",StringExpUtil.toLongGeneHex(-32));
    }
    @Test
    public void toLongGeneHex24Test(){
        assertEq("fffffffffffffedc",StringExpUtil.toLongGeneHex(-292));
    }
    @Test
    public void toLongGeneHex25Test(){
        assertEq("ffffffff80000000",StringExpUtil.toLongGeneHex(Integer.MIN_VALUE));
    }
    @Test
    public void toLongGeneHex26Test(){
        assertEq("8000000000000000",StringExpUtil.toLongGeneHex(Long.MIN_VALUE));
    }
    @Test
    public void toLongGeneOct1Test(){
        assertEq("0",StringExpUtil.toLongGeneOct(0));
    }
    @Test
    public void toLongGeneOct2Test(){
        assertEq("1",StringExpUtil.toLongGeneOct(1));
    }
    @Test
    public void toLongGeneOct3Test(){
        assertEq("11",StringExpUtil.toLongGeneOct(9));
    }
    @Test
    public void toLongGeneOct4Test(){
        assertEq("12",StringExpUtil.toLongGeneOct(10));
    }
    @Test
    public void toLongGeneOct5Test(){
        assertEq("7",StringExpUtil.toLongGeneOct(7));
    }
    @Test
    public void toLongGeneOct6Test(){
        assertEq("10",StringExpUtil.toLongGeneOct(8));
    }
    @Test
    public void toLongGeneOct7Test(){
        assertEq("21",StringExpUtil.toLongGeneOct(17));
    }
    @Test
    public void toLongGeneOct8Test(){
        assertEq("36",StringExpUtil.toLongGeneOct(30));
    }
    @Test
    public void toLongGeneOct9Test(){
        assertEq("177",StringExpUtil.toLongGeneOct(127));
    }
    @Test
    public void toLongGeneOct10Test(){
        assertEq("200",StringExpUtil.toLongGeneOct(128));
    }
    @Test
    public void toLongGeneOct11Test(){
        assertEq("377",StringExpUtil.toLongGeneOct(255));
    }
    @Test
    public void toLongGeneOct12Test(){
        assertEq("400",StringExpUtil.toLongGeneOct(256));
    }
    @Test
    public void toLongGeneOct13Test(){
        assertEq("443",StringExpUtil.toLongGeneOct(291));
    }
    @Test
    public void toLongGeneOct14Test(){
        assertEq("1777777777777777777777",StringExpUtil.toLongGeneOct(-1));
    }
    @Test
    public void toLongGeneOct15Test(){
        assertEq("1777777777777777777776",StringExpUtil.toLongGeneOct(-2));
    }
    @Test
    public void toLongGeneOct16Test(){
        assertEq("1777777777777777777772",StringExpUtil.toLongGeneOct(-6));
    }
    @Test
    public void toLongGeneOct17Test(){
        assertEq("1777777777777777777771",StringExpUtil.toLongGeneOct(-7));
    }
    @Test
    public void toLongGeneOct18Test(){
        assertEq("1777777777777777777760",StringExpUtil.toLongGeneOct(-16));
    }
    @Test
    public void toLongGeneOct19Test(){
        assertEq("1777777777777777777757",StringExpUtil.toLongGeneOct(-17));
    }
    @Test
    public void toLongGeneOct20Test(){
        assertEq("1777777777777777777752",StringExpUtil.toLongGeneOct(-22));
    }
    @Test
    public void toLongGeneOct21Test(){
        assertEq("1777777777777777777751",StringExpUtil.toLongGeneOct(-23));
    }
    @Test
    public void toLongGeneOct22Test(){
        assertEq("1777777777777777777745",StringExpUtil.toLongGeneOct(-27));
    }
    @Test
    public void toLongGeneOct23Test(){
        assertEq("1777777777777777777740",StringExpUtil.toLongGeneOct(-32));
    }
    @Test
    public void toLongGeneOct24Test(){
        assertEq("1777777777777777777334",StringExpUtil.toLongGeneOct(-292));
    }
    @Test
    public void toLongGeneOct25Test(){
        assertEq("1777777777760000000000",StringExpUtil.toLongGeneOct(Integer.MIN_VALUE));
    }
    @Test
    public void toLongGeneOct26Test(){
        assertEq("1777777777760000000001",StringExpUtil.toLongGeneOct(Integer.MIN_VALUE+1));
    }
    @Test
    public void toLongGeneOct27Test(){
        assertEq("1000000000000000000000",StringExpUtil.toLongGeneOct(Long.MIN_VALUE));
    }
    @Test
    public void toLongGeneBin1Test(){
        assertEq("0",StringExpUtil.toLongGeneBin(0));
    }
    @Test
    public void toLongGeneBin2Test(){
        assertEq("1",StringExpUtil.toLongGeneBin(1));
    }
    @Test
    public void toLongGeneBin3Test(){
        assertEq("10",StringExpUtil.toLongGeneBin(2));
    }
    @Test
    public void toLongGeneBin4Test(){
        assertEq("11",StringExpUtil.toLongGeneBin(3));
    }
    @Test
    public void toLongGeneBin5Test(){
        assertEq("100",StringExpUtil.toLongGeneBin(4));
    }
    @Test
    public void toLongGeneBin6Test(){
        assertEq("1111111111111111111111111111111111111111111111111111111111111111",StringExpUtil.toLongGeneBin(-1));
    }
    @Test
    public void toLongGeneBin7Test(){
        assertEq("1111111111111111111111111111111111111111111111111111111111111110",StringExpUtil.toLongGeneBin(-2));
    }
    @Test
    public void toLongGeneBin8Test(){
        assertEq("1111111111111111111111111111111111111111111111111111111111111101",StringExpUtil.toLongGeneBin(-3));
    }
    @Test
    public void toLongGeneBin9Test(){
        assertEq("1111111111111111111111111111111111111111111111111111111111111100",StringExpUtil.toLongGeneBin(-4));
    }
    @Test
    public void toLongGeneBin10Test(){
        assertEq("1111111111111111111111111111111110000000000000000000000000000000",StringExpUtil.toLongGeneBin(Integer.MIN_VALUE));
    }
    @Test
    public void toLongGeneBin11Test(){
        assertEq("1000000000000000000000000000000000000000000000000000000000000000",StringExpUtil.toLongGeneBin(Long.MIN_VALUE));
    }
    @Test
    public void toLongRadix1Test(){
        assertEq("0",StringExpUtil.toLongRadix(0,10));
    }
    @Test
    public void toLongRadix2Test(){
        assertEq("1",StringExpUtil.toLongRadix(1,10));
    }
    @Test
    public void toLongRadix3Test(){
        assertEq("-1",StringExpUtil.toLongRadix(-1,10));
    }
    @Test
    public void toLongRadix4Test(){
        assertEq("21",StringExpUtil.toLongRadix(33,16));
    }
    @Test
    public void toLongRadix5Test(){
        assertEq("-21",StringExpUtil.toLongRadix(-33,16));
    }
    @Test
    public void toLongRadix6Test(){
        assertEq("2",StringExpUtil.toLongRadix(2,1));
    }
    @Test
    public void toLongRadix7Test(){
        assertEq("10",StringExpUtil.toLongRadix(2,2));
    }
    @Test
    public void toLongRadix8Test(){
        assertEq("z",StringExpUtil.toLongRadix(35,36));
    }
    @Test
    public void toLongRadix9Test(){
        assertEq("10",StringExpUtil.toLongRadix(36,36));
    }
    @Test
    public void toLongRadix10Test(){
        assertEq("36",StringExpUtil.toLongRadix(36,37));
    }
    @Test
    public void toLongRadix11Test(){
        assertEq("-1000000000000000000000000000000000000000000000000000000000000000",StringExpUtil.toLongRadix(Long.MIN_VALUE,2));
    }
    @Test
    public void toLongRadix12Test(){
        assertEq("-2021110011022210012102010021220101220222",StringExpUtil.toLongRadix(Long.MIN_VALUE,3));
    }
    @Test
    public void toLongRadix13Test(){
        assertEq("-20000000000000000000000000000000",StringExpUtil.toLongRadix(Long.MIN_VALUE,4));
    }
    @Test
    public void toLongRadix14Test(){
        assertEq("-1104332401304422434310311213",StringExpUtil.toLongRadix(Long.MIN_VALUE,5));
    }
    @Test
    public void toLongRadix15Test(){
        assertEq("-1540241003031030222122212",StringExpUtil.toLongRadix(Long.MIN_VALUE,6));
    }
    @Test
    public void toLongRadix16Test(){
        assertEq("-22341010611245052052301",StringExpUtil.toLongRadix(Long.MIN_VALUE,7));
    }
    @Test
    public void toLongRadix17Test(){
        assertEq("-1000000000000000000000",StringExpUtil.toLongRadix(Long.MIN_VALUE,8));
    }
    @Test
    public void toLongRadix18Test(){
        assertEq("-67404283172107811828",StringExpUtil.toLongRadix(Long.MIN_VALUE,9));
    }
    @Test
    public void toLongRadix19Test(){
        assertEq("-9223372036854775808",StringExpUtil.toLongRadix(Long.MIN_VALUE,10));
    }
    @Test
    public void toLongRadix20Test(){
        assertEq("-1728002635214590698",StringExpUtil.toLongRadix(Long.MIN_VALUE,11));
    }
    @Test
    public void toLongRadix21Test(){
        assertEq("-41a792678515120368",StringExpUtil.toLongRadix(Long.MIN_VALUE,12));
    }
    @Test
    public void toLongRadix22Test(){
        assertEq("-10b269549075433c38",StringExpUtil.toLongRadix(Long.MIN_VALUE,13));
    }
    @Test
    public void toLongRadix23Test(){
        assertEq("-4340724c6c71dc7a8",StringExpUtil.toLongRadix(Long.MIN_VALUE,14));
    }
    @Test
    public void toLongRadix24Test(){
        assertEq("-160e2ad3246366808",StringExpUtil.toLongRadix(Long.MIN_VALUE,15));
    }
    @Test
    public void toLongRadix25Test(){
        assertEq("-8000000000000000",StringExpUtil.toLongRadix(Long.MIN_VALUE,16));
    }
    @Test
    public void toLongRadix26Test(){
        assertEq("-33d3d8307b214009",StringExpUtil.toLongRadix(Long.MIN_VALUE,17));
    }
    @Test
    public void toLongRadix27Test(){
        assertEq("-16agh595df825fa8",StringExpUtil.toLongRadix(Long.MIN_VALUE,18));
    }
    @Test
    public void toLongRadix28Test(){
        assertEq("-ba643dci0ffeehi",StringExpUtil.toLongRadix(Long.MIN_VALUE,19));
    }
    @Test
    public void toLongRadix29Test(){
        assertEq("-5cbfjia3fh26ja8",StringExpUtil.toLongRadix(Long.MIN_VALUE,20));
    }
    @Test
    public void toLongRadix30Test(){
        assertEq("-2heiciiie82dh98",StringExpUtil.toLongRadix(Long.MIN_VALUE,21));
    }
    @Test
    public void toLongRadix31Test(){
        assertEq("-1adaibb21dckfa8",StringExpUtil.toLongRadix(Long.MIN_VALUE,22));
    }
    @Test
    public void toLongRadix32Test(){
        assertEq("-i6k448cf4192c3",StringExpUtil.toLongRadix(Long.MIN_VALUE,23));
    }
    @Test
    public void toLongRadix33Test(){
        assertEq("-acd772jnc9l0l8",StringExpUtil.toLongRadix(Long.MIN_VALUE,24));
    }
    @Test
    public void toLongRadix34Test(){
        assertEq("-64ie1focnn5g78",StringExpUtil.toLongRadix(Long.MIN_VALUE,25));
    }
    @Test
    public void toLongRadix35Test(){
        assertEq("-3igoecjbmca688",StringExpUtil.toLongRadix(Long.MIN_VALUE,26));
    }
    @Test
    public void toLongRadix36Test(){
        assertEq("-27c48l5b37oaoq",StringExpUtil.toLongRadix(Long.MIN_VALUE,27));
    }
    @Test
    public void toLongRadix37Test(){
        assertEq("-1bk39f3ah3dmq8",StringExpUtil.toLongRadix(Long.MIN_VALUE,28));
    }
    @Test
    public void toLongRadix38Test(){
        assertEq("-q1se8f0m04isc",StringExpUtil.toLongRadix(Long.MIN_VALUE,29));
    }
    @Test
    public void toLongRadix39Test(){
        assertEq("-hajppbc1fc208",StringExpUtil.toLongRadix(Long.MIN_VALUE,30));
    }
    @Test
    public void toLongRadix40Test(){
        assertEq("-bm03i95hia438",StringExpUtil.toLongRadix(Long.MIN_VALUE,31));
    }
    @Test
    public void toLongRadix41Test(){
        assertEq("-8000000000000",StringExpUtil.toLongRadix(Long.MIN_VALUE,32));
    }
    @Test
    public void toLongRadix42Test(){
        assertEq("-5hg4ck9jd4u38",StringExpUtil.toLongRadix(Long.MIN_VALUE,33));
    }
    @Test
    public void toLongRadix43Test(){
        assertEq("-3tdtk1v8j6tpq",StringExpUtil.toLongRadix(Long.MIN_VALUE,34));
    }
    @Test
    public void toLongRadix44Test(){
        assertEq("-2pijmikexrxp8",StringExpUtil.toLongRadix(Long.MIN_VALUE,35));
    }
    @Test
    public void toLongRadix45Test(){
        assertEq("-1y2p0ij32e8e8",StringExpUtil.toLongRadix(Long.MIN_VALUE,36));
    }
}
