package code.bean.nat.fwd.opers;

import code.bean.nat.SpecNatMethod;

public final class NatExecStdFctContent {

    private final SpecNatMethod standardMethod;

    public NatExecStdFctContent(NatAnaCallFctContent _cont) {
        standardMethod = _cont.getStandardMethod();
    }

    public SpecNatMethod getStandardMethod() {
        return standardMethod;
    }

}
