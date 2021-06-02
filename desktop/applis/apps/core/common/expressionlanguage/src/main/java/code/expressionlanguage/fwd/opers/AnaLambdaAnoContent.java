package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.MethodId;

public final class AnaLambdaAnoContent {
    private MethodId method;
    private int rootNumber = -1;

    public MethodId getMethod() {
        return method;
    }

    public void setMethod(MethodId _method) {
        this.method = _method;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int _rootNumber) {
        this.rootNumber = _rootNumber;
    }

}
