package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectEnumMethods extends AbstractRefectLambdaMethodPageEl {
    public LambdaDirectEnumMethods(Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _context, StackCall _stack) {
        return initDefault(_context, _stack);
    }

    @Override
    boolean isAbstract(ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        return ExecInvokingOperation.processEnums(_context.getExiting(), _context, _list, _stack, method_.getPairType());
    }
}
