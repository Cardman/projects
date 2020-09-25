package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.fwd.blocks.ForwardInfos;

public final class ExecValuesContent {
    private final int argOffset;
    private final ExecRootBlock rootBlock;
    public ExecValuesContent(AnaValuesContent _cont, AnalyzedPageEl _page) {
        argOffset = _cont.getArgOffset();
        rootBlock = ForwardInfos.fetchType(_cont.getNumberEnum(), _page);
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public int getArgOffset() {
        return argOffset;
    }
}
