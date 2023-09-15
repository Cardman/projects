package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaPolymorphRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaPolymorphRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new PolyInitPreparer(_metaInfo), _r, _lms);
    }

    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return new MethodParamChecker(getPair(), _list, getAccessKind()).checkParams(getClassName(), ArgumentListCall.toStr(getParent()), getMetaInfo().getCache(), _context, _stack);
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        if (getCheckedParams() == 0) {
            setCheckedParams(1);
            return _stack.getStopper().isStopAtExcMethod();
        }
        return false;
    }

    @Override
    protected boolean postArg(StackCall _stack) {
        return postArgBase(_stack);
    }
}
