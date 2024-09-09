package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class LambdaDirectStdRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    private final MethodId methodId;
    public LambdaDirectStdRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefPreparer(), _r, _lms,new DefParamReflectCheckerStepping());
        methodId = _metaInfo.getRealId();
    }

    @Override
    Struct prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return ParamCheckerUtil.callStd(_context.getExiting(), _context, new ClassMethodId(getClassName().getFormatted(),methodId), getParent(), _list, _stack, getStdCallee());
    }
}
