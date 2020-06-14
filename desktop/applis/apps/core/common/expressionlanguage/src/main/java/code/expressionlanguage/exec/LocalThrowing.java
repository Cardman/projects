package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.ExceptionCallingFinally;
import code.expressionlanguage.exec.stacks.RemovableVars;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;

public final class LocalThrowing implements CallingFinally {

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        Struct custCause_ = (Struct) _conf.getCallingState();
        AbstractCatchEval catchElt_ = null;
        while (_conf.hasPages()) {
            AbstractPageEl bkIp_ = _conf.getLastPage();
            bkIp_.clearCurrentEls();
            _conf.setException(null);
            while (bkIp_.hasBlock()) {
                RemovableVars bl_ = bkIp_.getLastStack();
                Block currentBlock_ = bl_.getCurrentVisitedBlock();
                if (currentBlock_ instanceof TryEval) {
                    Block n_ = currentBlock_.getNextSibling();
                    //process try block
                    while (n_ instanceof AbstractCatchEval) {
                        if (n_ instanceof CatchEval) {
                            CatchEval ca_ = (CatchEval) n_;
                            String name_ = ca_.getImportedClassName();
                            if (custCause_ == NullStruct.NULL_VALUE) {
                                n_ = n_.getNextSibling();
                                continue;
                            }
                            name_ = bkIp_.formatVarType(name_, _conf);
                            Argument arg_ = new Argument(custCause_);
                            if (Templates.safeObject(name_, arg_, _conf) == ErrorType.NOTHING) {
                                catchElt_ = ca_;
                                bl_.setCurrentVisitedBlock(ca_);
                                break;
                            }
                        } else {
                            NullCatchEval ca_ = (NullCatchEval) n_;
                            if (custCause_ == NullStruct.NULL_VALUE) {
                                catchElt_ = ca_;
                                bl_.setCurrentVisitedBlock(ca_);
                                break;
                            }
                        }
                        n_ = n_.getNextSibling();
                    }
                    if (catchElt_ != null) {
                        _conf.getCoverage().passCatches(_conf,catchElt_);
                        Block childCatch_ = catchElt_.getFirstChild();
                        if (catchElt_ instanceof CatchEval) {
                            CatchEval c_ = (CatchEval) catchElt_;
                            String var_ = c_.getVariableName();
                            LocalVariable lv_ = LocalVariable.newLocalVariable(custCause_,_conf);
                            bkIp_.getCatchVars().put(var_, lv_);
                        }
                        bkIp_.getReadWrite().setBlock(childCatch_);
                        return;
                    }
                }
                if (AbstractPageEl.setRemovedCallingFinallyToProcess(bkIp_,bl_,this,custCause_)) {
                    return;
                }
            }
            custCause_ = _conf.getClasses().getLocks().processErrorClass(_conf, custCause_);
            _conf.removeLastPage();
        }
        _conf.setException(custCause_);
    }


    @Override
    public AbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new ExceptionCallingFinally(this,_struct);
    }
}
