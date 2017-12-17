package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
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
    protected StandardClass(String _name,StringMap<StandardField> _fields,
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
    public StringList getDirectInterfaces() {
        return directInterfaces;
    }
    public boolean isFinalType() {
        return finalType;
    }
    public boolean isAbstractType() {
        return abstractType;
    }
    @Override
    public StringList getAllSuperClasses(ContextEl _context) {
        StringList superClasses_ = new StringList();
        for (String s: getAllSuperTypes(_context)) {
            if (_context.getStandards().getStandards().getVal(s) instanceof StandardClass) {
                superClasses_.add(s);
            }
        }
        return superClasses_;
    }
    @Override
    public StringList getDirectSuperClasses(ContextEl _context) {
        return new StringList(superClass);
    }
    @Override
    public StringList getDirectInterfaces(ContextEl _context) {
        return new StringList(directInterfaces);
    }
    @Override
    public StringList getDirectSuperTypes(ContextEl _context) {
        StringList superTypes_ = new StringList();
        if (!StringList.quickEq(superClass, _context.getStandards().getAliasObject())) {
            superTypes_.add(superClass);
        }
        superTypes_.addAllElts(directInterfaces);
        return superTypes_;
    }
    @Override
    protected boolean mustImplement() {
        return !isAbstractType();
    }
    public StringList getAllInterfaces(ContextEl _conf) {
        StringList superClasses_ = new StringList();
        for (String s: getAllSuperTypes(_conf)) {
            if (_conf.getStandards().getStandards().getVal(s) instanceof StandardInterface) {
                superClasses_.add(s);
            }
        }
        return superClasses_;
    }
}
