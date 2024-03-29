package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.AssignmentBefore;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.util.IdMap;
import code.util.StringMap;

public abstract class AssBracedBlock extends AssBlock implements AssBracedBlockInt {

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
    public void setAssignmentBeforeChild(AssignedVariablesBlock _a) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        StringMap<AssignmentBefore> variables_;
        variables_ = parAss_.getVariablesRootBefore();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.copyBefore(parAss_.getFieldsRootBefore()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.copyBefore(variables_));
        id_.put(firstChild_, assBl_);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        AssBlock ch_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        if (ch_ == null) {
            AssignedVariables ass_ = id_.getVal(this);
            StringMap<AssignmentBefore> fields_ = ass_.getFieldsRootBefore();
            StringMap<AssignmentBefore> variables_ = ass_.getVariablesRootBefore();
            ass_.getFieldsRoot().putAllMap(AssignmentsUtil.assignAfterClassic(fields_));
            ass_.getVariablesRoot().clear();
            ass_.getVariablesRoot().putAllMap(AssignmentsUtil.assignAfterClassic(variables_));
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        AssignedVariables assTar_ = id_.getVal(this);
        AssignedVariables ass_ = id_.getVal(ch_);
        StringMap<SimpleAssignment> fields_ = ass_.getFieldsRoot();
        StringMap<SimpleAssignment> vars_ = ass_.getVariablesRoot();
        assTar_.getFieldsRoot().putAllMap(fields_);
        assTar_.getVariablesRoot().putAllMap(vars_);
    }
    @Override
    public final AssBlock getFirstChild() {
        return firstChild;
    }

}
