package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectStdRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectStdRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo, boolean _refer) {
        super(_instance,_array, _metaInfo, new DefPreparer(), _refer);
    }

    @Override
    Argument prepare(ContextEl _context, ArrayStruct _args, Argument _right, StackCall _stack) {
        MethodId mid_ = getMetaInfo().getRealId();
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.listArgs());
        return ParamCheckerUtil.callStd(_context.getExiting(), _context, new ClassMethodId(getClassName().getFormatted(),mid_), getInstance(), l_, _stack, getStdCallee());
    }
}
