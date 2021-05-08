package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaStaticCallMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaStaticCallMethodPageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont, StackCall _stack) {
        return initDefault(_cont, _stack);
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
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        String paramName_ = _stack.formatVarType(getClassName());
        return checkStaticCall(getPair(), method_.getCache(), _context, paramName_, _list, _stack);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
