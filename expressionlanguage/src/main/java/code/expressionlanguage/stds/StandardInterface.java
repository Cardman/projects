package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class StandardInterface extends StandardType implements GeneInterface {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList superInterfaces;

    private final StringList allInterfaces = new StringList();
    public StandardInterface(String _name,
            ObjectMap<MethodId, StandardMethod> _methods,
            StringList _superInterfaces) {
        super(_name, new StringMap<StandardField>(), new CustList<StandardConstructor>(), _methods);
        superInterfaces = _superInterfaces;
    }

    public StringList getDirectSuperClasses() {
        return getDirectInterfaces();
    }

    @Override
    public StringList getDirectInterfaces() {
        return new StringList(superInterfaces);
    }

    @Override
    public StringList getDirectSuperTypes() {
        return getDirectInterfaces();
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }

    @Override
    public StringList getAllGenericSuperTypes() {
        return allSuperTypes;
    }
    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public StringList getAllInterfaces() {
        return allInterfaces;
    }

    public StringList getDirectSuperClasses(Analyzable _classes) {
        StringList classes_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            classes_.add(s);
        }
        if (getDirectSuperTypes().isEmpty()) {
            classes_.add(_classes.getStandards().getAliasObject());
        }
        return classes_;
    }

    @Override
    public boolean isFinalType() {
        return false;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

}
