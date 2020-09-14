package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.blocks.AccessibleBlock;
import code.expressionlanguage.analyze.blocks.ExecAccessingImportingBlock;


public final class OperatorAccessor implements ExecAccessingImportingBlock {

    @Override
    public boolean isTypeHidden(AccessibleBlock _class, AnalyzedPageEl _analyzable) {
        return _class.getAccess() != AccessEnum.PUBLIC;
    }

}
