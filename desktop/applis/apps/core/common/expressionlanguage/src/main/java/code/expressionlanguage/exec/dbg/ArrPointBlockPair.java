package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ArrPointBlockPair implements AbsPairPoint {
    private final ExcPointBlockKey ep;
    private final ArrPoint value;

    public ArrPointBlockPair(boolean _ex, String _cl, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.ep = new ExcPointBlockKey(_ex, _cl);
        this.value = new ArrPoint(_v, ep);
        this.value.setEnabled(_enabled);
    }

    @Override
    public String keyStr() {
        return getEp().keyStr();
    }

    public ExcPointBlockKey getEp() {
        return ep;
    }

    public ArrPoint getValue() {
        return value;
    }
}
