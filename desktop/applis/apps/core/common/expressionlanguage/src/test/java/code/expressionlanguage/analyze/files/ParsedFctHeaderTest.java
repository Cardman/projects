package code.expressionlanguage.analyze.files;

import code.expressionlanguage.EquallableElUtil;
import code.util.CustList;
import code.util.core.BoolVal;
import org.junit.Test;

public final class ParsedFctHeaderTest extends EquallableElUtil {
    @Test
    public void test() {
        ParsedFctHeader p_ = parse2(",,)");
        assertTrue(!p_.isOk());
    }
    @Test
    public void test2() {
        ParsedFctHeader p_ = parse2("(,,)");
        assertTrue(!p_.isOk());
    }
    @Test
    public void test3() {
        ParsedFctHeader p_ = parse2("(elt))");
        assertTrue(p_.isOk());
    }
    @Test
    public void test4() {
        ParsedFctHeader p_ = parse2("(int a:int)->");
        assertTrue(p_.isOk());
    }
    @Test
    public void test5() {
        ParsedFctHeader p_ = parse2("(->");
        assertTrue(!p_.isOk());
    }

    @Test
    public void test6() {
        ParsedFctHeader p_ = parse("(->");
        assertEq(0,p_.getNextIndex());
    }
    @Test
    public void test7() {
        ParsedFctHeader p_ = parse("()->");
        assertEq(1,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(13,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test8() {
        ParsedFctHeader p_ = parse("(a)->");
        assertEq(2,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(14,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
    }
    @Test
    public void test9() {
        ParsedFctHeader p_ = parse("(int a)->");
        assertEq(6,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(18,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(17,p_.getOffestsParams().get(0));
    }
    @Test
    public void test10() {
        ParsedFctHeader p_ = parse("(:int)->");
        assertEq(5,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(14,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test11() {
        ParsedFctHeader p_ = parse("(:@Annot int)->");
        assertEq(12,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(21,p_.getReturnOffest());
        assertEq(1,p_.getAnnotations().size());
        assertEq("@Annot",p_.getAnnotations().get(0));
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(14,p_.getAnnotationsIndexes().get(0));
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test12() {
        ParsedFctHeader p_ = parse("(:that int)->");
        assertEq(10,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(p_.isRetRef());
        assertEq(19,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test13() {
        ParsedFctHeader p_ = parse("(:@Annot() int)->");
        assertEq(14,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(23,p_.getReturnOffest());
        assertEq(1,p_.getAnnotations().size());
        assertEq("@Annot()",p_.getAnnotations().get(0));
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(14,p_.getAnnotationsIndexes().get(0));
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test14() {
        ParsedFctHeader p_ = parse("(:@Annot@AnnotTwo int)->");
        assertEq(21,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(30,p_.getReturnOffest());
        assertEq(2,p_.getAnnotations().size());
        assertEq("@Annot",p_.getAnnotations().get(0));
        assertEq("@AnnotTwo",p_.getAnnotations().get(1));
        assertEq(2,p_.getAnnotationsIndexes().size());
        assertEq(14,p_.getAnnotationsIndexes().get(0));
        assertEq(20,p_.getAnnotationsIndexes().get(1));
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test15() {
        ParsedFctHeader p_ = parse("(:@Annot)->");
        assertEq(8,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(20,p_.getReturnOffest());
        assertEq(1,p_.getAnnotations().size());
        assertEq("@Annot",p_.getAnnotations().get(0));
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(14,p_.getAnnotationsIndexes().get(0));
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test16() {
        ParsedFctHeader p_ = parse("(:@Annot that)->");
        assertEq(13,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(p_.isRetRef());
        assertEq(25,p_.getReturnOffest());
        assertEq(1,p_.getAnnotations().size());
        assertEq("@Annot",p_.getAnnotations().get(0));
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(14,p_.getAnnotationsIndexes().get(0));
        assertEq(0,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getParametersType().size());
        assertEq(0,p_.getParametersRef().size());
        assertEq(0,p_.getParametersName().size());
        assertEq(0,p_.getOffestsParams().size());
        assertEq(0,p_.getOffestsTypes().size());
    }
    @Test
    public void test17() {
        ParsedFctHeader p_ = parse("(@Annot int a)->");
        assertEq(13,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(25,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(1,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(20,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(24,p_.getOffestsParams().get(0));
    }
    @Test
    public void test18() {
        ParsedFctHeader p_ = parse("(@Annot Pair<One,Two> a)->");
        assertEq(23,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(35,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(1,p_.getParametersType().size());
        assertEq("Pair<One,Two>",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(20,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(34,p_.getOffestsParams().get(0));
    }
    @Test
    public void test19() {
        ParsedFctHeader p_ = parse("(@Annot that int a)->");
        assertEq(18,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(30,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(1,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(25,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(29,p_.getOffestsParams().get(0));
    }
    @Test
    public void test20() {
        ParsedFctHeader p_ = parse("(@Annot that a)->");
        assertEq(14,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(26,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(25,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(25,p_.getOffestsParams().get(0));
    }
    @Test
    public void test21() {
        ParsedFctHeader p_ = parse("(@Annot a)->");
        assertEq(9,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(21,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(20,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(20,p_.getOffestsParams().get(0));
    }
    @Test
    public void test22() {
        ParsedFctHeader p_ = parse("(that a)->");
        assertEq(7,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(19,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(18,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(18,p_.getOffestsParams().get(0));
    }
    @Test
    public void test23() {
        ParsedFctHeader p_ = parse("(that int a)->");
        assertEq(11,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(23,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(18,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(22,p_.getOffestsParams().get(0));
    }
    @Test
    public void test24() {
        ParsedFctHeader p_ = parse("(@Annot int a,b)->");
        assertEq(15,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(27,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(2,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(20,p_.getOffestsTypes().get(0));
        assertEq(26,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(24,p_.getOffestsParams().get(0));
        assertEq(26,p_.getOffestsParams().get(1));
    }
    @Test
    public void test25() {
        ParsedFctHeader p_ = parse("(@Annot Pair<One,Two> a,b)->");
        assertEq(25,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(37,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(2,p_.getParametersType().size());
        assertEq("Pair<One,Two>",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(20,p_.getOffestsTypes().get(0));
        assertEq(36,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(34,p_.getOffestsParams().get(0));
        assertEq(36,p_.getOffestsParams().get(1));
    }
    @Test
    public void test26() {
        ParsedFctHeader p_ = parse("(@Annot that int a,b)->");
        assertEq(20,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(32,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(2,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(25,p_.getOffestsTypes().get(0));
        assertEq(31,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(29,p_.getOffestsParams().get(0));
        assertEq(31,p_.getOffestsParams().get(1));
    }
    @Test
    public void test27() {
        ParsedFctHeader p_ = parse("(@Annot that a,b)->");
        assertEq(16,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(28,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(25,p_.getOffestsTypes().get(0));
        assertEq(27,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(25,p_.getOffestsParams().get(0));
        assertEq(27,p_.getOffestsParams().get(1));
    }
    @Test
    public void test28() {
        ParsedFctHeader p_ = parse("(@Annot a,b)->");
        assertEq(11,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(23,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(1,p_.getAnnotationsParams().get(0).size());
        assertEq("@Annot",p_.getAnnotationsParams().get(0).get(0));
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(1,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(13,p_.getAnnotationsIndexesParams().get(0).get(0));
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(20,p_.getOffestsTypes().get(0));
        assertEq(22,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(20,p_.getOffestsParams().get(0));
        assertEq(22,p_.getOffestsParams().get(1));
    }
    @Test
    public void test29() {
        ParsedFctHeader p_ = parse("(that a,b)->");
        assertEq(9,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(21,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(18,p_.getOffestsTypes().get(0));
        assertEq(20,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(18,p_.getOffestsParams().get(0));
        assertEq(20,p_.getOffestsParams().get(1));
    }
    @Test
    public void test30() {
        ParsedFctHeader p_ = parse("(that int a,b)->");
        assertEq(13,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(25,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.TRUE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(18,p_.getOffestsTypes().get(0));
        assertEq(24,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(22,p_.getOffestsParams().get(0));
        assertEq(24,p_.getOffestsParams().get(1));
    }
    @Test
    public void test31() {
        ParsedFctHeader p_ = parse("(a,b)->");
        assertEq(4,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(16,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(15,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
        assertEq(15,p_.getOffestsParams().get(1));
    }
    @Test
    public void test32() {
        ParsedFctHeader p_ = parse("(int a,b)->");
        assertEq(8,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(20,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("int",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(19,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(17,p_.getOffestsParams().get(0));
        assertEq(19,p_.getOffestsParams().get(1));
    }
    @Test
    public void test33() {
        ParsedFctHeader p_ = parse("(a, b)->");
        assertEq(5,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(17,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(16,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
        assertEq(16,p_.getOffestsParams().get(1));
    }
    @Test
    public void test34() {
        ParsedFctHeader p_ = parse("(a ,b)->");
        assertEq(5,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(17,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(16,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
        assertEq(16,p_.getOffestsParams().get(1));
    }
    @Test
    public void test35() {
        ParsedFctHeader p_ = parse("( a,b)->");
        assertEq(5,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(17,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(14,p_.getOffestsTypes().get(0));
        assertEq(16,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(14,p_.getOffestsParams().get(0));
        assertEq(16,p_.getOffestsParams().get(1));
    }
    @Test
    public void test36() {
        ParsedFctHeader p_ = parse("(a:)->");
        assertEq(3,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(15,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
    }
    @Test
    public void test37() {
        ParsedFctHeader p_ = parse("(a:@Annot)->");
        assertEq(9,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(21,p_.getReturnOffest());
        assertEq(1,p_.getAnnotations().size());
        assertEq("@Annot",p_.getAnnotations().get(0));
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(15,p_.getAnnotationsIndexes().get(0));
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
    }
    @Test
    public void test38() {
        ParsedFctHeader p_ = parse("(a:that)->");
        assertEq(7,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(p_.isRetRef());
        assertEq(19,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
    }
    @Test
    public void test39() {
        ParsedFctHeader p_ = parse("(a:int)->");
        assertEq(6,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(15,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
    }
    @Test
    public void test40() {
        ParsedFctHeader p_ = parse("(a:@Annot int)->");
        assertEq(13,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(22,p_.getReturnOffest());
        assertEq(1,p_.getAnnotations().size());
        assertEq("@Annot",p_.getAnnotations().get(0));
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(15,p_.getAnnotationsIndexes().get(0));
        assertEq(1,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(1,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(1,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq(1,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertEq(1,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq(1,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(1,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
    }
    @Test
    public void test41() {
        ParsedFctHeader p_ = parse("(a,b:int)->");
        assertEq(8,p_.getNextIndex());
        assertEq("int",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(17,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(15,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
        assertEq(15,p_.getOffestsParams().get(1));
    }
    @Test
    public void test42() {
        ParsedFctHeader p_ = parse("( a,b)->");
        assertEq(5,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(17,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(14,p_.getOffestsTypes().get(0));
        assertEq(16,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(14,p_.getOffestsParams().get(0));
        assertEq(16,p_.getOffestsParams().get(1));
    }
    @Test
    public void test43() {
        ParsedFctHeader p_ = parse("(a, b)->");
        assertEq(5,p_.getNextIndex());
        assertEq("",p_.getReturnType());
        assertTrue(!p_.isRetRef());
        assertEq(17,p_.getReturnOffest());
        assertEq(0,p_.getAnnotations().size());
        assertEq(0,p_.getAnnotationsIndexes().size());
        assertEq(2,p_.getAnnotationsParams().size());
        assertEq(0,p_.getAnnotationsParams().get(0).size());
        assertEq(2,p_.getAnnotationsIndexesParams().size());
        assertEq(0,p_.getAnnotationsIndexesParams().get(0).size());
        assertEq(2,p_.getParametersType().size());
        assertEq("",p_.getParametersType().get(0));
        assertEq("",p_.getParametersType().get(1));
        assertEq(2,p_.getParametersRef().size());
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(0));
        assertSame(BoolVal.FALSE,p_.getParametersRef().get(1));
        assertEq(2,p_.getParametersName().size());
        assertEq("a",p_.getParametersName().get(0));
        assertEq("b",p_.getParametersName().get(1));
        assertEq(2,p_.getOffestsTypes().size());
        assertEq(13,p_.getOffestsTypes().get(0));
        assertEq(16,p_.getOffestsTypes().get(1));
        assertEq(2,p_.getOffestsParams().size());
        assertEq(13,p_.getOffestsParams().get(0));
        assertEq(16,p_.getOffestsParams().get(1));
    }
    @Test
    public void testNon1() {
        ParsedFctHeader p_ = parse("");
        assertEq(0,p_.getNextIndex());
    }
    @Test
    public void testNon2() {
        ParsedFctHeader p_ = parse("(a)b");
        assertEq(0,p_.getNextIndex());
    }
    @Test
    public void testNon3() {
        ParsedFctHeader p_ = parse("(a");
        assertEq(0,p_.getNextIndex());
    }
    @Test
    public void testNon4() {
        ParsedFctHeader p_ = parse("(a<b)");
        assertEq(0,p_.getNextIndex());
    }
    @Test
    public void testNon5() {
        ParsedFctHeader p_ = parse("(a:b)");
        assertEq(0,p_.getNextIndex());
    }
    @Test
    public void testNon6() {
        ParsedFctHeader p_ = parse("(a:b,");
        assertEq(0,p_.getNextIndex());
    }
    @Test
    public void testNon7() {
        ParsedFctHeader p_ = parse("(:b,");
        assertEq(0,p_.getNextIndex());
    }

    private ParsedFctHeader parse2(String _info) {
        ParsedFctHeader p_ = new ParsedFctHeader();
        StringComment str_ = new StringComment(_info,new CustList<CommentDelimiters>());
        p_.parse(str_.getStringParts(),_info, "that", 0);
        return p_;
    }

    private ParsedFctHeader parse(String _string) {
        ParsedFctHeader p_ = new ParsedFctHeader();
        StringComment str_ = new StringComment(_string,new CustList<CommentDelimiters>());
        p_.parseAnonymous(str_.getStringParts(),0, _string, 12, "that");
        return p_;
    }
}
