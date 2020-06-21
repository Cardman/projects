package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.AccessibleBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;


public final class OperatorAccessor implements ExecAccessingImportingBlock {

    @Override
    public boolean isTypeHidden(AccessibleBlock _class, ContextEl _analyzable) {
        return _class.getAccess() != AccessEnum.PUBLIC;
    }

}
