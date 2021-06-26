package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DefInitPreparerAbs extends DefInitPreparer {

    public DefInitPreparerAbs(MethodMetaInfo _metaInfo) {
        super(_metaInfo);
    }

    @Override
    public boolean isAbstract(ContextEl _context, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        return method_.isAbstract();
    }
}
