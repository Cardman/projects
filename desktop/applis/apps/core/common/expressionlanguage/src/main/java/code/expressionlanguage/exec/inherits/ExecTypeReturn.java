package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecTypeReturn {
    private final ExecRootBlock rootBlock;
    private final String returnType;

    public ExecTypeReturn(ExecRootBlock _rootBlock, String _returnType) {
        this.rootBlock = _rootBlock;
        this.returnType = _returnType;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public String getReturnType() {
        return returnType;
    }
}
