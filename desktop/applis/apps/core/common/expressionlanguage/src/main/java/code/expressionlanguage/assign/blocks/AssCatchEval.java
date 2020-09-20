package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssCatchEval extends AssBracedStack implements AssEval,AssBreakableBlock {
    private String label;
    AssCatchEval(boolean _completeNormally, boolean _completeNormallyGroup, String _a) {
        super(_completeNormally,_completeNormallyGroup);
        label = _a;
    }

    @Override
    public void setAssignmentBeforeNextSibling(AssignedVariablesBlock _anEl) {
        if (!canBeIncrementedCurGroup()) {
            super.setAssignmentBeforeNextSibling(_anEl);
            return;
        }
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssBlock nextSibling_ = getNextSibling();
        boolean finClause_ = nextSibling_ instanceof AssFinallyEval;
        AssBlock try_ = this;
        CustList<AssCatchEval> catch_ = new CustList<AssCatchEval>();
        while (try_ instanceof AssCatchEval) {
            catch_.add((AssCatchEval) try_);
            try_ = try_.getPreviousSibling();
        }
        if (!(try_ instanceof AssTryEval)) {
            super.setAssignmentBeforeNextSibling(_anEl);
            return;
        }
        AssTryEval tryBl_ = (AssTryEval)try_;
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        if (finClause_) {
            assBl_.getFieldsRootBefore().putAllMap(buildAssFieldsBefNextCatchFinally(tryBl_, _anEl, catch_));
            assBl_.getVariablesRootBefore().putAllMap(buildAssVarsBefNextCatchFinally(tryBl_, _anEl, catch_));
        } else {
            assBl_.getFieldsRootBefore().putAllMap(buildAssFieldsBefNextCatchFinally(tryBl_, _anEl, new CustList<AssCatchEval>()));
            assBl_.getVariablesRootBefore().putAllMap(buildAssVarsBefNextCatchFinally(tryBl_, _anEl, new CustList<AssCatchEval>()));
        }
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        super.setAssignmentAfter(_anEl, _page);
        AssBlock pBlock_ = getPreviousSibling();
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<AssBlock> prev_ = new CustList<AssBlock>();
        prev_.add(this);
        while (!(pBlock_ instanceof AssTryEval)) {
            if (!(pBlock_ instanceof AssEval)) {
                break;
            }
            prev_.add(pBlock_);
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ instanceof AssEval) {
            prev_.add(pBlock_);
        }
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        StringMap<SimpleAssignment> afterVars_;
        after_ =buildAssFieldsAfterTry(prev_, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterTry(prev_, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().putAllMap(afterVars_);
    }

    private boolean canBeIncrementedCurGroup() {
        AssBlock next_ = getNextSibling();
        return next_ instanceof AssCatchEval || next_ instanceof AssFinallyEval;
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
