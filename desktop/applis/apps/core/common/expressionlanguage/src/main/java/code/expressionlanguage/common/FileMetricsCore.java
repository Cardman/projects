package code.expressionlanguage.common;

import code.util.Ints;

public final class FileMetricsCore {

    private final Ints lineReturns;
    private final Ints tabulations;


    public FileMetricsCore(Ints _liRet, Ints _tabs) {
        this.lineReturns = _liRet;
        this.tabulations = _tabs;
    }

    public Ints getLineReturns() {
        return lineReturns;
    }

    public Ints getTabulations() {
        return tabulations;
    }
}
