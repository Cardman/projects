package code.expressionlanguage.functionid;

import code.util.CustList;

public class ClassMethodIdOverrides {
    private CustList<ClassMethodIdOverride> overrides = new CustList<ClassMethodIdOverride>();
    public void add(ClassMethodIdOverride _override) {
        overrides.add(_override);
    }
    public ClassMethodId getVal(ClassMethodId _analyzed, String _type) {
        for (ClassMethodIdOverride c: overrides) {
            if (_analyzed.eq(c.getAnalyzedMethod())) {
                return c.getVal(_type);
            }
        }
        return null;
    }
}
