package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    @Override
    boolean initType(ContextEl _cont) {
        return initDefault(_cont);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalArgument().getStruct());
        return method_.isAbstract();
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }
}
