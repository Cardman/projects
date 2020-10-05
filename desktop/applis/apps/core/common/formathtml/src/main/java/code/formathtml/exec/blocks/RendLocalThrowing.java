package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.stacks.RendExceptionCallingFinally;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.util.BeanLgNames;

public final class RendLocalThrowing implements RendCallingFinally {
    @Override
    public void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx) {
        RendAbstractCatchEval catchElt_ = null;
        Struct custCause_ = (Struct) _ctx.getCallingState();
        while (_conf.hasPages()) {
            _ctx.setException(null);
            ImportingPage bkIp_ = _conf.getLastPage();
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
                            if (custCause_ == NullStruct.NULL_VALUE) {
                                n_ = n_.getNextSibling();
                                continue;
                            }
                            Argument arg_ = new Argument(custCause_);
                            if (ExecTemplates.safeObject(name_, arg_, _ctx) == ErrorType.NOTHING) {
                                catchElt_ = ca_;
                                String var_ = ca_.getVariableName();
                                LocalVariable lv_ = LocalVariable.newLocalVariable(custCause_,name_);
                                bkIp_.getValueVars().put(var_, lv_);
                                bl_.setCurrentVisitedBlock(ca_);
                                break;
                            }
                        } else {
                            RendNullCatchEval ca_ = (RendNullCatchEval) n_;
                            if (custCause_ == NullStruct.NULL_VALUE) {
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
                if (ImportingPage.setRemovedCallingFinallyToProcess(bkIp_,bl_,this,custCause_)) {
                    return;
                }
            }
            _conf.removeLastPage();
        }
        _ctx.setException(custCause_);
    }

    @Override
    public RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new RendExceptionCallingFinally(this,_struct);
    }
}
