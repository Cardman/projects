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
            begin(_prev, _a, en_);
            AssBlock n_ = en_.getFirstChild();
            if (en_ != this) {
                tryBuildExpressionLanguage(en_, _a, _page);
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            setAssignmentAfter(en_, _a, _page);
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
                mutable(_a, _page, par_);
                setAssignmentAfter(par_, _a, _page);
                en_ = par_;
            }
        }
    }

    private void mutable(AssignedVariablesBlock _a, AnalyzedPageEl _page, AssBracedBlock _par) {
        if (_par instanceof AssForMutableIterativeLoop) {
            ((AssForMutableIterativeLoop) _par).buildIncrementPart(_a, _page);
        }
    }

    private void begin(AssBlock _prev, AssignedVariablesBlock _a, AssBlock _en) {
        if (_en == this) {
            setAssignmentBeforeCall(_prev, _a);
        } else {
            _en.setAssignmentBefore(_a);
        }
    }

    private static void setAssignmentAfter(AssBlock _en, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        if (_en instanceof AssBracedBlockInt) {
            ((AssBracedBlockInt)_en).setAssignmentAfter(_a, _page);
        }
    }

    private void tryBuildExpressionLanguage(AssBlock _en, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        if (_en instanceof AssBuildableElMethod) {
            ((AssBuildableElMethod)_en).buildExpressionLanguage(_a, _page);
        } else {
            _en.buildEmptyEl(_a);
        }
    }
    public abstract void setAssignmentBeforeCall(AssBlock _prev, AssignedVariablesBlock _anEl);
    public abstract void setAssignmentAfterCall(AssignedVariablesBlock _anEl, AnalyzedPageEl _page);
}
