package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;

public abstract class AssMemberCallingsBlock extends AssBracedBlock {
    AssMemberCallingsBlock(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }
    public final void buildFctInstructions(AssBlock _prev, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssBlock firstChild_ = getFirstChild();
        _a.getAssignments().clear();
        AssBlock en_ = this;
        if (firstChild_ == null) {
            setAssignmentBeforeCall(_prev, _a);
            setAssignmentAfterCall(_a, _page);
            return;
        }
        while (true) {
            if (en_ == this) {
                setAssignmentBeforeCall(_prev, _a);
            } else {
                en_.setAssignmentBefore(_a);
            }
            AssBlock n_ = en_.getFirstChild();
            if (en_ != this) {
                tryBuildExpressionLanguage(en_, _a, _page);
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            en_.setAssignmentAfter(_a, _page);
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                AssBracedBlock par_;
                par_ = en_.getParent();
                if (par_ == this) {
                    setAssignmentAfterCall(_a, _page);
                    return;
                }
                if (par_ instanceof AssForMutableIterativeLoop) {
                    ((AssForMutableIterativeLoop)par_).buildIncrementPart(_a, _page);
                }
                par_.setAssignmentAfter(_a, _page);
                en_ = par_;
            }
        }
    }

    private boolean tryBuildExpressionLanguage(AssBlock _en, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        if (_en instanceof AssBuildableElMethod) {
            ((AssBuildableElMethod)_en).buildExpressionLanguage(_a, _page);
        } else {
            _en.buildEmptyEl(_a);
        }
        return true;
    }
    public abstract void setAssignmentBeforeCall(AssBlock _prev, AssignedVariablesBlock _anEl);
    public abstract void setAssignmentAfterCall(AssignedVariablesBlock _anEl, AnalyzedPageEl _page);
}
