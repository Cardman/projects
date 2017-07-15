package code.util.opers;
import static code.util.opers.EquallableUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.EqList;
import code.util.StringList;
import code.util.opers.StringListUtil;


@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class StringListUtilTest {

    Object[] firstCommonSubstring() {
        return $($("","",""),
                $("a","a","a"),
                $("hello","kelly","ell"),
                $("hello","miss",""),
                $("elements","em","e"),
                $("elements","ems","e"),
                $("Star Wiz","Wars Quizz","ar"),
                $("Star Wizz","Wars Quizz","ar"),
                $("Star","Wars","ar"),
                $("hello world","allo gunners","llo "));
    }

    @Parameters(method="firstCommonSubstring")
    @Test
    public void firstCommonSubstring1Test(String _one, String _two, String _expected) {
        assertEq(_expected, StringListUtil.firstCommonSubstring(_one, _two));
        assertEq(_expected, StringListUtil.firstCommonSubstring(_two, _one));
    }

    Object[] inputsCommons() {
        return $($("","",new String[]{}),
                $("a","a",new String[]{"a"}),
                $("hello","kelly",new String[]{"ell"}),
                $("hello","miss",new String[]{}),
                $("elements","em",new String[]{"e","m"}),
                $("elements","ems",new String[]{"e","m","s"}),
                $("Star Wiz","Wars Quizz",new String[]{"ar"," ","iz"}),
                $("Star Wizz","Wars Quizz",new String[]{"ar"," ","izz"}),
                $("Star","Wars",new String[]{"ar"}),
                $("hello world","allo gunners",new String[]{"llo ","r"}),
                $("mime","rime",new String[]{"ime"}),
                $("mime","perime",new String[]{"m","e"}));
    }

    @Parameters(method="inputsCommons")
    @Test
    public void commonSubstrings1Test(String _one, String _two, String[] _expecteds) {
        StringList exp_ = new StringList(_expecteds);
        assertEq(exp_, StringListUtil.commonSubstrings(_one, _two));
        assertEq(exp_, StringListUtil.commonSubstrings(_two, _one));
    }

    @Test
    public void commonLinesFiles1Test() {
        String fileOne_ = "First line=Hello\nSecond line=World";
        String fileTwo_ = "Second line=World\nFirst line=Hello";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
    }

    @Test
    public void commonLinesFiles2Test() {
        String fileOne_ = "First line=Hell\nSecond line=World\nThird line=Word\nFourth line=Yes";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We\nFourth line=Yes";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("Second line=World", common_.get(0).get(0));
        assertEq(1, common_.get(1).size());
        assertEq("Fourth line=Yes", common_.get(1).get(0));
    }

    @Test
    public void commonLinesFiles3Test() {
        String fileTwo_ = "First line=Hello\nSecond line=World";
        String fileOne_ = "Second line=World\nFirst line=Hello";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("Second line=World", common_.get(0).get(0));
    }

    @Test
    public void commonLinesFiles4Test() {
        String fileTwo_ = "First line=Hell\nSecond line=World\nThird line=Word\nFourth line=Yes";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We\nFourth line=Yes";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("Second line=World", common_.get(0).get(0));
        assertEq(1, common_.get(1).size());
        assertEq("Fourth line=Yes", common_.get(1).get(0));
    }

    @Test
    public void commonLinesFiles5Test() {
        String fileOne_ = "First line=Hello\nSecond line=World";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }

    @Test
    public void commonLinesFiles6Test() {
        String fileOne_ = "First line=Hello\nSecond line=World";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }

    @Test
    public void commonLinesFiles7Test() {
        String fileOne_ = "First line=Hello\nThird line=We\nSecond line=World";
        String fileTwo_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq(2, common_.get(1).size());
        assertEq("Third line=We", common_.get(1).get(0));
        assertEq("Second line=World", common_.get(1).get(1));
    }

    @Test
    public void commonLinesFiles8Test() {
        String fileTwo_ = "First line=Hello\nSecond line=World";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }

    @Test
    public void commonLinesFiles9Test() {
        String fileTwo_ = "First line=Hello\nSecond line=World";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(1, common_.size());
        assertEq(2, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq("Second line=World", common_.get(0).get(1));
    }

    @Test
    public void commonLinesFiles10Test() {
        String fileTwo_ = "First line=Hello\nThird line=We\nSecond line=World";
        String fileOne_ = "First line=Hello\nSecond line=World\nThird line=We\nSecond line=World";
        EqList<StringList> common_ = StringListUtil.commonLinesFiles(fileOne_, fileTwo_);
        assertEq(2, common_.size());
        assertEq(1, common_.get(0).size());
        assertEq("First line=Hello", common_.get(0).get(0));
        assertEq(2, common_.get(1).size());
        assertEq("Third line=We", common_.get(1).get(0));
        assertEq("Second line=World", common_.get(1).get(1));
    }
}
