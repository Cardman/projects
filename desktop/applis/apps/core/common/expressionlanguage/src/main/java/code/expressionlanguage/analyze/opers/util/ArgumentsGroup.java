package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.util.StringList;
import code.util.StringMap;

public final class ArgumentsGroup {

    private final AnalyzedPageEl context;

    private final StringMap<StringList> map;

    private final AbstractComparer comparer;

    public ArgumentsGroup(AnalyzedPageEl _context, StringMap<StringList> _map, AbstractComparer _comparer) {
        context = _context;
        map = _map;
        comparer = _comparer;
    }

    public ArgumentsGroup(AnalyzedPageEl _context, StringMap<StringList> _map) {
        context = _context;
        map = _map;
        comparer = new DefaultComparer();
    }

    public AbstractComparer getComparer() {
        return comparer;
    }

    public AnalyzedPageEl getContext() {
        return context;
    }

    public StringMap<StringList> getMap() {
        return map;
    }


}
