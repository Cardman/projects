package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.analyze.syntax.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
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
        for (EntryCust<String, FileBlock> f: _res.getPageEl().getPreviousFilesBodies().entryList()) {
            FileBlock key_ = f.getValue();
            agg_.addEntry(key_,partsBpMpWp(_res,key_));
        }
        return agg_;
    }

    public static CustList<SegmentReadOnlyPart> partsBpMpWp(ResultContext _res, FileBlock _file) {
        CustList<SegmentReadOnlyPart> agg_ = new CustList<SegmentReadOnlyPart>();
        for (ResultExpressionBlockOperation r: CallersRef.fetch(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r);
            possible(agg_, parts_);
        }
        for (ResultExpressionBlock r: CallersRef.fetchRes(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r,_file);
            possible(agg_, parts_);
        }
        for (MemberCallingsBlock r: CallersRef.fetchFct(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = partsMethod(_res, r);
            possible(agg_, parts_);
        }
        return agg_;
    }

    private static CustList<SegmentReadOnlyPart> partsMethod(ResultContext _res, MemberCallingsBlock _r) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        BreakPointBlockList lsBp_ = _res.getForwards().dbg().getBreakPointsBlock();
        int offset_ = _r.getPlace();
        MethodPointBlockPair pair_ = lsBp_.getPair(MemberCallingsBlock.clName(_r));
        if (pair_ != null) {
            if (AbsBk.isAnonBlock(_r)) {
                parts_.add(new SegmentReadOnlyPart(offset_, ((NamedCalledFunctionBlock)_r).getNameOffset(),SyntaxRefEnum.METHOD));
            } else if (_r instanceof SwitchMethodBlock) {
                parts_.add(new SegmentReadOnlyPart(offset_, offset_ + 1,SyntaxRefEnum.METHOD));
            } else{
                parts_.add(new SegmentReadOnlyPart(offset_, _r.getBegin(),SyntaxRefEnum.METHOD));
            }
        }
        return parts_;
    }

    private static void possible(CustList<SegmentReadOnlyPart> _agg, CustList<SegmentReadOnlyPart> _parts) {
        _agg.addAllElts(_parts);
    }

    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, ResultExpressionBlockOperation _r) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        BreakPointBlockList lsBp_ = _res.getForwards().dbg().getBreakPointsBlock();
        OperationNode op_ = _r.getBlock();
        ResultExpression resStr_ = _r.getRes().getRes();
        int offset_ = resStr_.getSumOffset();
        if (op_ instanceof SettableAbstractFieldOperation) {
            WatchPointBlockPair w_ = lsBp_.getPairWatch(((SettableAbstractFieldOperation) op_).getFieldIdReadOnly());
            if (w_ != null) {
                int b_ = beginOff(offset_,((SettableAbstractFieldOperation) op_));
                parts_.add(new SegmentReadOnlyPart(b_,b_+((SettableAbstractFieldOperation) op_).getFieldNameLength(),SyntaxRefEnum.FIELD));
            }
        }
        return parts_;
    }

    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, ResultExpressionBlock _r, FileBlock _file) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        BreakPointBlockList lsBp_ = _res.getForwards().dbg().getBreakPointsBlock();
        ResultExpression resStr_ = _r.getRes();
        int offset_ = resStr_.getSumOffset();
        ExecFileBlock fileEx_ = _res.getForwards().dbg().getFiles().getVal(_file);
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
