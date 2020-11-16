package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;

public interface AccessingImportingBlock {
    boolean isTypeHidden(Accessed _class, AnalyzedPageEl _analyzable);

}
