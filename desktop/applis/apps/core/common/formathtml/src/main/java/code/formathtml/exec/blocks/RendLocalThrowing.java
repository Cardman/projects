package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendAbstractStask;

public final class RendLocalThrowing {

    private RendLocalThrowing() {
    }
    public static void removeBlockFinally(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall) {
        while (_rendStackCall.hasPages()) {
            _rendStackCall.getStackCall().setCallingState(null);
            ImportingPage bkIp_ = _rendStackCall.getLastPage();
            while (true) {
                RendAbstractStask bl_ = bkIp_.tryGetRendLastStack();
                if (bl_ == null) {
                    break;
                }
                if (setRemovedCallingFinallyToProcess(_ctx,_str, bkIp_,bl_)) {
                    return;
                }
            }
            _rendStackCall.removeLastPage();
        }
        _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(_str));
    }

    private static boolean setRemovedCallingFinallyToProcess(ContextEl _ctx, Struct _str, ImportingPage _bkIp, RendAbstractStask _bl) {
        RendBlock currentBlock_ = _bl.getCurrentVisitedBlock();
        if (currentBlock_ instanceof RendTryEval) {
            RendAbstractCatchEval catchElt_ = retCatch(_ctx, _str, _bkIp, currentBlock_);
            if (catchElt_ != null) {
                _bl.setCurrentVisitedBlock(catchElt_);
                RendBlock childCatch_ = catchElt_.getFirstChild();
                _bkIp.getRendReadWrite().setRead(childCatch_);
                return true;
            }
        }
        return ImportingPage.setRemovedCallingFinallyToProcess(_bkIp, _bl, null, _str);
    }

    private static RendAbstractCatchEval retCatch(ContextEl _ctx, Struct _str, ImportingPage _bkIp, RendBlock _currentBlock) {
        RendBlock n_ = _currentBlock.getNextSibling();
        //process try block
        while (n_ instanceof RendAbstractCatchEval || n_ instanceof RendPossibleEmpty) {
            if (n_ instanceof RendCatchEval) {
                RendCatchEval ca_ = (RendCatchEval) n_;
                String name_ = ca_.getImportedClassName();
                if (_str != NullStruct.NULL_VALUE && ExecInherits.safeObject(name_, Argument.getNull(_str).getClassName(_ctx), _ctx) == ErrorType.NOTHING) {
                    String var_ = ca_.getVariableName();
                    LocalVariable lv_ = LocalVariable.newLocalVariable(_str, name_);
                    _bkIp.putValueVar(var_, new VariableWrapper(lv_));
                    return ca_;
                }
            } else if (n_ instanceof RendNullCatchEval){
                RendNullCatchEval ca_ = (RendNullCatchEval) n_;
                if (_str == NullStruct.NULL_VALUE) {
                    return ca_;
                }
            }
            n_ = n_.getNextSibling();
        }
        return null;
    }

}
