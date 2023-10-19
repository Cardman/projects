package code.expressionlanguage;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.AbstractFormatParamChecker;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public abstract class AbsPrepareCustomEvents {
    private int eventIndex;

    public boolean call(ContextEl _context, StackCall _stack, Struct _inst, int _i) {
        if (excluded()) {
            return false;
        }
        if (_i < eventIndex) {
            return false;
        }
        eventIndex = _i + 1;
        prepare(_context, _stack, _inst, type(_context), fct(_context), args());
        return true;
    }

    public static void prepare(ContextEl _context, StackCall _stack, Struct _inst, ExecRootBlock _type, ExecNamedFunctionBlock _fct, CustList<Argument> _args) {
        ExecOverrideInfo poly_ = ExecInvokingOperation.polymorph(_context, _inst, new ExecTypeFunction(_type, _fct));
        LambdaStruct lda_ = AbstractFormatParamChecker.matchAbstract(_inst, poly_);
        if (lda_ != null) {
            ExecInvokingOperation.prepareCallDynReflect(new Argument(lda_), ArrayStruct.instance(StringExpUtil.getPrettyArrayType(_context.getStandards().getCoreNames().getAliasObject()), _args),0, _context, _stack);
        } else {
            ExecTemplates.wrapAndCall(poly_.getPair(), poly_.getClassName(), new Argument(_inst), _context, _stack, ArgumentListCall.wrapCall(_args));
        }
    }

    public int getEventIndex() {
        return eventIndex;
    }

    protected abstract boolean excluded();

    protected abstract ExecRootBlock type(ContextEl _ctx);

    protected abstract ExecNamedFunctionBlock fct(ContextEl _ctx);

    protected abstract CustList<Argument> args();
}
