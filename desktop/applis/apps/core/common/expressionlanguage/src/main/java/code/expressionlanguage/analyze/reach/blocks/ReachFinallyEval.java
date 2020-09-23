package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.blocks.ExecFinallyEval;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachFinallyEval extends ReachBracedBlock implements ReachEval {
    private String label;
    protected ReachFinallyEval(FinallyEval _info) {
        super(_info);
        label = _info.getRealLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        ExecFinallyEval exec_ = new ExecFinallyEval(getOffset());
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBlock p_ = getPreviousSibling();
        while (!(p_ instanceof ReachTryEval)) {
            if (p_ == null) {
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        if (p_ == null) {
            _anEl.reach(this);
            return;
        }
        if (_anEl.isReachable(p_)) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        CustList<ReachBlock> group_ = getTryBlocks();
        if (!_anEl.canCompleteNormally(this)) {
            for (ReachBlock b: group_) {
                _anEl.completeAbruptGroup(b);
            }
            _anEl.completeAbruptGroup(this);
            return;
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_ = _anEl.getReachBreakables();
        boolean existBreak_ = false;
        for (EntryCust<ReachBreakBlock, ReachBreakableBlock> b: breakables_.entryList()) {
            if (b.getValue() == this) {
                if (_anEl.isReachable(b.getKey())) {
                    existBreak_ = true;
                }
            }
        }
        if (existBreak_) {
            //because break instructions cancel all abrupt instructions in the previous blocks
            //there exists a break instruction that break the try statement
            return;
        }
        boolean canCmpNormally_ = false;
        for (ReachBlock b: group_) {
            if (_anEl.canCompleteNormally(b)) {
                canCmpNormally_ = true;
                break;
            }
        }
        if (!canCmpNormally_) {
            for (ReachBlock b: group_) {
                _anEl.completeAbruptGroup(b);
            }
            _anEl.completeAbruptGroup(this);
        }
    }
}
