package code.bean.nat.fwd.opers;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.analyze.opers.NatClassMethodIdReturn;

public final class NatAnaCallFctContent {

    private final String methodName;

    private SpecNatMethod standardMethod;

    public NatAnaCallFctContent(String _methodName) {
        this.methodName = _methodName;
    }

    public void update(NatClassMethodIdReturn _res) {
        standardMethod = _res.getStandardMethod();
    }

    public String getMethodName() {
        return methodName;
    }

    public SpecNatMethod getStandardMethod() {
        return standardMethod;
    }

}
