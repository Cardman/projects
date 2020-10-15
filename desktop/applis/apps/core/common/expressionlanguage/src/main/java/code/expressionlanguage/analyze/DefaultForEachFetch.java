package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.util.StringList;

public final class DefaultForEachFetch implements AbstractForEachFetch {
    private final AnalyzedPageEl page;

    public DefaultForEachFetch(AnalyzedPageEl _page) {
        this.page = _page;
    }

    @Override
    public IterableAnalysisResult getCustomType(StringList _names, String _first) {
        return ContextUtil.getCustomTypeBase(_names, page);
    }

    @Override
    public IterableAnalysisResult getCustomTableType(StringList _names, String _first, String _second) {
        return ContextUtil.getCustomTableType(_names,page);
    }
}
