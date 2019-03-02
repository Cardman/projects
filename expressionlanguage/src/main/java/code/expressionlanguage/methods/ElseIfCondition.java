package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.IfBlockStack;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import com.sun.org.apache.xpath.internal.Arg;

public final class ElseIfCondition extends Condition implements BlockCondition {

    public ElseIfCondition(ContextEl _importingPage,
            BracedBlock _m, OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_importingPage, _m, _condition, _offset);
    }

    @Override
    public String getRealLabel() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabel();
    }

    private boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof ElseIfCondition || next_ instanceof ElseCondition;
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        boolean abr_ = Argument.isTrueValue(arg_);
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
        assignWhenFalse(false, _an, _anEl);
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        assignWhenTrue(_an);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition)) {
            if (!(pBlock_ instanceof ElseIfCondition)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                _an.getClasses().addError(un_);
            }
        }
        if (canBeIncrementedCurGroup()) {
            return;
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
        AssignedBooleanVariables assTar_ = (AssignedBooleanVariables) id_.getVal(this);
        StringMap<SimpleAssignment> after_ ;
        CustList<StringMap<SimpleAssignment>> afterVars_;
        CustList<StringMap<SimpleAssignment>> mutableVars_;
        after_ = buildAssFieldsAfterIf(true, prev_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterIf(true, prev_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterIf(true, prev_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }
    @Override
    public boolean accessibleCondition() {
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        return !Argument.isFalseValue(arg_);
    }
    @Override
    public boolean accessibleForNext() {
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        return !Argument.isTrueValue(arg_);
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
