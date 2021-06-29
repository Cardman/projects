package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;

public final class LocalThrowing {

    private LocalThrowing() {
    }
    public static void removeBlockFinally(ContextEl _conf, Struct _str, StackCall _stackCall) {
        Struct custCause_ = _str;
        while (_stackCall.hasPages()) {
            AbstractPageEl bkIp_ = _stackCall.getLastPage();
            bkIp_.clearCurrentEls();
            _stackCall.setCallingState(null);
            while (true) {
                AbstractStask bl_ = bkIp_.tryGetLastStack();
                if (bl_ == null) {
                    break;
                }
                if (setRemovedCallingFinallyToProcess(_conf,_stackCall,custCause_, bkIp_, bl_)) {
                    return;
                }
            }
            custCause_ = _conf.getLocks().processErrorClass(_conf, custCause_, bkIp_, _stackCall);
            _stackCall.removeLastPage();
        }
        _stackCall.setCallingState(new CustomFoundExc(custCause_));
    }

    private static boolean setRemovedCallingFinallyToProcess(ContextEl _conf, StackCall _stackCall,Struct _custCause, AbstractPageEl _bkIp, AbstractStask _bl) {
        ExecBlock currentBlock_ = _bl.getCurrentVisitedBlock();
        if (currentBlock_ instanceof ExecTryEval) {
            ExecAbstractCatchEval catchElt_ = retCatch(_conf, _stackCall, _custCause, _bkIp, currentBlock_);
            if (catchElt_ != null) {
                _bl.setCurrentVisitedBlock(catchElt_);
                _conf.getCoverage().passCatches(catchElt_,_stackCall);
                ExecBlock childCatch_ = catchElt_.getFirstChild();
                _bkIp.setBlock(childCatch_);
                return true;
            }
        }
        return ExecHelperBlocks.setRemovedCallingFinallyToProcess(_bkIp, _bl, null, _custCause);
    }

    private static ExecAbstractCatchEval retCatch(ContextEl _conf, StackCall _stackCall, Struct _cause, AbstractPageEl _bkIp, ExecBlock _currentBlock) {
        ExecBlock n_ = _currentBlock.getNextSibling();
        //process try block
        while (n_ instanceof ExecAbstractCatchEval) {
            if (n_ instanceof ExecCatchEval) {
                ExecCatchEval ca_ = (ExecCatchEval) n_;
                String name_ = _stackCall.formatVarType(ca_.getImportedClassName());
                if (_cause != NullStruct.NULL_VALUE && ExecInherits.safeObject(name_, Argument.getNull(_cause).getClassName(_conf), _conf) == ErrorType.NOTHING) {
                    String var_ = ca_.getVariableName();
                    LocalVariable lv_ = LocalVariable.newLocalVariable(_cause, name_);
                    _bkIp.putValueVar(var_, lv_);
                    return ca_;
                }
            } else {
                ExecNullCatchEval ca_ = (ExecNullCatchEval) n_;
                if (_cause == NullStruct.NULL_VALUE) {
                    return ca_;
                }
            }
            n_ = n_.getNextSibling();
        }
        return null;
    }


}
