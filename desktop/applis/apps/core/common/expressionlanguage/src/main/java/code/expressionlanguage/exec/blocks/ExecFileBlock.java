package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;

public final class ExecFileBlock extends ExecBracedBlock {

    private final FileMetricsCore metricsCore;

    private final String fileName;

    public ExecFileBlock(int _offsetTrim, FileMetricsCore _metricsCore, String _fileName) {
        super(_offsetTrim);
        metricsCore = _metricsCore;
        fileName = _fileName;
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
