package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecAnnotationBlock extends ExecRootBlock implements ExecInterfacable {

    public ExecAnnotationBlock(int _offsetTrim, ExecRootBlockContent _rootBlockContent, AccessEnum _access) {
        super(_offsetTrim, _rootBlockContent, _access);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }


}
