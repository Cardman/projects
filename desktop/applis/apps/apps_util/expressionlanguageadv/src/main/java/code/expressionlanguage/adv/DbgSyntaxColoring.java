package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.analyze.syntax.CallersRef;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.syntax.ResultExpressionBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionBlockOperation;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public final class DbgSyntaxColoring {
    private DbgSyntaxColoring() {
    }
    public static IdMap<FileBlock,CustList<SegmentReadOnlyPart>> partsBpMpWp(ResultContext _res) {
        IdMap<FileBlock,CustList<SegmentReadOnlyPart>> agg_ = new IdMap<FileBlock, CustList<SegmentReadOnlyPart>>();
        for (ResultExpressionBlockOperation r: CallersRef.fetch(_res.getPageEl())) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r);
            FileBlock key_ = r.getRes().getBlock().getFile();
            CustList<SegmentReadOnlyPart> ls_ = agg_.getVal(key_);
            if (ls_ == null) {
                CustList<SegmentReadOnlyPart> e_ = new CustList<SegmentReadOnlyPart>();
                e_.addAllElts(parts_);
                agg_.addEntry(key_, e_);
            } else {
                ls_.addAllElts(parts_);
            }
        }
        for (EntryCust<String, FileBlock> f: _res.getPageEl().getPreviousFilesBodies().entryList()) {
            FileBlock key_ = f.getValue();
            CustList<SegmentReadOnlyPart> ls_ = agg_.getVal(key_);
            if (ls_ == null) {
                agg_.addEntry(key_, new CustList<SegmentReadOnlyPart>());
            }
        }
        return agg_;
    }

    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, ResultExpressionBlockOperation _r) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        BreakPointBlockList lsBp_ = _res.getForwards().dbg().getBreakPointsBlock();
        OperationNode op_ = _r.getBlock();
        ResultExpressionBlock rBlock_ = _r.getRes();
        ResultExpression resStr_ = rBlock_.getRes();
        int offset_ = resStr_.getSumOffset();
        if (op_ instanceof SettableAbstractFieldOperation) {
            WatchPointBlockPair w_ = lsBp_.getPairWatch(((SettableAbstractFieldOperation) op_).getFieldIdReadOnly());
            if (w_ != null) {
                int b_ = beginOff(offset_,((SettableAbstractFieldOperation) op_));
                parts_.add(new SegmentReadOnlyPart(b_,b_+((SettableAbstractFieldOperation) op_).getFieldNameLength(),SyntaxRefEnum.FIELD));
            }
        }
        ExecFileBlock fileEx_ = _res.getForwards().dbg().getFiles().getVal(rBlock_.getBlock().getFile());
        BreakPointBlockPair pair_ = lsBp_.getPair(fileEx_, offset_);
        if (pair_ != null) {
            parts_.add(new SegmentReadOnlyPart(offset_,offset_+resStr_.getAnalyzedString().length(),SyntaxRefEnum.INSTRUCTION));
        }
        return parts_;
    }

    private static int beginOff(int _sum, SettableAbstractFieldOperation _val) {
        int delta_ = _val.getOffset();
        return _sum + delta_ + _val.getIndexInEl() + _val.getDelta();
    }


}
