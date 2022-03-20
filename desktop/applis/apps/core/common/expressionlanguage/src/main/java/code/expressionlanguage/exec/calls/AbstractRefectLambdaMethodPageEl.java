package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public abstract class AbstractRefectLambdaMethodPageEl extends AbstractRefectCommonMethodPageEl {

    private boolean calledAfter;

    private final ArgumentListCall array;

    protected AbstractRefectLambdaMethodPageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo, AbstractPreparer _preparer) {
        super(_instance, _metaInfo, _preparer);
        setLambda(true);
        array = _array;
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        setNullReadWrite();
    }

    private boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(_context, _stack)) {
            return false;
        }
        if (!calledAfter) {
            setWrapException(false);
            Argument arg_ = prepare(_context, array, _stack);
            if (_stack.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            calledAfter = true;
            if (_context.callsOrException(_stack)) {
                setWrapException(_stack.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    abstract Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack);
}
