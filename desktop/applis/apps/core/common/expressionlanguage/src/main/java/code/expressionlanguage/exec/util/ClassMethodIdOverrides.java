package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ClassMethodIdOverrides {
    private final CustList<ClassMethodIdOverride> overrides = new CustList<ClassMethodIdOverride>();
    public void add(ClassMethodIdOverride _override) {
        overrides.add(_override);
    }

    public ExecOverrideInfo getVal(ExecNamedFunctionBlock _analyzed, String _type) {
        for (ClassMethodIdOverride c: overrides) {
            if (_analyzed == c.getAnalyzedMethod()) {
                return c.getVal(_type);
            }
        }
        return null;
    }

    public ExecOverrideInfo getVal(String _analyzed, String _type) {
        for (ClassMethodIdOverride c: overrides) {
            if (StringUtil.quickEq(_analyzed,c.getAnalyzedMethod().id())) {
                return c.getVal(_type);
            }
        }
        return null;
    }
}
