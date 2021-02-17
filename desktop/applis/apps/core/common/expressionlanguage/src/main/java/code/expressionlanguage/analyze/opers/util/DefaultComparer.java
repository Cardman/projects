package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;

public final class DefaultComparer implements AbstractComparer {
    @Override
    public boolean isCorrectOrNumbers(Mapping _m, AnalyzedPageEl _page) {
        return AnaInherits.isCorrectOrNumbers(_m, _page);
    }
}
