package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.StringList;
import code.util.StringMap;

public final class ArgumentsGroup {

    private ContextEl context;

    private StringMap<StringList> map;


    public ArgumentsGroup(ContextEl _context, StringMap<StringList> _map) {
        context = _context;
        map = _map;
    }

    public ContextEl getContext() {
        return context;
    }

    public StringMap<StringList> getMap() {
        return map;
    }


}
