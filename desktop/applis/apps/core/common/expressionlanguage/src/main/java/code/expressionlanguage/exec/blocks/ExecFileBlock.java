package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.StringMap;

public final class ExecFileBlock extends ExecFileBlockAbs {

    private final String fileName;

    public ExecFileBlock(FileMetricsCore _metricsCore, String _fileName, AbstractFileEscapedCalc _fileEscapedCalc) {
        super(_metricsCore,_fileEscapedCalc);
        fileName = _fileName;
    }

    public String getFileName() {
        return fileName;
    }
    public static StringMap<String> export(ContextEl _cont) {
        return LinkageUtil.export(_cont);
    }

}
