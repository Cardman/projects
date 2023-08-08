package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import org.junit.Test;

public final class DbgSyntaxColoringTest extends EquallableElAdvUtil {

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
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("src/file.txt",39,res_);
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
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("src/file.txt",41,res_);
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
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",55,res_);
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
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",21,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(21,l_.get(0).getBegin());
        assertEq(47,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
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
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",56,res_);
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
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",56,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(56,l_.get(0).getBegin());
        assertEq(62,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts10() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,int> f=a->a+1;return a.call(2);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",63,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(63,l_.get(0).getBegin());
        assertEq(64,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts11() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,int> f=a->{return a+1;};return a.call(2);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",63,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(63,l_.get(0).getBegin());
        assertEq(64,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts12() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->1).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",55,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(55,l_.get(0).getBegin());
        assertEq(73,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.INSTRUCTION,l_.get(0).getKind());
    }
    @Test
    public void parts13() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return ((:int)->((:int)->1).call()).call();}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",65,res_);
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
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",73,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(73,l_.get(0).getBegin());
        assertEq(79,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts15() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,Fct<int,int>> f=a->b->a+b+1;return a.call(2).call(3);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",75,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(75,l_.get(0).getBegin());
        assertEq(76,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts16() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){Fct<int,Fct<int,int>> f=a->b->{return a+b+1;};return a.call(2).call(3);}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",75,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(75,l_.get(0).getBegin());
        assertEq(76,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }
    @Test
    public void parts17() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int exmeth(){return (switch[int](0){default;return 1;});}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("src/file.txt",67,res_);
        CustList<SegmentReadOnlyPart> l_ = list(res_);
        assertEq(1,l_.size());
        assertEq(67,l_.get(0).getBegin());
        assertEq(68,l_.get(0).getEnd());
        assertSame(SyntaxRefEnum.METHOD,l_.get(0).getKind());
    }

    private CustList<SegmentReadOnlyPart> list(ResultContext _res) {
        IdMap<FileBlock,CustList<SegmentReadOnlyPart>> s_ = DbgSyntaxColoring.partsBpMpWp(_res);
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
        return _b.getCurrentResult();
    }
}
