package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectStdRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectStdRefectMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefPreparer(), _a);
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        if (getCheckedParams() == 0) {
            setCheckedParams(1);
            CustList<Argument> args_ = getArrRef().getArray().listArgs();
            MethodId mid_ = getMetaInfo().getRealId();
            return checkParamsBase(_context,_stack,mid_,args_);
        }
        return false;
    }

    @Override
    protected boolean postArg(StackCall _stack) {
        return postArgBase(_stack);
    }
    @Override
    Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack) {
        MethodId mid_ = getMetaInfo().getRealId();
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.getArray().listArgs());
        return ParamCheckerUtil.callStd(_context.getExiting(), _context, new ClassMethodId(getClassName().getFormatted(),mid_), getInstance(), l_, _stack, getStdCallee());
    }
}
