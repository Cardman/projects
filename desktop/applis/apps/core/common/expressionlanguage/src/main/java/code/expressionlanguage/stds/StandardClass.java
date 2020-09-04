package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneClass;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class StandardClass extends StandardType implements GeneClass {

    private final StringList allSuperTypes = new StringList();

    private final String superClass;

    private final StringList directInterfaces = new StringList();

    private final boolean finalType;
    private final boolean abstractType;
    public StandardClass(String _name,CustList<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
                         CustList<StandardMethod> _methods,
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

    public boolean isFinalStdType() {
        return finalType;
    }

    public boolean isAbstractStdType() {
        return abstractType;
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
    public StringList getAllGenericSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

}
