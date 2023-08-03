package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;


public final class OperatorAccessor implements AccessingImportingBlock {

    @Override
    public boolean isTypeHidden(Accessed _class) {
        return _class.getAccess() != AccessEnum.PUBLIC;
    }

}
