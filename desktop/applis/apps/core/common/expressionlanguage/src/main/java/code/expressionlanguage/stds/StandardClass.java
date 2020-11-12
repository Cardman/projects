package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneClass;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
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
    private final boolean hyperAbstractType;
    public StandardClass(String _name,CustList<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
                         CustList<StandardMethod> _methods,
            String _superClass, StdClassModifier _modifier) {
        super(_name, _fields, _constructors, _methods);
        superClass = _superClass;
        finalType = false;
        abstractType = _modifier == StdClassModifier.ABSTRACT;
        hyperAbstractType = _modifier == StdClassModifier.HYPER_ABSTRACT;
    }
    public StandardClass(String _name,CustList<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
                         CustList<StandardMethod> _methods,
            String _superClass, MethodModifier _modifier) {
        super(_name, _fields, _constructors, _methods);
        superClass = _superClass;
        finalType = _modifier == MethodModifier.FINAL;
        abstractType = false;
        hyperAbstractType = false;
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

    public boolean isHyperAbstractType() {
        return hyperAbstractType;
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
