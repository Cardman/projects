package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecInterfaceBlock extends ExecRootBlock implements GeneType {

    private final boolean staticType;
    public ExecInterfaceBlock(ExecRootBlockContent _rootBlockContent, AccessEnum _access, boolean _staticType) {
        super(_rootBlockContent, _access);
        staticType = _staticType;
    }

    @Override
    public boolean withoutInstance() {
        return staticType;
    }

}
