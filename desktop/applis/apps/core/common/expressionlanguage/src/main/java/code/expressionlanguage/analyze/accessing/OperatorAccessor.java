package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;


public final class OperatorAccessor implements AccessingImportingBlock {

    @Override
    public boolean isTypeHidden(AnalyzedPageEl _page, Accessed _class) {
        if (_page.isDynamic()) {
            return false;
        }
        return _class.getAccess() != AccessEnum.PUBLIC;
    }

}
