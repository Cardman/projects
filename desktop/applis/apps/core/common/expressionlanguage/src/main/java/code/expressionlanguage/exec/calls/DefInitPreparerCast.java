package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DefInitPreparerCast extends DefPreparerNoAbs {
    private final MethodMetaInfo metaInfo;

    protected DefInitPreparerCast(MethodMetaInfo _metaInfo) {
        this.metaInfo = _metaInfo;
    }

    @Override
    public boolean initType(ContextEl _context, StackCall _stack) {
        return _context.getExiting().hasToExit(_stack, metaInfo.getFormatted().getRootBlock());
    }

    @Override
    public boolean isPolymorph(ContextEl _context, StackCall _stack) {
        return false;
    }
}
