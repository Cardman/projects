package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;

public final class AllAccessedTypes implements AccessingImportingBlock {
    @Override
    public boolean isTypeHidden(Accessed _class) {
        return false;
    }
}
