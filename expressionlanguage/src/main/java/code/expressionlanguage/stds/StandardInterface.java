package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class StandardInterface extends StandardType implements GeneInterface {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList superInterfaces;

    protected StandardInterface(String _name,
            ObjectMap<MethodId, StandardMethod> _methods,
            StringList _superInterfaces) {
        super(_name, new StringMap<StandardField>(), new CustList<StandardConstructor>(), _methods);
        superInterfaces = _superInterfaces;
    }

    public StringList getSuperInterfaces() {
        return superInterfaces;
    }

    @Override
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
    public boolean mustImplement() {
        return false;
    }

    @Override
    public StringList getAllGenericSuperClasses(Analyzable _classes) {
        StringList allSuperTypes_ = TypeUtil.getAllGenericSuperTypes(this,_classes);
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = StringList.getAllTypes(s).first();
            if (_classes.getClassBody(base_) instanceof StandardInterface) {
                allGenericSuperClasses_.add(s);
            }
        }
        return allGenericSuperClasses_;
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public StringList getDirectGenericSuperClasses(Analyzable _classes) {
        StringList classes_ = new StringList(getDirectSuperTypes());
        if (getDirectSuperTypes().isEmpty()) {
            classes_.add(_classes.getStandards().getAliasObject());
        }
        return classes_;
    }

    @Override
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

    @Override
    public StringList getAllGenericInterfaces(Analyzable _classes) {
        return getAllGenericSuperClasses(_classes);
    }

    @Override
    public StringList getAllInterfaces() {
        return getAllSuperClasses();
    }

    @Override
    public StringList getDirectGenericSuperTypes(Analyzable _classes) {
        return new StringList(getDirectSuperTypes());
    }
}
