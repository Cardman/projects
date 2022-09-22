package code.expressionlanguage.methods;

import code.expressionlanguage.functionid.ConstructorId;

public final class ConstructorIdVarArg {
    private final String className;
    private final ConstructorId constructorId;
    private final int naturalVararg;

    public ConstructorIdVarArg(String _className,ConstructorId _classMethodId, int _naturalVararg) {
        className = _className;
        constructorId = _classMethodId;
        naturalVararg = _naturalVararg;
    }

    public String getClassName() {
        return className;
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

    public boolean isStaticMethod() {
        return constructorId.isStaticMethod();
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }
}
