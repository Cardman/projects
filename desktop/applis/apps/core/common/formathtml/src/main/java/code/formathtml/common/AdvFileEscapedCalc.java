package code.formathtml.common;

import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.util.IntTreeMap;

public final class AdvFileEscapedCalc implements AbstractFileEscapedCalc {
    private final IntTreeMap<Integer> escaped;

    public AdvFileEscapedCalc(IntTreeMap<Integer> _esc) {
        this.escaped = _esc;
    }

    public IntTreeMap<Integer> getEscaped() {
        return escaped;
    }

    @Override
    public int realIndex(int _index) {
        return RendBlockUtil.retrieve(_index,escaped);
    }
}
