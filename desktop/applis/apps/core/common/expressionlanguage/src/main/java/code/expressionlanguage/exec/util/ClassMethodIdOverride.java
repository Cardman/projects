package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.StringMap;

public final class ClassMethodIdOverride {
    private final ExecOverridableBlock analyzedMethod;
    private final StringMap<ExecOverrideInfo> redirections = new StringMap<ExecOverrideInfo>();

    public ClassMethodIdOverride(ExecOverridableBlock _analyzedMethod) {
        this.analyzedMethod = _analyzedMethod;
    }

    public ExecOverridableBlock getAnalyzedMethod() {
        return analyzedMethod;
    }

    public ExecOverrideInfo getVal(String _cl) {
        return redirections.getVal(_cl);
    }

    public void put(String _cl, ExecFormattedRootBlock _geneString, ExecTypeFunction _pair) {
        redirections.put(_cl, new ExecOverrideInfo(_geneString, _pair));
    }
}
