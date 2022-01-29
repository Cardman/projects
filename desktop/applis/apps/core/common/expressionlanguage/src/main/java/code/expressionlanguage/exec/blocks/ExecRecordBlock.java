package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecRecordBlock extends ExecRootBlock {

    private final boolean mutable;
    private final boolean staticType;
    public ExecRecordBlock(boolean _mutable, ExecRootBlockContent _rootBlockContent, AccessEnum _access, boolean _staticType) {
        super(_rootBlockContent, _access);
        mutable = _mutable;
        staticType = _staticType;
    }

    @Override
    public boolean withoutInstance() {
        return staticType;
    }

    public boolean isMutable() {
        return mutable;
    }
}
