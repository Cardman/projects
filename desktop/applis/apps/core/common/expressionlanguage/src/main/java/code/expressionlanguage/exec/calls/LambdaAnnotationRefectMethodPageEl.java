package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class LambdaAnnotationRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    private final String name;
    public LambdaAnnotationRefectMethodPageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r) {
        super(_instance,_array, _metaInfo, new DefPreparer(), _r);
        name = _metaInfo.getRealId().getName();
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        return checkParamsAnnot(_context,_stack, new CustList<Argument>());
    }
    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return ExecInvokingOperation.getAnnotation(getInstance(),name,_context, _stack);
    }
}
