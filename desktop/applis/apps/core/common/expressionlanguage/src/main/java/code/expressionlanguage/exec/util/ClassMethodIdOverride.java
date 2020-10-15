package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.util.StringMap;

public class ClassMethodIdOverride {
    private final ExecNamedFunctionBlock analyzedMethod;
    private StringMap<ExecOverrideInfo> redirections = new StringMap<ExecOverrideInfo>();

    public ClassMethodIdOverride(ExecNamedFunctionBlock _analyzedMethod) {
        this.analyzedMethod = _analyzedMethod;
    }

    public ExecNamedFunctionBlock getAnalyzedMethod() {
        return analyzedMethod;
    }

    public ExecOverrideInfo getVal(String _cl) {
        return redirections.getVal(_cl);
    }

    public void put(String _cl, String _geneString, ExecRootBlock _root, ExecNamedFunctionBlock _named) {
        redirections.put(_cl, new ExecOverrideInfo(_geneString, _root, _named));
    }
}
