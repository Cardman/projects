package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecOverrideInfo {
    private final String className;
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock overridableBlock;

    public ExecOverrideInfo(String className, ExecRootBlock rootBlock, ExecNamedFunctionBlock overridableBlock) {
        this.className = className;
        this.rootBlock = rootBlock;
        this.overridableBlock = overridableBlock;
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
