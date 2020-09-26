package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecInterfaceBlock extends ExecRootBlock implements GeneInterface,ExecInterfacable {

    private final boolean staticType;
    public ExecInterfaceBlock(int _offsetTrim, ExecRootBlockContent _rootBlockContent, AccessEnum _access, boolean _staticType) {
        super(_offsetTrim, _rootBlockContent, _access);
        staticType = _staticType;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }

}
