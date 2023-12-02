package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class OperNatPointBlockPair extends AbsOperNatPointBlockPair {

    private final OperNatPoint value;

    public OperNatPointBlockPair(String _k, String _symbol, AbstractInterceptorStdCaller _v, boolean _enabled, String _f, String _s) {
        super(_k, _symbol, _f, _s);
        this.value = new OperNatPoint(_v, this, getOn());
        this.value.setEnabled(_enabled);
    }

    @Override
    public void resetCount() {
        getValue().resetCount();
    }

    public OperNatPoint getValue() {
        return value;
    }
}
