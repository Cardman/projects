package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    @Override
    boolean initType(ContextEl _cont) {
        LgNames stds_ = _cont.getStandards();
        MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(getGlobalArgument().getStruct(),stds_);
        return method_.isWideStatic();
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        LgNames stds_ = _cont.getStandards();
        MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(getGlobalArgument().getStruct(),stds_);
        return method_.isAbstract();
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }
}
