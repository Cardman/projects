package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;

public final class AnaLambdaAnoContent {
    private ClassMethodId method;
    private int rootNumber = -1;

    public ClassMethodId getMethod() {
        return method;
    }

    public void setMethod(ClassMethodId method) {
        this.method = method;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int rootNumber) {
        this.rootNumber = rootNumber;
    }
}
