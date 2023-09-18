package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class OperNatPointBlockPair implements AbsPairPoint {
    private final OperNatPointBlockKey on;
    private final OperNatPoint value;

    public OperNatPointBlockPair(String _k, AbstractInterceptorStdCaller _v, boolean _enabled, String _f, String _s) {
        this.on = new OperNatPointBlockKey(_k, _f, _s);
        this.value = new OperNatPoint(_v, on);
        this.value.setEnabled(_enabled);
    }

    @Override
    public String keyStr() {
        return getOn().keyStr();
    }

    public OperNatPointBlockKey getOn() {
        return on;
    }

    public OperNatPoint getValue() {
        return value;
    }
}
