package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.AssignmentsUtil;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public abstract class BracedBlock extends Block implements BracedBlockInt {

    private Block firstChild;

    BracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public final void appendChild(Block _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        Block child_ = firstChild;
        while (true) {
            Block sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            if (!_anEl.isReachable(this)) {
                _anEl.completeAbruptGroup(this);
            }
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        boolean exist_ = false;
        for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
            if (b.getValue() == this) {
                if (_anEl.isReachable(b.getKey())) {
                    exist_ = true;
                }
            }
        }
        //parent breakable
        if (!_anEl.canCompleteNormallyGroup(ch_) && !exist_) {
            _anEl.completeAbrupt(this);
        }
    }
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
    public void checkTree(Analyzable _an, AnalyzingEl _anEl) {
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
    public void abruptGroup(AnalyzingEl _anEl) {
    }

    @Override
    public final Block getFirstChild() {
        return firstChild;
    }

    public final void removeLocalVars(AbstractPageEl _ip) {
        for (Block s: Classes.getDirectChildren(this)) {
            if (s instanceof DeclareVariable) {
                for (String v: ((DeclareVariable)s).getVariableNames()) {
                    _ip.removeLocalVar(v);
                }
            }
        }
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
        if (prev_ == null) {
            if (_anEl.isReachable(br_) && br_.accessibleCondition()) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_an, _anEl);
        }
    }

    public void removeVarAndLoop(AbstractPageEl _ip) {
        _ip.removeLastBlock();
    }
}
