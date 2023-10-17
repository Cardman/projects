package code.expressionlanguage;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public abstract class AbsPrepareCustomEvents {
    private int eventIndex;

    public boolean call(ContextEl _context, StackCall _stack, Struct _inst, int _i) {
        if (excluded()) {
            return false;
        }
        LambdaStruct lda_ = fct(_inst, _i);
        if (lda_ != null) {
            ExecInvokingOperation.prepareCallDynReflect(new Argument(lda_), ArrayStruct.instance(StringExpUtil.getPrettyArrayType(_context.getStandards().getCoreNames().getAliasObject()),args()),0, _context, _stack);
            return true;
        }
        ExecOverrideInfo ov_ = overrideState(_context, _inst, _i);
        if (ov_ != null) {
            ExecTemplates.wrapAndCall(ov_.getPair(), ov_.getClassName(), new Argument(_inst), _context, _stack, ArgumentListCall.wrapCall(args()));
            return true;
        }
        return false;
    }

    private LambdaStruct fct(Struct _l, int _i) {
        if (_i < eventIndex) {
            return null;
        }
        if (!(_l instanceof AbstractFunctionalInstanceImpl)) {
            return null;
        }
        eventIndex = _i + 1;
        return ((AbstractFunctionalInstanceImpl)_l).getFunctional();
    }
    private ExecOverrideInfo overrideState(ContextEl _context, Struct _l, int _i) {
        if (_i < eventIndex) {
            return null;
        }
        eventIndex = _i + 1;
        return over(_context, _l);
    }

    private ExecOverrideInfo over(ContextEl _context, Struct _l) {
        return over(_l, _context, type(_context),  fct(_context));
    }

    private static ExecOverrideInfo over(Struct _l, ContextEl _context, ExecRootBlock _type, ExecNamedFunctionBlock _method) {
        String base_ = StringExpUtil.getIdFromAllTypes(_l.getClassName(_context));
        return _context.getClasses().getRedirections().get(_type.getNumberType()).getVal(_method, base_);
    }

    public int getEventIndex() {
        return eventIndex;
    }

    protected abstract boolean excluded();

    protected abstract ExecRootBlock type(ContextEl _ctx);

    protected abstract ExecNamedFunctionBlock fct(ContextEl _ctx);

    protected abstract CustList<Argument> args();
}
