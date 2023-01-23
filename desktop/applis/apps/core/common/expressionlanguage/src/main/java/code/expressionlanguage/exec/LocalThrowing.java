package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
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
            }
            CallingState c_ = _stackCall.getCallingState();
            if (c_ instanceof CustomFoundExc) {
                custCause_ = _conf.getLocks().processErrorClass(_conf, ((CustomFoundExc)c_).getStruct(), bkIp_, _stackCall);
            } else {
                custCause_ = _conf.getLocks().processErrorClass(_conf, custCause_, bkIp_, _stackCall);
            }
            _stackCall.removeLastPage();
        }
        _stackCall.setCallingState(new CustomFoundExc(custCause_));
    }

    private static boolean setRemovedCallingFinallyToProcess(StackCall _stackCall,Struct _custCause, AbstractPageEl _bkIp, AbstractStask _bl) {
        if (_bl instanceof TryBlockStack) {
            ExecBracedBlock v_ = toVisit((TryBlockStack) _bl);
            if (v_ != null) {
                if (((TryBlockStack)_bl).getException() == null) {
                    ((TryBlockStack)_bl).setException(_custCause);
                }
                _stackCall.setNullCallingState();
                ((TryBlockStack)_bl).setCurrentVisitedBlock(v_);
                _bkIp.setBlock(v_);
                ((TryBlockStack) _bl).setCalling(new AbruptCallingFinally(null));
                return true;
            }
            ExecBracedBlock cur_ = _bl.getCurrentVisitedBlock();
            if (!(cur_ instanceof ExecFinallyEval)&&!((TryBlockStack) _bl).isEnteredCatch()) {
                _stackCall.setCallingState(new CustomFoundExc(((TryBlockStack)_bl).getException()));
                _bkIp.removeLastBlock();
                return false;
            }
        }
        _stackCall.setCallingState(new CustomFoundExc(_custCause));
        _bkIp.removeLastBlock();
        return false;
    }
    private static ExecBracedBlock toVisit(TryBlockStack _tr) {
        ExecBlock currentBlock_ = _tr.getCurrentVisitedBlock();
        if (currentBlock_ instanceof ExecFinallyEval) {
            return null;
        }
        ExecBlock n_ = nextBlock(currentBlock_);
        if (n_ instanceof ExecBracedBlock) {
            return (ExecBracedBlock) n_;
        }
        ExecBracedBlock l_ = _tr.getLastBlock();
        if (!_tr.isEnteredCatch()) {
            ExecBlock next_ = currentBlock_.getNextSibling();
            if (next_ instanceof ExecAbstractCatchEval) {
                return (ExecBracedBlock) next_;
            }
        }
        if (l_ instanceof ExecFinallyEval) {
            return l_;
        }
        return null;
    }
    private static ExecBlock nextBlock(ExecBlock _current) {
        if (_current instanceof ExecTryEval) {
            return _current.getNextSibling();
        }
        return null;
    }


}
