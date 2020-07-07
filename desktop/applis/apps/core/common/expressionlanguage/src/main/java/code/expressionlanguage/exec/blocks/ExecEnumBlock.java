package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.StringList;

public final class ExecEnumBlock extends ExecRootBlock implements ExecUniqueRootedBlock {

    public ExecEnumBlock(RootBlock _offset) {
        super(_offset);
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public boolean isStaticType() {
        return true;
    }

}
