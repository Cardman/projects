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
import code.util.Ints;

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
        for (AbsBkSrcFileLocation r: CallersRef.fetchBk(_file)) {
            CustList<SegmentReadOnlyPart> parts_ = parts(_res, r,_file);
            possible(agg_, parts_);
        }
        for (RootBlock r: _file.getAllFoundTypes()) {
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
            RootBlock ft_ = ((SettableAbstractFieldOperation) op_).getFieldType();
            if (ft_ != null) {
                WatchPointBlockPair w_ = lsBp_.getPairWatch(true,ft_.getNumberAll(),((SettableAbstractFieldOperation) op_).getFieldIdReadOnly().getFieldName());
                if (w_ != null) {
                    int b_ = beginOff(offset_,((SettableAbstractFieldOperation) op_));
                    parts_.add(new SegmentReadOnlyPart(b_,b_+((SettableAbstractFieldOperation) op_).getFieldNameLength(),SyntaxRefEnum.FIELD));
                }
            }
        }
        return parts_;
    }

    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, ResultExpressionBlock _r, FileBlock _file) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        BreakPointBlockList lsBp_ = _res.getForwards().dbg().getBreakPointsBlock();
        ResultExpression resStr_ = _r.getRes();
        ExecFileBlock fileEx_ = _res.getForwards().dbg().getFiles().getVal(_file);
        if (!resStr_.getAnalyzedString().trim().startsWith("@") && _r.getBlock() instanceof InnerTypeOrElement) {
            int f_ = ((InnerTypeOrElement) _r.getBlock()).getFieldNameOffset();
            BreakPointBlockPair pair_ = lsBp_.getPair(fileEx_, f_);
            if (pair_ != null) {
                parts_.add(new SegmentReadOnlyPart(f_,f_+((InnerTypeOrElement) _r.getBlock()).getUniqueFieldName().length(),SyntaxRefEnum.INSTRUCTION));
            }
        } else if (!resStr_.getAnalyzedString().isEmpty()){
            int offset_ = resStr_.getSumOffset();
            BreakPointBlockPair pair_ = lsBp_.getPair(fileEx_, offset_);
            if (pair_ != null) {
                parts_.add(new SegmentReadOnlyPart(offset_,offset_+resStr_.getAnalyzedString().length(),SyntaxRefEnum.INSTRUCTION));
            }
        }
        return parts_;
    }


    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, AbsBkSrcFileLocation _r, FileBlock _file) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        BreakPointBlockList lsBp_ = _res.getForwards().dbg().getBreakPointsBlock();
        AbsBk bk_ = _r.getBlock();
        BracedBlock rPar_ = BreakPointBlockList.rootOfAnnot(bk_);
        if (rPar_ instanceof RootBlock) {
            int offset_ = ((NamedCalledFunctionBlock)bk_).getPlace();
            WatchPointBlockPair w_ = lsBp_.getPairWatch(false,((RootBlock)rPar_).getNumberAll(),((NamedCalledFunctionBlock)bk_).getName());
            if (w_ != null) {
                parts_.add(new SegmentReadOnlyPart(offset_, ((NamedCalledFunctionBlock)bk_).getDefaultValueOffset(),SyntaxRefEnum.FIELD));
            }
            return parts_;
        }
        int o_ = offset(_r);
        ExecFileBlock fileEx_ = _res.getForwards().dbg().getFiles().getVal(_file);
        BreakPointBlockPair pair_ = lsBp_.getPair(fileEx_, o_);
        if (pair_ != null) {
            int b_ = bk_.getBegin();
            parts_.add(new SegmentReadOnlyPart(b_,b_+ bk_.getLengthHeader(),SyntaxRefEnum.INSTRUCTION));
        }
        for (Ints i: outExp(bk_)){
            BreakPointBlockPair pairSec_ = lsBp_.getPair(fileEx_, i.get(0));
            if (pairSec_ != null) {
                int b_ = i.get(0);
                parts_.add(new SegmentReadOnlyPart(b_,b_+i.get(1),SyntaxRefEnum.INSTRUCTION));
            }
        }
        return parts_;
    }
    private static int offset(AbsBkSrcFileLocation _r) {
        if (ResultExpressionOperationNode.withoutExp(_r.getBlock())) {
            return _r.getBlock().getOffset();
        }
        if (_r.getBlock() instanceof ReturnMethod&& ((ReturnMethod)_r.getBlock()).isEmpty()){
            return ((ReturnMethod)_r.getBlock()).getExpressionOffset();
        }
        return -1;
    }
    private static CustList<Ints> outExp(AbsBk _bl) {
        if (_bl instanceof ForIterativeLoop) {
            CustList<Ints> ls_ = new CustList<Ints>();
            ls_.add(Ints.newList(((ForIterativeLoop) _bl).getVariableNameOffset(), ((ForIterativeLoop) _bl).getVariableName().length()));
            return ls_;
        }
        if (_bl instanceof ForEachLoop) {
            return forEachIterable((ForEachLoop) _bl);
        }
        if (_bl instanceof ForEachTable) {
            return forEachTable((ForEachTable) _bl);
        }
        if (_bl instanceof WithFilterContent) {
            CustList<Ints> ls_ = new CustList<Ints>();
            if (!((WithFilterContent)_bl).getFilterContent().getDeclaringType().isEmpty()){
                ls_.add(Ints.newList(((WithFilterContent) _bl).getFilterContent().getValueOffset(), ((WithFilterContent) _bl).getFilterContent().getDeclaringType().length()));
            }
            ls_.add(Ints.newList(_bl.getOffset(),_bl.getLengthHeader()));
            return ls_;
        }
        return new CustList<Ints>();
    }

    private static CustList<Ints> forEachTable(ForEachTable _loop) {
        int s_ = _loop.getSepOffset();
        int sn_ = _loop.getSepNext();
        int vf_ = _loop.getVariableNameOffsetFirst();
        int nf_ = _loop.getVariableNameFirst().length();
        int vs_ = _loop.getVariableNameOffsetSecond();
        int ns_ = _loop.getVariableNameSecond().length();
        CustList<Ints> ls_ = new CustList<Ints>();
        ls_.add(Ints.newList(sn_,1));
        ls_.add(Ints.newList(s_,1));
        ls_.add(Ints.newList(vf_,nf_));
        ls_.add(Ints.newList(vs_,ns_));
        ls_.add(Ints.newList(_loop.getOffset(),_loop.getLengthHeader()));
        return ls_;
    }

    private static CustList<Ints> forEachIterable(ForEachLoop _loop) {
        int s_ = _loop.getSepOffset();
        int v_ = _loop.getVariableNameOffset();
        int n_ = _loop.getVariableName().length();
        CustList<Ints> ls_ = new CustList<Ints>();
        ls_.add(Ints.newList(s_, 1));
        ls_.add(Ints.newList(v_, n_));
        ls_.add(Ints.newList(_loop.getOffset(), _loop.getLengthHeader()));
        return ls_;
    }
    private static CustList<SegmentReadOnlyPart> parts(ResultContext _res, RootBlock _r, FileBlock _file) {
        CustList<SegmentReadOnlyPart> parts_ = new CustList<SegmentReadOnlyPart>();
        BreakPointBlockList lsBp_ = _res.getForwards().dbg().getBreakPointsBlock();
        int offset_ = _r.getIdRowCol();
        ExecFileBlock fileEx_ = _res.getForwards().dbg().getFiles().getVal(_file);
        BreakPointBlockPair pair_ = lsBp_.getPair(fileEx_, offset_);
        if (pair_ != null) {
            parts_.add(new SegmentReadOnlyPart(offset_,offset_+_r.getNameLength(),SyntaxRefEnum.INSTRUCTION));
        }
        return parts_;
    }
    private static int beginOff(int _sum, SettableAbstractFieldOperation _val) {
        int delta_ = _val.getOffset();
        return _sum + delta_ + _val.getIndexInEl() + _val.getDelta();
    }


}
