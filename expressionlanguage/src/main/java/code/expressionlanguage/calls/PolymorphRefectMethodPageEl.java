package code.expressionlanguage.calls;

import code.expressionlanguage.structs.MethodMetaInfo;

public final class PolymorphRefectMethodPageEl extends AbstractRefectMethodPageEl {

    @Override
    boolean initType() {
        MethodMetaInfo method_ = (MethodMetaInfo) getGlobalArgument().getStruct();
        return method_.isWideStatic();
    }

    @Override
    boolean isAbstract() {
        return false;
    }

    @Override
    boolean isPolymorph() {
        MethodMetaInfo method_ = (MethodMetaInfo) getGlobalArgument().getStruct();
        String className_ = method_.getClassName();
        return !method_.isWideStatic() && !className_.startsWith("[");
    }
}
