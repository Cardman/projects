package code.expressionlanguage.opers.util;
import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.ints.Listable;

public final class ArgumentsGroup extends CustList<ClassArgumentMatching> {

    private Classes classes;

    private boolean ambigous;

    public ArgumentsGroup(Classes _classes, ClassArgumentMatching[] _elements) {
        super(_elements);
        classes = _classes;
    }

    public ArgumentsGroup(Classes _classes, Listable<ClassArgumentMatching> _c) {
        super(_c);
        classes = _classes;
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
