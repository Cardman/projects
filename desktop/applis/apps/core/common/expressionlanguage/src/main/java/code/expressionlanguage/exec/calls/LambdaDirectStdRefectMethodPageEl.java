package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectStdRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    private final MethodId methodId;
    public LambdaDirectStdRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefPreparer(), _r, _lms);
        methodId = _metaInfo.getRealId();
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
    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return ParamCheckerUtil.callStd(_context.getExiting(), _context, new ClassMethodId(getClassName().getFormatted(),methodId), ArgumentListCall.toStr(getParent()), _list, _stack, getStdCallee());
    }
}
