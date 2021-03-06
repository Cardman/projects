package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendRemovableVars;

public final class RendLocalThrowing {

    private RendLocalThrowing() {
    }
    public static void removeBlockFinally(ContextEl _ctx, Struct _str, StackCall _stackCall, RendStackCall _rendStackCall) {
        RendAbstractCatchEval catchElt_ = null;
        while (_rendStackCall.hasPages()) {
            _stackCall.setCallingState(null);
            ImportingPage bkIp_ = _rendStackCall.getLastPage();
            while (bkIp_.hasBlock()) {
                RendRemovableVars bl_ = bkIp_.getRendLastStack();
                RendBlock currentBlock_ = bl_.getCurrentVisitedBlock();
                if (currentBlock_ instanceof RendTryEval) {
                    RendBlock n_ = currentBlock_.getNextSibling();
                    //process try block
                    while (n_ instanceof RendAbstractCatchEval || n_ instanceof RendPossibleEmpty) {
                        if (n_ instanceof RendPossibleEmpty) {
                            n_ = n_.getNextSibling();
                            continue;
                        }
                        if (n_ instanceof RendCatchEval) {
                            RendCatchEval ca_ = (RendCatchEval) n_;
                            String name_ = ca_.getImportedClassName();
                            if (_str == NullStruct.NULL_VALUE) {
                                n_ = n_.getNextSibling();
                                continue;
                            }
                            if (ExecTemplates.safeObject(name_, _str, _ctx) == ErrorType.NOTHING) {
                                catchElt_ = ca_;
                                String var_ = ca_.getVariableName();
                                LocalVariable lv_ = LocalVariable.newLocalVariable(_str,name_);
                                bkIp_.putValueVar(var_, lv_);
                                bl_.setCurrentVisitedBlock(ca_);
                                break;
                            }
                        } else {
                            RendNullCatchEval ca_ = (RendNullCatchEval) n_;
                            if (_str == NullStruct.NULL_VALUE) {
                                catchElt_ = ca_;
                                bl_.setCurrentVisitedBlock(ca_);
                                break;
                            }
                        }
                        n_ = n_.getNextSibling();
                    }
                    if (catchElt_ != null) {
                        RendBlock childCatch_ = catchElt_.getFirstChild();
                        bkIp_.getRendReadWrite().setRead(childCatch_);
                        return;
                    }
                }
                if (ImportingPage.setRemovedCallingFinallyToProcess(bkIp_,bl_, null,_str)) {
                    return;
                }
            }
            _rendStackCall.removeLastPage();
        }
        _stackCall.setCallingState(new CustomFoundExc(_str));
    }

}
