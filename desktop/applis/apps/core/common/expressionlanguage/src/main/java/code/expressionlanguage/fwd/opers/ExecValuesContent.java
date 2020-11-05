package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecValuesContent {
    private final int argOffset;
    private final ExecRootBlock rootBlock;
    public ExecValuesContent(AnaValuesContent _cont, Forwards _forwards) {
        argOffset = _cont.getArgOffset();
        rootBlock = FetchMemberUtil.fetchType(_cont.getNumberEnum(), _forwards);
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public int getArgOffset() {
        return argOffset;
    }
}
