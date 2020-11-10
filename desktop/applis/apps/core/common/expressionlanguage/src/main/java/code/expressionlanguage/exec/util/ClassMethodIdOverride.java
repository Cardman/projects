package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.StringMap;

public final class ClassMethodIdOverride {
    private final ExecNamedFunctionBlock analyzedMethod;
    private final StringMap<ExecOverrideInfo> redirections = new StringMap<ExecOverrideInfo>();

    public ClassMethodIdOverride(ExecNamedFunctionBlock _analyzedMethod) {
        this.analyzedMethod = _analyzedMethod;
    }

    public ExecNamedFunctionBlock getAnalyzedMethod() {
        return analyzedMethod;
    }

    public ExecOverrideInfo getVal(String _cl) {
        return redirections.getVal(_cl);
    }

    public void put(String _cl, String _geneString, ExecTypeFunction _pair) {
        redirections.put(_cl, new ExecOverrideInfo(_geneString, _pair));
    }
}
