package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.LocalThrowing;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendAbstractStask;
import code.formathtml.exec.stacks.RendTryBlockStack;
import code.util.CustList;

public final class RendLocalThrowing {

    private RendLocalThrowing() {
    }
    public static void removeBlockFinally(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall) {
        Struct s_ = _str;
        while (_rendStackCall.hasPages()) {
            boolean[] exit_ = new boolean[1];
            _rendStackCall.getStackCall().setNullCallingState();
            ImportingPage bkIp_ = _rendStackCall.getLastPage();
            while (true) {
                RendAbstractStask bl_ = bkIp_.tryGetRendLastStack();
                if (bl_ == null) {
                    break;
                }
                if (setRemovedCallingFinallyToProcess(_ctx,s_,_rendStackCall, bkIp_,bl_, exit_)) {
                    return;
                }
            }
            s_ = LocalThrowing.retrieve(_rendStackCall.getStackCall(),s_);
            _rendStackCall.removeLastPage();
        }
        _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(s_));
    }

    private static boolean setRemovedCallingFinallyToProcess(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, RendAbstractStask _bl, boolean[] _exit) {
        if (_bl instanceof RendTryBlockStack) {
            RendBlock currentBlock_ = _bl.getCurrentVisitedBlock();
            if (currentBlock_ instanceof RendTryEval) {
                RendAbstractCatchEval catchElt_ = retCatch((RendTryBlockStack)_bl, _ctx, _str, _rendStackCall, _bkIp, currentBlock_, _exit);
                if (catchElt_ != null) {
                    _rendStackCall.getStackCall().setNullCallingState();
                    _bl.setCurrentVisitedBlock(catchElt_);
                    RendBlock childCatch_ = catchElt_.getFirstChild();
                    _bkIp.getRendReadWrite().setRead(childCatch_);
                    _exit[0] = false;
                    return true;
                }
            }
        }
        if (ImportingPage.setRemovedCallingFinallyToProcess(_bkIp, _bl, null, _str)) {
            _rendStackCall.getStackCall().setNullCallingState();
            _exit[0] = false;
            return true;
        }
        _exit[0] = true;
        if (_bl instanceof RendTryBlockStack) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(TryBlockStack.choice(((RendTryBlockStack)_bl).getException(),_str)));
        } else {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(_str));
        }
        return false;
    }

    private static RendAbstractCatchEval retCatch(RendTryBlockStack _bl, ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, RendBlock _currentBlock, boolean[] _exit) {
        RendBlock n_ = _currentBlock.getNextSibling();
        //process try block
        while (n_ instanceof RendAbstractCatchEval || RendBlock.isPossibleEmpty(n_)) {
            RendAbstractCatchEval v_ = ret(n_, _ctx, _str, _rendStackCall, _bkIp, _exit);
            if (v_ != null) {
                return v_;
            }
            if (_exit[0]) {
                _exit[0] = false;
                _rendStackCall.getStackCall().setNullCallingState();
                if (throwIfGuardError(n_)) {
                    _bl.setException(_str);
                    return null;
                }
            }
            n_ = n_.getNextSibling();
        }
        return null;
    }
    private static RendAbstractCatchEval ret(RendBlock _n, ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, boolean[] _exit) {
        if (_n instanceof RendAbstractCatchEval) {
            RendAbstractCatchEval ca_ = (RendAbstractCatchEval) _n;
            int f_ = ExecHelperBlocks.first(0, _ctx, _rendStackCall, ca_.getContent(), _str, ca_.isCatchAll());
            if (f_ == -1) {
                return null;
            }
            return guard(_ctx, _rendStackCall, _bkIp, ca_, _exit);
        }
        return null;
    }

    private static RendAbstractCatchEval guard(ContextEl _ctx, RendStackCall _rendStackCall, ImportingPage _bkIp, RendAbstractCatchEval _ca, boolean[] _exit) {
        CustList<RendDynOperationNode> ls_ = _ca.getCondition().getList();
        if (ls_.isEmpty()) {
            return _ca;
        }
        String varName_ = _ca.getContent().getVariableName();
        Struct arg_ = RenderExpUtil.getFinalArg(ls_, _ctx, _rendStackCall);
        if (arg_ == null) {
            _exit[0] = true;
            possibleRemove(varName_, _bkIp);
            return null;
        }
        if (BooleanStruct.isTrue(arg_)) {
            return _ca;
        }
        possibleRemove(varName_, _bkIp);
        return null;
    }

    private static void possibleRemove(String _name, ImportingPage _bkIp) {
        if (!_name.isEmpty()) {
            _bkIp.removeRefVar(_name);
        }
    }

    public static boolean throwIfGuardError(RendBlock _block) {
        return _block instanceof RendAbstractCatchEval && ((RendAbstractCatchEval)_block).isThrowIfGuardError();
    }
}
