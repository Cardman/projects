package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class DirectStdRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectStdRefectMethodPageEl(Struct _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefPreparer(), _a, new DefParamReflectCheckerStepping());
    }

    @Override
    Struct prepare(ContextEl _context, ArrayRefState _args, Struct _right, StackCall _stack) {
        MethodId mid_ = getMetaInfo().getRealId();
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.getArray().listArgs());
        return ParamCheckerUtil.callStd(_context.getExiting(), _context, new ClassMethodId(getClassName().getFormatted(),mid_), getInstance(), l_, _stack, getStdCallee());
    }
}
