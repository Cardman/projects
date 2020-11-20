package code.expressionlanguage.methods;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;

public final class ClassMethodIdVarArg {
    private final ClassMethodId classMethodId;
    private final int naturalVararg;

    public ClassMethodIdVarArg(ClassMethodId _classMethodId, int _naturalVararg) {
        classMethodId = _classMethodId;
        naturalVararg = _naturalVararg;
    }
    public String getClassName() {
        return classMethodId.getClassName();
    }

    public MethodId getConstraints() {
        return classMethodId.getConstraints();
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }
}
