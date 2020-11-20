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
        return constructorId.getParametersTypes();
    }

    public int getParametersTypesLength() {
        return constructorId.getParametersTypes().size();
    }

    public String getParametersType(int _index) {
        return constructorId.getParametersTypes().get(_index);
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
