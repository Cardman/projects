package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class StringListTest {
    @Test
    public void sub1Test(){
        StringList s_ = new StringList();
        StringList out_ = s_.sub(0, 0);
        assertEq(0, out_.size());
    }
    @Test
    public void sub2Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        StringList out_ = s_.sub(1, 0);
        assertEq(0, out_.size());
    }
    @Test
    public void sub3Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        s_.add("world");
        StringList out_ = s_.sub(1, 2);
        assertEq(1, out_.size());
        assertEq("world", out_.get(0));
    }
    @Test
    public void removeAllString1Test(){
        StringList s_ = new StringList();
        s_.removeAllString("part");
        assertEq(0, s_.size());
    }
    @Test
    public void removeAllString2Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        s_.removeAllString("part");
        assertEq(1, s_.size());
        assertEq("hello", s_.get(0));
    }
    @Test
    public void removeAllString3Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        s_.add("part");
        s_.removeAllString("part");
        assertEq(1, s_.size());
        assertEq("hello", s_.get(0));
    }
    @Test
    public void removeString1Test(){
        StringList s_ = new StringList();
        s_.removeString("part");
        assertEq(0, s_.size());
    }
    @Test
    public void removeString2Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        s_.removeString("part");
        assertEq(1, s_.size());
        assertEq("hello", s_.get(0));
    }
    @Test
    public void removeString3Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        s_.add("part");
        s_.removeString("part");
        assertEq(1, s_.size());
        assertEq("hello", s_.get(0));
    }
    @Test
    public void match1Test(){
        assertEq(true, StringList.match("myString", ""));
    }
    @Test
    public void match2Test(){
        assertEq(true, StringList.match("myString", "myString"));
    }
    @Test
    public void match3Test(){
        assertEq(true, StringList.match("myString", "*......"));
    }
    @Test
    public void match4Test(){
        assertEq(true, StringList.match("myString", "......*"));
    }
    @Test
    public void match5Test(){
        assertEq(false, StringList.match("myString", "......?"));
    }
    @Test
    public void match6Test(){
        assertEq(true, StringList.match("myString", ".......?"));
    }
    @Test
    public void match7Test(){
        assertEq(true, StringList.match("myString", "........"));
    }
    @Test
    public void match8Test(){
        assertEq(false, StringList.match("myString", "........."));
    }
    @Test
    public void match9Test(){
        assertEq(false, StringList.match("myString", "???????"));
    }
    @Test
    public void match10Test(){
        assertEq(true, StringList.match("myString", "????????"));
    }
    @Test
    public void match11Test(){
        assertEq(true, StringList.match("myString", "m.....*"));
    }
    @Test
    public void match12Test(){
        assertEq(false, StringList.match("myString", "m.....?"));
    }
    @Test
    public void match13Test(){
        assertEq(true, StringList.match("myString", "m......?"));
    }
    @Test
    public void match14Test(){
        assertEq(true, StringList.match("myString", "m......."));
    }
    @Test
    public void match15Test(){
        assertEq(false, StringList.match("myString", "m........"));
    }
    @Test
    public void match16Test(){
        assertEq(false, StringList.match("myString", "m??????"));
    }
    @Test
    public void match17Test(){
        assertEq(true, StringList.match("myString", "m???????"));
    }
    @Test
    public void match18Test(){
        assertEq(true, StringList.match("myString", "myStri*"));
    }
    @Test
    public void match19Test(){
        assertEq(true, StringList.match("myString", "*String"));
    }
    @Test
    public void match20Test(){
        assertEq(false, StringList.match("myString", "\\*String"));
    }
    @Test
    public void match21Test(){
        assertEq(true, StringList.match("myString", "..String*"));
    }
    @Test
    public void match22Test(){
        assertEq(true, StringList.match("myString", "..Str?ing*"));
    }
    @Test
    public void match23Test(){
        assertEq(false, StringList.match("myString", ".String*"));
    }
    @Test
    public void match24Test(){
        assertEq(true, StringList.match("myString", "mySt..ng*"));
    }
    @Test
    public void match25Test(){
        assertEq(true, StringList.match("myString", ".ySt..ng*"));
    }
    @Test
    public void match26Test(){
        assertEq(false, StringList.match("myString", ".St?.ng*"));
    }
    @Test
    public void match27Test(){
        assertEq(false, StringList.match("myString", "?String*"));
    }
    @Test
    public void match28Test(){
        assertEq(true, StringList.match("myString", ".?String*"));
    }
    @Test
    public void match29Test(){
        assertEq(true, StringList.match("myString", "??String*"));
    }
    @Test
    public void match30Test(){
        assertEq(true, StringList.match("myString", "..?String*"));
    }
    @Test
    public void match31Test(){
        assertEq(true, StringList.match("myString", "???tring*"));
    }
    @Test
    public void match32Test(){
        assertEq(true, StringList.match("my.String", "my\\.String"));
    }
    @Test
    public void match33Test(){
        assertEq(true, StringList.match("my.*String", "my\\.\\*String"));
    }
    @Test
    public void match34Test(){
        assertEq(true, StringList.match("my.*String", "my\\..String"));
    }
    @Test
    public void match35Test(){
        assertEq(true, StringList.match("my.*?String", "my\\.\\*\\?String"));
    }
    @Test
    public void match36Test(){
        assertEq(true, StringList.match("my.*?\\String", "my\\.\\*\\?\\\\String"));
    }
    @Test
    public void match37Test(){
        assertEq(true, StringList.match("my.*?\\String", "my\\.\\*\\?\\String"));
    }
    @Test
    public void match38Test(){
        assertEq(false, StringList.match("my.*?\\\\String", "my\\.\\*\\?\\String"));
    }
    @Test
    public void match39Test(){
        assertTrue(StringList.match("myString","*String"));
    }
    @Test
    public void match40Test(){
        assertTrue(StringList.match("myStringString","*String"));
    }
    @Test
    public void match41Test(){
        assertTrue(!StringList.match("myStringStrin","*String"));
    }
    @Test
    public void match42Test(){
        assertTrue(StringList.match("myStrinString","*String"));
    }
    @Test
    public void match43Test(){
        assertTrue(StringList.match("myString","*y*String"));
    }
    @Test
    public void match44Test(){
        assertTrue(StringList.match("myStringString","*y*String"));
    }
    @Test
    public void match45Test(){
        assertTrue(!StringList.match("myStringStrin","*y*String"));
    }
    @Test
    public void match46Test(){
        assertTrue(StringList.match("myStrinString","*y*String"));
    }
    @Test
    public void match47Test(){
        assertTrue(!StringList.match("myStrinString","*ys*String"));
    }
    @Test
    public void match48Test(){
        assertTrue(!StringList.match("myStrinString","*yS*Strig"));
    }
    @Test
    public void match49Test(){
        assertTrue(!StringList.match("myStrinString","*yS*Strin"));
    }
    @Test
    public void matchSpace1Test(){
        assertEq(true, StringList.matchSpace("myString", ""));
    }
    @Test
    public void matchSpace2Test(){
        assertEq(true, StringList.matchSpace("myString", "myString"));
    }
    @Test
    public void matchSpace3Test(){
        assertEq(false, StringList.matchSpace("myString", "myStrings"));
    }
    @Test
    public void matchSpace4Test(){
        assertEq(false, StringList.matchSpace("myString", "my Strings"));
    }
    @Test
    public void matchSpace5Test(){
        assertEq(false, StringList.matchSpace("myString", "myStrin"));
    }
    @Test
    public void matchSpace6Test(){
        assertEq(false, StringList.matchSpace("myString", "my Strin"));
    }
    @Test
    public void matchSpace7Test(){
        assertEq(true, StringList.matchSpace("myString", " "));
    }
    @Test
    public void matchSpace8Test(){
        assertEq(false, StringList.matchSpace("myString", " \\  "));
    }
    @Test
    public void matchSpace9Test(){
        assertEq(true, StringList.matchSpace("my String", " \\  "));
    }
    @Test
    public void matchSpace10Test(){
        assertEq(true, StringList.matchSpace("my \\String", " \\\\ "));
    }
    @Test
    public void matchSpace11Test(){
        assertEq(true, StringList.matchSpace("myString", "m "));
    }
    @Test
    public void matchSpace12Test(){
        assertEq(true, StringList.matchSpace("myString", "myStri "));
    }
    @Test
    public void matchSpace13Test(){
        assertEq(false, StringList.matchSpace("myString", "myStrn "));
    }
    @Test
    public void matchSpace14Test(){
        assertEq(true, StringList.matchSpace("myString", " String"));
    }
    @Test
    public void matchSpace15Test(){
        assertEq(false, StringList.matchSpace("myString", "\\ String"));
    }
    @Test
    public void matchSpace16Test(){
        assertEq(true, StringList.matchSpace("myString", "myString "));
    }
    @Test
    public void matchSpace17Test(){
        assertEq(false, StringList.matchSpace("myString", "myString\\ "));
    }
    @Test
    public void matchSpace18Test(){
        assertEq(true, StringList.matchSpace("myString", " myString"));
    }
    @Test
    public void matchSpace19Test(){
        assertEq(true, StringList.matchSpace("myString", "my String"));
    }
    @Test
    public void matchSpace20Test(){
        assertEq(true, StringList.matchSpace("myString", " my String "));
    }
    @Test
    public void matchSpace21Test(){
        assertEq(true, StringList.matchSpace("myString", " my Str ing"));
    }
    @Test
    public void matchSpace22Test(){
        assertEq(true, StringList.matchSpace("myString", " my Str in "));
    }
    @Test
    public void matchSpace23Test(){
        assertEq(false, StringList.matchSpace("myString", " my Str ins "));
    }
    @Test
    public void matchSpace24Test(){
        assertEq(true, StringList.matchSpace("myString", "my Str ing"));
    }
    @Test
    public void matchSpace25Test(){
        assertEq(false, StringList.matchSpace("myString", "my Str ings"));
    }
    @Test
    public void matchSpace26Test(){
        assertEq(false, StringList.matchSpace("myString", "my Str ing\\ "));
    }
    @Test
    public void matchSpace27Test(){
        assertEq(false, StringList.matchSpace("myString", "my Str ngs"));
    }
    @Test
    public void matchSpace28Test(){
        assertEq(false, StringList.matchSpace("myString", "my Stg ing"));
    }
    @Test
    public void matchSpace29Test(){
        assertEq(false, StringList.matchSpace("my.String", "my\\ String"));
    }
    @Test
    public void matchSpace30Test(){
        assertEq(true, StringList.matchSpace("my String", "my\\ String"));
    }
    @Test
    public void matchSpace31Test(){
        assertEq(true, StringList.matchSpace("my\\String", "my\\String"));
    }
    @Test
    public void matchSpace32Test(){
        assertEq(true, StringList.matchSpace("my\\String", "my\\\\String"));
    }
    @Test
    public void matchSpace33Test(){
        assertEq(false, StringList.matchSpace("my\\String", "my\\ String"));
    }
    @Test
    public void matchSpace34Test(){
        assertEq(false, StringList.matchSpace("my String", "my\\\\String"));
    }
    @Test
    public void greatestIndex1Test(){
        assertEq(-1, StringList.greatestIndex("one", "two", 0));
    }
    @Test
    public void greatestIndex2Test(){
        assertEq(3, StringList.greatestIndex("my_string", "str", 0));
    }
    @Test
    public void greatestIndex3Test(){
        assertEq(-1, StringList.greatestIndex("my_string", "str", 4));
    }
    @Test
    public void greatestIndex4Test(){
        assertEq(3, StringList.greatestIndex("my_string", "str", 3));
    }
    @Test
    public void greatestIndex5Test(){
        assertEq(10, StringList.greatestIndex("my_string_string", "str", 3));
    }
    @Test
    public void replaceInStrings1Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        list_.add("MY_STRING_TWO");
        list_.replaceInStrings("MY_STRING", "MY_SUB_STRING");
        assertEq("MY_SUB_STRING_ONE",list_.first());
        assertEq("MY_SUB_STRING_TWO",list_.get(1));
        assertEq(2,list_.size());
    }
    @Test
    public void mid1Test(){
        StringList strings_ = new StringList();
        strings_.add("MY");
        strings_.add("SUB");
        strings_.add("STRING");
        strings_.add("ONE");
        StringList list_ = strings_.mid(1, 3);
        assertEq(3,list_.size());
        assertEq("SUB",list_.first());
        assertEq("STRING",list_.get(1));
        assertEq("ONE",list_.get(2));
    }
    @Test
    public void mid2Test(){
        StringList strings_ = new StringList();
        strings_.add("MY");
        strings_.add("SUB");
        strings_.add("STRING");
        strings_.add("ONE");
        StringList list_ = strings_.mid(1, 2);
        assertEq(2,list_.size());
        assertEq("SUB",list_.first());
        assertEq("STRING",list_.get(1));
    }
    @Test
    public void mid3Test(){
        StringList strings_ = new StringList();
        strings_.add("MY");
        strings_.add("SUB");
        strings_.add("STRING");
        strings_.add("ONE");
        StringList list_ = strings_.mid(1, 4);
        assertEq(3,list_.size());
        assertEq("SUB",list_.first());
        assertEq("STRING",list_.get(1));
        assertEq("ONE",list_.get(2));
    }
    @Test
    public void join1Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        list_.add("MY_STRING_TWO");
        assertEq("MY_STRING_ONE;MY_STRING_TWO",list_.join(";"));
    }
    @Test
    public void join2Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        list_.add("MY_STRING_TWO");
        assertEq("MY_STRING_ONE;MY_STRING_TWO",list_.join(';'));
    }
    @Test
    public void join3Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        assertEq("MY_STRING_ONE",list_.join(";"));
    }
    @Test
    public void join4Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        assertEq("MY_STRING_ONE",list_.join(';'));
    }
    @Test
    public void join5Test(){
        StringList list_ = new StringList();
        assertEq("",list_.join(";"));
    }
    @Test
    public void join6Test(){
        StringList list_ = new StringList();
        assertEq("",list_.join(';'));
    }
    @Test
    public void isNumber1Test(){
        assertTrue(StringList.isNumber("1"));
    }
    @Test
    public void isNumber2Test(){
        assertTrue(!StringList.isNumber("a"));
    }
    @Test
    public void isNumber3Test(){
        assertTrue(StringList.isNumber("-1"));
    }
    @Test
    public void isNumber4Test(){
        assertTrue(!StringList.isNumber("-a"));
    }
    @Test
    public void isNumber5Test(){
        assertTrue(!StringList.isNumber("-"));
    }
    @Test
    public void isNumber6Test(){
        assertTrue(!StringList.isNumber(""));
    }
    @Test
    public void isPositiveNumber1Test(){
        assertTrue(StringList.isPositiveNumber("1"));
    }
    @Test
    public void isPositiveNumber2Test(){
        assertTrue(!StringList.isPositiveNumber("a"));
    }
    @Test
    public void isPositiveNumber3Test(){
        assertTrue(!StringList.isPositiveNumber("-1"));
    }
    @Test
    public void isPositiveNumber4Test(){
        assertTrue(!StringList.isPositiveNumber("-a"));
    }
    @Test
    public void isPositiveNumber5Test(){
        assertTrue(!StringList.isPositiveNumber("-"));
    }
    @Test
    public void isPositiveNumber6Test(){
        assertTrue(!StringList.isPositiveNumber(""));
    }
    @Test
    public void isWord1Test(){
        assertTrue(StringList.isWord("1"));
    }
    @Test
    public void isWord2Test(){
        assertTrue(StringList.isWord("a"));
    }
    @Test
    public void isWord3Test(){
        assertTrue(!StringList.isWord("-1"));
    }
    @Test
    public void isWord4Test(){
        assertTrue(!StringList.isWord("-a"));
    }
    @Test
    public void isWord5Test(){
        assertTrue(!StringList.isWord("-"));
    }
    @Test
    public void isWord6Test(){
        assertTrue(!StringList.isWord(""));
    }
    @Test
    public void isWord7Test(){
        assertTrue(!StringList.isWord("$"));
    }
    @Test
    public void isDollarWord1Test(){
        assertTrue(StringList.isDollarWord("1"));
    }
    @Test
    public void isDollarWord2Test(){
        assertTrue(StringList.isDollarWord("a"));
    }
    @Test
    public void isDollarWord3Test(){
        assertTrue(!StringList.isDollarWord("-1"));
    }
    @Test
    public void isDollarWord4Test(){
        assertTrue(!StringList.isDollarWord("-a"));
    }
    @Test
    public void isDollarWord5Test(){
        assertTrue(!StringList.isDollarWord("-"));
    }
    @Test
    public void isDollarWord6Test(){
        assertTrue(!StringList.isDollarWord(""));
    }
    @Test
    public void isDollarWord7Test(){
        assertTrue(StringList.isDollarWord("$"));
    }
    @Test
    public void indexesOfSubString1Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfSubString("HELLO WORLD", "L");
        assertEq(3, indexes_.size());
        assertEq(2, indexes_.get(0));
        assertEq(3, indexes_.get(1));
        assertEq(9, indexes_.get(2));
    }
    @Test
    public void indexesOfSubString2Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfSubString("HELLO WORLD", "LL");
        assertEq(1, indexes_.size());
        assertEq(2, indexes_.get(0));
    }
    @Test
    public void indexesOfSubString3Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfSubString("HELLO WORLD", "LD");
        assertEq(1, indexes_.size());
        assertEq(9, indexes_.get(0));
    }
    @Test
    public void indexesOfSubString4Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfSubString("HELLO WORLD", "OO");
        assertEq(0, indexes_.size());
    }
    @Test
    public void indexesOfSubString5Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfSubString("HELLO WORLD", "");
        assertEq(1, indexes_.size());
        assertEq(0, indexes_.get(0));
    }
    @Test
    public void indexesOfSubString6Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfSubString("AAAA", "AA");
        assertEq(2, indexes_.size());
        assertEq(0, indexes_.get(0));
        assertEq(2, indexes_.get(1));
    }
    @Test
    public void indexesOfSubString7Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfSubString("AAA", "AA");
        assertEq(1, indexes_.size());
        assertEq(0, indexes_.get(0));
    }
    @Test
    public void indexesOfChar1Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfChar("HELLO WORLD", 'L');
        assertEq(3, indexes_.size());
        assertEq(2, indexes_.get(0));
        assertEq(3, indexes_.get(1));
        assertEq(9, indexes_.get(2));
    }
    @Test
    public void indexesOfChar2Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfChar("HELLO WORLD", 'Z');
        assertEq(0, indexes_.size());
    }
    @Test
    public void indexesOfChar3Test(){
        Numbers<Integer> indexes_;
        indexes_ = StringList.indexesOfChar("", 'Z');
        assertEq(0, indexes_.size());
    }
    @Test
    public void replace1Test(){
        String str_ = "AAAA";
        String old_ = "AA";
        String new_ = "B";
        assertEq("BB", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace2Test(){
        String str_ = "AAA";
        String old_ = "AA";
        String new_ = "B";
        assertEq("BA", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace3Test(){
        String str_ = "AAA";
        String old_ = "A";
        String new_ = "B";
        assertEq("BBB", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace4Test(){
        String str_ = "AAA";
        String old_ = "C";
        String new_ = "B";
        assertEq("AAA", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace5Test(){
        String str_ = "ACAA";
        String old_ = "AC";
        String new_ = "B";
        assertEq("BAA", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace6Test(){
        String str_ = "ACAC";
        String old_ = "AC";
        String new_ = "B";
        assertEq("BB", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace7Test(){
        String str_ = "ABCDE";
        String old_ = "ABCDEF";
        String new_ = "B";
        assertEq("ABCDE", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace8Test(){
        String str_ = "ABCDEF";
        String old_ = "ABCDEF";
        String new_ = "B";
        assertEq("B", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace9Test(){
        String str_ = "ABCDEF";
        String old_ = "ABCDE";
        String new_ = "B";
        assertEq("BF", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace10Test(){
        String str_ = "AC";
        String old_ = "";
        String new_ = "B";
        assertEq("BABCB", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace11Test(){
        String str_ = "AC";
        String old_ = "A";
        String new_ = null;
        assertEq("C", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace12Test(){
        String str_ = "AC";
        String old_ = "B";
        String new_ = null;
        assertEq("AC", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace13Test(){
        String str_ = "AA";
        String old_ = "A";
        String new_ = null;
        assertEq("", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace14Test(){
        String str_ = "AA";
        String old_ = "";
        String new_ = null;
        assertEq("AA", StringList.replace(str_, old_, new_));
    }
    @Test
    public void replace15Test(){
        String str_ = "AA";
        String old_ = null;
        String new_ = null;
        assertEq("AA", StringList.replace(str_, old_, new_));
    }
    @Test
    public void getFields1Test(){
        String html_ = "<html><body>composite.integer</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields2Test(){
        String html_ = "<html><body>{composite.integer}</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields3Test(){
        String html_ = "<html><body>'quoted'</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields4Test(){
        String html_ = "<html><body>{composite.integer} '{mycommented}'</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields5Test(){
        String html_ = "<html><body>'{mycommented}'</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields6Test(){
        String html_ = "<html><body>'{mycommented}' {composite.integer}</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields7Test(){
        String html_ = "<html><body>'{mycommented} {composite.integer}</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields8Test(){
        String html_ = "<html><body>{composite.integer} '{mycommented}</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields9Test(){
        String html_ = "<html><body>{composite.integer} {mycommented}</body></html>'";
        StringList fields_ = StringList.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("mycommented",fields_.get(1));
    }
    @Test
    public void getFields10Test(){
        String html_ = "<html><body>{composite.integer} {mycommented}</body></html>''";
        StringList fields_ = StringList.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("mycommented",fields_.get(1));
    }
    @Test
    public void getFields11Test(){
        String html_ = "<html><body>{composite.integer} '{mycommented}' {compositeTwo.integer}</body></html>";
        StringList fields_ = StringList.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("compositeTwo.integer",fields_.get(1));
    }
    @Test
    public void getFields12Test(){
        String html_ = "<html><body>{composite.integer} {mycommented}</body></html>'quoted'";
        StringList fields_ = StringList.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("mycommented",fields_.get(1));
    }
    @Test
    public void simpleFormat1Test(){
        String pattern_ = "Without variable in string";
        assertEq(pattern_, StringList.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat2Test(){
        String pattern_ = "Without variable '' in string";
        String outPut_ = "Without variable ' in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat3Test(){
        String pattern_ = "With variables {0} {1} in string";
        String outPut_ = "With variables zero one in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat4Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat5Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables one zero in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat6Test(){
        String pattern_ = "With variables '{0} {1}' in string";
        String outPut_ = "With variables {0} {1} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_,"zero","one"));
    }
    @Test
    public void simpleFormat7Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables one zero in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero", "one", "two"));
    }
    @Test
    public void simpleFormat8Test(){
        String pattern_ = "With variables {0} {1} '{2}' in string";
        String outPut_ = "With variables zero one {2} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat9Test(){
        String pattern_ = "With variables {0} {1} {2} in string";
        String outPut_ = "With variables zero one {2} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat10Test(){
        String pattern_ = "With variables {0} {1} '{arg}' in string";
        String outPut_ = "With variables zero one {arg} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat11Test(){
        String pattern_ = "With variables {0} {1} {arg} in string";
        String outPut_ = "With variables zero one {arg} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat12Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE} in string'";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat13Test(){
        String pattern_ = "With variables {-1} in string";
        String outPut_ = "With variables {-1} in string";
        assertEq(outPut_, StringList.simpleStringsFormat(pattern_, "zero"));
    }
    @Test
    public void simpleFormatNb1Test(){
        String pattern_ = "Without variable in string";
        assertEq(pattern_, StringList.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb2Test(){
        String pattern_ = "Without variable '' in string";
        String outPut_ = "Without variable ' in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb3Test(){
        String pattern_ = "With variables {0} {1} in string";
        String outPut_ = "With variables 10 11 in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb4Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb5Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables 11 10 in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb6Test(){
        String pattern_ = "With variables '{0} {1}' in string";
        String outPut_ = "With variables {0} {1} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb7Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables 11 10 in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11,12));
    }
    @Test
    public void simpleFormatNb8Test(){
        String pattern_ = "With variables {0} {1} '{2}' in string";
        String outPut_ = "With variables 10 11 {2} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb9Test(){
        String pattern_ = "With variables {0} {1} {2} in string";
        String outPut_ = "With variables 10 11 {2} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb10Test(){
        String pattern_ = "With variables {0} {1} '{arg}' in string";
        String outPut_ = "With variables 10 11 {arg} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb11Test(){
        String pattern_ = "With variables {0} {1} {arg} in string";
        String outPut_ = "With variables 10 11 {arg} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb12Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE} in string'";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb13Test(){
        String pattern_ = "With variables {-1} in string";
        String outPut_ = "With variables {-1} in string";
        assertEq(outPut_, StringList.simpleNumberFormat(pattern_, 10));
    }
    @Test
    public void sort1Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_TWO");
        list_.add("MY_STRING_ONE");
        list_.add("MY_STRING_THREE");
        list_.sort();
        assertEq(3,list_.size());
        assertEq("MY_STRING_ONE",list_.first());
        assertEq("MY_STRING_THREE",list_.get(1));
        assertEq("MY_STRING_TWO",list_.get(2));
    }
    @Test
    public void format1Test(){
        String pattern_ = "Without variable in string";
        assertEq(pattern_, StringList.format(pattern_, '\'', new StringMap<String>()));
    }
    @Test
    public void format2Test(){
        String pattern_ = "Without variable '' in string";
        String outPut_ = "Without variable ' in string";
        assertEq(outPut_, StringList.format(pattern_, '\'', new StringMap<String>()));
    }
    @Test
    public void format3Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format4Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format5Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' {STRING_TWO} in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} {STRING_TWO} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format6Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} {S} in string";
        String outPut_ = "With variables zero one {S} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format7Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} {S} in string";
        String outPut_ = "With variables {STRING} one {S} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format8Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} {S} in string'''";
        String outPut_ = "With variables {STRING} one {S} in string'";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format9Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format10Test(){
        String pattern_ = "With variables '{STRING}' '{STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringList.format(pattern_, '\'', map_));
    }
    @Test
    public void format11Test(){
        String pattern_ = "Without variable in string ''";
        String outPut_ = "Without variable in string '";
        assertEq(outPut_, StringList.format(pattern_, '\'', new StringMap<String>()));
    }
    @Test
    public void formatBasic1Test(){
        String pattern_ = "With variables STR STRING in string";
        String outPut_ = "With variables zero zeroING in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic2Test(){
        String pattern_ = "With variables & < > in string";
        String outPut_ = "With variables &amp; &lt; &gt; in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("&", "&amp;");
        map_.put("<", "&lt;");
        map_.put(">", "&gt;");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic3Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("NG", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic4Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables NG one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic5Test(){
        String pattern_ = "With variables STR STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic6Test(){
        String pattern_ = "With variables STR STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic7Test(){
        String pattern_ = "With variables & < > in string";
        String outPut_ = "With variables &amp; &lt; &gt; in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("&", "&amp;");
        map_.put("<", "&lt;");
        map_.put(">", "&gt;");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic8Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("NG", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic9Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables NG one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic10Test(){
        String pattern_ = "With variables STRING STR";
        String outPut_ = "With variables zeroING zero";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic11Test(){
        String pattern_ = "With variables STR STRING";
        String outPut_ = "With variables zero one";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic12Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables STR";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic13Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables STR";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic14Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables two";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic15Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables two";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic16Test(){
        String pattern_ = "";
        String outPut_ = "";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic17Test(){
        String pattern_ = "";
        String outPut_ = "";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringList.formatBasic(pattern_, map_, false));
    }
    @Test
    public void escape1Test(){
        assertEq("myString", StringList.escape("myString"));
    }
    @Test
    public void escape2Test(){
        assertEq("my?.String*end", StringList.escape("my\\?\\.String\\*end"));
    }
    @Test
    public void escape3Test(){
        assertEq("myString*end", StringList.escape("myString\\*end"));
    }
    @Test
    public void escape4Test(){
        assertEq("myString\\end", StringList.escape("myString\\\\end"));
    }
    @Test
    public void escape5Test(){
        assertEq("myString\\", StringList.escape("myString\\\\"));
    }
    @Test
    public void escape6Test(){
        assertEq("myString\\", StringList.escape("myString\\"));
    }
    @Test
    public void escape7Test(){
        assertEq("my\\String*", StringList.escape("my\\\\String\\*"));
    }
    @Test
    public void escape8Test(){
        assertEq("my*String\\", StringList.escape("my\\*String\\\\"));
    }
    @Test
    public void escape9Test(){
        assertEq("my\\String*end", StringList.escape("my\\\\String\\*end"));
    }
    @Test
    public void escapeSpace1Test(){
        assertEq("myString", StringList.escapeSpace("myString"));
    }
    @Test
    public void escapeSpace2Test(){
        assertEq("my\\?\\.String end", StringList.escapeSpace("my\\?\\.String\\ end"));
    }
    @Test
    public void escapeSpace3Test(){
        assertEq("myString end", StringList.escapeSpace("myString\\ end"));
    }
    @Test
    public void escapeSpace4Test(){
        assertEq("myString\\end", StringList.escapeSpace("myString\\\\end"));
    }
    @Test
    public void escapeSpace5Test(){
        assertEq("myString\\", StringList.escapeSpace("myString\\\\"));
    }
    @Test
    public void escapeSpace6Test(){
        assertEq("myString\\", StringList.escapeSpace("myString\\"));
    }
    @Test
    public void escapeSpace7Test(){
        assertEq("my\\String ", StringList.escapeSpace("my\\\\String\\ "));
    }
    @Test
    public void escapeSpace8Test(){
        assertEq("my String\\", StringList.escapeSpace("my\\ String\\\\"));
    }
    @Test
    public void escapeSpace9Test(){
        assertEq("my\\String end", StringList.escapeSpace("my\\\\String\\ end"));
    }
    @Test
    public void skipStringUntil1Test(){
        assertEq("IX", StringList.skipStringUntil("PREFIX", "F"));
    }
    @Test
    public void splitChar1Test(){
        StringList res_;
        res_ = StringList.splitChar("HELLO WORLD", ' ');
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq("WORLD", res_.get(1));
    }
    @Test
    public void splitChar2Test(){
        StringList res_;
        res_ = StringList.splitChar("HELLO WORLD", 'A');
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitChar3Test(){
        StringList res_;
        res_ = StringList.splitChar("", '1');
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitChar4Test(){
        StringList res_;
        res_ = StringList.splitChar("HELLO WORLD", 'L');
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitChars1Test(){
        StringList res_;
        res_ = StringList.splitChars("HELLO WORLD", ' ');
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq("WORLD", res_.get(1));
    }
    @Test
    public void splitChars2Test(){
        StringList res_;
        res_ = StringList.splitChars("HELLO WORLD", 'A');
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitChars3Test(){
        StringList res_;
        res_ = StringList.splitChars("", '1');
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitChars4Test(){
        StringList res_;
        res_ = StringList.splitChars("HELLO WORLD", 'L');
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitChars5Test(){
        StringList res_;
        res_ = StringList.splitChars("HELLO WORLD", 'L', 'O');
        assertEq(6, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("", res_.get(2));
        assertEq(" W", res_.get(3));
        assertEq("R", res_.get(4));
        assertEq("D", res_.get(5));
    }
    @Test
    public void splitCharsSep1Test(){
        StringList res_;
        res_ = StringList.splitCharSep("HELLO WORLD", ' ');
        assertEq(3, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq(" ", res_.get(1));
        assertEq("WORLD", res_.get(2));
    }
    @Test
    public void splitCharsSep2Test(){
        StringList res_;
        res_ = StringList.splitCharSep("HELLO WORLD", 'A');
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitCharsSep3Test(){
        StringList res_;
        res_ = StringList.splitCharSep("", '1');
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitCharsSep4Test(){
        StringList res_;
        res_ = StringList.splitCharSep("HELLO WORLD", 'L');
        assertEq(7, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("L", res_.get(1));
        assertEq("", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("O WOR", res_.get(4));
        assertEq("L", res_.get(5));
        assertEq("D", res_.get(6));
    }
    @Test
    public void splitStrings1Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", " ");
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq("WORLD", res_.get(1));
    }
    @Test
    public void splitStrings2Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "A");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStrings3Test(){
        StringList res_;
        res_ = StringList.splitStrings("", "1");
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitStrings4Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "L");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitStrings5Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "L", "O");
        assertEq(6, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("", res_.get(2));
        assertEq(" W", res_.get(3));
        assertEq("R", res_.get(4));
        assertEq("D", res_.get(5));
    }
    @Test
    public void splitStrings6Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "LL", "L");
        assertEq(3, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("O WOR", res_.get(1));
        assertEq("D", res_.get(2));
    }
    @Test
    public void splitStrings7Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "L", "LL");
        assertEq(3, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("O WOR", res_.get(1));
        assertEq("D", res_.get(2));
    }
    @Test
    public void splitStrings8Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "L", "LD");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("", res_.get(3));
    }
    @Test
    public void splitStrings9Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "L", "LW");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitStrings10Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "L", "LDW");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitStrings11Test(){
        StringList res_;
        res_ = StringList.splitStrings("HELLO WORLD", "LDW");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStringsSep1Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", " ");
        assertEq(3, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq(" ", res_.get(1));
        assertEq("WORLD", res_.get(2));
    }
    @Test
    public void splitStringsSep2Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "A");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStringsSep3Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("", "1");
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitStringsSep4Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "L");
        assertEq(7, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("L", res_.get(1));
        assertEq("", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("O WOR", res_.get(4));
        assertEq("L", res_.get(5));
        assertEq("D", res_.get(6));
    }
    @Test
    public void splitStringsSep5Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "L", "O");
        assertEq(11, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("L", res_.get(1));
        assertEq("", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("", res_.get(4));
        assertEq("O", res_.get(5));
        assertEq(" W", res_.get(6));
        assertEq("O", res_.get(7));
        assertEq("R", res_.get(8));
        assertEq("L", res_.get(9));
        assertEq("D", res_.get(10));
    }
    @Test
    public void splitStringsSep6Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "LL", "L");
        assertEq(5, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("LL", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("D", res_.get(4));
    }
    @Test
    public void splitStringsSep7Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "L", "LL");
        assertEq(5, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("LL", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("D", res_.get(4));
    }
    @Test
    public void splitStringsSep8Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "L", "LD");
        assertEq(7, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("L", res_.get(1));
        assertEq("", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("O WOR", res_.get(4));
        assertEq("LD", res_.get(5));
        assertEq("", res_.get(6));
    }
    @Test
    public void splitStringsSep9Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "L", "LW");
        assertEq(7, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("L", res_.get(1));
        assertEq("", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("O WOR", res_.get(4));
        assertEq("L", res_.get(5));
        assertEq("D", res_.get(6));
    }
    @Test
    public void splitStringsSep10Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "L", "LDW");
        assertEq(7, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("L", res_.get(1));
        assertEq("", res_.get(2));
        assertEq("L", res_.get(3));
        assertEq("O WOR", res_.get(4));
        assertEq("L", res_.get(5));
        assertEq("D", res_.get(6));
    }
    @Test
    public void splitStringsSep11Test(){
        StringList res_;
        res_ = StringList.splitStringsSep("HELLO WORLD", "LDW");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitInTwo1Test(){
        StringList res_;
        res_ = StringList.splitInTwo("HELLO WORLD", -1);
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitInTwo2Test(){
        StringList res_;
        res_ = StringList.splitInTwo("HELLO WORLD", 5);
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq(" WORLD", res_.get(1));
    }
    @Test
    public void wrapContent1Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 5, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent2Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 4, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent3Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 10, true);
        assertEq("Hello\nevery body", lines_);
    }
    @Test
    public void wrapContent4Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 11, true);
        assertEq("Hello every\nbody", lines_);
    }
    @Test
    public void wrapContent5Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 9, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent6Test(){
        String text_ = "Hello every\nbody";
        String lines_ = StringList.wrapContent(text_, 9, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent7Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 5, false);
        assertEq("Hello\nevery\nbody\n", lines_);
    }
    @Test
    public void wrapContent8Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 4, false);
        assertEq("Hello\nevery\nbody\n", lines_);
    }
    @Test
    public void wrapContent9Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 10, false);
        assertEq("Hello\nevery body\n", lines_);
    }
    @Test
    public void wrapContent10Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 11, false);
        assertEq("Hello every\nbody\n", lines_);
    }
    @Test
    public void wrapContent11Test(){
        String text_ = "Hello every body";
        String lines_ = StringList.wrapContent(text_, 9, false);
        assertEq("Hello\nevery\nbody\n", lines_);
    }
    @Test
    public void wrapContent12Test(){
        String text_ = "Hello every\nbody";
        String lines_ = StringList.wrapContent(text_, 9, false);
        assertEq("Hello\nevery\n\nbody\n", lines_);
    }
    @Test
    public void replaceFinalFile1Test(){
        String file_ = "word";
        String finalFile_ = StringList.replaceFinalFile(file_);
        assertEq("word", finalFile_);
    }
    @Test
    public void replaceFinalFile2Test(){
        String file_ = "word.txt";
        String finalFile_ = StringList.replaceFinalFile(file_);
        assertEq("word.txt", finalFile_);
    }
    @Test
    public void replaceFinalFile3Test(){
        String file_ = "word/txt";
        String finalFile_ = StringList.replaceFinalFile(file_);
        assertEq("word/txt", finalFile_);
    }
    @Test
    public void replaceFinalFile4Test(){
        String file_ = "folder/word.txt";
        String finalFile_ = StringList.replaceFinalFile(file_);
        assertEq("folder", finalFile_);
    }
    @Test
    public void replaceFinalFile5Test(){
        String file_ = "folder/word.txt.txt";
        String finalFile_ = StringList.replaceFinalFile(file_);
        assertEq("folder/word.txt.txt", finalFile_);
    }
    @Test
    public void replaceFinalFile6Test(){
        String file_ = "folder/.txt";
        String finalFile_ = StringList.replaceFinalFile(file_);
        assertEq("folder/.txt", finalFile_);
    }
    @Test
    public void replaceBkSlash1Test(){
        String file_ = "word/txt";
        String finalFile_ = StringList.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash2Test(){
        String file_ = "word\\txt";
        String finalFile_ = StringList.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash3Test(){
        String file_ = "word/txt/";
        String finalFile_ = StringList.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash4Test(){
        String file_ = "word\\txt\\";
        String finalFile_ = StringList.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash5Test(){
        String file_ = "word/txt/.";
        String finalFile_ = StringList.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash6Test(){
        String file_ = "word\\txt\\.";
        String finalFile_ = StringList.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void getWordsSeparators1Test(){
        String string_ = ";./:";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(1, parts_.size());
        assertEq(";./:", parts_.first());
    }
    @Test
    public void getWordsSeparators2Test(){
        String string_ = "hello";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(2, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getWordsSeparators3Test(){
        String string_ = ";hello";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(2, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getWordsSeparators4Test(){
        String string_ = "hello;";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(3, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getWordsSeparators5Test(){
        String string_ = ":hello;";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(3, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getWordsSeparators6Test(){
        String string_ = ":hello;world!";
        StringList parts_ = StringList.getWordsSeparators(string_);
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
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getWordsSeparators8Test(){
        String string_ = ":hello;,world";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getWordsSeparators9Test(){
        String string_ = "";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(0, parts_.size());
    }
    @Test
    public void getWordsSeparators10Test(){
        String string_ = ":hello_one;,world_one";
        StringList parts_ = StringList.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello_one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world_one", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators1Test(){
        String string_ = ";./:";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(1, parts_.size());
        assertEq(";./:", parts_.first());
    }
    @Test
    public void getDollarWordSeparators2Test(){
        String string_ = "hello";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(2, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getDollarWordSeparators3Test(){
        String string_ = ";hello";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(2, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getDollarWordSeparators4Test(){
        String string_ = "hello;";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(3, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getDollarWordSeparators5Test(){
        String string_ = ":hello;";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(3, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getDollarWordSeparators6Test(){
        String string_ = ":hello;world!";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(5, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
        assertEq("!", parts_.get(4));
    }
    @Test
    public void getDollarWordSeparators7Test(){
        String string_ = ":hello;world";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators8Test(){
        String string_ = ":hello;,world";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators9Test(){
        String string_ = "";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(0, parts_.size());
    }
    @Test
    public void getDollarWordSeparators10Test(){
        String string_ = ":hello_one;,world_one";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello_one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world_one", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators11Test(){
        String string_ = ":hello$one;,world$one";
        StringList parts_ = StringList.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello$one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world$one", parts_.get(3));
    }
    @Test
    public void replaceWordsJoin1Test(){
        String string_ = "";
        String parts_ = StringList.replaceWordsJoin(string_,new StringMap<String>());
        assertEq("", parts_);
    }
    @Test
    public void replaceWordsJoin2Test(){
        String string_ = "HELLO";
        String parts_ = StringList.replaceWordsJoin(string_,new StringMap<String>());
        assertEq("HELLO", parts_);
    }
    @Test
    public void replaceWordsJoin3Test(){
        String string_ = "";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO", "WORLD");
        String parts_ = StringList.replaceWordsJoin(string_,rep_);
        assertEq("", parts_);
    }
    @Test
    public void replaceWordsJoin4Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO", "WORLD");
        String parts_ = StringList.replaceWordsJoin(string_,rep_);
        assertEq("WORLD", parts_);
    }
    @Test
    public void replaceWordsJoin5Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("WORLD", "HELLO");
        String parts_ = StringList.replaceWordsJoin(string_,rep_);
        assertEq("HELLO", parts_);
    }
    @Test
    public void replaceWordsJoin6Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO_WORLD", "WORLD");
        String parts_ = StringList.replaceWordsJoin(string_,rep_);
        assertEq("HELLO", parts_);
    }
    @Test
    public void getWordsSeparatorsPrefix1Test(){
        String string_ = "";
        StringList parts_ = StringList.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(0, parts_.size());
    }
    @Test
    public void getWordsSeparatorsPrefix2Test(){
        String string_ = "word_one;hello;word_two";
        StringList parts_ = StringList.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(4, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
    }
    @Test
    public void getWordsSeparatorsPrefix3Test(){
        String string_ = "word_one;hello;word_two;world";
        StringList parts_ = StringList.getWordsSeparatorsPrefix(string_,"word_");
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
        StringList parts_ = StringList.getWordsSeparatorsPrefix(string_,"word_");
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
        StringList parts_ = StringList.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(4, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
    }
    @Test
    public void getWordsSeparatorsPrefix6Test(){
        String string_ = ";word_one;hello;word_two;world";
        StringList parts_ = StringList.getWordsSeparatorsPrefix(string_,"word_");
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
        StringList parts_ = StringList.getWordsSeparatorsPrefix(string_,"word_");
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
    public void getTokensSets1Test(){
        String string_ = ";word_one;hello;word_two;word_three;world";
        StringList parts_ = StringList.getTokensSets(string_);
        assertEq(1, parts_.size());
        assertEq(";word_one;hello;word_two;word_three;world", parts_.get(0));
    }
    @Test
    public void getTokensSets2Test(){
        String string_ = ";word_one;{hello};word_two;word_three;world";
        StringList parts_ = StringList.getTokensSets(string_);
        assertEq(3, parts_.size());
        assertEq(";word_one;", parts_.get(0));
        assertEq("{hello}", parts_.get(1));
        assertEq(";word_two;word_three;world", parts_.get(2));
    }
    @Test
    public void getTokensSets3Test(){
        String string_ = ";word_one;{};word_two;word_three;world";
        StringList parts_ = StringList.getTokensSets(string_);
        assertEq(1, parts_.size());
        assertEq(";word_one;{};word_two;word_three;world", parts_.get(0));
    }
    @Test
    public void getTokensSets4Test(){
        String string_ = ";word_one;{;word_two;word_three;world";
        StringList parts_ = StringList.getTokensSets(string_);
        assertEq(1, parts_.size());
        assertEq(";word_one;{;word_two;word_three;world", parts_.get(0));
    }
    @Test
    public void encode1Test() {
        byte[] bs_ = StringList.encode("a");
        assertEq(1, bs_.length);
        assertEq(97,bs_[0]);
    }
    @Test
    public void encode2Test() {
        byte[] bs_ = StringList.encode("\u0090");
        assertEq(2, bs_.length);
        assertEq(-62,bs_[0]);
        assertEq(-112,bs_[1]);
    }
    @Test
    public void encode3Test() {
        byte[] bs_ = StringList.encode("\u0b00");
        assertEq(3, bs_.length);
        assertEq(-32,bs_[0]);
        assertEq(-84,bs_[1]);
        assertEq(-128,bs_[2]);
    }
    @Test
    public void encode4Test() {
        byte[] bs_ = StringList.encode("\ub000");
        assertEq(3, bs_.length);
        assertEq(-21,bs_[0]);
        assertEq(-128,bs_[1]);
        assertEq(-128,bs_[2]);
    }
    @Test
    public void encode5Test() {
        byte[] bs_ = StringList.encode("");
        assertEq(0, bs_.length);
    }
    @Test
    public void decode1Test() {
        assertEq("a",StringList.decode(Numbers.wrapByteArray((byte)97)));
    }
    @Test
    public void decode2Test() {
        assertEq("\u0090",StringList.decode(Numbers.wrapByteArray((byte)-62,(byte)-112)));
    }
    @Test
    public void decode3Test() {
        assertEq("\u0b00",StringList.decode(Numbers.wrapByteArray((byte)-32,(byte)-84,(byte)-128)));
    }
    @Test
    public void decode4Test() {
        assertEq("\ub000",StringList.decode(Numbers.wrapByteArray((byte)-21,(byte)-128,(byte)-128)));
    }
    @Test
    public void decode5Test() {
        assertEq("",StringList.decode(Numbers.wrapByteArray()));
    }
    @Test
    public void decode6Test() {
        assertNull(StringList.decode(Numbers.wrapByteArray((byte)-1)));
    }
    @Test
    public void decode7Test() {
        assertNull(StringList.decode(Numbers.wrapByteArray((byte)-63,(byte)-1)));
    }
    @Test
    public void decode8Test() {
        assertNull(StringList.decode(Numbers.wrapByteArray((byte)-16,(byte)-1)));
    }
    @Test
    public void decode9Test() {
        assertNull(StringList.decode(Numbers.wrapByteArray((byte)-17,(byte)-64)));
    }
    @Test
    public void decode10Test() {
        assertNull(StringList.decode(Numbers.wrapByteArray((byte)-17,(byte)-65)));
    }
    @Test
    public void decode11Test() {
        assertNull(StringList.decode(Numbers.wrapByteArray((byte)-17,(byte)-65,(byte)-64)));
    }
    @Test
    public void badDecode1Test() {
        assertEq(-1,StringList.badDecode(Numbers.wrapByteArray((byte)97),0,1));
    }
    @Test
    public void badDecode2Test() {
        assertEq(-1,StringList.badDecode(Numbers.wrapByteArray((byte)-62,(byte)-112),0,2));
    }
    @Test
    public void badDecode3Test() {
        assertEq(-1,StringList.badDecode(Numbers.wrapByteArray((byte)-32,(byte)-84,(byte)-128),0,3));
    }
    @Test
    public void badDecode4Test() {
        assertEq(-1,StringList.badDecode(Numbers.wrapByteArray((byte)-21,(byte)-128,(byte)-128),0,3));
    }
    @Test
    public void badDecode5Test() {
        assertEq(-1,StringList.badDecode(Numbers.wrapByteArray(),0,0));
    }
    @Test
    public void badDecode6Test() {
        assertEq(0,StringList.badDecode(Numbers.wrapByteArray((byte)-1),0,1));
    }
    @Test
    public void badDecode7Test() {
        assertEq(0,StringList.badDecode(Numbers.wrapByteArray((byte)-63,(byte)-1),0,2));
    }
    @Test
    public void badDecode8Test() {
        assertEq(0,StringList.badDecode(Numbers.wrapByteArray((byte)-16,(byte)-1),0,2));
    }
    @Test
    public void badDecode9Test() {
        assertEq(0,StringList.badDecode(Numbers.wrapByteArray((byte)-17,(byte)-64),0,2));
    }
    @Test
    public void badDecode10Test() {
        assertEq(0,StringList.badDecode(Numbers.wrapByteArray((byte)-17,(byte)-65),0,2));
    }
    @Test
    public void badDecode11Test() {
        assertEq(0,StringList.badDecode(Numbers.wrapByteArray((byte)-17,(byte)-65,(byte)-64),0,3));
    }
    @Test
    public void toLowerCaseTest() {
        assertEq("0",StringList.toLowerCase("0"));
    }
    @Test
    public void toUpperCaseTest() {
        assertEq("0",StringList.toUpperCase("0"));
    }
    @Test
    public void eqStrings1Test(){
        assertTrue(StringList.eqStrings(new StringList(),new StringList()));
    }
    @Test
    public void eqStrings2Test(){
        assertTrue(StringList.eqStrings(new StringList("a"),new StringList("a")));
    }
    @Test
    public void eqStrings3Test(){
        assertTrue(!StringList.eqStrings(new StringList("a","b"),new StringList("b","a")));
    }
    @Test
    public void eqStrings4Test(){
        assertTrue(!StringList.eqStrings(new StringList("a","a","b"),new StringList("a","b","b")));
    }
    @Test
    public void eqStrings5Test(){
        assertTrue(!StringList.eqStrings(new StringList("a","b"),new StringList("a")));
    }
    @Test
    public void eqStrings6Test(){
        assertTrue(!StringList.eqStrings(new StringList("b"),new StringList("a","b")));
    }
    @Test
    public void equalsSet1Test(){
        assertTrue(StringList.equalsSet(new StringList(),new StringList()));
    }
    @Test
    public void equalsSet2Test(){
        assertTrue(StringList.equalsSet(new StringList("a"),new StringList("a")));
    }
    @Test
    public void equalsSet3Test(){
        assertTrue(StringList.equalsSet(new StringList("a","b"),new StringList("b","a")));
    }
    @Test
    public void equalsSet4Test(){
        assertTrue(StringList.equalsSet(new StringList("a","a","b"),new StringList("a","b","b")));
    }
    @Test
    public void equalsSet5Test(){
        assertTrue(!StringList.equalsSet(new StringList("a","b"),new StringList("a")));
    }
    @Test
    public void equalsSet6Test(){
        assertTrue(!StringList.equalsSet(new StringList("b"),new StringList("a","b")));
    }
    @Test
    public void equalsStringListSet1Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        CustList<StringList> lTwo_ =new CustList<StringList>();
        assertTrue(StringList.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet2Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("a"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("a"));
        assertTrue(StringList.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet3Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("a"));
        lOne_.add(new StringList("b"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("b"));
        lTwo_.add(new StringList("a"));
        assertTrue(StringList.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet4Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("a"));
        lOne_.add(new StringList("a"));
        lOne_.add(new StringList("b"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("b"));
        lTwo_.add(new StringList("b"));
        lTwo_.add(new StringList("a"));
        assertTrue(StringList.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet5Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("a"));
        lOne_.add(new StringList("b"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("a"));
        assertTrue(!StringList.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet6Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("b"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("a"));
        lTwo_.add(new StringList("b"));
        assertTrue(!StringList.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void eq1Test(){
        assertTrue(!StringList.eq("",null));
    }
    @Test
    public void eq2Test(){
        assertTrue(!StringList.eq(null,""));
    }
    @Test
    public void eq3Test(){
        assertTrue(!StringList.eq("a","b"));
    }
    @Test
    public void eq4Test(){
        assertTrue(StringList.eq(null,null));
    }
    @Test
    public void eq5Test(){
        assertTrue(StringList.eq("a","a"));
    }
    @Test
    public void removeDuplicates1Test() {
        StringList l_ =new StringList();
        l_.removeDuplicates();
        assertEq(0,l_.size());
    }
    @Test
    public void removeDuplicates2Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.removeDuplicates();
        assertEq(1,l_.size());
        assertEq("a",l_.get(0));
    }
    @Test
    public void removeDuplicates3Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        l_.removeDuplicates();
        assertEq(2,l_.size());
        assertEq("a",l_.get(0));
        assertEq("b",l_.get(1));
    }
    @Test
    public void removeDuplicates4Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("a");
        l_.removeDuplicates();
        assertEq(1,l_.size());
        assertEq("a",l_.get(0));
    }
    @Test
    public void getFirstPrintableCharIndex1Test() {
        assertEq(-1,StringList.getFirstPrintableCharIndex(""));
    }
    @Test
    public void getFirstPrintableCharIndex2Test() {
        assertEq(-1,StringList.getFirstPrintableCharIndex(" "));
    }
    @Test
    public void getFirstPrintableCharIndex3Test() {
        assertEq(0,StringList.getFirstPrintableCharIndex("a"));
    }
    @Test
    public void getFirstPrintableCharIndex4Test() {
        assertEq(1,StringList.getFirstPrintableCharIndex(" a"));
    }
    @Test
    public void getFirstPrintableCharIndex5Test() {
        assertEq(0,StringList.getFirstPrintableCharIndex("a "));
    }
    @Test
    public void getLastPrintableCharIndex1Test() {
        assertEq(-1,StringList.getLastPrintableCharIndex(""));
    }
    @Test
    public void getLastPrintableCharIndex2Test() {
        assertEq(-1,StringList.getLastPrintableCharIndex(" "));
    }
    @Test
    public void getLastPrintableCharIndex3Test() {
        assertEq(0,StringList.getLastPrintableCharIndex("a"));
    }
    @Test
    public void getLastPrintableCharIndex4Test() {
        assertEq(0,StringList.getLastPrintableCharIndex("a "));
    }
    @Test
    public void getLastPrintableCharIndex5Test() {
        assertEq(1,StringList.getLastPrintableCharIndex(" a"));
    }
    @Test
    public void getFirstToken1Test(){
        assertEq("hell",StringList.getFirstToken("hello world","o"));
    }
    @Test
    public void getFirstToken2Test(){
        assertEq("hello world",StringList.getFirstToken("hello world","a"));
    }
    @Test
    public void getFirstToken3Test(){
        assertEq("hell",StringList.getFirstToken("hello world",'o'));
    }
    @Test
    public void getFirstToken4Test(){
        assertEq("hello world",StringList.getFirstToken("hello world",'a'));
    }
    @Test
    public void replaceMultTest() {
        Replacement rOne_  =new Replacement();
        rOne_.setNewString("after");
        rOne_.setOldString("before");
        Replacement rTwo_  =new Replacement();
        rTwo_.setNewString("unknown");
        rTwo_.setOldString("known");
        assertEq("after",StringList.replaceMult("before",rOne_,rTwo_));
    }
    @Test
    public void removeStringsTest() {
        assertEq("bfor",StringList.removeStrings("before","e"));
    }
    @Test
    public void removeCharsTest() {
        assertEq("bfor",StringList.removeChars("before",'e'));
    }
    @Test
    public void removeAllSpacesTest() {
        assertEq("before",StringList.removeAllSpaces("be fore"));
    }
    @Test
    public void filterByMultiWordsTest() {
        StringList list_ = new StringList();
        list_.add(null);
        list_.add("hello");
        list_.add("world");
        StringList f_ = list_.filterByMultiWords("world");
        assertEq(1, f_.size());
        assertEq("world", f_.get(0));
    }
    @Test
    public void filterEndsWithTest() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList f_ = list_.filterEndsWith("world");
        assertEq(1, f_.size());
        assertEq("world", f_.get(0));
    }
    @Test
    public void filterBeginsWithTest() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList f_ = list_.filterBeginsWith("world");
        assertEq(1, f_.size());
        assertEq("world", f_.get(0));
    }
    @Test
    public void interscectTest() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList listTwo_ = new StringList();
        listTwo_.add("hellos");
        listTwo_.add("world");
        StringList f_ = list_.intersect(listTwo_);
        assertEq(1, f_.size());
        assertEq("world", f_.get(0));
    }
    @Test
    public void disjoints1Test() {
        CustList<StringList> lists_ = new CustList<StringList>();
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        lists_.add(list_);
        StringList listTwo_ = new StringList();
        listTwo_.add("hellos");
        listTwo_.add("world");
        lists_.add(listTwo_);
        assertTrue(!StringList.disjoints(lists_));
    }
    @Test
    public void disjoints2Test() {
        CustList<StringList> lists_ = new CustList<StringList>();
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        lists_.add(list_);
        StringList listTwo_ = new StringList();
        listTwo_.add("hellos");
        listTwo_.add("worlds");
        lists_.add(listTwo_);
        assertTrue(StringList.disjoints(lists_));
    }
    @Test
    public void retainAllElementsTest(){
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList listTwo_ = new StringList();
        listTwo_.add("hellos");
        listTwo_.add("world");
        list_.retainAllElements(listTwo_);
        assertEq(1, list_.size());
        assertEq("world", list_.get(0));
    }
    @Test
    public void removePrefixInStringsTest(){
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        list_.removePrefixInStrings("h");
        assertEq(2, list_.size());
        assertEq("ello", list_.get(0));
        assertEq("world", list_.get(1));
    }
    @Test
    public void replaceExtension1Test() {
        assertEq("file.log", StringList.replaceExtension("file.txt",".txt",".log"));
    }
    @Test
    public void replaceExtension2Test() {
        assertEq("file.txt", StringList.replaceExtension("file.txt",".log",".txt"));
    }
    @Test
    public void getReverseTest() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList f_ = list_.getReverse();
        assertEq(2, f_.size());
        assertEq("world", f_.get(0));
        assertEq("hello", f_.get(1));
    }
    @Test
    public void replaceEltTest() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        list_.replace("hello","good morning");
        assertEq(2, list_.size());
        assertEq("good morning", list_.get(0));
        assertEq("world", list_.get(1));
    }
    @Test
    public void removeAllElementsTest() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList listTwo_ = new StringList();
        listTwo_.add("hello");
        listTwo_.add("worlds");
        list_.removeAllElements(listTwo_);
        assertEq(1, list_.size());
        assertEq("world", list_.get(0));
    }
    @Test
    public void containsAllObj1Test() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList listTwo_ = new StringList();
        listTwo_.add("hello");
        assertTrue(list_.containsAllObj(listTwo_));
    }
    @Test
    public void containsAllObj2Test() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList listTwo_ = new StringList();
        listTwo_.add("hello");
        listTwo_.add("worlds");
        assertTrue(!list_.containsAllObj(listTwo_));
    }
    @Test
    public void startsWith1Test() {
        assertTrue(StringList.startsWith("hello","hello"));
    }
    @Test
    public void startsWith2Test() {
        assertTrue(!StringList.startsWith("hello","world"));
    }
    @Test
    public void endsWith1Test() {
        assertTrue(StringList.endsWith("hello","hello"));
    }
    @Test
    public void endsWith2Test() {
        assertTrue(!StringList.endsWith("hello","world"));
    }
    @Test
    public void replaceBegin1Test() {
        assertEq("",StringList.replaceBegin("hello","hello"));
    }
    @Test
    public void replaceBegin2Test() {
        assertEq("hello",StringList.replaceBegin("hello","world"));
    }
    @Test
    public void replaceEnd1Test() {
        assertEq("",StringList.replaceEnd("hello","hello"));
    }
    @Test
    public void replaceEnd2Test() {
        assertEq("hello",StringList.replaceEnd("hello","world"));
    }
    @Test
    public void replaceExt1Test() {
        assertEq("hello",StringList.replaceExtension("hello"));
    }
    @Test
    public void replaceExt2Test() {
        assertEq("hello",StringList.replaceExtension("hello.txt"));
    }
    @Test
    public void replaceBackSlashesInStringsTest() {
        StringList list_ = new StringList();
        list_.add("hello\\world");
        list_.replaceBackSlashesInStrings();
        assertEq(1, list_.size());
        assertEq("hello/world", list_.get(0));
    }
    @Test
    public void toArrayTest() {
        StringList list_ = new StringList(new CollCapacity(1));
        list_.add("hello");
        String[] arr_ = list_.toArray();
        assertEq(1, arr_.length);
        assertEq("hello", arr_[0]);
    }
    @Test
    public void formatQuoteTest() {
        assertEq("hello", StringList.formatQuote("hello", new StringMap<String>()));
    }
    @Test
    public void concatNbTest() {
        assertEq("0hello", StringList.concatNb(0,"hello"));
    }
    @Test
    public void concatNbsTest() {
        assertEq("hello0", StringList.concatNbs("hello", 0));
    }
}