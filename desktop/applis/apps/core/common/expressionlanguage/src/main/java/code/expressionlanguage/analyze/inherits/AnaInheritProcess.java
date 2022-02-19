package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AbstractInheritProcess;
import code.expressionlanguage.common.AbstractTypePairHash;
import code.expressionlanguage.common.MappingPairs;
import code.util.StringList;
import code.util.StringMap;

public final class AnaInheritProcess extends AbstractInheritProcess {
    private final AnalyzedPageEl page;
    private final StringMap<StringList> mapping;

    public AnaInheritProcess(AnalyzedPageEl _page, StringMap<StringList> _mapping) {
        this.page = _page;
        this.mapping = _mapping;
    }

    @Override
    protected AbstractTypePairHash checker() {
        return page.getChecker();
    }

    @Override
    protected MappingPairs getExecutingCorrect(String _a, String _p) {
        return AnaInherits.getSimpleMapping(_a,_p,mapping,page);
    }
}
