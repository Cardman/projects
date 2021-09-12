package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.StandardMethod;

public final class ExecStdFctContent {

    private final String methodName;

    private final ClassMethodId classMethodId;

    private final boolean staticMethod;

    private final String lastType;

    private final int naturalVararg;
    private final StandardMethod standardMethod;

    public ExecStdFctContent(AnaCallFctContent _cont, boolean _staticMethod) {
        standardMethod = _cont.getStandardMethod();
        methodName = _cont.getMethodName();
        classMethodId = _cont.getClassMethodId();
        staticMethod = _staticMethod;
        lastType = _cont.getLastType();
        naturalVararg = _cont.getNaturalVararg();
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
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
