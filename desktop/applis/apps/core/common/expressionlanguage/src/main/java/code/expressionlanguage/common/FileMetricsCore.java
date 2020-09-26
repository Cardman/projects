package code.expressionlanguage.common;

import code.util.Ints;

public final class FileMetricsCore {

    private final Ints lineReturns;
    private final Ints tabulations;


    public FileMetricsCore(Ints lineReturns, Ints tabulations) {
        this.lineReturns = lineReturns;
        this.tabulations = tabulations;
    }

    public Ints getLineReturns() {
        return lineReturns;
    }

    public Ints getTabulations() {
        return tabulations;
    }
}
