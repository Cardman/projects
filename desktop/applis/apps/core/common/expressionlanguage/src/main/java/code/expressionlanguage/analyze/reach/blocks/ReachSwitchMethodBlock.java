package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.SwitchBlock;
import code.expressionlanguage.analyze.blocks.SwitchMethodBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachSwitchMethodBlock extends ReachMemberCallingsBlock {
    private final AnaClassArgumentMatching result;
    private final String instanceTest;
    private final SwitchMethodBlock meta;

    protected ReachSwitchMethodBlock(SwitchMethodBlock _info) {
        super(_info);
        result = _info.getResult();
        instanceTest = _info.getInstanceTest();
        meta = _info;
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        ReachBlock ch_ = getFirstChild();
        if (ch_ == null) {
            return;
        }
        boolean abrupt_ = true;
        boolean def_ = hasDefaultCase();
        if (!def_) {
            abrupt_ = false;
        } else if (!instanceTest.isEmpty()) {
            CustList<ReachBlock> group_ = new CustList<ReachBlock>();
            for (ReachBlock b: getDirectChildren(this)) {
                group_.add(b);
            }
            boolean canCmpNormally_ = false;
            for (ReachBlock b: group_) {
                if (_anEl.canCompleteNormally(b)) {
                    canCmpNormally_ = true;
                    break;
                }
            }
            if (canCmpNormally_) {
                abrupt_ = false;
            }
        } else {
            while (ch_.getNextSibling() != null) {
                ch_ = ch_.getNextSibling();
            }
            if (_anEl.canCompleteNormally(ch_)) {
                abrupt_ = false;
            }
        }
        if (abrupt_) {
            _anEl.completeAbruptGroup(this);
        }
    }

    private boolean hasDefaultCase() {
        ReachBlock ch_ = getFirstChild();
        boolean def_ = false;
        while (ch_.getNextSibling() != null) {
            if (ch_ instanceof ReachDefaultCondition) {
                def_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (ch_ instanceof ReachDefaultCondition) {
            def_ = true;
        }
        return def_;
    }


    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public String getInstanceTest() {
        return instanceTest;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return meta.getSignature(_page);
    }

    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        if (_anEl.canCompleteNormally(this)) {
            //error
            FoundErrorInterpret miss_ = new FoundErrorInterpret();
            miss_.setIndexFile(getOffset().getOffsetTrim());
            miss_.setFileName(getFile().getFileName());
            //return type len
            miss_.buildError(_page.getAnalysisMessages().getMissingAbrupt(),
                    _page.getKeyWords().getKeyWordThrow(),
                    _page.getKeyWords().getKeyWordReturn(),
                    getPseudoSignature(_page));
            _page.addLocError(miss_);
            meta.addErrorBlock(miss_.getBuiltError());
        }
    }
}
