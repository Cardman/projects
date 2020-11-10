package code.expressionlanguage.exec.util;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class ExecOverrideInfo {
    private final String className;
    private final ExecTypeFunction pair;

    public ExecOverrideInfo(String _className, ExecTypeFunction _pair) {
        this.className = _className;
        this.pair = _pair;
    }

    public String getClassName() {
        return className;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

}
