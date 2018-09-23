package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.IfBlockStack;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class ElseCondition extends BracedStack implements BlockCondition, IncrNextGroup {

    public ElseCondition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
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
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        assignWhenFalse(true, _an, _anEl);
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEmptyEl(_cont);
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
    public boolean accessibleCondition() {
        Condition cond_ = (Condition) getPreviousSibling();
        OperationNode op_ = cond_.getElCondition().getRoot();
        boolean accessible_ = false;
        Argument arg_ = op_.getArgument();
        if (op_.getArgument() == null) {
            accessible_ = true;
        } else if (!(arg_.getObject() instanceof Boolean)) {
            accessible_ = true;
        } else if (!(Boolean)arg_.getObject()) {
            accessible_ = true;
        }
        return accessible_;
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
        CustList<Block> prev_ = new CustList<Block>();
        prev_.add(this);
        while (!(pBlock_ instanceof IfCondition)) {
            if (pBlock_ == null) {
                break;
            }
            if (pBlock_ instanceof ElseIfCondition) {
                prev_.add(pBlock_);
            }
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ != null) {
            prev_.add(pBlock_);
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_ = new StringMap<SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> mutableVars_ = new CustList<StringMap<SimpleAssignment>>();
        after_ = buildAssFieldsAfterIf(false, prev_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterIf(false, prev_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterIf(false, prev_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if_.setCurentVisitedBlock(this);
        if (!if_.isEntered()) {
            if_.setEntered(true);
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        ip_.removeLastBlock();
        processBlock(_cont);
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
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
        if (_anEl.isReachable(p_) && p_.accessibleForNext()) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
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
}
