package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;

public final class AllAccessedTypes implements AccessingImportingBlock {
    @Override
    public boolean isTypeHidden(AnalyzedPageEl _page, Accessed _class) {
        return false;
    }
}
