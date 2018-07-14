package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.IfBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class ElseIfCondition extends Condition implements BlockCondition, IncrCurrentGroup, IncrNextGroup {

    public ElseIfCondition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public ElseIfCondition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _condition, _offset);
    }

    @Override
    public String getLabel() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabel();
    }
    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof ElseIfCondition || next_ instanceof ElseCondition;
    }
    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        OperationNode op_ = getElCondition().getRoot();
        boolean abr_ = true;
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            abr_ = false;
        } else if (!(arg_.getObject() instanceof Boolean)) {
            abr_ = false;
        } else if (!(Boolean)arg_.getObject()) {
            abr_ = false;
        }
        if (!abr_) {
            return;
        }
        CustList<Block> group_ = new CustList<Block>();
        group_.add(this);
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        group_.add(p_);
        boolean canCmpNormally_ = false;
        for (Block b: group_) {
            if (_anEl.canCompleteNormally(b)) {
                canCmpNormally_ = true;
                break;
            }
        }
        if (!canCmpNormally_) {
            for (Block b: group_) {
                _anEl.completeAbruptGroup(b);
            }
        }
    }
    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        if (!canBeIncrementedCurGroup()) {
            super.setAssignmentBeforeNextSibling(_an, _anEl);
            return;
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        Block nextSibling_ = getNextSibling();
        AssignedBooleanVariables assBool_ = (AssignedBooleanVariables) prevAss_;
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        for (EntryCust<String, BooleanAssignment> e: assBool_.getFieldsRootAfter().entryList()) {
            AssignmentBefore asBef_ = e.getValue().copyWhenFalse();
            assBl_.getFieldsRootBefore().put(e.getKey(), asBef_);
        }
        for (StringMap<BooleanAssignment> s: assBool_.getVariablesRootAfter()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, BooleanAssignment> e: s.entryList()) {
                AssignmentBefore asBef_ = e.getValue().copyWhenFalse();
                sm_.put(e.getKey(), asBef_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        id_.put(nextSibling_, assBl_);
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        for (EntryCust<String, BooleanAssignment> e: abv_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            AssignmentBefore ab_ = ba_.copyWhenTrue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
        }
        for (StringMap<BooleanAssignment> s: abv_.getVariablesRootAfter()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                AssignmentBefore ab_ = ba_.copyWhenTrue();
                sm_.put(e.getKey(), ab_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
            return;
        }
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition)) {
            if (!(pBlock_ instanceof ElseIfCondition)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
                _an.getClasses().addError(un_);
            }
        }
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Condition> prev_ = new CustList<Condition>();
        prev_.add(this);
        while (!(pBlock_ instanceof IfCondition)) {
            if (pBlock_ == null) {
                break;
            }
            if (pBlock_ instanceof ElseIfCondition) {
                prev_.add((Condition)pBlock_);
            }
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ != null) {
            prev_.add((Condition)pBlock_);
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedBooleanVariables assTar_ = (AssignedBooleanVariables) id_.getVal(this);
        StringMap<BooleanAssignment> fieldsCond_ = assTar_.getFieldsRootAfter();
        CustList<StringMap<BooleanAssignment>> varsCond_ = assTar_.getVariablesRootAfter();
        AssignedVariables ass_ = id_.getVal(this);
        StringMap<SimpleAssignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> vars_ = ass_.getVariablesRoot();
        StringMap<SimpleAssignment> after_ = new StringMap<SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        for (EntryCust<String,SimpleAssignment> e: fields_.entryList()) {
            String key_ = e.getKey();
            BooleanAssignment condBa_ = fieldsCond_.getVal(key_);
            boolean assAfter_ = condBa_.isAssignedAfterWhenFalse();
            boolean unassAfter_ = condBa_.isUnassignedAfterWhenFalse();
            for (Block p: prev_) {
                if (_anEl.canCompleteStrictNormally(p)) {
                    AssignedVariables assLoc_ = id_.getVal(p);
                    StringMap<SimpleAssignment> fieldsLoc_ = assLoc_.getFieldsRoot();
                    if (!fieldsLoc_.getVal(key_).isAssignedAfter()) {
                        assAfter_ = false;
                        break;
                    }
                }
                for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                    if (b.getValue() != p) {
                        continue;
                    }
                    AssignedVariables assBr_ = id_.getVal(b.getKey());
                    if (!assBr_.getFieldsRootBefore().getVal(key_).isAssignedBefore()) {
                        assAfter_ = false;
                        break;
                    }
                }
            }
            for (Block p: prev_) {
                if (_anEl.canCompleteStrictNormally(p)) {
                    AssignedVariables assLoc_ = id_.getVal(p);
                    StringMap<SimpleAssignment> fieldsLoc_ = assLoc_.getFieldsRoot();
                    if (!fieldsLoc_.getVal(key_).isUnassignedAfter()) {
                        unassAfter_ = false;
                        break;
                    }
                }
                for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                    if (b.getValue() != p) {
                        continue;
                    }
                    AssignedVariables assBr_ = id_.getVal(b.getKey());
                    if (!assBr_.getFieldsRootBefore().getVal(key_).isUnassignedBefore()) {
                        unassAfter_ = false;
                        break;
                    }
                }
            }
            after_.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
        }
        assTar_.getFieldsRoot().putAllMap(after_);
        for (StringMap<SimpleAssignment> s: vars_) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            int index_ = afterVars_.size();
            for (EntryCust<String,SimpleAssignment> e: s.entryList()) {
                String key_ = e.getKey();
                BooleanAssignment condBa_ = varsCond_.get(index_).getVal(key_);
                boolean assAfter_ = condBa_.isAssignedAfterWhenFalse();
                boolean unassAfter_ = condBa_.isUnassignedAfterWhenFalse();
                for (Block p: prev_) {
                    if (_anEl.canCompleteStrictNormally(p)) {
                        AssignedVariables assLoc_ = id_.getVal(p);
                        StringMap<SimpleAssignment> fieldsLoc_ = assLoc_.getVariablesRoot().get(index_);
                        if (!fieldsLoc_.getVal(key_).isAssignedAfter()) {
                            assAfter_ = false;
                            break;
                        }
                    }
                    for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                        if (b.getValue() != p) {
                            continue;
                        }
                        AssignedVariables assBr_ = id_.getVal(b.getKey());
                        if (!assBr_.getVariablesRootBefore().isValidIndex(index_)) {
                            continue;
                        }
                        if (!assBr_.getVariablesRootBefore().get(index_).getVal(key_).isAssignedBefore()) {
                            assAfter_ = false;
                            break;
                        }
                    }
                }
                for (Block p: prev_) {
                    if (_anEl.canCompleteStrictNormally(p)) {
                        AssignedVariables assLoc_ = id_.getVal(p);
                        StringMap<SimpleAssignment> fieldsLoc_ = assLoc_.getVariablesRoot().get(index_);
                        if (!fieldsLoc_.getVal(key_).isUnassignedAfter()) {
                            unassAfter_ = false;
                            break;
                        }
                    }
                    for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                        if (b.getValue() != p) {
                            continue;
                        }
                        AssignedVariables assBr_ = id_.getVal(b.getKey());
                        if (!assBr_.getVariablesRootBefore().isValidIndex(index_)) {
                            continue;
                        }
                        if (!assBr_.getVariablesRootBefore().get(index_).getVal(key_).isUnassignedBefore()) {
                            unassAfter_ = false;
                            break;
                        }
                    }
                }
                sm_.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
            }
            afterVars_.add(sm_);
        }
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
    }
    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_ELSEIF;
    }
    @Override
    public boolean accessibleCondition() {
        OperationNode op_ = getElCondition().getRoot();
        boolean accessible_ = false;
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            accessible_ = true;
        } else if (!(arg_.getObject() instanceof Boolean)) {
            accessible_ = true;
        } else if ((Boolean)arg_.getObject()) {
            accessible_ = true;
        }
        return accessible_;
    }
    @Override
    public boolean accessibleForNext() {
        OperationNode op_ = getElCondition().getRoot();
        boolean accessible_ = false;
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            accessible_ = true;
        } else if (!(arg_.getObject() instanceof Boolean)) {
            accessible_ = true;
        } else if (!(Boolean)arg_.getObject()) {
            accessible_ = true;
        }
        return accessible_;
    }
    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if_.setCurentVisitedBlock(this);
        if (!if_.isEntered()) {
            Boolean assert_ = evaluateCondition(_cont);
            if (assert_ == null) {
                return;
            }
            if (assert_) {
                if_.setEntered(true);
                rw_.setBlock(getFirstChild());
                return;
            }
        }
        if (if_.getLastBlock() == this) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        rw_.setBlock(getNextSibling());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if (if_.getLastBlock() == this) {
            rw_.setBlock(this);
        } else {
            rw_.setBlock(getNextSibling());
        }
    }
    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        if (_anEl.isReachable(p_) && p_.accessibleForNext()) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
}
