package code.expressionlanguage.functionid;

import code.util.StringMap;

public class ClassMethodIdOverride {
    private final ClassMethodId analyzedMethod;
    private StringMap<ClassMethodId> redirections = new StringMap<ClassMethodId>();

    public ClassMethodIdOverride(ClassMethodId analyzedMethod) {
        this.analyzedMethod = analyzedMethod;
    }

    public ClassMethodId getAnalyzedMethod() {
        return analyzedMethod;
    }

    public ClassMethodId getVal(String _cl) {
        return redirections.getVal(_cl);
    }
    public void put(String _cl, ClassMethodId _dest) {
        redirections.put(_cl,_dest);
    }
}
