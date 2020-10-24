package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import org.junit.Test;



public class StringListTest {
    @Test
    public void sub1Test(){
        StringList s_ = new StringList(new CollCapacity(0));
        CustList<String> out_ = s_.sub(0, 0);
        assertEq(0, out_.size());
    }
    @Test
    public void sub2Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        CustList<String> out_ = s_.sub(1, 0);
        assertEq(0, out_.size());
    }
    @Test
    public void sub3Test(){
        StringList s_ = new StringList();
        s_.add("hello");
        s_.add("world");
        CustList<String> out_ = s_.sub(1, 2);
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
        assertEq(true, StringUtil.match("myString", ""));
    }
    @Test
    public void match2Test(){
        assertEq(true, StringUtil.match("myString", "myString"));
    }
    @Test
    public void match3Test(){
        assertEq(true, StringUtil.match("myString", "*......"));
    }
    @Test
    public void match4Test(){
        assertEq(true, StringUtil.match("myString", "......*"));
    }
    @Test
    public void match5Test(){
        assertEq(false, StringUtil.match("myString", "......?"));
    }
    @Test
    public void match6Test(){
        assertEq(true, StringUtil.match("myString", ".......?"));
    }
    @Test
    public void match7Test(){
        assertEq(true, StringUtil.match("myString", "........"));
    }
    @Test
    public void match8Test(){
        assertEq(false, StringUtil.match("myString", "........."));
    }
    @Test
    public void match9Test(){
        assertEq(false, StringUtil.match("myString", "???????"));
    }
    @Test
    public void match10Test(){
        assertEq(true, StringUtil.match("myString", "????????"));
    }
    @Test
    public void match11Test(){
        assertEq(true, StringUtil.match("myString", "m.....*"));
    }
    @Test
    public void match12Test(){
        assertEq(false, StringUtil.match("myString", "m.....?"));
    }
    @Test
    public void match13Test(){
        assertEq(true, StringUtil.match("myString", "m......?"));
    }
    @Test
    public void match14Test(){
        assertEq(true, StringUtil.match("myString", "m......."));
    }
    @Test
    public void match15Test(){
        assertEq(false, StringUtil.match("myString", "m........"));
    }
    @Test
    public void match16Test(){
        assertEq(false, StringUtil.match("myString", "m??????"));
    }
    @Test
    public void match17Test(){
        assertEq(true, StringUtil.match("myString", "m???????"));
    }
    @Test
    public void match18Test(){
        assertEq(true, StringUtil.match("myString", "myStri*"));
    }
    @Test
    public void match19Test(){
        assertEq(true, StringUtil.match("myString", "*String"));
    }
    @Test
    public void match20Test(){
        assertEq(false, StringUtil.match("myString", "\\*String"));
    }
    @Test
    public void match21Test(){
        assertEq(true, StringUtil.match("myString", "..String*"));
    }
    @Test
    public void match22Test(){
        assertEq(true, StringUtil.match("myString", "..Str?ing*"));
    }
    @Test
    public void match23Test(){
        assertEq(false, StringUtil.match("myString", ".String*"));
    }
    @Test
    public void match24Test(){
        assertEq(true, StringUtil.match("myString", "mySt..ng*"));
    }
    @Test
    public void match25Test(){
        assertEq(true, StringUtil.match("myString", ".ySt..ng*"));
    }
    @Test
    public void match26Test(){
        assertEq(false, StringUtil.match("myString", ".St?.ng*"));
    }
    @Test
    public void match27Test(){
        assertEq(false, StringUtil.match("myString", "?String*"));
    }
    @Test
    public void match28Test(){
        assertEq(true, StringUtil.match("myString", ".?String*"));
    }
    @Test
    public void match29Test(){
        assertEq(true, StringUtil.match("myString", "??String*"));
    }
    @Test
    public void match30Test(){
        assertEq(true, StringUtil.match("myString", "..?String*"));
    }
    @Test
    public void match31Test(){
        assertEq(true, StringUtil.match("myString", "???tring*"));
    }
    @Test
    public void match32Test(){
        assertEq(true, StringUtil.match("my.String", "my\\.String"));
    }
    @Test
    public void match33Test(){
        assertEq(true, StringUtil.match("my.*String", "my\\.\\*String"));
    }
    @Test
    public void match34Test(){
        assertEq(true, StringUtil.match("my.*String", "my\\..String"));
    }
    @Test
    public void match35Test(){
        assertEq(true, StringUtil.match("my.*?String", "my\\.\\*\\?String"));
    }
    @Test
    public void match36Test(){
        assertEq(true, StringUtil.match("my.*?\\String", "my\\.\\*\\?\\\\String"));
    }
    @Test
    public void match37Test(){
        assertEq(true, StringUtil.match("my.*?\\String", "my\\.\\*\\?\\String"));
    }
    @Test
    public void match38Test(){
        assertEq(false, StringUtil.match("my.*?\\\\String", "my\\.\\*\\?\\String"));
    }
    @Test
    public void match39Test(){
        assertTrue(StringUtil.match("myString","*String"));
    }
    @Test
    public void match40Test(){
        assertTrue(StringUtil.match("myStringString","*String"));
    }
    @Test
    public void match41Test(){
        assertTrue(!StringUtil.match("myStringStrin","*String"));
    }
    @Test
    public void match42Test(){
        assertTrue(StringUtil.match("myStrinString","*String"));
    }
    @Test
    public void match43Test(){
        assertTrue(StringUtil.match("myString","*y*String"));
    }
    @Test
    public void match44Test(){
        assertTrue(StringUtil.match("myStringString","*y*String"));
    }
    @Test
    public void match45Test(){
        assertTrue(!StringUtil.match("myStringStrin","*y*String"));
    }
    @Test
    public void match46Test(){
        assertTrue(StringUtil.match("myStrinString","*y*String"));
    }
    @Test
    public void match47Test(){
        assertTrue(!StringUtil.match("myStrinString","*ys*String"));
    }
    @Test
    public void match48Test(){
        assertTrue(!StringUtil.match("myStrinString","*yS*Strig"));
    }
    @Test
    public void match49Test(){
        assertTrue(!StringUtil.match("myStrinString","*yS*Strin"));
    }
    @Test
    public void matchSpace1Test(){
        assertEq(true, StringUtil.matchSpace("myString", ""));
    }
    @Test
    public void matchSpace2Test(){
        assertEq(true, StringUtil.matchSpace("myString", "myString"));
    }
    @Test
    public void matchSpace3Test(){
        assertEq(false, StringUtil.matchSpace("myString", "myStrings"));
    }
    @Test
    public void matchSpace4Test(){
        assertEq(false, StringUtil.matchSpace("myString", "my Strings"));
    }
    @Test
    public void matchSpace5Test(){
        assertEq(false, StringUtil.matchSpace("myString", "myStrin"));
    }
    @Test
    public void matchSpace6Test(){
        assertEq(false, StringUtil.matchSpace("myString", "my Strin"));
    }
    @Test
    public void matchSpace7Test(){
        assertEq(true, StringUtil.matchSpace("myString", " "));
    }
    @Test
    public void matchSpace8Test(){
        assertEq(false, StringUtil.matchSpace("myString", " \\  "));
    }
    @Test
    public void matchSpace9Test(){
        assertEq(true, StringUtil.matchSpace("my String", " \\  "));
    }
    @Test
    public void matchSpace10Test(){
        assertEq(true, StringUtil.matchSpace("my \\String", " \\\\ "));
    }
    @Test
    public void matchSpace11Test(){
        assertEq(true, StringUtil.matchSpace("myString", "m "));
    }
    @Test
    public void matchSpace12Test(){
        assertEq(true, StringUtil.matchSpace("myString", "myStri "));
    }
    @Test
    public void matchSpace13Test(){
        assertEq(false, StringUtil.matchSpace("myString", "myStrn "));
    }
    @Test
    public void matchSpace14Test(){
        assertEq(true, StringUtil.matchSpace("myString", " String"));
    }
    @Test
    public void matchSpace15Test(){
        assertEq(false, StringUtil.matchSpace("myString", "\\ String"));
    }
    @Test
    public void matchSpace16Test(){
        assertEq(true, StringUtil.matchSpace("myString", "myString "));
    }
    @Test
    public void matchSpace17Test(){
        assertEq(false, StringUtil.matchSpace("myString", "myString\\ "));
    }
    @Test
    public void matchSpace18Test(){
        assertEq(true, StringUtil.matchSpace("myString", " myString"));
    }
    @Test
    public void matchSpace19Test(){
        assertEq(true, StringUtil.matchSpace("myString", "my String"));
    }
    @Test
    public void matchSpace20Test(){
        assertEq(true, StringUtil.matchSpace("myString", " my String "));
    }
    @Test
    public void matchSpace21Test(){
        assertEq(true, StringUtil.matchSpace("myString", " my Str ing"));
    }
    @Test
    public void matchSpace22Test(){
        assertEq(true, StringUtil.matchSpace("myString", " my Str in "));
    }
    @Test
    public void matchSpace23Test(){
        assertEq(false, StringUtil.matchSpace("myString", " my Str ins "));
    }
    @Test
    public void matchSpace24Test(){
        assertEq(true, StringUtil.matchSpace("myString", "my Str ing"));
    }
    @Test
    public void matchSpace25Test(){
        assertEq(false, StringUtil.matchSpace("myString", "my Str ings"));
    }
    @Test
    public void matchSpace26Test(){
        assertEq(false, StringUtil.matchSpace("myString", "my Str ing\\ "));
    }
    @Test
    public void matchSpace27Test(){
        assertEq(false, StringUtil.matchSpace("myString", "my Str ngs"));
    }
    @Test
    public void matchSpace28Test(){
        assertEq(false, StringUtil.matchSpace("myString", "my Stg ing"));
    }
    @Test
    public void matchSpace29Test(){
        assertEq(false, StringUtil.matchSpace("my.String", "my\\ String"));
    }
    @Test
    public void matchSpace30Test(){
        assertEq(true, StringUtil.matchSpace("my String", "my\\ String"));
    }
    @Test
    public void matchSpace31Test(){
        assertEq(true, StringUtil.matchSpace("my\\String", "my\\String"));
    }
    @Test
    public void matchSpace32Test(){
        assertEq(true, StringUtil.matchSpace("my\\String", "my\\\\String"));
    }
    @Test
    public void matchSpace33Test(){
        assertEq(false, StringUtil.matchSpace("my\\String", "my\\ String"));
    }
    @Test
    public void matchSpace34Test(){
        assertEq(false, StringUtil.matchSpace("my String", "my\\\\String"));
    }
    @Test
    public void greatestIndex1Test(){
        assertEq(-1, StringUtil.greatestIndex("one", "two", 0));
    }
    @Test
    public void greatestIndex2Test(){
        assertEq(3, StringUtil.greatestIndex("my_string", "str", 0));
    }
    @Test
    public void greatestIndex3Test(){
        assertEq(-1, StringUtil.greatestIndex("my_string", "str", 4));
    }
    @Test
    public void greatestIndex4Test(){
        assertEq(3, StringUtil.greatestIndex("my_string", "str", 3));
    }
    @Test
    public void greatestIndex5Test(){
        assertEq(10, StringUtil.greatestIndex("my_string_string", "str", 3));
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
        CustList<String> list_ = strings_.mid(1, 3);
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
        CustList<String> list_ = strings_.mid(1, 2);
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
        CustList<String> list_ = strings_.mid(1, 4);
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
        assertEq("MY_STRING_ONE;MY_STRING_TWO", StringUtil.join(list_, ";"));
    }
    @Test
    public void join2Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        list_.add("MY_STRING_TWO");
        assertEq("MY_STRING_ONE;MY_STRING_TWO", StringUtil.join(list_, ';'));
    }
    @Test
    public void join3Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        assertEq("MY_STRING_ONE", StringUtil.join(list_, ";"));
    }
    @Test
    public void join4Test(){
        StringList list_ = new StringList();
        list_.add("MY_STRING_ONE");
        assertEq("MY_STRING_ONE", StringUtil.join(list_, ';'));
    }
    @Test
    public void join5Test(){
        StringList list_ = new StringList();
        assertEq("", StringUtil.join(list_, ";"));
    }
    @Test
    public void join6Test(){
        StringList list_ = new StringList();
        assertEq("", StringUtil.join(list_, ';'));
    }
    @Test
    public void isNumber1Test(){
        assertTrue(StringUtil.isNumber("1"));
    }
    @Test
    public void isNumber2Test(){
        assertTrue(!StringUtil.isNumber("a"));
    }
    @Test
    public void isNumber3Test(){
        assertTrue(StringUtil.isNumber("-1"));
    }
    @Test
    public void isNumber4Test(){
        assertTrue(!StringUtil.isNumber("-a"));
    }
    @Test
    public void isNumber5Test(){
        assertTrue(!StringUtil.isNumber("-"));
    }
    @Test
    public void isNumber6Test(){
        assertTrue(!StringUtil.isNumber(""));
    }
    @Test
    public void isPositiveNumber1Test(){
        assertTrue(StringUtil.isPositiveNumber("1"));
    }
    @Test
    public void isPositiveNumber2Test(){
        assertTrue(!StringUtil.isPositiveNumber("a"));
    }
    @Test
    public void isPositiveNumber3Test(){
        assertTrue(!StringUtil.isPositiveNumber("-1"));
    }
    @Test
    public void isPositiveNumber4Test(){
        assertTrue(!StringUtil.isPositiveNumber("-a"));
    }
    @Test
    public void isPositiveNumber5Test(){
        assertTrue(!StringUtil.isPositiveNumber("-"));
    }
    @Test
    public void isPositiveNumber6Test(){
        assertTrue(!StringUtil.isPositiveNumber(""));
    }
    @Test
    public void isWord1Test(){
        assertTrue(StringUtil.isWord("1"));
    }
    @Test
    public void isWord2Test(){
        assertTrue(StringUtil.isWord("a"));
    }
    @Test
    public void isWord3Test(){
        assertTrue(!StringUtil.isWord("-1"));
    }
    @Test
    public void isWord4Test(){
        assertTrue(!StringUtil.isWord("-a"));
    }
    @Test
    public void isWord5Test(){
        assertTrue(!StringUtil.isWord("-"));
    }
    @Test
    public void isWord6Test(){
        assertTrue(!StringUtil.isWord(""));
    }
    @Test
    public void isWord7Test(){
        assertTrue(!StringUtil.isWord("$"));
    }
    @Test
    public void isDollarWord1Test(){
        assertTrue(StringUtil.isDollarWord("1"));
    }
    @Test
    public void isDollarWord2Test(){
        assertTrue(StringUtil.isDollarWord("a"));
    }
    @Test
    public void isDollarWord3Test(){
        assertTrue(!StringUtil.isDollarWord("-1"));
    }
    @Test
    public void isDollarWord4Test(){
        assertTrue(!StringUtil.isDollarWord("-a"));
    }
    @Test
    public void isDollarWord5Test(){
        assertTrue(!StringUtil.isDollarWord("-"));
    }
    @Test
    public void isDollarWord6Test(){
        assertTrue(!StringUtil.isDollarWord(""));
    }
    @Test
    public void isDollarWord7Test(){
        assertTrue(StringUtil.isDollarWord("$"));
    }
    @Test
    public void indexesOfSubString1Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfSubString("HELLO WORLD", "L");
        assertEq(3, indexes_.size());
        assertEq(2, indexes_.get(0));
        assertEq(3, indexes_.get(1));
        assertEq(9, indexes_.get(2));
    }
    @Test
    public void indexesOfSubString2Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfSubString("HELLO WORLD", "LL");
        assertEq(1, indexes_.size());
        assertEq(2, indexes_.get(0));
    }
    @Test
    public void indexesOfSubString3Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfSubString("HELLO WORLD", "LD");
        assertEq(1, indexes_.size());
        assertEq(9, indexes_.get(0));
    }
    @Test
    public void indexesOfSubString4Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfSubString("HELLO WORLD", "OO");
        assertEq(0, indexes_.size());
    }
    @Test
    public void indexesOfSubString5Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfSubString("HELLO WORLD", "");
        assertEq(1, indexes_.size());
        assertEq(0, indexes_.get(0));
    }
    @Test
    public void indexesOfSubString6Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfSubString("AAAA", "AA");
        assertEq(2, indexes_.size());
        assertEq(0, indexes_.get(0));
        assertEq(2, indexes_.get(1));
    }
    @Test
    public void indexesOfSubString7Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfSubString("AAA", "AA");
        assertEq(1, indexes_.size());
        assertEq(0, indexes_.get(0));
    }
    @Test
    public void indexesOfChar1Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfChar("HELLO WORLD", 'L');
        assertEq(3, indexes_.size());
        assertEq(2, indexes_.get(0));
        assertEq(3, indexes_.get(1));
        assertEq(9, indexes_.get(2));
    }
    @Test
    public void indexesOfChar2Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfChar("HELLO WORLD", 'Z');
        assertEq(0, indexes_.size());
    }
    @Test
    public void indexesOfChar3Test(){
        Ints indexes_;
        indexes_ = StringUtil.indexesOfChar("", 'Z');
        assertEq(0, indexes_.size());
    }
    @Test
    public void replace1Test(){
        String str_ = "AAAA";
        String old_ = "AA";
        String new_ = "B";
        assertEq("BB", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace2Test(){
        String str_ = "AAA";
        String old_ = "AA";
        String new_ = "B";
        assertEq("BA", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace3Test(){
        String str_ = "AAA";
        String old_ = "A";
        String new_ = "B";
        assertEq("BBB", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace4Test(){
        String str_ = "AAA";
        String old_ = "C";
        String new_ = "B";
        assertEq("AAA", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace5Test(){
        String str_ = "ACAA";
        String old_ = "AC";
        String new_ = "B";
        assertEq("BAA", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace6Test(){
        String str_ = "ACAC";
        String old_ = "AC";
        String new_ = "B";
        assertEq("BB", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace7Test(){
        String str_ = "ABCDE";
        String old_ = "ABCDEF";
        String new_ = "B";
        assertEq("ABCDE", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace8Test(){
        String str_ = "ABCDEF";
        String old_ = "ABCDEF";
        String new_ = "B";
        assertEq("B", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace9Test(){
        String str_ = "ABCDEF";
        String old_ = "ABCDE";
        String new_ = "B";
        assertEq("BF", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace10Test(){
        String str_ = "AC";
        String old_ = "";
        String new_ = "B";
        assertEq("BABCB", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace11Test(){
        String str_ = "AC";
        String old_ = "A";
        String new_ = null;
        assertEq("C", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace12Test(){
        String str_ = "AC";
        String old_ = "B";
        String new_ = null;
        assertEq("AC", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace13Test(){
        String str_ = "AA";
        String old_ = "A";
        String new_ = null;
        assertEq("", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace14Test(){
        String str_ = "AA";
        String old_ = "";
        String new_ = null;
        assertEq("AA", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void replace15Test(){
        String str_ = "AA";
        String old_ = null;
        String new_ = null;
        assertEq("AA", StringUtil.replace(str_, old_, new_));
    }
    @Test
    public void getFields1Test(){
        String html_ = "<html><body>composite.integer</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields2Test(){
        String html_ = "<html><body>{composite.integer}</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields3Test(){
        String html_ = "<html><body>'quoted'</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields4Test(){
        String html_ = "<html><body>{composite.integer} '{mycommented}'</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields5Test(){
        String html_ = "<html><body>'{mycommented}'</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields6Test(){
        String html_ = "<html><body>'{mycommented}' {composite.integer}</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields7Test(){
        String html_ = "<html><body>'{mycommented} {composite.integer}</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(0, fields_.size());
    }
    @Test
    public void getFields8Test(){
        String html_ = "<html><body>{composite.integer} '{mycommented}</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(1, fields_.size());
        assertEq("composite.integer",fields_.get(0));
    }
    @Test
    public void getFields9Test(){
        String html_ = "<html><body>{composite.integer} {mycommented}</body></html>'";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("mycommented",fields_.get(1));
    }
    @Test
    public void getFields10Test(){
        String html_ = "<html><body>{composite.integer} {mycommented}</body></html>''";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("mycommented",fields_.get(1));
    }
    @Test
    public void getFields11Test(){
        String html_ = "<html><body>{composite.integer} '{mycommented}' {compositeTwo.integer}</body></html>";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("compositeTwo.integer",fields_.get(1));
    }
    @Test
    public void getFields12Test(){
        String html_ = "<html><body>{composite.integer} {mycommented}</body></html>'quoted'";
        StringList fields_ = StringUtil.getFields(html_);
        assertEq(2, fields_.size());
        assertEq("composite.integer",fields_.get(0));
        assertEq("mycommented",fields_.get(1));
    }
    @Test
    public void simpleFormat1Test(){
        String pattern_ = "Without variable in string";
        assertEq(pattern_, StringUtil.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat2Test(){
        String pattern_ = "Without variable '' in string";
        String outPut_ = "Without variable ' in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat3Test(){
        String pattern_ = "With variables {0} {1} in string";
        String outPut_ = "With variables zero one in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat4Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat5Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables one zero in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat6Test(){
        String pattern_ = "With variables '{0} {1}' in string";
        String outPut_ = "With variables {0} {1} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_,"zero","one"));
    }
    @Test
    public void simpleFormat7Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables one zero in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero", "one", "two"));
    }
    @Test
    public void simpleFormat8Test(){
        String pattern_ = "With variables {0} {1} '{2}' in string";
        String outPut_ = "With variables zero one {2} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat9Test(){
        String pattern_ = "With variables {0} {1} {2} in string";
        String outPut_ = "With variables zero one {2} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat10Test(){
        String pattern_ = "With variables {0} {1} '{arg}' in string";
        String outPut_ = "With variables zero one {arg} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat11Test(){
        String pattern_ = "With variables {0} {1} {arg} in string";
        String outPut_ = "With variables zero one {arg} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero", "one"));
    }
    @Test
    public void simpleFormat12Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE} in string'";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_));
    }
    @Test
    public void simpleFormat13Test(){
        String pattern_ = "With variables {-1} in string";
        String outPut_ = "With variables {-1} in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, "zero"));
    }
    @Test
    public void simpleFormat14Test(){
        String pattern_ = "With variables {0} {1} in string";
        String outPut_ = "With variables zero one in string";
        assertEq(outPut_, StringUtil.simpleStringsFormat(pattern_, new StringList("zero", "one")));
    }
    @Test
    public void simpleFormatNb1Test(){
        String pattern_ = "Without variable in string";
        assertEq(pattern_, StringUtil.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb2Test(){
        String pattern_ = "Without variable '' in string";
        String outPut_ = "Without variable ' in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb3Test(){
        String pattern_ = "With variables {0} {1} in string";
        String outPut_ = "With variables 10 11 in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb4Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb5Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables 11 10 in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb6Test(){
        String pattern_ = "With variables '{0} {1}' in string";
        String outPut_ = "With variables {0} {1} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb7Test(){
        String pattern_ = "With variables {1} {0} in string";
        String outPut_ = "With variables 11 10 in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11,12));
    }
    @Test
    public void simpleFormatNb8Test(){
        String pattern_ = "With variables {0} {1} '{2}' in string";
        String outPut_ = "With variables 10 11 {2} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb9Test(){
        String pattern_ = "With variables {0} {1} {2} in string";
        String outPut_ = "With variables 10 11 {2} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb10Test(){
        String pattern_ = "With variables {0} {1} '{arg}' in string";
        String outPut_ = "With variables 10 11 {arg} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb11Test(){
        String pattern_ = "With variables {0} {1} {arg} in string";
        String outPut_ = "With variables 10 11 {arg} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10,11));
    }
    @Test
    public void simpleFormatNb12Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE} in string'";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_));
    }
    @Test
    public void simpleFormatNb13Test(){
        String pattern_ = "With variables {-1} in string";
        String outPut_ = "With variables {-1} in string";
        assertEq(outPut_, StringUtil.simpleNumberFormat(pattern_, 10));
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
        assertEq(pattern_, StringUtil.format(pattern_, '\'', new StringMap<String>()));
    }
    @Test
    public void format2Test(){
        String pattern_ = "Without variable '' in string";
        String outPut_ = "Without variable ' in string";
        assertEq(outPut_, StringUtil.format(pattern_, '\'', new StringMap<String>()));
    }
    @Test
    public void format3Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format4Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format5Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' {STRING_TWO} in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} {STRING_TWO} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format6Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} {S} in string";
        String outPut_ = "With variables zero one {S} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING}", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format7Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} {S} in string";
        String outPut_ = "With variables {STRING} one {S} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format8Test(){
        String pattern_ = "With variables {STRING} {STRING_ONE} {S} in string'''";
        String outPut_ = "With variables {STRING} one {S} in string'";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format9Test(){
        String pattern_ = "With variables '{STRING} {STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format10Test(){
        String pattern_ = "With variables '{STRING}' '{STRING_ONE}' in string";
        String outPut_ = "With variables {STRING} {STRING_ONE} in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{STRING_ONE}", "one");
        assertEq(outPut_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void format11Test(){
        String pattern_ = "Without variable in string ''";
        String outPut_ = "Without variable in string '";
        assertEq(outPut_, StringUtil.format(pattern_, '\'', new StringMap<String>()));
    }
    @Test
    public void format12Test(){
        String pattern_ = "With long variable in string";
        String outPut_ = "With variable in string ";
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry("With long variable in string ",outPut_);
        assertEq(pattern_, StringUtil.format(pattern_, '\'', map_));
    }
    @Test
    public void formatBasic1Test(){
        String pattern_ = "With variables STR STRING in string";
        String outPut_ = "With variables zero zeroING in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
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
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic3Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("NG", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic4Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables NG one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic5Test(){
        String pattern_ = "With variables STR STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic6Test(){
        String pattern_ = "With variables STR STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
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
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic8Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables zero one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("NG", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic9Test(){
        String pattern_ = "With variables NG STRING in string";
        String outPut_ = "With variables NG one in string";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic10Test(){
        String pattern_ = "With variables STRING STR";
        String outPut_ = "With variables zeroING zero";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic11Test(){
        String pattern_ = "With variables STR STRING";
        String outPut_ = "With variables zero one";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STR", "zero");
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic12Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables STR";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic13Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables STR";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic14Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables two";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void formatBasic15Test(){
        String pattern_ = "With variables STR";
        String outPut_ = "With variables two";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic16Test(){
        String pattern_ = "";
        String outPut_ = "";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, true));
    }
    @Test
    public void formatBasic17Test(){
        String pattern_ = "";
        String outPut_ = "";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("STRING", "one");
        map_.put("STR", "two");
        assertEq(outPut_, StringUtil.formatBasic(pattern_, map_, false));
    }
    @Test
    public void escape1Test(){
        assertEq("myString", StringUtil.escape("myString"));
    }
    @Test
    public void escape2Test(){
        assertEq("my?.String*end", StringUtil.escape("my\\?\\.String\\*end"));
    }
    @Test
    public void escape3Test(){
        assertEq("myString*end", StringUtil.escape("myString\\*end"));
    }
    @Test
    public void escape4Test(){
        assertEq("myString\\end", StringUtil.escape("myString\\\\end"));
    }
    @Test
    public void escape5Test(){
        assertEq("myString\\", StringUtil.escape("myString\\\\"));
    }
    @Test
    public void escape6Test(){
        assertEq("myString\\", StringUtil.escape("myString\\"));
    }
    @Test
    public void escape7Test(){
        assertEq("my\\String*", StringUtil.escape("my\\\\String\\*"));
    }
    @Test
    public void escape8Test(){
        assertEq("my*String\\", StringUtil.escape("my\\*String\\\\"));
    }
    @Test
    public void escape9Test(){
        assertEq("my\\String*end", StringUtil.escape("my\\\\String\\*end"));
    }
    @Test
    public void escapeSpace1Test(){
        assertEq("myString", StringUtil.escapeSpace("myString"));
    }
    @Test
    public void escapeSpace2Test(){
        assertEq("my\\?\\.String end", StringUtil.escapeSpace("my\\?\\.String\\ end"));
    }
    @Test
    public void escapeSpace3Test(){
        assertEq("myString end", StringUtil.escapeSpace("myString\\ end"));
    }
    @Test
    public void escapeSpace4Test(){
        assertEq("myString\\end", StringUtil.escapeSpace("myString\\\\end"));
    }
    @Test
    public void escapeSpace5Test(){
        assertEq("myString\\", StringUtil.escapeSpace("myString\\\\"));
    }
    @Test
    public void escapeSpace6Test(){
        assertEq("myString\\", StringUtil.escapeSpace("myString\\"));
    }
    @Test
    public void escapeSpace7Test(){
        assertEq("my\\String ", StringUtil.escapeSpace("my\\\\String\\ "));
    }
    @Test
    public void escapeSpace8Test(){
        assertEq("my String\\", StringUtil.escapeSpace("my\\ String\\\\"));
    }
    @Test
    public void escapeSpace9Test(){
        assertEq("my\\String end", StringUtil.escapeSpace("my\\\\String\\ end"));
    }
    @Test
    public void skipStringUntil1Test(){
        assertEq("IX", StringUtil.skipStringUntil("PREFIX", "F"));
    }
    @Test
    public void splitChar1Test(){
        StringList res_;
        res_ = StringUtil.splitChar("HELLO WORLD", ' ');
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq("WORLD", res_.get(1));
    }
    @Test
    public void splitChar2Test(){
        StringList res_;
        res_ = StringUtil.splitChar("HELLO WORLD", 'A');
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitChar3Test(){
        StringList res_;
        res_ = StringUtil.splitChar("", '1');
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitChar4Test(){
        StringList res_;
        res_ = StringUtil.splitChar("HELLO WORLD", 'L');
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitChars1Test(){
        StringList res_;
        res_ = StringUtil.splitChars("HELLO WORLD", ' ');
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq("WORLD", res_.get(1));
    }
    @Test
    public void splitChars2Test(){
        StringList res_;
        res_ = StringUtil.splitChars("HELLO WORLD", 'A');
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitChars3Test(){
        StringList res_;
        res_ = StringUtil.splitChars("", '1');
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitChars4Test(){
        StringList res_;
        res_ = StringUtil.splitChars("HELLO WORLD", 'L');
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitChars5Test(){
        StringList res_;
        res_ = StringUtil.splitChars("HELLO WORLD", 'L', 'O');
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
        res_ = StringUtil.splitCharSep("HELLO WORLD", ' ');
        assertEq(3, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq(" ", res_.get(1));
        assertEq("WORLD", res_.get(2));
    }
    @Test
    public void splitCharsSep2Test(){
        StringList res_;
        res_ = StringUtil.splitCharSep("HELLO WORLD", 'A');
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitCharsSep3Test(){
        StringList res_;
        res_ = StringUtil.splitCharSep("", '1');
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitCharsSep4Test(){
        StringList res_;
        res_ = StringUtil.splitCharSep("HELLO WORLD", 'L');
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
        res_ = StringUtil.splitStrings("HELLO WORLD", " ");
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq("WORLD", res_.get(1));
    }
    @Test
    public void splitStrings2Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "A");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStrings3Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("", "1");
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitStrings4Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "L");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitStrings5Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "L", "O");
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
        res_ = StringUtil.splitStrings("HELLO WORLD", "LL", "L");
        assertEq(3, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("O WOR", res_.get(1));
        assertEq("D", res_.get(2));
    }
    @Test
    public void splitStrings7Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "L", "LL");
        assertEq(3, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("O WOR", res_.get(1));
        assertEq("D", res_.get(2));
    }
    @Test
    public void splitStrings8Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "L", "LD");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("", res_.get(3));
    }
    @Test
    public void splitStrings9Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "L", "LW");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitStrings10Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "L", "LDW");
        assertEq(4, res_.size());
        assertEq("HE", res_.get(0));
        assertEq("", res_.get(1));
        assertEq("O WOR", res_.get(2));
        assertEq("D", res_.get(3));
    }
    @Test
    public void splitStrings11Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "LDW");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStrings12Test(){
        StringList res_;
        res_ = StringUtil.splitStrings("HELLO WORLD", "");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStringsSep1Test(){
        StringList res_;
        res_ = StringUtil.splitStringsSep("HELLO WORLD", " ");
        assertEq(3, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq(" ", res_.get(1));
        assertEq("WORLD", res_.get(2));
    }
    @Test
    public void splitStringsSep2Test(){
        StringList res_;
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "A");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStringsSep3Test(){
        StringList res_;
        res_ = StringUtil.splitStringsSep("", "1");
        assertEq(1, res_.size());
        assertEq("", res_.get(0));
    }
    @Test
    public void splitStringsSep4Test(){
        StringList res_;
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "L");
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
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "L", "O");
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
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "LL", "L");
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
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "L", "LL");
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
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "L", "LD");
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
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "L", "LW");
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
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "L", "LDW");
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
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "LDW");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitStringsSep12Test(){
        StringList res_;
        res_ = StringUtil.splitStringsSep("HELLO WORLD", "");
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitInTwo1Test(){
        StringList res_;
        res_ = StringUtil.splitInTwo("HELLO WORLD", -1);
        assertEq(1, res_.size());
        assertEq("HELLO WORLD", res_.get(0));
    }
    @Test
    public void splitInTwo2Test(){
        StringList res_;
        res_ = StringUtil.splitInTwo("HELLO WORLD", 5);
        assertEq(2, res_.size());
        assertEq("HELLO", res_.get(0));
        assertEq(" WORLD", res_.get(1));
    }
    @Test
    public void wrapContent1Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 5, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent2Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 4, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent3Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 10, true);
        assertEq("Hello\nevery body", lines_);
    }
    @Test
    public void wrapContent4Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 11, true);
        assertEq("Hello every\nbody", lines_);
    }
    @Test
    public void wrapContent5Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 9, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent6Test(){
        String text_ = "Hello every\nbody";
        String lines_ = StringUtil.wrapContent(text_, 9, true);
        assertEq("Hello\nevery\nbody", lines_);
    }
    @Test
    public void wrapContent7Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 5, false);
        assertEq("Hello\nevery\nbody\n", lines_);
    }
    @Test
    public void wrapContent8Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 4, false);
        assertEq("Hello\nevery\nbody\n", lines_);
    }
    @Test
    public void wrapContent9Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 10, false);
        assertEq("Hello\nevery body\n", lines_);
    }
    @Test
    public void wrapContent10Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 11, false);
        assertEq("Hello every\nbody\n", lines_);
    }
    @Test
    public void wrapContent11Test(){
        String text_ = "Hello every body";
        String lines_ = StringUtil.wrapContent(text_, 9, false);
        assertEq("Hello\nevery\nbody\n", lines_);
    }
    @Test
    public void wrapContent12Test(){
        String text_ = "Hello every\nbody";
        String lines_ = StringUtil.wrapContent(text_, 9, false);
        assertEq("Hello\nevery\n\nbody\n", lines_);
    }
    @Test
    public void replaceFinalFile1Test(){
        String file_ = "word";
        String finalFile_ = StringUtil.replaceFinalFile(file_);
        assertEq("word", finalFile_);
    }
    @Test
    public void replaceFinalFile2Test(){
        String file_ = "word.txt";
        String finalFile_ = StringUtil.replaceFinalFile(file_);
        assertEq("word.txt", finalFile_);
    }
    @Test
    public void replaceFinalFile3Test(){
        String file_ = "word/txt";
        String finalFile_ = StringUtil.replaceFinalFile(file_);
        assertEq("word/txt", finalFile_);
    }
    @Test
    public void replaceFinalFile4Test(){
        String file_ = "folder/word.txt";
        String finalFile_ = StringUtil.replaceFinalFile(file_);
        assertEq("folder", finalFile_);
    }
    @Test
    public void replaceFinalFile5Test(){
        String file_ = "folder/word.txt.txt";
        String finalFile_ = StringUtil.replaceFinalFile(file_);
        assertEq("folder/word.txt.txt", finalFile_);
    }
    @Test
    public void replaceFinalFile6Test(){
        String file_ = "folder/.txt";
        String finalFile_ = StringUtil.replaceFinalFile(file_);
        assertEq("folder/.txt", finalFile_);
    }
    @Test
    public void replaceBkSlash1Test(){
        String file_ = "word/txt";
        String finalFile_ = StringUtil.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash2Test(){
        String file_ = "word\\txt";
        String finalFile_ = StringUtil.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash3Test(){
        String file_ = "word/txt/";
        String finalFile_ = StringUtil.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash4Test(){
        String file_ = "word\\txt\\";
        String finalFile_ = StringUtil.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash5Test(){
        String file_ = "word/txt/.";
        String finalFile_ = StringUtil.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void replaceBkSlash6Test(){
        String file_ = "word\\txt\\.";
        String finalFile_ = StringUtil.replaceBackSlashDot(file_);
        assertEq("word/txt/", finalFile_);
    }
    @Test
    public void getWordsSeparators1Test(){
        String string_ = ";./:";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(1, parts_.size());
        assertEq(";./:", parts_.first());
    }
    @Test
    public void getWordsSeparators2Test(){
        String string_ = "hello";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(2, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getWordsSeparators3Test(){
        String string_ = ";hello";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(2, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getWordsSeparators4Test(){
        String string_ = "hello;";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(3, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getWordsSeparators5Test(){
        String string_ = ":hello;";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(3, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getWordsSeparators6Test(){
        String string_ = ":hello;world!";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
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
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getWordsSeparators8Test(){
        String string_ = ":hello;,world";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getWordsSeparators9Test(){
        String string_ = "";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(0, parts_.size());
    }
    @Test
    public void getWordsSeparators10Test(){
        String string_ = ":hello_one;,world_one";
        StringList parts_ = StringUtil.getWordsSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello_one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world_one", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators1Test(){
        String string_ = ";./:";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(1, parts_.size());
        assertEq(";./:", parts_.first());
    }
    @Test
    public void getDollarWordSeparators2Test(){
        String string_ = "hello";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(2, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getDollarWordSeparators3Test(){
        String string_ = ";hello";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(2, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getDollarWordSeparators4Test(){
        String string_ = "hello;";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(3, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getDollarWordSeparators5Test(){
        String string_ = ":hello;";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(3, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getDollarWordSeparators6Test(){
        String string_ = ":hello;world!";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
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
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators8Test(){
        String string_ = ":hello;,world";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators9Test(){
        String string_ = "";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(0, parts_.size());
    }
    @Test
    public void getDollarWordSeparators10Test(){
        String string_ = ":hello_one;,world_one";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello_one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world_one", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators11Test(){
        String string_ = ":hello$one;,world$one";
        StringList parts_ = StringUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello$one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world$one", parts_.get(3));
    }
    @Test
    public void replaceWordsJoin1Test(){
        String string_ = "";
        String parts_ = StringUtil.replaceWordsJoin(string_,new StringMap<String>());
        assertEq("", parts_);
    }
    @Test
    public void replaceWordsJoin2Test(){
        String string_ = "HELLO";
        String parts_ = StringUtil.replaceWordsJoin(string_,new StringMap<String>());
        assertEq("HELLO", parts_);
    }
    @Test
    public void replaceWordsJoin3Test(){
        String string_ = "";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO", "WORLD");
        String parts_ = StringUtil.replaceWordsJoin(string_,rep_);
        assertEq("", parts_);
    }
    @Test
    public void replaceWordsJoin4Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO", "WORLD");
        String parts_ = StringUtil.replaceWordsJoin(string_,rep_);
        assertEq("WORLD", parts_);
    }
    @Test
    public void replaceWordsJoin5Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("WORLD", "HELLO");
        String parts_ = StringUtil.replaceWordsJoin(string_,rep_);
        assertEq("HELLO", parts_);
    }
    @Test
    public void replaceWordsJoin6Test(){
        String string_ = "HELLO";
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put("HELLO_WORLD", "WORLD");
        String parts_ = StringUtil.replaceWordsJoin(string_,rep_);
        assertEq("HELLO", parts_);
    }
    @Test
    public void getWordsSeparatorsPrefix1Test(){
        String string_ = "";
        StringList parts_ = StringUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(0, parts_.size());
    }
    @Test
    public void getWordsSeparatorsPrefix2Test(){
        String string_ = "word_one;hello;word_two";
        StringList parts_ = StringUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(4, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
    }
    @Test
    public void getWordsSeparatorsPrefix3Test(){
        String string_ = "word_one;hello;word_two;world";
        StringList parts_ = StringUtil.getWordsSeparatorsPrefix(string_,"word_");
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
        StringList parts_ = StringUtil.getWordsSeparatorsPrefix(string_,"word_");
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
        StringList parts_ = StringUtil.getWordsSeparatorsPrefix(string_,"word_");
        assertEq(4, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("word_one", parts_.get(1));
        assertEq(";hello;", parts_.get(2));
        assertEq("word_two", parts_.get(3));
    }
    @Test
    public void getWordsSeparatorsPrefix6Test(){
        String string_ = ";word_one;hello;word_two;world";
        StringList parts_ = StringUtil.getWordsSeparatorsPrefix(string_,"word_");
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
        StringList parts_ = StringUtil.getWordsSeparatorsPrefix(string_,"word_");
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
        StringList parts_ = StringUtil.getTokensSets(string_);
        assertEq(1, parts_.size());
        assertEq(";word_one;hello;word_two;word_three;world", parts_.get(0));
    }
    @Test
    public void getTokensSets2Test(){
        String string_ = ";word_one;{hello};word_two;word_three;world";
        StringList parts_ = StringUtil.getTokensSets(string_);
        assertEq(3, parts_.size());
        assertEq(";word_one;", parts_.get(0));
        assertEq("{hello}", parts_.get(1));
        assertEq(";word_two;word_three;world", parts_.get(2));
    }
    @Test
    public void getTokensSets3Test(){
        String string_ = ";word_one;{};word_two;word_three;world";
        StringList parts_ = StringUtil.getTokensSets(string_);
        assertEq(1, parts_.size());
        assertEq(";word_one;{};word_two;word_three;world", parts_.get(0));
    }
    @Test
    public void getTokensSets4Test(){
        String string_ = ";word_one;{;word_two;word_three;world";
        StringList parts_ = StringUtil.getTokensSets(string_);
        assertEq(1, parts_.size());
        assertEq(";word_one;{;word_two;word_three;world", parts_.get(0));
    }
    @Test
    public void encode1Test() {
        byte[] bs_ = StringUtil.encode("a");
        assertEq(1, bs_.length);
        assertEq(97,bs_[0]);
    }
    @Test
    public void encode2Test() {
        byte[] bs_ = StringUtil.encode("\u0090");
        assertEq(2, bs_.length);
        assertEq(-62,bs_[0]);
        assertEq(-112,bs_[1]);
    }
    @Test
    public void encode3Test() {
        byte[] bs_ = StringUtil.encode("\u0b00");
        assertEq(3, bs_.length);
        assertEq(-32,bs_[0]);
        assertEq(-84,bs_[1]);
        assertEq(-128,bs_[2]);
    }
    @Test
    public void encode4Test() {
        byte[] bs_ = StringUtil.encode("\ub000");
        assertEq(3, bs_.length);
        assertEq(-21,bs_[0]);
        assertEq(-128,bs_[1]);
        assertEq(-128,bs_[2]);
    }
    @Test
    public void encode5Test() {
        byte[] bs_ = StringUtil.encode("");
        assertEq(0, bs_.length);
    }
    @Test
    public void decode1Test() {
        assertEq("a", StringUtil.decode(NumberUtil.wrapByteArray((byte)97)));
    }
    @Test
    public void decode2Test() {
        assertEq("\u0090", StringUtil.decode(NumberUtil.wrapByteArray((byte)-62,(byte)-112)));
    }
    @Test
    public void decode3Test() {
        assertEq("\u0b00", StringUtil.decode(NumberUtil.wrapByteArray((byte)-32,(byte)-84,(byte)-128)));
    }
    @Test
    public void decode4Test() {
        assertEq("\ub000", StringUtil.decode(NumberUtil.wrapByteArray((byte)-21,(byte)-128,(byte)-128)));
    }
    @Test
    public void decode5Test() {
        assertEq("", StringUtil.decode(NumberUtil.wrapByteArray()));
    }
    @Test
    public void decode6Test() {
        assertNull(StringUtil.decode(NumberUtil.wrapByteArray((byte)-1)));
    }
    @Test
    public void decode7Test() {
        assertNull(StringUtil.decode(NumberUtil.wrapByteArray((byte)-63,(byte)-1)));
    }
    @Test
    public void decode8Test() {
        assertNull(StringUtil.decode(NumberUtil.wrapByteArray((byte)-16,(byte)-1)));
    }
    @Test
    public void decode9Test() {
        assertNull(StringUtil.decode(NumberUtil.wrapByteArray((byte)-17,(byte)-64)));
    }
    @Test
    public void decode10Test() {
        assertNull(StringUtil.decode(NumberUtil.wrapByteArray((byte)-17,(byte)-65)));
    }
    @Test
    public void decode11Test() {
        assertNull(StringUtil.decode(NumberUtil.wrapByteArray((byte)-17,(byte)-65,(byte)-64)));
    }
    @Test
    public void badDecode1Test() {
        assertEq(-1, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)97),0,1));
    }
    @Test
    public void badDecode2Test() {
        assertEq(-1, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-62,(byte)-112),0,2));
    }
    @Test
    public void badDecode3Test() {
        assertEq(-1, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-32,(byte)-84,(byte)-128),0,3));
    }
    @Test
    public void badDecode4Test() {
        assertEq(-1, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-21,(byte)-128,(byte)-128),0,3));
    }
    @Test
    public void badDecode5Test() {
        assertEq(-1, StringUtil.badDecode(NumberUtil.wrapByteArray(),0,0));
    }
    @Test
    public void badDecode6Test() {
        assertEq(0, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-1),0,1));
    }
    @Test
    public void badDecode7Test() {
        assertEq(0, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-63,(byte)-1),0,2));
    }
    @Test
    public void badDecode8Test() {
        assertEq(0, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-16,(byte)-1),0,2));
    }
    @Test
    public void badDecode9Test() {
        assertEq(0, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-17,(byte)-64),0,2));
    }
    @Test
    public void badDecode10Test() {
        assertEq(0, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-17,(byte)-65),0,2));
    }
    @Test
    public void badDecode11Test() {
        assertEq(0, StringUtil.badDecode(NumberUtil.wrapByteArray((byte)-17,(byte)-65,(byte)-64),0,3));
    }
    @Test
    public void toLowerCaseTest() {
        assertEq("0", StringUtil.toLowerCase("0"));
    }
    @Test
    public void toUpperCaseTest() {
        assertEq("0", StringUtil.toUpperCase("0"));
    }
    @Test
    public void eqStrings1Test(){
        assertTrue(StringUtil.eqStrings(new StringList(),new StringList()));
    }
    @Test
    public void eqStrings2Test(){
        assertTrue(StringUtil.eqStrings(new StringList("a"),new StringList("a")));
    }
    @Test
    public void eqStrings3Test(){
        assertTrue(!StringUtil.eqStrings(new StringList("a","b"),new StringList("b","a")));
    }
    @Test
    public void eqStrings4Test(){
        assertTrue(!StringUtil.eqStrings(new StringList("a","a","b"),new StringList("a","b","b")));
    }
    @Test
    public void eqStrings5Test(){
        assertTrue(!StringUtil.eqStrings(new StringList("a","b"),new StringList("a")));
    }
    @Test
    public void eqStrings6Test(){
        assertTrue(!StringUtil.eqStrings(new StringList("b"),new StringList("a","b")));
    }
    @Test
    public void equalsSet1Test(){
        assertTrue(StringUtil.equalsSet(new StringList(),new StringList()));
    }
    @Test
    public void equalsSet2Test(){
        assertTrue(StringUtil.equalsSet(new StringList("a"),new StringList("a")));
    }
    @Test
    public void equalsSet3Test(){
        assertTrue(StringUtil.equalsSet(new StringList("a","b"),new StringList("b","a")));
    }
    @Test
    public void equalsSet4Test(){
        assertTrue(StringUtil.equalsSet(new StringList("a","a","b"),new StringList("a","b","b")));
    }
    @Test
    public void equalsSet5Test(){
        assertTrue(!StringUtil.equalsSet(new StringList("a","b"),new StringList("a")));
    }
    @Test
    public void equalsSet6Test(){
        assertTrue(!StringUtil.equalsSet(new StringList("b"),new StringList("a","b")));
    }
    @Test
    public void equalsStringListSet1Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        CustList<StringList> lTwo_ =new CustList<StringList>();
        assertTrue(StringUtil.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet2Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("a"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("a"));
        assertTrue(StringUtil.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet3Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("a"));
        lOne_.add(new StringList("b"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("b"));
        lTwo_.add(new StringList("a"));
        assertTrue(StringUtil.equalsStringListSet(lOne_,lTwo_));
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
        assertTrue(StringUtil.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet5Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("a"));
        lOne_.add(new StringList("b"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("a"));
        assertTrue(!StringUtil.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void equalsStringListSet6Test(){
        CustList<StringList> lOne_ =new CustList<StringList>();
        lOne_.add(new StringList("b"));
        CustList<StringList> lTwo_ =new CustList<StringList>();
        lTwo_.add(new StringList("a"));
        lTwo_.add(new StringList("b"));
        assertTrue(!StringUtil.equalsStringListSet(lOne_,lTwo_));
    }
    @Test
    public void eq1Test(){
        assertTrue(!StringUtil.eq("",null));
    }
    @Test
    public void eq2Test(){
        assertTrue(!StringUtil.eq(null,""));
    }
    @Test
    public void eq3Test(){
        assertTrue(!StringUtil.eq("a","b"));
    }
    @Test
    public void eq4Test(){
        assertTrue(StringUtil.eq(null,null));
    }
    @Test
    public void eq5Test(){
        assertTrue(StringUtil.eq("a","a"));
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
    public void removeDuplicates5Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        l_.add("a");
        l_.removeDuplicates();
        assertEq(2,l_.size());
        assertEq("a",l_.get(0));
        assertEq("b",l_.get(1));
    }
    @Test
    public void removeDuplicates6Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        l_.add("a");
        l_.add("b");
        l_.removeDuplicates();
        assertEq(2,l_.size());
        assertEq("a",l_.get(0));
        assertEq("b",l_.get(1));
    }
    @Test
    public void hasDuplicates1Test() {
        StringList l_ =new StringList();
        assertTrue(!l_.hasDuplicates());
    }
    @Test
    public void hasDuplicates2Test() {
        StringList l_ =new StringList();
        l_.add("a");
        assertTrue(!l_.hasDuplicates());
    }
    @Test
    public void hasDuplicates3Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        assertTrue(!l_.hasDuplicates());
    }
    @Test
    public void hasDuplicates4Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("a");
        assertTrue(l_.hasDuplicates());
    }
    @Test
    public void hasDuplicates5Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        l_.add("a");
        assertTrue(l_.hasDuplicates());
    }
    @Test
    public void hasDuplicates6Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        l_.add("a");
        l_.add("b");
        assertTrue(l_.hasDuplicates());
    }
    @Test
    public void onlyOneElt1Test() {
        StringList l_ =new StringList();
        assertTrue(!l_.onlyOneElt());
    }
    @Test
    public void onlyOneElt2Test() {
        StringList l_ =new StringList();
        l_.add("a");
        assertTrue(l_.onlyOneElt());
    }
    @Test
    public void onlyOneElt3Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        assertTrue(!l_.onlyOneElt());
    }
    @Test
    public void onlyOneElt4Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("a");
        assertTrue(l_.onlyOneElt());
    }
    @Test
    public void onlyOneElt5Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        l_.add("a");
        assertTrue(!l_.onlyOneElt());
    }
    @Test
    public void onlyOneElt6Test() {
        StringList l_ =new StringList();
        l_.add("a");
        l_.add("b");
        l_.add("a");
        l_.add("b");
        assertTrue(!l_.onlyOneElt());
    }
    @Test
    public void getFirstPrintableCharIndex1Test() {
        assertEq(-1, StringUtil.getFirstPrintableCharIndex(""));
    }
    @Test
    public void getFirstPrintableCharIndex2Test() {
        assertEq(-1, StringUtil.getFirstPrintableCharIndex(" "));
    }
    @Test
    public void getFirstPrintableCharIndex3Test() {
        assertEq(0, StringUtil.getFirstPrintableCharIndex("a"));
    }
    @Test
    public void getFirstPrintableCharIndex4Test() {
        assertEq(1, StringUtil.getFirstPrintableCharIndex(" a"));
    }
    @Test
    public void getFirstPrintableCharIndex5Test() {
        assertEq(0, StringUtil.getFirstPrintableCharIndex("a "));
    }
    @Test
    public void getLastPrintableCharIndex1Test() {
        assertEq(-1, StringUtil.getLastPrintableCharIndex(""));
    }
    @Test
    public void getLastPrintableCharIndex2Test() {
        assertEq(-1, StringUtil.getLastPrintableCharIndex(" "));
    }
    @Test
    public void getLastPrintableCharIndex3Test() {
        assertEq(0, StringUtil.getLastPrintableCharIndex("a"));
    }
    @Test
    public void getLastPrintableCharIndex4Test() {
        assertEq(0, StringUtil.getLastPrintableCharIndex("a "));
    }
    @Test
    public void getLastPrintableCharIndex5Test() {
        assertEq(1, StringUtil.getLastPrintableCharIndex(" a"));
    }
    @Test
    public void getFirstToken1Test(){
        assertEq("hell", StringUtil.getFirstToken("hello world","o"));
    }
    @Test
    public void getFirstToken2Test(){
        assertEq("hello world", StringUtil.getFirstToken("hello world","a"));
    }
    @Test
    public void getFirstToken3Test(){
        assertEq("hell", StringUtil.getFirstToken("hello world",'o'));
    }
    @Test
    public void getFirstToken4Test(){
        assertEq("hello world", StringUtil.getFirstToken("hello world",'a'));
    }
    @Test
    public void replaceMultTest() {
        Replacement rOne_  =new Replacement();
        rOne_.setNewString("after");
        rOne_.setOldString("before");
        Replacement rTwo_  =new Replacement();
        rTwo_.setNewString("unknown");
        rTwo_.setOldString("known");
        assertEq("after", StringUtil.replaceMult("before",rOne_,rTwo_));
    }
    @Test
    public void removeStringsTest() {
        assertEq("bfor", StringUtil.removeStrings("before","e"));
    }
    @Test
    public void removeCharsTest() {
        assertEq("bfor", StringUtil.removeChars("before",'e'));
    }
    @Test
    public void removeAllSpacesTest() {
        assertEq("before", StringUtil.removeAllSpaces("be fore"));
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
        StringList f_ = StringUtil.intersect(list_,listTwo_);
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
        assertTrue(!StringUtil.disjoints(lists_));
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
        assertTrue(StringUtil.disjoints(lists_));
    }
    @Test
    public void spliceIfFirst1Test() {
        assertEq("p", StringUtil.spliceIfFirst('.',".p"));
    }
    @Test
    public void spliceIfFirst2Test() {
        assertEq("a.p", StringUtil.spliceIfFirst('.',"a.p"));
    }
    @Test
    public void spliceIfFirst3Test() {
        assertEq("", StringUtil.spliceIfFirst('.',""));
    }
    @Test
    public void retainAllElementsTest(){
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringList listTwo_ = new StringList();
        listTwo_.add("hellos");
        listTwo_.add("world");
        StringUtil.retainAllElements(list_, listTwo_);
        assertEq(1, list_.size());
        assertEq("world", list_.get(0));
    }
    @Test
    public void removePrefixInStringsTest(){
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        StringUtil.removePrefixInStrings(list_,"h");
        assertEq(2, list_.size());
        assertEq("ello", list_.get(0));
        assertEq("world", list_.get(1));
    }
    @Test
    public void replaceExtension1Test() {
        assertEq("file.log", StringUtil.replaceExtension("file.txt",".txt",".log"));
    }
    @Test
    public void replaceExtension2Test() {
        assertEq("file.txt", StringUtil.replaceExtension("file.txt",".log",".txt"));
    }
    @Test
    public void getReverseTest() {
        StringList list_ = new StringList();
        list_.add("hello");
        list_.add("world");
        CustList<String> f_ = list_.getReverse();
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
        StringUtil.removeAllElements(list_, listTwo_);
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
        assertTrue(StringUtil.startsWith("hello","hello"));
    }
    @Test
    public void startsWith2Test() {
        assertTrue(!StringUtil.startsWith("hello","world"));
    }
    @Test
    public void endsWith1Test() {
        assertTrue(StringUtil.endsWith("hello","hello"));
    }
    @Test
    public void endsWith2Test() {
        assertTrue(!StringUtil.endsWith("hello","world"));
    }
    @Test
    public void replaceBegin1Test() {
        assertEq("", StringUtil.replaceBegin("hello","hello"));
    }
    @Test
    public void replaceBegin2Test() {
        assertEq("hello", StringUtil.replaceBegin("hello","world"));
    }
    @Test
    public void replaceEnd1Test() {
        assertEq("", StringUtil.replaceEnd("hello","hello"));
    }
    @Test
    public void replaceEnd2Test() {
        assertEq("hello", StringUtil.replaceEnd("hello","world"));
    }
    @Test
    public void replaceExt1Test() {
        assertEq("hello", StringUtil.replaceExtension("hello"));
    }
    @Test
    public void replaceExt2Test() {
        assertEq("hello", StringUtil.replaceExtension("hello.txt"));
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
    public void formatQuoteTest() {
        assertEq("hello", StringUtil.formatQuote("hello", new StringMap<String>()));
    }
    @Test
    public void concatNbTest() {
        assertEq("0hello", StringUtil.concatNb(0,"hello"));
    }
    @Test
    public void concatNbsTest() {
        assertEq("hello0", StringUtil.concatNbs("hello", 0));
    }
    @Test
    public void displayTest(){
        StringList s_ = new StringList();
        assertEq("[]", s_.display());
    }
    @Test
    public void toLower1() {
        assertEq(97,StringUtil.toLowerCase((char)65));
    }
    @Test
    public void toLower2() {
        assertEq(224,StringUtil.toLowerCase((char)192));
    }
    @Test
    public void toLower3() {
        assertEq(248,StringUtil.toLowerCase((char)216));
    }
    @Test
    public void toLower4() {
        assertEq(257,StringUtil.toLowerCase((char)256));
    }
    @Test
    public void toLower5() {
        assertEq(105,StringUtil.toLowerCase((char)304));
    }
    @Test
    public void toLower6() {
        assertEq(307,StringUtil.toLowerCase((char)306));
    }
    @Test
    public void toLower7() {
        assertEq(314,StringUtil.toLowerCase((char)313));
    }
    @Test
    public void toLower8() {
        assertEq(331,StringUtil.toLowerCase((char)330));
    }
    @Test
    public void toLower9() {
        assertEq(255,StringUtil.toLowerCase((char)376));
    }
    @Test
    public void toLower10() {
        assertEq(378,StringUtil.toLowerCase((char)377));
    }
    @Test
    public void toLower11() {
        assertEq(595,StringUtil.toLowerCase((char)385));
    }
    @Test
    public void toLower12() {
        assertEq(387,StringUtil.toLowerCase((char)386));
    }
    @Test
    public void toLower13() {
        assertEq(596,StringUtil.toLowerCase((char)390));
    }
    @Test
    public void toLower14() {
        assertEq(392,StringUtil.toLowerCase((char)391));
    }
    @Test
    public void toLower15() {
        assertEq(598,StringUtil.toLowerCase((char)393));
    }
    @Test
    public void toLower16() {
        assertEq(396,StringUtil.toLowerCase((char)395));
    }
    @Test
    public void toLower17() {
        assertEq(477,StringUtil.toLowerCase((char)398));
    }
    @Test
    public void toLower18() {
        assertEq(601,StringUtil.toLowerCase((char)399));
    }
    @Test
    public void toLower19() {
        assertEq(603,StringUtil.toLowerCase((char)400));
    }
    @Test
    public void toLower20() {
        assertEq(402,StringUtil.toLowerCase((char)401));
    }
    @Test
    public void toLower21() {
        assertEq(608,StringUtil.toLowerCase((char)403));
    }
    @Test
    public void toLower22() {
        assertEq(611,StringUtil.toLowerCase((char)404));
    }
    @Test
    public void toLower23() {
        assertEq(617,StringUtil.toLowerCase((char)406));
    }
    @Test
    public void toLower24() {
        assertEq(616,StringUtil.toLowerCase((char)407));
    }
    @Test
    public void toLower25() {
        assertEq(409,StringUtil.toLowerCase((char)408));
    }
    @Test
    public void toLower26() {
        assertEq(623,StringUtil.toLowerCase((char)412));
    }
    @Test
    public void toLower27() {
        assertEq(626,StringUtil.toLowerCase((char)413));
    }
    @Test
    public void toLower28() {
        assertEq(629,StringUtil.toLowerCase((char)415));
    }
    @Test
    public void toLower29() {
        assertEq(417,StringUtil.toLowerCase((char)416));
    }
    @Test
    public void toLower30() {
        assertEq(640,StringUtil.toLowerCase((char)422));
    }
    @Test
    public void toLower31() {
        assertEq(424,StringUtil.toLowerCase((char)423));
    }
    @Test
    public void toLower32() {
        assertEq(643,StringUtil.toLowerCase((char)425));
    }
    @Test
    public void toLower33() {
        assertEq(429,StringUtil.toLowerCase((char)428));
    }
    @Test
    public void toLower34() {
        assertEq(648,StringUtil.toLowerCase((char)430));
    }
    @Test
    public void toLower35() {
        assertEq(432,StringUtil.toLowerCase((char)431));
    }
    @Test
    public void toLower36() {
        assertEq(650,StringUtil.toLowerCase((char)433));
    }
    @Test
    public void toLower37() {
        assertEq(436,StringUtil.toLowerCase((char)435));
    }
    @Test
    public void toLower38() {
        assertEq(658,StringUtil.toLowerCase((char)439));
    }
    @Test
    public void toLower39() {
        assertEq(441,StringUtil.toLowerCase((char)440));
    }
    @Test
    public void toLower40() {
        assertEq(445,StringUtil.toLowerCase((char)444));
    }
    @Test
    public void toLower41() {
        assertEq(454,StringUtil.toLowerCase((char)452));
    }
    @Test
    public void toLower42() {
        assertEq(454,StringUtil.toLowerCase((char)453));
    }
    @Test
    public void toLower43() {
        assertEq(457,StringUtil.toLowerCase((char)455));
    }
    @Test
    public void toLower44() {
        assertEq(457,StringUtil.toLowerCase((char)456));
    }
    @Test
    public void toLower45() {
        assertEq(460,StringUtil.toLowerCase((char)458));
    }
    @Test
    public void toLower46() {
        assertEq(460,StringUtil.toLowerCase((char)459));
    }
    @Test
    public void toLower47() {
        assertEq(479,StringUtil.toLowerCase((char)478));
    }
    @Test
    public void toLower48() {
        assertEq(499,StringUtil.toLowerCase((char)497));
    }
    @Test
    public void toLower49() {
        assertEq(499,StringUtil.toLowerCase((char)498));
    }
    @Test
    public void toLower50() {
        assertEq(405,StringUtil.toLowerCase((char)502));
    }
    @Test
    public void toLower51() {
        assertEq(447,StringUtil.toLowerCase((char)503));
    }
    @Test
    public void toLower52() {
        assertEq(505,StringUtil.toLowerCase((char)504));
    }
    @Test
    public void toLower53() {
        assertEq(414,StringUtil.toLowerCase((char)544));
    }
    @Test
    public void toLower54() {
        assertEq(547,StringUtil.toLowerCase((char)546));
    }
    @Test
    public void toLower55() {
        assertEq(11365,StringUtil.toLowerCase((char)570));
    }
    @Test
    public void toLower56() {
        assertEq(572,StringUtil.toLowerCase((char)571));
    }
    @Test
    public void toLower57() {
        assertEq(410,StringUtil.toLowerCase((char)573));
    }
    @Test
    public void toLower58() {
        assertEq(11366,StringUtil.toLowerCase((char)574));
    }
    @Test
    public void toLower59() {
        assertEq(578,StringUtil.toLowerCase((char)577));
    }
    @Test
    public void toLower60() {
        assertEq(384,StringUtil.toLowerCase((char)579));
    }
    @Test
    public void toLower61() {
        assertEq(649,StringUtil.toLowerCase((char)580));
    }
    @Test
    public void toLower62() {
        assertEq(652,StringUtil.toLowerCase((char)581));
    }
    @Test
    public void toLower63() {
        assertEq(583,StringUtil.toLowerCase((char)582));
    }
    @Test
    public void toLower64() {
        assertEq(881,StringUtil.toLowerCase((char)880));
    }
    @Test
    public void toLower65() {
        assertEq(887,StringUtil.toLowerCase((char)886));
    }
    @Test
    public void toLower66() {
        assertEq(940,StringUtil.toLowerCase((char)902));
    }
    @Test
    public void toLower67() {
        assertEq(941,StringUtil.toLowerCase((char)904));
    }
    @Test
    public void toLower68() {
        assertEq(972,StringUtil.toLowerCase((char)908));
    }
    @Test
    public void toLower69() {
        assertEq(973,StringUtil.toLowerCase((char)910));
    }
    @Test
    public void toLower70() {
        assertEq(945,StringUtil.toLowerCase((char)913));
    }
    @Test
    public void toLower71() {
        assertEq(963,StringUtil.toLowerCase((char)931));
    }
    @Test
    public void toLower72() {
        assertEq(983,StringUtil.toLowerCase((char)975));
    }
    @Test
    public void toLower73() {
        assertEq(985,StringUtil.toLowerCase((char)984));
    }
    @Test
    public void toLower74() {
        assertEq(952,StringUtil.toLowerCase((char)1012));
    }
    @Test
    public void toLower75() {
        assertEq(1016,StringUtil.toLowerCase((char)1015));
    }
    @Test
    public void toLower76() {
        assertEq(1010,StringUtil.toLowerCase((char)1017));
    }
    @Test
    public void toLower77() {
        assertEq(1019,StringUtil.toLowerCase((char)1018));
    }
    @Test
    public void toLower78() {
        assertEq(891,StringUtil.toLowerCase((char)1021));
    }
    @Test
    public void toLower79() {
        assertEq(1104,StringUtil.toLowerCase((char)1024));
    }
    @Test
    public void toLower80() {
        assertEq(1072,StringUtil.toLowerCase((char)1040));
    }
    @Test
    public void toLower81() {
        assertEq(1121,StringUtil.toLowerCase((char)1120));
    }
    @Test
    public void toLower82() {
        assertEq(1163,StringUtil.toLowerCase((char)1162));
    }
    @Test
    public void toLower83() {
        assertEq(1231,StringUtil.toLowerCase((char)1216));
    }
    @Test
    public void toLower84() {
        assertEq(1218,StringUtil.toLowerCase((char)1217));
    }
    @Test
    public void toLower85() {
        assertEq(1233,StringUtil.toLowerCase((char)1232));
    }
    @Test
    public void toLower86() {
        assertEq(1377,StringUtil.toLowerCase((char)1329));
    }
    @Test
    public void toLower87() {
        assertEq(11520,StringUtil.toLowerCase((char)4256));
    }
    @Test
    public void toLower88() {
        assertEq(11559,StringUtil.toLowerCase((char)4295));
    }
    @Test
    public void toLower89() {
        assertEq(11565,StringUtil.toLowerCase((char)4301));
    }
    @Test
    public void toLower90() {
        assertEq(7681,StringUtil.toLowerCase((char)7680));
    }
    @Test
    public void toLower91() {
        assertEq(223,StringUtil.toLowerCase((char)7838));
    }
    @Test
    public void toLower92() {
        assertEq(7841,StringUtil.toLowerCase((char)7840));
    }
    @Test
    public void toLower93() {
        assertEq(7936,StringUtil.toLowerCase((char)7944));
    }
    @Test
    public void toLower94() {
        assertEq(7952,StringUtil.toLowerCase((char)7960));
    }
    @Test
    public void toLower95() {
        assertEq(7968,StringUtil.toLowerCase((char)7976));
    }
    @Test
    public void toLower96() {
        assertEq(7984,StringUtil.toLowerCase((char)7992));
    }
    @Test
    public void toLower97() {
        assertEq(8000,StringUtil.toLowerCase((char)8008));
    }
    @Test
    public void toLower98() {
        assertEq(8017,StringUtil.toLowerCase((char)8025));
    }
    @Test
    public void toLower99() {
        assertEq(8019,StringUtil.toLowerCase((char)8027));
    }
    @Test
    public void toLower100() {
        assertEq(8021,StringUtil.toLowerCase((char)8029));
    }
    @Test
    public void toLower101() {
        assertEq(8023,StringUtil.toLowerCase((char)8031));
    }
    @Test
    public void toLower102() {
        assertEq(8032,StringUtil.toLowerCase((char)8040));
    }
    @Test
    public void toLower103() {
        assertEq(8064,StringUtil.toLowerCase((char)8072));
    }
    @Test
    public void toLower104() {
        assertEq(8080,StringUtil.toLowerCase((char)8088));
    }
    @Test
    public void toLower105() {
        assertEq(8096,StringUtil.toLowerCase((char)8104));
    }
    @Test
    public void toLower106() {
        assertEq(8112,StringUtil.toLowerCase((char)8120));
    }
    @Test
    public void toLower107() {
        assertEq(8048,StringUtil.toLowerCase((char)8122));
    }
    @Test
    public void toLower108() {
        assertEq(8115,StringUtil.toLowerCase((char)8124));
    }
    @Test
    public void toLower109() {
        assertEq(8050,StringUtil.toLowerCase((char)8136));
    }
    @Test
    public void toLower110() {
        assertEq(8131,StringUtil.toLowerCase((char)8140));
    }
    @Test
    public void toLower111() {
        assertEq(8144,StringUtil.toLowerCase((char)8152));
    }
    @Test
    public void toLower112() {
        assertEq(8054,StringUtil.toLowerCase((char)8154));
    }
    @Test
    public void toLower113() {
        assertEq(8160,StringUtil.toLowerCase((char)8168));
    }
    @Test
    public void toLower114() {
        assertEq(8058,StringUtil.toLowerCase((char)8170));
    }
    @Test
    public void toLower115() {
        assertEq(8165,StringUtil.toLowerCase((char)8172));
    }
    @Test
    public void toLower116() {
        assertEq(8056,StringUtil.toLowerCase((char)8184));
    }
    @Test
    public void toLower117() {
        assertEq(8060,StringUtil.toLowerCase((char)8186));
    }
    @Test
    public void toLower118() {
        assertEq(8179,StringUtil.toLowerCase((char)8188));
    }
    @Test
    public void toLower119() {
        assertEq(969,StringUtil.toLowerCase((char)8486));
    }
    @Test
    public void toLower120() {
        assertEq(107,StringUtil.toLowerCase((char)8490));
    }
    @Test
    public void toLower121() {
        assertEq(229,StringUtil.toLowerCase((char)8491));
    }
    @Test
    public void toLower122() {
        assertEq(8526,StringUtil.toLowerCase((char)8498));
    }
    @Test
    public void toLower123() {
        assertEq(8560,StringUtil.toLowerCase((char)8544));
    }
    @Test
    public void toLower124() {
        assertEq(8580,StringUtil.toLowerCase((char)8579));
    }
    @Test
    public void toLower125() {
        assertEq(9424,StringUtil.toLowerCase((char)9398));
    }
    @Test
    public void toLower126() {
        assertEq(11312,StringUtil.toLowerCase((char)11264));
    }
    @Test
    public void toLower127() {
        assertEq(11361,StringUtil.toLowerCase((char)11360));
    }
    @Test
    public void toLower128() {
        assertEq(619,StringUtil.toLowerCase((char)11362));
    }
    @Test
    public void toLower129() {
        assertEq(7549,StringUtil.toLowerCase((char)11363));
    }
    @Test
    public void toLower130() {
        assertEq(637,StringUtil.toLowerCase((char)11364));
    }
    @Test
    public void toLower131() {
        assertEq(11368,StringUtil.toLowerCase((char)11367));
    }
    @Test
    public void toLower132() {
        assertEq(593,StringUtil.toLowerCase((char)11373));
    }
    @Test
    public void toLower133() {
        assertEq(625,StringUtil.toLowerCase((char)11374));
    }
    @Test
    public void toLower134() {
        assertEq(592,StringUtil.toLowerCase((char)11375));
    }
    @Test
    public void toLower135() {
        assertEq(594,StringUtil.toLowerCase((char)11376));
    }
    @Test
    public void toLower136() {
        assertEq(11379,StringUtil.toLowerCase((char)11378));
    }
    @Test
    public void toLower137() {
        assertEq(11382,StringUtil.toLowerCase((char)11381));
    }
    @Test
    public void toLower138() {
        assertEq(575,StringUtil.toLowerCase((char)11390));
    }
    @Test
    public void toLower139() {
        assertEq(11393,StringUtil.toLowerCase((char)11392));
    }
    @Test
    public void toLower140() {
        assertEq(11500,StringUtil.toLowerCase((char)11499));
    }
    @Test
    public void toLower141() {
        assertEq(11507,StringUtil.toLowerCase((char)11506));
    }
    @Test
    public void toLower142() {
        assertEq(42561,StringUtil.toLowerCase((char)42560));
    }
    @Test
    public void toLower143() {
        assertEq(42625,StringUtil.toLowerCase((char)42624));
    }
    @Test
    public void toLower144() {
        assertEq(42787,StringUtil.toLowerCase((char)42786));
    }
    @Test
    public void toLower145() {
        assertEq(42803,StringUtil.toLowerCase((char)42802));
    }
    @Test
    public void toLower146() {
        assertEq(42874,StringUtil.toLowerCase((char)42873));
    }
    @Test
    public void toLower147() {
        assertEq(7545,StringUtil.toLowerCase((char)42877));
    }
    @Test
    public void toLower148() {
        assertEq(42879,StringUtil.toLowerCase((char)42878));
    }
    @Test
    public void toLower149() {
        assertEq(42892,StringUtil.toLowerCase((char)42891));
    }
    @Test
    public void toLower150() {
        assertEq(613,StringUtil.toLowerCase((char)42893));
    }
    @Test
    public void toLower151() {
        assertEq(42897,StringUtil.toLowerCase((char)42896));
    }
    @Test
    public void toLower152() {
        assertEq(42913,StringUtil.toLowerCase((char)42912));
    }
    @Test
    public void toLower153() {
        assertEq(614,StringUtil.toLowerCase((char)42922));
    }
    @Test
    public void toLower154() {
        assertEq(65345,StringUtil.toLowerCase((char)65313));
    }
    @Test
    public void toLower155() {
        assertEq(65339,StringUtil.toLowerCase((char)65339));
    }
    @Test
    public void toLower156() {
        assertEq(65340,StringUtil.toLowerCase((char)65340));
    }
    @Test
    public void toLower157() {
        assertEq(1,StringUtil.toLowerCase((char)1));
    }
    @Test
    public void toUpper1() {
        assertEq(65,StringUtil.toUpperCase((char)97));
    }
    @Test
    public void toUpper2() {
        assertEq(924,StringUtil.toUpperCase((char)181));
    }
    @Test
    public void toUpper3() {
        assertEq(192,StringUtil.toUpperCase((char)224));
    }
    @Test
    public void toUpper4() {
        assertEq(216,StringUtil.toUpperCase((char)248));
    }
    @Test
    public void toUpper5() {
        assertEq(376,StringUtil.toUpperCase((char)255));
    }
    @Test
    public void toUpper6() {
        assertEq(256,StringUtil.toUpperCase((char)257));
    }
    @Test
    public void toUpper7() {
        assertEq(73,StringUtil.toUpperCase((char)305));
    }
    @Test
    public void toUpper8() {
        assertEq(306,StringUtil.toUpperCase((char)307));
    }
    @Test
    public void toUpper9() {
        assertEq(313,StringUtil.toUpperCase((char)314));
    }
    @Test
    public void toUpper10() {
        assertEq(330,StringUtil.toUpperCase((char)331));
    }
    @Test
    public void toUpper11() {
        assertEq(377,StringUtil.toUpperCase((char)378));
    }
    @Test
    public void toUpper12() {
        assertEq(579,StringUtil.toUpperCase((char)384));
    }
    @Test
    public void toUpper13() {
        assertEq(386,StringUtil.toUpperCase((char)387));
    }
    @Test
    public void toUpper14() {
        assertEq(391,StringUtil.toUpperCase((char)392));
    }
    @Test
    public void toUpper15() {
        assertEq(395,StringUtil.toUpperCase((char)396));
    }
    @Test
    public void toUpper16() {
        assertEq(401,StringUtil.toUpperCase((char)402));
    }
    @Test
    public void toUpper17() {
        assertEq(502,StringUtil.toUpperCase((char)405));
    }
    @Test
    public void toUpper18() {
        assertEq(408,StringUtil.toUpperCase((char)409));
    }
    @Test
    public void toUpper19() {
        assertEq(544,StringUtil.toUpperCase((char)414));
    }
    @Test
    public void toUpper20() {
        assertEq(416,StringUtil.toUpperCase((char)417));
    }
    @Test
    public void toUpper21() {
        assertEq(423,StringUtil.toUpperCase((char)424));
    }
    @Test
    public void toUpper22() {
        assertEq(428,StringUtil.toUpperCase((char)429));
    }
    @Test
    public void toUpper23() {
        assertEq(431,StringUtil.toUpperCase((char)432));
    }
    @Test
    public void toUpper24() {
        assertEq(435,StringUtil.toUpperCase((char)436));
    }
    @Test
    public void toUpper25() {
        assertEq(440,StringUtil.toUpperCase((char)441));
    }
    @Test
    public void toUpper26() {
        assertEq(444,StringUtil.toUpperCase((char)445));
    }
    @Test
    public void toUpper27() {
        assertEq(503,StringUtil.toUpperCase((char)447));
    }
    @Test
    public void toUpper28() {
        assertEq(452,StringUtil.toUpperCase((char)453));
    }
    @Test
    public void toUpper29() {
        assertEq(455,StringUtil.toUpperCase((char)456));
    }
    @Test
    public void toUpper30() {
        assertEq(458,StringUtil.toUpperCase((char)459));
    }
    @Test
    public void toUpper31() {
        assertEq(461,StringUtil.toUpperCase((char)462));
    }
    @Test
    public void toUpper32() {
        assertEq(478,StringUtil.toUpperCase((char)479));
    }
    @Test
    public void toUpper33() {
        assertEq(497,StringUtil.toUpperCase((char)498));
    }
    @Test
    public void toUpper34() {
        assertEq(500,StringUtil.toUpperCase((char)501));
    }
    @Test
    public void toUpper35() {
        assertEq(504,StringUtil.toUpperCase((char)505));
    }
    @Test
    public void toUpper36() {
        assertEq(546,StringUtil.toUpperCase((char)547));
    }
    @Test
    public void toUpper37() {
        assertEq(571,StringUtil.toUpperCase((char)572));
    }
    @Test
    public void toUpper38() {
        assertEq(11390,StringUtil.toUpperCase((char)575));
    }
    @Test
    public void toUpper39() {
        assertEq(577,StringUtil.toUpperCase((char)578));
    }
    @Test
    public void toUpper40() {
        assertEq(582,StringUtil.toUpperCase((char)583));
    }
    @Test
    public void toUpper41() {
        assertEq(11373,StringUtil.toUpperCase((char)593));
    }
    @Test
    public void toUpper42() {
        assertEq(11376,StringUtil.toUpperCase((char)594));
    }
    @Test
    public void toUpper43() {
        assertEq(385,StringUtil.toUpperCase((char)595));
    }
    @Test
    public void toUpper44() {
        assertEq(390,StringUtil.toUpperCase((char)596));
    }
    @Test
    public void toUpper45() {
        assertEq(393,StringUtil.toUpperCase((char)598));
    }
    @Test
    public void toUpper46() {
        assertEq(399,StringUtil.toUpperCase((char)601));
    }
    @Test
    public void toUpper47() {
        assertEq(400,StringUtil.toUpperCase((char)603));
    }
    @Test
    public void toUpper48() {
        assertEq(403,StringUtil.toUpperCase((char)608));
    }
    @Test
    public void toUpper49() {
        assertEq(404,StringUtil.toUpperCase((char)611));
    }
    @Test
    public void toUpper50() {
        assertEq(42893,StringUtil.toUpperCase((char)613));
    }
    @Test
    public void toUpper51() {
        assertEq(42922,StringUtil.toUpperCase((char)614));
    }
    @Test
    public void toUpper52() {
        assertEq(407,StringUtil.toUpperCase((char)616));
    }
    @Test
    public void toUpper53() {
        assertEq(406,StringUtil.toUpperCase((char)617));
    }
    @Test
    public void toUpper54() {
        assertEq(11362,StringUtil.toUpperCase((char)619));
    }
    @Test
    public void toUpper55() {
        assertEq(412,StringUtil.toUpperCase((char)623));
    }
    @Test
    public void toUpper56() {
        assertEq(11374,StringUtil.toUpperCase((char)625));
    }
    @Test
    public void toUpper57() {
        assertEq(413,StringUtil.toUpperCase((char)626));
    }
    @Test
    public void toUpper58() {
        assertEq(415,StringUtil.toUpperCase((char)629));
    }
    @Test
    public void toUpper59() {
        assertEq(11364,StringUtil.toUpperCase((char)637));
    }
    @Test
    public void toUpper60() {
        assertEq(422,StringUtil.toUpperCase((char)640));
    }
    @Test
    public void toUpper61() {
        assertEq(425,StringUtil.toUpperCase((char)643));
    }
    @Test
    public void toUpper62() {
        assertEq(430,StringUtil.toUpperCase((char)648));
    }
    @Test
    public void toUpper63() {
        assertEq(580,StringUtil.toUpperCase((char)649));
    }
    @Test
    public void toUpper64() {
        assertEq(433,StringUtil.toUpperCase((char)650));
    }
    @Test
    public void toUpper65() {
        assertEq(581,StringUtil.toUpperCase((char)652));
    }
    @Test
    public void toUpper66() {
        assertEq(439,StringUtil.toUpperCase((char)658));
    }
    @Test
    public void toUpper67() {
        assertEq(921,StringUtil.toUpperCase((char)837));
    }
    @Test
    public void toUpper68() {
        assertEq(880,StringUtil.toUpperCase((char)881));
    }
    @Test
    public void toUpper69() {
        assertEq(886,StringUtil.toUpperCase((char)887));
    }
    @Test
    public void toUpper70() {
        assertEq(1021,StringUtil.toUpperCase((char)891));
    }
    @Test
    public void toUpper71() {
        assertEq(902,StringUtil.toUpperCase((char)940));
    }
    @Test
    public void toUpper72() {
        assertEq(904,StringUtil.toUpperCase((char)941));
    }
    @Test
    public void toUpper73() {
        assertEq(913,StringUtil.toUpperCase((char)945));
    }
    @Test
    public void toUpper74() {
        assertEq(931,StringUtil.toUpperCase((char)962));
    }
    @Test
    public void toUpper75() {
        assertEq(931,StringUtil.toUpperCase((char)963));
    }
    @Test
    public void toUpper76() {
        assertEq(908,StringUtil.toUpperCase((char)972));
    }
    @Test
    public void toUpper77() {
        assertEq(910,StringUtil.toUpperCase((char)973));
    }
    @Test
    public void toUpper78() {
        assertEq(914,StringUtil.toUpperCase((char)976));
    }
    @Test
    public void toUpper79() {
        assertEq(920,StringUtil.toUpperCase((char)977));
    }
    @Test
    public void toUpper80() {
        assertEq(934,StringUtil.toUpperCase((char)981));
    }
    @Test
    public void toUpper81() {
        assertEq(928,StringUtil.toUpperCase((char)982));
    }
    @Test
    public void toUpper82() {
        assertEq(975,StringUtil.toUpperCase((char)983));
    }
    @Test
    public void toUpper83() {
        assertEq(984,StringUtil.toUpperCase((char)985));
    }
    @Test
    public void toUpper84() {
        assertEq(929,StringUtil.toUpperCase((char)1009));
    }
    @Test
    public void toUpper85() {
        assertEq(1017,StringUtil.toUpperCase((char)1010));
    }
    @Test
    public void toUpper86() {
        assertEq(917,StringUtil.toUpperCase((char)1013));
    }
    @Test
    public void toUpper87() {
        assertEq(1015,StringUtil.toUpperCase((char)1016));
    }
    @Test
    public void toUpper88() {
        assertEq(1018,StringUtil.toUpperCase((char)1019));
    }
    @Test
    public void toUpper89() {
        assertEq(1040,StringUtil.toUpperCase((char)1072));
    }
    @Test
    public void toUpper90() {
        assertEq(1024,StringUtil.toUpperCase((char)1104));
    }
    @Test
    public void toUpper91() {
        assertEq(1120,StringUtil.toUpperCase((char)1121));
    }
    @Test
    public void toUpper92() {
        assertEq(1162,StringUtil.toUpperCase((char)1163));
    }
    @Test
    public void toUpper93() {
        assertEq(1217,StringUtil.toUpperCase((char)1218));
    }
    @Test
    public void toUpper94() {
        assertEq(1232,StringUtil.toUpperCase((char)1233));
    }
    @Test
    public void toUpper95() {
        assertEq(1329,StringUtil.toUpperCase((char)1377));
    }
    @Test
    public void toUpper96() {
        assertEq(42877,StringUtil.toUpperCase((char)7545));
    }
    @Test
    public void toUpper97() {
        assertEq(11363,StringUtil.toUpperCase((char)7549));
    }
    @Test
    public void toUpper98() {
        assertEq(7680,StringUtil.toUpperCase((char)7681));
    }
    @Test
    public void toUpper99() {
        assertEq(7776,StringUtil.toUpperCase((char)7835));
    }
    @Test
    public void toUpper100() {
        assertEq(7840,StringUtil.toUpperCase((char)7841));
    }
    @Test
    public void toUpper101() {
        assertEq(7945,StringUtil.toUpperCase((char)7937));
    }
    @Test
    public void toUpper102() {
        assertEq(7960,StringUtil.toUpperCase((char)7952));
    }
    @Test
    public void toUpper103() {
        assertEq(7976,StringUtil.toUpperCase((char)7968));
    }
    @Test
    public void toUpper104() {
        assertEq(7992,StringUtil.toUpperCase((char)7984));
    }
    @Test
    public void toUpper105() {
        assertEq(8008,StringUtil.toUpperCase((char)8000));
    }
    @Test
    public void toUpper106() {
        assertEq(8025,StringUtil.toUpperCase((char)8017));
    }
    @Test
    public void toUpper107() {
        assertEq(8027,StringUtil.toUpperCase((char)8019));
    }
    @Test
    public void toUpper108() {
        assertEq(8029,StringUtil.toUpperCase((char)8021));
    }
    @Test
    public void toUpper109() {
        assertEq(8031,StringUtil.toUpperCase((char)8023));
    }
    @Test
    public void toUpper110() {
        assertEq(8040,StringUtil.toUpperCase((char)8032));
    }
    @Test
    public void toUpper111() {
        assertEq(8122,StringUtil.toUpperCase((char)8048));
    }
    @Test
    public void toUpper112() {
        assertEq(8136,StringUtil.toUpperCase((char)8050));
    }
    @Test
    public void toUpper113() {
        assertEq(8154,StringUtil.toUpperCase((char)8054));
    }
    @Test
    public void toUpper114() {
        assertEq(8184,StringUtil.toUpperCase((char)8056));
    }
    @Test
    public void toUpper115() {
        assertEq(8170,StringUtil.toUpperCase((char)8058));
    }
    @Test
    public void toUpper116() {
        assertEq(8186,StringUtil.toUpperCase((char)8060));
    }
    @Test
    public void toUpper117() {
        assertEq(8072,StringUtil.toUpperCase((char)8064));
    }
    @Test
    public void toUpper118() {
        assertEq(8088,StringUtil.toUpperCase((char)8080));
    }
    @Test
    public void toUpper119() {
        assertEq(8104,StringUtil.toUpperCase((char)8096));
    }
    @Test
    public void toUpper120() {
        assertEq(8120,StringUtil.toUpperCase((char)8112));
    }
    @Test
    public void toUpper121() {
        assertEq(8124,StringUtil.toUpperCase((char)8115));
    }
    @Test
    public void toUpper122() {
        assertEq(921,StringUtil.toUpperCase((char)8126));
    }
    @Test
    public void toUpper123() {
        assertEq(8140,StringUtil.toUpperCase((char)8131));
    }
    @Test
    public void toUpper124() {
        assertEq(8152,StringUtil.toUpperCase((char)8144));
    }
    @Test
    public void toUpper125() {
        assertEq(8168,StringUtil.toUpperCase((char)8160));
    }
    @Test
    public void toUpper126() {
        assertEq(8172,StringUtil.toUpperCase((char)8165));
    }
    @Test
    public void toUpper127() {
        assertEq(8188,StringUtil.toUpperCase((char)8179));
    }
    @Test
    public void toUpper128() {
        assertEq(8498,StringUtil.toUpperCase((char)8526));
    }
    @Test
    public void toUpper129() {
        assertEq(8544,StringUtil.toUpperCase((char)8560));
    }
    @Test
    public void toUpper130() {
        assertEq(8579,StringUtil.toUpperCase((char)8580));
    }
    @Test
    public void toUpper131() {
        assertEq(9398,StringUtil.toUpperCase((char)9424));
    }
    @Test
    public void toUpper132() {
        assertEq(11264,StringUtil.toUpperCase((char)11312));
    }
    @Test
    public void toUpper133() {
        assertEq(11360,StringUtil.toUpperCase((char)11361));
    }
    @Test
    public void toUpper134() {
        assertEq(570,StringUtil.toUpperCase((char)11365));
    }
    @Test
    public void toUpper135() {
        assertEq(574,StringUtil.toUpperCase((char)11366));
    }
    @Test
    public void toUpper136() {
        assertEq(11367,StringUtil.toUpperCase((char)11368));
    }
    @Test
    public void toUpper137() {
        assertEq(11378,StringUtil.toUpperCase((char)11379));
    }
    @Test
    public void toUpper138() {
        assertEq(11381,StringUtil.toUpperCase((char)11382));
    }
    @Test
    public void toUpper139() {
        assertEq(11392,StringUtil.toUpperCase((char)11393));
    }
    @Test
    public void toUpper140() {
        assertEq(11499,StringUtil.toUpperCase((char)11500));
    }
    @Test
    public void toUpper141() {
        assertEq(11506,StringUtil.toUpperCase((char)11507));
    }
    @Test
    public void toUpper142() {
        assertEq(4256,StringUtil.toUpperCase((char)11520));
    }
    @Test
    public void toUpper143() {
        assertEq(4295,StringUtil.toUpperCase((char)11559));
    }
    @Test
    public void toUpper144() {
        assertEq(4301,StringUtil.toUpperCase((char)11565));
    }
    @Test
    public void toUpper145() {
        assertEq(42560,StringUtil.toUpperCase((char)42561));
    }
    @Test
    public void toUpper146() {
        assertEq(42624,StringUtil.toUpperCase((char)42625));
    }
    @Test
    public void toUpper147() {
        assertEq(42786,StringUtil.toUpperCase((char)42787));
    }
    @Test
    public void toUpper148() {
        assertEq(42802,StringUtil.toUpperCase((char)42803));
    }
    @Test
    public void toUpper149() {
        assertEq(42873,StringUtil.toUpperCase((char)42874));
    }
    @Test
    public void toUpper150() {
        assertEq(42878,StringUtil.toUpperCase((char)42879));
    }
    @Test
    public void toUpper151() {
        assertEq(42891,StringUtil.toUpperCase((char)42892));
    }
    @Test
    public void toUpper152() {
        assertEq(42896,StringUtil.toUpperCase((char)42897));
    }
    @Test
    public void toUpper153() {
        assertEq(42912,StringUtil.toUpperCase((char)42913));
    }
    @Test
    public void toUpper154() {
        assertEq(65313,StringUtil.toUpperCase((char)65345));
    }
    @Test
    public void toUpper155() {
        assertEq(65371,StringUtil.toUpperCase((char)65371));
    }
    @Test
    public void toUpper156() {
        assertEq(42878,StringUtil.toUpperCase((char)42878));
    }
    @Test
    public void toUpper157() {
        assertEq(1,StringUtil.toUpperCase((char)1));
    }
    @Test
    public void toUpper158() {
        assertEq(83,StringUtil.toUpperCase((char)383));
    }
    @Test
    public void isLower1() {
        assertTrue(!StringUtil.isLowerCase((char)1));
    }
    @Test
    public void isLower2() {
        assertTrue(!StringUtil.isLowerCase((char)65));
    }
    @Test
    public void isLower3() {
        assertTrue(StringUtil.isLowerCase((char)97));
    }
    @Test
    public void isUpper1() {
        assertTrue(!StringUtil.isUpperCase((char)1));
    }
    @Test
    public void isUpper2() {
        assertTrue(StringUtil.isUpperCase((char)65));
    }
    @Test
    public void isUpper3() {
        assertTrue(!StringUtil.isUpperCase((char)97));
    }
    @Test
    public void digit1() {
        assertEq(-1,StringUtil.digit('0',1));
    }
    @Test
    public void digit2() {
        assertEq(-1,StringUtil.digit('0',37));
    }
    @Test
    public void digit3() {
        assertEq(-1,StringUtil.digit('8',5));
    }
    @Test
    public void digit4() {
        assertEq(8,StringUtil.digit('8',9));
    }
    @Test
    public void digit5() {
        assertEq(-1,StringUtil.digit('a',9));
    }
    @Test
    public void digit6() {
        assertEq(10,StringUtil.digit('a',11));
    }
    @Test
    public void digit7() {
        assertEq(-1,StringUtil.digit('A',9));
    }
    @Test
    public void digit8() {
        assertEq(10,StringUtil.digit('A',11));
    }
    @Test
    public void digit9() {
        assertEq(-1,StringUtil.digit('!',36));
    }
    @Test
    public void digit10() {
        assertEq(-1,StringUtil.digit('|',36));
    }
    @Test
    public void forDigit1() {
        assertEq(0,StringUtil.forDigit(0,1));
    }
    @Test
    public void forDigit2() {
        assertEq(0,StringUtil.forDigit(0,37));
    }
    @Test
    public void forDigit3() {
        assertEq(0,StringUtil.forDigit(8,5));
    }
    @Test
    public void forDigit4() {
        assertEq(0,StringUtil.forDigit(-1,2));
    }
    @Test
    public void forDigit5() {
        assertEq('1',StringUtil.forDigit(1,9));
    }
    @Test
    public void forDigit6() {
        assertEq('a',StringUtil.forDigit(10,11));
    }
    @Test
    public void forDigit7() {
        assertEq('b',StringUtil.forDigit(11,12));
    }
    @Test
    public void isUp1(){
        assertTrue(StringUtil.isUpperCase((char) 65));
    }
    @Test
    public void isUp2(){
        assertTrue(StringUtil.isUpperCase((char) 90));
    }
    @Test
    public void isUp3(){
        assertTrue(StringUtil.isUpperCase((char) 192));
    }
    @Test
    public void isUp4(){
        assertTrue(StringUtil.isUpperCase((char) 214));
    }
    @Test
    public void isUp5(){
        assertTrue(StringUtil.isUpperCase((char) 216));
    }
    @Test
    public void isUp6(){
        assertTrue(StringUtil.isUpperCase((char) 222));
    }
    @Test
    public void isUp7(){
        assertTrue(StringUtil.isUpperCase((char) 256));
    }
    @Test
    public void isUp8(){
        assertTrue(StringUtil.isUpperCase((char) 258));
    }
    @Test
    public void isUp9(){
        assertTrue(StringUtil.isUpperCase((char) 260));
    }
    @Test
    public void isUp10(){
        assertTrue(StringUtil.isUpperCase((char) 262));
    }
    @Test
    public void isUp11(){
        assertTrue(StringUtil.isUpperCase((char) 264));
    }
    @Test
    public void isUp12(){
        assertTrue(StringUtil.isUpperCase((char) 266));
    }
    @Test
    public void isUp13(){
        assertTrue(StringUtil.isUpperCase((char) 268));
    }
    @Test
    public void isUp14(){
        assertTrue(StringUtil.isUpperCase((char) 270));
    }
    @Test
    public void isUp15(){
        assertTrue(StringUtil.isUpperCase((char) 272));
    }
    @Test
    public void isUp16(){
        assertTrue(StringUtil.isUpperCase((char) 274));
    }
    @Test
    public void isUp17(){
        assertTrue(StringUtil.isUpperCase((char) 276));
    }
    @Test
    public void isUp18(){
        assertTrue(StringUtil.isUpperCase((char) 278));
    }
    @Test
    public void isUp19(){
        assertTrue(StringUtil.isUpperCase((char) 280));
    }
    @Test
    public void isUp20(){
        assertTrue(StringUtil.isUpperCase((char) 282));
    }
    @Test
    public void isUp21(){
        assertTrue(StringUtil.isUpperCase((char) 284));
    }
    @Test
    public void isUp22(){
        assertTrue(StringUtil.isUpperCase((char) 286));
    }
    @Test
    public void isUp23(){
        assertTrue(StringUtil.isUpperCase((char) 288));
    }
    @Test
    public void isUp24(){
        assertTrue(StringUtil.isUpperCase((char) 290));
    }
    @Test
    public void isUp25(){
        assertTrue(StringUtil.isUpperCase((char) 292));
    }
    @Test
    public void isUp26(){
        assertTrue(StringUtil.isUpperCase((char) 294));
    }
    @Test
    public void isUp27(){
        assertTrue(StringUtil.isUpperCase((char) 296));
    }
    @Test
    public void isUp28(){
        assertTrue(StringUtil.isUpperCase((char) 298));
    }
    @Test
    public void isUp29(){
        assertTrue(StringUtil.isUpperCase((char) 300));
    }
    @Test
    public void isUp30(){
        assertTrue(StringUtil.isUpperCase((char) 302));
    }
    @Test
    public void isUp31(){
        assertTrue(StringUtil.isUpperCase((char) 304));
    }
    @Test
    public void isUp32(){
        assertTrue(StringUtil.isUpperCase((char) 306));
    }
    @Test
    public void isUp33(){
        assertTrue(StringUtil.isUpperCase((char) 308));
    }
    @Test
    public void isUp34(){
        assertTrue(StringUtil.isUpperCase((char) 310));
    }
    @Test
    public void isUp35(){
        assertTrue(StringUtil.isUpperCase((char) 313));
    }
    @Test
    public void isUp36(){
        assertTrue(StringUtil.isUpperCase((char) 315));
    }
    @Test
    public void isUp37(){
        assertTrue(StringUtil.isUpperCase((char) 317));
    }
    @Test
    public void isUp38(){
        assertTrue(StringUtil.isUpperCase((char) 319));
    }
    @Test
    public void isUp39(){
        assertTrue(StringUtil.isUpperCase((char) 321));
    }
    @Test
    public void isUp40(){
        assertTrue(StringUtil.isUpperCase((char) 323));
    }
    @Test
    public void isUp41(){
        assertTrue(StringUtil.isUpperCase((char) 325));
    }
    @Test
    public void isUp42(){
        assertTrue(StringUtil.isUpperCase((char) 327));
    }
    @Test
    public void isUp43(){
        assertTrue(StringUtil.isUpperCase((char) 330));
    }
    @Test
    public void isUp44(){
        assertTrue(StringUtil.isUpperCase((char) 332));
    }
    @Test
    public void isUp45(){
        assertTrue(StringUtil.isUpperCase((char) 334));
    }
    @Test
    public void isUp46(){
        assertTrue(StringUtil.isUpperCase((char) 336));
    }
    @Test
    public void isUp47(){
        assertTrue(StringUtil.isUpperCase((char) 338));
    }
    @Test
    public void isUp48(){
        assertTrue(StringUtil.isUpperCase((char) 340));
    }
    @Test
    public void isUp49(){
        assertTrue(StringUtil.isUpperCase((char) 342));
    }
    @Test
    public void isUp50(){
        assertTrue(StringUtil.isUpperCase((char) 344));
    }
    @Test
    public void isUp51(){
        assertTrue(StringUtil.isUpperCase((char) 346));
    }
    @Test
    public void isUp52(){
        assertTrue(StringUtil.isUpperCase((char) 348));
    }
    @Test
    public void isUp53(){
        assertTrue(StringUtil.isUpperCase((char) 350));
    }
    @Test
    public void isUp54(){
        assertTrue(StringUtil.isUpperCase((char) 352));
    }
    @Test
    public void isUp55(){
        assertTrue(StringUtil.isUpperCase((char) 354));
    }
    @Test
    public void isUp56(){
        assertTrue(StringUtil.isUpperCase((char) 356));
    }
    @Test
    public void isUp57(){
        assertTrue(StringUtil.isUpperCase((char) 358));
    }
    @Test
    public void isUp58(){
        assertTrue(StringUtil.isUpperCase((char) 360));
    }
    @Test
    public void isUp59(){
        assertTrue(StringUtil.isUpperCase((char) 362));
    }
    @Test
    public void isUp60(){
        assertTrue(StringUtil.isUpperCase((char) 364));
    }
    @Test
    public void isUp61(){
        assertTrue(StringUtil.isUpperCase((char) 366));
    }
    @Test
    public void isUp62(){
        assertTrue(StringUtil.isUpperCase((char) 368));
    }
    @Test
    public void isUp63(){
        assertTrue(StringUtil.isUpperCase((char) 370));
    }
    @Test
    public void isUp64(){
        assertTrue(StringUtil.isUpperCase((char) 372));
    }
    @Test
    public void isUp65(){
        assertTrue(StringUtil.isUpperCase((char) 374));
    }
    @Test
    public void isUp66(){
        assertTrue(StringUtil.isUpperCase((char) 376));
    }
    @Test
    public void isUp67(){
        assertTrue(StringUtil.isUpperCase((char) 377));
    }
    @Test
    public void isUp68(){
        assertTrue(StringUtil.isUpperCase((char) 379));
    }
    @Test
    public void isUp69(){
        assertTrue(StringUtil.isUpperCase((char) 381));
    }
    @Test
    public void isUp70(){
        assertTrue(StringUtil.isUpperCase((char) 385));
    }
    @Test
    public void isUp71(){
        assertTrue(StringUtil.isUpperCase((char) 386));
    }
    @Test
    public void isUp72(){
        assertTrue(StringUtil.isUpperCase((char) 388));
    }
    @Test
    public void isUp73(){
        assertTrue(StringUtil.isUpperCase((char) 390));
    }
    @Test
    public void isUp74(){
        assertTrue(StringUtil.isUpperCase((char) 391));
    }
    @Test
    public void isUp75(){
        assertTrue(StringUtil.isUpperCase((char) 393));
    }
    @Test
    public void isUp76(){
        assertTrue(StringUtil.isUpperCase((char) 395));
    }
    @Test
    public void isUp77(){
        assertTrue(StringUtil.isUpperCase((char) 398));
    }
    @Test
    public void isUp78(){
        assertTrue(StringUtil.isUpperCase((char) 401));
    }
    @Test
    public void isUp79(){
        assertTrue(StringUtil.isUpperCase((char) 403));
    }
    @Test
    public void isUp80(){
        assertTrue(StringUtil.isUpperCase((char) 404));
    }
    @Test
    public void isUp81(){
        assertTrue(StringUtil.isUpperCase((char) 406));
    }
    @Test
    public void isUp82(){
        assertTrue(StringUtil.isUpperCase((char) 408));
    }
    @Test
    public void isUp83(){
        assertTrue(StringUtil.isUpperCase((char) 412));
    }
    @Test
    public void isUp84(){
        assertTrue(StringUtil.isUpperCase((char) 413));
    }
    @Test
    public void isUp85(){
        assertTrue(StringUtil.isUpperCase((char) 415));
    }
    @Test
    public void isUp86(){
        assertTrue(StringUtil.isUpperCase((char) 416));
    }
    @Test
    public void isUp87(){
        assertTrue(StringUtil.isUpperCase((char) 418));
    }
    @Test
    public void isUp88(){
        assertTrue(StringUtil.isUpperCase((char) 420));
    }
    @Test
    public void isUp89(){
        assertTrue(StringUtil.isUpperCase((char) 422));
    }
    @Test
    public void isUp90(){
        assertTrue(StringUtil.isUpperCase((char) 423));
    }
    @Test
    public void isUp91(){
        assertTrue(StringUtil.isUpperCase((char) 425));
    }
    @Test
    public void isUp92(){
        assertTrue(StringUtil.isUpperCase((char) 428));
    }
    @Test
    public void isUp93(){
        assertTrue(StringUtil.isUpperCase((char) 430));
    }
    @Test
    public void isUp94(){
        assertTrue(StringUtil.isUpperCase((char) 431));
    }
    @Test
    public void isUp95(){
        assertTrue(StringUtil.isUpperCase((char) 433));
    }
    @Test
    public void isUp96(){
        assertTrue(StringUtil.isUpperCase((char) 435));
    }
    @Test
    public void isUp97(){
        assertTrue(StringUtil.isUpperCase((char) 437));
    }
    @Test
    public void isUp98(){
        assertTrue(StringUtil.isUpperCase((char) 439));
    }
    @Test
    public void isUp99(){
        assertTrue(StringUtil.isUpperCase((char) 440));
    }
    @Test
    public void isUp100(){
        assertTrue(StringUtil.isUpperCase((char) 444));
    }
    @Test
    public void isUp101(){
        assertTrue(StringUtil.isUpperCase((char) 452));
    }
    @Test
    public void isUp102(){
        assertTrue(StringUtil.isUpperCase((char) 455));
    }
    @Test
    public void isUp103(){
        assertTrue(StringUtil.isUpperCase((char) 458));
    }
    @Test
    public void isUp104(){
        assertTrue(!StringUtil.isUpperCase((char) 459));
    }
    @Test
    public void isUp105(){
        assertTrue(StringUtil.isUpperCase((char) 461));
    }
    @Test
    public void isUp106(){
        assertTrue(StringUtil.isUpperCase((char) 463));
    }
    @Test
    public void isUp107(){
        assertTrue(StringUtil.isUpperCase((char) 465));
    }
    @Test
    public void isUp108(){
        assertTrue(StringUtil.isUpperCase((char) 467));
    }
    @Test
    public void isUp109(){
        assertTrue(StringUtil.isUpperCase((char) 469));
    }
    @Test
    public void isUp110(){
        assertTrue(StringUtil.isUpperCase((char) 471));
    }
    @Test
    public void isUp111(){
        assertTrue(StringUtil.isUpperCase((char) 473));
    }
    @Test
    public void isUp112(){
        assertTrue(StringUtil.isUpperCase((char) 475));
    }
    @Test
    public void isUp113(){
        assertTrue(StringUtil.isUpperCase((char) 478));
    }
    @Test
    public void isUp114(){
        assertTrue(StringUtil.isUpperCase((char) 480));
    }
    @Test
    public void isUp115(){
        assertTrue(StringUtil.isUpperCase((char) 482));
    }
    @Test
    public void isUp116(){
        assertTrue(StringUtil.isUpperCase((char) 484));
    }
    @Test
    public void isUp117(){
        assertTrue(StringUtil.isUpperCase((char) 486));
    }
    @Test
    public void isUp118(){
        assertTrue(StringUtil.isUpperCase((char) 488));
    }
    @Test
    public void isUp119(){
        assertTrue(StringUtil.isUpperCase((char) 490));
    }
    @Test
    public void isUp120(){
        assertTrue(StringUtil.isUpperCase((char) 492));
    }
    @Test
    public void isUp121(){
        assertTrue(StringUtil.isUpperCase((char) 494));
    }
    @Test
    public void isUp122(){
        assertTrue(StringUtil.isUpperCase((char) 497));
    }
    @Test
    public void isUp123(){
        assertTrue(!StringUtil.isUpperCase((char) 498));
    }
    @Test
    public void isUp124(){
        assertTrue(StringUtil.isUpperCase((char) 500));
    }
    @Test
    public void isUp125(){
        assertTrue(StringUtil.isUpperCase((char) 502));
    }
    @Test
    public void isUp126(){
        assertTrue(StringUtil.isUpperCase((char) 504));
    }
    @Test
    public void isUp127(){
        assertTrue(StringUtil.isUpperCase((char) 506));
    }
    @Test
    public void isUp128(){
        assertTrue(StringUtil.isUpperCase((char) 508));
    }
    @Test
    public void isUp129(){
        assertTrue(StringUtil.isUpperCase((char) 510));
    }
    @Test
    public void isUp130(){
        assertTrue(StringUtil.isUpperCase((char) 512));
    }
    @Test
    public void isUp131(){
        assertTrue(StringUtil.isUpperCase((char) 514));
    }
    @Test
    public void isUp132(){
        assertTrue(StringUtil.isUpperCase((char) 516));
    }
    @Test
    public void isUp133(){
        assertTrue(StringUtil.isUpperCase((char) 518));
    }
    @Test
    public void isUp134(){
        assertTrue(StringUtil.isUpperCase((char) 520));
    }
    @Test
    public void isUp135(){
        assertTrue(StringUtil.isUpperCase((char) 522));
    }
    @Test
    public void isUp136(){
        assertTrue(StringUtil.isUpperCase((char) 524));
    }
    @Test
    public void isUp137(){
        assertTrue(StringUtil.isUpperCase((char) 526));
    }
    @Test
    public void isUp138(){
        assertTrue(StringUtil.isUpperCase((char) 528));
    }
    @Test
    public void isUp139(){
        assertTrue(StringUtil.isUpperCase((char) 530));
    }
    @Test
    public void isUp140(){
        assertTrue(StringUtil.isUpperCase((char) 532));
    }
    @Test
    public void isUp141(){
        assertTrue(StringUtil.isUpperCase((char) 534));
    }
    @Test
    public void isUp142(){
        assertTrue(StringUtil.isUpperCase((char) 536));
    }
    @Test
    public void isUp143(){
        assertTrue(StringUtil.isUpperCase((char) 538));
    }
    @Test
    public void isUp144(){
        assertTrue(StringUtil.isUpperCase((char) 540));
    }
    @Test
    public void isUp145(){
        assertTrue(StringUtil.isUpperCase((char) 542));
    }
    @Test
    public void isUp146(){
        assertTrue(StringUtil.isUpperCase((char) 544));
    }
    @Test
    public void isUp147(){
        assertTrue(StringUtil.isUpperCase((char) 546));
    }
    @Test
    public void isUp148(){
        assertTrue(StringUtil.isUpperCase((char) 548));
    }
    @Test
    public void isUp149(){
        assertTrue(StringUtil.isUpperCase((char) 550));
    }
    @Test
    public void isUp150(){
        assertTrue(StringUtil.isUpperCase((char) 552));
    }
    @Test
    public void isUp151(){
        assertTrue(StringUtil.isUpperCase((char) 554));
    }
    @Test
    public void isUp152(){
        assertTrue(StringUtil.isUpperCase((char) 556));
    }
    @Test
    public void isUp153(){
        assertTrue(StringUtil.isUpperCase((char) 558));
    }
    @Test
    public void isUp154(){
        assertTrue(StringUtil.isUpperCase((char) 560));
    }
    @Test
    public void isUp155(){
        assertTrue(StringUtil.isUpperCase((char) 562));
    }
    @Test
    public void isUp156(){
        assertTrue(StringUtil.isUpperCase((char) 570));
    }
    @Test
    public void isUp157(){
        assertTrue(StringUtil.isUpperCase((char) 571));
    }
    @Test
    public void isUp158(){
        assertTrue(StringUtil.isUpperCase((char) 573));
    }
    @Test
    public void isUp159(){
        assertTrue(StringUtil.isUpperCase((char) 574));
    }
    @Test
    public void isUp160(){
        assertTrue(StringUtil.isUpperCase((char) 577));
    }
    @Test
    public void isUp161(){
        assertTrue(StringUtil.isUpperCase((char) 579));
    }
    @Test
    public void isUp162(){
        assertTrue(StringUtil.isUpperCase((char) 582));
    }
    @Test
    public void isUp163(){
        assertTrue(StringUtil.isUpperCase((char) 584));
    }
    @Test
    public void isUp164(){
        assertTrue(StringUtil.isUpperCase((char) 586));
    }
    @Test
    public void isUp165(){
        assertTrue(StringUtil.isUpperCase((char) 588));
    }
    @Test
    public void isUp166(){
        assertTrue(StringUtil.isUpperCase((char) 590));
    }
    @Test
    public void isUp167(){
        assertTrue(StringUtil.isUpperCase((char) 880));
    }
    @Test
    public void isUp168(){
        assertTrue(StringUtil.isUpperCase((char) 882));
    }
    @Test
    public void isUp169(){
        assertTrue(StringUtil.isUpperCase((char) 886));
    }
    @Test
    public void isUp170(){
        assertTrue(StringUtil.isUpperCase((char) 902));
    }
    @Test
    public void isUp171(){
        assertTrue(StringUtil.isUpperCase((char) 904));
    }
    @Test
    public void isUp172(){
        assertTrue(StringUtil.isUpperCase((char) 906));
    }
    @Test
    public void isUp173(){
        assertTrue(StringUtil.isUpperCase((char) 908));
    }
    @Test
    public void isUp174(){
        assertTrue(StringUtil.isUpperCase((char) 910));
    }
    @Test
    public void isUp175(){
        assertTrue(StringUtil.isUpperCase((char) 911));
    }
    @Test
    public void isUp176(){
        assertTrue(StringUtil.isUpperCase((char) 913));
    }
    @Test
    public void isUp177(){
        assertTrue(StringUtil.isUpperCase((char) 929));
    }
    @Test
    public void isUp178(){
        assertTrue(StringUtil.isUpperCase((char) 931));
    }
    @Test
    public void isUp179(){
        assertTrue(StringUtil.isUpperCase((char) 939));
    }
    @Test
    public void isUp180(){
        assertTrue(StringUtil.isUpperCase((char) 975));
    }
    @Test
    public void isUp181(){
        assertTrue(StringUtil.isUpperCase((char) 984));
    }
    @Test
    public void isUp182(){
        assertTrue(StringUtil.isUpperCase((char) 986));
    }
    @Test
    public void isUp183(){
        assertTrue(StringUtil.isUpperCase((char) 988));
    }
    @Test
    public void isUp184(){
        assertTrue(StringUtil.isUpperCase((char) 990));
    }
    @Test
    public void isUp185(){
        assertTrue(StringUtil.isUpperCase((char) 992));
    }
    @Test
    public void isUp186(){
        assertTrue(StringUtil.isUpperCase((char) 994));
    }
    @Test
    public void isUp187(){
        assertTrue(StringUtil.isUpperCase((char) 996));
    }
    @Test
    public void isUp188(){
        assertTrue(StringUtil.isUpperCase((char) 998));
    }
    @Test
    public void isUp189(){
        assertTrue(StringUtil.isUpperCase((char) 1000));
    }
    @Test
    public void isUp190(){
        assertTrue(StringUtil.isUpperCase((char) 1002));
    }
    @Test
    public void isUp191(){
        assertTrue(StringUtil.isUpperCase((char) 1004));
    }
    @Test
    public void isUp192(){
        assertTrue(StringUtil.isUpperCase((char) 1006));
    }
    @Test
    public void isUp193(){
        assertTrue(StringUtil.isUpperCase((char) 1012));
    }
    @Test
    public void isUp194(){
        assertTrue(StringUtil.isUpperCase((char) 1015));
    }
    @Test
    public void isUp195(){
        assertTrue(StringUtil.isUpperCase((char) 1017));
    }
    @Test
    public void isUp196(){
        assertTrue(StringUtil.isUpperCase((char) 1018));
    }
    @Test
    public void isUp197(){
        assertTrue(StringUtil.isUpperCase((char) 1021));
    }
    @Test
    public void isUp198(){
        assertTrue(StringUtil.isUpperCase((char) 1071));
    }
    @Test
    public void isUp199(){
        assertTrue(StringUtil.isUpperCase((char) 1120));
    }
    @Test
    public void isUp200(){
        assertTrue(StringUtil.isUpperCase((char) 1122));
    }
    @Test
    public void isUp201(){
        assertTrue(StringUtil.isUpperCase((char) 1124));
    }
    @Test
    public void isUp202(){
        assertTrue(StringUtil.isUpperCase((char) 1126));
    }
    @Test
    public void isUp203(){
        assertTrue(StringUtil.isUpperCase((char) 1128));
    }
    @Test
    public void isUp204(){
        assertTrue(StringUtil.isUpperCase((char) 1130));
    }
    @Test
    public void isUp205(){
        assertTrue(StringUtil.isUpperCase((char) 1132));
    }
    @Test
    public void isUp206(){
        assertTrue(StringUtil.isUpperCase((char) 1134));
    }
    @Test
    public void isUp207(){
        assertTrue(StringUtil.isUpperCase((char) 1136));
    }
    @Test
    public void isUp208(){
        assertTrue(StringUtil.isUpperCase((char) 1138));
    }
    @Test
    public void isUp209(){
        assertTrue(StringUtil.isUpperCase((char) 1140));
    }
    @Test
    public void isUp210(){
        assertTrue(StringUtil.isUpperCase((char) 1142));
    }
    @Test
    public void isUp211(){
        assertTrue(StringUtil.isUpperCase((char) 1144));
    }
    @Test
    public void isUp212(){
        assertTrue(StringUtil.isUpperCase((char) 1146));
    }
    @Test
    public void isUp213(){
        assertTrue(StringUtil.isUpperCase((char) 1148));
    }
    @Test
    public void isUp214(){
        assertTrue(StringUtil.isUpperCase((char) 1150));
    }
    @Test
    public void isUp215(){
        assertTrue(StringUtil.isUpperCase((char) 1152));
    }
    @Test
    public void isUp216(){
        assertTrue(StringUtil.isUpperCase((char) 1162));
    }
    @Test
    public void isUp217(){
        assertTrue(StringUtil.isUpperCase((char) 1164));
    }
    @Test
    public void isUp218(){
        assertTrue(StringUtil.isUpperCase((char) 1166));
    }
    @Test
    public void isUp219(){
        assertTrue(StringUtil.isUpperCase((char) 1168));
    }
    @Test
    public void isUp220(){
        assertTrue(StringUtil.isUpperCase((char) 1170));
    }
    @Test
    public void isUp221(){
        assertTrue(StringUtil.isUpperCase((char) 1172));
    }
    @Test
    public void isUp222(){
        assertTrue(StringUtil.isUpperCase((char) 1174));
    }
    @Test
    public void isUp223(){
        assertTrue(StringUtil.isUpperCase((char) 1176));
    }
    @Test
    public void isUp224(){
        assertTrue(StringUtil.isUpperCase((char) 1178));
    }
    @Test
    public void isUp225(){
        assertTrue(StringUtil.isUpperCase((char) 1180));
    }
    @Test
    public void isUp226(){
        assertTrue(StringUtil.isUpperCase((char) 1182));
    }
    @Test
    public void isUp227(){
        assertTrue(StringUtil.isUpperCase((char) 1184));
    }
    @Test
    public void isUp228(){
        assertTrue(StringUtil.isUpperCase((char) 1186));
    }
    @Test
    public void isUp229(){
        assertTrue(StringUtil.isUpperCase((char) 1188));
    }
    @Test
    public void isUp230(){
        assertTrue(StringUtil.isUpperCase((char) 1190));
    }
    @Test
    public void isUp231(){
        assertTrue(StringUtil.isUpperCase((char) 1192));
    }
    @Test
    public void isUp232(){
        assertTrue(StringUtil.isUpperCase((char) 1194));
    }
    @Test
    public void isUp233(){
        assertTrue(StringUtil.isUpperCase((char) 1196));
    }
    @Test
    public void isUp234(){
        assertTrue(StringUtil.isUpperCase((char) 1198));
    }
    @Test
    public void isUp235(){
        assertTrue(StringUtil.isUpperCase((char) 1200));
    }
    @Test
    public void isUp236(){
        assertTrue(StringUtil.isUpperCase((char) 1202));
    }
    @Test
    public void isUp237(){
        assertTrue(StringUtil.isUpperCase((char) 1204));
    }
    @Test
    public void isUp238(){
        assertTrue(StringUtil.isUpperCase((char) 1206));
    }
    @Test
    public void isUp239(){
        assertTrue(StringUtil.isUpperCase((char) 1208));
    }
    @Test
    public void isUp240(){
        assertTrue(StringUtil.isUpperCase((char) 1210));
    }
    @Test
    public void isUp241(){
        assertTrue(StringUtil.isUpperCase((char) 1212));
    }
    @Test
    public void isUp242(){
        assertTrue(StringUtil.isUpperCase((char) 1214));
    }
    @Test
    public void isUp243(){
        assertTrue(StringUtil.isUpperCase((char) 1216));
    }
    @Test
    public void isUp244(){
        assertTrue(StringUtil.isUpperCase((char) 1217));
    }
    @Test
    public void isUp245(){
        assertTrue(StringUtil.isUpperCase((char) 1219));
    }
    @Test
    public void isUp246(){
        assertTrue(StringUtil.isUpperCase((char) 1221));
    }
    @Test
    public void isUp247(){
        assertTrue(StringUtil.isUpperCase((char) 1223));
    }
    @Test
    public void isUp248(){
        assertTrue(StringUtil.isUpperCase((char) 1225));
    }
    @Test
    public void isUp249(){
        assertTrue(StringUtil.isUpperCase((char) 1227));
    }
    @Test
    public void isUp250(){
        assertTrue(StringUtil.isUpperCase((char) 1229));
    }
    @Test
    public void isUp251(){
        assertTrue(StringUtil.isUpperCase((char) 1232));
    }
    @Test
    public void isUp252(){
        assertTrue(StringUtil.isUpperCase((char) 1234));
    }
    @Test
    public void isUp253(){
        assertTrue(StringUtil.isUpperCase((char) 1236));
    }
    @Test
    public void isUp254(){
        assertTrue(StringUtil.isUpperCase((char) 1238));
    }
    @Test
    public void isUp255(){
        assertTrue(StringUtil.isUpperCase((char) 1240));
    }
    @Test
    public void isUp256(){
        assertTrue(StringUtil.isUpperCase((char) 1242));
    }
    @Test
    public void isUp257(){
        assertTrue(StringUtil.isUpperCase((char) 1244));
    }
    @Test
    public void isUp258(){
        assertTrue(StringUtil.isUpperCase((char) 1246));
    }
    @Test
    public void isUp259(){
        assertTrue(StringUtil.isUpperCase((char) 1248));
    }
    @Test
    public void isUp260(){
        assertTrue(StringUtil.isUpperCase((char) 1250));
    }
    @Test
    public void isUp261(){
        assertTrue(StringUtil.isUpperCase((char) 1252));
    }
    @Test
    public void isUp262(){
        assertTrue(StringUtil.isUpperCase((char) 1254));
    }
    @Test
    public void isUp263(){
        assertTrue(StringUtil.isUpperCase((char) 1256));
    }
    @Test
    public void isUp264(){
        assertTrue(StringUtil.isUpperCase((char) 1258));
    }
    @Test
    public void isUp265(){
        assertTrue(StringUtil.isUpperCase((char) 1260));
    }
    @Test
    public void isUp266(){
        assertTrue(StringUtil.isUpperCase((char) 1262));
    }
    @Test
    public void isUp267(){
        assertTrue(StringUtil.isUpperCase((char) 1264));
    }
    @Test
    public void isUp268(){
        assertTrue(StringUtil.isUpperCase((char) 1266));
    }
    @Test
    public void isUp269(){
        assertTrue(StringUtil.isUpperCase((char) 1268));
    }
    @Test
    public void isUp270(){
        assertTrue(StringUtil.isUpperCase((char) 1270));
    }
    @Test
    public void isUp271(){
        assertTrue(StringUtil.isUpperCase((char) 1272));
    }
    @Test
    public void isUp272(){
        assertTrue(StringUtil.isUpperCase((char) 1274));
    }
    @Test
    public void isUp273(){
        assertTrue(StringUtil.isUpperCase((char) 1276));
    }
    @Test
    public void isUp274(){
        assertTrue(StringUtil.isUpperCase((char) 1278));
    }
    @Test
    public void isUp275(){
        assertTrue(StringUtil.isUpperCase((char) 1280));
    }
    @Test
    public void isUp276(){
        assertTrue(StringUtil.isUpperCase((char) 1282));
    }
    @Test
    public void isUp277(){
        assertTrue(StringUtil.isUpperCase((char) 1284));
    }
    @Test
    public void isUp278(){
        assertTrue(StringUtil.isUpperCase((char) 1286));
    }
    @Test
    public void isUp279(){
        assertTrue(StringUtil.isUpperCase((char) 1288));
    }
    @Test
    public void isUp280(){
        assertTrue(StringUtil.isUpperCase((char) 1290));
    }
    @Test
    public void isUp281(){
        assertTrue(StringUtil.isUpperCase((char) 1292));
    }
    @Test
    public void isUp282(){
        assertTrue(StringUtil.isUpperCase((char) 1294));
    }
    @Test
    public void isUp283(){
        assertTrue(StringUtil.isUpperCase((char) 1296));
    }
    @Test
    public void isUp284(){
        assertTrue(StringUtil.isUpperCase((char) 1298));
    }
    @Test
    public void isUp285(){
        assertTrue(StringUtil.isUpperCase((char) 1300));
    }
    @Test
    public void isUp286(){
        assertTrue(StringUtil.isUpperCase((char) 1302));
    }
    @Test
    public void isUp287(){
        assertTrue(StringUtil.isUpperCase((char) 1304));
    }
    @Test
    public void isUp288(){
        assertTrue(StringUtil.isUpperCase((char) 1306));
    }
    @Test
    public void isUp289(){
        assertTrue(StringUtil.isUpperCase((char) 1308));
    }
    @Test
    public void isUp290(){
        assertTrue(StringUtil.isUpperCase((char) 1310));
    }
    @Test
    public void isUp291(){
        assertTrue(StringUtil.isUpperCase((char) 1312));
    }
    @Test
    public void isUp292(){
        assertTrue(StringUtil.isUpperCase((char) 1314));
    }
    @Test
    public void isUp293(){
        assertTrue(StringUtil.isUpperCase((char) 1316));
    }
    @Test
    public void isUp294(){
        assertTrue(StringUtil.isUpperCase((char) 1318));
    }
    @Test
    public void isUp295(){
        assertTrue(StringUtil.isUpperCase((char) 1329));
    }
    @Test
    public void isUp296(){
        assertTrue(StringUtil.isUpperCase((char) 1366));
    }
    @Test
    public void isUp297(){
        assertTrue(StringUtil.isUpperCase((char) 4256));
    }
    @Test
    public void isUp298(){
        assertTrue(StringUtil.isUpperCase((char) 4293));
    }
    @Test
    public void isUp299(){
        assertTrue(StringUtil.isUpperCase((char) 4295));
    }
    @Test
    public void isUp300(){
        assertTrue(StringUtil.isUpperCase((char) 4301));
    }
    @Test
    public void isUp301(){
        assertTrue(StringUtil.isUpperCase((char) 7680));
    }
    @Test
    public void isUp302(){
        assertTrue(StringUtil.isUpperCase((char) 7682));
    }
    @Test
    public void isUp303(){
        assertTrue(StringUtil.isUpperCase((char) 7684));
    }
    @Test
    public void isUp304(){
        assertTrue(StringUtil.isUpperCase((char) 7686));
    }
    @Test
    public void isUp305(){
        assertTrue(StringUtil.isUpperCase((char) 7688));
    }
    @Test
    public void isUp306(){
        assertTrue(StringUtil.isUpperCase((char) 7690));
    }
    @Test
    public void isUp307(){
        assertTrue(StringUtil.isUpperCase((char) 7692));
    }
    @Test
    public void isUp308(){
        assertTrue(StringUtil.isUpperCase((char) 7694));
    }
    @Test
    public void isUp309(){
        assertTrue(StringUtil.isUpperCase((char) 7696));
    }
    @Test
    public void isUp310(){
        assertTrue(StringUtil.isUpperCase((char) 7698));
    }
    @Test
    public void isUp311(){
        assertTrue(StringUtil.isUpperCase((char) 7700));
    }
    @Test
    public void isUp312(){
        assertTrue(StringUtil.isUpperCase((char) 7702));
    }
    @Test
    public void isUp313(){
        assertTrue(StringUtil.isUpperCase((char) 7704));
    }
    @Test
    public void isUp314(){
        assertTrue(StringUtil.isUpperCase((char) 7706));
    }
    @Test
    public void isUp315(){
        assertTrue(StringUtil.isUpperCase((char) 7708));
    }
    @Test
    public void isUp316(){
        assertTrue(StringUtil.isUpperCase((char) 7710));
    }
    @Test
    public void isUp317(){
        assertTrue(StringUtil.isUpperCase((char) 7712));
    }
    @Test
    public void isUp318(){
        assertTrue(StringUtil.isUpperCase((char) 7714));
    }
    @Test
    public void isUp319(){
        assertTrue(StringUtil.isUpperCase((char) 7716));
    }
    @Test
    public void isUp320(){
        assertTrue(StringUtil.isUpperCase((char) 7718));
    }
    @Test
    public void isUp321(){
        assertTrue(StringUtil.isUpperCase((char) 7720));
    }
    @Test
    public void isUp322(){
        assertTrue(StringUtil.isUpperCase((char) 7722));
    }
    @Test
    public void isUp323(){
        assertTrue(StringUtil.isUpperCase((char) 7724));
    }
    @Test
    public void isUp324(){
        assertTrue(StringUtil.isUpperCase((char) 7726));
    }
    @Test
    public void isUp325(){
        assertTrue(StringUtil.isUpperCase((char) 7728));
    }
    @Test
    public void isUp326(){
        assertTrue(StringUtil.isUpperCase((char) 7730));
    }
    @Test
    public void isUp327(){
        assertTrue(StringUtil.isUpperCase((char) 7732));
    }
    @Test
    public void isUp328(){
        assertTrue(StringUtil.isUpperCase((char) 7734));
    }
    @Test
    public void isUp329(){
        assertTrue(StringUtil.isUpperCase((char) 7736));
    }
    @Test
    public void isUp330(){
        assertTrue(StringUtil.isUpperCase((char) 7738));
    }
    @Test
    public void isUp331(){
        assertTrue(StringUtil.isUpperCase((char) 7740));
    }
    @Test
    public void isUp332(){
        assertTrue(StringUtil.isUpperCase((char) 7742));
    }
    @Test
    public void isUp333(){
        assertTrue(StringUtil.isUpperCase((char) 7744));
    }
    @Test
    public void isUp334(){
        assertTrue(StringUtil.isUpperCase((char) 7746));
    }
    @Test
    public void isUp335(){
        assertTrue(StringUtil.isUpperCase((char) 7748));
    }
    @Test
    public void isUp336(){
        assertTrue(StringUtil.isUpperCase((char) 7750));
    }
    @Test
    public void isUp337(){
        assertTrue(StringUtil.isUpperCase((char) 7752));
    }
    @Test
    public void isUp338(){
        assertTrue(StringUtil.isUpperCase((char) 7754));
    }
    @Test
    public void isUp339(){
        assertTrue(StringUtil.isUpperCase((char) 7756));
    }
    @Test
    public void isUp340(){
        assertTrue(StringUtil.isUpperCase((char) 7758));
    }
    @Test
    public void isUp341(){
        assertTrue(StringUtil.isUpperCase((char) 7760));
    }
    @Test
    public void isUp342(){
        assertTrue(StringUtil.isUpperCase((char) 7762));
    }
    @Test
    public void isUp343(){
        assertTrue(StringUtil.isUpperCase((char) 7764));
    }
    @Test
    public void isUp344(){
        assertTrue(StringUtil.isUpperCase((char) 7766));
    }
    @Test
    public void isUp345(){
        assertTrue(StringUtil.isUpperCase((char) 7768));
    }
    @Test
    public void isUp346(){
        assertTrue(StringUtil.isUpperCase((char) 7770));
    }
    @Test
    public void isUp347(){
        assertTrue(StringUtil.isUpperCase((char) 7772));
    }
    @Test
    public void isUp348(){
        assertTrue(StringUtil.isUpperCase((char) 7774));
    }
    @Test
    public void isUp349(){
        assertTrue(StringUtil.isUpperCase((char) 7776));
    }
    @Test
    public void isUp350(){
        assertTrue(StringUtil.isUpperCase((char) 7778));
    }
    @Test
    public void isUp351(){
        assertTrue(StringUtil.isUpperCase((char) 7780));
    }
    @Test
    public void isUp352(){
        assertTrue(StringUtil.isUpperCase((char) 7782));
    }
    @Test
    public void isUp353(){
        assertTrue(StringUtil.isUpperCase((char) 7784));
    }
    @Test
    public void isUp354(){
        assertTrue(StringUtil.isUpperCase((char) 7786));
    }
    @Test
    public void isUp355(){
        assertTrue(StringUtil.isUpperCase((char) 7788));
    }
    @Test
    public void isUp356(){
        assertTrue(StringUtil.isUpperCase((char) 7790));
    }
    @Test
    public void isUp357(){
        assertTrue(StringUtil.isUpperCase((char) 7792));
    }
    @Test
    public void isUp358(){
        assertTrue(StringUtil.isUpperCase((char) 7794));
    }
    @Test
    public void isUp359(){
        assertTrue(StringUtil.isUpperCase((char) 7796));
    }
    @Test
    public void isUp360(){
        assertTrue(StringUtil.isUpperCase((char) 7798));
    }
    @Test
    public void isUp361(){
        assertTrue(StringUtil.isUpperCase((char) 7800));
    }
    @Test
    public void isUp362(){
        assertTrue(StringUtil.isUpperCase((char) 7802));
    }
    @Test
    public void isUp363(){
        assertTrue(StringUtil.isUpperCase((char) 7804));
    }
    @Test
    public void isUp364(){
        assertTrue(StringUtil.isUpperCase((char) 7806));
    }
    @Test
    public void isUp365(){
        assertTrue(StringUtil.isUpperCase((char) 7808));
    }
    @Test
    public void isUp366(){
        assertTrue(StringUtil.isUpperCase((char) 7810));
    }
    @Test
    public void isUp367(){
        assertTrue(StringUtil.isUpperCase((char) 7812));
    }
    @Test
    public void isUp368(){
        assertTrue(StringUtil.isUpperCase((char) 7814));
    }
    @Test
    public void isUp369(){
        assertTrue(StringUtil.isUpperCase((char) 7816));
    }
    @Test
    public void isUp370(){
        assertTrue(StringUtil.isUpperCase((char) 7818));
    }
    @Test
    public void isUp371(){
        assertTrue(StringUtil.isUpperCase((char) 7820));
    }
    @Test
    public void isUp372(){
        assertTrue(StringUtil.isUpperCase((char) 7822));
    }
    @Test
    public void isUp373(){
        assertTrue(StringUtil.isUpperCase((char) 7824));
    }
    @Test
    public void isUp374(){
        assertTrue(StringUtil.isUpperCase((char) 7826));
    }
    @Test
    public void isUp375(){
        assertTrue(StringUtil.isUpperCase((char) 7828));
    }
    @Test
    public void isUp376(){
        assertTrue(StringUtil.isUpperCase((char) 7838));
    }
    @Test
    public void isUp377(){
        assertTrue(StringUtil.isUpperCase((char) 7840));
    }
    @Test
    public void isUp378(){
        assertTrue(StringUtil.isUpperCase((char) 7842));
    }
    @Test
    public void isUp379(){
        assertTrue(StringUtil.isUpperCase((char) 7844));
    }
    @Test
    public void isUp380(){
        assertTrue(StringUtil.isUpperCase((char) 7846));
    }
    @Test
    public void isUp381(){
        assertTrue(StringUtil.isUpperCase((char) 7848));
    }
    @Test
    public void isUp382(){
        assertTrue(StringUtil.isUpperCase((char) 7850));
    }
    @Test
    public void isUp383(){
        assertTrue(StringUtil.isUpperCase((char) 7852));
    }
    @Test
    public void isUp384(){
        assertTrue(StringUtil.isUpperCase((char) 7854));
    }
    @Test
    public void isUp385(){
        assertTrue(StringUtil.isUpperCase((char) 7856));
    }
    @Test
    public void isUp386(){
        assertTrue(StringUtil.isUpperCase((char) 7858));
    }
    @Test
    public void isUp387(){
        assertTrue(StringUtil.isUpperCase((char) 7860));
    }
    @Test
    public void isUp388(){
        assertTrue(StringUtil.isUpperCase((char) 7862));
    }
    @Test
    public void isUp389(){
        assertTrue(StringUtil.isUpperCase((char) 7864));
    }
    @Test
    public void isUp390(){
        assertTrue(StringUtil.isUpperCase((char) 7866));
    }
    @Test
    public void isUp391(){
        assertTrue(StringUtil.isUpperCase((char) 7868));
    }
    @Test
    public void isUp392(){
        assertTrue(StringUtil.isUpperCase((char) 7870));
    }
    @Test
    public void isUp393(){
        assertTrue(StringUtil.isUpperCase((char) 7872));
    }
    @Test
    public void isUp394(){
        assertTrue(StringUtil.isUpperCase((char) 7874));
    }
    @Test
    public void isUp395(){
        assertTrue(StringUtil.isUpperCase((char) 7876));
    }
    @Test
    public void isUp396(){
        assertTrue(StringUtil.isUpperCase((char) 7878));
    }
    @Test
    public void isUp397(){
        assertTrue(StringUtil.isUpperCase((char) 7880));
    }
    @Test
    public void isUp398(){
        assertTrue(StringUtil.isUpperCase((char) 7882));
    }
    @Test
    public void isUp399(){
        assertTrue(StringUtil.isUpperCase((char) 7884));
    }
    @Test
    public void isUp400(){
        assertTrue(StringUtil.isUpperCase((char) 7886));
    }
    @Test
    public void isUp401(){
        assertTrue(StringUtil.isUpperCase((char) 7888));
    }
    @Test
    public void isUp402(){
        assertTrue(StringUtil.isUpperCase((char) 7890));
    }
    @Test
    public void isUp403(){
        assertTrue(StringUtil.isUpperCase((char) 7892));
    }
    @Test
    public void isUp404(){
        assertTrue(StringUtil.isUpperCase((char) 7894));
    }
    @Test
    public void isUp405(){
        assertTrue(StringUtil.isUpperCase((char) 7896));
    }
    @Test
    public void isUp406(){
        assertTrue(StringUtil.isUpperCase((char) 7898));
    }
    @Test
    public void isUp407(){
        assertTrue(StringUtil.isUpperCase((char) 7900));
    }
    @Test
    public void isUp408(){
        assertTrue(StringUtil.isUpperCase((char) 7902));
    }
    @Test
    public void isUp409(){
        assertTrue(StringUtil.isUpperCase((char) 7904));
    }
    @Test
    public void isUp410(){
        assertTrue(StringUtil.isUpperCase((char) 7906));
    }
    @Test
    public void isUp411(){
        assertTrue(StringUtil.isUpperCase((char) 7908));
    }
    @Test
    public void isUp412(){
        assertTrue(StringUtil.isUpperCase((char) 7910));
    }
    @Test
    public void isUp413(){
        assertTrue(StringUtil.isUpperCase((char) 7912));
    }
    @Test
    public void isUp414(){
        assertTrue(StringUtil.isUpperCase((char) 7914));
    }
    @Test
    public void isUp415(){
        assertTrue(StringUtil.isUpperCase((char) 7916));
    }
    @Test
    public void isUp416(){
        assertTrue(StringUtil.isUpperCase((char) 7918));
    }
    @Test
    public void isUp417(){
        assertTrue(StringUtil.isUpperCase((char) 7920));
    }
    @Test
    public void isUp418(){
        assertTrue(StringUtil.isUpperCase((char) 7922));
    }
    @Test
    public void isUp419(){
        assertTrue(StringUtil.isUpperCase((char) 7924));
    }
    @Test
    public void isUp420(){
        assertTrue(StringUtil.isUpperCase((char) 7926));
    }
    @Test
    public void isUp421(){
        assertTrue(StringUtil.isUpperCase((char) 7928));
    }
    @Test
    public void isUp422(){
        assertTrue(StringUtil.isUpperCase((char) 7930));
    }
    @Test
    public void isUp423(){
        assertTrue(StringUtil.isUpperCase((char) 7932));
    }
    @Test
    public void isUp424(){
        assertTrue(StringUtil.isUpperCase((char) 7934));
    }
    @Test
    public void isUp425(){
        assertTrue(StringUtil.isUpperCase((char) 7944));
    }
    @Test
    public void isUp426(){
        assertTrue(StringUtil.isUpperCase((char) 7951));
    }
    @Test
    public void isUp427(){
        assertTrue(StringUtil.isUpperCase((char) 7960));
    }
    @Test
    public void isUp428(){
        assertTrue(StringUtil.isUpperCase((char) 7965));
    }
    @Test
    public void isUp429(){
        assertTrue(StringUtil.isUpperCase((char) 7976));
    }
    @Test
    public void isUp430(){
        assertTrue(StringUtil.isUpperCase((char) 7983));
    }
    @Test
    public void isUp431(){
        assertTrue(StringUtil.isUpperCase((char) 7992));
    }
    @Test
    public void isUp432(){
        assertTrue(StringUtil.isUpperCase((char) 7999));
    }
    @Test
    public void isUp433(){
        assertTrue(StringUtil.isUpperCase((char) 8008));
    }
    @Test
    public void isUp434(){
        assertTrue(StringUtil.isUpperCase((char) 8013));
    }
    @Test
    public void isUp435(){
        assertTrue(StringUtil.isUpperCase((char) 8025));
    }
    @Test
    public void isUp436(){
        assertTrue(StringUtil.isUpperCase((char) 8027));
    }
    @Test
    public void isUp437(){
        assertTrue(StringUtil.isUpperCase((char) 8029));
    }
    @Test
    public void isUp438(){
        assertTrue(StringUtil.isUpperCase((char) 8031));
    }
    @Test
    public void isUp439(){
        assertTrue(StringUtil.isUpperCase((char) 8040));
    }
    @Test
    public void isUp440(){
        assertTrue(StringUtil.isUpperCase((char) 8047));
    }
    @Test
    public void isUp441(){
        assertTrue(StringUtil.isUpperCase((char) 8120));
    }
    @Test
    public void isUp442(){
        assertTrue(StringUtil.isUpperCase((char) 8123));
    }
    @Test
    public void isUp443(){
        assertTrue(StringUtil.isUpperCase((char) 8136));
    }
    @Test
    public void isUp444(){
        assertTrue(StringUtil.isUpperCase((char) 8139));
    }
    @Test
    public void isUp445(){
        assertTrue(StringUtil.isUpperCase((char) 8152));
    }
    @Test
    public void isUp446(){
        assertTrue(StringUtil.isUpperCase((char) 8155));
    }
    @Test
    public void isUp447(){
        assertTrue(StringUtil.isUpperCase((char) 8168));
    }
    @Test
    public void isUp448(){
        assertTrue(StringUtil.isUpperCase((char) 8172));
    }
    @Test
    public void isUp449(){
        assertTrue(StringUtil.isUpperCase((char) 8184));
    }
    @Test
    public void isUp450(){
        assertTrue(StringUtil.isUpperCase((char) 8187));
    }
    @Test
    public void isUp451(){
        assertTrue(StringUtil.isUpperCase((char) 8486));
    }
    @Test
    public void isUp452(){
        assertTrue(StringUtil.isUpperCase((char) 8490));
    }
    @Test
    public void isUp453(){
        assertTrue(StringUtil.isUpperCase((char) 8491));
    }
    @Test
    public void isUp454(){
        assertTrue(StringUtil.isUpperCase((char) 8498));
    }
    @Test
    public void isUp455(){
        assertTrue(StringUtil.isUpperCase((char) 8579));
    }
    @Test
    public void isUp456(){
        assertTrue(StringUtil.isUpperCase((char) 11264));
    }
    @Test
    public void isUp457(){
        assertTrue(StringUtil.isUpperCase((char) 11310));
    }
    @Test
    public void isUp458(){
        assertTrue(StringUtil.isUpperCase((char) 11360));
    }
    @Test
    public void isUp459(){
        assertTrue(StringUtil.isUpperCase((char) 11362));
    }
    @Test
    public void isUp460(){
        assertTrue(StringUtil.isUpperCase((char) 11364));
    }
    @Test
    public void isUp461(){
        assertTrue(StringUtil.isUpperCase((char) 11367));
    }
    @Test
    public void isUp462(){
        assertTrue(StringUtil.isUpperCase((char) 11369));
    }
    @Test
    public void isUp463(){
        assertTrue(StringUtil.isUpperCase((char) 11371));
    }
    @Test
    public void isUp464(){
        assertTrue(StringUtil.isUpperCase((char) 11373));
    }
    @Test
    public void isUp465(){
        assertTrue(StringUtil.isUpperCase((char) 11376));
    }
    @Test
    public void isUp466(){
        assertTrue(StringUtil.isUpperCase((char) 11378));
    }
    @Test
    public void isUp467(){
        assertTrue(StringUtil.isUpperCase((char) 11381));
    }
    @Test
    public void isUp468(){
        assertTrue(StringUtil.isUpperCase((char) 11390));
    }
    @Test
    public void isUp469(){
        assertTrue(StringUtil.isUpperCase((char) 11392));
    }
    @Test
    public void isUp470(){
        assertTrue(StringUtil.isUpperCase((char) 11394));
    }
    @Test
    public void isUp471(){
        assertTrue(StringUtil.isUpperCase((char) 11396));
    }
    @Test
    public void isUp472(){
        assertTrue(StringUtil.isUpperCase((char) 11398));
    }
    @Test
    public void isUp473(){
        assertTrue(StringUtil.isUpperCase((char) 11400));
    }
    @Test
    public void isUp474(){
        assertTrue(StringUtil.isUpperCase((char) 11402));
    }
    @Test
    public void isUp475(){
        assertTrue(StringUtil.isUpperCase((char) 11404));
    }
    @Test
    public void isUp476(){
        assertTrue(StringUtil.isUpperCase((char) 11406));
    }
    @Test
    public void isUp477(){
        assertTrue(StringUtil.isUpperCase((char) 11408));
    }
    @Test
    public void isUp478(){
        assertTrue(StringUtil.isUpperCase((char) 11410));
    }
    @Test
    public void isUp479(){
        assertTrue(StringUtil.isUpperCase((char) 11412));
    }
    @Test
    public void isUp480(){
        assertTrue(StringUtil.isUpperCase((char) 11414));
    }
    @Test
    public void isUp481(){
        assertTrue(StringUtil.isUpperCase((char) 11416));
    }
    @Test
    public void isUp482(){
        assertTrue(StringUtil.isUpperCase((char) 11418));
    }
    @Test
    public void isUp483(){
        assertTrue(StringUtil.isUpperCase((char) 11420));
    }
    @Test
    public void isUp484(){
        assertTrue(StringUtil.isUpperCase((char) 11422));
    }
    @Test
    public void isUp485(){
        assertTrue(StringUtil.isUpperCase((char) 11424));
    }
    @Test
    public void isUp486(){
        assertTrue(StringUtil.isUpperCase((char) 11426));
    }
    @Test
    public void isUp487(){
        assertTrue(StringUtil.isUpperCase((char) 11428));
    }
    @Test
    public void isUp488(){
        assertTrue(StringUtil.isUpperCase((char) 11430));
    }
    @Test
    public void isUp489(){
        assertTrue(StringUtil.isUpperCase((char) 11432));
    }
    @Test
    public void isUp490(){
        assertTrue(StringUtil.isUpperCase((char) 11434));
    }
    @Test
    public void isUp491(){
        assertTrue(StringUtil.isUpperCase((char) 11436));
    }
    @Test
    public void isUp492(){
        assertTrue(StringUtil.isUpperCase((char) 11438));
    }
    @Test
    public void isUp493(){
        assertTrue(StringUtil.isUpperCase((char) 11440));
    }
    @Test
    public void isUp494(){
        assertTrue(StringUtil.isUpperCase((char) 11442));
    }
    @Test
    public void isUp495(){
        assertTrue(StringUtil.isUpperCase((char) 11444));
    }
    @Test
    public void isUp496(){
        assertTrue(StringUtil.isUpperCase((char) 11446));
    }
    @Test
    public void isUp497(){
        assertTrue(StringUtil.isUpperCase((char) 11448));
    }
    @Test
    public void isUp498(){
        assertTrue(StringUtil.isUpperCase((char) 11450));
    }
    @Test
    public void isUp499(){
        assertTrue(StringUtil.isUpperCase((char) 11452));
    }
    @Test
    public void isUp500(){
        assertTrue(StringUtil.isUpperCase((char) 11454));
    }
    @Test
    public void isUp501(){
        assertTrue(StringUtil.isUpperCase((char) 11456));
    }
    @Test
    public void isUp502(){
        assertTrue(StringUtil.isUpperCase((char) 11458));
    }
    @Test
    public void isUp503(){
        assertTrue(StringUtil.isUpperCase((char) 11460));
    }
    @Test
    public void isUp504(){
        assertTrue(StringUtil.isUpperCase((char) 11462));
    }
    @Test
    public void isUp505(){
        assertTrue(StringUtil.isUpperCase((char) 11464));
    }
    @Test
    public void isUp506(){
        assertTrue(StringUtil.isUpperCase((char) 11466));
    }
    @Test
    public void isUp507(){
        assertTrue(StringUtil.isUpperCase((char) 11468));
    }
    @Test
    public void isUp508(){
        assertTrue(StringUtil.isUpperCase((char) 11470));
    }
    @Test
    public void isUp509(){
        assertTrue(StringUtil.isUpperCase((char) 11472));
    }
    @Test
    public void isUp510(){
        assertTrue(StringUtil.isUpperCase((char) 11474));
    }
    @Test
    public void isUp511(){
        assertTrue(StringUtil.isUpperCase((char) 11476));
    }
    @Test
    public void isUp512(){
        assertTrue(StringUtil.isUpperCase((char) 11478));
    }
    @Test
    public void isUp513(){
        assertTrue(StringUtil.isUpperCase((char) 11480));
    }
    @Test
    public void isUp514(){
        assertTrue(StringUtil.isUpperCase((char) 11482));
    }
    @Test
    public void isUp515(){
        assertTrue(StringUtil.isUpperCase((char) 11484));
    }
    @Test
    public void isUp516(){
        assertTrue(StringUtil.isUpperCase((char) 11486));
    }
    @Test
    public void isUp517(){
        assertTrue(StringUtil.isUpperCase((char) 11488));
    }
    @Test
    public void isUp518(){
        assertTrue(StringUtil.isUpperCase((char) 11490));
    }
    @Test
    public void isUp519(){
        assertTrue(StringUtil.isUpperCase((char) 11499));
    }
    @Test
    public void isUp520(){
        assertTrue(StringUtil.isUpperCase((char) 11501));
    }
    @Test
    public void isUp521(){
        assertTrue(StringUtil.isUpperCase((char) 11506));
    }
    @Test
    public void isUp522(){
        assertTrue(StringUtil.isUpperCase((char) 42560));
    }
    @Test
    public void isUp523(){
        assertTrue(StringUtil.isUpperCase((char) 42562));
    }
    @Test
    public void isUp524(){
        assertTrue(StringUtil.isUpperCase((char) 42564));
    }
    @Test
    public void isUp525(){
        assertTrue(StringUtil.isUpperCase((char) 42566));
    }
    @Test
    public void isUp526(){
        assertTrue(StringUtil.isUpperCase((char) 42568));
    }
    @Test
    public void isUp527(){
        assertTrue(StringUtil.isUpperCase((char) 42570));
    }
    @Test
    public void isUp528(){
        assertTrue(StringUtil.isUpperCase((char) 42572));
    }
    @Test
    public void isUp529(){
        assertTrue(StringUtil.isUpperCase((char) 42574));
    }
    @Test
    public void isUp530(){
        assertTrue(StringUtil.isUpperCase((char) 42576));
    }
    @Test
    public void isUp531(){
        assertTrue(StringUtil.isUpperCase((char) 42578));
    }
    @Test
    public void isUp532(){
        assertTrue(StringUtil.isUpperCase((char) 42580));
    }
    @Test
    public void isUp533(){
        assertTrue(StringUtil.isUpperCase((char) 42582));
    }
    @Test
    public void isUp534(){
        assertTrue(StringUtil.isUpperCase((char) 42584));
    }
    @Test
    public void isUp535(){
        assertTrue(StringUtil.isUpperCase((char) 42586));
    }
    @Test
    public void isUp536(){
        assertTrue(StringUtil.isUpperCase((char) 42588));
    }
    @Test
    public void isUp537(){
        assertTrue(StringUtil.isUpperCase((char) 42590));
    }
    @Test
    public void isUp538(){
        assertTrue(StringUtil.isUpperCase((char) 42592));
    }
    @Test
    public void isUp539(){
        assertTrue(StringUtil.isUpperCase((char) 42594));
    }
    @Test
    public void isUp540(){
        assertTrue(StringUtil.isUpperCase((char) 42596));
    }
    @Test
    public void isUp541(){
        assertTrue(StringUtil.isUpperCase((char) 42598));
    }
    @Test
    public void isUp542(){
        assertTrue(StringUtil.isUpperCase((char) 42600));
    }
    @Test
    public void isUp543(){
        assertTrue(StringUtil.isUpperCase((char) 42602));
    }
    @Test
    public void isUp544(){
        assertTrue(StringUtil.isUpperCase((char) 42604));
    }
    @Test
    public void isUp545(){
        assertTrue(StringUtil.isUpperCase((char) 42624));
    }
    @Test
    public void isUp546(){
        assertTrue(StringUtil.isUpperCase((char) 42626));
    }
    @Test
    public void isUp547(){
        assertTrue(StringUtil.isUpperCase((char) 42628));
    }
    @Test
    public void isUp548(){
        assertTrue(StringUtil.isUpperCase((char) 42630));
    }
    @Test
    public void isUp549(){
        assertTrue(StringUtil.isUpperCase((char) 42632));
    }
    @Test
    public void isUp550(){
        assertTrue(StringUtil.isUpperCase((char) 42634));
    }
    @Test
    public void isUp551(){
        assertTrue(StringUtil.isUpperCase((char) 42636));
    }
    @Test
    public void isUp552(){
        assertTrue(StringUtil.isUpperCase((char) 42638));
    }
    @Test
    public void isUp553(){
        assertTrue(StringUtil.isUpperCase((char) 42640));
    }
    @Test
    public void isUp554(){
        assertTrue(StringUtil.isUpperCase((char) 42642));
    }
    @Test
    public void isUp555(){
        assertTrue(StringUtil.isUpperCase((char) 42644));
    }
    @Test
    public void isUp556(){
        assertTrue(StringUtil.isUpperCase((char) 42646));
    }
    @Test
    public void isUp557(){
        assertTrue(StringUtil.isUpperCase((char) 42786));
    }
    @Test
    public void isUp558(){
        assertTrue(StringUtil.isUpperCase((char) 42788));
    }
    @Test
    public void isUp559(){
        assertTrue(StringUtil.isUpperCase((char) 42790));
    }
    @Test
    public void isUp560(){
        assertTrue(StringUtil.isUpperCase((char) 42792));
    }
    @Test
    public void isUp561(){
        assertTrue(StringUtil.isUpperCase((char) 42794));
    }
    @Test
    public void isUp562(){
        assertTrue(StringUtil.isUpperCase((char) 42796));
    }
    @Test
    public void isUp563(){
        assertTrue(StringUtil.isUpperCase((char) 42798));
    }
    @Test
    public void isUp564(){
        assertTrue(StringUtil.isUpperCase((char) 42802));
    }
    @Test
    public void isUp565(){
        assertTrue(StringUtil.isUpperCase((char) 42804));
    }
    @Test
    public void isUp566(){
        assertTrue(StringUtil.isUpperCase((char) 42806));
    }
    @Test
    public void isUp567(){
        assertTrue(StringUtil.isUpperCase((char) 42808));
    }
    @Test
    public void isUp568(){
        assertTrue(StringUtil.isUpperCase((char) 42810));
    }
    @Test
    public void isUp569(){
        assertTrue(StringUtil.isUpperCase((char) 42812));
    }
    @Test
    public void isUp570(){
        assertTrue(StringUtil.isUpperCase((char) 42814));
    }
    @Test
    public void isUp571(){
        assertTrue(StringUtil.isUpperCase((char) 42816));
    }
    @Test
    public void isUp572(){
        assertTrue(StringUtil.isUpperCase((char) 42818));
    }
    @Test
    public void isUp573(){
        assertTrue(StringUtil.isUpperCase((char) 42820));
    }
    @Test
    public void isUp574(){
        assertTrue(StringUtil.isUpperCase((char) 42822));
    }
    @Test
    public void isUp575(){
        assertTrue(StringUtil.isUpperCase((char) 42824));
    }
    @Test
    public void isUp576(){
        assertTrue(StringUtil.isUpperCase((char) 42826));
    }
    @Test
    public void isUp577(){
        assertTrue(StringUtil.isUpperCase((char) 42828));
    }
    @Test
    public void isUp578(){
        assertTrue(StringUtil.isUpperCase((char) 42830));
    }
    @Test
    public void isUp579(){
        assertTrue(StringUtil.isUpperCase((char) 42832));
    }
    @Test
    public void isUp580(){
        assertTrue(StringUtil.isUpperCase((char) 42834));
    }
    @Test
    public void isUp581(){
        assertTrue(StringUtil.isUpperCase((char) 42836));
    }
    @Test
    public void isUp582(){
        assertTrue(StringUtil.isUpperCase((char) 42838));
    }
    @Test
    public void isUp583(){
        assertTrue(StringUtil.isUpperCase((char) 42840));
    }
    @Test
    public void isUp584(){
        assertTrue(StringUtil.isUpperCase((char) 42842));
    }
    @Test
    public void isUp585(){
        assertTrue(StringUtil.isUpperCase((char) 42844));
    }
    @Test
    public void isUp586(){
        assertTrue(StringUtil.isUpperCase((char) 42846));
    }
    @Test
    public void isUp587(){
        assertTrue(StringUtil.isUpperCase((char) 42848));
    }
    @Test
    public void isUp588(){
        assertTrue(StringUtil.isUpperCase((char) 42850));
    }
    @Test
    public void isUp589(){
        assertTrue(StringUtil.isUpperCase((char) 42852));
    }
    @Test
    public void isUp590(){
        assertTrue(StringUtil.isUpperCase((char) 42854));
    }
    @Test
    public void isUp591(){
        assertTrue(StringUtil.isUpperCase((char) 42856));
    }
    @Test
    public void isUp592(){
        assertTrue(StringUtil.isUpperCase((char) 42858));
    }
    @Test
    public void isUp593(){
        assertTrue(StringUtil.isUpperCase((char) 42860));
    }
    @Test
    public void isUp594(){
        assertTrue(StringUtil.isUpperCase((char) 42862));
    }
    @Test
    public void isUp595(){
        assertTrue(StringUtil.isUpperCase((char) 42873));
    }
    @Test
    public void isUp596(){
        assertTrue(StringUtil.isUpperCase((char) 42875));
    }
    @Test
    public void isUp597(){
        assertTrue(StringUtil.isUpperCase((char) 42877));
    }
    @Test
    public void isUp598(){
        assertTrue(StringUtil.isUpperCase((char) 42878));
    }
    @Test
    public void isUp599(){
        assertTrue(StringUtil.isUpperCase((char) 42880));
    }
    @Test
    public void isUp600(){
        assertTrue(StringUtil.isUpperCase((char) 42882));
    }
    @Test
    public void isUp601(){
        assertTrue(StringUtil.isUpperCase((char) 42884));
    }
    @Test
    public void isUp602(){
        assertTrue(StringUtil.isUpperCase((char) 42886));
    }
    @Test
    public void isUp603(){
        assertTrue(StringUtil.isUpperCase((char) 42891));
    }
    @Test
    public void isUp604(){
        assertTrue(StringUtil.isUpperCase((char) 42893));
    }
    @Test
    public void isUp605(){
        assertTrue(StringUtil.isUpperCase((char) 42896));
    }
    @Test
    public void isUp606(){
        assertTrue(StringUtil.isUpperCase((char) 42898));
    }
    @Test
    public void isUp607(){
        assertTrue(StringUtil.isUpperCase((char) 42912));
    }
    @Test
    public void isUp608(){
        assertTrue(StringUtil.isUpperCase((char) 42914));
    }
    @Test
    public void isUp609(){
        assertTrue(StringUtil.isUpperCase((char) 42916));
    }
    @Test
    public void isUp610(){
        assertTrue(StringUtil.isUpperCase((char) 42918));
    }
    @Test
    public void isUp611(){
        assertTrue(StringUtil.isUpperCase((char) 42920));
    }
    @Test
    public void isUp612(){
        assertTrue(StringUtil.isUpperCase((char) 42922));
    }
    @Test
    public void isUp613(){
        assertTrue(StringUtil.isUpperCase((char) 65313));
    }
    @Test
    public void isUp614(){
        assertTrue(StringUtil.isUpperCase((char) 65338));
    }
    @Test
    public void isUp615(){
        assertTrue(!StringUtil.isUpperCase((char) 888));
    }
    @Test
    public void isUp616(){
        assertTrue(!StringUtil.isUpperCase((char) 889));
    }
    @Test
    public void isUp617(){
        assertTrue(!StringUtil.isUpperCase((char) 895));
    }
    @Test
    public void isUp618(){
        assertTrue(!StringUtil.isUpperCase((char) 899));
    }
    @Test
    public void isUp619(){
        assertTrue(!StringUtil.isUpperCase((char) 907));
    }
    @Test
    public void isUp620(){
        assertTrue(!StringUtil.isUpperCase((char) 909));
    }
    @Test
    public void isUp621(){
        assertTrue(!StringUtil.isUpperCase((char) 930));
    }
    @Test
    public void isUp622(){
        assertTrue(!StringUtil.isUpperCase((char) 1320));
    }
    @Test
    public void isUp623(){
        assertTrue(!StringUtil.isUpperCase((char) 1328));
    }
    @Test
    public void isUp624(){
        assertTrue(!StringUtil.isUpperCase((char) 1367));
    }
    @Test
    public void isUp625(){
        assertTrue(!StringUtil.isUpperCase((char) 1368));
    }
    @Test
    public void isUp626(){
        assertTrue(!StringUtil.isUpperCase((char) 1376));
    }
    @Test
    public void isUp627(){
        assertTrue(!StringUtil.isUpperCase((char) 1416));
    }
    @Test
    public void isUp628(){
        assertTrue(!StringUtil.isUpperCase((char) 1419));
    }
    @Test
    public void isUp629(){
        assertTrue(!StringUtil.isUpperCase((char) 1422));
    }
    @Test
    public void isUp630(){
        assertTrue(!StringUtil.isUpperCase((char) 1424));
    }
    @Test
    public void isUp631(){
        assertTrue(!StringUtil.isUpperCase((char) 1480));
    }
    @Test
    public void isUp632(){
        assertTrue(!StringUtil.isUpperCase((char) 1487));
    }
    @Test
    public void isUp633(){
        assertTrue(!StringUtil.isUpperCase((char) 1515));
    }
    @Test
    public void isUp634(){
        assertTrue(!StringUtil.isUpperCase((char) 1519));
    }
    @Test
    public void isUp635(){
        assertTrue(!StringUtil.isUpperCase((char) 1525));
    }
    @Test
    public void isUp636(){
        assertTrue(!StringUtil.isUpperCase((char) 1535));
    }
    @Test
    public void isUp637(){
        assertTrue(!StringUtil.isUpperCase((char) 1541));
    }
    @Test
    public void isUp638(){
        assertTrue(!StringUtil.isUpperCase((char) 1564));
    }
    @Test
    public void isUp639(){
        assertTrue(!StringUtil.isUpperCase((char) 1565));
    }
    @Test
    public void isUp640(){
        assertTrue(!StringUtil.isUpperCase((char) 1806));
    }
    @Test
    public void isUp641(){
        assertTrue(!StringUtil.isUpperCase((char) 1867));
    }
    @Test
    public void isUp642(){
        assertTrue(!StringUtil.isUpperCase((char) 1868));
    }
    @Test
    public void isUp643(){
        assertTrue(!StringUtil.isUpperCase((char) 1970));
    }
    @Test
    public void isUp644(){
        assertTrue(!StringUtil.isUpperCase((char) 1983));
    }
    @Test
    public void isUp645(){
        assertTrue(!StringUtil.isUpperCase((char) 2043));
    }
    @Test
    public void isUp646(){
        assertTrue(!StringUtil.isUpperCase((char) 2047));
    }
    @Test
    public void isUp647(){
        assertTrue(!StringUtil.isUpperCase((char) 2094));
    }
    @Test
    public void isUp648(){
        assertTrue(!StringUtil.isUpperCase((char) 2095));
    }
    @Test
    public void isUp649(){
        assertTrue(!StringUtil.isUpperCase((char) 2111));
    }
    @Test
    public void isUp650(){
        assertTrue(!StringUtil.isUpperCase((char) 2140));
    }
    @Test
    public void isUp651(){
        assertTrue(!StringUtil.isUpperCase((char) 2141));
    }
    @Test
    public void isUp652(){
        assertTrue(!StringUtil.isUpperCase((char) 2143));
    }
    @Test
    public void isUp653(){
        assertTrue(!StringUtil.isUpperCase((char) 2207));
    }
    @Test
    public void isUp654(){
        assertTrue(!StringUtil.isUpperCase((char) 2209));
    }
    @Test
    public void isUp655(){
        assertTrue(!StringUtil.isUpperCase((char) 2221));
    }
    @Test
    public void isUp656(){
        assertTrue(!StringUtil.isUpperCase((char) 2275));
    }
    @Test
    public void isUp657(){
        assertTrue(!StringUtil.isUpperCase((char) 2303));
    }
    @Test
    public void isUp658(){
        assertTrue(!StringUtil.isUpperCase((char) 2424));
    }
    @Test
    public void isUp659(){
        assertTrue(!StringUtil.isUpperCase((char) 2432));
    }
    @Test
    public void isUp660(){
        assertTrue(!StringUtil.isUpperCase((char) 2436));
    }
    @Test
    public void isUp661(){
        assertTrue(!StringUtil.isUpperCase((char) 2445));
    }
    @Test
    public void isUp662(){
        assertTrue(!StringUtil.isUpperCase((char) 2446));
    }
    @Test
    public void isUp663(){
        assertTrue(!StringUtil.isUpperCase((char) 2449));
    }
    @Test
    public void isUp664(){
        assertTrue(!StringUtil.isUpperCase((char) 2450));
    }
    @Test
    public void isUp665(){
        assertTrue(!StringUtil.isUpperCase((char) 2473));
    }
    @Test
    public void isUp666(){
        assertTrue(!StringUtil.isUpperCase((char) 2481));
    }
    @Test
    public void isUp667(){
        assertTrue(!StringUtil.isUpperCase((char) 2483));
    }
    @Test
    public void isUp668(){
        assertTrue(!StringUtil.isUpperCase((char) 2485));
    }
    @Test
    public void isUp669(){
        assertTrue(!StringUtil.isUpperCase((char) 2490));
    }
    @Test
    public void isUp670(){
        assertTrue(!StringUtil.isUpperCase((char) 2491));
    }
    @Test
    public void isUp671(){
        assertTrue(!StringUtil.isUpperCase((char) 2501));
    }
    @Test
    public void isUp672(){
        assertTrue(!StringUtil.isUpperCase((char) 2502));
    }
    @Test
    public void isUp673(){
        assertTrue(!StringUtil.isUpperCase((char) 2505));
    }
    @Test
    public void isUp674(){
        assertTrue(!StringUtil.isUpperCase((char) 2506));
    }
    @Test
    public void isUp675(){
        assertTrue(!StringUtil.isUpperCase((char) 2511));
    }
    @Test
    public void isUp676(){
        assertTrue(!StringUtil.isUpperCase((char) 2518));
    }
    @Test
    public void isUp677(){
        assertTrue(!StringUtil.isUpperCase((char) 2520));
    }
    @Test
    public void isUp678(){
        assertTrue(!StringUtil.isUpperCase((char) 2523));
    }
    @Test
    public void isUp679(){
        assertTrue(!StringUtil.isUpperCase((char) 2526));
    }
    @Test
    public void isUp680(){
        assertTrue(!StringUtil.isUpperCase((char) 2532));
    }
    @Test
    public void isUp681(){
        assertTrue(!StringUtil.isUpperCase((char) 2533));
    }
    @Test
    public void isUp682(){
        assertTrue(!StringUtil.isUpperCase((char) 2556));
    }
    @Test
    public void isUp683(){
        assertTrue(!StringUtil.isUpperCase((char) 2560));
    }
    @Test
    public void isUp684(){
        assertTrue(!StringUtil.isUpperCase((char) 2564));
    }
    @Test
    public void isUp685(){
        assertTrue(!StringUtil.isUpperCase((char) 2571));
    }
    @Test
    public void isUp686(){
        assertTrue(!StringUtil.isUpperCase((char) 2574));
    }
    @Test
    public void isUp687(){
        assertTrue(!StringUtil.isUpperCase((char) 2577));
    }
    @Test
    public void isUp688(){
        assertTrue(!StringUtil.isUpperCase((char) 2578));
    }
    @Test
    public void isUp689(){
        assertTrue(!StringUtil.isUpperCase((char) 2601));
    }
    @Test
    public void isUp690(){
        assertTrue(!StringUtil.isUpperCase((char) 2609));
    }
    @Test
    public void isUp691(){
        assertTrue(!StringUtil.isUpperCase((char) 2612));
    }
    @Test
    public void isUp692(){
        assertTrue(!StringUtil.isUpperCase((char) 2615));
    }
    @Test
    public void isUp693(){
        assertTrue(!StringUtil.isUpperCase((char) 2618));
    }
    @Test
    public void isUp694(){
        assertTrue(!StringUtil.isUpperCase((char) 2619));
    }
    @Test
    public void isUp695(){
        assertTrue(!StringUtil.isUpperCase((char) 2621));
    }
    @Test
    public void isUp696(){
        assertTrue(!StringUtil.isUpperCase((char) 2627));
    }
    @Test
    public void isUp697(){
        assertTrue(!StringUtil.isUpperCase((char) 2630));
    }
    @Test
    public void isUp698(){
        assertTrue(!StringUtil.isUpperCase((char) 2633));
    }
    @Test
    public void isUp699(){
        assertTrue(!StringUtil.isUpperCase((char) 2634));
    }
    @Test
    public void isUp700(){
        assertTrue(!StringUtil.isUpperCase((char) 2638));
    }
    @Test
    public void isUp701(){
        assertTrue(!StringUtil.isUpperCase((char) 2640));
    }
    @Test
    public void isUp702(){
        assertTrue(!StringUtil.isUpperCase((char) 2642));
    }
    @Test
    public void isUp703(){
        assertTrue(!StringUtil.isUpperCase((char) 2648));
    }
    @Test
    public void isUp704(){
        assertTrue(!StringUtil.isUpperCase((char) 2653));
    }
    @Test
    public void isUp705(){
        assertTrue(!StringUtil.isUpperCase((char) 2655));
    }
    @Test
    public void isUp706(){
        assertTrue(!StringUtil.isUpperCase((char) 2661));
    }
    @Test
    public void isUp707(){
        assertTrue(!StringUtil.isUpperCase((char) 2678));
    }
    @Test
    public void isUp708(){
        assertTrue(!StringUtil.isUpperCase((char) 2688));
    }
    @Test
    public void isUp709(){
        assertTrue(!StringUtil.isUpperCase((char) 2692));
    }
    @Test
    public void isUp710(){
        assertTrue(!StringUtil.isUpperCase((char) 2702));
    }
    @Test
    public void isUp711(){
        assertTrue(!StringUtil.isUpperCase((char) 2706));
    }
    @Test
    public void isUp712(){
        assertTrue(!StringUtil.isUpperCase((char) 2729));
    }
    @Test
    public void isUp713(){
        assertTrue(!StringUtil.isUpperCase((char) 2737));
    }
    @Test
    public void isUp714(){
        assertTrue(!StringUtil.isUpperCase((char) 2740));
    }
    @Test
    public void isUp715(){
        assertTrue(!StringUtil.isUpperCase((char) 2746));
    }
    @Test
    public void isUp716(){
        assertTrue(!StringUtil.isUpperCase((char) 2747));
    }
    @Test
    public void isUp717(){
        assertTrue(!StringUtil.isUpperCase((char) 2758));
    }
    @Test
    public void isUp718(){
        assertTrue(!StringUtil.isUpperCase((char) 2762));
    }
    @Test
    public void isUp719(){
        assertTrue(!StringUtil.isUpperCase((char) 2766));
    }
    @Test
    public void isUp720(){
        assertTrue(!StringUtil.isUpperCase((char) 2767));
    }
    @Test
    public void isUp721(){
        assertTrue(!StringUtil.isUpperCase((char) 2769));
    }
    @Test
    public void isUp722(){
        assertTrue(!StringUtil.isUpperCase((char) 2783));
    }
    @Test
    public void isUp723(){
        assertTrue(!StringUtil.isUpperCase((char) 2788));
    }
    @Test
    public void isUp724(){
        assertTrue(!StringUtil.isUpperCase((char) 2789));
    }
    @Test
    public void isUp725(){
        assertTrue(!StringUtil.isUpperCase((char) 2802));
    }
    @Test
    public void isUp726(){
        assertTrue(!StringUtil.isUpperCase((char) 2816));
    }
    @Test
    public void isUp727(){
        assertTrue(!StringUtil.isUpperCase((char) 2820));
    }
    @Test
    public void isUp728(){
        assertTrue(!StringUtil.isUpperCase((char) 2829));
    }
    @Test
    public void isUp729(){
        assertTrue(!StringUtil.isUpperCase((char) 2830));
    }
    @Test
    public void isUp730(){
        assertTrue(!StringUtil.isUpperCase((char) 2833));
    }
    @Test
    public void isUp731(){
        assertTrue(!StringUtil.isUpperCase((char) 2834));
    }
    @Test
    public void isUp732(){
        assertTrue(!StringUtil.isUpperCase((char) 2857));
    }
    @Test
    public void isUp733(){
        assertTrue(!StringUtil.isUpperCase((char) 2865));
    }
    @Test
    public void isUp734(){
        assertTrue(!StringUtil.isUpperCase((char) 2868));
    }
    @Test
    public void isUp735(){
        assertTrue(!StringUtil.isUpperCase((char) 2874));
    }
    @Test
    public void isUp736(){
        assertTrue(!StringUtil.isUpperCase((char) 2875));
    }
    @Test
    public void isUp737(){
        assertTrue(!StringUtil.isUpperCase((char) 2885));
    }
    @Test
    public void isUp738(){
        assertTrue(!StringUtil.isUpperCase((char) 2886));
    }
    @Test
    public void isUp739(){
        assertTrue(!StringUtil.isUpperCase((char) 2889));
    }
    @Test
    public void isUp740(){
        assertTrue(!StringUtil.isUpperCase((char) 2890));
    }
    @Test
    public void isUp741(){
        assertTrue(!StringUtil.isUpperCase((char) 2894));
    }
    @Test
    public void isUp742(){
        assertTrue(!StringUtil.isUpperCase((char) 2901));
    }
    @Test
    public void isUp743(){
        assertTrue(!StringUtil.isUpperCase((char) 2904));
    }
    @Test
    public void isUp744(){
        assertTrue(!StringUtil.isUpperCase((char) 2907));
    }
    @Test
    public void isUp745(){
        assertTrue(!StringUtil.isUpperCase((char) 2910));
    }
    @Test
    public void isUp746(){
        assertTrue(!StringUtil.isUpperCase((char) 2916));
    }
    @Test
    public void isUp747(){
        assertTrue(!StringUtil.isUpperCase((char) 2917));
    }
    @Test
    public void isUp748(){
        assertTrue(!StringUtil.isUpperCase((char) 2936));
    }
    @Test
    public void isUp749(){
        assertTrue(!StringUtil.isUpperCase((char) 2945));
    }
    @Test
    public void isUp750(){
        assertTrue(!StringUtil.isUpperCase((char) 2948));
    }
    @Test
    public void isUp751(){
        assertTrue(!StringUtil.isUpperCase((char) 2955));
    }
    @Test
    public void isUp752(){
        assertTrue(!StringUtil.isUpperCase((char) 2957));
    }
    @Test
    public void isUp753(){
        assertTrue(!StringUtil.isUpperCase((char) 2961));
    }
    @Test
    public void isUp754(){
        assertTrue(!StringUtil.isUpperCase((char) 2966));
    }
    @Test
    public void isUp755(){
        assertTrue(!StringUtil.isUpperCase((char) 2968));
    }
    @Test
    public void isUp756(){
        assertTrue(!StringUtil.isUpperCase((char) 2971));
    }
    @Test
    public void isUp757(){
        assertTrue(!StringUtil.isUpperCase((char) 2973));
    }
    @Test
    public void isUp758(){
        assertTrue(!StringUtil.isUpperCase((char) 2976));
    }
    @Test
    public void isUp759(){
        assertTrue(!StringUtil.isUpperCase((char) 2978));
    }
    @Test
    public void isUp760(){
        assertTrue(!StringUtil.isUpperCase((char) 2981));
    }
    @Test
    public void isUp761(){
        assertTrue(!StringUtil.isUpperCase((char) 2983));
    }
    @Test
    public void isUp762(){
        assertTrue(!StringUtil.isUpperCase((char) 2987));
    }
    @Test
    public void isUp763(){
        assertTrue(!StringUtil.isUpperCase((char) 2989));
    }
    @Test
    public void isUp764(){
        assertTrue(!StringUtil.isUpperCase((char) 3002));
    }
    @Test
    public void isUp765(){
        assertTrue(!StringUtil.isUpperCase((char) 3005));
    }
    @Test
    public void isUp766(){
        assertTrue(!StringUtil.isUpperCase((char) 3011));
    }
    @Test
    public void isUp767(){
        assertTrue(!StringUtil.isUpperCase((char) 3013));
    }
    @Test
    public void isUp768(){
        assertTrue(!StringUtil.isUpperCase((char) 3017));
    }
    @Test
    public void isUp769(){
        assertTrue(!StringUtil.isUpperCase((char) 3022));
    }
    @Test
    public void isUp770(){
        assertTrue(!StringUtil.isUpperCase((char) 3023));
    }
    @Test
    public void isUp771(){
        assertTrue(!StringUtil.isUpperCase((char) 3025));
    }
    @Test
    public void isUp772(){
        assertTrue(!StringUtil.isUpperCase((char) 3030));
    }
    @Test
    public void isUp773(){
        assertTrue(!StringUtil.isUpperCase((char) 3032));
    }
    @Test
    public void isUp774(){
        assertTrue(!StringUtil.isUpperCase((char) 3045));
    }
    @Test
    public void isUp775(){
        assertTrue(!StringUtil.isUpperCase((char) 3067));
    }
    @Test
    public void isUp776(){
        assertTrue(!StringUtil.isUpperCase((char) 3072));
    }
    @Test
    public void isUp777(){
        assertTrue(!StringUtil.isUpperCase((char) 3076));
    }
    @Test
    public void isUp778(){
        assertTrue(!StringUtil.isUpperCase((char) 3085));
    }
    @Test
    public void isUp779(){
        assertTrue(!StringUtil.isUpperCase((char) 3089));
    }
    @Test
    public void isUp780(){
        assertTrue(!StringUtil.isUpperCase((char) 3113));
    }
    @Test
    public void isUp781(){
        assertTrue(!StringUtil.isUpperCase((char) 3124));
    }
    @Test
    public void isUp782(){
        assertTrue(!StringUtil.isUpperCase((char) 3130));
    }
    @Test
    public void isUp783(){
        assertTrue(!StringUtil.isUpperCase((char) 3132));
    }
    @Test
    public void isUp784(){
        assertTrue(!StringUtil.isUpperCase((char) 3141));
    }
    @Test
    public void isUp785(){
        assertTrue(!StringUtil.isUpperCase((char) 3145));
    }
    @Test
    public void isUp786(){
        assertTrue(!StringUtil.isUpperCase((char) 3150));
    }
    @Test
    public void isUp787(){
        assertTrue(!StringUtil.isUpperCase((char) 3156));
    }
    @Test
    public void isUp788(){
        assertTrue(!StringUtil.isUpperCase((char) 3159));
    }
    @Test
    public void isUp789(){
        assertTrue(!StringUtil.isUpperCase((char) 3162));
    }
    @Test
    public void isUp790(){
        assertTrue(!StringUtil.isUpperCase((char) 3167));
    }
    @Test
    public void isUp791(){
        assertTrue(!StringUtil.isUpperCase((char) 3172));
    }
    @Test
    public void isUp792(){
        assertTrue(!StringUtil.isUpperCase((char) 3173));
    }
    @Test
    public void isUp793(){
        assertTrue(!StringUtil.isUpperCase((char) 3184));
    }
    @Test
    public void isUp794(){
        assertTrue(!StringUtil.isUpperCase((char) 3191));
    }
    @Test
    public void isUp795(){
        assertTrue(!StringUtil.isUpperCase((char) 3200));
    }
    @Test
    public void isUp796(){
        assertTrue(!StringUtil.isUpperCase((char) 3201));
    }
    @Test
    public void isUp797(){
        assertTrue(!StringUtil.isUpperCase((char) 3204));
    }
    @Test
    public void isUp798(){
        assertTrue(!StringUtil.isUpperCase((char) 3213));
    }
    @Test
    public void isUp799(){
        assertTrue(!StringUtil.isUpperCase((char) 3217));
    }
    @Test
    public void isUp800(){
        assertTrue(!StringUtil.isUpperCase((char) 3241));
    }
    @Test
    public void isUp801(){
        assertTrue(!StringUtil.isUpperCase((char) 3252));
    }
    @Test
    public void isUp802(){
        assertTrue(!StringUtil.isUpperCase((char) 3258));
    }
    @Test
    public void isUp803(){
        assertTrue(!StringUtil.isUpperCase((char) 3259));
    }
    @Test
    public void isUp804(){
        assertTrue(!StringUtil.isUpperCase((char) 3269));
    }
    @Test
    public void isUp805(){
        assertTrue(!StringUtil.isUpperCase((char) 3273));
    }
    @Test
    public void isUp806(){
        assertTrue(!StringUtil.isUpperCase((char) 3278));
    }
    @Test
    public void isUp807(){
        assertTrue(!StringUtil.isUpperCase((char) 3284));
    }
    @Test
    public void isUp808(){
        assertTrue(!StringUtil.isUpperCase((char) 3287));
    }
    @Test
    public void isUp809(){
        assertTrue(!StringUtil.isUpperCase((char) 3293));
    }
    @Test
    public void isUp810(){
        assertTrue(!StringUtil.isUpperCase((char) 3295));
    }
    @Test
    public void isUp811(){
        assertTrue(!StringUtil.isUpperCase((char) 3300));
    }
    @Test
    public void isUp812(){
        assertTrue(!StringUtil.isUpperCase((char) 3301));
    }
    @Test
    public void isUp813(){
        assertTrue(!StringUtil.isUpperCase((char) 3312));
    }
    @Test
    public void isUp814(){
        assertTrue(!StringUtil.isUpperCase((char) 3315));
    }
    @Test
    public void isUp815(){
        assertTrue(!StringUtil.isUpperCase((char) 3329));
    }
    @Test
    public void isUp816(){
        assertTrue(!StringUtil.isUpperCase((char) 3332));
    }
    @Test
    public void isUp817(){
        assertTrue(!StringUtil.isUpperCase((char) 3341));
    }
    @Test
    public void isUp818(){
        assertTrue(!StringUtil.isUpperCase((char) 3345));
    }
    @Test
    public void isUp819(){
        assertTrue(!StringUtil.isUpperCase((char) 3387));
    }
    @Test
    public void isUp820(){
        assertTrue(!StringUtil.isUpperCase((char) 3388));
    }
    @Test
    public void isUp821(){
        assertTrue(!StringUtil.isUpperCase((char) 3397));
    }
    @Test
    public void isUp822(){
        assertTrue(!StringUtil.isUpperCase((char) 3401));
    }
    @Test
    public void isUp823(){
        assertTrue(!StringUtil.isUpperCase((char) 3407));
    }
    @Test
    public void isUp824(){
        assertTrue(!StringUtil.isUpperCase((char) 3414));
    }
    @Test
    public void isUp825(){
        assertTrue(!StringUtil.isUpperCase((char) 3416));
    }
    @Test
    public void isUp826(){
        assertTrue(!StringUtil.isUpperCase((char) 3423));
    }
    @Test
    public void isUp827(){
        assertTrue(!StringUtil.isUpperCase((char) 3428));
    }
    @Test
    public void isUp828(){
        assertTrue(!StringUtil.isUpperCase((char) 3429));
    }
    @Test
    public void isUp829(){
        assertTrue(!StringUtil.isUpperCase((char) 3446));
    }
    @Test
    public void isUp830(){
        assertTrue(!StringUtil.isUpperCase((char) 3448));
    }
    @Test
    public void isUp831(){
        assertTrue(!StringUtil.isUpperCase((char) 3456));
    }
    @Test
    public void isUp832(){
        assertTrue(!StringUtil.isUpperCase((char) 3457));
    }
    @Test
    public void isUp833(){
        assertTrue(!StringUtil.isUpperCase((char) 3460));
    }
    @Test
    public void isUp834(){
        assertTrue(!StringUtil.isUpperCase((char) 3479));
    }
    @Test
    public void isUp835(){
        assertTrue(!StringUtil.isUpperCase((char) 3481));
    }
    @Test
    public void isUp836(){
        assertTrue(!StringUtil.isUpperCase((char) 3506));
    }
    @Test
    public void isUp837(){
        assertTrue(!StringUtil.isUpperCase((char) 3516));
    }
    @Test
    public void isUp838(){
        assertTrue(!StringUtil.isUpperCase((char) 3518));
    }
    @Test
    public void isUp839(){
        assertTrue(!StringUtil.isUpperCase((char) 3519));
    }
    @Test
    public void isUp840(){
        assertTrue(!StringUtil.isUpperCase((char) 3527));
    }
    @Test
    public void isUp841(){
        assertTrue(!StringUtil.isUpperCase((char) 3529));
    }
    @Test
    public void isUp842(){
        assertTrue(!StringUtil.isUpperCase((char) 3531));
    }
    @Test
    public void isUp843(){
        assertTrue(!StringUtil.isUpperCase((char) 3534));
    }
    @Test
    public void isUp844(){
        assertTrue(!StringUtil.isUpperCase((char) 3541));
    }
    @Test
    public void isUp845(){
        assertTrue(!StringUtil.isUpperCase((char) 3543));
    }
    @Test
    public void isUp846(){
        assertTrue(!StringUtil.isUpperCase((char) 3552));
    }
    @Test
    public void isUp847(){
        assertTrue(!StringUtil.isUpperCase((char) 3569));
    }
    @Test
    public void isUp848(){
        assertTrue(!StringUtil.isUpperCase((char) 3573));
    }
    @Test
    public void isUp849(){
        assertTrue(!StringUtil.isUpperCase((char) 3584));
    }
    @Test
    public void isUp850(){
        assertTrue(!StringUtil.isUpperCase((char) 3643));
    }
    @Test
    public void isUp851(){
        assertTrue(!StringUtil.isUpperCase((char) 3646));
    }
    @Test
    public void isUp852(){
        assertTrue(!StringUtil.isUpperCase((char) 3676));
    }
    @Test
    public void isUp853(){
        assertTrue(!StringUtil.isUpperCase((char) 3712));
    }
    @Test
    public void isUp854(){
        assertTrue(!StringUtil.isUpperCase((char) 3715));
    }
    @Test
    public void isUp855(){
        assertTrue(!StringUtil.isUpperCase((char) 3717));
    }
    @Test
    public void isUp856(){
        assertTrue(!StringUtil.isUpperCase((char) 3718));
    }
    @Test
    public void isUp857(){
        assertTrue(!StringUtil.isUpperCase((char) 3721));
    }
    @Test
    public void isUp858(){
        assertTrue(!StringUtil.isUpperCase((char) 3723));
    }
    @Test
    public void isUp859(){
        assertTrue(!StringUtil.isUpperCase((char) 3724));
    }
    @Test
    public void isUp860(){
        assertTrue(!StringUtil.isUpperCase((char) 3726));
    }
    @Test
    public void isUp861(){
        assertTrue(!StringUtil.isUpperCase((char) 3731));
    }
    @Test
    public void isUp862(){
        assertTrue(!StringUtil.isUpperCase((char) 3736));
    }
    @Test
    public void isUp863(){
        assertTrue(!StringUtil.isUpperCase((char) 3744));
    }
    @Test
    public void isUp864(){
        assertTrue(!StringUtil.isUpperCase((char) 3748));
    }
    @Test
    public void isUp865(){
        assertTrue(!StringUtil.isUpperCase((char) 3750));
    }
    @Test
    public void isUp866(){
        assertTrue(!StringUtil.isUpperCase((char) 3752));
    }
    @Test
    public void isUp867(){
        assertTrue(!StringUtil.isUpperCase((char) 3753));
    }
    @Test
    public void isUp868(){
        assertTrue(!StringUtil.isUpperCase((char) 3756));
    }
    @Test
    public void isUp869(){
        assertTrue(!StringUtil.isUpperCase((char) 3770));
    }
    @Test
    public void isUp870(){
        assertTrue(!StringUtil.isUpperCase((char) 3774));
    }
    @Test
    public void isUp871(){
        assertTrue(!StringUtil.isUpperCase((char) 3775));
    }
    @Test
    public void isUp872(){
        assertTrue(!StringUtil.isUpperCase((char) 3781));
    }
    @Test
    public void isUp873(){
        assertTrue(!StringUtil.isUpperCase((char) 3783));
    }
    @Test
    public void isUp874(){
        assertTrue(!StringUtil.isUpperCase((char) 3790));
    }
    @Test
    public void isUp875(){
        assertTrue(!StringUtil.isUpperCase((char) 3791));
    }
    @Test
    public void isUp876(){
        assertTrue(!StringUtil.isUpperCase((char) 3802));
    }
    @Test
    public void isUp877(){
        assertTrue(!StringUtil.isUpperCase((char) 3803));
    }
    @Test
    public void isUp878(){
        assertTrue(!StringUtil.isUpperCase((char) 3808));
    }
    @Test
    public void isUp879(){
        assertTrue(!StringUtil.isUpperCase((char) 3839));
    }
    @Test
    public void isUp880(){
        assertTrue(!StringUtil.isUpperCase((char) 3912));
    }
    @Test
    public void isUp881(){
        assertTrue(!StringUtil.isUpperCase((char) 3949));
    }
    @Test
    public void isUp882(){
        assertTrue(!StringUtil.isUpperCase((char) 3952));
    }
    @Test
    public void isUp883(){
        assertTrue(!StringUtil.isUpperCase((char) 3992));
    }
    @Test
    public void isUp884(){
        assertTrue(!StringUtil.isUpperCase((char) 4029));
    }
    @Test
    public void isUp885(){
        assertTrue(!StringUtil.isUpperCase((char) 4045));
    }
    @Test
    public void isUp886(){
        assertTrue(!StringUtil.isUpperCase((char) 4059));
    }
    @Test
    public void isUp887(){
        assertTrue(!StringUtil.isUpperCase((char) 4095));
    }
    @Test
    public void isUp888(){
        assertTrue(!StringUtil.isUpperCase((char) 4294));
    }
    @Test
    public void isUp889(){
        assertTrue(!StringUtil.isUpperCase((char) 4296));
    }
    @Test
    public void isUp890(){
        assertTrue(!StringUtil.isUpperCase((char) 4300));
    }
    @Test
    public void isUp891(){
        assertTrue(!StringUtil.isUpperCase((char) 4302));
    }
    @Test
    public void isUp892(){
        assertTrue(!StringUtil.isUpperCase((char) 4303));
    }
    @Test
    public void isUp893(){
        assertTrue(!StringUtil.isUpperCase((char) 4681));
    }
    @Test
    public void isUp894(){
        assertTrue(!StringUtil.isUpperCase((char) 4686));
    }
    @Test
    public void isUp895(){
        assertTrue(!StringUtil.isUpperCase((char) 4687));
    }
    @Test
    public void isUp896(){
        assertTrue(!StringUtil.isUpperCase((char) 4695));
    }
    @Test
    public void isUp897(){
        assertTrue(!StringUtil.isUpperCase((char) 4697));
    }
    @Test
    public void isUp898(){
        assertTrue(!StringUtil.isUpperCase((char) 4702));
    }
    @Test
    public void isUp899(){
        assertTrue(!StringUtil.isUpperCase((char) 4703));
    }
    @Test
    public void isUp900(){
        assertTrue(!StringUtil.isUpperCase((char) 4745));
    }
    @Test
    public void isUp901(){
        assertTrue(!StringUtil.isUpperCase((char) 4750));
    }
    @Test
    public void isUp902(){
        assertTrue(!StringUtil.isUpperCase((char) 4751));
    }
    @Test
    public void isUp903(){
        assertTrue(!StringUtil.isUpperCase((char) 4785));
    }
    @Test
    public void isUp904(){
        assertTrue(!StringUtil.isUpperCase((char) 4790));
    }
    @Test
    public void isUp905(){
        assertTrue(!StringUtil.isUpperCase((char) 4791));
    }
    @Test
    public void isUp906(){
        assertTrue(!StringUtil.isUpperCase((char) 4799));
    }
    @Test
    public void isUp907(){
        assertTrue(!StringUtil.isUpperCase((char) 4801));
    }
    @Test
    public void isUp908(){
        assertTrue(!StringUtil.isUpperCase((char) 4806));
    }
    @Test
    public void isUp909(){
        assertTrue(!StringUtil.isUpperCase((char) 4807));
    }
    @Test
    public void isUp910(){
        assertTrue(!StringUtil.isUpperCase((char) 4823));
    }
    @Test
    public void isUp911(){
        assertTrue(!StringUtil.isUpperCase((char) 4881));
    }
    @Test
    public void isUp912(){
        assertTrue(!StringUtil.isUpperCase((char) 4886));
    }
    @Test
    public void isUp913(){
        assertTrue(!StringUtil.isUpperCase((char) 4887));
    }
    @Test
    public void isUp914(){
        assertTrue(!StringUtil.isUpperCase((char) 4955));
    }
    @Test
    public void isUp915(){
        assertTrue(!StringUtil.isUpperCase((char) 4956));
    }
    @Test
    public void isUp916(){
        assertTrue(!StringUtil.isUpperCase((char) 4989));
    }
    @Test
    public void isUp917(){
        assertTrue(!StringUtil.isUpperCase((char) 4991));
    }
    @Test
    public void isUp918(){
        assertTrue(!StringUtil.isUpperCase((char) 5018));
    }
    @Test
    public void isUp919(){
        assertTrue(!StringUtil.isUpperCase((char) 5023));
    }
    @Test
    public void isUp920(){
        assertTrue(!StringUtil.isUpperCase((char) 5109));
    }
    @Test
    public void isUp921(){
        assertTrue(!StringUtil.isUpperCase((char) 5119));
    }
    @Test
    public void isUp922(){
        assertTrue(!StringUtil.isUpperCase((char) 5789));
    }
    @Test
    public void isUp923(){
        assertTrue(!StringUtil.isUpperCase((char) 5791));
    }
    @Test
    public void isUp924(){
        assertTrue(!StringUtil.isUpperCase((char) 5873));
    }
    @Test
    public void isUp925(){
        assertTrue(!StringUtil.isUpperCase((char) 5887));
    }
    @Test
    public void isUp926(){
        assertTrue(!StringUtil.isUpperCase((char) 5901));
    }
    @Test
    public void isUp927(){
        assertTrue(!StringUtil.isUpperCase((char) 5909));
    }
    @Test
    public void isUp928(){
        assertTrue(!StringUtil.isUpperCase((char) 5919));
    }
    @Test
    public void isUp929(){
        assertTrue(!StringUtil.isUpperCase((char) 5943));
    }
    @Test
    public void isUp930(){
        assertTrue(!StringUtil.isUpperCase((char) 5951));
    }
    @Test
    public void isUp931(){
        assertTrue(!StringUtil.isUpperCase((char) 5972));
    }
    @Test
    public void isUp932(){
        assertTrue(!StringUtil.isUpperCase((char) 5983));
    }
    @Test
    public void isUp933(){
        assertTrue(!StringUtil.isUpperCase((char) 5997));
    }
    @Test
    public void isUp934(){
        assertTrue(!StringUtil.isUpperCase((char) 6001));
    }
    @Test
    public void isUp935(){
        assertTrue(!StringUtil.isUpperCase((char) 6004));
    }
    @Test
    public void isUp936(){
        assertTrue(!StringUtil.isUpperCase((char) 6015));
    }
    @Test
    public void isUp937(){
        assertTrue(!StringUtil.isUpperCase((char) 6110));
    }
    @Test
    public void isUp938(){
        assertTrue(!StringUtil.isUpperCase((char) 6111));
    }
    @Test
    public void isUp939(){
        assertTrue(!StringUtil.isUpperCase((char) 6122));
    }
    @Test
    public void isUp940(){
        assertTrue(!StringUtil.isUpperCase((char) 6127));
    }
    @Test
    public void isUp941(){
        assertTrue(!StringUtil.isUpperCase((char) 6138));
    }
    @Test
    public void isUp942(){
        assertTrue(!StringUtil.isUpperCase((char) 6143));
    }
    @Test
    public void isUp943(){
        assertTrue(!StringUtil.isUpperCase((char) 6159));
    }
    @Test
    public void isUp944(){
        assertTrue(!StringUtil.isUpperCase((char) 6170));
    }
    @Test
    public void isUp945(){
        assertTrue(!StringUtil.isUpperCase((char) 6175));
    }
    @Test
    public void isUp946(){
        assertTrue(!StringUtil.isUpperCase((char) 6264));
    }
    @Test
    public void isUp947(){
        assertTrue(!StringUtil.isUpperCase((char) 6271));
    }
    @Test
    public void isUp948(){
        assertTrue(!StringUtil.isUpperCase((char) 6315));
    }
    @Test
    public void isUp949(){
        assertTrue(!StringUtil.isUpperCase((char) 6319));
    }
    @Test
    public void isUp950(){
        assertTrue(!StringUtil.isUpperCase((char) 6390));
    }
    @Test
    public void isUp951(){
        assertTrue(!StringUtil.isUpperCase((char) 6399));
    }
    @Test
    public void isUp952(){
        assertTrue(!StringUtil.isUpperCase((char) 6429));
    }
    @Test
    public void isUp953(){
        assertTrue(!StringUtil.isUpperCase((char) 6431));
    }
    @Test
    public void isUp954(){
        assertTrue(!StringUtil.isUpperCase((char) 6444));
    }
    @Test
    public void isUp955(){
        assertTrue(!StringUtil.isUpperCase((char) 6447));
    }
    @Test
    public void isUp956(){
        assertTrue(!StringUtil.isUpperCase((char) 6460));
    }
    @Test
    public void isUp957(){
        assertTrue(!StringUtil.isUpperCase((char) 6463));
    }
    @Test
    public void isUp958(){
        assertTrue(!StringUtil.isUpperCase((char) 6465));
    }
    @Test
    public void isUp959(){
        assertTrue(!StringUtil.isUpperCase((char) 6467));
    }
    @Test
    public void isUp960(){
        assertTrue(!StringUtil.isUpperCase((char) 6510));
    }
    @Test
    public void isUp961(){
        assertTrue(!StringUtil.isUpperCase((char) 6511));
    }
    @Test
    public void isUp962(){
        assertTrue(!StringUtil.isUpperCase((char) 6517));
    }
    @Test
    public void isUp963(){
        assertTrue(!StringUtil.isUpperCase((char) 6527));
    }
    @Test
    public void isUp964(){
        assertTrue(!StringUtil.isUpperCase((char) 6572));
    }
    @Test
    public void isUp965(){
        assertTrue(!StringUtil.isUpperCase((char) 6575));
    }
    @Test
    public void isUp966(){
        assertTrue(!StringUtil.isUpperCase((char) 6602));
    }
    @Test
    public void isUp967(){
        assertTrue(!StringUtil.isUpperCase((char) 6607));
    }
    @Test
    public void isUp968(){
        assertTrue(!StringUtil.isUpperCase((char) 6619));
    }
    @Test
    public void isUp969(){
        assertTrue(!StringUtil.isUpperCase((char) 6621));
    }
    @Test
    public void isUp970(){
        assertTrue(!StringUtil.isUpperCase((char) 6684));
    }
    @Test
    public void isUp971(){
        assertTrue(!StringUtil.isUpperCase((char) 6685));
    }
    @Test
    public void isUp972(){
        assertTrue(!StringUtil.isUpperCase((char) 6751));
    }
    @Test
    public void isUp973(){
        assertTrue(!StringUtil.isUpperCase((char) 6781));
    }
    @Test
    public void isUp974(){
        assertTrue(!StringUtil.isUpperCase((char) 6782));
    }
    @Test
    public void isUp975(){
        assertTrue(!StringUtil.isUpperCase((char) 6794));
    }
    @Test
    public void isUp976(){
        assertTrue(!StringUtil.isUpperCase((char) 6799));
    }
    @Test
    public void isUp977(){
        assertTrue(!StringUtil.isUpperCase((char) 6810));
    }
    @Test
    public void isUp978(){
        assertTrue(!StringUtil.isUpperCase((char) 6815));
    }
    @Test
    public void isUp979(){
        assertTrue(!StringUtil.isUpperCase((char) 6830));
    }
    @Test
    public void isUp980(){
        assertTrue(!StringUtil.isUpperCase((char) 6911));
    }
    @Test
    public void isUp981(){
        assertTrue(!StringUtil.isUpperCase((char) 6988));
    }
    @Test
    public void isUp982(){
        assertTrue(!StringUtil.isUpperCase((char) 6991));
    }
    @Test
    public void isUp983(){
        assertTrue(!StringUtil.isUpperCase((char) 7037));
    }
    @Test
    public void isUp984(){
        assertTrue(!StringUtil.isUpperCase((char) 7039));
    }
    @Test
    public void isUp985(){
        assertTrue(!StringUtil.isUpperCase((char) 7156));
    }
    @Test
    public void isUp986(){
        assertTrue(!StringUtil.isUpperCase((char) 7163));
    }
    @Test
    public void isUp987(){
        assertTrue(!StringUtil.isUpperCase((char) 7224));
    }
    @Test
    public void isUp988(){
        assertTrue(!StringUtil.isUpperCase((char) 7226));
    }
    @Test
    public void isUp989(){
        assertTrue(!StringUtil.isUpperCase((char) 7242));
    }
    @Test
    public void isUp990(){
        assertTrue(!StringUtil.isUpperCase((char) 7244));
    }
    @Test
    public void isUp991(){
        assertTrue(!StringUtil.isUpperCase((char) 7296));
    }
    @Test
    public void isUp992(){
        assertTrue(!StringUtil.isUpperCase((char) 7359));
    }
    @Test
    public void isUp993(){
        assertTrue(!StringUtil.isUpperCase((char) 7368));
    }
    @Test
    public void isUp994(){
        assertTrue(!StringUtil.isUpperCase((char) 7375));
    }
    @Test
    public void isUp995(){
        assertTrue(!StringUtil.isUpperCase((char) 7415));
    }
    @Test
    public void isUp996(){
        assertTrue(!StringUtil.isUpperCase((char) 7423));
    }
    @Test
    public void isUp997(){
        assertTrue(!StringUtil.isUpperCase((char) 7655));
    }
    @Test
    public void isUp998(){
        assertTrue(!StringUtil.isUpperCase((char) 7675));
    }
    @Test
    public void isUp999(){
        assertTrue(!StringUtil.isUpperCase((char) 7958));
    }
    @Test
    public void isUp1000(){
        assertTrue(!StringUtil.isUpperCase((char) 7959));
    }
    @Test
    public void isUp1001(){
        assertTrue(!StringUtil.isUpperCase((char) 7966));
    }
    @Test
    public void isUp1002(){
        assertTrue(!StringUtil.isUpperCase((char) 7967));
    }
    @Test
    public void isUp1003(){
        assertTrue(!StringUtil.isUpperCase((char) 8006));
    }
    @Test
    public void isUp1004(){
        assertTrue(!StringUtil.isUpperCase((char) 8007));
    }
    @Test
    public void isUp1005(){
        assertTrue(!StringUtil.isUpperCase((char) 8014));
    }
    @Test
    public void isUp1006(){
        assertTrue(!StringUtil.isUpperCase((char) 8015));
    }
    @Test
    public void isUp1007(){
        assertTrue(!StringUtil.isUpperCase((char) 8024));
    }
    @Test
    public void isUp1008(){
        assertTrue(!StringUtil.isUpperCase((char) 8026));
    }
    @Test
    public void isUp1009(){
        assertTrue(!StringUtil.isUpperCase((char) 8028));
    }
    @Test
    public void isUp1010(){
        assertTrue(!StringUtil.isUpperCase((char) 8030));
    }
    @Test
    public void isUp1011(){
        assertTrue(!StringUtil.isUpperCase((char) 8062));
    }
    @Test
    public void isUp1012(){
        assertTrue(!StringUtil.isUpperCase((char) 8063));
    }
    @Test
    public void isUp1013(){
        assertTrue(!StringUtil.isUpperCase((char) 8117));
    }
    @Test
    public void isUp1014(){
        assertTrue(!StringUtil.isUpperCase((char) 8133));
    }
    @Test
    public void isUp1015(){
        assertTrue(!StringUtil.isUpperCase((char) 8148));
    }
    @Test
    public void isUp1016(){
        assertTrue(!StringUtil.isUpperCase((char) 8149));
    }
    @Test
    public void isUp1017(){
        assertTrue(!StringUtil.isUpperCase((char) 8156));
    }
    @Test
    public void isUp1018(){
        assertTrue(!StringUtil.isUpperCase((char) 8176));
    }
    @Test
    public void isUp1019(){
        assertTrue(!StringUtil.isUpperCase((char) 8177));
    }
    @Test
    public void isUp1020(){
        assertTrue(!StringUtil.isUpperCase((char) 8181));
    }
    @Test
    public void isUp1021(){
        assertTrue(!StringUtil.isUpperCase((char) 8191));
    }
    @Test
    public void isUp1022(){
        assertTrue(!StringUtil.isUpperCase((char) 8293));
    }
    @Test
    public void isUp1023(){
        assertTrue(!StringUtil.isUpperCase((char) 8297));
    }
    @Test
    public void isUp1024(){
        assertTrue(!StringUtil.isUpperCase((char) 8306));
    }
    @Test
    public void isUp1025(){
        assertTrue(!StringUtil.isUpperCase((char) 8307));
    }
    @Test
    public void isUp1026(){
        assertTrue(!StringUtil.isUpperCase((char) 8335));
    }
    @Test
    public void isUp1027(){
        assertTrue(!StringUtil.isUpperCase((char) 8349));
    }
    @Test
    public void isUp1028(){
        assertTrue(!StringUtil.isUpperCase((char) 8351));
    }
    @Test
    public void isUp1029(){
        assertTrue(!StringUtil.isUpperCase((char) 8379));
    }
    @Test
    public void isUp1030(){
        assertTrue(!StringUtil.isUpperCase((char) 8399));
    }
    @Test
    public void isUp1031(){
        assertTrue(!StringUtil.isUpperCase((char) 8433));
    }
    @Test
    public void isUp1032(){
        assertTrue(!StringUtil.isUpperCase((char) 8447));
    }
    @Test
    public void isUp1033(){
        assertTrue(!StringUtil.isUpperCase((char) 8586));
    }
    @Test
    public void isUp1034(){
        assertTrue(!StringUtil.isUpperCase((char) 8591));
    }
    @Test
    public void isUp1035(){
        assertTrue(!StringUtil.isUpperCase((char) 9204));
    }
    @Test
    public void isUp1036(){
        assertTrue(!StringUtil.isUpperCase((char) 9215));
    }
    @Test
    public void isUp1037(){
        assertTrue(!StringUtil.isUpperCase((char) 9255));
    }
    @Test
    public void isUp1038(){
        assertTrue(!StringUtil.isUpperCase((char) 9279));
    }
    @Test
    public void isUp1039(){
        assertTrue(!StringUtil.isUpperCase((char) 9291));
    }
    @Test
    public void isUp1040(){
        assertTrue(!StringUtil.isUpperCase((char) 9311));
    }
    @Test
    public void isUp1041(){
        assertTrue(!StringUtil.isUpperCase((char) 9984));
    }
    @Test
    public void isUp1042(){
        assertTrue(!StringUtil.isUpperCase((char) 11085));
    }
    @Test
    public void isUp1043(){
        assertTrue(!StringUtil.isUpperCase((char) 11087));
    }
    @Test
    public void isUp1044(){
        assertTrue(!StringUtil.isUpperCase((char) 11098));
    }
    @Test
    public void isUp1045(){
        assertTrue(!StringUtil.isUpperCase((char) 11263));
    }
    @Test
    public void isUp1046(){
        assertTrue(!StringUtil.isUpperCase((char) 11311));
    }
    @Test
    public void isUp1047(){
        assertTrue(!StringUtil.isUpperCase((char) 11359));
    }
    @Test
    public void isUp1048(){
        assertTrue(!StringUtil.isUpperCase((char) 11508));
    }
    @Test
    public void isUp1049(){
        assertTrue(!StringUtil.isUpperCase((char) 11512));
    }
    @Test
    public void isUp1050(){
        assertTrue(!StringUtil.isUpperCase((char) 11558));
    }
    @Test
    public void isUp1051(){
        assertTrue(!StringUtil.isUpperCase((char) 11560));
    }
    @Test
    public void isUp1052(){
        assertTrue(!StringUtil.isUpperCase((char) 11564));
    }
    @Test
    public void isUp1053(){
        assertTrue(!StringUtil.isUpperCase((char) 11566));
    }
    @Test
    public void isUp1054(){
        assertTrue(!StringUtil.isUpperCase((char) 11567));
    }
    @Test
    public void isUp1055(){
        assertTrue(!StringUtil.isUpperCase((char) 11624));
    }
    @Test
    public void isUp1056(){
        assertTrue(!StringUtil.isUpperCase((char) 11630));
    }
    @Test
    public void isUp1057(){
        assertTrue(!StringUtil.isUpperCase((char) 11633));
    }
    @Test
    public void isUp1058(){
        assertTrue(!StringUtil.isUpperCase((char) 11646));
    }
    @Test
    public void isUp1059(){
        assertTrue(!StringUtil.isUpperCase((char) 11671));
    }
    @Test
    public void isUp1060(){
        assertTrue(!StringUtil.isUpperCase((char) 11679));
    }
    @Test
    public void isUp1061(){
        assertTrue(!StringUtil.isUpperCase((char) 11687));
    }
    @Test
    public void isUp1062(){
        assertTrue(!StringUtil.isUpperCase((char) 11695));
    }
    @Test
    public void isUp1063(){
        assertTrue(!StringUtil.isUpperCase((char) 11703));
    }
    @Test
    public void isUp1064(){
        assertTrue(!StringUtil.isUpperCase((char) 11711));
    }
    @Test
    public void isUp1065(){
        assertTrue(!StringUtil.isUpperCase((char) 11719));
    }
    @Test
    public void isUp1066(){
        assertTrue(!StringUtil.isUpperCase((char) 11727));
    }
    @Test
    public void isUp1067(){
        assertTrue(!StringUtil.isUpperCase((char) 11735));
    }
    @Test
    public void isUp1068(){
        assertTrue(!StringUtil.isUpperCase((char) 11743));
    }
    @Test
    public void isUp1069(){
        assertTrue(!StringUtil.isUpperCase((char) 11836));
    }
    @Test
    public void isUp1070(){
        assertTrue(!StringUtil.isUpperCase((char) 11903));
    }
    @Test
    public void isUp1071(){
        assertTrue(!StringUtil.isUpperCase((char) 11930));
    }
    @Test
    public void isUp1072(){
        assertTrue(!StringUtil.isUpperCase((char) 12020));
    }
    @Test
    public void isUp1073(){
        assertTrue(!StringUtil.isUpperCase((char) 12031));
    }
    @Test
    public void isUp1074(){
        assertTrue(!StringUtil.isUpperCase((char) 12246));
    }
    @Test
    public void isUp1075(){
        assertTrue(!StringUtil.isUpperCase((char) 12271));
    }
    @Test
    public void isUp1076(){
        assertTrue(!StringUtil.isUpperCase((char) 12284));
    }
    @Test
    public void isUp1077(){
        assertTrue(!StringUtil.isUpperCase((char) 12287));
    }
    @Test
    public void isUp1078(){
        assertTrue(!StringUtil.isUpperCase((char) 12352));
    }
    @Test
    public void isUp1079(){
        assertTrue(!StringUtil.isUpperCase((char) 12439));
    }
    @Test
    public void isUp1080(){
        assertTrue(!StringUtil.isUpperCase((char) 12440));
    }
    @Test
    public void isUp1081(){
        assertTrue(!StringUtil.isUpperCase((char) 12544));
    }
    @Test
    public void isUp1082(){
        assertTrue(!StringUtil.isUpperCase((char) 12548));
    }
    @Test
    public void isUp1083(){
        assertTrue(!StringUtil.isUpperCase((char) 12590));
    }
    @Test
    public void isUp1084(){
        assertTrue(!StringUtil.isUpperCase((char) 12592));
    }
    @Test
    public void isUp1085(){
        assertTrue(!StringUtil.isUpperCase((char) 12687));
    }
    @Test
    public void isUp1086(){
        assertTrue(!StringUtil.isUpperCase((char) 12731));
    }
    @Test
    public void isUp1087(){
        assertTrue(!StringUtil.isUpperCase((char) 12735));
    }
    @Test
    public void isUp1088(){
        assertTrue(!StringUtil.isUpperCase((char) 12772));
    }
    @Test
    public void isUp1089(){
        assertTrue(!StringUtil.isUpperCase((char) 12783));
    }
    @Test
    public void isUp1090(){
        assertTrue(!StringUtil.isUpperCase((char) 12831));
    }
    @Test
    public void isUp1091(){
        assertTrue(!StringUtil.isUpperCase((char) 13055));
    }
    @Test
    public void isUp1092(){
        assertTrue(!StringUtil.isUpperCase((char) 19894));
    }
    @Test
    public void isUp1093(){
        assertTrue(!StringUtil.isUpperCase((char) 19903));
    }
    @Test
    public void isUp1094(){
        assertTrue(!StringUtil.isUpperCase((char) 40909));
    }
    @Test
    public void isUp1095(){
        assertTrue(!StringUtil.isUpperCase((char) 40959));
    }
    @Test
    public void isUp1096(){
        assertTrue(!StringUtil.isUpperCase((char) 42125));
    }
    @Test
    public void isUp1097(){
        assertTrue(!StringUtil.isUpperCase((char) 42127));
    }
    @Test
    public void isUp1098(){
        assertTrue(!StringUtil.isUpperCase((char) 42183));
    }
    @Test
    public void isUp1099(){
        assertTrue(!StringUtil.isUpperCase((char) 42191));
    }
    @Test
    public void isUp1100(){
        assertTrue(!StringUtil.isUpperCase((char) 42540));
    }
    @Test
    public void isUp1101(){
        assertTrue(!StringUtil.isUpperCase((char) 42559));
    }
    @Test
    public void isUp1102(){
        assertTrue(!StringUtil.isUpperCase((char) 42648));
    }
    @Test
    public void isUp1103(){
        assertTrue(!StringUtil.isUpperCase((char) 42654));
    }
    @Test
    public void isUp1104(){
        assertTrue(!StringUtil.isUpperCase((char) 42744));
    }
    @Test
    public void isUp1105(){
        assertTrue(!StringUtil.isUpperCase((char) 42751));
    }
    @Test
    public void isUp1106(){
        assertTrue(!StringUtil.isUpperCase((char) 42895));
    }
    @Test
    public void isUp1107(){
        assertTrue(!StringUtil.isUpperCase((char) 42900));
    }
    @Test
    public void isUp1108(){
        assertTrue(!StringUtil.isUpperCase((char) 42911));
    }
    @Test
    public void isUp1109(){
        assertTrue(!StringUtil.isUpperCase((char) 42923));
    }
    @Test
    public void isUp1110(){
        assertTrue(!StringUtil.isUpperCase((char) 42999));
    }
    @Test
    public void isUp1111(){
        assertTrue(!StringUtil.isUpperCase((char) 43052));
    }
    @Test
    public void isUp1112(){
        assertTrue(!StringUtil.isUpperCase((char) 43055));
    }
    @Test
    public void isUp1113(){
        assertTrue(!StringUtil.isUpperCase((char) 43066));
    }
    @Test
    public void isUp1114(){
        assertTrue(!StringUtil.isUpperCase((char) 43071));
    }
    @Test
    public void isUp1115(){
        assertTrue(!StringUtil.isUpperCase((char) 43128));
    }
    @Test
    public void isUp1116(){
        assertTrue(!StringUtil.isUpperCase((char) 43135));
    }
    @Test
    public void isUp1117(){
        assertTrue(!StringUtil.isUpperCase((char) 43205));
    }
    @Test
    public void isUp1118(){
        assertTrue(!StringUtil.isUpperCase((char) 43213));
    }
    @Test
    public void isUp1119(){
        assertTrue(!StringUtil.isUpperCase((char) 43226));
    }
    @Test
    public void isUp1120(){
        assertTrue(!StringUtil.isUpperCase((char) 43231));
    }
    @Test
    public void isUp1121(){
        assertTrue(!StringUtil.isUpperCase((char) 43260));
    }
    @Test
    public void isUp1122(){
        assertTrue(!StringUtil.isUpperCase((char) 43263));
    }
    @Test
    public void isUp1123(){
        assertTrue(!StringUtil.isUpperCase((char) 43348));
    }
    @Test
    public void isUp1124(){
        assertTrue(!StringUtil.isUpperCase((char) 43358));
    }
    @Test
    public void isUp1125(){
        assertTrue(!StringUtil.isUpperCase((char) 43389));
    }
    @Test
    public void isUp1126(){
        assertTrue(!StringUtil.isUpperCase((char) 43391));
    }
    @Test
    public void isUp1127(){
        assertTrue(!StringUtil.isUpperCase((char) 43470));
    }
    @Test
    public void isUp1128(){
        assertTrue(!StringUtil.isUpperCase((char) 43482));
    }
    @Test
    public void isUp1129(){
        assertTrue(!StringUtil.isUpperCase((char) 43485));
    }
    @Test
    public void isUp1130(){
        assertTrue(!StringUtil.isUpperCase((char) 43488));
    }
    @Test
    public void isUp1131(){
        assertTrue(!StringUtil.isUpperCase((char) 43519));
    }
    @Test
    public void isUp1132(){
        assertTrue(!StringUtil.isUpperCase((char) 43575));
    }
    @Test
    public void isUp1133(){
        assertTrue(!StringUtil.isUpperCase((char) 43583));
    }
    @Test
    public void isUp1134(){
        assertTrue(!StringUtil.isUpperCase((char) 43598));
    }
    @Test
    public void isUp1135(){
        assertTrue(!StringUtil.isUpperCase((char) 43599));
    }
    @Test
    public void isUp1136(){
        assertTrue(!StringUtil.isUpperCase((char) 43610));
    }
    @Test
    public void isUp1137(){
        assertTrue(!StringUtil.isUpperCase((char) 43611));
    }
    @Test
    public void isUp1138(){
        assertTrue(!StringUtil.isUpperCase((char) 43644));
    }
    @Test
    public void isUp1139(){
        assertTrue(!StringUtil.isUpperCase((char) 43647));
    }
    @Test
    public void isUp1140(){
        assertTrue(!StringUtil.isUpperCase((char) 43715));
    }
    @Test
    public void isUp1141(){
        assertTrue(!StringUtil.isUpperCase((char) 43738));
    }
    @Test
    public void isUp1142(){
        assertTrue(!StringUtil.isUpperCase((char) 43767));
    }
    @Test
    public void isUp1143(){
        assertTrue(!StringUtil.isUpperCase((char) 43776));
    }
    @Test
    public void isUp1144(){
        assertTrue(!StringUtil.isUpperCase((char) 43783));
    }
    @Test
    public void isUp1145(){
        assertTrue(!StringUtil.isUpperCase((char) 43784));
    }
    @Test
    public void isUp1146(){
        assertTrue(!StringUtil.isUpperCase((char) 43791));
    }
    @Test
    public void isUp1147(){
        assertTrue(!StringUtil.isUpperCase((char) 43792));
    }
    @Test
    public void isUp1148(){
        assertTrue(!StringUtil.isUpperCase((char) 43799));
    }
    @Test
    public void isUp1149(){
        assertTrue(!StringUtil.isUpperCase((char) 43807));
    }
    @Test
    public void isUp1150(){
        assertTrue(!StringUtil.isUpperCase((char) 43815));
    }
    @Test
    public void isUp1151(){
        assertTrue(!StringUtil.isUpperCase((char) 43823));
    }
    @Test
    public void isUp1152(){
        assertTrue(!StringUtil.isUpperCase((char) 43967));
    }
    @Test
    public void isUp1153(){
        assertTrue(!StringUtil.isUpperCase((char) 44014));
    }
    @Test
    public void isUp1154(){
        assertTrue(!StringUtil.isUpperCase((char) 44015));
    }
    @Test
    public void isUp1155(){
        assertTrue(!StringUtil.isUpperCase((char) 44026));
    }
    @Test
    public void isUp1156(){
        assertTrue(!StringUtil.isUpperCase((char) 44031));
    }
    @Test
    public void isUp1157(){
        assertTrue(!StringUtil.isUpperCase((char) 55204));
    }
    @Test
    public void isUp1158(){
        assertTrue(!StringUtil.isUpperCase((char) 55215));
    }
    @Test
    public void isUp1159(){
        assertTrue(!StringUtil.isUpperCase((char) 55239));
    }
    @Test
    public void isUp1160(){
        assertTrue(!StringUtil.isUpperCase((char) 55242));
    }
    @Test
    public void isUp1161(){
        assertTrue(!StringUtil.isUpperCase((char) 55292));
    }
    @Test
    public void isUp1162(){
        assertTrue(!StringUtil.isUpperCase((char) 55295));
    }
    @Test
    public void isUp1163(){
        assertTrue(!StringUtil.isUpperCase((char) 64110));
    }
    @Test
    public void isUp1164(){
        assertTrue(!StringUtil.isUpperCase((char) 64111));
    }
    @Test
    public void isUp1165(){
        assertTrue(!StringUtil.isUpperCase((char) 64218));
    }
    @Test
    public void isUp1166(){
        assertTrue(!StringUtil.isUpperCase((char) 64255));
    }
    @Test
    public void isUp1167(){
        assertTrue(!StringUtil.isUpperCase((char) 64263));
    }
    @Test
    public void isUp1168(){
        assertTrue(!StringUtil.isUpperCase((char) 64274));
    }
    @Test
    public void isUp1169(){
        assertTrue(!StringUtil.isUpperCase((char) 64280));
    }
    @Test
    public void isUp1170(){
        assertTrue(!StringUtil.isUpperCase((char) 64284));
    }
    @Test
    public void isUp1171(){
        assertTrue(!StringUtil.isUpperCase((char) 64311));
    }
    @Test
    public void isUp1172(){
        assertTrue(!StringUtil.isUpperCase((char) 64317));
    }
    @Test
    public void isUp1173(){
        assertTrue(!StringUtil.isUpperCase((char) 64319));
    }
    @Test
    public void isUp1174(){
        assertTrue(!StringUtil.isUpperCase((char) 64322));
    }
    @Test
    public void isUp1175(){
        assertTrue(!StringUtil.isUpperCase((char) 64325));
    }
    @Test
    public void isUp1176(){
        assertTrue(!StringUtil.isUpperCase((char) 64450));
    }
    @Test
    public void isUp1177(){
        assertTrue(!StringUtil.isUpperCase((char) 64466));
    }
    @Test
    public void isUp1178(){
        assertTrue(!StringUtil.isUpperCase((char) 64832));
    }
    @Test
    public void isUp1179(){
        assertTrue(!StringUtil.isUpperCase((char) 64847));
    }
    @Test
    public void isUp1180(){
        assertTrue(!StringUtil.isUpperCase((char) 64912));
    }
    @Test
    public void isUp1181(){
        assertTrue(!StringUtil.isUpperCase((char) 64913));
    }
    @Test
    public void isUp1182(){
        assertTrue(!StringUtil.isUpperCase((char) 64968));
    }
    @Test
    public void isUp1183(){
        assertTrue(!StringUtil.isUpperCase((char) 65007));
    }
    @Test
    public void isUp1184(){
        assertTrue(!StringUtil.isUpperCase((char) 65022));
    }
    @Test
    public void isUp1185(){
        assertTrue(!StringUtil.isUpperCase((char) 65023));
    }
    @Test
    public void isUp1186(){
        assertTrue(!StringUtil.isUpperCase((char) 65050));
    }
    @Test
    public void isUp1187(){
        assertTrue(!StringUtil.isUpperCase((char) 65055));
    }
    @Test
    public void isUp1188(){
        assertTrue(!StringUtil.isUpperCase((char) 65063));
    }
    @Test
    public void isUp1189(){
        assertTrue(!StringUtil.isUpperCase((char) 65071));
    }
    @Test
    public void isUp1190(){
        assertTrue(!StringUtil.isUpperCase((char) 65107));
    }
    @Test
    public void isUp1191(){
        assertTrue(!StringUtil.isUpperCase((char) 65127));
    }
    @Test
    public void isUp1192(){
        assertTrue(!StringUtil.isUpperCase((char) 65132));
    }
    @Test
    public void isUp1193(){
        assertTrue(!StringUtil.isUpperCase((char) 65135));
    }
    @Test
    public void isUp1194(){
        assertTrue(!StringUtil.isUpperCase((char) 65141));
    }
    @Test
    public void isUp1195(){
        assertTrue(!StringUtil.isUpperCase((char) 65277));
    }
    @Test
    public void isUp1196(){
        assertTrue(!StringUtil.isUpperCase((char) 65278));
    }
    @Test
    public void isUp1197(){
        assertTrue(!StringUtil.isUpperCase((char) 65280));
    }
    @Test
    public void isUp1198(){
        assertTrue(!StringUtil.isUpperCase((char) 65471));
    }
    @Test
    public void isUp1199(){
        assertTrue(!StringUtil.isUpperCase((char) 65473));
    }
    @Test
    public void isUp1200(){
        assertTrue(!StringUtil.isUpperCase((char) 65480));
    }
    @Test
    public void isUp1201(){
        assertTrue(!StringUtil.isUpperCase((char) 65481));
    }
    @Test
    public void isUp1202(){
        assertTrue(!StringUtil.isUpperCase((char) 65488));
    }
    @Test
    public void isUp1203(){
        assertTrue(!StringUtil.isUpperCase((char) 65489));
    }
    @Test
    public void isUp1204(){
        assertTrue(!StringUtil.isUpperCase((char) 65496));
    }
    @Test
    public void isUp1205(){
        assertTrue(!StringUtil.isUpperCase((char) 65497));
    }
    @Test
    public void isUp1206(){
        assertTrue(!StringUtil.isUpperCase((char) 65501));
    }
    @Test
    public void isUp1207(){
        assertTrue(!StringUtil.isUpperCase((char) 65503));
    }
    @Test
    public void isUp1208(){
        assertTrue(!StringUtil.isUpperCase((char) 65511));
    }
    @Test
    public void isUp1209(){
        assertTrue(!StringUtil.isUpperCase((char) 65519));
    }
    @Test
    public void isUp1210(){
        assertTrue(!StringUtil.isUpperCase((char) 65528));
    }
    @Test
    public void isUp1211(){
        assertTrue(!StringUtil.isUpperCase((char) 65534));
    }
    @Test
    public void isUp1212(){
        assertTrue(StringUtil.isUpperCase((char) 1024));
    }
    @Test
    public void isUp1213(){
        assertTrue(StringUtil.isUpperCase((char) 8170));
    }
    @Test
    public void isUp1214(){
        assertTrue(!StringUtil.isUpperCase((char) 65339));
    }
    @Test
    public void isUp1215(){
        assertTrue(StringUtil.isUpperCase((char) 11374));
    }
    @Test
    public void isUp1216(){
        assertTrue(StringUtil.isUpperCase((char) 11375));
    }
    @Test
    public void isUp1217(){
        assertTrue(StringUtil.isUpperCase((char) 11363));
    }
    @Test
    public void isUp1218(){
        assertTrue(StringUtil.isUpperCase((char) 580));
    }
    @Test
    public void isUp1219(){
        assertTrue(StringUtil.isUpperCase((char) 581));
    }
    @Test
    public void isUp1220(){
        assertTrue(StringUtil.isUpperCase((char) 503));
    }
    @Test
    public void isUp1221(){
        assertTrue(StringUtil.isUpperCase((char) 407));
    }
    @Test
    public void isUp1222(){
        assertTrue(StringUtil.isUpperCase((char) 399));
    }
    @Test
    public void isUp1223(){
        assertTrue(StringUtil.isUpperCase((char) 400));
    }
    @Test
    public void isUp1224(){
        assertTrue(!StringUtil.isUpperCase((char) 1014));
    }
    @Test
    public void isUp1225(){
        assertTrue(!StringUtil.isUpperCase((char) 1542));
    }
    @Test
    public void isUp1226(){
        assertTrue(!StringUtil.isUpperCase((char) 1544));
    }
    @Test
    public void isUp1227(){
        assertTrue(!StringUtil.isUpperCase((char) 8260));
    }
    @Test
    public void isUp1228(){
        assertTrue(!StringUtil.isUpperCase((char) 8274));
    }
    @Test
    public void isUp1229(){
        assertTrue(!StringUtil.isUpperCase((char) 8314));
    }
    @Test
    public void isUp1230(){
        assertTrue(!StringUtil.isUpperCase((char) 8316));
    }
    @Test
    public void isUp1231(){
        assertTrue(!StringUtil.isUpperCase((char) 8330));
    }
    @Test
    public void isUp1232(){
        assertTrue(!StringUtil.isUpperCase((char) 8332));
    }
    @Test
    public void isUp1233(){
        assertTrue(!StringUtil.isUpperCase((char) 8472));
    }
    @Test
    public void isUp1234(){
        assertTrue(!StringUtil.isUpperCase((char) 8512));
    }
    @Test
    public void isUp1235(){
        assertTrue(!StringUtil.isUpperCase((char) 8516));
    }
    @Test
    public void isUp1236(){
        assertTrue(!StringUtil.isUpperCase((char) 8523));
    }
    @Test
    public void isUp1237(){
        assertTrue(!StringUtil.isUpperCase((char) 8592));
    }
    @Test
    public void isUp1238(){
        assertTrue(!StringUtil.isUpperCase((char) 8596));
    }
    @Test
    public void isUp1239(){
        assertTrue(!StringUtil.isUpperCase((char) 8602));
    }
    @Test
    public void isUp1240(){
        assertTrue(!StringUtil.isUpperCase((char) 8603));
    }
    @Test
    public void isUp1241(){
        assertTrue(!StringUtil.isUpperCase((char) 8608));
    }
    @Test
    public void isUp1242(){
        assertTrue(!StringUtil.isUpperCase((char) 8611));
    }
    @Test
    public void isUp1243(){
        assertTrue(!StringUtil.isUpperCase((char) 8614));
    }
    @Test
    public void isUp1244(){
        assertTrue(!StringUtil.isUpperCase((char) 8622));
    }
    @Test
    public void isUp1245(){
        assertTrue(!StringUtil.isUpperCase((char) 8654));
    }
    @Test
    public void isUp1246(){
        assertTrue(!StringUtil.isUpperCase((char) 8655));
    }
    @Test
    public void isUp1247(){
        assertTrue(!StringUtil.isUpperCase((char) 8658));
    }
    @Test
    public void isUp1248(){
        assertTrue(!StringUtil.isUpperCase((char) 8660));
    }
    @Test
    public void isUp1249(){
        assertTrue(!StringUtil.isUpperCase((char) 8692));
    }
    @Test
    public void isUp1250(){
        assertTrue(!StringUtil.isUpperCase((char) 8959));
    }
    @Test
    public void isUp1251(){
        assertTrue(!StringUtil.isUpperCase((char) 8968));
    }
    @Test
    public void isUp1252(){
        assertTrue(!StringUtil.isUpperCase((char) 8971));
    }
    @Test
    public void isUp1253(){
        assertTrue(!StringUtil.isUpperCase((char) 8992));
    }
    @Test
    public void isUp1254(){
        assertTrue(!StringUtil.isUpperCase((char) 8993));
    }
    @Test
    public void isUp1255(){
        assertTrue(!StringUtil.isUpperCase((char) 9084));
    }
    @Test
    public void isUp1256(){
        assertTrue(!StringUtil.isUpperCase((char) 9115));
    }
    @Test
    public void isUp1257(){
        assertTrue(!StringUtil.isUpperCase((char) 9139));
    }
    @Test
    public void isUp1258(){
        assertTrue(!StringUtil.isUpperCase((char) 9180));
    }
    @Test
    public void isUp1259(){
        assertTrue(!StringUtil.isUpperCase((char) 9185));
    }
    @Test
    public void isUp1260(){
        assertTrue(!StringUtil.isUpperCase((char) 9655));
    }
    @Test
    public void isUp1261(){
        assertTrue(!StringUtil.isUpperCase((char) 9665));
    }
    @Test
    public void isUp1262(){
        assertTrue(!StringUtil.isUpperCase((char) 9720));
    }
    @Test
    public void isUp1263(){
        assertTrue(!StringUtil.isUpperCase((char) 9727));
    }
    @Test
    public void isUp1264(){
        assertTrue(!StringUtil.isUpperCase((char) 9839));
    }
    @Test
    public void isUp1265(){
        assertTrue(!StringUtil.isUpperCase((char) 10176));
    }
    @Test
    public void isUp1266(){
        assertTrue(!StringUtil.isUpperCase((char) 10180));
    }
    @Test
    public void isUp1267(){
        assertTrue(!StringUtil.isUpperCase((char) 10183));
    }
    @Test
    public void isUp1268(){
        assertTrue(!StringUtil.isUpperCase((char) 10213));
    }
    @Test
    public void isUp1269(){
        assertTrue(!StringUtil.isUpperCase((char) 10224));
    }
    @Test
    public void isUp1270(){
        assertTrue(!StringUtil.isUpperCase((char) 10239));
    }
    @Test
    public void isUp1271(){
        assertTrue(!StringUtil.isUpperCase((char) 10496));
    }
    @Test
    public void isUp1272(){
        assertTrue(!StringUtil.isUpperCase((char) 10626));
    }
    @Test
    public void isUp1273(){
        assertTrue(!StringUtil.isUpperCase((char) 10649));
    }
    @Test
    public void isUp1274(){
        assertTrue(!StringUtil.isUpperCase((char) 10711));
    }
    @Test
    public void isUp1275(){
        assertTrue(!StringUtil.isUpperCase((char) 10716));
    }
    @Test
    public void isUp1276(){
        assertTrue(!StringUtil.isUpperCase((char) 10747));
    }
    @Test
    public void isUp1277(){
        assertTrue(!StringUtil.isUpperCase((char) 10750));
    }
    @Test
    public void isUp1278(){
        assertTrue(!StringUtil.isUpperCase((char) 11007));
    }
    @Test
    public void isUp1279(){
        assertTrue(!StringUtil.isUpperCase((char) 11056));
    }
    @Test
    public void isUp1280(){
        assertTrue(!StringUtil.isUpperCase((char) 11076));
    }
    @Test
    public void isUp1281(){
        assertTrue(!StringUtil.isUpperCase((char) 11079));
    }
    @Test
    public void isUp1282(){
        assertTrue(!StringUtil.isUpperCase((char) 11084));
    }
    @Test
    public void isUp1283(){
        assertTrue(!StringUtil.isUpperCase((char) 64297));
    }
    @Test
    public void isUp1284(){
        assertTrue(!StringUtil.isUpperCase((char) 65122));
    }
    @Test
    public void isUp1285(){
        assertTrue(!StringUtil.isUpperCase((char) 65124));
    }
    @Test
    public void isUp1286(){
        assertTrue(!StringUtil.isUpperCase((char) 65126));
    }
    @Test
    public void isUp1287(){
        assertTrue(!StringUtil.isUpperCase((char) 65291));
    }
    @Test
    public void isUp1288(){
        assertTrue(!StringUtil.isUpperCase((char) 65308));
    }
    @Test
    public void isUp1289(){
        assertTrue(!StringUtil.isUpperCase((char) 65310));
    }
    @Test
    public void isUp1290(){
        assertTrue(!StringUtil.isUpperCase((char) 65372));
    }
    @Test
    public void isUp1291(){
        assertTrue(!StringUtil.isUpperCase((char) 65374));
    }
    @Test
    public void isUp1292(){
        assertTrue(!StringUtil.isUpperCase((char) 65506));
    }
    @Test
    public void isUp1293(){
        assertTrue(!StringUtil.isUpperCase((char) 65513));
    }
    @Test
    public void isUp1294(){
        assertTrue(!StringUtil.isUpperCase((char) 65516));
    }
    @Test
    public void isUp1295(){
        assertTrue(!StringUtil.isUpperCase((char) 65517));
    }
    @Test
    public void isUp1296(){
        assertTrue(!StringUtil.isUpperCase((char) 11008));
    }
    @Test
    public void isUp1297(){
        assertTrue(!StringUtil.isUpperCase((char) 10712));
    }
    @Test
    public void isUp1298(){
        assertTrue(!StringUtil.isUpperCase((char) 10627));
    }
    @Test
    public void isUp1299(){
        assertTrue(!StringUtil.isUpperCase((char) 8960));
    }
    @Test
    public void isUp1300(){
        assertTrue(!StringUtil.isUpperCase((char) 247));
    }
    @Test
    public void isUp1301(){
        assertTrue(!StringUtil.isUpperCase((char) 215));
    }
    @Test
    public void isUp1302(){
        assertTrue(!StringUtil.isUpperCase((char) 177));
    }
    @Test
    public void isUp1303(){
        assertTrue(!StringUtil.isUpperCase((char) 172));
    }
    @Test
    public void isUp1304(){
        assertTrue(StringUtil.isUpperCase((char) 8544));
    }
    @Test
    public void isUp1305(){
        assertTrue(StringUtil.isUpperCase((char) 9398));
    }
    @Test
    public void isUp1306(){
        assertTrue(!StringUtil.isUpperCase((char) 8560));
    }
    @Test
    public void isUp1307(){
        assertTrue(!StringUtil.isUpperCase((char) 9424));
    }
    @Test
    public void isUp1308(){
        assertTrue(StringUtil.isUpperCase((char) 391));
    }
    @Test
    public void isUp1309(){
        assertTrue(StringUtil.isUpperCase((char) 393));
    }
    @Test
    public void isUp1310(){
        assertTrue(StringUtil.isUpperCase((char) 395));
    }
    @Test
    public void isUp1311(){
        assertTrue(!StringUtil.isUpperCase((char) 397));
    }
    @Test
    public void isUp1312(){
        assertTrue(StringUtil.isUpperCase((char) 401));
    }
    @Test
    public void isUp1313(){
        assertTrue(StringUtil.isUpperCase((char) 403));
    }
    @Test
    public void isUp1314(){
        assertTrue(StringUtil.isUpperCase((char) 408));
    }
    @Test
    public void isUp1315(){
        assertTrue(!StringUtil.isUpperCase((char) 411));
    }
    @Test
    public void isUp1316(){
        assertTrue(StringUtil.isUpperCase((char) 423));
    }
    @Test
    public void isUp1317(){
        assertTrue(StringUtil.isUpperCase((char) 425));
    }
    @Test
    public void isUp1318(){
        assertTrue(StringUtil.isUpperCase((char) 428));
    }
    @Test
    public void isUp1319(){
        assertTrue(StringUtil.isUpperCase((char) 430));
    }
    @Test
    public void isUp1320(){
        assertTrue(StringUtil.isUpperCase((char) 431));
    }
    @Test
    public void isUp1321(){
        assertTrue(StringUtil.isUpperCase((char) 433));
    }
    @Test
    public void isUp1322(){
        assertTrue(StringUtil.isUpperCase((char) 440));
    }
    @Test
    public void isUp1323(){
        assertTrue(!StringUtil.isUpperCase((char) 442));
    }
    @Test
    public void isUp1324(){
        assertTrue(StringUtil.isUpperCase((char) 444));
    }
    @Test
    public void isUp1325(){
        assertTrue(!StringUtil.isUpperCase((char) 446));
    }
    @Test
    public void isUp1326(){
        assertTrue(StringUtil.isUpperCase((char) 452));
    }
    @Test
    public void isUp1327(){
        assertTrue(StringUtil.isUpperCase((char) 455));
    }
    @Test
    public void isUp1328(){
        assertTrue(StringUtil.isUpperCase((char) 458));
    }
    @Test
    public void isUp1329(){
        assertTrue(!StringUtil.isUpperCase((char) 459));
    }
    @Test
    public void isUp1330(){
        assertTrue(StringUtil.isUpperCase((char) 461));
    }
    @Test
    public void isUp1331(){
        assertTrue(!StringUtil.isUpperCase((char) 476));
    }
    @Test
    public void isUp1332(){
        assertTrue(StringUtil.isUpperCase((char) 478));
    }
    @Test
    public void isUp1333(){
        assertTrue(!StringUtil.isUpperCase((char) 498));
    }
    @Test
    public void isUp1334(){
        assertTrue(StringUtil.isUpperCase((char) 500));
    }
    @Test
    public void isUp1335(){
        assertTrue(!StringUtil.isUpperCase((char) 591));
    }
    @Test
    public void isUp1336(){
        assertTrue(!StringUtil.isUpperCase((char) 593));
    }
    @Test
    public void isUp1337(){
        assertTrue(!StringUtil.isUpperCase((char) 1007));
    }
    @Test
    public void isUp1338(){
        assertTrue(!StringUtil.isUpperCase((char) 1009));
    }
    @Test
    public void isUp1339(){
        assertTrue(!StringUtil.isUpperCase((char) 1230));
    }
    @Test
    public void isUp1340(){
        assertTrue(StringUtil.isUpperCase((char) 1232));
    }
    @Test
    public void isUp1341(){
        assertTrue(!StringUtil.isUpperCase((char) 7935));
    }
    @Test
    public void isUp1342(){
        assertTrue(!StringUtil.isUpperCase((char) 7937));
    }
    @Test
    public void isUp1343(){
        assertTrue(!StringUtil.isUpperCase((char) 383));
    }
    @Test
    public void isUp1344(){
        assertTrue(!StringUtil.isUpperCase((char) 392));
    }
    @Test
    public void isUp1345(){
        assertTrue(!StringUtil.isUpperCase((char) 396));
    }
    @Test
    public void isUp1346(){
        assertTrue(!StringUtil.isUpperCase((char) 402));
    }
    @Test
    public void isUp1347(){
        assertTrue(!StringUtil.isUpperCase((char) 409));
    }
    @Test
    public void isUp1348(){
        assertTrue(!StringUtil.isUpperCase((char) 410));
    }
    @Test
    public void isUp1349(){
        assertTrue(!StringUtil.isUpperCase((char) 424));
    }
    @Test
    public void isUp1350(){
        assertTrue(!StringUtil.isUpperCase((char) 429));
    }
    @Test
    public void isUp1351(){
        assertTrue(!StringUtil.isUpperCase((char) 432));
    }
    @Test
    public void isUp1352(){
        assertTrue(!StringUtil.isUpperCase((char) 441));
    }
    @Test
    public void isUp1353(){
        assertTrue(!StringUtil.isUpperCase((char) 445));
    }
    @Test
    public void isUp1354(){
        assertTrue(!StringUtil.isUpperCase((char) 453));
    }
    @Test
    public void isUp1355(){
        assertTrue(!StringUtil.isUpperCase((char) 454));
    }
    @Test
    public void isUp1356(){
        assertTrue(!StringUtil.isUpperCase((char) 456));
    }
    @Test
    public void isUp1357(){
        assertTrue(!StringUtil.isUpperCase((char) 457));
    }
    @Test
    public void isUp1358(){
        assertTrue(!StringUtil.isUpperCase((char) 460));
    }
    @Test
    public void isUp1359(){
        assertTrue(!StringUtil.isUpperCase((char) 477));
    }
    @Test
    public void isUp1360(){
        assertTrue(!StringUtil.isUpperCase((char) 499));
    }
    @Test
    public void isUp1361(){
        assertTrue(!StringUtil.isUpperCase((char) 592));
    }
    @Test
    public void isUp1362(){
        assertTrue(!StringUtil.isUpperCase((char) 1008));
    }
    @Test
    public void isUp1363(){
        assertTrue(!StringUtil.isUpperCase((char) 1231));
    }
    @Test
    public void isUp1364(){
        assertTrue(!StringUtil.isUpperCase((char) 7936));
    }
    @Test
    public void isUp1365(){
        assertTrue(StringUtil.isUpperCase((char) 8473));
    }
    @Test
    public void isUp1366(){
        assertTrue(StringUtil.isUpperCase((char) 8517));
    }
    @Test
    public void isUp1367(){
        assertTrue(StringUtil.isUpperCase((char) 8499));
    }
    @Test
    public void isUp1368(){
        assertTrue(StringUtil.isUpperCase((char) 8469));
    }
    @Test
    public void isUp1369(){
        assertTrue(StringUtil.isUpperCase((char) 8488));
    }
    @Test
    public void isUp1370(){
        assertTrue(StringUtil.isUpperCase((char) 8455));
    }
    @Test
    public void isUp1371(){
        assertTrue(StringUtil.isUpperCase((char) 8484));
    }
    @Test
    public void isUp1372(){
        assertTrue(StringUtil.isUpperCase((char) 8450));
    }
    @Test
    public void isUp1373(){
        assertTrue(StringUtil.isUpperCase((char) 978));
    }
    @Test
    public void isUp1374(){
        assertTrue(StringUtil.isUpperCase((char) 8492));
    }
    @Test
    public void isUp1375(){
        assertTrue(!StringUtil.isUpperCase((char) 8188));
    }
    @Test
    public void isUp1376(){
        assertTrue(!StringUtil.isUpperCase((char) 8140));
    }
    @Test
    public void isUp1377(){
        assertTrue(!StringUtil.isUpperCase((char) 8124));
    }
    @Test
    public void isUp1378(){
        assertTrue(!StringUtil.isUpperCase((char) 8072));
    }
    @Test
    public void isUp1379(){
        assertTrue(!StringUtil.isUpperCase((char) 1632));
    }
    @Test
    public void isUp1380(){
        assertTrue(!StringUtil.isUpperCase('0'));
    }
    @Test
    public void isUp1381(){
        assertTrue(!StringUtil.isUpperCase((char)160));
    }
    @Test
    public void isLow1(){
        assertTrue(!StringUtil.isLowerCase((char)8544));
    }
    @Test
    public void isLow2(){
        assertTrue(StringUtil.isLowerCase((char)8560));
    }
    @Test
    public void isLow3(){
        assertTrue(!StringUtil.isLowerCase((char)9398));
    }
    @Test
    public void isLow4(){
        assertTrue(StringUtil.isLowerCase((char)9424));
    }
    @Test
    public void isLow5(){
        assertTrue(!StringUtil.isLowerCase((char)8602));
    }
    @Test
    public void isLow6(){
        assertTrue(StringUtil.isLowerCase((char) 382));
    }
    @Test
    public void isLow7(){
        assertTrue(StringUtil.isLowerCase((char) 384));
    }
    @Test
    public void isLow8(){
        assertTrue(!StringUtil.isLowerCase((char) 391));
    }
    @Test
    public void isLow9(){
        assertTrue(!StringUtil.isLowerCase((char) 393));
    }
    @Test
    public void isLow10(){
        assertTrue(!StringUtil.isLowerCase((char) 395));
    }
    @Test
    public void isLow11(){
        assertTrue(StringUtil.isLowerCase((char) 397));
    }
    @Test
    public void isLow12(){
        assertTrue(!StringUtil.isLowerCase((char) 401));
    }
    @Test
    public void isLow13(){
        assertTrue(!StringUtil.isLowerCase((char) 403));
    }
    @Test
    public void isLow14(){
        assertTrue(!StringUtil.isLowerCase((char) 408));
    }
    @Test
    public void isLow15(){
        assertTrue(StringUtil.isLowerCase((char) 411));
    }
    @Test
    public void isLow16(){
        assertTrue(!StringUtil.isLowerCase((char) 423));
    }
    @Test
    public void isLow17(){
        assertTrue(!StringUtil.isLowerCase((char) 425));
    }
    @Test
    public void isLow18(){
        assertTrue(!StringUtil.isLowerCase((char) 428));
    }
    @Test
    public void isLow19(){
        assertTrue(!StringUtil.isLowerCase((char) 430));
    }
    @Test
    public void isLow20(){
        assertTrue(!StringUtil.isLowerCase((char) 431));
    }
    @Test
    public void isLow21(){
        assertTrue(!StringUtil.isLowerCase((char) 433));
    }
    @Test
    public void isLow22(){
        assertTrue(!StringUtil.isLowerCase((char) 440));
    }
    @Test
    public void isLow23(){
        assertTrue(StringUtil.isLowerCase((char) 442));
    }
    @Test
    public void isLow24(){
        assertTrue(!StringUtil.isLowerCase((char) 444));
    }
    @Test
    public void isLow25(){
        assertTrue(StringUtil.isLowerCase((char) 446));
    }
    @Test
    public void isLow26(){
        assertTrue(!StringUtil.isLowerCase((char) 453));
    }
    @Test
    public void isLow27(){
        assertTrue(!StringUtil.isLowerCase((char) 455));
    }
    @Test
    public void isLow28(){
        assertTrue(!StringUtil.isLowerCase((char) 456));
    }
    @Test
    public void isLow29(){
        assertTrue(!StringUtil.isLowerCase((char) 458));
    }
    @Test
    public void isLow30(){
        assertTrue(!StringUtil.isLowerCase((char) 459));
    }
    @Test
    public void isLow31(){
        assertTrue(!StringUtil.isLowerCase((char) 461));
    }
    @Test
    public void isLow32(){
        assertTrue(StringUtil.isLowerCase((char) 476));
    }
    @Test
    public void isLow33(){
        assertTrue(!StringUtil.isLowerCase((char) 478));
    }
    @Test
    public void isLow34(){
        assertTrue(!StringUtil.isLowerCase((char) 498));
    }
    @Test
    public void isLow35(){
        assertTrue(!StringUtil.isLowerCase((char) 500));
    }
    @Test
    public void isLow36(){
        assertTrue(StringUtil.isLowerCase((char) 591));
    }
    @Test
    public void isLow37(){
        assertTrue(StringUtil.isLowerCase((char) 593));
    }
    @Test
    public void isLow38(){
        assertTrue(StringUtil.isLowerCase((char) 1007));
    }
    @Test
    public void isLow39(){
        assertTrue(StringUtil.isLowerCase((char) 1009));
    }
    @Test
    public void isLow40(){
        assertTrue(StringUtil.isLowerCase((char) 1230));
    }
    @Test
    public void isLow41(){
        assertTrue(!StringUtil.isLowerCase((char) 1232));
    }
    @Test
    public void isLow42(){
        assertTrue(StringUtil.isLowerCase((char) 7935));
    }
    @Test
    public void isLow43(){
        assertTrue(StringUtil.isLowerCase((char) 7937));
    }

    @Test
    public void isLow44(){
        assertTrue(StringUtil.isLowerCase((char) 383));
    }
    @Test
    public void isLow45(){
        assertTrue(StringUtil.isLowerCase((char) 392));
    }
    @Test
    public void isLow46(){
        assertTrue(StringUtil.isLowerCase((char) 396));
    }
    @Test
    public void isLow47(){
        assertTrue(StringUtil.isLowerCase((char) 402));
    }
    @Test
    public void isLow48(){
        assertTrue(StringUtil.isLowerCase((char) 409));
    }
    @Test
    public void isLow49(){
        assertTrue(StringUtil.isLowerCase((char) 410));
    }
    @Test
    public void isLow50(){
        assertTrue(StringUtil.isLowerCase((char) 424));
    }
    @Test
    public void isLow51(){
        assertTrue(StringUtil.isLowerCase((char) 429));
    }
    @Test
    public void isLow52(){
        assertTrue(StringUtil.isLowerCase((char) 432));
    }
    @Test
    public void isLow53(){
        assertTrue(StringUtil.isLowerCase((char) 441));
    }
    @Test
    public void isLow54(){
        assertTrue(StringUtil.isLowerCase((char) 445));
    }
    @Test
    public void isLow55(){
        assertTrue(StringUtil.isLowerCase((char) 454));
    }
    @Test
    public void isLow56(){
        assertTrue(StringUtil.isLowerCase((char) 457));
    }
    @Test
    public void isLow57(){
        assertTrue(StringUtil.isLowerCase((char) 460));
    }
    @Test
    public void isLow58(){
        assertTrue(StringUtil.isLowerCase((char) 477));
    }
    @Test
    public void isLow59(){
        assertTrue(StringUtil.isLowerCase((char) 499));
    }
    @Test
    public void isLow60(){
        assertTrue(StringUtil.isLowerCase((char) 592));
    }
    @Test
    public void isLow61(){
        assertTrue(StringUtil.isLowerCase((char) 1008));
    }
    @Test
    public void isLow62(){
        assertTrue(StringUtil.isLowerCase((char) 1231));
    }
    @Test
    public void isLow63(){
        assertTrue(StringUtil.isLowerCase((char) 7936));
    }

    @Test
    public void isLow64(){
        assertTrue(!StringUtil.isLowerCase((char) 48));
    }
    @Test
    public void isLow65(){
        assertTrue(!StringUtil.isLowerCase((char) 57));
    }
    @Test
    public void isLow66(){
        assertTrue(!StringUtil.isLowerCase((char) 127));
    }
    @Test
    public void isLow67(){
        assertTrue(!StringUtil.isLowerCase((char) 160));
    }
    @Test
    public void isLow68(){
        assertTrue(StringUtil.isLowerCase((char) 170));
    }
    @Test
    public void isLow69(){
        assertTrue(StringUtil.isLowerCase((char) 186));
    }
    @Test
    public void isLow70(){
        assertTrue(StringUtil.isLowerCase((char) 223));
    }
    @Test
    public void isLow71(){
        assertTrue(StringUtil.isLowerCase((char) 312));
    }
    @Test
    public void isLow72(){
        assertTrue(StringUtil.isLowerCase((char) 329));
    }
    @Test
    public void isLow73(){
        assertTrue(StringUtil.isLowerCase((char) 397));
    }
    @Test
    public void isLow74(){
        assertTrue(StringUtil.isLowerCase((char) 411));
    }
    @Test
    public void isLow75(){
        assertTrue(StringUtil.isLowerCase((char) 426));
    }
    @Test
    public void isLow76(){
        assertTrue(StringUtil.isLowerCase((char) 427));
    }
    @Test
    public void isLow77(){
        assertTrue(StringUtil.isLowerCase((char) 442));
    }
    @Test
    public void isLow78(){
        assertTrue(StringUtil.isLowerCase((char) 446));
    }
    @Test
    public void isLow79(){
        assertTrue(!StringUtil.isLowerCase((char) 453));
    }
    @Test
    public void isLow80(){
        assertTrue(!StringUtil.isLowerCase((char) 456));
    }
    @Test
    public void isLow81(){
        assertTrue(!StringUtil.isLowerCase((char) 459));
    }
    @Test
    public void isLow82(){
        assertTrue(StringUtil.isLowerCase((char) 496));
    }
    @Test
    public void isLow83(){
        assertTrue(!StringUtil.isLowerCase((char) 498));
    }
    @Test
    public void isLow84(){
        assertTrue(StringUtil.isLowerCase((char) 545));
    }
    @Test
    public void isLow85(){
        assertTrue(StringUtil.isLowerCase((char) 564));
    }
    @Test
    public void isLow86(){
        assertTrue(StringUtil.isLowerCase((char) 569));
    }
    @Test
    public void isLow87(){
        assertTrue(StringUtil.isLowerCase((char) 597));
    }
    @Test
    public void isLow88(){
        assertTrue(StringUtil.isLowerCase((char) 600));
    }
    @Test
    public void isLow89(){
        assertTrue(StringUtil.isLowerCase((char) 602));
    }
    @Test
    public void isLow90(){
        assertTrue(StringUtil.isLowerCase((char) 604));
    }
    @Test
    public void isLow91(){
        assertTrue(StringUtil.isLowerCase((char) 607));
    }
    @Test
    public void isLow92(){
        assertTrue(StringUtil.isLowerCase((char) 609));
    }
    @Test
    public void isLow93(){
        assertTrue(StringUtil.isLowerCase((char) 610));
    }
    @Test
    public void isLow94(){
        assertTrue(StringUtil.isLowerCase((char) 612));
    }
    @Test
    public void isLow95(){
        assertTrue(StringUtil.isLowerCase((char) 624));
    }
    @Test
    public void isLow96(){
        assertTrue(StringUtil.isLowerCase((char) 627));
    }
    @Test
    public void isLow97(){
        assertTrue(StringUtil.isLowerCase((char) 628));
    }
    @Test
    public void isLow98(){
        assertTrue(StringUtil.isLowerCase((char) 630));
    }
    @Test
    public void isLow99(){
        assertTrue(StringUtil.isLowerCase((char) 636));
    }
    @Test
    public void isLow100(){
        assertTrue(StringUtil.isLowerCase((char) 638));
    }
    @Test
    public void isLow101(){
        assertTrue(StringUtil.isLowerCase((char) 639));
    }
    @Test
    public void isLow102(){
        assertTrue(StringUtil.isLowerCase((char) 641));
    }
    @Test
    public void isLow103(){
        assertTrue(StringUtil.isLowerCase((char) 647));
    }
    @Test
    public void isLow104(){
        assertTrue(StringUtil.isLowerCase((char) 653));
    }
    @Test
    public void isLow105(){
        assertTrue(StringUtil.isLowerCase((char) 696));
    }
    @Test
    public void isLow106(){
        assertTrue(StringUtil.isLowerCase((char) 704));
    }
    @Test
    public void isLow107(){
        assertTrue(StringUtil.isLowerCase((char) 705));
    }
    @Test
    public void isLow108(){
        assertTrue(StringUtil.isLowerCase((char) 736));
    }
    @Test
    public void isLow109(){
        assertTrue(StringUtil.isLowerCase((char) 740));
    }
    @Test
    public void isLow110(){
        assertTrue(!StringUtil.isLowerCase((char) 888));
    }
    @Test
    public void isLow111(){
        assertTrue(StringUtil.isLowerCase((char) 890));
    }
    @Test
    public void isLow112(){
        assertTrue(!StringUtil.isLowerCase((char) 895));
    }
    @Test
    public void isLow113(){
        assertTrue(!StringUtil.isLowerCase((char) 899));
    }
    @Test
    public void isLow114(){
        assertTrue(!StringUtil.isLowerCase((char) 907));
    }
    @Test
    public void isLow115(){
        assertTrue(!StringUtil.isLowerCase((char) 909));
    }
    @Test
    public void isLow116(){
        assertTrue(StringUtil.isLowerCase((char) 912));
    }
    @Test
    public void isLow117(){
        assertTrue(!StringUtil.isLowerCase((char) 930));
    }
    @Test
    public void isLow118(){
        assertTrue(StringUtil.isLowerCase((char) 944));
    }
    @Test
    public void isLow119(){
        assertTrue(StringUtil.isLowerCase((char) 1011));
    }
    @Test
    public void isLow120(){
        assertTrue(StringUtil.isLowerCase((char) 1020));
    }
    @Test
    public void isLow121(){
        assertTrue(!StringUtil.isLowerCase((char) 1320));
    }
    @Test
    public void isLow122(){
        assertTrue(!StringUtil.isLowerCase((char) 1328));
    }
    @Test
    public void isLow123(){
        assertTrue(!StringUtil.isLowerCase((char) 1367));
    }
    @Test
    public void isLow124(){
        assertTrue(!StringUtil.isLowerCase((char) 1368));
    }
    @Test
    public void isLow125(){
        assertTrue(!StringUtil.isLowerCase((char) 1376));
    }
    @Test
    public void isLow126(){
        assertTrue(StringUtil.isLowerCase((char) 1415));
    }
    @Test
    public void isLow127(){
        assertTrue(!StringUtil.isLowerCase((char) 1416));
    }
    @Test
    public void isLow128(){
        assertTrue(!StringUtil.isLowerCase((char) 1419));
    }
    @Test
    public void isLow129(){
        assertTrue(!StringUtil.isLowerCase((char) 1422));
    }
    @Test
    public void isLow130(){
        assertTrue(!StringUtil.isLowerCase((char) 1424));
    }
    @Test
    public void isLow131(){
        assertTrue(!StringUtil.isLowerCase((char) 1480));
    }
    @Test
    public void isLow132(){
        assertTrue(!StringUtil.isLowerCase((char) 1487));
    }
    @Test
    public void isLow133(){
        assertTrue(!StringUtil.isLowerCase((char) 1515));
    }
    @Test
    public void isLow134(){
        assertTrue(!StringUtil.isLowerCase((char) 1519));
    }
    @Test
    public void isLow135(){
        assertTrue(!StringUtil.isLowerCase((char) 1525));
    }
    @Test
    public void isLow136(){
        assertTrue(!StringUtil.isLowerCase((char) 1535));
    }
    @Test
    public void isLow137(){
        assertTrue(!StringUtil.isLowerCase((char) 1541));
    }
    @Test
    public void isLow138(){
        assertTrue(!StringUtil.isLowerCase((char) 1564));
    }
    @Test
    public void isLow139(){
        assertTrue(!StringUtil.isLowerCase((char) 1565));
    }
    @Test
    public void isLow140(){
        assertTrue(!StringUtil.isLowerCase((char) 1632));
    }
    @Test
    public void isLow141(){
        assertTrue(!StringUtil.isLowerCase((char) 1641));
    }
    @Test
    public void isLow142(){
        assertTrue(!StringUtil.isLowerCase((char) 1776));
    }
    @Test
    public void isLow143(){
        assertTrue(!StringUtil.isLowerCase((char) 1785));
    }
    @Test
    public void isLow144(){
        assertTrue(!StringUtil.isLowerCase((char) 1806));
    }
    @Test
    public void isLow145(){
        assertTrue(!StringUtil.isLowerCase((char) 1867));
    }
    @Test
    public void isLow146(){
        assertTrue(!StringUtil.isLowerCase((char) 1868));
    }
    @Test
    public void isLow147(){
        assertTrue(!StringUtil.isLowerCase((char) 1970));
    }
    @Test
    public void isLow148(){
        assertTrue(!StringUtil.isLowerCase((char) 1993));
    }
    @Test
    public void isLow149(){
        assertTrue(!StringUtil.isLowerCase((char) 2043));
    }
    @Test
    public void isLow150(){
        assertTrue(!StringUtil.isLowerCase((char) 2047));
    }
    @Test
    public void isLow151(){
        assertTrue(!StringUtil.isLowerCase((char) 2094));
    }
    @Test
    public void isLow152(){
        assertTrue(!StringUtil.isLowerCase((char) 2095));
    }
    @Test
    public void isLow153(){
        assertTrue(!StringUtil.isLowerCase((char) 2111));
    }
    @Test
    public void isLow154(){
        assertTrue(!StringUtil.isLowerCase((char) 2140));
    }
    @Test
    public void isLow155(){
        assertTrue(!StringUtil.isLowerCase((char) 2141));
    }
    @Test
    public void isLow156(){
        assertTrue(!StringUtil.isLowerCase((char) 2143));
    }
    @Test
    public void isLow157(){
        assertTrue(!StringUtil.isLowerCase((char) 2207));
    }
    @Test
    public void isLow158(){
        assertTrue(!StringUtil.isLowerCase((char) 2209));
    }
    @Test
    public void isLow159(){
        assertTrue(!StringUtil.isLowerCase((char) 2221));
    }
    @Test
    public void isLow160(){
        assertTrue(!StringUtil.isLowerCase((char) 2275));
    }
    @Test
    public void isLow161(){
        assertTrue(!StringUtil.isLowerCase((char) 2303));
    }
    @Test
    public void isLow162(){
        assertTrue(!StringUtil.isLowerCase((char) 2406));
    }
    @Test
    public void isLow163(){
        assertTrue(!StringUtil.isLowerCase((char) 2415));
    }
    @Test
    public void isLow164(){
        assertTrue(!StringUtil.isLowerCase((char) 2424));
    }
    @Test
    public void isLow165(){
        assertTrue(!StringUtil.isLowerCase((char) 2432));
    }
    @Test
    public void isLow166(){
        assertTrue(!StringUtil.isLowerCase((char) 2436));
    }
    @Test
    public void isLow167(){
        assertTrue(!StringUtil.isLowerCase((char) 2445));
    }
    @Test
    public void isLow168(){
        assertTrue(!StringUtil.isLowerCase((char) 2446));
    }
    @Test
    public void isLow169(){
        assertTrue(!StringUtil.isLowerCase((char) 2449));
    }
    @Test
    public void isLow170(){
        assertTrue(!StringUtil.isLowerCase((char) 2450));
    }
    @Test
    public void isLow171(){
        assertTrue(!StringUtil.isLowerCase((char) 2473));
    }
    @Test
    public void isLow172(){
        assertTrue(!StringUtil.isLowerCase((char) 2481));
    }
    @Test
    public void isLow173(){
        assertTrue(!StringUtil.isLowerCase((char) 2483));
    }
    @Test
    public void isLow174(){
        assertTrue(!StringUtil.isLowerCase((char) 2485));
    }
    @Test
    public void isLow175(){
        assertTrue(!StringUtil.isLowerCase((char) 2490));
    }
    @Test
    public void isLow176(){
        assertTrue(!StringUtil.isLowerCase((char) 2491));
    }
    @Test
    public void isLow177(){
        assertTrue(!StringUtil.isLowerCase((char) 2501));
    }
    @Test
    public void isLow178(){
        assertTrue(!StringUtil.isLowerCase((char) 2502));
    }
    @Test
    public void isLow179(){
        assertTrue(!StringUtil.isLowerCase((char) 2505));
    }
    @Test
    public void isLow180(){
        assertTrue(!StringUtil.isLowerCase((char) 2506));
    }
    @Test
    public void isLow181(){
        assertTrue(!StringUtil.isLowerCase((char) 2511));
    }
    @Test
    public void isLow182(){
        assertTrue(!StringUtil.isLowerCase((char) 2518));
    }
    @Test
    public void isLow183(){
        assertTrue(!StringUtil.isLowerCase((char) 2520));
    }
    @Test
    public void isLow184(){
        assertTrue(!StringUtil.isLowerCase((char) 2523));
    }
    @Test
    public void isLow185(){
        assertTrue(!StringUtil.isLowerCase((char) 2526));
    }
    @Test
    public void isLow186(){
        assertTrue(!StringUtil.isLowerCase((char) 2532));
    }
    @Test
    public void isLow187(){
        assertTrue(!StringUtil.isLowerCase((char) 2543));
    }
    @Test
    public void isLow188(){
        assertTrue(!StringUtil.isLowerCase((char) 2556));
    }
    @Test
    public void isLow189(){
        assertTrue(!StringUtil.isLowerCase((char) 2560));
    }
    @Test
    public void isLow190(){
        assertTrue(!StringUtil.isLowerCase((char) 2564));
    }
    @Test
    public void isLow191(){
        assertTrue(!StringUtil.isLowerCase((char) 2571));
    }
    @Test
    public void isLow192(){
        assertTrue(!StringUtil.isLowerCase((char) 2574));
    }
    @Test
    public void isLow193(){
        assertTrue(!StringUtil.isLowerCase((char) 2577));
    }
    @Test
    public void isLow194(){
        assertTrue(!StringUtil.isLowerCase((char) 2578));
    }
    @Test
    public void isLow195(){
        assertTrue(!StringUtil.isLowerCase((char) 2601));
    }
    @Test
    public void isLow196(){
        assertTrue(!StringUtil.isLowerCase((char) 2609));
    }
    @Test
    public void isLow197(){
        assertTrue(!StringUtil.isLowerCase((char) 2612));
    }
    @Test
    public void isLow198(){
        assertTrue(!StringUtil.isLowerCase((char) 2615));
    }
    @Test
    public void isLow199(){
        assertTrue(!StringUtil.isLowerCase((char) 2618));
    }
    @Test
    public void isLow200(){
        assertTrue(!StringUtil.isLowerCase((char) 2619));
    }
    @Test
    public void isLow201(){
        assertTrue(!StringUtil.isLowerCase((char) 2621));
    }
    @Test
    public void isLow202(){
        assertTrue(!StringUtil.isLowerCase((char) 2627));
    }
    @Test
    public void isLow203(){
        assertTrue(!StringUtil.isLowerCase((char) 2630));
    }
    @Test
    public void isLow204(){
        assertTrue(!StringUtil.isLowerCase((char) 2633));
    }
    @Test
    public void isLow205(){
        assertTrue(!StringUtil.isLowerCase((char) 2634));
    }
    @Test
    public void isLow206(){
        assertTrue(!StringUtil.isLowerCase((char) 2638));
    }
    @Test
    public void isLow207(){
        assertTrue(!StringUtil.isLowerCase((char) 2640));
    }
    @Test
    public void isLow208(){
        assertTrue(!StringUtil.isLowerCase((char) 2642));
    }
    @Test
    public void isLow209(){
        assertTrue(!StringUtil.isLowerCase((char) 2648));
    }
    @Test
    public void isLow210(){
        assertTrue(!StringUtil.isLowerCase((char) 2653));
    }
    @Test
    public void isLow211(){
        assertTrue(!StringUtil.isLowerCase((char) 2655));
    }
    @Test
    public void isLow212(){
        assertTrue(!StringUtil.isLowerCase((char) 2671));
    }
    @Test
    public void isLow213(){
        assertTrue(!StringUtil.isLowerCase((char) 2678));
    }
    @Test
    public void isLow214(){
        assertTrue(!StringUtil.isLowerCase((char) 2688));
    }
    @Test
    public void isLow215(){
        assertTrue(!StringUtil.isLowerCase((char) 2692));
    }
    @Test
    public void isLow216(){
        assertTrue(!StringUtil.isLowerCase((char) 2702));
    }
    @Test
    public void isLow217(){
        assertTrue(!StringUtil.isLowerCase((char) 2706));
    }
    @Test
    public void isLow218(){
        assertTrue(!StringUtil.isLowerCase((char) 2729));
    }
    @Test
    public void isLow219(){
        assertTrue(!StringUtil.isLowerCase((char) 2737));
    }
    @Test
    public void isLow220(){
        assertTrue(!StringUtil.isLowerCase((char) 2740));
    }
    @Test
    public void isLow221(){
        assertTrue(!StringUtil.isLowerCase((char) 2746));
    }
    @Test
    public void isLow222(){
        assertTrue(!StringUtil.isLowerCase((char) 2747));
    }
    @Test
    public void isLow223(){
        assertTrue(!StringUtil.isLowerCase((char) 2758));
    }
    @Test
    public void isLow224(){
        assertTrue(!StringUtil.isLowerCase((char) 2762));
    }
    @Test
    public void isLow225(){
        assertTrue(!StringUtil.isLowerCase((char) 2766));
    }
    @Test
    public void isLow226(){
        assertTrue(!StringUtil.isLowerCase((char) 2767));
    }
    @Test
    public void isLow227(){
        assertTrue(!StringUtil.isLowerCase((char) 2769));
    }
    @Test
    public void isLow228(){
        assertTrue(!StringUtil.isLowerCase((char) 2783));
    }
    @Test
    public void isLow229(){
        assertTrue(!StringUtil.isLowerCase((char) 2788));
    }
    @Test
    public void isLow230(){
        assertTrue(!StringUtil.isLowerCase((char) 2799));
    }
    @Test
    public void isLow231(){
        assertTrue(!StringUtil.isLowerCase((char) 2802));
    }
    @Test
    public void isLow232(){
        assertTrue(!StringUtil.isLowerCase((char) 2816));
    }
    @Test
    public void isLow233(){
        assertTrue(!StringUtil.isLowerCase((char) 2820));
    }
    @Test
    public void isLow234(){
        assertTrue(!StringUtil.isLowerCase((char) 2829));
    }
    @Test
    public void isLow235(){
        assertTrue(!StringUtil.isLowerCase((char) 2830));
    }
    @Test
    public void isLow236(){
        assertTrue(!StringUtil.isLowerCase((char) 2833));
    }
    @Test
    public void isLow237(){
        assertTrue(!StringUtil.isLowerCase((char) 2834));
    }
    @Test
    public void isLow238(){
        assertTrue(!StringUtil.isLowerCase((char) 2857));
    }
    @Test
    public void isLow239(){
        assertTrue(!StringUtil.isLowerCase((char) 2865));
    }
    @Test
    public void isLow240(){
        assertTrue(!StringUtil.isLowerCase((char) 2868));
    }
    @Test
    public void isLow241(){
        assertTrue(!StringUtil.isLowerCase((char) 2874));
    }
    @Test
    public void isLow242(){
        assertTrue(!StringUtil.isLowerCase((char) 2875));
    }
    @Test
    public void isLow243(){
        assertTrue(!StringUtil.isLowerCase((char) 2885));
    }
    @Test
    public void isLow244(){
        assertTrue(!StringUtil.isLowerCase((char) 2886));
    }
    @Test
    public void isLow245(){
        assertTrue(!StringUtil.isLowerCase((char) 2889));
    }
    @Test
    public void isLow246(){
        assertTrue(!StringUtil.isLowerCase((char) 2890));
    }
    @Test
    public void isLow247(){
        assertTrue(!StringUtil.isLowerCase((char) 2894));
    }
    @Test
    public void isLow248(){
        assertTrue(!StringUtil.isLowerCase((char) 2901));
    }
    @Test
    public void isLow249(){
        assertTrue(!StringUtil.isLowerCase((char) 2904));
    }
    @Test
    public void isLow250(){
        assertTrue(!StringUtil.isLowerCase((char) 2907));
    }
    @Test
    public void isLow251(){
        assertTrue(!StringUtil.isLowerCase((char) 2910));
    }
    @Test
    public void isLow252(){
        assertTrue(!StringUtil.isLowerCase((char) 2916));
    }
    @Test
    public void isLow253(){
        assertTrue(!StringUtil.isLowerCase((char) 2927));
    }
    @Test
    public void isLow254(){
        assertTrue(!StringUtil.isLowerCase((char) 2936));
    }
    @Test
    public void isLow255(){
        assertTrue(!StringUtil.isLowerCase((char) 2945));
    }
    @Test
    public void isLow256(){
        assertTrue(!StringUtil.isLowerCase((char) 2948));
    }
    @Test
    public void isLow257(){
        assertTrue(!StringUtil.isLowerCase((char) 2955));
    }
    @Test
    public void isLow258(){
        assertTrue(!StringUtil.isLowerCase((char) 2957));
    }
    @Test
    public void isLow259(){
        assertTrue(!StringUtil.isLowerCase((char) 2961));
    }
    @Test
    public void isLow260(){
        assertTrue(!StringUtil.isLowerCase((char) 2966));
    }
    @Test
    public void isLow261(){
        assertTrue(!StringUtil.isLowerCase((char) 2968));
    }
    @Test
    public void isLow262(){
        assertTrue(!StringUtil.isLowerCase((char) 2971));
    }
    @Test
    public void isLow263(){
        assertTrue(!StringUtil.isLowerCase((char) 2973));
    }
    @Test
    public void isLow264(){
        assertTrue(!StringUtil.isLowerCase((char) 2976));
    }
    @Test
    public void isLow265(){
        assertTrue(!StringUtil.isLowerCase((char) 2978));
    }
    @Test
    public void isLow266(){
        assertTrue(!StringUtil.isLowerCase((char) 2981));
    }
    @Test
    public void isLow267(){
        assertTrue(!StringUtil.isLowerCase((char) 2983));
    }
    @Test
    public void isLow268(){
        assertTrue(!StringUtil.isLowerCase((char) 2987));
    }
    @Test
    public void isLow269(){
        assertTrue(!StringUtil.isLowerCase((char) 2989));
    }
    @Test
    public void isLow270(){
        assertTrue(!StringUtil.isLowerCase((char) 3002));
    }
    @Test
    public void isLow271(){
        assertTrue(!StringUtil.isLowerCase((char) 3005));
    }
    @Test
    public void isLow272(){
        assertTrue(!StringUtil.isLowerCase((char) 3011));
    }
    @Test
    public void isLow273(){
        assertTrue(!StringUtil.isLowerCase((char) 3013));
    }
    @Test
    public void isLow274(){
        assertTrue(!StringUtil.isLowerCase((char) 3017));
    }
    @Test
    public void isLow275(){
        assertTrue(!StringUtil.isLowerCase((char) 3022));
    }
    @Test
    public void isLow276(){
        assertTrue(!StringUtil.isLowerCase((char) 3023));
    }
    @Test
    public void isLow277(){
        assertTrue(!StringUtil.isLowerCase((char) 3025));
    }
    @Test
    public void isLow278(){
        assertTrue(!StringUtil.isLowerCase((char) 3030));
    }
    @Test
    public void isLow279(){
        assertTrue(!StringUtil.isLowerCase((char) 3032));
    }
    @Test
    public void isLow280(){
        assertTrue(!StringUtil.isLowerCase((char) 3055));
    }
    @Test
    public void isLow281(){
        assertTrue(!StringUtil.isLowerCase((char) 3067));
    }
    @Test
    public void isLow282(){
        assertTrue(!StringUtil.isLowerCase((char) 3072));
    }
    @Test
    public void isLow283(){
        assertTrue(!StringUtil.isLowerCase((char) 3076));
    }
    @Test
    public void isLow284(){
        assertTrue(!StringUtil.isLowerCase((char) 3085));
    }
    @Test
    public void isLow285(){
        assertTrue(!StringUtil.isLowerCase((char) 3089));
    }
    @Test
    public void isLow286(){
        assertTrue(!StringUtil.isLowerCase((char) 3113));
    }
    @Test
    public void isLow287(){
        assertTrue(!StringUtil.isLowerCase((char) 3124));
    }
    @Test
    public void isLow288(){
        assertTrue(!StringUtil.isLowerCase((char) 3130));
    }
    @Test
    public void isLow289(){
        assertTrue(!StringUtil.isLowerCase((char) 3132));
    }
    @Test
    public void isLow290(){
        assertTrue(!StringUtil.isLowerCase((char) 3141));
    }
    @Test
    public void isLow291(){
        assertTrue(!StringUtil.isLowerCase((char) 3145));
    }
    @Test
    public void isLow292(){
        assertTrue(!StringUtil.isLowerCase((char) 3150));
    }
    @Test
    public void isLow293(){
        assertTrue(!StringUtil.isLowerCase((char) 3156));
    }
    @Test
    public void isLow294(){
        assertTrue(!StringUtil.isLowerCase((char) 3159));
    }
    @Test
    public void isLow295(){
        assertTrue(!StringUtil.isLowerCase((char) 3162));
    }
    @Test
    public void isLow296(){
        assertTrue(!StringUtil.isLowerCase((char) 3167));
    }
    @Test
    public void isLow297(){
        assertTrue(!StringUtil.isLowerCase((char) 3172));
    }
    @Test
    public void isLow298(){
        assertTrue(!StringUtil.isLowerCase((char) 3191));
    }
    @Test
    public void isLow299(){
        assertTrue(!StringUtil.isLowerCase((char) 3200));
    }
    @Test
    public void isLow300(){
        assertTrue(!StringUtil.isLowerCase((char) 3201));
    }
    @Test
    public void isLow301(){
        assertTrue(!StringUtil.isLowerCase((char) 3204));
    }
    @Test
    public void isLow302(){
        assertTrue(!StringUtil.isLowerCase((char) 3213));
    }
    @Test
    public void isLow303(){
        assertTrue(!StringUtil.isLowerCase((char) 3217));
    }
    @Test
    public void isLow304(){
        assertTrue(!StringUtil.isLowerCase((char) 3241));
    }
    @Test
    public void isLow305(){
        assertTrue(!StringUtil.isLowerCase((char) 3252));
    }
    @Test
    public void isLow306(){
        assertTrue(!StringUtil.isLowerCase((char) 3258));
    }
    @Test
    public void isLow307(){
        assertTrue(!StringUtil.isLowerCase((char) 3259));
    }
    @Test
    public void isLow308(){
        assertTrue(!StringUtil.isLowerCase((char) 3269));
    }
    @Test
    public void isLow309(){
        assertTrue(!StringUtil.isLowerCase((char) 3273));
    }
    @Test
    public void isLow310(){
        assertTrue(!StringUtil.isLowerCase((char) 3278));
    }
    @Test
    public void isLow311(){
        assertTrue(!StringUtil.isLowerCase((char) 3284));
    }
    @Test
    public void isLow312(){
        assertTrue(!StringUtil.isLowerCase((char) 3287));
    }
    @Test
    public void isLow313(){
        assertTrue(!StringUtil.isLowerCase((char) 3293));
    }
    @Test
    public void isLow314(){
        assertTrue(!StringUtil.isLowerCase((char) 3295));
    }
    @Test
    public void isLow315(){
        assertTrue(!StringUtil.isLowerCase((char) 3300));
    }
    @Test
    public void isLow316(){
        assertTrue(!StringUtil.isLowerCase((char) 3312));
    }
    @Test
    public void isLow317(){
        assertTrue(!StringUtil.isLowerCase((char) 3315));
    }
    @Test
    public void isLow318(){
        assertTrue(!StringUtil.isLowerCase((char) 3329));
    }
    @Test
    public void isLow319(){
        assertTrue(!StringUtil.isLowerCase((char) 3332));
    }
    @Test
    public void isLow320(){
        assertTrue(!StringUtil.isLowerCase((char) 3341));
    }
    @Test
    public void isLow321(){
        assertTrue(!StringUtil.isLowerCase((char) 3345));
    }
    @Test
    public void isLow322(){
        assertTrue(!StringUtil.isLowerCase((char) 3387));
    }
    @Test
    public void isLow323(){
        assertTrue(!StringUtil.isLowerCase((char) 3388));
    }
    @Test
    public void isLow324(){
        assertTrue(!StringUtil.isLowerCase((char) 3397));
    }
    @Test
    public void isLow325(){
        assertTrue(!StringUtil.isLowerCase((char) 3401));
    }
    @Test
    public void isLow326(){
        assertTrue(!StringUtil.isLowerCase((char) 3407));
    }
    @Test
    public void isLow327(){
        assertTrue(!StringUtil.isLowerCase((char) 3414));
    }
    @Test
    public void isLow328(){
        assertTrue(!StringUtil.isLowerCase((char) 3416));
    }
    @Test
    public void isLow329(){
        assertTrue(!StringUtil.isLowerCase((char) 3423));
    }
    @Test
    public void isLow330(){
        assertTrue(!StringUtil.isLowerCase((char) 3428));
    }
    @Test
    public void isLow331(){
        assertTrue(!StringUtil.isLowerCase((char) 3439));
    }
    @Test
    public void isLow332(){
        assertTrue(!StringUtil.isLowerCase((char) 3446));
    }
    @Test
    public void isLow333(){
        assertTrue(!StringUtil.isLowerCase((char) 3448));
    }
    @Test
    public void isLow334(){
        assertTrue(!StringUtil.isLowerCase((char) 3456));
    }
    @Test
    public void isLow335(){
        assertTrue(!StringUtil.isLowerCase((char) 3457));
    }
    @Test
    public void isLow336(){
        assertTrue(!StringUtil.isLowerCase((char) 3460));
    }
    @Test
    public void isLow337(){
        assertTrue(!StringUtil.isLowerCase((char) 3479));
    }
    @Test
    public void isLow338(){
        assertTrue(!StringUtil.isLowerCase((char) 3481));
    }
    @Test
    public void isLow339(){
        assertTrue(!StringUtil.isLowerCase((char) 3506));
    }
    @Test
    public void isLow340(){
        assertTrue(!StringUtil.isLowerCase((char) 3516));
    }
    @Test
    public void isLow341(){
        assertTrue(!StringUtil.isLowerCase((char) 3518));
    }
    @Test
    public void isLow342(){
        assertTrue(!StringUtil.isLowerCase((char) 3519));
    }
    @Test
    public void isLow343(){
        assertTrue(!StringUtil.isLowerCase((char) 3527));
    }
    @Test
    public void isLow344(){
        assertTrue(!StringUtil.isLowerCase((char) 3529));
    }
    @Test
    public void isLow345(){
        assertTrue(!StringUtil.isLowerCase((char) 3531));
    }
    @Test
    public void isLow346(){
        assertTrue(!StringUtil.isLowerCase((char) 3534));
    }
    @Test
    public void isLow347(){
        assertTrue(!StringUtil.isLowerCase((char) 3541));
    }
    @Test
    public void isLow348(){
        assertTrue(!StringUtil.isLowerCase((char) 3543));
    }
    @Test
    public void isLow349(){
        assertTrue(!StringUtil.isLowerCase((char) 3552));
    }
    @Test
    public void isLow350(){
        assertTrue(!StringUtil.isLowerCase((char) 3569));
    }
    @Test
    public void isLow351(){
        assertTrue(!StringUtil.isLowerCase((char) 3573));
    }
    @Test
    public void isLow352(){
        assertTrue(!StringUtil.isLowerCase((char) 3584));
    }
    @Test
    public void isLow353(){
        assertTrue(!StringUtil.isLowerCase((char) 3643));
    }
    @Test
    public void isLow354(){
        assertTrue(!StringUtil.isLowerCase((char) 3646));
    }
    @Test
    public void isLow355(){
        assertTrue(!StringUtil.isLowerCase((char) 3664));
    }
    @Test
    public void isLow356(){
        assertTrue(!StringUtil.isLowerCase((char) 3673));
    }
    @Test
    public void isLow357(){
        assertTrue(!StringUtil.isLowerCase((char) 3676));
    }
    @Test
    public void isLow358(){
        assertTrue(!StringUtil.isLowerCase((char) 3712));
    }
    @Test
    public void isLow359(){
        assertTrue(!StringUtil.isLowerCase((char) 3715));
    }
    @Test
    public void isLow360(){
        assertTrue(!StringUtil.isLowerCase((char) 3717));
    }
    @Test
    public void isLow361(){
        assertTrue(!StringUtil.isLowerCase((char) 3718));
    }
    @Test
    public void isLow362(){
        assertTrue(!StringUtil.isLowerCase((char) 3721));
    }
    @Test
    public void isLow363(){
        assertTrue(!StringUtil.isLowerCase((char) 3723));
    }
    @Test
    public void isLow364(){
        assertTrue(!StringUtil.isLowerCase((char) 3724));
    }
    @Test
    public void isLow365(){
        assertTrue(!StringUtil.isLowerCase((char) 3726));
    }
    @Test
    public void isLow366(){
        assertTrue(!StringUtil.isLowerCase((char) 3731));
    }
    @Test
    public void isLow367(){
        assertTrue(!StringUtil.isLowerCase((char) 3736));
    }
    @Test
    public void isLow368(){
        assertTrue(!StringUtil.isLowerCase((char) 3744));
    }
    @Test
    public void isLow369(){
        assertTrue(!StringUtil.isLowerCase((char) 3748));
    }
    @Test
    public void isLow370(){
        assertTrue(!StringUtil.isLowerCase((char) 3750));
    }
    @Test
    public void isLow371(){
        assertTrue(!StringUtil.isLowerCase((char) 3752));
    }
    @Test
    public void isLow372(){
        assertTrue(!StringUtil.isLowerCase((char) 3753));
    }
    @Test
    public void isLow373(){
        assertTrue(!StringUtil.isLowerCase((char) 3756));
    }
    @Test
    public void isLow374(){
        assertTrue(!StringUtil.isLowerCase((char) 3770));
    }
    @Test
    public void isLow375(){
        assertTrue(!StringUtil.isLowerCase((char) 3774));
    }
    @Test
    public void isLow376(){
        assertTrue(!StringUtil.isLowerCase((char) 3775));
    }
    @Test
    public void isLow377(){
        assertTrue(!StringUtil.isLowerCase((char) 3781));
    }
    @Test
    public void isLow378(){
        assertTrue(!StringUtil.isLowerCase((char) 3783));
    }
    @Test
    public void isLow379(){
        assertTrue(!StringUtil.isLowerCase((char) 3790));
    }
    @Test
    public void isLow380(){
        assertTrue(!StringUtil.isLowerCase((char) 3803));
    }
    @Test
    public void isLow381(){
        assertTrue(!StringUtil.isLowerCase((char) 3808));
    }
    @Test
    public void isLow382(){
        assertTrue(!StringUtil.isLowerCase((char) 3839));
    }
    @Test
    public void isLow383(){
        assertTrue(!StringUtil.isLowerCase((char) 3872));
    }
    @Test
    public void isLow384(){
        assertTrue(!StringUtil.isLowerCase((char) 3881));
    }
    @Test
    public void isLow385(){
        assertTrue(!StringUtil.isLowerCase((char) 3912));
    }
    @Test
    public void isLow386(){
        assertTrue(!StringUtil.isLowerCase((char) 3949));
    }
    @Test
    public void isLow387(){
        assertTrue(!StringUtil.isLowerCase((char) 3952));
    }
    @Test
    public void isLow388(){
        assertTrue(!StringUtil.isLowerCase((char) 3992));
    }
    @Test
    public void isLow389(){
        assertTrue(!StringUtil.isLowerCase((char) 4029));
    }
    @Test
    public void isLow390(){
        assertTrue(!StringUtil.isLowerCase((char) 4045));
    }
    @Test
    public void isLow391(){
        assertTrue(!StringUtil.isLowerCase((char) 4059));
    }
    @Test
    public void isLow392(){
        assertTrue(!StringUtil.isLowerCase((char) 4095));
    }
    @Test
    public void isLow393(){
        assertTrue(!StringUtil.isLowerCase((char) 4160));
    }
    @Test
    public void isLow394(){
        assertTrue(!StringUtil.isLowerCase((char) 4169));
    }
    @Test
    public void isLow395(){
        assertTrue(!StringUtil.isLowerCase((char) 4240));
    }
    @Test
    public void isLow396(){
        assertTrue(!StringUtil.isLowerCase((char) 4249));
    }
    @Test
    public void isLow397(){
        assertTrue(!StringUtil.isLowerCase((char) 4294));
    }
    @Test
    public void isLow398(){
        assertTrue(!StringUtil.isLowerCase((char) 4296));
    }
    @Test
    public void isLow399(){
        assertTrue(!StringUtil.isLowerCase((char) 4300));
    }
    @Test
    public void isLow400(){
        assertTrue(!StringUtil.isLowerCase((char) 4302));
    }
    @Test
    public void isLow401(){
        assertTrue(!StringUtil.isLowerCase((char) 4303));
    }
    @Test
    public void isLow402(){
        assertTrue(!StringUtil.isLowerCase((char) 4681));
    }
    @Test
    public void isLow403(){
        assertTrue(!StringUtil.isLowerCase((char) 4686));
    }
    @Test
    public void isLow404(){
        assertTrue(!StringUtil.isLowerCase((char) 4687));
    }
    @Test
    public void isLow405(){
        assertTrue(!StringUtil.isLowerCase((char) 4695));
    }
    @Test
    public void isLow406(){
        assertTrue(!StringUtil.isLowerCase((char) 4697));
    }
    @Test
    public void isLow407(){
        assertTrue(!StringUtil.isLowerCase((char) 4702));
    }
    @Test
    public void isLow408(){
        assertTrue(!StringUtil.isLowerCase((char) 4703));
    }
    @Test
    public void isLow409(){
        assertTrue(!StringUtil.isLowerCase((char) 4745));
    }
    @Test
    public void isLow410(){
        assertTrue(!StringUtil.isLowerCase((char) 4750));
    }
    @Test
    public void isLow411(){
        assertTrue(!StringUtil.isLowerCase((char) 4751));
    }
    @Test
    public void isLow412(){
        assertTrue(!StringUtil.isLowerCase((char) 4785));
    }
    @Test
    public void isLow413(){
        assertTrue(!StringUtil.isLowerCase((char) 4790));
    }
    @Test
    public void isLow414(){
        assertTrue(!StringUtil.isLowerCase((char) 4791));
    }
    @Test
    public void isLow415(){
        assertTrue(!StringUtil.isLowerCase((char) 4799));
    }
    @Test
    public void isLow416(){
        assertTrue(!StringUtil.isLowerCase((char) 4801));
    }
    @Test
    public void isLow417(){
        assertTrue(!StringUtil.isLowerCase((char) 4806));
    }
    @Test
    public void isLow418(){
        assertTrue(!StringUtil.isLowerCase((char) 4807));
    }
    @Test
    public void isLow419(){
        assertTrue(!StringUtil.isLowerCase((char) 4823));
    }
    @Test
    public void isLow420(){
        assertTrue(!StringUtil.isLowerCase((char) 4881));
    }
    @Test
    public void isLow421(){
        assertTrue(!StringUtil.isLowerCase((char) 4886));
    }
    @Test
    public void isLow422(){
        assertTrue(!StringUtil.isLowerCase((char) 4887));
    }
    @Test
    public void isLow423(){
        assertTrue(!StringUtil.isLowerCase((char) 4955));
    }
    @Test
    public void isLow424(){
        assertTrue(!StringUtil.isLowerCase((char) 4956));
    }
    @Test
    public void isLow425(){
        assertTrue(!StringUtil.isLowerCase((char) 4989));
    }
    @Test
    public void isLow426(){
        assertTrue(!StringUtil.isLowerCase((char) 4991));
    }
    @Test
    public void isLow427(){
        assertTrue(!StringUtil.isLowerCase((char) 5018));
    }
    @Test
    public void isLow428(){
        assertTrue(!StringUtil.isLowerCase((char) 5023));
    }
    @Test
    public void isLow429(){
        assertTrue(!StringUtil.isLowerCase((char) 5109));
    }
    @Test
    public void isLow430(){
        assertTrue(!StringUtil.isLowerCase((char) 5119));
    }
    @Test
    public void isLow431(){
        assertTrue(!StringUtil.isLowerCase((char) 5760));
    }
    @Test
    public void isLow432(){
        assertTrue(!StringUtil.isLowerCase((char) 5789));
    }
    @Test
    public void isLow433(){
        assertTrue(!StringUtil.isLowerCase((char) 5791));
    }
    @Test
    public void isLow434(){
        assertTrue(!StringUtil.isLowerCase((char) 5873));
    }
    @Test
    public void isLow435(){
        assertTrue(!StringUtil.isLowerCase((char) 5887));
    }
    @Test
    public void isLow436(){
        assertTrue(!StringUtil.isLowerCase((char) 5901));
    }
    @Test
    public void isLow437(){
        assertTrue(!StringUtil.isLowerCase((char) 5909));
    }
    @Test
    public void isLow438(){
        assertTrue(!StringUtil.isLowerCase((char) 5919));
    }
    @Test
    public void isLow439(){
        assertTrue(!StringUtil.isLowerCase((char) 5943));
    }
    @Test
    public void isLow440(){
        assertTrue(!StringUtil.isLowerCase((char) 5951));
    }
    @Test
    public void isLow441(){
        assertTrue(!StringUtil.isLowerCase((char) 5972));
    }
    @Test
    public void isLow442(){
        assertTrue(!StringUtil.isLowerCase((char) 5983));
    }
    @Test
    public void isLow443(){
        assertTrue(!StringUtil.isLowerCase((char) 5997));
    }
    @Test
    public void isLow444(){
        assertTrue(!StringUtil.isLowerCase((char) 6001));
    }
    @Test
    public void isLow445(){
        assertTrue(!StringUtil.isLowerCase((char) 6004));
    }
    @Test
    public void isLow446(){
        assertTrue(!StringUtil.isLowerCase((char) 6015));
    }
    @Test
    public void isLow447(){
        assertTrue(!StringUtil.isLowerCase((char) 6110));
    }
    @Test
    public void isLow448(){
        assertTrue(!StringUtil.isLowerCase((char) 6127));
    }
    @Test
    public void isLow449(){
        assertTrue(!StringUtil.isLowerCase((char) 6138));
    }
    @Test
    public void isLow450(){
        assertTrue(!StringUtil.isLowerCase((char) 6143));
    }
    @Test
    public void isLow451(){
        assertTrue(!StringUtil.isLowerCase((char) 6158));
    }
    @Test
    public void isLow452(){
        assertTrue(!StringUtil.isLowerCase((char) 6175));
    }
    @Test
    public void isLow453(){
        assertTrue(!StringUtil.isLowerCase((char) 6264));
    }
    @Test
    public void isLow454(){
        assertTrue(!StringUtil.isLowerCase((char) 6271));
    }
    @Test
    public void isLow455(){
        assertTrue(!StringUtil.isLowerCase((char) 6315));
    }
    @Test
    public void isLow456(){
        assertTrue(!StringUtil.isLowerCase((char) 6319));
    }
    @Test
    public void isLow457(){
        assertTrue(!StringUtil.isLowerCase((char) 6390));
    }
    @Test
    public void isLow458(){
        assertTrue(!StringUtil.isLowerCase((char) 6399));
    }
    @Test
    public void isLow459(){
        assertTrue(!StringUtil.isLowerCase((char) 6429));
    }
    @Test
    public void isLow460(){
        assertTrue(!StringUtil.isLowerCase((char) 6431));
    }
    @Test
    public void isLow461(){
        assertTrue(!StringUtil.isLowerCase((char) 6444));
    }
    @Test
    public void isLow462(){
        assertTrue(!StringUtil.isLowerCase((char) 6447));
    }
    @Test
    public void isLow463(){
        assertTrue(!StringUtil.isLowerCase((char) 6460));
    }
    @Test
    public void isLow464(){
        assertTrue(!StringUtil.isLowerCase((char) 6463));
    }
    @Test
    public void isLow465(){
        assertTrue(!StringUtil.isLowerCase((char) 6465));
    }
    @Test
    public void isLow466(){
        assertTrue(!StringUtil.isLowerCase((char) 6467));
    }
    @Test
    public void isLow467(){
        assertTrue(!StringUtil.isLowerCase((char) 6470));
    }
    @Test
    public void isLow468(){
        assertTrue(!StringUtil.isLowerCase((char) 6479));
    }
    @Test
    public void isLow469(){
        assertTrue(!StringUtil.isLowerCase((char) 6510));
    }
    @Test
    public void isLow470(){
        assertTrue(!StringUtil.isLowerCase((char) 6511));
    }
    @Test
    public void isLow471(){
        assertTrue(!StringUtil.isLowerCase((char) 6517));
    }
    @Test
    public void isLow472(){
        assertTrue(!StringUtil.isLowerCase((char) 6527));
    }
    @Test
    public void isLow473(){
        assertTrue(!StringUtil.isLowerCase((char) 6572));
    }
    @Test
    public void isLow474(){
        assertTrue(!StringUtil.isLowerCase((char) 6575));
    }
    @Test
    public void isLow475(){
        assertTrue(!StringUtil.isLowerCase((char) 6602));
    }
    @Test
    public void isLow476(){
        assertTrue(!StringUtil.isLowerCase((char) 6617));
    }
    @Test
    public void isLow477(){
        assertTrue(!StringUtil.isLowerCase((char) 6619));
    }
    @Test
    public void isLow478(){
        assertTrue(!StringUtil.isLowerCase((char) 6621));
    }
    @Test
    public void isLow479(){
        assertTrue(!StringUtil.isLowerCase((char) 6684));
    }
    @Test
    public void isLow480(){
        assertTrue(!StringUtil.isLowerCase((char) 6685));
    }
    @Test
    public void isLow481(){
        assertTrue(!StringUtil.isLowerCase((char) 6751));
    }
    @Test
    public void isLow482(){
        assertTrue(!StringUtil.isLowerCase((char) 6781));
    }
    @Test
    public void isLow483(){
        assertTrue(!StringUtil.isLowerCase((char) 6782));
    }
    @Test
    public void isLow484(){
        assertTrue(!StringUtil.isLowerCase((char) 6784));
    }
    @Test
    public void isLow485(){
        assertTrue(!StringUtil.isLowerCase((char) 6815));
    }
    @Test
    public void isLow486(){
        assertTrue(!StringUtil.isLowerCase((char) 6830));
    }
    @Test
    public void isLow487(){
        assertTrue(!StringUtil.isLowerCase((char) 6911));
    }
    @Test
    public void isLow488(){
        assertTrue(!StringUtil.isLowerCase((char) 6988));
    }
    @Test
    public void isLow489(){
        assertTrue(!StringUtil.isLowerCase((char) 7001));
    }
    @Test
    public void isLow490(){
        assertTrue(!StringUtil.isLowerCase((char) 7037));
    }
    @Test
    public void isLow491(){
        assertTrue(!StringUtil.isLowerCase((char) 7039));
    }
    @Test
    public void isLow492(){
        assertTrue(!StringUtil.isLowerCase((char) 7088));
    }
    @Test
    public void isLow493(){
        assertTrue(!StringUtil.isLowerCase((char) 7097));
    }
    @Test
    public void isLow494(){
        assertTrue(!StringUtil.isLowerCase((char) 7156));
    }
    @Test
    public void isLow495(){
        assertTrue(!StringUtil.isLowerCase((char) 7163));
    }
    @Test
    public void isLow496(){
        assertTrue(!StringUtil.isLowerCase((char) 7224));
    }
    @Test
    public void isLow497(){
        assertTrue(!StringUtil.isLowerCase((char) 7226));
    }
    @Test
    public void isLow498(){
        assertTrue(!StringUtil.isLowerCase((char) 7232));
    }
    @Test
    public void isLow499(){
        assertTrue(!StringUtil.isLowerCase((char) 7244));
    }
    @Test
    public void isLow500(){
        assertTrue(!StringUtil.isLowerCase((char) 7248));
    }
    @Test
    public void isLow501(){
        assertTrue(!StringUtil.isLowerCase((char) 7257));
    }
    @Test
    public void isLow502(){
        assertTrue(!StringUtil.isLowerCase((char) 7296));
    }
    @Test
    public void isLow503(){
        assertTrue(!StringUtil.isLowerCase((char) 7359));
    }
    @Test
    public void isLow504(){
        assertTrue(!StringUtil.isLowerCase((char) 7368));
    }
    @Test
    public void isLow505(){
        assertTrue(!StringUtil.isLowerCase((char) 7375));
    }
    @Test
    public void isLow506(){
        assertTrue(!StringUtil.isLowerCase((char) 7415));
    }
    @Test
    public void isLow507(){
        assertTrue(StringUtil.isLowerCase((char) 7615));
    }
    @Test
    public void isLow508(){
        assertTrue(!StringUtil.isLowerCase((char) 7655));
    }
    @Test
    public void isLow509(){
        assertTrue(!StringUtil.isLowerCase((char) 7675));
    }
    @Test
    public void isLow510(){
        assertTrue(StringUtil.isLowerCase((char) 7830));
    }
    @Test
    public void isLow511(){
        assertTrue(StringUtil.isLowerCase((char) 7834));
    }
    @Test
    public void isLow512(){
        assertTrue(StringUtil.isLowerCase((char) 7836));
    }
    @Test
    public void isLow513(){
        assertTrue(StringUtil.isLowerCase((char) 7837));
    }
    @Test
    public void isLow514(){
        assertTrue(StringUtil.isLowerCase((char) 7839));
    }
    @Test
    public void isLow515(){
        assertTrue(!StringUtil.isLowerCase((char) 7958));
    }
    @Test
    public void isLow516(){
        assertTrue(!StringUtil.isLowerCase((char) 7959));
    }
    @Test
    public void isLow517(){
        assertTrue(!StringUtil.isLowerCase((char) 7966));
    }
    @Test
    public void isLow518(){
        assertTrue(!StringUtil.isLowerCase((char) 7967));
    }
    @Test
    public void isLow519(){
        assertTrue(!StringUtil.isLowerCase((char) 8006));
    }
    @Test
    public void isLow520(){
        assertTrue(!StringUtil.isLowerCase((char) 8007));
    }
    @Test
    public void isLow521(){
        assertTrue(!StringUtil.isLowerCase((char) 8014));
    }
    @Test
    public void isLow522(){
        assertTrue(StringUtil.isLowerCase((char) 8016));
    }
    @Test
    public void isLow523(){
        assertTrue(StringUtil.isLowerCase((char) 8018));
    }
    @Test
    public void isLow524(){
        assertTrue(StringUtil.isLowerCase((char) 8020));
    }
    @Test
    public void isLow525(){
        assertTrue(StringUtil.isLowerCase((char) 8022));
    }
    @Test
    public void isLow526(){
        assertTrue(!StringUtil.isLowerCase((char) 8024));
    }
    @Test
    public void isLow527(){
        assertTrue(!StringUtil.isLowerCase((char) 8026));
    }
    @Test
    public void isLow528(){
        assertTrue(!StringUtil.isLowerCase((char) 8028));
    }
    @Test
    public void isLow529(){
        assertTrue(!StringUtil.isLowerCase((char) 8030));
    }
    @Test
    public void isLow530(){
        assertTrue(!StringUtil.isLowerCase((char) 8062));
    }
    @Test
    public void isLow531(){
        assertTrue(!StringUtil.isLowerCase((char) 8063));
    }
    @Test
    public void isLow532(){
        assertTrue(StringUtil.isLowerCase((char) 8114));
    }
    @Test
    public void isLow533(){
        assertTrue(StringUtil.isLowerCase((char) 8116));
    }
    @Test
    public void isLow534(){
        assertTrue(StringUtil.isLowerCase((char) 8119));
    }
    @Test
    public void isLow535(){
        assertTrue(StringUtil.isLowerCase((char) 8130));
    }
    @Test
    public void isLow536(){
        assertTrue(StringUtil.isLowerCase((char) 8132));
    }
    @Test
    public void isLow537(){
        assertTrue(StringUtil.isLowerCase((char) 8135));
    }
    @Test
    public void isLow538(){
        assertTrue(StringUtil.isLowerCase((char) 8146));
    }
    @Test
    public void isLow539(){
        assertTrue(StringUtil.isLowerCase((char) 8151));
    }
    @Test
    public void isLow540(){
        assertTrue(!StringUtil.isLowerCase((char) 8156));
    }
    @Test
    public void isLow541(){
        assertTrue(StringUtil.isLowerCase((char) 8162));
    }
    @Test
    public void isLow542(){
        assertTrue(StringUtil.isLowerCase((char) 8164));
    }
    @Test
    public void isLow543(){
        assertTrue(StringUtil.isLowerCase((char) 8166));
    }
    @Test
    public void isLow544(){
        assertTrue(StringUtil.isLowerCase((char) 8167));
    }
    @Test
    public void isLow545(){
        assertTrue(!StringUtil.isLowerCase((char) 8176));
    }
    @Test
    public void isLow546(){
        assertTrue(StringUtil.isLowerCase((char) 8178));
    }
    @Test
    public void isLow547(){
        assertTrue(StringUtil.isLowerCase((char) 8180));
    }
    @Test
    public void isLow548(){
        assertTrue(StringUtil.isLowerCase((char) 8183));
    }
    @Test
    public void isLow549(){
        assertTrue(!StringUtil.isLowerCase((char) 8191));
    }
    @Test
    public void isLow550(){
        assertTrue(!StringUtil.isLowerCase((char) 8202));
    }
    @Test
    public void isLow551(){
        assertTrue(!StringUtil.isLowerCase((char) 8239));
    }
    @Test
    public void isLow552(){
        assertTrue(!StringUtil.isLowerCase((char) 8287));
    }
    @Test
    public void isLow553(){
        assertTrue(!StringUtil.isLowerCase((char) 8293));
    }
    @Test
    public void isLow554(){
        assertTrue(!StringUtil.isLowerCase((char) 8297));
    }
    @Test
    public void isLow555(){
        assertTrue(StringUtil.isLowerCase((char) 8305));
    }
    @Test
    public void isLow556(){
        assertTrue(!StringUtil.isLowerCase((char) 8307));
    }
    @Test
    public void isLow557(){
        assertTrue(StringUtil.isLowerCase((char) 8319));
    }
    @Test
    public void isLow558(){
        assertTrue(!StringUtil.isLowerCase((char) 8335));
    }
    @Test
    public void isLow559(){
        assertTrue(!StringUtil.isLowerCase((char) 8351));
    }
    @Test
    public void isLow560(){
        assertTrue(!StringUtil.isLowerCase((char) 8379));
    }
    @Test
    public void isLow561(){
        assertTrue(!StringUtil.isLowerCase((char) 8399));
    }
    @Test
    public void isLow562(){
        assertTrue(!StringUtil.isLowerCase((char) 8433));
    }
    @Test
    public void isLow563(){
        assertTrue(!StringUtil.isLowerCase((char) 8447));
    }
    @Test
    public void isLow564(){
        assertTrue(StringUtil.isLowerCase((char) 8458));
    }
    @Test
    public void isLow565(){
        assertTrue(StringUtil.isLowerCase((char) 8462));
    }
    @Test
    public void isLow566(){
        assertTrue(StringUtil.isLowerCase((char) 8463));
    }
    @Test
    public void isLow567(){
        assertTrue(StringUtil.isLowerCase((char) 8467));
    }
    @Test
    public void isLow568(){
        assertTrue(StringUtil.isLowerCase((char) 8495));
    }
    @Test
    public void isLow569(){
        assertTrue(StringUtil.isLowerCase((char) 8500));
    }
    @Test
    public void isLow570(){
        assertTrue(StringUtil.isLowerCase((char) 8505));
    }
    @Test
    public void isLow571(){
        assertTrue(StringUtil.isLowerCase((char) 8508));
    }
    @Test
    public void isLow572(){
        assertTrue(StringUtil.isLowerCase((char) 8509));
    }
    @Test
    public void isLow573(){
        assertTrue(StringUtil.isLowerCase((char) 8518));
    }
    @Test
    public void isLow574(){
        assertTrue(StringUtil.isLowerCase((char) 8521));
    }
    @Test
    public void isLow575(){
        assertTrue(!StringUtil.isLowerCase((char) 8586));
    }
    @Test
    public void isLow576(){
        assertTrue(!StringUtil.isLowerCase((char) 8591));
    }
    @Test
    public void isLow577(){
        assertTrue(!StringUtil.isLowerCase((char) 9204));
    }
    @Test
    public void isLow578(){
        assertTrue(!StringUtil.isLowerCase((char) 9215));
    }
    @Test
    public void isLow579(){
        assertTrue(!StringUtil.isLowerCase((char) 9255));
    }
    @Test
    public void isLow580(){
        assertTrue(!StringUtil.isLowerCase((char) 9279));
    }
    @Test
    public void isLow581(){
        assertTrue(!StringUtil.isLowerCase((char) 9291));
    }
    @Test
    public void isLow582(){
        assertTrue(!StringUtil.isLowerCase((char) 9311));
    }
    @Test
    public void isLow583(){
        assertTrue(!StringUtil.isLowerCase((char) 9984));
    }
    @Test
    public void isLow584(){
        assertTrue(!StringUtil.isLowerCase((char) 11085));
    }
    @Test
    public void isLow585(){
        assertTrue(!StringUtil.isLowerCase((char) 11087));
    }
    @Test
    public void isLow586(){
        assertTrue(!StringUtil.isLowerCase((char) 11098));
    }
    @Test
    public void isLow587(){
        assertTrue(!StringUtil.isLowerCase((char) 11263));
    }
    @Test
    public void isLow588(){
        assertTrue(!StringUtil.isLowerCase((char) 11311));
    }
    @Test
    public void isLow589(){
        assertTrue(!StringUtil.isLowerCase((char) 11359));
    }
    @Test
    public void isLow590(){
        assertTrue(StringUtil.isLowerCase((char) 11377));
    }
    @Test
    public void isLow591(){
        assertTrue(StringUtil.isLowerCase((char) 11380));
    }
    @Test
    public void isLow592(){
        assertTrue(StringUtil.isLowerCase((char) 11383));
    }
    @Test
    public void isLow593(){
        assertTrue(StringUtil.isLowerCase((char) 11389));
    }
    @Test
    public void isLow594(){
        assertTrue(StringUtil.isLowerCase((char) 11492));
    }
    @Test
    public void isLow595(){
        assertTrue(!StringUtil.isLowerCase((char) 11508));
    }
    @Test
    public void isLow596(){
        assertTrue(!StringUtil.isLowerCase((char) 11512));
    }
    @Test
    public void isLow597(){
        assertTrue(!StringUtil.isLowerCase((char) 11558));
    }
    @Test
    public void isLow598(){
        assertTrue(!StringUtil.isLowerCase((char) 11560));
    }
    @Test
    public void isLow599(){
        assertTrue(!StringUtil.isLowerCase((char) 11564));
    }
    @Test
    public void isLow600(){
        assertTrue(!StringUtil.isLowerCase((char) 11566));
    }
    @Test
    public void isLow601(){
        assertTrue(!StringUtil.isLowerCase((char) 11567));
    }
    @Test
    public void isLow602(){
        assertTrue(!StringUtil.isLowerCase((char) 11624));
    }
    @Test
    public void isLow603(){
        assertTrue(!StringUtil.isLowerCase((char) 11630));
    }
    @Test
    public void isLow604(){
        assertTrue(!StringUtil.isLowerCase((char) 11633));
    }
    @Test
    public void isLow605(){
        assertTrue(!StringUtil.isLowerCase((char) 11646));
    }
    @Test
    public void isLow606(){
        assertTrue(!StringUtil.isLowerCase((char) 11671));
    }
    @Test
    public void isLow607(){
        assertTrue(!StringUtil.isLowerCase((char) 11679));
    }
    @Test
    public void isLow608(){
        assertTrue(!StringUtil.isLowerCase((char) 11687));
    }
    @Test
    public void isLow609(){
        assertTrue(!StringUtil.isLowerCase((char) 11695));
    }
    @Test
    public void isLow610(){
        assertTrue(!StringUtil.isLowerCase((char) 11703));
    }
    @Test
    public void isLow611(){
        assertTrue(!StringUtil.isLowerCase((char) 11711));
    }
    @Test
    public void isLow612(){
        assertTrue(!StringUtil.isLowerCase((char) 11719));
    }
    @Test
    public void isLow613(){
        assertTrue(!StringUtil.isLowerCase((char) 11727));
    }
    @Test
    public void isLow614(){
        assertTrue(!StringUtil.isLowerCase((char) 11735));
    }
    @Test
    public void isLow615(){
        assertTrue(!StringUtil.isLowerCase((char) 11743));
    }
    @Test
    public void isLow616(){
        assertTrue(!StringUtil.isLowerCase((char) 11836));
    }
    @Test
    public void isLow617(){
        assertTrue(!StringUtil.isLowerCase((char) 11903));
    }
    @Test
    public void isLow618(){
        assertTrue(!StringUtil.isLowerCase((char) 11930));
    }
    @Test
    public void isLow619(){
        assertTrue(!StringUtil.isLowerCase((char) 12020));
    }
    @Test
    public void isLow620(){
        assertTrue(!StringUtil.isLowerCase((char) 12031));
    }
    @Test
    public void isLow621(){
        assertTrue(!StringUtil.isLowerCase((char) 12246));
    }
    @Test
    public void isLow622(){
        assertTrue(!StringUtil.isLowerCase((char) 12271));
    }
    @Test
    public void isLow623(){
        assertTrue(!StringUtil.isLowerCase((char) 12284));
    }
    @Test
    public void isLow624(){
        assertTrue(!StringUtil.isLowerCase((char) 12288));
    }
    @Test
    public void isLow625(){
        assertTrue(!StringUtil.isLowerCase((char) 12352));
    }
    @Test
    public void isLow626(){
        assertTrue(!StringUtil.isLowerCase((char) 12439));
    }
    @Test
    public void isLow627(){
        assertTrue(!StringUtil.isLowerCase((char) 12440));
    }
    @Test
    public void isLow628(){
        assertTrue(!StringUtil.isLowerCase((char) 12544));
    }
    @Test
    public void isLow629(){
        assertTrue(!StringUtil.isLowerCase((char) 12548));
    }
    @Test
    public void isLow630(){
        assertTrue(!StringUtil.isLowerCase((char) 12590));
    }
    @Test
    public void isLow631(){
        assertTrue(!StringUtil.isLowerCase((char) 12592));
    }
    @Test
    public void isLow632(){
        assertTrue(!StringUtil.isLowerCase((char) 12687));
    }
    @Test
    public void isLow633(){
        assertTrue(!StringUtil.isLowerCase((char) 12731));
    }
    @Test
    public void isLow634(){
        assertTrue(!StringUtil.isLowerCase((char) 12735));
    }
    @Test
    public void isLow635(){
        assertTrue(!StringUtil.isLowerCase((char) 12772));
    }
    @Test
    public void isLow636(){
        assertTrue(!StringUtil.isLowerCase((char) 12783));
    }
    @Test
    public void isLow637(){
        assertTrue(!StringUtil.isLowerCase((char) 12831));
    }
    @Test
    public void isLow638(){
        assertTrue(!StringUtil.isLowerCase((char) 13055));
    }
    @Test
    public void isLow639(){
        assertTrue(!StringUtil.isLowerCase((char) 19894));
    }
    @Test
    public void isLow640(){
        assertTrue(!StringUtil.isLowerCase((char) 19903));
    }
    @Test
    public void isLow641(){
        assertTrue(!StringUtil.isLowerCase((char) 40909));
    }
    @Test
    public void isLow642(){
        assertTrue(!StringUtil.isLowerCase((char) 40959));
    }
    @Test
    public void isLow643(){
        assertTrue(!StringUtil.isLowerCase((char) 42125));
    }
    @Test
    public void isLow644(){
        assertTrue(!StringUtil.isLowerCase((char) 42127));
    }
    @Test
    public void isLow645(){
        assertTrue(!StringUtil.isLowerCase((char) 42183));
    }
    @Test
    public void isLow646(){
        assertTrue(!StringUtil.isLowerCase((char) 42191));
    }
    @Test
    public void isLow647(){
        assertTrue(!StringUtil.isLowerCase((char) 42528));
    }
    @Test
    public void isLow648(){
        assertTrue(!StringUtil.isLowerCase((char) 42537));
    }
    @Test
    public void isLow649(){
        assertTrue(!StringUtil.isLowerCase((char) 42540));
    }
    @Test
    public void isLow650(){
        assertTrue(!StringUtil.isLowerCase((char) 42559));
    }
    @Test
    public void isLow651(){
        assertTrue(!StringUtil.isLowerCase((char) 42648));
    }
    @Test
    public void isLow652(){
        assertTrue(!StringUtil.isLowerCase((char) 42654));
    }
    @Test
    public void isLow653(){
        assertTrue(!StringUtil.isLowerCase((char) 42744));
    }
    @Test
    public void isLow654(){
        assertTrue(!StringUtil.isLowerCase((char) 42751));
    }
    @Test
    public void isLow655(){
        assertTrue(StringUtil.isLowerCase((char) 42800));
    }
    @Test
    public void isLow656(){
        assertTrue(StringUtil.isLowerCase((char) 42801));
    }
    @Test
    public void isLow657(){
        assertTrue(StringUtil.isLowerCase((char) 42864));
    }
    @Test
    public void isLow658(){
        assertTrue(StringUtil.isLowerCase((char) 42872));
    }
    @Test
    public void isLow659(){
        assertTrue(StringUtil.isLowerCase((char) 42894));
    }
    @Test
    public void isLow660(){
        assertTrue(!StringUtil.isLowerCase((char) 42895));
    }
    @Test
    public void isLow661(){
        assertTrue(!StringUtil.isLowerCase((char) 42900));
    }
    @Test
    public void isLow662(){
        assertTrue(!StringUtil.isLowerCase((char) 42911));
    }
    @Test
    public void isLow663(){
        assertTrue(!StringUtil.isLowerCase((char) 42923));
    }
    @Test
    public void isLow664(){
        assertTrue(StringUtil.isLowerCase((char) 43002));
    }
    @Test
    public void isLow665(){
        assertTrue(!StringUtil.isLowerCase((char) 43052));
    }
    @Test
    public void isLow666(){
        assertTrue(!StringUtil.isLowerCase((char) 43055));
    }
    @Test
    public void isLow667(){
        assertTrue(!StringUtil.isLowerCase((char) 43066));
    }
    @Test
    public void isLow668(){
        assertTrue(!StringUtil.isLowerCase((char) 43071));
    }
    @Test
    public void isLow669(){
        assertTrue(!StringUtil.isLowerCase((char) 43128));
    }
    @Test
    public void isLow670(){
        assertTrue(!StringUtil.isLowerCase((char) 43135));
    }
    @Test
    public void isLow671(){
        assertTrue(!StringUtil.isLowerCase((char) 43205));
    }
    @Test
    public void isLow672(){
        assertTrue(!StringUtil.isLowerCase((char) 43213));
    }
    @Test
    public void isLow673(){
        assertTrue(!StringUtil.isLowerCase((char) 43216));
    }
    @Test
    public void isLow674(){
        assertTrue(!StringUtil.isLowerCase((char) 43231));
    }
    @Test
    public void isLow675(){
        assertTrue(!StringUtil.isLowerCase((char) 43260));
    }
    @Test
    public void isLow676(){
        assertTrue(!StringUtil.isLowerCase((char) 43273));
    }
    @Test
    public void isLow677(){
        assertTrue(!StringUtil.isLowerCase((char) 43348));
    }
    @Test
    public void isLow678(){
        assertTrue(!StringUtil.isLowerCase((char) 43358));
    }
    @Test
    public void isLow679(){
        assertTrue(!StringUtil.isLowerCase((char) 43389));
    }
    @Test
    public void isLow680(){
        assertTrue(!StringUtil.isLowerCase((char) 43391));
    }
    @Test
    public void isLow681(){
        assertTrue(!StringUtil.isLowerCase((char) 43470));
    }
    @Test
    public void isLow682(){
        assertTrue(!StringUtil.isLowerCase((char) 43472));
    }
    @Test
    public void isLow683(){
        assertTrue(!StringUtil.isLowerCase((char) 43485));
    }
    @Test
    public void isLow684(){
        assertTrue(!StringUtil.isLowerCase((char) 43488));
    }
    @Test
    public void isLow685(){
        assertTrue(!StringUtil.isLowerCase((char) 43519));
    }
    @Test
    public void isLow686(){
        assertTrue(!StringUtil.isLowerCase((char) 43575));
    }
    @Test
    public void isLow687(){
        assertTrue(!StringUtil.isLowerCase((char) 43583));
    }
    @Test
    public void isLow688(){
        assertTrue(!StringUtil.isLowerCase((char) 43598));
    }
    @Test
    public void isLow689(){
        assertTrue(!StringUtil.isLowerCase((char) 43611));
    }
    @Test
    public void isLow690(){
        assertTrue(!StringUtil.isLowerCase((char) 43644));
    }
    @Test
    public void isLow691(){
        assertTrue(!StringUtil.isLowerCase((char) 43647));
    }
    @Test
    public void isLow692(){
        assertTrue(!StringUtil.isLowerCase((char) 43715));
    }
    @Test
    public void isLow693(){
        assertTrue(!StringUtil.isLowerCase((char) 43738));
    }
    @Test
    public void isLow694(){
        assertTrue(!StringUtil.isLowerCase((char) 43767));
    }
    @Test
    public void isLow695(){
        assertTrue(!StringUtil.isLowerCase((char) 43776));
    }
    @Test
    public void isLow696(){
        assertTrue(!StringUtil.isLowerCase((char) 43783));
    }
    @Test
    public void isLow697(){
        assertTrue(!StringUtil.isLowerCase((char) 43784));
    }
    @Test
    public void isLow698(){
        assertTrue(!StringUtil.isLowerCase((char) 43791));
    }
    @Test
    public void isLow699(){
        assertTrue(!StringUtil.isLowerCase((char) 43792));
    }
    @Test
    public void isLow700(){
        assertTrue(!StringUtil.isLowerCase((char) 43799));
    }
    @Test
    public void isLow701(){
        assertTrue(!StringUtil.isLowerCase((char) 43807));
    }
    @Test
    public void isLow702(){
        assertTrue(!StringUtil.isLowerCase((char) 43815));
    }
    @Test
    public void isLow703(){
        assertTrue(!StringUtil.isLowerCase((char) 43823));
    }
    @Test
    public void isLow704(){
        assertTrue(!StringUtil.isLowerCase((char) 43967));
    }
    @Test
    public void isLow705(){
        assertTrue(!StringUtil.isLowerCase((char) 44014));
    }
    @Test
    public void isLow706(){
        assertTrue(!StringUtil.isLowerCase((char) 44031));
    }
    @Test
    public void isLow707(){
        assertTrue(!StringUtil.isLowerCase((char) 55204));
    }
    @Test
    public void isLow708(){
        assertTrue(!StringUtil.isLowerCase((char) 55215));
    }
    @Test
    public void isLow709(){
        assertTrue(!StringUtil.isLowerCase((char) 55239));
    }
    @Test
    public void isLow710(){
        assertTrue(!StringUtil.isLowerCase((char) 55242));
    }
    @Test
    public void isLow711(){
        assertTrue(!StringUtil.isLowerCase((char) 55292));
    }
    @Test
    public void isLow712(){
        assertTrue(!StringUtil.isLowerCase((char) 55295));
    }
    @Test
    public void isLow713(){
        assertTrue(!StringUtil.isLowerCase((char) 64110));
    }
    @Test
    public void isLow714(){
        assertTrue(!StringUtil.isLowerCase((char) 64111));
    }
    @Test
    public void isLow715(){
        assertTrue(!StringUtil.isLowerCase((char) 64218));
    }
    @Test
    public void isLow716(){
        assertTrue(!StringUtil.isLowerCase((char) 64284));
    }
    @Test
    public void isLow717(){
        assertTrue(!StringUtil.isLowerCase((char) 64311));
    }
    @Test
    public void isLow718(){
        assertTrue(!StringUtil.isLowerCase((char) 64317));
    }
    @Test
    public void isLow719(){
        assertTrue(!StringUtil.isLowerCase((char) 64319));
    }
    @Test
    public void isLow720(){
        assertTrue(!StringUtil.isLowerCase((char) 64322));
    }
    @Test
    public void isLow721(){
        assertTrue(!StringUtil.isLowerCase((char) 64325));
    }
    @Test
    public void isLow722(){
        assertTrue(!StringUtil.isLowerCase((char) 64450));
    }
    @Test
    public void isLow723(){
        assertTrue(!StringUtil.isLowerCase((char) 64466));
    }
    @Test
    public void isLow724(){
        assertTrue(!StringUtil.isLowerCase((char) 64832));
    }
    @Test
    public void isLow725(){
        assertTrue(!StringUtil.isLowerCase((char) 64847));
    }
    @Test
    public void isLow726(){
        assertTrue(!StringUtil.isLowerCase((char) 64912));
    }
    @Test
    public void isLow727(){
        assertTrue(!StringUtil.isLowerCase((char) 64913));
    }
    @Test
    public void isLow728(){
        assertTrue(!StringUtil.isLowerCase((char) 64968));
    }
    @Test
    public void isLow729(){
        assertTrue(!StringUtil.isLowerCase((char) 65007));
    }
    @Test
    public void isLow730(){
        assertTrue(!StringUtil.isLowerCase((char) 65022));
    }
    @Test
    public void isLow731(){
        assertTrue(!StringUtil.isLowerCase((char) 65023));
    }
    @Test
    public void isLow732(){
        assertTrue(!StringUtil.isLowerCase((char) 65050));
    }
    @Test
    public void isLow733(){
        assertTrue(!StringUtil.isLowerCase((char) 65055));
    }
    @Test
    public void isLow734(){
        assertTrue(!StringUtil.isLowerCase((char) 65063));
    }
    @Test
    public void isLow735(){
        assertTrue(!StringUtil.isLowerCase((char) 65071));
    }
    @Test
    public void isLow736(){
        assertTrue(!StringUtil.isLowerCase((char) 65107));
    }
    @Test
    public void isLow737(){
        assertTrue(!StringUtil.isLowerCase((char) 65127));
    }
    @Test
    public void isLow738(){
        assertTrue(!StringUtil.isLowerCase((char) 65132));
    }
    @Test
    public void isLow739(){
        assertTrue(!StringUtil.isLowerCase((char) 65135));
    }
    @Test
    public void isLow740(){
        assertTrue(!StringUtil.isLowerCase((char) 65141));
    }
    @Test
    public void isLow741(){
        assertTrue(!StringUtil.isLowerCase((char) 65277));
    }
    @Test
    public void isLow742(){
        assertTrue(!StringUtil.isLowerCase((char) 65278));
    }
    @Test
    public void isLow743(){
        assertTrue(!StringUtil.isLowerCase((char) 65280));
    }
    @Test
    public void isLow744(){
        assertTrue(!StringUtil.isLowerCase((char) 65296));
    }
    @Test
    public void isLow745(){
        assertTrue(!StringUtil.isLowerCase((char) 65305));
    }
    @Test
    public void isLow746(){
        assertTrue(!StringUtil.isLowerCase((char) 65471));
    }
    @Test
    public void isLow747(){
        assertTrue(!StringUtil.isLowerCase((char) 65473));
    }
    @Test
    public void isLow748(){
        assertTrue(!StringUtil.isLowerCase((char) 65480));
    }
    @Test
    public void isLow749(){
        assertTrue(!StringUtil.isLowerCase((char) 65481));
    }
    @Test
    public void isLow750(){
        assertTrue(!StringUtil.isLowerCase((char) 65488));
    }
    @Test
    public void isLow751(){
        assertTrue(!StringUtil.isLowerCase((char) 65489));
    }
    @Test
    public void isLow752(){
        assertTrue(!StringUtil.isLowerCase((char) 65496));
    }
    @Test
    public void isLow753(){
        assertTrue(!StringUtil.isLowerCase((char) 65497));
    }
    @Test
    public void isLow754(){
        assertTrue(!StringUtil.isLowerCase((char) 65501));
    }
    @Test
    public void isLow755(){
        assertTrue(!StringUtil.isLowerCase((char) 65503));
    }
    @Test
    public void isLow756(){
        assertTrue(!StringUtil.isLowerCase((char) 65511));
    }
    @Test
    public void isLow757(){
        assertTrue(!StringUtil.isLowerCase((char) 65519));
    }
    @Test
    public void isLow758(){
        assertTrue(!StringUtil.isLowerCase((char) 65528));
    }
    @Test
    public void isLow759(){
        assertTrue(!StringUtil.isLowerCase((char) 65534));
    }
    @Test
    public void isLow760(){
        assertTrue(StringUtil.isLowerCase((char) 8336));
    }
    @Test
    public void isLow761(){
        assertTrue(!StringUtil.isLowerCase((char) 660));
    }

    @Test
    public void toUpper159(){
        assertEq(83,StringUtil.toUpperCase((char) 383));
    }
    @Test
    public void toUpper160(){
        assertEq(391,StringUtil.toUpperCase((char) 392));
    }
    @Test
    public void toUpper161(){
        assertEq(395,StringUtil.toUpperCase((char) 396));
    }
    @Test
    public void toUpper162(){
        assertEq(401,StringUtil.toUpperCase((char) 402));
    }
    @Test
    public void toUpper163(){
        assertEq(408,StringUtil.toUpperCase((char) 409));
    }
    @Test
    public void toUpper164(){
        assertEq(573,StringUtil.toUpperCase((char) 410));
    }
    @Test
    public void toUpper165(){
        assertEq(423,StringUtil.toUpperCase((char) 424));
    }
    @Test
    public void toUpper166(){
        assertEq(428,StringUtil.toUpperCase((char) 429));
    }
    @Test
    public void toUpper167(){
        assertEq(431,StringUtil.toUpperCase((char) 432));
    }
    @Test
    public void toUpper168(){
        assertEq(440,StringUtil.toUpperCase((char) 441));
    }
    @Test
    public void toUpper169(){
        assertEq(444,StringUtil.toUpperCase((char) 445));
    }
    @Test
    public void toUpper170(){
        assertEq(452,StringUtil.toUpperCase((char) 453));
    }
    @Test
    public void toUpper171(){
        assertEq(452,StringUtil.toUpperCase((char) 454));
    }
    @Test
    public void toUpper172(){
        assertEq(455,StringUtil.toUpperCase((char) 456));
    }
    @Test
    public void toUpper173(){
        assertEq(455,StringUtil.toUpperCase((char) 457));
    }
    @Test
    public void toUpper174(){
        assertEq(458,StringUtil.toUpperCase((char) 460));
    }
    @Test
    public void toUpper175(){
        assertEq(398,StringUtil.toUpperCase((char) 477));
    }
    @Test
    public void toUpper176(){
        assertEq(497,StringUtil.toUpperCase((char) 499));
    }
    @Test
    public void toUpper177(){
        assertEq(11375,StringUtil.toUpperCase((char) 592));
    }
    @Test
    public void toUpper178(){
        assertEq(922,StringUtil.toUpperCase((char) 1008));
    }
    @Test
    public void toUpper179(){
        assertEq(1216,StringUtil.toUpperCase((char) 1231));
    }
    @Test
    public void toUpper180(){
        assertEq(7944,StringUtil.toUpperCase((char) 7936));
    }
    @Test
    public void toUpper181(){
        assertEq(48,StringUtil.toUpperCase((char) 48));
    }
    @Test
    public void toUpper182(){
        assertEq(57,StringUtil.toUpperCase((char) 57));
    }
    @Test
    public void toUpper183(){
        assertEq(127,StringUtil.toUpperCase((char) 127));
    }
    @Test
    public void toUpper184(){
        assertEq(160,StringUtil.toUpperCase((char) 160));
    }
    @Test
    public void toUpper185(){
        assertEq(888,StringUtil.toUpperCase((char) 888));
    }
    @Test
    public void toUpper186(){
        assertEq(8544,StringUtil.toUpperCase((char) 8544));
    }
    @Test
    public void toUpper187(){
        assertEq(9398,StringUtil.toUpperCase((char) 9398));
    }
    @Test
    public void toUpper188(){
        assertEq(8602,StringUtil.toUpperCase((char) 8602));
    }
    @Test
    public void toUpper189(){
        assertEq(1632,StringUtil.toUpperCase((char) 1632));
    }
    @Test
    public void toLower158(){
        assertEq(1632,StringUtil.toLowerCase((char) 1632));
    }
    @Test
    public void toLower159(){
        assertEq(888,StringUtil.toLowerCase((char) 888));
    }
    @Test
    public void toLower160(){
        assertEq(160,StringUtil.toLowerCase((char) 160));
    }


    @Test
    public void getType0(){
        assertEq(30,StringUtil.getCustomType((char) 0));
    }
    @Test
    public void getType1(){
        assertEq(29,StringUtil.getCustomType((char) 9));
    }
    @Test
    public void getType2(){
        assertEq(30,StringUtil.getCustomType((char) 11));
    }
    @Test
    public void getType3(){
        assertEq(29,StringUtil.getCustomType((char) 32));
    }
    @Test
    public void getType4(){
        assertEq(17,StringUtil.getCustomType((char) 33));
    }
    @Test
    public void getType5(){
        assertEq(19,StringUtil.getCustomType((char) 34));
    }
    @Test
    public void getType6(){
        assertEq(17,StringUtil.getCustomType((char) 35));
    }
    @Test
    public void getType7(){
        assertEq(12,StringUtil.getCustomType((char) 36));
    }
    @Test
    public void getType8(){
        assertEq(17,StringUtil.getCustomType((char) 37));
    }
    @Test
    public void getType9(){
        assertEq(19,StringUtil.getCustomType((char) 39));
    }
    @Test
    public void getType10(){
        assertEq(15,StringUtil.getCustomType((char) 40));
    }
    @Test
    public void getType11(){
        assertEq(16,StringUtil.getCustomType((char) 41));
    }
    @Test
    public void getType12(){
        assertEq(17,StringUtil.getCustomType((char) 42));
    }
    @Test
    public void getType13(){
        assertEq(18,StringUtil.getCustomType((char) 44));
    }
    @Test
    public void getType14(){
        assertEq(17,StringUtil.getCustomType((char) 45));
    }
    @Test
    public void getType15(){
        assertEq(18,StringUtil.getCustomType((char) 46));
    }
    @Test
    public void getType16(){
        assertEq(17,StringUtil.getCustomType((char) 47));
    }
    @Test
    public void getType17(){
        assertEq(9,StringUtil.getCustomType((char) 48));
    }
    @Test
    public void getType18(){
        assertEq(17,StringUtil.getCustomType((char) 58));
    }
    @Test
    public void getType19(){
        assertEq(18,StringUtil.getCustomType((char) 59));
    }
    @Test
    public void getType20(){
        assertEq(17,StringUtil.getCustomType((char) 60));
    }
    @Test
    public void getType21(){
        assertEq(2,StringUtil.getCustomType((char) 65));
    }
    @Test
    public void getType22(){
        assertEq(15,StringUtil.getCustomType((char) 91));
    }
    @Test
    public void getType23(){
        assertEq(20,StringUtil.getCustomType((char) 92));
    }
    @Test
    public void getType24(){
        assertEq(16,StringUtil.getCustomType((char) 93));
    }
    @Test
    public void getType25(){
        assertEq(17,StringUtil.getCustomType((char) 94));
    }
    @Test
    public void getType26(){
        assertEq(11,StringUtil.getCustomType((char) 95));
    }
    @Test
    public void getType27(){
        assertEq(19,StringUtil.getCustomType((char) 96));
    }
    @Test
    public void getType28(){
        assertEq(1,StringUtil.getCustomType((char) 97));
    }
    @Test
    public void getType29(){
        assertEq(15,StringUtil.getCustomType((char) 123));
    }
    @Test
    public void getType30(){
        assertEq(17,StringUtil.getCustomType((char) 124));
    }
    @Test
    public void getType31(){
        assertEq(16,StringUtil.getCustomType((char) 125));
    }
    @Test
    public void getType32(){
        assertEq(17,StringUtil.getCustomType((char) 126));
    }
    @Test
    public void getType33(){
        assertEq(30,StringUtil.getCustomType((char) 127));
    }
    @Test
    public void getType34(){
        assertEq(28,StringUtil.getCustomType((char) 161));
    }
    @Test
    public void getType35(){
        assertEq(12,StringUtil.getCustomType((char) 162));
    }
    @Test
    public void getType36(){
        assertEq(22,StringUtil.getCustomType((char) 166));
    }
    @Test
    public void getType37(){
        assertEq(28,StringUtil.getCustomType((char) 167));
    }
    @Test
    public void getType38(){
        assertEq(22,StringUtil.getCustomType((char) 168));
    }
    @Test
    public void getType39(){
        assertEq(3,StringUtil.getCustomType((char) 170));
    }
    @Test
    public void getType40(){
        assertEq(27,StringUtil.getCustomType((char) 171));
    }
    @Test
    public void getType41(){
        assertEq(21,StringUtil.getCustomType((char) 172));
    }
    @Test
    public void getType42(){
        assertEq(22,StringUtil.getCustomType((char) 173));
    }
    @Test
    public void getType43(){
        assertEq(21,StringUtil.getCustomType((char) 177));
    }
    @Test
    public void getType44(){
        assertEq(22,StringUtil.getCustomType((char) 178));
    }
    @Test
    public void getType45(){
        assertEq(1,StringUtil.getCustomType((char) 181));
    }
    @Test
    public void getType46(){
        assertEq(28,StringUtil.getCustomType((char) 182));
    }
    @Test
    public void getType47(){
        assertEq(22,StringUtil.getCustomType((char) 184));
    }
    @Test
    public void getType48(){
        assertEq(3,StringUtil.getCustomType((char) 186));
    }
    @Test
    public void getType49(){
        assertEq(27,StringUtil.getCustomType((char) 187));
    }
    @Test
    public void getType50(){
        assertEq(22,StringUtil.getCustomType((char) 188));
    }
    @Test
    public void getType51(){
        assertEq(28,StringUtil.getCustomType((char) 191));
    }
    @Test
    public void getType52(){
        assertEq(2,StringUtil.getCustomType((char) 192));
    }
    @Test
    public void getType53(){
        assertEq(21,StringUtil.getCustomType((char) 215));
    }
    @Test
    public void getType54(){
        assertEq(2,StringUtil.getCustomType((char) 216));
    }
    @Test
    public void getType55(){
        assertEq(3,StringUtil.getCustomType((char) 223));
    }
    @Test
    public void getType56(){
        assertEq(1,StringUtil.getCustomType((char) 224));
    }
    @Test
    public void getType57(){
        assertEq(21,StringUtil.getCustomType((char) 247));
    }
    @Test
    public void getType58(){
        assertEq(1,StringUtil.getCustomType((char) 248));
    }
    @Test
    public void getType59(){
        assertEq(2,StringUtil.getCustomType((char) 256));
    }
    @Test
    public void getType60(){
        assertEq(1,StringUtil.getCustomType((char) 257));
    }
    @Test
    public void getType61(){
        assertEq(2,StringUtil.getCustomType((char) 258));
    }
    @Test
    public void getType62(){
        assertEq(1,StringUtil.getCustomType((char) 259));
    }
    @Test
    public void getType63(){
        assertEq(2,StringUtil.getCustomType((char) 260));
    }
    @Test
    public void getType64(){
        assertEq(1,StringUtil.getCustomType((char) 261));
    }
    @Test
    public void getType65(){
        assertEq(2,StringUtil.getCustomType((char) 262));
    }
    @Test
    public void getType66(){
        assertEq(1,StringUtil.getCustomType((char) 263));
    }
    @Test
    public void getType67(){
        assertEq(2,StringUtil.getCustomType((char) 264));
    }
    @Test
    public void getType68(){
        assertEq(1,StringUtil.getCustomType((char) 265));
    }
    @Test
    public void getType69(){
        assertEq(2,StringUtil.getCustomType((char) 266));
    }
    @Test
    public void getType70(){
        assertEq(1,StringUtil.getCustomType((char) 267));
    }
    @Test
    public void getType71(){
        assertEq(2,StringUtil.getCustomType((char) 268));
    }
    @Test
    public void getType72(){
        assertEq(1,StringUtil.getCustomType((char) 269));
    }
    @Test
    public void getType73(){
        assertEq(2,StringUtil.getCustomType((char) 270));
    }
    @Test
    public void getType74(){
        assertEq(1,StringUtil.getCustomType((char) 271));
    }
    @Test
    public void getType75(){
        assertEq(2,StringUtil.getCustomType((char) 272));
    }
    @Test
    public void getType76(){
        assertEq(1,StringUtil.getCustomType((char) 273));
    }
    @Test
    public void getType77(){
        assertEq(2,StringUtil.getCustomType((char) 274));
    }
    @Test
    public void getType78(){
        assertEq(1,StringUtil.getCustomType((char) 275));
    }
    @Test
    public void getType79(){
        assertEq(2,StringUtil.getCustomType((char) 276));
    }
    @Test
    public void getType80(){
        assertEq(1,StringUtil.getCustomType((char) 277));
    }
    @Test
    public void getType81(){
        assertEq(2,StringUtil.getCustomType((char) 278));
    }
    @Test
    public void getType82(){
        assertEq(1,StringUtil.getCustomType((char) 279));
    }
    @Test
    public void getType83(){
        assertEq(2,StringUtil.getCustomType((char) 280));
    }
    @Test
    public void getType84(){
        assertEq(1,StringUtil.getCustomType((char) 281));
    }
    @Test
    public void getType85(){
        assertEq(2,StringUtil.getCustomType((char) 282));
    }
    @Test
    public void getType86(){
        assertEq(1,StringUtil.getCustomType((char) 283));
    }
    @Test
    public void getType87(){
        assertEq(2,StringUtil.getCustomType((char) 284));
    }
    @Test
    public void getType88(){
        assertEq(1,StringUtil.getCustomType((char) 285));
    }
    @Test
    public void getType89(){
        assertEq(2,StringUtil.getCustomType((char) 286));
    }
    @Test
    public void getType90(){
        assertEq(1,StringUtil.getCustomType((char) 287));
    }
    @Test
    public void getType91(){
        assertEq(2,StringUtil.getCustomType((char) 288));
    }
    @Test
    public void getType92(){
        assertEq(1,StringUtil.getCustomType((char) 289));
    }
    @Test
    public void getType93(){
        assertEq(2,StringUtil.getCustomType((char) 290));
    }
    @Test
    public void getType94(){
        assertEq(1,StringUtil.getCustomType((char) 291));
    }
    @Test
    public void getType95(){
        assertEq(2,StringUtil.getCustomType((char) 292));
    }
    @Test
    public void getType96(){
        assertEq(1,StringUtil.getCustomType((char) 293));
    }
    @Test
    public void getType97(){
        assertEq(2,StringUtil.getCustomType((char) 294));
    }
    @Test
    public void getType98(){
        assertEq(1,StringUtil.getCustomType((char) 295));
    }
    @Test
    public void getType99(){
        assertEq(2,StringUtil.getCustomType((char) 296));
    }
    @Test
    public void getType100(){
        assertEq(1,StringUtil.getCustomType((char) 297));
    }
    @Test
    public void getType101(){
        assertEq(2,StringUtil.getCustomType((char) 298));
    }
    @Test
    public void getType102(){
        assertEq(1,StringUtil.getCustomType((char) 299));
    }
    @Test
    public void getType103(){
        assertEq(2,StringUtil.getCustomType((char) 300));
    }
    @Test
    public void getType104(){
        assertEq(1,StringUtil.getCustomType((char) 301));
    }
    @Test
    public void getType105(){
        assertEq(2,StringUtil.getCustomType((char) 302));
    }
    @Test
    public void getType106(){
        assertEq(1,StringUtil.getCustomType((char) 303));
    }
    @Test
    public void getType107(){
        assertEq(2,StringUtil.getCustomType((char) 304));
    }
    @Test
    public void getType108(){
        assertEq(1,StringUtil.getCustomType((char) 305));
    }
    @Test
    public void getType109(){
        assertEq(2,StringUtil.getCustomType((char) 306));
    }
    @Test
    public void getType110(){
        assertEq(1,StringUtil.getCustomType((char) 307));
    }
    @Test
    public void getType111(){
        assertEq(2,StringUtil.getCustomType((char) 308));
    }
    @Test
    public void getType112(){
        assertEq(1,StringUtil.getCustomType((char) 309));
    }
    @Test
    public void getType113(){
        assertEq(2,StringUtil.getCustomType((char) 310));
    }
    @Test
    public void getType114(){
        assertEq(1,StringUtil.getCustomType((char) 311));
    }
    @Test
    public void getType115(){
        assertEq(3,StringUtil.getCustomType((char) 312));
    }
    @Test
    public void getType116(){
        assertEq(2,StringUtil.getCustomType((char) 313));
    }
    @Test
    public void getType117(){
        assertEq(1,StringUtil.getCustomType((char) 314));
    }
    @Test
    public void getType118(){
        assertEq(2,StringUtil.getCustomType((char) 315));
    }
    @Test
    public void getType119(){
        assertEq(1,StringUtil.getCustomType((char) 316));
    }
    @Test
    public void getType120(){
        assertEq(2,StringUtil.getCustomType((char) 317));
    }
    @Test
    public void getType121(){
        assertEq(1,StringUtil.getCustomType((char) 318));
    }
    @Test
    public void getType122(){
        assertEq(2,StringUtil.getCustomType((char) 319));
    }
    @Test
    public void getType123(){
        assertEq(1,StringUtil.getCustomType((char) 320));
    }
    @Test
    public void getType124(){
        assertEq(2,StringUtil.getCustomType((char) 321));
    }
    @Test
    public void getType125(){
        assertEq(1,StringUtil.getCustomType((char) 322));
    }
    @Test
    public void getType126(){
        assertEq(2,StringUtil.getCustomType((char) 323));
    }
    @Test
    public void getType127(){
        assertEq(1,StringUtil.getCustomType((char) 324));
    }
    @Test
    public void getType128(){
        assertEq(2,StringUtil.getCustomType((char) 325));
    }
    @Test
    public void getType129(){
        assertEq(1,StringUtil.getCustomType((char) 326));
    }
    @Test
    public void getType130(){
        assertEq(2,StringUtil.getCustomType((char) 327));
    }
    @Test
    public void getType131(){
        assertEq(1,StringUtil.getCustomType((char) 328));
    }
    @Test
    public void getType132(){
        assertEq(3,StringUtil.getCustomType((char) 329));
    }
    @Test
    public void getType133(){
        assertEq(2,StringUtil.getCustomType((char) 330));
    }
    @Test
    public void getType134(){
        assertEq(1,StringUtil.getCustomType((char) 331));
    }
    @Test
    public void getType135(){
        assertEq(2,StringUtil.getCustomType((char) 332));
    }
    @Test
    public void getType136(){
        assertEq(1,StringUtil.getCustomType((char) 333));
    }
    @Test
    public void getType137(){
        assertEq(2,StringUtil.getCustomType((char) 334));
    }
    @Test
    public void getType138(){
        assertEq(1,StringUtil.getCustomType((char) 335));
    }
    @Test
    public void getType139(){
        assertEq(2,StringUtil.getCustomType((char) 336));
    }
    @Test
    public void getType140(){
        assertEq(1,StringUtil.getCustomType((char) 337));
    }
    @Test
    public void getType141(){
        assertEq(2,StringUtil.getCustomType((char) 338));
    }
    @Test
    public void getType142(){
        assertEq(1,StringUtil.getCustomType((char) 339));
    }
    @Test
    public void getType143(){
        assertEq(2,StringUtil.getCustomType((char) 340));
    }
    @Test
    public void getType144(){
        assertEq(1,StringUtil.getCustomType((char) 341));
    }
    @Test
    public void getType145(){
        assertEq(2,StringUtil.getCustomType((char) 342));
    }
    @Test
    public void getType146(){
        assertEq(1,StringUtil.getCustomType((char) 343));
    }
    @Test
    public void getType147(){
        assertEq(2,StringUtil.getCustomType((char) 344));
    }
    @Test
    public void getType148(){
        assertEq(1,StringUtil.getCustomType((char) 345));
    }
    @Test
    public void getType149(){
        assertEq(2,StringUtil.getCustomType((char) 346));
    }
    @Test
    public void getType150(){
        assertEq(1,StringUtil.getCustomType((char) 347));
    }
    @Test
    public void getType151(){
        assertEq(2,StringUtil.getCustomType((char) 348));
    }
    @Test
    public void getType152(){
        assertEq(1,StringUtil.getCustomType((char) 349));
    }
    @Test
    public void getType153(){
        assertEq(2,StringUtil.getCustomType((char) 350));
    }
    @Test
    public void getType154(){
        assertEq(1,StringUtil.getCustomType((char) 351));
    }
    @Test
    public void getType155(){
        assertEq(2,StringUtil.getCustomType((char) 352));
    }
    @Test
    public void getType156(){
        assertEq(1,StringUtil.getCustomType((char) 353));
    }
    @Test
    public void getType157(){
        assertEq(2,StringUtil.getCustomType((char) 354));
    }
    @Test
    public void getType158(){
        assertEq(1,StringUtil.getCustomType((char) 355));
    }
    @Test
    public void getType159(){
        assertEq(2,StringUtil.getCustomType((char) 356));
    }
    @Test
    public void getType160(){
        assertEq(1,StringUtil.getCustomType((char) 357));
    }
    @Test
    public void getType161(){
        assertEq(2,StringUtil.getCustomType((char) 358));
    }
    @Test
    public void getType162(){
        assertEq(1,StringUtil.getCustomType((char) 359));
    }
    @Test
    public void getType163(){
        assertEq(2,StringUtil.getCustomType((char) 360));
    }
    @Test
    public void getType164(){
        assertEq(1,StringUtil.getCustomType((char) 361));
    }
    @Test
    public void getType165(){
        assertEq(2,StringUtil.getCustomType((char) 362));
    }
    @Test
    public void getType166(){
        assertEq(1,StringUtil.getCustomType((char) 363));
    }
    @Test
    public void getType167(){
        assertEq(2,StringUtil.getCustomType((char) 364));
    }
    @Test
    public void getType168(){
        assertEq(1,StringUtil.getCustomType((char) 365));
    }
    @Test
    public void getType169(){
        assertEq(2,StringUtil.getCustomType((char) 366));
    }
    @Test
    public void getType170(){
        assertEq(1,StringUtil.getCustomType((char) 367));
    }
    @Test
    public void getType171(){
        assertEq(2,StringUtil.getCustomType((char) 368));
    }
    @Test
    public void getType172(){
        assertEq(1,StringUtil.getCustomType((char) 369));
    }
    @Test
    public void getType173(){
        assertEq(2,StringUtil.getCustomType((char) 370));
    }
    @Test
    public void getType174(){
        assertEq(1,StringUtil.getCustomType((char) 371));
    }
    @Test
    public void getType175(){
        assertEq(2,StringUtil.getCustomType((char) 372));
    }
    @Test
    public void getType176(){
        assertEq(1,StringUtil.getCustomType((char) 373));
    }
    @Test
    public void getType177(){
        assertEq(2,StringUtil.getCustomType((char) 374));
    }
    @Test
    public void getType178(){
        assertEq(1,StringUtil.getCustomType((char) 375));
    }
    @Test
    public void getType179(){
        assertEq(2,StringUtil.getCustomType((char) 376));
    }
    @Test
    public void getType180(){
        assertEq(1,StringUtil.getCustomType((char) 378));
    }
    @Test
    public void getType181(){
        assertEq(2,StringUtil.getCustomType((char) 379));
    }
    @Test
    public void getType182(){
        assertEq(1,StringUtil.getCustomType((char) 380));
    }
    @Test
    public void getType183(){
        assertEq(2,StringUtil.getCustomType((char) 381));
    }
    @Test
    public void getType184(){
        assertEq(1,StringUtil.getCustomType((char) 382));
    }
    @Test
    public void getType185(){
        assertEq(2,StringUtil.getCustomType((char) 385));
    }
    @Test
    public void getType186(){
        assertEq(1,StringUtil.getCustomType((char) 387));
    }
    @Test
    public void getType187(){
        assertEq(2,StringUtil.getCustomType((char) 388));
    }
    @Test
    public void getType188(){
        assertEq(1,StringUtil.getCustomType((char) 389));
    }
    @Test
    public void getType189(){
        assertEq(2,StringUtil.getCustomType((char) 390));
    }
    @Test
    public void getType190(){
        assertEq(1,StringUtil.getCustomType((char) 392));
    }
    @Test
    public void getType191(){
        assertEq(2,StringUtil.getCustomType((char) 393));
    }
    @Test
    public void getType192(){
        assertEq(1,StringUtil.getCustomType((char) 396));
    }
    @Test
    public void getType193(){
        assertEq(3,StringUtil.getCustomType((char) 397));
    }
    @Test
    public void getType194(){
        assertEq(2,StringUtil.getCustomType((char) 398));
    }
    @Test
    public void getType195(){
        assertEq(1,StringUtil.getCustomType((char) 402));
    }
    @Test
    public void getType196(){
        assertEq(2,StringUtil.getCustomType((char) 403));
    }
    @Test
    public void getType197(){
        assertEq(1,StringUtil.getCustomType((char) 405));
    }
    @Test
    public void getType198(){
        assertEq(2,StringUtil.getCustomType((char) 406));
    }
    @Test
    public void getType199(){
        assertEq(1,StringUtil.getCustomType((char) 409));
    }
    @Test
    public void getType200(){
        assertEq(3,StringUtil.getCustomType((char) 411));
    }
    @Test
    public void getType201(){
        assertEq(2,StringUtil.getCustomType((char) 412));
    }
    @Test
    public void getType202(){
        assertEq(1,StringUtil.getCustomType((char) 414));
    }
    @Test
    public void getType203(){
        assertEq(2,StringUtil.getCustomType((char) 415));
    }
    @Test
    public void getType204(){
        assertEq(1,StringUtil.getCustomType((char) 417));
    }
    @Test
    public void getType205(){
        assertEq(2,StringUtil.getCustomType((char) 418));
    }
    @Test
    public void getType206(){
        assertEq(1,StringUtil.getCustomType((char) 419));
    }
    @Test
    public void getType207(){
        assertEq(2,StringUtil.getCustomType((char) 420));
    }
    @Test
    public void getType208(){
        assertEq(1,StringUtil.getCustomType((char) 421));
    }
    @Test
    public void getType209(){
        assertEq(2,StringUtil.getCustomType((char) 422));
    }
    @Test
    public void getType210(){
        assertEq(1,StringUtil.getCustomType((char) 424));
    }
    @Test
    public void getType211(){
        assertEq(2,StringUtil.getCustomType((char) 425));
    }
    @Test
    public void getType212(){
        assertEq(3,StringUtil.getCustomType((char) 426));
    }
    @Test
    public void getType213(){
        assertEq(2,StringUtil.getCustomType((char) 428));
    }
    @Test
    public void getType214(){
        assertEq(1,StringUtil.getCustomType((char) 429));
    }
    @Test
    public void getType215(){
        assertEq(2,StringUtil.getCustomType((char) 430));
    }
    @Test
    public void getType216(){
        assertEq(1,StringUtil.getCustomType((char) 432));
    }
    @Test
    public void getType217(){
        assertEq(2,StringUtil.getCustomType((char) 433));
    }
    @Test
    public void getType218(){
        assertEq(1,StringUtil.getCustomType((char) 436));
    }
    @Test
    public void getType219(){
        assertEq(2,StringUtil.getCustomType((char) 437));
    }
    @Test
    public void getType220(){
        assertEq(1,StringUtil.getCustomType((char) 438));
    }
    @Test
    public void getType221(){
        assertEq(2,StringUtil.getCustomType((char) 439));
    }
    @Test
    public void getType222(){
        assertEq(1,StringUtil.getCustomType((char) 441));
    }
    @Test
    public void getType223(){
        assertEq(3,StringUtil.getCustomType((char) 442));
    }
    @Test
    public void getType224(){
        assertEq(6,StringUtil.getCustomType((char) 443));
    }
    @Test
    public void getType225(){
        assertEq(2,StringUtil.getCustomType((char) 444));
    }
    @Test
    public void getType226(){
        assertEq(1,StringUtil.getCustomType((char) 445));
    }
    @Test
    public void getType227(){
        assertEq(3,StringUtil.getCustomType((char) 446));
    }
    @Test
    public void getType228(){
        assertEq(1,StringUtil.getCustomType((char) 447));
    }
    @Test
    public void getType229(){
        assertEq(6,StringUtil.getCustomType((char) 448));
    }
    @Test
    public void getType230(){
        assertEq(2,StringUtil.getCustomType((char) 452));
    }
    @Test
    public void getType231(){
        assertEq(5,StringUtil.getCustomType((char) 453));
    }
    @Test
    public void getType232(){
        assertEq(1,StringUtil.getCustomType((char) 454));
    }
    @Test
    public void getType233(){
        assertEq(2,StringUtil.getCustomType((char) 455));
    }
    @Test
    public void getType234(){
        assertEq(5,StringUtil.getCustomType((char) 456));
    }
    @Test
    public void getType235(){
        assertEq(1,StringUtil.getCustomType((char) 457));
    }
    @Test
    public void getType236(){
        assertEq(2,StringUtil.getCustomType((char) 458));
    }
    @Test
    public void getType237(){
        assertEq(5,StringUtil.getCustomType((char) 459));
    }
    @Test
    public void getType238(){
        assertEq(1,StringUtil.getCustomType((char) 460));
    }
    @Test
    public void getType239(){
        assertEq(2,StringUtil.getCustomType((char) 461));
    }
    @Test
    public void getType240(){
        assertEq(1,StringUtil.getCustomType((char) 462));
    }
    @Test
    public void getType241(){
        assertEq(2,StringUtil.getCustomType((char) 463));
    }
    @Test
    public void getType242(){
        assertEq(1,StringUtil.getCustomType((char) 464));
    }
    @Test
    public void getType243(){
        assertEq(2,StringUtil.getCustomType((char) 465));
    }
    @Test
    public void getType244(){
        assertEq(1,StringUtil.getCustomType((char) 466));
    }
    @Test
    public void getType245(){
        assertEq(2,StringUtil.getCustomType((char) 467));
    }
    @Test
    public void getType246(){
        assertEq(1,StringUtil.getCustomType((char) 468));
    }
    @Test
    public void getType247(){
        assertEq(2,StringUtil.getCustomType((char) 469));
    }
    @Test
    public void getType248(){
        assertEq(1,StringUtil.getCustomType((char) 470));
    }
    @Test
    public void getType249(){
        assertEq(2,StringUtil.getCustomType((char) 471));
    }
    @Test
    public void getType250(){
        assertEq(1,StringUtil.getCustomType((char) 472));
    }
    @Test
    public void getType251(){
        assertEq(2,StringUtil.getCustomType((char) 473));
    }
    @Test
    public void getType252(){
        assertEq(1,StringUtil.getCustomType((char) 474));
    }
    @Test
    public void getType253(){
        assertEq(2,StringUtil.getCustomType((char) 475));
    }
    @Test
    public void getType254(){
        assertEq(1,StringUtil.getCustomType((char) 476));
    }
    @Test
    public void getType255(){
        assertEq(2,StringUtil.getCustomType((char) 478));
    }
    @Test
    public void getType256(){
        assertEq(1,StringUtil.getCustomType((char) 479));
    }
    @Test
    public void getType257(){
        assertEq(2,StringUtil.getCustomType((char) 480));
    }
    @Test
    public void getType258(){
        assertEq(1,StringUtil.getCustomType((char) 481));
    }
    @Test
    public void getType259(){
        assertEq(2,StringUtil.getCustomType((char) 482));
    }
    @Test
    public void getType260(){
        assertEq(1,StringUtil.getCustomType((char) 483));
    }
    @Test
    public void getType261(){
        assertEq(2,StringUtil.getCustomType((char) 484));
    }
    @Test
    public void getType262(){
        assertEq(1,StringUtil.getCustomType((char) 485));
    }
    @Test
    public void getType263(){
        assertEq(2,StringUtil.getCustomType((char) 486));
    }
    @Test
    public void getType264(){
        assertEq(1,StringUtil.getCustomType((char) 487));
    }
    @Test
    public void getType265(){
        assertEq(2,StringUtil.getCustomType((char) 488));
    }
    @Test
    public void getType266(){
        assertEq(1,StringUtil.getCustomType((char) 489));
    }
    @Test
    public void getType267(){
        assertEq(2,StringUtil.getCustomType((char) 490));
    }
    @Test
    public void getType268(){
        assertEq(1,StringUtil.getCustomType((char) 491));
    }
    @Test
    public void getType269(){
        assertEq(2,StringUtil.getCustomType((char) 492));
    }
    @Test
    public void getType270(){
        assertEq(1,StringUtil.getCustomType((char) 493));
    }
    @Test
    public void getType271(){
        assertEq(2,StringUtil.getCustomType((char) 494));
    }
    @Test
    public void getType272(){
        assertEq(1,StringUtil.getCustomType((char) 495));
    }
    @Test
    public void getType273(){
        assertEq(3,StringUtil.getCustomType((char) 496));
    }
    @Test
    public void getType274(){
        assertEq(2,StringUtil.getCustomType((char) 497));
    }
    @Test
    public void getType275(){
        assertEq(5,StringUtil.getCustomType((char) 498));
    }
    @Test
    public void getType276(){
        assertEq(1,StringUtil.getCustomType((char) 499));
    }
    @Test
    public void getType277(){
        assertEq(2,StringUtil.getCustomType((char) 500));
    }
    @Test
    public void getType278(){
        assertEq(1,StringUtil.getCustomType((char) 501));
    }
    @Test
    public void getType279(){
        assertEq(2,StringUtil.getCustomType((char) 502));
    }
    @Test
    public void getType280(){
        assertEq(1,StringUtil.getCustomType((char) 505));
    }
    @Test
    public void getType281(){
        assertEq(2,StringUtil.getCustomType((char) 506));
    }
    @Test
    public void getType282(){
        assertEq(1,StringUtil.getCustomType((char) 507));
    }
    @Test
    public void getType283(){
        assertEq(2,StringUtil.getCustomType((char) 508));
    }
    @Test
    public void getType284(){
        assertEq(1,StringUtil.getCustomType((char) 509));
    }
    @Test
    public void getType285(){
        assertEq(2,StringUtil.getCustomType((char) 510));
    }
    @Test
    public void getType286(){
        assertEq(1,StringUtil.getCustomType((char) 511));
    }
    @Test
    public void getType287(){
        assertEq(2,StringUtil.getCustomType((char) 512));
    }
    @Test
    public void getType288(){
        assertEq(1,StringUtil.getCustomType((char) 513));
    }
    @Test
    public void getType289(){
        assertEq(2,StringUtil.getCustomType((char) 514));
    }
    @Test
    public void getType290(){
        assertEq(1,StringUtil.getCustomType((char) 515));
    }
    @Test
    public void getType291(){
        assertEq(2,StringUtil.getCustomType((char) 516));
    }
    @Test
    public void getType292(){
        assertEq(1,StringUtil.getCustomType((char) 517));
    }
    @Test
    public void getType293(){
        assertEq(2,StringUtil.getCustomType((char) 518));
    }
    @Test
    public void getType294(){
        assertEq(1,StringUtil.getCustomType((char) 519));
    }
    @Test
    public void getType295(){
        assertEq(2,StringUtil.getCustomType((char) 520));
    }
    @Test
    public void getType296(){
        assertEq(1,StringUtil.getCustomType((char) 521));
    }
    @Test
    public void getType297(){
        assertEq(2,StringUtil.getCustomType((char) 522));
    }
    @Test
    public void getType298(){
        assertEq(1,StringUtil.getCustomType((char) 523));
    }
    @Test
    public void getType299(){
        assertEq(2,StringUtil.getCustomType((char) 524));
    }
    @Test
    public void getType300(){
        assertEq(1,StringUtil.getCustomType((char) 525));
    }
    @Test
    public void getType301(){
        assertEq(2,StringUtil.getCustomType((char) 526));
    }
    @Test
    public void getType302(){
        assertEq(1,StringUtil.getCustomType((char) 527));
    }
    @Test
    public void getType303(){
        assertEq(2,StringUtil.getCustomType((char) 528));
    }
    @Test
    public void getType304(){
        assertEq(1,StringUtil.getCustomType((char) 529));
    }
    @Test
    public void getType305(){
        assertEq(2,StringUtil.getCustomType((char) 530));
    }
    @Test
    public void getType306(){
        assertEq(1,StringUtil.getCustomType((char) 531));
    }
    @Test
    public void getType307(){
        assertEq(2,StringUtil.getCustomType((char) 532));
    }
    @Test
    public void getType308(){
        assertEq(1,StringUtil.getCustomType((char) 533));
    }
    @Test
    public void getType309(){
        assertEq(2,StringUtil.getCustomType((char) 534));
    }
    @Test
    public void getType310(){
        assertEq(1,StringUtil.getCustomType((char) 535));
    }
    @Test
    public void getType311(){
        assertEq(2,StringUtil.getCustomType((char) 536));
    }
    @Test
    public void getType312(){
        assertEq(1,StringUtil.getCustomType((char) 537));
    }
    @Test
    public void getType313(){
        assertEq(2,StringUtil.getCustomType((char) 538));
    }
    @Test
    public void getType314(){
        assertEq(1,StringUtil.getCustomType((char) 539));
    }
    @Test
    public void getType315(){
        assertEq(2,StringUtil.getCustomType((char) 540));
    }
    @Test
    public void getType316(){
        assertEq(1,StringUtil.getCustomType((char) 541));
    }
    @Test
    public void getType317(){
        assertEq(2,StringUtil.getCustomType((char) 542));
    }
    @Test
    public void getType318(){
        assertEq(1,StringUtil.getCustomType((char) 543));
    }
    @Test
    public void getType319(){
        assertEq(2,StringUtil.getCustomType((char) 544));
    }
    @Test
    public void getType320(){
        assertEq(3,StringUtil.getCustomType((char) 545));
    }
    @Test
    public void getType321(){
        assertEq(2,StringUtil.getCustomType((char) 546));
    }
    @Test
    public void getType322(){
        assertEq(1,StringUtil.getCustomType((char) 547));
    }
    @Test
    public void getType323(){
        assertEq(2,StringUtil.getCustomType((char) 548));
    }
    @Test
    public void getType324(){
        assertEq(1,StringUtil.getCustomType((char) 549));
    }
    @Test
    public void getType325(){
        assertEq(2,StringUtil.getCustomType((char) 550));
    }
    @Test
    public void getType326(){
        assertEq(1,StringUtil.getCustomType((char) 551));
    }
    @Test
    public void getType327(){
        assertEq(2,StringUtil.getCustomType((char) 552));
    }
    @Test
    public void getType328(){
        assertEq(1,StringUtil.getCustomType((char) 553));
    }
    @Test
    public void getType329(){
        assertEq(2,StringUtil.getCustomType((char) 554));
    }
    @Test
    public void getType330(){
        assertEq(1,StringUtil.getCustomType((char) 555));
    }
    @Test
    public void getType331(){
        assertEq(2,StringUtil.getCustomType((char) 556));
    }
    @Test
    public void getType332(){
        assertEq(1,StringUtil.getCustomType((char) 557));
    }
    @Test
    public void getType333(){
        assertEq(2,StringUtil.getCustomType((char) 558));
    }
    @Test
    public void getType334(){
        assertEq(1,StringUtil.getCustomType((char) 559));
    }
    @Test
    public void getType335(){
        assertEq(2,StringUtil.getCustomType((char) 560));
    }
    @Test
    public void getType336(){
        assertEq(1,StringUtil.getCustomType((char) 561));
    }
    @Test
    public void getType337(){
        assertEq(2,StringUtil.getCustomType((char) 562));
    }
    @Test
    public void getType338(){
        assertEq(1,StringUtil.getCustomType((char) 563));
    }
    @Test
    public void getType339(){
        assertEq(3,StringUtil.getCustomType((char) 564));
    }
    @Test
    public void getType340(){
        assertEq(2,StringUtil.getCustomType((char) 570));
    }
    @Test
    public void getType341(){
        assertEq(1,StringUtil.getCustomType((char) 572));
    }
    @Test
    public void getType342(){
        assertEq(2,StringUtil.getCustomType((char) 573));
    }
    @Test
    public void getType343(){
        assertEq(1,StringUtil.getCustomType((char) 575));
    }
    @Test
    public void getType344(){
        assertEq(2,StringUtil.getCustomType((char) 577));
    }
    @Test
    public void getType345(){
        assertEq(1,StringUtil.getCustomType((char) 578));
    }
    @Test
    public void getType346(){
        assertEq(2,StringUtil.getCustomType((char) 579));
    }
    @Test
    public void getType347(){
        assertEq(1,StringUtil.getCustomType((char) 583));
    }
    @Test
    public void getType348(){
        assertEq(2,StringUtil.getCustomType((char) 584));
    }
    @Test
    public void getType349(){
        assertEq(1,StringUtil.getCustomType((char) 585));
    }
    @Test
    public void getType350(){
        assertEq(2,StringUtil.getCustomType((char) 586));
    }
    @Test
    public void getType351(){
        assertEq(1,StringUtil.getCustomType((char) 587));
    }
    @Test
    public void getType352(){
        assertEq(2,StringUtil.getCustomType((char) 588));
    }
    @Test
    public void getType353(){
        assertEq(1,StringUtil.getCustomType((char) 589));
    }
    @Test
    public void getType354(){
        assertEq(2,StringUtil.getCustomType((char) 590));
    }
    @Test
    public void getType355(){
        assertEq(1,StringUtil.getCustomType((char) 591));
    }
    @Test
    public void getType356(){
        assertEq(3,StringUtil.getCustomType((char) 597));
    }
    @Test
    public void getType357(){
        assertEq(1,StringUtil.getCustomType((char) 598));
    }
    @Test
    public void getType358(){
        assertEq(3,StringUtil.getCustomType((char) 600));
    }
    @Test
    public void getType359(){
        assertEq(1,StringUtil.getCustomType((char) 601));
    }
    @Test
    public void getType360(){
        assertEq(3,StringUtil.getCustomType((char) 602));
    }
    @Test
    public void getType361(){
        assertEq(1,StringUtil.getCustomType((char) 603));
    }
    @Test
    public void getType362(){
        assertEq(3,StringUtil.getCustomType((char) 604));
    }
    @Test
    public void getType363(){
        assertEq(1,StringUtil.getCustomType((char) 608));
    }
    @Test
    public void getType364(){
        assertEq(3,StringUtil.getCustomType((char) 609));
    }
    @Test
    public void getType365(){
        assertEq(1,StringUtil.getCustomType((char) 611));
    }
    @Test
    public void getType366(){
        assertEq(3,StringUtil.getCustomType((char) 612));
    }
    @Test
    public void getType367(){
        assertEq(1,StringUtil.getCustomType((char) 613));
    }
    @Test
    public void getType368(){
        assertEq(3,StringUtil.getCustomType((char) 615));
    }
    @Test
    public void getType369(){
        assertEq(1,StringUtil.getCustomType((char) 616));
    }
    @Test
    public void getType370(){
        assertEq(3,StringUtil.getCustomType((char) 618));
    }
    @Test
    public void getType371(){
        assertEq(1,StringUtil.getCustomType((char) 619));
    }
    @Test
    public void getType372(){
        assertEq(3,StringUtil.getCustomType((char) 620));
    }
    @Test
    public void getType373(){
        assertEq(1,StringUtil.getCustomType((char) 623));
    }
    @Test
    public void getType374(){
        assertEq(3,StringUtil.getCustomType((char) 624));
    }
    @Test
    public void getType375(){
        assertEq(1,StringUtil.getCustomType((char) 625));
    }
    @Test
    public void getType376(){
        assertEq(3,StringUtil.getCustomType((char) 627));
    }
    @Test
    public void getType377(){
        assertEq(1,StringUtil.getCustomType((char) 629));
    }
    @Test
    public void getType378(){
        assertEq(3,StringUtil.getCustomType((char) 630));
    }
    @Test
    public void getType379(){
        assertEq(1,StringUtil.getCustomType((char) 637));
    }
    @Test
    public void getType380(){
        assertEq(3,StringUtil.getCustomType((char) 638));
    }
    @Test
    public void getType381(){
        assertEq(1,StringUtil.getCustomType((char) 640));
    }
    @Test
    public void getType382(){
        assertEq(3,StringUtil.getCustomType((char) 641));
    }
    @Test
    public void getType383(){
        assertEq(1,StringUtil.getCustomType((char) 643));
    }
    @Test
    public void getType384(){
        assertEq(3,StringUtil.getCustomType((char) 644));
    }
    @Test
    public void getType385(){
        assertEq(1,StringUtil.getCustomType((char) 648));
    }
    @Test
    public void getType386(){
        assertEq(3,StringUtil.getCustomType((char) 653));
    }
    @Test
    public void getType387(){
        assertEq(1,StringUtil.getCustomType((char) 658));
    }
    @Test
    public void getType388(){
        assertEq(3,StringUtil.getCustomType((char) 659));
    }
    @Test
    public void getType389(){
        assertEq(6,StringUtil.getCustomType((char) 660));
    }
    @Test
    public void getType390(){
        assertEq(3,StringUtil.getCustomType((char) 661));
    }
    @Test
    public void getType391(){
        assertEq(7,StringUtil.getCustomType((char) 697));
    }
    @Test
    public void getType392(){
        assertEq(6,StringUtil.getCustomType((char) 699));
    }
    @Test
    public void getType393(){
        assertEq(3,StringUtil.getCustomType((char) 704));
    }
    @Test
    public void getType394(){
        assertEq(23,StringUtil.getCustomType((char) 706));
    }
    @Test
    public void getType395(){
        assertEq(7,StringUtil.getCustomType((char) 710));
    }
    @Test
    public void getType396(){
        assertEq(6,StringUtil.getCustomType((char) 720));
    }
    @Test
    public void getType397(){
        assertEq(23,StringUtil.getCustomType((char) 722));
    }
    @Test
    public void getType398(){
        assertEq(3,StringUtil.getCustomType((char) 736));
    }
    @Test
    public void getType399(){
        assertEq(23,StringUtil.getCustomType((char) 741));
    }
    @Test
    public void getType400(){
        assertEq(7,StringUtil.getCustomType((char) 748));
    }
    @Test
    public void getType401(){
        assertEq(23,StringUtil.getCustomType((char) 749));
    }
    @Test
    public void getType402(){
        assertEq(6,StringUtil.getCustomType((char) 750));
    }
    @Test
    public void getType403(){
        assertEq(23,StringUtil.getCustomType((char) 751));
    }
    @Test
    public void getType404(){
        assertEq(31,StringUtil.getCustomType((char) 768));
    }
    @Test
    public void getType405(){
        assertEq(2,StringUtil.getCustomType((char) 880));
    }
    @Test
    public void getType406(){
        assertEq(1,StringUtil.getCustomType((char) 881));
    }
    @Test
    public void getType407(){
        assertEq(2,StringUtil.getCustomType((char) 882));
    }
    @Test
    public void getType408(){
        assertEq(1,StringUtil.getCustomType((char) 883));
    }
    @Test
    public void getType409(){
        assertEq(7,StringUtil.getCustomType((char) 884));
    }
    @Test
    public void getType410(){
        assertEq(23,StringUtil.getCustomType((char) 885));
    }
    @Test
    public void getType411(){
        assertEq(2,StringUtil.getCustomType((char) 886));
    }
    @Test
    public void getType412(){
        assertEq(1,StringUtil.getCustomType((char) 887));
    }
    @Test
    public void getType413(){
        assertEq(32,StringUtil.getCustomType((char) 888));
    }
    @Test
    public void getType414(){
        assertEq(3,StringUtil.getCustomType((char) 890));
    }
    @Test
    public void getType415(){
        assertEq(1,StringUtil.getCustomType((char) 891));
    }
    @Test
    public void getType416(){
        assertEq(28,StringUtil.getCustomType((char) 894));
    }
    @Test
    public void getType417(){
        assertEq(32,StringUtil.getCustomType((char) 895));
    }
    @Test
    public void getType418(){
        assertEq(23,StringUtil.getCustomType((char) 900));
    }
    @Test
    public void getType419(){
        assertEq(2,StringUtil.getCustomType((char) 902));
    }
    @Test
    public void getType420(){
        assertEq(28,StringUtil.getCustomType((char) 903));
    }
    @Test
    public void getType421(){
        assertEq(2,StringUtil.getCustomType((char) 904));
    }
    @Test
    public void getType422(){
        assertEq(32,StringUtil.getCustomType((char) 907));
    }
    @Test
    public void getType423(){
        assertEq(2,StringUtil.getCustomType((char) 908));
    }
    @Test
    public void getType424(){
        assertEq(32,StringUtil.getCustomType((char) 909));
    }
    @Test
    public void getType425(){
        assertEq(2,StringUtil.getCustomType((char) 910));
    }
    @Test
    public void getType426(){
        assertEq(3,StringUtil.getCustomType((char) 912));
    }
    @Test
    public void getType427(){
        assertEq(2,StringUtil.getCustomType((char) 913));
    }
    @Test
    public void getType428(){
        assertEq(32,StringUtil.getCustomType((char) 930));
    }
    @Test
    public void getType429(){
        assertEq(2,StringUtil.getCustomType((char) 931));
    }
    @Test
    public void getType430(){
        assertEq(1,StringUtil.getCustomType((char) 940));
    }
    @Test
    public void getType431(){
        assertEq(3,StringUtil.getCustomType((char) 944));
    }
    @Test
    public void getType432(){
        assertEq(1,StringUtil.getCustomType((char) 945));
    }
    @Test
    public void getType433(){
        assertEq(2,StringUtil.getCustomType((char) 975));
    }
    @Test
    public void getType434(){
        assertEq(1,StringUtil.getCustomType((char) 976));
    }
    @Test
    public void getType435(){
        assertEq(4,StringUtil.getCustomType((char) 978));
    }
    @Test
    public void getType436(){
        assertEq(1,StringUtil.getCustomType((char) 981));
    }
    @Test
    public void getType437(){
        assertEq(2,StringUtil.getCustomType((char) 984));
    }
    @Test
    public void getType438(){
        assertEq(1,StringUtil.getCustomType((char) 985));
    }
    @Test
    public void getType439(){
        assertEq(2,StringUtil.getCustomType((char) 986));
    }
    @Test
    public void getType440(){
        assertEq(1,StringUtil.getCustomType((char) 987));
    }
    @Test
    public void getType441(){
        assertEq(2,StringUtil.getCustomType((char) 988));
    }
    @Test
    public void getType442(){
        assertEq(1,StringUtil.getCustomType((char) 989));
    }
    @Test
    public void getType443(){
        assertEq(2,StringUtil.getCustomType((char) 990));
    }
    @Test
    public void getType444(){
        assertEq(1,StringUtil.getCustomType((char) 991));
    }
    @Test
    public void getType445(){
        assertEq(2,StringUtil.getCustomType((char) 992));
    }
    @Test
    public void getType446(){
        assertEq(1,StringUtil.getCustomType((char) 993));
    }
    @Test
    public void getType447(){
        assertEq(2,StringUtil.getCustomType((char) 994));
    }
    @Test
    public void getType448(){
        assertEq(1,StringUtil.getCustomType((char) 995));
    }
    @Test
    public void getType449(){
        assertEq(2,StringUtil.getCustomType((char) 996));
    }
    @Test
    public void getType450(){
        assertEq(1,StringUtil.getCustomType((char) 997));
    }
    @Test
    public void getType451(){
        assertEq(2,StringUtil.getCustomType((char) 998));
    }
    @Test
    public void getType452(){
        assertEq(1,StringUtil.getCustomType((char) 999));
    }
    @Test
    public void getType453(){
        assertEq(2,StringUtil.getCustomType((char) 1000));
    }
    @Test
    public void getType454(){
        assertEq(1,StringUtil.getCustomType((char) 1001));
    }
    @Test
    public void getType455(){
        assertEq(2,StringUtil.getCustomType((char) 1002));
    }
    @Test
    public void getType456(){
        assertEq(1,StringUtil.getCustomType((char) 1003));
    }
    @Test
    public void getType457(){
        assertEq(2,StringUtil.getCustomType((char) 1004));
    }
    @Test
    public void getType458(){
        assertEq(1,StringUtil.getCustomType((char) 1005));
    }
    @Test
    public void getType459(){
        assertEq(2,StringUtil.getCustomType((char) 1006));
    }
    @Test
    public void getType460(){
        assertEq(1,StringUtil.getCustomType((char) 1007));
    }
    @Test
    public void getType461(){
        assertEq(3,StringUtil.getCustomType((char) 1011));
    }
    @Test
    public void getType462(){
        assertEq(2,StringUtil.getCustomType((char) 1012));
    }
    @Test
    public void getType463(){
        assertEq(1,StringUtil.getCustomType((char) 1013));
    }
    @Test
    public void getType464(){
        assertEq(21,StringUtil.getCustomType((char) 1014));
    }
    @Test
    public void getType465(){
        assertEq(2,StringUtil.getCustomType((char) 1015));
    }
    @Test
    public void getType466(){
        assertEq(1,StringUtil.getCustomType((char) 1016));
    }
    @Test
    public void getType467(){
        assertEq(2,StringUtil.getCustomType((char) 1017));
    }
    @Test
    public void getType468(){
        assertEq(1,StringUtil.getCustomType((char) 1019));
    }
    @Test
    public void getType469(){
        assertEq(3,StringUtil.getCustomType((char) 1020));
    }
    @Test
    public void getType470(){
        assertEq(2,StringUtil.getCustomType((char) 1021));
    }
    @Test
    public void getType471(){
        assertEq(1,StringUtil.getCustomType((char) 1072));
    }
    @Test
    public void getType472(){
        assertEq(2,StringUtil.getCustomType((char) 1120));
    }
    @Test
    public void getType473(){
        assertEq(1,StringUtil.getCustomType((char) 1121));
    }
    @Test
    public void getType474(){
        assertEq(2,StringUtil.getCustomType((char) 1122));
    }
    @Test
    public void getType475(){
        assertEq(1,StringUtil.getCustomType((char) 1123));
    }
    @Test
    public void getType476(){
        assertEq(2,StringUtil.getCustomType((char) 1124));
    }
    @Test
    public void getType477(){
        assertEq(1,StringUtil.getCustomType((char) 1125));
    }
    @Test
    public void getType478(){
        assertEq(2,StringUtil.getCustomType((char) 1126));
    }
    @Test
    public void getType479(){
        assertEq(1,StringUtil.getCustomType((char) 1127));
    }
    @Test
    public void getType480(){
        assertEq(2,StringUtil.getCustomType((char) 1128));
    }
    @Test
    public void getType481(){
        assertEq(1,StringUtil.getCustomType((char) 1129));
    }
    @Test
    public void getType482(){
        assertEq(2,StringUtil.getCustomType((char) 1130));
    }
    @Test
    public void getType483(){
        assertEq(1,StringUtil.getCustomType((char) 1131));
    }
    @Test
    public void getType484(){
        assertEq(2,StringUtil.getCustomType((char) 1132));
    }
    @Test
    public void getType485(){
        assertEq(1,StringUtil.getCustomType((char) 1133));
    }
    @Test
    public void getType486(){
        assertEq(2,StringUtil.getCustomType((char) 1134));
    }
    @Test
    public void getType487(){
        assertEq(1,StringUtil.getCustomType((char) 1135));
    }
    @Test
    public void getType488(){
        assertEq(2,StringUtil.getCustomType((char) 1136));
    }
    @Test
    public void getType489(){
        assertEq(1,StringUtil.getCustomType((char) 1137));
    }
    @Test
    public void getType490(){
        assertEq(2,StringUtil.getCustomType((char) 1138));
    }
    @Test
    public void getType491(){
        assertEq(1,StringUtil.getCustomType((char) 1139));
    }
    @Test
    public void getType492(){
        assertEq(2,StringUtil.getCustomType((char) 1140));
    }
    @Test
    public void getType493(){
        assertEq(1,StringUtil.getCustomType((char) 1141));
    }
    @Test
    public void getType494(){
        assertEq(2,StringUtil.getCustomType((char) 1142));
    }
    @Test
    public void getType495(){
        assertEq(1,StringUtil.getCustomType((char) 1143));
    }
    @Test
    public void getType496(){
        assertEq(2,StringUtil.getCustomType((char) 1144));
    }
    @Test
    public void getType497(){
        assertEq(1,StringUtil.getCustomType((char) 1145));
    }
    @Test
    public void getType498(){
        assertEq(2,StringUtil.getCustomType((char) 1146));
    }
    @Test
    public void getType499(){
        assertEq(1,StringUtil.getCustomType((char) 1147));
    }
    @Test
    public void getType500(){
        assertEq(2,StringUtil.getCustomType((char) 1148));
    }
    @Test
    public void getType501(){
        assertEq(1,StringUtil.getCustomType((char) 1149));
    }
    @Test
    public void getType502(){
        assertEq(2,StringUtil.getCustomType((char) 1150));
    }
    @Test
    public void getType503(){
        assertEq(1,StringUtil.getCustomType((char) 1151));
    }
    @Test
    public void getType504(){
        assertEq(2,StringUtil.getCustomType((char) 1152));
    }
    @Test
    public void getType505(){
        assertEq(1,StringUtil.getCustomType((char) 1153));
    }
    @Test
    public void getType506(){
        assertEq(24,StringUtil.getCustomType((char) 1154));
    }
    @Test
    public void getType507(){
        assertEq(31,StringUtil.getCustomType((char) 1155));
    }
    @Test
    public void getType508(){
        assertEq(2,StringUtil.getCustomType((char) 1162));
    }
    @Test
    public void getType509(){
        assertEq(1,StringUtil.getCustomType((char) 1163));
    }
    @Test
    public void getType510(){
        assertEq(2,StringUtil.getCustomType((char) 1164));
    }
    @Test
    public void getType511(){
        assertEq(1,StringUtil.getCustomType((char) 1165));
    }
    @Test
    public void getType512(){
        assertEq(2,StringUtil.getCustomType((char) 1166));
    }
    @Test
    public void getType513(){
        assertEq(1,StringUtil.getCustomType((char) 1167));
    }
    @Test
    public void getType514(){
        assertEq(2,StringUtil.getCustomType((char) 1168));
    }
    @Test
    public void getType515(){
        assertEq(1,StringUtil.getCustomType((char) 1169));
    }
    @Test
    public void getType516(){
        assertEq(2,StringUtil.getCustomType((char) 1170));
    }
    @Test
    public void getType517(){
        assertEq(1,StringUtil.getCustomType((char) 1171));
    }
    @Test
    public void getType518(){
        assertEq(2,StringUtil.getCustomType((char) 1172));
    }
    @Test
    public void getType519(){
        assertEq(1,StringUtil.getCustomType((char) 1173));
    }
    @Test
    public void getType520(){
        assertEq(2,StringUtil.getCustomType((char) 1174));
    }
    @Test
    public void getType521(){
        assertEq(1,StringUtil.getCustomType((char) 1175));
    }
    @Test
    public void getType522(){
        assertEq(2,StringUtil.getCustomType((char) 1176));
    }
    @Test
    public void getType523(){
        assertEq(1,StringUtil.getCustomType((char) 1177));
    }
    @Test
    public void getType524(){
        assertEq(2,StringUtil.getCustomType((char) 1178));
    }
    @Test
    public void getType525(){
        assertEq(1,StringUtil.getCustomType((char) 1179));
    }
    @Test
    public void getType526(){
        assertEq(2,StringUtil.getCustomType((char) 1180));
    }
    @Test
    public void getType527(){
        assertEq(1,StringUtil.getCustomType((char) 1181));
    }
    @Test
    public void getType528(){
        assertEq(2,StringUtil.getCustomType((char) 1182));
    }
    @Test
    public void getType529(){
        assertEq(1,StringUtil.getCustomType((char) 1183));
    }
    @Test
    public void getType530(){
        assertEq(2,StringUtil.getCustomType((char) 1184));
    }
    @Test
    public void getType531(){
        assertEq(1,StringUtil.getCustomType((char) 1185));
    }
    @Test
    public void getType532(){
        assertEq(2,StringUtil.getCustomType((char) 1186));
    }
    @Test
    public void getType533(){
        assertEq(1,StringUtil.getCustomType((char) 1187));
    }
    @Test
    public void getType534(){
        assertEq(2,StringUtil.getCustomType((char) 1188));
    }
    @Test
    public void getType535(){
        assertEq(1,StringUtil.getCustomType((char) 1189));
    }
    @Test
    public void getType536(){
        assertEq(2,StringUtil.getCustomType((char) 1190));
    }
    @Test
    public void getType537(){
        assertEq(1,StringUtil.getCustomType((char) 1191));
    }
    @Test
    public void getType538(){
        assertEq(2,StringUtil.getCustomType((char) 1192));
    }
    @Test
    public void getType539(){
        assertEq(1,StringUtil.getCustomType((char) 1193));
    }
    @Test
    public void getType540(){
        assertEq(2,StringUtil.getCustomType((char) 1194));
    }
    @Test
    public void getType541(){
        assertEq(1,StringUtil.getCustomType((char) 1195));
    }
    @Test
    public void getType542(){
        assertEq(2,StringUtil.getCustomType((char) 1196));
    }
    @Test
    public void getType543(){
        assertEq(1,StringUtil.getCustomType((char) 1197));
    }
    @Test
    public void getType544(){
        assertEq(2,StringUtil.getCustomType((char) 1198));
    }
    @Test
    public void getType545(){
        assertEq(1,StringUtil.getCustomType((char) 1199));
    }
    @Test
    public void getType546(){
        assertEq(2,StringUtil.getCustomType((char) 1200));
    }
    @Test
    public void getType547(){
        assertEq(1,StringUtil.getCustomType((char) 1201));
    }
    @Test
    public void getType548(){
        assertEq(2,StringUtil.getCustomType((char) 1202));
    }
    @Test
    public void getType549(){
        assertEq(1,StringUtil.getCustomType((char) 1203));
    }
    @Test
    public void getType550(){
        assertEq(2,StringUtil.getCustomType((char) 1204));
    }
    @Test
    public void getType551(){
        assertEq(1,StringUtil.getCustomType((char) 1205));
    }
    @Test
    public void getType552(){
        assertEq(2,StringUtil.getCustomType((char) 1206));
    }
    @Test
    public void getType553(){
        assertEq(1,StringUtil.getCustomType((char) 1207));
    }
    @Test
    public void getType554(){
        assertEq(2,StringUtil.getCustomType((char) 1208));
    }
    @Test
    public void getType555(){
        assertEq(1,StringUtil.getCustomType((char) 1209));
    }
    @Test
    public void getType556(){
        assertEq(2,StringUtil.getCustomType((char) 1210));
    }
    @Test
    public void getType557(){
        assertEq(1,StringUtil.getCustomType((char) 1211));
    }
    @Test
    public void getType558(){
        assertEq(2,StringUtil.getCustomType((char) 1212));
    }
    @Test
    public void getType559(){
        assertEq(1,StringUtil.getCustomType((char) 1213));
    }
    @Test
    public void getType560(){
        assertEq(2,StringUtil.getCustomType((char) 1214));
    }
    @Test
    public void getType561(){
        assertEq(1,StringUtil.getCustomType((char) 1215));
    }
    @Test
    public void getType562(){
        assertEq(2,StringUtil.getCustomType((char) 1216));
    }
    @Test
    public void getType563(){
        assertEq(1,StringUtil.getCustomType((char) 1218));
    }
    @Test
    public void getType564(){
        assertEq(2,StringUtil.getCustomType((char) 1219));
    }
    @Test
    public void getType565(){
        assertEq(1,StringUtil.getCustomType((char) 1220));
    }
    @Test
    public void getType566(){
        assertEq(2,StringUtil.getCustomType((char) 1221));
    }
    @Test
    public void getType567(){
        assertEq(1,StringUtil.getCustomType((char) 1222));
    }
    @Test
    public void getType568(){
        assertEq(2,StringUtil.getCustomType((char) 1223));
    }
    @Test
    public void getType569(){
        assertEq(1,StringUtil.getCustomType((char) 1224));
    }
    @Test
    public void getType570(){
        assertEq(2,StringUtil.getCustomType((char) 1225));
    }
    @Test
    public void getType571(){
        assertEq(1,StringUtil.getCustomType((char) 1226));
    }
    @Test
    public void getType572(){
        assertEq(2,StringUtil.getCustomType((char) 1227));
    }
    @Test
    public void getType573(){
        assertEq(1,StringUtil.getCustomType((char) 1228));
    }
    @Test
    public void getType574(){
        assertEq(2,StringUtil.getCustomType((char) 1229));
    }
    @Test
    public void getType575(){
        assertEq(1,StringUtil.getCustomType((char) 1230));
    }
    @Test
    public void getType576(){
        assertEq(2,StringUtil.getCustomType((char) 1232));
    }
    @Test
    public void getType577(){
        assertEq(1,StringUtil.getCustomType((char) 1233));
    }
    @Test
    public void getType578(){
        assertEq(2,StringUtil.getCustomType((char) 1234));
    }
    @Test
    public void getType579(){
        assertEq(1,StringUtil.getCustomType((char) 1235));
    }
    @Test
    public void getType580(){
        assertEq(2,StringUtil.getCustomType((char) 1236));
    }
    @Test
    public void getType581(){
        assertEq(1,StringUtil.getCustomType((char) 1237));
    }
    @Test
    public void getType582(){
        assertEq(2,StringUtil.getCustomType((char) 1238));
    }
    @Test
    public void getType583(){
        assertEq(1,StringUtil.getCustomType((char) 1239));
    }
    @Test
    public void getType584(){
        assertEq(2,StringUtil.getCustomType((char) 1240));
    }
    @Test
    public void getType585(){
        assertEq(1,StringUtil.getCustomType((char) 1241));
    }
    @Test
    public void getType586(){
        assertEq(2,StringUtil.getCustomType((char) 1242));
    }
    @Test
    public void getType587(){
        assertEq(1,StringUtil.getCustomType((char) 1243));
    }
    @Test
    public void getType588(){
        assertEq(2,StringUtil.getCustomType((char) 1244));
    }
    @Test
    public void getType589(){
        assertEq(1,StringUtil.getCustomType((char) 1245));
    }
    @Test
    public void getType590(){
        assertEq(2,StringUtil.getCustomType((char) 1246));
    }
    @Test
    public void getType591(){
        assertEq(1,StringUtil.getCustomType((char) 1247));
    }
    @Test
    public void getType592(){
        assertEq(2,StringUtil.getCustomType((char) 1248));
    }
    @Test
    public void getType593(){
        assertEq(1,StringUtil.getCustomType((char) 1249));
    }
    @Test
    public void getType594(){
        assertEq(2,StringUtil.getCustomType((char) 1250));
    }
    @Test
    public void getType595(){
        assertEq(1,StringUtil.getCustomType((char) 1251));
    }
    @Test
    public void getType596(){
        assertEq(2,StringUtil.getCustomType((char) 1252));
    }
    @Test
    public void getType597(){
        assertEq(1,StringUtil.getCustomType((char) 1253));
    }
    @Test
    public void getType598(){
        assertEq(2,StringUtil.getCustomType((char) 1254));
    }
    @Test
    public void getType599(){
        assertEq(1,StringUtil.getCustomType((char) 1255));
    }
    @Test
    public void getType600(){
        assertEq(2,StringUtil.getCustomType((char) 1256));
    }
    @Test
    public void getType601(){
        assertEq(1,StringUtil.getCustomType((char) 1257));
    }
    @Test
    public void getType602(){
        assertEq(2,StringUtil.getCustomType((char) 1258));
    }
    @Test
    public void getType603(){
        assertEq(1,StringUtil.getCustomType((char) 1259));
    }
    @Test
    public void getType604(){
        assertEq(2,StringUtil.getCustomType((char) 1260));
    }
    @Test
    public void getType605(){
        assertEq(1,StringUtil.getCustomType((char) 1261));
    }
    @Test
    public void getType606(){
        assertEq(2,StringUtil.getCustomType((char) 1262));
    }
    @Test
    public void getType607(){
        assertEq(1,StringUtil.getCustomType((char) 1263));
    }
    @Test
    public void getType608(){
        assertEq(2,StringUtil.getCustomType((char) 1264));
    }
    @Test
    public void getType609(){
        assertEq(1,StringUtil.getCustomType((char) 1265));
    }
    @Test
    public void getType610(){
        assertEq(2,StringUtil.getCustomType((char) 1266));
    }
    @Test
    public void getType611(){
        assertEq(1,StringUtil.getCustomType((char) 1267));
    }
    @Test
    public void getType612(){
        assertEq(2,StringUtil.getCustomType((char) 1268));
    }
    @Test
    public void getType613(){
        assertEq(1,StringUtil.getCustomType((char) 1269));
    }
    @Test
    public void getType614(){
        assertEq(2,StringUtil.getCustomType((char) 1270));
    }
    @Test
    public void getType615(){
        assertEq(1,StringUtil.getCustomType((char) 1271));
    }
    @Test
    public void getType616(){
        assertEq(2,StringUtil.getCustomType((char) 1272));
    }
    @Test
    public void getType617(){
        assertEq(1,StringUtil.getCustomType((char) 1273));
    }
    @Test
    public void getType618(){
        assertEq(2,StringUtil.getCustomType((char) 1274));
    }
    @Test
    public void getType619(){
        assertEq(1,StringUtil.getCustomType((char) 1275));
    }
    @Test
    public void getType620(){
        assertEq(2,StringUtil.getCustomType((char) 1276));
    }
    @Test
    public void getType621(){
        assertEq(1,StringUtil.getCustomType((char) 1277));
    }
    @Test
    public void getType622(){
        assertEq(2,StringUtil.getCustomType((char) 1278));
    }
    @Test
    public void getType623(){
        assertEq(1,StringUtil.getCustomType((char) 1279));
    }
    @Test
    public void getType624(){
        assertEq(2,StringUtil.getCustomType((char) 1280));
    }
    @Test
    public void getType625(){
        assertEq(1,StringUtil.getCustomType((char) 1281));
    }
    @Test
    public void getType626(){
        assertEq(2,StringUtil.getCustomType((char) 1282));
    }
    @Test
    public void getType627(){
        assertEq(1,StringUtil.getCustomType((char) 1283));
    }
    @Test
    public void getType628(){
        assertEq(2,StringUtil.getCustomType((char) 1284));
    }
    @Test
    public void getType629(){
        assertEq(1,StringUtil.getCustomType((char) 1285));
    }
    @Test
    public void getType630(){
        assertEq(2,StringUtil.getCustomType((char) 1286));
    }
    @Test
    public void getType631(){
        assertEq(1,StringUtil.getCustomType((char) 1287));
    }
    @Test
    public void getType632(){
        assertEq(2,StringUtil.getCustomType((char) 1288));
    }
    @Test
    public void getType633(){
        assertEq(1,StringUtil.getCustomType((char) 1289));
    }
    @Test
    public void getType634(){
        assertEq(2,StringUtil.getCustomType((char) 1290));
    }
    @Test
    public void getType635(){
        assertEq(1,StringUtil.getCustomType((char) 1291));
    }
    @Test
    public void getType636(){
        assertEq(2,StringUtil.getCustomType((char) 1292));
    }
    @Test
    public void getType637(){
        assertEq(1,StringUtil.getCustomType((char) 1293));
    }
    @Test
    public void getType638(){
        assertEq(2,StringUtil.getCustomType((char) 1294));
    }
    @Test
    public void getType639(){
        assertEq(1,StringUtil.getCustomType((char) 1295));
    }
    @Test
    public void getType640(){
        assertEq(2,StringUtil.getCustomType((char) 1296));
    }
    @Test
    public void getType641(){
        assertEq(1,StringUtil.getCustomType((char) 1297));
    }
    @Test
    public void getType642(){
        assertEq(2,StringUtil.getCustomType((char) 1298));
    }
    @Test
    public void getType643(){
        assertEq(1,StringUtil.getCustomType((char) 1299));
    }
    @Test
    public void getType644(){
        assertEq(2,StringUtil.getCustomType((char) 1300));
    }
    @Test
    public void getType645(){
        assertEq(1,StringUtil.getCustomType((char) 1301));
    }
    @Test
    public void getType646(){
        assertEq(2,StringUtil.getCustomType((char) 1302));
    }
    @Test
    public void getType647(){
        assertEq(1,StringUtil.getCustomType((char) 1303));
    }
    @Test
    public void getType648(){
        assertEq(2,StringUtil.getCustomType((char) 1304));
    }
    @Test
    public void getType649(){
        assertEq(1,StringUtil.getCustomType((char) 1305));
    }
    @Test
    public void getType650(){
        assertEq(2,StringUtil.getCustomType((char) 1306));
    }
    @Test
    public void getType651(){
        assertEq(1,StringUtil.getCustomType((char) 1307));
    }
    @Test
    public void getType652(){
        assertEq(2,StringUtil.getCustomType((char) 1308));
    }
    @Test
    public void getType653(){
        assertEq(1,StringUtil.getCustomType((char) 1309));
    }
    @Test
    public void getType654(){
        assertEq(2,StringUtil.getCustomType((char) 1310));
    }
    @Test
    public void getType655(){
        assertEq(1,StringUtil.getCustomType((char) 1311));
    }
    @Test
    public void getType656(){
        assertEq(2,StringUtil.getCustomType((char) 1312));
    }
    @Test
    public void getType657(){
        assertEq(1,StringUtil.getCustomType((char) 1313));
    }
    @Test
    public void getType658(){
        assertEq(2,StringUtil.getCustomType((char) 1314));
    }
    @Test
    public void getType659(){
        assertEq(1,StringUtil.getCustomType((char) 1315));
    }
    @Test
    public void getType660(){
        assertEq(2,StringUtil.getCustomType((char) 1316));
    }
    @Test
    public void getType661(){
        assertEq(1,StringUtil.getCustomType((char) 1317));
    }
    @Test
    public void getType662(){
        assertEq(2,StringUtil.getCustomType((char) 1318));
    }
    @Test
    public void getType663(){
        assertEq(1,StringUtil.getCustomType((char) 1319));
    }
    @Test
    public void getType664(){
        assertEq(32,StringUtil.getCustomType((char) 1320));
    }
    @Test
    public void getType665(){
        assertEq(2,StringUtil.getCustomType((char) 1329));
    }
    @Test
    public void getType666(){
        assertEq(32,StringUtil.getCustomType((char) 1367));
    }
    @Test
    public void getType667(){
        assertEq(6,StringUtil.getCustomType((char) 1369));
    }
    @Test
    public void getType668(){
        assertEq(28,StringUtil.getCustomType((char) 1370));
    }
    @Test
    public void getType669(){
        assertEq(32,StringUtil.getCustomType((char) 1376));
    }
    @Test
    public void getType670(){
        assertEq(1,StringUtil.getCustomType((char) 1377));
    }
    @Test
    public void getType671(){
        assertEq(3,StringUtil.getCustomType((char) 1415));
    }
    @Test
    public void getType672(){
        assertEq(32,StringUtil.getCustomType((char) 1416));
    }
    @Test
    public void getType673(){
        assertEq(28,StringUtil.getCustomType((char) 1417));
    }
    @Test
    public void getType674(){
        assertEq(25,StringUtil.getCustomType((char) 1418));
    }
    @Test
    public void getType675(){
        assertEq(32,StringUtil.getCustomType((char) 1419));
    }
    @Test
    public void getType676(){
        assertEq(12,StringUtil.getCustomType((char) 1423));
    }
    @Test
    public void getType677(){
        assertEq(32,StringUtil.getCustomType((char) 1424));
    }
    @Test
    public void getType678(){
        assertEq(31,StringUtil.getCustomType((char) 1425));
    }
    @Test
    public void getType679(){
        assertEq(25,StringUtil.getCustomType((char) 1470));
    }
    @Test
    public void getType680(){
        assertEq(31,StringUtil.getCustomType((char) 1471));
    }
    @Test
    public void getType681(){
        assertEq(28,StringUtil.getCustomType((char) 1472));
    }
    @Test
    public void getType682(){
        assertEq(31,StringUtil.getCustomType((char) 1473));
    }
    @Test
    public void getType683(){
        assertEq(28,StringUtil.getCustomType((char) 1475));
    }
    @Test
    public void getType684(){
        assertEq(31,StringUtil.getCustomType((char) 1476));
    }
    @Test
    public void getType685(){
        assertEq(28,StringUtil.getCustomType((char) 1478));
    }
    @Test
    public void getType686(){
        assertEq(31,StringUtil.getCustomType((char) 1479));
    }
    @Test
    public void getType687(){
        assertEq(32,StringUtil.getCustomType((char) 1480));
    }
    @Test
    public void getType688(){
        assertEq(8,StringUtil.getCustomType((char) 1488));
    }
    @Test
    public void getType689(){
        assertEq(32,StringUtil.getCustomType((char) 1515));
    }
    @Test
    public void getType690(){
        assertEq(8,StringUtil.getCustomType((char) 1520));
    }
    @Test
    public void getType691(){
        assertEq(28,StringUtil.getCustomType((char) 1523));
    }
    @Test
    public void getType692(){
        assertEq(32,StringUtil.getCustomType((char) 1525));
    }
    @Test
    public void getType693(){
        assertEq(31,StringUtil.getCustomType((char) 1536));
    }
    @Test
    public void getType694(){
        assertEq(32,StringUtil.getCustomType((char) 1541));
    }
    @Test
    public void getType695(){
        assertEq(21,StringUtil.getCustomType((char) 1542));
    }
    @Test
    public void getType696(){
        assertEq(28,StringUtil.getCustomType((char) 1545));
    }
    @Test
    public void getType697(){
        assertEq(12,StringUtil.getCustomType((char) 1547));
    }
    @Test
    public void getType698(){
        assertEq(28,StringUtil.getCustomType((char) 1548));
    }
    @Test
    public void getType699(){
        assertEq(24,StringUtil.getCustomType((char) 1550));
    }
    @Test
    public void getType700(){
        assertEq(31,StringUtil.getCustomType((char) 1552));
    }
    @Test
    public void getType701(){
        assertEq(28,StringUtil.getCustomType((char) 1563));
    }
    @Test
    public void getType702(){
        assertEq(32,StringUtil.getCustomType((char) 1564));
    }
    @Test
    public void getType703(){
        assertEq(28,StringUtil.getCustomType((char) 1566));
    }
    @Test
    public void getType704(){
        assertEq(8,StringUtil.getCustomType((char) 1568));
    }
    @Test
    public void getType705(){
        assertEq(31,StringUtil.getCustomType((char) 1611));
    }
    @Test
    public void getType706(){
        assertEq(10,StringUtil.getCustomType((char) 1632));
    }
    @Test
    public void getType707(){
        assertEq(28,StringUtil.getCustomType((char) 1642));
    }
    @Test
    public void getType708(){
        assertEq(8,StringUtil.getCustomType((char) 1646));
    }
    @Test
    public void getType709(){
        assertEq(31,StringUtil.getCustomType((char) 1648));
    }
    @Test
    public void getType710(){
        assertEq(8,StringUtil.getCustomType((char) 1649));
    }
    @Test
    public void getType711(){
        assertEq(28,StringUtil.getCustomType((char) 1748));
    }
    @Test
    public void getType712(){
        assertEq(8,StringUtil.getCustomType((char) 1749));
    }
    @Test
    public void getType713(){
        assertEq(31,StringUtil.getCustomType((char) 1750));
    }
    @Test
    public void getType714(){
        assertEq(24,StringUtil.getCustomType((char) 1758));
    }
    @Test
    public void getType715(){
        assertEq(31,StringUtil.getCustomType((char) 1759));
    }
    @Test
    public void getType716(){
        assertEq(8,StringUtil.getCustomType((char) 1765));
    }
    @Test
    public void getType717(){
        assertEq(31,StringUtil.getCustomType((char) 1767));
    }
    @Test
    public void getType718(){
        assertEq(24,StringUtil.getCustomType((char) 1769));
    }
    @Test
    public void getType719(){
        assertEq(31,StringUtil.getCustomType((char) 1770));
    }
    @Test
    public void getType720(){
        assertEq(8,StringUtil.getCustomType((char) 1774));
    }
    @Test
    public void getType721(){
        assertEq(10,StringUtil.getCustomType((char) 1776));
    }
    @Test
    public void getType722(){
        assertEq(8,StringUtil.getCustomType((char) 1786));
    }
    @Test
    public void getType723(){
        assertEq(24,StringUtil.getCustomType((char) 1789));
    }
    @Test
    public void getType724(){
        assertEq(8,StringUtil.getCustomType((char) 1791));
    }
    @Test
    public void getType725(){
        assertEq(28,StringUtil.getCustomType((char) 1792));
    }
    @Test
    public void getType726(){
        assertEq(32,StringUtil.getCustomType((char) 1806));
    }
    @Test
    public void getType727(){
        assertEq(31,StringUtil.getCustomType((char) 1807));
    }
    @Test
    public void getType728(){
        assertEq(8,StringUtil.getCustomType((char) 1808));
    }
    @Test
    public void getType729(){
        assertEq(31,StringUtil.getCustomType((char) 1809));
    }
    @Test
    public void getType730(){
        assertEq(8,StringUtil.getCustomType((char) 1810));
    }
    @Test
    public void getType731(){
        assertEq(31,StringUtil.getCustomType((char) 1840));
    }
    @Test
    public void getType732(){
        assertEq(32,StringUtil.getCustomType((char) 1867));
    }
    @Test
    public void getType733(){
        assertEq(8,StringUtil.getCustomType((char) 1869));
    }
    @Test
    public void getType734(){
        assertEq(31,StringUtil.getCustomType((char) 1958));
    }
    @Test
    public void getType735(){
        assertEq(8,StringUtil.getCustomType((char) 1969));
    }
    @Test
    public void getType736(){
        assertEq(32,StringUtil.getCustomType((char) 1970));
    }
    @Test
    public void getType737(){
        assertEq(10,StringUtil.getCustomType((char) 1984));
    }
    @Test
    public void getType738(){
        assertEq(8,StringUtil.getCustomType((char) 1994));
    }
    @Test
    public void getType739(){
        assertEq(31,StringUtil.getCustomType((char) 2027));
    }
    @Test
    public void getType740(){
        assertEq(8,StringUtil.getCustomType((char) 2036));
    }
    @Test
    public void getType741(){
        assertEq(24,StringUtil.getCustomType((char) 2038));
    }
    @Test
    public void getType742(){
        assertEq(28,StringUtil.getCustomType((char) 2039));
    }
    @Test
    public void getType743(){
        assertEq(8,StringUtil.getCustomType((char) 2042));
    }
    @Test
    public void getType744(){
        assertEq(32,StringUtil.getCustomType((char) 2043));
    }
    @Test
    public void getType745(){
        assertEq(8,StringUtil.getCustomType((char) 2048));
    }
    @Test
    public void getType746(){
        assertEq(31,StringUtil.getCustomType((char) 2070));
    }
    @Test
    public void getType747(){
        assertEq(8,StringUtil.getCustomType((char) 2074));
    }
    @Test
    public void getType748(){
        assertEq(31,StringUtil.getCustomType((char) 2075));
    }
    @Test
    public void getType749(){
        assertEq(8,StringUtil.getCustomType((char) 2084));
    }
    @Test
    public void getType750(){
        assertEq(31,StringUtil.getCustomType((char) 2085));
    }
    @Test
    public void getType751(){
        assertEq(8,StringUtil.getCustomType((char) 2088));
    }
    @Test
    public void getType752(){
        assertEq(31,StringUtil.getCustomType((char) 2089));
    }
    @Test
    public void getType753(){
        assertEq(32,StringUtil.getCustomType((char) 2094));
    }
    @Test
    public void getType754(){
        assertEq(28,StringUtil.getCustomType((char) 2096));
    }
    @Test
    public void getType755(){
        assertEq(32,StringUtil.getCustomType((char) 2111));
    }
    @Test
    public void getType756(){
        assertEq(8,StringUtil.getCustomType((char) 2112));
    }
    @Test
    public void getType757(){
        assertEq(31,StringUtil.getCustomType((char) 2137));
    }
    @Test
    public void getType758(){
        assertEq(32,StringUtil.getCustomType((char) 2140));
    }
    @Test
    public void getType759(){
        assertEq(28,StringUtil.getCustomType((char) 2142));
    }
    @Test
    public void getType760(){
        assertEq(32,StringUtil.getCustomType((char) 2143));
    }
    @Test
    public void getType761(){
        assertEq(8,StringUtil.getCustomType((char) 2208));
    }
    @Test
    public void getType762(){
        assertEq(32,StringUtil.getCustomType((char) 2209));
    }
    @Test
    public void getType763(){
        assertEq(8,StringUtil.getCustomType((char) 2210));
    }
    @Test
    public void getType764(){
        assertEq(32,StringUtil.getCustomType((char) 2221));
    }
    @Test
    public void getType765(){
        assertEq(31,StringUtil.getCustomType((char) 2276));
    }
    @Test
    public void getType766(){
        assertEq(32,StringUtil.getCustomType((char) 2303));
    }
    @Test
    public void getType767(){
        assertEq(31,StringUtil.getCustomType((char) 2304));
    }
    @Test
    public void getType768(){
        assertEq(6,StringUtil.getCustomType((char) 2308));
    }
    @Test
    public void getType769(){
        assertEq(31,StringUtil.getCustomType((char) 2362));
    }
    @Test
    public void getType770(){
        assertEq(6,StringUtil.getCustomType((char) 2365));
    }
    @Test
    public void getType771(){
        assertEq(31,StringUtil.getCustomType((char) 2366));
    }
    @Test
    public void getType772(){
        assertEq(6,StringUtil.getCustomType((char) 2384));
    }
    @Test
    public void getType773(){
        assertEq(31,StringUtil.getCustomType((char) 2385));
    }
    @Test
    public void getType774(){
        assertEq(6,StringUtil.getCustomType((char) 2392));
    }
    @Test
    public void getType775(){
        assertEq(31,StringUtil.getCustomType((char) 2402));
    }
    @Test
    public void getType776(){
        assertEq(28,StringUtil.getCustomType((char) 2404));
    }
    @Test
    public void getType777(){
        assertEq(10,StringUtil.getCustomType((char) 2406));
    }
    @Test
    public void getType778(){
        assertEq(28,StringUtil.getCustomType((char) 2416));
    }
    @Test
    public void getType779(){
        assertEq(6,StringUtil.getCustomType((char) 2417));
    }
    @Test
    public void getType780(){
        assertEq(32,StringUtil.getCustomType((char) 2424));
    }
    @Test
    public void getType781(){
        assertEq(6,StringUtil.getCustomType((char) 2425));
    }
    @Test
    public void getType782(){
        assertEq(32,StringUtil.getCustomType((char) 2432));
    }
    @Test
    public void getType783(){
        assertEq(31,StringUtil.getCustomType((char) 2433));
    }
    @Test
    public void getType784(){
        assertEq(32,StringUtil.getCustomType((char) 2436));
    }
    @Test
    public void getType785(){
        assertEq(6,StringUtil.getCustomType((char) 2437));
    }
    @Test
    public void getType786(){
        assertEq(32,StringUtil.getCustomType((char) 2445));
    }
    @Test
    public void getType787(){
        assertEq(6,StringUtil.getCustomType((char) 2447));
    }
    @Test
    public void getType788(){
        assertEq(32,StringUtil.getCustomType((char) 2449));
    }
    @Test
    public void getType789(){
        assertEq(6,StringUtil.getCustomType((char) 2451));
    }
    @Test
    public void getType790(){
        assertEq(32,StringUtil.getCustomType((char) 2473));
    }
    @Test
    public void getType791(){
        assertEq(6,StringUtil.getCustomType((char) 2474));
    }
    @Test
    public void getType792(){
        assertEq(32,StringUtil.getCustomType((char) 2481));
    }
    @Test
    public void getType793(){
        assertEq(6,StringUtil.getCustomType((char) 2482));
    }
    @Test
    public void getType794(){
        assertEq(32,StringUtil.getCustomType((char) 2483));
    }
    @Test
    public void getType795(){
        assertEq(6,StringUtil.getCustomType((char) 2486));
    }
    @Test
    public void getType796(){
        assertEq(32,StringUtil.getCustomType((char) 2490));
    }
    @Test
    public void getType797(){
        assertEq(31,StringUtil.getCustomType((char) 2492));
    }
    @Test
    public void getType798(){
        assertEq(6,StringUtil.getCustomType((char) 2493));
    }
    @Test
    public void getType799(){
        assertEq(31,StringUtil.getCustomType((char) 2494));
    }
    @Test
    public void getType800(){
        assertEq(32,StringUtil.getCustomType((char) 2501));
    }
    @Test
    public void getType801(){
        assertEq(31,StringUtil.getCustomType((char) 2503));
    }
    @Test
    public void getType802(){
        assertEq(32,StringUtil.getCustomType((char) 2505));
    }
    @Test
    public void getType803(){
        assertEq(31,StringUtil.getCustomType((char) 2507));
    }
    @Test
    public void getType804(){
        assertEq(6,StringUtil.getCustomType((char) 2510));
    }
    @Test
    public void getType805(){
        assertEq(32,StringUtil.getCustomType((char) 2511));
    }
    @Test
    public void getType806(){
        assertEq(31,StringUtil.getCustomType((char) 2519));
    }
    @Test
    public void getType807(){
        assertEq(32,StringUtil.getCustomType((char) 2520));
    }
    @Test
    public void getType808(){
        assertEq(6,StringUtil.getCustomType((char) 2524));
    }
    @Test
    public void getType809(){
        assertEq(32,StringUtil.getCustomType((char) 2526));
    }
    @Test
    public void getType810(){
        assertEq(6,StringUtil.getCustomType((char) 2527));
    }
    @Test
    public void getType811(){
        assertEq(31,StringUtil.getCustomType((char) 2530));
    }
    @Test
    public void getType812(){
        assertEq(32,StringUtil.getCustomType((char) 2532));
    }
    @Test
    public void getType813(){
        assertEq(10,StringUtil.getCustomType((char) 2534));
    }
    @Test
    public void getType814(){
        assertEq(6,StringUtil.getCustomType((char) 2544));
    }
    @Test
    public void getType815(){
        assertEq(12,StringUtil.getCustomType((char) 2546));
    }
    @Test
    public void getType816(){
        assertEq(31,StringUtil.getCustomType((char) 2548));
    }
    @Test
    public void getType817(){
        assertEq(24,StringUtil.getCustomType((char) 2554));
    }
    @Test
    public void getType818(){
        assertEq(12,StringUtil.getCustomType((char) 2555));
    }
    @Test
    public void getType819(){
        assertEq(32,StringUtil.getCustomType((char) 2556));
    }
    @Test
    public void getType820(){
        assertEq(31,StringUtil.getCustomType((char) 2561));
    }
    @Test
    public void getType821(){
        assertEq(32,StringUtil.getCustomType((char) 2564));
    }
    @Test
    public void getType822(){
        assertEq(6,StringUtil.getCustomType((char) 2565));
    }
    @Test
    public void getType823(){
        assertEq(32,StringUtil.getCustomType((char) 2571));
    }
    @Test
    public void getType824(){
        assertEq(6,StringUtil.getCustomType((char) 2575));
    }
    @Test
    public void getType825(){
        assertEq(32,StringUtil.getCustomType((char) 2577));
    }
    @Test
    public void getType826(){
        assertEq(6,StringUtil.getCustomType((char) 2579));
    }
    @Test
    public void getType827(){
        assertEq(32,StringUtil.getCustomType((char) 2601));
    }
    @Test
    public void getType828(){
        assertEq(6,StringUtil.getCustomType((char) 2602));
    }
    @Test
    public void getType829(){
        assertEq(32,StringUtil.getCustomType((char) 2609));
    }
    @Test
    public void getType830(){
        assertEq(6,StringUtil.getCustomType((char) 2610));
    }
    @Test
    public void getType831(){
        assertEq(32,StringUtil.getCustomType((char) 2612));
    }
    @Test
    public void getType832(){
        assertEq(6,StringUtil.getCustomType((char) 2613));
    }
    @Test
    public void getType833(){
        assertEq(32,StringUtil.getCustomType((char) 2615));
    }
    @Test
    public void getType834(){
        assertEq(6,StringUtil.getCustomType((char) 2616));
    }
    @Test
    public void getType835(){
        assertEq(32,StringUtil.getCustomType((char) 2618));
    }
    @Test
    public void getType836(){
        assertEq(31,StringUtil.getCustomType((char) 2620));
    }
    @Test
    public void getType837(){
        assertEq(32,StringUtil.getCustomType((char) 2621));
    }
    @Test
    public void getType838(){
        assertEq(31,StringUtil.getCustomType((char) 2622));
    }
    @Test
    public void getType839(){
        assertEq(32,StringUtil.getCustomType((char) 2627));
    }
    @Test
    public void getType840(){
        assertEq(31,StringUtil.getCustomType((char) 2631));
    }
    @Test
    public void getType841(){
        assertEq(32,StringUtil.getCustomType((char) 2633));
    }
    @Test
    public void getType842(){
        assertEq(31,StringUtil.getCustomType((char) 2635));
    }
    @Test
    public void getType843(){
        assertEq(32,StringUtil.getCustomType((char) 2638));
    }
    @Test
    public void getType844(){
        assertEq(31,StringUtil.getCustomType((char) 2641));
    }
    @Test
    public void getType845(){
        assertEq(32,StringUtil.getCustomType((char) 2642));
    }
    @Test
    public void getType846(){
        assertEq(6,StringUtil.getCustomType((char) 2649));
    }
    @Test
    public void getType847(){
        assertEq(32,StringUtil.getCustomType((char) 2653));
    }
    @Test
    public void getType848(){
        assertEq(6,StringUtil.getCustomType((char) 2654));
    }
    @Test
    public void getType849(){
        assertEq(32,StringUtil.getCustomType((char) 2655));
    }
    @Test
    public void getType850(){
        assertEq(10,StringUtil.getCustomType((char) 2662));
    }
    @Test
    public void getType851(){
        assertEq(31,StringUtil.getCustomType((char) 2672));
    }
    @Test
    public void getType852(){
        assertEq(6,StringUtil.getCustomType((char) 2674));
    }
    @Test
    public void getType853(){
        assertEq(31,StringUtil.getCustomType((char) 2677));
    }
    @Test
    public void getType854(){
        assertEq(32,StringUtil.getCustomType((char) 2678));
    }
    @Test
    public void getType855(){
        assertEq(31,StringUtil.getCustomType((char) 2689));
    }
    @Test
    public void getType856(){
        assertEq(32,StringUtil.getCustomType((char) 2692));
    }
    @Test
    public void getType857(){
        assertEq(6,StringUtil.getCustomType((char) 2693));
    }
    @Test
    public void getType858(){
        assertEq(32,StringUtil.getCustomType((char) 2702));
    }
    @Test
    public void getType859(){
        assertEq(6,StringUtil.getCustomType((char) 2703));
    }
    @Test
    public void getType860(){
        assertEq(32,StringUtil.getCustomType((char) 2706));
    }
    @Test
    public void getType861(){
        assertEq(6,StringUtil.getCustomType((char) 2707));
    }
    @Test
    public void getType862(){
        assertEq(32,StringUtil.getCustomType((char) 2729));
    }
    @Test
    public void getType863(){
        assertEq(6,StringUtil.getCustomType((char) 2730));
    }
    @Test
    public void getType864(){
        assertEq(32,StringUtil.getCustomType((char) 2737));
    }
    @Test
    public void getType865(){
        assertEq(6,StringUtil.getCustomType((char) 2738));
    }
    @Test
    public void getType866(){
        assertEq(32,StringUtil.getCustomType((char) 2740));
    }
    @Test
    public void getType867(){
        assertEq(6,StringUtil.getCustomType((char) 2741));
    }
    @Test
    public void getType868(){
        assertEq(32,StringUtil.getCustomType((char) 2746));
    }
    @Test
    public void getType869(){
        assertEq(31,StringUtil.getCustomType((char) 2748));
    }
    @Test
    public void getType870(){
        assertEq(6,StringUtil.getCustomType((char) 2749));
    }
    @Test
    public void getType871(){
        assertEq(31,StringUtil.getCustomType((char) 2750));
    }
    @Test
    public void getType872(){
        assertEq(32,StringUtil.getCustomType((char) 2758));
    }
    @Test
    public void getType873(){
        assertEq(31,StringUtil.getCustomType((char) 2759));
    }
    @Test
    public void getType874(){
        assertEq(32,StringUtil.getCustomType((char) 2762));
    }
    @Test
    public void getType875(){
        assertEq(31,StringUtil.getCustomType((char) 2763));
    }
    @Test
    public void getType876(){
        assertEq(32,StringUtil.getCustomType((char) 2766));
    }
    @Test
    public void getType877(){
        assertEq(6,StringUtil.getCustomType((char) 2768));
    }
    @Test
    public void getType878(){
        assertEq(32,StringUtil.getCustomType((char) 2769));
    }
    @Test
    public void getType879(){
        assertEq(6,StringUtil.getCustomType((char) 2784));
    }
    @Test
    public void getType880(){
        assertEq(31,StringUtil.getCustomType((char) 2786));
    }
    @Test
    public void getType881(){
        assertEq(32,StringUtil.getCustomType((char) 2788));
    }
    @Test
    public void getType882(){
        assertEq(10,StringUtil.getCustomType((char) 2790));
    }
    @Test
    public void getType883(){
        assertEq(28,StringUtil.getCustomType((char) 2800));
    }
    @Test
    public void getType884(){
        assertEq(12,StringUtil.getCustomType((char) 2801));
    }
    @Test
    public void getType885(){
        assertEq(32,StringUtil.getCustomType((char) 2802));
    }
    @Test
    public void getType886(){
        assertEq(31,StringUtil.getCustomType((char) 2817));
    }
    @Test
    public void getType887(){
        assertEq(32,StringUtil.getCustomType((char) 2820));
    }
    @Test
    public void getType888(){
        assertEq(6,StringUtil.getCustomType((char) 2821));
    }
    @Test
    public void getType889(){
        assertEq(32,StringUtil.getCustomType((char) 2829));
    }
    @Test
    public void getType890(){
        assertEq(6,StringUtil.getCustomType((char) 2831));
    }
    @Test
    public void getType891(){
        assertEq(32,StringUtil.getCustomType((char) 2833));
    }
    @Test
    public void getType892(){
        assertEq(6,StringUtil.getCustomType((char) 2835));
    }
    @Test
    public void getType893(){
        assertEq(32,StringUtil.getCustomType((char) 2857));
    }
    @Test
    public void getType894(){
        assertEq(6,StringUtil.getCustomType((char) 2858));
    }
    @Test
    public void getType895(){
        assertEq(32,StringUtil.getCustomType((char) 2865));
    }
    @Test
    public void getType896(){
        assertEq(6,StringUtil.getCustomType((char) 2866));
    }
    @Test
    public void getType897(){
        assertEq(32,StringUtil.getCustomType((char) 2868));
    }
    @Test
    public void getType898(){
        assertEq(6,StringUtil.getCustomType((char) 2869));
    }
    @Test
    public void getType899(){
        assertEq(32,StringUtil.getCustomType((char) 2874));
    }
    @Test
    public void getType900(){
        assertEq(31,StringUtil.getCustomType((char) 2876));
    }
    @Test
    public void getType901(){
        assertEq(6,StringUtil.getCustomType((char) 2877));
    }
    @Test
    public void getType902(){
        assertEq(31,StringUtil.getCustomType((char) 2878));
    }
    @Test
    public void getType903(){
        assertEq(32,StringUtil.getCustomType((char) 2885));
    }
    @Test
    public void getType904(){
        assertEq(31,StringUtil.getCustomType((char) 2887));
    }
    @Test
    public void getType905(){
        assertEq(32,StringUtil.getCustomType((char) 2889));
    }
    @Test
    public void getType906(){
        assertEq(31,StringUtil.getCustomType((char) 2891));
    }
    @Test
    public void getType907(){
        assertEq(32,StringUtil.getCustomType((char) 2894));
    }
    @Test
    public void getType908(){
        assertEq(31,StringUtil.getCustomType((char) 2902));
    }
    @Test
    public void getType909(){
        assertEq(32,StringUtil.getCustomType((char) 2904));
    }
    @Test
    public void getType910(){
        assertEq(6,StringUtil.getCustomType((char) 2908));
    }
    @Test
    public void getType911(){
        assertEq(32,StringUtil.getCustomType((char) 2910));
    }
    @Test
    public void getType912(){
        assertEq(6,StringUtil.getCustomType((char) 2911));
    }
    @Test
    public void getType913(){
        assertEq(31,StringUtil.getCustomType((char) 2914));
    }
    @Test
    public void getType914(){
        assertEq(32,StringUtil.getCustomType((char) 2916));
    }
    @Test
    public void getType915(){
        assertEq(10,StringUtil.getCustomType((char) 2918));
    }
    @Test
    public void getType916(){
        assertEq(24,StringUtil.getCustomType((char) 2928));
    }
    @Test
    public void getType917(){
        assertEq(6,StringUtil.getCustomType((char) 2929));
    }
    @Test
    public void getType918(){
        assertEq(31,StringUtil.getCustomType((char) 2930));
    }
    @Test
    public void getType919(){
        assertEq(32,StringUtil.getCustomType((char) 2936));
    }
    @Test
    public void getType920(){
        assertEq(31,StringUtil.getCustomType((char) 2946));
    }
    @Test
    public void getType921(){
        assertEq(6,StringUtil.getCustomType((char) 2947));
    }
    @Test
    public void getType922(){
        assertEq(32,StringUtil.getCustomType((char) 2948));
    }
    @Test
    public void getType923(){
        assertEq(6,StringUtil.getCustomType((char) 2949));
    }
    @Test
    public void getType924(){
        assertEq(32,StringUtil.getCustomType((char) 2955));
    }
    @Test
    public void getType925(){
        assertEq(6,StringUtil.getCustomType((char) 2958));
    }
    @Test
    public void getType926(){
        assertEq(32,StringUtil.getCustomType((char) 2961));
    }
    @Test
    public void getType927(){
        assertEq(6,StringUtil.getCustomType((char) 2962));
    }
    @Test
    public void getType928(){
        assertEq(32,StringUtil.getCustomType((char) 2966));
    }
    @Test
    public void getType929(){
        assertEq(6,StringUtil.getCustomType((char) 2969));
    }
    @Test
    public void getType930(){
        assertEq(32,StringUtil.getCustomType((char) 2971));
    }
    @Test
    public void getType931(){
        assertEq(6,StringUtil.getCustomType((char) 2972));
    }
    @Test
    public void getType932(){
        assertEq(32,StringUtil.getCustomType((char) 2973));
    }
    @Test
    public void getType933(){
        assertEq(6,StringUtil.getCustomType((char) 2974));
    }
    @Test
    public void getType934(){
        assertEq(32,StringUtil.getCustomType((char) 2976));
    }
    @Test
    public void getType935(){
        assertEq(6,StringUtil.getCustomType((char) 2979));
    }
    @Test
    public void getType936(){
        assertEq(32,StringUtil.getCustomType((char) 2981));
    }
    @Test
    public void getType937(){
        assertEq(6,StringUtil.getCustomType((char) 2984));
    }
    @Test
    public void getType938(){
        assertEq(32,StringUtil.getCustomType((char) 2987));
    }
    @Test
    public void getType939(){
        assertEq(6,StringUtil.getCustomType((char) 2990));
    }
    @Test
    public void getType940(){
        assertEq(32,StringUtil.getCustomType((char) 3002));
    }
    @Test
    public void getType941(){
        assertEq(31,StringUtil.getCustomType((char) 3006));
    }
    @Test
    public void getType942(){
        assertEq(32,StringUtil.getCustomType((char) 3011));
    }
    @Test
    public void getType943(){
        assertEq(31,StringUtil.getCustomType((char) 3014));
    }
    @Test
    public void getType944(){
        assertEq(32,StringUtil.getCustomType((char) 3017));
    }
    @Test
    public void getType945(){
        assertEq(31,StringUtil.getCustomType((char) 3018));
    }
    @Test
    public void getType946(){
        assertEq(32,StringUtil.getCustomType((char) 3022));
    }
    @Test
    public void getType947(){
        assertEq(6,StringUtil.getCustomType((char) 3024));
    }
    @Test
    public void getType948(){
        assertEq(32,StringUtil.getCustomType((char) 3025));
    }
    @Test
    public void getType949(){
        assertEq(31,StringUtil.getCustomType((char) 3031));
    }
    @Test
    public void getType950(){
        assertEq(32,StringUtil.getCustomType((char) 3032));
    }
    @Test
    public void getType951(){
        assertEq(10,StringUtil.getCustomType((char) 3046));
    }
    @Test
    public void getType952(){
        assertEq(31,StringUtil.getCustomType((char) 3056));
    }
    @Test
    public void getType953(){
        assertEq(24,StringUtil.getCustomType((char) 3059));
    }
    @Test
    public void getType954(){
        assertEq(12,StringUtil.getCustomType((char) 3065));
    }
    @Test
    public void getType955(){
        assertEq(24,StringUtil.getCustomType((char) 3066));
    }
    @Test
    public void getType956(){
        assertEq(32,StringUtil.getCustomType((char) 3067));
    }
    @Test
    public void getType957(){
        assertEq(31,StringUtil.getCustomType((char) 3073));
    }
    @Test
    public void getType958(){
        assertEq(32,StringUtil.getCustomType((char) 3076));
    }
    @Test
    public void getType959(){
        assertEq(6,StringUtil.getCustomType((char) 3077));
    }
    @Test
    public void getType960(){
        assertEq(32,StringUtil.getCustomType((char) 3085));
    }
    @Test
    public void getType961(){
        assertEq(6,StringUtil.getCustomType((char) 3086));
    }
    @Test
    public void getType962(){
        assertEq(32,StringUtil.getCustomType((char) 3089));
    }
    @Test
    public void getType963(){
        assertEq(6,StringUtil.getCustomType((char) 3090));
    }
    @Test
    public void getType964(){
        assertEq(32,StringUtil.getCustomType((char) 3113));
    }
    @Test
    public void getType965(){
        assertEq(6,StringUtil.getCustomType((char) 3114));
    }
    @Test
    public void getType966(){
        assertEq(32,StringUtil.getCustomType((char) 3124));
    }
    @Test
    public void getType967(){
        assertEq(6,StringUtil.getCustomType((char) 3125));
    }
    @Test
    public void getType968(){
        assertEq(32,StringUtil.getCustomType((char) 3130));
    }
    @Test
    public void getType969(){
        assertEq(6,StringUtil.getCustomType((char) 3133));
    }
    @Test
    public void getType970(){
        assertEq(31,StringUtil.getCustomType((char) 3134));
    }
    @Test
    public void getType971(){
        assertEq(32,StringUtil.getCustomType((char) 3141));
    }
    @Test
    public void getType972(){
        assertEq(31,StringUtil.getCustomType((char) 3142));
    }
    @Test
    public void getType973(){
        assertEq(32,StringUtil.getCustomType((char) 3145));
    }
    @Test
    public void getType974(){
        assertEq(31,StringUtil.getCustomType((char) 3146));
    }
    @Test
    public void getType975(){
        assertEq(32,StringUtil.getCustomType((char) 3150));
    }
    @Test
    public void getType976(){
        assertEq(31,StringUtil.getCustomType((char) 3157));
    }
    @Test
    public void getType977(){
        assertEq(32,StringUtil.getCustomType((char) 3159));
    }
    @Test
    public void getType978(){
        assertEq(6,StringUtil.getCustomType((char) 3160));
    }
    @Test
    public void getType979(){
        assertEq(32,StringUtil.getCustomType((char) 3162));
    }
    @Test
    public void getType980(){
        assertEq(6,StringUtil.getCustomType((char) 3168));
    }
    @Test
    public void getType981(){
        assertEq(31,StringUtil.getCustomType((char) 3170));
    }
    @Test
    public void getType982(){
        assertEq(32,StringUtil.getCustomType((char) 3172));
    }
    @Test
    public void getType983(){
        assertEq(10,StringUtil.getCustomType((char) 3174));
    }
    @Test
    public void getType984(){
        assertEq(32,StringUtil.getCustomType((char) 3184));
    }
    @Test
    public void getType985(){
        assertEq(31,StringUtil.getCustomType((char) 3192));
    }
    @Test
    public void getType986(){
        assertEq(24,StringUtil.getCustomType((char) 3199));
    }
    @Test
    public void getType987(){
        assertEq(32,StringUtil.getCustomType((char) 3200));
    }
    @Test
    public void getType988(){
        assertEq(31,StringUtil.getCustomType((char) 3202));
    }
    @Test
    public void getType989(){
        assertEq(32,StringUtil.getCustomType((char) 3204));
    }
    @Test
    public void getType990(){
        assertEq(6,StringUtil.getCustomType((char) 3205));
    }
    @Test
    public void getType991(){
        assertEq(32,StringUtil.getCustomType((char) 3213));
    }
    @Test
    public void getType992(){
        assertEq(6,StringUtil.getCustomType((char) 3214));
    }
    @Test
    public void getType993(){
        assertEq(32,StringUtil.getCustomType((char) 3217));
    }
    @Test
    public void getType994(){
        assertEq(6,StringUtil.getCustomType((char) 3218));
    }
    @Test
    public void getType995(){
        assertEq(32,StringUtil.getCustomType((char) 3241));
    }
    @Test
    public void getType996(){
        assertEq(6,StringUtil.getCustomType((char) 3242));
    }
    @Test
    public void getType997(){
        assertEq(32,StringUtil.getCustomType((char) 3252));
    }
    @Test
    public void getType998(){
        assertEq(6,StringUtil.getCustomType((char) 3253));
    }
    @Test
    public void getType999(){
        assertEq(32,StringUtil.getCustomType((char) 3258));
    }
    @Test
    public void getType1000(){
        assertEq(31,StringUtil.getCustomType((char) 3260));
    }
    @Test
    public void getType1001(){
        assertEq(6,StringUtil.getCustomType((char) 3261));
    }
    @Test
    public void getType1002(){
        assertEq(31,StringUtil.getCustomType((char) 3262));
    }
    @Test
    public void getType1003(){
        assertEq(32,StringUtil.getCustomType((char) 3269));
    }
    @Test
    public void getType1004(){
        assertEq(31,StringUtil.getCustomType((char) 3270));
    }
    @Test
    public void getType1005(){
        assertEq(32,StringUtil.getCustomType((char) 3273));
    }
    @Test
    public void getType1006(){
        assertEq(31,StringUtil.getCustomType((char) 3274));
    }
    @Test
    public void getType1007(){
        assertEq(32,StringUtil.getCustomType((char) 3278));
    }
    @Test
    public void getType1008(){
        assertEq(31,StringUtil.getCustomType((char) 3285));
    }
    @Test
    public void getType1009(){
        assertEq(32,StringUtil.getCustomType((char) 3287));
    }
    @Test
    public void getType1010(){
        assertEq(6,StringUtil.getCustomType((char) 3294));
    }
    @Test
    public void getType1011(){
        assertEq(32,StringUtil.getCustomType((char) 3295));
    }
    @Test
    public void getType1012(){
        assertEq(6,StringUtil.getCustomType((char) 3296));
    }
    @Test
    public void getType1013(){
        assertEq(31,StringUtil.getCustomType((char) 3298));
    }
    @Test
    public void getType1014(){
        assertEq(32,StringUtil.getCustomType((char) 3300));
    }
    @Test
    public void getType1015(){
        assertEq(10,StringUtil.getCustomType((char) 3302));
    }
    @Test
    public void getType1016(){
        assertEq(32,StringUtil.getCustomType((char) 3312));
    }
    @Test
    public void getType1017(){
        assertEq(6,StringUtil.getCustomType((char) 3313));
    }
    @Test
    public void getType1018(){
        assertEq(32,StringUtil.getCustomType((char) 3315));
    }
    @Test
    public void getType1019(){
        assertEq(31,StringUtil.getCustomType((char) 3330));
    }
    @Test
    public void getType1020(){
        assertEq(32,StringUtil.getCustomType((char) 3332));
    }
    @Test
    public void getType1021(){
        assertEq(6,StringUtil.getCustomType((char) 3333));
    }
    @Test
    public void getType1022(){
        assertEq(32,StringUtil.getCustomType((char) 3341));
    }
    @Test
    public void getType1023(){
        assertEq(6,StringUtil.getCustomType((char) 3342));
    }
    @Test
    public void getType1024(){
        assertEq(32,StringUtil.getCustomType((char) 3345));
    }
    @Test
    public void getType1025(){
        assertEq(6,StringUtil.getCustomType((char) 3346));
    }
    @Test
    public void getType1026(){
        assertEq(32,StringUtil.getCustomType((char) 3387));
    }
    @Test
    public void getType1027(){
        assertEq(6,StringUtil.getCustomType((char) 3389));
    }
    @Test
    public void getType1028(){
        assertEq(31,StringUtil.getCustomType((char) 3390));
    }
    @Test
    public void getType1029(){
        assertEq(32,StringUtil.getCustomType((char) 3397));
    }
    @Test
    public void getType1030(){
        assertEq(31,StringUtil.getCustomType((char) 3398));
    }
    @Test
    public void getType1031(){
        assertEq(32,StringUtil.getCustomType((char) 3401));
    }
    @Test
    public void getType1032(){
        assertEq(31,StringUtil.getCustomType((char) 3402));
    }
    @Test
    public void getType1033(){
        assertEq(6,StringUtil.getCustomType((char) 3406));
    }
    @Test
    public void getType1034(){
        assertEq(32,StringUtil.getCustomType((char) 3407));
    }
    @Test
    public void getType1035(){
        assertEq(31,StringUtil.getCustomType((char) 3415));
    }
    @Test
    public void getType1036(){
        assertEq(32,StringUtil.getCustomType((char) 3416));
    }
    @Test
    public void getType1037(){
        assertEq(6,StringUtil.getCustomType((char) 3424));
    }
    @Test
    public void getType1038(){
        assertEq(31,StringUtil.getCustomType((char) 3426));
    }
    @Test
    public void getType1039(){
        assertEq(32,StringUtil.getCustomType((char) 3428));
    }
    @Test
    public void getType1040(){
        assertEq(10,StringUtil.getCustomType((char) 3430));
    }
    @Test
    public void getType1041(){
        assertEq(31,StringUtil.getCustomType((char) 3440));
    }
    @Test
    public void getType1042(){
        assertEq(32,StringUtil.getCustomType((char) 3446));
    }
    @Test
    public void getType1043(){
        assertEq(24,StringUtil.getCustomType((char) 3449));
    }
    @Test
    public void getType1044(){
        assertEq(6,StringUtil.getCustomType((char) 3450));
    }
    @Test
    public void getType1045(){
        assertEq(32,StringUtil.getCustomType((char) 3456));
    }
    @Test
    public void getType1046(){
        assertEq(31,StringUtil.getCustomType((char) 3458));
    }
    @Test
    public void getType1047(){
        assertEq(32,StringUtil.getCustomType((char) 3460));
    }
    @Test
    public void getType1048(){
        assertEq(6,StringUtil.getCustomType((char) 3461));
    }
    @Test
    public void getType1049(){
        assertEq(32,StringUtil.getCustomType((char) 3479));
    }
    @Test
    public void getType1050(){
        assertEq(6,StringUtil.getCustomType((char) 3482));
    }
    @Test
    public void getType1051(){
        assertEq(32,StringUtil.getCustomType((char) 3506));
    }
    @Test
    public void getType1052(){
        assertEq(6,StringUtil.getCustomType((char) 3507));
    }
    @Test
    public void getType1053(){
        assertEq(32,StringUtil.getCustomType((char) 3516));
    }
    @Test
    public void getType1054(){
        assertEq(6,StringUtil.getCustomType((char) 3517));
    }
    @Test
    public void getType1055(){
        assertEq(32,StringUtil.getCustomType((char) 3518));
    }
    @Test
    public void getType1056(){
        assertEq(6,StringUtil.getCustomType((char) 3520));
    }
    @Test
    public void getType1057(){
        assertEq(32,StringUtil.getCustomType((char) 3527));
    }
    @Test
    public void getType1058(){
        assertEq(31,StringUtil.getCustomType((char) 3530));
    }
    @Test
    public void getType1059(){
        assertEq(32,StringUtil.getCustomType((char) 3531));
    }
    @Test
    public void getType1060(){
        assertEq(31,StringUtil.getCustomType((char) 3535));
    }
    @Test
    public void getType1061(){
        assertEq(32,StringUtil.getCustomType((char) 3541));
    }
    @Test
    public void getType1062(){
        assertEq(31,StringUtil.getCustomType((char) 3542));
    }
    @Test
    public void getType1063(){
        assertEq(32,StringUtil.getCustomType((char) 3543));
    }
    @Test
    public void getType1064(){
        assertEq(31,StringUtil.getCustomType((char) 3544));
    }
    @Test
    public void getType1065(){
        assertEq(32,StringUtil.getCustomType((char) 3552));
    }
    @Test
    public void getType1066(){
        assertEq(31,StringUtil.getCustomType((char) 3570));
    }
    @Test
    public void getType1067(){
        assertEq(28,StringUtil.getCustomType((char) 3572));
    }
    @Test
    public void getType1068(){
        assertEq(32,StringUtil.getCustomType((char) 3573));
    }
    @Test
    public void getType1069(){
        assertEq(6,StringUtil.getCustomType((char) 3585));
    }
    @Test
    public void getType1070(){
        assertEq(31,StringUtil.getCustomType((char) 3633));
    }
    @Test
    public void getType1071(){
        assertEq(6,StringUtil.getCustomType((char) 3634));
    }
    @Test
    public void getType1072(){
        assertEq(31,StringUtil.getCustomType((char) 3636));
    }
    @Test
    public void getType1073(){
        assertEq(32,StringUtil.getCustomType((char) 3643));
    }
    @Test
    public void getType1074(){
        assertEq(12,StringUtil.getCustomType((char) 3647));
    }
    @Test
    public void getType1075(){
        assertEq(6,StringUtil.getCustomType((char) 3648));
    }
    @Test
    public void getType1076(){
        assertEq(31,StringUtil.getCustomType((char) 3655));
    }
    @Test
    public void getType1077(){
        assertEq(28,StringUtil.getCustomType((char) 3663));
    }
    @Test
    public void getType1078(){
        assertEq(10,StringUtil.getCustomType((char) 3664));
    }
    @Test
    public void getType1079(){
        assertEq(28,StringUtil.getCustomType((char) 3674));
    }
    @Test
    public void getType1080(){
        assertEq(32,StringUtil.getCustomType((char) 3676));
    }
    @Test
    public void getType1081(){
        assertEq(6,StringUtil.getCustomType((char) 3713));
    }
    @Test
    public void getType1082(){
        assertEq(32,StringUtil.getCustomType((char) 3715));
    }
    @Test
    public void getType1083(){
        assertEq(6,StringUtil.getCustomType((char) 3716));
    }
    @Test
    public void getType1084(){
        assertEq(32,StringUtil.getCustomType((char) 3717));
    }
    @Test
    public void getType1085(){
        assertEq(6,StringUtil.getCustomType((char) 3719));
    }
    @Test
    public void getType1086(){
        assertEq(32,StringUtil.getCustomType((char) 3721));
    }
    @Test
    public void getType1087(){
        assertEq(6,StringUtil.getCustomType((char) 3722));
    }
    @Test
    public void getType1088(){
        assertEq(32,StringUtil.getCustomType((char) 3723));
    }
    @Test
    public void getType1089(){
        assertEq(6,StringUtil.getCustomType((char) 3725));
    }
    @Test
    public void getType1090(){
        assertEq(32,StringUtil.getCustomType((char) 3726));
    }
    @Test
    public void getType1091(){
        assertEq(6,StringUtil.getCustomType((char) 3732));
    }
    @Test
    public void getType1092(){
        assertEq(32,StringUtil.getCustomType((char) 3736));
    }
    @Test
    public void getType1093(){
        assertEq(6,StringUtil.getCustomType((char) 3737));
    }
    @Test
    public void getType1094(){
        assertEq(32,StringUtil.getCustomType((char) 3744));
    }
    @Test
    public void getType1095(){
        assertEq(6,StringUtil.getCustomType((char) 3745));
    }
    @Test
    public void getType1096(){
        assertEq(32,StringUtil.getCustomType((char) 3748));
    }
    @Test
    public void getType1097(){
        assertEq(6,StringUtil.getCustomType((char) 3749));
    }
    @Test
    public void getType1098(){
        assertEq(32,StringUtil.getCustomType((char) 3750));
    }
    @Test
    public void getType1099(){
        assertEq(6,StringUtil.getCustomType((char) 3751));
    }
    @Test
    public void getType1100(){
        assertEq(32,StringUtil.getCustomType((char) 3752));
    }
    @Test
    public void getType1101(){
        assertEq(6,StringUtil.getCustomType((char) 3754));
    }
    @Test
    public void getType1102(){
        assertEq(32,StringUtil.getCustomType((char) 3756));
    }
    @Test
    public void getType1103(){
        assertEq(6,StringUtil.getCustomType((char) 3757));
    }
    @Test
    public void getType1104(){
        assertEq(31,StringUtil.getCustomType((char) 3761));
    }
    @Test
    public void getType1105(){
        assertEq(6,StringUtil.getCustomType((char) 3762));
    }
    @Test
    public void getType1106(){
        assertEq(31,StringUtil.getCustomType((char) 3764));
    }
    @Test
    public void getType1107(){
        assertEq(32,StringUtil.getCustomType((char) 3770));
    }
    @Test
    public void getType1108(){
        assertEq(31,StringUtil.getCustomType((char) 3771));
    }
    @Test
    public void getType1109(){
        assertEq(6,StringUtil.getCustomType((char) 3773));
    }
    @Test
    public void getType1110(){
        assertEq(32,StringUtil.getCustomType((char) 3774));
    }
    @Test
    public void getType1111(){
        assertEq(6,StringUtil.getCustomType((char) 3776));
    }
    @Test
    public void getType1112(){
        assertEq(32,StringUtil.getCustomType((char) 3781));
    }
    @Test
    public void getType1113(){
        assertEq(6,StringUtil.getCustomType((char) 3782));
    }
    @Test
    public void getType1114(){
        assertEq(32,StringUtil.getCustomType((char) 3783));
    }
    @Test
    public void getType1115(){
        assertEq(31,StringUtil.getCustomType((char) 3784));
    }
    @Test
    public void getType1116(){
        assertEq(32,StringUtil.getCustomType((char) 3790));
    }
    @Test
    public void getType1117(){
        assertEq(10,StringUtil.getCustomType((char) 3792));
    }
    @Test
    public void getType1118(){
        assertEq(32,StringUtil.getCustomType((char) 3802));
    }
    @Test
    public void getType1119(){
        assertEq(6,StringUtil.getCustomType((char) 3804));
    }
    @Test
    public void getType1120(){
        assertEq(32,StringUtil.getCustomType((char) 3808));
    }
    @Test
    public void getType1121(){
        assertEq(6,StringUtil.getCustomType((char) 3840));
    }
    @Test
    public void getType1122(){
        assertEq(24,StringUtil.getCustomType((char) 3841));
    }
    @Test
    public void getType1123(){
        assertEq(28,StringUtil.getCustomType((char) 3844));
    }
    @Test
    public void getType1124(){
        assertEq(24,StringUtil.getCustomType((char) 3859));
    }
    @Test
    public void getType1125(){
        assertEq(28,StringUtil.getCustomType((char) 3860));
    }
    @Test
    public void getType1126(){
        assertEq(24,StringUtil.getCustomType((char) 3861));
    }
    @Test
    public void getType1127(){
        assertEq(31,StringUtil.getCustomType((char) 3864));
    }
    @Test
    public void getType1128(){
        assertEq(24,StringUtil.getCustomType((char) 3866));
    }
    @Test
    public void getType1129(){
        assertEq(10,StringUtil.getCustomType((char) 3872));
    }
    @Test
    public void getType1130(){
        assertEq(31,StringUtil.getCustomType((char) 3882));
    }
    @Test
    public void getType1131(){
        assertEq(24,StringUtil.getCustomType((char) 3892));
    }
    @Test
    public void getType1132(){
        assertEq(31,StringUtil.getCustomType((char) 3893));
    }
    @Test
    public void getType1133(){
        assertEq(24,StringUtil.getCustomType((char) 3894));
    }
    @Test
    public void getType1134(){
        assertEq(31,StringUtil.getCustomType((char) 3895));
    }
    @Test
    public void getType1135(){
        assertEq(24,StringUtil.getCustomType((char) 3896));
    }
    @Test
    public void getType1136(){
        assertEq(31,StringUtil.getCustomType((char) 3897));
    }
    @Test
    public void getType1137(){
        assertEq(26,StringUtil.getCustomType((char) 3898));
    }
    @Test
    public void getType1138(){
        assertEq(31,StringUtil.getCustomType((char) 3902));
    }
    @Test
    public void getType1139(){
        assertEq(6,StringUtil.getCustomType((char) 3904));
    }
    @Test
    public void getType1140(){
        assertEq(32,StringUtil.getCustomType((char) 3912));
    }
    @Test
    public void getType1141(){
        assertEq(6,StringUtil.getCustomType((char) 3913));
    }
    @Test
    public void getType1142(){
        assertEq(32,StringUtil.getCustomType((char) 3949));
    }
    @Test
    public void getType1143(){
        assertEq(31,StringUtil.getCustomType((char) 3953));
    }
    @Test
    public void getType1144(){
        assertEq(28,StringUtil.getCustomType((char) 3973));
    }
    @Test
    public void getType1145(){
        assertEq(31,StringUtil.getCustomType((char) 3974));
    }
    @Test
    public void getType1146(){
        assertEq(6,StringUtil.getCustomType((char) 3976));
    }
    @Test
    public void getType1147(){
        assertEq(31,StringUtil.getCustomType((char) 3981));
    }
    @Test
    public void getType1148(){
        assertEq(32,StringUtil.getCustomType((char) 3992));
    }
    @Test
    public void getType1149(){
        assertEq(31,StringUtil.getCustomType((char) 3993));
    }
    @Test
    public void getType1150(){
        assertEq(32,StringUtil.getCustomType((char) 4029));
    }
    @Test
    public void getType1151(){
        assertEq(24,StringUtil.getCustomType((char) 4030));
    }
    @Test
    public void getType1152(){
        assertEq(31,StringUtil.getCustomType((char) 4038));
    }
    @Test
    public void getType1153(){
        assertEq(24,StringUtil.getCustomType((char) 4039));
    }
    @Test
    public void getType1154(){
        assertEq(32,StringUtil.getCustomType((char) 4045));
    }
    @Test
    public void getType1155(){
        assertEq(24,StringUtil.getCustomType((char) 4046));
    }
    @Test
    public void getType1156(){
        assertEq(28,StringUtil.getCustomType((char) 4048));
    }
    @Test
    public void getType1157(){
        assertEq(24,StringUtil.getCustomType((char) 4053));
    }
    @Test
    public void getType1158(){
        assertEq(28,StringUtil.getCustomType((char) 4057));
    }
    @Test
    public void getType1159(){
        assertEq(32,StringUtil.getCustomType((char) 4059));
    }
    @Test
    public void getType1160(){
        assertEq(6,StringUtil.getCustomType((char) 4096));
    }
    @Test
    public void getType1161(){
        assertEq(31,StringUtil.getCustomType((char) 4139));
    }
    @Test
    public void getType1162(){
        assertEq(6,StringUtil.getCustomType((char) 4159));
    }
    @Test
    public void getType1163(){
        assertEq(10,StringUtil.getCustomType((char) 4160));
    }
    @Test
    public void getType1164(){
        assertEq(28,StringUtil.getCustomType((char) 4170));
    }
    @Test
    public void getType1165(){
        assertEq(6,StringUtil.getCustomType((char) 4176));
    }
    @Test
    public void getType1166(){
        assertEq(31,StringUtil.getCustomType((char) 4182));
    }
    @Test
    public void getType1167(){
        assertEq(6,StringUtil.getCustomType((char) 4186));
    }
    @Test
    public void getType1168(){
        assertEq(31,StringUtil.getCustomType((char) 4190));
    }
    @Test
    public void getType1169(){
        assertEq(6,StringUtil.getCustomType((char) 4193));
    }
    @Test
    public void getType1170(){
        assertEq(31,StringUtil.getCustomType((char) 4194));
    }
    @Test
    public void getType1171(){
        assertEq(6,StringUtil.getCustomType((char) 4197));
    }
    @Test
    public void getType1172(){
        assertEq(31,StringUtil.getCustomType((char) 4199));
    }
    @Test
    public void getType1173(){
        assertEq(6,StringUtil.getCustomType((char) 4206));
    }
    @Test
    public void getType1174(){
        assertEq(31,StringUtil.getCustomType((char) 4209));
    }
    @Test
    public void getType1175(){
        assertEq(6,StringUtil.getCustomType((char) 4213));
    }
    @Test
    public void getType1176(){
        assertEq(31,StringUtil.getCustomType((char) 4226));
    }
    @Test
    public void getType1177(){
        assertEq(6,StringUtil.getCustomType((char) 4238));
    }
    @Test
    public void getType1178(){
        assertEq(31,StringUtil.getCustomType((char) 4239));
    }
    @Test
    public void getType1179(){
        assertEq(10,StringUtil.getCustomType((char) 4240));
    }
    @Test
    public void getType1180(){
        assertEq(31,StringUtil.getCustomType((char) 4250));
    }
    @Test
    public void getType1181(){
        assertEq(24,StringUtil.getCustomType((char) 4254));
    }
    @Test
    public void getType1182(){
        assertEq(2,StringUtil.getCustomType((char) 4256));
    }
    @Test
    public void getType1183(){
        assertEq(32,StringUtil.getCustomType((char) 4294));
    }
    @Test
    public void getType1184(){
        assertEq(2,StringUtil.getCustomType((char) 4295));
    }
    @Test
    public void getType1185(){
        assertEq(32,StringUtil.getCustomType((char) 4296));
    }
    @Test
    public void getType1186(){
        assertEq(2,StringUtil.getCustomType((char) 4301));
    }
    @Test
    public void getType1187(){
        assertEq(32,StringUtil.getCustomType((char) 4302));
    }
    @Test
    public void getType1188(){
        assertEq(6,StringUtil.getCustomType((char) 4304));
    }
    @Test
    public void getType1189(){
        assertEq(28,StringUtil.getCustomType((char) 4347));
    }
    @Test
    public void getType1190(){
        assertEq(6,StringUtil.getCustomType((char) 4348));
    }
    @Test
    public void getType1191(){
        assertEq(32,StringUtil.getCustomType((char) 4681));
    }
    @Test
    public void getType1192(){
        assertEq(6,StringUtil.getCustomType((char) 4682));
    }
    @Test
    public void getType1193(){
        assertEq(32,StringUtil.getCustomType((char) 4686));
    }
    @Test
    public void getType1194(){
        assertEq(6,StringUtil.getCustomType((char) 4688));
    }
    @Test
    public void getType1195(){
        assertEq(32,StringUtil.getCustomType((char) 4695));
    }
    @Test
    public void getType1196(){
        assertEq(6,StringUtil.getCustomType((char) 4696));
    }
    @Test
    public void getType1197(){
        assertEq(32,StringUtil.getCustomType((char) 4697));
    }
    @Test
    public void getType1198(){
        assertEq(6,StringUtil.getCustomType((char) 4698));
    }
    @Test
    public void getType1199(){
        assertEq(32,StringUtil.getCustomType((char) 4702));
    }
    @Test
    public void getType1200(){
        assertEq(6,StringUtil.getCustomType((char) 4704));
    }
    @Test
    public void getType1201(){
        assertEq(32,StringUtil.getCustomType((char) 4745));
    }
    @Test
    public void getType1202(){
        assertEq(6,StringUtil.getCustomType((char) 4746));
    }
    @Test
    public void getType1203(){
        assertEq(32,StringUtil.getCustomType((char) 4750));
    }
    @Test
    public void getType1204(){
        assertEq(6,StringUtil.getCustomType((char) 4752));
    }
    @Test
    public void getType1205(){
        assertEq(32,StringUtil.getCustomType((char) 4785));
    }
    @Test
    public void getType1206(){
        assertEq(6,StringUtil.getCustomType((char) 4786));
    }
    @Test
    public void getType1207(){
        assertEq(32,StringUtil.getCustomType((char) 4790));
    }
    @Test
    public void getType1208(){
        assertEq(6,StringUtil.getCustomType((char) 4792));
    }
    @Test
    public void getType1209(){
        assertEq(32,StringUtil.getCustomType((char) 4799));
    }
    @Test
    public void getType1210(){
        assertEq(6,StringUtil.getCustomType((char) 4800));
    }
    @Test
    public void getType1211(){
        assertEq(32,StringUtil.getCustomType((char) 4801));
    }
    @Test
    public void getType1212(){
        assertEq(6,StringUtil.getCustomType((char) 4802));
    }
    @Test
    public void getType1213(){
        assertEq(32,StringUtil.getCustomType((char) 4806));
    }
    @Test
    public void getType1214(){
        assertEq(6,StringUtil.getCustomType((char) 4808));
    }
    @Test
    public void getType1215(){
        assertEq(32,StringUtil.getCustomType((char) 4823));
    }
    @Test
    public void getType1216(){
        assertEq(6,StringUtil.getCustomType((char) 4824));
    }
    @Test
    public void getType1217(){
        assertEq(32,StringUtil.getCustomType((char) 4881));
    }
    @Test
    public void getType1218(){
        assertEq(6,StringUtil.getCustomType((char) 4882));
    }
    @Test
    public void getType1219(){
        assertEq(32,StringUtil.getCustomType((char) 4886));
    }
    @Test
    public void getType1220(){
        assertEq(6,StringUtil.getCustomType((char) 4888));
    }
    @Test
    public void getType1221(){
        assertEq(32,StringUtil.getCustomType((char) 4955));
    }
    @Test
    public void getType1222(){
        assertEq(31,StringUtil.getCustomType((char) 4957));
    }
    @Test
    public void getType1223(){
        assertEq(28,StringUtil.getCustomType((char) 4960));
    }
    @Test
    public void getType1224(){
        assertEq(31,StringUtil.getCustomType((char) 4969));
    }
    @Test
    public void getType1225(){
        assertEq(32,StringUtil.getCustomType((char) 4989));
    }
    @Test
    public void getType1226(){
        assertEq(6,StringUtil.getCustomType((char) 4992));
    }
    @Test
    public void getType1227(){
        assertEq(24,StringUtil.getCustomType((char) 5008));
    }
    @Test
    public void getType1228(){
        assertEq(32,StringUtil.getCustomType((char) 5018));
    }
    @Test
    public void getType1229(){
        assertEq(6,StringUtil.getCustomType((char) 5024));
    }
    @Test
    public void getType1230(){
        assertEq(32,StringUtil.getCustomType((char) 5109));
    }
    @Test
    public void getType1231(){
        assertEq(25,StringUtil.getCustomType((char) 5120));
    }
    @Test
    public void getType1232(){
        assertEq(6,StringUtil.getCustomType((char) 5121));
    }
    @Test
    public void getType1233(){
        assertEq(28,StringUtil.getCustomType((char) 5741));
    }
    @Test
    public void getType1234(){
        assertEq(6,StringUtil.getCustomType((char) 5743));
    }
    @Test
    public void getType1235(){
        assertEq(30,StringUtil.getCustomType((char) 5760));
    }
    @Test
    public void getType1236(){
        assertEq(6,StringUtil.getCustomType((char) 5761));
    }
    @Test
    public void getType1237(){
        assertEq(26,StringUtil.getCustomType((char) 5787));
    }
    @Test
    public void getType1238(){
        assertEq(32,StringUtil.getCustomType((char) 5789));
    }
    @Test
    public void getType1239(){
        assertEq(6,StringUtil.getCustomType((char) 5792));
    }
    @Test
    public void getType1240(){
        assertEq(28,StringUtil.getCustomType((char) 5867));
    }
    @Test
    public void getType1241(){
        assertEq(31,StringUtil.getCustomType((char) 5870));
    }
    @Test
    public void getType1242(){
        assertEq(32,StringUtil.getCustomType((char) 5873));
    }
    @Test
    public void getType1243(){
        assertEq(6,StringUtil.getCustomType((char) 5888));
    }
    @Test
    public void getType1244(){
        assertEq(32,StringUtil.getCustomType((char) 5901));
    }
    @Test
    public void getType1245(){
        assertEq(6,StringUtil.getCustomType((char) 5902));
    }
    @Test
    public void getType1246(){
        assertEq(31,StringUtil.getCustomType((char) 5906));
    }
    @Test
    public void getType1247(){
        assertEq(32,StringUtil.getCustomType((char) 5909));
    }
    @Test
    public void getType1248(){
        assertEq(6,StringUtil.getCustomType((char) 5920));
    }
    @Test
    public void getType1249(){
        assertEq(31,StringUtil.getCustomType((char) 5938));
    }
    @Test
    public void getType1250(){
        assertEq(28,StringUtil.getCustomType((char) 5941));
    }
    @Test
    public void getType1251(){
        assertEq(32,StringUtil.getCustomType((char) 5943));
    }
    @Test
    public void getType1252(){
        assertEq(6,StringUtil.getCustomType((char) 5952));
    }
    @Test
    public void getType1253(){
        assertEq(31,StringUtil.getCustomType((char) 5970));
    }
    @Test
    public void getType1254(){
        assertEq(32,StringUtil.getCustomType((char) 5972));
    }
    @Test
    public void getType1255(){
        assertEq(6,StringUtil.getCustomType((char) 5984));
    }
    @Test
    public void getType1256(){
        assertEq(32,StringUtil.getCustomType((char) 5997));
    }
    @Test
    public void getType1257(){
        assertEq(6,StringUtil.getCustomType((char) 5998));
    }
    @Test
    public void getType1258(){
        assertEq(32,StringUtil.getCustomType((char) 6001));
    }
    @Test
    public void getType1259(){
        assertEq(31,StringUtil.getCustomType((char) 6002));
    }
    @Test
    public void getType1260(){
        assertEq(32,StringUtil.getCustomType((char) 6004));
    }
    @Test
    public void getType1261(){
        assertEq(6,StringUtil.getCustomType((char) 6016));
    }
    @Test
    public void getType1262(){
        assertEq(31,StringUtil.getCustomType((char) 6068));
    }
    @Test
    public void getType1263(){
        assertEq(28,StringUtil.getCustomType((char) 6100));
    }
    @Test
    public void getType1264(){
        assertEq(6,StringUtil.getCustomType((char) 6103));
    }
    @Test
    public void getType1265(){
        assertEq(28,StringUtil.getCustomType((char) 6104));
    }
    @Test
    public void getType1266(){
        assertEq(12,StringUtil.getCustomType((char) 6107));
    }
    @Test
    public void getType1267(){
        assertEq(6,StringUtil.getCustomType((char) 6108));
    }
    @Test
    public void getType1268(){
        assertEq(31,StringUtil.getCustomType((char) 6109));
    }
    @Test
    public void getType1269(){
        assertEq(32,StringUtil.getCustomType((char) 6110));
    }
    @Test
    public void getType1270(){
        assertEq(10,StringUtil.getCustomType((char) 6112));
    }
    @Test
    public void getType1271(){
        assertEq(32,StringUtil.getCustomType((char) 6122));
    }
    @Test
    public void getType1272(){
        assertEq(31,StringUtil.getCustomType((char) 6128));
    }
    @Test
    public void getType1273(){
        assertEq(32,StringUtil.getCustomType((char) 6138));
    }
    @Test
    public void getType1274(){
        assertEq(28,StringUtil.getCustomType((char) 6144));
    }
    @Test
    public void getType1275(){
        assertEq(25,StringUtil.getCustomType((char) 6150));
    }
    @Test
    public void getType1276(){
        assertEq(28,StringUtil.getCustomType((char) 6151));
    }
    @Test
    public void getType1277(){
        assertEq(31,StringUtil.getCustomType((char) 6155));
    }
    @Test
    public void getType1278(){
        assertEq(30,StringUtil.getCustomType((char) 6158));
    }
    @Test
    public void getType1279(){
        assertEq(32,StringUtil.getCustomType((char) 6159));
    }
    @Test
    public void getType1280(){
        assertEq(10,StringUtil.getCustomType((char) 6160));
    }
    @Test
    public void getType1281(){
        assertEq(32,StringUtil.getCustomType((char) 6170));
    }
    @Test
    public void getType1282(){
        assertEq(6,StringUtil.getCustomType((char) 6176));
    }
    @Test
    public void getType1283(){
        assertEq(32,StringUtil.getCustomType((char) 6264));
    }
    @Test
    public void getType1284(){
        assertEq(6,StringUtil.getCustomType((char) 6272));
    }
    @Test
    public void getType1285(){
        assertEq(31,StringUtil.getCustomType((char) 6313));
    }
    @Test
    public void getType1286(){
        assertEq(6,StringUtil.getCustomType((char) 6314));
    }
    @Test
    public void getType1287(){
        assertEq(32,StringUtil.getCustomType((char) 6315));
    }
    @Test
    public void getType1288(){
        assertEq(6,StringUtil.getCustomType((char) 6320));
    }
    @Test
    public void getType1289(){
        assertEq(32,StringUtil.getCustomType((char) 6390));
    }
    @Test
    public void getType1290(){
        assertEq(6,StringUtil.getCustomType((char) 6400));
    }
    @Test
    public void getType1291(){
        assertEq(32,StringUtil.getCustomType((char) 6429));
    }
    @Test
    public void getType1292(){
        assertEq(31,StringUtil.getCustomType((char) 6432));
    }
    @Test
    public void getType1293(){
        assertEq(32,StringUtil.getCustomType((char) 6444));
    }
    @Test
    public void getType1294(){
        assertEq(31,StringUtil.getCustomType((char) 6448));
    }
    @Test
    public void getType1295(){
        assertEq(32,StringUtil.getCustomType((char) 6460));
    }
    @Test
    public void getType1296(){
        assertEq(24,StringUtil.getCustomType((char) 6464));
    }
    @Test
    public void getType1297(){
        assertEq(32,StringUtil.getCustomType((char) 6465));
    }
    @Test
    public void getType1298(){
        assertEq(28,StringUtil.getCustomType((char) 6468));
    }
    @Test
    public void getType1299(){
        assertEq(10,StringUtil.getCustomType((char) 6470));
    }
    @Test
    public void getType1300(){
        assertEq(6,StringUtil.getCustomType((char) 6480));
    }
    @Test
    public void getType1301(){
        assertEq(32,StringUtil.getCustomType((char) 6510));
    }
    @Test
    public void getType1302(){
        assertEq(6,StringUtil.getCustomType((char) 6512));
    }
    @Test
    public void getType1303(){
        assertEq(32,StringUtil.getCustomType((char) 6517));
    }
    @Test
    public void getType1304(){
        assertEq(6,StringUtil.getCustomType((char) 6528));
    }
    @Test
    public void getType1305(){
        assertEq(32,StringUtil.getCustomType((char) 6572));
    }
    @Test
    public void getType1306(){
        assertEq(31,StringUtil.getCustomType((char) 6576));
    }
    @Test
    public void getType1307(){
        assertEq(6,StringUtil.getCustomType((char) 6593));
    }
    @Test
    public void getType1308(){
        assertEq(31,StringUtil.getCustomType((char) 6600));
    }
    @Test
    public void getType1309(){
        assertEq(32,StringUtil.getCustomType((char) 6602));
    }
    @Test
    public void getType1310(){
        assertEq(10,StringUtil.getCustomType((char) 6608));
    }
    @Test
    public void getType1311(){
        assertEq(31,StringUtil.getCustomType((char) 6618));
    }
    @Test
    public void getType1312(){
        assertEq(32,StringUtil.getCustomType((char) 6619));
    }
    @Test
    public void getType1313(){
        assertEq(24,StringUtil.getCustomType((char) 6622));
    }
    @Test
    public void getType1314(){
        assertEq(6,StringUtil.getCustomType((char) 6656));
    }
    @Test
    public void getType1315(){
        assertEq(31,StringUtil.getCustomType((char) 6679));
    }
    @Test
    public void getType1316(){
        assertEq(32,StringUtil.getCustomType((char) 6684));
    }
    @Test
    public void getType1317(){
        assertEq(28,StringUtil.getCustomType((char) 6686));
    }
    @Test
    public void getType1318(){
        assertEq(6,StringUtil.getCustomType((char) 6688));
    }
    @Test
    public void getType1319(){
        assertEq(31,StringUtil.getCustomType((char) 6741));
    }
    @Test
    public void getType1320(){
        assertEq(32,StringUtil.getCustomType((char) 6751));
    }
    @Test
    public void getType1321(){
        assertEq(31,StringUtil.getCustomType((char) 6752));
    }
    @Test
    public void getType1322(){
        assertEq(32,StringUtil.getCustomType((char) 6781));
    }
    @Test
    public void getType1323(){
        assertEq(31,StringUtil.getCustomType((char) 6783));
    }
    @Test
    public void getType1324(){
        assertEq(10,StringUtil.getCustomType((char) 6784));
    }
    @Test
    public void getType1325(){
        assertEq(32,StringUtil.getCustomType((char) 6794));
    }
    @Test
    public void getType1326(){
        assertEq(10,StringUtil.getCustomType((char) 6800));
    }
    @Test
    public void getType1327(){
        assertEq(32,StringUtil.getCustomType((char) 6810));
    }
    @Test
    public void getType1328(){
        assertEq(28,StringUtil.getCustomType((char) 6816));
    }
    @Test
    public void getType1329(){
        assertEq(6,StringUtil.getCustomType((char) 6823));
    }
    @Test
    public void getType1330(){
        assertEq(28,StringUtil.getCustomType((char) 6824));
    }
    @Test
    public void getType1331(){
        assertEq(32,StringUtil.getCustomType((char) 6830));
    }
    @Test
    public void getType1332(){
        assertEq(31,StringUtil.getCustomType((char) 6912));
    }
    @Test
    public void getType1333(){
        assertEq(6,StringUtil.getCustomType((char) 6917));
    }
    @Test
    public void getType1334(){
        assertEq(31,StringUtil.getCustomType((char) 6964));
    }
    @Test
    public void getType1335(){
        assertEq(6,StringUtil.getCustomType((char) 6981));
    }
    @Test
    public void getType1336(){
        assertEq(32,StringUtil.getCustomType((char) 6988));
    }
    @Test
    public void getType1337(){
        assertEq(10,StringUtil.getCustomType((char) 6992));
    }
    @Test
    public void getType1338(){
        assertEq(28,StringUtil.getCustomType((char) 7002));
    }
    @Test
    public void getType1339(){
        assertEq(24,StringUtil.getCustomType((char) 7009));
    }
    @Test
    public void getType1340(){
        assertEq(31,StringUtil.getCustomType((char) 7019));
    }
    @Test
    public void getType1341(){
        assertEq(24,StringUtil.getCustomType((char) 7028));
    }
    @Test
    public void getType1342(){
        assertEq(32,StringUtil.getCustomType((char) 7037));
    }
    @Test
    public void getType1343(){
        assertEq(31,StringUtil.getCustomType((char) 7040));
    }
    @Test
    public void getType1344(){
        assertEq(6,StringUtil.getCustomType((char) 7043));
    }
    @Test
    public void getType1345(){
        assertEq(31,StringUtil.getCustomType((char) 7073));
    }
    @Test
    public void getType1346(){
        assertEq(6,StringUtil.getCustomType((char) 7086));
    }
    @Test
    public void getType1347(){
        assertEq(10,StringUtil.getCustomType((char) 7088));
    }
    @Test
    public void getType1348(){
        assertEq(6,StringUtil.getCustomType((char) 7098));
    }
    @Test
    public void getType1349(){
        assertEq(31,StringUtil.getCustomType((char) 7142));
    }
    @Test
    public void getType1350(){
        assertEq(32,StringUtil.getCustomType((char) 7156));
    }
    @Test
    public void getType1351(){
        assertEq(28,StringUtil.getCustomType((char) 7164));
    }
    @Test
    public void getType1352(){
        assertEq(6,StringUtil.getCustomType((char) 7168));
    }
    @Test
    public void getType1353(){
        assertEq(31,StringUtil.getCustomType((char) 7204));
    }
    @Test
    public void getType1354(){
        assertEq(32,StringUtil.getCustomType((char) 7224));
    }
    @Test
    public void getType1355(){
        assertEq(28,StringUtil.getCustomType((char) 7227));
    }
    @Test
    public void getType1356(){
        assertEq(10,StringUtil.getCustomType((char) 7232));
    }
    @Test
    public void getType1357(){
        assertEq(32,StringUtil.getCustomType((char) 7242));
    }
    @Test
    public void getType1358(){
        assertEq(6,StringUtil.getCustomType((char) 7245));
    }
    @Test
    public void getType1359(){
        assertEq(10,StringUtil.getCustomType((char) 7248));
    }
    @Test
    public void getType1360(){
        assertEq(6,StringUtil.getCustomType((char) 7258));
    }
    @Test
    public void getType1361(){
        assertEq(28,StringUtil.getCustomType((char) 7294));
    }
    @Test
    public void getType1362(){
        assertEq(32,StringUtil.getCustomType((char) 7296));
    }
    @Test
    public void getType1363(){
        assertEq(28,StringUtil.getCustomType((char) 7360));
    }
    @Test
    public void getType1364(){
        assertEq(32,StringUtil.getCustomType((char) 7368));
    }
    @Test
    public void getType1365(){
        assertEq(31,StringUtil.getCustomType((char) 7376));
    }
    @Test
    public void getType1366(){
        assertEq(28,StringUtil.getCustomType((char) 7379));
    }
    @Test
    public void getType1367(){
        assertEq(31,StringUtil.getCustomType((char) 7380));
    }
    @Test
    public void getType1368(){
        assertEq(6,StringUtil.getCustomType((char) 7401));
    }
    @Test
    public void getType1369(){
        assertEq(31,StringUtil.getCustomType((char) 7405));
    }
    @Test
    public void getType1370(){
        assertEq(6,StringUtil.getCustomType((char) 7406));
    }
    @Test
    public void getType1371(){
        assertEq(31,StringUtil.getCustomType((char) 7410));
    }
    @Test
    public void getType1372(){
        assertEq(6,StringUtil.getCustomType((char) 7413));
    }
    @Test
    public void getType1373(){
        assertEq(32,StringUtil.getCustomType((char) 7415));
    }
    @Test
    public void getType1374(){
        assertEq(3,StringUtil.getCustomType((char) 7424));
    }
    @Test
    public void getType1375(){
        assertEq(1,StringUtil.getCustomType((char) 7545));
    }
    @Test
    public void getType1376(){
        assertEq(3,StringUtil.getCustomType((char) 7546));
    }
    @Test
    public void getType1377(){
        assertEq(1,StringUtil.getCustomType((char) 7549));
    }
    @Test
    public void getType1378(){
        assertEq(3,StringUtil.getCustomType((char) 7550));
    }
    @Test
    public void getType1379(){
        assertEq(31,StringUtil.getCustomType((char) 7616));
    }
    @Test
    public void getType1380(){
        assertEq(32,StringUtil.getCustomType((char) 7655));
    }
    @Test
    public void getType1381(){
        assertEq(31,StringUtil.getCustomType((char) 7676));
    }
    @Test
    public void getType1382(){
        assertEq(2,StringUtil.getCustomType((char) 7680));
    }
    @Test
    public void getType1383(){
        assertEq(1,StringUtil.getCustomType((char) 7681));
    }
    @Test
    public void getType1384(){
        assertEq(2,StringUtil.getCustomType((char) 7682));
    }
    @Test
    public void getType1385(){
        assertEq(1,StringUtil.getCustomType((char) 7683));
    }
    @Test
    public void getType1386(){
        assertEq(2,StringUtil.getCustomType((char) 7684));
    }
    @Test
    public void getType1387(){
        assertEq(1,StringUtil.getCustomType((char) 7685));
    }
    @Test
    public void getType1388(){
        assertEq(2,StringUtil.getCustomType((char) 7686));
    }
    @Test
    public void getType1389(){
        assertEq(1,StringUtil.getCustomType((char) 7687));
    }
    @Test
    public void getType1390(){
        assertEq(2,StringUtil.getCustomType((char) 7688));
    }
    @Test
    public void getType1391(){
        assertEq(1,StringUtil.getCustomType((char) 7689));
    }
    @Test
    public void getType1392(){
        assertEq(2,StringUtil.getCustomType((char) 7690));
    }
    @Test
    public void getType1393(){
        assertEq(1,StringUtil.getCustomType((char) 7691));
    }
    @Test
    public void getType1394(){
        assertEq(2,StringUtil.getCustomType((char) 7692));
    }
    @Test
    public void getType1395(){
        assertEq(1,StringUtil.getCustomType((char) 7693));
    }
    @Test
    public void getType1396(){
        assertEq(2,StringUtil.getCustomType((char) 7694));
    }
    @Test
    public void getType1397(){
        assertEq(1,StringUtil.getCustomType((char) 7695));
    }
    @Test
    public void getType1398(){
        assertEq(2,StringUtil.getCustomType((char) 7696));
    }
    @Test
    public void getType1399(){
        assertEq(1,StringUtil.getCustomType((char) 7697));
    }
    @Test
    public void getType1400(){
        assertEq(2,StringUtil.getCustomType((char) 7698));
    }
    @Test
    public void getType1401(){
        assertEq(1,StringUtil.getCustomType((char) 7699));
    }
    @Test
    public void getType1402(){
        assertEq(2,StringUtil.getCustomType((char) 7700));
    }
    @Test
    public void getType1403(){
        assertEq(1,StringUtil.getCustomType((char) 7701));
    }
    @Test
    public void getType1404(){
        assertEq(2,StringUtil.getCustomType((char) 7702));
    }
    @Test
    public void getType1405(){
        assertEq(1,StringUtil.getCustomType((char) 7703));
    }
    @Test
    public void getType1406(){
        assertEq(2,StringUtil.getCustomType((char) 7704));
    }
    @Test
    public void getType1407(){
        assertEq(1,StringUtil.getCustomType((char) 7705));
    }
    @Test
    public void getType1408(){
        assertEq(2,StringUtil.getCustomType((char) 7706));
    }
    @Test
    public void getType1409(){
        assertEq(1,StringUtil.getCustomType((char) 7707));
    }
    @Test
    public void getType1410(){
        assertEq(2,StringUtil.getCustomType((char) 7708));
    }
    @Test
    public void getType1411(){
        assertEq(1,StringUtil.getCustomType((char) 7709));
    }
    @Test
    public void getType1412(){
        assertEq(2,StringUtil.getCustomType((char) 7710));
    }
    @Test
    public void getType1413(){
        assertEq(1,StringUtil.getCustomType((char) 7711));
    }
    @Test
    public void getType1414(){
        assertEq(2,StringUtil.getCustomType((char) 7712));
    }
    @Test
    public void getType1415(){
        assertEq(1,StringUtil.getCustomType((char) 7713));
    }
    @Test
    public void getType1416(){
        assertEq(2,StringUtil.getCustomType((char) 7714));
    }
    @Test
    public void getType1417(){
        assertEq(1,StringUtil.getCustomType((char) 7715));
    }
    @Test
    public void getType1418(){
        assertEq(2,StringUtil.getCustomType((char) 7716));
    }
    @Test
    public void getType1419(){
        assertEq(1,StringUtil.getCustomType((char) 7717));
    }
    @Test
    public void getType1420(){
        assertEq(2,StringUtil.getCustomType((char) 7718));
    }
    @Test
    public void getType1421(){
        assertEq(1,StringUtil.getCustomType((char) 7719));
    }
    @Test
    public void getType1422(){
        assertEq(2,StringUtil.getCustomType((char) 7720));
    }
    @Test
    public void getType1423(){
        assertEq(1,StringUtil.getCustomType((char) 7721));
    }
    @Test
    public void getType1424(){
        assertEq(2,StringUtil.getCustomType((char) 7722));
    }
    @Test
    public void getType1425(){
        assertEq(1,StringUtil.getCustomType((char) 7723));
    }
    @Test
    public void getType1426(){
        assertEq(2,StringUtil.getCustomType((char) 7724));
    }
    @Test
    public void getType1427(){
        assertEq(1,StringUtil.getCustomType((char) 7725));
    }
    @Test
    public void getType1428(){
        assertEq(2,StringUtil.getCustomType((char) 7726));
    }
    @Test
    public void getType1429(){
        assertEq(1,StringUtil.getCustomType((char) 7727));
    }
    @Test
    public void getType1430(){
        assertEq(2,StringUtil.getCustomType((char) 7728));
    }
    @Test
    public void getType1431(){
        assertEq(1,StringUtil.getCustomType((char) 7729));
    }
    @Test
    public void getType1432(){
        assertEq(2,StringUtil.getCustomType((char) 7730));
    }
    @Test
    public void getType1433(){
        assertEq(1,StringUtil.getCustomType((char) 7731));
    }
    @Test
    public void getType1434(){
        assertEq(2,StringUtil.getCustomType((char) 7732));
    }
    @Test
    public void getType1435(){
        assertEq(1,StringUtil.getCustomType((char) 7733));
    }
    @Test
    public void getType1436(){
        assertEq(2,StringUtil.getCustomType((char) 7734));
    }
    @Test
    public void getType1437(){
        assertEq(1,StringUtil.getCustomType((char) 7735));
    }
    @Test
    public void getType1438(){
        assertEq(2,StringUtil.getCustomType((char) 7736));
    }
    @Test
    public void getType1439(){
        assertEq(1,StringUtil.getCustomType((char) 7737));
    }
    @Test
    public void getType1440(){
        assertEq(2,StringUtil.getCustomType((char) 7738));
    }
    @Test
    public void getType1441(){
        assertEq(1,StringUtil.getCustomType((char) 7739));
    }
    @Test
    public void getType1442(){
        assertEq(2,StringUtil.getCustomType((char) 7740));
    }
    @Test
    public void getType1443(){
        assertEq(1,StringUtil.getCustomType((char) 7741));
    }
    @Test
    public void getType1444(){
        assertEq(2,StringUtil.getCustomType((char) 7742));
    }
    @Test
    public void getType1445(){
        assertEq(1,StringUtil.getCustomType((char) 7743));
    }
    @Test
    public void getType1446(){
        assertEq(2,StringUtil.getCustomType((char) 7744));
    }
    @Test
    public void getType1447(){
        assertEq(1,StringUtil.getCustomType((char) 7745));
    }
    @Test
    public void getType1448(){
        assertEq(2,StringUtil.getCustomType((char) 7746));
    }
    @Test
    public void getType1449(){
        assertEq(1,StringUtil.getCustomType((char) 7747));
    }
    @Test
    public void getType1450(){
        assertEq(2,StringUtil.getCustomType((char) 7748));
    }
    @Test
    public void getType1451(){
        assertEq(1,StringUtil.getCustomType((char) 7749));
    }
    @Test
    public void getType1452(){
        assertEq(2,StringUtil.getCustomType((char) 7750));
    }
    @Test
    public void getType1453(){
        assertEq(1,StringUtil.getCustomType((char) 7751));
    }
    @Test
    public void getType1454(){
        assertEq(2,StringUtil.getCustomType((char) 7752));
    }
    @Test
    public void getType1455(){
        assertEq(1,StringUtil.getCustomType((char) 7753));
    }
    @Test
    public void getType1456(){
        assertEq(2,StringUtil.getCustomType((char) 7754));
    }
    @Test
    public void getType1457(){
        assertEq(1,StringUtil.getCustomType((char) 7755));
    }
    @Test
    public void getType1458(){
        assertEq(2,StringUtil.getCustomType((char) 7756));
    }
    @Test
    public void getType1459(){
        assertEq(1,StringUtil.getCustomType((char) 7757));
    }
    @Test
    public void getType1460(){
        assertEq(2,StringUtil.getCustomType((char) 7758));
    }
    @Test
    public void getType1461(){
        assertEq(1,StringUtil.getCustomType((char) 7759));
    }
    @Test
    public void getType1462(){
        assertEq(2,StringUtil.getCustomType((char) 7760));
    }
    @Test
    public void getType1463(){
        assertEq(1,StringUtil.getCustomType((char) 7761));
    }
    @Test
    public void getType1464(){
        assertEq(2,StringUtil.getCustomType((char) 7762));
    }
    @Test
    public void getType1465(){
        assertEq(1,StringUtil.getCustomType((char) 7763));
    }
    @Test
    public void getType1466(){
        assertEq(2,StringUtil.getCustomType((char) 7764));
    }
    @Test
    public void getType1467(){
        assertEq(1,StringUtil.getCustomType((char) 7765));
    }
    @Test
    public void getType1468(){
        assertEq(2,StringUtil.getCustomType((char) 7766));
    }
    @Test
    public void getType1469(){
        assertEq(1,StringUtil.getCustomType((char) 7767));
    }
    @Test
    public void getType1470(){
        assertEq(2,StringUtil.getCustomType((char) 7768));
    }
    @Test
    public void getType1471(){
        assertEq(1,StringUtil.getCustomType((char) 7769));
    }
    @Test
    public void getType1472(){
        assertEq(2,StringUtil.getCustomType((char) 7770));
    }
    @Test
    public void getType1473(){
        assertEq(1,StringUtil.getCustomType((char) 7771));
    }
    @Test
    public void getType1474(){
        assertEq(2,StringUtil.getCustomType((char) 7772));
    }
    @Test
    public void getType1475(){
        assertEq(1,StringUtil.getCustomType((char) 7773));
    }
    @Test
    public void getType1476(){
        assertEq(2,StringUtil.getCustomType((char) 7774));
    }
    @Test
    public void getType1477(){
        assertEq(1,StringUtil.getCustomType((char) 7775));
    }
    @Test
    public void getType1478(){
        assertEq(2,StringUtil.getCustomType((char) 7776));
    }
    @Test
    public void getType1479(){
        assertEq(1,StringUtil.getCustomType((char) 7777));
    }
    @Test
    public void getType1480(){
        assertEq(2,StringUtil.getCustomType((char) 7778));
    }
    @Test
    public void getType1481(){
        assertEq(1,StringUtil.getCustomType((char) 7779));
    }
    @Test
    public void getType1482(){
        assertEq(2,StringUtil.getCustomType((char) 7780));
    }
    @Test
    public void getType1483(){
        assertEq(1,StringUtil.getCustomType((char) 7781));
    }
    @Test
    public void getType1484(){
        assertEq(2,StringUtil.getCustomType((char) 7782));
    }
    @Test
    public void getType1485(){
        assertEq(1,StringUtil.getCustomType((char) 7783));
    }
    @Test
    public void getType1486(){
        assertEq(2,StringUtil.getCustomType((char) 7784));
    }
    @Test
    public void getType1487(){
        assertEq(1,StringUtil.getCustomType((char) 7785));
    }
    @Test
    public void getType1488(){
        assertEq(2,StringUtil.getCustomType((char) 7786));
    }
    @Test
    public void getType1489(){
        assertEq(1,StringUtil.getCustomType((char) 7787));
    }
    @Test
    public void getType1490(){
        assertEq(2,StringUtil.getCustomType((char) 7788));
    }
    @Test
    public void getType1491(){
        assertEq(1,StringUtil.getCustomType((char) 7789));
    }
    @Test
    public void getType1492(){
        assertEq(2,StringUtil.getCustomType((char) 7790));
    }
    @Test
    public void getType1493(){
        assertEq(1,StringUtil.getCustomType((char) 7791));
    }
    @Test
    public void getType1494(){
        assertEq(2,StringUtil.getCustomType((char) 7792));
    }
    @Test
    public void getType1495(){
        assertEq(1,StringUtil.getCustomType((char) 7793));
    }
    @Test
    public void getType1496(){
        assertEq(2,StringUtil.getCustomType((char) 7794));
    }
    @Test
    public void getType1497(){
        assertEq(1,StringUtil.getCustomType((char) 7795));
    }
    @Test
    public void getType1498(){
        assertEq(2,StringUtil.getCustomType((char) 7796));
    }
    @Test
    public void getType1499(){
        assertEq(1,StringUtil.getCustomType((char) 7797));
    }
    @Test
    public void getType1500(){
        assertEq(2,StringUtil.getCustomType((char) 7798));
    }
    @Test
    public void getType1501(){
        assertEq(1,StringUtil.getCustomType((char) 7799));
    }
    @Test
    public void getType1502(){
        assertEq(2,StringUtil.getCustomType((char) 7800));
    }
    @Test
    public void getType1503(){
        assertEq(1,StringUtil.getCustomType((char) 7801));
    }
    @Test
    public void getType1504(){
        assertEq(2,StringUtil.getCustomType((char) 7802));
    }
    @Test
    public void getType1505(){
        assertEq(1,StringUtil.getCustomType((char) 7803));
    }
    @Test
    public void getType1506(){
        assertEq(2,StringUtil.getCustomType((char) 7804));
    }
    @Test
    public void getType1507(){
        assertEq(1,StringUtil.getCustomType((char) 7805));
    }
    @Test
    public void getType1508(){
        assertEq(2,StringUtil.getCustomType((char) 7806));
    }
    @Test
    public void getType1509(){
        assertEq(1,StringUtil.getCustomType((char) 7807));
    }
    @Test
    public void getType1510(){
        assertEq(2,StringUtil.getCustomType((char) 7808));
    }
    @Test
    public void getType1511(){
        assertEq(1,StringUtil.getCustomType((char) 7809));
    }
    @Test
    public void getType1512(){
        assertEq(2,StringUtil.getCustomType((char) 7810));
    }
    @Test
    public void getType1513(){
        assertEq(1,StringUtil.getCustomType((char) 7811));
    }
    @Test
    public void getType1514(){
        assertEq(2,StringUtil.getCustomType((char) 7812));
    }
    @Test
    public void getType1515(){
        assertEq(1,StringUtil.getCustomType((char) 7813));
    }
    @Test
    public void getType1516(){
        assertEq(2,StringUtil.getCustomType((char) 7814));
    }
    @Test
    public void getType1517(){
        assertEq(1,StringUtil.getCustomType((char) 7815));
    }
    @Test
    public void getType1518(){
        assertEq(2,StringUtil.getCustomType((char) 7816));
    }
    @Test
    public void getType1519(){
        assertEq(1,StringUtil.getCustomType((char) 7817));
    }
    @Test
    public void getType1520(){
        assertEq(2,StringUtil.getCustomType((char) 7818));
    }
    @Test
    public void getType1521(){
        assertEq(1,StringUtil.getCustomType((char) 7819));
    }
    @Test
    public void getType1522(){
        assertEq(2,StringUtil.getCustomType((char) 7820));
    }
    @Test
    public void getType1523(){
        assertEq(1,StringUtil.getCustomType((char) 7821));
    }
    @Test
    public void getType1524(){
        assertEq(2,StringUtil.getCustomType((char) 7822));
    }
    @Test
    public void getType1525(){
        assertEq(1,StringUtil.getCustomType((char) 7823));
    }
    @Test
    public void getType1526(){
        assertEq(2,StringUtil.getCustomType((char) 7824));
    }
    @Test
    public void getType1527(){
        assertEq(1,StringUtil.getCustomType((char) 7825));
    }
    @Test
    public void getType1528(){
        assertEq(2,StringUtil.getCustomType((char) 7826));
    }
    @Test
    public void getType1529(){
        assertEq(1,StringUtil.getCustomType((char) 7827));
    }
    @Test
    public void getType1530(){
        assertEq(2,StringUtil.getCustomType((char) 7828));
    }
    @Test
    public void getType1531(){
        assertEq(1,StringUtil.getCustomType((char) 7829));
    }
    @Test
    public void getType1532(){
        assertEq(3,StringUtil.getCustomType((char) 7830));
    }
    @Test
    public void getType1533(){
        assertEq(1,StringUtil.getCustomType((char) 7835));
    }
    @Test
    public void getType1534(){
        assertEq(3,StringUtil.getCustomType((char) 7836));
    }
    @Test
    public void getType1535(){
        assertEq(2,StringUtil.getCustomType((char) 7838));
    }
    @Test
    public void getType1536(){
        assertEq(3,StringUtil.getCustomType((char) 7839));
    }
    @Test
    public void getType1537(){
        assertEq(2,StringUtil.getCustomType((char) 7840));
    }
    @Test
    public void getType1538(){
        assertEq(1,StringUtil.getCustomType((char) 7841));
    }
    @Test
    public void getType1539(){
        assertEq(2,StringUtil.getCustomType((char) 7842));
    }
    @Test
    public void getType1540(){
        assertEq(1,StringUtil.getCustomType((char) 7843));
    }
    @Test
    public void getType1541(){
        assertEq(2,StringUtil.getCustomType((char) 7844));
    }
    @Test
    public void getType1542(){
        assertEq(1,StringUtil.getCustomType((char) 7845));
    }
    @Test
    public void getType1543(){
        assertEq(2,StringUtil.getCustomType((char) 7846));
    }
    @Test
    public void getType1544(){
        assertEq(1,StringUtil.getCustomType((char) 7847));
    }
    @Test
    public void getType1545(){
        assertEq(2,StringUtil.getCustomType((char) 7848));
    }
    @Test
    public void getType1546(){
        assertEq(1,StringUtil.getCustomType((char) 7849));
    }
    @Test
    public void getType1547(){
        assertEq(2,StringUtil.getCustomType((char) 7850));
    }
    @Test
    public void getType1548(){
        assertEq(1,StringUtil.getCustomType((char) 7851));
    }
    @Test
    public void getType1549(){
        assertEq(2,StringUtil.getCustomType((char) 7852));
    }
    @Test
    public void getType1550(){
        assertEq(1,StringUtil.getCustomType((char) 7853));
    }
    @Test
    public void getType1551(){
        assertEq(2,StringUtil.getCustomType((char) 7854));
    }
    @Test
    public void getType1552(){
        assertEq(1,StringUtil.getCustomType((char) 7855));
    }
    @Test
    public void getType1553(){
        assertEq(2,StringUtil.getCustomType((char) 7856));
    }
    @Test
    public void getType1554(){
        assertEq(1,StringUtil.getCustomType((char) 7857));
    }
    @Test
    public void getType1555(){
        assertEq(2,StringUtil.getCustomType((char) 7858));
    }
    @Test
    public void getType1556(){
        assertEq(1,StringUtil.getCustomType((char) 7859));
    }
    @Test
    public void getType1557(){
        assertEq(2,StringUtil.getCustomType((char) 7860));
    }
    @Test
    public void getType1558(){
        assertEq(1,StringUtil.getCustomType((char) 7861));
    }
    @Test
    public void getType1559(){
        assertEq(2,StringUtil.getCustomType((char) 7862));
    }
    @Test
    public void getType1560(){
        assertEq(1,StringUtil.getCustomType((char) 7863));
    }
    @Test
    public void getType1561(){
        assertEq(2,StringUtil.getCustomType((char) 7864));
    }
    @Test
    public void getType1562(){
        assertEq(1,StringUtil.getCustomType((char) 7865));
    }
    @Test
    public void getType1563(){
        assertEq(2,StringUtil.getCustomType((char) 7866));
    }
    @Test
    public void getType1564(){
        assertEq(1,StringUtil.getCustomType((char) 7867));
    }
    @Test
    public void getType1565(){
        assertEq(2,StringUtil.getCustomType((char) 7868));
    }
    @Test
    public void getType1566(){
        assertEq(1,StringUtil.getCustomType((char) 7869));
    }
    @Test
    public void getType1567(){
        assertEq(2,StringUtil.getCustomType((char) 7870));
    }
    @Test
    public void getType1568(){
        assertEq(1,StringUtil.getCustomType((char) 7871));
    }
    @Test
    public void getType1569(){
        assertEq(2,StringUtil.getCustomType((char) 7872));
    }
    @Test
    public void getType1570(){
        assertEq(1,StringUtil.getCustomType((char) 7873));
    }
    @Test
    public void getType1571(){
        assertEq(2,StringUtil.getCustomType((char) 7874));
    }
    @Test
    public void getType1572(){
        assertEq(1,StringUtil.getCustomType((char) 7875));
    }
    @Test
    public void getType1573(){
        assertEq(2,StringUtil.getCustomType((char) 7876));
    }
    @Test
    public void getType1574(){
        assertEq(1,StringUtil.getCustomType((char) 7877));
    }
    @Test
    public void getType1575(){
        assertEq(2,StringUtil.getCustomType((char) 7878));
    }
    @Test
    public void getType1576(){
        assertEq(1,StringUtil.getCustomType((char) 7879));
    }
    @Test
    public void getType1577(){
        assertEq(2,StringUtil.getCustomType((char) 7880));
    }
    @Test
    public void getType1578(){
        assertEq(1,StringUtil.getCustomType((char) 7881));
    }
    @Test
    public void getType1579(){
        assertEq(2,StringUtil.getCustomType((char) 7882));
    }
    @Test
    public void getType1580(){
        assertEq(1,StringUtil.getCustomType((char) 7883));
    }
    @Test
    public void getType1581(){
        assertEq(2,StringUtil.getCustomType((char) 7884));
    }
    @Test
    public void getType1582(){
        assertEq(1,StringUtil.getCustomType((char) 7885));
    }
    @Test
    public void getType1583(){
        assertEq(2,StringUtil.getCustomType((char) 7886));
    }
    @Test
    public void getType1584(){
        assertEq(1,StringUtil.getCustomType((char) 7887));
    }
    @Test
    public void getType1585(){
        assertEq(2,StringUtil.getCustomType((char) 7888));
    }
    @Test
    public void getType1586(){
        assertEq(1,StringUtil.getCustomType((char) 7889));
    }
    @Test
    public void getType1587(){
        assertEq(2,StringUtil.getCustomType((char) 7890));
    }
    @Test
    public void getType1588(){
        assertEq(1,StringUtil.getCustomType((char) 7891));
    }
    @Test
    public void getType1589(){
        assertEq(2,StringUtil.getCustomType((char) 7892));
    }
    @Test
    public void getType1590(){
        assertEq(1,StringUtil.getCustomType((char) 7893));
    }
    @Test
    public void getType1591(){
        assertEq(2,StringUtil.getCustomType((char) 7894));
    }
    @Test
    public void getType1592(){
        assertEq(1,StringUtil.getCustomType((char) 7895));
    }
    @Test
    public void getType1593(){
        assertEq(2,StringUtil.getCustomType((char) 7896));
    }
    @Test
    public void getType1594(){
        assertEq(1,StringUtil.getCustomType((char) 7897));
    }
    @Test
    public void getType1595(){
        assertEq(2,StringUtil.getCustomType((char) 7898));
    }
    @Test
    public void getType1596(){
        assertEq(1,StringUtil.getCustomType((char) 7899));
    }
    @Test
    public void getType1597(){
        assertEq(2,StringUtil.getCustomType((char) 7900));
    }
    @Test
    public void getType1598(){
        assertEq(1,StringUtil.getCustomType((char) 7901));
    }
    @Test
    public void getType1599(){
        assertEq(2,StringUtil.getCustomType((char) 7902));
    }
    @Test
    public void getType1600(){
        assertEq(1,StringUtil.getCustomType((char) 7903));
    }
    @Test
    public void getType1601(){
        assertEq(2,StringUtil.getCustomType((char) 7904));
    }
    @Test
    public void getType1602(){
        assertEq(1,StringUtil.getCustomType((char) 7905));
    }
    @Test
    public void getType1603(){
        assertEq(2,StringUtil.getCustomType((char) 7906));
    }
    @Test
    public void getType1604(){
        assertEq(1,StringUtil.getCustomType((char) 7907));
    }
    @Test
    public void getType1605(){
        assertEq(2,StringUtil.getCustomType((char) 7908));
    }
    @Test
    public void getType1606(){
        assertEq(1,StringUtil.getCustomType((char) 7909));
    }
    @Test
    public void getType1607(){
        assertEq(2,StringUtil.getCustomType((char) 7910));
    }
    @Test
    public void getType1608(){
        assertEq(1,StringUtil.getCustomType((char) 7911));
    }
    @Test
    public void getType1609(){
        assertEq(2,StringUtil.getCustomType((char) 7912));
    }
    @Test
    public void getType1610(){
        assertEq(1,StringUtil.getCustomType((char) 7913));
    }
    @Test
    public void getType1611(){
        assertEq(2,StringUtil.getCustomType((char) 7914));
    }
    @Test
    public void getType1612(){
        assertEq(1,StringUtil.getCustomType((char) 7915));
    }
    @Test
    public void getType1613(){
        assertEq(2,StringUtil.getCustomType((char) 7916));
    }
    @Test
    public void getType1614(){
        assertEq(1,StringUtil.getCustomType((char) 7917));
    }
    @Test
    public void getType1615(){
        assertEq(2,StringUtil.getCustomType((char) 7918));
    }
    @Test
    public void getType1616(){
        assertEq(1,StringUtil.getCustomType((char) 7919));
    }
    @Test
    public void getType1617(){
        assertEq(2,StringUtil.getCustomType((char) 7920));
    }
    @Test
    public void getType1618(){
        assertEq(1,StringUtil.getCustomType((char) 7921));
    }
    @Test
    public void getType1619(){
        assertEq(2,StringUtil.getCustomType((char) 7922));
    }
    @Test
    public void getType1620(){
        assertEq(1,StringUtil.getCustomType((char) 7923));
    }
    @Test
    public void getType1621(){
        assertEq(2,StringUtil.getCustomType((char) 7924));
    }
    @Test
    public void getType1622(){
        assertEq(1,StringUtil.getCustomType((char) 7925));
    }
    @Test
    public void getType1623(){
        assertEq(2,StringUtil.getCustomType((char) 7926));
    }
    @Test
    public void getType1624(){
        assertEq(1,StringUtil.getCustomType((char) 7927));
    }
    @Test
    public void getType1625(){
        assertEq(2,StringUtil.getCustomType((char) 7928));
    }
    @Test
    public void getType1626(){
        assertEq(1,StringUtil.getCustomType((char) 7929));
    }
    @Test
    public void getType1627(){
        assertEq(2,StringUtil.getCustomType((char) 7930));
    }
    @Test
    public void getType1628(){
        assertEq(1,StringUtil.getCustomType((char) 7931));
    }
    @Test
    public void getType1629(){
        assertEq(2,StringUtil.getCustomType((char) 7932));
    }
    @Test
    public void getType1630(){
        assertEq(1,StringUtil.getCustomType((char) 7933));
    }
    @Test
    public void getType1631(){
        assertEq(2,StringUtil.getCustomType((char) 7934));
    }
    @Test
    public void getType1632(){
        assertEq(1,StringUtil.getCustomType((char) 7935));
    }
    @Test
    public void getType1633(){
        assertEq(2,StringUtil.getCustomType((char) 7944));
    }
    @Test
    public void getType1634(){
        assertEq(1,StringUtil.getCustomType((char) 7952));
    }
    @Test
    public void getType1635(){
        assertEq(32,StringUtil.getCustomType((char) 7958));
    }
    @Test
    public void getType1636(){
        assertEq(2,StringUtil.getCustomType((char) 7960));
    }
    @Test
    public void getType1637(){
        assertEq(32,StringUtil.getCustomType((char) 7966));
    }
    @Test
    public void getType1638(){
        assertEq(1,StringUtil.getCustomType((char) 7968));
    }
    @Test
    public void getType1639(){
        assertEq(2,StringUtil.getCustomType((char) 7976));
    }
    @Test
    public void getType1640(){
        assertEq(1,StringUtil.getCustomType((char) 7984));
    }
    @Test
    public void getType1641(){
        assertEq(2,StringUtil.getCustomType((char) 7992));
    }
    @Test
    public void getType1642(){
        assertEq(1,StringUtil.getCustomType((char) 8000));
    }
    @Test
    public void getType1643(){
        assertEq(32,StringUtil.getCustomType((char) 8006));
    }
    @Test
    public void getType1644(){
        assertEq(2,StringUtil.getCustomType((char) 8008));
    }
    @Test
    public void getType1645(){
        assertEq(32,StringUtil.getCustomType((char) 8014));
    }
    @Test
    public void getType1646(){
        assertEq(3,StringUtil.getCustomType((char) 8016));
    }
    @Test
    public void getType1647(){
        assertEq(1,StringUtil.getCustomType((char) 8017));
    }
    @Test
    public void getType1648(){
        assertEq(3,StringUtil.getCustomType((char) 8018));
    }
    @Test
    public void getType1649(){
        assertEq(1,StringUtil.getCustomType((char) 8019));
    }
    @Test
    public void getType1650(){
        assertEq(3,StringUtil.getCustomType((char) 8020));
    }
    @Test
    public void getType1651(){
        assertEq(1,StringUtil.getCustomType((char) 8021));
    }
    @Test
    public void getType1652(){
        assertEq(3,StringUtil.getCustomType((char) 8022));
    }
    @Test
    public void getType1653(){
        assertEq(1,StringUtil.getCustomType((char) 8023));
    }
    @Test
    public void getType1654(){
        assertEq(32,StringUtil.getCustomType((char) 8024));
    }
    @Test
    public void getType1655(){
        assertEq(2,StringUtil.getCustomType((char) 8025));
    }
    @Test
    public void getType1656(){
        assertEq(32,StringUtil.getCustomType((char) 8026));
    }
    @Test
    public void getType1657(){
        assertEq(2,StringUtil.getCustomType((char) 8027));
    }
    @Test
    public void getType1658(){
        assertEq(32,StringUtil.getCustomType((char) 8028));
    }
    @Test
    public void getType1659(){
        assertEq(2,StringUtil.getCustomType((char) 8029));
    }
    @Test
    public void getType1660(){
        assertEq(32,StringUtil.getCustomType((char) 8030));
    }
    @Test
    public void getType1661(){
        assertEq(2,StringUtil.getCustomType((char) 8031));
    }
    @Test
    public void getType1662(){
        assertEq(1,StringUtil.getCustomType((char) 8032));
    }
    @Test
    public void getType1663(){
        assertEq(2,StringUtil.getCustomType((char) 8040));
    }
    @Test
    public void getType1664(){
        assertEq(1,StringUtil.getCustomType((char) 8048));
    }
    @Test
    public void getType1665(){
        assertEq(32,StringUtil.getCustomType((char) 8062));
    }
    @Test
    public void getType1666(){
        assertEq(1,StringUtil.getCustomType((char) 8064));
    }
    @Test
    public void getType1667(){
        assertEq(6,StringUtil.getCustomType((char) 8072));
    }
    @Test
    public void getType1668(){
        assertEq(1,StringUtil.getCustomType((char) 8080));
    }
    @Test
    public void getType1669(){
        assertEq(6,StringUtil.getCustomType((char) 8088));
    }
    @Test
    public void getType1670(){
        assertEq(1,StringUtil.getCustomType((char) 8096));
    }
    @Test
    public void getType1671(){
        assertEq(6,StringUtil.getCustomType((char) 8104));
    }
    @Test
    public void getType1672(){
        assertEq(1,StringUtil.getCustomType((char) 8112));
    }
    @Test
    public void getType1673(){
        assertEq(3,StringUtil.getCustomType((char) 8114));
    }
    @Test
    public void getType1674(){
        assertEq(1,StringUtil.getCustomType((char) 8115));
    }
    @Test
    public void getType1675(){
        assertEq(3,StringUtil.getCustomType((char) 8116));
    }
    @Test
    public void getType1676(){
        assertEq(32,StringUtil.getCustomType((char) 8117));
    }
    @Test
    public void getType1677(){
        assertEq(3,StringUtil.getCustomType((char) 8118));
    }
    @Test
    public void getType1678(){
        assertEq(2,StringUtil.getCustomType((char) 8120));
    }
    @Test
    public void getType1679(){
        assertEq(6,StringUtil.getCustomType((char) 8124));
    }
    @Test
    public void getType1680(){
        assertEq(23,StringUtil.getCustomType((char) 8125));
    }
    @Test
    public void getType1681(){
        assertEq(1,StringUtil.getCustomType((char) 8126));
    }
    @Test
    public void getType1682(){
        assertEq(23,StringUtil.getCustomType((char) 8127));
    }
    @Test
    public void getType1683(){
        assertEq(3,StringUtil.getCustomType((char) 8130));
    }
    @Test
    public void getType1684(){
        assertEq(1,StringUtil.getCustomType((char) 8131));
    }
    @Test
    public void getType1685(){
        assertEq(3,StringUtil.getCustomType((char) 8132));
    }
    @Test
    public void getType1686(){
        assertEq(32,StringUtil.getCustomType((char) 8133));
    }
    @Test
    public void getType1687(){
        assertEq(3,StringUtil.getCustomType((char) 8134));
    }
    @Test
    public void getType1688(){
        assertEq(2,StringUtil.getCustomType((char) 8136));
    }
    @Test
    public void getType1689(){
        assertEq(6,StringUtil.getCustomType((char) 8140));
    }
    @Test
    public void getType1690(){
        assertEq(23,StringUtil.getCustomType((char) 8141));
    }
    @Test
    public void getType1691(){
        assertEq(1,StringUtil.getCustomType((char) 8144));
    }
    @Test
    public void getType1692(){
        assertEq(3,StringUtil.getCustomType((char) 8146));
    }
    @Test
    public void getType1693(){
        assertEq(32,StringUtil.getCustomType((char) 8148));
    }
    @Test
    public void getType1694(){
        assertEq(3,StringUtil.getCustomType((char) 8150));
    }
    @Test
    public void getType1695(){
        assertEq(2,StringUtil.getCustomType((char) 8152));
    }
    @Test
    public void getType1696(){
        assertEq(32,StringUtil.getCustomType((char) 8156));
    }
    @Test
    public void getType1697(){
        assertEq(23,StringUtil.getCustomType((char) 8157));
    }
    @Test
    public void getType1698(){
        assertEq(1,StringUtil.getCustomType((char) 8160));
    }
    @Test
    public void getType1699(){
        assertEq(3,StringUtil.getCustomType((char) 8162));
    }
    @Test
    public void getType1700(){
        assertEq(1,StringUtil.getCustomType((char) 8165));
    }
    @Test
    public void getType1701(){
        assertEq(3,StringUtil.getCustomType((char) 8166));
    }
    @Test
    public void getType1702(){
        assertEq(2,StringUtil.getCustomType((char) 8168));
    }
    @Test
    public void getType1703(){
        assertEq(23,StringUtil.getCustomType((char) 8173));
    }
    @Test
    public void getType1704(){
        assertEq(32,StringUtil.getCustomType((char) 8176));
    }
    @Test
    public void getType1705(){
        assertEq(3,StringUtil.getCustomType((char) 8178));
    }
    @Test
    public void getType1706(){
        assertEq(1,StringUtil.getCustomType((char) 8179));
    }
    @Test
    public void getType1707(){
        assertEq(3,StringUtil.getCustomType((char) 8180));
    }
    @Test
    public void getType1708(){
        assertEq(32,StringUtil.getCustomType((char) 8181));
    }
    @Test
    public void getType1709(){
        assertEq(3,StringUtil.getCustomType((char) 8182));
    }
    @Test
    public void getType1710(){
        assertEq(2,StringUtil.getCustomType((char) 8184));
    }
    @Test
    public void getType1711(){
        assertEq(6,StringUtil.getCustomType((char) 8188));
    }
    @Test
    public void getType1712(){
        assertEq(23,StringUtil.getCustomType((char) 8189));
    }
    @Test
    public void getType1713(){
        assertEq(32,StringUtil.getCustomType((char) 8191));
    }
    @Test
    public void getType1714(){
        assertEq(30,StringUtil.getCustomType((char) 8192));
    }
    @Test
    public void getType1715(){
        assertEq(31,StringUtil.getCustomType((char) 8203));
    }
    @Test
    public void getType1716(){
        assertEq(25,StringUtil.getCustomType((char) 8208));
    }
    @Test
    public void getType1717(){
        assertEq(28,StringUtil.getCustomType((char) 8214));
    }
    @Test
    public void getType1718(){
        assertEq(27,StringUtil.getCustomType((char) 8216));
    }
    @Test
    public void getType1719(){
        assertEq(26,StringUtil.getCustomType((char) 8218));
    }
    @Test
    public void getType1720(){
        assertEq(27,StringUtil.getCustomType((char) 8219));
    }
    @Test
    public void getType1721(){
        assertEq(26,StringUtil.getCustomType((char) 8222));
    }
    @Test
    public void getType1722(){
        assertEq(27,StringUtil.getCustomType((char) 8223));
    }
    @Test
    public void getType1723(){
        assertEq(28,StringUtil.getCustomType((char) 8224));
    }
    @Test
    public void getType1724(){
        assertEq(31,StringUtil.getCustomType((char) 8232));
    }
    @Test
    public void getType1725(){
        assertEq(30,StringUtil.getCustomType((char) 8239));
    }
    @Test
    public void getType1726(){
        assertEq(28,StringUtil.getCustomType((char) 8240));
    }
    @Test
    public void getType1727(){
        assertEq(27,StringUtil.getCustomType((char) 8249));
    }
    @Test
    public void getType1728(){
        assertEq(28,StringUtil.getCustomType((char) 8251));
    }
    @Test
    public void getType1729(){
        assertEq(25,StringUtil.getCustomType((char) 8255));
    }
    @Test
    public void getType1730(){
        assertEq(28,StringUtil.getCustomType((char) 8257));
    }
    @Test
    public void getType1731(){
        assertEq(21,StringUtil.getCustomType((char) 8260));
    }
    @Test
    public void getType1732(){
        assertEq(26,StringUtil.getCustomType((char) 8261));
    }
    @Test
    public void getType1733(){
        assertEq(28,StringUtil.getCustomType((char) 8263));
    }
    @Test
    public void getType1734(){
        assertEq(21,StringUtil.getCustomType((char) 8274));
    }
    @Test
    public void getType1735(){
        assertEq(28,StringUtil.getCustomType((char) 8275));
    }
    @Test
    public void getType1736(){
        assertEq(25,StringUtil.getCustomType((char) 8276));
    }
    @Test
    public void getType1737(){
        assertEq(28,StringUtil.getCustomType((char) 8277));
    }
    @Test
    public void getType1738(){
        assertEq(30,StringUtil.getCustomType((char) 8287));
    }
    @Test
    public void getType1739(){
        assertEq(31,StringUtil.getCustomType((char) 8288));
    }
    @Test
    public void getType1740(){
        assertEq(32,StringUtil.getCustomType((char) 8293));
    }
    @Test
    public void getType1741(){
        assertEq(31,StringUtil.getCustomType((char) 8298));
    }
    @Test
    public void getType1742(){
        assertEq(3,StringUtil.getCustomType((char) 8305));
    }
    @Test
    public void getType1743(){
        assertEq(32,StringUtil.getCustomType((char) 8306));
    }
    @Test
    public void getType1744(){
        assertEq(31,StringUtil.getCustomType((char) 8308));
    }
    @Test
    public void getType1745(){
        assertEq(21,StringUtil.getCustomType((char) 8314));
    }
    @Test
    public void getType1746(){
        assertEq(26,StringUtil.getCustomType((char) 8317));
    }
    @Test
    public void getType1747(){
        assertEq(3,StringUtil.getCustomType((char) 8319));
    }
    @Test
    public void getType1748(){
        assertEq(31,StringUtil.getCustomType((char) 8320));
    }
    @Test
    public void getType1749(){
        assertEq(21,StringUtil.getCustomType((char) 8330));
    }
    @Test
    public void getType1750(){
        assertEq(26,StringUtil.getCustomType((char) 8333));
    }
    @Test
    public void getType1751(){
        assertEq(32,StringUtil.getCustomType((char) 8335));
    }
    @Test
    public void getType1752(){
        assertEq(3,StringUtil.getCustomType((char) 8336));
    }
    @Test
    public void getType1753(){
        assertEq(32,StringUtil.getCustomType((char) 8349));
    }
    @Test
    public void getType1754(){
        assertEq(12,StringUtil.getCustomType((char) 8352));
    }
    @Test
    public void getType1755(){
        assertEq(32,StringUtil.getCustomType((char) 8379));
    }
    @Test
    public void getType1756(){
        assertEq(31,StringUtil.getCustomType((char) 8400));
    }
    @Test
    public void getType1757(){
        assertEq(32,StringUtil.getCustomType((char) 8433));
    }
    @Test
    public void getType1758(){
        assertEq(24,StringUtil.getCustomType((char) 8448));
    }
    @Test
    public void getType1759(){
        assertEq(4,StringUtil.getCustomType((char) 8450));
    }
    @Test
    public void getType1760(){
        assertEq(24,StringUtil.getCustomType((char) 8451));
    }
    @Test
    public void getType1761(){
        assertEq(4,StringUtil.getCustomType((char) 8455));
    }
    @Test
    public void getType1762(){
        assertEq(24,StringUtil.getCustomType((char) 8456));
    }
    @Test
    public void getType1763(){
        assertEq(3,StringUtil.getCustomType((char) 8458));
    }
    @Test
    public void getType1764(){
        assertEq(4,StringUtil.getCustomType((char) 8459));
    }
    @Test
    public void getType1765(){
        assertEq(3,StringUtil.getCustomType((char) 8462));
    }
    @Test
    public void getType1766(){
        assertEq(4,StringUtil.getCustomType((char) 8464));
    }
    @Test
    public void getType1767(){
        assertEq(3,StringUtil.getCustomType((char) 8467));
    }
    @Test
    public void getType1768(){
        assertEq(24,StringUtil.getCustomType((char) 8468));
    }
    @Test
    public void getType1769(){
        assertEq(4,StringUtil.getCustomType((char) 8469));
    }
    @Test
    public void getType1770(){
        assertEq(24,StringUtil.getCustomType((char) 8470));
    }
    @Test
    public void getType1771(){
        assertEq(21,StringUtil.getCustomType((char) 8472));
    }
    @Test
    public void getType1772(){
        assertEq(4,StringUtil.getCustomType((char) 8473));
    }
    @Test
    public void getType1773(){
        assertEq(24,StringUtil.getCustomType((char) 8478));
    }
    @Test
    public void getType1774(){
        assertEq(4,StringUtil.getCustomType((char) 8484));
    }
    @Test
    public void getType1775(){
        assertEq(24,StringUtil.getCustomType((char) 8485));
    }
    @Test
    public void getType1776(){
        assertEq(2,StringUtil.getCustomType((char) 8486));
    }
    @Test
    public void getType1777(){
        assertEq(24,StringUtil.getCustomType((char) 8487));
    }
    @Test
    public void getType1778(){
        assertEq(4,StringUtil.getCustomType((char) 8488));
    }
    @Test
    public void getType1779(){
        assertEq(24,StringUtil.getCustomType((char) 8489));
    }
    @Test
    public void getType1780(){
        assertEq(2,StringUtil.getCustomType((char) 8490));
    }
    @Test
    public void getType1781(){
        assertEq(4,StringUtil.getCustomType((char) 8492));
    }
    @Test
    public void getType1782(){
        assertEq(24,StringUtil.getCustomType((char) 8494));
    }
    @Test
    public void getType1783(){
        assertEq(3,StringUtil.getCustomType((char) 8495));
    }
    @Test
    public void getType1784(){
        assertEq(4,StringUtil.getCustomType((char) 8496));
    }
    @Test
    public void getType1785(){
        assertEq(2,StringUtil.getCustomType((char) 8498));
    }
    @Test
    public void getType1786(){
        assertEq(4,StringUtil.getCustomType((char) 8499));
    }
    @Test
    public void getType1787(){
        assertEq(3,StringUtil.getCustomType((char) 8500));
    }
    @Test
    public void getType1788(){
        assertEq(6,StringUtil.getCustomType((char) 8501));
    }
    @Test
    public void getType1789(){
        assertEq(3,StringUtil.getCustomType((char) 8505));
    }
    @Test
    public void getType1790(){
        assertEq(24,StringUtil.getCustomType((char) 8506));
    }
    @Test
    public void getType1791(){
        assertEq(3,StringUtil.getCustomType((char) 8508));
    }
    @Test
    public void getType1792(){
        assertEq(4,StringUtil.getCustomType((char) 8510));
    }
    @Test
    public void getType1793(){
        assertEq(21,StringUtil.getCustomType((char) 8512));
    }
    @Test
    public void getType1794(){
        assertEq(4,StringUtil.getCustomType((char) 8517));
    }
    @Test
    public void getType1795(){
        assertEq(3,StringUtil.getCustomType((char) 8518));
    }
    @Test
    public void getType1796(){
        assertEq(24,StringUtil.getCustomType((char) 8522));
    }
    @Test
    public void getType1797(){
        assertEq(21,StringUtil.getCustomType((char) 8523));
    }
    @Test
    public void getType1798(){
        assertEq(24,StringUtil.getCustomType((char) 8524));
    }
    @Test
    public void getType1799(){
        assertEq(1,StringUtil.getCustomType((char) 8526));
    }
    @Test
    public void getType1800(){
        assertEq(24,StringUtil.getCustomType((char) 8527));
    }
    @Test
    public void getType1801(){
        assertEq(31,StringUtil.getCustomType((char) 8528));
    }
    @Test
    public void getType1802(){
        assertEq(14,StringUtil.getCustomType((char) 8544));
    }
    @Test
    public void getType1803(){
        assertEq(31,StringUtil.getCustomType((char) 8576));
    }
    @Test
    public void getType1804(){
        assertEq(2,StringUtil.getCustomType((char) 8579));
    }
    @Test
    public void getType1805(){
        assertEq(1,StringUtil.getCustomType((char) 8580));
    }
    @Test
    public void getType1806(){
        assertEq(31,StringUtil.getCustomType((char) 8581));
    }
    @Test
    public void getType1807(){
        assertEq(32,StringUtil.getCustomType((char) 8586));
    }
    @Test
    public void getType1808(){
        assertEq(21,StringUtil.getCustomType((char) 8592));
    }
    @Test
    public void getType1809(){
        assertEq(24,StringUtil.getCustomType((char) 8597));
    }
    @Test
    public void getType1810(){
        assertEq(21,StringUtil.getCustomType((char) 8602));
    }
    @Test
    public void getType1811(){
        assertEq(24,StringUtil.getCustomType((char) 8604));
    }
    @Test
    public void getType1812(){
        assertEq(21,StringUtil.getCustomType((char) 8608));
    }
    @Test
    public void getType1813(){
        assertEq(24,StringUtil.getCustomType((char) 8609));
    }
    @Test
    public void getType1814(){
        assertEq(21,StringUtil.getCustomType((char) 8611));
    }
    @Test
    public void getType1815(){
        assertEq(24,StringUtil.getCustomType((char) 8612));
    }
    @Test
    public void getType1816(){
        assertEq(21,StringUtil.getCustomType((char) 8614));
    }
    @Test
    public void getType1817(){
        assertEq(24,StringUtil.getCustomType((char) 8615));
    }
    @Test
    public void getType1818(){
        assertEq(21,StringUtil.getCustomType((char) 8622));
    }
    @Test
    public void getType1819(){
        assertEq(24,StringUtil.getCustomType((char) 8623));
    }
    @Test
    public void getType1820(){
        assertEq(21,StringUtil.getCustomType((char) 8654));
    }
    @Test
    public void getType1821(){
        assertEq(24,StringUtil.getCustomType((char) 8656));
    }
    @Test
    public void getType1822(){
        assertEq(21,StringUtil.getCustomType((char) 8658));
    }
    @Test
    public void getType1823(){
        assertEq(24,StringUtil.getCustomType((char) 8659));
    }
    @Test
    public void getType1824(){
        assertEq(21,StringUtil.getCustomType((char) 8660));
    }
    @Test
    public void getType1825(){
        assertEq(24,StringUtil.getCustomType((char) 8661));
    }
    @Test
    public void getType1826(){
        assertEq(21,StringUtil.getCustomType((char) 8692));
    }
    @Test
    public void getType1827(){
        assertEq(24,StringUtil.getCustomType((char) 8960));
    }
    @Test
    public void getType1828(){
        assertEq(21,StringUtil.getCustomType((char) 8968));
    }
    @Test
    public void getType1829(){
        assertEq(24,StringUtil.getCustomType((char) 8972));
    }
    @Test
    public void getType1830(){
        assertEq(21,StringUtil.getCustomType((char) 8992));
    }
    @Test
    public void getType1831(){
        assertEq(24,StringUtil.getCustomType((char) 8994));
    }
    @Test
    public void getType1832(){
        assertEq(26,StringUtil.getCustomType((char) 9001));
    }
    @Test
    public void getType1833(){
        assertEq(24,StringUtil.getCustomType((char) 9003));
    }
    @Test
    public void getType1834(){
        assertEq(21,StringUtil.getCustomType((char) 9084));
    }
    @Test
    public void getType1835(){
        assertEq(24,StringUtil.getCustomType((char) 9085));
    }
    @Test
    public void getType1836(){
        assertEq(21,StringUtil.getCustomType((char) 9115));
    }
    @Test
    public void getType1837(){
        assertEq(24,StringUtil.getCustomType((char) 9140));
    }
    @Test
    public void getType1838(){
        assertEq(21,StringUtil.getCustomType((char) 9180));
    }
    @Test
    public void getType1839(){
        assertEq(24,StringUtil.getCustomType((char) 9186));
    }
    @Test
    public void getType1840(){
        assertEq(32,StringUtil.getCustomType((char) 9204));
    }
    @Test
    public void getType1841(){
        assertEq(24,StringUtil.getCustomType((char) 9216));
    }
    @Test
    public void getType1842(){
        assertEq(32,StringUtil.getCustomType((char) 9255));
    }
    @Test
    public void getType1843(){
        assertEq(24,StringUtil.getCustomType((char) 9280));
    }
    @Test
    public void getType1844(){
        assertEq(32,StringUtil.getCustomType((char) 9291));
    }
    @Test
    public void getType1845(){
        assertEq(31,StringUtil.getCustomType((char) 9312));
    }
    @Test
    public void getType1846(){
        assertEq(24,StringUtil.getCustomType((char) 9372));
    }
    @Test
    public void getType1847(){
        assertEq(13,StringUtil.getCustomType((char) 9398));
    }
    @Test
    public void getType1848(){
        assertEq(31,StringUtil.getCustomType((char) 9450));
    }
    @Test
    public void getType1849(){
        assertEq(24,StringUtil.getCustomType((char) 9472));
    }
    @Test
    public void getType1850(){
        assertEq(21,StringUtil.getCustomType((char) 9655));
    }
    @Test
    public void getType1851(){
        assertEq(24,StringUtil.getCustomType((char) 9656));
    }
    @Test
    public void getType1852(){
        assertEq(21,StringUtil.getCustomType((char) 9665));
    }
    @Test
    public void getType1853(){
        assertEq(24,StringUtil.getCustomType((char) 9666));
    }
    @Test
    public void getType1854(){
        assertEq(21,StringUtil.getCustomType((char) 9720));
    }
    @Test
    public void getType1855(){
        assertEq(24,StringUtil.getCustomType((char) 9728));
    }
    @Test
    public void getType1856(){
        assertEq(21,StringUtil.getCustomType((char) 9839));
    }
    @Test
    public void getType1857(){
        assertEq(24,StringUtil.getCustomType((char) 9840));
    }
    @Test
    public void getType1858(){
        assertEq(32,StringUtil.getCustomType((char) 9984));
    }
    @Test
    public void getType1859(){
        assertEq(24,StringUtil.getCustomType((char) 9985));
    }
    @Test
    public void getType1860(){
        assertEq(26,StringUtil.getCustomType((char) 10088));
    }
    @Test
    public void getType1861(){
        assertEq(31,StringUtil.getCustomType((char) 10102));
    }
    @Test
    public void getType1862(){
        assertEq(24,StringUtil.getCustomType((char) 10132));
    }
    @Test
    public void getType1863(){
        assertEq(21,StringUtil.getCustomType((char) 10176));
    }
    @Test
    public void getType1864(){
        assertEq(26,StringUtil.getCustomType((char) 10181));
    }
    @Test
    public void getType1865(){
        assertEq(21,StringUtil.getCustomType((char) 10183));
    }
    @Test
    public void getType1866(){
        assertEq(26,StringUtil.getCustomType((char) 10214));
    }
    @Test
    public void getType1867(){
        assertEq(21,StringUtil.getCustomType((char) 10224));
    }
    @Test
    public void getType1868(){
        assertEq(24,StringUtil.getCustomType((char) 10240));
    }
    @Test
    public void getType1869(){
        assertEq(21,StringUtil.getCustomType((char) 10496));
    }
    @Test
    public void getType1870(){
        assertEq(26,StringUtil.getCustomType((char) 10627));
    }
    @Test
    public void getType1871(){
        assertEq(21,StringUtil.getCustomType((char) 10649));
    }
    @Test
    public void getType1872(){
        assertEq(26,StringUtil.getCustomType((char) 10712));
    }
    @Test
    public void getType1873(){
        assertEq(21,StringUtil.getCustomType((char) 10716));
    }
    @Test
    public void getType1874(){
        assertEq(26,StringUtil.getCustomType((char) 10748));
    }
    @Test
    public void getType1875(){
        assertEq(21,StringUtil.getCustomType((char) 10750));
    }
    @Test
    public void getType1876(){
        assertEq(24,StringUtil.getCustomType((char) 11008));
    }
    @Test
    public void getType1877(){
        assertEq(21,StringUtil.getCustomType((char) 11056));
    }
    @Test
    public void getType1878(){
        assertEq(24,StringUtil.getCustomType((char) 11077));
    }
    @Test
    public void getType1879(){
        assertEq(21,StringUtil.getCustomType((char) 11079));
    }
    @Test
    public void getType1880(){
        assertEq(32,StringUtil.getCustomType((char) 11085));
    }
    @Test
    public void getType1881(){
        assertEq(24,StringUtil.getCustomType((char) 11088));
    }
    @Test
    public void getType1882(){
        assertEq(32,StringUtil.getCustomType((char) 11098));
    }
    @Test
    public void getType1883(){
        assertEq(2,StringUtil.getCustomType((char) 11264));
    }
    @Test
    public void getType1884(){
        assertEq(32,StringUtil.getCustomType((char) 11311));
    }
    @Test
    public void getType1885(){
        assertEq(1,StringUtil.getCustomType((char) 11312));
    }
    @Test
    public void getType1886(){
        assertEq(32,StringUtil.getCustomType((char) 11359));
    }
    @Test
    public void getType1887(){
        assertEq(2,StringUtil.getCustomType((char) 11360));
    }
    @Test
    public void getType1888(){
        assertEq(1,StringUtil.getCustomType((char) 11361));
    }
    @Test
    public void getType1889(){
        assertEq(2,StringUtil.getCustomType((char) 11362));
    }
    @Test
    public void getType1890(){
        assertEq(1,StringUtil.getCustomType((char) 11365));
    }
    @Test
    public void getType1891(){
        assertEq(2,StringUtil.getCustomType((char) 11367));
    }
    @Test
    public void getType1892(){
        assertEq(1,StringUtil.getCustomType((char) 11368));
    }
    @Test
    public void getType1893(){
        assertEq(2,StringUtil.getCustomType((char) 11369));
    }
    @Test
    public void getType1894(){
        assertEq(1,StringUtil.getCustomType((char) 11370));
    }
    @Test
    public void getType1895(){
        assertEq(2,StringUtil.getCustomType((char) 11371));
    }
    @Test
    public void getType1896(){
        assertEq(1,StringUtil.getCustomType((char) 11372));
    }
    @Test
    public void getType1897(){
        assertEq(2,StringUtil.getCustomType((char) 11373));
    }
    @Test
    public void getType1898(){
        assertEq(3,StringUtil.getCustomType((char) 11377));
    }
    @Test
    public void getType1899(){
        assertEq(2,StringUtil.getCustomType((char) 11378));
    }
    @Test
    public void getType1900(){
        assertEq(1,StringUtil.getCustomType((char) 11379));
    }
    @Test
    public void getType1901(){
        assertEq(3,StringUtil.getCustomType((char) 11380));
    }
    @Test
    public void getType1902(){
        assertEq(2,StringUtil.getCustomType((char) 11381));
    }
    @Test
    public void getType1903(){
        assertEq(1,StringUtil.getCustomType((char) 11382));
    }
    @Test
    public void getType1904(){
        assertEq(3,StringUtil.getCustomType((char) 11383));
    }
    @Test
    public void getType1905(){
        assertEq(2,StringUtil.getCustomType((char) 11390));
    }
    @Test
    public void getType1906(){
        assertEq(1,StringUtil.getCustomType((char) 11393));
    }
    @Test
    public void getType1907(){
        assertEq(2,StringUtil.getCustomType((char) 11394));
    }
    @Test
    public void getType1908(){
        assertEq(1,StringUtil.getCustomType((char) 11395));
    }
    @Test
    public void getType1909(){
        assertEq(2,StringUtil.getCustomType((char) 11396));
    }
    @Test
    public void getType1910(){
        assertEq(1,StringUtil.getCustomType((char) 11397));
    }
    @Test
    public void getType1911(){
        assertEq(2,StringUtil.getCustomType((char) 11398));
    }
    @Test
    public void getType1912(){
        assertEq(1,StringUtil.getCustomType((char) 11399));
    }
    @Test
    public void getType1913(){
        assertEq(2,StringUtil.getCustomType((char) 11400));
    }
    @Test
    public void getType1914(){
        assertEq(1,StringUtil.getCustomType((char) 11401));
    }
    @Test
    public void getType1915(){
        assertEq(2,StringUtil.getCustomType((char) 11402));
    }
    @Test
    public void getType1916(){
        assertEq(1,StringUtil.getCustomType((char) 11403));
    }
    @Test
    public void getType1917(){
        assertEq(2,StringUtil.getCustomType((char) 11404));
    }
    @Test
    public void getType1918(){
        assertEq(1,StringUtil.getCustomType((char) 11405));
    }
    @Test
    public void getType1919(){
        assertEq(2,StringUtil.getCustomType((char) 11406));
    }
    @Test
    public void getType1920(){
        assertEq(1,StringUtil.getCustomType((char) 11407));
    }
    @Test
    public void getType1921(){
        assertEq(2,StringUtil.getCustomType((char) 11408));
    }
    @Test
    public void getType1922(){
        assertEq(1,StringUtil.getCustomType((char) 11409));
    }
    @Test
    public void getType1923(){
        assertEq(2,StringUtil.getCustomType((char) 11410));
    }
    @Test
    public void getType1924(){
        assertEq(1,StringUtil.getCustomType((char) 11411));
    }
    @Test
    public void getType1925(){
        assertEq(2,StringUtil.getCustomType((char) 11412));
    }
    @Test
    public void getType1926(){
        assertEq(1,StringUtil.getCustomType((char) 11413));
    }
    @Test
    public void getType1927(){
        assertEq(2,StringUtil.getCustomType((char) 11414));
    }
    @Test
    public void getType1928(){
        assertEq(1,StringUtil.getCustomType((char) 11415));
    }
    @Test
    public void getType1929(){
        assertEq(2,StringUtil.getCustomType((char) 11416));
    }
    @Test
    public void getType1930(){
        assertEq(1,StringUtil.getCustomType((char) 11417));
    }
    @Test
    public void getType1931(){
        assertEq(2,StringUtil.getCustomType((char) 11418));
    }
    @Test
    public void getType1932(){
        assertEq(1,StringUtil.getCustomType((char) 11419));
    }
    @Test
    public void getType1933(){
        assertEq(2,StringUtil.getCustomType((char) 11420));
    }
    @Test
    public void getType1934(){
        assertEq(1,StringUtil.getCustomType((char) 11421));
    }
    @Test
    public void getType1935(){
        assertEq(2,StringUtil.getCustomType((char) 11422));
    }
    @Test
    public void getType1936(){
        assertEq(1,StringUtil.getCustomType((char) 11423));
    }
    @Test
    public void getType1937(){
        assertEq(2,StringUtil.getCustomType((char) 11424));
    }
    @Test
    public void getType1938(){
        assertEq(1,StringUtil.getCustomType((char) 11425));
    }
    @Test
    public void getType1939(){
        assertEq(2,StringUtil.getCustomType((char) 11426));
    }
    @Test
    public void getType1940(){
        assertEq(1,StringUtil.getCustomType((char) 11427));
    }
    @Test
    public void getType1941(){
        assertEq(2,StringUtil.getCustomType((char) 11428));
    }
    @Test
    public void getType1942(){
        assertEq(1,StringUtil.getCustomType((char) 11429));
    }
    @Test
    public void getType1943(){
        assertEq(2,StringUtil.getCustomType((char) 11430));
    }
    @Test
    public void getType1944(){
        assertEq(1,StringUtil.getCustomType((char) 11431));
    }
    @Test
    public void getType1945(){
        assertEq(2,StringUtil.getCustomType((char) 11432));
    }
    @Test
    public void getType1946(){
        assertEq(1,StringUtil.getCustomType((char) 11433));
    }
    @Test
    public void getType1947(){
        assertEq(2,StringUtil.getCustomType((char) 11434));
    }
    @Test
    public void getType1948(){
        assertEq(1,StringUtil.getCustomType((char) 11435));
    }
    @Test
    public void getType1949(){
        assertEq(2,StringUtil.getCustomType((char) 11436));
    }
    @Test
    public void getType1950(){
        assertEq(1,StringUtil.getCustomType((char) 11437));
    }
    @Test
    public void getType1951(){
        assertEq(2,StringUtil.getCustomType((char) 11438));
    }
    @Test
    public void getType1952(){
        assertEq(1,StringUtil.getCustomType((char) 11439));
    }
    @Test
    public void getType1953(){
        assertEq(2,StringUtil.getCustomType((char) 11440));
    }
    @Test
    public void getType1954(){
        assertEq(1,StringUtil.getCustomType((char) 11441));
    }
    @Test
    public void getType1955(){
        assertEq(2,StringUtil.getCustomType((char) 11442));
    }
    @Test
    public void getType1956(){
        assertEq(1,StringUtil.getCustomType((char) 11443));
    }
    @Test
    public void getType1957(){
        assertEq(2,StringUtil.getCustomType((char) 11444));
    }
    @Test
    public void getType1958(){
        assertEq(1,StringUtil.getCustomType((char) 11445));
    }
    @Test
    public void getType1959(){
        assertEq(2,StringUtil.getCustomType((char) 11446));
    }
    @Test
    public void getType1960(){
        assertEq(1,StringUtil.getCustomType((char) 11447));
    }
    @Test
    public void getType1961(){
        assertEq(2,StringUtil.getCustomType((char) 11448));
    }
    @Test
    public void getType1962(){
        assertEq(1,StringUtil.getCustomType((char) 11449));
    }
    @Test
    public void getType1963(){
        assertEq(2,StringUtil.getCustomType((char) 11450));
    }
    @Test
    public void getType1964(){
        assertEq(1,StringUtil.getCustomType((char) 11451));
    }
    @Test
    public void getType1965(){
        assertEq(2,StringUtil.getCustomType((char) 11452));
    }
    @Test
    public void getType1966(){
        assertEq(1,StringUtil.getCustomType((char) 11453));
    }
    @Test
    public void getType1967(){
        assertEq(2,StringUtil.getCustomType((char) 11454));
    }
    @Test
    public void getType1968(){
        assertEq(1,StringUtil.getCustomType((char) 11455));
    }
    @Test
    public void getType1969(){
        assertEq(2,StringUtil.getCustomType((char) 11456));
    }
    @Test
    public void getType1970(){
        assertEq(1,StringUtil.getCustomType((char) 11457));
    }
    @Test
    public void getType1971(){
        assertEq(2,StringUtil.getCustomType((char) 11458));
    }
    @Test
    public void getType1972(){
        assertEq(1,StringUtil.getCustomType((char) 11459));
    }
    @Test
    public void getType1973(){
        assertEq(2,StringUtil.getCustomType((char) 11460));
    }
    @Test
    public void getType1974(){
        assertEq(1,StringUtil.getCustomType((char) 11461));
    }
    @Test
    public void getType1975(){
        assertEq(2,StringUtil.getCustomType((char) 11462));
    }
    @Test
    public void getType1976(){
        assertEq(1,StringUtil.getCustomType((char) 11463));
    }
    @Test
    public void getType1977(){
        assertEq(2,StringUtil.getCustomType((char) 11464));
    }
    @Test
    public void getType1978(){
        assertEq(1,StringUtil.getCustomType((char) 11465));
    }
    @Test
    public void getType1979(){
        assertEq(2,StringUtil.getCustomType((char) 11466));
    }
    @Test
    public void getType1980(){
        assertEq(1,StringUtil.getCustomType((char) 11467));
    }
    @Test
    public void getType1981(){
        assertEq(2,StringUtil.getCustomType((char) 11468));
    }
    @Test
    public void getType1982(){
        assertEq(1,StringUtil.getCustomType((char) 11469));
    }
    @Test
    public void getType1983(){
        assertEq(2,StringUtil.getCustomType((char) 11470));
    }
    @Test
    public void getType1984(){
        assertEq(1,StringUtil.getCustomType((char) 11471));
    }
    @Test
    public void getType1985(){
        assertEq(2,StringUtil.getCustomType((char) 11472));
    }
    @Test
    public void getType1986(){
        assertEq(1,StringUtil.getCustomType((char) 11473));
    }
    @Test
    public void getType1987(){
        assertEq(2,StringUtil.getCustomType((char) 11474));
    }
    @Test
    public void getType1988(){
        assertEq(1,StringUtil.getCustomType((char) 11475));
    }
    @Test
    public void getType1989(){
        assertEq(2,StringUtil.getCustomType((char) 11476));
    }
    @Test
    public void getType1990(){
        assertEq(1,StringUtil.getCustomType((char) 11477));
    }
    @Test
    public void getType1991(){
        assertEq(2,StringUtil.getCustomType((char) 11478));
    }
    @Test
    public void getType1992(){
        assertEq(1,StringUtil.getCustomType((char) 11479));
    }
    @Test
    public void getType1993(){
        assertEq(2,StringUtil.getCustomType((char) 11480));
    }
    @Test
    public void getType1994(){
        assertEq(1,StringUtil.getCustomType((char) 11481));
    }
    @Test
    public void getType1995(){
        assertEq(2,StringUtil.getCustomType((char) 11482));
    }
    @Test
    public void getType1996(){
        assertEq(1,StringUtil.getCustomType((char) 11483));
    }
    @Test
    public void getType1997(){
        assertEq(2,StringUtil.getCustomType((char) 11484));
    }
    @Test
    public void getType1998(){
        assertEq(1,StringUtil.getCustomType((char) 11485));
    }
    @Test
    public void getType1999(){
        assertEq(2,StringUtil.getCustomType((char) 11486));
    }
    @Test
    public void getType2000(){
        assertEq(1,StringUtil.getCustomType((char) 11487));
    }
    @Test
    public void getType2001(){
        assertEq(2,StringUtil.getCustomType((char) 11488));
    }
    @Test
    public void getType2002(){
        assertEq(1,StringUtil.getCustomType((char) 11489));
    }
    @Test
    public void getType2003(){
        assertEq(2,StringUtil.getCustomType((char) 11490));
    }
    @Test
    public void getType2004(){
        assertEq(1,StringUtil.getCustomType((char) 11491));
    }
    @Test
    public void getType2005(){
        assertEq(3,StringUtil.getCustomType((char) 11492));
    }
    @Test
    public void getType2006(){
        assertEq(24,StringUtil.getCustomType((char) 11493));
    }
    @Test
    public void getType2007(){
        assertEq(2,StringUtil.getCustomType((char) 11499));
    }
    @Test
    public void getType2008(){
        assertEq(1,StringUtil.getCustomType((char) 11500));
    }
    @Test
    public void getType2009(){
        assertEq(2,StringUtil.getCustomType((char) 11501));
    }
    @Test
    public void getType2010(){
        assertEq(1,StringUtil.getCustomType((char) 11502));
    }
    @Test
    public void getType2011(){
        assertEq(31,StringUtil.getCustomType((char) 11503));
    }
    @Test
    public void getType2012(){
        assertEq(2,StringUtil.getCustomType((char) 11506));
    }
    @Test
    public void getType2013(){
        assertEq(1,StringUtil.getCustomType((char) 11507));
    }
    @Test
    public void getType2014(){
        assertEq(32,StringUtil.getCustomType((char) 11508));
    }
    @Test
    public void getType2015(){
        assertEq(28,StringUtil.getCustomType((char) 11513));
    }
    @Test
    public void getType2016(){
        assertEq(31,StringUtil.getCustomType((char) 11517));
    }
    @Test
    public void getType2017(){
        assertEq(28,StringUtil.getCustomType((char) 11518));
    }
    @Test
    public void getType2018(){
        assertEq(1,StringUtil.getCustomType((char) 11520));
    }
    @Test
    public void getType2019(){
        assertEq(32,StringUtil.getCustomType((char) 11558));
    }
    @Test
    public void getType2020(){
        assertEq(1,StringUtil.getCustomType((char) 11559));
    }
    @Test
    public void getType2021(){
        assertEq(32,StringUtil.getCustomType((char) 11560));
    }
    @Test
    public void getType2022(){
        assertEq(1,StringUtil.getCustomType((char) 11565));
    }
    @Test
    public void getType2023(){
        assertEq(32,StringUtil.getCustomType((char) 11566));
    }
    @Test
    public void getType2024(){
        assertEq(6,StringUtil.getCustomType((char) 11568));
    }
    @Test
    public void getType2025(){
        assertEq(32,StringUtil.getCustomType((char) 11624));
    }
    @Test
    public void getType2026(){
        assertEq(6,StringUtil.getCustomType((char) 11631));
    }
    @Test
    public void getType2027(){
        assertEq(28,StringUtil.getCustomType((char) 11632));
    }
    @Test
    public void getType2028(){
        assertEq(32,StringUtil.getCustomType((char) 11633));
    }
    @Test
    public void getType2029(){
        assertEq(31,StringUtil.getCustomType((char) 11647));
    }
    @Test
    public void getType2030(){
        assertEq(6,StringUtil.getCustomType((char) 11648));
    }
    @Test
    public void getType2031(){
        assertEq(32,StringUtil.getCustomType((char) 11671));
    }
    @Test
    public void getType2032(){
        assertEq(6,StringUtil.getCustomType((char) 11680));
    }
    @Test
    public void getType2033(){
        assertEq(32,StringUtil.getCustomType((char) 11687));
    }
    @Test
    public void getType2034(){
        assertEq(6,StringUtil.getCustomType((char) 11688));
    }
    @Test
    public void getType2035(){
        assertEq(32,StringUtil.getCustomType((char) 11695));
    }
    @Test
    public void getType2036(){
        assertEq(6,StringUtil.getCustomType((char) 11696));
    }
    @Test
    public void getType2037(){
        assertEq(32,StringUtil.getCustomType((char) 11703));
    }
    @Test
    public void getType2038(){
        assertEq(6,StringUtil.getCustomType((char) 11704));
    }
    @Test
    public void getType2039(){
        assertEq(32,StringUtil.getCustomType((char) 11711));
    }
    @Test
    public void getType2040(){
        assertEq(6,StringUtil.getCustomType((char) 11712));
    }
    @Test
    public void getType2041(){
        assertEq(32,StringUtil.getCustomType((char) 11719));
    }
    @Test
    public void getType2042(){
        assertEq(6,StringUtil.getCustomType((char) 11720));
    }
    @Test
    public void getType2043(){
        assertEq(32,StringUtil.getCustomType((char) 11727));
    }
    @Test
    public void getType2044(){
        assertEq(6,StringUtil.getCustomType((char) 11728));
    }
    @Test
    public void getType2045(){
        assertEq(32,StringUtil.getCustomType((char) 11735));
    }
    @Test
    public void getType2046(){
        assertEq(6,StringUtil.getCustomType((char) 11736));
    }
    @Test
    public void getType2047(){
        assertEq(32,StringUtil.getCustomType((char) 11743));
    }
    @Test
    public void getType2048(){
        assertEq(31,StringUtil.getCustomType((char) 11744));
    }
    @Test
    public void getType2049(){
        assertEq(28,StringUtil.getCustomType((char) 11776));
    }
    @Test
    public void getType2050(){
        assertEq(27,StringUtil.getCustomType((char) 11778));
    }
    @Test
    public void getType2051(){
        assertEq(28,StringUtil.getCustomType((char) 11782));
    }
    @Test
    public void getType2052(){
        assertEq(27,StringUtil.getCustomType((char) 11785));
    }
    @Test
    public void getType2053(){
        assertEq(28,StringUtil.getCustomType((char) 11787));
    }
    @Test
    public void getType2054(){
        assertEq(27,StringUtil.getCustomType((char) 11788));
    }
    @Test
    public void getType2055(){
        assertEq(28,StringUtil.getCustomType((char) 11790));
    }
    @Test
    public void getType2056(){
        assertEq(25,StringUtil.getCustomType((char) 11799));
    }
    @Test
    public void getType2057(){
        assertEq(28,StringUtil.getCustomType((char) 11800));
    }
    @Test
    public void getType2058(){
        assertEq(25,StringUtil.getCustomType((char) 11802));
    }
    @Test
    public void getType2059(){
        assertEq(28,StringUtil.getCustomType((char) 11803));
    }
    @Test
    public void getType2060(){
        assertEq(27,StringUtil.getCustomType((char) 11804));
    }
    @Test
    public void getType2061(){
        assertEq(28,StringUtil.getCustomType((char) 11806));
    }
    @Test
    public void getType2062(){
        assertEq(27,StringUtil.getCustomType((char) 11808));
    }
    @Test
    public void getType2063(){
        assertEq(26,StringUtil.getCustomType((char) 11810));
    }
    @Test
    public void getType2064(){
        assertEq(28,StringUtil.getCustomType((char) 11818));
    }
    @Test
    public void getType2065(){
        assertEq(7,StringUtil.getCustomType((char) 11823));
    }
    @Test
    public void getType2066(){
        assertEq(28,StringUtil.getCustomType((char) 11824));
    }
    @Test
    public void getType2067(){
        assertEq(25,StringUtil.getCustomType((char) 11834));
    }
    @Test
    public void getType2068(){
        assertEq(32,StringUtil.getCustomType((char) 11836));
    }
    @Test
    public void getType2069(){
        assertEq(24,StringUtil.getCustomType((char) 11904));
    }
    @Test
    public void getType2070(){
        assertEq(32,StringUtil.getCustomType((char) 11930));
    }
    @Test
    public void getType2071(){
        assertEq(24,StringUtil.getCustomType((char) 11931));
    }
    @Test
    public void getType2072(){
        assertEq(32,StringUtil.getCustomType((char) 12020));
    }
    @Test
    public void getType2073(){
        assertEq(24,StringUtil.getCustomType((char) 12032));
    }
    @Test
    public void getType2074(){
        assertEq(32,StringUtil.getCustomType((char) 12246));
    }
    @Test
    public void getType2075(){
        assertEq(24,StringUtil.getCustomType((char) 12272));
    }
    @Test
    public void getType2076(){
        assertEq(32,StringUtil.getCustomType((char) 12284));
    }
    @Test
    public void getType2077(){
        assertEq(30,StringUtil.getCustomType((char) 12288));
    }
    @Test
    public void getType2078(){
        assertEq(28,StringUtil.getCustomType((char) 12289));
    }
    @Test
    public void getType2079(){
        assertEq(24,StringUtil.getCustomType((char) 12292));
    }
    @Test
    public void getType2080(){
        assertEq(6,StringUtil.getCustomType((char) 12293));
    }
    @Test
    public void getType2081(){
        assertEq(31,StringUtil.getCustomType((char) 12295));
    }
    @Test
    public void getType2082(){
        assertEq(26,StringUtil.getCustomType((char) 12296));
    }
    @Test
    public void getType2083(){
        assertEq(24,StringUtil.getCustomType((char) 12306));
    }
    @Test
    public void getType2084(){
        assertEq(26,StringUtil.getCustomType((char) 12308));
    }
    @Test
    public void getType2085(){
        assertEq(25,StringUtil.getCustomType((char) 12316));
    }
    @Test
    public void getType2086(){
        assertEq(26,StringUtil.getCustomType((char) 12317));
    }
    @Test
    public void getType2087(){
        assertEq(24,StringUtil.getCustomType((char) 12320));
    }
    @Test
    public void getType2088(){
        assertEq(31,StringUtil.getCustomType((char) 12321));
    }
    @Test
    public void getType2089(){
        assertEq(25,StringUtil.getCustomType((char) 12336));
    }
    @Test
    public void getType2090(){
        assertEq(6,StringUtil.getCustomType((char) 12337));
    }
    @Test
    public void getType2091(){
        assertEq(24,StringUtil.getCustomType((char) 12342));
    }
    @Test
    public void getType2092(){
        assertEq(31,StringUtil.getCustomType((char) 12344));
    }
    @Test
    public void getType2093(){
        assertEq(6,StringUtil.getCustomType((char) 12347));
    }
    @Test
    public void getType2094(){
        assertEq(28,StringUtil.getCustomType((char) 12349));
    }
    @Test
    public void getType2095(){
        assertEq(24,StringUtil.getCustomType((char) 12350));
    }
    @Test
    public void getType2096(){
        assertEq(32,StringUtil.getCustomType((char) 12352));
    }
    @Test
    public void getType2097(){
        assertEq(6,StringUtil.getCustomType((char) 12353));
    }
    @Test
    public void getType2098(){
        assertEq(32,StringUtil.getCustomType((char) 12439));
    }
    @Test
    public void getType2099(){
        assertEq(31,StringUtil.getCustomType((char) 12441));
    }
    @Test
    public void getType2100(){
        assertEq(23,StringUtil.getCustomType((char) 12443));
    }
    @Test
    public void getType2101(){
        assertEq(6,StringUtil.getCustomType((char) 12445));
    }
    @Test
    public void getType2102(){
        assertEq(25,StringUtil.getCustomType((char) 12448));
    }
    @Test
    public void getType2103(){
        assertEq(6,StringUtil.getCustomType((char) 12449));
    }
    @Test
    public void getType2104(){
        assertEq(28,StringUtil.getCustomType((char) 12539));
    }
    @Test
    public void getType2105(){
        assertEq(6,StringUtil.getCustomType((char) 12540));
    }
    @Test
    public void getType2106(){
        assertEq(32,StringUtil.getCustomType((char) 12544));
    }
    @Test
    public void getType2107(){
        assertEq(6,StringUtil.getCustomType((char) 12549));
    }
    @Test
    public void getType2108(){
        assertEq(32,StringUtil.getCustomType((char) 12590));
    }
    @Test
    public void getType2109(){
        assertEq(6,StringUtil.getCustomType((char) 12593));
    }
    @Test
    public void getType2110(){
        assertEq(32,StringUtil.getCustomType((char) 12687));
    }
    @Test
    public void getType2111(){
        assertEq(24,StringUtil.getCustomType((char) 12688));
    }
    @Test
    public void getType2112(){
        assertEq(31,StringUtil.getCustomType((char) 12690));
    }
    @Test
    public void getType2113(){
        assertEq(24,StringUtil.getCustomType((char) 12694));
    }
    @Test
    public void getType2114(){
        assertEq(6,StringUtil.getCustomType((char) 12704));
    }
    @Test
    public void getType2115(){
        assertEq(32,StringUtil.getCustomType((char) 12731));
    }
    @Test
    public void getType2116(){
        assertEq(24,StringUtil.getCustomType((char) 12736));
    }
    @Test
    public void getType2117(){
        assertEq(32,StringUtil.getCustomType((char) 12772));
    }
    @Test
    public void getType2118(){
        assertEq(6,StringUtil.getCustomType((char) 12784));
    }
    @Test
    public void getType2119(){
        assertEq(24,StringUtil.getCustomType((char) 12800));
    }
    @Test
    public void getType2120(){
        assertEq(32,StringUtil.getCustomType((char) 12831));
    }
    @Test
    public void getType2121(){
        assertEq(31,StringUtil.getCustomType((char) 12832));
    }
    @Test
    public void getType2122(){
        assertEq(24,StringUtil.getCustomType((char) 12842));
    }
    @Test
    public void getType2123(){
        assertEq(31,StringUtil.getCustomType((char) 12872));
    }
    @Test
    public void getType2124(){
        assertEq(24,StringUtil.getCustomType((char) 12880));
    }
    @Test
    public void getType2125(){
        assertEq(31,StringUtil.getCustomType((char) 12881));
    }
    @Test
    public void getType2126(){
        assertEq(24,StringUtil.getCustomType((char) 12896));
    }
    @Test
    public void getType2127(){
        assertEq(31,StringUtil.getCustomType((char) 12928));
    }
    @Test
    public void getType2128(){
        assertEq(24,StringUtil.getCustomType((char) 12938));
    }
    @Test
    public void getType2129(){
        assertEq(31,StringUtil.getCustomType((char) 12977));
    }
    @Test
    public void getType2130(){
        assertEq(24,StringUtil.getCustomType((char) 12992));
    }
    @Test
    public void getType2131(){
        assertEq(32,StringUtil.getCustomType((char) 13055));
    }
    @Test
    public void getType2132(){
        assertEq(24,StringUtil.getCustomType((char) 13056));
    }
    @Test
    public void getType2133(){
        assertEq(6,StringUtil.getCustomType((char) 13312));
    }
    @Test
    public void getType2134(){
        assertEq(32,StringUtil.getCustomType((char) 19894));
    }
    @Test
    public void getType2135(){
        assertEq(24,StringUtil.getCustomType((char) 19904));
    }
    @Test
    public void getType2136(){
        assertEq(6,StringUtil.getCustomType((char) 19968));
    }
    @Test
    public void getType2137(){
        assertEq(32,StringUtil.getCustomType((char) 40909));
    }
    @Test
    public void getType2138(){
        assertEq(6,StringUtil.getCustomType((char) 40960));
    }
    @Test
    public void getType2139(){
        assertEq(32,StringUtil.getCustomType((char) 42125));
    }
    @Test
    public void getType2140(){
        assertEq(24,StringUtil.getCustomType((char) 42128));
    }
    @Test
    public void getType2141(){
        assertEq(32,StringUtil.getCustomType((char) 42183));
    }
    @Test
    public void getType2142(){
        assertEq(6,StringUtil.getCustomType((char) 42192));
    }
    @Test
    public void getType2143(){
        assertEq(28,StringUtil.getCustomType((char) 42238));
    }
    @Test
    public void getType2144(){
        assertEq(6,StringUtil.getCustomType((char) 42240));
    }
    @Test
    public void getType2145(){
        assertEq(28,StringUtil.getCustomType((char) 42509));
    }
    @Test
    public void getType2146(){
        assertEq(6,StringUtil.getCustomType((char) 42512));
    }
    @Test
    public void getType2147(){
        assertEq(10,StringUtil.getCustomType((char) 42528));
    }
    @Test
    public void getType2148(){
        assertEq(6,StringUtil.getCustomType((char) 42538));
    }
    @Test
    public void getType2149(){
        assertEq(32,StringUtil.getCustomType((char) 42540));
    }
    @Test
    public void getType2150(){
        assertEq(2,StringUtil.getCustomType((char) 42560));
    }
    @Test
    public void getType2151(){
        assertEq(1,StringUtil.getCustomType((char) 42561));
    }
    @Test
    public void getType2152(){
        assertEq(2,StringUtil.getCustomType((char) 42562));
    }
    @Test
    public void getType2153(){
        assertEq(1,StringUtil.getCustomType((char) 42563));
    }
    @Test
    public void getType2154(){
        assertEq(2,StringUtil.getCustomType((char) 42564));
    }
    @Test
    public void getType2155(){
        assertEq(1,StringUtil.getCustomType((char) 42565));
    }
    @Test
    public void getType2156(){
        assertEq(2,StringUtil.getCustomType((char) 42566));
    }
    @Test
    public void getType2157(){
        assertEq(1,StringUtil.getCustomType((char) 42567));
    }
    @Test
    public void getType2158(){
        assertEq(2,StringUtil.getCustomType((char) 42568));
    }
    @Test
    public void getType2159(){
        assertEq(1,StringUtil.getCustomType((char) 42569));
    }
    @Test
    public void getType2160(){
        assertEq(2,StringUtil.getCustomType((char) 42570));
    }
    @Test
    public void getType2161(){
        assertEq(1,StringUtil.getCustomType((char) 42571));
    }
    @Test
    public void getType2162(){
        assertEq(2,StringUtil.getCustomType((char) 42572));
    }
    @Test
    public void getType2163(){
        assertEq(1,StringUtil.getCustomType((char) 42573));
    }
    @Test
    public void getType2164(){
        assertEq(2,StringUtil.getCustomType((char) 42574));
    }
    @Test
    public void getType2165(){
        assertEq(1,StringUtil.getCustomType((char) 42575));
    }
    @Test
    public void getType2166(){
        assertEq(2,StringUtil.getCustomType((char) 42576));
    }
    @Test
    public void getType2167(){
        assertEq(1,StringUtil.getCustomType((char) 42577));
    }
    @Test
    public void getType2168(){
        assertEq(2,StringUtil.getCustomType((char) 42578));
    }
    @Test
    public void getType2169(){
        assertEq(1,StringUtil.getCustomType((char) 42579));
    }
    @Test
    public void getType2170(){
        assertEq(2,StringUtil.getCustomType((char) 42580));
    }
    @Test
    public void getType2171(){
        assertEq(1,StringUtil.getCustomType((char) 42581));
    }
    @Test
    public void getType2172(){
        assertEq(2,StringUtil.getCustomType((char) 42582));
    }
    @Test
    public void getType2173(){
        assertEq(1,StringUtil.getCustomType((char) 42583));
    }
    @Test
    public void getType2174(){
        assertEq(2,StringUtil.getCustomType((char) 42584));
    }
    @Test
    public void getType2175(){
        assertEq(1,StringUtil.getCustomType((char) 42585));
    }
    @Test
    public void getType2176(){
        assertEq(2,StringUtil.getCustomType((char) 42586));
    }
    @Test
    public void getType2177(){
        assertEq(1,StringUtil.getCustomType((char) 42587));
    }
    @Test
    public void getType2178(){
        assertEq(2,StringUtil.getCustomType((char) 42588));
    }
    @Test
    public void getType2179(){
        assertEq(1,StringUtil.getCustomType((char) 42589));
    }
    @Test
    public void getType2180(){
        assertEq(2,StringUtil.getCustomType((char) 42590));
    }
    @Test
    public void getType2181(){
        assertEq(1,StringUtil.getCustomType((char) 42591));
    }
    @Test
    public void getType2182(){
        assertEq(2,StringUtil.getCustomType((char) 42592));
    }
    @Test
    public void getType2183(){
        assertEq(1,StringUtil.getCustomType((char) 42593));
    }
    @Test
    public void getType2184(){
        assertEq(2,StringUtil.getCustomType((char) 42594));
    }
    @Test
    public void getType2185(){
        assertEq(1,StringUtil.getCustomType((char) 42595));
    }
    @Test
    public void getType2186(){
        assertEq(2,StringUtil.getCustomType((char) 42596));
    }
    @Test
    public void getType2187(){
        assertEq(1,StringUtil.getCustomType((char) 42597));
    }
    @Test
    public void getType2188(){
        assertEq(2,StringUtil.getCustomType((char) 42598));
    }
    @Test
    public void getType2189(){
        assertEq(1,StringUtil.getCustomType((char) 42599));
    }
    @Test
    public void getType2190(){
        assertEq(2,StringUtil.getCustomType((char) 42600));
    }
    @Test
    public void getType2191(){
        assertEq(1,StringUtil.getCustomType((char) 42601));
    }
    @Test
    public void getType2192(){
        assertEq(2,StringUtil.getCustomType((char) 42602));
    }
    @Test
    public void getType2193(){
        assertEq(1,StringUtil.getCustomType((char) 42603));
    }
    @Test
    public void getType2194(){
        assertEq(2,StringUtil.getCustomType((char) 42604));
    }
    @Test
    public void getType2195(){
        assertEq(1,StringUtil.getCustomType((char) 42605));
    }
    @Test
    public void getType2196(){
        assertEq(6,StringUtil.getCustomType((char) 42606));
    }
    @Test
    public void getType2197(){
        assertEq(31,StringUtil.getCustomType((char) 42607));
    }
    @Test
    public void getType2198(){
        assertEq(28,StringUtil.getCustomType((char) 42611));
    }
    @Test
    public void getType2199(){
        assertEq(31,StringUtil.getCustomType((char) 42612));
    }
    @Test
    public void getType2200(){
        assertEq(28,StringUtil.getCustomType((char) 42622));
    }
    @Test
    public void getType2201(){
        assertEq(7,StringUtil.getCustomType((char) 42623));
    }
    @Test
    public void getType2202(){
        assertEq(2,StringUtil.getCustomType((char) 42624));
    }
    @Test
    public void getType2203(){
        assertEq(1,StringUtil.getCustomType((char) 42625));
    }
    @Test
    public void getType2204(){
        assertEq(2,StringUtil.getCustomType((char) 42626));
    }
    @Test
    public void getType2205(){
        assertEq(1,StringUtil.getCustomType((char) 42627));
    }
    @Test
    public void getType2206(){
        assertEq(2,StringUtil.getCustomType((char) 42628));
    }
    @Test
    public void getType2207(){
        assertEq(1,StringUtil.getCustomType((char) 42629));
    }
    @Test
    public void getType2208(){
        assertEq(2,StringUtil.getCustomType((char) 42630));
    }
    @Test
    public void getType2209(){
        assertEq(1,StringUtil.getCustomType((char) 42631));
    }
    @Test
    public void getType2210(){
        assertEq(2,StringUtil.getCustomType((char) 42632));
    }
    @Test
    public void getType2211(){
        assertEq(1,StringUtil.getCustomType((char) 42633));
    }
    @Test
    public void getType2212(){
        assertEq(2,StringUtil.getCustomType((char) 42634));
    }
    @Test
    public void getType2213(){
        assertEq(1,StringUtil.getCustomType((char) 42635));
    }
    @Test
    public void getType2214(){
        assertEq(2,StringUtil.getCustomType((char) 42636));
    }
    @Test
    public void getType2215(){
        assertEq(1,StringUtil.getCustomType((char) 42637));
    }
    @Test
    public void getType2216(){
        assertEq(2,StringUtil.getCustomType((char) 42638));
    }
    @Test
    public void getType2217(){
        assertEq(1,StringUtil.getCustomType((char) 42639));
    }
    @Test
    public void getType2218(){
        assertEq(2,StringUtil.getCustomType((char) 42640));
    }
    @Test
    public void getType2219(){
        assertEq(1,StringUtil.getCustomType((char) 42641));
    }
    @Test
    public void getType2220(){
        assertEq(2,StringUtil.getCustomType((char) 42642));
    }
    @Test
    public void getType2221(){
        assertEq(1,StringUtil.getCustomType((char) 42643));
    }
    @Test
    public void getType2222(){
        assertEq(2,StringUtil.getCustomType((char) 42644));
    }
    @Test
    public void getType2223(){
        assertEq(1,StringUtil.getCustomType((char) 42645));
    }
    @Test
    public void getType2224(){
        assertEq(2,StringUtil.getCustomType((char) 42646));
    }
    @Test
    public void getType2225(){
        assertEq(1,StringUtil.getCustomType((char) 42647));
    }
    @Test
    public void getType2226(){
        assertEq(32,StringUtil.getCustomType((char) 42648));
    }
    @Test
    public void getType2227(){
        assertEq(31,StringUtil.getCustomType((char) 42655));
    }
    @Test
    public void getType2228(){
        assertEq(6,StringUtil.getCustomType((char) 42656));
    }
    @Test
    public void getType2229(){
        assertEq(31,StringUtil.getCustomType((char) 42726));
    }
    @Test
    public void getType2230(){
        assertEq(28,StringUtil.getCustomType((char) 42738));
    }
    @Test
    public void getType2231(){
        assertEq(32,StringUtil.getCustomType((char) 42744));
    }
    @Test
    public void getType2232(){
        assertEq(23,StringUtil.getCustomType((char) 42752));
    }
    @Test
    public void getType2233(){
        assertEq(7,StringUtil.getCustomType((char) 42775));
    }
    @Test
    public void getType2234(){
        assertEq(23,StringUtil.getCustomType((char) 42784));
    }
    @Test
    public void getType2235(){
        assertEq(2,StringUtil.getCustomType((char) 42786));
    }
    @Test
    public void getType2236(){
        assertEq(1,StringUtil.getCustomType((char) 42787));
    }
    @Test
    public void getType2237(){
        assertEq(2,StringUtil.getCustomType((char) 42788));
    }
    @Test
    public void getType2238(){
        assertEq(1,StringUtil.getCustomType((char) 42789));
    }
    @Test
    public void getType2239(){
        assertEq(2,StringUtil.getCustomType((char) 42790));
    }
    @Test
    public void getType2240(){
        assertEq(1,StringUtil.getCustomType((char) 42791));
    }
    @Test
    public void getType2241(){
        assertEq(2,StringUtil.getCustomType((char) 42792));
    }
    @Test
    public void getType2242(){
        assertEq(1,StringUtil.getCustomType((char) 42793));
    }
    @Test
    public void getType2243(){
        assertEq(2,StringUtil.getCustomType((char) 42794));
    }
    @Test
    public void getType2244(){
        assertEq(1,StringUtil.getCustomType((char) 42795));
    }
    @Test
    public void getType2245(){
        assertEq(2,StringUtil.getCustomType((char) 42796));
    }
    @Test
    public void getType2246(){
        assertEq(1,StringUtil.getCustomType((char) 42797));
    }
    @Test
    public void getType2247(){
        assertEq(2,StringUtil.getCustomType((char) 42798));
    }
    @Test
    public void getType2248(){
        assertEq(1,StringUtil.getCustomType((char) 42799));
    }
    @Test
    public void getType2249(){
        assertEq(3,StringUtil.getCustomType((char) 42800));
    }
    @Test
    public void getType2250(){
        assertEq(2,StringUtil.getCustomType((char) 42802));
    }
    @Test
    public void getType2251(){
        assertEq(1,StringUtil.getCustomType((char) 42803));
    }
    @Test
    public void getType2252(){
        assertEq(2,StringUtil.getCustomType((char) 42804));
    }
    @Test
    public void getType2253(){
        assertEq(1,StringUtil.getCustomType((char) 42805));
    }
    @Test
    public void getType2254(){
        assertEq(2,StringUtil.getCustomType((char) 42806));
    }
    @Test
    public void getType2255(){
        assertEq(1,StringUtil.getCustomType((char) 42807));
    }
    @Test
    public void getType2256(){
        assertEq(2,StringUtil.getCustomType((char) 42808));
    }
    @Test
    public void getType2257(){
        assertEq(1,StringUtil.getCustomType((char) 42809));
    }
    @Test
    public void getType2258(){
        assertEq(2,StringUtil.getCustomType((char) 42810));
    }
    @Test
    public void getType2259(){
        assertEq(1,StringUtil.getCustomType((char) 42811));
    }
    @Test
    public void getType2260(){
        assertEq(2,StringUtil.getCustomType((char) 42812));
    }
    @Test
    public void getType2261(){
        assertEq(1,StringUtil.getCustomType((char) 42813));
    }
    @Test
    public void getType2262(){
        assertEq(2,StringUtil.getCustomType((char) 42814));
    }
    @Test
    public void getType2263(){
        assertEq(1,StringUtil.getCustomType((char) 42815));
    }
    @Test
    public void getType2264(){
        assertEq(2,StringUtil.getCustomType((char) 42816));
    }
    @Test
    public void getType2265(){
        assertEq(1,StringUtil.getCustomType((char) 42817));
    }
    @Test
    public void getType2266(){
        assertEq(2,StringUtil.getCustomType((char) 42818));
    }
    @Test
    public void getType2267(){
        assertEq(1,StringUtil.getCustomType((char) 42819));
    }
    @Test
    public void getType2268(){
        assertEq(2,StringUtil.getCustomType((char) 42820));
    }
    @Test
    public void getType2269(){
        assertEq(1,StringUtil.getCustomType((char) 42821));
    }
    @Test
    public void getType2270(){
        assertEq(2,StringUtil.getCustomType((char) 42822));
    }
    @Test
    public void getType2271(){
        assertEq(1,StringUtil.getCustomType((char) 42823));
    }
    @Test
    public void getType2272(){
        assertEq(2,StringUtil.getCustomType((char) 42824));
    }
    @Test
    public void getType2273(){
        assertEq(1,StringUtil.getCustomType((char) 42825));
    }
    @Test
    public void getType2274(){
        assertEq(2,StringUtil.getCustomType((char) 42826));
    }
    @Test
    public void getType2275(){
        assertEq(1,StringUtil.getCustomType((char) 42827));
    }
    @Test
    public void getType2276(){
        assertEq(2,StringUtil.getCustomType((char) 42828));
    }
    @Test
    public void getType2277(){
        assertEq(1,StringUtil.getCustomType((char) 42829));
    }
    @Test
    public void getType2278(){
        assertEq(2,StringUtil.getCustomType((char) 42830));
    }
    @Test
    public void getType2279(){
        assertEq(1,StringUtil.getCustomType((char) 42831));
    }
    @Test
    public void getType2280(){
        assertEq(2,StringUtil.getCustomType((char) 42832));
    }
    @Test
    public void getType2281(){
        assertEq(1,StringUtil.getCustomType((char) 42833));
    }
    @Test
    public void getType2282(){
        assertEq(2,StringUtil.getCustomType((char) 42834));
    }
    @Test
    public void getType2283(){
        assertEq(1,StringUtil.getCustomType((char) 42835));
    }
    @Test
    public void getType2284(){
        assertEq(2,StringUtil.getCustomType((char) 42836));
    }
    @Test
    public void getType2285(){
        assertEq(1,StringUtil.getCustomType((char) 42837));
    }
    @Test
    public void getType2286(){
        assertEq(2,StringUtil.getCustomType((char) 42838));
    }
    @Test
    public void getType2287(){
        assertEq(1,StringUtil.getCustomType((char) 42839));
    }
    @Test
    public void getType2288(){
        assertEq(2,StringUtil.getCustomType((char) 42840));
    }
    @Test
    public void getType2289(){
        assertEq(1,StringUtil.getCustomType((char) 42841));
    }
    @Test
    public void getType2290(){
        assertEq(2,StringUtil.getCustomType((char) 42842));
    }
    @Test
    public void getType2291(){
        assertEq(1,StringUtil.getCustomType((char) 42843));
    }
    @Test
    public void getType2292(){
        assertEq(2,StringUtil.getCustomType((char) 42844));
    }
    @Test
    public void getType2293(){
        assertEq(1,StringUtil.getCustomType((char) 42845));
    }
    @Test
    public void getType2294(){
        assertEq(2,StringUtil.getCustomType((char) 42846));
    }
    @Test
    public void getType2295(){
        assertEq(1,StringUtil.getCustomType((char) 42847));
    }
    @Test
    public void getType2296(){
        assertEq(2,StringUtil.getCustomType((char) 42848));
    }
    @Test
    public void getType2297(){
        assertEq(1,StringUtil.getCustomType((char) 42849));
    }
    @Test
    public void getType2298(){
        assertEq(2,StringUtil.getCustomType((char) 42850));
    }
    @Test
    public void getType2299(){
        assertEq(1,StringUtil.getCustomType((char) 42851));
    }
    @Test
    public void getType2300(){
        assertEq(2,StringUtil.getCustomType((char) 42852));
    }
    @Test
    public void getType2301(){
        assertEq(1,StringUtil.getCustomType((char) 42853));
    }
    @Test
    public void getType2302(){
        assertEq(2,StringUtil.getCustomType((char) 42854));
    }
    @Test
    public void getType2303(){
        assertEq(1,StringUtil.getCustomType((char) 42855));
    }
    @Test
    public void getType2304(){
        assertEq(2,StringUtil.getCustomType((char) 42856));
    }
    @Test
    public void getType2305(){
        assertEq(1,StringUtil.getCustomType((char) 42857));
    }
    @Test
    public void getType2306(){
        assertEq(2,StringUtil.getCustomType((char) 42858));
    }
    @Test
    public void getType2307(){
        assertEq(1,StringUtil.getCustomType((char) 42859));
    }
    @Test
    public void getType2308(){
        assertEq(2,StringUtil.getCustomType((char) 42860));
    }
    @Test
    public void getType2309(){
        assertEq(1,StringUtil.getCustomType((char) 42861));
    }
    @Test
    public void getType2310(){
        assertEq(2,StringUtil.getCustomType((char) 42862));
    }
    @Test
    public void getType2311(){
        assertEq(1,StringUtil.getCustomType((char) 42863));
    }
    @Test
    public void getType2312(){
        assertEq(3,StringUtil.getCustomType((char) 42864));
    }
    @Test
    public void getType2313(){
        assertEq(2,StringUtil.getCustomType((char) 42873));
    }
    @Test
    public void getType2314(){
        assertEq(1,StringUtil.getCustomType((char) 42874));
    }
    @Test
    public void getType2315(){
        assertEq(2,StringUtil.getCustomType((char) 42875));
    }
    @Test
    public void getType2316(){
        assertEq(1,StringUtil.getCustomType((char) 42876));
    }
    @Test
    public void getType2317(){
        assertEq(2,StringUtil.getCustomType((char) 42877));
    }
    @Test
    public void getType2318(){
        assertEq(1,StringUtil.getCustomType((char) 42879));
    }
    @Test
    public void getType2319(){
        assertEq(2,StringUtil.getCustomType((char) 42880));
    }
    @Test
    public void getType2320(){
        assertEq(1,StringUtil.getCustomType((char) 42881));
    }
    @Test
    public void getType2321(){
        assertEq(2,StringUtil.getCustomType((char) 42882));
    }
    @Test
    public void getType2322(){
        assertEq(1,StringUtil.getCustomType((char) 42883));
    }
    @Test
    public void getType2323(){
        assertEq(2,StringUtil.getCustomType((char) 42884));
    }
    @Test
    public void getType2324(){
        assertEq(1,StringUtil.getCustomType((char) 42885));
    }
    @Test
    public void getType2325(){
        assertEq(2,StringUtil.getCustomType((char) 42886));
    }
    @Test
    public void getType2326(){
        assertEq(1,StringUtil.getCustomType((char) 42887));
    }
    @Test
    public void getType2327(){
        assertEq(7,StringUtil.getCustomType((char) 42888));
    }
    @Test
    public void getType2328(){
        assertEq(23,StringUtil.getCustomType((char) 42889));
    }
    @Test
    public void getType2329(){
        assertEq(2,StringUtil.getCustomType((char) 42891));
    }
    @Test
    public void getType2330(){
        assertEq(1,StringUtil.getCustomType((char) 42892));
    }
    @Test
    public void getType2331(){
        assertEq(2,StringUtil.getCustomType((char) 42893));
    }
    @Test
    public void getType2332(){
        assertEq(3,StringUtil.getCustomType((char) 42894));
    }
    @Test
    public void getType2333(){
        assertEq(32,StringUtil.getCustomType((char) 42895));
    }
    @Test
    public void getType2334(){
        assertEq(2,StringUtil.getCustomType((char) 42896));
    }
    @Test
    public void getType2335(){
        assertEq(1,StringUtil.getCustomType((char) 42897));
    }
    @Test
    public void getType2336(){
        assertEq(2,StringUtil.getCustomType((char) 42898));
    }
    @Test
    public void getType2337(){
        assertEq(1,StringUtil.getCustomType((char) 42899));
    }
    @Test
    public void getType2338(){
        assertEq(32,StringUtil.getCustomType((char) 42900));
    }
    @Test
    public void getType2339(){
        assertEq(2,StringUtil.getCustomType((char) 42912));
    }
    @Test
    public void getType2340(){
        assertEq(1,StringUtil.getCustomType((char) 42913));
    }
    @Test
    public void getType2341(){
        assertEq(2,StringUtil.getCustomType((char) 42914));
    }
    @Test
    public void getType2342(){
        assertEq(1,StringUtil.getCustomType((char) 42915));
    }
    @Test
    public void getType2343(){
        assertEq(2,StringUtil.getCustomType((char) 42916));
    }
    @Test
    public void getType2344(){
        assertEq(1,StringUtil.getCustomType((char) 42917));
    }
    @Test
    public void getType2345(){
        assertEq(2,StringUtil.getCustomType((char) 42918));
    }
    @Test
    public void getType2346(){
        assertEq(1,StringUtil.getCustomType((char) 42919));
    }
    @Test
    public void getType2347(){
        assertEq(2,StringUtil.getCustomType((char) 42920));
    }
    @Test
    public void getType2348(){
        assertEq(1,StringUtil.getCustomType((char) 42921));
    }
    @Test
    public void getType2349(){
        assertEq(2,StringUtil.getCustomType((char) 42922));
    }
    @Test
    public void getType2350(){
        assertEq(32,StringUtil.getCustomType((char) 42923));
    }
    @Test
    public void getType2351(){
        assertEq(3,StringUtil.getCustomType((char) 43000));
    }
    @Test
    public void getType2352(){
        assertEq(6,StringUtil.getCustomType((char) 43003));
    }
    @Test
    public void getType2353(){
        assertEq(31,StringUtil.getCustomType((char) 43010));
    }
    @Test
    public void getType2354(){
        assertEq(6,StringUtil.getCustomType((char) 43011));
    }
    @Test
    public void getType2355(){
        assertEq(31,StringUtil.getCustomType((char) 43014));
    }
    @Test
    public void getType2356(){
        assertEq(6,StringUtil.getCustomType((char) 43015));
    }
    @Test
    public void getType2357(){
        assertEq(31,StringUtil.getCustomType((char) 43019));
    }
    @Test
    public void getType2358(){
        assertEq(6,StringUtil.getCustomType((char) 43020));
    }
    @Test
    public void getType2359(){
        assertEq(31,StringUtil.getCustomType((char) 43043));
    }
    @Test
    public void getType2360(){
        assertEq(24,StringUtil.getCustomType((char) 43048));
    }
    @Test
    public void getType2361(){
        assertEq(32,StringUtil.getCustomType((char) 43052));
    }
    @Test
    public void getType2362(){
        assertEq(31,StringUtil.getCustomType((char) 43056));
    }
    @Test
    public void getType2363(){
        assertEq(24,StringUtil.getCustomType((char) 43062));
    }
    @Test
    public void getType2364(){
        assertEq(12,StringUtil.getCustomType((char) 43064));
    }
    @Test
    public void getType2365(){
        assertEq(24,StringUtil.getCustomType((char) 43065));
    }
    @Test
    public void getType2366(){
        assertEq(32,StringUtil.getCustomType((char) 43066));
    }
    @Test
    public void getType2367(){
        assertEq(6,StringUtil.getCustomType((char) 43072));
    }
    @Test
    public void getType2368(){
        assertEq(28,StringUtil.getCustomType((char) 43124));
    }
    @Test
    public void getType2369(){
        assertEq(32,StringUtil.getCustomType((char) 43128));
    }
    @Test
    public void getType2370(){
        assertEq(31,StringUtil.getCustomType((char) 43136));
    }
    @Test
    public void getType2371(){
        assertEq(6,StringUtil.getCustomType((char) 43138));
    }
    @Test
    public void getType2372(){
        assertEq(31,StringUtil.getCustomType((char) 43188));
    }
    @Test
    public void getType2373(){
        assertEq(32,StringUtil.getCustomType((char) 43205));
    }
    @Test
    public void getType2374(){
        assertEq(28,StringUtil.getCustomType((char) 43214));
    }
    @Test
    public void getType2375(){
        assertEq(10,StringUtil.getCustomType((char) 43216));
    }
    @Test
    public void getType2376(){
        assertEq(32,StringUtil.getCustomType((char) 43226));
    }
    @Test
    public void getType2377(){
        assertEq(31,StringUtil.getCustomType((char) 43232));
    }
    @Test
    public void getType2378(){
        assertEq(6,StringUtil.getCustomType((char) 43250));
    }
    @Test
    public void getType2379(){
        assertEq(28,StringUtil.getCustomType((char) 43256));
    }
    @Test
    public void getType2380(){
        assertEq(6,StringUtil.getCustomType((char) 43259));
    }
    @Test
    public void getType2381(){
        assertEq(32,StringUtil.getCustomType((char) 43260));
    }
    @Test
    public void getType2382(){
        assertEq(10,StringUtil.getCustomType((char) 43264));
    }
    @Test
    public void getType2383(){
        assertEq(6,StringUtil.getCustomType((char) 43274));
    }
    @Test
    public void getType2384(){
        assertEq(31,StringUtil.getCustomType((char) 43302));
    }
    @Test
    public void getType2385(){
        assertEq(28,StringUtil.getCustomType((char) 43310));
    }
    @Test
    public void getType2386(){
        assertEq(6,StringUtil.getCustomType((char) 43312));
    }
    @Test
    public void getType2387(){
        assertEq(31,StringUtil.getCustomType((char) 43335));
    }
    @Test
    public void getType2388(){
        assertEq(32,StringUtil.getCustomType((char) 43348));
    }
    @Test
    public void getType2389(){
        assertEq(28,StringUtil.getCustomType((char) 43359));
    }
    @Test
    public void getType2390(){
        assertEq(6,StringUtil.getCustomType((char) 43360));
    }
    @Test
    public void getType2391(){
        assertEq(32,StringUtil.getCustomType((char) 43389));
    }
    @Test
    public void getType2392(){
        assertEq(31,StringUtil.getCustomType((char) 43392));
    }
    @Test
    public void getType2393(){
        assertEq(6,StringUtil.getCustomType((char) 43396));
    }
    @Test
    public void getType2394(){
        assertEq(31,StringUtil.getCustomType((char) 43443));
    }
    @Test
    public void getType2395(){
        assertEq(28,StringUtil.getCustomType((char) 43457));
    }
    @Test
    public void getType2396(){
        assertEq(32,StringUtil.getCustomType((char) 43470));
    }
    @Test
    public void getType2397(){
        assertEq(6,StringUtil.getCustomType((char) 43471));
    }
    @Test
    public void getType2398(){
        assertEq(10,StringUtil.getCustomType((char) 43472));
    }
    @Test
    public void getType2399(){
        assertEq(32,StringUtil.getCustomType((char) 43482));
    }
    @Test
    public void getType2400(){
        assertEq(28,StringUtil.getCustomType((char) 43486));
    }
    @Test
    public void getType2401(){
        assertEq(32,StringUtil.getCustomType((char) 43488));
    }
    @Test
    public void getType2402(){
        assertEq(6,StringUtil.getCustomType((char) 43520));
    }
    @Test
    public void getType2403(){
        assertEq(31,StringUtil.getCustomType((char) 43561));
    }
    @Test
    public void getType2404(){
        assertEq(32,StringUtil.getCustomType((char) 43575));
    }
    @Test
    public void getType2405(){
        assertEq(6,StringUtil.getCustomType((char) 43584));
    }
    @Test
    public void getType2406(){
        assertEq(31,StringUtil.getCustomType((char) 43587));
    }
    @Test
    public void getType2407(){
        assertEq(6,StringUtil.getCustomType((char) 43588));
    }
    @Test
    public void getType2408(){
        assertEq(31,StringUtil.getCustomType((char) 43596));
    }
    @Test
    public void getType2409(){
        assertEq(32,StringUtil.getCustomType((char) 43598));
    }
    @Test
    public void getType2410(){
        assertEq(10,StringUtil.getCustomType((char) 43600));
    }
    @Test
    public void getType2411(){
        assertEq(32,StringUtil.getCustomType((char) 43610));
    }
    @Test
    public void getType2412(){
        assertEq(28,StringUtil.getCustomType((char) 43612));
    }
    @Test
    public void getType2413(){
        assertEq(6,StringUtil.getCustomType((char) 43616));
    }
    @Test
    public void getType2414(){
        assertEq(24,StringUtil.getCustomType((char) 43639));
    }
    @Test
    public void getType2415(){
        assertEq(6,StringUtil.getCustomType((char) 43642));
    }
    @Test
    public void getType2416(){
        assertEq(31,StringUtil.getCustomType((char) 43643));
    }
    @Test
    public void getType2417(){
        assertEq(32,StringUtil.getCustomType((char) 43644));
    }
    @Test
    public void getType2418(){
        assertEq(6,StringUtil.getCustomType((char) 43648));
    }
    @Test
    public void getType2419(){
        assertEq(31,StringUtil.getCustomType((char) 43696));
    }
    @Test
    public void getType2420(){
        assertEq(6,StringUtil.getCustomType((char) 43697));
    }
    @Test
    public void getType2421(){
        assertEq(31,StringUtil.getCustomType((char) 43698));
    }
    @Test
    public void getType2422(){
        assertEq(6,StringUtil.getCustomType((char) 43701));
    }
    @Test
    public void getType2423(){
        assertEq(31,StringUtil.getCustomType((char) 43703));
    }
    @Test
    public void getType2424(){
        assertEq(6,StringUtil.getCustomType((char) 43705));
    }
    @Test
    public void getType2425(){
        assertEq(31,StringUtil.getCustomType((char) 43710));
    }
    @Test
    public void getType2426(){
        assertEq(6,StringUtil.getCustomType((char) 43712));
    }
    @Test
    public void getType2427(){
        assertEq(31,StringUtil.getCustomType((char) 43713));
    }
    @Test
    public void getType2428(){
        assertEq(6,StringUtil.getCustomType((char) 43714));
    }
    @Test
    public void getType2429(){
        assertEq(32,StringUtil.getCustomType((char) 43715));
    }
    @Test
    public void getType2430(){
        assertEq(6,StringUtil.getCustomType((char) 43739));
    }
    @Test
    public void getType2431(){
        assertEq(28,StringUtil.getCustomType((char) 43742));
    }
    @Test
    public void getType2432(){
        assertEq(6,StringUtil.getCustomType((char) 43744));
    }
    @Test
    public void getType2433(){
        assertEq(31,StringUtil.getCustomType((char) 43755));
    }
    @Test
    public void getType2434(){
        assertEq(28,StringUtil.getCustomType((char) 43760));
    }
    @Test
    public void getType2435(){
        assertEq(6,StringUtil.getCustomType((char) 43762));
    }
    @Test
    public void getType2436(){
        assertEq(31,StringUtil.getCustomType((char) 43765));
    }
    @Test
    public void getType2437(){
        assertEq(32,StringUtil.getCustomType((char) 43767));
    }
    @Test
    public void getType2438(){
        assertEq(6,StringUtil.getCustomType((char) 43777));
    }
    @Test
    public void getType2439(){
        assertEq(32,StringUtil.getCustomType((char) 43783));
    }
    @Test
    public void getType2440(){
        assertEq(6,StringUtil.getCustomType((char) 43785));
    }
    @Test
    public void getType2441(){
        assertEq(32,StringUtil.getCustomType((char) 43791));
    }
    @Test
    public void getType2442(){
        assertEq(6,StringUtil.getCustomType((char) 43793));
    }
    @Test
    public void getType2443(){
        assertEq(32,StringUtil.getCustomType((char) 43799));
    }
    @Test
    public void getType2444(){
        assertEq(6,StringUtil.getCustomType((char) 43808));
    }
    @Test
    public void getType2445(){
        assertEq(32,StringUtil.getCustomType((char) 43815));
    }
    @Test
    public void getType2446(){
        assertEq(6,StringUtil.getCustomType((char) 43816));
    }
    @Test
    public void getType2447(){
        assertEq(32,StringUtil.getCustomType((char) 43823));
    }
    @Test
    public void getType2448(){
        assertEq(6,StringUtil.getCustomType((char) 43968));
    }
    @Test
    public void getType2449(){
        assertEq(31,StringUtil.getCustomType((char) 44003));
    }
    @Test
    public void getType2450(){
        assertEq(28,StringUtil.getCustomType((char) 44011));
    }
    @Test
    public void getType2451(){
        assertEq(31,StringUtil.getCustomType((char) 44012));
    }
    @Test
    public void getType2452(){
        assertEq(32,StringUtil.getCustomType((char) 44014));
    }
    @Test
    public void getType2453(){
        assertEq(10,StringUtil.getCustomType((char) 44016));
    }
    @Test
    public void getType2454(){
        assertEq(32,StringUtil.getCustomType((char) 44026));
    }
    @Test
    public void getType2455(){
        assertEq(6,StringUtil.getCustomType((char) 44032));
    }
    @Test
    public void getType2456(){
        assertEq(32,StringUtil.getCustomType((char) 55204));
    }
    @Test
    public void getType2457(){
        assertEq(6,StringUtil.getCustomType((char) 55216));
    }
    @Test
    public void getType2458(){
        assertEq(32,StringUtil.getCustomType((char) 55239));
    }
    @Test
    public void getType2459(){
        assertEq(6,StringUtil.getCustomType((char) 55243));
    }
    @Test
    public void getType2460(){
        assertEq(32,StringUtil.getCustomType((char) 55292));
    }
    @Test
    public void getType2461(){
        assertEq(31,StringUtil.getCustomType((char) 55296));
    }
    @Test
    public void getType2462(){
        assertEq(6,StringUtil.getCustomType((char) 63744));
    }
    @Test
    public void getType2463(){
        assertEq(32,StringUtil.getCustomType((char) 64110));
    }
    @Test
    public void getType2464(){
        assertEq(6,StringUtil.getCustomType((char) 64112));
    }
    @Test
    public void getType2465(){
        assertEq(32,StringUtil.getCustomType((char) 64218));
    }
    @Test
    public void getType2466(){
        assertEq(3,StringUtil.getCustomType((char) 64256));
    }
    @Test
    public void getType2467(){
        assertEq(32,StringUtil.getCustomType((char) 64263));
    }
    @Test
    public void getType2468(){
        assertEq(3,StringUtil.getCustomType((char) 64275));
    }
    @Test
    public void getType2469(){
        assertEq(32,StringUtil.getCustomType((char) 64280));
    }
    @Test
    public void getType2470(){
        assertEq(8,StringUtil.getCustomType((char) 64285));
    }
    @Test
    public void getType2471(){
        assertEq(31,StringUtil.getCustomType((char) 64286));
    }
    @Test
    public void getType2472(){
        assertEq(8,StringUtil.getCustomType((char) 64287));
    }
    @Test
    public void getType2473(){
        assertEq(21,StringUtil.getCustomType((char) 64297));
    }
    @Test
    public void getType2474(){
        assertEq(8,StringUtil.getCustomType((char) 64298));
    }
    @Test
    public void getType2475(){
        assertEq(32,StringUtil.getCustomType((char) 64311));
    }
    @Test
    public void getType2476(){
        assertEq(8,StringUtil.getCustomType((char) 64312));
    }
    @Test
    public void getType2477(){
        assertEq(32,StringUtil.getCustomType((char) 64317));
    }
    @Test
    public void getType2478(){
        assertEq(8,StringUtil.getCustomType((char) 64318));
    }
    @Test
    public void getType2479(){
        assertEq(32,StringUtil.getCustomType((char) 64319));
    }
    @Test
    public void getType2480(){
        assertEq(8,StringUtil.getCustomType((char) 64320));
    }
    @Test
    public void getType2481(){
        assertEq(32,StringUtil.getCustomType((char) 64322));
    }
    @Test
    public void getType2482(){
        assertEq(8,StringUtil.getCustomType((char) 64323));
    }
    @Test
    public void getType2483(){
        assertEq(32,StringUtil.getCustomType((char) 64325));
    }
    @Test
    public void getType2484(){
        assertEq(8,StringUtil.getCustomType((char) 64326));
    }
    @Test
    public void getType2485(){
        assertEq(23,StringUtil.getCustomType((char) 64434));
    }
    @Test
    public void getType2486(){
        assertEq(32,StringUtil.getCustomType((char) 64450));
    }
    @Test
    public void getType2487(){
        assertEq(8,StringUtil.getCustomType((char) 64467));
    }
    @Test
    public void getType2488(){
        assertEq(26,StringUtil.getCustomType((char) 64830));
    }
    @Test
    public void getType2489(){
        assertEq(32,StringUtil.getCustomType((char) 64832));
    }
    @Test
    public void getType2490(){
        assertEq(8,StringUtil.getCustomType((char) 64848));
    }
    @Test
    public void getType2491(){
        assertEq(32,StringUtil.getCustomType((char) 64912));
    }
    @Test
    public void getType2492(){
        assertEq(8,StringUtil.getCustomType((char) 64914));
    }
    @Test
    public void getType2493(){
        assertEq(32,StringUtil.getCustomType((char) 64968));
    }
    @Test
    public void getType2494(){
        assertEq(8,StringUtil.getCustomType((char) 65008));
    }
    @Test
    public void getType2495(){
        assertEq(12,StringUtil.getCustomType((char) 65020));
    }
    @Test
    public void getType2496(){
        assertEq(24,StringUtil.getCustomType((char) 65021));
    }
    @Test
    public void getType2497(){
        assertEq(32,StringUtil.getCustomType((char) 65022));
    }
    @Test
    public void getType2498(){
        assertEq(31,StringUtil.getCustomType((char) 65024));
    }
    @Test
    public void getType2499(){
        assertEq(28,StringUtil.getCustomType((char) 65040));
    }
    @Test
    public void getType2500(){
        assertEq(26,StringUtil.getCustomType((char) 65047));
    }
    @Test
    public void getType2501(){
        assertEq(28,StringUtil.getCustomType((char) 65049));
    }
    @Test
    public void getType2502(){
        assertEq(32,StringUtil.getCustomType((char) 65050));
    }
    @Test
    public void getType2503(){
        assertEq(31,StringUtil.getCustomType((char) 65056));
    }
    @Test
    public void getType2504(){
        assertEq(32,StringUtil.getCustomType((char) 65063));
    }
    @Test
    public void getType2505(){
        assertEq(28,StringUtil.getCustomType((char) 65072));
    }
    @Test
    public void getType2506(){
        assertEq(25,StringUtil.getCustomType((char) 65073));
    }
    @Test
    public void getType2507(){
        assertEq(26,StringUtil.getCustomType((char) 65077));
    }
    @Test
    public void getType2508(){
        assertEq(28,StringUtil.getCustomType((char) 65093));
    }
    @Test
    public void getType2509(){
        assertEq(26,StringUtil.getCustomType((char) 65095));
    }
    @Test
    public void getType2510(){
        assertEq(28,StringUtil.getCustomType((char) 65097));
    }
    @Test
    public void getType2511(){
        assertEq(25,StringUtil.getCustomType((char) 65101));
    }
    @Test
    public void getType2512(){
        assertEq(28,StringUtil.getCustomType((char) 65104));
    }
    @Test
    public void getType2513(){
        assertEq(32,StringUtil.getCustomType((char) 65107));
    }
    @Test
    public void getType2514(){
        assertEq(28,StringUtil.getCustomType((char) 65108));
    }
    @Test
    public void getType2515(){
        assertEq(25,StringUtil.getCustomType((char) 65112));
    }
    @Test
    public void getType2516(){
        assertEq(26,StringUtil.getCustomType((char) 65113));
    }
    @Test
    public void getType2517(){
        assertEq(28,StringUtil.getCustomType((char) 65119));
    }
    @Test
    public void getType2518(){
        assertEq(21,StringUtil.getCustomType((char) 65122));
    }
    @Test
    public void getType2519(){
        assertEq(25,StringUtil.getCustomType((char) 65123));
    }
    @Test
    public void getType2520(){
        assertEq(21,StringUtil.getCustomType((char) 65124));
    }
    @Test
    public void getType2521(){
        assertEq(32,StringUtil.getCustomType((char) 65127));
    }
    @Test
    public void getType2522(){
        assertEq(28,StringUtil.getCustomType((char) 65128));
    }
    @Test
    public void getType2523(){
        assertEq(12,StringUtil.getCustomType((char) 65129));
    }
    @Test
    public void getType2524(){
        assertEq(28,StringUtil.getCustomType((char) 65130));
    }
    @Test
    public void getType2525(){
        assertEq(32,StringUtil.getCustomType((char) 65132));
    }
    @Test
    public void getType2526(){
        assertEq(8,StringUtil.getCustomType((char) 65136));
    }
    @Test
    public void getType2527(){
        assertEq(32,StringUtil.getCustomType((char) 65141));
    }
    @Test
    public void getType2528(){
        assertEq(8,StringUtil.getCustomType((char) 65142));
    }
    @Test
    public void getType2529(){
        assertEq(32,StringUtil.getCustomType((char) 65277));
    }
    @Test
    public void getType2530(){
        assertEq(31,StringUtil.getCustomType((char) 65279));
    }
    @Test
    public void getType2531(){
        assertEq(32,StringUtil.getCustomType((char) 65280));
    }
    @Test
    public void getType2532(){
        assertEq(28,StringUtil.getCustomType((char) 65281));
    }
    @Test
    public void getType2533(){
        assertEq(12,StringUtil.getCustomType((char) 65284));
    }
    @Test
    public void getType2534(){
        assertEq(28,StringUtil.getCustomType((char) 65285));
    }
    @Test
    public void getType2535(){
        assertEq(26,StringUtil.getCustomType((char) 65288));
    }
    @Test
    public void getType2536(){
        assertEq(28,StringUtil.getCustomType((char) 65290));
    }
    @Test
    public void getType2537(){
        assertEq(21,StringUtil.getCustomType((char) 65291));
    }
    @Test
    public void getType2538(){
        assertEq(28,StringUtil.getCustomType((char) 65292));
    }
    @Test
    public void getType2539(){
        assertEq(25,StringUtil.getCustomType((char) 65293));
    }
    @Test
    public void getType2540(){
        assertEq(28,StringUtil.getCustomType((char) 65294));
    }
    @Test
    public void getType2541(){
        assertEq(10,StringUtil.getCustomType((char) 65296));
    }
    @Test
    public void getType2542(){
        assertEq(28,StringUtil.getCustomType((char) 65306));
    }
    @Test
    public void getType2543(){
        assertEq(21,StringUtil.getCustomType((char) 65308));
    }
    @Test
    public void getType2544(){
        assertEq(28,StringUtil.getCustomType((char) 65311));
    }
    @Test
    public void getType2545(){
        assertEq(2,StringUtil.getCustomType((char) 65313));
    }
    @Test
    public void getType2546(){
        assertEq(26,StringUtil.getCustomType((char) 65339));
    }
    @Test
    public void getType2547(){
        assertEq(28,StringUtil.getCustomType((char) 65340));
    }
    @Test
    public void getType2548(){
        assertEq(26,StringUtil.getCustomType((char) 65341));
    }
    @Test
    public void getType2549(){
        assertEq(23,StringUtil.getCustomType((char) 65342));
    }
    @Test
    public void getType2550(){
        assertEq(25,StringUtil.getCustomType((char) 65343));
    }
    @Test
    public void getType2551(){
        assertEq(23,StringUtil.getCustomType((char) 65344));
    }
    @Test
    public void getType2552(){
        assertEq(1,StringUtil.getCustomType((char) 65345));
    }
    @Test
    public void getType2553(){
        assertEq(26,StringUtil.getCustomType((char) 65371));
    }
    @Test
    public void getType2554(){
        assertEq(21,StringUtil.getCustomType((char) 65372));
    }
    @Test
    public void getType2555(){
        assertEq(26,StringUtil.getCustomType((char) 65373));
    }
    @Test
    public void getType2556(){
        assertEq(21,StringUtil.getCustomType((char) 65374));
    }
    @Test
    public void getType2557(){
        assertEq(26,StringUtil.getCustomType((char) 65375));
    }
    @Test
    public void getType2558(){
        assertEq(28,StringUtil.getCustomType((char) 65377));
    }
    @Test
    public void getType2559(){
        assertEq(26,StringUtil.getCustomType((char) 65378));
    }
    @Test
    public void getType2560(){
        assertEq(28,StringUtil.getCustomType((char) 65380));
    }
    @Test
    public void getType2561(){
        assertEq(6,StringUtil.getCustomType((char) 65382));
    }
    @Test
    public void getType2562(){
        assertEq(32,StringUtil.getCustomType((char) 65471));
    }
    @Test
    public void getType2563(){
        assertEq(6,StringUtil.getCustomType((char) 65474));
    }
    @Test
    public void getType2564(){
        assertEq(32,StringUtil.getCustomType((char) 65480));
    }
    @Test
    public void getType2565(){
        assertEq(6,StringUtil.getCustomType((char) 65482));
    }
    @Test
    public void getType2566(){
        assertEq(32,StringUtil.getCustomType((char) 65488));
    }
    @Test
    public void getType2567(){
        assertEq(6,StringUtil.getCustomType((char) 65490));
    }
    @Test
    public void getType2568(){
        assertEq(32,StringUtil.getCustomType((char) 65496));
    }
    @Test
    public void getType2569(){
        assertEq(6,StringUtil.getCustomType((char) 65498));
    }
    @Test
    public void getType2570(){
        assertEq(32,StringUtil.getCustomType((char) 65501));
    }
    @Test
    public void getType2571(){
        assertEq(12,StringUtil.getCustomType((char) 65504));
    }
    @Test
    public void getType2572(){
        assertEq(21,StringUtil.getCustomType((char) 65506));
    }
    @Test
    public void getType2573(){
        assertEq(23,StringUtil.getCustomType((char) 65507));
    }
    @Test
    public void getType2574(){
        assertEq(24,StringUtil.getCustomType((char) 65508));
    }
    @Test
    public void getType2575(){
        assertEq(12,StringUtil.getCustomType((char) 65509));
    }
    @Test
    public void getType2576(){
        assertEq(32,StringUtil.getCustomType((char) 65511));
    }
    @Test
    public void getType2577(){
        assertEq(24,StringUtil.getCustomType((char) 65512));
    }
    @Test
    public void getType2578(){
        assertEq(21,StringUtil.getCustomType((char) 65513));
    }
    @Test
    public void getType2579(){
        assertEq(24,StringUtil.getCustomType((char) 65517));
    }
    @Test
    public void getType2580(){
        assertEq(32,StringUtil.getCustomType((char) 65519));
    }
    @Test
    public void getType2581(){
        assertEq(31,StringUtil.getCustomType((char) 65529));
    }
    @Test
    public void getType2582(){
        assertEq(24,StringUtil.getCustomType((char) 65532));
    }
    @Test
    public void getType2583(){
        assertEq(32,StringUtil.getCustomType((char) 65534));
    }
    @Test
    public void getType2584(){
        assertEq(24,StringUtil.getCustomType((char) 8598));
    }
    @Test
    public void getType2585(){
        assertEq(28,StringUtil.getCustomType((char) 6818));
    }
    @Test
    public void getType2586(){
        assertEq(17,StringUtil.getCustomType((char) 38));
    }
    @Test
    public void getType2587(){
        assertEq(17,StringUtil.getCustomType((char) 43));
    }
    @Test
    public void getType2588(){
        assertEq(17,StringUtil.getCustomType((char) 61));
    }
    @Test
    public void getType2589(){
        assertEq(17,StringUtil.getCustomType((char) 62));
    }
    @Test
    public void getType2590(){
        assertEq(17,StringUtil.getCustomType((char) 63));
    }
    @Test
    public void getType2591(){
        assertEq(17,StringUtil.getCustomType((char) 64));
    }
    @Test
    public void getType2592(){
        assertEq(29,StringUtil.getCustomType((char) 10));
    }
    @Test
    public void getType2593(){
        assertEq(31,StringUtil.getCustomType((char) 837));
    }

    @Test
    public void isLetter1() {
        assertTrue(StringUtil.isLetter('a'));
    }

    @Test
    public void isLetter2() {
        assertTrue(!StringUtil.isLetter('1'));
    }
    @Test
    public void isLetter3() {
        assertTrue(StringUtil.isLetter((char)65500));
    }

    @Test
    public void isLetter4() {
        assertTrue(!StringUtil.isLetter((char)65501));
    }

    @Test
    public void isLetter5() {
        assertTrue(!StringUtil.isLetter((char)215));
    }

    @Test
    public void isLetter6() {
        assertTrue(!StringUtil.isLetter((char)191));
    }

    @Test
    public void isLetter7() {
        assertTrue(!StringUtil.isLetter((char)128));
    }

    @Test
    public void isLetter8() {
        assertTrue(!StringUtil.isLetter('_'));
    }

    @Test
    public void isLetter9() {
        assertTrue(StringUtil.isLetter('A'));
    }
    @Test
    public void isLetterOrDigit1() {
        assertTrue(StringUtil.isLetterOrDigit((char)65500));
    }

    @Test
    public void isLetterOrDigit2() {
        assertTrue(!StringUtil.isLetterOrDigit((char)65501));
    }

    @Test
    public void isLetterOrDigit3() {
        assertTrue(!StringUtil.isLetterOrDigit((char)247));
    }

    @Test
    public void isLetterOrDigit4() {
        assertTrue(StringUtil.isLetterOrDigit((char)192));
    }

    @Test
    public void isLetterOrDigit5() {
        assertTrue(!StringUtil.isLetterOrDigit((char)191));
    }

    @Test
    public void isLetterOrDigit6() {
        assertTrue(!StringUtil.isLetterOrDigit((char)128));
    }

    @Test
    public void isLetterOrDigit7() {
        assertTrue(!StringUtil.isLetterOrDigit('_'));
    }

    @Test
    public void isLetterOrDigit8() {
        assertTrue(StringUtil.isLetterOrDigit('A'));
    }

    @Test
    public void isKeyWordChar1() {
        assertTrue(!StringUtil.isKeyWordChar((char)127));
    }

    @Test
    public void isKeyWordChar2() {
        assertTrue(StringUtil.isKeyWordChar('_'));
    }

    @Test
    public void isKeyWordChar3() {
        assertTrue(!StringUtil.isKeyWordChar('!'));
    }

    @Test
    public void isKeyWordChar4() {
        assertTrue(StringUtil.isKeyWordChar('0'));
    }

    @Test
    public void isKeyWordChar5() {
        assertTrue(StringUtil.isKeyWordChar('a'));
    }

    @Test
    public void isKeyWordChar6() {
        assertTrue(StringUtil.isKeyWordChar('A'));
    }

    @Test
    public void isKeyWordChar7() {
        assertTrue(!StringUtil.isKeyWordChar(':'));
    }

    @Test
    public void isKeyWordChar8() {
        assertTrue(!StringUtil.isKeyWordChar('\\'));
    }
    @Test
    public void type() {
        int max_ = -1;
        int maxType_ = -1;
        int min_ = 100;
        int minType_ = 100;
        for (int i = 0; i < 256*256;i++) {
            int dir_ = StringUtil.getDirectionality((char) i);
            int type_ = StringUtil.getType((char) i);
            max_ = Math.max(dir_,max_);
            min_ = Math.min(dir_,min_);
            maxType_ = Math.max(type_,maxType_);
            minType_ = Math.min(type_,minType_);
        }
        assertEq(-1,min_);
        assertEq(18,max_);
        assertEq(0,minType_);
        assertEq(30,maxType_);
    }

}