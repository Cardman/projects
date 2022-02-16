package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.common.FileMetricsCore;

public abstract class ExecAbstractFileBlock {

    private final FileMetricsCore metricsCore;

    private final String fileName;

    protected ExecAbstractFileBlock(FileMetricsCore _metricsCore, String _fileName) {
        metricsCore = _metricsCore;
        fileName = _fileName;
    }

    public FileMetrics getMetrics(int _tabWidth) {
        return new FileMetrics(metricsCore,_tabWidth);
    }

    public String getFileName() {
        return fileName;
    }

    public abstract int realIndex(int _index);

}
