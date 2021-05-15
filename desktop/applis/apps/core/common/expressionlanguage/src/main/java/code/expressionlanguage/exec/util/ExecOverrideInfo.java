package code.expressionlanguage.exec.util;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class ExecOverrideInfo {
    private final ExecFormattedRootBlock className;
    private final ExecTypeFunction pair;

    public ExecOverrideInfo(ExecFormattedRootBlock _className, ExecTypeFunction _pair) {
        this.className = _className;
        this.pair = _pair;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

}
