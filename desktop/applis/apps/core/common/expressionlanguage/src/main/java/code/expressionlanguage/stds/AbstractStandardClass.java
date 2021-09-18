package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneClass;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.util.CustList;
import code.util.StringList;

public abstract class AbstractStandardClass extends StandardType implements GeneClass {

    private final StringList allSuperTypes = new StringList();

    private final String superClass;

    private final StringList directInterfaces = new StringList();

    private final boolean finalType;
    private final boolean abstractType;
    private final boolean hyperAbstractType;
    protected AbstractStandardClass(String _name,
                                    CustList<StandardConstructor> _constructors,
                                    CustList<StandardMethod> _methods,
                                    String _superClass, StdClassModifier _modifier, StdCaller _caller) {
        super(_name, _constructors, _methods,_caller);
        superClass = _superClass;
        finalType = false;
        abstractType = true;
        hyperAbstractType = _modifier == StdClassModifier.HYPER_ABSTRACT;
    }
    protected AbstractStandardClass(String _name,
                                    CustList<StandardConstructor> _constructors,
                                    CustList<StandardMethod> _methods,
                                    String _superClass, MethodModifier _modifier, StdCaller _caller) {
        super(_name, _constructors, _methods,_caller);
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
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

}
