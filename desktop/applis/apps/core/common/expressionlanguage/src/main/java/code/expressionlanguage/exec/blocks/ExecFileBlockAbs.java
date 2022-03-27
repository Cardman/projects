package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.expressionlanguage.common.FileMetricsCore;

public abstract class ExecFileBlockAbs {

    private final FileMetricsCore metricsCore;

    private final AbstractFileEscapedCalc fileEscapedCalc;

    protected ExecFileBlockAbs(FileMetricsCore _met, AbstractFileEscapedCalc _esc) {
        this.metricsCore = _met;
        this.fileEscapedCalc = _esc;
    }

    public FileMetricsCore getMetricsCore() {
        return metricsCore;
    }

    public AbstractFileEscapedCalc getFileEscapedCalc() {
        return fileEscapedCalc;
    }
}
