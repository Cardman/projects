package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.util.CustList;

public final class ClassMethodIdOverrides {
    private final ExecRootBlock root;

    private final CustList<ClassMethodIdOverride> overrides = new CustList<ClassMethodIdOverride>();
    public ClassMethodIdOverrides(ExecRootBlock _r) {
        root = _r;
    }
    public void add(ClassMethodIdOverride _override) {
        overrides.add(_override);
    }

    public ExecOverrideInfo getVal(int _analyzed, String _type) {
        for (ClassMethodIdOverride c: overrides) {
            if (_analyzed == c.getAnalyzedMethod()) {
                return c.getVal(_type);
            }
        }
        return null;
    }

    public ExecRootBlock getRoot() {
        return root;
    }
}
