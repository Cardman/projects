package code.expressionlanguage.functionid;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.util.CustList;

public class ClassMethodIdOverrides {
    private CustList<ClassMethodIdOverride> overrides = new CustList<ClassMethodIdOverride>();
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
}
