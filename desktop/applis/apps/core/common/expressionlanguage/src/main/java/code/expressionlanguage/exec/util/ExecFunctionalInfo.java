package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecOverridableBlock;

public final class ExecFunctionalInfo {
    private final String className;
    private final ExecOverridableBlock overridableBlock;

    public ExecFunctionalInfo(String className, ExecOverridableBlock overridableBlock) {
        this.className = className;
        this.overridableBlock = overridableBlock;
    }

    public String getClassName() {
        return className;
    }

    public ExecOverridableBlock getOverridableBlock() {
        return overridableBlock;
    }
}
