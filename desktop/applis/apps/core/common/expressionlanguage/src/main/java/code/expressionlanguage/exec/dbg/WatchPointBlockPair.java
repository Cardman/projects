package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class WatchPointBlockPair {
    private final WatchPointBlockKey wp;
    private final RootBlock root;
    private final WatchPoint value;

    public WatchPointBlockPair(boolean _trField, RootBlock _rBlock, int _nb, String _field, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.wp = new WatchPointBlockKey(_trField, _nb, _field);
        this.root = _rBlock;
        this.value = new WatchPoint(_v,wp);
        this.value.setEnabled(_enabled);
    }

    public WatchPointBlockKey getWp() {
        return wp;
    }

    public RootBlock getRoot() {
        return root;
    }

    public WatchPoint getValue() {
        return value;
    }
}
