package code.expressionlanguage.stds;

import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class StandardClass extends StandardType {

    private final String superClass;

    private final StringList directInterfaces = new StringList();

    private final boolean finalType;
    private final boolean abstractType;
    public StandardClass(String _name,StringMap<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
            ObjectMap<MethodId, StandardMethod> _methods,
            String _superClass, MethodModifier _modifier) {
        super(_name, _fields, _constructors, _methods);
        superClass = _superClass;
        finalType = _modifier == MethodModifier.FINAL;
        abstractType = _modifier == MethodModifier.ABSTRACT;
    }
    public String getSuperClass() {
        return superClass;
    }
    @Override
    public StringList getDirectInterfaces() {
        return directInterfaces;
    }
    public boolean isFinalType() {
        return finalType;
    }
    public boolean isAbstractType() {
        return abstractType;
    }
    public StringList getAllSuperClasses(LgNames _context) {
        String current_ = getName();
        String objectAlias_ = _context.getAliasObject();
        StringList superClasses_ = new StringList();
        while (!StringList.quickEq(current_, objectAlias_)) {
            StandardClass r_ = (StandardClass) _context.getStandards().getVal(current_);
            String superClass_ = r_.getSuperClass();
            superClasses_.add(superClass_);
            current_ = superClass_;
        }
        return superClasses_;
    }
    @Override
    public StringList getDirectSuperClasses() {
        return new StringList(superClass);
    }
    @Override
    public StringList getDirectSuperTypes() {
        StringList superTypes_ = new StringList();
        if (!superClass.isEmpty()) {
            superTypes_.add(superClass);
        }
        superTypes_.addAllElts(directInterfaces);
        return superTypes_;
    }
    @Override
    public boolean mustImplement() {
        return !isAbstractType();
    }
    public StringList getAllInterfaces(LgNames _conf) {
        StringList superClasses_ = new StringList();
        for (String s: getAllSuperTypes(_conf)) {
            if (_conf.getStandards().getVal(s) instanceof StandardInterface) {
                superClasses_.add(s);
            }
        }
        return superClasses_;
    }
}
