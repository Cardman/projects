package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.StringList;

public final class ExecAnnotationBlock extends ExecRootBlock implements ExecInterfacable {

    public ExecAnnotationBlock(RootBlock _offset) {
        super(_offset);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }


}
