package code.expressionlanguage.opers.util;
import code.expressionlanguage.Analyzable;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ArgumentsGroup {

    private Analyzable context;

    private StringMap<StringList> map;


    public ArgumentsGroup(Analyzable _context, StringMap<StringList> _map) {
        context = _context;
        map = _map;
    }

    public Analyzable getContext() {
        return context;
    }

    public StringMap<StringList> getMap() {
        return map;
    }


}
