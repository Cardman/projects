package code.expressionlanguage.exec.dbg;

import code.util.core.StringUtil;

public final class OperNatPointBlockKey implements AbsKeyPoint{
    private final String keySt;
    private final String first;
    private final String second;

    public OperNatPointBlockKey(String _k, String _f, String _s) {
        this.keySt = _k;
        this.first = _f;
        this.second = _s;
    }

    public boolean match(OperNatPointBlockKey _b) {
        return match(_b.keySt);
    }
    public boolean match(String _i) {
        return StringUtil.quickEq(keySt,_i);
    }

    @Override
    public String keyStr() {
        return keySt;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
