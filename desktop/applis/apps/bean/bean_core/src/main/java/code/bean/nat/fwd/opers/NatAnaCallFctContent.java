package code.bean.nat.fwd.opers;

import code.bean.nat.analyze.opers.NatClassMethodIdReturn;
import code.expressionlanguage.stds.StandardMethod;

public final class NatAnaCallFctContent {

    private final String methodName;

    private StandardMethod standardMethod;
    private String foundClass = "";

    public NatAnaCallFctContent(String _methodName) {
        this.methodName = _methodName;
    }

    public void update(NatClassMethodIdReturn _res) {
        foundClass = _res.getRealClass();
        standardMethod = _res.getStandardMethod();
    }

    public String getMethodName() {
        return methodName;
    }

    public String getFoundClass() {
        return foundClass;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

}
