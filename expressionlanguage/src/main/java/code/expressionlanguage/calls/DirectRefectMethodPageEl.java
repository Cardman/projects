package code.expressionlanguage.calls;

import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {
    @Override
    boolean isAbstract() {
        MethodMetaInfo method_ = (MethodMetaInfo) getGlobalArgument().getStruct();
        return method_.isAbstract();
    }

    @Override
    boolean isPolymorph() {
        return false;
    }
}
