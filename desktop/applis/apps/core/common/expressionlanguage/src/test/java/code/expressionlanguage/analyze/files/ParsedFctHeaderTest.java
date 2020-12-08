package code.expressionlanguage.analyze.files;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public final class ParsedFctHeaderTest {
    @Test
    public void test() {
        ParsedFctHeader p_ = new ParsedFctHeader();
        p_.parse(0,",,)",0,"$intern", "that");
        assertTrue(!p_.isOk());
    }
    @Test
    public void test2() {
        ParsedFctHeader p_ = new ParsedFctHeader();
        p_.parse(0,"(,,)",0,"$intern", "that");
        assertTrue(!p_.isOk());
    }
    @Test
    public void test3() {
        ParsedFctHeader p_ = new ParsedFctHeader();
        p_.parse(0,"(elt))",0,"$intern", "that");
        assertTrue(p_.isOk());
    }
    @Test
    public void test4() {
        ParsedFctHeader p_ = new ParsedFctHeader();
        p_.parse(0,"(int a:int)->",0,"$intern", "that");
        assertTrue(!p_.isOk());
    }
    @Test
    public void test5() {
        ParsedFctHeader p_ = new ParsedFctHeader();
        p_.parse(0,"(->",0,"$intern", "that");
        assertTrue(!p_.isOk());
    }
    @Test
    public void test6() {
        ParsedFctHeader p_ = new ParsedFctHeader();
        p_.parseAnonymous(0,"(->",0,':',"that");
        assertTrue(!p_.isOk());
    }
}
