package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectStdRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    private final MethodId methodId;
    public LambdaDirectStdRefectMethodPageEl(Argument _instance, ArgumentListCall _array,Argument _right,  MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
        methodId = _metaInfo.getRealId();
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
    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list, StackCall _stack) {
        return ExecInvokingOperation.callStd(_context.getExiting(), _context, _className, methodId, _instance, _list, _stack);
    }
}
