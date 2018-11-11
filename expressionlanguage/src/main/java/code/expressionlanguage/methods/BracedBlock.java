package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public abstract class BracedBlock extends Block implements BracedBlockInt {

    private Block firstChild;

    BracedBlock(ContextEl _importingPage,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_m, _offset);
    }

    public final void appendChild(Block _child) {
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
                _anEl.completeAbrupt(this);
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
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        StringMap<AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        CustList<StringMap<AssignmentBefore>> mutable_;
        fields_ = new StringMap<AssignmentBefore>();
        variables_ = parAss_.getVariablesRootBefore();
        mutable_ = parAss_.getMutableLoopRootBefore();
        for (EntryCust<String,AssignmentBefore> e: parAss_.getFieldsRootBefore().entryList()) {
            fields_.put(e.getKey(), e.getValue().copy());
        }
        assBl_.getFieldsRootBefore().putAllMap(fields_);
        for (StringMap<AssignmentBefore> s: variables_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        for (StringMap<AssignmentBefore> s: mutable_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
            }
            assBl_.getMutableLoopRootBefore().add(sm_);
        }
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        if (ch_ == null) {
            AssignedVariables ass_ = id_.getVal(this);
            StringMap<AssignmentBefore> fields_ = ass_.getFieldsRootBefore();
            CustList<StringMap<AssignmentBefore>> variables_ = ass_.getVariablesRootBefore();
            StringMap<SimpleAssignment> after_ = new StringMap<SimpleAssignment>();
            CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
            for (EntryCust<String,AssignmentBefore> e: fields_.entryList()) {
                AssignmentBefore ab_ = e.getValue();
                String key_ = e.getKey();
                after_.put(key_, ab_.assignAfterClassic());
            }
            ass_.getFieldsRoot().putAllMap(after_);
            for (StringMap<AssignmentBefore> s: variables_) {
                StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore ab_ = e.getValue();
                    String key_ = e.getKey();
                    sm_.put(key_, ab_.assignAfterClassic());
                }
                afterVars_.add(sm_);
            }
            ass_.getVariablesRoot().clear();
            ass_.getVariablesRoot().addAllElts(afterVars_);
            CustList<StringMap<SimpleAssignment>> afterMutable_;
            afterMutable_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<StringMap<AssignmentBefore>> mutable_ = ass_.getMutableLoopRootBefore();
            for (StringMap<AssignmentBefore> s: mutable_) {
                StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore ab_ = e.getValue();
                    String key_ = e.getKey();
                    sm_.put(key_, ab_.assignAfterClassic());
                }
                afterMutable_.add(sm_);
            }
            ass_.getMutableLoopRoot().clear();
            ass_.getMutableLoopRoot().addAllElts(afterMutable_);
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
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
    }

    @Override
    public final Block getFirstChild() {
        return firstChild;
    }

    public final void removeLocalVars(AbstractPageEl _ip) {
        for (Block s: Classes.getDirectChildren(this)) {
            if (s instanceof InitVariable) {
                for (String v: ((InitVariable)s).getVariableNames()) {
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

    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        _ip.removeLastBlock();
    }
}
