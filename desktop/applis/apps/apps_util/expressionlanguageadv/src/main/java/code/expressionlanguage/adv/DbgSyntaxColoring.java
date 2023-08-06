package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public final class DbgSyntaxColoring {
    private DbgSyntaxColoring() {
    }
    public static StringMap<CustList<SegmentReadOnlyPart>> parts(ResultContext _res) {
        StringMap<CustList<SegmentReadOnlyPart>> segsFiles_ = new StringMap<CustList<SegmentReadOnlyPart>>();
        for (EntryCust<String, FileBlock> e: _res.getPageEl().getPreviousFilesBodies().entryList()) {
            CustList<SegmentReadOnlyPart> segs_ = new CustList<SegmentReadOnlyPart>();
            loopFile(_res,e.getValue(),segs_);
            segsFiles_.addEntry(e.getKey(), segs_);
        }
        return segsFiles_;
    }
    private static void loopFile(ResultContext _res, FileBlock _f, CustList<SegmentReadOnlyPart> _segs) {
        AbsBk r_ = _f;
        while (r_ != null) {
            fieldsBlock(_res, r_,_segs);
            AbsBk c_ = r_.getFirstChild();
            if (c_ != null) {
                r_ = c_;
                continue;
            }
            while (r_ != null) {
                AbsBk n_ = r_.getNextSibling();
                if (n_ != null) {
                    r_ = n_;
                    break;
                }
                AbsBk p_ = r_.getParent();
                if (p_ == null || p_ == _f) {
                    r_ = null;
                } else {
                    r_ = p_;
                }
            }
        }
    }

    private static void fieldsBlock(ResultContext _res, AbsBk _r, CustList<SegmentReadOnlyPart> _segs) {
        BreakPointBlockList ls_ = _res.getForwards().dbg().getBreakPointsBlock();
        if (_r instanceof InfoBlock) {
            for (String f: ((InfoBlock) _r).getElements().getFieldName()) {
                WatchPointBlockPair w_ = ls_.getPairWatch(new ClassField(((InfoBlock) _r).getDeclaringType().getFullName(), f));
                if (w_ != null) {
                    int b_ = AnaTypeUtil.getIndex((InfoBlock) _r, f);
                    _segs.add(new SegmentReadOnlyPart(b_,b_+f.length(),SyntaxRefEnum.FIELD));
                }
            }
        }
    }


}
