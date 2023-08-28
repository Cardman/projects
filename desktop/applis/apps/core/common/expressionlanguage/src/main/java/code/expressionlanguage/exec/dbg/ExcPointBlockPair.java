package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ExcPointBlockPair {
    private final ExcPointBlockKey ep;
    private final ExcPoint value;

    public ExcPointBlockPair(boolean _ex, String _cl, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.ep = new ExcPointBlockKey(_ex, _cl);
        this.value = new ExcPoint(_v, ep);
        this.value.setEnabled(_enabled);
    }

    public ExcPointBlockKey getEp() {
        return ep;
    }

    public ExcPoint getValue() {
        return value;
    }
}
