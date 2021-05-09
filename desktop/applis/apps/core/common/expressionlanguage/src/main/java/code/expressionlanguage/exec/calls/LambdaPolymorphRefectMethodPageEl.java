package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.DefaultParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaPolymorphRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaPolymorphRefectMethodPageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo) {
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
        MethodMetaInfo method_ = getMetaInfo();
        return method_.isInstanceMethod();
    }

    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return new DefaultParamChecker(getPair(), _list, getAccessKind(), CallPrepareState.METHOD, null).checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
    }

}
