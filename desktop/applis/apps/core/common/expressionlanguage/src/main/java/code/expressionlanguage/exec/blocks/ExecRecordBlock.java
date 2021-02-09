package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecRecordBlock extends ExecRootBlock {

    private final boolean mutable;
    public ExecRecordBlock(boolean _mutable,int _offsetTrim, ExecRootBlockContent _rootBlockContent, AccessEnum _access) {
        super(_offsetTrim, _rootBlockContent, _access);
        mutable = _mutable;
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }

    public boolean isMutable() {
        return mutable;
    }
}
