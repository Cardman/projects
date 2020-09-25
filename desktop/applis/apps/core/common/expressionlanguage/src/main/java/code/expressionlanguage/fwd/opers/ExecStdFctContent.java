package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ExecStdFctContent {

    private final String methodName;

    private final ClassMethodId classMethodId;

    private final boolean staticMethod;

    private final String lastType;

    private final int naturalVararg;
    public ExecStdFctContent(AnaCallFctContent _cont, boolean _staticMethod) {
        methodName = _cont.getMethodName();
        classMethodId = _cont.getClassMethodId();
        staticMethod = _staticMethod;
        lastType = _cont.getLastType();
        naturalVararg = _cont.getNaturalVararg();
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }
}
