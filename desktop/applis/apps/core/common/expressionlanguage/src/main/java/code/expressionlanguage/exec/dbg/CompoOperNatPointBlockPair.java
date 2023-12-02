package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class CompoOperNatPointBlockPair extends AbsOperNatPointBlockPair {

    private final CompOperNatPoint value;

    public CompoOperNatPointBlockPair(String _k, String _symbol, AbstractInterceptorStdCaller _v, boolean _enabled, String _f, String _s) {
        super(_k, _symbol, _f, _s);
        this.value = new CompOperNatPoint(_v, this, getOn());
        this.value.setEnabled(_enabled);
    }

    @Override
    public void resetCount() {
        getValue().resetCount();
    }

    public CompOperNatPoint getValue() {
        return value;
    }
}
