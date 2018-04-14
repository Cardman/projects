package code.expressionlanguage.opers.util;
import code.expressionlanguage.Analyzable;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ArgumentsGroup extends CustList<ClassArgumentMatching> {

    private Analyzable context;

    private boolean ambigous;

    private StringMap<StringList> map;

    private String globalClass;

    public ArgumentsGroup(Analyzable _context, StringMap<StringList> _map, ClassArgumentMatching[] _elements) {
        super(_elements);
        context = _context;
        map = _map;
    }

    public Analyzable getContext() {
        return context;
    }

    public StringMap<StringList> getMap() {
        return map;
    }

    public boolean isAmbigous() {
        return ambigous;
    }

    public void setAmbigous(boolean _ambigous) {
        ambigous = _ambigous;
    }

    public String getGlobalClass() {
        return globalClass;
    }

    public void setGlobalClass(String _globalClass) {
        globalClass = _globalClass;
    }

    public ClassArgumentMatching[] getArgumentsArray() {
        int len_ = size();
        ClassArgumentMatching[] args_;
        args_ = new ClassArgumentMatching[len_];
        for (int i = FIRST_INDEX; i < len_; i++) {
            args_[i] = get(i);
        }
        return args_;
    }
}
