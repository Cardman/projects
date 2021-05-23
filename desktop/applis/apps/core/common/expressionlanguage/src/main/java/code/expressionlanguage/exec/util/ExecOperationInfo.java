package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.variables.ArgumentsPair;

public final class ExecOperationInfo {
    private final boolean filter;
    private final boolean wrapper;
    private final int index;
    private final ArgumentsPair pair;

    public ExecOperationInfo(boolean _filter, boolean _wrapper, int _index, ArgumentsPair _pair) {
        this.filter = _filter;
        this.wrapper = _wrapper;
        this.index = _index;
        this.pair = _pair;
    }

    public ArgumentsPair getPair() {
        return pair;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFilter() {
        return filter;
    }

    public boolean isWrapper() {
        return wrapper;
    }

}
