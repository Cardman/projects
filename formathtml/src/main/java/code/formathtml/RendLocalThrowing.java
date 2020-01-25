package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ErrorType;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.stacks.RendTryBlockStack;

public final class RendLocalThrowing implements RendCallingFinally {
    @Override
    public void removeBlockFinally(Configuration _conf) {
        RendAbstractCatchEval catchElt_ = null;
        while (_conf.hasPages()) {
            Struct custCause_ = _conf.getException();
            ImportingPage bkIp_ = _conf.getLastPage();
            while (bkIp_.hasBlock()) {
                RendRemovableVars bl_ = bkIp_.getRendLastStack();
                if (!(bl_ instanceof RendTryBlockStack)) {
                    bl_.removeVarAndLoop(bkIp_);
                    continue;
                }
                RendTryBlockStack try_ = (RendTryBlockStack)bl_;
                boolean addFinallyClause_ = true;
                if (!(try_.getLastBlock() instanceof RendFinallyEval)) {
                    addFinallyClause_ = false;
                }
                RendBlock currentBlock_ = try_.getCurrentVisitedBlock();
                if (!(currentBlock_ instanceof RendTryEval)) {
                    if (!(currentBlock_ instanceof RendFinallyEval)) {
                        if (addFinallyClause_) {
                            try_.setException(custCause_);
                            try_.setCalling(this);
                            _conf.setException(null);
                            bkIp_.getRendReadWrite().setRead(try_.getLastBlock());
                            return;
                        }
                    }
                    bkIp_.removeRendLastBlock();
                    continue;
                }
                RendBlock n_ = currentBlock_.getNextSibling();
                String excType_ = "";
                //process try block
                while (n_ instanceof RendAbstractCatchEval || n_ instanceof RendPossibleEmpty) {
                    if (n_ instanceof RendPossibleEmpty) {
                        n_ = n_.getNextSibling();
                        continue;
                    }
                    if (n_ instanceof RendCatchEval) {
                        RendCatchEval ca_ = (RendCatchEval) n_;
                        String name_ = ca_.getImportedClassName();
                        if (custCause_ == NullStruct.NULL_VALUE) {
                            n_ = n_.getNextSibling();
                            continue;
                        }
                        Argument arg_ = new Argument(custCause_);
                        if (Templates.safeObject(name_, arg_, _conf) == ErrorType.NOTHING) {
                            excType_ = name_;
                            catchElt_ = ca_;
                            try_.setCurrentBlock(ca_);
                            break;
                        }
                    } else {
                        RendNullCatchEval ca_ = (RendNullCatchEval) n_;
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
                    RendBlock childCatch_ = catchElt_.getFirstChild();
                    if (catchElt_ instanceof RendCatchEval) {
                        RendCatchEval c_ = (RendCatchEval) catchElt_;
                        String var_ = c_.getVariableName();
                        LocalVariable lv_ = LocalVariable.newLocalVariable(custCause_,excType_);
                        bkIp_.getCatchVars().put(var_, lv_);
                    }
                    bkIp_.getRendReadWrite().setRead(childCatch_);
                    return;
                }
                if (addFinallyClause_) {
                    try_.setCalling(this);
                    _conf.setException(null);
                    try_.setException(custCause_);
                    bkIp_.getRendReadWrite().setRead(try_.getLastBlock());
                    return;
                }
                bkIp_.removeRendLastBlock();
            }
            _conf.removeLastPage();
        }
    }
}
