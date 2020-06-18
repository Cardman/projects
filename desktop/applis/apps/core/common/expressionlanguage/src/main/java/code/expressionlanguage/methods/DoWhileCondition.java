package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.exec.blocks.ExecDoWhileCondition;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.util.*;

public final class DoWhileCondition extends Condition {

    public DoWhileCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_condition, _offset);
    }

    @Override
    protected ExecCondition newCondition(String _condition, int _conditionOffset, CustList<ExecOperationNode> _ops) {
        return new ExecDoWhileCondition(getOffset(),_condition,_conditionOffset,_ops);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        Loop previous_ = (Loop) getPreviousSibling();
        boolean abr_ = true;
        Block last_ = getPreviousSibling().getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        boolean proc_ = Argument.isTrueValue(arg_);
        if (!proc_) {
            if (_anEl.canCompleteNormallyGroup(last_)) {
                abr_ = false;
            }
        }
        if (!proc_) {
            IdMap<ContinueBlock, Loop> breakables_;
            breakables_ = _anEl.getContinuables();
            for (EntryCust<ContinueBlock, Loop> e: breakables_.entryList()) {
                if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                    abr_ = false;
                    break;
                }
            }
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
        if (getFirstChild() != null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_an.getAnalysisMessages().getDoWhileNotEmpty(),
                    _an.getKeyWords().getKeyWordWhile(),
                    _an.getKeyWords().getKeyWordDo());
            _an.addError(un_);
        }
    }


}
