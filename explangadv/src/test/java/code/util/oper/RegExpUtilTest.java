package code.util.oper;
import static code.util.EquallableExUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.StringList;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class RegExpUtilTest {

    Object[] inputsTokensSeparators() {
        return $($("Hello world every body","\\w+",new StringList("","Hello"," ","world"," ","every", " ","body")),
                $("Hello world every body!","\\w+",new StringList("","Hello"," ","world"," ","every", " ","body","!")),
                $("!Hello world every body","\\w+",new StringList("!","Hello"," ","world"," ","every", " ","body")),
                $("!Hello world eVery body","\\b[A-Z]\\w+\\b",new StringList("!","Hello"," world eVery body")),
                $("!Hello world every body!","\\w+",new StringList("!","Hello"," ","world"," ","every", " ","body","!")),
                $("_STR1_STRING2","_[0-9A-Z]+",new StringList("","_STR1","","_STRING2")));
    }

    @Test
    @Parameters(method="inputsTokensSeparators")
    public void getTokensSeparators1Test(String _input,String _regExpToken,StringList _match) {
        assertEq(_match, RegExpUtil.getTokensSeparators(_input, _regExpToken));
    }

    @Test
    public void getTokensSeparators2Test() {
        assertEq(new StringList("","_STR1","\n","_STRING2"), RegExpUtil.getTokensSeparators("_STR1\n_STRING2","[^\\n]+"));
    }

    @Test
    public void replaceRegExpInStrings1Test() {
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        list_.add("MY_STRING_TWO");
        RegExpUtil.replaceRegExpInStrings(list_, "^MY_STRING", "MY_SUB_STRING");
        assertEq("MY_SUB_STRING_ONE",list_.first());
        assertEq("MY_SUB_STRING_TWO",list_.get(1));
        assertEq(2,list_.size());
    }

    @Test
    public void matchingRegExp1Test() {
        StringList list_ = RegExpUtil.matchingRegExp("MY_SUB_STRING_ONE", "[A-Z]+");
        assertEq(4,list_.size());
        assertEq("MY",list_.first());
        assertEq("SUB",list_.get(1));
        assertEq("STRING",list_.get(2));
        assertEq("ONE",list_.get(3));
    }


    @Test
    public void filterIgnoreCase1Test() {
        StringList strings_ = new StringList();
        strings_.add("string_one");
        strings_.add("STRING_TWO");
        StringList res_ = RegExpUtil.filterIgnoreCase(strings_,"STRING_one");
        assertEq(1, res_.size());
        assertEq("string_one", res_.first());
    }

}
