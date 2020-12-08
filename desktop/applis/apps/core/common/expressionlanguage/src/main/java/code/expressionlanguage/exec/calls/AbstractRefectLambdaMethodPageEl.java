package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public abstract class AbstractRefectLambdaMethodPageEl extends AbstractRefectCommonMethodPageEl {

    private boolean calledAfter;

    private final ArgumentListCall array;
    private Argument rightArg;

    public AbstractRefectLambdaMethodPageEl(Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance, _metaInfo);
        array = _array;
        rightArg = _right;
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        if (!keep(_context)) {
            return false;
        }
        if (!calledAfter) {
            setWrapException(false);
            Argument arg_ = prepare(_context, getClassName(), getInstance(), rightArg, array);
            if (_context.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            calledAfter = true;
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list) {
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, _className, getPair(), _instance, getMetaInfo().getCache(), _list, _right, getAccessKind(), getMethodName());
    }
}
