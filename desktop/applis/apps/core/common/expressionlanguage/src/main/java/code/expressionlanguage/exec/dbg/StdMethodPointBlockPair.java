package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;

public final class StdMethodPointBlockPair {
    private final StandardNamedFunction id;
    private final StandardType type;
    private final String keySt;
    private final MethodPoint value;

    public StdMethodPointBlockPair(StandardNamedFunction _i, StandardType _t, String _k, MethodPoint _v) {
        this.id = _i;
        this.type = _t;
        this.keySt = _k;
        this.value = _v;
    }

    public CustList<String> names() {
        return id.getParametersNames();
    }
    public boolean match(StdMethodPointBlockPair _b) {
        return match(_b.id);
    }
    public boolean match(StandardNamedFunction _i) {
        return id == _i;
    }

    public String keyStr() {
        return keySt;
    }

    public StandardType getType() {
        return type;
    }

    public StandardNamedFunction getId() {
        return id;
    }

    public MethodPoint getValue() {
        return value;
    }
}
