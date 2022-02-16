package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.StringMap;

public final class ExecFileBlock extends ExecAbstractFileBlock {

    public ExecFileBlock(FileMetricsCore _metricsCore, String _fileName) {
        super(_metricsCore, _fileName);
    }

    @Override
    public int realIndex(int _index) {
        return _index;
    }

    public static StringMap<String> export(ContextEl _cont) {
        return LinkageUtil.export(_cont);
    }

}
