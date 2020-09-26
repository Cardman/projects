package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecEnumBlock extends ExecRootBlock implements ExecUniqueRootedBlock {

    public ExecEnumBlock(int _offsetTrim, ExecRootBlockContent _rootBlockContent, AccessEnum _access) {
        super(_offsetTrim, _rootBlockContent, _access);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }

}
