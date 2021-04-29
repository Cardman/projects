package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;

public final class LocalThrowing {

    private LocalThrowing() {
    }
    public static void removeBlockFinally(ContextEl _conf, Struct _str, StackCall _stackCall) {
        Struct custCause_ = _str;
        ExecAbstractCatchEval catchElt_ = null;
        while (_stackCall.hasPages()) {
            AbstractPageEl bkIp_ = _stackCall.getLastPage();
            bkIp_.clearCurrentEls();
            _stackCall.setCallingState(null);
            while (bkIp_.hasBlock()) {
                AbstractStask bl_ = bkIp_.getLastStack();
                ExecBlock currentBlock_ = bl_.getCurrentVisitedBlock();
                if (currentBlock_ instanceof ExecTryEval) {
                    ExecBlock n_ = currentBlock_.getNextSibling();
                    //process try block
                    while (n_ instanceof ExecAbstractCatchEval) {
                        if (n_ instanceof ExecCatchEval) {
                            ExecCatchEval ca_ = (ExecCatchEval) n_;
                            String name_ = ca_.getImportedClassName();
                            if (custCause_ == NullStruct.NULL_VALUE) {
                                n_ = n_.getNextSibling();
                                continue;
                            }
                            name_ = _stackCall.formatVarType(name_);
                            if (ExecTemplates.safeObject(name_, Argument.getNull(custCause_).getClassName(_conf), _conf) == ErrorType.NOTHING) {
                                catchElt_ = ca_;
                                String var_ = ca_.getVariableName();
                                LocalVariable lv_ = LocalVariable.newLocalVariable(custCause_,name_);
                                bkIp_.putValueVar(var_, lv_);
                                bl_.setCurrentVisitedBlock(ca_);
                                break;
                            }
                        } else {
                            ExecNullCatchEval ca_ = (ExecNullCatchEval) n_;
                            if (custCause_ == NullStruct.NULL_VALUE) {
                                catchElt_ = ca_;
                                bl_.setCurrentVisitedBlock(ca_);
                                break;
                            }
                        }
                        n_ = n_.getNextSibling();
                    }
                    if (catchElt_ != null) {
                        _conf.getCoverage().passCatches(catchElt_,_stackCall);
                        ExecBlock childCatch_ = catchElt_.getFirstChild();
                        bkIp_.setBlock(childCatch_);
                        return;
                    }
                }
                if (AbstractPageEl.setRemovedCallingFinallyToProcess(bkIp_,bl_,null,custCause_)) {
                    return;
                }
            }
            custCause_ = _conf.getLocks().processErrorClass(_conf, custCause_, bkIp_, _stackCall);
            _stackCall.removeLastPage();
        }
        _stackCall.setCallingState(new CustomFoundExc(custCause_));
    }


}
