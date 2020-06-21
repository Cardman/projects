package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    @Override
    boolean initType(ContextEl _cont) {
        LgNames stds_ = _cont.getStandards();
        return initDefault(_cont);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        LgNames stds_ = _cont.getStandards();
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalArgument().getStruct());
        return method_.isAbstract();
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }
}
