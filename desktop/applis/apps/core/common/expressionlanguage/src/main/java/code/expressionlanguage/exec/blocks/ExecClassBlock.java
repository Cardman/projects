package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecClassContent;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecClassBlock extends ExecRootBlock implements ExecUniqueRootedBlock {

    private final ExecClassContent classContent;

    public ExecClassBlock(ExecRootBlockContent _rootBlockContent, AccessEnum _access, ExecClassContent _classContent) {
        super(_rootBlockContent, _access);
        classContent = _classContent;
    }

    public boolean isFinalType() {
        return classContent.isFinalType();
    }

    public boolean isAbstractType() {
        return classContent.isAbstractType();
    }

    @Override
    public boolean withoutInstance() {
        return classContent.isStaticType();
    }


}
