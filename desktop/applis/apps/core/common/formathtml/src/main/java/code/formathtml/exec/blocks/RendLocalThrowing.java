package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
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
import code.util.CustList;

public final class RendLocalThrowing {

    private RendLocalThrowing() {
    }
    public static void removeBlockFinally(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall) {
        while (_rendStackCall.hasPages()) {
            _rendStackCall.getStackCall().setNullCallingState();
            ImportingPage bkIp_ = _rendStackCall.getLastPage();
            while (true) {
                RendAbstractStask bl_ = bkIp_.tryGetRendLastStack();
                if (bl_ == null) {
                    break;
                }
                if (setRemovedCallingFinallyToProcess(_ctx,_str,_rendStackCall, bkIp_,bl_)) {
                    return;
                }
            }
            _rendStackCall.removeLastPage();
        }
        _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(_str));
    }

    private static boolean setRemovedCallingFinallyToProcess(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, RendAbstractStask _bl) {
        RendBlock currentBlock_ = _bl.getCurrentVisitedBlock();
        if (currentBlock_ instanceof RendTryEval) {
            RendAbstractCatchEval catchElt_ = retCatch(_ctx, _str, _rendStackCall, _bkIp, currentBlock_);
            if (catchElt_ != null) {
                _bl.setCurrentVisitedBlock(catchElt_);
                RendBlock childCatch_ = catchElt_.getFirstChild();
                _bkIp.getRendReadWrite().setRead(childCatch_);
                return true;
            }
        }
        return ImportingPage.setRemovedCallingFinallyToProcess(_bkIp, _bl, null, _str);
    }

    private static RendAbstractCatchEval retCatch(ContextEl _ctx, Struct _str, RendStackCall _rendStackCall, ImportingPage _bkIp, RendBlock _currentBlock) {
        RendBlock n_ = _currentBlock.getNextSibling();
        //process try block
        while (n_ instanceof RendAbstractCatchEval || n_ instanceof RendPossibleEmpty) {
            RendAbstractCatchEval v_ = ret(n_, _ctx, _str, _rendStackCall, _bkIp);
            if (v_ != null) {
                return v_;
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
            _rendStackCall.getStackCall().setNullCallingState();
            return null;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return _ca;
        }
        _bkIp.removeRefVar(var_);
        return null;
    }

}
