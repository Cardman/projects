package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;

public class StdMethodPointBlockKey implements AbsKeyPoint{
    private final StandardNamedFunction id;
    private final StandardType type;
    private final String keySt;

    public StdMethodPointBlockKey(StandardNamedFunction _i, StandardType _t, String _k) {
        this.id = _i;
        this.type = _t;
        this.keySt = _k;
    }

    public CustList<String> names() {
        return id.getParametersNames();
    }
    public boolean match(StdMethodPointBlockKey _b) {
        return match(_b.id);
    }
    public boolean match(StandardNamedFunction _i) {
        return id == _i;
    }

    @Override
    public String keyStr() {
        return keySt;
    }

    public StandardType getType() {
        return type;
    }

    public StandardNamedFunction getId() {
        return id;
    }
}
