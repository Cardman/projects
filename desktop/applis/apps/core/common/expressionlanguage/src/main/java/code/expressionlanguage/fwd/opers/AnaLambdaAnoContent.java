package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;

public final class AnaLambdaAnoContent {
    private ClassMethodId method;
    private int rootNumber = -1;

    public ClassMethodId getMethod() {
        return method;
    }

    public void setMethod(ClassMethodId _method) {
        this.method = _method;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int _rootNumber) {
        this.rootNumber = _rootNumber;
    }
}
