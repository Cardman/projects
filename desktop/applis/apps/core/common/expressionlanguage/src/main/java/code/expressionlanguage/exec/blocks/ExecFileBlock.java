package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.StringMap;

public final class ExecFileBlock {

    private final FileMetricsCore metricsCore;

    private final String fileName;

    private final AbstractFileEscapedCalc fileEscapedCalc;
    public ExecFileBlock(FileMetricsCore _metricsCore, String _fileName, AbstractFileEscapedCalc _fileEscapedCalc) {
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
    public static StringMap<String> export(ContextEl _cont) {
        return LinkageUtil.export(_cont);
    }

}
