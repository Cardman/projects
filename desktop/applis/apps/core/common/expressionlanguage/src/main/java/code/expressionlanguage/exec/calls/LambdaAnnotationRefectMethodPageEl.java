package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaAnnotationRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    private final String name;
    public LambdaAnnotationRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefPreparer(), _r, _lms);
        name = _metaInfo.getRealId().getName();
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        return checkParamsAnnot(_stack);
    }
    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return ExecInvokingOperation.getAnnotation(ArgumentListCall.toStr(getParent()),name,_context, _stack);
    }
}
