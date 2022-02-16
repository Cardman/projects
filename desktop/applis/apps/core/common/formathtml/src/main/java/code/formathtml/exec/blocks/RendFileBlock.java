package code.formathtml.exec.blocks;

import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.exec.blocks.ExecAbstractFileBlock;
import code.formathtml.common.RendBlockUtil;
import code.util.IntTreeMap;

public final class RendFileBlock extends ExecAbstractFileBlock {

    private final IntTreeMap<Integer> escapedChars;
    public RendFileBlock(FileMetricsCore _metricsCore, String _fileName, IntTreeMap<Integer> _esc) {
        super(_metricsCore, _fileName);
        escapedChars = _esc;
    }

    @Override
    public int realIndex(int _index) {
        return RendBlockUtil.retrieve(_index,escapedChars);
    }
}
