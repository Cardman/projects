package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectCloneRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectCloneRefectMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefPreparer(), _a);
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        return checkParamsAnnot(_stack);
    }

    @Override
    Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack) {
        MethodId mid_ = getMetaInfo().getRealId();
        CustomFoundExc ex_ = ExecTemplates.checkParams(_context, getClassName().getFormatted(), mid_, getInstance(), _args.getArray().listArgs(), _stack);
        if (ex_ != null) {
            _stack.setCallingState(ex_);
            return Argument.createVoid();
        }
        return ExecInvokingOperation.cloneArray(getInstance(),_context, _stack);
    }
}
