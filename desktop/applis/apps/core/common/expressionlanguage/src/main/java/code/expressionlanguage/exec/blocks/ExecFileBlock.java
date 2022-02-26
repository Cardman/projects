package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.StringMap;

public final class ExecFileBlock extends ExecAbstractFileBlock {

    public ExecFileBlock(FileMetricsCore _metricsCore, String _fileName, AbstractFileEscapedCalc _fileEscapedCalc) {
        super(_metricsCore, _fileName,_fileEscapedCalc);
    }

    public static StringMap<String> export(ContextEl _cont) {
        return LinkageUtil.export(_cont);
    }

}
