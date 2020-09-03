package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public abstract class AssMemberCallingsBlock extends AssBracedBlock {
    AssMemberCallingsBlock(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }
    public final void buildFctInstructions(ContextEl _cont, AssBlock _prev,AssignedVariablesBlock _a) {
        AssBlock firstChild_ = getFirstChild();
        _a.getAssignments().clear();
        AssBlock en_ = this;
        if (firstChild_ == null) {
            setAssignmentBeforeCall(_cont,_prev, _a);
            setAssignmentAfterCall(_cont, _a);
            return;
        }
        while (true) {
            if (en_ == this) {
                setAssignmentBeforeCall(_cont,_prev, _a);
            } else {
                en_.setAssignmentBefore(_cont, _a);
            }
            AssBlock n_ = en_.getFirstChild();
            if (en_ != this) {
                tryBuildExpressionLanguage(en_, _cont, _a);
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            en_.setAssignmentAfter(_cont, _a);
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                AssBracedBlock par_;
                par_ = en_.getParent();
                if (par_ == this) {
                    setAssignmentAfterCall(_cont, _a);
                    return;
                }
                if (par_ instanceof AssForMutableIterativeLoop) {
                    ((AssForMutableIterativeLoop)par_).buildIncrementPart(_cont,_a);
                }
                par_.setAssignmentAfter(_cont, _a);
                en_ = par_;
            }
        }
    }

    private boolean tryBuildExpressionLanguage(AssBlock en_, ContextEl cont, AssignedVariablesBlock a) {
        if (en_ instanceof AssBuildableElMethod) {
            ((AssBuildableElMethod)en_).buildExpressionLanguage(cont,a);
        } else {
            en_.buildEmptyEl(cont,a);
        }
        return true;
    }
    public abstract void setAssignmentBeforeCall(ContextEl _an, AssBlock _prev,AssignedVariablesBlock _anEl);
    public abstract void setAssignmentAfterCall(ContextEl _an, AssignedVariablesBlock _anEl);
}
