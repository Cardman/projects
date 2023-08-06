package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class DbgSyntaxColoringTest extends EquallableElAdvUtil {

    @Test
    public void parts1() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        StringMap<CustList<SegmentReadOnlyPart>> s_ = DbgSyntaxColoring.parts(res_);
        CustList<SegmentReadOnlyPart> l_ = s_.getVal("src/file.txt");
        assertEq(0,l_.size());
    }
    @Test
    public void parts2() {
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", "public class pkg.Ex {public static int field;public static int exmeth(){return 1;}}");
        ResultContext res_ = ctxReadOnlyOk(src_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleWatchPoint("src/file.txt",39,res_);
        StringMap<CustList<SegmentReadOnlyPart>> s_ = DbgSyntaxColoring.parts(res_);
        CustList<SegmentReadOnlyPart> l_ = s_.getVal("src/file.txt");
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
        StringMap<CustList<SegmentReadOnlyPart>> s_ = DbgSyntaxColoring.parts(res_);
        CustList<SegmentReadOnlyPart> l_ = s_.getVal("src/file.txt");
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
        StringMap<CustList<SegmentReadOnlyPart>> s_ = DbgSyntaxColoring.parts(res_);
        CustList<SegmentReadOnlyPart> l_ = s_.getVal("src/file.txt");
        assertEq(0,l_.size());
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
