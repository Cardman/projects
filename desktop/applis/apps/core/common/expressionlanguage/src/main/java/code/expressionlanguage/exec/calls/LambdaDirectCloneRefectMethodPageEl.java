package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectCloneRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaDirectCloneRefectMethodPageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r) {
        super(_instance,_array, _metaInfo, new DefPreparer(), _r);
    }

    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return ExecInvokingOperation.cloneArray(getInstance(),_context, _stack);
    }
}
