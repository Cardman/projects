package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class BreakPointBlockPair implements AbsPairPoint {
    private final BreakPointBlockKey bp;
    private final BreakPoint value;

    public BreakPointBlockPair(ExecFileBlock _file, int _nf, int _offset, AbstractInterceptorStdCaller _v, boolean _enabled) {
        bp = new BreakPointBlockKey(_file, _nf, _offset);
        this.value = new BreakPoint(_v,bp);
        this.value.setEnabled(_enabled);
    }

    @Override
    public String keyStr() {
        return getBp().keyStr();
    }

    public BreakPointBlockKey getBp() {
        return bp;
    }

    public BreakPoint getValue() {
        return value;
    }
}
