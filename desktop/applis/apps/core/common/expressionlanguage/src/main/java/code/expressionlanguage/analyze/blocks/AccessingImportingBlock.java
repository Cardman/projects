package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;

public interface AccessingImportingBlock {
    boolean isTypeHidden(AccessibleBlock _class, AnalyzedPageEl _analyzable);

}
