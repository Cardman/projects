package code.expressionlanguage.opers.util;
import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ArgumentsGroup extends CustList<ClassArgumentMatching> {

    private Classes classes;

    private boolean ambigous;

    private StringMap<StringList> map;

    private String globalClass;

    public ArgumentsGroup(Classes _classes, StringMap<StringList> _map, ClassArgumentMatching[] _elements) {
        super(_elements);
        classes = _classes;
        map = _map;
    }

    public StringMap<StringList> getMap() {
        return map;
    }

    public Classes getClasses() {
        return classes;
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
