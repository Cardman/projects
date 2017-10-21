package code.expressionlanguage.opers.util;
import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ArgumentsGroup extends CustList<ClassArgumentMatching> {

    private Classes classes;

    private boolean ambigous;

    private StringMap<StringList> map;

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
}
