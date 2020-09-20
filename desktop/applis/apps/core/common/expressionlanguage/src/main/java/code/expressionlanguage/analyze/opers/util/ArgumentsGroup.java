package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.util.StringList;
import code.util.StringMap;

public final class ArgumentsGroup {

    private AnalyzedPageEl context;

    private StringMap<StringList> map;


    public ArgumentsGroup(AnalyzedPageEl _context, StringMap<StringList> _map) {
        context = _context;
        map = _map;
    }

    public AnalyzedPageEl getContext() {
        return context;
    }

    public StringMap<StringList> getMap() {
        return map;
    }


}
