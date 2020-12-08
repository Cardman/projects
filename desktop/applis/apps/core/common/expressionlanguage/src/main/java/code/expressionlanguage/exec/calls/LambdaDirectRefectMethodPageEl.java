package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaDirectRefectMethodPageEl(Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont) {
        return initDefault(_cont);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        MethodMetaInfo method_ = getMetaInfo();
        return method_.isAbstract();
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }
}
