package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectStdRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectStdRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo, new DefPreparer());
    }

    @Override
    Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack) {
        MethodId mid_ = getMetaInfo().getRealId();
        ArgumentListCall l_ = new ArgumentListCall(_args);
        return ExecInvokingOperation.callStd(_context.getExiting(), _context, new ClassMethodId(getClassName().getFormatted(),mid_), getInstance(), l_, _stack, getStdCallee());
    }
}
