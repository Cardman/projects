package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.StringList;

public final class ExecInterfaceBlock extends ExecRootBlock implements GeneInterface,ExecInterfacable {

    private final boolean staticType;
    public ExecInterfaceBlock(RootBlock _offset) {
        super(_offset);
        staticType = _offset.isStaticType();
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }

}
