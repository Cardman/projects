package code.expressionlanguage.methods;

import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;

public final class ConstructorIdVarArg {
    private String className;
    private ConstructorId constructorId;
    private final int naturalVararg;

    public ConstructorIdVarArg(String _className,ConstructorId _classMethodId, int _naturalVararg) {
        className = _className;
        constructorId = _classMethodId;
        naturalVararg = _naturalVararg;
    }

    public String getClassName() {
        return className;
    }

    public StringList getParametersTypes() {
        StringList list_ = new StringList();
        int len_ = constructorId.getParametersTypesLength();
        for (int i = 0; i < len_; i++) {
            list_.add(constructorId.getParametersType(i));
        }
        return list_;
    }

    public int getParametersTypesLength() {
        return constructorId.getParametersTypesLength();
    }

    public String getParametersType(int _index) {
        return constructorId.getParametersType(_index);
    }

    public boolean isVararg() {
        return constructorId.isVararg();
    }

    public String getName() {
        return constructorId.getName();
    }

    public boolean isStaticMethod() {
        return constructorId.isStaticMethod();
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }
}
