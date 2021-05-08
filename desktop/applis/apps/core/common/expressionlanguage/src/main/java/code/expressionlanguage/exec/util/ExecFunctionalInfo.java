package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecFunctionalInfo {
    private final String className;
    private final ExecRootBlock overridableBlockParent;
    private final ExecOverridableBlock overridableBlock;

    public ExecFunctionalInfo(String _className, ExecRootBlock _overridableBlockParent,ExecOverridableBlock _overridableBlock) {
        this.className = _className;
        this.overridableBlockParent = _overridableBlockParent;
        this.overridableBlock = _overridableBlock;
    }

    public String getClassName() {
        return className;
    }

    public ExecRootBlock getOverridableBlockParent() {
        return overridableBlockParent;
    }

    public ExecOverridableBlock getOverridableBlock() {
        return overridableBlock;
    }
}
