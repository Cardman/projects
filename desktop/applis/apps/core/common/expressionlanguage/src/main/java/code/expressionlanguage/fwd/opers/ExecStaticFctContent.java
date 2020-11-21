package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecStaticFctContent {

    private final String methodName;

    private final ExecStaticEltContent elts;

    private final String lastType;

    private final int naturalVararg;

    public ExecStaticFctContent(AnaTypeFct _fct, AnaCallFctContent _a) {
        methodName = _a.getMethodName();
        elts = new ExecStaticEltContent(_fct,_a.getClassName());
        lastType = _a.getLastType();
        naturalVararg = _a.getNaturalVararg();
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return elts.getClassName();
    }

    public MethodAccessKind getKind() {
        return elts.getKind();
    }
}
