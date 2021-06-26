package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public abstract class DefInitPreparer implements AbstractPreparer {
    private final MethodMetaInfo metaInfo;

    protected DefInitPreparer(MethodMetaInfo _metaInfo) {
        this.metaInfo = _metaInfo;
    }

    public MethodMetaInfo getMetaInfo() {
        return metaInfo;
    }

    @Override
    public boolean initType(ContextEl _context, StackCall _stack) {
        return initDel(_context, _stack, metaInfo);
    }

    static boolean initDel(ContextEl _context, StackCall _stack, MethodMetaInfo _metaInfo) {
        return _metaInfo.isWideStatic() && _context.getExiting().hasToExit(_stack, _metaInfo.getFormatted().getRootBlock());
    }

    @Override
    public boolean isPolymorph(ContextEl _context, StackCall _stack) {
        return false;
    }
}
