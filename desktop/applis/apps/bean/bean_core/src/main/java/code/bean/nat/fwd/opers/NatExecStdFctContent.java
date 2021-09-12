package code.bean.nat.fwd.opers;

import code.bean.nat.SpecNatMethod;

public final class NatExecStdFctContent {

    private final String methodName;

    private final SpecNatMethod standardMethod;

    private final String foundClass;
    public NatExecStdFctContent(NatAnaCallFctContent _cont) {
        methodName = _cont.getMethodName();
        standardMethod = _cont.getStandardMethod();
        foundClass = _cont.getFoundClass();
    }

    public String getMethodName() {
        return methodName;
    }

    public SpecNatMethod getStandardMethod() {
        return standardMethod;
    }

    public String getFoundClass() {
        return foundClass;
    }
}
