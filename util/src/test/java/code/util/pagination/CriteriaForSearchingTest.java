package code.util.pagination;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.StringList;
import code.util.classestest.EnumDigitExample;
import code.util.pagination.CriteriaForSearching;
import code.util.pagination.SearchingMode;
import code.util.pagination.SelectedBoolean;

@SuppressWarnings("static-method")
public class CriteriaForSearchingTest {

    @Test
    public void matchString1Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.WHOLE_STRING, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.SUBSTRING, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.REG_EXP, null, ""));
        assertTrue(!CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.SUBSTRING, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.META_CHARACTER, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.REG_EXP, "STRING", (String) null));
    }

    @Test
    public void matchString2Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "string", "substring"));
    }

    @Test
    public void matchString3Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "substring", "substring"));
    }

    @Test
    public void matchString4Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.SUBSTRING, "substring", "string"));
    }

    @Test
    public void matchString5Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.SUBSTRING, "string", "substring"));
    }

    @Test
    public void matchString6Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.REG_EXP, ".string", "string"));
    }

    @Test
    public void matchString7Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.REG_EXP, ".string", "substring"));
    }

    @Test
    public void matchString8Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.META_CHARACTER, "*strin", "string"));
    }

    @Test
    public void matchString9Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, "*string", "substring"));
    }

    @Test
    public void matchNumber1Test() {
        assertTrue(CriteriaForSearching.match(null, null, 0));
    }

    @Test
    public void matchNumber2Test() {
        assertTrue(CriteriaForSearching.match(0, null, 0));
    }

    @Test
    public void matchNumber3Test() {
        assertTrue(CriteriaForSearching.match(1, null, 2));
    }

    @Test
    public void matchNumber4Test() {
        assertTrue(!CriteriaForSearching.match(1, null, 0));
    }

    @Test
    public void matchNumber5Test() {
        assertTrue(CriteriaForSearching.match(null, 0, 0));
    }

    @Test
    public void matchNumber6Test() {
        assertTrue(CriteriaForSearching.match(null, 0, -1));
    }

    @Test
    public void matchNumber7Test() {
        assertTrue(!CriteriaForSearching.match(null , -1, 0));
    }

    @Test
    public void matchNumber8Test() {
        assertTrue(CriteriaForSearching.match(0, 2, 1));
    }

    @Test
    public void matchNumber9Test() {
        assertTrue(!CriteriaForSearching.match(0 , 2, -1));
    }

    @Test
    public void matchNumber10Test() {
        assertTrue(!CriteriaForSearching.match(0 , 2, 3));
    }

    @Test
    public void matchEnum1Test() {
        assertTrue(CriteriaForSearching.match(null, EnumDigitExample.ONE));
    }

    @Test
    public void matchEnum2Test() {
        assertTrue(!CriteriaForSearching.match(EnumDigitExample.TWO, EnumDigitExample.ONE));
    }

    @Test
    public void matchEnum3Test() {
        assertTrue(CriteriaForSearching.match(EnumDigitExample.ONE, EnumDigitExample.ONE));
    }

    @Test
    public void matchBoolean1Test() {
        assertTrue(CriteriaForSearching.match(SelectedBoolean.YES_AND_NO, true));
    }

    @Test
    public void matchBoolean2Test() {
        assertTrue(CriteriaForSearching.match(SelectedBoolean.YES_AND_NO, false));
    }

    @Test
    public void matchBoolean3Test() {
        assertTrue(CriteriaForSearching.match(SelectedBoolean.YES, true));
    }

    @Test
    public void matchBoolean4Test() {
        assertTrue(!CriteriaForSearching.match(SelectedBoolean.YES, false));
    }

    @Test
    public void matchBoolean5Test() {
        assertTrue(!CriteriaForSearching.match(SelectedBoolean.NO, true));
    }

    @Test
    public void matchBoolean6Test() {
        assertTrue(CriteriaForSearching.match(SelectedBoolean.NO, false));
    }

    @Test
    public void matchStringList1Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.WHOLE_STRING, null, new StringList()));
    }

    @Test
    public void matchStringList2Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.SUBSTRING, null, new StringList()));
    }

    @Test
    public void matchStringList3Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.REG_EXP, null, new StringList()));
    }

    @Test
    public void matchStringList4Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, null, new StringList()));
    }

    @Test
    public void matchStringList5Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "STRING", new StringList("SUBSTRING","STRING_ONE")));
    }

    @Test
    public void matchStringList6Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "SUBSTRING", new StringList("SUBSTRING","STRING_ONE")));
    }

    @Test
    public void matchStringList7Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "SUBSTRING", new StringList((String)null,(String)null)));
    }

    @Test
    public void matchStringList8Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.SUBSTRING, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList9Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.SUBSTRING, "STRING", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList10Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.SUBSTRING, "STRING", new StringList((String)null,(String)null)));
    }

    @Test
    public void matchStringList11Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.REG_EXP, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList12Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.REG_EXP, "STRING.*", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList13Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.META_CHARACTER, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList14Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, "STRING*", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList15Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, "", new StringList("STRING_ONE","STRING_TWO")));
    }
}
