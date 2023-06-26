package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecInstanceBlock extends ExecInitBlock {
    public ExecInstanceBlock(String _i,int _offsetTrim) {
        super(_i,_offsetTrim);
    }

    @Override
    protected MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
}
