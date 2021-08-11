package code.bean.nat.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;

public final class NatExecStdFctContent {

    private final String methodName;

    private final ClassMethodId classMethodId;

    public NatExecStdFctContent(NatAnaCallFctContent _cont) {
        methodName = _cont.getMethodName();
        classMethodId = _cont.getClassMethodId();
    }

    public String getMethodName() {
        return methodName;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }
}
