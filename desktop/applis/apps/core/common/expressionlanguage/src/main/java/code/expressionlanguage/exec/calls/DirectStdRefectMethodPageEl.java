package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectStdRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectStdRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    boolean isAbstract(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right, StackCall _stack) {
        return ExecInvokingOperation.callStd(_context.getExiting(), _context, _className, _mid, _instance, _args, _stack);
    }
}
