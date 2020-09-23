package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.ElseCondition;
import code.expressionlanguage.exec.blocks.ExecElseCondition;
import code.util.CustList;

public final class ReachElseCondition extends ReachBracedBlock implements ReachBlockCondition,ReachBuildableElMethod {
    private String label;
    protected ReachElseCondition(ElseCondition _info) {
        super(_info);
        label = _info.getRealLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        ExecElseCondition exec_ = new ExecElseCondition(getOffset());
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }


    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBlock p_ = getPreviousSibling();
        if (_anEl.isReachable(p_) && p_.accessibleForNext()) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        CustList<ReachBlock> group_ = getConditionBlocks();
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
        }
    }
}
