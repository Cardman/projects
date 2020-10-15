package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecOverrideInfo {
    private final String className;
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock overridableBlock;

    public ExecOverrideInfo(String _className, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _overridableBlock) {
        this.className = _className;
        this.rootBlock = _rootBlock;
        this.overridableBlock = _overridableBlock;
    }

    public String getClassName() {
        return className;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecNamedFunctionBlock getOverridableBlock() {
        return overridableBlock;
    }
}
