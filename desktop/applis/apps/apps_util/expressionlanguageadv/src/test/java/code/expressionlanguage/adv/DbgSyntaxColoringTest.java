package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class DbgSyntaxColoringTest extends EquallableElAdvUtil {

    @Test
    public void matches1() {
        assertFalse(SegmentReadOnlyTokenPart.matches(new CustList<SegmentReadOnlyTokenPart>(),0,0));
    }

    @Test
    public void matches2() {
        CustList<SegmentReadOnlyTokenPart> ls_ = new CustList<SegmentReadOnlyTokenPart>();
        ls_.add(new SegmentReadOnlyTokenPart(0,1));
        assertFalse(SegmentReadOnlyTokenPart.matches(ls_,0,0));
    }

    @Test
    public void matches3() {
        CustList<SegmentReadOnlyTokenPart> ls_ = new CustList<SegmentReadOnlyTokenPart>();
        ls_.add(new SegmentReadOnlyTokenPart(0,1));
        assertFalse(SegmentReadOnlyTokenPart.matches(ls_,1,0));
    }

    @Test
    public void matches4() {
        CustList<SegmentReadOnlyTokenPart> ls_ = new CustList<SegmentReadOnlyTokenPart>();
        ls_.add(new SegmentReadOnlyTokenPart(0,1));
        assertTrue(SegmentReadOnlyTokenPart.matches(ls_,0,1));
    }

    @Test
    public void parts1() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts2() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",39);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(39,l_.get(0).getBegin());
        assertEq(44,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
    }

    @Test
    public void parts3() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int f,s;public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",41);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(41,l_.get(0).getBegin());
        assertEq(42,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
    }

    @Test
    public void parts4() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts5() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",55);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(55,l_.get(0).getBegin());
        assertEq(56,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts6() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",21);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(21,l_.get(0).getBegin());
        assertEq(47,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(57,l_.get(1).getBegin());
        assertEq(58,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
    }
    @Test
    public void parts7() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts8() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->1).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",56);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(56,l_.get(0).getBegin());
        assertEq(62,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts9() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->{return 1;}).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",56);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(56,l_.get(0).getBegin());
        assertEq(62,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(64,l_.get(1).getBegin());
        assertEq(65,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(74,l_.get(2).getBegin());
        assertEq(75,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
    }
    @Test
    public void parts10() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,int> f=a->a+1;return f.call(2);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",63);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(63,l_.get(0).getBegin());
        assertEq(64,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts11() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,int> f=a->{return a+1;};return f.call(2);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",63);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(63,l_.get(0).getBegin());
        assertEq(64,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(66,l_.get(1).getBegin());
        assertEq(67,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(78,l_.get(2).getBegin());
        assertEq(79,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
    }
    @Test
    public void parts12() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->1).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",55);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(55,l_.get(0).getBegin());
        assertEq(56,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
        assertEq(62,l_.get(1).getBegin());
        assertEq(64,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(1).getKind());
        assertEq(65,l_.get(2).getBegin());
        assertEq(73,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(2).getKind());
    }
    @Test
    public void parts13() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->((:int)->1).call()).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",65);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(65,l_.get(0).getBegin());
        assertEq(71,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts14() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->{return ((:int)->{return 1;}).call();}).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",73);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(73,l_.get(0).getBegin());
        assertEq(79,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(81,l_.get(1).getBegin());
        assertEq(82,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(91,l_.get(2).getBegin());
        assertEq(92,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
    }
    @Test
    public void parts15() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,Fct<int,int>> f=a->b->a+b+1;return f.call(2).call(3);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",75);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(75,l_.get(0).getBegin());
        assertEq(76,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts16() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,Fct<int,int>> f=a->b->{return a+b+1;};return f.call(2).call(3);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",75);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(75,l_.get(0).getBegin());
        assertEq(76,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(78,l_.get(1).getBegin());
        assertEq(79,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(92,l_.get(2).getBegin());
        assertEq(93,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
    }
    @Test
    public void parts17() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return (switch[int](0){default;return 1;});}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",67);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(67,l_.get(0).getBegin());
        assertEq(68,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(69,l_.get(1).getBegin());
        assertEq(71,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
    }
    @Test
    public void parts18() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",13);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(13,l_.get(0).getBegin());
        assertEq(19,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts19() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {@Annot ONE;}public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",20);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(20,l_.get(0).getBegin());
        assertEq(26,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts20() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {@Annot ONE;}public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",27);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(27,l_.get(0).getBegin());
        assertEq(30,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts21() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {@Annot ONE;}public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts22() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex<T> {@Annot ONE<int>;}public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",34);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(30,l_.get(0).getBegin());
        assertEq(33,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts23() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){if(field==field){return 1;}else{return 1;}}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts24() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){if(field==field){return 1;}else{return 1;}}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",99);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(99,l_.get(0).getBegin());
        assertEq(103,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts25() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {ONE(OTHER);public static int OTHER=2;public final int i;(int i){this.i=i;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",49);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(24,l_.get(0).getBegin());
        assertEq(29,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(49,l_.get(1).getBegin());
        assertEq(54,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts26() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {THREE(OTHER);public static int OTHER=2;public final int i;(int i){this.i=i;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",51);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(26,l_.get(0).getBegin());
        assertEq(31,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(51,l_.get(1).getBegin());
        assertEq(56,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts27() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {FOUR(OTHER);public static int OTHER=2;public final int i;(int i){this.i=i;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",50);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(25,l_.get(0).getBegin());
        assertEq(30,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(50,l_.get(1).getBegin());
        assertEq(55,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }

    @Test
    public void parts28() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static void exmeth(){return;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts29() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static void exmeth(){return;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",79);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(73,l_.get(0).getBegin());
        assertEq(79,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts30() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static void exmeth(){Integer.MAX_VALUE;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts31() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;switch((Object)10){case int v;t=3;}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",80);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(80,l_.get(0).getBegin());
        assertEq(83,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts32() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;switch(10){case 10;t=3;}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",67);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(67,l_.get(0).getBegin());
        assertEq(71,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts33() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;switch((Object)10){case int v;t=3;}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts34() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;iter(int i=0;1;1){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",65);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(65,l_.get(0).getBegin());
        assertEq(66,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts35() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;for(int i:{0}){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",64);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(64,l_.get(0).getBegin());
        assertEq(65,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts36() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;for(int i:{0}){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",65);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(65,l_.get(0).getBegin());
        assertEq(66,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts37() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;IterableTable<int,int> v=null;for(int i,int j:v){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",101);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(101,l_.get(0).getBegin());
        assertEq(102,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts38() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;for(int i:{0}){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",56);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(56,l_.get(0).getBegin());
        assertEq(59,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts39() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;IterableTable<int,int> v=null;for(int i,int j:v){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",95);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(95,l_.get(0).getBegin());
        assertEq(96,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts40() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;IterableTable<int,int> v=null;for(int i,int j:v){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",94);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(94,l_.get(0).getBegin());
        assertEq(95,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts41() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;IterableTable<int,int> v=null;for(int i,int j:v){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",100);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(100,l_.get(0).getBegin());
        assertEq(101,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts42() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;IterableTable<int,int> v=null;for(int i,int j:v){}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",86);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(86,l_.get(0).getBegin());
        assertEq(89,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts43() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {int exmeth();}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",26);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(26,l_.get(0).getBegin());
        assertEq(38,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
    }
    @Test
    public void parts44() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {int exmeth();}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts45() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {int exmeth()1;}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",38);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(38,l_.get(0).getBegin());
        assertEq(39,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts46() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {int exmeth()1;}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",26);
        res_.toggleBreakPoint("src/file.txt",38);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(38,l_.get(0).getBegin());
        assertEq(39,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
        assertEq(26,l_.get(1).getBegin());
        assertEq(38,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts47() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;switch((Object)10){case int v;t=3;}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",55);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(55,l_.get(0).getBegin());
        assertEq(56,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts48() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){var f=(int a:int)->2*a;return 0;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",70);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(70,l_.get(0).getBegin());
        assertEq(71,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts49() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int t=2;switch((Object)10){case int v;t=3;}return t;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",99);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(99,l_.get(0).getBegin());
        assertEq(100,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts50() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){var f=(int a:int)->2*a;throw 0;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",78);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(78,l_.get(0).getBegin());
        assertEq(79,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts51() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {ONE(OTHER);public static int OTHER=2;public final int i;(int i){this.i=i;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",20);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(20,l_.get(0).getBegin());
        assertEq(23,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
    }
    @Test
    public void parts52() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {ONE(OTHER);public static int OTHER=2;public final int i;(int i){this.i=i;}static void m(){ONE.ordinal();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",20);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(20,l_.get(0).getBegin());
        assertEq(23,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(110,l_.get(1).getBegin());
        assertEq(113,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts53() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {public int rec;static void m(){new Ex(rec:0);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",33);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(33,l_.get(0).getBegin());
        assertEq(36,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(60,l_.get(1).getBegin());
        assertEq(63,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts54() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {public int rec;static void m(){new Ex(rec:0);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts55() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {public int rec;static void m(){$lambda(Ex,new,rec);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",33);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(33,l_.get(0).getBegin());
        assertEq(36,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(68,l_.get(1).getBegin());
        assertEq(71,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts56() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {public int rec;static void m(){$lambda(Ex,new,rec);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts57() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int rec;static void m(){$lambda(Ex,,rec);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",32);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(32,l_.get(0).getBegin());
        assertEq(35,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(64,l_.get(1).getBegin());
        assertEq(67,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts58() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int rec;static void m(){$lambda(Ex,,rec);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts59() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "@Ex(rec=0)public annotation pkg.Ex {int rec();}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void parts60() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "@Ex(rec=0)public annotation pkg.Ex {int rec();}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPointEnabled("src/file.txt",40);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(4,l_.get(0).getBegin());
        assertEq(7,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(0).getKind());
        assertEq(36,l_.get(1).getBegin());
        assertEq(45,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.FIELD,l_.get(1).getKind());
    }
    @Test
    public void parts61() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(@Annot int t){return 1;}}public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",21);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(21,l_.get(0).getBegin());
        assertEq(46,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(52,l_.get(1).getBegin());
        assertEq(59,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(69,l_.get(2).getBegin());
        assertEq(70,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
    }
    @Test
    public void parts62() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {@Annot public static int exmeth(int t){return 1;}}public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",28);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(27,l_.get(0).getBegin());
        assertEq(59,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(69,l_.get(1).getBegin());
        assertEq(70,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
    }
    @Test
    public void parts63() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return (@Annot int a:@Annot int)->{return 1;}.call(0);}}public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",55);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(5,l_.size());
        assertEq(55,l_.get(0).getBegin());
        assertEq(56,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(62,l_.get(1).getBegin());
        assertEq(69,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(75,l_.get(2).getBegin());
        assertEq(80,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
        assertEq(82,l_.get(3).getBegin());
        assertEq(83,l_.get(3).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(3).getKind());
        assertEq(92,l_.get(4).getBegin());
        assertEq(93,l_.get(4).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(4).getKind());
    }
    @Test
    public void parts64() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return (switch[int] ( 0 ) {default;return 1;});}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",68);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(2,l_.size());
        assertEq(68,l_.get(0).getBegin());
        assertEq(69,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(72,l_.get(1).getBegin());
        assertEq(75,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
    }
    @Test
    public void parts65() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)-> { return 1; } ).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",64);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(56,l_.get(0).getBegin());
        assertEq(62,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(64,l_.get(1).getBegin());
        assertEq(67,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(76,l_.get(2).getBegin());
        assertEq(78,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
    }
    @Test
    public void parts66() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)-> { int i = 0; return i; } ).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",56);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(4,l_.size());
        assertEq(56,l_.get(0).getBegin());
        assertEq(62,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(64,l_.get(1).getBegin());
        assertEq(67,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(77,l_.get(2).getBegin());
        assertEq(78,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
        assertEq(87,l_.get(3).getBegin());
        assertEq(89,l_.get(3).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(3).getKind());
    }
    @Test
    public void parts67() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)-> { int i = 0;return i; } ).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",56);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(56,l_.get(0).getBegin());
        assertEq(62,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
        assertEq(64,l_.get(1).getBegin());
        assertEq(67,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(1).getKind());
        assertEq(86,l_.get(2).getBegin());
        assertEq(88,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(2).getKind());
    }
    @Test
    public void parts68() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static void exmeth(){}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleWatchPoint("src/file.txt",21);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(21,l_.get(0).getBegin());
        assertEq(50,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts69() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return (switch [ int : @Annot : @Annot ] ( 0 ){default;return 1;});}} public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",55);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(4,l_.size());
        assertEq(55,l_.get(0).getBegin());
        assertEq(71,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
        assertEq(77,l_.get(1).getBegin());
        assertEq(80,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(1).getKind());
        assertEq(86,l_.get(2).getBegin());
        assertEq(93,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(2).getKind());
        assertEq(113,l_.get(3).getBegin());
        assertEq(114,l_.get(3).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(3).getKind());
    }
    @Test
    public void parts70() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->{return 1;}).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",55);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(55,l_.get(0).getBegin());
        assertEq(56,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
        assertEq(62,l_.get(1).getBegin());
        assertEq(64,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(1).getKind());
        assertEq(75,l_.get(2).getBegin());
        assertEq(83,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(2).getKind());
    }
    @Test
    public void parts71() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex { public static void exmeth(){ ( new @Annot Ex ( 0 ) { public int escargot; public (int i){} } ) ; }} public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",51);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(51,l_.get(0).getBegin());
        assertEq(57,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
        assertEq(63,l_.get(1).getBegin());
        assertEq(71,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(1).getKind());
        assertEq(114,l_.get(2).getBegin());
        assertEq(116,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(2).getKind());
    }
    @Test
    public void parts72() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex { public static void exmeth(){ ( new @Annot Ex ( ) { public int escargot; } ) ; }} public annotation pkg.Annot{}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.toggleBreakPoint("src/file.txt",51);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(3,l_.size());
        assertEq(51,l_.get(0).getBegin());
        assertEq(57,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
        assertEq(63,l_.get(1).getBegin());
        assertEq(67,l_.get(1).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(1).getKind());
        assertEq(95,l_.get(2).getBegin());
        assertEq(97,l_.get(2).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(2).getKind());
    }
    @Test
    public void partsTokens1() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int i=0;if(i==0)lab{break lab;}}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensLabel(res_);
        assertEq(2,l_.size());
        assertEq(64,l_.get(0).getBegin());
        assertEq(67,l_.get(0).getEnd());
        assertEq(74,l_.get(1).getBegin());
        assertEq(77,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens2() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int i=0;while(i==0)lab{break lab;}}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensLabel(res_);
        assertEq(2,l_.size());
        assertEq(67,l_.get(0).getBegin());
        assertEq(70,l_.get(0).getEnd());
        assertEq(77,l_.get(1).getBegin());
        assertEq(80,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens3() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int i=0;do lab{break lab;}while(i==0);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensLabel(res_);
        assertEq(2,l_.size());
        assertEq(59,l_.get(0).getBegin());
        assertEq(62,l_.get(0).getEnd());
        assertEq(69,l_.get(1).getBegin());
        assertEq(72,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens4() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){int i=0;do {break;}while(i==0);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensLabel(res_);
        assertEq(0,l_.size());
    }

    @Test
    public void partsTokens5() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){field++;}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensStaticField(res_);
        assertEq(2,l_.size());
        assertEq(39,l_.get(0).getBegin());
        assertEq(44,l_.get(0).getEnd());
        assertEq(72,l_.get(1).getBegin());
        assertEq(77,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens6() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int field;public int exmeth(){field++;}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(2,l_.size());
        assertEq(32,l_.get(0).getBegin());
        assertEq(37,l_.get(0).getEnd());
        assertEq(58,l_.get(1).getBegin());
        assertEq(63,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens7() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {ONE;public static int exmeth(){ONE.ordinal();}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensStaticField(res_);
        assertEq(2,l_.size());
        assertEq(20,l_.get(0).getBegin());
        assertEq(23,l_.get(0).getEnd());
        assertEq(51,l_.get(1).getBegin());
        assertEq(54,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens8() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {ONE,TWO;public static int exmeth(){ONE.ordinal();TWO.ordinal();}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensStaticField(res_);
        assertEq(4,l_.size());
        assertEq(20,l_.get(0).getBegin());
        assertEq(23,l_.get(0).getEnd());
        assertEq(24,l_.get(1).getBegin());
        assertEq(27,l_.get(1).getEnd());
        assertEq(55,l_.get(2).getBegin());
        assertEq(58,l_.get(2).getEnd());
        assertEq(69,l_.get(3).getBegin());
        assertEq(72,l_.get(3).getEnd());
    }

    @Test
    public void partsTokens9() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int field;public int exmeth(){field=1;}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(2,l_.size());
        assertEq(32,l_.get(0).getBegin());
        assertEq(37,l_.get(0).getEnd());
        assertEq(58,l_.get(1).getBegin());
        assertEq(63,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens10() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public enum pkg.Ex {ONE;int two;public int exmeth(){ONE.ordinal();two=5;");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensStaticField(res_);
        assertEq(2,l_.size());
        assertEq(20,l_.get(0).getBegin());
        assertEq(23,l_.get(0).getEnd());
        assertEq(52,l_.get(1).getBegin());
        assertEq(55,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens11() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){$lambda(Ex,,field);}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensStaticField(res_);
        assertEq(2,l_.size());
        assertEq(39,l_.get(0).getBegin());
        assertEq(44,l_.get(0).getEnd());
        assertEq(84,l_.get(1).getBegin());
        assertEq(89,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens12() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){$lambda(Ex,,that,field);}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensStaticField(res_);
        assertEq(2,l_.size());
        assertEq(39,l_.get(0).getBegin());
        assertEq(44,l_.get(0).getEnd());
        assertEq(89,l_.get(1).getBegin());
        assertEq(94,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens13() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int field;public static int exmeth(){$lambda(Ex,,field);}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(2,l_.size());
        assertEq(32,l_.get(0).getBegin());
        assertEq(37,l_.get(0).getEnd());
        assertEq(77,l_.get(1).getBegin());
        assertEq(82,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens14() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int field;public static int exmeth(){$lambda(Ex,,that,field);}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(2,l_.size());
        assertEq(32,l_.get(0).getBegin());
        assertEq(37,l_.get(0).getEnd());
        assertEq(82,l_.get(1).getBegin());
        assertEq(87,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens15() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {public int field;public static int exmeth(){$lambda(Ex,new,field);}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(2,l_.size());
        assertEq(33,l_.get(0).getBegin());
        assertEq(38,l_.get(0).getEnd());
        assertEq(81,l_.get(1).getBegin());
        assertEq(86,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens16() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {public static int exmeth(){$lambda(Ex,new,field);}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(0,l_.size());
    }

    @Test
    public void partsTokens17() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int field;public static int exmeth(){$lambda(StringSegment,new,begin,end);}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstFieldPred(res_);
        assertEq(2,l_.size());
        assertEq(91,l_.get(0).getBegin());
        assertEq(96,l_.get(0).getEnd());
        assertEq(97,l_.get(1).getBegin());
        assertEq(100,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens18() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {int field();{$lambda(Ex,field);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensAnnotField(res_);
        assertEq(2,l_.size());
        assertEq(30,l_.get(0).getBegin());
        assertEq(35,l_.get(0).getEnd());
        assertEq(50,l_.get(1).getBegin());
        assertEq(55,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens19() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {int field();{Ex e;e.field();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensAnnotField(res_);
        assertEq(2,l_.size());
        assertEq(30,l_.get(0).getBegin());
        assertEq(35,l_.get(0).getEnd());
        assertEq(46,l_.get(1).getBegin());
        assertEq(51,l_.get(1).getEnd());
    }

    @Test
    public void partsTokens20() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {{Ex e;e.field();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensAnnotField(res_);
        assertEq(0,l_.size());
    }

    @Test
    public void partsTokens21() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public annotation pkg.Ex {{$lambda(Ex,field);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensAnnotField(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void partsTokens22() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {public int rec;static void m(){new Ex(rec:0);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(2,l_.size());
        assertEq(33,l_.get(0).getBegin());
        assertEq(36,l_.get(0).getEnd());
        assertEq(60,l_.get(1).getBegin());
        assertEq(63,l_.get(1).getEnd());
    }
    @Test
    public void partsTokens23() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public @class pkg.Ex {static void m(){new Ex(rec:0);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensInstField(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void partsTokens24() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "@Ex(rec=0)public annotation pkg.Ex {int rec();}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensAnnotField(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,4,7));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,40,43));
    }
    @Test
    public void partsTokens25() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "@Ex(rec=0)public annotation pkg.Ex {}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensAnnotField(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void partsTokens26() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){method();}public static int method(){}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensFctStat(res_);
        assertEq(3,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,39,45));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,48,54));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,76,82));
    }
    @Test
    public void partsTokens27() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "operator+pkg.Ex(pkg.Ex a,pkg.Ex b){return new pkg.Ex(a.f+b.f);}public class pkg.Ex {public int f;public(int p){f=p;}public static int exmeth(){return (new Ex(1)+new Ex(2)).f;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensOper(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,8,9));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,160,161));
    }
    @Test
    public void partsTokens28() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int f;operator+pkg.Ex(pkg.Ex a,pkg.Ex b){return new pkg.Ex(a.f+b.f);}public(int p){f=p;}public static int exmeth(){return (new Ex(1)+new Ex(2)).f;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensOper(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,42,43));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,160,161));
    }
    @Test
    public void partsTokens29() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int f;operator+Ex(Ex a,Ex b){return new Ex(a.f+b.f);}public(int p){f=p;}public static int exmeth(){var a=new Ex(1);return-(a+=new Ex(2)).f;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensOper(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,42,43));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,152,153));
    }
    @Test
    public void partsTokens30() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int f;public(int p){f=p;}public String $toString(){return \"\"+f;}public static String exmeth(){String o=\"\";return o+new Ex(2);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensToStr(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,67,76));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,142,143));
    }
    @Test
    public void partsTokens31() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int f;public(int p){f=p;}public String $toString(){return \"\"+f;}public static String exmeth(){String o=\"\";return o+=new Ex(2);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensToStr(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,67,76));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,142,143));
    }
    @Test
    public void partsTokens32() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int f;public(int p){f=p;}public String toString(){var s=\"\";return s+=f;}public static String exmeth(){return\"\";}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensToStr(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void partsTokens33() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int f;public(int p){f=p;}public String $toString(){return \"\"+f;}public static String exmeth(){String o=\"\";return o+new Ex(2).$toString();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensToStr(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,67,76));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,153,162));
    }
    @Test
    public void partsTokens34() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int[] f={0};public int this(int i){return f[i];}public void this(int i){f[i]=value;}public static int exmeth(){var e=new Ex();e[0]=2;return e[0];}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensOper(res_);
        assertEq(4,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,155,156));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,157,158));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,169,170));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,171,172));
    }
    @Test
    public void partsTokens35() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public long null(){return *0;}public static long exmeth(){return *new Ex();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensRand(res_);
        assertEq(1,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,86,87));
    }
    @Test
    public void partsTokens36() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){$lambda(Ex,exmeth);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensFctStat(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,39,45));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,59,65));
    }
    @Test
    public void partsTokens37() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public operator-Ex(Ex a){return new(-a.field);}public int field;public(int i){field=i;}public static void exmeth(){$lambda(operator,Ex,-,Ex);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensOper(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,36,37));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,156,157));
    }
    @Test
    public void partsTokens38() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int exmeth(){new Ex().$lambda(Ex,exmeth);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensFct(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,32,38));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,61,67));
    }
    @Test
    public void partsTokens39() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public staticCall int exmeth(){new Ex().$lambda(Ex,exmeth);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensFctStatCall(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,43,49));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,72,78));
    }
    @Test
    public void partsTokens40() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int exmeth(){([a]);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensFctStatCall(res_);
        assertEq(0,l_.size());
    }
    @Test
    public void partsTokens41() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public int exmeth(){return 1+((:int)->2).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        CustList<SegmentReadOnlyTokenPart> l_ = listTokensNb(res_);
        assertEq(2,l_.size());
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,48,49));
        assertTrue(SegmentReadOnlyTokenPart.matches(l_,59,60));
    }
    @Test
    public void colors() {
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.ANNOT_FIELD_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.INST_FIELD_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.STATIC_FIELD_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.FCT_STAT_CALL_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.FCT_STAT_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.FCT_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.TO_STR_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.RAND_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.OPERATOR_PRED)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.ANNOT_FIELD)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.INST_FIELD)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.STATIC_FIELD)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.FCT_STAT_CALL)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.FCT_STAT)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.FCT)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.TO_STR)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.RAND)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.OPERATOR)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.LABEL)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.VARIABLES)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.VAR_SCOPE)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(0L+ToggleBreakPointEvent.color(SyntaxRefTokenEnum.NUMBERS)+Integer.MAX_VALUE));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.FCT),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.FCT_STAT),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.FCT_STAT_CALL),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.FCT_STAT_PRED),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.FCT_STAT_CALL_PRED),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.FCT_PRED),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.STATIC_FIELD_PRED),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.italic(SyntaxRefTokenEnum.STATIC_FIELD),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.underline(SyntaxRefTokenEnum.VARIABLES),false)));
        assertEq(1,NumberUtil.signum(2+ ComparatorBoolean.cmp(ToggleBreakPointEvent.underline(SyntaxRefTokenEnum.VAR_SCOPE),false)));
    }
    private CustList<SegmentReadOnlyPart> list(ResultContext _res) {
        IdMap<FileBlock,CustList<SegmentReadOnlyPart>> s_ = DbgSyntaxColoring.partsBpMpWp(_res);
        return s_.getVal(_res.getPageEl().getPreviousFilesBodies().getVal("src/file.txt"));
    }
    private CustList<SegmentReadOnlyTokenPart> listTokensLabel(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.LABEL);
    }
    private CustList<SegmentReadOnlyTokenPart> listTokensInstField(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.INST_FIELD);
    }
    private CustList<SegmentReadOnlyTokenPart> listTokensStaticField(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.STATIC_FIELD);
    }

    private CustList<SegmentReadOnlyTokenPart> listTokensInstFieldPred(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.INST_FIELD_PRED);
    }

    private CustList<SegmentReadOnlyTokenPart> listTokensAnnotField(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.ANNOT_FIELD);
    }

    private CustList<SegmentReadOnlyTokenPart> listTokensFct(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.FCT);
    }

    private CustList<SegmentReadOnlyTokenPart> listTokensFctStat(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.FCT_STAT);
    }

    private CustList<SegmentReadOnlyTokenPart> listTokensFctStatCall(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.FCT_STAT_CALL);
    }

    private CustList<SegmentReadOnlyTokenPart> listTokensToStr(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.TO_STR);
    }


    private CustList<SegmentReadOnlyTokenPart> listTokensRand(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.RAND);
    }
    private CustList<SegmentReadOnlyTokenPart> listTokensOper(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.OPERATOR);
    }

    private CustList<SegmentReadOnlyTokenPart> listTokensNb(ResultContext _res) {
        return listTokens(_res).getVal(SyntaxRefTokenEnum.NUMBERS);
    }

    private IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> listTokens(ResultContext _res) {
        IdMap<FileBlock,IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>> s_ = DbgSyntaxColoring.partsTokens(_res);
        return s_.getVal(_res.getPageEl().getPreviousFilesBodies().getVal("src/file.txt"));
    }

    private static ResultContext ctxReadOnlyOk(StringMap<String> _src) {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        return analyzed(b_, o_, r_, _src);
    }

    private static ResultContext analyzed(AbsDebuggerGui _b, ManageOptions _o, ResultContext _r, StringMap<String> _src) {
        guiAna(_r, _b, _o, _src);
        return ((OpenFramePointsEvent)_b.getOpenPoints().getActionListeners().get(0)).getCurrentResult();
    }
}
