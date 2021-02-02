package code.util.opers;

import code.util.CustList;
import code.util.EquallableExUtil;
import code.util.core.StringUtil;
import org.junit.Test;

import code.util.StringList;



public class StringListUtilTest extends EquallableExUtil {
    @Test
    public void firstCommonSubstring1Test(){
        assertEq("", StringListUtil.firstCommonSubstring("", ""));
        assertEq("", StringListUtil.firstCommonSubstring("", ""));
    }
    @Test
    public void firstCommonSubstring2Test(){
        assertEq("a", StringListUtil.firstCommonSubstring("a", "a"));
        assertEq("a", StringListUtil.firstCommonSubstring("a", "a"));
    }
    @Test
    public void firstCommonSubstring3Test(){
        assertEq("ell", StringListUtil.firstCommonSubstring("hello", "kelly"));
        assertEq("ell", StringListUtil.firstCommonSubstring("kelly", "hello"));
    }
    @Test
    public void firstCommonSubstring4Test(){
        assertEq("", StringListUtil.firstCommonSubstring("hello", "miss"));
        assertEq("", StringListUtil.firstCommonSubstring("miss", "hello"));
    }
    @Test
    public void firstCommonSubstring5Test(){
        assertEq("e", StringListUtil.firstCommonSubstring("elements", "em"));
        assertEq("e", StringListUtil.firstCommonSubstring("em", "elements"));
    }
    @Test
    public void firstCommonSubstring6Test(){
        assertEq("e", StringListUtil.firstCommonSubstring("elements", "ems"));
        assertEq("e", StringListUtil.firstCommonSubstring("ems", "elements"));
    }
    @Test
    public void firstCommonSubstring7Test(){
        assertEq("ar", StringListUtil.firstCommonSubstring("Star Wiz", "Wars Quizz"));
        assertEq("ar", StringListUtil.firstCommonSubstring("Wars Quizz", "Star Wiz"));
    }
    @Test
    public void firstCommonSubstring8Test(){
        assertEq("ar", StringListUtil.firstCommonSubstring("Star Wizz", "Wars Quizz"));
        assertEq("ar", StringListUtil.firstCommonSubstring("Wars Quizz", "Star Wizz"));
    }
    @Test
    public void firstCommonSubstring9Test(){
        assertEq("ar", StringListUtil.firstCommonSubstring("Star", "Wars"));
        assertEq("ar", StringListUtil.firstCommonSubstring("Wars", "Star"));
    }
    @Test
    public void firstCommonSubstring10Test(){
        assertEq("llo ", StringListUtil.firstCommonSubstring("hello world", "allo gunners"));
        assertEq("llo ", StringListUtil.firstCommonSubstring("allo gunners", "hello world"));
    }
    @Test
    public void commonSubstrings1Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray());
        assertEq(exp_, StringListUtil.commonSubstrings("", ""));
        assertEq(exp_, StringListUtil.commonSubstrings("", ""));
    }
    @Test
    public void commonSubstrings2Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("a"));
        assertEq(exp_, StringListUtil.commonSubstrings("a", "a"));
        assertEq(exp_, StringListUtil.commonSubstrings("a", "a"));
    }
    @Test
    public void commonSubstrings3Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("ell"));
        assertEq(exp_, StringListUtil.commonSubstrings("hello", "kelly"));
        assertEq(exp_, StringListUtil.commonSubstrings("kelly", "hello"));
    }
    @Test
    public void commonSubstrings4Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray());
        assertEq(exp_, StringListUtil.commonSubstrings("hello", "miss"));
        assertEq(exp_, StringListUtil.commonSubstrings("miss", "hello"));
    }
    @Test
    public void commonSubstrings5Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("e","m"));
        assertEq(exp_, StringListUtil.commonSubstrings("elements", "em"));
        assertEq(exp_, StringListUtil.commonSubstrings("em", "elements"));
    }
    @Test
    public void commonSubstrings6Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("e","m","s"));
        assertEq(exp_, StringListUtil.commonSubstrings("elements", "ems"));
        assertEq(exp_, StringListUtil.commonSubstrings("ems", "elements"));
    }
    @Test
    public void commonSubstrings7Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("ar"," ","iz"));
        assertEq(exp_, StringListUtil.commonSubstrings("Star Wiz", "Wars Quizz"));
        assertEq(exp_, StringListUtil.commonSubstrings("Wars Quizz", "Star Wiz"));
    }
    @Test
    public void commonSubstrings8Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("ar"," ","izz"));
        assertEq(exp_, StringListUtil.commonSubstrings("Star Wizz", "Wars Quizz"));
        assertEq(exp_, StringListUtil.commonSubstrings("Wars Quizz", "Star Wizz"));
    }
    @Test
    public void commonSubstrings9Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("ar"));
        assertEq(exp_, StringListUtil.commonSubstrings("Star", "Wars"));
        assertEq(exp_, StringListUtil.commonSubstrings("Wars", "Star"));
    }
    @Test
    public void commonSubstrings10Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("llo ","r"));
        assertEq(exp_, StringListUtil.commonSubstrings("hello world", "allo gunners"));
        assertEq(exp_, StringListUtil.commonSubstrings("allo gunners", "hello world"));
    }
    @Test
    public void commonSubstrings11Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("ime"));
        assertEq(exp_, StringListUtil.commonSubstrings("mime", "rime"));
        assertEq(exp_, StringListUtil.commonSubstrings("rime", "mime"));
    }
    @Test
    public void commonSubstrings12Test(){
        StringList exp_ = new StringList(StringUtil.wrapStringArray("m","e"));
        assertEq(exp_, StringListUtil.commonSubstrings("mime", "perime"));
        assertEq(exp_, StringListUtil.commonSubstrings("perime", "mime"));
    }
    @Test
    public void commonLinesFiles1(){
        String fileOne_ = "First line=Hello\nSecond line=World";
        String fileTwo_ = "Second line=World\nFirst line=Hello";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
    }
    @Test
    public void commonLinesFiles2(){
        String fileOne_ = "First line=Hell\nSecond line=World\nThird line=Word\nFourth line=Yes";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We\nFourth line=Yes";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("Second line=World", common_.get(0).get(0));
        assertEq(1, common_.get(1).size());
        assertEq("Fourth line=Yes", common_.get(1).get(0));
    }
    @Test
    public void commonLinesFiles3(){
        String fileTwo_ = "First line=Hello\nSecond line=World";
        String fileOne_ = "Second line=World\nFirst line=Hello";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("Second line=World", common_.get(0).get(0));
    }
    @Test
    public void commonLinesFiles4(){
        String fileTwo_ = "First line=Hell\nSecond line=World\nThird line=Word\nFourth line=Yes";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We\nFourth line=Yes";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("Second line=World", common_.get(0).get(0));
        assertEq(1, common_.get(1).size());
        assertEq("Fourth line=Yes", common_.get(1).get(0));
    }
    @Test
    public void commonLinesFiles5(){
        String fileOne_ = "First line=Hello\nSecond line=World";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }
    @Test
    public void commonLinesFiles6(){
        String fileOne_ = "First line=Hello\nSecond line=World";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }
    @Test
    public void commonLinesFiles7(){
        String fileOne_ = "First line=Hello\nThird line=We\nSecond line=World";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq(2, common_.get(1).size());
        assertEq("Third line=We", common_.get(1).get(0));
        assertEq("Second line=World", common_.get(1).get(1));
    }
    @Test
    public void commonLinesFiles8(){
        String fileTwo_ = "First line=Hello\nSecond line=World";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }
    @Test
    public void commonLinesFiles9(){
        String fileTwo_ = "First line=Hello\nSecond line=World";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }
    @Test
    public void commonLinesFiles10(){
        String fileTwo_ = "First line=Hello\nThird line=We\nSecond line=World";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        CustList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq(2, common_.get(1).size());
        assertEq("Third line=We", common_.get(1).get(0));
        assertEq("Second line=World", common_.get(1).get(1));
    }
}