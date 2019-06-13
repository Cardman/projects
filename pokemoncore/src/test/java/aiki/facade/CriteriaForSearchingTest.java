package aiki.facade;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;


public class CriteriaForSearchingTest {

    @Test
    public void matchString1Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.WHOLE_STRING, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.SUBSTRING, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.BEGIN, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.END, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.MATCH_SPACE, null, ""));
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, null, ""));
        assertTrue(!CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.SUBSTRING, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.BEGIN, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.END, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.META_CHARACTER, "STRING", (String) null));
        assertTrue(!CriteriaForSearching.match(SearchingMode.MATCH_SPACE, "STRING", (String) null));
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
        assertTrue(!CriteriaForSearching.match(SearchingMode.META_CHARACTER, "*strin", "string"));
    }

    @Test
    public void matchString7Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, "*string", "substring"));
    }

    @Test
    public void matchString8Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.BEGIN, "substring", "string"));
    }

    @Test
    public void matchString9Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.BEGIN, "string", "strings"));
    }

    @Test
    public void matchString10Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.END, "substring", "string"));
    }

    @Test
    public void matchString11Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.END, "string", "substring"));
    }

    @Test
    public void matchString12Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.MATCH_SPACE, " strin", "string"));
    }

    @Test
    public void matchString13Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.MATCH_SPACE, "sub string", "substring"));
    }

    @Test
    public void matchNumber1Test() {
        assertTrue(CriteriaForSearching.match(null, null, 0));
    }

    @Test
    public void matchNumber2Test() {
        assertTrue(CriteriaForSearching.match(0L, null, 0));
    }

    @Test
    public void matchNumber3Test() {
        assertTrue(CriteriaForSearching.match(1L, null, 2));
    }

    @Test
    public void matchNumber4Test() {
        assertTrue(!CriteriaForSearching.match(1L, null, 0));
    }

    @Test
    public void matchNumber5Test() {
        assertTrue(CriteriaForSearching.match(null, 0L, 0));
    }

    @Test
    public void matchNumber6Test() {
        assertTrue(CriteriaForSearching.match(null, 0L, -1));
    }

    @Test
    public void matchNumber7Test() {
        assertTrue(!CriteriaForSearching.match(null , -1L, 0));
    }

    @Test
    public void matchNumber8Test() {
        assertTrue(CriteriaForSearching.match(0L, 2L, 1));
    }

    @Test
    public void matchNumber9Test() {
        assertTrue(!CriteriaForSearching.match(0L , 2L, -1));
    }

    @Test
    public void matchNumber10Test() {
        assertTrue(!CriteriaForSearching.match(0L , 2L, 3));
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
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, null, new StringList()));
    }

    @Test
    public void matchStringList4Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "STRING", new StringList("SUBSTRING","STRING_ONE")));
    }

    @Test
    public void matchStringList5Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "SUBSTRING", new StringList("SUBSTRING","STRING_ONE")));
    }

    @Test
    public void matchStringList6Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.WHOLE_STRING, "SUBSTRING", new StringList((String)null,(String)null)));
    }

    @Test
    public void matchStringList7Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.SUBSTRING, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList8Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.SUBSTRING, "STRING", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList9Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.SUBSTRING, "STRING", new StringList((String)null,(String)null)));
    }

    @Test
    public void matchStringList10Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.META_CHARACTER, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList11Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, "STRING*", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList12Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.META_CHARACTER, "", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList13Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.BEGIN, null, new StringList()));
    }

    @Test
    public void matchStringList14Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.END, null, new StringList()));
    }

    @Test
    public void matchStringList15Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.MATCH_SPACE, null, new StringList()));
    }

    @Test
    public void matchStringList16Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.END, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList17Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.END, "TWO", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList18Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.END, "STRING", new StringList((String)null,(String)null)));
    }

    @Test
    public void matchStringList19Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.BEGIN, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList20Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.BEGIN, "STRING", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList21Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.BEGIN, "STRING", new StringList((String)null,(String)null)));
    }

    @Test
    public void matchStringList22Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.MATCH_SPACE, "THREE", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList23Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.MATCH_SPACE, "STRING ", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList24Test() {
        assertTrue(CriteriaForSearching.match(SearchingMode.MATCH_SPACE, "", new StringList("STRING_ONE","STRING_TWO")));
    }

    @Test
    public void matchStringList25Test() {
        assertTrue(!CriteriaForSearching.match(SearchingMode.MATCH_SPACE, "STRING", new StringList((String)null)));
    }

}
