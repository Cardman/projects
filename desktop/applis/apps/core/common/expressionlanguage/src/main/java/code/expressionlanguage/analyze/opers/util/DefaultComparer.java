package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;

public final class DefaultComparer implements AbstractComparer {
    @Override
    public boolean isCorrectOrNumbers(Mapping _m, AnalyzedPageEl _page) {
        return AnaTemplates.isCorrectOrNumbers(_m, _page);
    }
}
