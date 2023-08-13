package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.structs.Struct;

public final class LocalThrowing {

    private LocalThrowing() {
    }
    public static void removeBlockFinally(ContextEl _conf, Struct _str, StackCall _stackCall) {
        Struct custCause_ = _str;
        while (_stackCall.hasPages()) {
            AbstractPageEl bkIp_ = _stackCall.getLastPage();
            bkIp_.clearCurrentEls();
            _stackCall.setNullCallingState();
            while (true) {
                AbstractStask bl_ = bkIp_.tryGetLastStack();
                if (bl_ == null) {
                    break;
                }
                if (setRemovedCallingFinallyToProcess(_stackCall,custCause_, bkIp_, bl_)) {
                    return;
                }
                bkIp_.removeLastBlock();
            }
            if (_stackCall.getStopper().isStopAtExcMethod()) {
                bkIp_.setThrown(new CustomFoundExc(retrieve(_stackCall, custCause_), _stackCall));
                _stackCall.nullReadWriteFail();
                _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
                return;
            }
            custCause_ = _conf.getLocks().processErrorClass(_conf, retrieve(_stackCall,custCause_), bkIp_, _stackCall);
            _stackCall.removeLastPage();
        }
        excState(_stackCall, custCause_);
    }

    private static void excState(StackCall _stackCall, Struct _custCause) {
        _stackCall.setCallingState(new CustomFoundExc(_custCause, _stackCall));
    }

    public static Struct retrieve(StackCall _stackCall, Struct _old) {
        CustomFoundExc c_ = _stackCall.trueException();
        if (c_ != null) {
            return c_.getStruct();
        }
        return _old;
    }

    private static boolean setRemovedCallingFinallyToProcess(StackCall _stackCall,Struct _custCause, AbstractPageEl _bkIp, AbstractStask _bl) {
        if (!(_bl instanceof TryBlockStack)) {
            _stackCall.setCallingState(new CustomFoundExc(_custCause));
            return false;
        }
        TryBlockStack curr_ = (TryBlockStack) _bl;
        ExecBlock currentBlock_ = curr_.getCurrentVisitedBlock();
        if (currentBlock_ instanceof ExecElseCondition) {
            _stackCall.setCallingState(new CustomFoundExc(_custCause));
            return false;
        }
        ExecBlock n_ = nextBlock(currentBlock_);
        boolean thr_ = throwIfGuardError(currentBlock_);
        if (n_ instanceof ExecBracedBlock) {
            curr_.exception(_custCause);
            goBlock(_stackCall, _bkIp, curr_, (ExecBracedBlock) n_);
            return true;
        }
        if (thr_) {
            curr_.setEnteredCatch(true);
        }
        if (curr_.isEnteredCatch()) {
            curr_.setException(_custCause);
        }
        ExecBlock next_ = currentBlock_.getNextSibling();
        if (!curr_.isEnteredCatch() && next_ instanceof ExecAbstractCatchEval) {
            curr_.exception(_custCause);
            goBlock(_stackCall, _bkIp, curr_, (ExecBracedBlock) next_);
            return true;
        }
        ExecBracedBlock l_ = curr_.getLastBlock();
        if (l_ instanceof ExecElseCondition) {
            goBlock(_stackCall, _bkIp, curr_, l_);
            return true;
        }
        _stackCall.setCallingState(new CustomFoundExc(TryBlockStack.choice(curr_.getException(),_custCause)));
        return false;
    }

    private static void goBlock(StackCall _stackCall, AbstractPageEl _bkIp, TryBlockStack _curr, ExecBracedBlock _dest) {
        _stackCall.setNullCallingState();
        _curr.setCurrentVisitedBlock(_dest);
        _bkIp.setBlock(_dest);
        _curr.setCalling(new AbruptCallingFinally(null));
    }

    public static boolean throwIfGuardError(ExecBlock _block) {
        return _block instanceof ExecAbstractCatchEval && ((ExecAbstractCatchEval)_block).isThrowIfGuardError();
    }
    private static ExecBlock nextBlock(ExecBlock _current) {
        if (_current instanceof ExecTryEval) {
            return _current.getNextSibling();
        }
        return null;
    }


}
