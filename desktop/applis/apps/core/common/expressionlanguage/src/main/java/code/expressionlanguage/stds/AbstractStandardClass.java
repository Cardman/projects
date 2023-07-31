package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.util.CustList;

public abstract class AbstractStandardClass extends StandardType implements GeneType {

    private final String superClass;

    private final boolean finalType;
    private final boolean abstractType;
    private final boolean hyperAbstractType;
    protected AbstractStandardClass(String _name,
                                    CustList<StandardConstructor> _constructors,
                                    CustList<StandardMethod> _methods,
                                    String _superClass, StdClassModifier _modifier, DfInstancer _caller) {
        super(_name, _constructors, _methods,_caller);
        superClass = _superClass;
        finalType = false;
        abstractType = true;
        hyperAbstractType = _modifier == StdClassModifier.HYPER_ABSTRACT;
    }
    protected AbstractStandardClass(String _name,
                                    CustList<StandardConstructor> _constructors,
                                    CustList<StandardMethod> _methods,
                                    String _superClass, MethodModifier _modifier, DfInstancer _caller) {
        super(_name, _constructors, _methods,_caller);
        superClass = _superClass;
        finalType = _modifier == MethodModifier.FINAL;
        abstractType = false;
        hyperAbstractType = false;
    }
    public String getSuperClass() {
        return superClass;
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

}
