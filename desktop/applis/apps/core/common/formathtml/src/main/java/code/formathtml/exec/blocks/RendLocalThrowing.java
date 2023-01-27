package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.LocalThrowing;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
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
            _rendStackCall.getStackCall().setNullCallingState();
            ImportingPage bkIp_ = _rendStackCall.getLastPage();
            while (true) {
                RendAbstractStask bl_ = bkIp_.tryGetRendLastStack();
                if (bl_ == null) {
                    break;
                }
                if (setRemovedCallingFinallyToProcess(_ctx,s_,_rendStackCall, bkIp_,bl_)) {
                    return;
                }
            }
            s_ = LocalThrowing.retrieve(_rendStackCall.getStackCall(),s_);
            _rendStackCall.removeLastPage();
        }
        _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(s_));
    }

    private static boolean setRemovedCallingFinallyToProcess(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, RendAbstractStask _bl) {
        if (_bl instanceof RendTryBlockStack) {
            RendBlock currentBlock_ = _bl.getCurrentVisitedBlock();
            if (currentBlock_ instanceof RendTryEval) {
                RendAbstractCatchEval catchElt_ = retCatch((RendTryBlockStack)_bl, _ctx, _str, _rendStackCall, _bkIp, currentBlock_);
                if (catchElt_ != null) {
                    _rendStackCall.getStackCall().setNullCallingState();
                    _bl.setCurrentVisitedBlock(catchElt_);
                    RendBlock childCatch_ = catchElt_.getFirstChild();
                    _bkIp.getRendReadWrite().setRead(childCatch_);
                    return true;
                }
            }
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(((RendTryBlockStack)_bl).getException()));
        }
        if (ImportingPage.setRemovedCallingFinallyToProcess(_bkIp, _bl, null, _str)) {
            _rendStackCall.getStackCall().setNullCallingState();
            return true;
        }
        return false;
    }

    private static RendAbstractCatchEval retCatch(RendTryBlockStack _bl, ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, RendBlock _currentBlock) {
        RendBlock n_ = _currentBlock.getNextSibling();
        //process try block
        while (n_ instanceof RendAbstractCatchEval || n_ instanceof RendPossibleEmpty) {
            RendAbstractCatchEval v_ = ret(n_, _ctx, _str, _rendStackCall, _bkIp);
            if (v_ != null) {
                return v_;
            }
            if (throwIfGuardError(n_)) {
                _bl.setException(_str);
                return null;
            }
            n_ = n_.getNextSibling();
        }
        return null;
    }
    private static RendAbstractCatchEval ret(RendBlock _n, ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp) {
        if (_n instanceof RendCatchEval) {
            RendCatchEval ca_ = (RendCatchEval) _n;
            String name_ = _rendStackCall.formatVarType(ca_.getImportedClassName());
            if (_str != NullStruct.NULL_VALUE && ExecInherits.safeObject(name_, Argument.getNull(_str).getClassName(_ctx), _ctx) == ErrorType.NOTHING) {
                return guard(_ctx, _str, _rendStackCall, _bkIp, ca_, name_);
            }
        }
        if (_n instanceof RendListCatchEval) {
            RendListCatchEval ca_ = (RendListCatchEval) _n;
            int match_ = ca_.getList().match(_str, _ctx);
            if (match_ >= 0) {
                return ca_;
            }
        }
        return null;
    }

    private static RendCatchEval guard(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, RendCatchEval _ca, String _name) {
        CustList<RendDynOperationNode> ls_ = _ca.getCondition().getList();
        if (ls_.isEmpty()) {
            String var_ = _ca.getVariableName();
            LocalVariable lv_ = LocalVariable.newLocalVariable(_str, _name);
            _bkIp.putValueVar(var_, new VariableWrapper(lv_));
            return _ca;
        }
        String var_ = _ca.getVariableName();
        LocalVariable lv_ = LocalVariable.newLocalVariable(_str, _name);
        _bkIp.putValueVar(var_, new VariableWrapper(lv_));
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(ls_, _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            _bkIp.removeRefVar(var_);
            _rendStackCall.getStackCall().setNullCallingState();
            return null;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return _ca;
        }
        _bkIp.removeRefVar(var_);
        return null;
    }

    public static boolean throwIfGuardError(RendBlock _block) {
        return _block instanceof RendCatchEval && ((RendCatchEval)_block).isThrowIfGuardError();
    }
}
