package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class PolyInitPreparer extends DefPreparerNoAbs {
    private final MethodMetaInfo metaInfo;

    protected PolyInitPreparer(MethodMetaInfo _metaInfo) {
        this.metaInfo = _metaInfo;
    }

    @Override
    public boolean initType(ContextEl _context, StackCall _stack) {
        return DefInitPreparer.initDel(_context,_stack,metaInfo);
    }

    @Override
    public boolean isPolymorph(ContextEl _context, StackCall _stack) {
        return metaInfo.isInstanceMethod();
    }
}
