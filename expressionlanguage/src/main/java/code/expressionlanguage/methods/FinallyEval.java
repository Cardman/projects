package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.LocalThrowing;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.TryBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringMap;

public final class FinallyEval extends BracedStack implements Eval, IncrNextGroup {

    public FinallyEval(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public FinallyEval(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetsBlock _offset) {
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
    public void buildExpressionLanguage(ContextEl _cont) {
        AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
        AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
        for (EntryCust<ClassField,AssignmentBefore> e: ass_.getFieldsRootBefore().entryList()) {
            ClassField key_ = e.getKey();
            ass_.getFieldsRoot().put(key_, e.getValue().assignAfterClassic());
        }
        for (StringMap<AssignmentBefore> s: ass_.getVariablesRootBefore()) {
            StringMap<SimpleAssignment> vars_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                vars_.put(e.getKey(), e.getValue().assignAfterClassic());
            }
            ass_.getVariablesRoot().add(vars_);
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
            return;
        }
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval)) {
            if (!(pBlock_ instanceof TryEval)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
                _an.getClasses().getErrorsDet().add(un_);
            }
        }
        CustList<Block> prev_ = new CustList<Block>();
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
        AssignedVariables assTar_ = id_.getVal(this);
        AssignedVariables ass_ = id_.getVal(this);
        ObjectMap<ClassField,SimpleAssignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> vars_ = ass_.getVariablesRoot();
        ObjectMap<ClassField,SimpleAssignment> after_ = new ObjectMap<ClassField,SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        for (EntryCust<ClassField,SimpleAssignment> e: fields_.entryList()) {
            SimpleAssignment ab_ = e.getValue();
            ClassField key_ = e.getKey();
            boolean assAfter_ = true;
            boolean unassAfter_ = true;
            if (_anEl.canCompleteStrictNormally(this)) {
                unassAfter_ = ab_.isUnassignedAfter();
            }
            if (unassAfter_) {
                for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                    if (b.getValue() != this) {
                        continue;
                    }
                    AssignedVariables assBr_ = id_.getVal(b.getKey());
                    if (!assBr_.getFieldsRootBefore().getVal(key_).isUnassignedBefore()) {
                        unassAfter_ = false;
                        break;
                    }
                }
            }
            for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                if (b.getValue() != this) {
                    continue;
                }
                AssignedVariables assBr_ = id_.getVal(b.getKey());
                if (!assBr_.getFieldsRootBefore().getVal(key_).isAssignedBefore()) {
                    assAfter_ = false;
                    break;
                }
            }
            if (!ab_.isAssignedAfter() && _anEl.canCompleteStrictNormally(this)) {
                assAfter_ = false;
            }
            if (!assAfter_) {
                assAfter_ = true;
                for (Block p: prev_) {
                    if (_anEl.canCompleteStrictNormally(p)) {
                        AssignedVariables assLoc_ = _an.getAssignedVariables().getFinalVariables().getVal(p);
                        ObjectMap<ClassField,SimpleAssignment> fieldsLoc_ = assLoc_.getFieldsRoot();
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
            }
            after_.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
        }
        assTar_.getFieldsRoot().putAllMap(after_);
        for (EntryCust<ReturnMehod, Eval> e: _anEl.getReturnables().entryList()) {
            for (Block b: prev_) {
                if (b != e.getValue()) {
                    continue;
                }
                for (EntryCust<ClassField, SimpleAssignment> f: _anEl.getAssignments().getVal(e.getKey()).entryList()) {
                    SimpleAssignment asLoc_ = f.getValue();
                    if (assTar_.getFieldsRoot().getVal(f.getKey()).isAssignedAfter() || asLoc_.isAssignedAfter()) {
                        asLoc_.setAssignedAfter(true);
                        asLoc_.setUnassignedAfter(false);
                    }
                }
                break;
            }
        }
        for (StringMap<SimpleAssignment> s: vars_) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            int index_ = afterVars_.size();
            for (EntryCust<String,SimpleAssignment> e: s.entryList()) {
                SimpleAssignment ab_ = e.getValue();
                String key_ = e.getKey();
                boolean assAfter_ = true;
                boolean unassAfter_ = true;
                if (_anEl.canCompleteStrictNormally(this)) {
                    unassAfter_ = ab_.isUnassignedAfter();
                }
                if (unassAfter_) {
                    for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                        if (b.getValue() != this) {
                            continue;
                        }
                        AssignedVariables assBr_ = id_.getVal(b.getKey());
                        if (!assBr_.getVariablesRootBefore().get(index_).getVal(key_).isUnassignedBefore()) {
                            unassAfter_ = false;
                            break;
                        }
                    }
                }
                for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
                    if (b.getValue() != this) {
                        continue;
                    }
                    AssignedVariables assBr_ = id_.getVal(b.getKey());
                    if (!assBr_.getVariablesRootBefore().get(index_).getVal(key_).isAssignedBefore()) {
                        assAfter_ = false;
                        break;
                    }
                }
                if (!ab_.isAssignedAfter() && _anEl.canCompleteStrictNormally(this)) {
                    assAfter_ = false;
                }
                if (!assAfter_) {
                    assAfter_ = true;
                    for (Block p: prev_) {
                        if (_anEl.canCompleteStrictNormally(p)) {
                            AssignedVariables assLoc_ = _an.getAssignedVariables().getFinalVariables().getVal(p);
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
                            if (!assBr_.getVariablesRootBefore().get(index_).getVal(key_).isAssignedBefore()) {
                                assAfter_ = false;
                                break;
                            }
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
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return true;
    }

    @Override
    public String getTagName() {
        return TAG_FINALLY;
    }
    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setCurrentBlock(this);
        if (ts_.isVisitedFinally()) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack tryStack_ = (TryBlockStack) ip_.getLastStack();
        CallingFinally call_ = tryStack_.getCalling();
        if (call_ != null) {
            ip_.removeLastBlock();
            if (call_ instanceof LocalThrowing) {
                _context.setException(tryStack_.getException());
            } else {
                ip_.setCurrentBlock((Block) call_);
            }
            call_.removeBlockFinally(_context);
            return;
        }
        rw_.setBlock(this);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            p_ = p_.getPreviousSibling();
        }
        if (_anEl.isReachable(p_)) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        CustList<Block> group_ = new CustList<Block>();
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        group_.add(p_);
        if (!_anEl.canCompleteNormally(this)) {
            for (Block b: group_) {
                _anEl.completeAbruptGroup(b);
            }
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
            return;
        }
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        boolean existBreak_ = false;
        for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
            if (b.getValue() == this) {
                if (_anEl.isReachable(b.getKey())) {
                    existBreak_ = true;
                }
            }
        }
        if (existBreak_) {
            //because break instructions cancel all abrupt instructions in the previous blocks
            //there exists a break instruction that break the try statement
            return;
        }
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
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void processToFinally(AbstractPageEl _ip, TryBlockStack _stack) {
        removeLocalVars(_ip);
        _ip.removeLastBlock();
    }
}
