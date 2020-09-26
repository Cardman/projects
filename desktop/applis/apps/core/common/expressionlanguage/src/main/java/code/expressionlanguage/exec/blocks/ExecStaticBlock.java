package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecStaticBlock extends ExecInitBlock {
    public ExecStaticBlock(int _offsetTrim) {
        super(_offsetTrim);
    }
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.STATIC;
    }
}
