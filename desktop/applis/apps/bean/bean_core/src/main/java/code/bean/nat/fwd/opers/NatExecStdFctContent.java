package code.bean.nat.fwd.opers;

import code.bean.nat.SpecNatMethod;

public final class NatExecStdFctContent {

    private final SpecNatMethod standardMethod;

    private final String foundClass;
    public NatExecStdFctContent(NatAnaCallFctContent _cont) {
        standardMethod = _cont.getStandardMethod();
        foundClass = _cont.getFoundClass();
    }

    public SpecNatMethod getStandardMethod() {
        return standardMethod;
    }

    public String getFoundClass() {
        return foundClass;
    }
}
