package code.expressionlanguage.methods;

import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.TryBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public abstract class AbstractCatchEval extends BracedBlock implements Eval,
        IncrCurrentGroup, IncrNextGroup {

    public AbstractCatchEval(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public AbstractCatchEval(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public String getLabel() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((TryEval)p_).getLabel();
    }

    @Override
    public final void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> group_ = new CustList<Block>();
        group_.add(this);
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
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
    public final void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        if (!canBeIncrementedCurGroup()) {
            super.setAssignmentBeforeNextSibling(_an, _anEl);
            return;
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        Block nextSibling_ = getNextSibling();
        boolean finClause_ = nextSibling_ instanceof FinallyEval;
        Block try_ = this;
        CustList<AbstractCatchEval> catch_ = new CustList<AbstractCatchEval>();
        while (try_ instanceof AbstractCatchEval) {
            catch_.add((AbstractCatchEval) try_);
            try_ = try_.getPreviousSibling();
        }
        IdMap<Block, AssignedVariables> inners_;
        inners_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == try_) {
                add_ = true;
            }
            if (e.getKey().getPreviousSibling() == try_) {
                break;
            }
            if (add_) {
                inners_.put(e.getKey(), e.getValue());
            }
        }
        AssignedVariables parAss_ = inners_.firstValue();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        for (EntryCust<String, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            if (parAss_.getFieldsRootBefore().getVal(e.getKey()).isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            }
            boolean unass_ = true;
            if (!e.getValue().isUnassignedAfter() && (_anEl.canCompleteStrictNormally(try_) || finClause_)) {
                unass_ = false;
            }
            for (EntryCust<Block, AssignedVariables> f: inners_.entryList()) {
                if (!(f.getKey() instanceof AbruptBlock)) {
                    continue;
                }
                if (f.getKey() instanceof ContinueBlock) {
                    Loop lp_ = _anEl.getContinuables().getVal((ContinueBlock) f.getKey());
                    if (!_anEl.getContinuablesAncestors().getVal((ContinueBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_)) {
                        continue;
                    }
                }
                if (f.getKey() instanceof BreakBlock) {
                    BreakableBlock lp_ = _anEl.getBreakables().getVal((BreakBlock) f.getKey());
                    if (!_anEl.getBreakablesAncestors().getVal((BreakBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_) && lp_ != try_) {
                        continue;
                    }
                }
                if (f.getKey() instanceof Throwing) {
                    //throwing clause => test just after the root
                    AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(f.getKey());
                    if (!vars_.getFields().lastValue().getVal(e.getKey()).isUnassignedAfter()) {
                        unass_ = false;
                        break;
                    }
                } else if (!f.getValue().getFieldsRootBefore().getVal(e.getKey()).isUnassignedBefore()) {
                    unass_ = false;
                    break;
                }
            }
            if (finClause_) {
                for (AbstractCatchEval c: catch_) {
                    AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(c);
                    if (!vars_.getFieldsRoot().getVal(e.getKey()).isUnassignedAfter()) {
                        unass_ = false;
                        break;
                    }
                }
            }
            if (unass_) {
                ab_.setUnassignedBefore(true);
            }
            assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
        }
        for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            int index_ = assBl_.getVariablesRootBefore().size();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                if (parAss_.getVariablesRootBefore().get(index_).getVal(e.getKey()).isAssignedBefore()) {
                    ab_.setAssignedBefore(true);
                }
                boolean unass_ = true;
                if (!e.getValue().isUnassignedAfter() && (_anEl.canCompleteStrictNormally(try_) || finClause_)) {
                    unass_ = false;
                }
                for (EntryCust<Block, AssignedVariables> f: inners_.entryList()) {
                    if (!(f.getKey() instanceof AbruptBlock)) {
                        continue;
                    }
                    if (f.getKey() instanceof ContinueBlock) {
                        Loop lp_ = _anEl.getContinuables().getVal((ContinueBlock) f.getKey());
                        if (!_anEl.getContinuablesAncestors().getVal((ContinueBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_)) {
                            continue;
                        }
                    }
                    if (f.getKey() instanceof BreakBlock) {
                        BreakableBlock lp_ = _anEl.getBreakables().getVal((BreakBlock) f.getKey());
                        if (!_anEl.getBreakablesAncestors().getVal((BreakBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_) && lp_ != try_) {
                            continue;
                        }
                    }
                    if (f.getKey() instanceof Throwing) {
                        //throwing clause => test just after the root
                        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(f.getKey());
                        CustList<StringMap<Assignment>> list_ = vars_.getVariables().lastValue();
                        if (!list_.isValidIndex(index_)) {
                            continue;
                        }
                        if (!list_.get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                            unass_ = false;
                            break;
                        }
                    } else {
                        CustList<StringMap<AssignmentBefore>> list_ = f.getValue().getVariablesRootBefore();
                        if (!list_.isValidIndex(index_)) {
                            continue;
                        }
                        if (!list_.get(index_).getVal(e.getKey()).isUnassignedBefore()) {
                            unass_ = false;
                            break;
                        }
                    }
                }
                if (finClause_) {
                    for (AbstractCatchEval c: catch_) {
                        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(c);
                        if (!vars_.getVariablesRoot().get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                            unass_ = false;
                            break;
                        }
                    }
                }
                if (unass_) {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public final void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval)) {
            if (!(pBlock_ instanceof TryEval)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
                _an.getClasses().addError(un_);
            }
        }
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> prev_ = new CustList<Block>();
        prev_.add(this);
        while (!(pBlock_ instanceof TryEval)) {
            if (pBlock_ == null) {
                break;
            }
            if (pBlock_ instanceof Eval) {
                prev_.add(pBlock_);
            }
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ != null) {
            prev_.add(pBlock_);
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables ass_;
        ass_ = id_.getVal(this);
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> vars_ = ass_.getVariablesRoot();
        StringMap<SimpleAssignment> after_ = new StringMap<SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        for (EntryCust<String,SimpleAssignment> e: fields_.entryList()) {
            String key_ = e.getKey();
            boolean assAfter_ = true;
            boolean unassAfter_ = true;
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
                boolean assAfter_ = true;
                boolean unassAfter_ = true;
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
    final boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    final boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof AbstractCatchEval || next_ instanceof FinallyEval;
    }

    @Override
    final boolean canBeLastOfBlockGroup() {
        return true;
    }

    @Override
    public final String getTagName() {
        return TAG_CATCH;
    }
    @Override
    public final void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setException(NullStruct.NULL_VALUE);
        if (ts_.getLastBlock() == this) {
            ip_.removeLastBlock();
            processBlock(_cont);
        } else {
            ts_.setCurrentBlock(this);
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public final ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }

    @Override
    public void processToFinally(AbstractPageEl _ip, TryBlockStack _stack) {
        removeLocalVars(_ip);
        BracedBlock br_ = _stack.getLastBlock();
        if (br_ instanceof FinallyEval) {
            _ip.clearCurrentEls();
            _ip.getReadWrite().setBlock(br_);
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }
}
