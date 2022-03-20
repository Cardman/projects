package code.expressionlanguage.exec;

import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.util.AbsBasicMap;

public final class RootStatus extends AbsBasicMap<ExecRootBlock,InitClassState> {

    @Override
    protected boolean matchKeys(ExecRootBlock _one, ExecRootBlock _two) {
        return _one == _two;
    }
}
