package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
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
