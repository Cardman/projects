package code.expressionlanguage.methods.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ErrorType;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;

public final class LocalThrowing implements CallingFinally {

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractCatchEval catchElt_ = null;
        while (_conf.hasPages()) {
            Struct custCause_ = _conf.getException();
            AbstractPageEl bkIp_ = _conf.getLastPage();
            while (bkIp_.hasBlock()) {
                RemovableVars bl_ = bkIp_.getLastStack();
                if (!(bl_ instanceof TryBlockStack)) {
                    bl_.getCurrentVisitedBlock().removeAllVars(bkIp_);
                    bkIp_.removeLastBlock();
                    continue;
                }
                TryBlockStack try_ = (TryBlockStack)bl_;
                boolean addFinallyClause_ = true;
                BracedBlock lastPart_ = try_.getLastBlock();
                if (!(lastPart_ instanceof FinallyEval)) {
                    addFinallyClause_ = false;
                }
                Block currentBlock_ = try_.getCurrentVisitedBlock();
                if (!(currentBlock_ instanceof TryEval)) {
                    if (!(currentBlock_ instanceof FinallyEval)) {
                        if (addFinallyClause_) {
                            try_.setException(custCause_);
                            try_.setCalling(this);
                            _conf.setException(null);
                            bkIp_.clearCurrentEls();
                            bkIp_.getReadWrite().setBlock(lastPart_);
                            return;
                        }
                    }
                    bkIp_.removeLastBlock();
                    continue;
                }
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
                            try_.setCurrentBlock(ca_);
                            break;
                        }
                    } else {
                        NullCatchEval ca_ = (NullCatchEval) n_;
                        if (custCause_ == NullStruct.NULL_VALUE) {
                            catchElt_ = ca_;
                            try_.setCurrentBlock(ca_);
                            break;
                        }
                    }
                    n_ = n_.getNextSibling();
                }
                if (catchElt_ != null) {
                    try_.setCalling(null);
                    _conf.setException(null);
                    bkIp_.clearCurrentEls();
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
                if (addFinallyClause_) {
                    try_.setCalling(this);
                    _conf.setException(null);
                    try_.setException(custCause_);
                    bkIp_.clearCurrentEls();
                    bkIp_.getReadWrite().setBlock(lastPart_);
                    return;
                }
                bkIp_.removeLastBlock();
            }
            _conf.getClasses().getLocks().processErrorClass(_conf, custCause_);
            _conf.removeLastPage();
        }
    }

}
