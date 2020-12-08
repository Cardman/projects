package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaPolymorphRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaPolymorphRefectMethodPageEl(Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont) {
        return initDefault(_cont);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        MethodMetaInfo method_ = getMetaInfo();
        return method_.isInstanceMethod();
    }
}
