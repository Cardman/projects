package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecInstanceBlock extends ExecInitBlock {
    public ExecInstanceBlock(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    protected MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
}
