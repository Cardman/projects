package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringMap;

public abstract class BracedBlock extends Block implements BracedBlockInt {

    private Block firstChild;

    BracedBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _indexChild, _m);
    }

    BracedBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_indexChild, _m, _offset);
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
                child_.setupNextSiblingGroup();
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
        if (!_anEl.canCompleteNormallyGroup(ch_)) {
            _anEl.completeAbrupt(this);
        }
    }
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(parAss_.getFieldsRootBefore());
        assBl_.getVariablesRootBefore().addAllElts(parAss_.getVariablesRootBefore());
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        if (ch_ == null) {
            AssignedVariables ass_ = id_.getVal(this);
            ObjectMap<ClassField,AssignmentBefore> fields_ = ass_.getFieldsRootBefore();
            CustList<StringMap<AssignmentBefore>> variables_ = ass_.getVariablesRootBefore();
            ObjectMap<ClassField,SimpleAssignment> after_ = new ObjectMap<ClassField,SimpleAssignment>();
            CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
            for (EntryCust<ClassField,AssignmentBefore> e: fields_.entryList()) {
                AssignmentBefore ab_ = e.getValue();
                ClassField key_ = e.getKey();
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
            return;
        }
        if (!_anEl.canCompleteNormally(this)) {
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        AssignedVariables assTar_ = id_.getVal(this);
        AssignedVariables ass_ = id_.getVal(ch_);
        ObjectMap<ClassField,SimpleAssignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> variables_ = ass_.getVariablesRoot();
        assTar_.getFieldsRoot().putAllMap(fields_);
        int count_ = ass_.getVariablesRootBefore().size();
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(variables_.mid(0, count_ - 1));
    }
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
    }

    @Override
    public final Block getFirstChild() {
        return firstChild;
    }

    public final void removeLocalVars(PageEl _ip) {
        for (Block s: Classes.getDirectChildren(this)) {
            if (s instanceof InitVariable) {
                String var_ = ((InitVariable)s).getVariableName();
                _ip.removeLocalVar(var_);
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
    public void removeVarAndLoop(PageEl _ip) {
        _ip.removeLastBlock();
    }
}
