package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class DirectCallRefectMethodPageEl extends AbstractRefectMethodPageEl {
    private final AbstractQuickCall quickCall;

    public DirectCallRefectMethodPageEl(Struct _instance, MethodMetaInfo _metaInfo, ArrayRefState _a, AbstractQuickCall _abs) {
        super(_instance, _metaInfo, new DefPreparer(), _a);
        quickCall = _abs;
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        return checkParamsAnnot(_stack);
    }

    @Override
    Struct prepare(ContextEl _context, ArrayRefState _args, Struct _right, StackCall _stack) {
        MethodId mid_ = getMetaInfo().getRealId();
        CustomFoundExc ex_ = ExecTemplates.checkParams(_context, getClassName().getFormatted(), mid_, getInstance(), _args.getArray().listArgs(), _stack);
        if (ex_ != null) {
            _stack.setCallingState(ex_);
            return NullStruct.NULL_VALUE;
        }
        return quickCall.calculate(this,_context, _stack);
    }

    public AbstractQuickCall getQuickCall() {
        return quickCall;
    }
}
