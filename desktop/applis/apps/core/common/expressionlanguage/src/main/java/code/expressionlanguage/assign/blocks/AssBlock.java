package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.AssCurrentInvokingConstructor;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.*;

public abstract class AssBlock {
    private AssBracedBlock parent;

    private AssBlock nextSibling;

    private AssBlock previousSibling;
    private boolean completeNormally;
    private boolean completeNormallyGroup;
    AssBlock(boolean _completeNormally,boolean _completeNormallyGroup) {
        completeNormally = _completeNormally;
        completeNormallyGroup = _completeNormallyGroup;
    }
    protected final void setParent(AssBracedBlock _b) {
        parent = _b;
    }

    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedVariables();
    }
    protected void buildEmptyEl(ContextEl _cont, AssignedVariablesBlock _a) {
        AssignedVariables ass_ = _a.getFinalVariables().getVal(this);
        ass_.getFieldsRoot().putAllMap(AssignmentsUtil.assignAfterClassic(ass_.getFieldsRootBefore()));
        ass_.getVariablesRoot().addAllElts(AssignmentsUtil.assignAfterClassic(ass_.getVariablesRootBefore()));
        ass_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignAfterClassic(ass_.getMutableLoopRootBefore()));
    }
    public void defaultAssignmentBefore(ContextEl _an, AssignedVariablesBlock _a, AssOperationNode _root) {
        AssignedVariables vars_ =_a.getFinalVariables().getVal(this);
        vars_.getFieldsBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
        vars_.getVariablesBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
        vars_.getMutableLoopBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
    }
    public void defaultAssignmentAfter(ContextEl _an, AssignedVariablesBlock _a, AssOperationNode _root) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        vars_.getFieldsRoot().putAllMap(AssignmentsUtil.assignClassic(res_));
        if (_root instanceof AssCurrentInvokingConstructor) {
            for (EntryCust<String,SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
                SimpleAssignment a_ = e.getValue();
                a_.setAssignedAfter(true);
                a_.setUnassignedAfter(false);
            }
        }
        CustList<StringMap<Assignment>> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        vars_.getVariablesRoot().addAllElts(AssignmentsUtil.assignClassic(varsRes_));
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getLastMutableLoopOrEmpty();
        vars_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignClassic(mutableRes_));
    }
    public void setAssignmentBeforeNextSibling(ContextEl _an, AssignedVariablesBlock _a) {
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(prevAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(prevAss_.getVariablesRoot()));
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(prevAss_.getMutableLoopRoot()));
        id_.put(nextSibling_, assBl_);
    }
    public void setAssignmentBefore(ContextEl _an, AssignedVariablesBlock _a) {
        AssBracedBlock br_ = getParent();
        AssBlock prev_ = getPreviousSibling();
        if (prev_ == null) {
            br_.setAssignmentBeforeChild(_an,_a);
        } else {
            prev_.setAssignmentBeforeNextSibling(_an,_a);
        }
    }
    public abstract void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl);
    protected StringMap<AssignmentBefore> makeHypothesisFields(ContextEl _an, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        return AssignmentsUtil.getHypoAssignmentBefore(vars_.getFieldsRootBefore());
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisVars(ContextEl _an, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            variables_.add(AssignmentsUtil.getHypoAssignmentBefore(s));
        }
        return variables_;
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisMutableLoop(ContextEl _an, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
            variables_.add(AssignmentsUtil.getHypoAssignmentBefore(s));
        }
        return variables_;
    }

    public final AssBlock getPreviousSibling() {
        return previousSibling;
    }
    public abstract AssBlock getFirstChild();

    public final AssBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(AssBlock _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(AssBlock _previousSibling) {
        previousSibling = _previousSibling;
    }

    public boolean isCompleteNormally() {
        return completeNormally;
    }

    public boolean isCompleteNormallyGroup() {
        return completeNormallyGroup;
    }

    public final AssBracedBlock getParent() {
        return parent;
    }


    protected IdList<AssBracedBlock> getParentsIntervalUntil(AssBracedBlock _par) {
        IdList<AssBracedBlock> pars_ = new IdList<AssBracedBlock>();
        AssBracedBlock par_ = getParent();
        while (par_ != _par) {
            pars_.add(par_);
            par_ = par_.getParent();
        }
        return pars_;
    }
}
