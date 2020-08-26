package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;

public final class ExecOverrideInfo {
    private final String className;
    private final ExecNamedFunctionBlock overridableBlock;

    public ExecOverrideInfo(String className, ExecNamedFunctionBlock overridableBlock) {
        this.className = className;
        this.overridableBlock = overridableBlock;
    }

    public String getClassName() {
        return className;
    }

    public ExecNamedFunctionBlock getOverridableBlock() {
        return overridableBlock;
    }
}
