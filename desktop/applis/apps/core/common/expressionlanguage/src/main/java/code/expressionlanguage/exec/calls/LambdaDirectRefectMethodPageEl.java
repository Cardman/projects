package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;

public final class LambdaDirectRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaDirectRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefInitPreparerAbs(_metaInfo), _r, _lms, new DefParamReflectCheckerStepping());
    }

    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        new MethodParamChecker(getPair(), _list, getAccessKind()).checkParams(getClassName(), ArgumentListCall.toStr(getParent()), getMetaInfo().getCache(), _context, _stack);
        return ArgumentListCall.toStr(NullStruct.NULL_VALUE);
    }
}
