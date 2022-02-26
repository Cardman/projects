package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.common.FileMetricsCore;

public abstract class ExecAbstractFileBlock {

    private final FileMetricsCore metricsCore;

    private final String fileName;

    private final AbstractFileEscapedCalc fileEscapedCalc;

    protected ExecAbstractFileBlock(FileMetricsCore _metricsCore, String _fileName, AbstractFileEscapedCalc _fileEscapedCalc) {
        metricsCore = _metricsCore;
        fileName = _fileName;
        fileEscapedCalc = _fileEscapedCalc;
    }

    public AbstractFileEscapedCalc getFileEscapedCalc() {
        return fileEscapedCalc;
    }

    public FileMetrics getMetrics(int _tabWidth) {
        return new FileMetrics(metricsCore,_tabWidth);
    }

    public String getFileName() {
        return fileName;
    }

}
