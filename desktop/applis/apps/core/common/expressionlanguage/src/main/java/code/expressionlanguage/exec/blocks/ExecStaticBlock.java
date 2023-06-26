package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecStaticBlock extends ExecInitBlock {
    public ExecStaticBlock(String _i,int _offsetTrim) {
        super(_i,_offsetTrim);
    }
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.STATIC;
    }
}
