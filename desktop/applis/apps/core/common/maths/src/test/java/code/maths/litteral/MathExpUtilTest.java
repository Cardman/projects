package code.maths.litteral;

import code.maths.EquallableMathUtil;
import code.maths.litteralcom.MathExpUtil;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public class MathExpUtilTest extends EquallableMathUtil {
    @Test
    public void getWordsSeparators1Test(){
        String string_ = ";./:";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(1, parts_.size());
        assertEq(";./:", parts_.first());
    }
    @Test
    public void getWordsSeparators2Test(){
        String string_ = "hello";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(2, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getWordsSeparators3Test(){
        String string_ = ";hello";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(2, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getWordsSeparators4Test(){
        String string_ = "hello;";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(3, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getWordsSeparators5Test(){
        String string_ = ":hello;";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(3, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getWordsSeparators6Test(){
        String string_ = ":hello;world!";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(5, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
        assertEq("!", parts_.get(4));
    }
    @Test
    public void getWordsSeparators7Test(){
        String string_ = ":hello;world";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getWordsSeparators8Test(){
        String string_ = ":hello;,world";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getWordsSeparators9Test(){
        String string_ = "";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(0, parts_.size());
    }
    @Test
    public void getWordsSeparators10Test(){
        String string_ = ":hello_one;,world_one";
        StringList parts_ = MathExpUtil.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello_one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world_one", parts_.get(3));
    }
    @Test
    public void getWordsSeparatorsPrefix1Test(){
        String string_ = "";
        StringList parts_ = MathExpUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(0, parts_.size());
    }
    @Test
    public void getWordsSeparatorsPrefix2Test(){
        String string_ = "word_one;hello;word_two";
        StringList parts_ = MathExpUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(4, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
    }
    @Test
    public void getWordsSeparatorsPrefix3Test(){
        String string_ = "word_one;hello;word_two;world";
        StringList parts_ = MathExpUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(5, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
        assertEq(";world", parts_.get(4));
    }
    @Test
    public void getWordsSeparatorsPrefix4Test(){
        String string_ = "word_one;hello;word_two;word_three;world";
        StringList parts_ = MathExpUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(7, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
        assertEq(";", parts_.get(4));
        assertEq("word_three", parts_.get(5));
        assertEq(";world", parts_.get(6));
    }
    @Test
    public void getWordsSeparatorsPrefix5Test(){
        String string_ = ";word_one;hello;word_two";
        StringList parts_ = MathExpUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(4, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
    }
    @Test
    public void getWordsSeparatorsPrefix6Test(){
        String string_ = ";word_one;hello;word_two;world";
        StringList parts_ = MathExpUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(5, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
        assertEq(";world", parts_.get(4));
    }
    @Test
    public void getWordsSeparatorsPrefix7Test(){
        String string_ = ";word_one;hello;word_two;word_three;world";
        StringList parts_ = MathExpUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(7, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
        assertEq(";", parts_.get(4));
        assertEq("word_three", parts_.get(5));
        assertEq(";world", parts_.get(6));
    }
    @Test
    public void replaceWordsJoin1Test(){
        String string_ = "";
        String parts_ = MathExpUtil.replaceWordsJoin(string_,new StringMap<String>());
        assertEq("", parts_);
    }
    @Test
    public void replaceWordsJoin2Test(){
        String string_ = "HELLO";
        String parts_ = MathExpUtil.replaceWordsJoin(string_,new StringMap<String>());
        assertEq("HELLO", parts_);
    }
    @Test
    public void replaceWordsJoin3Test(){
        String string_ = "";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO", "WORLD");
        String parts_ = MathExpUtil.replaceWordsJoin(string_,rep_);
        assertEq("", parts_);
    }
    @Test
    public void replaceWordsJoin4Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO", "WORLD");
        String parts_ = MathExpUtil.replaceWordsJoin(string_,rep_);
        assertEq("WORLD", parts_);
    }
    @Test
    public void replaceWordsJoin5Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("WORLD", "HELLO");
        String parts_ = MathExpUtil.replaceWordsJoin(string_,rep_);
        assertEq("HELLO", parts_);
    }
    @Test
    public void replaceWordsJoin6Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO_WORLD", "WORLD");
        String parts_ = MathExpUtil.replaceWordsJoin(string_,rep_);
        assertEq("HELLO", parts_);
    }
    @Test
    public void isNumber1Test(){
        assertTrue(MathExpUtil.isNumber("1"));
    }
    @Test
    public void isNumber2Test(){
        assertTrue(!MathExpUtil.isNumber("a"));
    }
    @Test
    public void isNumber3Test(){
        assertTrue(MathExpUtil.isNumber("-1"));
    }
    @Test
    public void isNumber4Test(){
        assertTrue(!MathExpUtil.isNumber("-a"));
    }
    @Test
    public void isNumber5Test(){
        assertTrue(!MathExpUtil.isNumber("-"));
    }
    @Test
    public void isNumber6Test(){
        assertTrue(!MathExpUtil.isNumber(""));
    }
    @Test
    public void isWord1Test(){
        assertTrue(MathExpUtil.isWord("1"));
    }
    @Test
    public void isWord2Test(){
        assertTrue(MathExpUtil.isWord("a"));
    }
    @Test
    public void isWord3Test(){
        assertTrue(!MathExpUtil.isWord("-1"));
    }
    @Test
    public void isWord4Test(){
        assertTrue(!MathExpUtil.isWord("-a"));
    }
    @Test
    public void isWord5Test(){
        assertTrue(!MathExpUtil.isWord("-"));
    }
    @Test
    public void isWord6Test(){
        assertTrue(!MathExpUtil.isWord(""));
    }
    @Test
    public void isWord7Test(){
        assertTrue(!MathExpUtil.isWord("$"));
    }
    @Test
    public void isPositiveNumber1Test(){
        assertTrue(MathExpUtil.isPositiveNumber("1"));
    }

    @Test
    public void isWordChar1() {
        assertTrue(!MathExpUtil.isWordChar((char)127));
    }

    @Test
    public void isWordChar2() {
        assertTrue(MathExpUtil.isWordChar('_'));
    }

    @Test
    public void isWordChar3() {
        assertTrue(!MathExpUtil.isWordChar('!'));
    }

    @Test
    public void isWordChar4() {
        assertTrue(MathExpUtil.isWordChar('0'));
    }

    @Test
    public void isWordChar5() {
        assertTrue(MathExpUtil.isWordChar('a'));
    }

    @Test
    public void isWordChar6() {
        assertTrue(MathExpUtil.isWordChar('A'));
    }

    @Test
    public void isWordChar7() {
        assertTrue(!MathExpUtil.isWordChar(':'));
    }

    @Test
    public void isWordChar8() {
        assertTrue(!MathExpUtil.isWordChar('\\'));
    }

    @Test
    public void isWordChar9() {
        assertTrue(MathExpUtil.isWordChar((char) 160));
    }

    @Test
    public void isWordChar10() {
        assertTrue(!MathExpUtil.isWordChar((char) 159));
    }

    @Test
    public void isPositiveNumber2Test(){
        assertTrue(!MathExpUtil.isPositiveNumber("a"));
    }
    @Test
    public void isPositiveNumber3Test(){
        assertTrue(!MathExpUtil.isPositiveNumber("-1"));
    }
    @Test
    public void isPositiveNumber4Test(){
        assertTrue(!MathExpUtil.isPositiveNumber("-a"));
    }
    @Test
    public void isPositiveNumber5Test(){
        assertTrue(!MathExpUtil.isPositiveNumber("-"));
    }
    @Test
    public void isPositiveNumber6Test(){
        assertTrue(!MathExpUtil.isPositiveNumber(""));
    }

}
