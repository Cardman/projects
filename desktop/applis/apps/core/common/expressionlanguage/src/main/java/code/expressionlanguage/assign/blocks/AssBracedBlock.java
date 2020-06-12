package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public abstract class AssBracedBlock extends AssBlock {

    private AssBlock firstChild;

    AssBracedBlock(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    public final void appendChild(AssBlock _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        AssBlock child_ = firstChild;
        while (true) {
            AssBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }
    public void setAssignmentBeforeChild(ContextEl _an, AssignedVariablesBlock _a) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        CustList<StringMap<AssignmentBefore>> variables_;
        CustList<StringMap<AssignmentBefore>> mutable_;
        variables_ = parAss_.getVariablesRootBefore();
        mutable_ = parAss_.getMutableLoopRootBefore();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.copyBefore(parAss_.getFieldsRootBefore()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.copyBefore(variables_));
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.copyBefore(mutable_));
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }

    @Override
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock ch_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        if (ch_ == null) {
            AssignedVariables ass_ = id_.getVal(this);
            StringMap<AssignmentBefore> fields_ = ass_.getFieldsRootBefore();
            CustList<StringMap<AssignmentBefore>> variables_ = ass_.getVariablesRootBefore();
            ass_.getFieldsRoot().putAllMap(AssignmentsUtil.assignAfterClassic(fields_));
            ass_.getVariablesRoot().clear();
            ass_.getVariablesRoot().addAllElts(AssignmentsUtil.assignAfterClassic(variables_));
            CustList<StringMap<AssignmentBefore>> mutable_ = ass_.getMutableLoopRootBefore();
            ass_.getMutableLoopRoot().clear();
            ass_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignAfterClassic(mutable_));
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        AssignedVariables assTar_ = id_.getVal(this);
        AssignedVariables ass_ = id_.getVal(ch_);
        StringMap<SimpleAssignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> variables_ = ass_.getVariablesRoot();
        assTar_.getFieldsRoot().putAllMap(fields_);
        int count_ = ass_.getVariablesRootBefore().size();
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(variables_.mid(0, count_ - 1));
        CustList<StringMap<SimpleAssignment>> mutable_ = ass_.getMutableLoopRoot();
        count_ = ass_.getMutableLoopRootBefore().size();
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutable_.mid(0, count_ - 1));
    }
    @Override
    public final AssBlock getFirstChild() {
        return firstChild;
    }

}
